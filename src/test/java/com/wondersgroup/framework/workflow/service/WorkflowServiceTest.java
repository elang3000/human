/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: WorkflowServiceTest.java
 * 工程名: human
 * 包名: com.wondersgroup.framework.workflow.service
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年8月28日 下午4:03:05
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年8月28日 下午4:03:05
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.framework.workflow.service;

import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.icu.util.Calendar;
import com.wondersgroup.framework.core.test.AbstractTest;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.dto.Allocation;

/**
 * @ClassName: WorkflowServiceTest
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年8月28日 下午4:03:05
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class WorkflowServiceTest extends AbstractTest {
	
	@Autowired
	WorkflowService workflowService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FlowRecordService flowRecordService;
	
	// @Test
	public void queryNextAllocation() {
		
		OrganNode organNode = organNodeService.get("34B06A51E5D3400BA6FB6AB7CD4D509C");
		
		Allocation allocation = workflowService.queryNextAllocation("eddbbace-cd45-47f5-b43b-fa4129c8984a", organNode,
		        "REPORT_EMPLOY_PLAN");
		System.out.println(allocation);
	}
	
	public void createFlowRecord() {
		
		OrganNode targetOrganNode = organNodeService.get("FC1F0C52E3C74D04C794F8434F7CBE99");
		SecurityUser targetSecurityUser = userService.get("237DCA6DFA094EC08C2FF42F0AF76CA7");
		FlowRecord flowRecord = new FlowRecord();
		flowRecord.setAppNodeId("eddbbace-cd45-47f5-b43b-fa4129c8984a");
		flowRecord.setTargetOrganNode(targetOrganNode);
		flowRecord.setTargetSecurityUser(targetSecurityUser);
		flowRecord.setOperationCode("REPORT_EMPLOY_PLAN");
		flowRecord.setBusId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		flowRecord.setBusName("公务员录用计划");
		flowRecord.setBusType("RecruitEmployPlan");
		flowRecord = workflowService.createFlowRecord(flowRecord, "REPORT_EMPLOY_PLAN");
		System.out.print(flowRecord);
	}
	
	//@Test
	public void completeFlowRecord() {
		
		FlowRecord flowRecord = flowRecordService.loadWithLazy("479558E19FE94C3C2185AD28844035B5", "targetSecurityUser",
		        "targetOrganNode", "sourceSecurityUser", "sourceOrganNode");
		
		//flowRecord = flowRecordService.get("7D3BE56E410F413D4373497A2871A8BE");
		flowRecord.setOpinion("不通过");
		flowRecord.setResult(FlowRecord.NOPASS);
		workflowService.completeWorkItem(flowRecord);
	}
	
	@Test
	public void countWorkFLowBusinessNum() {
		Map<String,Integer> count = workflowService.countWorkFLowBusinessNum(null,null,Calendar.getInstance().getTime());
	
		System.out.println("数量：" + count);
	}
}
