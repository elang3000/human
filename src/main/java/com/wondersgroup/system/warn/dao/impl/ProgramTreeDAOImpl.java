/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProgramTreeDAOImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.dao.impl
 * 描述: 预警预告：字段信息 tree 知识库实现类
 * 创建人: tzy
 * 创建时间: 2018年10月31日 下午2:27:13
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年10月31日 下午2:27:13
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.warn.dao.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericTreeRepositoryImpl;
import com.wondersgroup.system.warn.bo.ProgramTree;
import com.wondersgroup.system.warn.dao.ProgramTreeDAO;

/**
 * @ClassName: ProgramTreeDAOImpl
 * @Description: 预警预告：字段信息 tree 知识库实现类
 * @author: tzy
 * @date: 2018年10月31日 下午2:27:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class ProgramTreeDAOImpl extends GenericTreeRepositoryImpl<ProgramTree> implements ProgramTreeDAO {
	
	public Class<ProgramTree> getEntityClass() {
		return ProgramTree.class;
	}
}
