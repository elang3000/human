/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: SecurityGroupController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.group
 * 描述: 关于群组操作的控制器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年10月15日 下午4:35:12
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年10月15日 下午4:35:12
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.controller.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganTree;
import com.wondersgroup.framework.organization.service.DepartmentService;
import com.wondersgroup.framework.security.bo.SecurityGroup;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.security.vo.UserVO;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: SecurityGroupController
 * @Description: 关于群组操作的控制器
 * @author: Wonders-Rain
 * @date: 2018年10月15日 下午4:35:12
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("group/")
public class SecurityGroupController extends GenericController {
	
	private final static String COMMON_USER_GROUP_INDEX = "security/group/queryCommonGroupIndex",
	        COMMON_USER_GROUP_CREATE_INDEX = "security/group/createCommonGroupIndex",
	        COMMON_USER_GROUP_EDIT_INDEX = "security/group/editCommonGroupIndex",
	        COMMON_USER_GROUP_EDIT_USER_INDEX = "security/group/editCommonGroupUserIndex",
	        COMMON_USER_GROUP_SELECT_USER_INDEX = "security/group/selectUserForCommonGroupIndex";
	
	@Autowired
	UserService userService;
	
	@Autowired
	DepartmentService departmentService;
	
	/*
	 * @Autowired
	 * DynaGroupService dynaGroupService;
	 */
	
	@RequestMapping("common/index")
	public String commonGroupIndex() {
		
		return COMMON_USER_GROUP_INDEX;
	}
	
	@RequestMapping("common/page")
	@ResponseBody
	public Page<SecurityGroup> queryCommonGroup(String code, String name, Integer page, Integer limit) {
		
		Map<String, Object> filter = new HashMap<String, Object>();
		
		if (StringUtils.isNotBlank(code)) {
			filter.put("code", code);
		}
		if (StringUtils.isNotBlank(name)) {
			filter.put("name", name);
		}
		Map<String, String> sort = new HashMap<String, String>();
		return userService.getAllGroupsByPage(filter, sort, page, limit);
	}
	
	@RequestMapping("common/create")
	public String createCommonGroupIndex() {
		
		return COMMON_USER_GROUP_CREATE_INDEX;
	}
	
