package com.wondersgroup.human.repository.socialworker;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.socialworker.SrEducation;

public interface SwEducationRepository extends GenericRepository<SrEducation> {

	/**
	 * 
	 * @Title: updateServantAllEducationTopTipBySid 
	 * @Description: 根据公务员ID，修改该公务员学历子集下所有的 最高学历标识
	 * @param servantId
	 * @param codeInfo
	 * @return: void
	 */
	void updateAllEducationTopTipBySid(String servantId, CodeInfo codeInfo);

}
