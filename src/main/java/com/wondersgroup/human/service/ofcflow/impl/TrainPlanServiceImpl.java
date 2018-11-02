/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月13日 上午9:13:50 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月13日 上午9:13:50 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofcflow.TrainPlan;
import com.wondersgroup.human.service.ofcflow.TrainPlanService;
import com.wondersgroup.human.vo.ofcflow.TrainPlanVO;

/** 
 * @ClassName: TrainPlanServiceImpl 
 * @Description: 培训学时服务类
 * @author: lihao
 * @date: 2018年9月13日 上午9:13:50
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TrainPlanServiceImpl extends GenericServiceImpl<TrainPlan> implements TrainPlanService {
	
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainPlanService#pageList(com.wondersgroup.human.bo.ofcflow.TrainPlan, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<TrainPlanVO> pageList(TrainPlan trainPlan, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(TrainPlan.class);
		if (StringUtils.isNotBlank(trainPlan.getTrainName())) {// 培训名称
			detachedcriteria.add(Restrictions.like("trainName", trainPlan.getTrainName(), MatchMode.ANYWHERE));
		}
		if (trainPlan.getStartDate()!=null) {// 开始时间
			detachedcriteria.add(Restrictions.le("startDate", trainPlan.getStartDate()));
		}
		if (trainPlan.getEndDate()!=null) {// 结束时间
			detachedcriteria.add(Restrictions.ge("endDate", trainPlan.getEndDate()));
		}
		
		if (trainPlan.getYearPlan() != null && StringUtils.isNotBlank(trainPlan.getYearPlan().getId())) {// 年度培训计划
			DetachedCriteria yp = detachedcriteria.createAlias("yearPlan", "yp");
			yp.add(Restrictions.eq("yp.id", trainPlan.getYearPlan().getId()));
		}
		detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		detachedcriteria.add(Restrictions.eq("removed", false));
		//创建时间倒叙
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<TrainPlan> trainPage = this.findByCriteria(detachedcriteria, page, limit);
		List<TrainPlanVO> voList = new ArrayList<>();
		for (TrainPlan t : trainPage.getResult()) {
			TrainPlanVO vo = new TrainPlanVO(t);
			voList.add(vo);
		}
		Page<TrainPlanVO> result = new Page<>(trainPage.getStart(), trainPage.getCurrentPageSize(),
				trainPage.getTotalSize(), trainPage.getPageSize(), voList);
		return result;
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainPlanService#savePlan(com.wondersgroup.human.bo.ofcflow.TrainPlan, java.lang.String, java.lang.String) 
	 */
	@Override
	public void savePlan(TrainPlan temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(TrainPlan.STATUS_TRAIN_PLAN_STEP1==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"培训考核");//流程业务名称
			flow.setBusType("Train");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV); //分类公务员单位
			flow = workflowService.createFlowRecord(flow, "STATUS_TRAIN_PLAN_STEP1");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(TrainPlan.STATUS_TRAIN_PLAN_STEP8 == temp.getStatus()&&FlowRecord.PASS.equals(r)){//培训考核最后环节
			temp.setStatus(TrainPlan.STATUS_TRAIN_PLAN_PASS);//
			temp.setFlowRecord(null);//修改当前业务的流程节点
		}else{
			temp.setStatus(TrainPlan.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
		
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainPlanService#savePlan2(com.wondersgroup.human.bo.ofcflow.TrainPlan, java.lang.String, java.lang.String) 
	 */
	@Override
	public void savePlan2(TrainPlan temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(TrainPlan.STATUS_TRAIN_PLAN_STEP1==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"培训考核");//流程业务名称
			flow.setBusType("Train");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV); //分类公务员单位
			flow = workflowService.createFlowRecord(flow, "STATUS_TRAIN_PLAN_BY_HUMAN_STEP1");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(TrainPlan.STATUS_TRAIN_PLAN_STEP8 == temp.getStatus()&&FlowRecord.PASS.equals(r)){//培训考核最后环节
			temp.setStatus(TrainPlan.STATUS_TRAIN_PLAN_PASS);//
			temp.setFlowRecord(null);//修改当前业务的流程节点
		}else{
			temp.setStatus(TrainPlan.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}

}
