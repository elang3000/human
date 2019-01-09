/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgFormationMgrFlowController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 单位编制信息流程表单 控制器
 * 创建人: jiang
 * 创建时间: 2018年9月19日15:24:42
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月19日15:24:44
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.OrgFormationMgrFlow;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgFormationHistory;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.ofcflow.OrgFormationMgrFlowService;
import com.wondersgroup.human.service.organization.OrgFormationHistoryService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.ofcflow.OrgFormationMgrFlowVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: OrgFormationMgrFlowController
 * @Description: 单位编制信息流程表单 控制器
 * @author: jiang
 * @date: 2018年9月12日15:45:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/orgFormationFlow")
@Controller
public class OrgFormationMgrFlowController extends GenericController {
	
	@Autowired
	private OrgFormationMgrFlowService orgFormationMgrFlowService;
	
	@Autowired
	private OrgFormationService orgFormationService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private OrgFormationHistoryService orgFormationHistoryService;
	
	/**
	 * 组织机构编制信息维护 返回视图界面
	 */
	private static final String ORG_FORMATION_MGR_FlOW_LIST = "models/ofcflow/orgFormation/orgFormationMgrFlowList";
	
	private static final String ORG_FORMATION_MGR_FlOW_ADJUST = "models/ofcflow/orgFormation/orgFormationMgrFlowAdjustPage";
	
	private static final String ORG_FORMATION_MGR_FlOW_ADJUST_VIEW = "models/ofcflow/orgFormation/orgFormationMgrFlowAdjustView";
	
	private static final String ORG_FORMATION_MGR_FlOW_ADJUST_APPROVAL_PAGE = "models/ofcflow/orgFormation/orgFormationMgrFlowAdjustApprovalPage";
	
	/**
	 * @Title: orgFormationlist
	 * @Description: 单位编制维护表单信息列表
	 * @return: String
	 */
	@RequestMapping("/list")
	public String list() {
		
		return ORG_FORMATION_MGR_FlOW_LIST;
	}
	
