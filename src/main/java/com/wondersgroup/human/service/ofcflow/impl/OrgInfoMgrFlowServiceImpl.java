/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoMgrFlowServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 单位编制信息维护流程表单 服务接口实现类
 * 创建人: jiang
 * 创建时间: 2018年9月18日14:38:51
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月18日14:38:51
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

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganNodeTypeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofcflow.InstitutionOrgFormationMgrFlow;
import com.wondersgroup.human.bo.ofcflow.OrgInfoMgrFlow;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.bo.organization.OrgInfoHistory;
import com.wondersgroup.human.repository.ofcflow.OrgInfoMgrFlowRepository;
import com.wondersgroup.human.service.ofcflow.OrgInfoMgrFlowService;
import com.wondersgroup.human.service.organization.OrgInfoHistoryService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.ofcflow.OrgInfoMgrFlowVO;

/**
 * @ClassName: OrgInfoMgrFlowServiceImpl
 * @Description: 单位信息维护流程表单 服务接口实现类
 * @author: jiang
 * @date: 2018年5月21日 上午11:05:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class OrgInfoMgrFlowServiceImpl extends GenericServiceImpl<OrgInfoMgrFlow> implements OrgInfoMgrFlowService {
	
	@Autowired
	private OrgInfoMgrFlowRepository orgInfoMgrFlowRepository;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	private OrganNodeTypeService organNodeTypeService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrgInfoHistoryService orgInfoHistoryService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public Page<OrgInfoMgrFlowVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit) {
		
		Page<OrgInfoMgrFlow> orgInfoPage = orgInfoMgrFlowRepository.findByCriteria(detachedCriteria, pageNo, limit);
		List<OrgInfoMgrFlowVO> voList = new ArrayList<>();
		for (OrgInfoMgrFlow s : orgInfoPage.getResult()) {
			OrgInfoMgrFlowVO vo = new OrgInfoMgrFlowVO(s);
			voList.add(vo);
		}
		Page<OrgInfoMgrFlowVO> page = new Page<>(orgInfoPage.getStart(), orgInfoPage.getCurrentPageSize(),
				orgInfoPage.getTotalSize(), orgInfoPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public void startOrgApplyFlow(OrgInfoMgrFlow orgInfoMgrFlow) {
		
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(orgInfoMgrFlow.getId())) {
			saveOrUpdate(orgInfoMgrFlow);// 保存业务数据
		}
		// 创建初始流程对象
		FlowRecord flow = new FlowRecord();
		flow.setAppNodeId(appNode.getId());// 流程业务所在系统
		flow.setBusId(orgInfoMgrFlow.getId());// 流程业务ID
		flow.setBusName("创建组织机构单位（" + orgInfoMgrFlow.getUnitBasicShortName() + "）申请流程");// 流程业务名称
		flow.setBusType("H004001001");// 流程业务类型
		flow.setTargetOrganNode(userOrg);// 流程业务目标组织
		flow.setTargetSecurityUser(user);// 流程业务目标人员
		flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_ORG);// 流程分类
		flow = workflowService.createFlowRecord(flow, "APPLY_WAIT_SUBMIT");// 初始节点
		
		orgInfoMgrFlow.setStatus(OrgInfoMgrFlow.power.get(flow.getOperationCode()));// 实际有权限的操作节点
		orgInfoMgrFlow.setFlowRecord(flow);// 修改当前业务的流程节点
		orgInfoMgrFlowRepository.merge(orgInfoMgrFlow);
	}
	
	@Override
	public void startOrgAdjustFlow(OrgInfoMgrFlow orgInfoMgrFlow) {
		
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(orgInfoMgrFlow.getId())) {
			saveOrUpdate(orgInfoMgrFlow);// 保存业务数据
		}
		// 创建初始流程对象
		FlowRecord flow = new FlowRecord();
		flow.setAppNodeId(appNode.getId());// 流程业务所在系统
		flow.setBusId(orgInfoMgrFlow.getId());// 流程业务ID
		flow.setBusName("调整组织机构单位（" + orgInfoMgrFlow.getUnitBasicShortName() + "）申请流程");// 流程业务名称
		flow.setBusType("H004001002");// 流程业务类型
		flow.setTargetOrganNode(userOrg);// 流程业务目标组织
		flow.setTargetSecurityUser(user);// 流程业务目标人员
		flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_ORG);// 流程分类
		flow = workflowService.createFlowRecord(flow, "ADJUST_WAIT_SUBMIT");// 初始节点
		
		orgInfoMgrFlow.setStatus(OrgInfoMgrFlow.power.get(flow.getOperationCode()));// 实际有权限的操作节点
		orgInfoMgrFlow.setFlowRecord(flow);// 修改当前业务的流程节点
		orgInfoMgrFlowRepository.merge(orgInfoMgrFlow);
		
	}
	
	@Override
	public void commitAuditApplyFlow(OrgInfoMgrFlow orgInfoMgrFlow, String opinion, String result) {
		
		FlowRecord flow = orgInfoMgrFlow.getFlowRecord();
		flow.setOpinion(opinion);
		flow.setResult(result);
		flow = workflowService.completeWorkItem(flow);// 提交下个节点
		if (flow == null) {
			if (FlowRecord.PASS.equals(result)) {
				// 流程正常结束，做创建机构通过操作
				executeAppLyFlowPassSaveBusiness(orgInfoMgrFlow);
				orgInfoMgrFlow.setStatus(OrgInfoMgrFlow.STATUS_ORG_INFO_MGR_FLOW_TRIAL2);
			} else if (FlowRecord.STOP.equals(result)) {
				orgInfoMgrFlow.setStatus(FlowRecord.BUS_STOP);
				
				// 流程非正常结束，发送通知
				String title = messageSource.getMessage("stopFlowTitle", new Object[] {
						"创建组织机构单位申请"
				}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[] {
						"创建组织机构单位申请"
				}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(
						new AnnouncementEventData(true, orgInfoMgrFlow.getCreater(), title, content, "","创建组织机构单位")));
				
			}
			
		} else {
			// 流程未结束
			orgInfoMgrFlow.setStatus(OrgInfoMgrFlow.power.get(flow.getOperationCode()));
			orgInfoMgrFlow.setFlowRecord(flow);
		}
		orgInfoMgrFlowRepository.update(orgInfoMgrFlow);
	}
	
	@Override
	public void commitAuditAdjustFlow(OrgInfoMgrFlow orgInfoMgrFlow, String opinion, String result) {
		
		FlowRecord flow = orgInfoMgrFlow.getFlowRecord();
		flow.setOpinion(opinion);
		flow.setResult(result);
		flow = workflowService.completeWorkItem(flow);// 提交下个节点
		if (flow == null) {
			if (FlowRecord.PASS.equals(result)) {
				// 流程正常结束，做创建机构通过操作
				executeAdjustFlowPassSaveBusiness(orgInfoMgrFlow);
				orgInfoMgrFlow.setStatus(OrgInfoMgrFlow.STATUS_ORG_INFO_MGR_FLOW_TRIAL2);
			} else if (FlowRecord.STOP.equals(result)) {
				orgInfoMgrFlow.setStatus(FlowRecord.BUS_STOP);
				
				// 流程非正常结束，发送通知
				String title = messageSource.getMessage("stopFlowTitle", new Object[] {
						"调整组织机构单位申请"
				}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[] {
						"调整组织机构单位申请"
				}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(
						new AnnouncementEventData(true, orgInfoMgrFlow.getCreater(), title, content, "","调整组织机构单位")));
				
			}
		} else {
			// 流程未结束
			orgInfoMgrFlow.setStatus(OrgInfoMgrFlow.power.get(flow.getOperationCode()));
			orgInfoMgrFlow.setFlowRecord(flow);
		}
		orgInfoMgrFlowRepository.update(orgInfoMgrFlow);
		
	}
	
	/**
	 * @Title: executeAdjustFlowPassSaveBusiness
	 * @Description: 调整申请流程通过后保存机构
	 * @param orgInfoMgrFlow
	 * @return: void
	 */
	private void executeAdjustFlowPassSaveBusiness(OrgInfoMgrFlow orgInfoMgrFlow) {
		
		try {
			// 获得即将修改的机构信息
			OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", orgInfoMgrFlow.getOrgan().getId());
			
			// 将修改前的值赋值给历史表
			OrgInfoHistory orgInfoHistory = new OrgInfoHistory();
			BeanUtils.copyProperties(orgInfo, orgInfoHistory, new String[] {
					"id"
			});
			// 将修改后的值赋值给当前单位信息表
			BeanUtils.copyProperties(orgInfoMgrFlow, orgInfo, new String[] {
					"id"
			});
			
			// 修改organNode属性
			OrganNode organNode = organNodeService.get(orgInfo.getOrgan().getId());
			organNode.setCode(orgInfo.getXydm());
			organNode.setName(orgInfo.getUnitBasicSimpleName());
			organNode.setAllName(orgInfo.getUnitBasicName());
			
			// 不同机构性质级别，使用不同的code码
			String unitPropertyLevelCode = orgInfoMgrFlow.getUnitPropertyLevel().getCode();
			if (unitPropertyLevelCode.equals(CommonConst.UNIT_NATURE_D_CLASS)) {
				organNode.setOrganNodeType(
						organNodeTypeService.getOrganNodeTypeByCode(CommonConst.ORGAN_TYPE_D_CLASS_CODE));
			} else if (unitPropertyLevelCode.equals(CommonConst.UNIT_NATURE_SY)) {
				organNode.setOrganNodeType(
						organNodeTypeService.getOrganNodeTypeByCode(CommonConst.ORGAN_TYPE_UNIT_CODE));
			} else if (unitPropertyLevelCode.equals(CommonConst.UNIT_NATURE_ENTERPRISE)) {
				organNode.setOrganNodeType(
						organNodeTypeService.getOrganNodeTypeByCode(CommonConst.ORGAN_TYPE_ENTERPRISE_CODE));
			}
			
			orgInfoService.update(orgInfo);
			organNodeService.update(organNode);
			
			// 调整历史信息表
			orgInfoHistory.setOrgInfo(orgInfo);
			orgInfoHistory.setOrgInfoMgrFlow(orgInfoMgrFlow);
			orgInfoHistoryService.save(orgInfoHistory);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("新建机构失败，请联系管理员");
		}
	}
	
	/**
	 * @Title: executeAppLyFlowPassSaveBusiness
	 * @Description: 新增申请流程通过后保存机构
	 * @param orgInfoMgrFlow
	 * @return: void
	 */
	private void executeAppLyFlowPassSaveBusiness(OrgInfoMgrFlow orgInfoMgrFlow) {
		
		try {
			// 插入OrganNode表
			OrganNode organNode = new OrganNode();
			organNode.setCode(orgInfoMgrFlow.getXydm());
			organNode.setName(orgInfoMgrFlow.getUnitBasicSimpleName());
			organNode.setAllName(orgInfoMgrFlow.getUnitBasicName());
			organNode.setCreater(SecurityUtils.getUserId());
			// 不同机构性质级别，使用不同的code码
			String unitPropertyLevelCode = orgInfoMgrFlow.getUnitPropertyLevel().getCode();
			if (unitPropertyLevelCode.equals(CommonConst.UNIT_NATURE_D_CLASS)) {
				organNode.setOrganNodeType(
						organNodeTypeService.getOrganNodeTypeByCode(CommonConst.ORGAN_TYPE_D_CLASS_CODE));
			} else if (unitPropertyLevelCode.equals(CommonConst.UNIT_NATURE_SY)) {
				organNode.setOrganNodeType(
						organNodeTypeService.getOrganNodeTypeByCode(CommonConst.ORGAN_TYPE_UNIT_CODE));
			} else if (unitPropertyLevelCode.equals(CommonConst.UNIT_NATURE_ENTERPRISE)) {
				organNode.setOrganNodeType(
						organNodeTypeService.getOrganNodeTypeByCode(CommonConst.ORGAN_TYPE_ENTERPRISE_CODE));
			}
			
			// 插入orgModel
			organNodeService.addOrganNodeToTree(organNode, orgInfoMgrFlow.getParentOrgan(),
					organizationService.getDefaultOrganTree());
			
			// 插入B02
			OrgInfo orgInfo = new OrgInfo();
			
			BeanUtils.copyProperties(orgInfoMgrFlow, orgInfo);
			orgInfo.setId(null);
			orgInfo.setOrgan(organNode);
			orgInfoService.save(orgInfo);
			
			// 修改orgInfoMgrFlow对象
			orgInfoMgrFlow.setOrgan(organNode);
			orgInfoMgrFlowRepository.update(orgInfoMgrFlow);
			
			// 调整历史信息表
			OrgInfoHistory orgInfoHistory = new OrgInfoHistory();
			orgInfoHistory.setOrgInfo(orgInfo);
			orgInfoHistory.setOrgInfoMgrFlow(orgInfoMgrFlow);
			orgInfoHistoryService.save(orgInfoHistory);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("新建机构失败，请联系管理员");
		}
		
	}
	
}
