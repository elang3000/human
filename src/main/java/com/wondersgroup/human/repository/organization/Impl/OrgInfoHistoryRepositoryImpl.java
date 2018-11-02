/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OrgInfoRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.impl 
 * 描述: 单位信息历史调整 知识库接口实现类
 * 创建人: jiang   
 * 创建时间: 2018年9月12日15:00:12
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年9月12日15:00:15 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.organization.Impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.organization.OrgInfoHistory;
import com.wondersgroup.human.repository.organization.OrgInfoHistoryRepository;

/** 
 * @ClassName: OrgInfoRepositoryImpl
 * @Description: 单位信息历史调整 知识库实现类
 * @author: jiang
 * @date: 2018年9月12日15:00:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class OrgInfoHistoryRepositoryImpl extends GenericRepositoryImpl<OrgInfoHistory> implements OrgInfoHistoryRepository{

	public Class<OrgInfoHistory> getEntityClass() {
		return OrgInfoHistory.class;
	}
}
