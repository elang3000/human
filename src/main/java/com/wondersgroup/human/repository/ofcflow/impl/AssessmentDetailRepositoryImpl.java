package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.AssessmentDetail;
import com.wondersgroup.human.repository.ofcflow.AssessmentDetailRepository;

@Repository
public class AssessmentDetailRepositoryImpl extends GenericRepositoryImpl<AssessmentDetail> implements AssessmentDetailRepository{

	@Override
	public Class<AssessmentDetail> getEntityClass() {
		return AssessmentDetail.class;
	}
	
}
