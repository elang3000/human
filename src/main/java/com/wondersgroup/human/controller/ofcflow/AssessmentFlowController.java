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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.AssessmentDetail;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowCollect;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowUnitPercent;
import com.wondersgroup.human.service.ofc.AssessmentService;
import com.wondersgroup.human.service.ofc.RewardAndPunishService;
import com.wondersgroup.human.service.ofcflow.AssessmentDetailService;
import com.wondersgroup.human.service.ofcflow.AssessmentFlowCollectService;
import com.wondersgroup.human.service.ofcflow.AssessmentFlowUnitPercentService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.vo.ofcflow.AssessFlowUnitCollectVO;
import com.wondersgroup.human.vo.ofcflow.AssessmentDetailVO;
import com.wondersgroup.human.vo.ofcflow.AssessmentFlowUnitPercentEditVO;
import com.wondersgroup.human.vo.ofcflow.AssessmentFlowUnitPercentVO;

/**
 * @ClassName: AccessmentController
 * @Description: 公务员考核 控制器
 * @author: youyd
 * @date: 2018年9月20日15:45:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]-.
 * @since     [产品/模块版本]
 */
@RequestMapping("/ofcflow/assess")
@Controller
public class AssessmentFlowController extends GenericController {
	
	// 公务员考核入口列表页面
	private static final String ASSESS_INDEX_PAGE = "models/ofcflow/assess/assessIndex";
	
	// 发起考核页面
	private static final String ASSESS_START_PAGE = "models/ofcflow/assess/assessStart";
	
	// 公务员考核 区人事部门查看考核指标页面
	private static final String ASSESS_TARGET_PAGE = "models/ofcflow/assess/assessTarget";
	
	//公务员考核 区人事指定修改考核指标页面
	private static final String ASSESS_TARGET_EDIT_PAGE="models/ofcflow/assess/assessTargetEdit";
	
	// 公务员考核审批 单位人员考核列表页面
	private static final String ASSESS_UNITCHECK_INDEX_PAGE = "models/ofcflow/assess/assessUnitCheckIndex";
	
	//公务员考核审批  单位人员考核页面
	private static final String ASSESS_UNITCHECK_PAGE = "models/ofcflow/assess/assessUnitCheck";
	
	//查看审核进度页面-年度考核
	private static final String ASSESS_PROGRESS_YEAR_PAGE="models/ofcflow/assess/assessProcessYearView";
	
	//查看审核进度页面-季度考核
	private static final String ASSESS_PROGRESS_SEASON_PAGE="models/ofcflow/assess/assessProcessSeasonView";
	
	// 单位发起流程
	private static final String ASSESS_UNIT_START="models/ofcflow/assess/flow/assessUnitStart";
	
	// 流程 审批审核为优秀的公务员的数量
	private static final String ASSESS_FLOW_NUMBERCHECK_PAGE = "models/ofcflow/assess/flow/assessFlowNumberCheck";
	
	// 流程 上报考核人名单 审核考核人名单页面
	private static final String ASSESS_FLOW_HUMANPICK_PAGE = "models/ofcflow/assess/flow/assessFlowHumanPick";
	
	// 区人事汇总,重新分配页面
	private static final String ASSESS_FLOW_REPICK_PAGE = "models/ofcflow/assess/flow/assessFlowRepick";
	
	// 区人事汇总完成 查看页面
	private static final String ASSESS_FLOW_COMPLETE_DETAIL_PAGE = "models/ofcflow/assess/assessFlowCompleteDetail";
	
	// 年度考核奖励流程代办列表
	private final static String ASSESS_FLOW = "models/ofcflow/employPlan/flow";
	
	
	@Autowired
	private AssessmentFlowCollectService assessmentFlowCollectService;
	
	@Resource
	private AssessmentDetailService assessmentDetailService;
	
