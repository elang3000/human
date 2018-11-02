/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月28日 上午9:49:52 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月28日 上午9:49:52 
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
import com.wondersgroup.human.bo.ofcflow.AbroadPlan;
import com.wondersgroup.human.service.ofcflow.AbroadPlanService;
import com.wondersgroup.human.vo.ofcflow.AbroadPlanVO;

/** 
 * @ClassName: AbroadPlanServiceImpl 
 * @Description: 单位因公出国服务实现
 * @author: lihao
 * @date: 2018年9月28日 上午9:49:52
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class AbroadPlanServiceImpl extends GenericServiceImpl<AbroadPlan> implements AbroadPlanService {

	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	
	/**
	 * @see com.wondersgroup.human.service.ofcflow.AbroadPlanService#pageList(com.wondersgroup.human.bo.ofcflow.AbroadPlan, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<AbroadPlanVO> pageList(AbroadPlan abroadPlan, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(AbroadPlan.class);
		DetachedCriteria yp = detachedcriteria.createAlias("yearPlan", "yp");
		
		if (abroadPlan.getYearPlan() != null && StringUtils.isNotBlank(abroadPlan.getYearPlan().getId())) {// 因公出国计划
			yp.add(Restrictions.eq("yp.id", abroadPlan.getYearPlan().getId()));
		}
		if (abroadPlan.getServant() != null && StringUtils.isNotBlank(abroadPlan.getServant().getName())) {// 姓名
			DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
			s.add(Restrictions.like("s.name", abroadPlan.getServant().getName(), MatchMode.ANYWHERE));
		}
		if (abroadPlan.getYearPlan() != null && StringUtils.isNotBlank(abroadPlan.getYearPlan().getCountry())) {// 出访国家（地区）
			yp.add(Restrictions.like("yp.country", abroadPlan.getYearPlan().getCountry(), MatchMode.ANYWHERE));
		}
		detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		detachedcriteria.add(Restrictions.eq("removed", false));
		//创建时间倒叙
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<AbroadPlan> abroadPage = this.findByCriteria(detachedcriteria, page, limit);
		List<AbroadPlanVO> voList = new ArrayList<>();
		for (AbroadPlan a : abroadPage.getResult()) {
			AbroadPlanVO vo = new AbroadPlanVO(a);
			voList.add(vo);
		}
		Page<AbroadPlanVO> result = new Page<>(abroadPage.getStart(), abroadPage.getCurrentPageSize(),
				abroadPage.getTotalSize(), abroadPage.getPageSize(), voList);
		return result;
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.AbroadPlanService#savePlan(com.wondersgroup.human.bo.ofcflow.AbroadPlan, java.lang.String, java.lang.String) 
	 */
	@Override
	public void savePlan(AbroadPlan temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(AbroadPlan.STATUS_ABROAD_PLAN_STEP1==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"因公出国");//流程业务名称
			flow.setBusType("Abroad");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV); //分类公务员单位
			flow = workflowService.createFlowRecord(flow, "STATUS_ABROAD_PLAN_STEP1");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(AbroadPlan.STATUS_ABROAD_PLAN_STEP8 == temp.getStatus()&&FlowRecord.PASS.equals(r)){//因公出国最后环节
			temp.setStatus(AbroadPlan.STATUS_ABROAD_PLAN_PASS);//
			temp.setFlowRecord(null);//修改当前业务的流程节点
		}else{
			temp.setStatus(AbroadPlan.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}

}
