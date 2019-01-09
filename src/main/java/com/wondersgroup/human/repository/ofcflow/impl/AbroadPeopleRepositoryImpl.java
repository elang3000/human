/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPeopleRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月11日 下午4:52:07 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月11日 下午4:52:07 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.AbroadPeople;
import com.wondersgroup.human.repository.ofcflow.AbroadPeopleRepository;

/** 
 * @ClassName: AbroadPeopleRepositoryImpl 
 * @Description: 因公出国人员知识库实现
 * @author: lihao
 * @date: 2018年12月11日 下午4:52:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class AbroadPeopleRepositoryImpl extends GenericRepositoryImpl<AbroadPeople> implements AbroadPeopleRepository{

	@Override
	public Class<AbroadPeople> getEntityClass() {
		return AbroadPeople.class;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofcflow.AbroadPeopleRepository#selectAbroadTimeByServantId(java.lang.String) 
	 */
	@Override
	public int selectAbroadTimeByServantId(String id,String abroadYearId) {
		StringBuilder sql=new StringBuilder();
		sql.append("select abp.id from HUMAN_ABROAD_PEOPLE abp  ");
		sql.append("left join HUMAN_ABROAD ab on ab.id = abp.ABROAD_ID  ");
		sql.append("left join HUMAN_ABROAD_YEAR_PLAN abyp on abyp.id = ab.ABROAD_YEAR_ID  ");
		sql.append("where abp.removed = 'N' and  ab.removed = 'N' and  ab.removed = 'N' ");
		sql.append("and sysdate between abyp.APPROVE_START_DATE and abyp.APPROVE_END_DATE ");
		sql.append("and abp.servant_id = :id ");
		sql.append("and abyp.id != :abroadYearId ");
		Query query =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		query.setParameter("id", id);
		query.setParameter("abroadYearId", abroadYearId);
	    List<AbroadPeople> list=query.list();
	    return list.size();
	}
}
