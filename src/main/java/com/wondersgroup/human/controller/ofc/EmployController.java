/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EmployController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-职务 控制器
 * 创建人: tzy
 * 创建时间: 2018年5月30日 下午5:39:59
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月30日 下午5:39:59
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.ofc.Employ;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.service.ofc.EmployService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.EmployVO;

/**
 * @ClassName: EmployController
 * @Description: 人员信息子表-录用 控制器
 * @author: tzy
 * @date: 2018年5月30日 下午5:39:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/ofc/employ")
@Controller
public class EmployController extends GenericController {
	
	/**
	 * 录用编辑层
	 */
	private static final String VIEW_EMPLOY_EDIT = "models/ofc/infoMainten/employEdit";
	
	@Autowired
	EmployService employService;
	
	@Autowired
	ServantService servantService;
	
	@RequestMapping("/edit")
	public String employEdit(String servantId, String employId, Model model) {
		
		if (StringUtils.isNoneBlank(employId)) {
			Employ employ = employService.get(employId);
			model.addAttribute("employ", employ);
			model.addAttribute("servant", employ.getServant());
		} else {
			Servant servant = servantService.get(servantId);
			model.addAttribute("servant", servant);
		}
		return VIEW_EMPLOY_EDIT;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 在职人员信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<PostVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<EmployVO> pageList(String servantId, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("servant.id", Operator.EQ, servantId, "");
		filter.add(p);
		Page<EmployVO> pageInfo = employService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 录用信息保存功能
	 * @param temp 录用信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(Employ temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				Employ employ = employService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, employ);
				DictUtils.operationCodeInfo(employ);// 将CodeInfo中id为空的属性 设置为null
				employService.saveOrUpdate(employ);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				employService.saveOrUpdate(temp);
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
	 * @Description: 录用信息删除功能
	 * @param temp 录用信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			Employ employ = employService.get(id);
			employService.delete(employ);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
