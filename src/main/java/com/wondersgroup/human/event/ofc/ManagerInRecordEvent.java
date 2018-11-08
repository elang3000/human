/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerInRecordEvent.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.event.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月9日 下午3:50:45 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月9日 下午3:50:45 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.event.ofc;

import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;

/** 
 * @ClassName: ManagerInRecordEvent 
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日 下午3:50:45
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ManagerInRecordEvent extends ManagerRecordEvent {

	private static final long serialVersionUID = -1732068473553096695L;

	/** 
	 * @Title:ManagerInRecordEvent
	 * @Description:TODO 
	 * @param source 
	 */
	public ManagerInRecordEvent(ManagerRecordDTO source) {
		super(source,ManagerRecord.IN);
	}

}
