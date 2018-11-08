/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPlanController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月12日 下午5:22:32 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月12日 下午5:22:32 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.ofcflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.TrainPerson;
import com.wondersgroup.human.bo.ofcflow.TrainPlan;
import com.wondersgroup.human.bo.ofcflow.TrainYearPlan;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.TrainPersonService;
import com.wondersgroup.human.service.ofcflow.TrainPlanService;
import com.wondersgroup.human.service.ofcflow.TrainYearPlanService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.vo.ofcflow.TrainPersonVO;
import com.wondersgroup.human.vo.ofcflow.TrainPlanVO;
import com.wondersgroup.human.vo.ofcflow.TrainYearPlanVO;

/** 
 * @ClassName: TrainPlanController 
 * @Description: 培训学时控制类
 * @author: lihao
 * @date: 2018年9月12日 下午5:22:32
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/train")
public class TrainController  extends GenericController{
	
	@Autowired
	TrainPlanService trainPlanService;
	
	@Autowired
	TrainPersonService trainPersonService;
	
	@Autowired
	TrainYearPlanService trainYearPlanService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@Autowired
	ServantService servantService;
	
	// 页面路径--培训信息列表
	private final static String TRAIN_INDEX = "models/ofcflow/trainPlan/index";
	
	// 页面路径--培训信息添加
	private final static String ADD_TRAIN_PLAN = "models/ofcflow/trainPlan/addTrainPlan";
	
	// 页面路径--培训个人信息添加
	private final static String ADD_TRAIN_PERSON = "models/ofcflow/trainPlan/addTrainPerson";
		
	
	// 页面路径--培训信息上报(非人社局上报)
	private final static String UPLOAD_TRAIN_PLAN = "models/ofcflow/trainPlan/uploadTrainPlan";
	
	// 页面路径--培训信息上报(人社局上报)
	private final static String UPLOAD_TRAIN_PLAN2 = "models/ofcflow/trainPlan/uploadTrainPlan2";
	
	// 页面路径--培训信息流程列表
	private final static String FLOW_LIST = "models/ofcflow/employPlan/flow";
	
	// 页面路径--培训信息流程审批页面
	private final static String TRAIN_PLAN_FLOW = "models/ofcflow/trainPlan/trainFlow";
	
	// 页面路径--培训信息流程审批页面
	private final static String TRAIN_PLAN_VIEW = "models/ofcflow/trainPlan/trainPlanView";
	
	// 年度培训考核列表
	private final static String TRAIN_YEAR_PLAN_PAGE = "models/ofcflow/trainPlan/yearList";
	
	// 年度培训考核添加编辑页面
	private final static String TRAIN_YEAR_PLAN_EDIT_PAGE = "models/ofcflow/trainPlan/year";
	
	// 单位培训考核选择年度培训考核页面(非人社局)
	private final static String TRAIN_EMPLOY_PRE_PLAN_PAGE = "models/ofcflow/trainPlan/pre";
	
	// 单位培训考核选择年度培训考核页面(人社局)
	private final static String TRAIN_EMPLOY_PRE_PLAN_PAGE2 = "models/ofcflow/trainPlan/pre2";
	
	
		
	/**
	 * 培训信息列表
	 * @Title: index
	 * @Description: 培训信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return TRAIN_INDEX;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 培训列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<TrainPlanVO> pageList(TrainPlan trainPlan, Integer limit, Integer page) {
		Page<TrainPlanVO> pageInfo = trainPlanService.pageList(trainPlan, page, limit);
		return pageInfo;
	}
	
	/**
	 * 添加培训信息页面
	 * @Title: ResignList
	 * @Description: 培训信息列表
	 * @return
	 * @return: String
	 *//*
	@RequestMapping("/addTrainPlan")
	public String addTrainPlan(String id,Model model) {
		if (StringUtils.isNoneBlank(id)) {
			TrainPlan trainPlan = trainPlanService.get(id);
			model.addAttribute("trainPlan", trainPlan);
		}
		return ADD_TRAIN_PLAN;
	}*/
	
	/**
	 * @Title: saveTrainPlan
	 * @Description: 培训信息保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveTrainPlan")
	public AjaxResult saveTrainPlan(TrainPlan temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				trainPlanService.save(temp);// 保存
			} else {
				TrainPlan trainPlan = trainPlanService.get(temp.getId());
				if(StringUtils.isNotBlank(temp.getTrainName())){
					trainPlan.setTrainName(temp.getTrainName());
				}
				if(temp.getHours()!=null){
					trainPlan.setHours(temp.getHours());
				}
				if(temp.getStartDate()!=null){
					trainPlan.setStartDate(temp.getStartDate());
				}
				if(temp.getEndDate()!=null){
					trainPlan.setEndDate(temp.getEndDate());
				}
				if(StringUtils.isNotBlank(temp.getDescription())){
					trainPlan.setDescription(temp.getDescription());
				}
				trainPlanService.update(trainPlan);// 保存
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
	 * @Title: remove 
	 * @Description: 	培训信息删除
	 * @param id		
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/remove")
	public AjaxResult remove(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			TrainPlan trainPlan = trainPlanService.get(id);
			for(TrainPerson t:trainPlan.getTrainPerson()){
				trainPersonService.delete(t);
			}
			trainPlanService.delete(trainPlan);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * 培训信息提交页面
	 * @Title: uploadTrainPlan
	 * @Description: 培训信息提交页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/uploadTrainPlan")
	public String uploadTrainPlan(String id,Model model) {
		TrainPlan trainPlan = trainPlanService.get(id);
		model.addAttribute("trainPlan",trainPlan);
		if(trainPlan.getInputOrgan().getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)){//录入单位如果是人社局
			return UPLOAD_TRAIN_PLAN2;
		}else{
			return UPLOAD_TRAIN_PLAN;
		}
	}
	
	/**
	 * 添加培训人员页面
	 * @Title: addTrainPerson
	 * @Description: 培训信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/addTrainPerson")
	public String addTrainPerson(String id,Model model) {
		if (StringUtils.isNoneBlank(id)) {
			TrainPlan trainPlan = trainPlanService.get(id);
			model.addAttribute("trainPlan", trainPlan);
		}
		return ADD_TRAIN_PERSON;
	}
	
	/**
	 * @Title: saveTrainPerson
	 * @Description: 培训个人信息保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveTrainPerson")
	public AjaxResult saveTrainPerson(TrainPerson temp) {
		AjaxResult result = new AjaxResult(true);
		DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
		try {
			DetachedCriteria detachedcriteria = DetachedCriteria.forClass(TrainPerson.class);
			//根据人员id查询
			DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
			s.add(Restrictions.eq("s.id", temp.getServant().getId()));
			//根据培训信息id查询
			DetachedCriteria p = detachedcriteria.createAlias("plan", "p");
			p.add(Restrictions.eq("p.id", temp.getPlan().getId()));
			
			detachedcriteria.add(Restrictions.eq("removed", false));
			List<TrainPerson> list = trainPersonService.findByCriteria(detachedcriteria);
			
			if(list.size()>0){
				throw new BusinessException("人员已存在！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				temp.setId(null);
				trainPersonService.save(temp);// 保存
			} else {
				TrainPerson trainPerson = trainPersonService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, trainPerson);
				trainPersonService.update(trainPerson);// 保存
			}
			result.setMessage("保存成功！");
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
	 * @Title: delTrainPerson
	 * @Description: 培训个人信息删除
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delTrainPerson")
	public AjaxResult delTrainPerson(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			TrainPerson trainPerson = trainPersonService.get(id);
			trainPersonService.delete(trainPerson);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: trainPersonList
	 * @Description: 培训人员列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<TrainPersonVO>
	 */
	@ResponseBody
	@RequestMapping("/trainPersonList")
	public Page<TrainPersonVO> trainPersonList(String planId, Integer limit, Integer page) {
		Page<TrainPersonVO> pageInfo = trainPersonService.pageList(planId,page,limit);
		return pageInfo;
	}
	
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","Train");
		return FLOW_LIST;
	}
	
	/**
	 * @Title: operationPlan 
	 * @Description: 审批培训计划（非人社局）
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationPlan")
	public AjaxResult operationPlan(TrainPlan temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			TrainPlan trainPlan = trainPlanService.load(temp.getId());
			trainPlanService.savePlan(trainPlan,opinion,r);
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
	 * @Title: trainFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/trainPlanFlow")
	public String planFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		TrainPlan trainPlan = trainPlanService.get(flow.getBusId());
		model.addAttribute("trainPlan", trainPlan);
		if(TrainPlan.STATUS_TRAIN_PLAN_STEP1==trainPlan.getStatus()){//如果是待提交
			return UPLOAD_TRAIN_PLAN;
		}else{
			return TRAIN_PLAN_FLOW;
		}
	}
	
	/**
	 * @Title: planView 
	 * @Description: 录用计划详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/view")
	public String planView(Model model,String id) {
		TrainPlan trainPlan = trainPlanService.load(id);
		model.addAttribute("trainPlan",trainPlan);
		return TRAIN_PLAN_VIEW;//培训信息
	}
	
	/**
	 * @Title: planView 
	 * @Description: 年度培训页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/year/list")
	public String yearlist() {
		return TRAIN_YEAR_PLAN_PAGE;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 年度培训考核列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@ResponseBody
	@RequestMapping("/year/yearplanlist")
	public Page<TrainYearPlanVO> yearplanlist(TrainYearPlan trainYearPlan, Integer limit, Integer page) {
		Page<TrainYearPlanVO> pageInfo = trainYearPlanService.pageList(trainYearPlan, page, limit);
		return pageInfo;
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
			TrainYearPlan trainYearPlan = trainYearPlanService.get(id);
			
			if(trainYearPlan.getState()==0){
				trainYearPlan.setState(null);
			}
			model.addAttribute("trainYearPlan", trainYearPlan);
		}

		return TRAIN_YEAR_PLAN_EDIT_PAGE;
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
	public AjaxResult saveYearPlan(TrainYearPlan temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				trainYearPlanService.save(temp);// 保存
			} else {
				TrainYearPlan trainYearPlan = trainYearPlanService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, trainYearPlan);
				trainYearPlanService.update(trainYearPlan);// 保存
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
	 * @Title: preParams 
	 * @Description: 招录计划选择页面
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/pre")
	public String preParams(Model model) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("inputOrgan", x);
		if(x!=null){
			if(x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)){//如果x是人社局
				return TRAIN_EMPLOY_PRE_PLAN_PAGE2;
			}else{
				model.addAttribute("trainOrgan", x);
			}
		}
		return TRAIN_EMPLOY_PRE_PLAN_PAGE;
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
			
			TrainYearPlan trainYearPlan = trainYearPlanService.get(id);

			//判断该年度计划下是否存在培训考核，如果存在，不能删除
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TrainPlan.class);
			detachedCriteria.add(Restrictions.in("yearPlan", trainYearPlan));
			List<TrainPlan> planList = trainPlanService.findByCriteria(detachedCriteria);
			
			if(planList!=null&&planList.size()>0){
				throw new BusinessException("该年度培训考核已新增培训考核，不能删除！");
			}
			
			trainYearPlanService.delete(trainYearPlan);// 删除

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
	 * @Description: 年度培训考核查询
	 * @param id 单位id 
	 * @return
	 * @return: List<RecruitYearPlan>
	 */
	@ResponseBody
	@RequestMapping("/year/find")
	public List<TrainYearPlan> findYearPlan(String id) {
		
		List<TrainYearPlan> list = null;
		try {
			Sorts sort = new Sorts();// 排序规则
			sort.add("startDate", false);// 降序
			List<Predicate> filter = new ArrayList<>();// 查询条件
			Predicate p = new Predicate("state", Operator.EQ, 1, "");
			filter.add(p);
			Predicate p1 = new Predicate("startDate", Operator.LTE, new Date(), "");
			filter.add(p1);
			Predicate p2 = new Predicate("endDate", Operator.GTE, new Date(), "");
			filter.add(p2);
			list = trainYearPlanService.findByFilter(filter, sort);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 
	 * @Title: valiateExistEmployPlan 
	 * @Description:  判断选择的培训考核是否已经有了培训数据
	 * @param model
	 * @param employOrganCode  单位CODE
	 * @param yearPlanId   年度培训考核id
	 * @return
	 * @return: Boolean
	 */
	@RequestMapping("/plan/validate")
	@ResponseBody
	public AjaxResult valiateExistEmployPlan(Model model, String yearPlanId) {
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(yearPlanId)){
				throw new BusinessException("年度培训考核不能为空！");
			}
			TrainYearPlan trainYearPlan = trainYearPlanService.load(yearPlanId);
			Date start = trainYearPlan.getStartDate();
			Date end = trainYearPlan.getEndDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long now = sdf.parse(sdf.format(new Date())).getTime();
			if(now<start.getTime()){
				throw new BusinessException("该年度培训考核还未开始！");
			}
			if(now>end.getTime()){
				throw new BusinessException("该年度培训考核已经结束！");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("新增单位培训考核失败！");
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
	@RequestMapping("/trainPlan/plan")
	public String plan(Model model, String trainPlanId,String yearPlanId,String trainOrganId) {
		TrainPlan plan =null;
		if(StringUtils.isNotBlank(trainPlanId)){
			plan = trainPlanService.get(trainPlanId);
			model.addAttribute("yearPlan", plan.getYearPlan());
		}else{
			plan = new TrainPlan();
			TrainYearPlan yearPlan = trainYearPlanService.get(yearPlanId);
			plan.setYearPlan(yearPlan);
			OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			model.addAttribute("OrganNode", x);
			plan.setInputOrgan(x);
			if(StringUtils.isNotBlank(trainOrganId)){//如果不为空 这位人社局传过来的数据，为空则是本单位传过来的数据
				OrganNode y = organNodeService.load(trainOrganId);
				plan.setTrainOrgan(y);
			}else{
				plan.setTrainOrgan(x);
			}
		}
		
		//机关类别
		if(StringUtils.isNotBlank(yearPlanId)){
			TrainYearPlan yearPlan = trainYearPlanService.get(yearPlanId);
			model.addAttribute("yearPlan", yearPlan);
		}
		
		model.addAttribute("trainPlan", plan);
		
		return ADD_TRAIN_PLAN;
	}
	
	/**
	 * @Title: operationPlan 
	 * @Description: 审批培训计划（人社局）
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationPlan2")
	public AjaxResult operationPlan2(TrainPlan temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			TrainPlan trainPlan = trainPlanService.load(temp.getId());
			trainPlanService.savePlan2(trainPlan,opinion,r);
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
	 * @Title: exportByPerson 
	 * @Description: 导出人员信息
	 * @param id
	 * @return: void
	 */
	@RequestMapping("/exportByPerson")
	public void exportByPerson(String id,HttpServletRequest request,HttpServletResponse response) {
		try {
			TrainYearPlan typ = trainYearPlanService.get(id);
			List<?> list = trainPersonService.exportByPerson(typ);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String,Object> params = new HashMap<>();//excel导出使用的参数
			String planName = typ.getName();//年度计划名称
			String startDate = sdf.format(typ.getStartDate());//开始时间
			String endDate = sdf.format(typ.getEndDate());//结束时间
			params.put("planName", planName);
			params.put("date", startDate+"至"+endDate);
			List<String[]> dataList = new ArrayList<>();//excel中数据列表
			for(int i=0;i<list.size();i++){
				String[] arr = new String[6];
				Object[] objlist= (Object[]) list.get(i); 
				arr[0] = i+1+"";
				arr[1] = objlist[0].toString();
				arr[2] = objlist[1].toString();
				arr[3] = objlist[2].toString();
				arr[4] = objlist[3].toString();
				arr[5] = objlist[4].toString();
				dataList.add(arr);
			}
			params.put("listDataList", dataList);
			String savePath = request.getSession().getServletContext().getRealPath("/");
			String templet = savePath+"\\WEB-INF\\templates\\trainByPerson.xls";//模板路径
			String path = savePath+"\\WEB-INF\\templates\\"+planName+".xls";//生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet,path, 1,null);//替换模板数据 生成excel到tomcat服务器
		  	ExcelUtilsPOI.exceldown(path, "长宁区"+planName+"培训考核人员统计表.xls", request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: exportByUnit 
	 * @Description: 导出通过单位信息
	 * @param id
	 * @return: void
	 */
	@RequestMapping("/exportByUnit")
	public void exportByUnit(String id,HttpServletRequest request,HttpServletResponse response) {
		try {
			TrainYearPlan typ = trainYearPlanService.get(id);
			List<?> list = trainPersonService.exportByUnit(typ);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String,Object> params = new HashMap<>();//excel导出使用的参数
			String planName = typ.getName();//年度计划名称
			String startDate = sdf.format(typ.getStartDate());//开始时间
			String endDate = sdf.format(typ.getEndDate());//结束时间
			params.put("planName", planName);
			params.put("date", startDate+"至"+endDate);
			List<String[]> dataList = new ArrayList<>();//excel中数据列表
			for(int i=0;i<list.size();i++){
				String[] arr = new String[8];
				Object[] objlist= (Object[]) list.get(i); 
				arr[0] = i+1+"";
				OrganNode y = organNodeService.load(objlist[0].toString());
				arr[1] = y.getName();
				arr[2] = objlist[1].toString();
				arr[3] = objlist[2].toString();
				arr[4] = objlist[3].toString();
				arr[5] = objlist[4].toString();
				arr[6] = objlist[5].toString();
				arr[7] = objlist[6].toString();
				dataList.add(arr);
			}
			params.put("listDataList", dataList);
			String savePath = request.getSession().getServletContext().getRealPath("/");
			String templet = savePath+"\\WEB-INF\\templates\\trainByUnit.xls";//模板路径
			String path = savePath+"\\WEB-INF\\templates\\"+planName+".xls";//生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet,path, 1,null);//替换模板数据 生成excel到tomcat服务器
		  	ExcelUtilsPOI.exceldown(path, "干部教育培训完成情况.xls", request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public AjaxResult checkExport(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from TrainPlan where removed=:removed and status>=:status and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("yearPlan", id));
			listqueryparameter.add(new QueryParameter("status", TrainPlan.STATUS_TRAIN_PLAN_PASS));
			hql.append( " order by createTime desc");
			List<TrainPlan> planList = trainPlanService.findByHQL(hql.toString(), listqueryparameter);
			
			if(planList==null||planList.size()==0){
				throw new BusinessException("该年度计划下暂无完成审批的培训考核计划！");
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
}
