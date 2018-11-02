/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:13:23 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:13:23 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.RecruitYearPlan;
import com.wondersgroup.human.service.ofcflow.RecruitYearPlanService;
import com.wondersgroup.human.vo.ofcflow.RecruitYearPlanVO;

/** 
 * @ClassName: RecruitEmployPlanServiceImpl 
 * @Description: TODO
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午11:13:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class RecruitYearPlanServiceImpl  extends GenericServiceImpl<RecruitYearPlan> implements RecruitYearPlanService{
	
	  @Override
	  public  Page<RecruitYearPlanVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3){
		  Page<RecruitYearPlan> pageInfo = findByFilter(arg0, arg1, arg2, arg3);
		  List<RecruitYearPlanVO> voList = new ArrayList<>();
		  for (RecruitYearPlan plan :  pageInfo.getResult()) {
			  RecruitYearPlanVO pl = new  RecruitYearPlanVO();
			  pl.setId(plan.getId());
			  pl.setName(plan.getName());
			  pl.setDescription(plan.getDescription());
			  if("1".equals(plan.getState().toString())){
				  pl.setState("开启");
			  }else{
				  pl.setState("关闭");
			  }
			  DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
			  pl.setStartDate(dateFormat.format(plan.getStartDate()));
			  pl.setEndDate(dateFormat.format(plan.getEndDate()));
			  voList.add(pl);
		  }
		  Page<RecruitYearPlanVO> page = new Page<>(pageInfo.getStart(), pageInfo.getCurrentPageSize(), pageInfo.getTotalSize(), pageInfo.getPageSize(), voList);
		  return page;
	  };
   
}
