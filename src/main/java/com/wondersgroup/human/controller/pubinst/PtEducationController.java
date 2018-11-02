/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EducationController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-学历 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月15日14:35:02
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月15日14:35:06
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.pubinst;

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
import com.wondersgroup.human.bo.pubinst.PtEducation;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtEducationService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtEducationVO;

/**
 * @ClassName: EducationController
 * @Description: 人员信息子表-学历 控制器
 * @author: jiang
 * @date: 2018年8月15日14:35:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/pubinst/education")
@Controller
public class PtEducationController extends GenericController {
	
	/**
	 * 学历编辑层
	 */
	private static final String VIEW_EDUCATION_EDIT = "models/publicInstitution/pubinstEducationEdit";
	
	@Autowired
	PtEducationService educationService;
	
	@Autowired
	PublicInstitutionService p;
	
	@RequestMapping("/edit")
	public String educationEdit(String Id, String educationId, Model model) {
		
		if (StringUtils.isNoneBlank(educationId)) {
			PtEducation education = educationService.get(educationId);
			model.addAttribute("education", education);
			model.addAttribute("pubinst", education.getPublicInstitution());
		} else {
			PublicInstitution servant = p.get(Id);
			model.addAttribute("pubinst", servant);
		}
		return VIEW_EDUCATION_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 学历信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<PostVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtEducationVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<PtEducationVO> pageInfo = educationService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 学历信息保存功能
	 * @param temp 学历信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtEducation temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				PtEducation education = educationService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, education);
				DictUtils.operationCodeInfo(education);// 将CodeInfo中id为空的属性 设置为null
				educationService.saveOrUpdate(education);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				educationService.saveOrUpdate(temp);
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
	 * @Description: 学历信息删除功能
	 * @param temp 学历信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			PtEducation education = educationService.get(id);
			educationService.delete(education);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
