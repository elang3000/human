/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerManageRecordListener.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.listener.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月9日 下午3:55:59 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月9日 下午3:55:59 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.listener.ofc;

import org.springframework.stereotype.Component;

import com.wondersgroup.human.event.ofc.ManagerManageRecordEvent;

/** 
 * @ClassName: ManagerManageRecordListener 
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日 下午3:55:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Component
public class ManagerManageRecordListener extends ManagerRecordListener<ManagerManageRecordEvent> {

	/** (non Javadoc) 
	 * @Title: onApplicationEvent
	 * @Description: TODO
	 * @param arg0 
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent) 
	 */
	@Override
	public void onApplicationEvent(ManagerManageRecordEvent event) {
		super.createManagerRecordService(event);
	}

}
