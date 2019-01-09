/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProbationServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 试用期管理 服务接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月25日 下午3:20:12 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月25日 下午3:20:12 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
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
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.Probation;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerInRecordEvent;
import com.wondersgroup.human.repository.ofcflow.ProbationServantRepository;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.ProbationService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.service.ofcflow.ProbationServantService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.ofcflow.ProbationServantVO;

/** 
 * @ClassName: ProbationServantServiceImpl 
 * @Description: 试用期管理 服务接口实现类
 * @author: tzy
 * @date: 2018年7月25日 下午3:20:12
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ProbationServantServiceImpl extends GenericServiceImpl<ProbationServant> implements ProbationServantService{
	
	@Autowired
	private ProbationService probationService;
	
	@Autowired
	private DraftServantService draftServantService;
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private CodeInfoService codeInfoService;

	@Autowired
	private ProbationServantRepository probationServantRepository;

	@Autowired
	private FormationControlService formationControlService;
	@Autowired
	private JobLevelService jobLevelService;
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 列表分页查询 返回VO对象
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ProbationServantVO>
	 */
	public Page<ProbationServantVO> findbyHQLforVO(String hql, List<QueryParameter> listqueryparametry, Integer pageNo,Integer pagesize){
		Page<ProbationServant> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ProbationServantVO> voList = new ArrayList<>();
		for(ProbationServant s:temppage.getResult()){
			ProbationServantVO vo = new ProbationServantVO(s);
			voList.add(vo);
		}
		Page<ProbationServantVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: updateStatusById 
	 * @Description: 修改状态
	 * @param id	如果id中有逗号，批量修改
	 * @param status	状态
	 * @return: void
	 */
	public void updateStatusById(String id,int status){
		if(id.contains(",")){
			String[] ids = id.split(",");
			for (String i : ids) {
				ProbationServant ds = load(i);
				ds.setStatus(status);
				update(ds);
			}
		}else{
			ProbationServant ds = load(id);
			ds.setStatus(status);
			update(ds);
		}
	}
	/**
	 * @Title: getServantNotMemory 
	 * @Description: 修改试用期人员为在职
	 * @param id	试用期表 id
	 * @return: void
	 */
	public void getServantNotMemory(String id){
		ProbationServant p = get(id);
		DraftServant d = draftServantService.load(p.getDraftServant().getId());//拟录用 数据
		
		Servant servant = servantService.get(p.getServant().getId());
		servant.setIsOnHold(dictableService.getCodeInfoByCode("1", "DM200"));// 在职CODE
		servantService.update(servant);//修改为在职
		
		//职级子集
		JobLevel jobLevel = new JobLevel();
		jobLevel.setServant(servant);//人员信息
		CodeInfo YES = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);// 是 CODE
		jobLevel.setCurrentIdentification(YES);//现行职级
		
		CodeInfo code = dictableService.getCodeInfoByCode(FormationControl.CLERK, "GBT_12407_2008");//查询科员级的职级代码
		jobLevel.setName(code.getName());//职级名称
		jobLevel.setCode(code);//职级代码
		jobLevel.setIsLeader(p.getIsLeader());//是否领导
		jobLevel.setRealJobLevelCode(code.getCode());
		jobLevel.setRealLeader(CommonConst.NO);
		jobLevelService.save(jobLevel);
		
		Probation probation = new Probation();//试用情况
		probation.setServant(servant);//关联基本信息
		probation.setStartDate(p.getProbationStartDate());//试用开始时间
		probation.setEndDate(p.getProbationEndDate());//试用结束时间
		probation.setBecomeDate(p.getFormalDate());//入职转正日期
		probation.setBecomeNo(p.getFormalNumber());//入职转正批准文号
		probation.setUnitName(d.getDraftUnitName());//试用单位
		//probation.setConclusion(p.getCheckResult());试用期满考核结论
		probationService.save(probation);//保存试用信息
		
		//进出管理
		ManagerRecordDTO dto = new ManagerRecordDTO(servant.getId(),ManagerRecord.HUMAN_SY);
		ManagerInRecordEvent event = new ManagerInRecordEvent(dto);
		EventManager.send(event);
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 审批试用期信息
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ProbationServant temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(ProbationServant.STATUS_EMPLOY_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("试用期管理");//流程业务名称
			flow.setBusType("ProbationServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			if(temp.getProbationStatus()!=null){
				CodeInfo code = codeInfoService.get(temp.getProbationStatus().getId());//试用期考核结果，放入流程的remark字段，用于辨识试用期流程
				if(code!=null){
					flow.setRemark(code.getName());
				}
			}
			flow = workflowService.createFlowRecord(flow, "STATUS_EMPLOY_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow.setRemark(temp.getProbationStatus().getName());
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(flow==null){//试用期审核最后环节
			temp.setFlowRecord(null);//该业务没有待办流程节点 
			if(FlowRecord.PASS.equals(r)){
				if("2".equals(temp.getProbationStatus().getCode())){//延长试用期 更新 试用期结束时间
					Calendar cal = Calendar.getInstance();
					cal.setTime(temp.getProbationEndDate());//设置起时间
				    cal.add(Calendar.MONTH, temp.getDelayDate());//增加 延期时长月份
				    temp.setProbationEndDate(cal.getTime());//更新结束时间
				    temp.setProbationDate(temp.getProbationDate()+temp.getDelayDate());//试用时长增加
				    if(temp.getDelayDateDone()!=null){//设置延期时长，作为流程结束显示
				    	temp.setDelayDateDone(temp.getDelayDateDone()+temp.getDelayDate());
				    }else{
				    	temp.setDelayDateDone(temp.getDelayDate());
				    }
				    ProbationServant probation = new ProbationServant();
				    BeanUtils.copyPropertiesIgnoreNull(temp,probation);
				    probation.setId(null);
				    probation.setDelayReasonDone(temp.getDelayReason());//设置延期理由，作为流程结束显示
				    probation.setIsDelay(ProbationServant.SEND_ED);//设置是延期流程结束
				    
				    probation.setStatus(ProbationServant.STATUS_EMPLOY_STATE);//延长试用期审批通过，试用期结束时 可再次发起试用期流程
				    probation.setFlowRecord(null);
				    //清除延长试用期信息
				    probation.setProbationStatus(null);
				    probation.setDelayDate(null);
				    probation.setProbationStatus(null);
				    probation.setDelayReason(null);
				    
				    save(probation);//新增一条相同数据，使其是新流程，删除原数据
				    delete(temp);
				}else{
					//流程结束，改变编制
					DraftServant ds = draftServantService.get(temp.getDraftServant().getId());
					formationControlService.executeUnlockIntoFormationNum(ds.getDraftUnit().getOrgan().getId());//1.解锁调入单位未调入编制
					formationControlService.executeIntoFormation(ds.getDraftUnit().getOrgan().getId());//2.增加调入单位实际编制数
					//职级
					formationControlService.executeUnlockPostIntoNum(ds.getDraftUnit().getOrgan().getId(),FormationControl.CLERK,temp.getIsLeader());//1.解锁职级调入数
					
					temp.setStatus(ProbationServant.STATUS_EMPLOY_TRIAL_PASS);//试用期审批通过
					getServantNotMemory(temp.getId());//人员信息进入正式库
				}
			}else if(FlowRecord.STOP.equals(r)){//流程被中止
				//返编
				DraftServant ds = draftServantService.get(temp.getDraftServant().getId());
				formationControlService.executeUnlockIntoFormationNum(ds.getDraftUnit().getOrgan().getId());//1.解锁调入单位未调入编制
				//职级
				formationControlService.executeUnlockPostIntoNum(ds.getDraftUnit().getOrgan().getId(),FormationControl.CLERK,temp.getIsLeader());//1.解锁职级调入数
				
				Servant servant = servantService.get(temp.getServant().getId());
				servant.setIsOnHold(dictableService.getCodeInfoByCode("22", "DM200"));// 试用期离退CODE
				servantService.update(servant);//修改为试用期离退人员
				
				temp.setStatus(FlowRecord.BUS_STOP);
				
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"试用期"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"试用期"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "","试用期")));
			}
		}else{
			temp.setStatus(ProbationServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
	/**
	 * @Title: saveFlowCancel 
	 * @Description: 审批试用期信息 取消录用
	 * @param temp
	 * @return: void
	 */
	public void saveFlowCancel(ProbationServant temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(ProbationServant.STATUS_EMPLOY_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("试用期取消录用");//流程业务名称
			flow.setBusType("CancelProbationServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			if(temp.getProbationStatus()!=null){
				CodeInfo code = codeInfoService.get(temp.getProbationStatus().getId());//试用期考核结果，放入流程的remark字段，用于辨识试用期流程
				if(code!=null){
					flow.setRemark(code.getName());
				}
			}
			flow = workflowService.createFlowRecord(flow, "STATUS_EMPLOY_STATE_2");//初始节点
		}else if(ProbationServant.STATUS_EMPLOY_STATE==temp.getStatus()&&temp.getFlowRecord()!=null){
			//考核合格或者延长试用期流程审批不通过到最初节点，修改为考核不合格进入此方法时，启动考核不合格流程
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow.setRemark(temp.getProbationStatus().getName());
			flow = workflowService.forwardNewFlowRecord(flow,"STATUS_EMPLOY_STATE_2");//提交下个节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow.setRemark(temp.getProbationStatus().getName());
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(flow==null&&FlowRecord.PASS.equals(r)){//试用期审核最后环节
			temp.setStatus(ProbationServant.STATUS_EMPLOY_TRIAL_CONFIRM_DONE);//取消录用，区人事主管部门已备案确认
			temp.setCancelFlag(ProbationServant.SEND_ED);//取消录用标识
			temp.setFlowRecord(null);//该业务没有待办流程节点 
			
			//流程结束，改变编制
			DraftServant ds = draftServantService.get(temp.getDraftServant().getId());
			formationControlService.executeUnlockIntoFormationNum(ds.getDraftUnit().getOrgan().getId());//1.解锁调入单位未调入编制
			//职级
			formationControlService.executeUnlockPostIntoNum(ds.getDraftUnit().getOrgan().getId(),FormationControl.CLERK,temp.getIsLeader());//1.解锁职级调入数
			
			Servant servant = servantService.get(temp.getServant().getId());
			servant.setIsOnHold(dictableService.getCodeInfoByCode("22", "DM200"));// 试用期离退CODE
			servantService.update(servant);//修改为试用期离退人员
		}else{
			temp.setStatus(ProbationServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}

	/**
	 * @return 获取各单位试用期人数统计 返回orginfoid 和 count数量
	 */
	@Override
	public Map<String, Integer> getUnitProbationCount() {
		return probationServantRepository.getUnitProbationCount();
	}
}
