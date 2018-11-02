/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: JobLevelController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-职级 控制器
 * 创建人: jiang
 * 创建时间: 2018年6月12日10:45:45
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日10:45:48
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
import com.wondersgroup.human.bo.pubinst.PtJobLevel;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtJobLevelService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtJobLevelVO;

/**
 * @ClassName: JobLevelController
 * @Description: 人员信息子表-职级 控制器
 * @author: jiang
 * @date: 2018年5月30日 下午5:39:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/pubinst/jobLevel")
@Controller
public class PtJobLevelController extends GenericController {
	
	/**
	 * 职级编辑层
	 */
	private static final String VIEW_JOBLEVEL_EDIT = "models/publicInstitution/pubinstJobLevelEdit";
	
	@Autowired
	PtJobLevelService jobLevelService;
	
	@Autowired
	PublicInstitutionService publicInstitutionService;
	
	@RequestMapping("/edit")
	public String jobLevelEdit(String Id, String jobLevelId, Model model) {
		
		if (StringUtils.isNoneBlank(jobLevelId)) {
			PtJobLevel jobLevel = jobLevelService.get(jobLevelId);
			model.addAttribute("jobLevel", jobLevel);
			model.addAttribute("pubinst", jobLevel.getPublicInstitution());
		} else {
			PublicInstitution servant = publicInstitutionService.get(Id);
			model.addAttribute("pubinst", servant);
		}
		return VIEW_JOBLEVEL_EDIT;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 职级信息列表
	 * @param publicInstitutionService 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<PostVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtJobLevelVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<PtJobLevelVO> pageInfo = jobLevelService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 职级信息保存功能
	 * @param temp 职级信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtJobLevel temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				PtJobLevel jobLevel = jobLevelService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, jobLevel);
				DictUtils.operationCodeInfo(jobLevel);// 将CodeInfo中id为空的属性 设置为null
				jobLevelService.saveOrUpdate(jobLevel);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				jobLevelService.saveOrUpdate(temp);
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
	 * @Description: 职级信息删除功能
	 * @param temp 职级信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			PtJobLevel jobLevel = jobLevelService.get(id);
			jobLevelService.delete(jobLevel);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
