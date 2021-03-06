/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月22日 上午9:53:38 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月22日 上午9:53:38 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
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
import com.wondersgroup.human.bo.ofc.Experience;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DeathServant;
import com.wondersgroup.human.bo.ofcflow.ResignPeople;
import com.wondersgroup.human.bo.ofcflow.ResignPlan;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.dto.ofcflow.DeathServantQueryParam;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerOutRecordEvent;
import com.wondersgroup.human.event.record.ServantHumamKeepRecordEvent;
import com.wondersgroup.human.service.ofc.ExperienceService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DeathServantService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.ofcflow.DeathVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/**
 * @ClassName: DeathServantServiceImpl
 * @Description: 死亡服务接口实现类
 * @author: lihao
 * @date: 2018年6月22日上午9:53:38 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Service
public class DeathServantServiceImpl extends GenericServiceImpl<DeathServant> implements DeathServantService {

	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	ServantService servantService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private OutMgrService outMgrService;
	@Autowired
	private FormationControlService formationControlService;
	@Autowired
	JobLevelService jobLevelService;
	@Autowired
	private ExperienceService experienceService;
	
	/** 
	 * @see com.wondersgroup.human.service.ofcflow.DeathServantService#saveDeath(com.wondersgroup.human.bo.ofcflow.DeathServant, java.lang.String, java.lang.String) 
	 */
	@Override
	public void saveDeath(DeathServant temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		if(DeathServant.DEATH_EMPLOY_APPLY==temp.getStatus()){//提交环节，1.锁编
			executeLockOutFormationAndPost(temp);
		}
		
		FlowRecord flow;
		if(DeathServant.DEATH_EMPLOY_APPLY==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("死亡");//流程业务名称
			flow.setBusType("DeathServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV); //分类公务员单位
			flow = workflowService.createFlowRecord(flow, "DEATH_EMPLOY_APPLY");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(DeathServant.DEATH_EMPLOY_CONFIRM == temp.getStatus()&&FlowRecord.PASS.equals(r)){//死亡最后环节
			temp.setStatus(DeathServant.DEATH_EMPLOY_DONE);//通过
			temp.setFlowRecord(null);//修改当前业务的流程节点
			createServant(temp);//更新人员基本信息
			executeFinish(temp);//放编
		}else{
			temp.setStatus(DeathServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}

	/**  
	 * @see com.wondersgroup.human.service.ofcflow.DeathServantService#pageList(com.wondersgroup.human.bo.ofcflow.DeathServant, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<DeathVO> pageList(DeathServantQueryParam param, Integer page, Integer limit) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(DeathServant.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			s.add(Restrictions.like("s.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			s.add(Restrictions.eq("s.cardNo",param.getCardNo()));
		}
		if (StringUtils.isNotBlank(param.getRemark())) {// 备注
			detachedcriteria.add(Restrictions.like("remark", param.getRemark(), MatchMode.ANYWHERE));
		}
		if(x!=null&&x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)){
			detachedcriteria.add(Restrictions.eq("lastOperator", SecurityUtils.getUserId()));
		}else{
			detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		}
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<DeathServant> deathPage = this.findByCriteria(detachedcriteria, page, limit);
		List<DeathVO> voList = new ArrayList<>();
		for (DeathServant ds : deathPage.getResult()) {
			DeathVO vo = new DeathVO(ds);
			voList.add(vo);
		}
		Page<DeathVO> result = new Page<>(deathPage.getStart(), deathPage.getCurrentPageSize(),
				deathPage.getTotalSize(), deathPage.getPageSize(), voList);
		return result;
	}

	public void createServant(DeathServant temp){
		//主表
		Servant s = servantService.get(temp.getServant().getId());
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("4", "DM200");// 死亡CODE
		s.setIsOnHold(isOnHold);
		servantService.update(s);
		
		//转出子集表
		CodeInfo category = dictableService.getCodeInfoByCode("98", "GBT_12405_2008");// 调出本单位类别-死亡
		CodeInfo reasonType = dictableService.getCodeInfoByCode("9", "DM015");// 调动原因-其他
		CodeInfo proposeType = dictableService.getCodeInfoByCode("9", "DM039");// 提出调动类型-其他
		
		OutMgr out = new OutMgr();
		out.setServant(s);//人员基本信息
		out.setCategory(category);//调出本单位类别
		out.setReasonType(reasonType);//调动原因
		out.setOutDate(new Date());//调出本单位日期
		out.setGotoUnitName(null);//调往单位名称
		out.setProposeType(proposeType);//提出调动类型
		out.setRemark(temp.getRemark());//调出备注
		DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
		
		outMgrService.save(out);
		
		//更新简历
		Experience e = experienceService.getLatestExperienceByServantId(s.getId());
		e.setEndDate(new Date());
		experienceService.update(e);
		
		ManagerRecordDTO dto = new ManagerRecordDTO(s.getId(),ManagerRecord.HUMAN_SW);
		ManagerOutRecordEvent event = new ManagerOutRecordEvent(dto);
		EventManager.send(event);
		
		HumankeepRecordDTO dto2 = new HumankeepRecordDTO(s.getId(),HumanKeepRecord.KEEP_SW);
		ServantHumamKeepRecordEvent event2 = new ServantHumamKeepRecordEvent(dto2);	
		EventManager.send(event2);
	}
	
	/** 
	 * @see com.wondersgroup.human.service.ofcflow.ResignServantService#getByServantId(java.lang.String) 
	 */
	@Override
	public DeathServant getByServantId(String servantId) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(DeathServant.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		s.add(Restrictions.eq("s.id", servantId));
		detachedcriteria.add(Restrictions.eq("removed", false));
		
		List<DeathServant> list = this.findByCriteria(detachedcriteria);
		
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * @Title: executeLockOutFormationAndPost 
	 * @Description: 锁未调出编制
	 * @param temp
	 * @return: List<JudgePostResult> 原单位职级信息
	 */
	public void executeLockOutFormationAndPost(DeathServant temp){
		List<JudgePostResult> list = new ArrayList<>();
		JobLevel tempJ = jobLevelService.getJobLevelByServantId(temp.getServant().getId());//查询当前人员的现行职级
		if(StringUtils.isBlank(tempJ.getRealJobLevelCode())){
			throw new BusinessException("占编职级信息异常，请联系管理员！");
		}
		JudgePostResult j = new JudgePostResult(tempJ.getRealJobLevelCode(), tempJ.getRealLeader(),1);
		list.add(j);
		formationControlService.executeLockOutFormationAndPost(temp.getOrgan().getId(), list);//锁未调出
	}
	
	/**
	 * @Title: executeFinish 
	 * @Description: 流程结束，真实占编，解锁未调出编制
	 * @param temp
	 * @return: void
	 */
	public void executeFinish(DeathServant temp){
		JobLevel tempJ = jobLevelService.getJobLevelByServantId(temp.getServant().getId());//查询当前人员的现行职级
			
		formationControlService.executeUnlockOutFormationNum(temp.getOrgan().getId());//1.解锁调出单位未调出编制
		formationControlService.executeOutFormation(temp.getOrgan().getId());//2.减少调出单位实际编制数
		
		formationControlService.executeUnlockPostOutNum(temp.getOrgan().getId(),tempJ.getRealJobLevelCode(), tempJ.getRealLeader());//1.解锁职级调出数
		formationControlService.executeOutPost(temp.getOrgan().getId(),tempJ.getRealJobLevelCode(), tempJ.getRealLeader());//2.减少调出单位实际职级数
	}
}
