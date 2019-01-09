/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainServantController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月13日 上午9:37:31 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月13日 上午9:37:31 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.ofcflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.TrainPeople;
import com.wondersgroup.human.bo.ofcflow.TrainServant;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.TrainPeopleService;
import com.wondersgroup.human.service.ofcflow.TrainServantService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.vo.ofcflow.TrainPeopleVO;
import com.wondersgroup.human.vo.ofcflow.TrainServantVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: TrainServantController 
 * @Description: 培训控制类
 * @author: lihao
 * @date: 2018年11月13日 上午9:37:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/trainServant")
public class TrainServantController {
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@Autowired
	ServantService servantService;
	
	@Autowired
	TrainServantService trainServantService;
	
	@Autowired
	TrainPeopleService trainPeopleService;
	
	// 页面路径--培训信息列表
	private final static String TRAIN_INDEX = "models/ofcflow/train/index";
	
	// 页面路径--培训信息编辑页面
	private final static String EDIT_TRAIN_PLAN = "models/ofcflow/train/editTrain";
	
	// 页面路径--培训信息详情页面
	private final static String TRAIN_VIEW = "models/ofcflow/train/trainView";
	
	// 页面路径--培训信息流程审批页面
	private final static String TRAIN_PLAN_FLOW = "models/ofcflow/train/trainFlow";
	
	// 页面路径--培训信息流程列表
	private final static String FLOW_LIST = "models/ofcflow/employPlan/flow";
	
	// 页面路径--培训信息流程审批页面
	private final static String PRE1 = "models/ofcflow/train/pre1";
	
	// 页面路径--培训信息流程审批页面
	private final static String PRE2 = "models/ofcflow/train/pre2";
	
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
	@Log(title = "查询培训信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<TrainServantVO> pageList(TrainServant trainServant, Integer limit, Integer page) {
		Page<TrainServantVO> pageInfo = trainServantService.pageList(trainServant, page, limit);
		return pageInfo;
	}
	
	/**
	 * 添加培训信息页面
	 * @Title: ResignList
	 * @Description: 培训信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editTrain")
	public String editTrain(String id,Model model) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNoneBlank(id)) {
			TrainServant trainServant = trainServantService.get(id);
			if(trainServant.getStartDate()!=null){
				model.addAttribute("start",sdf.format(trainServant.getStartDate()));
			}
			if(trainServant.getStartDate()!=null){
				model.addAttribute("end",sdf.format(trainServant.getEndDate()));
			}
			model.addAttribute("train", trainServant);
		}
		if(x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)){//如果x是人社局
			model.addAttribute("type", TrainServant.TRAIN_BY_RS);
		}else{
			model.addAttribute("type", TrainServant.TRAIN_NOT_BY_RS);
		}
		model.addAttribute("x",x);
		
		return EDIT_TRAIN_PLAN;
	}
	
	/**
	 * @Title: saveTrain
	 * @Description: 培训信息保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "编辑培训信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveTrain")
	public AjaxResult saveTrain(TrainServant temp,String orgId) {
		AjaxResult result = new AjaxResult(true);
		try {
			DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
			if (StringUtils.isBlank(temp.getId())) {
				temp.setStatus(TrainServant.STATUS_TRAIN_PLAN_STEP1);
				trainServantService.save(temp);// 保存
			} else {
				TrainServant trainServant = trainServantService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, trainServant);
				trainServantService.update(trainServant);// 保存
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
	 * @Description: 培训人员
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@Log(title = "查询培训人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageTrainServant")
	public Page<TrainPeopleVO> pageTrainServant(String id, Servant servant,Integer limit, Integer page) {
		Page<TrainPeopleVO> pageInfo = trainPeopleService.pageList(id,servant,page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: delTrainPerson
	 * @Description: 培训人员删除
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "删除培训人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delTrainPeople")
	public AjaxResult delTrainPeople(String ids) {
		AjaxResult result = new AjaxResult(true);
		try {
			String[] id = ids.split(",");
			for(String s :id){
				TrainPeople trainPeople = trainPeopleService.get(s);
				trainPeopleService.delete(trainPeople);
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
	 * 添加培训信息人员页面
	 * @Title: ResignList
	 * @Description: 培训信息列表
	 * @return
	 * @return: String
	 */
	@Log(title = "查询培训信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/trainView")
	public String trainView(String id,Model model) {
		if (StringUtils.isNoneBlank(id)) {
			TrainServant trainServant = trainServantService.get(id);
			model.addAttribute("train", trainServant);
		}
		return TRAIN_VIEW;
	}
	
