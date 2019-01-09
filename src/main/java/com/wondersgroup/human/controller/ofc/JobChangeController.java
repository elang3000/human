/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: JobChangeController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-职务变动情况 控制器
 * 创建人: jiang
 * 创建时间: 2018年11月20日10:20:30
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年11月20日10:20:33
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
import com.wondersgroup.human.bo.ofc.JobChange;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.service.ofc.JobChangeService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.JobChangeVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: JobChangeController
 * @Description: 人员信息子表-职务变动情况 控制器
 * @author: jiang
 * @date: 2018年11月20日10:20:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/ofc/jobChange")
@Controller
public class JobChangeController extends GenericController {
	
	/**
	 * 职务变动情况编辑层
	 */
	private static final String VIEW_JOB_CHANGE_EDIT = "models/ofc/infoMainten/jobChangeEdit";
	
	@Autowired
	JobChangeService jobChangeService;
	
	@Autowired
	ServantService servantService;
	
	@RequestMapping("/edit")
	public String degreeEdit(String servantId, String jobChangeId, Model model) {
		
		if (StringUtils.isNoneBlank(jobChangeId)) {
			JobChange jobChange = jobChangeService.get(jobChangeId);
			model.addAttribute("jobChange", jobChange);
			model.addAttribute("servant", jobChange.getServant());
		} else {
			Servant servant = servantService.get(servantId);
			model.addAttribute("servant", servant);
		}
		return VIEW_JOB_CHANGE_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 职务变动情况信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<DegreeVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<JobChangeVO> pageList(String servantId, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("servant.id", Operator.EQ, servantId, "");
		filter.add(p);
		Page<JobChangeVO> pageInfo = jobChangeService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 职务变动情况信息保存功能
	 * @param temp 职务变动情况信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑职务变动信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(JobChange temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				JobChange jobChange = jobChangeService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, jobChange);
				DictUtils.operationCodeInfo(jobChange);// 将CodeInfo中id为空的属性 设置为null
				jobChangeService.saveOrUpdate(jobChange);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				jobChangeService.saveOrUpdate(temp);
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
	 * @Description: 职务变动情况信息删除功能
	 * @param temp 职务变动情况信息
	 * @return: AjaxResult
	 */
	@Log(title = "删除职务变动信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			JobChange jobChange = jobChangeService.get(id);
			jobChangeService.delete(jobChange);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
