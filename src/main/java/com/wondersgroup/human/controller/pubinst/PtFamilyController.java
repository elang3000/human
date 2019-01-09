/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: FamilyController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-家庭 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月20日14:08:50
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月20日14:08:53
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
import com.wondersgroup.human.bo.pubinst.PtFamily;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtFamilyService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtFamilyVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: FamilyController
 * @Description: 人员信息子表-学位 控制器
 * @author: jiang
 * @date: 2018年8月16日14:44:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/pubinst/family")
@Controller
public class PtFamilyController extends GenericController {
	
	/**
	 * 家庭编辑层
	 */
	private static final String VIEW_FAMILY_EDIT = "models/publicInstitution/pubinstFamilyEdit";
	
	@Autowired
	PtFamilyService familyService;
	
	@Autowired
	PublicInstitutionService publicInstitutionService;
	
	@RequestMapping("/edit")
	public String familyEdit(String Id, String familyId, Model model) {
		
		if (StringUtils.isNoneBlank(familyId)) {
			PtFamily family = familyService.get(familyId);
			model.addAttribute("family", family);
			model.addAttribute("pubinst", family.getPublicInstitution());
		} else {
			PublicInstitution servant = publicInstitutionService.get(Id);
			model.addAttribute("pubinst", servant);
		}
		return VIEW_FAMILY_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 家庭信息列表
	 * @param Id 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<FamilyVO>
	 */
	@Log(title = "查询家庭信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtFamilyVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<PtFamilyVO> pageInfo = familyService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 家庭信息保存功能
	 * @param temp 学位信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑家庭信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtFamily temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				PtFamily family = familyService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, family);
				DictUtils.operationCodeInfo(family);// 将CodeInfo中id为空的属性 设置为null
				familyService.saveOrUpdate(family);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				familyService.saveOrUpdate(temp);
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
	 * @Description: 家庭信息删除功能
	 * @param temp 家庭信息
	 * @return: AjaxResult
	 */
	@Log(title = "删除家庭信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			PtFamily family = familyService.get(id);
			familyService.delete(family);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
