/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeBatchRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年12月17日 上午11:03:07 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年12月17日 上午11:03:07 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeBatch;
import com.wondersgroup.human.repository.ofcflow.ReferenceExchangeBatchRepository;

/** 
 * @ClassName: ReferenceExchangeBatchRepositoryImpl 
 * @Description: TODO	参公交流批量
 * @author: GP
 * @date: 2018年12月17日 上午11:03:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class ReferenceExchangeBatchRepositoryImpl extends GenericRepositoryImpl<ReferenceExchangeBatch> implements ReferenceExchangeBatchRepository{

	/** (non Javadoc) 
	 * @Title: getEntityClass
	 * @Description: TODO
	 * @return 
	 * @see com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl#getEntityClass() 
	 */
	@Override
	public Class<ReferenceExchangeBatch> getEntityClass() {
		return ReferenceExchangeBatch.class;
	}
	
}
