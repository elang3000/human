/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月13日 上午11:15:54 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月13日 上午11:15:54 
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
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofc.Study;
import com.wondersgroup.human.bo.ofcflow.TrainPeople;
import com.wondersgroup.human.bo.ofcflow.TrainServant;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerManageRecordEvent;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofc.StudyService;
import com.wondersgroup.human.service.ofcflow.TrainPeopleService;
import com.wondersgroup.human.service.ofcflow.TrainServantService;
import com.wondersgroup.human.vo.ofcflow.TrainServantVO;

/** 
 * @ClassName: TrainServantServiceImpl 
 * @Description: 培训计划服务类实现
 * @author: lihao
 * @date: 2018年11月13日 上午11:15:54
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TrainServantServiceImpl extends GenericServiceImpl<TrainServant> implements TrainServantService {

	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	private StudyService studyService;
	@Autowired
	TrainPeopleService trainPeopleService;
	@Autowired
	private ServantService servantService;
	@Autowired
	OrganNodeService organNodeService;
	@Autowired
	MessageSource messageSource;
	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainServantService#pageList(com.wondersgroup.human.bo.ofcflow.TrainServant, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<TrainServantVO> pageList(TrainServant trainServant, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(TrainServant.class);
		
		if (StringUtils.isNotBlank(trainServant.getTrainName())) {// 培训名
			detachedcriteria.add(Restrictions.like("trainName", trainServant.getTrainName(), MatchMode.ANYWHERE));
		}
		if (trainServant.getStartDate()!=null) {// 开始时间
			detachedcriteria.add(Restrictions.ge("startDate",trainServant.getStartDate()));
		}
		if (trainServant.getEndDate()!=null) {// 结束时间
			detachedcriteria.add(Restrictions.le("endDate", trainServant.getEndDate()));
		}
		
		detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<TrainServant> trainPage = this.findByCriteria(detachedcriteria, page, limit);
		List<TrainServantVO> voList = new ArrayList<>();
		for (TrainServant ds : trainPage.getResult()) {
			TrainServantVO vo = new TrainServantVO(ds);
			voList.add(vo);
		}
		Page<TrainServantVO> result = new Page<>(trainPage.getStart(), trainPage.getCurrentPageSize(),
				trainPage.getTotalSize(), trainPage.getPageSize(), voList);
		return result;
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainServantService#savePlan(com.wondersgroup.human.bo.ofcflow.TrainServant, java.lang.String, java.lang.String) 
	 */
	@Override
	public void savePlan(TrainServant temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		DetachedCriteria criteria = DetachedCriteria.forClass(TrainPeople.class);//查询该批次内人员信息
		criteria.add(Restrictions.eq("train.id", temp.getId()));
		criteria.add(Restrictions.eq("removed", false));
		List<TrainPeople> list = trainPeopleService.findByCriteria(criteria);
		
		if(TrainServant.STATUS_TRAIN_PLAN_STEP1==temp.getStatus()){//提交环节，1.校验数据是否填写
			//1.校验数据是否填写
			if(list.size()==0){
				throw new BusinessException("请添加人员信息！");
			}
		}
		
		FlowRecord flow;
		if(temp.getType()==TrainServant.TRAIN_NOT_BY_RS){
			if(TrainServant.STATUS_TRAIN_PLAN_STEP1==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
				flow = new FlowRecord();
				flow.setAppNodeId(appNode.getId());//流程业务所在系统
				flow.setBusId(temp.getId());//流程业务ID
				flow.setBusName("培训考核");//流程业务名称
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
			if(flow==null){
				if(FlowRecord.PASS.equals(r)){
					createServant(temp);
					temp.setStatus(TrainServant.STATUS_TRAIN_PLAN_PASS);//
					temp.setFlowRecord(null);//修改当前业务的流程节点
				}else if(FlowRecord.STOP.equals(r)){
					//发送消息
					temp.setStatus(FlowRecord.BUS_STOP);
					String title = messageSource.getMessage("stopFlowTitle", new Object[]{"培训学时考核"}, Locale.CHINESE);
					String content = messageSource.getMessage("stopFlowContent", new Object[]{"培训学时考核"}, Locale.CHINESE);
					AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "")));

				}
			}else{
				temp.setStatus(TrainServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
				temp.setFlowRecord(flow);//修改当前业务的流程节点
			}
			update(temp);
		}else{
			if(TrainServant.STATUS_TRAIN_PLAN_STEP1==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
				flow = new FlowRecord();
				flow.setAppNodeId(appNode.getId());//流程业务所在系统
				flow.setBusId(temp.getId());//流程业务ID
				flow.setBusName("培训学时考核");//流程业务名称
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
			if(flow==null){
				if(FlowRecord.PASS.equals(r)){
					createServant(temp);
					temp.setStatus(TrainServant.STATUS_TRAIN_PLAN_PASS);//
					temp.setFlowRecord(null);//修改当前业务的流程节点
				}else if(FlowRecord.STOP.equals(r)){
					//发送消息
					temp.setStatus(FlowRecord.BUS_STOP);
					String title = messageSource.getMessage("stopFlowTitle", new Object[]{"培训学时考核"}, Locale.CHINESE);
					String content = messageSource.getMessage("stopFlowContent", new Object[]{"培训学时考核"}, Locale.CHINESE);
					AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "","培训学时考核")));

				}
			}else{
				temp.setStatus(TrainServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
				temp.setFlowRecord(flow);//修改当前业务的流程节点
			}
			
			update(temp);
		}
	}
	
	public void createServant(TrainServant temp){
		
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(TrainPeople.class);
		//根据培训信息id查询
		DetachedCriteria t = detachedcriteria.createAlias("train", "t");
		t.add(Restrictions.eq("t.id", temp.getId()));
		
		detachedcriteria.add(Restrictions.eq("removed", false));
		List<TrainPeople> list = trainPeopleService.findByCriteria(detachedcriteria);
		
		for(TrainPeople s:list){
			Study study = new Study();
			
			study.setServant(s.getServant());
			study.setCategory(temp.getStudyType());//培训类别
			study.setStartDate(temp.getStartDate());//开始时间
			study.setEndDate(temp.getEndDate());//结束时间
			study.setHostUnitName(temp.getHostOrgan().getName());//主办单位名称名称
			study.setStudyUnitName(temp.getTrainOrganName());//在学单位名称
			study.setIsAbroadStudy(temp.getIsAbroad());//出国标识
			study.setStudyLengthOfTime(temp.getDays());//天数
			study.setStudyHours(temp.getHours());//学时
			study.setStudyClassName(temp.getTrainClassName());//培训班名称
			study.setCurrentUnitType(temp.getTrainOrganType());//培训机构类别
			DictUtils.operationCodeInfo(study);//将CodeInfo中id为空的属性 设置为null
			
			studyService.save(study);
			
			ManagerRecordDTO dto = new ManagerRecordDTO(s.getServant().getId(),ManagerRecord.HUMAN_PXKH);
			ManagerManageRecordEvent event = new ManagerManageRecordEvent(dto);
			EventManager.send(event);
		}
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainServantService#savePeople(com.wondersgroup.human.bo.ofcflow.TrainServant, java.lang.String) 
	 */
	@Override
	public void savePeople(TrainServant trainServant, String servantIds) {
		String[] idArr = servantIds.split(",");
		if(StringUtils.isBlank(trainServant.getId())){//如果没有保存批次信息，先保存
			trainServant.setStatus(TrainServant.STATUS_TRAIN_PLAN_STEP1);//流程状态，待提交
			DictUtils.operationCodeInfo(trainServant);//将CodeInfo中id为空的属性 设置为null
			if(StringUtils.isBlank(trainServant.getHostOrgan().getId())){
				trainServant.setHostOrgan(null);
			}
			save(trainServant);
		}else{
			TrainServant temp = get(trainServant.getId());
			BeanUtils.copyPropertiesIgnoreNull(trainServant,temp);
			if(StringUtils.isBlank(trainServant.getHostOrgan().getId())){
				trainServant.setHostOrgan(null);
			}
			update(temp);
		}
		TrainPeople rp = null;
		for(String i : idArr){
			Servant servant = servantService.get(i);
			rp = new TrainPeople();
			DictUtils.operationCodeInfo(rp);//将CodeInfo中id为空的属性 设置为null
			rp.setServant(servant);
			rp.setTrain(trainServant);
			rp.setOrgan(organNodeService.get(servant.getDepartId()));
			trainPeopleService.save(rp);
		}
	}
}