	/**
	 * @Title: adjustPage
	 * @Description: 申请机构调整信息维护表单 页面
	 * @return: String
	 */
	@RequestMapping("/adjustPage")
	public String adjustPage(Model model, String organId, String id) {
		
		if (StringUtils.isNotBlank(id)) {
			
			OrgFormationMgrFlow orgFormationMgrFlow = orgFormationMgrFlowService.get(id);
			
			OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id",
					orgFormationMgrFlow.getOrgInfo().getId());
			if (orgFormation == null)
				orgFormation = new OrgFormation();
			model.addAttribute("orgFormationMgrFlow", orgFormationMgrFlow);
			model.addAttribute("orgFormation", orgFormation);
		} else {
			// 首先判断选择的节点是否具有单位信息
			OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", organId);
			if (orgInfo == null) { throw new BusinessException("单位信息为空，无法操作编制调整业务！"); }
			
			OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", orgInfo.getId());
			if (orgFormation == null)
				orgFormation = new OrgFormation();
			
			// 复制到调整申请表
			OrgFormationMgrFlow orgFormationMgrFlow = new OrgFormationMgrFlow();
			BeanUtils.copyProperties(orgFormation, orgFormationMgrFlow, new String[] {
					"id","planPath"
			});
			
			orgFormationMgrFlow.setOrgInfo(orgInfo);
			model.addAttribute("orgFormation", orgFormation);
			model.addAttribute("orgFormationMgrFlow", orgFormationMgrFlow);
		}
		return ORG_FORMATION_MGR_FlOW_ADJUST;
	}
	
	/**
	 * @Title: adjustSave
	 * @Description: 调整申请表单保存功能
	 * @param temp
	 * @return: AjaxResult
	 */
	@Log(title = "编辑行政编制调整申请", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/adjustSave")
	public AjaxResult adjustSave(OrgFormationMgrFlow temp) {
		
		// 判断当前单位是否有编制信息，否的话为新增编制,并且默认流程状态为待提交
		OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", temp.getOrgInfo().getId());
		if (orgFormation == null) {
			temp.setOptionType(
					dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_ORGFORMATION_MAINTIAN_TYPE));
		} else {
			temp.setOptionType(
					dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_HUMAN_ORGFORMATION_MAINTIAN_TYPE));
		}
		
		temp.setStatus(OrgFormationMgrFlow.STATUS_ORG_FORMATION_MGR_FLOW_STATE);
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 暂存修改
				OrgFormationMgrFlow orgFormationMgrFlow = orgFormationMgrFlowService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, orgFormationMgrFlow);
				DictUtils.operationCodeInfo(orgFormationMgrFlow);// 将CodeInfo中id为空的属性 设置为null
				orgFormationMgrFlowService.saveOrUpdate(orgFormationMgrFlow);// 保存
			} else {// 新增暂存
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				orgFormationMgrFlowService.saveOrUpdate(temp);
			}
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: adjustSubmit
	 * @Description: 请求调整申请提交
	 * @param temp
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "提交行政编制调整申请流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/adjustSubmit")
	public AjaxResult adjustSubmit(OrgFormationMgrFlow flow, HttpServletRequest request) {
		
		// 判断当前单位是否有编制信息，否的话为新增编制,并且默认流程状态为待提交
		OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", flow.getOrgInfo().getId());
		if (orgFormation == null) {
			flow.setOptionType(
					dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_ORGFORMATION_MAINTIAN_TYPE));
		} else {
			flow.setOptionType(
					dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_HUMAN_ORGFORMATION_MAINTIAN_TYPE));
		}
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(flow.getId())) {
				DictUtils.operationCodeInfo(flow);// 将CodeInfo中id为空的属性 设置为null
				flow.setId(null);
				orgFormationMgrFlowService.startOrgFormationAdjustFlow(flow);
				;
			} else {
				OrgFormationMgrFlow orgFormationMgrFlow = orgFormationMgrFlowService.get(flow.getId());
				BeanUtils.copyPropertiesIgnoreNull(flow, orgFormationMgrFlow);
				DictUtils.operationCodeInfo(orgFormationMgrFlow);// 将CodeInfo中id为空的属性 设置为null
				if (orgFormationMgrFlow.getFlowRecord() != null) {
					orgFormationMgrFlowService.commitAuditAdjustFlow(orgFormationMgrFlow, "再次提交", FlowRecord.PASS);
				} else {
					orgFormationMgrFlowService.startOrgFormationAdjustFlow(orgFormationMgrFlow);
				}
				
			}
			result.setMessage("操作成功！");
		} catch (BusinessException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("操作失败！");
		}
		return result;
	}
	
	@Log(title = "审批行政编制调整申请流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/auditAdjustFlow")
	public AjaxResult auditAdjustFlow(OrgFormationMgrFlow temp, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");// 审批意见
		String r = request.getParameter("result");// 审批结果
		try {
			OrgFormationMgrFlow flow = orgFormationMgrFlowService.get(temp.getId());
			orgFormationMgrFlowService.commitAuditAdjustFlow(flow, opinion, r);
			result.setMessage("操作成功！");
		} catch (BusinessException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("操作失败！");
		}
		return result;
	}
	
	/**
	 * @Title: adjustApprovalPage
	 * @Description: 调整请求审批查看
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/adjustApprovalPage")
	public String adjustApprovalPage(Model model, String id) {
		
		FlowRecord flowRecord = flowRecordService.get(id);
		String busId = flowRecord.getBusId();
		OrgFormationMgrFlow orgFormationMgrFlow = orgFormationMgrFlowService.get(busId);
		
		OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id",
				orgFormationMgrFlow.getOrgInfo().getId());
		if (orgFormation == null)
			orgFormation = new OrgFormation();
		
		model.addAttribute("orgFormationMgrFlow", orgFormationMgrFlow);
		model.addAttribute("orgFormation", orgFormation);
		if (orgFormationMgrFlow.getStatus() == OrgFormationMgrFlow.STATUS_ORG_FORMATION_MGR_FLOW_STATE) {
			return ORG_FORMATION_MGR_FlOW_ADJUST;
		} else {
			return ORG_FORMATION_MGR_FlOW_ADJUST_APPROVAL_PAGE;
		}
	}
	
	/**
	 * @Title: adjustView
	 * @Description: 调整申请查看
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/adjustView")
	public String adjustView(Model model, String id) {
		
		OrgFormationMgrFlow orgFormationMgrFlow = orgFormationMgrFlowService.get(id);
		
		OrgFormationHistory orgFormationHistory = orgFormationHistoryService.findUniqueBy("orgFormationMgrFlow.id",
				orgFormationMgrFlow.getId());
		if (orgFormationHistory == null) {
			OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id",
					orgFormationMgrFlow.getOrgInfo().getId());
			if (orgFormation == null)
				orgFormation = new OrgFormation();
			model.addAttribute("orgFormation", orgFormation);
		} else {
			model.addAttribute("orgFormation", orgFormationHistory);
		}
		model.addAttribute("orgFormationMgrFlow", orgFormationMgrFlow);
		
		return ORG_FORMATION_MGR_FlOW_ADJUST_VIEW;
	}
	
	/**
	 * @Title: delete
	 * @Description: 删除待提交的流程
	 * @param temp 流程信息
	 * @return: AjaxResult
	 */
	@Log(title = "删除行政编制调整申请", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			OrgFormationMgrFlow orgFormationMgrFlow = orgFormationMgrFlowService.get(id);
			orgFormationMgrFlowService.delete(orgFormationMgrFlow);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 机构信息维护流程表单 列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查询行政编制调整申请", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<OrgFormationMgrFlowVO> pageList(OrgFormationMgrFlow orgFormationMgrFlow, Integer limit, Integer page,
			String organId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrgFormationMgrFlow.class);
		detachedCriteria.createAlias("orgInfo", "org", JoinType.LEFT_OUTER_JOIN);
		if (orgFormationMgrFlow.getOrgInfo() != null
				&& StringUtils.isNotBlank(orgFormationMgrFlow.getOrgInfo().getUnitBasicName())) {
			detachedCriteria.add(Restrictions.like("org.unitBasicName",
					orgFormationMgrFlow.getOrgInfo().getUnitBasicName(), MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(organId)) {// organ下的单位
			// 查询本节点下所有单位
			List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(organId);
			List<String> organList = new ArrayList<String>();
			for (OrganNode org : orgNodeList) {
				organList.add(org.getId());
			}
			detachedCriteria.createAlias("org.organ", "organ", JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in("organ.id", organList));
			
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.addOrder(Order.desc("createTime"));
		Page<OrgFormationMgrFlowVO> pageInfo = orgFormationMgrFlowService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
}
