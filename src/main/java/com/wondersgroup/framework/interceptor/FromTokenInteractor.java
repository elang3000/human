/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: FromTokenInteractor.java
 * 工程名: human
 * 包名: com.wondersgroup.framework.interceptor
 * 描述: 系统防止同步提交拦截器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年9月4日 上午11:07:10
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.framework.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.widget.token.FromTokenTag;

/**
 * @ClassName: FromTokenInteractor
 * @Description: 系统防止同步提交拦截器
 * @author: Wonders-Rain
 * @date: 2018年9月4日 上午11:07:10
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class FromTokenInteractor implements HandlerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(FromTokenInteractor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		String requestTokenTag = request.getParameter(FromTokenTag.TOKEN_SESSION_ATTR_NAME_TAG);
		String requestToken = request.getParameter(FromTokenTag.TOKEN_REQUEST_ATTR_NAME);
		
		Object sessionToken = SecurityUtils.getSession().getAttribute(FromTokenTag.TOKEN_SESSION_ATTR_NAME + "_"+ requestTokenTag);
		// 需要验证重复提交
		if (StringUtils.isNotBlank(requestToken)) {
			// 重复提交
			if (sessionToken == null) {
				return false;
			} else {
				if (requestToken.equals(sessionToken)) {
					logger.debug("执行线程(preHandle)：" + Thread.currentThread().getName() + " ;执行时间:"
					        + Calendar.getInstance().getTimeInMillis());
					SecurityUtils.getSession().removeAttribute(FromTokenTag.TOKEN_SESSION_ATTR_NAME + "_"+ requestTokenTag);
					return true;
				} else {
					return false;
				}
			}
			
		} else {
			return true;
		}
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	        ModelAndView modelAndView) throws Exception {
		
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	        throws Exception {
		
		String requestToken = request.getParameter(FromTokenTag.TOKEN_REQUEST_ATTR_NAME);
		String requestTokenTag = request.getParameter(FromTokenTag.TOKEN_SESSION_ATTR_NAME_TAG);
		if (StringUtils.isNotBlank(requestToken) && StringUtils.isNotBlank(requestTokenTag)) {
			logger.debug("执行线程(afterCompletion)：" + Thread.currentThread().getName() + " ;执行时间:"
			        + Calendar.getInstance().getTimeInMillis());
			SecurityUtils.getSession().setAttribute(FromTokenTag.TOKEN_SESSION_ATTR_NAME + "_"+ requestTokenTag, requestToken);
		}
	}
}
