/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: SecurityAuthcController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.security
 * 描述: 系统相关的权限操作控制器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年6月11日 下午3:41:11
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年6月11日 下午3:41:11
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.controller.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.security.bo.ACLOperation;
import com.wondersgroup.framework.security.bo.ACLResource;
import com.wondersgroup.framework.security.bo.ACLResourceType;
import com.wondersgroup.framework.security.bo.SecurityGroup;
import com.wondersgroup.framework.security.bo.SecurityRole;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.ACLResourceService;
import com.wondersgroup.framework.security.service.ACLService;
import com.wondersgroup.framework.security.service.AuthenticationService;
import com.wondersgroup.framework.security.service.RoleService;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.security.vo.OperationVO;
import com.wondersgroup.framework.security.vo.ResourceVO;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: SecurityAuthcController
 * @Description: 系统权限相关操作类
 * @author: Wonders-Rain
 * @date: 2018年6月11日 下午3:41:11
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("security/authc/")
public class SecurityAuthcController extends GenericController {
	
	private final static String AUTHC_USER_INDEX = "security/authc/manageAuthcUser";
	
	private final static String AUTHC_GROUP_INDEX = "security/authc/manageAuthcGroup";
	
	private final static String AUTHC_ORGAN_INDEX = "security/authc/manageAuthcOrgan";
	
	private final static String AUTHC_ACCREDIT_USER_INDEX = "security/authc/accreditAuthcUser";
	
	private final static String AUTHC_ACCREDIT_GROUP_INDEX = "security/authc/accreditAuthcGroup";
	
	private final static String AUTHC_ACCREDIT_ORGAN_INDEX = "security/authc/accreditAuthcOrgan";
	
	@Autowired
	UserService userService;
	
	@Autowired
	ACLResourceService aclResourceService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ACLService aclService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@RequestMapping("user/index")
	public String authcUserIndex(Model model) {
		
		return AUTHC_USER_INDEX;
	}
	
	@RequestMapping("group/index")
	public String authcGroupIndex(Model model) {
		
		return AUTHC_GROUP_INDEX;
	}
	
	@RequestMapping("organ/index")
	public String authcOrganIndex(Model model) {
		
		return AUTHC_ORGAN_INDEX;
	}
	
	@RequestMapping("user/accredit/index")
	public String accreditUserIndex(Model model, String id) {
		
		// 获取权限用户信息
		SecurityUser securityUser = userService.get(id);
		
		model.addAttribute("user", securityUser);
		return AUTHC_ACCREDIT_USER_INDEX;
	}
	
	@RequestMapping("group/accredit/index")
	public String accreditGroupIndex(Model model, String id) {
		
		// 获取权限用户信息
		SecurityGroup securityGroup = userService.loadGroupById(id);
		
		model.addAttribute("group", securityGroup);
		return AUTHC_ACCREDIT_GROUP_INDEX;
	}
	
	@RequestMapping("organ/accredit/index")
	public String accreditOrganIndex(Model model, String id) {
		
		OrganNode organNode = organNodeService.load(id);
		model.addAttribute("organ", organNode);
		return AUTHC_ACCREDIT_ORGAN_INDEX;
	}
	
	@RequestMapping("user/accredit/do")
	@ResponseBody
	public AjaxResult accreditUser(@RequestBody List<ResourceVO> resources) {
		
		try {
			for (ResourceVO resource : resources) {
				
				ACLResourceType resourceType = aclResourceService.loadResourceTypeById(resource.getResourceTypeId());
				ACLOperation operation = aclResourceService.loadOperationById(resource.getOperationId());
				SecurityUser user = userService.loadUserById(resource.getAuthcUserId());
				SecurityRole securityRole = roleService.loadDefaultRoleWithUser(user);
				
				ACLResource aclResource = new ACLResource();
				aclResource.setNativeResourceId(resource.getId());
				aclResource.setName(resource.getName());
				aclResource.setAclResourceType(resourceType);
				
				if (resource.getAuthc() == 0) {
					aclService.accredit(securityRole, aclResource, operation);
				}
			}
		} catch (Exception ex) {
			logger.error("系统用户授权异常：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, ex.getMessage());
		}
		return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("security.user.accredit.success"));
	}
	
	@RequestMapping("group/accredit/do")
	@ResponseBody
	public AjaxResult accreditGroup(@RequestBody List<ResourceVO> resources) {
		
		try {
			for (ResourceVO resource : resources) {
				
				ACLResourceType resourceType = aclResourceService.loadResourceTypeById(resource.getResourceTypeId());
				ACLOperation operation = aclResourceService.loadOperationById(resource.getOperationId());
				SecurityUser user = userService.loadUserById(resource.getAuthcUserId());
				SecurityRole securityRole = roleService.loadDefaultRoleWithUser(user);
				
				ACLResource aclResource = new ACLResource();
				aclResource.setNativeResourceId(resource.getId());
				aclResource.setName(resource.getName());
				aclResource.setAclResourceType(resourceType);
				
				if (resource.getAuthc() == 0) {
					aclService.accredit(securityRole, aclResource, operation);
				}
			}
		} catch (Exception ex) {
			logger.error("普通群组授权异常：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, ex.getMessage());
		}
		return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("security.user.accredit.success"));
	}
	
