/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EmployRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: 人员信息子表-录用 知识库实现类
 * 创建人: jiang   
 * 创建时间: 2018年6月1日10:05:57
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月1日10:06:04
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofc.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofc.Employ;
import com.wondersgroup.human.repository.ofc.EmployRepository;

/**
 * @ClassName: EmployRepositoryImpl 
 * @Description: 人员信息子表-录用 知识库实现类
 * @author: jiang
 * @date: 2018年6月1日 上午9:26:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class EmployRepositoryImpl extends GenericRepositoryImpl<Employ> implements EmployRepository {
	
	public Class<Employ> getEntityClass() {
		
		return Employ.class;
	}
}
