/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: InstitutionOrgFormationMgrFlowServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 事业单位编制信息维护流程表单 服务接口实现类
 * 创建人: jiang
 * 创建时间: 2018年12月5日15:02:47
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月5日15:02:50
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofcflow.ImportantEventApply;
import com.wondersgroup.human.bo.ofcflow.InstitutionOrgFormationMgrFlow;
import com.wondersgroup.human.bo.ofcflow.OrgFormationMgrFlow;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormationHistory;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.repository.ofcflow.InstitutionOrgFormationMgrFlowRepository;
import com.wondersgroup.human.service.ofcflow.InstitutionOrgFormationMgrFlowService;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationHistoryService;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.ofcflow.InstitutionOrgFormationMgrFlowVO;

/**
 * @ClassName: InstitutionOrgFormationMgrFlowServiceImpl
 * @Description: 事业单位编制信息维护流程表单 服务接口实现类
 * @author: jiang
 * @date: 2018年12月5日15:03:10
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class InstitutionOrgFormationMgrFlowServiceImpl extends GenericServiceImpl<InstitutionOrgFormationMgrFlow>
		implements InstitutionOrgFormationMgrFlowService {
	
	@Autowired
	private InstitutionOrgFormationMgrFlowRepository institutionOrgFormationMgrFlowRepository;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private InstitutionOrgFormationService institutionOrgFormationService;
	
	@Autowired
	private InstitutionOrgFormationHistoryService institutionOrgFormationHistoryService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public Page<InstitutionOrgFormationMgrFlowVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit) {
		
		Page<InstitutionOrgFormationMgrFlow> orgFormationPage = institutionOrgFormationMgrFlowRepository.findByCriteria(detachedCriteria,
				pageNo, limit);
		List<InstitutionOrgFormationMgrFlowVO> voList = new ArrayList<>();
		for (InstitutionOrgFormationMgrFlow s : orgFormationPage.getResult()) {
			InstitutionOrgFormationMgrFlowVO vo = new InstitutionOrgFormationMgrFlowVO(s);
			voList.add(vo);
		}
		Page<InstitutionOrgFormationMgrFlowVO> page = new Page<>(orgFormationPage.getStart(),
				orgFormationPage.getCurrentPageSize(), orgFormationPage.getTotalSize(), orgFormationPage.getPageSize(),
				voList);
		return page;
	}
	
	@Override
	public void startOrgFormationAdjustFlow(InstitutionOrgFormationMgrFlow orgFormationMgrFlow) {
		
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(orgFormationMgrFlow.getId())) {
			saveOrUpdate(orgFormationMgrFlow);// 保存业务数据
		}
		OrgInfo orgInfo = orgInfoService.get(orgFormationMgrFlow.getOrgInfo().getId());
		// 创建初始流程对象
		FlowRecord flow = new FlowRecord();
		flow.setAppNodeId(appNode.getId());// 流程业务所在系统
		flow.setBusId(orgFormationMgrFlow.getId());// 流程业务ID
		flow.setBusName("调整事业编制（" + orgInfo.getUnitBasicName() + "）申请流程");// 流程业务名称
		flow.setBusType("H004001005");// 流程业务类型
		flow.setTargetOrganNode(userOrg);// 流程业务目标组织
		flow.setTargetSecurityUser(user);// 流程业务目标人员
		flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_ORG);// 流程分类
		flow = workflowService.createFlowRecord(flow, "INST_FORMATION_ADJUST_WAIT_SUBMIT");// 初始节点
		
		orgFormationMgrFlow.setStatus(InstitutionOrgFormationMgrFlow.power.get(flow.getOperationCode()));// 实际有权限的操作节点
		orgFormationMgrFlow.setFlowRecord(flow);// 修改当前业务的流程节点
		institutionOrgFormationMgrFlowRepository.merge(orgFormationMgrFlow);
		
	}
	
	@Override
	public void commitAuditAdjustFlow(InstitutionOrgFormationMgrFlow orgFormationMgrFlow, String opinion, String result) {
		
		FlowRecord flow = orgFormationMgrFlow.getFlowRecord();
		flow.setOpinion(opinion);
		flow.setResult(result);
		flow = workflowService.completeWorkItem(flow);// 提交下个节点
		if (flow == null) {
			if(FlowRecord.PASS.equals(result)){
				// 流程结束，做创建机构单位申请通过操作
				
				executeAdjustFlowPassSaveBusiness(orgFormationMgrFlow);
				orgFormationMgrFlow.setStatus(OrgFormationMgrFlow.STATUS_ORG_FORMATION_MGR_FLOW_TRIAL2);
			}else if(FlowRecord.STOP.equals(result)){
				orgFormationMgrFlow.setStatus(FlowRecord.BUS_STOP);
				
				//流程非正常结束，发送通知
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"调整事业编制申请"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"调整事业编制申请"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, orgFormationMgrFlow.getCreater(), title, content, "","调整事业编制")));
				
			}
			
		} else {
			// 流程未结束
			orgFormationMgrFlow.setStatus(InstitutionOrgFormationMgrFlow.power.get(flow.getOperationCode()));
			orgFormationMgrFlow.setFlowRecord(flow);
		}
		institutionOrgFormationMgrFlowRepository.update(orgFormationMgrFlow);
		
	}
	
	/**
	 * @Title: executeAdjustFlowPassSaveBusiness
	 * @Description: 调整申请流程通过后保存业务
	 * @param orgInfoMgrFlow
	 * @return: void
	 */
	private void executeAdjustFlowPassSaveBusiness(InstitutionOrgFormationMgrFlow orgFormationMgrFlow) {
		
		InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
				orgFormationMgrFlow.getOrgInfo().getId());
		if (orgFormation == null)
			orgFormation = new InstitutionOrgFormation();
		
		//将修改前的值赋值给历史表
		InstitutionOrgFormationHistory orgFormationHistory = new InstitutionOrgFormationHistory();
		BeanUtils.copyProperties(orgFormation, orgFormationHistory, new String[] {
				"id"
		});
		
		// 将调整后的值赋值于机构编制表
		BeanUtils.copyProperties(orgFormationMgrFlow, orgFormation, new String[] {
				"id"
		});
		institutionOrgFormationService.saveOrUpdate(orgFormation);
		
		//调整历史信息表
		orgFormationHistory.setOrgInfo(orgFormationMgrFlow.getOrgInfo());
		orgFormationHistory.setInstOrgFormationMgrFlow(orgFormationMgrFlow);
		institutionOrgFormationHistoryService.save(orgFormationHistory);
	}
	
}