	@RequestMapping("common/create/save")
	@ResponseBody
	public AjaxResult saveCreateCommonGroup(SecurityGroup group) {
		
		try {
			Assert.notNull(group.getCode(), "The Property Code of SecurityGroup can't own Nullable.");
			Assert.notNull(group.getName(), "The Property Name of SecurityGroup can't own Nullable.");
			SecurityGroup tGroup = userService.loadGroupByCode(group.getCode());
			if (tGroup != null) { return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE,
			        getMessage("security.group.exist.code.error")); }
			tGroup = userService.getGroupByName(group.getName());
			if (tGroup != null) { return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE,
			        getMessage("security.group.exist.name.error")); }
			userService.createNewGroup(group);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("新增普通用户组错误：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("common/edit")
	public String editCommonGroupIndex(String id, Model model) {
		
		SecurityGroup group = userService.loadGroupById(id);
		model.addAttribute("group", group);
		return COMMON_USER_GROUP_EDIT_INDEX;
	}
	
	@RequestMapping("common/edit/save")
	@ResponseBody
	public AjaxResult saveEditCommonGroup(SecurityGroup group) {
		
		try {
			userService.updateGroup(group);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("编辑普通用户组错误：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("common/remove")
	@ResponseBody
	public AjaxResult removeCommonGroup(String id) {
		
		try {
			SecurityGroup group = userService.loadGroupById(id);
			userService.removeGroup(group);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除普通用户组错误：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("common/user/index")
	public String editCommonGroupUserIndex(String id, Model model) {
		
		SecurityGroup group = userService.loadGroupById(id);
		model.addAttribute("group", group);
		
		return COMMON_USER_GROUP_EDIT_USER_INDEX;
	}
	
	@RequestMapping("common/user/add/index")
	public String addUserForCommonGroupIndex(String id, Model model) {
		
		SecurityGroup group = userService.loadGroupById(id);
		model.addAttribute("group", group);
		return COMMON_USER_GROUP_SELECT_USER_INDEX;
	}
	
	@RequestMapping("common/user/page")
	@ResponseBody
	public Page<UserVO> queryCommonGroupUserIndex(String groupId, String name, String mobile, Integer page,
	        Integer limit) {
		
		SecurityGroup group = userService.loadGroupById(groupId);
		Map<String, Object> filter = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(name)) {
			filter.put("name", name);
		}
		if (StringUtils.isNotBlank(mobile)) {
			filter.put("mobile1", mobile);
		}
		Page<SecurityUser> pageUser = userService.getAllUsersInGroupByPage(group, filter, page, limit);
		
		List<SecurityUser> result = pageUser.getResult();
		List<UserVO> convertResult = new ArrayList<UserVO>();
		
		if (result == null || result.isEmpty()) { return new Page<UserVO>(); }
		
		Map<String, OrganNode> departmentMap = departmentService.getUsersDepartment(result);
		for (Iterator<SecurityUser> iter = result.iterator(); iter.hasNext();) {
			SecurityUser user = iter.next();
			UserVO userVo = convertUserToSummaryVO(user, null);
			OrganNode department = departmentMap.get(user.getId());
			if (department != null) {
				userVo.setDepartmentName(department.getAllName());
			}
			convertResult.add(userVo);
		}
		return new Page<UserVO>(pageUser.getStart(), pageUser.getCurrentPageSize(), pageUser.getTotalSize(),
		        pageUser.getPageSize(), convertResult);
	}
	
	@RequestMapping("common/noneuser/page")
	@ResponseBody
	public Page<UserVO> queryCommonGroupNoneUserIndex(String groupId, String name, String mobile, Integer page,
	        Integer limit) {
		
		SecurityGroup group = userService.loadGroupById(groupId);
		Map<String, Object> filter = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(name)) {
			filter.put("name", name);
		}
		if (StringUtils.isNotBlank(mobile)) {
			filter.put("mobile1", mobile);
		}
		Page<SecurityUser> pageUser = userService.getAllUsersNotInGroupByPage(group, filter, page, limit);
		
		List<SecurityUser> result = pageUser.getResult();
		List<UserVO> convertResult = new ArrayList<UserVO>();
		
		if (result == null || result.isEmpty()) { return new Page<UserVO>(); }
		
		Map<String, OrganNode> departmentMap = departmentService.getUsersDepartment(result);
		for (Iterator<SecurityUser> iter = result.iterator(); iter.hasNext();) {
			SecurityUser user = iter.next();
			UserVO userVo = convertUserToSummaryVO(user, null);
			OrganNode department = departmentMap.get(user.getId());
			if (department != null) {
				userVo.setDepartmentName(department.getAllName());
			}
			convertResult.add(userVo);
		}
		return new Page<UserVO>(pageUser.getStart(), pageUser.getCurrentPageSize(), pageUser.getTotalSize(),
		        pageUser.getPageSize(), convertResult);
	}
	
	public UserVO convertUserToSummaryVO(SecurityUser user, OrganTree tree) {
		
		UserVO userVo = new UserVO();
		
		userVo.setId(user.getId());
		userVo.setLoginName(user.getLoginName());
		userVo.setAccountType(user.getAccountType());
		userVo.setName(user.getName());
		userVo.setOfficePhone(user.getOfficePhone());
		userVo.setMobile1(user.getMobile1());
		userVo.setEmail(user.getEmail());
		userVo.setStatus(user.getStatus());
		userVo.setSex(user.getSex());
		userVo.setAccountType(user.getAccountType());
		userVo.setAddress(user.getAddress());
		return userVo;
	}
	
	@RequestMapping("common/user/add")
	@ResponseBody
	public AjaxResult addUserIntoCommonGroup(String userId, String groupId) {
		
		SecurityGroup group = userService.loadGroupById(groupId);
		Assert.notNull(group, "操作的普通用户组对象不能为空！");
		SecurityUser user = userService.loadUserById(userId);
		Assert.notNull(group, "操作的普通用户对象不能为空！");
		try {
			userService.addGroupToUser(group, user);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除普通用户组错误：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("common/user/remove")
	@ResponseBody
	public AjaxResult removeUserFromCommonGroup(String userId, String groupId) {
		
		SecurityGroup group = userService.loadGroupById(groupId);
		Assert.notNull(group, "操作的普通用户组对象不能为空！");
		SecurityUser user = userService.loadUserById(userId);
		Assert.notNull(group, "操作的普通用户对象不能为空！");
		try {
			userService.removeGroupFromUser(group, user);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除普通用户组错误：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
}
