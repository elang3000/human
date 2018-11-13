
package com.wondersgroup.human.controller.ofcflow;

import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.bo.ofcflow.JobShiftDepose;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.JobShiftDeposeService;
import com.wondersgroup.human.service.ofcflow.JobShiftService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.organization.JudgePostResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: JobChangeController.java
 * </p>
 * <p>
 * Description:职务变动控制器
 * </p>
 * @author youyd
 * @date 2018年8月14日
 * @version 1.0
 */
@Controller
@RequestMapping("ofcflow/jobchange")
public class JobShiftController extends GenericController {
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private JobShiftService jobShiftService;
	
	@Autowired
	private JobShiftDeposeService jobShiftDeposeService;
	
	@Autowired
	private FlowRecordService flowRecordService;

    @Autowired
    private DictableService dictableService;
    
	@Autowired
	private FormationControlService formationControlService;
	
	@Autowired
	private CodeInfoService codeInfoService;

	// 职务变动列表页面
	private final static String JOBCHANGE_INDEX_PAGE = "models/ofcflow/jobChange/jobChangeIndex";

	// 职务变动列表页面
	private final static String JOBCHANGE_HUMANPICK_PAGE = "models/ofcflow/jobChange/jobChangeHumanPick";

	// 职务变动详情页面
	private final static String JOBCHANGE_DETIAL_PAGE = "models/ofcflow/jobChange/jobChangeDetail";
	
	// 职务变动-晋升页面
	private final static String JOBCHANGE_PROMOTE_PAGE = "models/ofcflow/jobChange/jobChangePromote";
	
	// 降职流程审批页面
	private final static String JOBCHANGE_PROMOTE_FLOW_PAGE = "models/ofcflow/jobChange/flow/jobChangePromoteFlow";
	
	// 职务变动-降职页面
	private final static String JOBCHANGE_DEMOTE_PAGE = "models/ofcflow/jobChange/jobChangeDemote";
	
	// 降职流程审批页面
	private final static String JOBCHANGE_DEMOTE_FLOW_PAGE = "models/ofcflow/jobChange/flow/jobChangeDemoteFlow";
	
	// 职务变动-免职页面
	private final static String JOBCHANGE_DEPOSE_PAGE = "models/ofcflow/jobChange/jobChangeDepose";
	
	// 职务变动-免职流程审批页面
	private final static String JOBCHANGE_DEPOSE_FLOW_PAGE = "models/ofcflow/jobChange/flow/jobChangeDeposeFlow";
	
	// 职务变动-轮岗  暂时没用,和降职使用同一页面
//	private final static String JOBCHANGE_SHIFT_PAGE = "models/ofcflow/jobChange/jobChangeShift";
	
	// 职务变动管理流程代办列表
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动主页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String jobChangeIndexPage(Model model) {
		OrganNode orgNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		String orgId = orgNode.getId();
		model.addAttribute("orgId", orgId);
		return JOBCHANGE_INDEX_PAGE;
//		return JOBCHANGE_HUMANPICK_PAGE;
	}

