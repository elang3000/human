/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantReportRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 录用信息上报 知识库接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月20日 下午2:35:28 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月20日 下午2:35:28 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.DraftServantReport;
import com.wondersgroup.human.repository.ofcflow.DraftServantReportRepository;

/** 
 * @ClassName: DraftServantReportRepositoryImpl 
 * @Description: 录用信息上报 知识库接口实现类
 * @author: tzy
 * @date: 2018年7月20日 下午2:35:28
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class DraftServantReportRepositoryImpl extends GenericRepositoryImpl<DraftServantReport> implements DraftServantReportRepository{
	
	public Class<DraftServantReport> getEntityClass() {
		return DraftServantReport.class;
	}
}
