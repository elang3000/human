/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServantRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月22日 上午9:59:04 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月22日 上午9:59:04 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowCollect;
import com.wondersgroup.human.repository.ofcflow.AssessmentFlowCollectRepository;
import com.wondersgroup.human.vo.ofcflow.AssessFlowUnitCollectVO;
import com.wondersgroup.human.vo.ofcflow.AssessmentFlowUnitPercentVO;

/**
 * 
 * @ClassName: AssessmentFlowCollectRepositoryImpl 
 * @Description: 考核计划表DAOIMPL
 * @author: youyd
 * @date: 2018年9月25日 下午3:46:50
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class AssessmentFlowCollectRepositoryImpl extends GenericRepositoryImpl<AssessmentFlowCollect> implements AssessmentFlowCollectRepository {

	public Class<AssessmentFlowCollect> getEntityClass() {
		return AssessmentFlowCollect.class;
	}
	
	@Override
	public Page<AssessmentFlowUnitPercentVO> getUnitAssessProgress(String assessmentFlowCollectId,String unitName,Integer page,Integer limit){
		StringBuilder sql=new StringBuilder();
		sql.append("select t1.DEPARTID id, ");
		sql.append("       t1.departName, ");
		sql.append("       t1.userCount, ");
		sql.append("       t2.status, ");
		sql.append("       t2.flow_status, ");
		sql.append("       nvl(t2.OUTSTANDING_NUMB, 0) OUTSTANDING_NUMB, ");
		sql.append("       nvl(t2.UNIT_OUTSTANDING_NUMB, 0) UNIT_OUTSTANDING_NUMB, ");
		sql.append("       nvl(t3.assessCount, 0) assessCount, ");
		sql.append("       nvl(t4.confirmCount, 0) confirmCount ");
		sql.append("  from (select a.DEPARTID, ");
		sql.append("               max(org.name) departName, ");
		sql.append("               max(assessment_flow_unit_percent) assessment_flow_unit_percent, ");
		sql.append("               count(t.id) usercount ");
		sql.append("          from HUMAN_ASSESSMENT_FLOW_DETAIL t ");
		sql.append("          left join a01 a ");
		sql.append("            on t.servant = a.id ");
		sql.append("          left join CS_ORGAN_NODE org ");
		sql.append("            on a.departid = org.id ");
		sql.append("         where t.assessment_flow_collect = ");
		sql.append("               :assessmentFlowCollectId ");
		if(StringUtils.isNotEmpty(unitName)){
			sql.append("  and org.name like :unitName   ");
		}
		sql.append("         group by a.DEPARTID) t1 ");
		sql.append("  left join HUMAN_ASSESSMENT_FLOW_UNIT_PERCENT t2 ");
		sql.append("    on t1.assessment_flow_unit_percent = t2.id ");
		sql.append("  left join (select a.DEPARTID, count(t.id) assessCount ");
		sql.append("               from HUMAN_ASSESSMENT_FLOW_DETAIL t ");
		sql.append("               left join a01 a ");
		sql.append("                 on t.servant = a.id ");
		sql.append("               left join CS_ORGAN_NODE org ");
		sql.append("                 on a.departid = org.id ");
		sql.append("              where t.assessment_flow_collect = ");
		sql.append("                    :assessmentFlowCollectId ");
		if(StringUtils.isNotEmpty(unitName)){
			sql.append("  and org.name like :unitName   ");
		}
		sql.append("                and t.result is not null ");
		sql.append("              group by a.DEPARTID) t3 ");
		sql.append("    on t1.departid = t3.departid ");
		sql.append("  left join (select a.DEPARTID, count(t.id) confirmCount ");
		sql.append("               from HUMAN_ASSESSMENT_FLOW_DETAIL t ");
		sql.append("               left join a01 a ");
		sql.append("                 on t.servant = a.id ");
		sql.append("               left join CS_ORGAN_NODE org ");
		sql.append("                 on a.departid = org.id ");
		sql.append("              where t.assessment_flow_collect = ");
		sql.append("                    :assessmentFlowCollectId ");
		if(StringUtils.isNotEmpty(unitName)){
			sql.append("  and org.name like :unitName   ");
		}
		sql.append("                and t.status = 1 ");
		sql.append("              group by a.DEPARTID) t4 ");
		sql.append("    on t1.departid = t4.departid ");
		sql.append(" order by id ");

		
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
        queryObject.setParameter("assessmentFlowCollectId", assessmentFlowCollectId);
		if(StringUtils.isNotEmpty(unitName)){
	        queryObject.setParameter("unitName", "%"+unitName+"%");
		}
		
		
		List listAll=queryObject.list();
	 	queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        queryObject.setFirstResult((page - 1) * limit);  
        queryObject.setMaxResults(limit); 
        List list =queryObject.list();  
		List<AssessmentFlowUnitPercentVO> listVO=new ArrayList<>();
		for (Object object : list) {
			Map<String,Object> map=(Map<String,Object>)object;
			listVO.add(new AssessmentFlowUnitPercentVO(map));
		}

		Page<AssessmentFlowUnitPercentVO> pages = new Page<>((page-1)*limit, page, listAll.size(), limit, listVO);
		return pages;
	}

	@Override
	public Page<AssessFlowUnitCollectVO> getCollectAndFlowStatus(OrganNode org, Integer page, Integer limit) {
		StringBuilder sql=new StringBuilder();
		sql.append("select ct.id,pt.flow_status, ct.year,ct.season,ct.ASSESSMENT_TYPE,ct.REMARK,ct.DRAFT_OUTSTANDING_PERCENT,ct.CREATE_TIME,ct.STATUS ");
		sql.append("  from HUMAN_ASSESSMENT_FLOW_COLLECT ct ");
		sql.append("  left join (select * ");
		sql.append("               from HUMAN_ASSESSMENT_FLOW_UNIT_PERCENT t ");
		sql.append("              where t.orgnode = :orgId) pt ");
		sql.append("    on ct.id = pt.assessment_flow_collect order by ct.ASSESSMENT_TYPE asc,ct.year desc,ct.season desc");

		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
	    queryObject.setParameter("orgId", org.getId());
	    List listAll=queryObject.list();
	 	queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        queryObject.setFirstResult((page - 1) * limit);  
        queryObject.setMaxResults(limit); 
		List list = queryObject.list();
		List<AssessFlowUnitCollectVO> listVO=new ArrayList<>();
		for (Object object : list) {
			Map<String,Object> map=(Map<String,Object>)object;
			listVO.add(new AssessFlowUnitCollectVO(map));
		}

		Page<AssessFlowUnitCollectVO> pages = new Page<>((page-1)*limit, page, listAll.size(), limit, listVO);
		return pages;
	}
	

}
