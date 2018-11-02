/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: HumanKeepRecordListener.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.listener 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月4日 下午3:48:00 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月4日 下午3:48:00 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.listener.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.wondersgroup.human.event.record.HumanKeepRecordEvent;
import com.wondersgroup.human.service.record.HumanKeepRecordService;

/**
 * @ClassName: HumanKeepRecordListener
 * @Description: 备案监听
 * @author: lihao
 * @date: 2018年6月4日下午3:48:00 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public abstract class HumanKeepRecordListener<T extends ApplicationEvent> implements ApplicationListener<T> {
	
	@Autowired
	HumanKeepRecordService humanKeepRecordService;
	
	public HumanKeepRecordListener() {
	
	}

	public HumanKeepRecordService getHumanKeepRecordService() {
		return humanKeepRecordService;
	}

	public void setHumanKeepRecordService(HumanKeepRecordService humanKeepRecordService) {
		this.humanKeepRecordService = humanKeepRecordService;
	}

	public void createHumanKeepRecord(HumanKeepRecordEvent event) {
		humanKeepRecordService.createHumanKeepRecord(event);
	}

	
}