	@RequestMapping("user/accredit/undo")
	@ResponseBody
	public AjaxResult antiAccreditUser(@RequestBody List<ResourceVO> resources) {
		
		try {
			for (ResourceVO resource : resources) {
				
				ACLResourceType resourceType = aclResourceService.loadResourceTypeById(resource.getResourceTypeId());
				SecurityUser user = userService.loadUserById(resource.getAuthcUserId());
				SecurityRole securityRole = roleService.loadDefaultRoleWithUser(user);
				
				ACLResource aclResource = aclResourceService.getResourceByTypeAndNativeId(resourceType,
				        resource.getId());
				if (resource.getAuthc() == 1) {
					aclService.revokeResourcePermission(securityRole, aclResource);
				}
			}
		} catch (Exception ex) {
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, ex.getMessage());
		}
		return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("security.user.accredit.success"));
	}
	
	@RequestMapping("user/accredit/revoke")
	@ResponseBody
	public AjaxResult revokeAccreditUser(@RequestBody List<ResourceVO> resources) {
		
		for (ResourceVO resource : resources) {
			ACLResourceType resourceType = aclResourceService.loadResourceTypeById(resource.getResourceTypeId());
			ACLOperation operation = aclResourceService.loadOperationById(resource.getOperationId());
			SecurityUser user = userService.loadUserById(resource.getAuthcUserId());
			SecurityRole securityRole = roleService.loadDefaultRoleWithUser(user);
			
			ACLResource aclResource = new ACLResource();
			aclResource.setNativeResourceId(resource.getId());
			aclResource.setName(resource.getName());
			aclResource.setAclResourceType(resourceType);
			
			if (resource.getAuthc() == 0) {
				aclService.revoke(securityRole, aclResource, operation);
			}
		}
		return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("security.user.revoke.success"));
	}
	
	@RequestMapping("user/accredit/unrevoke")
	@ResponseBody
	public AjaxResult antiRevokeAccreditUser(@RequestBody List<ResourceVO> resources) {
		
		for (ResourceVO resource : resources) {
			ACLResourceType resourceType = aclResourceService.loadResourceTypeById(resource.getResourceTypeId());
			ACLOperation operation = aclResourceService.loadOperationById(resource.getOperationId());
			SecurityUser user = userService.loadUserById(resource.getAuthcUserId());
			SecurityRole securityRole = roleService.loadDefaultRoleWithUser(user);
			
			ACLResource aclResource = aclResourceService.getResourceByTypeAndNativeId(resourceType, resource.getId());
			if (resource.getAuthc() == -1) {
				aclService.antiRevokePermission(securityRole, aclResource, operation);
			}
		}
		return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("security.user.revoke.success"));
	}
	
	@RequestMapping("user/info")
	@ResponseBody
	public Page<ResourceVO> queryUserACLResource(String securityUserId, String appNodeId, String resourceTypeId,
	        String organNodeId, String organTreeId, String operationId) {
		
		if (StringUtils.isBlank(resourceTypeId) || StringUtils.isBlank(appNodeId) || StringUtils.isBlank(resourceTypeId)
		        || StringUtils.isBlank(organNodeId) || StringUtils.isBlank(operationId)) {
			return new Page<ResourceVO>();
		} else {
			List<ResourceVO> result = authenticationService.getAuthResourceForUserByResorucTypeAndOrganTree(
			        resourceTypeId, securityUserId, organTreeId, organNodeId, operationId, appNodeId);
			return new Page<ResourceVO>(1, 1, 5, 10, result);
		}
	}
	
	@RequestMapping("group/info")
	@ResponseBody
	public Page<ResourceVO> queryGroupACLResource(String securityUserId, String appNodeId, String resourceTypeId, String operationId) {
		
		if (StringUtils.isBlank(resourceTypeId) || StringUtils.isBlank(appNodeId) || StringUtils.isBlank(operationId)) {
			return new Page<ResourceVO>();
		} else {
			List<ResourceVO> result = null;//authenticationService.getAuthResourceForUserByResorucTypeAndOrganTree( resourceTypeId, securityUserId, organTreeId, organNodeId, operationId, appNodeId);
			return new Page<ResourceVO>(1, 1, 5, 10, result);
		}
	}
	
	@RequestMapping("resource/type")
	@ResponseBody
	public List<ACLResourceType> queryActResourceType() {
		
		ACLResourceType[] types = aclResourceService.getAllResourceTypes();
		return new ArrayList<ACLResourceType>(Arrays.asList(types));
	}
	
	@RequestMapping("resource/operation")
	@ResponseBody
	public List<OperationVO> queryActResourceOperation(String resourceTypeId) {
		
		if (StringUtils.isBlank(resourceTypeId)) { return new ArrayList<OperationVO>(); }
		ACLResourceType type = aclResourceService.loadResourceTypeById(resourceTypeId);
		if (type != null) {
			ACLOperation[] ops = aclResourceService.getOperations(type);
			List<OperationVO> vos = new ArrayList<OperationVO>();
			for (ACLOperation operation : ops) {
				OperationVO vo = new OperationVO();
				vo.setId(operation.getId());
				vo.setName(operation.getName());
				vos.add(vo);
			}
			return vos;
		} else {
			return new ArrayList<OperationVO>();
		}
	}
}
