/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EventPostController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 流程信息 职务 临时表 控制器
 * 创建人: tzy   
 * 创建时间: 2018年7月31日 下午2:09:57 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月31日 下午2:09:57 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.ofcflow.EventPost;
import com.wondersgroup.human.service.ofcflow.EventPostService;
import com.wondersgroup.human.vo.ofcflow.EventPostVO;

/** 
 * @ClassName: EventPostController 
 * @Description: 流程信息 职务 临时表 控制器
 * @author: tzy
 * @date: 2018年7月31日 下午2:09:57
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/eventPost")
public class EventPostController extends GenericController{
	
	/**
	 * 职务临时信息 编辑页面
	 */
	private final static String EVENT_POST_EDIT = "models/ofcflow/subset/eventPostEdit";
	
	@Autowired
	private EventPostService eventPostService;
	
	/**
	 * @Title: edit 
	 * @Description: 进入编辑页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String id,String name,String cardNo, Model model) {
		if(StringUtils.isNotBlank(id)){
			EventPost post = eventPostService.load(id);
			model.addAttribute("post", post);
		}else{
			Map<String,String> map = new HashMap<>();
			map.put("personName", name);
			map.put("cardNo", cardNo);
			model.addAttribute("post", map);
		}
		return EVENT_POST_EDIT;
	}
	/**
	 * @Title: list 
	 * @Description: 	职务列表查询
	 * @param name		根据姓名和身份证号匹配
	 * @param cardNo	根据姓名和身份证号匹配
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<EventPostVO>
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Page<EventPostVO> list(String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from EventPost where removed=:removed and personName=:name and cardNo=:cardNo ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("name", name);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("cardNo", cardNo);
		listqueryparameter.add(queryParameteritem);
		hql.append( " order by createTime desc");
		
		Page<EventPostVO> pageInfo = eventPostService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 	职务信息保存功能
	 * @param temp		职务信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(EventPost temp){
		AjaxResult result = new AjaxResult(true);
		try {
			DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
			if(StringUtils.isNotBlank(temp.getId())){//更新
				EventPost post = eventPostService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				eventPostService.saveOrUpdate(post);//保存
			}else{//新增
				temp.setId(null);
				eventPostService.saveOrUpdate(temp);
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
	 * @Description: 	职务信息删除功能
	 * @param temp		职务信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			EventPost post = eventPostService.get(id);
			eventPostService.delete(post);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
