package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.repository.ofcflow.JobShiftRepository;

@Repository
public class JobShiftRepositoryImpl extends GenericRepositoryImpl<JobShift> implements JobShiftRepository{

	@Override
	public Class<JobShift> getEntityClass() {
		return JobShift.class;
	}

}
