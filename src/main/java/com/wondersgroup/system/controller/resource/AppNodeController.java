/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: AppController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.app
 * 描述: 系统应用管理控制器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年6月20日 下午9:25:03
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.controller.resource;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;
import com.wondersgroup.framework.menu.bo.MenuResource;
import com.wondersgroup.framework.menu.service.MenuService;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.resource.service.AppNodeService;
import com.wondersgroup.framework.resource.vo.AppNodeViewModel;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.dto.UserDTO;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.security.vo.UserVO;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: AppController
 * @Description: 系统应用管理控制器
 * @author: Wonders-Rain
 * @date: 2018年6月20日 下午9:25:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("app/")
public class AppNodeController extends GenericController {
	
	private static final Logger logger = LoggerFactory.getLogger(AppNodeController.class);
	
	private final String APP_NODE_INDEX = "resource/queryAppNodeIndex",
	        APP_NODE_CREATE_INDEX = "resource/createAppNodeIndex", APP_NODE_EDIT_INDEX = "resource/editAppNodeIndex",
	        APP_USER_INDEX = "resource/querySecurityUserIndex";
	
	@Autowired
	AppNodeService appNodeService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("query/all")
	@ResponseBody
	public List<AppNode> queryApplication() {
		
		AppNode[] nodes = appNodeService.findAllCentralNodes();
		return new ArrayList<AppNode>(Arrays.asList(nodes));
	}
	
