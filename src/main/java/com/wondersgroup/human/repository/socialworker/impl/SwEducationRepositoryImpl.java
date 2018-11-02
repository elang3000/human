package com.wondersgroup.human.repository.socialworker.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.socialworker.SrEducation;
import com.wondersgroup.human.repository.socialworker.SwEducationRepository;

@Repository
public class SwEducationRepositoryImpl extends GenericRepositoryImpl<SrEducation> implements SwEducationRepository{

	@Override
	public void updateAllEducationTopTipBySid(String servantId, CodeInfo codeInfo) {

		Query query = this.currentSession().createQuery("update com.wondersgroup.human.bo.socialworker.SrEducation set topFlag = :topFlag where socialWorker.id = :servantId");
		query.setParameter("topFlag", codeInfo);
		query.setParameter("servantId",servantId);
		query.executeUpdate();
		
	}

	@Override
	public Class<SrEducation> getEntityClass() {
		// TODO Auto-generated method stub
		return SrEducation.class;
	}

}
