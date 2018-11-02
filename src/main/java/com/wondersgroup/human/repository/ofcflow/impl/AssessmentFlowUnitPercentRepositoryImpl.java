package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowUnitPercent;
import com.wondersgroup.human.repository.ofcflow.AssessmentFlowUnitPercentRepository;

@Repository
public class AssessmentFlowUnitPercentRepositoryImpl extends GenericRepositoryImpl<AssessmentFlowUnitPercent> implements AssessmentFlowUnitPercentRepository{

	@Override
	public Class<AssessmentFlowUnitPercent> getEntityClass() {
		return AssessmentFlowUnitPercent.class;
	}
	
}
