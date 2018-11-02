/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantDAO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:11:22 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:11:22 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow;

import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.DraftServant;

/** 
 * @ClassName: DraftServantDAO 
 * @Description: TODO
 * @author: GP
 * @date: 2018年6月26日 上午10:11:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DraftServantDAO extends GenericRepository<DraftServant>{
	public List<DraftServant> findByCondMap(Map condition);
	/**
	 * @Title: publishByRecordId 
	 * @Description: 将导入记录修改为发布状态
	 * @param id
	 * @return: void
	 */
	public void publishByRecordId(String id);
	/**
	 * 
	 * @Title: getOrganIdByUnitId 
	 * @Description: 通过unitid查询对应的organnode的id
	 * @param unitId
	 * @return: void
	 */
	public String getUnitIdByOrganId(String organId);
}
