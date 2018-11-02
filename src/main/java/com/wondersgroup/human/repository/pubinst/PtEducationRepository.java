/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EducationRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: 人员信息子表-学历 知识库接口
 * 创建人: jiang   
 * 创建时间: 2018年7月2日14:44:16
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:44:19
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.pubinst;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.pubinst.PtEducation;

/**
 * 
 * @ClassName: EducationRepository 
 * @Description: 人员信息子表-学历 知识库接口
 * @author: jiang
 * @date: 2018年7月2日14:44:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface PtEducationRepository extends GenericRepository<PtEducation> {

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
