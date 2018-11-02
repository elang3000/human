/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 单位基础信息流程表单 控制器
 * 创建人: jiang
 * 创建时间: 2018年9月12日15:44:38
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月12日15:44:43
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
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.OrgInfoMgrFlow;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.bo.organization.OrgInfoHistory;
import com.wondersgroup.human.service.ofcflow.OrgInfoMgrFlowService;
import com.wondersgroup.human.service.organization.OrgInfoHistoryService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.ofcflow.OrgInfoMgrFlowVO;

/**
 * @ClassName: OrgInfoMgrFlowController
 * @Description: 单位基础信息流程表单 控制器
 * @author: jiang
 * @date: 2018年9月12日15:45:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/orgInfoflow")
@Controller
public class OrgInfoMgrFlowController extends GenericController {
	
	@Autowired
	private OrgInfoMgrFlowService orgInfoMgrFlowService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private OrgInfoHistoryService orgInfoHistoryService;
	
	/**
	 * 组织机构信息维护 返回视图界面
	 */
	private static final String ORG_INFO_MGR_FlOW_LIST = "models/ofcflow/orgInfo/orgInfoMgrFlowList";
	
	private static final String ORG_INFO_MGR_FlOW_APPLY = "models/ofcflow/orgInfo/orgInfoMgrFlowApplyPage";
	
	private static final String ORG_INFO_MGR_FlOW_ADJUST = "models/ofcflow/orgInfo/orgInfoMgrFlowAdjustPage";
	
	private static final String ORG_INFO_MGR_FlOW_APPLY_VIEW = "models/ofcflow/orgInfo/orgInfoMgrFlowApplyView";
	
	private static final String ORG_INFO_MGR_FlOW_ADJUST_VIEW = "models/ofcflow/orgInfo/orgInfoMgrFlowAdjustView";
	
	private static final String ORG_INFO_MGR_FlOW_APPLY_APPROVAL_PAGE = "models/ofcflow/orgInfo/orgInfoMgrFlowApplyApprovalPage";
	
	private static final String ORG_INFO_MGR_FlOW_ADJUST_APPROVAL_PAGE = "models/ofcflow/orgInfo/orgInfoMgrFlowAdjustApprovalPage";
	
	/**
	 * @Title: orgInfolist
	 * @Description: 单位信息维护表单信息列表
	 * @return: String
	 */
	@RequestMapping("/orgInfoMgrFlowList")
	public String orgInfolist() {
		
		return ORG_INFO_MGR_FlOW_LIST;
	}
	
	/**
	 * @Title: applyPage
	 * @Description: 申请新增单位信息维护表单新增页面
	 * @return: String
	 */
	@RequestMapping("/applyPage")
	public String applyPage(Model model, String parentOrganId, String id) {
		
		if (StringUtils.isNotBlank(id)) {
			OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(id);
			model.addAttribute("orgInfoMgrFlow", orgInfoMgrFlow);
		} else {
			OrgInfoMgrFlow orgInfoMgrFlow = new OrgInfoMgrFlow();
			OrganNode parentOrgan = organNodeService.get(parentOrganId);
			// 默认行政区划使用长宁区
			String changNingAreaCode = "310105";
			CodeInfo unitDistrict = dictableService.getCodeInfoByCode(changNingAreaCode,
					DictTypeCodeContant.CODE_TYPE_AREA);
			orgInfoMgrFlow.setUnitDistrict(unitDistrict);
			orgInfoMgrFlow.setParentOrgan(parentOrgan);
			model.addAttribute("orgInfoMgrFlow", orgInfoMgrFlow);
		}
		return ORG_INFO_MGR_FlOW_APPLY;
	}
	
	/**
	 * @Title: adjustPage
	 * @Description: 申请机构调整信息维护表单 页面
	 * @return: String
	 */
	@RequestMapping("/adjustPage")
	public String adjustPage(Model model, String organId, String id) {
		
		if (StringUtils.isNoneBlank(id)) {
			OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(id);
			model.addAttribute("orgInfoMgrFlow", orgInfoMgrFlow);
			
			OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", orgInfoMgrFlow.getOrgan().getId());
			model.addAttribute("orgInfo", orgInfo);
		} else {
			// 查询到原单位数据
			OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", organId);
			if(orgInfo == null){
				throw new BusinessException("单位信息为空，无法操作调整业务！");
			}
			OrgInfoMgrFlow orgInfoMgrFlow = new OrgInfoMgrFlow();
			
			// 复制到调整申请表
			BeanUtils.copyProperties(orgInfo, orgInfoMgrFlow, new String[] {
					"id"
			});
			OrganNode organ = organNodeService.get(organId);
			OrganNode parentOrgan = organNodeService.getParentNode(organ, organizationService.getDefaultOrganTree());
			orgInfoMgrFlow.setOrgan(organ);
			orgInfoMgrFlow.setParentOrgan(parentOrgan);
			// 默认行政区划使用长宁区
			String changNingAreaCode = "310105";
			CodeInfo unitDistrict = dictableService.getCodeInfoByCode(changNingAreaCode,
					DictTypeCodeContant.CODE_TYPE_AREA);
			orgInfoMgrFlow.setUnitDistrict(unitDistrict);
			
			model.addAttribute("orgInfo", orgInfo);
			model.addAttribute("orgInfoMgrFlow", orgInfoMgrFlow);
		}
		return ORG_INFO_MGR_FlOW_ADJUST;
	}
	
	/**
	 * @Title: applySave
	 * @Description: 新建申请表单保存功能
	 * @param temp
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/applySave")
	public AjaxResult applySave(OrgInfoMgrFlow temp) {
		
		// 改操作为机构维护类型中 新增操作,并且默认流程状态为待提交
		temp.setOptionType(
				dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAM_ORGINFO_MAINTIAN_TYPE));
		temp.setStatus(OrgInfoMgrFlow.STATUS_ORG_INFO_MGR_FLOW_STATE);
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 暂存修改
				OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, orgInfoMgrFlow);
				DictUtils.operationCodeInfo(orgInfoMgrFlow);// 将CodeInfo中id为空的属性 设置为null
				orgInfoMgrFlowService.saveOrUpdate(orgInfoMgrFlow);// 保存
			} else {// 新增暂存
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				orgInfoMgrFlowService.saveOrUpdate(temp);
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
	 * @Title: adjustSave
	 * @Description: 调整申请表单保存功能
	 * @param temp
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/adjustSave")
	public AjaxResult adjustSave(OrgInfoMgrFlow temp) {
		
		// 改操作为机构维护类型中 调整操作,并且默认流程状态为待提交
		temp.setOptionType(
				dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_HUMAM_ORGINFO_MAINTIAN_TYPE));
		temp.setStatus(OrgInfoMgrFlow.STATUS_ORG_INFO_MGR_FLOW_STATE);
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 暂存修改
				OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, orgInfoMgrFlow);
				DictUtils.operationCodeInfo(orgInfoMgrFlow);// 将CodeInfo中id为空的属性 设置为null
				orgInfoMgrFlowService.saveOrUpdate(orgInfoMgrFlow);// 保存
			} else {// 新增暂存
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				orgInfoMgrFlowService.saveOrUpdate(temp);
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
	 * @Title: applySubmit
	 * @Description: 请求新增申请提交
	 * @param temp
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/applySubmit")
	public AjaxResult applySubmit(OrgInfoMgrFlow temp, HttpServletRequest request) {
		
		temp.setOptionType(
				dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAM_ORGINFO_MAINTIAN_TYPE));
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				orgInfoMgrFlowService.startOrgApplyFlow(temp);
			} else {
				OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, orgInfoMgrFlow);
				DictUtils.operationCodeInfo(orgInfoMgrFlow);// 将CodeInfo中id为空的属性 设置为null
				if (orgInfoMgrFlow.getFlowRecord() != null) {
					orgInfoMgrFlowService.commitAuditApplyFlow(orgInfoMgrFlow, "再次提交", FlowRecord.PASS);
				} else {
					orgInfoMgrFlowService.startOrgApplyFlow(orgInfoMgrFlow);
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
	
	/**
	 * @Title: adjustSubmit
	 * @Description: 请求调整申请提交
	 * @param temp
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/adjustSubmit")
	public AjaxResult adjustSubmit(OrgInfoMgrFlow temp, HttpServletRequest request) {
		
		temp.setOptionType(
				dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_HUMAM_ORGINFO_MAINTIAN_TYPE));
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				orgInfoMgrFlowService.startOrgAdjustFlow(temp);
			} else {
				OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, orgInfoMgrFlow);
				DictUtils.operationCodeInfo(orgInfoMgrFlow);// 将CodeInfo中id为空的属性 设置为null
				if (orgInfoMgrFlow.getFlowRecord() != null) {
					orgInfoMgrFlowService.commitAuditAdjustFlow(orgInfoMgrFlow, "再次提交", FlowRecord.PASS);
				} else {
					orgInfoMgrFlowService.startOrgAdjustFlow(orgInfoMgrFlow);
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
	
	@ResponseBody
	@RequestMapping("/auditApplyFlow")
	public AjaxResult auditApplyFlow(OrgInfoMgrFlow temp, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");// 审批意见
		String r = request.getParameter("result");// 审批结果
		OrgInfoMgrFlow flow = orgInfoMgrFlowService.get(temp.getId());
		try {
			orgInfoMgrFlowService.commitAuditApplyFlow(flow, opinion, r);
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
	
	@ResponseBody
	@RequestMapping("/auditAdjustFlow")
	public AjaxResult auditAdjustFlow(OrgInfoMgrFlow temp, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");// 审批意见
		String r = request.getParameter("result");// 审批结果
		try {
			OrgInfoMgrFlow flow = orgInfoMgrFlowService.get(temp.getId());
			orgInfoMgrFlowService.commitAuditAdjustFlow(flow, opinion, r);
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
	 * @Title: delete
	 * @Description: 删除待提交的流程
	 * @param temp 流程信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/deleteApply")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(id);
			orgInfoMgrFlowService.delete(orgInfoMgrFlow);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	/**
	 * @Title: applyApprovalPage
	 * @Description: 新增请求审批查看
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/applyApprovalPage")
	public String applyApprovalPage(Model model, String id) {
		
		FlowRecord flowRecord = flowRecordService.get(id);
		String busId = flowRecord.getBusId();
		OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(busId);
		model.addAttribute("orgInfoMgrFlow", orgInfoMgrFlow);
		if (orgInfoMgrFlow.getStatus() == OrgInfoMgrFlow.STATUS_ORG_INFO_MGR_FLOW_STATE) {
			return ORG_INFO_MGR_FlOW_APPLY;
		} else {
			return ORG_INFO_MGR_FlOW_APPLY_APPROVAL_PAGE;
		}
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
		OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(busId);
		
		OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", orgInfoMgrFlow.getOrgan().getId());
		model.addAttribute("orgInfo", orgInfo);
		
		model.addAttribute("orgInfoMgrFlow", orgInfoMgrFlow);
		model.addAttribute("orgInfo", orgInfo);
		if (orgInfoMgrFlow.getStatus() == OrgInfoMgrFlow.STATUS_ORG_INFO_MGR_FLOW_STATE) {
			return ORG_INFO_MGR_FlOW_ADJUST;
		} else {
			return ORG_INFO_MGR_FlOW_ADJUST_APPROVAL_PAGE;
		}
	}
	
	/**
	 * @Title: applyView
	 * @Description: 新增申请查看
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/applyView")
	public String applyView(Model model, String id) {
		
		if (StringUtils.isNoneBlank(id)) {
			OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(id);
			model.addAttribute("orgInfoMgrFlow", orgInfoMgrFlow);
		}
		return ORG_INFO_MGR_FlOW_APPLY_VIEW;
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
		
		if (StringUtils.isNoneBlank(id)) {
			OrgInfoMgrFlow orgInfoMgrFlow = orgInfoMgrFlowService.get(id);
			model.addAttribute("orgInfoMgrFlow", orgInfoMgrFlow);
			
			OrgInfoHistory orgInfoHistory = orgInfoHistoryService.findUniqueBy("orgInfoMgrFlow.id", orgInfoMgrFlow.getId());
			if(orgInfoHistory == null){
				OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", orgInfoMgrFlow.getOrgan().getId());
				model.addAttribute("orgInfo", orgInfo);
			}else{
				model.addAttribute("orgInfo", orgInfoHistory);
			}
			
		}
		return ORG_INFO_MGR_FlOW_ADJUST_VIEW;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 机构信息维护流程表单 列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<OrgInfoMgrFlowVO> pageList(OrgInfoMgrFlow orgInfoMgrFlow, Integer limit, Integer page,
			String parentOrganId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrgInfoMgrFlow.class);
		if (StringUtils.isNotBlank(orgInfoMgrFlow.getUnitBasicName())) {
			detachedCriteria
					.add(Restrictions.like("unitBasicName", orgInfoMgrFlow.getUnitBasicName(), MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(parentOrganId)) {// organ下的单位
			// 查询本节点下所有单位
			List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(parentOrganId);
			List<String> parentOrganList = new ArrayList<String>();
			for (OrganNode org : orgNodeList) {
				parentOrganList.add(org.getId());
			}
			detachedCriteria.createAlias("parentOrgan", "porg", JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias("organ", "org", JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.or(Restrictions.in("porg.id", parentOrganList),
					Restrictions.in("org.id", parentOrganList)));
			
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.addOrder(Order.desc("createTime"));
		Page<OrgInfoMgrFlowVO> pageInfo = orgInfoMgrFlowService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
}
