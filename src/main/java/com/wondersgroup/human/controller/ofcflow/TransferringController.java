/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TransferringController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 选调交流控制器
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月21日 下午1:56:30 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月21日 下午1:56:30 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.ofcflow.TransferringExchanges;
import com.wondersgroup.human.bo.ofcflow.TransferringExchangesPost;
import com.wondersgroup.human.bo.ofcflow.TransferringSummery;
import com.wondersgroup.human.bo.ofcflow.TransferringSummeryInfo;
import com.wondersgroup.human.service.ofcflow.TransferringExchangesPostService;
import com.wondersgroup.human.service.ofcflow.TransferringExchangesService;
import com.wondersgroup.human.service.ofcflow.TransferringSummeryInfoService;
import com.wondersgroup.human.service.ofcflow.TransferringSummeryService;
import com.wondersgroup.human.vo.ofcflow.TransferringExchangesPostVO;
import com.wondersgroup.human.vo.ofcflow.TransferringExchangesVO;
import com.wondersgroup.human.vo.ofcflow.TransferringSummeryVO;

import net.sf.json.JSONArray;

/** 
 * @ClassName: TransferringController
 * @Description: 选调交流控制器
 * @author: wangzhanfei
 * @date: 2018年5月21日 下午1:56:30
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/transferring")
public class TransferringController extends GenericController{
	
	@Autowired
	private TransferringExchangesService exchangesService;
	@Autowired
	private TransferringExchangesPostService postService;
	@Autowired
	private TransferringSummeryService summeryService;
	@Autowired
	private TransferringSummeryInfoService summeryInfoService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private OrganNodeService organNodeService;
	/**
	 * 选调计划列表頁面
	 */
	private final static String RECRUIT_LIST_PAGE="models/ofcflow/transferring/index";
	/**
	 * 用人单位选择页面
	 */
	private final static String RECRUIT_EMPLOY_PRE_PLAN_PAGE="models/ofcflow/transferring/pre";
	/**
	 * 新增组织招录计划
	 */
	private final static String RECRUIT_EMPLOY_PLAN_PAGE="models/ofcflow/transferring/plan";
	/**
	 * 修改招录职位
	 */
	private final static String RECRUIT_POST_PAGE="models/ofcflow/transferring/post";
	/**
	 * 管理招录职位
	 */
	private final static String RECRUIT_ADD_POST_PAGE = "models/ofcflow/transferring/addPost";
	/**
	 * 招录计划汇总列表
	 */
	private final static String RECRUIT_SUMMARY_LIST_PAGE = "models/ofcflow/transferring/summaryList";
	/**
	 * 招录计划汇总详细页面
	 */
	private final static String RECRUIT_SUMMARY_PAGE = "models/ofcflow/transferring/summary";
	
	/**
	 * @Title: recruitList 
	 * @Description: 选调交流列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String recruitList(Model model){
		List<Map<String,String>> statusList = new ArrayList<>();
		for(int i=0;i<=8;i++){
			Map<String,String> map = new HashMap<>();
			map.put("key", i+"");
			if (i == 0) {
				map.put("value", "待职位上报");
			} else if (i == 1) {
				map.put("value", "待计划上报");
			} else if (i == 2) {
				map.put("value", "待提交审批");
			} else if (i == 3) {
				map.put("value", "审批通过");
			} else if (i == 4) {
				map.put("value", "审批不通过");
			} else if (i == 5) {
				map.put("value", "审批归档");
			} else if (i == 6) {
				map.put("value", "待汇总");
			} else if (i == 7) {
				map.put("value", "待修改");
			} else if (i == 8) {
				map.put("value", "待审批");
			}
			statusList.add(map);
		}
		model.addAttribute("statusList",JSONArray.fromObject(statusList).toString());
		return RECRUIT_LIST_PAGE;
	}
	
	/**
	 * @Title: preParams 
	 * @Description: 用人单位选择页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/pre")
	public String preParams(){
		return RECRUIT_EMPLOY_PRE_PLAN_PAGE;
	}
	
	/**
	 * @Title: plan 
	 * @Description: 新增选调交流计划
	 * @return
	 * @return: String
	 */
	@RequestMapping("/plan")
	public String plan(String id,Model model){
		TransferringExchanges t = null;
		if(StringUtils.isNotBlank(id)){
			t = exchangesService.load(id);
		}else{
			t = new TransferringExchanges();
			String userId = SecurityUtils.getUserId();//当前登录人信息
			OrganNode organNode = OrganCacheProvider.getOrganNodeInUserNode(userId);
			t.setRecruitOrgan(organNode);
			t.setEmployOrgan(organNode);
		}
		model.addAttribute("t", t);
		List<CodeInfo> recuritTypeList = dictableService.findCodeInfoByCodeType(DictTypeCodeContant.CODE_TYPE_EMPLOY_TYPE);
		List<Map<String,String>> recuritType = new ArrayList<>();
		for(CodeInfo c : recuritTypeList){
			Map<String,String> map = new HashMap<>();
			map.put("key", c.getId());
			map.put("value", c.getName());
			recuritType.add(map);
		}
		model.addAttribute("recuritType", JSONArray.fromObject(recuritType).toString());
		return RECRUIT_EMPLOY_PLAN_PAGE;
	}
	/**
	 * @Title: post 
	 * @Description: 修改选调职位
	 * @return
	 * @return: String
	 */
	@RequestMapping("/post")
	public String post(String id,Model model){
		TransferringExchanges t = exchangesService.load(id);
		model.addAttribute("t", t);
		return RECRUIT_POST_PAGE;
	}
	/**
	 * @Title: addPost 
	 * @Description: 管理选调职位
	 * @return
	 * @return: String
	 */
	@RequestMapping("/addPost")
	public String addPost(String id,String tId,Model model){
		if(StringUtils.isNotBlank(id)){
			TransferringExchangesPost p = postService.load(id);
			model.addAttribute("p", p);
			model.addAttribute("t", p.getPlan());
		}else{
			TransferringExchanges t = exchangesService.load(tId);
			model.addAttribute("t",t);
		}
		return RECRUIT_ADD_POST_PAGE;
	}
	/**
	 * @Title: summaryList 
	 * @Description: 选调交流汇总列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/summaryList")
	public String summaryList(){
		return RECRUIT_SUMMARY_LIST_PAGE;
	}

	/**
	 * @Title: pageList 
	 * @Description: 选调计划数据分页列表
	 * @param status
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<TransferringExchangesVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<TransferringExchangesVO> pageList(String status,Integer limit,Integer page){
		List<Predicate> filter = new ArrayList<>();//查询条件
		if(StringUtils.isNotBlank(status)){
			Predicate p = new Predicate("status", Operator.EQ,Integer.parseInt(status), "");
			filter.add(p);
		}
		Page<TransferringExchangesVO> pageInfo = exchangesService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 保存选调计划信息
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(TransferringExchanges temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(temp.getEmployOrgan()==null||StringUtils.isBlank(temp.getEmployOrgan().getId())){
				temp.setEmployOrgan(null);//暂时设置为null，以免保存报错，因未获取到当前登录人所在单位信息
			}
			if(temp.getRecruitOrgan()==null||StringUtils.isBlank(temp.getRecruitOrgan().getId())){
				temp.setRecruitOrgan(organNodeService.load("e6d39af2-bd58-4e81-b709-abd74fe92276"));//暂时设置为null，以免保存报错，因未获取到当前登录人所在单位信息
			}
			if(StringUtils.isNotBlank(temp.getId())){//更新
				TransferringExchanges e = exchangesService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, e);
				DictUtils.operationCodeInfo(e);//将CodeInfo中id为空的属性 设置为null
				exchangesService.saveOrUpdate(e);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				exchangesService.save(temp);
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
	 * @Title: delete 
	 * @Description: 删除
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			TransferringExchanges t = exchangesService.load(id);
			exchangesService.delete(t);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	//职位信息
	/**
	 * @Title: pageList 
	 * @Description: 选调计划数据分页列表
	 * @param status
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<TransferringExchangesVO>
	 */
	@ResponseBody
	@RequestMapping("/postList")
	public Page<TransferringExchangesPostVO> postList(String planId,Integer limit,Integer page){
		List<Predicate> filter = new ArrayList<>();//查询条件
		if(StringUtils.isNotBlank(planId)){
			Predicate p = new Predicate("plan.id", Operator.EQ,planId, "");
			filter.add(p);
		}
		Page<TransferringExchangesPostVO> pageInfo = postService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	/**
	 * @Title: postSave 
	 * @Description: 保存职位信息
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/postSave")
	public AjaxResult postSave(TransferringExchangesPost temp){
		AjaxResult result = new AjaxResult(true);
		try {
			List<Predicate> filter = new ArrayList<>();//查询条件
			Predicate p = new Predicate("plan.id", Operator.EQ,temp.getPlan().getId(), "");
			filter.add(p);
			if(StringUtils.isNotBlank(temp.getId())){//排除修改的该条数据
				Predicate p2 = new Predicate("id", Operator.NE,temp.getId(), "");
				filter.add(p2);
			}
			List<TransferringExchangesPost> postList = postService.findByFilter(filter);
			int num = 0;
			for(TransferringExchangesPost post : postList){
				num+=post.getPlanEmployNum();//该选调计划下所有职位的计划人数相加
			}
			int sum = num+temp.getPlanEmployNum();//再加上本次保存数据的计划选调人数，判断是否超过  总计划选调人数
			if(sum>temp.getPlan().getPlanEmployNum()){
				throw new BusinessException("选调人数超出计划人数！");
			}
			DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
			if(StringUtils.isNotBlank(temp.getId())){//更新
				TransferringExchangesPost e = postService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, e);
				postService.saveOrUpdate(e);//保存
			}else{//新增
				postService.save(temp);
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
	 * @Title: postDelete 
	 * @Description: 职位删除
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/postDelete")
	public AjaxResult postDelete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			TransferringExchangesPost t = postService.load(id);
			postService.delete(t);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	//汇总
	/**
	 * @Title: summaryPageList 
	 * @Description: 选调交流汇总列表
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<TransferringSummeryVO>
	 */
	@ResponseBody
	@RequestMapping("/summaryPageList")
	public Page<TransferringSummeryVO> summaryPageList(Integer limit,Integer page){
		List<Predicate> filter = new ArrayList<>();//查询条件
		Page<TransferringSummeryVO> pageInfo = summeryService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: saveSt 
	 * @Description: 提交到 待汇总状态
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveSt")
	public AjaxResult saveSt(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			TransferringExchanges t = exchangesService.load(id);
			t.setStatus(TransferringExchanges.RECRUIT_EMPLOY_PLAN_STATE_SUMMARY);
			exchangesService.update(t);//修改为待汇总状态
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: summary 
	 * @Description: 提交汇总
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/summary")
	public AjaxResult summary(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(id)){
				throw new BusinessException("请选择汇总数据！");
			}
			exchangesService.summary(id,TransferringExchanges.RECRUIT_EMPLOY_PLAN_STATE_APPROVAL);//报送汇总
			result.setMessage("提交汇总成功！");
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
	 * @Title: summaryPage 
	 * @Description: 汇总详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/summaryPage")
	public String summaryPage(Model model,String id){
		TransferringSummery tra = summeryService.load(id);
		List<Predicate> filter = new ArrayList<>();//查询条件
		filter.add(new Predicate("summery.id", Operator.EQ,id, ""));//汇总信息
		List<TransferringSummeryInfo> info = summeryInfoService.findByFilter(filter);//具体的选调计划数据

		Map<String,String> types = new LinkedHashMap<>();//人数类型
		types.put("hd", "核定行政编制数");
		types.put("sy", "实有人数");
		types.put("jy", "计划减员人数");
		types.put("qb", "本年度缺编人数");
		List<CodeInfo> employType = new ArrayList<>();//编制类型
		CodeInfo sumCode = new CodeInfo();
		sumCode.setId("sum");
		sumCode.setName("总数");
		employType.add(sumCode);//添加一个总数
		employType.addAll(dictableService.findCodeInfoByCodeType(DictTypeCodeContant.CODE_TYPE_EMPLOY_TYPE));
		
		Map<String,Integer> number = new LinkedHashMap<>();//各编制类型 机构人数
		for(String s : types.keySet()){
			for(CodeInfo c : employType){
				number.put(s+"_"+c.getId(), 0);
			}
		}
		
		List<TransferringExchangesPost> summary = new ArrayList<>();
		Map<String,Integer> sum = new LinkedHashMap<>();//各编制类型 计划选调总数
		for(CodeInfo c : employType){
			sum.put("sum_"+c.getId(), 0);
		}
		for (TransferringSummeryInfo t : info) {
			TransferringExchanges ts = t.getPlan();
			
			sum.put("sum_"+ts.getRecuritType().getId(), sum.get("sum_"+ts.getRecuritType().getId())==null?0:sum.get("sum_"+ts.getRecuritType().getId())+ts.getPlanEmployNum());
			
			number.put("hd_"+ts.getRecuritType().getId(), number.get("hd_"+ts.getRecuritType().getId())==null?0:number.get("hd_"+ts.getRecuritType().getId())+ts.getAllowWeaveNum());//核定
			number.put("sy_"+ts.getRecuritType().getId(), number.get("sy_"+ts.getRecuritType().getId())==null?0:number.get("sy_"+ts.getRecuritType().getId())+ts.getRealNum());//实有
			number.put("jy_"+ts.getRecuritType().getId(), number.get("jy_"+ts.getRecuritType().getId())==null?0:number.get("jy_"+ts.getRecuritType().getId())+ts.getPlanCutNum());//减员
			number.put("qb_"+ts.getRecuritType().getId(), number.get("qb_"+ts.getRecuritType().getId())==null?0:number.get("qb_"+ts.getRecuritType().getId())+ts.getAllowWeaveNum());//缺编
			for(TransferringExchangesPost tt:ts.getPost()){
				summary.add(tt);
			}
		}
		//追加总数
		sum.put("sum_sum", tra.getPlanEmployNum());
		number.put("hd_sum", tra.getAllowWeaveNum());
		number.put("sy_sum", tra.getRealNum());
		number.put("jy_sum", tra.getPlanCutNum());
		number.put("qb_sum", tra.getAllowWeaveNum());
		
		//查询职位人数
		List<CodeInfo> empType = dictableService.findCodeInfoByCodeType(DictTypeCodeContant.CODE_TYPE_EMPLOY_PRO_TYPE);
		List<CodeInfo> eduType = dictableService.findCodeInfoByCodeType(DictTypeCodeContant.CODE_TYPE_EDUCATION);
		List<CodeInfo> degType = dictableService.findCodeInfoByCodeType(DictTypeCodeContant.CODE_TYPE_DEGREE);
		Map<String,Integer> type = new LinkedHashMap<>();
		type.put("专业类别",empType.size());
		type.put("学历",eduType.size());
		type.put("学位",degType.size());
		List<CodeInfo> postType = new ArrayList<>();//职位统计类型
		CodeInfo postCode = new CodeInfo();
		postCode.setId("post");
		postCode.setName("职位\\项目");
		postType.add(postCode);//添加职位信息
		postType.addAll(empType);//专业类别
		postType.addAll(eduType);//学历
		postType.addAll(degType);//学位
		
		List<Map<String,String>> postList = new ArrayList<>();//职位信息
		for(TransferringExchangesPost p:summary){//遍历职位，封装数据
			Map<String,String> posts = new LinkedHashMap<>();//所有职位信息赋初始值，前台遍历的时候数据才能一一对应
			for(CodeInfo c:postType){
				posts.put(c.getId(), "0");
			}
			if(p.getProfessional()!=null){
				posts.put(p.getProfessional().getId(),"1");//专业类别
			}
			if(p.getEducation()!=null){
				posts.put(p.getEducation().getId(),"1");//学历
			}
			if(p.getDegree()!=null){
				posts.put(p.getDegree().getId(),"1");//学位
			}
			posts.put("post", p.getName());//职务名称
			postList.add(posts);
		}
		
		model.addAttribute("postType", postType);
		model.addAttribute("type", type);
		model.addAttribute("postList", postList);
		model.addAttribute("types", types);
		model.addAttribute("employType", employType);
		model.addAttribute("summary", summary);
		model.addAttribute("tra", tra);
		model.addAttribute("sum", sum);
		model.addAttribute("number", number);
				
		return RECRUIT_SUMMARY_PAGE;
	}
}
