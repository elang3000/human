/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: AssessmentController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-考核 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月22日11:00:07
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月22日11:00:09
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
import com.wondersgroup.human.bo.pubinst.PtAssessment;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtAssessmentService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtAssessmentVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: AssessmentController
 * @Description: 人员信息子表-考核 控制器
 * @author: jiang
 * @date: 2018年8月22日11:00:02
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/pubinst/assessment")
@Controller
public class PtAssessmentController extends GenericController {
	
	/**
	 * 考核编辑层
	 */
	private static final String VIEW_ASSESSMENT_EDIT = "models/publicInstitution/pubinstAssessmentEdit";
	
	@Autowired
	PtAssessmentService assessmentService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	@RequestMapping("/edit")
	public String assessmentEdit(String Id, String assessmentId, Model model) {
		
		if (StringUtils.isNoneBlank(assessmentId)) {
			PtAssessment assessment = assessmentService.get(assessmentId);
			model.addAttribute("assessment", assessment);
			model.addAttribute("pubinst", assessment.getPublicInstitution());
		} else {
			PublicInstitution pubinst = publicInstitutionService.get(Id);
			model.addAttribute("pubinst", pubinst);
		}
		return VIEW_ASSESSMENT_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 考核信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<AssessmentVO>
	 */
	@Log(title = "查询信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtAssessmentVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<PtAssessmentVO> pageInfo = assessmentService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 考核信息保存功能
	 * @param temp 考核信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑考核信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtAssessment temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				PtAssessment assessment = assessmentService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, assessment);
				DictUtils.operationCodeInfo(assessment);// 将CodeInfo中id为空的属性 设置为null
				assessmentService.saveOrUpdate(assessment);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				assessmentService.saveOrUpdate(temp);
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
	 * @Description: 考核信息删除功能
	 * @param temp 考核信息
	 * @return: AjaxResult
	 */
	@Log(title = "删除考核信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			PtAssessment assessment = assessmentService.get(id);
			assessmentService.delete(assessment);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
