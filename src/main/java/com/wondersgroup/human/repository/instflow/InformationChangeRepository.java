package com.wondersgroup.human.repository.instflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;

public interface InformationChangeRepository extends GenericRepository<InformationChange>{

	public InformationChange findInformationChangePid(String id);

	public InformationChange operationAlterFlag(PublicInstitution publicInst);
}
