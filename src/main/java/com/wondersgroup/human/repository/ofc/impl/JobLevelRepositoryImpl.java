/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: JobLevelRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: 人员信息子表-试用 知识库实现类
 * 创建人: jiang   
 * 创建时间: 2018年6月12日10:08:49
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日10:08:52
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofc.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.repository.ofc.JobLevelRepository;

/**
 * @ClassName: JobLevelRepositoryImpl 
 * @Description: 人员信息子表-职级 知识库实现类
 * @author: jiang
 * @date: 2018年6月12日10:09:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class JobLevelRepositoryImpl extends GenericRepositoryImpl<JobLevel> implements JobLevelRepository {
	
	public Class<JobLevel> getEntityClass() {
		
		return JobLevel.class;
	}
	
	@Override
	public void updateServantAllCurrentLevelBySid(String servantId, CodeInfo codeInfo) {
		
		Query query = this.currentSession().createQuery("update com.wondersgroup.human.bo.ofc.JobLevel set currentIdentification = :currentIdentification where servant.id = :servantId");
		query.setParameter("currentIdentification", codeInfo);
		query.setParameter("servantId",servantId);
		query.executeUpdate();
	}

}
