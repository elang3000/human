/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ImportantEventApplyController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 重大事项申请 控制器
 * 创建人: jiang
 * 创建时间: 2018年12月17日09:54:24
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月17日09:54:27
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.ImportantEventApply;
import com.wondersgroup.human.bo.ofcflow.InstitutionOrgFormationMgrFlow;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;
import com.wondersgroup.human.service.ofcflow.ImportantEventApplyService;
import com.wondersgroup.human.vo.ofcflow.ImportantEventApplyVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: ImportantEventApplyController
 * @Description: 重大事项申请 控制器
 * @author: jiang
 * @date: 2018年12月17日09:54:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/importantEventApply")
@Controller
public class ImportantEventApplyController extends GenericController {
	
	@Autowired
	private ImportantEventApplyService importantEventApplyService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	/**
	 * 重大事项申请 返回视图界面
	 */
	private static final String IMPORTNAT_EVENT_APPLY_LIST = "models/ofcflow/importantEventApply/list";
	
	private static final String IMPORTNAT_EVENT_APPLY_EDIT = "models/ofcflow/importantEventApply/edit";
	
	private static final String IMPORTNAT_EVENT_APPLY_VIEW = "models/ofcflow/importantEventApply/view";
	
	private static final String IMPORTNAT_EVENT_APPLY_APPROVAL = "models/ofcflow/importantEventApply/approval";
	
	
	/**
	 * @Title: list
	 * @Description: 重大事项申请 列表
	 * @return: String
	 */
	@RequestMapping("/list")
	public String list() {
		
		return IMPORTNAT_EVENT_APPLY_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 重大事项申请 列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查询重大事项申请信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<ImportantEventApplyVO> pageList(ImportantEventApply importantEventApply, Integer limit, Integer page,
			String organId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ImportantEventApply.class);
		
		// 登录人organNode
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		organId = organNode.getId();
		
		// 查询本节点下所有单位
		List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(organId);
		List<String> organList = new ArrayList<String>();
		for (OrganNode org : orgNodeList) {
			organList.add(org.getId());
		}
		detachedCriteria.add(Restrictions.in("applyOrgan.id", organList));
		
		if (importantEventApply.getEventType() != null
				&& StringUtils.isNotBlank(importantEventApply.getEventType().getId())) {// 事项类型
			detachedCriteria.add(Restrictions.eq("eventType.id", importantEventApply.getEventType().getId()));
		}
		
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.addOrder(Order.desc("createTime"));
		Page<ImportantEventApplyVO> pageInfo = importantEventApplyService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
	
	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		
		if (StringUtils.isNotBlank(id)) {
			ImportantEventApply importantEventApply = importantEventApplyService.get(id);
			model.addAttribute("importantEventApply", importantEventApply);
		} else {
			ImportantEventApply importantEventApply = new ImportantEventApply();
			// 默认申请单位为本单位
			OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			importantEventApply.setApplyOrgan(organNode);
			// 默认申请人为本人
			SecurityUser applyUser = userService.get(SecurityUtils.getUserId());
			importantEventApply.setApplyUser(applyUser);
			
			model.addAttribute("importantEventApply", importantEventApply);
		}
		return IMPORTNAT_EVENT_APPLY_EDIT;
	}
	
	/**
	 * @Title: save
	 * @Description: 表单编辑保存功能
	 * @param temp
	 * @return: AjaxResult
	 */
	@Log(title = "编辑重大事项申请信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ImportantEventApply temp) {
		
		AjaxResult result = new AjaxResult(true);
		temp.setStatus(ImportantEventApply.STATUS_IMPORTANT_EVENT_STATE);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 暂存修改
				ImportantEventApply importantEventApply = importantEventApplyService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, importantEventApply);
				DictUtils.operationCodeInfo(importantEventApply);// 将CodeInfo中id为空的属性 设置为null
				importantEventApplyService.saveOrUpdate(importantEventApply);// 保存
			} else {// 新增暂存
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				importantEventApplyService.saveOrUpdate(temp);
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
	 * @Title: delete
	 * @Description: 删除待提交的流程
	 * @param temp 流程信息
	 * @return: AjaxResult
	 */
	@Log(title = "删除重大事项申请信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			ImportantEventApply importantEventApply = importantEventApplyService.get(id);
			importantEventApplyService.delete(importantEventApply);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	/**
	 * @Title: view
	 * @Description: 表单查看
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@Log(title = "查看重大事项申请信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/view")
	public String adjustView(Model model, String id) {
		
		ImportantEventApply importantEventApply = importantEventApplyService.get(id);
		model.addAttribute("importantEventApply", importantEventApply);
		
		return IMPORTNAT_EVENT_APPLY_VIEW;
	}
	
	/**
	 * @Title: applySubmit
	 * @Description: 申请提交
	 * @param temp
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "提交重大事项申请流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/applySubmit")
	public AjaxResult applySubmit(ImportantEventApply apply, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		apply.setStatus(ImportantEventApply.STATUS_IMPORTANT_EVENT_STATE);
		try {
			if (StringUtils.isBlank(apply.getId())) {
				DictUtils.operationCodeInfo(apply);// 将CodeInfo中id为空的属性 设置为null
				apply.setId(null);
				importantEventApplyService.startApplyFlow(apply);
			} else {
				ImportantEventApply importantEventApply = importantEventApplyService.get(apply.getId());
				BeanUtils.copyPropertiesIgnoreNull(apply, importantEventApply);
				DictUtils.operationCodeInfo(importantEventApply);// 将CodeInfo中id为空的属性 设置为null
				if (importantEventApply.getFlowRecord() != null) {
					importantEventApplyService.commitApplyFlow(importantEventApply, "再次提交",
							FlowRecord.PASS);
				} else {
					importantEventApplyService.startApplyFlow(importantEventApply);
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
	
	@RequestMapping("/approvalPage")
	public String approvalPage(String id, Model model) {
		
		FlowRecord flowRecord = flowRecordService.get(id);
		String busId = flowRecord.getBusId();
		ImportantEventApply importantEventApply = importantEventApplyService.get(busId);
		model.addAttribute("importantEventApply", importantEventApply);
		if (importantEventApply.getStatus() == ImportantEventApply.STATUS_IMPORTANT_EVENT_STATE) {
			return IMPORTNAT_EVENT_APPLY_EDIT;
		} else {
			return IMPORTNAT_EVENT_APPLY_APPROVAL;
		}
	}
	
	@Log(title = "审批重大事项申请流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/auditFlow")
	public AjaxResult auditFlow(ImportantEventApply temp, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");// 审批意见
		String r = request.getParameter("result");// 审批结果
		try {
			ImportantEventApply flow = importantEventApplyService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, flow);
			importantEventApplyService.commitApplyFlow(flow, opinion, r);
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
}
