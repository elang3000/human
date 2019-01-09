/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月27日 下午7:12:55 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月27日 下午7:12:55 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.ofcflow;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.console.bo.FrameWorkResource;
import com.wondersgroup.framework.console.service.FrameWorkService;
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
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.AbroadPeople;
import com.wondersgroup.human.bo.ofcflow.AbroadServant;
import com.wondersgroup.human.bo.ofcflow.AbroadYearPlan;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.AbroadPeopleService;
import com.wondersgroup.human.service.ofcflow.AbroadServantService;
import com.wondersgroup.human.service.ofcflow.AbroadYearPlanService;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.AbroadPeopleVO;
import com.wondersgroup.human.vo.ofcflow.AbroadServantVO;
import com.wondersgroup.human.vo.ofcflow.AbroadYearPlanVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: AbroadController 
 * @Description: 因公出国控制类
 * @author: lihao
 * @date: 2018年9月27日 下午7:12:55
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@RequestMapping("/ofcflow/abroadB")
@Controller
public class AbroadServantController extends GenericController{
	
	@Autowired
	AbroadYearPlanService abroadYearPlanService;
	
	@Autowired
	AbroadServantService abroadServantService;
	
	@Autowired
	AbroadPeopleService abroadPeopleService;
	
	@Autowired
	ServantService servantService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	FrameWorkService frameWorkService;
	
	@Autowired
	private WorkflowService workFlowService;
	
	// 因公出国列表
	private final static String ABROAD_INDEX = "models/ofcflow/abroad1/index";

	// 因公出国汇总列表
	private final static String ABROAD_YEAR_PLAN_PAGE = "models/ofcflow/abroad1/yearList";
	
	// 因公出国选择计划页面
	private final static String ABROAD_YEAR_PLAN_PRE = "models/ofcflow/abroad1/pre";
	
	// 因公出国汇总导出页面
	private final static String ABROAD_YEAR_PLAN_EXPORT = "models/ofcflow/abroad1/export";
	
	// 因公出国汇总添加编辑页面
	private final static String ABROAD_YEAR_PLAN_EDIT_PAGE = "models/ofcflow/abroad1/year";
	
	// 因公出国单位添加编辑页面
	private final static String ABROAD_EDIT_PAGE = "models/ofcflow/abroad1/abroadEdit";
	
	// 因公出国查看页面
	private final static String ABROAD_VIEW = "models/ofcflow/abroad1/view";
	
	// 页面路径--因公出国流程列表
	private final static String FLOW_LIST = "models/ofcflow/employPlan/flow";
	
	// 页面路径--因公出国流程详情
	private final static String ABROAD_FLOW = "models/ofcflow/abroad1/abroadFlow";
	
