/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: MenuResourceController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.menu
 * 描述: 系统菜单控制器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年8月3日 上午10:47:45
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.controller.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.menu.bo.MenuResource;
import com.wondersgroup.framework.menu.service.MenuService;
import com.wondersgroup.framework.menu.vo.MenuResourceVO;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.resource.service.AppNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: MenuResourceController
 * @Description: 系统菜单控制器
 * @author: Wonders-Rain
 * @date: 2018年8月3日 上午10:47:45
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("resource/menu")
public class MenuResourceController extends GenericController {
	
	private static String RESOURCE_MENU_INDEX = "resource/queryMenuResourceIndex",
	        RESOURCE_MENU_CREATE_INDEX = "resource/createMenuResourceIndex",
	        RESOURCE_MENU_EDIT_INDEX = "resource/editMenuResourceIndex",
	        RESOURCE_MENU_ICON_INDEX = "resource/queryMenuResourceIconIndex";
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	AppNodeService appNodeService;
	
	@RequestMapping("/index")
	public String menuResoruceIndex(String id, Model model) {
		
		AppNode appNode = appNodeService.get(id);
		MenuResource menuResource = menuService.getMenuResourceByCode(appNode.getRootMenuCode());
		model.addAttribute("appId", id);
		model.addAttribute("rootMenuId", menuResource.getId());
		return RESOURCE_MENU_INDEX;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<HashMap<String, Object>> queryMenuResource(String appId, String id) {
		
		List<MenuResource> menuResources = new ArrayList<MenuResource>();
		if (StringUtils.isNotBlank(id)) {
			MenuResource parentMenu = menuService.getMenuResourceById(id);
			
			List<MenuResource> temp = Arrays
			        .asList(menuService.getAuthMenusByParentMenu(parentMenu, SecurityUtils.getUserId()));
			if (!temp.isEmpty()) {
				menuResources.addAll(temp);
			}
		} else {
			if (StringUtils.isNotBlank(appId)) {
				AppNode appNode = appNodeService.get(appId);
				MenuResource menuResource = menuService.getMenuResourceByCode(appNode.getRootMenuCode());
				menuResources.add(menuResource);
			} else {
				menuResources.addAll(Arrays.asList(menuService.getTopMenuResources()));
			}
		}
		List<HashMap<String, Object>> treeData = new ArrayList<HashMap<String, Object>>();
		for (MenuResource menu : menuResources) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", menu.getId());
			map.put("name", menu.getResourceName());
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
	public String queryMenuResourceById(String id, Model model) {
		
		MenuResource menu = null, parentMenu = null;
		if (StringUtils.isNotBlank(id)) {
			parentMenu = menuService.getParentResourceById(id);
			menu = menuService.getMenuResourceById(id);
		} else {
			menu = new MenuResource();
		}
		MenuResourceVO vo = menu.toViewObject();
		if (parentMenu != null) {
			vo.setParentId(parentMenu.getId());
		}
		model.addAttribute("menu", vo);
		return RESOURCE_MENU_EDIT_INDEX;
	}
	
	@RequestMapping("/create")
	public String createMenuResource(String id, Model model) {
		
		MenuResource menu = menuService.get(id);
		model.addAttribute("menu", menu.toViewObject());
		return RESOURCE_MENU_CREATE_INDEX;
	}
	
	@RequestMapping("/create/save")
	@ResponseBody
	public AjaxResult saveCreateMenuResource(MenuResourceVO param) {
		
		try {
			
			MenuResource parentMenu = menuService.get(param.getParentId());
			MenuResource menu = menuService.getMenuResourceByCode(param.getCode());
			if (menu != null) { return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, "菜单代码已存在，请重新输入！"); }
			MenuResource menuResource = new MenuResource();
			menuResource.setCode(param.getCode());
			menuResource.setAppNodeId(parentMenu.getAppNodeId());
			menuResource.setResourceName(param.getResourceName());
			menuResource.setIcon(param.getIcon());
			menuResource.setCls(param.getCls());
			menuResource.setLinkPath(param.getLinkPath());
			menuResource.setTarget(param.getTarget());
			menuResource.setHandler(param.getHandler());
			menuResource.setDescription(param.getDescription());
			menuResource.setDisplayOrder(param.getDisplayOrder());
			menuResource.setParentResource(parentMenu);
			menuService.createMenuResource(menuResource);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("新增菜单", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/edit/save")
	@ResponseBody
	public AjaxResult saveMenuResource(MenuResourceVO param) {
		
		try {
			MenuResource menu = menuService.get(param.getId());
			BeanUtils.copyPropertiesIgnoreNull(param, menu);
			menuService.update(menu);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("编辑菜单", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("remove/save")
	@ResponseBody
	public AjaxResult removeMenuResource(String id) {
		
		try {
			menuService.deleteMenuResourceById(id);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("编辑菜单", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/icon")
	public String iconIndex(String id, Model model) {
		
		if (StringUtils.isNotBlank(id)) {
			MenuResource menu = menuService.get(id);
			model.addAttribute("icon", menu.getIcon());
			model.addAttribute("id", id);
		}
		return RESOURCE_MENU_ICON_INDEX;
	}
	
	@RequestMapping("/icon/save")
	@ResponseBody
	public AjaxResult saveIcon(String id, String icon) {
		
		try {
			if (StringUtils.isNotBlank(id)) {
				MenuResource menu = menuService.get(id);
				menu.setIcon(icon);
				menuService.update(menu);
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"),
				        menu.toViewObject());
			} else {
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"), icon);
			}
		} catch (Exception ex) {
			logger.error("编辑菜单", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
}
