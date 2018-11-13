/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: SecurityUserController.java
 * 工程名: human
 * 包名: com.wondersgroup.framework.controller.security
 * 描述: 关于用户操作的控制器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月21日 下午2:10:31
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月21日 下午2:10:31
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.controller.security;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganTree;
import com.wondersgroup.framework.organization.service.DepartmentService;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.PositionService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.dto.UserDTO;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.security.vo.ResourceVO;
import com.wondersgroup.framework.security.vo.UserQueryVO;
import com.wondersgroup.framework.security.vo.UserVO;
import com.wondersgroup.framework.shiro.session.SessionManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: SecurityUserController
 * @Description: 关于用户操作的控制器
 * @author: Wonders-Rain
 * @date: 2018年5月21日 下午2:10:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("security/user")
public class SecurityUserController extends GenericController {
	
	private final static String SECURITY_USER_INFO_EDIT_VIEW = "security/user/editSecurityUserView";
	
	// private final static String SECURITY_USER_PASSWORD_RESET_VIEW =
	// "security/user/resetSecurityUserPasswordView";
	
	private final static String SECURITY_USER_QUERY_VIEW = "security/user/querySecurityUserView";
	
	private final static String SECURITY_USER_ONLINE_VIEW = "security/user/querySecurityOnlineView";
	
	@Value("#{system['default.security.user.password']}")
	private String defaultPassword;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@Autowired
	SessionManager sessionManager;
	
	@RequestMapping("/online")
	public String querySecurityUserOnline() {
		return SECURITY_USER_ONLINE_VIEW;
	}
	
	@RequestMapping("/online/page")
	public Page<UserVO> querySecurityUserOnline(String loginName, Integer limit, Integer page) {
		List<UserVO> reuslt = new ArrayList<UserVO>();
		SessionDAO sessionDAO = sessionManager.getSessionDAO();
		Collection<?> sessions = sessionDAO.getActiveSessions();
		for (Object session : sessions) {
/*			if (session instanceOf(Session.class)) {
				
			}
			logger.debug("SESSION:" + session.getHost());*/
		}
		return new Page<UserVO>(page, limit, sessions.size(),limit, reuslt);
	}
	
	@RequestMapping("/info/edit")
	public String editSecurityUserInfo(Model model, UserDTO dto) {
		
		SecurityUser user = null;
		if (StringUtils.isNotBlank(dto.getId())) {
			user = userService.get(dto.getId());
			if (user == null) {
				user = new SecurityUser();
			} else {
				OrganNode[] organNodes = organNodeService.getOrganNodesByUserId(user.getId());
				String organNodeAllName = "";
				String flag = "";
				for (OrganNode organNode : organNodes) {
					organNodeAllName += flag + organNode.getAllName();
					flag = "\\";
				}
				model.addAttribute("organNodeAllName", organNodeAllName);
			}
		} else {
			user = new SecurityUser();
		}
		model.addAttribute("user", user);
		return SECURITY_USER_INFO_EDIT_VIEW;
	}
	
