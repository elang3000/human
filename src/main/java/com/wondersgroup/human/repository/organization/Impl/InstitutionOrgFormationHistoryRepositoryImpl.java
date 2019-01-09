/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: InstitutionOrgFormationHistoryRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.impl 
 * 描述: 事业单位编制历史调整 知识库接口实现类
 * 创建人: jiang   
 * 创建时间: 2018年12月5日15:46:20
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年12月5日15:46:23
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.organization.Impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormationHistory;
import com.wondersgroup.human.repository.organization.InstitutionOrgFormationHistoryRepository;

/** 
 * @ClassName: InstitutionOrgFormationHistoryRepositoryImpl
 * @Description: 事业单位编制历史调整 知识库实现类
 * @author: jiang
 * @date: 2018年12月5日15:46:26
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class InstitutionOrgFormationHistoryRepositoryImpl extends GenericRepositoryImpl<InstitutionOrgFormationHistory> implements InstitutionOrgFormationHistoryRepository{

	public Class<InstitutionOrgFormationHistory> getEntityClass() {
		return InstitutionOrgFormationHistory.class;
	}
}
