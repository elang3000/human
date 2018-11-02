/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProbationController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-试用 控制器
 * 创建人: jiang
 * 创建时间: 2018年6月11日15:55:49
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月11日15:55:56
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
import com.wondersgroup.human.bo.ofc.Probation;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.service.ofc.ProbationService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.ProbationVO;

/**
 * @ClassName: EmployController
 * @Description: 人员信息子表-试用 控制器
 * @author: jiang
 * @date: 2018年6月11日15:55:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/ofc/probation")
@Controller
public class ProbationController extends GenericController {
	
	/**
	 * 试用编辑层
	 */
	private static final String VIEW_PROBATION_EDIT = "models/ofc/infoMainten/probationEdit";
	
	@Autowired
	ProbationService probationService;
	
	@Autowired
	ServantService servantService;
	
	@RequestMapping("/edit")
	public String probationEdit(String servantId, String probationId, Model model) {
		
		if (StringUtils.isNoneBlank(probationId)) {
			Probation probation = probationService.get(probationId);
			model.addAttribute("probation", probation);
			model.addAttribute("servant", probation.getServant());
		} else {
			Servant servant = servantService.get(servantId);
			model.addAttribute("servant", servant);
		}
		return VIEW_PROBATION_EDIT;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 试用信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<PostVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<ProbationVO> pageList(String servantId, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("servant.id", Operator.EQ, servantId, "");
		filter.add(p);
		Page<ProbationVO> pageInfo = probationService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 试用信息保存功能
	 * @param temp 试用信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(Probation temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				Probation probation = probationService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, probation);
				DictUtils.operationCodeInfo(probation);// 将CodeInfo中id为空的属性 设置为null
				probationService.saveOrUpdate(probation);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				probationService.saveOrUpdate(temp);
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
	 * @Description: 试用信息删除功能
	 * @param temp 试用信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			Probation probation = probationService.get(id);
			probationService.delete(probation);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
