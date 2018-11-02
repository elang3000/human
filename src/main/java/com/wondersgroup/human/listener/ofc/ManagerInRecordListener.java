/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerInRecordListener.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.listener.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月9日 下午3:55:03 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月9日 下午3:55:03 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.listener.ofc;

import org.springframework.stereotype.Component;

import com.wondersgroup.human.event.ofc.ManagerInRecordEvent;
/** 
 * @ClassName: ManagerInRecordListener 
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日 下午3:55:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Component
public class ManagerInRecordListener extends ManagerRecordListener<ManagerInRecordEvent> {

	/** 
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent) 
	 */
	@Override
	public void onApplicationEvent(ManagerInRecordEvent event) {
		super.createManagerRecordService(event);
	}

}
