/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeOutRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 参公交流调出
 * 创建人: tzy   
 * 创建时间: 2018年11月8日 下午6:43:13 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年11月8日 下午6:43:13 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOut;
import com.wondersgroup.human.repository.ofcflow.ReferenceExchangeOutRepository;

/** 
 * @ClassName: ReferenceExchangeOutRepositoryImpl 
 * @Description: 参公交流调出
 * @author: tzy
 * @date: 2018年11月8日 下午6:43:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class ReferenceExchangeOutRepositoryImpl extends GenericRepositoryImpl<ReferenceExchangeOut> implements ReferenceExchangeOutRepository{

	/** (non Javadoc) 
	 * @Title: getEntityClass
	 * @return 
	 * @see com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl#getEntityClass() 
	 */
	@Override
	public Class<ReferenceExchangeOut> getEntityClass() {
		
		return ReferenceExchangeOut.class;
	}
	
}
