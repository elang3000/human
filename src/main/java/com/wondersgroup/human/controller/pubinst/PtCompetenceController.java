/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: CompetenceController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-专业技术任职资格 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月20日17:11:46
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月20日17:11:49
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
import com.wondersgroup.human.bo.pubinst.PtCompetence;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtCompetenceService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtCompetenceVO;

/**
 * @ClassName: CompetenceController
 * @Description: 人员信息子表-专业技术任职资格 控制器
 * @author: jiang
 * @date: 2018年8月20日17:14:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/pubinst/competence")
@Controller
public class PtCompetenceController extends GenericController {
	
	/**
	 * 专业技术任职资格编辑层
	 */
	private static final String VIEW_COMPETENCE_EDIT = "models/publicInstitution/pubinstCompetenceEdit";
	
	@Autowired
	PtCompetenceService competenceService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	@RequestMapping("/edit")
	public String competenceEdit(String Id, String competenceId, Model model) {
		
		if (StringUtils.isNoneBlank(competenceId)) {
			PtCompetence competence = competenceService.get(competenceId);
			model.addAttribute("competence", competence);
			model.addAttribute("pubinst", competence.getPublicInstitution());
		} else {
			PublicInstitution pubinst = publicInstitutionService.get(Id);
			model.addAttribute("pubinst", pubinst);
		}
		return VIEW_COMPETENCE_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 专业技术资格信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<CompetenceVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtCompetenceVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<PtCompetenceVO> pageInfo = competenceService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 专业技术资格信息保存功能
	 * @param temp 专业技术资格信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtCompetence temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				PtCompetence competence = competenceService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, competence);
				DictUtils.operationCodeInfo(competence);// 将CodeInfo中id为空的属性 设置为null
				competenceService.saveOrUpdate(competence);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				competenceService.saveOrUpdate(temp);
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
	 * @Description: 专业技术资格信息删除功能
	 * @param temp 专业技术资格信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			PtCompetence competence = competenceService.get(id);
			competenceService.delete(competence);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