	@RequestMapping("/info/edit/save")
	@ResponseBody
	public AjaxResult saveSecurityUserInfo(Model model, UserDTO dto) {
		
		try {
			if (StringUtils.isNotBlank(dto.getId())) {
				SecurityUser user = userService.load(dto.getId());
				user.setSex(dto.getSex());
				user.setEmail(dto.getEmail());
				user.setAddress(dto.getAddress());
				user.setHonePhone(dto.getHonePhone());
				user.setOfficePhone(dto.getOfficePhone());
				user.setMobile1(dto.getMobile1());
				user.setMobile2(dto.getMobile2());
				user.setLastOperateTime(Calendar.getInstance().getTime());
				user.setLastOperator(SecurityUtils.getPrincipal().getId());
				userService.updateUser(user);
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
				        getMessage("security.user.update.success"));
			} else {
				OrganNode organNode = organNodeService.get(dto.getOrganNodeId());
				SecurityUser user = new SecurityUser();
				user.setLoginName(dto.getLoginName());
				user.setName(dto.getName());
				user.setPassword(defaultPassword);
				user.setSex(dto.getSex());
				user.setEmail(dto.getEmail());
				user.setAddress(dto.getAddress());
				user.setHonePhone(dto.getHonePhone());
				user.setOfficePhone(dto.getOfficePhone());
				user.setMobile1(dto.getMobile1());
				user.setMobile2(dto.getMobile2());
				user.setCreateTime(Calendar.getInstance().getTime());
				user.setCreater(SecurityUtils.getPrincipal().getId());
				user.setLastOperateTime(Calendar.getInstance().getTime());
				user.setLastOperator(SecurityUtils.getPrincipal().getId());
				userService.addNewUser(user, organNode);
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
				        getMessage("security.user.create.success", new String[] {
				                defaultPassword
						}));
			}
		} catch (Exception ex) {
			logger.error("维护用户信息时出现出错：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/password/reset")
	@ResponseBody
	public AjaxResult resetSecurityUserPassword(Model model, String id) {
		
		SecurityUser user = userService.get(id);
		try {
			if (user != null) {
				user.setPassword(defaultPassword);
				user.setLastOperateTime(Calendar.getInstance().getTime());
				user.setLastOperator(SecurityUtils.getPrincipal().getId());
				userService.refreshSecurityUserPassward(user);
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
				        getMessage("security.user.password.reset.success", new String[] {
				                defaultPassword
						}));
			} else {
				return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("security.user.not.exist"));
			}
		} catch (Exception ex) {
			logger.error("维护用户信息时出现出错：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/status/start")
	@ResponseBody
	public AjaxResult startSecurityUser(Model model, String id) {
		
		SecurityUser user = userService.get(id);
		try {
			if (user != null) {
				user.setLastOperateTime(Calendar.getInstance().getTime());
				user.setLastOperator(SecurityUtils.getPrincipal().getId());
				userService.activateUser(user);
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
				        getMessage("security.user.start.success", new String[] {
				                user.getName()
						}));
			} else {
				return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("security.user.not.exist"));
			}
		} catch (Exception ex) {
			logger.error("维护用户信息时出现出错：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/status/stop")
	@ResponseBody
	public AjaxResult stopSecurityUser(Model model, String id) {
		
		SecurityUser user = userService.get(id);
		try {
			if (user != null) {
				user.setLastOperateTime(Calendar.getInstance().getTime());
				user.setLastOperator(SecurityUtils.getPrincipal().getId());
				userService.forbidUser(user);
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
				        getMessage("security.user.start.success", new String[] {
				                user.getName()
						}));
			} else {
				return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("security.user.not.exist"));
			}
		} catch (Exception ex) {
			logger.error("维护用户信息时出现出错：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/status/remove")
	@ResponseBody
	public AjaxResult removeSecurityUser(Model model, String id) {
		
		SecurityUser user = userService.get(id);
		try {
			if (user != null) {
				user.setLastOperateTime(Calendar.getInstance().getTime());
				user.setLastOperator(SecurityUtils.getPrincipal().getId());
				userService.removeUser(user);
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
				        getMessage("security.user.remove.success"));
			} else {
				return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("security.user.not.exist"));
			}
		} catch (Exception ex) {
			logger.error("维护用户信息时出现出错：", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/list")
	public String listSecurityUser(Model model) {
		
		return SECURITY_USER_QUERY_VIEW;
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Page<UserVO> querySecurityUserDetail(Model model, UserQueryVO queryVo) {
		
		queryVo.setSummary(true);
		return queryUsersWithPage(queryVo);
	}
	
	private Page<UserVO> queryUsersWithPage(UserQueryVO queryVo) {
		
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(queryVo, dto);
		Page<SecurityUser> page = userService.queryUsersWithPage(dto);
		
		if (queryVo.getSummary()) {
			return convertUserToSummaryVO(page, null);
		} else {
			return convertUserToVO(page);
		}
	}
	
	@RequestMapping("/query/list")
	@ResponseBody
	public List<UserVO> queryUserForSecurityUser() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SecurityUser.class);
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("status", SecurityUser.NORMAL));
		List<SecurityUser> securityUsers = userService.findByCriteria(detachedCriteria);
		List<UserVO> userResult = new ArrayList<UserVO>();
		for (SecurityUser securityUser: securityUsers) {
			UserVO vo = new UserVO();
			vo.setId(securityUser.getId());
			vo.setLoginName(securityUser.getLoginName());
			vo.setName(securityUser.getName());
			userResult.add(vo);
		}
		return userResult;
	}
	
	private Page<UserVO> convertUserToSummaryVO(Page<SecurityUser> page, OrganTree tree) {
		
		List<SecurityUser> result = page.getResult();
		List<UserVO> convertResult = new ArrayList<UserVO>();
		
		if (result == null || result.isEmpty()) { return new Page<UserVO>(); }
		
		Map<String, OrganNode> departmentMap = departmentService.getUsersDepartment(result);
		for (Iterator<SecurityUser> iter = result.iterator(); iter.hasNext();) {
			SecurityUser user = iter.next();
			UserVO userVo = convertUserToSummaryVO(user, tree);
			OrganNode department = departmentMap.get(user.getId());
			if (department != null) {
				userVo.setDepartmentName(department.getAllName());
			}
			convertResult.add(userVo);
		}
		return new Page<UserVO>(page.getStart(), page.getCurrentPageSize(), page.getTotalSize(), page.getPageSize(),
		        convertResult);
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
	
	private Page<UserVO> convertUserToVO(Page<SecurityUser> page) {
		
		List<SecurityUser> result = page.getResult();
		List<UserVO> convertResult = new ArrayList<UserVO>();
		if (result == null || result.isEmpty()) { return new Page<UserVO>(); }
		for (Iterator<SecurityUser> iter = result.iterator(); iter.hasNext();) {
			SecurityUser securityUser = iter.next();
			
			UserVO userVo = new UserVO();
			userVo.setId(securityUser.getId());
			userVo.setName(securityUser.getName());
			userVo.setLoginName(securityUser.getLoginName());
			userVo.setSex(securityUser.getSex());
			userVo.setEmail(securityUser.getEmail());
			userVo.setOfficePhone(securityUser.getOfficePhone());
			userVo.setMobile1(securityUser.getMobile1());
			userVo.setStatus(securityUser.getStatus());
			userVo.setAccountType(securityUser.getAccountType());
			convertResult.add(userVo);
		}
		return new Page<UserVO>(page.getStart(), page.getCurrentPageSize(), page.getTotalSize(), page.getPageSize(),
		        convertResult);
	}
}
