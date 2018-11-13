package com.wondersgroup.human.controller.instflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
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
import com.wondersgroup.human.bo.instflow.RecordableRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.instflow.RecordableRecordService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

@Controller
@RequestMapping("/instflow/recordablerecord")//离退备案instflow/recordablerecord/index
public class RecordableRecordController extends GenericController{
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	@Autowired
	private RecordableRecordService recordableService;
	
	@Autowired
	private OrganizationService organinationService;
	
	@Autowired
	private FlowRecordService flowRecordService;

	
	//人员列表index
	private static final String  VIEW_INSTITUTION_LIST = "models/instflow/recordablerecord/list";
	//事业单位待离退备案人员信息查看
	private static final String VIEW_RECORD_LIST = "models/instflow/recordablerecord/personList";
	
	//离退人员申请页面
	private static final String INST_RECORD_APPLY = "models/instflow/recordablerecord/applyregister";
	
	//跳转提交审核信息列表
	//private static final String RETURN_SUBMIT_PAGE ="models/instflow/recordablerecord/register";
	
	//审批页面
	private static final String OPINION_RECORD_FLOW = "models/instflow/recordablerecord/queryregister";
	/**
	 * @Title:registerList
	 * @Description: 人员信息备案列表
	 * @returnneng
	 * @return: String
	 */
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("busType","RecordableRecord");
		return VIEW_INSTITUTION_LIST;
	}
	
	//人员信息查看
	@RequestMapping("/personList")
	public String recordlist(){
		return VIEW_RECORD_LIST;
	}
	
	//离退人员提交申请页面
	@RequestMapping("/recordApply")
	public String returnApply(Model model,String id) {
		//当前用户信息
		PublicInstitution instInfo = publicInstitutionService.load(id);
		model.addAttribute("publicInstitution", instInfo);
		
		
		//得到用户当前所在的单位
		//长宁区
		OrganNode root = organinationService.getCurrentUnitOrganForOrganNodeCode(CommonConst.ROOT_ORGAN_CODE);
		OrganNode currentUnit = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("root", root);
		model.addAttribute("currentUnit", currentUnit);
		
		return INST_RECORD_APPLY;
	}
	
	//新增离退备案登记
	/*@RequestMapping("/register")
	public String plan(Model model, String employPlanId,Integer level, String yearPlanId) {
		//得到用户当前所在的单位
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		//长宁区
		OrganNode root = organinationService.getCurrentUnitOrganForOrganNodeCode(CommonConst.ROOT_ORGAN_CODE);
		
		model.addAttribute("OrganNode", x);
		model.addAttribute("rootOrgan", root);
		
		return RETURN_SUBMIT_PAGE;
	}
	*/
	
	/**
	 * @Title: operationRegister
	 * @Description: 人员离退备案(上报)
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/submitregister")
	public AjaxResult submitRegister(RecordableRecord temp,HttpServletRequest request){
		AjaxResult result= new AjaxResult(true);
		String planState = request.getParameter("planState");//审批状态
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		String pId = request.getParameter("pid");//
		
		try {
			if (StringUtils.isBlank(r) || (!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))) {
				throw new BusinessException("审批结果信息不正确！");
			}
			
			if (StringUtils.isBlank(temp.getId())) {
				PublicInstitution publicInstitution = publicInstitutionService.load(pId);
				boolean flag = recordableService.operationPublicFlag(publicInstitution);
				if (flag) {
					throw new BusinessException("当前用户正在离退备案处理中，不能重复提交！");
				}
				
				
				temp.setPublicInstitution(publicInstitution );
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				recordableService.saveRegister(temp,opinion,r,planState);
			} else {
				RecordableRecord recordablerecord =recordableService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp,recordablerecord);
				//BeanUtils.copyProperties(temp, recordable);
				DictUtils.operationCodeInfo(recordablerecord);
				recordableService.saveRegister(recordablerecord, opinion, r, planState);
				
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
	
	/*
	 * 审批详情页面
	 */
	@RequestMapping("/queryRegister")
	public String planFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		String busType = flow.getBusType();
		RecordableRecord recordablerecord = recordableService.get(flow.getBusId());
		model.addAttribute("recordablerecord", recordablerecord);
		
		//办理详情
		List<FlowRecordVO> records = recordFlowRecordDetail(id, model);
		model.addAttribute("records", records);
		return OPINION_RECORD_FLOW;
	}
	
	
	/*
	 * 查询业务描述详情
	 */
	public List<FlowRecordVO> recordFlowRecordDetail(String id, Model model) {
		FlowRecord flowRecord = flowRecordService.get(id);
		List<FlowRecordVO> records = flowRecordService.findFlowRecordByBusinessId(flowRecord.getBusId(),
		        flowRecord.getBusType(), false);
		//model.addAttribute("records", records);
		return records;
	}
	
	
	
	
	
	
	
	
	
	
	
}