	@Autowired
	private AssessmentFlowUnitPercentService assessmentFlowUnitPercentService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	private RewardAndPunishService rewardAndPunishService;
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	

	
	/**
	 * @Title: indexPage
	 * @Description: 公务员考核首页入口列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/assessIndex", method = {
	        RequestMethod.GET
	})
	public String indexPage(Model model) {
		// YYDTODO 判断当前是否区人事登录,对相应的按钮设置权限,区人事可以点击季度和年度考核按钮和编辑考核比例人数,单位<具有指定权限的人员>可以点击单位人员考核按钮,配置人员菜单权限
		OrganNode org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		//是否人社局
		boolean isBureau = true;
		if (org.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
			isBureau=true;
		} else {
			isBureau=false;
		}
		model.addAttribute("isBureau", isBureau);
		return ASSESS_INDEX_PAGE;
	}
	
	
	/**
	 * 
	 * @Title: flow 
	 * @Description: 流程主页
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType", FlowBusTypeConstant.FLOW_ASSESS_REWARD);
		return ASSESS_FLOW;
	}
	
	/**
	 * 
	 * @Title: assessFlow 
	 * @Description: 流程跳转重定向
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/assessFlow")
	public String assessFlow(Model model, String id){
		FlowRecord flow = flowRecordService.load(id);
		String step=flow.getOperationCode();
		AssessmentFlowUnitPercent assessmentFlowUnitPercent = this.assessmentFlowUnitPercentService.get(flow.getBusId());
		List<String> flow1List=new ArrayList<>();
		List<String> flow2List=new ArrayList<>();
//		flow1List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP1);
		flow1List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP2);
		flow1List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP3);
		flow1List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP4);
		flow1List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP5);
		flow1List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP6);
		
		flow2List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP7);
		flow2List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP8);
		flow2List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP9);
		flow2List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP10);
		flow2List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP11);
		flow2List.add(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP12);
		
		if(flow1List.contains(step)){
			return "forward:assessNumbFlowView/"+id;
		}
		if(flow2List.contains(step)){
			return "forward:assessPickPeopleFlow/"+id;
		}
		if(step.equals(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP1)){
			OrganNode org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			AssessmentFlowCollect assessmentFlowCollect = this.assessmentFlowCollectService.get(assessmentFlowUnitPercent.getAssessmentFlowCollect().getId());
			//获取单位的percent
			AssessmentFlowUnitPercent percent = this.assessmentFlowUnitPercentService.getByCollectAndOrg(assessmentFlowUnitPercent.getAssessmentFlowCollect().getId(), org);
			Map<String,Object> maps=new HashMap<>();
			maps.put("percent", percent);
			maps.put("collect", assessmentFlowCollect);
			model.addAllAttributes(maps);
			return  ASSESS_UNIT_START;
		}
		return null;
	}
	
	/**
	 * @Title: getAssessmentFlowCollectList
	 * @Description: 考核记录列表数据
	 * @return
	 * @return: Page<AssessmentFlowCollect>
	 */
	@ResponseBody
	@RequestMapping(value = "/assessCollects")
	public Page<AssessFlowUnitCollectVO> getAssessmentFlowCollectList(Integer year,Integer limit, Integer page) {
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AssessmentFlowCollect.class);
//		if(year!=null){
//			detachedCriteria.add(Restrictions.eq("year", year));
//		}
//		detachedCriteria.add(Restrictions.eq("removed", false));
//		detachedCriteria.addOrder(Order.desc("createTime"));
		OrganNode org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		Page<AssessFlowUnitCollectVO> collectAndFlowStatus = this.assessmentFlowCollectService.getCollectAndFlowStatus(org,page, limit);
//        Page<AssessmentFlowCollect> pages = this.assessmentFlowCollectService.findByCriteria(detachedCriteria, page, limit);
		return collectAndFlowStatus;
	}
	
	/**
	 * @Title: assessStart
	 * @Description: 区人事发起考核页面
	 * @param assessType
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/assessStart/{assessmentType}", method = {
	        RequestMethod.GET
	})
	public String assessStart(@PathVariable(value = "assessmentType") Integer assessmentType, Model model) {
		
		boolean isYearAssess;
		if (assessmentType == AssessmentFlowCollect.ASSESSYEAR) {
			isYearAssess = true;
		} else if (assessmentType == AssessmentFlowCollect.ASSESSSEASON) {
			isYearAssess = false;
		} else {
			throw new BusinessException("考核类型错误!");
		}
		model.addAttribute("isYearAssess", isYearAssess);
		model.addAttribute("assessmentType", assessmentType);
		return ASSESS_START_PAGE;
	}
	
	/**
	 * 
	 * @Title: assessTarget 
	 * @Description: 考核人数指标列表页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="/assessTarget/{id}",method={RequestMethod.GET})
	public String assessTarget(@PathVariable(value="id") String id,Model model){
		Map<String,Object> maps=new HashMap<>();
		maps.put("id", id);
		model.addAllAttributes(maps);
		return ASSESS_TARGET_PAGE;
	}
	
	/**
	 * 
	 * @Title: assessTarget 
	 * @Description: 考核人数指标指定页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="/assessTargetEdit/{id}",method={RequestMethod.GET})
	public String assessTargetEdit(@PathVariable(value="id") String id,Model model){
		Map<String,Object> maps=new HashMap<>();

		maps.put("id", id);
		maps.put("outStandingNumb",assessmentFlowUnitPercentService.get(id).getOutstandingNumb());
		model.addAllAttributes(maps);
		return ASSESS_TARGET_EDIT_PAGE;
	}
	
	/**
	 * 
	 * @Title: assessTarget 
	 * @Description: 考核人数指标指定保存方法
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="/assessTargetEditSave",method={RequestMethod.POST})
	@ResponseBody
	public AjaxResult assessTargetEditSave(@RequestParam(value="id",required=true) String id,@RequestParam(value="outstandingNumb",required=true) Integer outstandingNumb,Model model){
		AjaxResult result = new AjaxResult(true);
		try {
			result.setSuccess(true);
			result.setMessage(CommonConst.AJAXRESULT_SUCCESS);
			AssessmentFlowUnitPercent assessmentFlowUnitPercent = this.assessmentFlowUnitPercentService.get(id);
			assessmentFlowUnitPercent.setOutstandingNumb(outstandingNumb);
			assessmentFlowUnitPercentService.update(assessmentFlowUnitPercent);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(CommonConst.AJAXRESULT_FAIL + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: assessTargetData 
	 * @Description: 考核人数列表页面数据
	 * @param id
	 * @param model
	 * @param limit
	 * @param page
	 * @param name
	 * @return
	 * @return: Page<AssessmentFlowUnitPercentVO>
	 */
	@RequestMapping(value="/assessTargetData/{id}")
	@ResponseBody
	public Page<AssessmentFlowUnitPercentEditVO> assessTargetData(@PathVariable(value="id") String id,Model model,Integer limit, Integer page,String name){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AssessmentFlowUnitPercent.class);
		detachedCriteria.add(Restrictions.eq("assessmentFlowCollect.id", id));
		if(StringUtils.isNotEmpty(name)){
			detachedCriteria.add(Restrictions.like("orgName", "%"+name+"%"));
		}
		Page<AssessmentFlowUnitPercent> percentPage = assessmentFlowUnitPercentService.findByCriteria(detachedCriteria, page, limit);
		List<AssessmentFlowUnitPercentEditVO> voList=new ArrayList<>();
		for(AssessmentFlowUnitPercent flowUnitPercent:percentPage.getResult()){
			AssessmentFlowUnitPercentEditVO flowUnitPercentVO=new AssessmentFlowUnitPercentEditVO(flowUnitPercent);
			voList.add(flowUnitPercentVO);
		}
		Page<AssessmentFlowUnitPercentEditVO> pageVO = new Page<>(percentPage.getStart(), percentPage.getCurrentPageSize(),
				percentPage.getTotalSize(), percentPage.getPageSize(), voList);
		return pageVO;
	}
	
	/**
	 * @Title: assessStartSave
	 * @Description: 保存考核计划表
	 * @param assessmentFlowCollect
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping(value = "/assessCollectSave", method = {
	        RequestMethod.POST
	})
	public AjaxResult assessStartSave(AssessmentFlowCollect assessmentFlowCollect) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			//检查是否已经提交过相同的考核,提交过则不能提交.保存考核计划表
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AssessmentFlowCollect.class);
			detachedCriteria.add(Restrictions.eq("year", assessmentFlowCollect.getYear()));
			detachedCriteria.add(Restrictions.eq("assessmentType", assessmentFlowCollect.getAssessmentType()));
			detachedCriteria.add(Restrictions.eq("removed", false));
			if (null != assessmentFlowCollect.getSeason()) {
				detachedCriteria.add(Restrictions.eq("season", assessmentFlowCollect.getSeason()));
			}
			List<AssessmentFlowCollect> assessmentCollectList = this.assessmentFlowCollectService
			        .findByCriteria(detachedCriteria);
			//查看是否保存相同的记录,假如没有则生成
			if (assessmentCollectList.size() == 0) {
				this.assessmentFlowCollectService.createCollectAndAssessmentDetail(assessmentFlowCollect);
			} else {
				String assessTypeStr = assessmentFlowCollect.getAssessmentType() == AssessmentFlowCollect.ASSESSYEAR
				        ? assessmentFlowCollect.getYear() + " 年度考核"
				        : assessmentFlowCollect.getYear() + "年" + assessmentFlowCollect.getSeason() + " 季度考核";
				throw new BusinessException("已经提交过相同的  " + assessTypeStr);
			}
			
			// YYDTODO 给所有单位有考核权限的人员发送消息,通知进行考核 .年度考核和季度考核通知
			result.setSuccess(true);
			result.setMessage(CommonConst.AJAXRESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(CommonConst.AJAXRESULT_FAIL + e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Title: unitCheckIndexPage 
	 * @Description: 单位审核列表页面
	 * @param assessCollectId
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value={"/unitCheckIndexPage/{assessCollectId}/{orgId}","/unitCheckIndexPage/{assessCollectId}"})
	public String unitCheckIndexPage(
			@PathVariable(value = "assessCollectId", required = true) String assessCollectId,
			@PathVariable(name = "orgId", required = false) String orgId,
			Model model){
		//是否是单位登录
		boolean isUnitAssess=false;
		OrganNode org=null;
		//orgid为空的话表示是单位人员登录,进行考核
		if(StringUtils.isNotEmpty(orgId)){
			org = organNodeService.get(orgId); 
			isUnitAssess=false;
		}else{
			//从考核状态页面跳转过来的
			org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			isUnitAssess=true;
		}
		AssessmentFlowCollect assessmentFlowCollect = this.assessmentFlowCollectService.get(assessCollectId);
		AssessmentFlowUnitPercent percnet = this.assessmentFlowUnitPercentService.getByCollectAndOrg(assessCollectId, org);
		//是否已经确认考核完成
		//是否还允许考核
		boolean isAllowAssess=false;
		//假如是季度考核
		if(assessmentFlowCollect.getAssessmentType()==AssessmentFlowCollect.ASSESSSEASON){
			isAllowAssess=this.assessmentDetailService.isConfirmAssess(org, assessCollectId);
		}else if(assessmentFlowCollect.getAssessmentType()==AssessmentFlowCollect.ASSESSYEAR){
			isAllowAssess=this.assessmentDetailService.isConfirmAssess(org, assessCollectId)&&percnet.getStatus()==CommonConst.YES;
		}
		model.addAttribute("assessCollectId",assessCollectId);
		//是否考核完成
		model.addAttribute("isAllowAssess",isAllowAssess);
		//是否是单位考核,不是单位考核的话就只能查看
		model.addAttribute("isUnitAssess",isUnitAssess);
		model.addAttribute("seasonAlertStr",assessmentFlowCollect.getAssessmentType()==2?"季度考核完成将不能修改,":"");
		model.addAttribute("orgId",orgId);
		model.addAttribute("assessmentFlowCollect",assessmentFlowCollect);
		return ASSESS_UNITCHECK_INDEX_PAGE;
	}
	
	/**
	 * 
	 * @Title: unitCheckPageList
	 * @Description: 单位审核列表页面数据
	 * @param assessCollectId
	 * @param model
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping("/unitCheckPageList")
	public  Page<AssessmentDetailVO>  unitCheckPageList(@RequestParam(value = "assessCollectId",required=true) String assessCollectId, String orgId,Model model,Integer limit, Integer page,String name,@RequestParam(value="id",required=false) String resultId){
		OrganNode org =null;
		if(StringUtils.isNotEmpty(orgId)){
			org = organNodeService.get(orgId); 
		}else{
			org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		}
		
		Page<AssessmentDetail> pages= assessmentDetailService.getCurrentUnitDetailsPage(org,assessCollectId,resultId,name,page,limit);
		Page<AssessmentDetailVO> voPage = AssessmentDetailVO.AssessmentDetail2VO(pages);
		return voPage;
	}
	
	/**
	 * 
	 * @Title: unitCheckPage 
	 * @Description: 人员审核页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/unitCheckPage")
	public String unitCheckPage(String id, Model model,Boolean view){
		AssessmentDetail detail = this.assessmentDetailService.get(id);

		model.addAttribute("detail", detail);
		model.addAttribute("servant", detail.getServant());
		if(detail.getResult()!=null){
			model.addAttribute("result", detail.getResult().getName().equals("优秀"));
		}
		
		model.addAttribute("view", view);
		model.addAttribute("assessmentType",detail.getAssessmentFlowCollect().getAssessmentType());
		return ASSESS_UNITCHECK_PAGE;
	}
	
	/**
	 * 
	 * @Title: saveUnitCheckDetail 
	 * @Description: 保存考核结果-单个考核
	 * @param detail
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveUnitCheckDetail")
	public AjaxResult saveUnitCheckDetail(AssessmentDetail detail){
		AjaxResult result = new AjaxResult(true);
		try {
			AssessmentDetail dbDetail=this.assessmentDetailService.get(detail.getId());
			Servant servant=dbDetail.getServant();
			//考核结论代码DM018  1优秀
			CodeInfo fineCodeInfo = dictableService.getCodeInfoByCode("1", "DM018");
			//是否记三等功结果
			CodeInfo creditCodeInfo =dictableService.getCodeInfoByCode("1", "DM215");
			//假如是年度考核的话
			if(dbDetail.getAssessmentFlowCollect().getAssessmentType()==1&&detail.getResult().getId().equals(fineCodeInfo.getId())){
				//1  验证是否超额
				boolean isExcess=false;
				int currentUnitOutStandingNumb=0;
				if(currentUnitOutStandingNumb<dbDetail.getAssessmentFlowUnitPercent().getOutstandingNumb()){
					isExcess=true;
				}else{
					throw new BusinessException("优秀人员数已经超额!");
				}
				//2 验证是否有处分
				boolean hasNoPunish=false;
				hasNoPunish=!rewardAndPunishService.hasPublish(servant);
				if(hasNoPunish){
					throw new BusinessException("当前人员曾经有过处分,考核结果不能指定为优秀!");
				}

				//3 假如是记三等功 检查距离上次记三等功是否过了三年并且这三年是否都是优秀
				Calendar cal = Calendar.getInstance();
				Date dateNow=new Date();
			    boolean isCredit=false;
			    //假如记三等功
			    if(detail.getIscredit().equals(creditCodeInfo.getId())){
				    //最近的一次记三等功的时间
				    Date latestThirdClassDate = rewardAndPunishService.getLatestThirdClassDate(servant);
				    if(null!=latestThirdClassDate){
					    cal.setTime(latestThirdClassDate);
					    cal.add(Calendar.YEAR, 3);
				    }
				    //上次记三等功三年后的时间
				    Date afterLatestThirdClassDate=cal.getTime();
				    if(null==latestThirdClassDate||dateNow.after(afterLatestThirdClassDate)){
				    	//查询最近三年是否都是优秀
				    	if(assessmentService.isRecent3YearsFine(servant,dbDetail.getAssessmentFlowCollect().getYear())){
				    		isCredit=true;
				    	}else{
				    		throw new BusinessException("当前人员并非最近三年年度考核都为优秀,考核结果不能记三等功!");
				    	}
				    		
				    }else{
				    	throw new BusinessException("当前人员上次记三等功时间未超过三年,考核结果不能记三等功!");
				    }
			    }

				    
				//4 本年季度考核是否全部优秀
				boolean isCurrentSeasonFine=false;
				isCurrentSeasonFine=assessmentService.isCurrentYearSeasonFine(servant,dbDetail.getAssessmentFlowCollect().getYear());
				if(!isCurrentSeasonFine){
					throw new BusinessException("当前人员季度考核没有全部优秀,考核结果不能指定为优秀!");
				}
					

			}
			dbDetail.setResult(detail.getResult());
			dbDetail.setRemarks(detail.getRemarks());
			dbDetail.setIscommend(detail.getIscommend());
			dbDetail.setIscredit(detail.getIscredit());
			this.assessmentDetailService.saveOrUpdate(dbDetail);
			result.setSuccess(true);
			result.setMessage(CommonConst.AJAXRESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(CommonConst.AJAXRESULT_FAIL + e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Title: batchAssess 
	 * @Description: 批量审核
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/batchAssess")
	public AjaxResult  batchAssess(@RequestParam(value = "ids[]")  String[]  ids,CodeInfo assessResult){
		
		//考核结论代码DM018  1优秀
		CodeInfo fineCodeInfo = dictableService.getCodeInfoByCode("1", "DM018");
		AjaxResult result = new AjaxResult(true);
		try {
			AssessmentDetail assessmentDetail1 = this.assessmentDetailService.get(ids[0]);
			if(assessmentDetail1.getAssessmentFlowCollect().getAssessmentType()==1&&assessResult.getId().equals(fineCodeInfo.getId())){
				throw new BusinessException("年度考核优秀人员请单独进行考核!");
			}
			for(int i=0;i<ids.length;i++){
				AssessmentDetail assessmentDetail = this.assessmentDetailService.get(ids[i]);
				assessmentDetail.setResult(assessResult);
				this.assessmentDetailService.update(assessmentDetail);
			}
			result.setSuccess(true);
			result.setMessage(CommonConst.AJAXRESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(CommonConst.AJAXRESULT_FAIL + e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Title: completeAssess 
	 * @Description: 完成考核,考核之后不再修改
	 * @return
	 * @return: AjaxResult
	 */
	@RequestMapping("/completeAssess/{assessCollectId}")
	@ResponseBody
	public AjaxResult completeAssess(@PathVariable(value="assessCollectId") String assessCollectId){
		OrganNode org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		AssessmentFlowCollect assessmentFlowCollect = this.assessmentFlowCollectService.get(assessCollectId);
		AjaxResult result = new AjaxResult(true);
		try {
			//是否已经考核完成
			boolean isAssessAll=this.assessmentDetailService.isAssessAll(org, assessCollectId);
			if (isAssessAll){
				this.assessmentDetailService.complete(org, assessmentFlowCollect);
				result.setSuccess(true);
				result.setMessage(CommonConst.AJAXRESULT_SUCCESS);
			}else{
				throw new BusinessException("并非所有的人员已经考核完成!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(CommonConst.AJAXRESULT_FAIL + e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Title: assessProgressView 
	 * @Description: 考核进度页面
	 * @param assessCollectId
	 * @return
	 * @return: String
	 */
	@RequestMapping("/assessProgressView/{assessCollectId}")
	public String assessProgressView(@PathVariable(value="assessCollectId") String assessCollectId,Model model){
		AssessmentFlowCollect collect = this.assessmentFlowCollectService.load(assessCollectId);
		Integer assessmentType = collect.getAssessmentType();
		model.addAttribute("assessCollectId", assessCollectId);
		if(assessmentType==1){
			return ASSESS_PROGRESS_YEAR_PAGE;
		}else{
			return ASSESS_PROGRESS_SEASON_PAGE;
		}
		
	}
	
	/**
	 * 
	 * @Title: assessProgressViewList 
	 * @Description: 考核进度数据
	 * @param assessCollectId
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<AssessProgressVO>
	 */
	@ResponseBody
	@RequestMapping("/assessProgressViewList")
	public Page<AssessmentFlowUnitPercentVO> assessProgressViewList(String assessCollectId,Integer page,Integer limit,String unitName){
		Page<AssessmentFlowUnitPercentVO> unitAssessProgress = this.assessmentFlowCollectService.getUnitAssessProgress(assessCollectId,unitName,page,limit);
		return unitAssessProgress;
	}
	
	
	/**
	 * 
	 * @Title: assessProgressStartView 
	 * @Description: 发起考核页面
	 * @param assessCollectId
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/assessProgressStartView/{assessCollectId}")
	public String assessProgressStartView(@PathVariable(value = "assessCollectId", required = true) String assessCollectId,Model model){
		OrganNode org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		AssessmentFlowCollect assessmentFlowCollect = this.assessmentFlowCollectService.get(assessCollectId);
		//获取单位的percent
		AssessmentFlowUnitPercent percent = this.assessmentFlowUnitPercentService.getByCollectAndOrg(assessCollectId, org);
		Map<String,Object> maps=new HashMap<>();
		maps.put("percent", percent);
		maps.put("collect", assessmentFlowCollect);
		model.addAllAttributes(maps);
		return ASSESS_UNIT_START;
	}
	
	/**
	 * 
	 * @Title: operateAssessProgress 
	 * @Description: 考核操作流程
	 * @param percent
	 * @param result
	 * @param opinion
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operateAssessProgress")
	public AjaxResult operateAssessProgress(AssessmentFlowUnitPercent percent,String result,String opinion){
		//考核结论代码DM018  1优秀
		CodeInfo fineCodeInfo = dictableService.getCodeInfoByCode("1", "DM018");
		AjaxResult ajaxResult = new AjaxResult(true);
		

		try {
/*			List<FlowRecordVO> flowRecordVOs = flowRecordService.findFlowRecordByBusinessId(percent.getId(),FlowBusTypeConstant.FLOW_ASSESS_REWARD,true);
			if(flowRecordVOs.size()!=0){
				throw new BusinessException("同单位已经由["+flowRecordVOs.get(0).getTargetSecurityUser()+"]发起过相同流程!");
			}*/
			AssessmentFlowUnitPercent percentDb=assessmentFlowUnitPercentService.get(percent.getId());
			if(percentDb.getFlowRecord()==null){
				percentDb.setUnitOutStandingNumb(percent.getUnitOutStandingNumb());
				percentDb.setRemark(percent.getRemark());
			}else{
				//假如是第七个步骤的话,需要验证上报嘉奖名单人数是否超过之前申请的人数
				if(percentDb.getFlowRecord().getOperationCode().equals(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP7)){
					//是否已经考核完成
					boolean isAssessAll=this.assessmentDetailService.isAssessAll(percentDb.getOrgNode(), percentDb.getAssessmentFlowCollect().getId());
					if (!isAssessAll){
						throw new BusinessException("并非所有的人员已经考核完成!");
					}
					Page<AssessmentDetail> fineDetailPage = this.assessmentDetailService.getCurrentUnitDetailsPage(percentDb.getOrgNode(),percentDb.getAssessmentFlowCollect().getId(),fineCodeInfo.getId(),null,1,10);
					//假如上报优秀人数大于申请的优秀人数
					if(fineDetailPage.getTotalSize()>percentDb.getUnitOutStandingNumb()){
						throw new BusinessException("嘉奖人数超过申请的优秀人数!");
					}
				}
			}
			assessmentFlowUnitPercentService.updateDataAndFlow(percentDb,opinion,result);
			ajaxResult.setSuccess(true);
			ajaxResult.setMessage(CommonConst.AJAXRESULT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage(CommonConst.AJAXRESULT_FAIL + e.getMessage());
		}
		return ajaxResult;
	}
	
	/**
	 * 
	 * @Title: assessNumbFlowView 
	 * @Description: 流程审批-数量审批页面
	 * @param flowId
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/assessNumbFlowView/{flowId}")
	public String assessNumbFlowView(@PathVariable(value = "flowId", required = true) String flowId,Model model){
		FlowRecord flowRecord = this.flowRecordService.get(flowId);
		AssessmentFlowUnitPercent percent = this.assessmentFlowUnitPercentService.get(flowRecord.getBusId());
		AssessmentFlowCollect assessmentFlowCollect = percent.getAssessmentFlowCollect();
		assessmentFlowCollect.getId();
		Map<String,Object> maps=new HashMap<>();
		maps.put("percent", percent);
		maps.put("collect", assessmentFlowCollect);
		model.addAllAttributes(maps);
		return ASSESS_FLOW_NUMBERCHECK_PAGE;
	}
	
	/**
	 * 
	 * @Title: assessPickPeopleFlow 
	 * @Description: 流程审批-人员选择与审核页面
	 * @param flowId
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/assessPickPeopleFlow/{flowId}")
	public String assessPickPeopleFlow(@PathVariable(value = "flowId", required = true) String flowId,Model model){
		
		FlowRecord flow = flowRecordService.load(flowId);
		String step=flow.getOperationCode();
		//考核结论代码DM018  1优秀
		CodeInfo fineCodeInfo = dictableService.getCodeInfoByCode("1", "DM018");
		FlowRecord flowRecord = this.flowRecordService.get(flowId);
		AssessmentFlowUnitPercent percent = this.assessmentFlowUnitPercentService.get(flowRecord.getBusId());
		AssessmentFlowCollect assessmentFlowCollect = percent.getAssessmentFlowCollect();
		Map<String,Object> maps=new HashMap<>();
		maps.put("percent", percent);
		maps.put("collect", assessmentFlowCollect);
		maps.put("collectId", assessmentFlowCollect.getId());
		maps.put("fineCodeInfoId", fineCodeInfo.getId());
		maps.put("step", step);
		model.addAllAttributes(maps);
		return ASSESS_FLOW_HUMANPICK_PAGE;
	}
	
	@RequestMapping("/assessFlowRepick/{assessCollectId}/{orgId}")
	public String assessFlowRepick(
			@PathVariable(value = "assessCollectId", required = true) String assessCollectId,
			@PathVariable(name = "orgId", required = true) String orgId,
			Model model){
		AssessmentFlowCollect assessmentFlowCollect = this.assessmentFlowCollectService.get(assessCollectId);
		model.addAttribute("assessCollectId",assessCollectId);
		model.addAttribute("orgId",orgId);
		model.addAttribute("assessmentFlowCollect",assessmentFlowCollect);
		
		return ASSESS_FLOW_REPICK_PAGE;
	}
	
	@RequestMapping("/exportSummaryExcel")
	public void exportSummaryExcel(Model model,HttpServletRequest request,HttpServletResponse response,String collectId){
		//考核结论代码DM018  1优秀
		CodeInfo fineCodeInfo = dictableService.getCodeInfoByCode("1", "DM018");
		//是否记三等功结果
		CodeInfo creditCodeInfo =dictableService.getCodeInfoByCode("1", "DM215");
		AssessmentFlowCollect collect = assessmentFlowCollectService.load(collectId);
		List<AssessmentDetail> allFineDetails = this.assessmentDetailService.getAllFineDetails(collectId);
		Map<String,Object> params = new HashMap<>();//excel导出使用的参数
		List<String[]> dataList = new ArrayList<>();//excel中数据列表
		for(int i=0;i<allFineDetails.size();i++){
			AssessmentDetail fineDetail=allFineDetails.get(i);
			String[] arr = new String[4];
			arr[0] = fineDetail.getServant().getDepartName();
			arr[1] = fineDetail.getServant().getName();
			arr[2] = fineDetail.getServant().getCardNo();
			if(fineCodeInfo.getId().equals(fineDetail.getResult().getId())){
				arr[3] = "嘉奖";
			}
			if(creditCodeInfo.getId().equals(fineDetail.getIscredit())){
				arr[3] = "记三等功";
			}
			dataList.add(arr);
		}
		params.put("listDataList", dataList);
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String templet = savePath+"\\WEB-INF\\templates\\ndkh.xls";//模板路径
		String path = savePath+"\\WEB-INF\\templates\\"+111+".xls";//生成excel文件路径，临时存放，下载成功之后会删除
		ExcelUtilsPOI.replaceModel(params, templet,path, 1,null);//替换模板数据 生成excel到tomcat服务器
	  	ExcelUtilsPOI.exceldown(path, "长宁区"+collect.getYear()+"年度考核人员统计表.xls", request, response);
		
	}
	
	
	/**
	 * 
	 * @Title: finishCollect 
	 * @Description: 完成考核,更新考核单状态
	 * @return
	 * @return: AjaxResult
	 */
	@RequestMapping("/finishCollect")
	@ResponseBody
	public AjaxResult finishCollect(@RequestParam(value="collectId",required=true) String collectId){
		
		AjaxResult result = new AjaxResult(true);
		try {
			AssessmentFlowCollect collect = this.assessmentFlowCollectService.load(collectId);
			if(collect.getAssessmentType()==1){
				//假如所有单位都已经完成考核
				List<AssessmentFlowUnitPercent> percents=this.assessmentFlowUnitPercentService.getPercentByCollect(collectId);
				//获取所有该考核单下已经完成的考核单位
				List<AssessmentFlowUnitPercent> finishPercents=this.assessmentFlowUnitPercentService.getFinishPercentByCollect(collectId);
				if(percents.size()==finishPercents.size()){
					
					collect.setStatus(CommonConst.YES);
					assessmentFlowCollectService.update(collect);
					result.setSuccess(true);
					result.setMessage(CommonConst.AJAXRESULT_SUCCESS);
				}else{
					throw new BusinessException("并非所有单位已经完成考核!");
				}
			}else{
				List<AssessmentDetail> statusDetails=this.assessmentDetailService.getAllAssessStatusDetails(null, collectId);
				List<AssessmentDetail> details=this.assessmentDetailService.getAllDetails(null, collectId);
				if(statusDetails.size()==details.size()){
					collect.setStatus(CommonConst.YES);
					assessmentFlowCollectService.update(collect);
					result.setSuccess(true);
					result.setMessage(CommonConst.AJAXRESULT_SUCCESS);
				}else{
					throw new BusinessException("并非所有单位已经完成考核!");
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(CommonConst.AJAXRESULT_FAIL + e.getMessage());
		}
		return result;
	}
	
	
	
}
