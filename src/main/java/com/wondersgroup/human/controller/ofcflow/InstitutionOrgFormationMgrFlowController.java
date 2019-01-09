/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: InstitutionOrgFormationMgrFlowController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 事业单位编制信息流程表单 控制器
 * 创建人: jiang
 * 创建时间: 2018年12月5日15:20:41
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月5日15:20:44
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
import com.wondersgroup.human.bo.ofcflow.InstitutionOrgFormationMgrFlow;
import com.wondersgroup.human.bo.ofcflow.OrgFormationMgrFlow;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormationHistory;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.ofcflow.InstitutionOrgFormationMgrFlowService;
import com.wondersgroup.human.service.ofcflow.OrgInfoMgrFlowService;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationHistoryService;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.ofcflow.InstitutionOrgFormationMgrFlowVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: InstitutionOrgFormationMgrFlowController
 * @Description: 事业单位编制信息流程表单 控制器
 * @author: jiang
 * @date: 2018年12月5日15:20:53
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/institutionOrgFormationFlow")
@Controller
public class InstitutionOrgFormationMgrFlowController extends GenericController {
	
	@Autowired
	private InstitutionOrgFormationMgrFlowService institutionOrgFormationMgrFlowService;
	
	@Autowired
	private InstitutionOrgFormationService institutionOrgFormationService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private InstitutionOrgFormationHistoryService institutionOrgFormationHistoryService;
	
	/**
	 * 组织机构编制信息维护 返回视图界面
	 */
	private static final String ORG_FORMATION_MGR_FlOW_LIST = "models/ofcflow/instOrgFormation/orgFormationMgrFlowList";
	
	private static final String ORG_FORMATION_MGR_FlOW_ADJUST = "models/ofcflow/instOrgFormation/orgFormationMgrFlowAdjustPage";
	
	private static final String ORG_FORMATION_MGR_FlOW_ADJUST_VIEW = "models/ofcflow/instOrgFormation/orgFormationMgrFlowAdjustView";
	
	private static final String ORG_FORMATION_MGR_FlOW_ADJUST_APPROVAL_PAGE = "models/ofcflow/instOrgFormation/orgFormationMgrFlowAdjustApprovalPage";
	
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
	 * @Description: 申请事业编制调整信息维护表单 页面
	 * @return: String
	 */
	@RequestMapping("/adjustPage")
	public String adjustPage(Model model, String organId, String id) {
		
		if (StringUtils.isNotBlank(id)) {
			
			InstitutionOrgFormationMgrFlow orgFormationMgrFlow = institutionOrgFormationMgrFlowService.get(id);
			
			InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
					orgFormationMgrFlow.getOrgInfo().getId());
			if (orgFormation == null)
				orgFormation = new InstitutionOrgFormation();
			
			model.addAttribute("orgFormationMgrFlow", orgFormationMgrFlow);
			model.addAttribute("orgFormation", orgFormation);
		} else {
			// 首先判断选择的节点是否具有单位信息
			OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", organId);
			if (orgInfo == null) { throw new BusinessException("单位信息为空，无法操作编制调整业务！"); }
			
			InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
					orgInfo.getId());
			if (orgFormation == null)
				orgFormation = new InstitutionOrgFormation();
			
