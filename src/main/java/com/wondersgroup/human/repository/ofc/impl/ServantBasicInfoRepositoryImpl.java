/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServantBasicInfoRepositoryImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年9月24日 下午3:55:34
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年9月24日 下午3:55:34
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.repository.ofc.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.bo.ofc.ServantBasicInfo;
import com.wondersgroup.human.repository.ofc.ServantBasicInfoRepository;

/**
 * @ClassName: ServantBasicInfoRepositoryImpl
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年9月24日 下午3:55:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class ServantBasicInfoRepositoryImpl extends HibernateDaoSupport implements ServantBasicInfoRepository {
	
	@Override
	public Class<ServantBasicInfo> getEntityClass() {
		
		return ServantBasicInfo.class;
	}
	
	@Autowired
	public void setDefaultSessionFactory(SessionFactory sessionFactory) {
		
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * @see com.wondersgroup.human.repository.ofc.ServantBasicInfoRepository#load(java.io.Serializable)
	 */
	@Override
	public ServantBasicInfo load(Serializable id) {
		
		return currentSession().load(getEntityClass(), id);
	}
	
	/**
	 * @see com.wondersgroup.human.repository.ofc.ServantBasicInfoRepository#get(java.io.Serializable)
	 */
	@Override
	public ServantBasicInfo get(Serializable id) {
		
		return currentSession().get(getEntityClass(), id);
	}
	
	/**
	 * @see com.wondersgroup.human.repository.ofc.ServantBasicInfoRepository#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ServantBasicInfo> findByCriteria(DetachedCriteria detachedCriteria) {
		
		Criteria criteria = detachedCriteria.getExecutableCriteria(currentSession());
		return criteria.list();
	}
	
	/**
	 * @see com.wondersgroup.human.repository.ofc.ServantBasicInfoRepository#findByCriteria(org.hibernate.criterion.DetachedCriteria,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<ServantBasicInfo> findByCriteria(DetachedCriteria detachedCriteria, Integer pageNumber,
	        Integer pageSize) {
		
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		Integer startIndex = Page.getStartOfAnyPage(pageNumber, pageSize);
		
		Criteria criteria = detachedCriteria.getExecutableCriteria(this.currentSession());
		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
		
		Projection projection = criteriaImpl.getProjection();
		Integer totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(projection);
		
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		
		@SuppressWarnings("unchecked")
		List<ServantBasicInfo> list = criteria.setFirstResult(startIndex - 1).setMaxResults(pageSize).list();
		@SuppressWarnings("null")
		Integer avaCount = (list == null && list.isEmpty()) ? 0 : list.size();
		
		return new Page<ServantBasicInfo>(startIndex, avaCount, totalCount, pageSize, list);
	}
	
}
