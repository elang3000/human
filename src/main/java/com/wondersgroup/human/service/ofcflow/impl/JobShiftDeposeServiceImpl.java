
package com.wondersgroup.human.service.ofcflow.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.bo.ofcflow.JobShiftDepose;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofcflow.JobShiftDeposeService;
import com.wondersgroup.human.service.organization.FormationControlService;

@Service
public class JobShiftDeposeServiceImpl extends GenericServiceImpl<JobShiftDepose>
        implements JobShiftDeposeService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FormationControlService formationControlService;

	@Override
	public void updateDeposeFlow(JobShiftDepose jobShiftDepose, String opinion, String result) {

		
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(jobShiftDepose.getId())) {
			saveOrUpdate(jobShiftDepose);// 保存业务数据
		}
		FlowRecord flow;
		if (jobShiftDepose.getFlowRecord() == null) {// 提交环节，先生成流程数据
			// 设置申请人员的单位
			jobShiftDepose.setSourceOrganNode(userOrg);
//			jobShiftDepose.setApprovalDismissalCode(approvalDismissalCode);
			jobShiftDepose.setNominationDismissalCode(userOrg.getCode());
			jobShiftDepose.setNominationDismissalName(userOrg.getAllName());
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(jobShiftDepose.getId());// 流程业务ID
			flow.setBusName(userOrg.getAllName() + "职务变动信息");// 流程业务名称
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);// 流程业务目标人员
			flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_DEPOSE);// 流程业务类型
			flow = workflowService.createFlowRecord(flow, JobShift.JOBSHIFT_DEMOTE_STEP1);// 初始节点,降职和轮岗和免职使用相同的流程,起始节点一样
			
		} else {
			flow = jobShiftDepose.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(result);
			flow = workflowService.completeWorkItem(flow);// 提交下个节点
		}
		if (null == flow) {
			//数据库中的post
			Post oldPost = jobShiftDepose.getPost();
			System.out.println(jobShiftDepose.getPost().getId());
			oldPost.setNominationDismissalName(jobShiftDepose.getNominationDismissalName());
			oldPost.setNominationDismissalCode(jobShiftDepose.getNominationDismissalCode());
			oldPost.setNominationDismissalDate(jobShiftDepose.getNominationDismissalDate());
			oldPost.setNominationDismissalNumber(jobShiftDepose.getNominationDismissalNumber());
//			jobShiftDepose.getApprovalDismissalName();
//			jobShiftDepose.getApprovalDismissalCode();
			oldPost.setApprovalDismissalDate(jobShiftDepose.getApprovalDismissalDate());
			oldPost.setApprovalDismissalNumber(jobShiftDepose.getApprovalDismissalNumber());
			oldPost.setDismissalType(jobShiftDepose.getDismissalType());
			oldPost.setDismissalReason(jobShiftDepose.getDismissalReason());
			oldPost.setDismissalChange(jobShiftDepose.getDismissalChange());
			oldPost.setDismissalMode(jobShiftDepose.getDismissalMode());
			postService.update(oldPost);
			
			//FIXME 免职之后 是否需要让出编制?
			//旧职位
			Post formerPost = postService.load(jobShiftDepose.getPost().getId());
			//解锁旧编制
			formationControlService.executeUnlockPostOutNum(jobShiftDepose.getSourceOrganNode().getId(), formerPost.getPostCode().getCode(), jobShiftDepose.getPost().getIsLowToHigh());
			//插入数据
			formationControlService.executeOutPost(jobShiftDepose.getSourceOrganNode().getId(), formerPost.getPostCode().getCode(), jobShiftDepose.getPost().getIsLowToHigh());
		}
		jobShiftDepose.setFlowRecord(flow);
		this.update(jobShiftDepose);
		
			
	}
	
	
	
}
