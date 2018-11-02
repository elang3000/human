/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:12:04 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:12:04 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.RecruitYearPlan;
import com.wondersgroup.human.vo.ofcflow.RecruitYearPlanVO;
import com.wondersgroup.framework.core.dao.support.Predicate;

/** 
 * @ClassName: RecruitEmployPlanService 
 * @Description: TODO
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午11:12:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RecruitYearPlanService extends GenericService<RecruitYearPlan>{

	/** 
	 * @Title: findByFilter1 
	 * @Description: TODO
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @return: Page<T>
	 */
	Page<RecruitYearPlanVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);

   
}
