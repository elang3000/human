/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantHumanKeepRecordListener.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.listener 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月5日 下午12:23:25 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月5日 下午12:23:25 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.listener.record;

import org.springframework.stereotype.Component;

import com.wondersgroup.human.event.record.ReferenceServantHumamKeepRecordEvent;
/** 
 * @ClassName: ServantHumanKeepRecordListener 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年6月5日 下午12:23:25
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Component
public class ReferenceServantHumanKeepRecordListener extends HumanKeepRecordListener<ReferenceServantHumamKeepRecordEvent> {


	@Override
	public void onApplicationEvent(ReferenceServantHumamKeepRecordEvent event) {
		super.createHumanKeepRecord(event);
	}
}
