package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.JobShiftDepose;
import com.wondersgroup.human.repository.ofcflow.JobShiftDeposeRepository;

@Repository
public class JobShiftDeposeRepositoryImpl extends GenericRepositoryImpl<JobShiftDepose> implements JobShiftDeposeRepository{

	@Override
	public Class<JobShiftDepose> getEntityClass() {
		return JobShiftDepose.class;
	}

}
