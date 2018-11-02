/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: CompetenceRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: 人员信息子表-学位 知识库实现类
 * 创建人: jiang   
 * 创建时间: 2018年7月2日14:19:24
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:19:26
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofc.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofc.Competence;
import com.wondersgroup.human.repository.ofc.CompetenceRepository;

/**
 * @ClassName: DegreeRepositoryImpl 
 * @Description: 人员信息子表-专业技术资格 知识库实现类
 * @author: jiang
 * @date: 2018年7月2日14:19:38
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class CompetenceRepositoryImpl extends GenericRepositoryImpl<Competence> implements CompetenceRepository {
	
	public Class<Competence> getEntityClass() {
		
		return Competence.class;
	}
}
