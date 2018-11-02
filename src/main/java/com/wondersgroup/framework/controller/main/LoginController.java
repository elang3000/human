/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: LoginController.java
 * 工程名: human
 * 包名: com.wondersgroup.framework.controller.main
 * 描述: 系统登录控制器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月23日 下午3:06:31
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.framework.controller.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

import com.wondersgroup.common.contant.SystemParamContant;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.resource.connector.provider.SystemParamProvider;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.HttpClientUtil;

/**
 * @ClassName: LoginController
 * @Description: 系统登录控制器
 * @author: Wonders-Rain
 * @date: 2018年5月23日 下午3:06:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
public class LoginController extends GenericController {
	
	private final static String LOGIN_VIEW = "login";
	
	private final static String RETURN_TO_CA = "returnToCA";
	
	private final static String CA_ERROR_VIEW = "ca";
	
	@Autowired
	UserService userService;
	
	@Value("#{system['system.ca.login.enabled']}")
	public Boolean enabledCA = false;
	
	@Value("#{system['system.ca.oauth.authorize.server.url']}")
	public String oauthAuthorizeUrl;
	
	@Value("#{system['system.ca.oauth.client.id']}")
	public String clientId;
	
	@Value("#{system['system.ca.oauth.client.secret']}")
	public String clientSecret;
	
	private final static String CA_REDIRECT_MAPPING = "/oauth/client/authorization/code/callback";
	
	/**
	 * @Title: sendRedirectForAuthorizeCode
	 * @Description: 获取授权代码请求
	 * @return: void
	 */
	private String sendRedirectForAuthorizeCode(String appCode) {
		
		String redirectUri = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":"
		        + this.getRequest().getServerPort() + this.getRequest().getContextPath() + CA_REDIRECT_MAPPING;
		
		// CA服务器重定向地址
		String location = oauthAuthorizeUrl + "oauth/authorize";
		location += "?response_type=code&scope=read,write&client_id=" + clientId + "&redirect_uri=" + redirectUri + "?state="
		        + appCode;
		//http://103.36.136.173:8480/oauth/oauth/authorize?response_type=code&scope=read,write&client_id=ywxt&redirect_uri=http://localhost:8080/OauthDemo/authorization_code_callback?state=8fc002aa-8d15-4400-aca3-cf1ca39ed3ac
		//http://103.36.136.173:8480/oauth/oauth/authorize?response_type=code&scope=read,write&client_id=ywxt&redirect_uri=http://localhost:8080/human/oauth/client/authorization/code/callback?state=human
		System.out.println("----------location----------" + location);
		
		//request.setAttribute("returnUrl", location);
		//this.getResponse().setCharacterEncoding("UTF-8");
		//this.getResponse().sendRedirect(location);
		return location;
	}
	
	/**
	 * @Title: sendRedirectForAccessToken
	 * @Description: 获取服务器访问令牌信息
	 * @param code 服务器授权代码
	 * @param appCode 应用系统代码
	 * @return: void
	 */
	private JSONObject getAccessToken(String code) {
		
		String redirectUri = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":"
		        + this.getRequest().getServerPort() + this.getRequest().getContextPath() + CA_REDIRECT_MAPPING;
		
		// 请求参数
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("client_id", clientId);
		parms.put("client_secret", clientSecret);
		parms.put("grant_type", "authorization_code");
		parms.put("code", code);
		parms.put("redirect_uri", redirectUri);
		String jsonStr = HttpClientUtil.doPost(oauthAuthorizeUrl + "oauth/token", parms, "UTF-8");
		if (jsonStr != null) {
			return JSONObject.fromObject(jsonStr);
		} else {
			return null;
		}
	}
	
	// 获取用户信息
	private JSONObject getCaUserInfo(String accessToken) {
		
		String userUrl = oauthAuthorizeUrl + "unity/user_info?access_token=" + accessToken;
		String jsonStr = HttpClientUtil.doGet(userUrl, "UTF-8");
		logger.info("获取CA返回的信息:" + jsonStr);
		if (jsonStr != null) {
			return JSONObject.fromObject(jsonStr);
		} else {
			return null;
		}
	}
	
	@RequestMapping("/")
	public String root(Model model) {
		
		String defaultApplicationCode = SystemParamProvider.getString(SystemParamContant.DEFAULT_APPLICATION_CODE);
		return "forward:/" + defaultApplicationCode + "/login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		
		String appCode = request.getParameter("appCode");
		if (StringUtils.isBlank(appCode)) {
			appCode = SystemParamProvider.getString(SystemParamContant.DEFAULT_APPLICATION_CODE);
		}
		return "forward:/" + appCode + "/login";
		
	}
	
	@RequestMapping("/{appCode}/login")
	public String appLoginView(Model model, @PathVariable(name = "appCode", required = true) String appCode) {
		
		if (SecurityUtils.getPrincipal() != null
		        && StringUtils.isNotBlank(SecurityUtils.getPrincipal().getAppCode())) { return "forward:/main/index"; }
		if (enabledCA) {
			String location = sendRedirectForAuthorizeCode(appCode);
			this.getRequest().setAttribute("returnUrl", location);
			return RETURN_TO_CA;
			//return "loading";
		} else {
			AppNode appNode = getAppNodeService().findNodeByCode(appCode);
			
			if (appNode == null) {
				appNode = getAppNodeService().findNodeByCode(SystemParamContant.DEFAULT_APPLICATION_CODE);
			}
			this.getRequest().setAttribute("appNode", appNode);
			SecurityUtils.getSession().setAttribute("appCode", appCode);
			return LOGIN_VIEW;
		}
	}
	
	@RequestMapping(CA_REDIRECT_MAPPING)
	public String completeCaAccessTokenCallback(Model model) {
		
		String error = this.getRequest().getParameter("error");
		if (StringUtils.isNotBlank(error)) {
			String errorDescription = this.getRequest().getParameter("error_description");
			logger.error("请求CA授权代码错误，错误代码：" + error + ",错误描述：" + errorDescription);
			model.addAttribute("message", error + "[" + getCaErrorDescription(error) + "]");
			return CA_ERROR_VIEW;
		} else {
			String code = this.getRequest().getParameter("code");
			String appCode = this.getRequest().getParameter("state");
			JSONObject accessTokenObject = getAccessToken(code);
			error = accessTokenObject.getString("error");
			if (StringUtils.isBlank(error)) {
				String accessToken = accessTokenObject.getString("access_token");
				String accessTokenExpiresIn = accessTokenObject.getString("expires_in");
				JSONObject caUserInfoObject = getCaUserInfo(accessToken);
				// 证书序列号
				String certSn = caUserInfoObject.getString("CertSn");
				// 证书序列号
				String commonName = caUserInfoObject.getString("CommonName");
				// 所属单位名称
				// String unitName = caUserInfoObject.getString("UnitName");
				// 证书序列号
				String personalId = caUserInfoObject.getString("PersonalID");
				// 通过姓名和身份证号获取系统中有效的用户对象
				SecurityUser securityUser = userService.querySecurityUserByNameAndIdNumber(commonName, personalId);
				if (securityUser == null) {
					model.addAttribute("message", getMessage("ca.validate.security.user.not.exist", new String[] {
					        commonName
					}));
					return CA_ERROR_VIEW;
				}
				AppNode appNode = getAppNodeService().findNodeByCode(appCode);
				if (appNode == null) {
					appNode = getAppNodeService().findNodeByCode(SystemParamContant.DEFAULT_APPLICATION_CODE);
				}
				model.addAttribute("appNode", appNode);
				model.addAttribute("certSn", certSn);
				model.addAttribute("accessToken", accessToken);
				model.addAttribute("expiresIn", accessTokenExpiresIn);
				model.addAttribute("user", securityUser);
				model.addAttribute("enabledCA", enabledCA);
				SecurityUtils.getSession().setAttribute("certSn", certSn);
				SecurityUtils.getSession().setAttribute("expiresIn", accessTokenExpiresIn);
				SecurityUtils.getSession().setAttribute("appCode", appCode);
				return LOGIN_VIEW;
			} else {
				String errorDescription = this.getRequest().getParameter("error_description");
				logger.error("请求CA授权令牌错误，错误代码：" + error + ",错误描述：" + errorDescription);
				model.addAttribute("message", error + "[" + getCaErrorDescription(error) + "]");
				return CA_ERROR_VIEW;
			}
		}
	}
	
	public String getCaErrorDescription(String errorCode) {
		
		switch (errorCode) {
			case "invalid_request":
				return "请求格式不正确(参数缺失，重复等)";
			case "invalid_client":
				return "客户端权限校验失败";
			case "invalid_grant":
				return "授权码或refresh token无效";
			case "unauthorized_client":
				return "客户端未被授权使用该授权类型";
			case "unsupported_grant_type":
				return "授权服务器不支持该授权类型";
			case "invalid_scope":
				return "scope参数有误";
			default:
				return "未知错误";
		}
	}
}
