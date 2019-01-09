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
import com.wondersgroup.human.bo.pubinst.PtEducation;
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.bo.socialworker.SrEducation;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwEducationService;
import com.wondersgroup.human.vo.socialworker.SwEducationVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;
@Controller
@RequestMapping("/social/education")
public class SwEducationController extends GenericController{
	
	/*
	 * 学历编辑
	 */
 
	private static final String VIEW_EDUCATION_EDIT = "models/socialworker/socialEducationEdit";

	
	
	@Autowired
	private SocialWorkerService socialWorkerService;
	
	@Autowired
	private SwEducationService swEducationService;
	
	@RequestMapping("/edit")
	public String educationEdit(String Id, String educationId, Model model) {
		
		if (StringUtils.isNoneBlank(educationId)) {
			SrEducation education = swEducationService.get(educationId);
			model.addAttribute("education", education);
			model.addAttribute("social", education.getSocialWorker());
		} else {
			SocialWorker social = socialWorkerService.get(Id);
			model.addAttribute("social", social);
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
	@Log(title = "查询学历信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<SwEducationVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("socialWorker.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<SwEducationVO> pageInfo = swEducationService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 学历信息保存功能
	 * @param temp 学历信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑学历信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(SrEducation temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				SrEducation education = swEducationService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, education);
				DictUtils.operationCodeInfo(education);// 将CodeInfo中id为空的属性 设置为null
				swEducationService.saveOrUpdate(education);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				swEducationService.saveOrUpdate(temp);
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
	@Log(title = "删除学历信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			SrEducation education = swEducationService.get(id);
			swEducationService.delete(education);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	
	
	
	
	
	
}
