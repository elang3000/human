/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: StudyRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: 人员信息子表-学习（培训、进修） 知识库实现类
 * 创建人: jiang   
 * 创建时间: 2018年8月21日15:08:40
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月21日15:08:43
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.pubinst.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.pubinst.PtStudy;
import com.wondersgroup.human.repository.pubinst.PtStudyRepository;

/**
 * @ClassName: StudyRepositoryImpl 
 * @Description: 人员信息子表-学习（培训、进修） 知识库实现类
 * @author: jiang
 * @date: 2018年8月21日15:08:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class PtStudyRepositoryImpl extends GenericRepositoryImpl<PtStudy> implements PtStudyRepository {
	
	public Class<PtStudy> getEntityClass() {
		
		return PtStudy.class;
	}
}
