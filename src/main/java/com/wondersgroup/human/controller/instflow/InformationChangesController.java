package com.wondersgroup.human.controller.instflow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.framework.workflow.vo.FlowRecordVO;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.instflow.InfoChangePublicInstitutionService;
import com.wondersgroup.human.service.instflow.InformationChangeService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;

@Controller
@RequestMapping("/instflow/informationchanges")
public class InformationChangesController extends GenericController{
	
	@Autowired
	private InfoChangePublicInstitutionService infoChangePublicInstitutionService;
	
	@Autowired
	private InformationChangeService informationChangeService;
	
	@Autowired
	private OrganizationService organinationService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private PublicInstitutionService  publicInstitutionService;
	
	//人员信息变更待办列表
	private static final String VIEW_INSTITUTION_LIST = "models/instflow/informationchanges/list";
	
	//人员信息列表
	private static final String VIEW_INFORMATION_INDEX ="models/instflow/informationchanges/index";
	
	//人员信息变更提交页面
	private static final String VIEW_INFORMATON_APPLY ="models/instflow/informationchanges/applyinformation";
	
	//审批页面
	private static final String CHECK_INFORMATION_FLOW = "models/instflow/informationchanges/queryinformation";
	
	
	/*
	 * 人员信息待办列表
	 */
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("busType","InformationChange");
		return VIEW_INSTITUTION_LIST;
	}
	/*
	 * 人员信息查看
	 */
	@RequestMapping("/index")
	public String informationchanges(){
		return VIEW_INFORMATION_INDEX;
		
		
	}
	
	/*
	 * 人员信息变更提交申请页面
	 */
	@RequestMapping("/informationChangeApply")
	public String returnApply(InformationChange temp, Model model, HttpServletRequest request){
		//获取当前用户信息
		String pid = request.getParameter("id");
		PublicInstitution publicInst = publicInstitutionService.load(pid);
		if (publicInst != null) {
			temp.setPublicInstitution(publicInst);
		}
		model.addAttribute("publicinstitution", publicInst);
		//获取用户当前单位
		OrganNode root = organinationService.getCurrentUnitOrganForOrganNodeCode(CommonConst.ROOT_ORGAN_CODE);
		OrganNode currentUnit = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("root", root);
		model.addAttribute("currentUnit", currentUnit);
		
		return VIEW_INFORMATON_APPLY;
	
	}
	
	/**
	 * @Title: operationRegister
	 * @Description: 人员信息变更(上报)
	 * @param temp	 
	 * @param request recordOrgan
	 * @return
	 * @return: AjaxResult
	 */
	//(CREATE_TIME, CREATER, REMOVED, InformationChange_id, INFORMATION_CHANGE_DATE, FLOWRECORD_ID, nowOrgan_id, OPINION, PLAN_STATE, publicInstitution_id, REMARK, id) 

	@ResponseBody
	@RequestMapping("/submitinformation")
	public AjaxResult submitinformation(InformationChange temp,HttpServletRequest request){
		AjaxResult result = new AjaxResult(true);
		String planState = request.getParameter("planState");//审批状态
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		//String remark = request.getParameter("remark");//备注
		//String changeDate = request.getParameter("changeDate");//更改时间
		String pid = request.getParameter("pid");
		
		try {
			if(StringUtils.isBlank(r) || (!FlowRecord.PASS.equals(r) && !FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确!");
			}
			
			if(StringUtils.isBlank(temp.getId())){
				
				PublicInstitution publicInst = publicInstitutionService.load(pid);
				boolean flag = informationChangeService.operationPublicFlag(publicInst);
				if (flag) {
					throw new BusinessException("当前用户正在信息变更处理中，不能重复处理！");
				}
				
				//InfoChangePublicInstitution infochangepublic = infoChangePublicInstitutionService.load(pid);
				 temp.setPublicInstitution(publicInst);
				//temp.setInfoChangePublicInstitution(infochangepublic);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性,设置为null
				temp.setId(null);
				informationChangeService.saveRegister(temp, opinion, r, planState);
			}else{
				InformationChange information =	informationChangeService.load(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, information);
				DictUtils.operationCodeInfo(information);
				informationChangeService.saveRegister(information, opinion, r, planState );
				
			}
			result.setMessage("操作成功!");
			
		}catch (BusinessException e){ 
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("操作失败!");
		}
		
		
		return result;
	
		
	}
	
	/*
	 * 审批页面详情
	 */ 
	@RequestMapping("/queryinformation")
	public String checkFlow(Model model,String id){
		FlowRecord flow = flowRecordService.load(id);
		String busType = flow.getBusType();
		InformationChange informationchange = informationChangeService.get(flow.getBusId());
		model.addAttribute("informationchange", informationchange);
		//InfoChangePublicInstitution infochangepublic = infoChangePublicInstitutionService.get(flow.getBusId());
		//model.addAttribute("infochangepublic", infochangepublic);

		//办理详情
		List<FlowRecordVO> records = inforFlowRecordDetail(id, model);
		model.addAttribute("records", records);
		
		return CHECK_INFORMATION_FLOW;
		
		
	}
	
	/*
	 * 查询业务描述详情
	 */
	public List<FlowRecordVO> inforFlowRecordDetail(String id, Model model) {
		FlowRecord flowRecord = flowRecordService.get(id);
		List<FlowRecordVO> records = flowRecordService.findFlowRecordByBusinessId(flowRecord.getBusId(),
		        flowRecord.getBusType(), false);
		//model.addAttribute("records", records);
		return records;
	}
	
}
