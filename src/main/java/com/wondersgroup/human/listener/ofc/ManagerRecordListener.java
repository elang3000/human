/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerRecordListener.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.listener.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月9日 下午3:33:03 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月9日 下午3:33:03 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.listener.ofc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.wondersgroup.human.event.ofc.ManagerRecordEvent;
import com.wondersgroup.human.service.ofc.ManagerRecordService;

/** 
 * @ClassName: ManagerRecordListener 
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日 下午3:33:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public abstract class ManagerRecordListener <T extends ApplicationEvent> implements ApplicationListener<T>{
	
	@Autowired
	ManagerRecordService managerRecordService;
	
	public ManagerRecordListener() {
	
	}

	public ManagerRecordService getManagerRecordService() {
		return managerRecordService;
	}

	public void setManagerRecordService(ManagerRecordService managerRecordService) {
		this.managerRecordService = managerRecordService;
	}

	public void createManagerRecordService(ManagerRecordEvent event) {
		managerRecordService.createManagerRecord(event);
	}

}
