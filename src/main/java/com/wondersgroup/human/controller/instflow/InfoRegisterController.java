/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 职位简章 控制器
 * 创建人: tzy   
 * 创建时间: 2018年7月25日 下午3:11:21 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月25日 下午3:11:21 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.instflow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.Paragraph;
import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.console.bo.FrameWorkResource;
import com.wondersgroup.framework.console.service.FrameWorkService;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.framework.workflow.vo.FlowRecordVO;
import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.controller.workflow.WorkFlowController.FlowRecordSeq;
import com.wondersgroup.human.dto.instflow.InfoRegisterQueryParam;
import com.wondersgroup.human.dto.ofcflow.ResignServantQueryParam;
import com.wondersgroup.human.service.instflow.MemberInfoRegisterService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.instflow.MemberInfoRegisterVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * 
 * @ClassName: InfoRegisterController
 * @Description: 事业单位登记   控制器
 * @author: lyf
 * @date: 2018年9月5日 上午10:27:16 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("/instflow/inforegister")
public class InfoRegisterController extends GenericController {
	
	//登记人员审核列表
	private static final String VIEW_INFOREGISTER_LIST="models/instflow/inforegister/flow";
	
	//跳转到提交审核信息列表
	private final static String RETURN_REGISTER_PAGE = "models/instflow/inforegister/register";

	/**
	 * 流程待办列表
	 */
	private final static String INST_REGISTER_FLOW = "models/instflow/inforegister/index";
	
	
	/**
	 * 登记流程审批页面
	 */
	private final static String MEMBER_INST_REGISTER_FLOW = "models/instflow/inforegister/registerFlow";
	
	
	// 页面路径--人员登记信息详情
	private final String REGISTER_DETAIL = "models/instflow/inforegister/registerDetail";
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private MemberInfoRegisterService memberInfoRegisterService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@Autowired
	private FrameWorkService frameWorkService;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private InstitutionOrgFormationService institutionOrgFormationService;
	
	
	private static String busType = "MemberInfoRegister";
	
	/**
	 * @Title: ResignDetail
	 * @Description: 辞职人员列表
	 * @return
	 * @return: String
	 */
	@Log(title = "详情信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@RequestMapping("/registerDetail")
	public String resignDetail(String id, Model model) {
		
		MemberInfoRegister memberInfoRegister = memberInfoRegisterService.get(id);
		List<FlowRecordVO> records = flowRecordService.findFlowRecordByBusinessId(id, busType, null, false);
		
		model.addAttribute("memberInfoRegister", memberInfoRegister);
		model.addAttribute("records", records);
		
		return REGISTER_DETAIL;

	}
	
	
	/**
	 * @Title: pageList
	 * @Description: 登记列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@Log(title = "查询信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<MemberInfoRegisterVO> pageList(InfoRegisterQueryParam param, Integer limit, Integer page) {
		Page<MemberInfoRegisterVO> pageInfo = memberInfoRegisterService.pageList(param, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: index 
	 * @Description: 人员信息登记列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String list(Model model){
		model.addAttribute("busType","MemberInfoRegister");
		model.addAttribute("category", FlowRecord.FLOW_RECORD_CATEGORY_INS); //事业单位
		return INST_REGISTER_FLOW;
	}
	
	
	/**
	 * @Title: index 
	 * @Description: 人员信息登记列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index_flow")
	public String list_flow(Model model){
		model.addAttribute("busType","MemberInfoRegister");
		return VIEW_INFOREGISTER_LIST;
	}
	
	/**
	 * 
	 * @Title: plan 
	 * @Description: 新增组织招录计划
	 * @return
	 * @return: String
	 */
	@Log(title = "新增招录计划", operatorType = OperatorType.MANAGE, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@RequestMapping("/register")
	public String plan(Model model) {
		//得到用户当前所在的单位
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		//长宁区
		OrganNode root = organizationService.getCurrentUnitOrganForOrganNodeCode(CommonConst.ROOT_ORGAN_CODE);
		
		model.addAttribute("OrganNode", x);
		model.addAttribute("rootOrgan", root);
		
		
		/**********获取编制信息********************/
		//查询当前单位编制情况
		OrgInfo org = orgInfoService.findUniqueBy("organ.id", x.getId());
		InstitutionOrgFormation institutionOrgFormation = null;
		if(org!=null){
			institutionOrgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id", org.getId());
					
		}
		model.addAttribute("d", institutionOrgFormation);
		
		return RETURN_REGISTER_PAGE;
	}
	
	/**
	 * @Title: operationRegister
	 * @Description: 人员信息上报
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "新增人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationRegister")
	public AjaxResult operationPlan(PublicInstitution temp,  HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String planState = request.getParameter("planState");//审核状态
		String sexid = temp.getSex().getId();
		String r = request.getParameter("result");//审批结果
		String mid = request.getParameter("mid"); //流程主表id
		
		
		try {
			if (StringUtils.isBlank(r) || (!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))) {
				throw new BusinessException("审批结果信息不正确！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				
				CodeInfo nowJobLevel = codeInfoService.load(temp.getNowJobLevel().getId());
				//验证编制职数
//				boolean flag = publicInstitutionService.saveFormationControl(nowJobLevel);
//				if (!flag) {
//					throw new BusinessException("控编控职没有通过！");
//				}
				
				String postName = "";
				if (nowJobLevel != null) {
					CodeInfo parentPost = nowJobLevel.parent;
					if (parentPost != null) {
						postName = parentPost.getName() + " " + nowJobLevel.getName();
					}
				}
				
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				publicInstitutionService.saveRegister(temp,opinion,r, planState, mid);
			} else {
				PublicInstitution publicInstitution = publicInstitutionService.load(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp,publicInstitution);
				DictUtils.operationCodeInfo(publicInstitution);//将CodeInfo中id为空的属性 设置为null
				publicInstitutionService.saveRegister(publicInstitution,opinion,r, planState, mid);
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
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","MemberInfoRegister");
		return INST_REGISTER_FLOW;
	}
	
	/**
	 * @Title: planFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@Log(title = "更新详情信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@RequestMapping("/queryRegisterInfo")
	public String planFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		String busType = flow.getBusType();
		MemberInfoRegister memberInfoRegister = memberInfoRegisterService.get(flow.getBusId());
		model.addAttribute("memberInfoRegister", memberInfoRegister);
		
		model.addAttribute("records", queryFlowRecordDetail(id));
		
		return MEMBER_INST_REGISTER_FLOW;
	}
	
	
	/**
	 * table显示处理详情
	 * @param id
	 */
	public List<FlowRecordVO> queryFlowRecordDetail(String id) {
		FlowRecord flowRecord = flowRecordService.get(id);
		flowRecord = flowRecordService.queryLastFlowRecord(flowRecord.getBusId(), flowRecord.getBusType(), flowRecord.getProcessInstanceId());
		
		List<FlowRecordVO> records = flowRecordService.findFlowRecordByBusinessId(flowRecord.getBusId(), 
				flowRecord.getBusType(), flowRecord.getProcessInstanceId(), false);

         return records;
	}
	
	
}
