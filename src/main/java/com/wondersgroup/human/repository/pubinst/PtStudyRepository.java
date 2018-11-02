/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: StudyRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: 人员信息子表-学习（培训、进修） 知识库接口
 * 创建人: jiang   
 * 创建时间: 2018年8月21日15:06:09
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月21日15:06:11
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.pubinst;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.pubinst.PtStudy;

/**
 * 
 * @ClassName: StudyRepository 
 * @Description: 人员信息子表-学习（培训、进修） 知识库接口
 * @author: jiang
 * @date: 2018年8月21日15:06:15
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface PtStudyRepository extends GenericRepository<PtStudy> {
	
}
