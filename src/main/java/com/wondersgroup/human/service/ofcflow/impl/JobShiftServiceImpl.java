
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.JobChange;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.service.ofc.JobChangeService;
import com.wondersgroup.human.service.ofcflow.JobShiftService;

@Service
public class JobShiftServiceImpl extends GenericServiceImpl<JobShift>
        implements JobShiftService, ApplicationContextAware {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private JobChangeService jobChangeService;
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@Override
	public void updateDemoteFlow(JobShift jobShift, String opinion, String result,boolean isShift) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(jobShift.getId())) {
			saveOrUpdate(jobShift);// 保存业务数据
		}
		FlowRecord flow;
		if (jobShift.getStatus() == null && jobShift.getFlowRecord() == null) {// 提交环节，先生成流程数据
			// 设置申请人员的单位
			jobShift.setSourceOrganNode(userOrg);
			jobShift.setStatus(1);
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(jobShift.getId());// 流程业务ID
			flow.setBusName(userOrg.getAllName() + "职务变动信息");// 流程业务名称
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);// 流程业务目标人员
			if(isShift){
				jobShift.setPostTenureChange(codeInfoService.getCodeInfoByDictTypeAndName(DictTypeCodeContant.CODE_TYPE_POST_CHANGE, "轮岗"));
				flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT);// 流程业务类型
			}else{
				jobShift.setPostTenureChange(codeInfoService.getCodeInfoByDictTypeAndName(DictTypeCodeContant.CODE_TYPE_POST_CHANGE, "降职"));
				flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_DEMOTE);// 流程业务类型
			}
			flow = workflowService.createFlowRecord(flow, JobShift.JOBSHIFT_DEMOTE_STEP1);// 初始节点,降职和轮岗使用相同的流程,起始节点一样
			
		} else {
			jobShift.setStatus(jobShift.getStatus() + 1);
			flow = jobShift.getFlowRecord();
			flow = jobShift.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(result);
			flow = workflowService.completeWorkItem(flow);// 提交下个节点
		}
		if (null == flow) {
			//流程结束,插入子集信息
			JobChange change = new JobChange();
			BeanUtils.copyProperties(jobShift, change);
			change.setFormerUnitCode(jobShift.getSourceOrganNode().getId());
			change.setFormerUnitName(jobShift.getSourceOrganNode().getAllName());
			change.setNewUnitCode(jobShift.getSourceOrganNode().getId());
			change.setNewUnitName(jobShift.getSourceOrganNode().getAllName());
			change.setNewPostName(jobShift.getNewPostCode().getName());
			jobChangeService.save(change);
			//FIXME 升职后将原职务设置为非现任职务,将新职务设置为现任职务 ???是否需要让出老职务的编制
			System.out.println("流程结束");
		}
		jobShift.setFlowRecord(flow);
		this.update(jobShift);
		
	}
	
	@Override
	public void updatePromoteFlow(JobShift jobShift, String opinion, String result){
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(jobShift.getId())) {
			save(jobShift);// 保存业务数据
		}
		FlowRecord flow;
		if (jobShift.getFlowRecord() == null) {// 提交环节，先生成流程数据
			// 设置申请人员的单位
			jobShift.setSourceOrganNode(userOrg);
			jobShift.setStatus(1);
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(jobShift.getId());// 流程业务ID
			flow.setBusName(userOrg.getAllName() + "职务变动信息");// 流程业务名称
			flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_PROMOTE);// 流程业务类型
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);// 流程业务目标人员
			flow = workflowService.createFlowRecord(flow, JobShift.JOBSHIFT_PROMOTE_STEP1);// 初始节点
		} else {
			flow = jobShift.getFlowRecord();
			jobShift.setStatus(jobShift.getStatus() + 1);
			flow.setOpinion(opinion);
			flow.setResult(result);
			if (result.equals(FlowRecord.PASS)
			        && (jobShift.getFlowRecord().getOperationCode().equals(JobShift.JOBSHIFT_PROMOTE_STEP6)
			                || jobShift.getFlowRecord().getOperationCode().equals(JobShift.JOBSHIFT_PROMOTE_STEP11))) {
				flow = workflowService.completeFlowRecordByAppoint(flow, jobShift.getSourceOrganNode());
			} else {
				flow = workflowService.completeWorkItem(flow);// 提交下个节点
			}
		}
		if (null == flow) {
			String title = messageSource.getMessage(JobShift.PROMOTE_TITLE, new Object[] {
			        jobShift.getServant().getName()
			}, Locale.CHINESE);
			String content = messageSource.getMessage(JobShift.PROMOTE_CONTENT, new Object[] {
			        jobShift.getServant().getName()
			}, Locale.CHINESE);
			applicationContext.publishEvent(new AnnouncementEventData(true, jobShift.getCreater(), title, content, ""));
			//流程结束,插入子集信息
			JobChange change = new JobChange();
			BeanUtils.copyProperties(jobShift, change);
			change.setFormerUnitCode(jobShift.getSourceOrganNode().getId());
			change.setFormerUnitName(jobShift.getSourceOrganNode().getAllName());
			change.setNewUnitCode(jobShift.getSourceOrganNode().getId());
			change.setNewUnitName(jobShift.getSourceOrganNode().getAllName());
			change.setNewPostName(jobShift.getNewPostCode().getName());
			jobChangeService.save(change);
			//FIXME 升职后将原职务设置为非现任职务,将新职务设置为现任职务  ???是否需要让出老职务的编制
			System.out.println("流程结束");
		}
		jobShift.setFlowRecord(flow);
		this.update(jobShift);
		
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	
}
