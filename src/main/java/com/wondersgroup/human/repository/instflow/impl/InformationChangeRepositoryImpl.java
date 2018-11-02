package com.wondersgroup.human.repository.instflow.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.instflow.RecordableRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.repository.instflow.InformationChangeRepository;

@Repository
public class InformationChangeRepositoryImpl extends GenericRepositoryImpl<InformationChange>implements InformationChangeRepository{

	@Override
	public InformationChange findInformationChangePid(String id) {
		Criteria criteria = currentSession().createCriteria(InformationChange.class);
		criteria.add(Restrictions.eq("informationchange.id", id));
		criteria.add(Restrictions.eq("removed", false));
		if ( criteria.list() != null &&  criteria.list().size() > 0) {
			return (InformationChange) criteria.list().get(0);
		}
		return null;
	}

	@Override
	public Class<InformationChange> getEntityClass() {
		// TODO Auto-generated method stub
		return InformationChange.class;
	}

	@Override
	public InformationChange  operationAlterFlag(PublicInstitution publicInst) {
		Criteria criteria = currentSession().createCriteria(InformationChange.class);
		criteria.add(Restrictions.eq("publicInstitution.id", publicInst.getId()));
		criteria.add(Restrictions.eq("removed", false));
		criteria.add(Restrictions.isNotNull("planState")); //表示未处理完成的流程主表记录
		if ( criteria.list() != null &&  criteria.list().size() > 0) {
			return (InformationChange) criteria.list().get(0);
		}
		return null;
	}

}
