/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerRecordEvent.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.event.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月9日 下午3:31:47 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月9日 下午3:31:47 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.event.ofc;

import org.springframework.context.ApplicationEvent;

/** 
 * @ClassName: ManagerRecordEvent 
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日 下午3:31:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public abstract class ManagerRecordEvent extends ApplicationEvent{

	private static final long serialVersionUID = 3758201596962103152L;
	
	private String managerType;

	/**
	 * @return the managerType
	 */
	public String getManagerType() {
		return managerType;
	}

	/**
	 * @param managerType the managerType to set
	 */
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}

	/** 
	 * @Title:ManagerRecordEvent
	 * @Description:TODO 
	 * @param source 
	 */
	public ManagerRecordEvent(Object source) {
		super(source);
		managerType = null;
	}
	
	/** 
	 * @Title:ManagerRecordEvent
	 * @Description:TODO 
	 * @param source 
	 */
	public ManagerRecordEvent(Object source,String managerType) {
		super(source);
		this.managerType = null;
		setManagerType(managerType);
	}

}
