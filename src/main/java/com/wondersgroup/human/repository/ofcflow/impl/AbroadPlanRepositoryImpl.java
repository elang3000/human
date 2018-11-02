/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPlanRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月28日 上午9:53:11 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月28日 上午9:53:11 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.AbroadPlan;
import com.wondersgroup.human.repository.ofcflow.AbroadPlanRepository;

/** 
 * @ClassName: AbroadPlanRepositoryImpl 
 * @Description: 因公出国单位计划知识库实现
 * @author: lihao
 * @date: 2018年9月28日 上午9:53:11
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class AbroadPlanRepositoryImpl extends GenericRepositoryImpl<AbroadPlan> implements AbroadPlanRepository{

	@Override
	public Class<AbroadPlan> getEntityClass() {
		return AbroadPlan.class;
	}

}
