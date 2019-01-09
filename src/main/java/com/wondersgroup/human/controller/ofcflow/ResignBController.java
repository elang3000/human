/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignBController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月20日 上午10:22:18 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月20日 上午10:22:18 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.ResignPeople;
import com.wondersgroup.human.bo.ofcflow.ResignPlan;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.ResignPeopleService;
import com.wondersgroup.human.service.ofcflow.ResignPlanService;
import com.wondersgroup.human.vo.ofcflow.ResignPeopleVO;
import com.wondersgroup.human.vo.ofcflow.ResignPlanVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: ResignBController 
 * @Description: 辞职控制层
 * @author: lihao
 * @date: 2018年12月20日 上午10:22:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/resignB")
public class ResignBController {

	private int num = 0 ;
		
	@Autowired
	ResignPlanService resignPlanService;
	
	@Autowired
	ResignPeopleService resignPeopleService;
	
	@Autowired
	ServantService servantService;
	
	@Autowired
	private FlowRecordService flowRecordService;

	// 页面路径--辞职批量列表页面
	private final String RESIGN_LIST = "models/ofcflow/resignB/index";
	
	// 页面路径--辞职批量编辑页面
	private final String RESIGN_EDIT = "models/ofcflow/resignB/editResign";
	
	// 页面路径--辞职编辑人员页面
	private final String RESIGN_PEOPLE_EDIT = "models/ofcflow/resignB/editResignPeople";
	
	// 页面路径--辞职人员列表页面
	private final String RESIGN_PEOPLE_LIST = "models/ofcflow/resignB/resignPeople";
	
	// 页面路径--辞职人员详情页面
	private final String RESIGN_DETAIL = "models/ofcflow/resignB/resignDetail";
	
	// 页面路径--辞职流程页面
	private final String RESIGN_FLOW = "models/ofcflow/resignB/resignFlow";
	
	// 页面路径--辞职批次详情页面
	private final String RESIGN_VIEW = "models/ofcflow/resignB/resignView";
	
