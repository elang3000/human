/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerOutRecordEvent.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.event.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月9日 下午3:51:38 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月9日 下午3:51:38 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.event.ofc;

import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;

/** 
 * @ClassName: ManagerOutRecordEvent 
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日 下午3:51:38
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ManagerOutRecordEvent extends ManagerRecordEvent {

	private static final long serialVersionUID = -1606599152022008766L;

	/** 
	 * @Title:ManagerOutRecordEvent
	 * @Description:TODO 
	 * @param source 
	 */
	public ManagerOutRecordEvent(ManagerRecordDTO source) {
		super(source,ManagerRecord.MANAGER_MANAGER_TYPE_OUT);
	}

}
