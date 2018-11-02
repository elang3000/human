/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanDAO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午10:42:18 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午10:42:18 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow;

import java.math.BigDecimal;
import java.util.List;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan;
import com.wondersgroup.human.bo.ofcflow.RecruitYearPlan;

/** 
 * @ClassName: RecruitEmployPlanDAO 
 * @Description: TODO
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午10:42:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RecruitEmployPlanDAO extends GenericRepository<RecruitEmployPlan>{
	
	Integer valiateEmployPlanPostNumber(String planId, String objectTypeId);
	
	BigDecimal getRecruitEmployPlanByEmployOrgan(RecruitYearPlan yearPlan, OrganNode employOrgan);
	
	/** 
	 * @Title: getLastRecruitEmployPlanByRecruitOrganWithPlanState 
	 * @Description: TODO
	 * @param yearPlan
	 * @param organNode
	 * @param planStates
	 * @param loc
	 * @return
	 * @return: RecruitEmployPlan
	 */
	RecruitEmployPlan getLastRecruitEmployPlanByRecruitOrganWithPlanState(RecruitYearPlan yearPlan, OrganNode organNode,List<Integer> planStates, String loc);
	
	List<RecruitEmployPlan> getRecruitEmployPlanByRecruitOrgan(RecruitYearPlan yearPlan ,OrganNode organNode, String loc);
}
