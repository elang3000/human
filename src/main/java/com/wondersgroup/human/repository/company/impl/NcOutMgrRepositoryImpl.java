package com.wondersgroup.human.repository.company.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.company.CyOutMgr;
import com.wondersgroup.human.repository.company.NcOutMgrRepository;

@Repository
public class NcOutMgrRepositoryImpl extends GenericRepositoryImpl<CyOutMgr>implements NcOutMgrRepository {

	
	public Class<CyOutMgr> getEntityClass() {
		
		return CyOutMgr.class;
	}

}
