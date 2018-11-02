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
import com.wondersgroup.human.bo.socialworker.SrExperience;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwExperienceService;
import com.wondersgroup.human.vo.socialworker.SwExperienceVO;

/**
 * @ClassName: ExperienceController
 * @Description: 人员信息子表-简历（工作经历） 控制器
 * @author: jiang
 * @date: 2018年8月22日14:15:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/social/experience")
@Controller
public class SwExperienceController extends GenericController {
	
	/**
	 * 简历（工作经历）编辑层
	 */
	private static final String VIEW_EXPERIENCE_EDIT = "models/socialworker/socialExperienceEdit";
	
	@Autowired
	SwExperienceService swexperienceService;
	
	@Autowired
	private SocialWorkerService socialWorkerService;
	
	@RequestMapping("/edit")
	public String experienceEditSw(String Id, String experienceId, Model model) {
		
		if (StringUtils.isNoneBlank(experienceId)) {
			SrExperience experience = swexperienceService.get(experienceId);
			model.addAttribute("experience", experience);
			model.addAttribute("pubinst", experience.getSocialWorker());
		} else {
			SocialWorker servant = socialWorkerService.get(Id);
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
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<SwExperienceVO> pageListSw(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("socialWorker.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<SwExperienceVO> pageInfo = swexperienceService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 简历（工作经历）信息保存功能
	 * @param temp 简历（工作经历）信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult saveSw(SrExperience temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				SrExperience experience = swexperienceService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, experience);
				DictUtils.operationCodeInfo(experience);// 将CodeInfo中id为空的属性 设置为null
				swexperienceService.saveOrUpdate(experience);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				swexperienceService.saveOrUpdate(temp);
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
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult deleteSw(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			SrExperience experience = swexperienceService.get(id);
			swexperienceService.delete(experience);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
