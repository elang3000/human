/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
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
package com.wondersgroup.human.repository.instflow.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.geo.Circle;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.repository.instflow.AlternatingRotationRepository;

/** 
 * @ClassName: AlternatingRotationRepositoryImpl
 * @Description: 交流轮岗
 * @author: lyf
 * @date: 2018年9月14日 上午11:22:54
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class AlternatingRotationRepositoryImpl extends GenericRepositoryImpl<AlternatingRotation> implements AlternatingRotationRepository{

	public Class<AlternatingRotation> getEntityClass() {
		return AlternatingRotation.class;
	}

	@Override
	public AlternatingRotation findAlternatingRotationByPid(String id) {
		Criteria criteria = currentSession().createCriteria(AlternatingRotation.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("removed", false));
		if ( criteria.list() != null &&  criteria.list().size() > 0) {
			return (AlternatingRotation) criteria.list().get(0);
		}
		return null;
	}

	@Override
	public AlternatingRotation operationAlterFlag(PublicInstitution publicInstitution) {
		Criteria criteria = currentSession().createCriteria(AlternatingRotation.class);
		criteria.add(Restrictions.eq("publicInstitution.id", publicInstitution.getId()));
		criteria.add(Restrictions.eq("removed", false));
		criteria.add(Restrictions.isNotNull("planState")); //表示未处理完成的流程主表记录
		if ( criteria.list() != null &&  criteria.list().size() > 0) {
			return (AlternatingRotation) criteria.list().get(0);
		}
		return null;
	}
}
