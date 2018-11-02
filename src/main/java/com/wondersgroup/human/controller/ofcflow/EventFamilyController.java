/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EventFamilyController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 流程信息 家庭成员 控制器
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
import com.wondersgroup.human.bo.ofcflow.EventFamily;
import com.wondersgroup.human.service.ofcflow.EventFamilyService;
import com.wondersgroup.human.vo.ofcflow.EventFamilyVO;

/** 
 * @ClassName: EventFamilyController 
 * @Description: 流程信息 家庭成员 控制器
 * @author: tzy
 * @date: 2018年7月31日 下午2:09:57
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/eventFamily")
public class EventFamilyController extends GenericController{
	
	/**
	 * 家庭成员临时信息 编辑页面
	 */
	private final static String EVENT_Family_EDIT = "models/ofcflow/subset/eventFamilyEdit";
	
	@Autowired
	private EventFamilyService eventFamilyService;
	
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
			EventFamily Family = eventFamilyService.load(id);
			model.addAttribute("e", Family);
		}else{
			Map<String,String> map = new HashMap<>();
			map.put("personName", name);
			map.put("cardNo", cardNo);
			model.addAttribute("e", map);
		}
		return EVENT_Family_EDIT;
	}
	/**
	 * @Title: list 
	 * @Description: 	家庭成员列表查询
	 * @param name		根据姓名和身份证号匹配
	 * @param cardNo	根据姓名和身份证号匹配
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<EventFamilyVO>
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Page<EventFamilyVO> list(String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from EventFamily where removed=:removed and personName=:name and cardNo=:cardNo ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("name", name);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("cardNo", cardNo);
		listqueryparameter.add(queryParameteritem);
		hql.append( " order by createTime desc");
		
		Page<EventFamilyVO> pageInfo = eventFamilyService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 	家庭成员信息保存功能
	 * @param temp		家庭成员信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(EventFamily temp){
		AjaxResult result = new AjaxResult(true);
		try {
			DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
			if(StringUtils.isNotBlank(temp.getId())){//更新
				EventFamily Family = eventFamilyService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, Family);
				eventFamilyService.saveOrUpdate(Family);//保存
			}else{//新增
				temp.setId(null);
				eventFamilyService.saveOrUpdate(temp);
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
	 * @Description: 	家庭成员信息删除功能
	 * @param temp		家庭成员信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			EventFamily Family = eventFamilyService.get(id);
			eventFamilyService.delete(Family);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