	/**
	 * 主页数据
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/indexData")
    @ResponseBody
	public Page<Map> jobChangeIndexData(String name,Integer page,String jobChangeType, Integer limit){
        OrganNode orgNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
        String orgId = orgNode.getId();
        Page<Map> formRecordData = this.jobShiftService.getFormRecordData(orgId, jobChangeType, name, page, limit);
        return  formRecordData;
    }

    @RequestMapping("/detailView")
    public String jobChangeView(Model model,String id,String postTenureChangeCode){

		//职务变动代码DM006 职务晋升
        List<CodeInfo> promoteCodes = dictableService.findCodeInfoByCodeType("DM006", "2");
		List<String> codeStrs = new ArrayList<>();
        promoteCodes.forEach(
			codeInfo->{
				codeStrs.add(codeInfo.getId());
			}
		);
		if(codeStrs.contains(postTenureChangeCode)){
			return JOBCHANGE_PROMOTE_PAGE;
		}
        //职务变动代码 降职
		CodeInfo demoteCode = dictableService.getCodeInfoByCode("5", "DM006");
		if(demoteCode.getId().equals(postTenureChangeCode)){
			return "2";
		}
		//职务变动代码 免职
		List<CodeInfo> deposeCodes = dictableService.findCodeInfoByCodeType("DM006", "4");
		List<String> deposeCodeStrs = new ArrayList<>();
		deposeCodes.forEach(codeInfo -> {
			deposeCodeStrs.add(codeInfo.getId());
		});
		if (deposeCodeStrs.contains(postTenureChangeCode)){
			return "3";
		}
		//职务变动代码 轮岗
		CodeInfo shiftCode = dictableService.getCodeInfoByCode("6", "DM006");
		if(shiftCode.getId().equals(postTenureChangeCode)){
			return "4";
		}
		throw new BusinessException("没有对应的职务变动类型");
	}

	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动选择人员页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/humanPick")
	public String jobChangeHumanPick(Model model) {
		OrganNode orgNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		String orgId = orgNode.getId();
		model.addAttribute("orgId", orgId);
		return JOBCHANGE_HUMANPICK_PAGE;
	}

	/**
	 * @Title: flow
	 * @Description: 流程审批列表页面
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType", FlowBusTypeConstant.FLOW_JOBSHIFT_ALL);
		return RECRUIT_EMPLOYPLAN_FLOW;
	}
	
	/**
	 * @Title: jobChangeFlow
	 * @Description: 流程审批页面跳转,根据流程类型不同,分别重定向到不同的页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/jobChangeFlow")
	public String jobChangeFlow(Model model, String id) {
		FlowRecord flow = flowRecordService.load(id);
		String busType = flow.getBusType();
		//根据流程的bustype跳转到不同的页面
		switch (busType) {
			case FlowBusTypeConstant.FLOW_JOBSHIFT_PROMOTE:
				return "forward:promoteFlowPage/" + id;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_DEMOTE:
				return "forward:demoteFlowPage/" + id;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_DEPOSE:
				return "forward:deposeFlowPage/" + id;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT:
				return "forward:demoteFlowPage/" + id;
			default:
				throw new BusinessException("无法找到指定页面!");
		}
	}
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动--晋升表单页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/promote/{servantId}/post/{postId}", method = {RequestMethod.GET})
	public String jobChangePromote(@PathVariable(value = "servantId") String servantId,
	        @PathVariable(value = "postId") String postId, Model model) {
		Servant servant = servantService.get(servantId);
		Post post = postService.get(postId);
		model.addAttribute("servant", servant);
		model.addAttribute("post", post);
		return JOBCHANGE_PROMOTE_PAGE;
	}
	
	/**
	 * @Title: demoteFlowPage
	 * @Description: 晋升流程审批页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/promoteFlowPage/{id}")
	public String promoteFlowPage(@PathVariable(value = "id") String id, Model model) {
		
		FlowRecord flow = flowRecordService.load(id);
		// 获取业务对象
		JobShift jobShift = this.jobShiftService.get(flow.getBusId());
		// 获取操作的人员对象
		Servant servant = jobShift.getServant();
		Post post = jobShift.getPrePost();
		model.addAttribute("post", post);
		model.addAttribute("servant", servant);
		model.addAttribute("jobShift", jobShift);
		// 第7部时显示文件上传按钮 不是第七步
		model.addAttribute("isStep7", !flow.getOperationCode().equals("STATUS_JOBSHIFT_PROMOTE_STEP7"));
		model.addAttribute("isStep12", !flow.getOperationCode().equals("STATUS_JOBSHIFT_PROMOTE_STEP12"));
		model.addAttribute("isStep17", !flow.getOperationCode().equals("STATUS_JOBSHIFT_PROMOTE_STEP17"));
		//假如是第一步的跳转到表单页面
		if (flow.getOperationCode().equals(JobShift.JOBSHIFT_PROMOTE_STEP1)) { return JOBCHANGE_PROMOTE_PAGE; }
		//跳转到流程审批页面
		return JOBCHANGE_PROMOTE_FLOW_PAGE;
	}
	
	/**
	 * @Title: jobChangeSave
	 * @Description: 保存职务变动-晋升以及 流程操作
	 * @param jobShift
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping(value = "/operatePromoteFlow")
	public AjaxResult jobChangePromoteSave(JobShift jobShift, String opinion, String result) {
	
		AjaxResult ajaxResult = new AjaxResult(true);
		String examineFilePath = jobShift.getExamineFilePath();
		String personInfoFilePath = jobShift.getPersonInfoFilePath();
		if (!StringUtils.isBlank(jobShift.getId())) {
			jobShift = this.jobShiftService.get(jobShift.getId());
		}
		try {
			
			OrganNode orgNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			String orgId = orgNode.getId();
			//新职位
			CodeInfo newPost = codeInfoService.load(jobShift.getNewPostCode().getId());
			//旧职位
			CodeInfo formerPost = codeInfoService.load(jobShift.getFormerPostCode().getId());
			//编控，校验职位编制数是否足够，判断数据能否保存，如果超编，抛出异常
			JudgePostResult queryJudgePostNum = formationControlService.queryJudgePostNum(orgId, newPost.getCode());
			//锁定职位调入编控
			formationControlService.executeLockPostIntoNum(orgId, newPost.getCode(), queryJudgePostNum.isLowToHigh);
			//锁定职位调出编控
			formationControlService.executeLockPostOutNum(orgId, formerPost.getCode(), queryJudgePostNum.isLowToHigh);
			//保存是否高职低配到业务表
			jobShift.setLowToHigh(queryJudgePostNum.isLowToHigh);
			
			// 判断是否上传了人员信息任免表
			if (jobShift.getAppointSheetFilePath() == null || jobShift.getAppointSheetFilePath().equals("")
			        || jobShift.getAppointSheetFilePath().equals("[]")) { throw new BusinessException("请上传人员信息任免表!"); }
			// 是否在第七个步骤中上传了考核材料
			if (jobShift.getFlowRecord() != null
			        && jobShift.getFlowRecord().getOperationCode().equals("STATUS_JOBSHIFT_PROMOTE_STEP7")) {
				if (examineFilePath.equals("[]")) {
					throw new BusinessException("请上传考核材料文件!");
				} else {
					jobShift.setExamineFilePath(examineFilePath);
				}
			}
			// 是否在第17个步骤中上传了公示人员信息文件
			if (jobShift.getFlowRecord() != null
			        && jobShift.getFlowRecord().getOperationCode().equals("STATUS_JOBSHIFT_PROMOTE_STEP12")) {
				if (personInfoFilePath.equals("[]")) {
					throw new BusinessException("请上传公示人员信息文件!");
				} else {
					jobShift.setPersonInfoFilePath(personInfoFilePath);
				}
			}
			if (StringUtils.isBlank(result) || (!FlowRecord.PASS.equals(result)
			        && !FlowRecord.NOPASS.equals(result))) { throw new BusinessException("审批结果信息不正确！"); }
			// 审批职务变动晋升
			jobShiftService.updatePromoteFlow(jobShift, opinion, result);
			ajaxResult.setMessage("保存成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！"+e.getMessage());
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动--降职
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/demote/{servantId}/post/{postId}", method = {
	        RequestMethod.GET
	})
	public String jobChangeDemote(@PathVariable(value = "servantId") String servantId,
	        @PathVariable(value = "postId") String postId, Model model) {
		
		Servant servant = servantService.get(servantId);
		Post post = postService.get(postId);
		model.addAttribute("servant", servant);
		model.addAttribute("post", post);
		model.addAttribute("isShift", false);
		model.addAttribute("head", "降职");
		return JOBCHANGE_DEMOTE_PAGE;
	}
	
	/**
	 * @Title: demoteFlowPage
	 * @Description: 降职流程审批页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/demoteFlowPage/{id}")
	public String demoteFlowPage(@PathVariable(value = "id") String id, Model model) {
		
		FlowRecord flow = flowRecordService.load(id);
		// 获取业务对象
		JobShift jobShift = this.jobShiftService.get(flow.getBusId());
		// 获取操作的人员对象
		Servant servant = jobShift.getServant();
		Post post = jobShift.getPrePost();
		model.addAttribute("post", post);
		model.addAttribute("servant", servant);
		model.addAttribute("jobShift", jobShift);
		model.addAttribute("isShift", flow.getBusType().equals(FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT));
		if (flow.getOperationCode().equals(JobShift.JOBSHIFT_DEMOTE_STEP1)) { return JOBCHANGE_DEMOTE_PAGE; }
		return JOBCHANGE_DEMOTE_FLOW_PAGE;
	}
	
	/**
	 * @Title: jobChangeSave
	 * @Description: 职务变动-降职 流程操作
	 * @param result 审批结果 opinion 审批意见 jobShift 表单对象
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping(value = "/operateDemoteFlow")
	public AjaxResult jobChangeDemoteSave(JobShift jobShift, String opinion, String result,boolean isShift) {
		
		AjaxResult ajaxResult = new AjaxResult(true);
		if (!StringUtils.isBlank(jobShift.getId())) {
			JobShift jobShiftDB = this.jobShiftService.get(jobShift.getId());
			BeanUtils.copyPropertiesIgnoreNull(jobShift, jobShiftDB);
			jobShift = jobShiftDB;
		}
		try {
			
			
			//YYDTODO 操作流程前要进行 超编判断,超编则不允许变动,并且返回提示.如果没有超编,则占用编制,继续以下代码
			//YYDTODO 假如流程取消,则让出编制
			OrganNode orgNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			String orgId = orgNode.getId();
			
			
			
			//新职位
			CodeInfo newPost = codeInfoService.load(jobShift.getNewPostCode().getId());
			//旧职位
			CodeInfo formerPost = codeInfoService.load(jobShift.getFormerPostCode().getId());
			//编控，校验职位编制数是否足够，判断数据能否保存，如果超编，抛出异常
			JudgePostResult queryJudgePostNum = formationControlService.queryJudgePostNum(orgId, newPost.getCode());
			//锁定职位调入编控
			formationControlService.executeLockPostIntoNum(orgId, newPost.getCode(), queryJudgePostNum.isLowToHigh);
			//锁定职位调出编控
			formationControlService.executeLockPostOutNum(orgId, formerPost.getCode(), queryJudgePostNum.isLowToHigh);
			
			
			if (StringUtils.isBlank(result) || (!FlowRecord.PASS.equals(result)
			        && !FlowRecord.NOPASS.equals(result))) { throw new BusinessException("审批结果信息不正确！"); }
			// 审批职务变动降职
			jobShiftService.updateDemoteFlow(jobShift, opinion, result, isShift);
			ajaxResult.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！");
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动--免职表单页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/depose/{servantId}/post/{postId}", method = {
	        RequestMethod.GET
	})
	public String jobChangeDepose(@PathVariable(value = "servantId") String servantId,
	        @PathVariable(value = "postId") String postId, Model model) {
		
		Servant servant = servantService.get(servantId);
		Post post = postService.get(postId);
		model.addAttribute("servant", servant);
		model.addAttribute("post", post);
		return JOBCHANGE_DEPOSE_PAGE;
	}
	
	/**
	 * @Title: deposeFlowPage
	 * @Description: 免职流程审批页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/deposeFlowPage/{id}")
	public String deposeFlowPage(@PathVariable(value = "id") String id, Model model) {
		
		FlowRecord flow = flowRecordService.load(id);
		// 获取业务对象
		JobShiftDepose jobShiftDepose = this.jobShiftDeposeService.get(flow.getBusId());
		// 获取操作的人员对象
		Servant servant = jobShiftDepose.getServant();
		Post post = jobShiftDepose.getPost();
		model.addAttribute("post", post);
		model.addAttribute("servant", servant);
		model.addAttribute("jobShiftDepose", jobShiftDepose);
		//假如是流程第一步,那么跳转到表单页面
		if (flow.getOperationCode().equals(JobShift.JOBSHIFT_DEMOTE_STEP1)) { return JOBCHANGE_DEPOSE_PAGE; }
		return JOBCHANGE_DEPOSE_FLOW_PAGE;
	}
	
	/**
	 * @Title:
	 * @Description: 职务变动-免职 流程操作
	 * @param isSubmit 是否提交 result 审批结果 opinion 审批意见 jobShift 表单对象
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping(value = "/operateDeposeFlow")
	public AjaxResult jobChangeDeposeSave(JobShiftDepose jobShiftDepose, String opinion, String result,
	        String isSubmit) {
		
		AjaxResult ajaxResult = new AjaxResult(true);
		if (!StringUtils.isBlank(jobShiftDepose.getId())) {
			JobShiftDepose jobShiftDB = this.jobShiftDeposeService.get(jobShiftDepose.getId());
			BeanUtils.copyPropertiesIgnoreNull(jobShiftDepose, jobShiftDB);
			jobShiftDepose = jobShiftDB;
		}
		try {
			
			
			//YYDTODO 操作流程前要进行 超编判断,超编则不允许变动,并且返回提示.如果没有超编,则占用编制,继续以下代码
			//YYDTODO 假如流程取消,则让出编制
			OrganNode orgNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			String orgId = orgNode.getId();
			Post prePost = postService.load(jobShiftDepose.getPost().getId());
			//旧职位
			CodeInfo formerPost = codeInfoService.load(prePost.getPostCode().getId());
			//YYDTODO 锁定职位调出编控
//			formationControlService.executeLockPostOutNum(orgId, formerPost.getCode(), jobShiftDepose.getPost().getIsLowToHigh());
			
			
			if (StringUtils.isBlank(result) || (!FlowRecord.PASS.equals(result)
			        && !FlowRecord.NOPASS.equals(result))) { throw new BusinessException("审批结果信息不正确！"); }
			// 审批职务变动降职
			jobShiftDeposeService.updateDeposeFlow(jobShiftDepose, opinion, result);
			ajaxResult.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！");
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 轮岗
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/shift/{servantId}/post/{postId}", method = {
	        RequestMethod.GET
	})
	public String jobChangeShift(@PathVariable(value = "servantId") String servantId,
	        @PathVariable(value = "postId") String postId, Model model) {
		
		Servant servant = servantService.get(servantId);
		Post post = postService.get(postId);
		model.addAttribute("servant", servant);
		model.addAttribute("post", post);
		model.addAttribute("isShift", true);
		model.addAttribute("head", "轮岗");
		// 和降职使用同一个页面
		return JOBCHANGE_DEMOTE_PAGE;
	}
	
	/**
	 * @Title: jobChangeDetailPage
	 * @Description: 职务变动详情页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/detail/{servantId}", method = {
	        RequestMethod.GET
	})
	public String jobChangeDetailPage(@PathVariable("servantId") String servantId, Model model) {
		
		Servant servant = servantService.get(servantId);
		model.addAttribute("servant", servant);
		model.addAttribute("servantId", servantId);
		return JOBCHANGE_DETIAL_PAGE;
	}
	
}
