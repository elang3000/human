/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: HumanKeepRecordEvent.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.event 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月4日 下午3:34:47 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月4日 下午3:34:47 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.event.record;

import org.springframework.context.ApplicationEvent;

/** 
 * @ClassName: HumanKeepRecordEvent 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年6月4日 下午3:34:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public abstract class HumanKeepRecordEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	/** 
	 * @Title:HumanKeepRecordEvent
	 * @Description:TODO 
	 * @param source 
	 */
	public HumanKeepRecordEvent(Object source) {
		super(source);
	}
}
