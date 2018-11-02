/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantRelationReportServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 录用 流程和信息关联表 服务接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月20日 下午2:48:33 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月20日 下午2:48:33 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.DraftServantRelationReport;
import com.wondersgroup.human.repository.ofcflow.DraftServantRelationReportRepository;
import com.wondersgroup.human.service.ofcflow.DraftServantRelationReportService;

/** 
 * @ClassName: DraftServantRelationReportServiceImpl 
 * @Description: 录用 流程和信息关联表 服务接口实现类
 * @author: tzy
 * @date: 2018年7月20日 下午2:48:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DraftServantRelationReportServiceImpl extends GenericServiceImpl<DraftServantRelationReport> implements DraftServantRelationReportService{
	
	@Autowired
	private DraftServantRelationReportRepository draftServantRelationReportRepository;

	@Override
	public String getServantIdByReport(String reportId) {
		return draftServantRelationReportRepository.getServantIdByReport(reportId);
	}

	@Override
	public DraftServantRelationReport getReportByServant(String id) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DraftServantRelationReport.class);
		detachedCriteria.add(Restrictions.eq("draftServant.id", id));
		List<DraftServantRelationReport> list = this.findByCriteria(detachedCriteria);
		if(list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	
}
