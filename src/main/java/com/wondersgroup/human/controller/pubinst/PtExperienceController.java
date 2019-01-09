/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ExperienceController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-简历（工作经历） 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月22日14:15:40
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月22日14:15:43
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
import com.wondersgroup.human.bo.pubinst.PtExperience;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtExperienceService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtExperienceVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: ExperienceController
 * @Description: 人员信息子表-简历（工作经历） 控制器
 * @author: jiang
 * @date: 2018年8月22日14:15:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/pubinst/experience")
@Controller
public class PtExperienceController extends GenericController {
	
	/**
	 * 简历（工作经历）编辑层
	 */
	private static final String VIEW_EXPERIENCE_EDIT = "models/publicInstitution/pubinstExperienceEdit";
	
	@Autowired
	PtExperienceService experienceService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	@RequestMapping("/edit")
	public String experienceEdit(String Id, String experienceId, Model model) {
		
		if (StringUtils.isNoneBlank(experienceId)) {
			PtExperience experience = experienceService.get(experienceId);
			model.addAttribute("experience", experience);
			model.addAttribute("pubinst", experience.getPublicInstitution());
		} else {
			PublicInstitution servant = publicInstitutionService.get(Id);
			model.addAttribute("pubinst", servant);
		}
		return VIEW_EXPERIENCE_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 简历（工作经历）信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ExperienceVO>
	 */
	@Log(title = "查询简历(工作经历)信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtExperienceVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<PtExperienceVO> pageInfo = experienceService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 简历（工作经历）信息保存功能
	 * @param temp 简历（工作经历）信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑简历(工作经历)信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtExperience temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				PtExperience experience = experienceService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, experience);
				DictUtils.operationCodeInfo(experience);// 将CodeInfo中id为空的属性 设置为null
				experienceService.saveOrUpdate(experience);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				experienceService.saveOrUpdate(temp);
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
	 * @Description: 简历（工作经历）信息删除功能
	 * @param temp 简历（工作经历）信息
	 * @return: AjaxResult
	 */
	@Log(title = "删除简历信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			PtExperience experience = experienceService.get(id);
			experienceService.delete(experience);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
