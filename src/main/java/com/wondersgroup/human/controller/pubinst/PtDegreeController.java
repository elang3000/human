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
import com.wondersgroup.human.bo.pubinst.PtDegree;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtDegreeService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtDegreeVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: DegreeController
 * @Description: 人员信息子表-学位 控制器
 * @author: jiang
 * @date: 2018年8月16日14:44:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/pubinst/degree")
@Controller
public class PtDegreeController extends GenericController {
	
	/**
	 * 学位编辑层
	 */
	private static final String VIEW_DEGREE_EDIT = "models/publicInstitution/pubinstDegreeEdit";
	
	@Autowired
	PtDegreeService degreeService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	@RequestMapping("/edit")
	public String degreeEdit(String Id, String degreeId, Model model) {
		
		if (StringUtils.isNoneBlank(degreeId)) {
			PtDegree degree = degreeService.get(degreeId);
			model.addAttribute("degree", degree);
			model.addAttribute("pubinst", degree.getPublicInstitution());
		} else {
			PublicInstitution servant = publicInstitutionService.get(Id);
			model.addAttribute("pubinst", servant);
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
	@Log(title = "查询信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtDegreeVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<PtDegreeVO> pageInfo = degreeService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 学位信息保存功能
	 * @param temp 学位信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑学位信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtDegree temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				PtDegree degree = degreeService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, degree);
				DictUtils.operationCodeInfo(degree);// 将CodeInfo中id为空的属性 设置为null
				degreeService.saveOrUpdate(degree);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				degreeService.saveOrUpdate(temp);
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
	@Log(title = "删除学位信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			PtDegree degree = degreeService.get(id);
			degreeService.delete(degree);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
