/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaoRenIntoMgrRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 调任流程 调入情况信息-知识库实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月30日 下午4:11:27 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月30日 下午4:11:27 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.DiaoRenIntoMgr;
import com.wondersgroup.human.repository.ofcflow.DiaoRenIntoMgrRepository;

/** 
 * @ClassName: DiaoRenIntoMgrRepositoryImpl 
 * @Description: 调任流程 调入情况信息-知识库实现类
 * @author: tzy
 * @date: 2018年7月30日 下午4:11:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class DiaoRenIntoMgrRepositoryImpl extends GenericRepositoryImpl<DiaoRenIntoMgr> implements DiaoRenIntoMgrRepository{

	public Class<DiaoRenIntoMgr> getEntityClass() {
		return DiaoRenIntoMgr.class;
	}
	
}