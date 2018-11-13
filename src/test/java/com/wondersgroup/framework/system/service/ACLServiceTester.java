/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ACLServiceTester.java 
 * 工程名: human
 * 包名: com.wondersgroup.framework.system.service 
 * 描述: TODO
 * 创建人: Wonders-Rain   
 * 创建时间: 2018年11月9日 下午5:27:43 
 * 版本号: V1.0
 * 修改人：Wonders-Rain 
 * 修改时间：2018年11月9日 下午5:27:43 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.framework.system.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.framework.core.test.AbstractTest;
import com.wondersgroup.framework.security.service.ACLService;


/** 
 * @ClassName: ACLServiceTester 
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年11月9日 下午5:27:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ACLServiceTester extends AbstractTest {
	
	@Autowired
	ACLService aclService;
	
	@Test
	public void queryAccRolePermission() {
		List<String> result = aclService.queryAccRolePermission("25C15A63BE9146E5BDFD23EFD1565185", "ff5f8969-2c0c-4c2c-a397-716ca2bdac37","e111ed06-7dc3-43c7-a603-4d69b1efb82d");
		logger.debug("测试结果：" + result.toString());
	}
}
