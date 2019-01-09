package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.JobShiftCollect;
import com.wondersgroup.human.repository.ofcflow.JobShiftCollectRepository;

@Repository
public class JobShiftCollectRepositoryImpl extends GenericRepositoryImpl<JobShiftCollect> implements JobShiftCollectRepository{


	@Override
	public Class<JobShiftCollect> getEntityClass() {
		return JobShiftCollect.class;
	}

}