			// 复制到调整申请表
			InstitutionOrgFormationMgrFlow orgFormationMgrFlow = new InstitutionOrgFormationMgrFlow();
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
	@Log(title = "编辑事业编制调整申请信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/adjustSave")
	public AjaxResult adjustSave(InstitutionOrgFormationMgrFlow temp) {
		
		// 判断当前单位是否有编制信息，否的话为新增编制,并且默认流程状态为待提交
		InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
				temp.getOrgInfo().getId());
		if (orgFormation == null) {
			temp.setOptionType(
					dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_ORGFORMATION_MAINTIAN_TYPE));
		} else {
			temp.setOptionType(
					dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_HUMAN_ORGFORMATION_MAINTIAN_TYPE));
		}
		
		temp.setStatus(InstitutionOrgFormationMgrFlow.STATUS_ORG_FORMATION_MGR_FLOW_STATE);
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 暂存修改
				InstitutionOrgFormationMgrFlow orgFormationMgrFlow = institutionOrgFormationMgrFlowService
						.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, orgFormationMgrFlow);
				DictUtils.operationCodeInfo(orgFormationMgrFlow);// 将CodeInfo中id为空的属性 设置为null
				institutionOrgFormationMgrFlowService.saveOrUpdate(orgFormationMgrFlow);// 保存
			} else {// 新增暂存
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				institutionOrgFormationMgrFlowService.saveOrUpdate(temp);
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
	@Log(title = "提交事业编制调整申请流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/adjustSubmit")
	public AjaxResult adjustSubmit(InstitutionOrgFormationMgrFlow flow, HttpServletRequest request) {
		
		// 判断当前单位是否有编制信息，否的话为新增编制,并且默认流程状态为待提交
		InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
				flow.getOrgInfo().getId());
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
				institutionOrgFormationMgrFlowService.startOrgFormationAdjustFlow(flow);
				;
			} else {
				InstitutionOrgFormationMgrFlow orgFormationMgrFlow = institutionOrgFormationMgrFlowService
						.get(flow.getId());
				BeanUtils.copyPropertiesIgnoreNull(flow, orgFormationMgrFlow);
				DictUtils.operationCodeInfo(orgFormationMgrFlow);// 将CodeInfo中id为空的属性 设置为null
				if (orgFormationMgrFlow.getFlowRecord() != null) {
					institutionOrgFormationMgrFlowService.commitAuditAdjustFlow(orgFormationMgrFlow, "再次提交",
							FlowRecord.PASS);
				} else {
					institutionOrgFormationMgrFlowService.startOrgFormationAdjustFlow(orgFormationMgrFlow);
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
	
	@Log(title = "审批事业编制申请流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/auditAdjustFlow")
	public AjaxResult auditAdjustFlow(InstitutionOrgFormationMgrFlow temp, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");// 审批意见
		String r = request.getParameter("result");// 审批结果
		try {
			InstitutionOrgFormationMgrFlow flow = institutionOrgFormationMgrFlowService.get(temp.getId());
			institutionOrgFormationMgrFlowService.commitAuditAdjustFlow(flow, opinion, r);
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
		InstitutionOrgFormationMgrFlow orgFormationMgrFlow = institutionOrgFormationMgrFlowService.get(busId);
		
		InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
				orgFormationMgrFlow.getOrgInfo().getId());
		if (orgFormation == null)
			orgFormation = new InstitutionOrgFormation();
		model.addAttribute("orgFormationMgrFlow", orgFormationMgrFlow);
		model.addAttribute("orgFormation", orgFormation);
		if (orgFormationMgrFlow.getStatus() == InstitutionOrgFormationMgrFlow.STATUS_ORG_FORMATION_MGR_FLOW_STATE) {
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
	@Log(title = "查看事业编制调整申请信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/adjustView")
	public String adjustView(Model model, String id) {
		
		InstitutionOrgFormationMgrFlow orgFormationMgrFlow = institutionOrgFormationMgrFlowService.get(id);
		
		InstitutionOrgFormationHistory orgFormationHistory = institutionOrgFormationHistoryService
				.findUniqueBy("instOrgFormationMgrFlow.id", orgFormationMgrFlow.getId());
		if (orgFormationHistory == null) {
			InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
					orgFormationMgrFlow.getOrgInfo().getId());
			if (orgFormation == null)
				orgFormation = new InstitutionOrgFormation();
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
	@Log(title = "删除事业编制调整申请", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			InstitutionOrgFormationMgrFlow orgFormationMgrFlow = institutionOrgFormationMgrFlowService.get(id);
			institutionOrgFormationMgrFlowService.delete(orgFormationMgrFlow);
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
	 * @Description: 编制维护流程表单 列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查看事业编制调整申请列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<InstitutionOrgFormationMgrFlowVO> pageList(InstitutionOrgFormationMgrFlow orgFormationMgrFlow,
			Integer limit, Integer page, String organId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(InstitutionOrgFormationMgrFlow.class);
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
		Page<InstitutionOrgFormationMgrFlowVO> pageInfo = institutionOrgFormationMgrFlowService
				.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
}
