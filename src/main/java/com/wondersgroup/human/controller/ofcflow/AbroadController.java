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
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.AbroadPlan;
import com.wondersgroup.human.bo.ofcflow.AbroadYearPlan;
import com.wondersgroup.human.service.ofcflow.AbroadPlanService;
import com.wondersgroup.human.service.ofcflow.AbroadYearPlanService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.AbroadPersonVO;
import com.wondersgroup.human.vo.ofcflow.AbroadPlanVO;
import com.wondersgroup.human.vo.ofcflow.AbroadYearPlanVO;

/** 
 * @ClassName: AbroadController 
 * @Description: 因公出国控制类
 * @author: lihao
 * @date: 2018年9月27日 下午7:12:55
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@RequestMapping("/ofcflow/abroad")
@Controller
public class AbroadController extends GenericController{
	
	@Autowired
	AbroadYearPlanService abroadYearPlanService;
	
	@Autowired
	AbroadPlanService abroadPlanService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	// 因公出国列表
	private final static String ABROAD_INDEX = "models/ofcflow/abroad/index";

	// 因公出国汇总列表
	private final static String ABROAD_YEAR_PLAN_PAGE = "models/ofcflow/abroad/yearList";
	
	// 因公出国汇总添加编辑页面
	private final static String ABROAD_YEAR_PLAN_EDIT_PAGE = "models/ofcflow/abroad/year";
	
	// 因公出国单位添加编辑页面
	private final static String ABROAD_PLAN_EDIT_PAGE = "models/ofcflow/abroad/abroadPlanEdit";
	
	// 因公出国查看页面
	private final static String ABROAD_PLAN_VIEW = "models/ofcflow/abroad/view";
	
	// 页面路径--因公出国流程列表
	private final static String FLOW_LIST = "models/ofcflow/employPlan/flow";
	
	// 页面路径--因公出国流程列表
	private final static String ABROAD_PLAN_FLOW = "models/ofcflow/abroad/abroadFlow";
	
	/**
	 * 参公交流列表
	 * @Title: index
	 * @Description: 参公交流列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return ABROAD_INDEX;
	}
	
	/**
	 * @Title: planView 
	 * @Description: 参公交流汇总页面
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
	 * @Description: 参公交流汇总添加
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/year/edit")
	public String yearEdit(String id, Model model) {

		if (StringUtils.isNotBlank(id)) {
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(id);
			model.addAttribute("abroadYearPlan", abroadYearPlan);
		}

		return ABROAD_YEAR_PLAN_EDIT_PAGE;
	}
	
	/**
	 * 
	 * @Title: findYearPlan 
	 * @Description: 年度因公参训查询
	 * @param id 单位id 
	 * @return
	 * @return: List<RecruitYearPlan>
	 */
	@ResponseBody
	@RequestMapping("/year/find")
	public List<AbroadYearPlan> findYearPlan(String id) {
		
		List<AbroadYearPlan> list = null;
		try {
			Sorts sort = new Sorts();// 排序规则
			sort.add("startDate", false);// 降序
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
	 * @Title: pageList
	 * @Description: 年度因公参训列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@ResponseBody
	@RequestMapping("/year/yearplanlist")
	public Page<AbroadYearPlanVO> yearplanlist(AbroadYearPlan abroadYearPlan, Integer limit, Integer page) {
		Page<AbroadYearPlanVO> pageInfo = abroadYearPlanService.pageList(abroadYearPlan, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: saveYearPlan
	 * @Description: 年度因公参训保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveYearPlan")
	public AjaxResult saveYearPlan(AbroadYearPlan temp) {
		AjaxResult result = new AjaxResult(true);
		try {
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
			
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(id);

			//判断该年度计划下是否存在培训考核，如果存在，不能删除
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AbroadPlan.class);
			detachedCriteria.add(Restrictions.in("yearPlan", abroadYearPlan));
			List<AbroadPlan> planList = abroadPlanService.findByCriteria(detachedCriteria);
			
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
	public AjaxResult validate(Model model, String yearPlanId) {
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
	 * @Title: yearEdit 
	 * @Description: 参公交流汇总添加
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/addAbroadPlan")
	public String addAbroadPlan(String id, Model model) {
		
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		
		if (StringUtils.isNotBlank(id)) {
			AbroadPlan abroadPlan = abroadPlanService.get(id);
			model.addAttribute("abroadPlan", abroadPlan);
			model.addAttribute("servant", abroadPlan.getServant());
			model.addAttribute("abroadYearPlan", abroadPlan.getYearPlan());
		}
		model.addAttribute("x", x);
		return ABROAD_PLAN_EDIT_PAGE;
	}
	
	/**
	 * @Title: saveAbroadPlan
	 * @Description: 培训信息保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveAbroadPlan")
	public AjaxResult saveAbroadPlan(AbroadPlan temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				temp.setId(null);
				abroadPlanService.save(temp);// 保存
			} else {
				AbroadPlan abroadPlan = abroadPlanService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, abroadPlan);
				abroadPlanService.update(abroadPlan);// 保存
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
	 * @Title: pageList
	 * @Description: 因公参训事项列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<AbroadPlanVO> pageList(AbroadPlan abroadPlan, Integer limit, Integer page) {
		Page<AbroadPlanVO> pageInfo = abroadPlanService.pageList(abroadPlan, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: remove 
	 * @Description: 	因公参训事项删除
	 * @param id		
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/remove")
	public AjaxResult remove(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			AbroadPlan abroadPlan = abroadPlanService.get(id);
			abroadPlanService.delete(abroadPlan);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * 因公出国信息
	 * @Title: addTrainPerson
	 * @Description: 因公出国信息
	 * @return
	 * @return: String
	 */
	@RequestMapping("/view")
	public String view(String id,Model model) {
		AbroadPlan abroadPlan = abroadPlanService.get(id);
		model.addAttribute("abroadPlan", abroadPlan);
		model.addAttribute("servant", abroadPlan.getServant());
		model.addAttribute("abroadYearPlan", abroadPlan.getYearPlan());
		return ABROAD_PLAN_VIEW;
	}
	
	/**
	 * @Title: operationPlan 
	 * @Description: 审批因公出国
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationPlan")
	public AjaxResult operationPlan(AbroadPlan temp, HttpServletRequest request) {
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
				abroadPlanService.savePlan(temp,opinion,r);// 保存
			} else {
				AbroadPlan abroadPlan = abroadPlanService.load(temp.getId());
				abroadPlanService.savePlan(abroadPlan,opinion,r);
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
		model.addAttribute("busType","Abroad");
		return FLOW_LIST;
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
		AbroadPlan abroadPlan = abroadPlanService.get(flow.getBusId());
		model.addAttribute("abroadPlan", abroadPlan);
		model.addAttribute("servant", abroadPlan.getServant());
		model.addAttribute("abroadYearPlan", abroadPlan.getYearPlan());
		if(AbroadPlan.STATUS_ABROAD_PLAN_STEP1==abroadPlan.getStatus()){//如果是待提交
			return ABROAD_PLAN_EDIT_PAGE;
		}else{
			return ABROAD_PLAN_FLOW;
		}
	}
	
	/**
	 * @Title: exportByUnit 
	 * @Description: 导出人员信息
	 * @param id
	 * @return: void
	 */
	@RequestMapping("/exportPerson")
	public void exportPerson(String id,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from AbroadPlan where removed=:removed and status>=:status and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("status", AbroadPlan.STATUS_ABROAD_PLAN_PASS));
			listqueryparameter.add(new QueryParameter("yearPlan", id));
			hql.append( " order by createTime desc");
			List<AbroadPlan> list = abroadPlanService.findByHQL(hql.toString(), listqueryparameter);
			
			Map<String,Object> params = new HashMap<>();//excel导出使用的参数
			List<String[]> dataList = new ArrayList<>();//excel中数据列表
			for(int i=0;i<list.size();i++){
				AbroadPlan abroadPlan = list.get(i);
				AbroadPersonVO vo = new AbroadPersonVO(abroadPlan);
				String[] arr = new String[6];
				arr[0] = vo.getName();//姓名
				arr[1] = vo.getSex();//性别
				arr[2] = vo.getBirthDate();//出生日期
				arr[3] = vo.getBirthPlaceName();//出生地
				arr[4] = vo.getDepartName();//工作单位
				arr[5] = vo.getPostName();//职务职称
				dataList.add(arr);
			}
			params.put("listDataList", dataList);
			String savePath = request.getSession().getServletContext().getRealPath("/");
			String templet = savePath+"\\WEB-INF\\templates\\abroadPersonList.xls";//模板路径
			String path = savePath+"\\WEB-INF\\templates\\因公出国政审名单.xls";//生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet,path, 1,null);//替换模板数据 生成excel到tomcat服务器
		  	ExcelUtilsPOI.exceldown(path, "因公出国政审名单.xls", request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: exportByUnit 
	 * @Description: 导出文件
	 * @param id
	 * @return: void
	 */
	@RequestMapping("/exportAbroad")
	public void exportAbroad(String id,HttpServletRequest request,HttpServletResponse response) {
		try {
			AbroadYearPlan abroadYearPlan = abroadYearPlanService.get(id);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);//设置起时间
			cal.add(Calendar.YEAR, 3);//增加
			
			abroadYearPlan.setApproveStartDate(date);//有效期起始时间
			abroadYearPlan.setApproveEndDate(cal.getTime());//有效期结束时间
			abroadYearPlanService.update(abroadYearPlan);
			
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from AbroadPlan where removed=:removed and status>=:status and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("status", AbroadPlan.STATUS_ABROAD_PLAN_PASS));
			listqueryparameter.add(new QueryParameter("yearPlan", id));
			hql.append( " order by createTime desc");
			List<AbroadPlan> list = abroadPlanService.findByHQL(hql.toString(), listqueryparameter);
			
			String firstName = list.get(0).getServant().getName();
			long num = list.size();
			String numCN = Number2CN.cvt(num,true);//数字转中文
			
			Map<String, Object> ygcgMap = new HashMap<String, Object>();
			ygcgMap.put("nyear", DateUtils.getYear());//年号
			ygcgMap.put("nnumCN", numCN);//人数
			ygcgMap.put("nname", firstName);//姓名
			ygcgMap.put("ncountry", abroadYearPlan.getCountry());//国家（地区）
			ygcgMap.put("nday", abroadYearPlan.getDay());//停留天数
			ygcgMap.put("nstartday", abroadYearPlan.getApproveStartDate());//有效期起始时间
			ygcgMap.put("nendday", abroadYearPlan.getApproveEndDate());//有效期结束时间
			ygcgMap.put("nnowday", date);//当前时间
			
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
			hql.append("from AbroadPlan where removed=:removed and status>=:status and yearPlan.id=:yearPlan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("status", AbroadPlan.STATUS_ABROAD_PLAN_PASS));
			listqueryparameter.add(new QueryParameter("yearPlan", id));
			hql.append( " order by createTime desc");
			List<AbroadPlan> list = abroadPlanService.findByHQL(hql.toString(), listqueryparameter);
			
			if(list==null||list.size()==0){
				throw new BusinessException("该因公出国计划暂无完成审批的计划！");
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
