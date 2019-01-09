/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RoleServiceTester.java 
 * 工程名: human
 * 包名: com.wondersgroup.framework.security.service 
 * 描述: TODO
 * 创建人: Wonders-Rain   
 * 创建时间: 2018年11月22日 上午9:03:09 
 * 版本号: V1.0
 * 修改人：Wonders-Rain 
 * 修改时间：2018年11月22日 上午9:03:09 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.framework.security.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.framework.core.test.AbstractTest;
import com.wondersgroup.framework.security.bo.SecurityRole;
import com.wondersgroup.framework.security.bo.SecurityUser;


/** 
 * @ClassName: RoleServiceTester 
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年11月22日 上午9:03:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class RoleServiceTester extends AbstractTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Test
	public void loadAllRolesFromUser() {
		SecurityUser securityUser = userService.getUserByLoginName("lhcs2");
		
		SecurityRole[] roles = roleService.loadAllRolesFromUser(securityUser);
		logger.debug("角色数量：" + roles.length);
	}
}
