/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: JobLevelRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: 人员信息子表-职级 知识库接口
 * 创建人: jiang   
 * 创建时间: 2018年6月12日10:02:25
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日10:02:30
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.pubinst;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.pubinst.PtJobLevel;

/**
 * 
 * @ClassName: JobLevelRepository 
 * @Description: 人员信息子表-录用 知识库接口
 * @author: jiang
 * @date: 2018年6月12日10:02:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface PtJobLevelRepository extends GenericRepository<PtJobLevel> {

	/**
	 * 
	 * @Title: updateServantAllCurrentLevelBySid 
	 * @Description: 根据公务员ID，修改该公务员职级子集下所有的现行职级标识
	 * @param servantId
	 * @param codeInfo
	 * @return: void
	 */
	void updateAllCurrentLevelBySid(String servantId, CodeInfo codeInfo);
	
}
