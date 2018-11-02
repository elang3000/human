package com.wondersgroup.human.service.socialworker;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.socialworker.SrEducation;
import com.wondersgroup.human.vo.socialworker.SwEducationVO;

public interface SwEducationService extends GenericService<SrEducation>{
	
	Page<SwEducationVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit);

	/**
	 * 
	 * @Title: executeRestAllTopEducationFlag 
	 * @Description: 将改用户下所有的最高学历标识重置为0，否。
	 * @return: void
	 */
	void executeRestAllTopEducationFlag(String servantId);
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除学历
	 * @param entity 
	 * @see com.wondersgroup.framework.core.service.GenericService#delete(com.wondersgroup.framework.core.bo.GenericEntity)
	 */
	void delete(SrEducation entity);

}
