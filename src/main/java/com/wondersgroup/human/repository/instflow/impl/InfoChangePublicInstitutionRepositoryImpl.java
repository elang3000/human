package com.wondersgroup.human.repository.instflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.instflow.InfoChangePublicInstitution;
import com.wondersgroup.human.repository.instflow.InfoChangePublicInstitutionRepository;

@Repository
public class InfoChangePublicInstitutionRepositoryImpl extends GenericRepositoryImpl<InfoChangePublicInstitution>implements InfoChangePublicInstitutionRepository{

	@Override
	public Class<InfoChangePublicInstitution> getEntityClass() {
		// TODO Auto-generated method stub
		return InfoChangePublicInstitution.class;
	}

}
