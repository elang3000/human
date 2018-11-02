/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: StatistServiceTester.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.analysis
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年9月14日 下午2:55:44
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年9月14日 下午2:55:44
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.service.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.test.AbstractTest;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganizationService;

/**
 * @ClassName: StatistServiceTester
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年9月14日 下午2:55:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class StatistServiceTester extends AbstractTest {
	
	@Autowired
	
	StatistService statistService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@Autowired
	OrganizationService organizationService;
	
	@Test
	public void statistServantTopEducation() {
		
		List<String> organNodeIds = new ArrayList<String>();
		OrganNode organNode = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE);
		List<OrganNode> organNodes = organizationService.getAllChildOrganNode(organNode.getId());
		for (OrganNode node : organNodes) {
			organNodeIds.add(node.getId());
		}
		Map<String, Integer> result = statistService.statistServantTopEducation(organNodeIds);
		logger.error("结果：" + result.toString());
	}
	
}
