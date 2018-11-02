/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: SystemParamController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.resource
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年8月22日 下午3:18:20
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年8月22日 下午3:18:20
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.system.controller.resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.resource.bo.SystemParam;
import com.wondersgroup.framework.resource.service.SystemParamService;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: SystemParamController
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年8月22日 下午3:18:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("system/param/")
public class SystemParamController extends GenericController {
	
	private final String SYSTEM_PARAM_QUERY_INDEX = "resource/querySystemParamIndex",
	        SYSTEM_PARAM_CREATE_INDEX = "resource/createSystemParamIndex",
	        SYSTEM_PARAM_EDIT_INDEX = "resource/editSystemParamIndex";
	
	@Autowired
	SystemParamService systemParamService;
	
	@RequestMapping("index")
	public String index() {
		
		return SYSTEM_PARAM_QUERY_INDEX;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Page<SystemParam> page(String code, String name, String type, Integer limit, Integer page) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SystemParam.class);
		if (StringUtils.isNotBlank(code)) {
			detachedCriteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(type)) {
			detachedCriteria.add(Restrictions.eq("type", type));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		return systemParamService.findByCriteria(detachedCriteria, page, limit);
	}
	
	@RequestMapping("create")
	public String create(Model model) {
		
		return SYSTEM_PARAM_CREATE_INDEX;
	}
	
	@RequestMapping("create/save")
	@ResponseBody
	public AjaxResult saveCreate(SystemParam param) {
		
		try {
			systemParamService.save(param);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("创建系统参数", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("edit")
	public String edit(String id, Model model) {
		
		SystemParam param = systemParamService.get(id);
		model.addAttribute("id", param.getId());
		model.addAttribute("code", param.getCode());
		model.addAttribute("name", param.getName());
		model.addAttribute("type", param.getType());
		model.addAttribute("value", param.getValue());
		return SYSTEM_PARAM_EDIT_INDEX;
	}
	
	@RequestMapping("edit/save")
	@ResponseBody
	public AjaxResult saveEdit(SystemParam param) {
		
		try {
			systemParamService.update(param);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("修改系统参数", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("remove")
	@ResponseBody
	public AjaxResult remove(String id) {
		
		try {
			SystemParam param = systemParamService.get(id);
			systemParamService.delete(param);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除系统参数", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
}
