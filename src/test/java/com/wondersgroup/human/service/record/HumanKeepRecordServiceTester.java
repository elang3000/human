/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: HumanKeepRecordServiceTester.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.record
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月29日 下午5:40:12
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月29日 下午5:40:12
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.service.record;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.test.AbstractTest;
import com.wondersgroup.human.vo.record.HumanKeepRecordVO;

/**
 * @ClassName: HumanKeepRecordServiceTester
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年5月29日 下午5:40:12
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class HumanKeepRecordServiceTester extends AbstractTest {
	
	@Autowired
	HumanKeepRecordService humanKeepRecordService;
	
	@Test
	public void queryServantKeepRecord() {
		
		Map<String, Object> filter = new HashMap<String, Object>();
		Integer pageNumber = 1;
		Integer pageSize = 10;
		Page<HumanKeepRecordVO> reuslt = humanKeepRecordService.queryServantKeepRecord(filter, pageNumber, pageSize);
		logger.error(reuslt.toString());
	}
}
