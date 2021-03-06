/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantRelationReportRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: 录用 流程和信息关联表 知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年7月20日 下午2:42:40 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月20日 下午2:42:40 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.DraftServantRelationReport;

/** 
 * @ClassName: DraftServantRelationReportRepository 
 * @Description: 录用 流程和信息关联表 知识库接口
 * @author: tzy
 * @date: 2018年7月20日 下午2:42:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DraftServantRelationReportRepository extends GenericRepository<DraftServantRelationReport> {

	public String getServantIdByReport(String reportId);
	
}
