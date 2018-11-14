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
package com.wondersgroup.human.controller.ofcflow;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan;
import com.wondersgroup.human.bo.ofcflow.RecruitPost;
import com.wondersgroup.human.bo.ofcflow.RecruitYearPlan;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.RecruitEmployPlanService;
import com.wondersgroup.human.service.ofcflow.RecruitPostService;
import com.wondersgroup.human.service.ofcflow.RecruitYearPlanService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.vo.ofcflow.RecruitEmployPlanVO;
import com.wondersgroup.human.vo.ofcflow.RecruitPostVO;
import com.wondersgroup.human.vo.ofcflow.RecruitYearPlanVO;

/**
 * 
 * @ClassName: RecruitController
 * @Description: 职位简章 控制器
 * @author: wangzhanfei
 * @date: 2018年5月15日 上午10:27:16 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("ofcflow/recruit")
public class RecruitController extends GenericController {

	// @Autowired
	// private servantService servantService;
	// 初始化列表頁面
	private final static String RECRUIT_LIST_PAGE = "models/ofcflow/employPlan/index";
	
	// 招录计划选择页面
	private final static String RECRUIT_EMPLOY_PRE_PLAN_PAGE = "models/ofcflow/employPlan/pre";
	// 新增组织招录计划
	private final static String RECRUIT_EMPLOY_PLAN_PAGE = "models/ofcflow/employPlan/plan";
	// 修改招录职位
	private final static String RECRUIT_POST_PAGE = "models/ofcflow/employPlan/post";
	// 管理招录职位
	private final static String RECRUIT_ADD_POST_PAGE = "models/ofcflow/employPlan/addPost";
	/**
	 * 查看招录职位
	 */
	private final static String RECRUIT_VIEW_POST_PAGE = "models/ofcflow/employPlan/postView";
	// 年度招录计划
	private final static String RECRUIT_YEAR_PLAN_PAGE = "models/ofcflow/employPlan/yearList";
	// 年度招录计划信息页面
	private final static String RECRUIT_YEAR_PLAN_EDIT_PAGE = "models/ofcflow/employPlan/year";
	
	/**
	 * 招录计划流程待办列表
	 */
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	/**
	 * 招录计划流程审批页面
	 */
	private final static String RECRUIT_EMPLOYPLAN_PLANFLOW = "models/ofcflow/employPlan/planFlow";
	/**
	 * 招录计划详情查看页面
	 */
	private final static String RECRUIT_EMPLOYPLAN_PLANVIEW = "models/ofcflow/employPlan/planView";
	/**
	 * 业务流程信息列表
	 */
	private final static String RECRUIT_EMPLOYPLAN_PLANFLOW_INFO = "models/ofcflow/employPlan/planFlowInfo";
	
	@Autowired
	RecruitEmployPlanService recruitemployplanservice;
	@Autowired
	RecruitYearPlanService recruityearplanservice;
	@Autowired
	RecruitPostService recruitpostservice;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	DictableService dictableservice;
	@Autowired
	ServantService servantService;
	@Autowired
	OrgInfoService unitbasciinfoservice;
	@Autowired
	private FlowRecordService flowRecordService;
	@Autowired
	private OrgFormationService orgFormationService;
	@Autowired
	private OrgInfoService orgInfoService;
	//@Autowired
	//private FormationControlService formationControlService;
	/**
	 * @Title: recruitList 
	 * @Description: 职位简章主页
	 * @param model
	 * @param pageNo
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String recruitList(Model model, Integer pageNo) {
		return RECRUIT_LIST_PAGE;
	}

	/**
	 * @Title: preParams 
	 * @Description: 招录计划选择页面
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/pre")
	public String preParams(Model model) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		OrganNode root = organizationService.getCurrentUnitOrganForOrganNodeCode(CommonConst.ROOT_ORGAN_CODE);//长宁区
		model.addAttribute("OrganNode", x);
		model.addAttribute("root", root);
		return RECRUIT_EMPLOY_PRE_PLAN_PAGE;
	}
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","RecruitEmployPlan,RecruitPost");
		return RECRUIT_EMPLOYPLAN_FLOW;
	}
	/**
	 * @Title: planView 
	 * @Description: 录用计划详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/planView")
	public String planView(Model model,String id) {
		RecruitEmployPlan r = recruitemployplanservice.load(id);
		model.addAttribute("recruitemployplan",r);
		model.addAttribute("recruityearplan",r.getYearPlan());
		if(r.getPlanState()>RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_POST){
			return RECRUIT_POST_PAGE;//招录计划和职位信息
		}else{
			return RECRUIT_EMPLOYPLAN_PLANVIEW;//招录计划信息
		}
	}
	/**
	 * @Title: planFlowInfo 
	 * @Description: 业务流程信息列表
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/planFlowInfo")
	public String planFlowInfo(Model model,String id) {
		model.addAttribute("busId", id);
		return RECRUIT_EMPLOYPLAN_PLANFLOW_INFO;
	}
	/**
	 * @Title: planFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/planFlow")
	public String planFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		String busType = flow.getBusType();
		RecruitEmployPlan r = recruitemployplanservice.get(flow.getBusId());
		model.addAttribute("recruitemployplan", r);
		model.addAttribute("recruityearplan", r.getYearPlan());
		if("RecruitEmployPlan".equals(busType)){//招录计划 流程
			if(RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_STATE_POST==r.getPlanState()){//如果是待提交
				return RECRUIT_EMPLOY_PLAN_PAGE;
			}else{
				return RECRUIT_EMPLOYPLAN_PLANFLOW;
			}
		}else{//招录计划-职位上报 流程
			model.addAttribute("isFlow", true);
			return RECRUIT_POST_PAGE;
		}
	}

	/**
	 * @Title: yearList 
	 * @Description: 年度招录计划列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/year/list")
	public String yearList() {

		return RECRUIT_YEAR_PLAN_PAGE;
	}

	/**
	 * @Title: yearEdit 
	 * @Description: 年度招录计划
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/year/edit")
	public String yearEdit(String id, Model model) {

		if (StringUtils.isNotBlank(id)) {
			RecruitYearPlan recruityearplan = recruityearplanservice.get(id);
			
			if(recruityearplan.getState()==0){
				recruityearplan.setState(null);
			}
			model.addAttribute("recruityearplan", recruityearplan);
		}

		return RECRUIT_YEAR_PLAN_EDIT_PAGE;
	}

	/**
	 * @Title: year
	 * @Description: 年度招录计划列表
	 * @param recruityearplan
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<RecruitYearPlan>
	 */
	@ResponseBody
	@RequestMapping("/year/yearplanlist")
	public Page<RecruitYearPlanVO> year(RecruitYearPlan recruityearplan, Integer limit, Integer page) {

		List<Predicate> filter = new ArrayList<>();// 查询条件
		Sorts sort = new Sorts();// 排序规则
		sort.add("startDate", false);// 降序

		if (recruityearplan.getStartDate() != null) {// 开始时间
			Predicate p = new Predicate("startDate", Operator.BETWEEN, recruityearplan.getStartDate(),
					recruityearplan.getStartDate(), "");
			filter.add(p);
		}
		if (recruityearplan.getEndDate() != null) {// 结束时间
			Predicate p = new Predicate("endDate", Operator.BETWEEN, recruityearplan.getStartDate(),
					recruityearplan.getEndDate(), "");
			filter.add(p);
		}

		Page<RecruitYearPlanVO> pageInfo = recruityearplanservice.findByFilterVO(filter, sort, page, limit);
		return pageInfo;

	}

	/**
	 * @Title: saveYearPlan
	 * @Description: 年度招录计划保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveYearPlan")
	public AjaxResult saveYearPlan(RecruitYearPlan temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				recruityearplanservice.save(temp);// 保存
			} else {
				RecruitYearPlan recruityearplan = recruityearplanservice.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, recruityearplan);
				recruityearplanservice.update(recruityearplan);// 保存
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
	 * 
	 * @Title: deleteYearPlan
	 * @Description: 年度招录计划删除
	 * @param temp
	 * @return AjaxResult
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/year/delete")
	public AjaxResult deleteYearPlan(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			
			RecruitYearPlan recruityearplan = recruityearplanservice.get(id);

			//判断该年度计划下是否存在招录计划，如果存在，不能删除
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RecruitEmployPlan.class);
			detachedCriteria.add(Restrictions.in("yearPlan", recruityearplan));
			List<RecruitEmployPlan> planList = recruitemployplanservice.findByCriteria(detachedCriteria);
			
			if(planList!=null&&planList.size()>0){
				throw new BusinessException("该年度计划已新增招录计划，不能删除！");
			}
			
			recruityearplanservice.delete(recruityearplan);// 删除

			result.setMessage("删除成功！");
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * 
	 * @Title: findYearPlan 
	 * @Description: 招录计划查询
	 * @param id 单位id 
	 * @return
	 * @return: List<RecruitYearPlan>
	 */
	@ResponseBody
	@RequestMapping("/year/find")
	public List<RecruitYearPlan> findYearPlan(String id) {
		
		List<RecruitYearPlan> list = null;
		try {
			Sorts sort = new Sorts();// 排序规则
			sort.add("startDate", false);// 降序
			List<Predicate> filter = new ArrayList<>();// 查询条件
			Predicate p = new Predicate("state", Operator.EQ, 1, "");
			filter.add(p);
			list = recruityearplanservice.findByFilter(filter, sort);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 
	 * @Title: valiateExistEmployPlan 
	 * @Description:  判断选择的招录计划是否已经有了招录数据
	 * @param model
	 * @param employOrganCode  单位CODE
	 * @param yearPlanId   年度招录计划id
	 * @return
	 * @return: Boolean
	 */
	@RequestMapping("/employPlan/plan/validate")
	@ResponseBody
	public AjaxResult valiateExistEmployPlan(Model model, String employOrganCode, String yearPlanId) {
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(yearPlanId)){
				throw new BusinessException("年度招录计划不能为空！");
			}
			RecruitYearPlan yearPlan = recruityearplanservice.load(yearPlanId);
			Date start = yearPlan.getStartDate();
			Date end = yearPlan.getEndDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long now = sdf.parse(sdf.format(new Date())).getTime();
			if(now<start.getTime()){
				throw new BusinessException("该年度招录计划还未开始！");
			}
			if(now>end.getTime()){
				throw new BusinessException("该年度招录计划已经结束！");
			}
			OrganNode employOrgan =  organizationService.getCurrentUnitOrganForOrganNodeCode(employOrganCode);
			BigDecimal plans = recruitemployplanservice.getRecruitEmployPlanByEmployOrgan(yearPlan, employOrgan);
			int r= plans.compareTo(BigDecimal.ZERO);
			if (r==1) {
				result.setSuccess(false);
				result.setMessage(employOrgan.getName()+"在" + yearPlan.getName() + "当中已存在招录计划，不能再次新增招录计划！");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("新增招录计划失败！");
		}
		return result;
	}
	
	
	
	/**
	 * 
	 * @Title: plan 
	 * @Description: 新增组织招录计划
	 * @param model
	 * @param employPlanId
	 * @param level
	 * @param yearPlanId
	 * @param recruitOrganId
	 * @param employOrganId
	 * @param createOrganId
	 * @return
	 * @return: String
	 */
	@RequestMapping("/employPlan/plan")
	public String plan(Model model, String employPlanId,Integer level, String yearPlanId) {
		RecruitEmployPlan plan =null;
		if(StringUtils.isNotBlank(employPlanId)){
			plan = recruitemployplanservice.get(employPlanId);
			model.addAttribute("recruityearplan", plan.getYearPlan());
		}else{
			plan = new RecruitEmployPlan();
			RecruitYearPlan yearPlan = recruityearplanservice.get(yearPlanId);
			plan.setYearPlan(yearPlan);
			OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			model.addAttribute("OrganNode", x);
			//查询当前单位编制情况
			OrgInfo org = orgInfoService.findUniqueBy("organ.id", x.getId());
			if(org!=null){
				OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", org.getId());
				if(orgFormation!=null){
					plan.setAllowWeaveNum(orgFormation.getUnitPlanningTotal());//核定编制数
					plan.setRealNum(orgFormation.getActualNumber());//实有人数
					plan.setThisYearLackWeaveNum(orgFormation.getVacancyExcessNumber());//机构缺编数
					plan.setChiefLackWeaveNum(orgFormation.getVacancyDivisionChiefLevelNumber());//处级实职缺编人数
				}
			}
			
			OrganNode root = organizationService.getCurrentUnitOrganForOrganNodeCode(CommonConst.ROOT_ORGAN_CODE);//长宁区
			plan.setEmployOrgan(x);
			plan.setRecruitOrgan(root);
			try{ 
				Map<String, String> condMap = new HashMap<String, String>();
				condMap.put("departId", String.valueOf(plan.getEmployOrgan().getId()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//机关类别
		if(StringUtils.isNotBlank(yearPlanId)){
			RecruitYearPlan recruityearplan = recruityearplanservice.get(yearPlanId);
			model.addAttribute("recruityearplan", recruityearplan);
		}
		
		model.addAttribute("recruitemployplan", plan);
		
		return RECRUIT_EMPLOY_PLAN_PAGE;
	}
	
	/**
	 * 
	 * @Title: saveEmployPlan 
	 * @Description: 职位简章计划新增
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/employPlan/save")
	public AjaxResult saveEmployPlan(RecruitEmployPlan recruitemployplan, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		try {
			//编控，校验编制数是否足够，判断数据能否保存
//			boolean isTrue = formationControlService.queryJudgeFormationNum(recruitemployplan.getEmployOrgan().getId(), recruitemployplan.getPlanEmployNum());
//			if(isTrue){
				if (StringUtils.isBlank(recruitemployplan.getId())) {
					DictUtils.operationCodeInfo(recruitemployplan);//将CodeInfo中id为空的属性 设置为null
					recruitemployplanservice.save(recruitemployplan);// 保存
				} else {
					RecruitEmployPlan employplan = recruitemployplanservice.load(recruitemployplan.getId());
					BeanUtils.copyPropertiesIgnoreNull(recruitemployplan,employplan);
					DictUtils.operationCodeInfo(employplan);//将CodeInfo中id为空的属性 设置为null
					recruitemployplanservice.update(employplan);// 保存
				}
				result.setMessage("保存成功！");
//			}else{
//				result.setSuccess(false);
//				result.setMessage("编制校验不通过！");
//			}
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: operationPlan 
	 * @Description: 审批招录计划
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationPlan")
	public AjaxResult operationPlan(RecruitEmployPlan temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				recruitemployplanservice.savePlan(temp,opinion,r);
			} else {
				RecruitEmployPlan employplan = recruitemployplanservice.load(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp,employplan);
				DictUtils.operationCodeInfo(employplan);//将CodeInfo中id为空的属性 设置为null
				recruitemployplanservice.savePlan(employplan,opinion,r);
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
	 * @Title: operationPost 
	 * @Description: 审批招录计划-职位信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationPost")
	public AjaxResult operationPost(RecruitEmployPlan temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			RecruitEmployPlan employplan = recruitemployplanservice.get(temp.getId());
			recruitemployplanservice.savePost(employplan,opinion,r);
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
	 * 
	 * @Title: EmployPlanList 
	 * @Description: 组织招录计划列表
	 * @param recruitemployplan
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<RecruitEmployPlan>
	 */
	@ResponseBody
	@RequestMapping("/employPlan/list")
	public Page<RecruitEmployPlanVO>  EmployPlanList(RecruitEmployPlan recruitemployplan, Integer limit, Integer page) {
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Sorts sort = new Sorts();// 排序规则
		sort.add("createTime", false);// 降序
		
		filter.add(new Predicate("creater", Operator.EQ, SecurityUtils.getUserId(), ""));
		
		if (recruitemployplan.getYearPlan() != null&&StringUtils.isNotBlank(recruitemployplan.getYearPlan().getId())) {// 年度计划
			Predicate p = new Predicate("yearPlan", Operator.EQ, recruitemployplan.getYearPlan(), "");
			filter.add(p);
		}
		if (recruitemployplan.getRecuritType() != null&&StringUtils.isNotBlank(recruitemployplan.getRecuritType().getId())) {// 编制类型
			Predicate p = new Predicate("recuritType", Operator.EQ, recruitemployplan.getRecuritType(), "");
			filter.add(p);
		}

		Page<RecruitEmployPlanVO> pageInfo = recruitemployplanservice.findByFilterVO(filter, sort, page, limit);
		return pageInfo;
	}
	
	/**
	 * 
	 * @Title: deleteEmployPlan 
	 * @Description: 组织招录计划删除
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/employPlan/delete")
	public AjaxResult deleteEmployPlan(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			RecruitEmployPlan recruitemployplan = recruitemployplanservice.get(id);
			recruitemployplanservice.delete(recruitemployplan);// 删除

			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	// 修改招录职位
	@RequestMapping("/employPlan/post")
	public String post(Model model, String employPlanId) {
		if(StringUtils.isNotBlank(employPlanId)){
			RecruitEmployPlan recruitemployplan = recruitemployplanservice.get(employPlanId);
			
			model.addAttribute("recruityearplan", recruitemployplan.getYearPlan());
			model.addAttribute("recruitemployplan", recruitemployplan);
		}
		return RECRUIT_POST_PAGE;
	}
	
	/**
	 * 
	 * @Title: EmployPlanPostList 
	 * @Description: 招录职位列表
	 * @param recruitemployplan
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<RecruitPostVO>
	 */
	@ResponseBody
	@RequestMapping("/employPlan/postlist")
	public Page<RecruitPostVO>  EmployPlanPostList(RecruitPost recruitpost,String planId, Integer limit, Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from RecruitPost where removed=:removed and plan.id=:planId");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("planId", planId));
		hql.append( " order by createTime desc");
		
		Page<RecruitPostVO> pageInfo = recruitpostservice.findByFilterVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	
	/**
	 * 
	 * @Title: addPost 
	 * @Description: 管理招录职位
	 * @return
	 * @return: String
	 */
	@RequestMapping("/addPost")
	public String addPost(Model model, HttpServletRequest request) {
		String planId = request.getParameter("id");
		String postId = request.getParameter("postId");
		if(StringUtils.isNotBlank(planId)){
			RecruitEmployPlan ep = recruitemployplanservice.get(planId);
			model.addAttribute("ep", ep);
			
		}
		if(StringUtils.isNotBlank(postId)){
			RecruitPost post =recruitpostservice.get(postId);
			model.addAttribute("post",post);
			
		}
		return RECRUIT_ADD_POST_PAGE;
	}
	/**
	 * 
	 * @Title: viewPost 
	 * @Description: 查看招录职位信息
	 * @return
	 * @return: String
	 */
	@RequestMapping("/viewPost")
	public String viewPost(Model model, HttpServletRequest request) {
		String planId = request.getParameter("id");
		String postId = request.getParameter("postId");
		if(StringUtils.isNotBlank(planId)){
			RecruitEmployPlan ep = recruitemployplanservice.get(planId);
			model.addAttribute("ep", ep);
			
		}
		if(StringUtils.isNotBlank(postId)){
			RecruitPost post =recruitpostservice.get(postId);
			model.addAttribute("post",post);
			
		}
		return RECRUIT_VIEW_POST_PAGE;
	}
	/**
	 * 
	 * @Title: savePost 
	 * @Description: 招录职位保存
	 * @param model
	 * @param post
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping("/employPlan/savePost")
	public AjaxResult savePost(Model model, RecruitPost temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		
		try {
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from RecruitPost where removed=:removed and plan =:plan ");
			listqueryparameter.add(new QueryParameter("removed", false));
			listqueryparameter.add(new QueryParameter("plan", temp.getPlan()));
			if(StringUtils.isNotBlank(temp.getId())){//排除修改的该条数据
				hql.append(" and id!=:id ");
				listqueryparameter.add(new QueryParameter("id", temp.getId()));
			}
			List<RecruitPost> postList = recruitpostservice.findByHQL(hql.toString(), listqueryparameter);
			int num = 0;
			for(RecruitPost post : postList){
				num+=post.getPlanEmployNum();//该招录计划下所有职位的计划人数相加
			}
			int sum = num+temp.getPlanEmployNum();//再加上本次保存数据的计划招录人数，判断是否超过  终审招录人数
			if(sum>temp.getPlan().getEndEmployNum()){
				throw new BusinessException("招录人数超出计划人数！");
			}
			DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
			if(StringUtils.isNotBlank(temp.getId())){//更新
				RecruitPost e = recruitpostservice.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, e);
				recruitpostservice.saveOrUpdate(e);//保存
			}else{//新增
				recruitpostservice.save(temp);
			}
			result.setMessage("保存成功！");
		} catch (BusinessException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: employPlanConfirm 
	 * @Description: 招录计划确认
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/employPlan/confirm")
	public AjaxResult employPlanConfirm(Model model, HttpServletRequest request,String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			RecruitEmployPlan temp = recruitemployplanservice.get(id);
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from RecruitPost where removed=:removed and plan.id =:plan ");
			listqueryparameter.add(new QueryParameter("removed", false));
			listqueryparameter.add(new QueryParameter("plan", temp.getId()));
			List<RecruitPost> postList = recruitpostservice.findByHQL(hql.toString(), listqueryparameter);
			int sum = 0;
			for(RecruitPost post : postList){
				sum+=post.getPlanEmployNum();//该招录计划下所有职位的计划人数相加
			}
			if(sum>temp.getEndEmployNum()){
				throw new BusinessException("招录人数超出计划人数！");
			}
			if(sum<temp.getEndEmployNum()){
				throw new BusinessException("请检查，计划招录人数还有"+(temp.getEndEmployNum()-sum)+"位没有添加到职位！");
			}
			recruitemployplanservice.savePost(temp,null,FlowRecord.PASS);
			
 			result.setMessage("提交成功！");
		} catch (BusinessException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("提交失败！");
		}
		return result;
	}
	/**
	 * 
	 * @Title: deleteEmployPlanPost 
	 * @Description: 招录职位删除
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/employPlan/deletepost")
	public AjaxResult deleteEmployPlanPost(String planId,String postId) {
		AjaxResult result = new AjaxResult(true);
		try {
			
			RecruitPost post = recruitpostservice.get(postId);
			recruitpostservice.delete(post);
			
 			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * 
	 * @Title: checkExport 
	 * @Description: 招录计划导出校验
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/checkExport")
	public AjaxResult checkExport(String id,String type) {
		AjaxResult result = new AjaxResult(true);
		try {
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from RecruitEmployPlan where removed=:removed and planState>=:planState and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			if("1".equals(type)){
				listqueryparameter.add(new QueryParameter("planState", RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_POST));
			}else{
				listqueryparameter.add(new QueryParameter("planState", RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_POST_PASS));
			}
			listqueryparameter.add(new QueryParameter("yearPlan", id));
			hql.append( " order by createTime desc");
			List<RecruitEmployPlan> planList = recruitemployplanservice.findByHQL(hql.toString(), listqueryparameter);
			
			if(planList==null||planList.size()==0){
				if("1".equals(type)){
					throw new BusinessException("该年度计划下暂无完成审批的招录计划信息！");
				}else{
					throw new BusinessException("该年度计划下暂无完成审批的招录职位信息！");
				}
			}
			result.setMessage("导出成功！");
		} catch (BusinessException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("导出失败！");
		}
		return result;
	}
	/**
	 * @Title: exportPlan 
	 * @Description: 生成年度计划下 已经确认的招录计划信息
	 * @param id
	 * @return: void
	 */
	@RequestMapping("/exportPlan")
	public void exportPlan(String id,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from RecruitEmployPlan where removed=:removed and planState>=:planState and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("planState", RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_POST));
			listqueryparameter.add(new QueryParameter("yearPlan", id));
			hql.append( " order by createTime desc");
			List<RecruitEmployPlan> planList = recruitemployplanservice.findByHQL(hql.toString(), listqueryparameter);
			
			Map<String,Object> params = new HashMap<>();//excel导出使用的参数
			String planName = planList.get(0).getYearPlan().getName();//年度计划名称
			params.put("planName", planName);
			List<String[]> dataList = new ArrayList<>();//excel中数据列表
			for(int i=0;i<planList.size();i++){
				String[] arr = new String[11];
				RecruitEmployPlan plan = planList.get(i);
				RecruitEmployPlanVO vo = new RecruitEmployPlanVO(plan);
				arr[0] = i+"";
				arr[1] = vo.getEmployOrgan();
				arr[2] = vo.getAllowWeaveNum();
				arr[3] = vo.getRealNum();
				arr[4] = vo.getThisYearLackWeaveNum();
				arr[5] = vo.getChiefLackWeaveNum();
				arr[6] = vo.getPlanCutNum();
				arr[7] = vo.getPlanEmployNum();
				arr[8] = vo.getFirstEmployNum();
				arr[9] = vo.getEndEmployNum();
				arr[10] = vo.getRemark();
				dataList.add(arr);
			}
			params.put("listDataList", dataList);
			String savePath = request.getSession().getServletContext().getRealPath("/");
			String templet = savePath+"\\WEB-INF\\templates\\recruitEmployPlan.xls";//模板路径
			String path = savePath+"\\WEB-INF\\templates\\"+planName+".xls";//生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet,path, 1,null);//替换模板数据 生成excel到tomcat服务器
		  	ExcelUtilsPOI.exceldown(path, "长宁区"+planName+"招录计划申报表.xls", request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Title: exportPost 
	 * @Description: 生成年度计划下 已经审批完成的招录计划-职位 信息
	 * @param id
	 * @return: void
	 */
	@RequestMapping("/exportPost")
	public void exportPost(String id,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from RecruitEmployPlan where removed=:removed and planState>=:planState and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("planState", RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_POST_PASS));
			listqueryparameter.add(new QueryParameter("yearPlan", id));
			hql.append( " order by createTime desc");
			List<RecruitEmployPlan> planList = recruitemployplanservice.findByHQL(hql.toString(), listqueryparameter);
			
			Map<String,Object> params = new HashMap<>();//excel导出使用的参数
			String planName = planList.get(0).getYearPlan().getName();//年度计划名称
			params.put("planName", planName);
			List<String[]> dataList = new ArrayList<>();//excel中数据列表
			for(int i=0;i<planList.size();i++){
				RecruitEmployPlan plan = planList.get(i);
				RecruitEmployPlanVO vo = new RecruitEmployPlanVO(plan);
				Set<RecruitPost>  postSet = plan.getPost();
				int j=0;
				for(RecruitPost post : postSet){
					if(post.getRemoved()){
						continue;
					}
					RecruitPostVO postVO = new RecruitPostVO(post);
					String[] arr = new String[17];
					if(j==0){
						arr[0] = vo.getEmployOrgan();
						arr[1] = vo.getAllowWeaveNum();
						arr[2] = vo.getRealNum();
						arr[3] = vo.getThisYearLackWeaveNum();
						arr[4] = vo.getChiefLackWeaveNum();
						arr[5] = vo.getPlanCutNum();
					}else{
						arr[0] = null;
						arr[1] = null;
						arr[2] = null;
						arr[3] = null;
						arr[4] = null;
						arr[5] = null;
					}
					arr[6] = postVO.getName();
					arr[7] = postVO.getDescription();
					arr[8] = postVO.getPlanEmployNum();
					arr[9] = postVO.getWorkYear();
					arr[10] = postVO.getUndergraduateCourse();
					arr[11] = postVO.getGraduateCourse();
					arr[12] = postVO.getEducation();
					arr[13] = postVO.getDegree();
					arr[14] = postVO.getPoliticalStatus();
					arr[15] = postVO.getMajorSubject();
					arr[16] = post.getOther();
					dataList.add(arr);
					j++;
				}
			}
			params.put("listDataList", dataList);
			String savePath = request.getSession().getServletContext().getRealPath("/");
			String templet = savePath+"\\WEB-INF\\templates\\recruitEmployPost.xls";//模板路径
			String path = savePath+"\\WEB-INF\\templates\\"+planName+"_Post.xls";//生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet,path, 1,"1");//替换模板数据 生成excel到tomcat服务器
		  	ExcelUtilsPOI.exceldown(path, "长宁区"+planName+"招录计划职位申报表.xls", request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
