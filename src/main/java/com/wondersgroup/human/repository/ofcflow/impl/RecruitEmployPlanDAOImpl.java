/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanDAOImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午10:46:41 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午10:46:41 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan;
import com.wondersgroup.human.bo.ofcflow.RecruitYearPlan;
import com.wondersgroup.human.repository.ofcflow.RecruitEmployPlanDAO;

/** 
 * @ClassName: RecruitEmployPlanDAOImpl 
 * @Description: TODO
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午10:46:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class RecruitEmployPlanDAOImpl  extends GenericRepositoryImpl<RecruitEmployPlan> implements RecruitEmployPlanDAO{

	/** (non Javadoc) 
	 * @Title: getEntityClass
	 * @Description: TODO
	 * @return 
	 * @see com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl#getEntityClass() 
	 */
	@Override
	public Class<RecruitEmployPlan> getEntityClass() {

		return RecruitEmployPlan.class;
	}
	
	@Override
	public Integer valiateEmployPlanPostNumber(String planId, String objectTypeId) {
		SQLQuery query = currentSession().createSQLQuery("SELECT T1.NUM1 - CASE WHEN T2.NUM2 IS NOT NULL THEN T2.NUM2 ELSE 0 END FROM " +
				"(SELECT SUM (PI.PLAN_EMPLOY_NUM) AS NUM1 FROM HUMAN_RECRUIT_EMPLOY_PLAN_INFO PI WHERE PI.REMOVED = 'N' AND PI.OBJECTTYPE_ID = :objectTypeId AND PI.PLAN_ID = :planId) T1," +
				"(SELECT SUM (CASE WHEN PLAN_EMPLOY_NUM IS NOT NULL THEN PLAN_EMPLOY_NUM ELSE 0 END) AS NUM2 FROM HUMAN_RECRUIT_POST WHERE PLAN_ID = :planId AND OBJECTTYPE_ID = :objectTypeId AND REMOVED = 'N') T2");
		query.setParameter("planId", planId);
		query.setParameter("objectTypeId", objectTypeId);
		BigDecimal reuslt = (BigDecimal) query.uniqueResult();
		
		return reuslt.intValue() ;
	}

	/** (non Javadoc) 
	 * @Title: getRecruitEmployPlanByEmployOrgan
	 * @Description: TODO
	 * @param yearPlan
	 * @param employOrgan
	 * @return 
	 * @see com.wondersgroup.human.repository.ofcflow.RecruitEmployPlanDAO#getRecruitEmployPlanByEmployOrgan(com.wondersgroup.human.bo.ofcflow.RecruitYearPlan, com.wondersgroup.framework.organization.bo.OrganNode) 
	 */
	@Override
	public BigDecimal getRecruitEmployPlanByEmployOrgan(RecruitYearPlan yearPlan, OrganNode employOrgan) {
		SQLQuery query = currentSession().createSQLQuery("SELECT count(*) from HUMAN_RECRUIT_EMPLOY_PLAN p where p.EMPLOYORGAN_ID =:employOrgan and p.YEARPLAN_ID = :yearPlan and removed='N'");
		query.setParameter("employOrgan", employOrgan.getId()+"");
		query.setParameter("yearPlan", yearPlan.getId());
		BigDecimal reuslt = (BigDecimal) query.uniqueResult();
		
		return reuslt ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RecruitEmployPlan getLastRecruitEmployPlanByRecruitOrganWithPlanState(RecruitYearPlan yearPlan, OrganNode organNode, List<Integer> planStates, String loc) {
		
		Criteria criteria = currentSession().createCriteria(RecruitEmployPlan.class);
		criteria.add(Restrictions.eq("yearPlan", yearPlan));
		criteria.add(Restrictions.eq("recruitOrgan", organNode));
		if (planStates != null && !planStates.isEmpty()) {
			criteria.add(Restrictions.in("planState", planStates));
		}
//		if (StringUtils.isNotBlank(loc)) {
//			criteria.add(Restrictions.eq("loc", loc));
//		} else {
//			criteria.add(Restrictions.isNull("loc"));
//		}
		//criteria.addOrder(Order.desc("operateTime"));
		
		List<RecruitEmployPlan> plans = criteria.list();
		if (plans.isEmpty()) {
			return null;
		} else {
			return plans.get(0);
		}
	}
	
	public List<RecruitEmployPlan> getRecruitEmployPlanByRecruitOrgan(RecruitYearPlan yearPlan, OrganNode organNode,
	        String loc) {

		Criteria criteria = currentSession().createCriteria(RecruitEmployPlan.class);
		criteria.add(Restrictions.eq("yearPlan", yearPlan));
		criteria.add(Restrictions.eq("recruitOrgan", organNode));
		criteria.add(Restrictions.eq("removed", false));
//		if (StringUtils.isNotBlank(loc)) {
//			criteria.add(Restrictions.eq("loc", loc));
//		} else {
//			criteria.add(Restrictions.isNull("loc"));
//		}
		return criteria.list();
	}
	
}
