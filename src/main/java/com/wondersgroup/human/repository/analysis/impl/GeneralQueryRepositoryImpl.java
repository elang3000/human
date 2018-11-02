/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: GeneralQueryRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.analysis.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年10月18日 下午4:06:39 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年10月18日 下午4:06:39 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.analysis.impl;

import org.springframework.stereotype.Repository;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.analysis.GeneralQuery;
import com.wondersgroup.human.repository.analysis.GeneralQueryRepository;

/** 
 * @ClassName: GeneralQueryRepositoryImpl 
 * @Description: 综合查询知识库实现类
 * @author: lihao
 * @date: 2018年10月18日 下午4:06:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class GeneralQueryRepositoryImpl extends GenericRepositoryImpl<GeneralQuery> implements GeneralQueryRepository {

	public Class<GeneralQuery> getEntityClass() {
		return GeneralQuery.class;
	}

}
