/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: YearCheckController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 年度考核控制器
 * 创建人: tzy   
 * 创建时间: 2018年5月17日 下午2:29:55 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月17日 下午2:29:55 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;

import net.sf.json.JSONArray;


/** 
 * @ClassName: YearCheckController
 * @Description: 年度考核控制器
 * @author: tzy
 * @date: 2018年5月17日 下午2:29:55
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofc/yearcheck")
public class YearCheckController extends GenericController {
	/**
	 * 考核年度列表
	 */
	private final String YEARCHECK_LIST="models/ofcflow/yearCheck/yearCheckList";
	
	/**
	 * 某一年度考核列表
	 */
	private final String CHECK_LIST="models/ofcflow/yearCheck/checkList";
	/**
	 * 人员考核详细信息
	 */
	private final String CHECK_DETAIL="models/ofcflow/yearCheck/checkDetail";
	
	/**
	 * @Title: list 
	 * @Description: 考核年度列表页面
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/list")
	public String list(Model model){
		List<Map<String,Object>> yearList = new ArrayList<>();
		Calendar date = Calendar.getInstance();
		int now = date.get(Calendar.YEAR);
		for(int i = 0;i<5;i++){
			Map<String,Object> map = new HashMap<>();
			map.put("value", now-i);
			map.put("key", now-i);
			yearList.add(map);
		}
		model.addAttribute("yearList", JSONArray.fromObject(yearList).toString());
		return YEARCHECK_LIST;
	}
	/**
	 * @Title: ajaxofclist 
	 * @Description: 考核年度列表数据加载
	 * @return
	 * @return: Page<Map<String,Object>>
	 */
	@ResponseBody
	@RequestMapping("/ajaxofclist")
	public Page<Map<String,Object>> ajaxofclist(){
		List<Map<String,Object>> list = new ArrayList<>();
		for(int i = 0 ;i<8;i++){
			Map<String,Object> map = new HashMap<>();
			map.put("id","111111111111"+ i);
			map.put("year","年份"+ i);
			map.put("danwei", "单位"+i);
			map.put("create", "创建人"+i);
			map.put("status", "状态"+i);
			list.add(map);
		}
		Page<Map<String,Object>> p = new Page<>();
		p.setTotalSize(8);
		p.setResult(list);
		
		return p;
	}
	/**
	 * @Title: checkList 
	 * @Description: 某一年度考核列表页面
	 * @param request
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/checkList")
	public String checkList(HttpServletRequest request,Model model){
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		return CHECK_LIST;
	}
	/**
	 * @Title: checkdatalist 
	 * @Description: 某一年度考核列表数据加载
	 * @param id
	 * @return
	 * @return: Page<Map<String,Object>>
	 */
	@ResponseBody
	@RequestMapping("/checkdatalist")
	public Page<Map<String,Object>> checkdatalist(String id){
		List<Map<String,Object>> list = new ArrayList<>();
		for(int i = 0 ;i<8;i++){
			Map<String,Object> map = new HashMap<>();
			map.put("id","111111111111"+ i);
			map.put("name","姓名"+ i);
			map.put("cardno", "5101212000121250"+i);
			map.put("unitname", "长宁区公务员局");
			map.put("status", "优秀");
			list.add(map);
		}
		Page<Map<String,Object>> p = new Page<>();
		p.setTotalSize(8);
		p.setResult(list);
		
		return p;
	}
	/**
	 * @Title: checkDetail 
	 * @Description: 人员详细考核信息
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/checkDetail")
	public String checkDetail(HttpServletRequest request,Model model,String id){
		
		return CHECK_DETAIL;
	}
}
