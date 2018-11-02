/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EducationRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: 人员信息子表-学历 知识库实现类
 * 创建人: jiang   
 * 创建时间: 2018年7月2日14:45:00
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:45:04
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.pubinst.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.pubinst.PtEducation;
import com.wondersgroup.human.repository.pubinst.PtEducationRepository;

/**
 * @ClassName: EducationRepositoryImpl 
 * @Description: 人员信息子表-学历 知识库实现类
 * @author: jiang
 * @date: 2018年7月2日14:45:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class PtEducationRepositoryImpl extends GenericRepositoryImpl<PtEducation> implements PtEducationRepository {
	
	public Class<PtEducation> getEntityClass() {
		
		return PtEducation.class;
	}

	@Override
	public void updateAllEducationTopTipBySid(String Id, CodeInfo codeInfo) {
		
		Query query = this.currentSession().createQuery("update com.wondersgroup.human.bo.pubinst.PtEducation set topFlag = :topFlag where publicInstitution.id = :Id");
		query.setParameter("topFlag", codeInfo);
		query.setParameter("Id",Id);
		query.executeUpdate();
	}
}
