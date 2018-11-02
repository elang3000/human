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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.console.bo.FrameWorkResource;
import com.wondersgroup.framework.console.service.FrameWorkService;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.framework.workflow.vo.FlowRecordVO;
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.controller.workflow.WorkFlowController.FlowRecordSeq;
import com.wondersgroup.human.service.instflow.AlternatingRotationService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;

/**
 * 
 * @ClassName: AlternatingRotationController
 * @Description: 事业单位交流轮岗  控制器
 * @author: lyf
 * @date: 2018年9月12日 上午10:27:16 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("/instflow/alternatingrotation")
public class AlternatingRotationController extends GenericController {
	
	//流程待办列表
	private final static String INST_ALTERNATINGROTATION_FLOW = "models/instflow/alternatingrotation/altrotflow";
	
	//事业单位人员信息列表
	private static final String VIEW_INSTITUTION_LIST="models/instflow/alternatingrotation/memberList";
	
	//提交申请页
	private final static String INST_SUBMIT_APPLY = "models/instflow/alternatingrotation/applypage";

	
	//跳转到提交审核信息列表
	private final static String RETURN_REGISTER_PAGE = "models/instflow/alternatingrotation/register";

	
	//提交审核页
    private final static String INST_SUBMIT_OPERATION = "models/instflow/alternatingrotation/operationpage";
	
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private AlternatingRotationService alternatingRotationService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	@Autowired
	private OrganNodeService organNodeService;
	@Autowired
	private FrameWorkService frameWorkService;
	
	
	
	
	
	
	/**
	 * @Title: index 
	 * @Description: 人员交流轮岗
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("busType","AlternatingRotation");
		return INST_ALTERNATINGROTATION_FLOW;
	}
	
	/**
	 * @Title: publicInstitutionlist 
	 * @Description: 事业人员信息列表
	 * @return: String
	 */
	@RequestMapping("/list")
	public String publicInstitutionlist(){
		return VIEW_INSTITUTION_LIST;
	}
	
	
	/**
	 * @Title: returnApply 
	 * @Description: 个人提交申请页
	 * @param model
	 * @param id
	 * @return: String
	 */
	@RequestMapping("/returnApply")
	public String returnApply(Model model,String id) {
		//当前用户信息
		PublicInstitution instInfo = publicInstitutionService.load(id);
		model.addAttribute("publicInstitution", instInfo);
		
		//得到用户当前所在的单位
		//长宁区
		OrganNode root = organizationService.getCurrentUnitOrganForOrganNodeCode(CommonConst.ROOT_ORGAN_CODE);
		OrganNode currentUnit = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("root", root);
		model.addAttribute("currentUnit", currentUnit);
		
		return INST_SUBMIT_APPLY;
	}
	
	
	/**
	 * 
	 * @Title: plan 
	 * @Description: 新增组织招录计划
	 * @return: String
	 */
	@RequestMapping("/register")
	public String register(Model model, String employPlanId,Integer level, String yearPlanId) {
		//得到用户当前所在的单位
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		//长宁区
		OrganNode root = organizationService.getCurrentUnitOrganForOrganNodeCode(CommonConst.ROOT_ORGAN_CODE);
		
		model.addAttribute("OrganNode", x);
		model.addAttribute("rootOrgan", root);
		model.addAttribute("organTreeId", "394e21fa-1eb6-42ee-ba32-50655fa16517"); //长宁区
		
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
	@ResponseBody
	@RequestMapping("/registerAlternatingRotation")
	public AjaxResult registerAlternatingRotation(AlternatingRotation temp,  HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String planState = request.getParameter("planState");//审核状态
		String r = request.getParameter("result");//审批结果
		String pId = request.getParameter("pid"); //人员信息主表id
		
		
		//String communicationSignId = request.getParameter("communicationSign.id"); //交流标识
		//String tenureName = request.getParameter("tenureName"); //任职机构
		//String postCodeId = request.getParameter("postCode.id"); //职务名称
		//String attribute = request.getParameter("attribute"); //职务属性
		String organNodeId = request.getParameter("organNodeId"); //交流单位
		OrganNode alterOrgan = organNodeService.get(organNodeId);
		temp.setAlterRotaOrgan(alterOrgan);
		
		OrganNode oldOrgan = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId()); //处理人的单位就是当前单位
		temp.setOldOrgan(oldOrgan);
		
		try {
			if (StringUtils.isBlank(r) || (!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))) {
				throw new BusinessException("审批结果信息不正确！");
			}
			
			if (StringUtils.isBlank(temp.getId())) {
				//判断当前用户是否已在流程处理中
				PublicInstitution publicInst = publicInstitutionService.get(pId);
				boolean flag = alternatingRotationService.operationAlterFlag(publicInst);
				if (flag) {
					throw new BusinessException("当前用户正在轮岗处理中，不能重复处理！");
				}
				
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				temp.setPublicInstitution(publicInst);;
				alternatingRotationService.executeAlternatingRotation(temp,opinion,r, planState);
			} else {
				AlternatingRotation alternatingRotation = alternatingRotationService.load(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp,alternatingRotation);
				DictUtils.operationCodeInfo(alternatingRotation);//将CodeInfo中id为空的属性 设置为null
				alternatingRotationService.executeAlternatingRotation(alternatingRotation,opinion,r, planState);
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
	 * @Title: planFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return: String
	 */
	@RequestMapping("/queryRegisterInfo")
	public String planFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.get(id);
		String busType = flow.getBusType();
		AlternatingRotation alternatingRotation = alternatingRotationService.get(flow.getBusId());
		model.addAttribute("alternatingRotation", alternatingRotation);
		
		//办理详情
		List<FlowRecordVO> records = showFlowRecordDetail(id, model);
		model.addAttribute("records", records);
		
		return INST_SUBMIT_OPERATION;
	}

	
	/**
	 * 查询业务描述详情
	 * @param id  流程id
	 * @param model Model
	 * @return List<FlowRecordVO
	 */
	public List<FlowRecordVO> showFlowRecordDetail(String id, Model model) {
		FlowRecord flowRecord = flowRecordService.get(id);
		List<FlowRecordVO> records = flowRecordService.findFlowRecordByBusinessId(flowRecord.getBusId(),
		        flowRecord.getBusType(), false);
		//model.addAttribute("records", records);
		return records;
	}
	
}