	/**
	 * 辞职流程待办列表
	 */
	private final static String RESIGN_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	/**
	
	/**
	 * 辞职批次页面
	 * 
	 * @Title: index
	 * @Description: 辞职批次
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return RESIGN_LIST;
	}
	
	/**
	 * @Title: resignList
	 * @Description: 批次列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@Log(title = "查询辞职批次列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/resignList")
	public Page<ResignPlanVO> resignList(String resignName,Integer numLow,Integer numHigh, Integer limit, Integer page) {
		Page<ResignPlanVO> pageInfo = resignPlanService.resignList(resignName,numLow,numHigh, page, limit);
		return pageInfo;
	}
	
	/**
	 * 批量辞职编辑页面
	 * 
	 * @Title: editResign
	 * @Description: 批量辞职编辑页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editResign")
	public String editResign(String id, Model model) {
		
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("x", x);
		
		if(StringUtils.isNotBlank(id)){
			ResignPlan resignPlan = resignPlanService.get(id);
			model.addAttribute("resignPlan",resignPlan);
		}
		
		return RESIGN_EDIT;
	}
	
	/**
	 * @Title: resignPeopleList
	 * @Description: 批次内辞职人员列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@Log(title = "查询辞职批次人员列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/resignPeopleList")
	public Page<ResignPeopleVO> resignPeopleList(String planId, Integer limit, Integer page) {
		Page<ResignPeopleVO> pageInfo = resignPeopleService.resignPeopleList(planId, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: saveResign 
	 * @Description: 保存批次信息
	 * @param temp
	 * @return: AjaxResult
	 */
	@Log(title = "编辑辞职信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveResign")
	public AjaxResult saveResign(ResignPlan temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				num = resignPeopleService.getResignPeopleCountByPlanId(temp.getId()).size();
				temp.setResignNumber(num);
				ResignPlan resignPlan = resignPlanService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, resignPlan);
				DictUtils.operationCodeInfo(resignPlan);//将CodeInfo中id为空的属性 设置为null
				resignPlanService.saveOrUpdate(resignPlan);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				temp.setStatus(ResignPlan.RESIGN_EMPLOY_APPLYB);
				temp.setResignNumber(num);
				resignPlanService.saveOrUpdate(temp);
			}
			result.setData(temp.getId());
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
	 * @Title: deleteResign 
	 * @Description: 删除
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "删除辞职信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/deleteResign")
	public AjaxResult deleteResign(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			
			List<QueryParameter> listqueryparameter=new ArrayList<>();
			StringBuilder hql=new StringBuilder();
			hql.append("from ResignPeople where removed=:removed and plan.id=:plan ");
			QueryParameter queryParameteritem=new QueryParameter("removed", false);
			listqueryparameter.add(queryParameteritem);
			listqueryparameter.add(new QueryParameter("plan", id));
			List<ResignPeople> list = resignPeopleService.findByHQL(hql.toString(), listqueryparameter);
			for(ResignPeople rp:list){
				resignPeopleService.delete(rp);
			}
			
			ResignPlan resignPlan = resignPlanService.get(id);
			resignPlanService.delete(resignPlan);
			
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * @Title: savePeople 
	 * @Description: 转出添加人员
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "保存辞职人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/savePeople")
	public AjaxResult savePeople(ResignPlan resignPlan,String servantIds){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(servantIds)){
				throw new BusinessException("添加人员信息获取失败！");
			}
			DetachedCriteria criteria = DetachedCriteria.forClass(ResignPeople.class);//校验人员是否已存在表内
			criteria.add(Restrictions.in("servant.id", servantIds.split(",")));
			criteria.add(Restrictions.eq("removed", false));
			List<ResignPeople> list = resignPeopleService.findByCriteria(criteria);
			if(list!=null&&list.size()>0){
				throw new BusinessException("已有人员处于流程当中，请重新选择！");
			}
			resignPlanService.savePeople(resignPlan,servantIds);
			result.setData(resignPlan.getId());
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
	@Log(title = "删除辞职人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/deletePeople")
	public AjaxResult deletePeople(String ids){
		AjaxResult result = new AjaxResult(true);
		try {
			
			for(String s:ids.split(",")){
				ResignPeople resignPeople = resignPeopleService.get(s);
				resignPeopleService.delete(resignPeople);
			}
			
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	/**
	 * 批量辞职编辑页面
	 * @Title: editPeople
	 * @Description: 批量辞职人员编辑页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editPeople")
	public String editPeople(String id, Model model) {
		
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("x", x);
		
		if(StringUtils.isNotBlank(id)){
			ResignPeople resignPeople = resignPeopleService.get(id);
			model.addAttribute("servant",resignPeople.getServant());
			model.addAttribute("resignPeople",resignPeople);
		}
		
		return RESIGN_PEOPLE_EDIT;
	}
	
	/**
	 * @Title: saveResignPeople 
	 * @Description: 保存人员信息
	 * @param temp
	 * @return: AjaxResult
	 */
	@Log(title = "编辑辞职人员信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveResignPeople")
	public AjaxResult saveResignPeople(ResignPeople temp){
		AjaxResult result = new AjaxResult(true);
		try {
			ResignPeople resignPeople = resignPeopleService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, resignPeople);
			DictUtils.operationCodeInfo(resignPeople);//将CodeInfo中id为空的属性 设置为null
			resignPeopleService.saveOrUpdate(resignPeople);//保存
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
	 * @Title: operationFlow 
	 * @Description: 审批转出信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批辞职流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ResignPlan temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ResignPlan resignPlan = resignPlanService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, resignPlan);
				DictUtils.operationCodeInfo(resignPlan);//将CodeInfo中id为空的属性 设置为null
				resignPlanService.saveFlow(resignPlan,opinion,r);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				temp.setStatus(ResignPlan.RESIGN_EMPLOY_APPLYB);
				temp.setResignNumber(num);
				resignPlanService.saveFlow(temp,opinion,r);
			}
			result.setSuccess(true);
			result.setMessage("保存成功！");
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
		model.addAttribute("busType","ResignServant");
		return RESIGN_EMPLOYPLAN_FLOW;
	}
	
	/**
	 * 辞职人员页面
	 * 
	 * @Title: index
	 * @Description: 辞职人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/resignPeople/index")
	public String indexResignPeople() {
		return RESIGN_PEOPLE_LIST;
	}
	
	/**
	 * @Title: peopleList
	 * @Description: 辞职人员列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@Log(title = "查询辞职人员列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/peopleList")
	public Page<ResignPeopleVO> peopleList(ResignPeople resignPeople, Integer limit, Integer page) {
		Page<ResignPeopleVO> pageInfo = resignPeopleService.peopleList(resignPeople, page, limit);
		return pageInfo;
	}
	
	/**
	 * 辞职人员详情
	 * 
	 * @Title: ResignDetail
	 * @Description: 辞职人员详情
	 * @return
	 * @return: String
	 */
	@Log(title = "查询辞职人员信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/resignDetail")
	public String resignDetail(String id, Model model) {
		ResignPeople r = resignPeopleService.get(id);
		model.addAttribute("resignPeople", r);
		model.addAttribute("servant", r.getServant());
		model.addAttribute("resignPlan", r.getPlan());
		return RESIGN_DETAIL;
	}
	
	/**
	 * @Title: resignFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/resignFlow")
	public String resignFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		ResignPlan r = resignPlanService.get(flow.getBusId());
		model.addAttribute("resignPlan", r);
		
		String resignPeopleStr = "";
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(ResignPeople.class);
		detachedcriteria.add(Restrictions.eq("plan.id", flow.getBusId()));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		List<ResignPeople> list = resignPeopleService.findByCriteria(detachedcriteria);
		for(ResignPeople rp:list){//根据辞职人员表id查询存放附件表id让后以“，”形式传在页面，然后页面调用方法
			resignPeopleStr += rp.getId() + ",";
		}
		if(StringUtils.isNotBlank(resignPeopleStr)){
			model.addAttribute("resignPeopleStr", resignPeopleStr.substring(0,resignPeopleStr.length()-1));
		}
		return RESIGN_FLOW;
	}
	
	/**
	 * @Title: resignFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@Log(title = "查询辞职信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/resignView")
	public String resignView(Model model,String id) {
		ResignPlan r = resignPlanService.get(id);
		model.addAttribute("resignPlan", r);
		return RESIGN_VIEW;
	}
}
