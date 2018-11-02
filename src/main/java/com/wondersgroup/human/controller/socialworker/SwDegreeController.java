/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DegreeController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-学位 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月16日14:44:26
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月16日14:44:29
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.socialworker;

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
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.bo.socialworker.SrDegree;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwDegreeService;
import com.wondersgroup.human.vo.socialworker.SwDegreeVO;

/**
 * @ClassName: DegreeController
 * @Description: 人员信息子表-学位 控制器
 * @author: jiang
 * @date: 2018年8月16日14:44:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/social/degree")
@Controller
public class SwDegreeController extends GenericController {
	
	/**
	 * 学位编辑层
	 */
	private static final String VIEW_DEGREE_EDIT = "models/socialworker/socialDegreeEdit";
	
	@Autowired
	SwDegreeService swdegreeService;
	
	@Autowired
	private SocialWorkerService socialWorkerService;
	
	@RequestMapping("/edit")
	public String degreeEdit(String Id, String degreeId, Model model) {
		
		if (StringUtils.isNoneBlank(degreeId)) {
			SrDegree degree = swdegreeService.get(degreeId);
			model.addAttribute("degree", degree);
			model.addAttribute("social", degree.getSocialWorker());
		} else {
			SocialWorker servant = socialWorkerService.get(Id);
			model.addAttribute("social", servant);
		}
		return VIEW_DEGREE_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 学位信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<DegreeVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<SwDegreeVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("socialWorker.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<SwDegreeVO> pageInfo = swdegreeService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 学位信息保存功能
	 * @param temp 学位信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(SrDegree temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				SrDegree degree = swdegreeService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, degree);
				DictUtils.operationCodeInfo(degree);// 将CodeInfo中id为空的属性 设置为null
				swdegreeService.saveOrUpdate(degree);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				swdegreeService.saveOrUpdate(temp);
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
	 * @Description: 学位信息删除功能
	 * @param temp 学位信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			SrDegree degree = swdegreeService.get(id);
			swdegreeService.delete(degree);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}