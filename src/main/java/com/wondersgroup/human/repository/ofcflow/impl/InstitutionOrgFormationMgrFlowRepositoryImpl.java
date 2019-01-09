/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: InstitutionOrgFormationMgrFlowRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.impl 
 * 描述: 事业单位编制信息维护流程表单  知识库接口实现类
 * 创建人: jiang   
 * 创建时间: 2018年12月5日14:50:16
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年12月5日14:50:28
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.InstitutionOrgFormationMgrFlow;
import com.wondersgroup.human.repository.ofcflow.InstitutionOrgFormationMgrFlowRepository;

/** 
 * @ClassName: InstitutionOrgFormationMgrFlowRepositoryImpl
 * @Description: 事业单位编制信息维护流程表单 知识库实现类
 * @author: jiang
 * @date: 2018年12月5日14:50:11
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class InstitutionOrgFormationMgrFlowRepositoryImpl extends GenericRepositoryImpl<InstitutionOrgFormationMgrFlow> implements InstitutionOrgFormationMgrFlowRepository{

	public Class<InstitutionOrgFormationMgrFlow> getEntityClass() {
		return InstitutionOrgFormationMgrFlow.class;
	}
}
