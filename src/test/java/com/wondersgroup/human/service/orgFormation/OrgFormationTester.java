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

package com.wondersgroup.human.service.orgFormation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.test.AbstractTest;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/**
 * @ClassName: StatistServiceTester
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年9月14日 下午2:55:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class OrgFormationTester extends AbstractTest {
	
	@Autowired
	FormationControlService formationControlService;
	
	
	@Test
	public void a() {
		
		List<JudgePostResult> targetList = new ArrayList<>();
		targetList.add(new JudgePostResult(FormationControl.SECTION_CHIEF, CommonConst.YES, 0));
		targetList.add(new JudgePostResult(FormationControl.SECTION_CHIEF, CommonConst.NO, 0));
		targetList.add(new JudgePostResult(FormationControl.DEPUTY_SECTION_CHIEF, CommonConst.YES, 0));
		targetList.add(new JudgePostResult(FormationControl.DEPUTY_SECTION_CHIEF, CommonConst.NO, 9));
		
		
		formationControlService.queryValidateFormationAndPostLvlNum("9FD37734AF0E4AE6B6DE1E0C47BA2C97",targetList);
	}
	
}
