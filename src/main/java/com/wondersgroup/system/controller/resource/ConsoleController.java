/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ConsoleController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.resource
 * 描述: 应用系统功能控制器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年8月21日 上午11:18:59
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.controller.resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.console.bo.FrameWorkResource;
import com.wondersgroup.framework.console.service.FrameWorkService;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.resource.service.AppNodeService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: ConsoleController
 * @Description: 应用系统功能控制器
 * @author: Wonders-Rain
 * @date: 2018年8月21日 上午11:18:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("resource/console")
public class ConsoleController extends GenericController {
	
	private final static String RESOURCE_CONSOLE_INDEX = "resource/queryFrameWorkIndex",
	        RESOURCE_CONSOLE_CREATE_INDEX = "resource/createFrameWorkIndex",
	        RESOURCE_CONSOLE_EDIT_INDEX = "resource/editFrameWorkIndex";
	
	@Autowired
	AppNodeService appNodeService;
	
	@Autowired
	FrameWorkService frameWorkService;
	
	@RequestMapping("/index")
	public String menuResoruceIndex(String id, Model model) {
		
		model.addAttribute("appId", id);
		return RESOURCE_CONSOLE_INDEX;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<HashMap<String, Object>> queryFrameWorkResource(String appId, String id) {
		
		List<FrameWorkResource> resources = frameWorkService.getFrameWorkResourceByParentId(appId, id, true);
		List<HashMap<String, Object>> treeData = new ArrayList<HashMap<String, Object>>();
		for (FrameWorkResource resource : resources) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", resource.getId());
			map.put("name", resource.getResourceName());
			if (StringUtils.isNotBlank(id)) {
				map.put("parentId", id);
			} else {
				map.put("parentId", null);
			}
			map.put("appId", appId);
			treeData.add(map);
		}
		return treeData;
	}
	
	@RequestMapping("/load")
	public String queryFrameWorkResourceById(String id, Model model) {
		
		FrameWorkResource resource = null;
		if (StringUtils.isNotBlank(id)) {
			resource = frameWorkService.getFrameWorkResourceById(id);
		} else {
			resource = new FrameWorkResource();
		}
		model.addAttribute("resource", resource);
		return RESOURCE_CONSOLE_EDIT_INDEX;
	}
	
	@RequestMapping("/create")
	public String createFrameWorkInde(String id, Model model) {
		
		FrameWorkResource resource = frameWorkService.get(id);
		model.addAttribute("parent", resource);
		return RESOURCE_CONSOLE_CREATE_INDEX;
	}
	
	@RequestMapping("/create/save")
	@ResponseBody
	public AjaxResult saveCreateFrameWorkIndex(FrameWorkResource resource, String parentId) {
		
		try {
			FrameWorkResource parentResource = frameWorkService.get(parentId);
			resource.setParentResource(parentResource);
			frameWorkService.save(resource);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("新增系统功能时出现出错：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/edit/save")
	@ResponseBody
	public AjaxResult saveEditFrameWorkIndex(FrameWorkResource resource) {
		
		try {
			FrameWorkResource tResource = frameWorkService.get(resource.getId());
			tResource.setCode(resource.getCode());
			tResource.setResourceName(resource.getResourceName());
			tResource.setDescription(resource.getDescription());
			tResource.setExecuteOrder(resource.getExecuteOrder());
			frameWorkService.update(tResource);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("新增系统功能时出现出错：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/remove/save")
	@ResponseBody
	public AjaxResult saveEditFrameWorkIndex(String id) {
		
		try {
			FrameWorkResource resource = frameWorkService.get(id);
			frameWorkService.delete(resource);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("新增系统功能时出现出错：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
}