	@Log(title = "应用查询", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
	     isSaveRequestData = true)
	@RequestMapping("query/page")
	@ResponseBody
	public Page<AppNodeViewModel> queryAppNode(String name, Integer page, Integer limit) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AppNode.class);
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<AppNode> appNodes = appNodeService.findByCriteria(detachedCriteria, page, limit);
		List<AppNodeViewModel> result = new ArrayList<AppNodeViewModel>();
		for (AppNode appNode : appNodes.getResult()) {
			AppNodeViewModel appNodeViewModel = new AppNodeViewModel();
			try {
				BeanUtils.copyProperties(appNodeViewModel, appNode);
				appNodeViewModel.setMaxInactiveInterval(
				        (appNode.getMaxInactiveInterval() >= 60) ? appNode.getMaxInactiveInterval() / 60 : 1);
				result.add(appNodeViewModel);
			} catch (IllegalAccessException | InvocationTargetException ex) {
				logger.error("应用节点拷贝视图对象错误：", ex);
			}
		}
		return new Page<AppNodeViewModel>(appNodes.getStart(), appNodes.getCurrentPageSize(), appNodes.getTotalSize(),
		        appNodes.getPageSize(), result);
	}
	
	@RequestMapping("index")
	public String appNodeIndex() {
		
		return APP_NODE_INDEX;
	}
	
	@RequestMapping("create")
	public String createAppNodeIndex(Model model) {
		
		return APP_NODE_CREATE_INDEX;
	}
	
	@Log(title = "新增应用", operatorType = OperatorType.MANAGE, businessType = BusinessType.INSERT,
	     isSaveRequestData = true)
	@RequestMapping("create/save")
	@ResponseBody
	public AjaxResult saveCreateAppNodeIndex(AppNodeViewModel model) {
		
		try {
			if (appNodeService.getAppNodeByEntryPath(model.getIndexURL()) != null) { return new AjaxResult(false,
			        AjaxResult.MESSAGE_ERROR_TYPE, "已存在相同的首页链接，请重新输入！"); }
			if (appNodeService.findNodeByCode(model.getCode()) != null) { return new AjaxResult(false,
			        AjaxResult.MESSAGE_ERROR_TYPE, "已存在相同的应用系统代码，请重新输入！"); }
			
			model.setMaxInactiveInterval(model.getMaxInactiveInterval() * 60);
			
			AppNode appNode = new AppNode();
			PropertyUtils.copyProperties(appNode, model);
			
			appNode.setRootMenuCode(model.getCode());
			appNodeService.addNewAppNode(appNode);
			MenuResource menuResource = new MenuResource();
			menuResource.setCode(appNode.getRootMenuCode());
			menuResource.setResourceName(appNode.getName());
			menuResource.setAppNodeId(appNode.getId());
			menuService.createMenuResource(menuResource);
			
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("创建应用系统", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("edit")
	public String editAppNodeIndex(String id, Model model) {
		
		AppNode appNode = appNodeService.get(id);
		AppNodeViewModel appNodeViewModel = new AppNodeViewModel();
		try {
			BeanUtils.copyProperties(appNodeViewModel, appNode);
			appNodeViewModel.setMaxInactiveInterval(
			        (appNode.getMaxInactiveInterval() >= 60) ? appNode.getMaxInactiveInterval() / 60 : 1);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			logger.error("应用节点拷贝视图对象错误：", ex);
		}
		model.addAttribute("appNode", appNodeViewModel);
		return APP_NODE_EDIT_INDEX;
	}
	
	@Log(title = "修改应用", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
	     isSaveRequestData = true)
	@RequestMapping("edit/save")
	@ResponseBody
	public AjaxResult saveEditAppNode(AppNodeViewModel model) {
		
		try {
			AppNode existedNode = appNodeService.getAppNodeByEntryPath(model.getIndexURL());
			if (existedNode != null && !StringUtils.equals(existedNode.getCode(), model.getCode())) {
				if (appNodeService.getAppNodeByEntryPath(model.getIndexURL()) != null) { return new AjaxResult(false,
				        AjaxResult.MESSAGE_ERROR_TYPE, "已存在相同的首页链接，请重新输入！"); }
			}
			model.setMaxInactiveInterval(model.getMaxInactiveInterval() * 60);
			AppNode appNode = appNodeService.loadById(model.getId());
			PropertyUtils.copyProperties(appNode, model);
			appNodeService.updateAppNode(appNode);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护应用系统", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@Log(title = "删除应用", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
	     isSaveRequestData = true)
	@RequestMapping("remove/save")
	@ResponseBody
	public AjaxResult removeAppNode(String id) {
		
		try {
			AppNode appNode = appNodeService.get(id);
			appNodeService.removeAppNode(appNode);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除应用系统", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("user/index")
	public String appNodeUserIndex(String id, Model model) {
		
		model.addAttribute("id", id);
		return APP_USER_INDEX;
	}
	
	@Log(title = "查询应用人员", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
	     isSaveRequestData = true)
	@RequestMapping("user/page")
	@ResponseBody
	public Page<UserVO> queryAppNodeUser(String id, String loginName, String name, Integer inAppNode, Integer limit,
	        Integer page) {
		
		UserDTO dto = new UserDTO();
		dto.setSummary(false);
		Page<SecurityUser> securityUsers = null;
		if (StringUtils.isNotBlank(name)) {
			dto.setName(name);
		}
		if (StringUtils.isNotBlank(loginName)) {
			dto.setLoginName(loginName);
		}
		dto.setAppNodeId(id);
		if (inAppNode != null && inAppNode == 1) {
			dto.setInAppNode(true);
		} else {
			dto.setInAppNode(false);
		}
		securityUsers = userService.queryUsersWithPage(dto);
		List<UserVO> result = new ArrayList<UserVO>();
		for (SecurityUser securityUser : securityUsers.getResult()) {
			UserVO vo = new UserVO();
			vo.setId(securityUser.getId());
			vo.setName(securityUser.getName());
			vo.setLoginName(securityUser.getLoginName());
			vo.setSex(securityUser.getSex());
			vo.setEmail(securityUser.getEmail());
			vo.setHonePhone(securityUser.getHonePhone());
			vo.setOfficePhone(securityUser.getOfficePhone());
			if (inAppNode != null && inAppNode == 1) {
				vo.setAppNodeId(id);
			}
			result.add(vo);
		}
		return new Page<UserVO>(securityUsers.getStart(), securityUsers.getCurrentPageSize(),
		        securityUsers.getTotalSize(), securityUsers.getPageSize(), result);
	}
	
	@Log(title = "新增应用人员", operatorType = OperatorType.MANAGE, businessType = BusinessType.INSERT,
	     isSaveRequestData = true)
	@RequestMapping("user/add/{appNodeId}")
	@ResponseBody
	public AjaxResult addUserInAppNode(@PathVariable("appNodeId") String appNodeId, @RequestBody List<String> userIds) {
		
		try {
			AppNode appNode = appNodeService.get(appNodeId);
			for (String userId : userIds) {
				SecurityUser securityUser = userService.get(userId);
				appNodeService.addUserToNode(securityUser, appNode);
			}
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除应用系统", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@Log(title = "删除应用人员", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
	     isSaveRequestData = true)
	@RequestMapping("user/remove")
	@ResponseBody
	public AjaxResult removeUserFromAppNode(String appNodeId, String userId) {
		
		try {
			AppNode appNode = appNodeService.get(appNodeId);
			SecurityUser securityUser = userService.get(userId);
			appNodeService.removeUserFromNode(securityUser, appNode);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除应用系统", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
}
