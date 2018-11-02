package com.wondersgroup.human.service.socialworker;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.socialworker.SrPost;
import com.wondersgroup.human.vo.socialworker.SwPostVO;

public interface SwPostService extends GenericService<SrPost>{

	
	/**
	 * 
	 * @Title: getPage 
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return
	 * @return: Page<PostVO>
	 */
	public Page<SwPostVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);
	
	/**
	 * 
	 * @Title: executeRestAllTopPostFlag 
	 * @Description: 将改用户下所有的最高职位标识重置为0，否。
	 * @return: void
	 */
	void executeRestAllTopPostFlag(String servantId);
	
	/**
	 * 
	 * @Title: executeRestAllNowPostFlag 
	 * @Description: 将改用户下所有的现任职位标识重置为0，否。
	 * @return: void
	 */
	void executeRestAllNowPostFlag(String servantId);
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除职务
	 * @param entity 
	 * @see com.wondersgroup.framework.core.service.GenericService#delete(com.wondersgroup.framework.core.bo.GenericEntity)
	 */
	void delete(SrPost post);
}
