/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月13日 下午3:01:00 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月13日 下午3:01:00 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.AbroadPeople;
import com.wondersgroup.human.bo.ofcflow.AbroadServant;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerManageRecordEvent;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.AbroadPeopleService;
import com.wondersgroup.human.service.ofcflow.AbroadServantService;
import com.wondersgroup.human.vo.ofcflow.AbroadServantVO;

/** 
 * @ClassName: AbroadServantServiceImpl 
 * @Description: 因公出国服务层实现
 * @author: lihao
 * @date: 2018年12月13日 下午3:01:00
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class AbroadServantServiceImpl  extends GenericServiceImpl<AbroadServant> implements AbroadServantService {

	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	AbroadPeopleService abroadPeopleService;
	@Autowired
	ServantService servantService;
	
	@Autowired
	MessageSource messageSource;
	
	/** 
	 * @see com.wondersgroup.human.service.ofcflow.AbroadServantService#pageList(com.wondersgroup.human.bo.ofcflow.AbroadServant, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<AbroadServantVO> pageList(AbroadServant abroadServant, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(AbroadServant.class);
		DetachedCriteria yp = detachedcriteria.createAlias("yearPlan", "yp");
		
		if (abroadServant.getYearPlan() != null && StringUtils.isNotBlank(abroadServant.getYearPlan().getId())) {// 因公出国计划
			yp.add(Restrictions.eq("yp.id", abroadServant.getYearPlan().getId()));
		}
		if (abroadServant.getYearPlan() != null && StringUtils.isNotBlank(abroadServant.getYearPlan().getCountry())) {// 出访国家（地区）
			yp.add(Restrictions.like("yp.country", abroadServant.getYearPlan().getCountry(), MatchMode.ANYWHERE));
		}
		detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		detachedcriteria.add(Restrictions.eq("removed", false));
		//创建时间倒叙
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<AbroadServant> abroadPage = this.findByCriteria(detachedcriteria, page, limit);
		List<AbroadServantVO> voList = new ArrayList<>();
		for (AbroadServant a : abroadPage.getResult()) {
			AbroadServantVO vo = new AbroadServantVO(a);
			voList.add(vo);
		}
		Page<AbroadServantVO> result = new Page<>(abroadPage.getStart(), abroadPage.getCurrentPageSize(),
				abroadPage.getTotalSize(), abroadPage.getPageSize(), voList);
		return result;
	}

	public void createServant(AbroadServant temp){
		
		List<AbroadPeople> apList = abroadPeopleService.getServantIdsByAbroadId(temp.getId());
		ManagerRecordDTO dto = null;
		ManagerManageRecordEvent event = null;
		for(AbroadPeople ap : apList){
			dto = new ManagerRecordDTO(ap.getServant().getId(),ManagerRecord.HUMAN_YGCG);
			event = new ManagerManageRecordEvent(dto);
			EventManager.send(event);
		}
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.AbroadServantService#saveFlow(com.wondersgroup.human.bo.ofcflow.AbroadServant, java.lang.String, java.lang.String) 
	 */
	@Override
	public void saveFlow(AbroadServant temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		DetachedCriteria criteria = DetachedCriteria.forClass(AbroadPeople.class);//查询该批次内人员信息
		criteria.add(Restrictions.eq("abroadServant.id", temp.getId()));
		criteria.add(Restrictions.eq("removed", false));
		List<AbroadPeople> list = abroadPeopleService.findByCriteria(criteria);
		
		if(AbroadServant.STATUS_ABROAD_PLAN_STEP1==temp.getStatus()){//提交环节，1.校验数据是否填写
			//1.校验数据是否填写
			if(list.size()==0){
				throw new BusinessException("请添加人员信息！");
			}
		}
		
		FlowRecord flow;
		if(AbroadServant.STATUS_ABROAD_PLAN_STEP1==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("因公出国政审");//流程业务名称
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
		if(flow==null){
			if(FlowRecord.PASS.equals(r)){
				createServant(temp);
				temp.setStatus(AbroadServant.STATUS_ABROAD_PLAN_PASS);//
				temp.setFlowRecord(null);//修改当前业务的流程节点
			}else if(FlowRecord.STOP.equals(r)){
				//发送消息
				temp.setStatus(FlowRecord.BUS_STOP);
				
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"因公出国政审"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"因公出国政审"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "","因公出国政审")));

			}
		}else{
			temp.setStatus(AbroadServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.AbroadServantService#savePeople(com.wondersgroup.human.bo.ofcflow.AbroadServant, java.lang.String) 
	 */
	@Override
	public void savePeople(AbroadServant abroadServant, String servantIds) {
		String[] idArr = servantIds.split(",");
		if(StringUtils.isBlank(abroadServant.getId())){//如果没有保存批次信息，先保存
			abroadServant.setStatus(AbroadServant.STATUS_ABROAD_PLAN_STEP1);//流程状态，待提交
			save(abroadServant);
		}else{
			abroadServant = get(abroadServant.getId());
		}
		AbroadPeople ap = null;
		for(String i : idArr){
			Servant servant = servantService.get(i);
			ap = new AbroadPeople();
			DictUtils.operationCodeInfo(ap);//将CodeInfo中id为空的属性 设置为null
			ap.setServant(servant);
			ap.setAbroadServant(abroadServant);
			abroadPeopleService.save(ap);
		}
	}
}
