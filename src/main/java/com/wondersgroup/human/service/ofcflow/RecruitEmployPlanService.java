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

import java.math.BigDecimal;
import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan;
import com.wondersgroup.human.bo.ofcflow.RecruitYearPlan;
import com.wondersgroup.human.vo.ofcflow.RecruitEmployPlanVO;

/** 
 * @ClassName: RecruitEmployPlanService 
 * @Description: TODO
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午11:12:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RecruitEmployPlanService extends GenericService<RecruitEmployPlan>{
	/**
	 * 
	 * @Title: getRecruitEmployPlanByEmployOrgan 
	 * @Description: 根据年度计划和单位获取组织招录计划
	 * @param yearPlan
	 * @param employOrgan
	 * @return
	 * @return: List<RecruitEmployPlan>
	 */
	BigDecimal getRecruitEmployPlanByEmployOrgan(RecruitYearPlan yearPlan, OrganNode employOrgan);
	/**
	 * 
	 * @Title: findByFilterVO 
	 * @Description: 列表分页
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @return: Page<RecruitEmployPlanVO>
	 */
	Page<RecruitEmployPlanVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);
	
	
	Integer valiateEmployPlanPostNumber(String planId, String objectTypeId);
	
	List<RecruitEmployPlan> getRecruitEmployPlanByRecruitOrgan(RecruitYearPlan yearPlan,OrganNode organNode,String loc);
	
	RecruitEmployPlan getLastRecruitEmployPlanByRecruitOrganWithPlanState(RecruitYearPlan yearPlan,OrganNode organNode,
	        List<Integer> planStates, String loc);
	
	/**
	 * @Title: savePlan 
	 * @Description: 审批招录计划
	 * @param temp
	 * @return: void
	 */
	public void savePlan(RecruitEmployPlan temp,String opinion,String r);
	/**
	 * @Title: savePost 
	 * @Description: 审批招录计划的职位信息
	 * @param temp
	 * @return: void
	 */
	public void savePost(RecruitEmployPlan temp,String opinion,String r);
}
