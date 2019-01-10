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
import com.wondersgroup.human.bo.ofc.PeopleGenericy;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.dto.analysis.ServantParam;
import com.wondersgroup.human.dto.analysis.ServantQueryParam;
import com.wondersgroup.human.repository.ofc.ServantRepository;
import com.wondersgroup.human.vo.ofc.PeopleVO;
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
	 * @see com.wondersgroup.human.repository.ofc.ServantRepository#queryServantInfoBySeniorCondation(java.util.List,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<ServantVO> queryServantInfoBySeniorCondation(List<ServantParam> spList, Map<String, String> m,
	        Integer page, Integer limit) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from A01 where removed ='N' and id in (");
		sql.append("select distinct a1.id from a01 a1 ");
		for (String key : m.keySet()) {// keySet获取map集合key的集合  然后在遍历key即可
			String value = m.get(key).toString();//
			sql.append("left join " + key + " " + value + " on a1.id = " + value + ".servant_id ");
		}
		sql.append("where 1=1 and a1.removed = 'N' ");
		for (String key : m.keySet()) {// keySet获取map集合key的集合  然后在遍历key即可
			String value = m.get(key).toString();//
			sql.append("and " + value + ".removed = 'N' ");
		}
		for (ServantParam s : spList) {
			sql.append(" AND  " + s.getCode1() + " " + s.getCode3() + " '" + s.getCode2() + "' ");
		}
		sql.append(" group by a1.id) order by SH_REPORT_DATE desc");
		
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString())
		        .addEntity(Servant.class);
		List<Servant> list1 = query.list();
		query.setFirstResult((page - 1) * limit);
		query.setMaxResults(limit);
		List<Servant> list = query.list();
		List<ServantVO> listVO = new ArrayList<>();
		for (Servant s : list) {
			ServantVO vo = new ServantVO(s);
			listVO.add(vo);
		}
		
		Page<ServantVO> pages = new Page<>((page - 1) * limit, page, list1.size(), limit, listVO);
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
		Query queryObject = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = queryObject.list();
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
		Query queryObject = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = queryObject.list();
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getEducationMapData(String orgId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(t.id) value, nvl(t.SH_ZGXL,'未知') name ");
		sql.append("  from a01 t ");
		sql.append(" where t.DEPARTID = :orgId ");
		sql.append(" group by t.SH_ZGXL ");
		Query queryObject = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = queryObject.list();
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getDegreeMapData(String orgId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(t.id) value, nvl(t.SH_ZGXW,'未知') name ");
		sql.append("  from a01 t ");
		sql.append(" where t.DEPARTID = :orgId ");
		sql.append(" group by t.SH_ZGXW ");
		Query queryObject = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = queryObject.list();
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
		Query queryObject = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = queryObject.list();
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
		Query queryObject = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setParameter("orgId", orgId);
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = queryObject.list();
		return list;
	}
	
	/**
	 * (non Javadoc)
	 * @Title: querybyBaseSql
	 * @Description: TODO
	 * @param orgIds
	 * @return
	 * @see com.wondersgroup.human.repository.ofc.ServantRepository#querybyBaseSql(java.lang.String)
	 */
	@Override
	public Map<String, Integer> statistServantSchoolNature(String isHold, String isflag, List<String> organNodeIds) {
		
		String querybase = "select count(*) from a08 a8 left join a01 a1 on a8.servant_id=a1.id where "
				+ " a1.a01063=:zgxl "
				+" and a1.DEPARTID IN (:departId)" ;
		String queryString1 = " and a8.A08034 =:nineflag and a8.nine_eight_five_flag=:nineflag";
		SQLQuery query = currentSession().createSQLQuery(querybase + queryString1);
		query.setString("zgxl", isHold);
		query.setString("nineflag", isflag);
		query.setParameterList("departId", organNodeIds);
		List queryResult = query.list();
		Map<String, Integer> result = new HashMap<String, Integer>();
		Object nineflag = queryResult.get(0);
		Integer nineflagint = Integer.parseInt(String.valueOf(nineflag));
		result.put("985大学", nineflagint);
		
		queryString1 = " and a8.TWO_ONE_ONE_FLAG=:nineflag";
		query = currentSession().createSQLQuery(querybase + queryString1);
		query.setString("zgxl", isHold);
		query.setString("nineflag", isflag);
		query.setParameterList("departId", organNodeIds);
		queryResult = query.list();
		nineflag = queryResult.get(0);
		nineflagint = Integer.parseInt(String.valueOf(nineflag));
		result.put("211大学", nineflagint);
		
		queryString1 = " and a8.A08034 =:nineflag  and a8.DOUBLE_FIRST_RATE_FlAG=:nineflag";
		query = currentSession().createSQLQuery(querybase + queryString1);
		query.setString("zgxl", isHold);
		query.setString("nineflag", isflag);
		query.setParameterList("departId", organNodeIds);
		queryResult = query.list();
		nineflag = queryResult.get(0);
		nineflagint = Integer.parseInt(String.valueOf(nineflag));
		result.put("双一流大学", nineflagint);
		
		queryString1 = " and (a8.nine_eight_five_flag!=:nineflag or a8.nine_eight_five_flag is null)  and (a8.TWO_ONE_ONE_FLAG!=:nineflag or a8.TWO_ONE_ONE_FLAG is null) and (a8.DOUBLE_FIRST_RATE_FlAG!=:nineflag or a8.DOUBLE_FIRST_RATE_FlAG is null)";
		query = currentSession().createSQLQuery(querybase + queryString1);
		query.setString("zgxl", isHold);
		query.setString("nineflag", isflag);
		query.setParameterList("departId", organNodeIds);
		queryResult = query.list();
		nineflag = queryResult.get(0);
		nineflagint = Integer.parseInt(String.valueOf(nineflag));
		result.put("其他", nineflagint);
		
		return result;
	}

	/** (non Javadoc) 
	 * @Title: statistServantisshanghailocal
	 * @Description: TODO
	 * @param isHold
	 * @param organNodeIds
	 * @return 
	 * @see com.wondersgroup.human.repository.ofc.ServantRepository#statistServantisshanghailocal(java.lang.String, java.util.List) 
	 */
	@Override
	public Map<String, Integer> statistServantisshanghailocal(String isHold, List<String> organNodeIds) {
		String querybase = "select count(*) from a01 a1 where a1.A01085 like '310%' "
				+ " and a1.a01063=:zgxl "
				+" and a1.DEPARTID IN (:departId)" ;
		SQLQuery query = currentSession().createSQLQuery(querybase);
		query.setString("zgxl", isHold);
		query.setParameterList("departId", organNodeIds);
		List queryResult = query.list();
		Map<String, Integer> result = new HashMap<String, Integer>();
		Object nineflag = queryResult.get(0);
		Integer nineflagint = Integer.parseInt(String.valueOf(nineflag));
		result.put("310人员", nineflagint);
		
		querybase = "select count(*) from a01 a1 where 1=1 "
				+ " and a1.a01063=:zgxl "
				+" and a1.DEPARTID IN (:departId)" ;
		query = currentSession().createSQLQuery(querybase);
		query.setString("zgxl", isHold);
		query.setParameterList("departId", organNodeIds);
		queryResult = query.list();
		nineflag = queryResult.get(0);
		Integer nineflagint1 = Integer.parseInt(String.valueOf(nineflag));
		
		result.put("非310人员", (nineflagint1-nineflagint));
		
		return result;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.ServantRepository#queryServantInfoBySeniorCondation(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ServantVO> queryServantInfoBySeniorCondation(List<String> pList, List<String> l, Integer page,
			Integer limit) {
StringBuffer sql = new StringBuffer();
		
		sql.append("select * from A01 where removed ='N' and id in (");
		sql.append("select distinct a1.id from a01 a1 ");
		for (String s : l) {
			sql.append("left join " + s + " on a1.id = " + s + ".servant_id ");
		}
		sql.append("where 1=1 and a1.removed = 'N' ");
		for (String s : l) {
			sql.append("and " + s + ".removed = 'N' ");
		}
		for (String s  : pList) {
			sql.append(" AND  ("+s+") ");
		}
		sql.append(" group by a1.id) order by SH_REPORT_DATE desc");
		
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString())
		        .addEntity(Servant.class);
		List<Servant> list1 = query.list();
		query.setFirstResult((page - 1) * limit);
		query.setMaxResults(limit);
		List<Servant> list = query.list();
		List<ServantVO> listVO = new ArrayList<>();
		for (Servant s : list) {
			ServantVO vo = new ServantVO(s);
			listVO.add(vo);
		}
		
		Page<ServantVO> pages = new Page<>((page - 1) * limit, page, list1.size(), limit, listVO);
		return pages;
	}
	
	/** (non Javadoc) 
	 * @Title: queryPeopleInfo
	 * @Description: TODO
	 * @param spList
	 * @param page
	 * @param limit
	 * @return 
	 * @see com.wondersgroup.human.repository.ofc.ServantRepository#queryPeopleInfo(java.util.List, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<PeopleVO> queryPeopleInfo(ServantQueryParam spList,String itype, Integer page, Integer limit) {
		
		StringBuffer sql = new StringBuffer();
		StringBuilder countsql=new StringBuilder();
		sql.append("select * from V_PEOPLE_INFOS v where  1=1");
		if (StringUtils.isNotBlank(spList.getName())) {
			sql.append(" and v.name like '%");
			sql.append(spList.getName());
			sql.append("%'");
		}
		if(StringUtils.isNotBlank(spList.getDepartName())){//部门名称
			sql.append(" and v.departName like '%");
			sql.append(spList.getDepartName());
			sql.append("%'");
		}
		if(StringUtils.isNotBlank(spList.getCardNo())){//身份证号
			sql.append(" and v.cardNo = '");
			sql.append(spList.getCardNo());
			sql.append("'");
		}
		if(spList.getSex()!=null&&StringUtils.isNotBlank(spList.getSex().getId())){//性别
			sql.append(" and v.sex = '");
			sql.append(spList.getSex().getId());
			sql.append("'");
		}
		if(StringUtils.isNotBlank(itype)){//身份证号
			sql.append(" and v.itype = '");
			sql.append(itype);
			sql.append("'");
		}
		
		countsql.append("select count(*) from ("); 
		countsql.append(sql.toString());
		countsql.append(")");
		Query countquery=this.getSessionFactory().getCurrentSession().createSQLQuery(countsql.toString());
		int icount=((Number) countquery.uniqueResult()).intValue();

		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString())
		        .addEntity(PeopleGenericy.class);
		query.setFirstResult((page - 1) * limit);
		query.setMaxResults(limit);
		List<PeopleGenericy> list = query.list();
		List<PeopleVO> listVO = new ArrayList<>();
		for (PeopleGenericy s : list) {
			PeopleVO vo = new PeopleVO(s);
			listVO.add(vo);
		}
		
		Page<PeopleVO> pages = new Page<>((page - 1) * limit, page, icount, limit, listVO);
		return pages;
	}
}
