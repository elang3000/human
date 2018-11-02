/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 参公交流 知识库实现类
 * 创建人: tzy   
 * 创建时间: 2018年9月27日 下午2:56:19 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月27日 下午2:56:19 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchange;
import com.wondersgroup.human.repository.ofcflow.ReferenceExchangeRepository;

/** 
 * @ClassName: ReferenceExchangeRepositoryImpl 
 * @Description: 参公交流 知识库实现类
 * @author: tzy
 * @date: 2018年9月27日 下午2:56:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class ReferenceExchangeRepositoryImpl extends GenericRepositoryImpl<ReferenceExchange> implements ReferenceExchangeRepository{

	@Override
	public Class<ReferenceExchange> getEntityClass() {
		return ReferenceExchange.class;
	}
	
}
