/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantRelationReportService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 录用 流程和信息关联表 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年7月20日 下午2:47:17 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月20日 下午2:47:17 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.DraftServantRelationReport;

/** 
 * @ClassName: DraftServantRelationReportService 
 * @Description: 录用 流程和信息关联表 服务接口
 * @author: tzy
 * @date: 2018年7月20日 下午2:47:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DraftServantRelationReportService extends GenericService<DraftServantRelationReport>{
	
	/**
	 * 
	 * @Title: getServantIdByReport 
	 * @Description: 通过报告id获取报告中人员的id
	 * @param reportId
	 * @return
	 * @return: String
	 */
	public String getServantIdByReport(String reportId);
	
	/**
	 * 
	 * @Title: getReportByServant 
	 * @Description: 获取人员属于哪个汇总表单
	 * @param id
	 * @return
	 * @return: DraftServantReport
	 */
	public DraftServantRelationReport getReportByServant(String id);
	
}
