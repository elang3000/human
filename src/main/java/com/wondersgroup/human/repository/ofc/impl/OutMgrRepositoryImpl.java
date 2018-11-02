/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OutMgrRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: 人员信息子表-调出情况 知识库实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月30日 上午10:37:37 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月30日 上午10:37:37 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofc.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.repository.ofc.OutMgrRepository;

/** 
 * @ClassName: OutMgrRepositoryImpl 
 * @Description: 人员信息子表-调出情况 知识库实现类
 * @author: tzy
 * @date: 2018年7月30日 上午10:37:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class OutMgrRepositoryImpl extends GenericRepositoryImpl<OutMgr> implements OutMgrRepository{

	public Class<OutMgr> getEntityClass() {
		return OutMgr.class;
	}
	
}
