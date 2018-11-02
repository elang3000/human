/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: JobLevelService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc 
 * 描述: 人员信息子表-职级 服务接口
 * 创建人: jiang   
 * 创建时间: 2018年6月12日10:12:54
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年6月12日10:12:59
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.pubinst;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.pubinst.PtJobLevel;
import com.wondersgroup.human.vo.pubinst.PtJobLevelVO;

/** 
 * @ClassName: JobLevelService 
 * @Description: 人员信息子表-职级 服务接口
 * @author: jiang
 * @date: 2018年6月12日10:13:14
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface PtJobLevelService extends GenericService<PtJobLevel>{

	Page<PtJobLevelVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit);

	/**
	 * 
	 * @Title: executeRestAllCurrentLevelFlag 
	 * @Description: 将改用户下所有的现行职级标识重置为0，否。
	 * @return: void
	 */
	void executeRestAllCurrentLevelFlag(String servantId);
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除职级
	 * @param entity 
	 * @see com.wondersgroup.framework.core.service.GenericService#delete(com.wondersgroup.framework.core.bo.GenericEntity)
	 */
	void delete(PtJobLevel entity);
}
