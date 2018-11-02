/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanDAOImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午10:46:41 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午10:46:41 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.RecruitYearPlan;
import com.wondersgroup.human.repository.ofcflow.RecruitYearPlanDAO;

/** 
 * @ClassName: RecruitEmployPlanDAOImpl 
 * @Description: TODO
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午10:46:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class RecruitYearPlanDAOImpl  extends GenericRepositoryImpl<RecruitYearPlan> implements RecruitYearPlanDAO{

	/** (non Javadoc) 
	 * @Title: getEntityClass
	 * @Description: TODO
	 * @return 
	 * @see com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl#getEntityClass() 
	 */
	@Override
	public Class<RecruitYearPlan> getEntityClass() {

		return RecruitYearPlan.class;
	}

}
