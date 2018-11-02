/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: StudyController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-学习（培训、进修） 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月21日15:18:03
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月21日15:18:06
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
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofc.Study;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofc.StudyService;
import com.wondersgroup.human.vo.ofc.StudyVO;

/**
 * @ClassName: StudyController
 * @Description: 人员信息子表-学习（培训、进修） 控制器
 * @author: jiang
 * @date: 2018年8月21日15:18:11
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/ofc/study")
@Controller
public class StudyController extends GenericController {
	
	/**
	 * 学习（培训、进修）编辑层
	 */
	private static final String VIEW_STUDY_EDIT = "models/ofc/infoMainten/studyEdit";
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	ServantService servantService;
	
	@RequestMapping("/edit")
	public String studyEdit(String servantId, String studyId, Model model) {
		
		if (StringUtils.isNoneBlank(studyId)) {
			Study study = studyService.get(studyId);
			model.addAttribute("study", study);
			model.addAttribute("servant", study.getServant());
		} else {
			Servant servant = servantService.get(servantId);
			model.addAttribute("servant", servant);
		}
		return VIEW_STUDY_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 学习（培训、进修）信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<PostVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<StudyVO> pageList(String servantId, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("servant.id", Operator.EQ, servantId, "");
		filter.add(p);
		Page<StudyVO> pageInfo = studyService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 学习（培训、进修）信息保存功能
	 * @param temp 学习（培训、进修）信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(Study temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				Study study = studyService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, study);
				DictUtils.operationCodeInfo(study);// 将CodeInfo中id为空的属性 设置为null
				studyService.saveOrUpdate(study);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				studyService.saveOrUpdate(temp);
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
	 * @Description: 学习（培训、进修）信息删除功能
	 * @param temp 学习（培训、进修）信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			Study study = studyService.get(id);
			studyService.delete(study);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
