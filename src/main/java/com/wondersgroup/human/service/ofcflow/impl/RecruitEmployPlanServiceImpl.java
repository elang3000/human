/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 招录计划 服务实现类
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:13:23 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:13:23 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan;
import com.wondersgroup.human.bo.ofcflow.RecruitYearPlan;
import com.wondersgroup.human.repository.ofcflow.RecruitEmployPlanDAO;
import com.wondersgroup.human.service.ofcflow.RecruitEmployPlanService;
import com.wondersgroup.human.vo.ofcflow.RecruitEmployPlanVO;

/** 
 * @ClassName: RecruitEmployPlanServiceImpl 
 * @Description: 招录计划 服务实现类
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午11:13:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class RecruitEmployPlanServiceImpl  extends GenericServiceImpl<RecruitEmployPlan> implements RecruitEmployPlanService{
	
	@Autowired
	RecruitEmployPlanDAO recruitEmployPlanDAO;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * (non Javadoc) 
	 * @Title: getRecruitEmployPlanByEmployOrgan
	 * @Description: 根据年度计划及单位查询组织计划
	 * @param yearPlan 年度计划
	 * @param employOrgan 单位
	 * @return 
	 * @see com.wondersgroup.human.service.ofcflow.RecruitEmployPlanService#getRecruitEmployPlanByEmployOrgan(com.wondersgroup.human.bo.ofcflow.RecruitYearPlan, com.wondersgroup.framework.organization.bo.OrganNode)
	 */
	@Override
	public BigDecimal getRecruitEmployPlanByEmployOrgan(RecruitYearPlan yearPlan, OrganNode employOrgan) {
		
		
		return recruitEmployPlanDAO.getRecruitEmployPlanByEmployOrgan(yearPlan,employOrgan);
		 
		 
	}
	
	/**
	 * 	(non Javadoc) 
	 * @Title: findByFilterVO
	 * @Description:  列表查询
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return 
	 * @see com.wondersgroup.human.service.ofcflow.RecruitEmployPlanService#findByFilterVO(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	  @Override
	  public  Page<RecruitEmployPlanVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3){
		  Page<RecruitEmployPlan> pageInfo = findByFilter(arg0, arg1, arg2, arg3);
		  List<RecruitEmployPlanVO> voList = new ArrayList<>();
		  for (RecruitEmployPlan plan :  pageInfo.getResult()) {
			  RecruitEmployPlanVO pl = new  RecruitEmployPlanVO(plan);
			  voList.add(pl);
		  }
		  Page<RecruitEmployPlanVO> page = new Page<>(pageInfo.getStart(), pageInfo.getCurrentPageSize(), pageInfo.getTotalSize(),
				  pageInfo.getPageSize(), voList);
		  return page;
	  };
	  
	  
  	@Override
	public Integer valiateEmployPlanPostNumber(String planId, String objectTypeId) {
		
		return recruitEmployPlanDAO.valiateEmployPlanPostNumber(planId,objectTypeId);
	}
  	
	@Override
	public RecruitEmployPlan getLastRecruitEmployPlanByRecruitOrganWithPlanState(RecruitYearPlan yearPlan,OrganNode organNode, List<Integer> planStates, String loc) {
		
		return recruitEmployPlanDAO.getLastRecruitEmployPlanByRecruitOrganWithPlanState(yearPlan,organNode, planStates, loc);
	}
	
	@Override
	public List<RecruitEmployPlan> getRecruitEmployPlanByRecruitOrgan(RecruitYearPlan yearPlan ,OrganNode organNode, String loc) {
		
		return recruitEmployPlanDAO.getRecruitEmployPlanByRecruitOrgan(yearPlan, organNode, loc);	
	}
	/**
	 * @Title: savePlan 
	 * @Description: 审批招录计划
	 * @param temp
	 * @return: void
	 */
	public void savePlan(RecruitEmployPlan temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_STATE_POST==temp.getPlanState()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("录用计划上报");//流程业务名称
			flow.setBusType("RecruitEmployPlan");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "REPORT_EMPLOY_PLAN");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(temp.getEmployNum()!=null){//如果有初审或复审人数，保存到备用字段
				flow.setRemark(String.valueOf(temp.getEmployNum()));
			}
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		//保存业务信息
		if(FlowRecord.PASS.equals(r)){//审批通过
			if(temp.getPlanState()>=RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_1&&temp.getPlanState()<=RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_4){//初审环节，保存初审人数到业务表
				temp.setFirstEmployNum(temp.getEmployNum());
			}
			if(RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_AREA_OGR==temp.getPlanState()){//终审环节，保存终审人数到业务表
				temp.setEndEmployNum(temp.getEmployNum());
			}
		}
		if(flow==null){
			temp.setFlowRecord(null);//修改当前业务的流程节点
			if(FlowRecord.PASS.equals(r)){//流程最后环节
				temp.setPlanState(RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_POST);//待职位上报
			}else if(FlowRecord.STOP.equals(r)){//流程被中止
				temp.setPlanState(FlowRecord.BUS_STOP);
				
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"录用计划上报"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"录用计划上报"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "","录用计划上报")));
			}
			
		}else{
			temp.setPlanState(RecruitEmployPlan.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
	/**
	 * @Title: savePost 
	 * @Description: 审批招录计划的职位信息
	 * @param temp
	 * @return: void
	 */
	public void savePost(RecruitEmployPlan temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_POST==temp.getPlanState()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("录用计划职位上报");//流程业务名称
			flow.setBusType("RecruitPost");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "REPORT_EMPLOY_PLAN_POST_DOING");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(flow==null){
			temp.setFlowRecord(null);//修改当前业务的流程节点
			if(FlowRecord.PASS.equals(r)){//流程最后环节
				temp.setPlanState(RecruitEmployPlan.RECRUIT_EMPLOY_PLAN_POST_PASS);//职位审批通过
			}else if(FlowRecord.STOP.equals(r)){//流程被中止
				temp.setPlanState(FlowRecord.BUS_STOP);
				
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"录用计划职位上报"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"录用计划职位上报"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "","录用计划职位上报")));
			}
			
		}else{
			temp.setPlanState(RecruitEmployPlan.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
}
