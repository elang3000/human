/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantReportService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 录用信息上报 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年7月20日 下午2:26:19 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月20日 下午2:26:19 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantReport;

import java.util.List;

/** 
 * @ClassName: DraftServantReportService 
 * @Description: 录用信息上报 服务接口
 * @author: tzy
 * @date: 2018年7月20日 下午2:26:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DraftServantReportService extends GenericService<DraftServantReport>{
	/**
	 * @Title: createReportWork 
	 * @Description: 保存上报信息，启动流程
	 * @param ids	上报的录用人员id，逗号隔开形式
	 * @param type	0：暂存	1：提交
	 * @param report	上报信息，两个附件的ftp路径
	 * @return: void
	 */
	public void createReportWork(List<DraftServant> draftServantList,String type,DraftServantReport report);
	/**
	 * @Title: addProbationServant 
	 * @Description: 录用数据进入试用期
	 * @param reportId	上报信息ID
	 * @return
	 * @return: boolean
	 */
	public boolean addProbationServant(String reportId);
	
	/**
	 * 
	 * @Title: addProbationServantSingle 
	 * @Description: 单个人员录用数据进入试用期
	 * @param servantId
	 * @return
	 * @return: boolean
	 */
	public boolean addProbationServantSingle(String servantId);
	
	
	/**
	 * 
	 * @Title: getDraftServantByTypeInIds 
	 * @Description: 通过类别和id查找拟录用人员
	 * @param ids
	 * @param servantType
	 * @return
	 * @return: List<DraftServant>
	 */
	public List<DraftServant> getDraftServantByTypeInIds(String ids,int servantType);
	
	
	/**
	 * 
	 * @Title: agree 
	 * @Description: 市局批复同意表单
	 * @param id
	 * @return: void
	 */
	public void updateAgreeServantReport(String id);
}
