package com.wondersgroup.human.repository.company.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.repository.company.NationalCompanyRepository;

@Repository
public class NationalCompanyRepositoryImpl extends GenericRepositoryImpl<NationalCompany> implements NationalCompanyRepository{

	public Class<NationalCompany> getEntityClass() {
		return NationalCompany.class;
	}

}
