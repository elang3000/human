/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantImportRecordDAO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:21:32 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:21:32 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.DraftServantImportRecord;

/** 
 * @ClassName: DraftServantImportRecordDAO 
 * @Description: TODO
 * @author: GP
 * @date: 2018年6月26日 上午10:21:32
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DraftServantImportRecordDAO extends GenericRepository<DraftServantImportRecord>{

	/**
	 * 
	 * @Title: updateCollectStatusByImportId 
	 * @Description: 通过导入文件id 修改导入的人员的汇总状态
	 * @param id
	 * @return: void
	 */
	void updateCollectStatusByImportId(String id);
	
}
