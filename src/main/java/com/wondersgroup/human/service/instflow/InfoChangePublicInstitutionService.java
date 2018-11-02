package com.wondersgroup.human.service.instflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.instflow.InfoChangePublicInstitution;
import com.wondersgroup.human.vo.instflow.InfoChangePublicInstitutionVO;

public interface InfoChangePublicInstitutionService extends GenericService<InfoChangePublicInstitution>{

	
	
	public Page<InfoChangePublicInstitutionVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);

}
