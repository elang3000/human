/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerManageRecordEvent.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.event.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月9日 下午3:52:23 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月9日 下午3:52:23 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.event.pubinst;

import com.wondersgroup.human.bo.pubinst.PtManagerRecord;
import com.wondersgroup.human.dto.pubinst.PtManagerRecordDTO;

/** 
 * @ClassName: ManagerManageRecordEvent 
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日 下午3:52:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class PtManagerManageRecordEvent extends PtManagerRecordEvent {

	private static final long serialVersionUID = -602680383885377866L;
	
	/** 
	 * @Title:ManagerManageRecordEvent
	 * @Description:TODO 
	 * @param source 
	 */
	public PtManagerManageRecordEvent(PtManagerRecordDTO source) {
		super(source,PtManagerRecord.MANAGER_MANAGER_TYPE_MANAGER);
	}

}
