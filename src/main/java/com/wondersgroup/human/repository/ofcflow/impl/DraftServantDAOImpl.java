/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantDAOImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:12:00 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:12:00 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.repository.ofcflow.DraftServantDAO;

/** 
 * @ClassName: DraftServantDAOImpl 
 * @Description: TODO
 * @author: GP
 * @date: 2018年6月26日 上午10:12:00
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class DraftServantDAOImpl extends GenericRepositoryImpl<DraftServant> implements DraftServantDAO{

	/** (non Javadoc) 
	 * @Title: getEntityClass
	 * @Description: TODO
	 * @return 
	 * @see com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl#getEntityClass() 
	 */
	@Override
	public Class<DraftServant> getEntityClass() {
		return DraftServant.class;
	}

	/** (non Javadoc) 
	 * @Title: findByCondMap
	 * @Description: TODO
	 * @param condition
	 * @return 
	 * @see com.wondersgroup.human.repository.ofcflow.DraftServantDAO#findByCondMap(java.util.Map) 
	 */
	@Override
	public List<DraftServant> findByCondMap(Map condMap) {
		
		Criteria criteria = currentSession().createCriteria(getEntityClass());
		if (condMap.get("importFlag") != null)
			criteria.add(Restrictions.eq("importFlag", (String)condMap.get("importFlag")));
		if (condMap.get("importRecordId") != null)
			criteria.add(Restrictions.eq("importRecordId", (String) condMap.get("importRecordId")));
		if (condMap.get("name") != null)
			criteria.add(Restrictions.like("name", (String)condMap.get("name"), MatchMode.ANYWHERE));
		if (condMap.get("cardNo") != null)
			criteria.add(Restrictions.eq("cardNo", (String) condMap.get("cardNo")));
		if (condMap.get("yearTip") != null)
			criteria.add(Restrictions.eq("yearTip", (Integer) condMap.get("yearTip")));
		if (condMap.get("publish") != null)
			criteria.add(Restrictions.eq("publish", (Integer) condMap.get("publish")));
		if (condMap.get("status") != null)
			criteria.add(Restrictions.eq("status", (Integer) condMap.get("status")));
		if (condMap.get("draftUnitId") != null)
			criteria.add(Restrictions.eq("draftUnitId", (String) condMap.get("draftUnitId")));
		if (condMap.get("draftDeptId") != null)
			criteria.add(Restrictions.eq("draftDeptId", (String) condMap.get("draftDeptId")));
		if(condMap.get("notDraftUnitIds")!=null)
			criteria.add(Restrictions.not(Restrictions.in("draftUnitId", (List<String>)condMap.get("notDraftUnitIds"))));
		if(condMap.get("employResultId")!=null)
			criteria.add(Restrictions.eq("employResult.id", (long) condMap.get("employResultId")));
		if(condMap.get("ids") != null)
			criteria.add(Restrictions.in("id", (String[])condMap.get("ids")));
		criteria.add(Restrictions.eq("removed", false));
		return criteria.list();
	}
	/**
	 * @Title: publishByRecordId 
	 * @Description: 将导入记录修改为发布状态
	 * @param id
	 * @return: void
	 */
	public void publishByRecordId(String id) {
		this.getHibernateTemplate().execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				String hql = "update DraftServant d set d.publish = 1 where d.importRecordId=:importRecordId";
				Query q = session.createQuery(hql);
				q.setString("importRecordId", id);
				q.executeUpdate();
				return null;
			}
			
		});
		
	}

	@Override
	public String getUnitIdByOrganId(String organId) {
		String sql="select t.id from CS_ORGAN_NODE con left join B01 t on con.id =t.ORGANID where con.id='"+organId+"'";
		Object result = this.currentSession().createSQLQuery(sql).uniqueResult();
		if(null!=result){
			return result.toString();
		}else{
			return null;
		}
		
	}
}
