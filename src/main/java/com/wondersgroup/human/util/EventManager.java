/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EventManager.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.util 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月26日 上午11:30:11 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月26日 上午11:30:11 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.util;

import org.springframework.context.ApplicationEvent;

import com.wondersgroup.framework.util.SpringContextHolder;

/**
 * @ClassName: EventManager
 * @Description: TODO
 * @author: lihao
 * @date: 2018年6月26日上午11:30:11 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public class EventManager {
	public static <T extends ApplicationEvent> void send(T event) {
		SpringContextHolder.getApplicationContext().publishEvent(event);
	}

}
