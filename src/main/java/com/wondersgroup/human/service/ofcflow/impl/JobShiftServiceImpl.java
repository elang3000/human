
package com.wondersgroup.human.service.ofcflow.impl;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.*;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerManageRecordEvent;
import com.wondersgroup.human.repository.ofcflow.JobShiftRepository;
import com.wondersgroup.human.service.ofc.JobChangeService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.JobShiftService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.organization.JudgePostResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class JobShiftServiceImpl extends GenericServiceImpl<JobShift>
        implements JobShiftService, ApplicationContextAware {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private JobChangeService jobChangeService;
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FormationControlService formationControlService;

	@Autowired
	private DictableService dictableService;

	@Autowired
	private ServantService servantService;

	@Autowired
	private JobShiftRepository jobShiftRepository;

	@Autowired
	private JobLevelService jobLevelService;
	
	@Override
	public void updateDemoteFlow(JobShift jobShift, String opinion, String result,boolean isShift) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(jobShift.getId())) {
			saveOrUpdate(jobShift);// 保存业务数据
		}
		FlowRecord flow;
		Servant servant = servantService.load(jobShift.getServant().getId());
		String servantId=servant.getId();
		//旧职级
		JobLevel formerJobLevel = jobLevelService.getJobLevelByServantId(servantId);//查询当前人员的现行职级
		//新职级
		CodeInfo newJobLevel=null;
		//新职级是否为空
		if(null!=jobShift.getNewJobLevel()){
			newJobLevel = codeInfoService.get(jobShift.getNewJobLevel().getId());
		}


		if (jobShift.getStatus() == null && jobShift.getFlowRecord() == null) {
			// 提交环节，先生成流程数据


			if(null!=newJobLevel&&newJobLevel.getId()!=formerJobLevel.getCode().getId()) {
				String orgId = servant.getDepartId();
				//编控，校验职位编制数是否足够，判断数据能否保存，如果超编，抛出异常
//				JudgePostResult queryJudgePostNum = formationControlService.queryJudgePostNum(orgId, newJobLevel.getCode());
				//锁定职位调入编控
//				formationControlService.executeLockPostIntoNum(orgId, newJobLevel.getCode(), queryJudgePostNum.isLowToHigh);
				//锁定职位调出编控
//				formationControlService.executeLockPostOutNum(orgId, formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
				//保存是否高职低配到业务表
//				jobShift.setLowToHigh(queryJudgePostNum.isLowToHigh);
			}

			// 设置申请人员的单位
			jobShift.setSourceOrganNode(userOrg);
			jobShift.setStatus(1);
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(jobShift.getId());// 流程业务ID
			flow.setBusName("职务变动");// 流程业务名称
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);// 流程业务目标人员
			if(isShift){
				jobShift.setPostTenureChange(codeInfoService.getCodeInfoByDictTypeAndName(DictTypeCodeContant.CODE_TYPE_POST_CHANGE, "轮岗"));
				flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT);// 流程业务类型
			}else{
				jobShift.setPostTenureChange(codeInfoService.getCodeInfoByDictTypeAndName(DictTypeCodeContant.CODE_TYPE_POST_CHANGE, "降职"));
				flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_DEMOTE);// 流程业务类型
			}
			flow = workflowService.createFlowRecord(flow, JobShift.JOBSHIFT_DEMOTE_STEP1);// 初始节点,降职和轮岗使用相同的流程,起始节点一样
			
		} else {
			jobShift.setStatus(jobShift.getStatus() + 1);
			flow = jobShift.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(result);
			flow = workflowService.completeWorkItem(flow);// 提交下个节点
		}
		if (null == flow) {

			//1 升职后将原职务设置为非现任职务,将新职务设置为现任职务 让出老职务的编制 =======================

			if(null!=newJobLevel&&newJobLevel.getId()!=formerJobLevel.getCode().getId()) {
				//解锁职位调入编控
//				formationControlService.executeUnlockPostIntoNum(servant.getDepartId(), newJobLevel.getCode(), jobShift.getLowToHigh());
				//解锁职位调出编控
//				formationControlService.executeUnlockPostOutNum(servant.getDepartId(), formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
				//出编控
//				formationControlService.executeOutPost(servant.getDepartId(), formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
			}



			// 2 发送消息 =====================================================
			String title = messageSource.getMessage(JobShift.PROMOTE_TITLE, new Object[] {
					jobShift.getServant().getName()
			}, Locale.CHINESE);
			String content = messageSource.getMessage(JobShift.PROMOTE_CONTENT, new Object[] {
					jobShift.getServant().getName()
			}, Locale.CHINESE);
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, jobShift.getCreater(), title, content, "")));

			//3 流程结束,插入职务变动子集信息   ===================================================
			//流程结束,插入子集信息
			JobChange change = new JobChange();
			BeanUtils.copyProperties(jobShift, change);
			change.setFormerUnitCode(jobShift.getSourceOrganNode().getId());
			change.setFormerUnitName(jobShift.getSourceOrganNode().getAllName());
			change.setNewUnitCode(jobShift.getSourceOrganNode().getId());
			change.setNewUnitName(jobShift.getSourceOrganNode().getAllName());
			change.setNewPostName(jobShift.getNewPostCode().getName());
			jobChangeService.save(change);


			//4 流程结束,升职后将原职务设置为非现任职务,插入新post子集,将新职务设置为现任职务     ==================================================

			//任职状态,DM215 是 和 否
			CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");
			CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");

			//任职状态,DM007 表示该人是否任职或不任职
			CodeInfo inOfficeCodeInfo = dictableService.getCodeInfoByCode("2", "DM007");
			CodeInfo notInOfficeCodeInfo = dictableService.getCodeInfoByCode("1", "DM007");

			Post prePost = jobShift.getPrePost();
			//在职状态为不在职
			prePost.setTenureStatus(notInOfficeCodeInfo);
			postService.update(prePost);


			//插入新post子集,将新职务设置为现任职务
			Post newPost=new Post();
			newPost.setServant(servant);
			newPost.setTenureName(servant.getDepartName());
			newPost.setAttribute(jobShift.getNewPostAttribute());
			newPost.setPostName(jobShift.getNewPostCode().getName());
			newPost.setPostCode(jobShift.getNewPostCode());
			newPost.setApprovalDate(new Date());
			//设置任职状态为在任
			newPost.setTenureStatus(inOfficeCodeInfo);
			//任职变动类别
			newPost.setTenureChange(jobShift.getPostTenureChange());
			postService.save(newPost);
			//5 暂时不加,流程结束,新职务插入简历子集.!!!!旧简历加上结束时间,但是就简历没法找,所以暂时没有此操作===========================================


			//6 判断假如新职级不为空且不和老职级相同则生成新职级并保存到servant当中
			if(null!=newJobLevel&&newJobLevel.getId()!=servant.getNowJobLevel().getId()){
				JobLevel jobLevel = new JobLevel();
				jobLevel.setServant(servant);
//				jobLevel.setIsLowToHigh(jobShift.getLowToHigh());
				jobLevel.setName(jobShift.getNewJobLevel().getName());
				jobLevel.setCode(jobShift.getNewJobLevel());
				jobLevel.setApprovalDate(new Date());
				jobLevel.setCurrentIdentification(yesCodeInfo);
				jobLevelService.save(jobLevel);
			}

			//7 增加进出管操作
			ManagerRecordDTO dto = new ManagerRecordDTO(jobShift.getServant().getId(), ManagerRecord.HUMAN_ZWBD);

			ManagerManageRecordEvent event = new ManagerManageRecordEvent(dto);
			EventManager.send(event);

			//System.out.println("流程结束");
		}
		jobShift.setFlowRecord(flow);
		this.update(jobShift);
		
	}

	/**
	 * 获取当前单位职务变动表单提交记录数据
	 *
	 * @param orgId
	 * @return
	 */
	@Override
	public Page<Map> getFormRecordData(String orgId, String jobChangeType, String name, Integer page, Integer limit) {
		return this.jobShiftRepository.getIndexData(orgId,jobChangeType,name,page,limit);
	}

	/**
	 * @param servantId
	 * @return
	 */
	@Override
	public List<String> getHandledPostIds(String servantId) {
		List<String> postIds=this.jobShiftRepository.getHandledPostIds(servantId);
		return postIds;
	}

	@Override
	public void updatePromoteFlow(JobShift jobShift, String opinion, String result){
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(jobShift.getId())) {
			save(jobShift);// 保存业务数据
		}
		FlowRecord flow;
		Servant servant = servantService.get(jobShift.getServant().getId());
		String servantId=servant.getId();
		//旧职级
		JobLevel formerJobLevel = jobLevelService.getJobLevelByServantId(servantId);//查询当前人员的现行职级
		//新职级
		CodeInfo newJobLevel=null;
		//新职级是否为空
		if(null!=jobShift.getNewJobLevel()){
			newJobLevel = codeInfoService.get(jobShift.getNewJobLevel().getId());
		}
		if (jobShift.getFlowRecord() == null) {// 提交环节，先生成流程数据

			if(null!=newJobLevel&&!newJobLevel.getId().equals(formerJobLevel.getCode().getId())) {
				String orgId = servant.getDepartId();
				//编控，校验职位编制数是否足够，判断数据能否保存，如果超编，抛出异常
//				JudgePostResult queryJudgePostNum = formationControlService.queryJudgePostNum(orgId, newJobLevel.getCode());
				//锁定职位调入编控
//				formationControlService.executeLockPostIntoNum(orgId, newJobLevel.getCode(), queryJudgePostNum.isLowToHigh);
				//锁定职位调出编控
//				formationControlService.executeLockPostOutNum(orgId, formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
				//保存是否高职低配到业务表
//				jobShift.setLowToHigh(queryJudgePostNum.isLowToHigh);
			}


			// 设置业务流程发起组织部门
			jobShift.setSourceOrganNode(userOrg);
			jobShift.setStatus(1);
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(jobShift.getId());// 流程业务ID
			flow.setBusName("职务变动");// 流程业务名称
			flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_PROMOTE);// 流程业务类型
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);// 流程业务目标人员
			flow = workflowService.createFlowRecord(flow, JobShift.JOBSHIFT_PROMOTE_STEP1);// 初始节点
		} else {
			flow = jobShift.getFlowRecord();
			jobShift.setStatus(jobShift.getStatus() + 1);
			flow.setOpinion(opinion);
			flow.setResult(result);
			if (result.equals(FlowRecord.PASS)
			        && (jobShift.getFlowRecord().getOperationCode().equals(JobShift.JOBSHIFT_PROMOTE_STEP6)
			                || jobShift.getFlowRecord().getOperationCode().equals(JobShift.JOBSHIFT_PROMOTE_STEP11))) {
				flow = workflowService.completeFlowRecordByAppoint(flow, jobShift.getSourceOrganNode());
			} else {
				flow = workflowService.completeWorkItem(flow);// 提交下个节点
			}
		}
		if (null == flow) {
			//1 判断假如新职级不为空且不和老职级相同则 编控操作 让出老职务的编制 新职务入编控=================================
			if(null!=newJobLevel&&newJobLevel.getId()!=servant.getNowJobLevel().getId()) {

				//解锁职位调入编控
//				formationControlService.executeUnlockPostIntoNum(servant.getDepartId(), newJobLevel.getCode(), jobShift.getLowToHigh());
				//解锁职位调出编控
//				formationControlService.executeUnlockPostOutNum(servant.getDepartId(), formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
				//出编控
//				formationControlService.executeOutPost(servant.getDepartId(), formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
			}


			// 2 发送消息 =====================================================
			String title = messageSource.getMessage(JobShift.PROMOTE_TITLE, new Object[] {
			        jobShift.getServant().getName()
			}, Locale.CHINESE);
			String content = messageSource.getMessage(JobShift.PROMOTE_CONTENT, new Object[] {
			        jobShift.getServant().getName()
			}, Locale.CHINESE);
			//发送通知
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, jobShift.getCreater(), title, content, "")));


			//3 流程结束,插入职务变动子集信息   ===================================================
			JobChange change = new JobChange();
			BeanUtils.copyProperties(jobShift, change);
			change.setFormerUnitCode(jobShift.getSourceOrganNode().getId());
			change.setFormerUnitName(jobShift.getSourceOrganNode().getAllName());
			change.setNewUnitCode(jobShift.getSourceOrganNode().getId());
			change.setNewUnitName(jobShift.getSourceOrganNode().getAllName());
			change.setNewPostName(jobShift.getNewPostCode().getName());
			jobChangeService.save(change);


			//4 流程结束,升职后将原职务设置为非现任职务,插入新post子集,将新职务设置为现任职务     ==================================================

			//任职状态,DM215 是 和 否
			CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");
			CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");

			//任职状态,DM007 表示该人是否任职或不任职
			CodeInfo inOfficeCodeInfo = dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_TYPE_POST_STATUS);
			CodeInfo notInOfficeCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_POST_STATUS);

			Post prePost = jobShift.getPrePost();
			//在职状态为不在职
			prePost.setTenureStatus(notInOfficeCodeInfo);
			postService.update(prePost);


			//插入新post子集,将新职务设置为现任职务
			Post newPost=new Post();
			newPost.setServant(servant);
			newPost.setTenureName(servant.getDepartName());
			newPost.setAttribute(jobShift.getNewPostAttribute());
			newPost.setPostName(jobShift.getNewPostCode().getName());
			newPost.setPostCode(jobShift.getNewPostCode());
			newPost.setApprovalDate(new Date());
			//设置任职状态为在任
			newPost.setTenureStatus(inOfficeCodeInfo);
			//任职变动类别
			newPost.setTenureChange(jobShift.getPostTenureChange());
			postService.save(newPost);
			//5 暂时不加,流程结束,新职务插入简历子集.!!!!旧简历加上结束时间,但是就简历没法找,所以暂时没有此操作===========================================

			//6 判断假如新职级不为空且不和老职级相同则生成新职级并保存到servant当中
			if(null!=newJobLevel&&newJobLevel.getId()!=servant.getNowJobLevel().getId()){
				JobLevel jobLevel = new JobLevel();
				jobLevel.setServant(servant);
//				jobLevel.setIsLowToHigh(jobShift.getLowToHigh());
				jobLevel.setName(jobShift.getNewJobLevel().getName());
				jobLevel.setCode(jobShift.getNewJobLevel());
				jobLevel.setApprovalDate(new Date());
				jobLevel.setCurrentIdentification(yesCodeInfo);
				jobLevelService.save(jobLevel);
			}

			//7 增加进出管操作 =============================================
			ManagerRecordDTO dto = new ManagerRecordDTO(jobShift.getServant().getId(), ManagerRecord.HUMAN_ZWBD);
			ManagerManageRecordEvent event = new ManagerManageRecordEvent(dto);
			EventManager.send(event);

		}
		jobShift.setFlowRecord(flow);
		this.update(jobShift);
		
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	
}
