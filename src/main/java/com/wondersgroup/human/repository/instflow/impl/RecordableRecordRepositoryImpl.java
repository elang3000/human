package com.wondersgroup.human.repository.instflow.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.instflow.RecordableRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.repository.instflow.RecordableRecordRepository;

@Repository
public class RecordableRecordRepositoryImpl extends GenericRepositoryImpl<RecordableRecord> implements RecordableRecordRepository{

	@Override
	public RecordableRecord findRecordableRecordByPid(String id) {
		Criteria criteria = currentSession().createCriteria(RecordableRecord.class);
		criteria.add(Restrictions.eq("publicInstitution.id", id));
		criteria.add(Restrictions.eq("removed", false));
		if ( criteria.list() != null &&  criteria.list().size() > 0) {
			return (RecordableRecord) criteria.list().get(0);
		}
		return null;
	}

	@Override
	public Class<RecordableRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return RecordableRecord.class;
	}

	@Override
	public RecordableRecord operationAlterFlag(PublicInstitution publicInstitution) {
		Criteria criteria = currentSession().createCriteria(RecordableRecord.class);
		criteria.add(Restrictions.eq("publicInstitution.id", publicInstitution.getId()));
		criteria.add(Restrictions.eq("removed", false));
		criteria.add(Restrictions.isNotNull("planState")); //表示未处理完成的流程主表记录
		if ( criteria.list() != null &&  criteria.list().size() > 0) {
			return (RecordableRecord) criteria.list().get(0);
		}
		return null;
	}

}