	/**
	 * @Title: remove 
	 * @Description: 	培训信息删除
	 * @param id		
	 * @return: AjaxResult
	 */
	@Log(title = "删除培训信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/remove")
	public AjaxResult remove(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			TrainServant trainPlan = trainServantService.get(id);
			
			DetachedCriteria detachedcriteria = DetachedCriteria.forClass(TrainPeople.class);
			//根据培训信息id查询
			DetachedCriteria t = detachedcriteria.createAlias("train", "t");
			t.add(Restrictions.eq("t.id", id));
			
			detachedcriteria.add(Restrictions.eq("removed", false));
			List<TrainPeople> list = trainPeopleService.findByCriteria(detachedcriteria);
			
			trainServantService.delete(trainPlan);
			
			for(TrainPeople tp :list){
				trainPeopleService.delete(tp);
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
	 * @Title: operationPlan 
	 * @Description: 审批培训计划
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批培训流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationPlan")
	public AjaxResult operationPlan(TrainServant temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r)&&!FlowRecord.STOP.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			if(StringUtils.isNotBlank(temp.getId())){//更新
				TrainServant trainServant = trainServantService.load(temp.getId());
				DictUtils.operationCodeInfo(trainServant);//将CodeInfo中id为空的属性 设置为null
				BeanUtils.copyPropertiesIgnoreNull(temp, trainServant);
				trainServantService.savePlan(trainServant,opinion,r);
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				temp.setStatus(TrainServant.STATUS_TRAIN_PLAN_STEP1);
				trainServantService.savePlan(temp,opinion,r);
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
	 * @Title: trainFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/trainFlow")
	public String planFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		TrainServant trainServant = trainServantService.get(flow.getBusId());
		model.addAttribute("train", trainServant);
		if(TrainServant.STATUS_TRAIN_PLAN_STEP1==trainServant.getStatus()){//如果是待提交
			return EDIT_TRAIN_PLAN;
		}else{
			return TRAIN_PLAN_FLOW;
		}
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
	 * @Title: exportByPerson 
	 * @Description: 导出人员信息
	 * @param id
	 * @return: void
	 */
	@Log(title = "导出培训人员信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.EXPORT,
		     isSaveRequestData = true)
	@RequestMapping("/exportByPerson")
	public void exportByPerson(String start,String end,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<?> list = trainPeopleService.exportByPerson(start,end);
			
			Map<String,Object> params = new HashMap<>();//excel导出使用的参数
			params.put("date", start+"至"+end);
			List<String[]> dataList = new ArrayList<>();//excel中数据列表
			for(int i=0;i<list.size();i++){
				String[] arr = new String[5];
				Object[] objlist= (Object[]) list.get(i); 
				arr[0] = i+1+"";
				arr[1] = objlist[0].toString();
				arr[2] = objlist[1].toString();
				arr[3] = objlist[2].toString();
				arr[4] = objlist[3].toString();
				dataList.add(arr);
			}
			params.put("listDataList", dataList);
			String savePath = request.getSession().getServletContext().getRealPath("/");
			String templet = savePath+"\\static\\templates\\trainByPerson.xls";//模板路径
			String path = savePath+"\\static\\templates\\"+start+"至"+end+".xls";//生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet,path, 1,null);//替换模板数据 生成excel到tomcat服务器
		  	ExcelUtilsPOI.exceldown(path, "长宁区"+start+"至"+end+"培训考核人员统计表.xls", request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: exportByPerson 
	 * @Description: 导出单位信息
	 * @param id
	 * @return: void
	 */
	@Log(title = "导出培训单位信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.EXPORT,
		     isSaveRequestData = true)
	@RequestMapping("/exportByUnit")
	public void exportByUnit(String start,String end,Integer hour1,Integer hour2,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<?> list = trainPeopleService.exportByUnit(start,end,hour1,hour2);
			
			Map<String,Object> params = new HashMap<>();//excel导出使用的参数
			params.put("date", start+"至"+end);
			params.put("hour1", hour1);
			params.put("hour2", hour2);
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
			String templet = savePath+"\\static\\templates\\trainByUnit.xls";//模板路径
			String path = savePath+"\\static\\templates\\"+start+"至"+end+".xls";//生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet,path, 1,null);//替换模板数据 生成excel到tomcat服务器
		  	ExcelUtilsPOI.exceldown(path, "长宁区"+start+"至"+end+"培训考核单位统计表.xls", request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 培训信息列表
	 * @Title: index
	 * @Description: 培训信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/pre1")
	public String pre1() {
		return PRE1;
	}
	
	/**
	 * 培训信息列表
	 * @Title: index
	 * @Description: 培训信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/pre2")
	public String pre2() {
		return PRE2;
	}
	
	/**
	 * @Title: savePeople 
	 * @Description: 转出添加人员
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "保存培训人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/savePeople")
	public AjaxResult savePeople(TrainServant trainServant,String servantIds,String orgId){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(servantIds)){
				throw new BusinessException("添加人员信息获取失败！");
			}
			trainServantService.savePeople(trainServant,servantIds);
			result.setData(trainServant.getId());
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
}
