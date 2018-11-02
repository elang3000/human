/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: LoanToRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 借调 知识库实现类
 * 创建人: tzy   
 * 创建时间: 2018年9月25日 下午3:02:02 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月25日 下午3:02:02 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.LoanTo;
import com.wondersgroup.human.repository.ofcflow.LoanToRepository;

/** 
 * @ClassName: LoanToRepositoryImpl 
 * @Description: 借调 知识库实现类
 * @author: tzy
 * @date: 2018年9月25日 下午3:02:02
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class LoanToRepositoryImpl extends GenericRepositoryImpl<LoanTo> implements LoanToRepository{

	public Class<LoanTo> getEntityClass() {
		
		return LoanTo.class;
	}
	
}
