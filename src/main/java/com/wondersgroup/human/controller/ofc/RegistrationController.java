/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RegistrationController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-登记 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月22日15:56:43
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月22日15:56:47
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
import com.wondersgroup.human.bo.ofc.Registration;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.service.ofc.RegistrationService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.RegistrationVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: RegistrationController
 * @Description: 人员信息子表-登记 控制器
 * @author: jiang
 * @date: 2018年8月22日15:56:51
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/ofc/registration")
@Controller
public class RegistrationController extends GenericController {
	
	/**
	 * 登记编辑层
	 */
	private static final String VIEW_REGISTRATION_EDIT = "models/ofc/infoMainten/registrationEdit";
	
	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	ServantService servantService;
	
	@RequestMapping("/edit")
	public String registrationEdit(String servantId, String registrationId, Model model) {
		
		if (StringUtils.isNoneBlank(registrationId)) {
			Registration registration = registrationService.get(registrationId);
			model.addAttribute("registration", registration);
			model.addAttribute("servant", registration.getServant());
		} else {
			Servant servant = servantService.get(servantId);
			model.addAttribute("servant", servant);
		}
		return VIEW_REGISTRATION_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 登记信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<RegistrationVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<RegistrationVO> pageList(String servantId, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("servant.id", Operator.EQ, servantId, "");
		filter.add(p);
		Page<RegistrationVO> pageInfo = registrationService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 登记信息保存功能
	 * @param temp 登记信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑登记信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(Registration temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				Registration registration = registrationService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, registration);
				DictUtils.operationCodeInfo(registration);// 将CodeInfo中id为空的属性 设置为null
				registrationService.saveOrUpdate(registration);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				registrationService.saveOrUpdate(temp);
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
	 * @Description: 登记信息删除功能
	 * @param temp 登记信息
	 * @return: AjaxResult
	 */
	@Log(title = "删除登记信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			Registration registration = registrationService.get(id);
			registrationService.delete(registration);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