	/**
	 * 因公出国列表
	 * @Title: index
	 * @Description: 因公出国列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return ABROAD_INDEX;
	}
	
	/**
	 * @Title: planView 
	 * @Description: 因公出国汇总页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/year/list")
	public String yearlist() {
		return ABROAD_YEAR_PLAN_PAGE;
	}
	
	/**
	 * @Title: yearEdit 
	 * @Description: 因公出国汇总添加页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/year/edit")
	public String yearEdit(String id, Model model) {

		if (StringUtils.isNotBlank(id)) {
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(id);
			
			if(abroadYearPlan.getState()==0){
				abroadYearPlan.setState(null);
			}
			model.addAttribute("abroadYearPlan", abroadYearPlan);
		}

		return ABROAD_YEAR_PLAN_EDIT_PAGE;
	}
	
	/**
	 * 
	 * @Title: findYearPlan 
	 * @Description: 因公出国汇总查询下拉框
	 * @param id 单位id 
	 * @return
	 * @return: List<RecruitYearPlan>
	 */
	@ResponseBody
	@RequestMapping("/year/find1")
	public List<AbroadYearPlan> findYearPlan1(String id) {
		
		List<AbroadYearPlan> list = null;
		try {
			Sorts sort = new Sorts();// 排序规则
			sort.add("createTime", false);// 降序
			List<Predicate> filter = new ArrayList<>();// 查询条件
			Predicate p = new Predicate("state", Operator.EQ, 1, "");
			filter.add(p);
			list = abroadYearPlanService.findByFilter(filter, sort);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 
	 * @Title: findYearPlan 
	 * @Description: 因公出国汇总下拉框
	 * @param id 单位id 
	 * @return
	 * @return: List<RecruitYearPlan>
	 */
	@ResponseBody
	@RequestMapping("/year/find2")
	public List<AbroadYearPlan> findYearPlan2(String id) {
		
		List<AbroadYearPlan> list = null;
		try {
			Sorts sort = new Sorts();// 排序规则
			sort.add("createTime", false);// 降序
			List<Predicate> filter = new ArrayList<>();// 查询条件
			Predicate p = new Predicate("state", Operator.EQ, 1, "");
			filter.add(p);
			Predicate p1 = new Predicate("startDate", Operator.LTE, new Date(), "");
			filter.add(p1);
			Predicate p2 = new Predicate("endDate", Operator.GTE, new Date(), "");
			filter.add(p2);
			list = abroadYearPlanService.findByFilter(filter, sort);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 因公出国汇总列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@Log(title = "查询因公出国政审汇总列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/year/yearplanlist")
	public Page<AbroadYearPlanVO> yearplanlist(AbroadYearPlan abroadYearPlan, Integer limit, Integer page) {
		Page<AbroadYearPlanVO> pageInfo = abroadYearPlanService.pageList(abroadYearPlan, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: saveYearPlan
	 * @Description: 因公出国汇总保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "编辑因公出国政审汇总", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveYearPlan")
	public AjaxResult saveYearPlan(AbroadYearPlan temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			if(temp.getState()==null){
				temp.setState(0);
			}
			
			if (StringUtils.isBlank(temp.getId())) {
				abroadYearPlanService.save(temp);// 保存
			} else {
				AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, abroadYearPlan);
				abroadYearPlanService.update(abroadYearPlan);// 保存
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
	 * @Description: 因公出国汇总删除
	 * @param temp
	 * @return AjaxResult
	 * @return: AjaxResult
	 */
	@Log(title = "删除因公出国政审汇总", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/year/delete")
	public AjaxResult deleteYearPlan(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(id);

			//判断该计划下是否存在培训考核，如果存在，不能删除
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AbroadServant.class);
			detachedCriteria.add(Restrictions.in("yearPlan", abroadYearPlan));
			List<AbroadServant> planList = abroadServantService.findByCriteria(detachedCriteria);
			
			if(planList!=null&&planList.size()>0){
				throw new BusinessException("该因公出国计划已新增培训考核，不能删除！");
			}
			
			abroadYearPlanService.delete(abroadYearPlan);// 删除

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
	 * @Title: validate 
	 * @Description:  判断计划是否开始结束，该计划下人员是否已被选择
	 * @param model
	 * @param employOrganCode  单位CODE
	 * @param yearPlanId   培训考核id
	 * @return
	 * @return: Boolean
	 */
	@RequestMapping("/plan/validate")
	@ResponseBody
	public AjaxResult validate(Model model, String yearPlanId,String id,String servantIds) {
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(yearPlanId)){
				throw new BusinessException("因公出国计划不能为空！");
			}
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(yearPlanId);
			Date start = abroadYearPlan.getStartDate();
			Date end = abroadYearPlan.getEndDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long now = sdf.parse(sdf.format(new Date())).getTime();
			if(now<start.getTime()){
				throw new BusinessException("该因公出国计划还未开始！");
			}
			if(now>end.getTime()){
				throw new BusinessException("该因公出国计划已经结束！");
			}
			if(abroadYearPlan.getState()==0){
				throw new BusinessException("该因公出国计划还未开启！");
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("新增单位参公计划失败！");
		}
		return result;
	}
	
	/**
	 * 因公出国信息
	 * @Title: view
	 * @Description: 因公出国信息
	 * @return
	 * @return: String
	 */
	@Log(title = "查看单位因公出国政审信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/view")
	public String view(String id,Model model) {
		AbroadServant abroadServant = abroadServantService.get(id);
		model.addAttribute("abroadServant", abroadServant);
		model.addAttribute("abroadYearPlan", abroadServant.getYearPlan());
		String servantIds = "";
		List<AbroadPeople> apList = abroadPeopleService.getServantIdsByAbroadId(id);
		for(AbroadPeople ap:apList){
			servantIds += ap.getServant().getId() +",";
		}
		if(StringUtils.isNotBlank(servantIds)){
			servantIds = servantIds.substring(0,servantIds.length()-1);
		}
		model.addAttribute("servantIds", servantIds);
		return ABROAD_VIEW;
	}
	
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","Abroad");
		return FLOW_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 因公出国事项列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@Log(title = "查询单位因公出国政审列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<AbroadServantVO> pageList(AbroadServant abroadServant, Integer limit, Integer page) {
		Page<AbroadServantVO> pageInfo = abroadServantService.pageList(abroadServant, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: remove 
	 * @Description: 	因公出国事项删除
	 * @param id		
	 * @return: AjaxResult
	 */
	@Log(title = "删除单位因公出国政审", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/remove")
	public AjaxResult remove(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			AbroadServant abroadServant = abroadServantService.get(id);
			abroadServantService.delete(abroadServant);
			
			List<AbroadPeople> apList = abroadPeopleService.getServantIdsByAbroadId(id);//根据AbroadServant的id删除原来保存的人员
			for(AbroadPeople ap:apList){
				abroadPeopleService.delete(ap);
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
	 * @Title: abroadFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/abroadFlow")
	public String abroadFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		AbroadServant abroadServant = abroadServantService.get(flow.getBusId());
		model.addAttribute("abroadServant", abroadServant);
		model.addAttribute("abroadYearPlan", abroadServant.getYearPlan());
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("x", x);
		if(AbroadServant.STATUS_ABROAD_PLAN_STEP1==abroadServant.getStatus()){//如果是待提交
			return ABROAD_EDIT_PAGE;
		}else{
			return ABROAD_FLOW;
		}
	}
	
	/**
	 * @Title: export 
	 * @Description: 导出页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/year/export")
	public String export(Model model,String id) {
		AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(id);
		model.addAttribute("abroadYearPlan", abroadYearPlan);
		return ABROAD_YEAR_PLAN_EXPORT;
	}
	
	/**
	 * @Title: exportByUnit 
	 * @Description: 导出文件
	 * @param id
	 * @return: void
	 */
	@Log(title = "导出因公出国政审汇总", operatorType = OperatorType.BUSINESS, businessType = BusinessType.EXPORT,
		     isSaveRequestData = true)
	@RequestMapping("/exportAbroad")
	public void exportAbroad(AbroadYearPlan temp,HttpServletRequest request,HttpServletResponse response) {
		try {
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(temp.getId());
			abroadYearPlan.setApproveStartDate(temp.getApproveStartDate());//设置批件开始时间
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(temp.getApproveStartDate());//设置起时间
			cal.add(Calendar.YEAR, 3);//增加
			
			abroadYearPlan.setApproveEndDate(cal.getTime());//设置批件结束时间
			abroadYearPlanService.update(abroadYearPlan);
			
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from AbroadPeople where removed=:removed and abroadServant.status>=:status and abroadServant.yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("status", AbroadServant.STATUS_ABROAD_PLAN_PASS));
			listqueryparameter.add(new QueryParameter("yearPlan", temp.getId()));
			hql.append( " order by createTime asc");
			List<AbroadPeople> list = abroadPeopleService.findByHQL(hql.toString(), listqueryparameter);
			
			String firstName = "";
			if(list.size()>0){
				firstName = list.get(0).getServant().getName();
			}
			long num = list.size();
			String numCN = Number2CN.cvt(num,true);//数字转中文
			Map<String,String> map = ofcFlowNumberService.executeNumber("Abroad",abroadYearPlan.getId());
			String abroadYear = map.get("year");
			String abroadNum = map.get("number");
			
			Map<String, Object> ygcgMap = new HashMap<String, Object>();
			ygcgMap.put("nyear", DateUtils.getYear());//年号
			ygcgMap.put("nnumCN", numCN);//人数
			ygcgMap.put("nname", firstName);//姓名
			ygcgMap.put("ncountry", abroadYearPlan.getCountry());//国家（地区）
			ygcgMap.put("nday", abroadYearPlan.getDay());//停留天数
			ygcgMap.put("nstartday", abroadYearPlan.getApproveStartDate());//有效期起始时间
			ygcgMap.put("nendday", abroadYearPlan.getApproveEndDate());//有效期结束时间
			ygcgMap.put("nnowday", new Date());//当前时间
			
			ygcgMap.put("abroadYear", abroadYear);//编号年
			ygcgMap.put("abroadNum", abroadNum);//编号
			
			List<Map<String,String>> dataList = new ArrayList<>();//excel中数据列表
			for(int i=0;i<list.size();i++){
				AbroadPeople abroadPeople = list.get(i);
				AbroadPeopleVO vo = new AbroadPeopleVO(abroadPeople);
				Map<String,String> m = new HashMap<String,String>();
				m.put("name", vo.getName());//姓名
				m.put("sex", vo.getSex());//性别
				m.put("birthDate", vo.getBirthDate());//出生日期
				m.put("birthPlaceName", vo.getBirthPlaceName());//出生地
				m.put("departName", vo.getDepartName());//工作单位
				m.put("postName", vo.getPostName());//职务职称
				dataList.add(m);
			}
			ygcgMap.put("listDataList", dataList);
			
			try {
                WordUtils.exportMillCertificateWord(request,response,ygcgMap,abroadYearPlan.getName()+"因公出国批件","ygcg.ftl");
            } catch (IOException e) {
                e.printStackTrace();
            }    
			
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
			hql.append("from AbroadServant where removed=:removed and status=:status and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("status", AbroadServant.STATUS_ABROAD_PLAN_PASS));
			listqueryparameter.add(new QueryParameter("yearPlan", id));
			hql.append( " order by createTime asc");
			List<AbroadServant> list = abroadServantService.findByHQL(hql.toString(), listqueryparameter);
			
			if(list==null||list.size()==0){
				throw new BusinessException("因公出国事项不能为空！");
			}
			
			List<QueryParameter> listqueryparameter2=new ArrayList<>();
			StringBuilder hql2=new StringBuilder();
			hql2.append("from AbroadServant where removed=:removed and status<:status and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem2=new QueryParameter("removed", false);
			listqueryparameter2.add(queryParameteritem2);
			listqueryparameter2.add(new QueryParameter("status", AbroadServant.STATUS_ABROAD_PLAN_PASS));
			listqueryparameter2.add(new QueryParameter("yearPlan", id));
			hql2.append( " order by createTime asc");
			List<AbroadServant> list2 = abroadServantService.findByHQL(hql2.toString(), listqueryparameter2);
			
			if(list2.size()>0){
				throw new BusinessException("还有因公出国事项在审批中！");
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
	 * @Title: pre 
	 * @Description: 选择计划页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/year/pre")
	public String pre(Model model) {
		return ABROAD_YEAR_PLAN_PRE;
	}
	
	/**
	 * 因公出国汇总单位权限人员通知
	 * @Title: yearPlanNoticeOrgan
	 * @Description: 因公出国汇总单位权限人员通知
	 * @return
	 * @return: String
	 */
	@Log(title = "新增单位因公出国政审通知", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("year/noticeOrgan")
	public AjaxResult yearPlanNoticeOrgan(String id,String organIds) {
		AjaxResult result = new AjaxResult(true);
		try {
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(id);
			
			DetachedCriteria c = DetachedCriteria.forClass(FrameWorkResource.class);
			c.add(Restrictions.eq("code", "ADD_ABROAD_PLAN_BTN"));
			List<FrameWorkResource>  f = frameWorkService.findByCriteria(c);//根据权限代码查询权限ID
			
			String title = "关于（"+abroadYearPlan.getName()+"）因公出国事项申请填写通知";
			String content = "关于（"+abroadYearPlan.getName()+"）因公出国事项申请填写，请在"+abroadYearPlan.getStartDate()+"至"+abroadYearPlan.getEndDate()+"上报人员信息及相关材料";
			OrganNode o = null;
			for(String s:organIds.split(",")){
				o = organNodeService.get(s);
				List<SecurityUser>  a = workFlowService.queryAuthUser(o.getId(), f.get(0).getId());//查询单位下有权限人员
				if(a!=null&&a.size()>0){
					AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, a.get(0).getId(), title, content, "")));
				}else{
					throw new BusinessException(o.getName()+"未分配节点权限！");
				}
			}
			result.setMessage("发送成功！");
		} catch (BusinessException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("发送失败！");
		}
		return result;
	}
	
	/**
	 * @Title: editAbroadServant 
	 * @Description: 因公出国添加页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editAbroadServant")
	public String editAbroadServant(String id, Model model,String yearPlayId) {
		
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		
		if (StringUtils.isNotBlank(id)) {
			AbroadServant abroadServant = abroadServantService.get(id);
			model.addAttribute("abroadServant", abroadServant);
			model.addAttribute("abroadYearPlan", abroadServant.getYearPlan());
		}else{
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(yearPlayId);
			model.addAttribute("abroadYearPlan", abroadYearPlan);
		}
		model.addAttribute("x", x);
		return ABROAD_EDIT_PAGE;
	}
	
	/**
	 * @Title: saveAbroadServant
	 * @Description: 因公出国保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "编辑因公出国政审", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveAbroadServant")
	public AjaxResult saveAbroadServant(AbroadServant temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				temp.setId(null);
				abroadServantService.save(temp);// 保存
			} else {
				AbroadServant abroadServant = abroadServantService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, abroadServant);
				abroadServantService.update(abroadServant);// 保存
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
	 * @Title: operationFlow 
	 * @Description: 审批因公出国
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批因公出国政审流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(AbroadServant temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r)&&!FlowRecord.STOP.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				abroadServantService.saveFlow(temp,opinion,r);// 保存
			} else {
				AbroadServant abroadServant = abroadServantService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, abroadServant);
				DictUtils.operationCodeInfo(abroadServant);//将CodeInfo中id为空的属性 设置为null
				abroadServantService.saveFlow(abroadServant,opinion,r);
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
	 * @Title: pageList
	 * @Description: 因公出国事项人员列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<AbroadPeopleVO>
	 */
	@Log(title = "查询因公出国政审人员列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/abroadPeopleList")
	public Page<AbroadPeopleVO> abroadPeopleList(String abroadId,String abroadYearId, Integer limit, Integer page) {
		Page<AbroadPeopleVO> pageInfo = abroadPeopleService.getByServantIds(abroadId,abroadYearId,limit,page);
		return pageInfo;
	}
	
	/**
	 * @Title: savePeople 
	 * @Description: 转出添加人员
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "新增因公出国政审人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/savePeople")
	public AjaxResult savePeople(AbroadServant abroadServant,String servantIds){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(servantIds)){
				throw new BusinessException("添加人员信息获取失败！");
			}
			
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from AbroadPeople where removed=:removed and abroadServant.yearPlan.id=:yearPlan and abroadServant.id !=:id and abroadServant.status != :status");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("yearPlan", abroadServant.getYearPlan().getId()));
			listqueryparameter.add(new QueryParameter("id", abroadServant.getId()));
			listqueryparameter.add(new QueryParameter("status", FlowRecord.BUS_STOP));//排除业务状态为-1（业务中止）的
			List<AbroadPeople> list = abroadPeopleService.findByHQL(hql.toString(), listqueryparameter);
			
			String servantNames = "";
			if(StringUtils.isNotBlank(servantIds)){
				for(String s:servantIds.split(",")){
					for(AbroadPeople ap:list){
						Servant servant = servantService.get(ap.getServant().getId());
						if(s.equals(servant.getId())){
							servantNames +=servant.getName() +",";
							break;
						}
					}
				}
			}
			
			if(StringUtils.isNotBlank(servantNames)){
				throw new BusinessException(servantNames.substring(0,servantNames.length()-1)+"已在计划内！");
			}
			
			abroadServantService.savePeople(abroadServant,servantIds);
			result.setData(abroadServant.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("添加失败！");
		}
		return result;
	}
	
	/**
	 * @Title: deletePeople
	 * @Description: 删除人员
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "删除因公出国政审人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/deletePeople")
	public AjaxResult deletePeople(String ids){
		AjaxResult result = new AjaxResult(true);
		try {
			
			for(String s:ids.split(",")){
				AbroadPeople abroadPeople = abroadPeopleService.get(s);
				abroadPeopleService.delete(abroadPeople);
			}
			
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
