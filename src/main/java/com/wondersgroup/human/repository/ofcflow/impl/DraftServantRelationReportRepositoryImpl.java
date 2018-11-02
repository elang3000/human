/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantRelationReportRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 录用 流程和信息关联表 知识库接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月20日 下午2:44:17 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月20日 下午2:44:17 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.DraftServantRelationReport;
import com.wondersgroup.human.repository.ofcflow.DraftServantRelationReportRepository;

/** 
 * @ClassName: DraftServantRelationReportRepositoryImpl 
 * @Description: 录用 流程和信息关联表 知识库接口实现类
 * @author: tzy
 * @date: 2018年7月20日 下午2:44:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class DraftServantRelationReportRepositoryImpl extends GenericRepositoryImpl<DraftServantRelationReport> implements DraftServantRelationReportRepository{
	public Class<DraftServantRelationReport> getEntityClass() {
		return DraftServantRelationReport.class;
	}

	@Override
	public String getServantIdByReport(String reportId) {
		Session session = this.getSessionFactory().getCurrentSession();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.draft_servant_id from HUMAN_DRAFT_SERVANT_R_REPORT t where t.report_id='"+reportId+"'");
	    //获得SQLQuery对象
	    SQLQuery query = session.createSQLQuery(sql.toString());
	    //设定结果结果集中的每个对象为Map类型   
	    query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
	    //执行查询
	    List<HashMap> list = query.list();
	    StringBuilder sb=new StringBuilder();
	    
	    if(list.size()>0){
	    	if(list.size()>1){
	    		for (HashMap map : list) {
	    			sb.append(map.get("DRAFT_SERVANT_ID")+",");
				}
	    		return sb.toString().substring(0,sb.length()-1);
	    	}else{
	    		return (String)list.get(0).get("DRAFT_SERVANT_ID");
	    	}
	    }else{
	    	return null;
	    }
		
	}
}
