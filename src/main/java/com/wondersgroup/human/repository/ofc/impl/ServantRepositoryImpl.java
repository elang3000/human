/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServantDAOImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.impl
 * 描述: 人员信息维护知识库接口实现类
 * 创建人: tzy
 * 创建时间: 2018年5月21日 上午11:22:54
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月21日 上午11:22:54
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.repository.ofc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wondersgroup.human.vo.ofcflow.AssessmentFlowUnitPercentVO;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.dto.analysis.ServantParam;
import com.wondersgroup.human.repository.ofc.ServantRepository;
import com.wondersgroup.human.vo.ofc.ServantVO;

/**
 * @ClassName: ServantDAOImpl
 * @Description: 人员信息维护知识库实现类
 * @author: tzy
 * @date: 2018年5月21日 上午11:22:54
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class ServantRepositoryImpl extends GenericRepositoryImpl<Servant> implements ServantRepository {
	
	public Class<Servant> getEntityClass() {
		
		return Servant.class;
	}
	
	/**
	 * @see com.wondersgroup.human.repository.ofc.ServantRepository#statistServantTopEducation(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Integer> statistServantTopEducation(List<String> organNodeIds, String isHold) {
		
		String queryString = "SELECT SH_ZGXL AS ZGXL,COUNT(ID) AS NUM FROM A01 WHERE REMOVED = 'N' AND A01063= :zgxl AND DEPARTID IN (:departId) GROUP BY SH_ZGXL";
		
		SQLQuery query = currentSession().createSQLQuery(queryString);
		query.setString("zgxl", isHold);
		query.setParameterList("departId", organNodeIds);
		
		@SuppressWarnings("unchecked")
		List<Object[]> queryResult = query.list();
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (Object[] entry : queryResult) {
			String name = (String) entry[0];
			if (StringUtils.isBlank(name)) {
				name = "未知";
			}
			Integer number = ((BigDecimal) entry[1]).intValue();
			result.put(name, number);
		}
		return result;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.ServantRepository#queryServantInfoBySeniorCondation(java.util.List, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ServantVO> queryServantInfoBySeniorCondation(List<ServantParam> spList, Integer page,
			Integer limit) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from A01 where removed ='N' and id in (");
		sql.append("select distinct a1.id from a01 a1 ");
		sql.append("left join A02 a2 on a1.id = a2.servant_id ");
		sql.append("left join A03 a3 on a1.id = a3.servant_id ");
		sql.append("left join A04 a4 on a1.id = a4.servant_id ");
		sql.append("left join A05 a5 on a1.id = a5.servant_id ");
		sql.append("left join A06 a6 on a1.id = a6.servant_id ");
		sql.append("left join A08 a8 on a1.id = a8.servant_id ");
		sql.append("left join A09 a9 on a1.id = a9.servant_id ");
		sql.append("left join A11 a11 on a1.id = a11.servant_id ");
		sql.append("left join A14 a14 on a1.id = a14.servant_id ");
		sql.append("left join A15 a15 on a1.id = a15.servant_id ");
		sql.append("left join A17 a17 on a1.id = a17.servant_id ");
		sql.append("left join A29 a29 on a1.id = a29.servant_id ");
		sql.append("left join A30 a30 on a1.id = a30.servant_id ");
//		sql.append("left join A36 a36 on a1.id = a36.servant_id ");
//		sql.append("left join A51 a51 on a1.id = a51.servant_id ");
//		sql.append("left join A52 a52 on a1.id = a52.servant_id ");
		sql.append("left join A61 a61 on a1.id = a61.servant_id ");
		sql.append("where 1=1 ");
		for(ServantParam s : spList){
			sql.append(" AND  "+s.getCode1()+" "+s.getCode3()+" '"+s.getCode2()+"' ");
		}
		sql.append(" group by a1.id) order by SH_REPORT_DATE desc");
		
		Query query =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(Servant.class);
		List<Servant> list1=query.list();
		query.setFirstResult((page - 1) * limit);  
		query.setMaxResults(limit); 
        List<Servant> list =query.list();  
		List<ServantVO> listVO=new ArrayList<>();
		for (Servant s : list) {
			ServantVO vo = new ServantVO(s);
			listVO.add(vo);
		}

		Page<ServantVO> pages = new Page<>((page-1)*limit, page, list1.size(), limit, listVO);
		return pages;
	}

	@Override
	public List<Map<String, Object>> getSexMapData(String orgId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(t.id) value, c.name ");
		sql.append("  from a01 t ");
		sql.append("  left join cf_code_info c ");
		sql.append("    on t.A01004 = c.id ");
		sql.append(" where t.DEPARTID = :orgId ");
		sql.append(" group by t.A01004, c.name ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =queryObject.list();
		return list;
	}

	@Override
	public List<Map<String, Object>> getNationMapData(String orgId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(t.id) value, c.name ");
		sql.append("  from a01 t ");
		sql.append("  left join cf_code_info c ");
		sql.append("    on t.A01017 = c.id ");
		sql.append(" where t.DEPARTID = :orgId ");
		sql.append(" group by t.A01017, c.name ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =queryObject.list();
		return list;
	}

	@Override
	public List<Map<String, Object>> getEducationMapData(String orgId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(t.id) value, nvl(t.SH_ZGXL,'未知') name ");
		sql.append("  from a01 t ");
		sql.append(" where t.DEPARTID = :orgId ");
		sql.append(" group by t.SH_ZGXL ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =queryObject.list();
		return list;
	}
	@Override
	public List<Map<String, Object>> getDegreeMapData(String orgId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(t.id) value, nvl(t.SH_ZGXW,'未知') name ");
		sql.append("  from a01 t ");
		sql.append(" where t.DEPARTID = :orgId ");
		sql.append(" group by t.SH_ZGXW ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =queryObject.list();
		return list;
	}
	@Override
	public List<Map<String, Object>> getJobLevelMapData(String orgId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(t.id) value, nvl(c.name, '未知') name ");
		sql.append("  from a01 t ");
		sql.append("  left join cf_code_info c ");
		sql.append("    on t.SH_A0192E = c.id ");
		sql.append(" where t.DEPARTID = :orgId ");
		sql.append(" group by t.SH_A0192E, c.name ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =queryObject.list();
		return list;
	}

	@Override
	public List<Map<String, Object>> getPostMapData(String orgId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(p.A02016A, '未知') name, count(t.id) value ");
		sql.append("  from a01 t ");
		sql.append("  left join a02 p ");
		sql.append("    on t.id = p.servant_id ");
		sql.append(" where t.DEPARTID = :orgId ");
		sql.append(" group by p.A02016A; ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =queryObject.list();
		return list;
	}

}
