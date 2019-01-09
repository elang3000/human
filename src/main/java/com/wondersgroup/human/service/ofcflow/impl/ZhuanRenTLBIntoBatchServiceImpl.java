/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBBatchServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 同类别转任 批量数据表 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年12月7日 上午9:54:53 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月7日 上午9:54:53 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.config.ReadProperties;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
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
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Experience;
import com.wondersgroup.human.bo.ofc.IntoMgr;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.Material;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBIntoBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOut;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerInRecordEvent;
import com.wondersgroup.human.repository.ofcflow.ZhuanRenTLBIntoRepository;
import com.wondersgroup.human.service.ofc.ExperienceService;
import com.wondersgroup.human.service.ofc.IntoMgrService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.MaterialService;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenTLBIntoBatchService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenTLBIntoService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenTLBOutService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBIntoBatchVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/** 
 * @ClassName: ZhuanRenTLBBatchServiceImpl 
 * @Description: 同类别转任 批量数据表 服务实现类
 * @author: tzy
 * @date: 2018年12月7日 上午9:54:53
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ZhuanRenTLBIntoBatchServiceImpl extends GenericServiceImpl<ZhuanRenTLBIntoBatch> implements ZhuanRenTLBIntoBatchService{
	@Autowired
	private ZhuanRenTLBIntoService zhuanRenTLBIntoService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private IntoMgrService intoMgrService;
	@Autowired
	private OutMgrService outMgrService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private ZhuanRenTLBIntoRepository repository;
	@Autowired
	private PostService postService;
	@Autowired
	private JobLevelService jobLevelService;
	@Autowired
	private FormationControlService formationControlService;
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ZhuanRenTLBOutService zhuanRenTLBOutService;
	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;
	@Autowired
	private MaterialService materialService;
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ZhuanRenTLBIntoBatchVO>
	 */
	public Page<ZhuanRenTLBIntoBatchVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<ZhuanRenTLBIntoBatch> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ZhuanRenTLBIntoBatchVO> voList = new ArrayList<>();
		for(ZhuanRenTLBIntoBatch s:temppage.getResult()){
			ZhuanRenTLBIntoBatchVO vo = new ZhuanRenTLBIntoBatchVO(s);
			voList.add(vo);
		}
		Page<ZhuanRenTLBIntoBatchVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: remove 
	 * @Description: 根据批量表ID删除 批次数据并删除批次下人员数据
	 * @param id
	 * @return: void
	 */
	public void remove(String id){
		DetachedCriteria criteria = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		criteria.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", id));
		criteria.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> list = zhuanRenTLBIntoService.findByCriteria(criteria);
		for(ZhuanRenTLBInto t : list){
			zhuanRenTLBIntoService.delete(t);
		}
		ZhuanRenTLBIntoBatch z = get(id);
		delete(z);
	}
	
	/**
	 * @Title: savePeople 
	 * @Description: 添加本区转任人员信息
	 * @param id
	 * @param servantIds	人员ID，逗号分隔
	 * @return: void
	 */
	public void savePeople(String id,String servantIds){
		ZhuanRenTLBIntoBatch z = get(id);
		String[] idArr = servantIds.split(",");
		for(String i : idArr){
			Servant servant = servantService.get(i);
			ZhuanRenTLBInto zz = new ZhuanRenTLBInto();
			zz.setZhuanRenTLBIntoBatch(z);//关联批次信息
			zz.setServant(servant);//关联人员信息
			zz.setFormerUnitName(z.getSourceOrgan().getName());
			zhuanRenTLBIntoService.save(zz);
		}
	}
	/**
	 * @Title: checkNumber 
	 * @Description: 校验转任人数是否和校验编制各职级人数一致
	 * @param list
	 * @return: void
	 */
	private void checkNumber(ZhuanRenTLBIntoBatch temp){
		DetachedCriteria criteria = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		criteria.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", temp.getId()));
		criteria.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> list = zhuanRenTLBIntoService.findByCriteria(criteria);//该批次下所有转任人员信息，判断各个职级人数是否和批次中人数一致
		
		int plusKeLeaderNum = 0;//乡科级正职（领导）转任人数
		int plusKeNoLeaderNum = 0;//乡科级正职（非领导）转任人数
		int minusKeLeaderNum = 0;//乡科级副职（领导）转任人数
		int minusKeNoLeaderNum = 0;//乡科级副职（非领导）转任人数
		int clerkNumber = 0;//科员转任人数
		int cClerkNumber = 0;//办事员转任人数
		for(ZhuanRenTLBInto i : list){
			if(i.getJobLevelCode()==null){
				throw new BusinessException("请先编辑所有人员的转任信息！");
			}
			String code = i.getJobLevelCode().getCode();
			if(FormationControl.SECTION_CHIEF.equals(code)){//乡科级正职
				if(i.getIsLeader()==CommonConst.YES){//领导
					plusKeLeaderNum++;
				}else if(i.getIsLeader()==CommonConst.NO){//非领导
					plusKeNoLeaderNum++;
				}
			}else if(FormationControl.DEPUTY_SECTION_CHIEF.equals(code)){//乡科级副职
				if(i.getIsLeader()==CommonConst.YES){//领导
					minusKeLeaderNum++;
				}else if(i.getIsLeader()==CommonConst.NO){//非领导
					minusKeNoLeaderNum++;
				}
			}else if(FormationControl.CLERK.equals(code)){//科员
				clerkNumber++;
			}else if(FormationControl.C_CLERK.equals(code)){//办事员
				cClerkNumber++;
			}else{
				throw new BusinessException(i.getJobLevelName()+" 该职级信息校验异常！");
			}
		}
		if((temp.getPlusKeLeaderNum()!=null&&temp.getPlusKeLeaderNum()!=plusKeLeaderNum)||(temp.getPlusKeLeaderNum()==null&&plusKeLeaderNum>0)){//乡科级正职（领导）
			throw new BusinessException("乡科级正职（领导）人数和校验人数不一致！");
		}
		if((temp.getPlusKeNoLeaderNum()!=null&&temp.getPlusKeNoLeaderNum()!=plusKeNoLeaderNum)||(temp.getPlusKeNoLeaderNum()==null&&plusKeNoLeaderNum>0)){//乡科级正职（非领导）
			throw new BusinessException("乡科级正职（非领导）人数和校验人数不一致！");
		}
		if((temp.getMinusKeLeaderNum()!=null&&temp.getMinusKeLeaderNum()!=minusKeLeaderNum)||(temp.getMinusKeLeaderNum()==null&&minusKeLeaderNum>0)){//乡科级副职（领导）
			throw new BusinessException("乡科级副职（领导）人数和校验人数不一致！");
		}
		if((temp.getMinusKeNoLeaderNum()!=null&&temp.getMinusKeNoLeaderNum()!=minusKeNoLeaderNum)||(temp.getMinusKeNoLeaderNum()==null&&minusKeNoLeaderNum>0)){//乡科级副职（非领导）
			throw new BusinessException("乡科级副职（非领导）人数和校验人数不一致！");
		}
		if((temp.getClerkNumber()!=null&&temp.getClerkNumber()!=clerkNumber)||(temp.getClerkNumber()==null&&clerkNumber>0)){//科员
			throw new BusinessException("科员级人数和校验人数不一致！");
		}
		if((temp.getcClerkNumber()!=null&&temp.getcClerkNumber()!=cClerkNumber)||(temp.getcClerkNumber()==null&&cClerkNumber>0)){//办事员
			throw new BusinessException("办事员级人数和校验人数不一致！");
		}
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 审批本区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ZhuanRenTLBIntoBatch temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE==temp.getStatus()){//提交环节，校验转任人数是否正确，检验编制，锁编
			//1.校验转任人数是否正确
			checkNumber(temp);
			//2.校验编控，锁编，回写真实占编数据
			List<JudgePostResult> list = executeValidateAndLockFormation(temp);
			//3.锁调出单位编制
			formationControlService.executeLockOutFormationAndPost(temp.getSourceOrgan().getId(),list);
		}
		
		FlowRecord flow;
		if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，生成流程数据
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("同类别转任");//流程业务名称
			flow.setBusType("ZhuanRenTLBIntoMgr_THIS");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_ZHUANREN_STATE");//初始节点
		}else{
			if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE==temp.getStatus()){
				r = FlowRecord.PASS;
			}
			
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_TRIAL_4==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事审核通过之后，生成转出单位待办信息，生成转出数据，生成介绍信
				createOut(temp);//生成转出信息
				flow = workflowService.completeFlowRecordByAppoint(flow,temp.getSourceOrgan());
				createIntroduction(temp.getId());//生成介绍信
			}else if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事主管部门打印电子介绍信之后，更新转入转出子集信息
				createServant(temp);//更新人员基本信息
				//流程结束，改变编制
				executeFinish(temp);
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}else{
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}
		}
		if(flow==null){
			temp.setFlowRecord(null);//修改当前业务的流程节点
			if(FlowRecord.PASS.equals(r)){//流程最后环节
				temp.setStatus(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_FINISH);
				//发送通知
				sendNotice(temp);
			}else if(FlowRecord.STOP.equals(r)){//流程被中止
				executeUnLockFormationAndPost(temp);//返编
				
				temp.setStatus(FlowRecord.BUS_STOP);
				
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"同类别转任"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"同类别转任"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "","同类别转任")));
			}
			
		}else{
			temp.setStatus(ZhuanRenTLBIntoBatch.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
			
			if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE==temp.getStatus()&&FlowRecord.NOPASS.equals(r)){//如果流程退回到最初节点，解锁编制，每次提交重新锁编
				executeUnLockFormationAndPost(temp);
			}
		}
		update(temp);
	}
	/**
	 * @Title: saveFlowOuter 
	 * @Description: 审批外区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlowOuter(ZhuanRenTLBIntoBatch temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE==temp.getStatus()){//提交环节，检验编制，锁编
			//1.校验转任人数是否正确
			checkNumber(temp);
			//2.校验编控，锁编，回写真实占编数据
			executeValidateAndLockFormation(temp);
		}
		
		FlowRecord flow;
		if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("同类别转任");//流程业务名称
			flow.setBusType("ZhuanRenTLBIntoMgr_OUTER");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_ZHUANREN_OUTER_STATE");//初始节点
		}else{
			if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE==temp.getStatus()){
				r = FlowRecord.PASS;
			}
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_TRIAL_4==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事审核通过之后，生成介绍信
				createIntroduction(temp.getId());//生成介绍信
			}else if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事主管部门打印电子介绍信之后，更新转入转出子集信息
				createServant(temp);//更新人员基本信息
				//流程结束，改变编制
				executeFinish(temp);
			}
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(flow==null){
			temp.setFlowRecord(null);//修改当前业务的流程节点
			if(FlowRecord.PASS.equals(r)){//流程最后环节
				temp.setStatus(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_FINISH);
				//发送通知
				sendNotice(temp);
			}else if(FlowRecord.STOP.equals(r)){//流程被中止
				executeUnLockFormationAndPost(temp);//返编
				
				temp.setStatus(FlowRecord.BUS_STOP);
				
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"同类别转任"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"同类别转任"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "","同类别转任")));
			}
			
		}else{
			temp.setStatus(ZhuanRenTLBIntoBatch.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
			
			if(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE==temp.getStatus()&&FlowRecord.NOPASS.equals(r)){//如果流程退回到最初节点，解锁编制，每次提交重新锁编
				executeUnLockFormationAndPost(temp);
			}
			
		}
		update(temp);
	}
	/**
	 * @Title: executeFinish 
	 * @Description: 流程结束，
	 * @param temp
	 * @return: void
	 */
	public void executeFinish(ZhuanRenTLBIntoBatch temp){
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		d.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> intoList = zhuanRenTLBIntoService.findByCriteria(d);
		
		if(ZhuanRenTLBIntoBatch.AREA_THIS.equals(temp.getAreaType())){//本区
			formationControlService.executeUnlockOutFormationNum(temp.getSourceOrgan().getId(),intoList.size());//2.解锁调出单位未调出编制
			formationControlService.executeOutFormation(temp.getSourceOrgan().getId(),intoList.size());//4.减少调出单位实际编制数
		}
		
		formationControlService.executeUnlockIntoFormationNum(temp.getTargetOrgan().getId(),intoList.size());//1.解锁调入单位未调入编制
		formationControlService.executeIntoFormation(temp.getTargetOrgan().getId(),intoList.size());//3.增加调入单位实际编制数
		
		for(ZhuanRenTLBInto z : intoList){
			if(ZhuanRenTLBIntoBatch.AREA_THIS.equals(temp.getAreaType())){//本区
				
				formationControlService.executeUnlockPostOutNum(temp.getSourceOrgan().getId(),z.getSourceRealJobLevelCode(), z.getSourceRealLeader());//2.解锁职级调出数
				formationControlService.executeOutPost(temp.getSourceOrgan().getId(),z.getSourceRealJobLevelCode(), z.getSourceRealLeader());//4.减少调出单位实际职级数
			}
			
			//职级
			formationControlService.executeUnlockPostIntoNum(temp.getTargetOrgan().getId(),z.getRealJobLevelCode(),z.getRealLeader());//1.解锁职级调入数
//			formationControlService.executeIntoPost(temp.getTargetOrgan().getId(),tempJ.getCode().getCode(),temp.getIsLowToHigh());//3.增加调入单位实际职级数  职级save方法已增加
		}
	}
	/**
	 * @Title: executeUnLockFormationAndPost 
	 * @Description: 返编 未调入/未调出
	 * @param temp
	 * @return: void
	 */
	public void executeUnLockFormationAndPost(ZhuanRenTLBIntoBatch temp){
		List<JudgePostResult> list = new ArrayList<>();//转入单位真实职级
		List<JudgePostResult> list2 = new ArrayList<>();//原单位真实职级
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		d.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> intoList = zhuanRenTLBIntoService.findByCriteria(d);
		for(ZhuanRenTLBInto z : intoList){
			JudgePostResult j = new JudgePostResult(z.getRealJobLevelCode(), z.getRealLeader(),1);
			list.add(j);
			if(ZhuanRenTLBIntoBatch.AREA_THIS.equals(temp.getAreaType())){//本区
				JudgePostResult j2 = new JudgePostResult(z.getSourceRealJobLevelCode(), z.getSourceRealLeader(),1);
				list2.add(j2);
			}
		}
		if(ZhuanRenTLBIntoBatch.AREA_THIS.equals(temp.getAreaType())){//本区
			formationControlService.executeUnLockOutFormationAndPost(temp.getSourceOrgan().getId(), list2);//返编未调出
		}
		formationControlService.executeUnLockIntoFormationAndPost(temp.getTargetOrgan().getId(), list);//返编未调入
	}
	/**
	 * @Title: executeValidateAndLockFormation 
	 * @Description: 校验编控并锁编
	 * @param temp
	 * @return: List<JudgePostResult> 原单位职级信息
	 */
	public List<JudgePostResult> executeValidateAndLockFormation(ZhuanRenTLBIntoBatch temp){
		List<JudgePostResult> list = new ArrayList<>();//用于校验的职级信息
		List<JudgePostResult> list2 = new ArrayList<>();//原单位职级信息，本区才使用
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		d.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> intoList = zhuanRenTLBIntoService.findByCriteria(d);
		for(ZhuanRenTLBInto z : intoList){
			JudgePostResult j = new JudgePostResult(z.getJobLevelCode().getCode(), z.getIsLeader(),1);
			list.add(j);
			if(ZhuanRenTLBIntoBatch.AREA_THIS.equals(temp.getAreaType())){//本区
				if(StringUtils.isBlank(z.getSourceRealJobLevelCode())){
					throw new BusinessException("占编职级信息异常，请联系管理员！");
				}
				JudgePostResult j2 = new JudgePostResult(z.getSourceRealJobLevelCode(), z.getSourceRealLeader(),1);
				list2.add(j2);
			}
		}
		List<JudgePostResult> result = formationControlService.executeValidateAndLockFormation(temp.getTargetOrgan().getId(), list);//校验编控并锁编
		for(int i=0;i<intoList.size();i++){//回写真实占编数据
			ZhuanRenTLBInto z = intoList.get(i);
			z.setRealJobLevelCode(result.get(i).getPostLvlCode());
			z.setRealLeader(result.get(i).getIsLeader());
			zhuanRenTLBIntoService.update(z);
		}
		return list2;
	}
	/**
	 * @Title: queryValidateFormationAndPostLvlNum 
	 * @Description: 校验编控
	 * @param temp
	 * @return: void
	 */
	public void queryValidateFormationAndPostLvlNum(ZhuanRenTLBIntoBatch temp){
		List<JudgePostResult> list = new ArrayList<>();
//		编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
		if(temp.getPlusKeLeaderNum()!=null&&temp.getPlusKeLeaderNum()>0){//乡科级正职（领导）
			JudgePostResult j = new JudgePostResult(FormationControl.SECTION_CHIEF, CommonConst.YES,temp.getPlusKeLeaderNum());
			list.add(j);
		}
		if(temp.getPlusKeNoLeaderNum()!=null&&temp.getPlusKeNoLeaderNum()>0){//乡科级正职（非领导）
			JudgePostResult j = new JudgePostResult(FormationControl.SECTION_CHIEF, CommonConst.NO,temp.getPlusKeNoLeaderNum());
			list.add(j);
		}
		if(temp.getMinusKeLeaderNum()!=null&&temp.getMinusKeLeaderNum()>0){//乡科级副职（领导）
			JudgePostResult j = new JudgePostResult(FormationControl.DEPUTY_SECTION_CHIEF, CommonConst.YES,temp.getMinusKeLeaderNum());
			list.add(j);
		}
		if(temp.getMinusKeNoLeaderNum()!=null&&temp.getMinusKeNoLeaderNum()>0){//乡科级副职（非领导）
			JudgePostResult j = new JudgePostResult(FormationControl.DEPUTY_SECTION_CHIEF, CommonConst.NO,temp.getMinusKeNoLeaderNum());
			list.add(j);
		}
		if(temp.getClerkNumber()!=null&&temp.getClerkNumber()!=0){//科员级
			JudgePostResult j = new JudgePostResult(FormationControl.CLERK, CommonConst.NO,temp.getClerkNumber());
			list.add(j);
		}
		if(temp.getcClerkNumber()!=null&&temp.getcClerkNumber()!=0){//办事员级
			JudgePostResult j = new JudgePostResult(FormationControl.C_CLERK, CommonConst.NO,temp.getcClerkNumber());
			list.add(j);
		}
		formationControlService.queryValidateFormationAndPostLvlNum(temp.getTargetOrgan().getId(), list);//校验编控
	}
	/**
	 * @Title: sendNotice 
	 * @Description: 给该批次下所有人员发送通知
	 * @param temp
	 * @return: void
	 */
	private void sendNotice(ZhuanRenTLBIntoBatch temp){
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		d.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> list = zhuanRenTLBIntoService.findByCriteria(d);
		for(ZhuanRenTLBInto z : list){
			String name = z.getName();//外区转任人员
			//发送通知
			if(ZhuanRenTLBIntoBatch.AREA_THIS.equals(temp.getAreaType())){//本区转任人员
				Servant tempS = servantService.get(z.getServant().getId());
				name = tempS.getName();
			}
			String title = messageSource.getMessage("zhuanRenTitle", new Object[]{name}, Locale.CHINESE);
			String content = messageSource.getMessage("zhuanRenContent", new Object[]{name}, Locale.CHINESE);
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, z.getCreater(), title, content, "","同类别转任")));
		}
	}
	/**
	 * @Title: createServant
	 * @Description: 更新人员基本信息和转入转出子集信息
	 * @param id
	 * @return: void
	 */
	public void createServant(ZhuanRenTLBIntoBatch temp){
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		d.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> list = zhuanRenTLBIntoService.findByCriteria(d);
		for(ZhuanRenTLBInto z : list){
			Servant servant = z.getServant();
			if(servant==null){//外区转任，需要生成人员基本信息和转入转出、职务子集信息
				servant = new Servant();//人员基本信息
				servant.setName(z.getName());//姓名
				servant.setCardNo(z.getCardNo());//身份证
				servant.setSex(z.getSex());//性别
				servant.setBirthDate(z.getBirthDate());//出生日期
				servant.setNativePlaceName(z.getNativePlaceName());//籍贯名称
				servant.setNativePlace(z.getNativePlace());//籍贯
				servant.setBirthPlaceName(z.getBirthPlaceName());//出生地名称
				servant.setBirthPlace(z.getBirthPlace());//出生地
				servant.setNation(z.getNation());//民族
				servant.setPolitics(z.getPolitics());//政治面貌
				servant.setHealth(z.getHealth());//健康状况
				servant.setPersonType(z.getPersonType());//人员类别
				
				servant.setDepartId(temp.getTargetOrgan().getId());//单位id
				servant.setDepartName(temp.getTargetOrgan().getName());//单位名称
				servant.setDepartCode(temp.getTargetOrgan().getCode());//单位代码
				CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
				servant.setIsOnHold(isOnHold);//在职状态
				servant.setNowPostName(z.getPostName());//职务名称
				servant.setNowPostCode(z.getPostCode());//职务代码
				servant.setNowPostAttribute(z.getAttribute());//职务属性
				servant.setPhotoPath(z.getPhotoPath());//头像
				servantService.save(servant);
			}else{//如果是本区转任，更新该人员原数据的在职状态为调出，并生成新数据和转入转出、职务子集信息
				//更新简历
				Experience e = experienceService.getLatestExperienceByServantId(servant.getId());
				e.setEndDate(new Date());
				experienceService.update(e);
				
				Map<String,String> params = new HashMap<>();//存储过程入参
				params.put("SERVANTID", servant.getId());
				List<String> backList = new ArrayList<>();//返回参数名
				backList.add("SERVANTNEWID");
				backList.add("prm_AppCode");
				backList.add("prm_ErrorMsg");
				ProcedureOutputs out = repository.executeStoreProcedure("COPY_SERVANT", params,backList);//调用复制人员存储过程，返回新生成人员id
				String servantNewId = (String)out.getOutputParameterValue(backList.get(0));//新生成人员数据id
				//生成新数据
				servant= servantService.get(servantNewId);
				servant.setDepartId(temp.getTargetOrgan().getId());//单位id
				servant.setDepartName(temp.getTargetOrgan().getName());//单位名称
				servant.setDepartCode(temp.getTargetOrgan().getCode());//单位代码
				CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
				servant.setIsOnHold(isOnHold);//在职状态
				servant.setNowPostName(z.getPostName());//职务名称
				servant.setNowPostCode(z.getPostCode());//职务代码
				servant.setNowPostAttribute(z.getAttribute());//职务属性
				servantService.update(servant);
				//修改原数据状态为调出
				Servant oldServant = servantService.get(z.getServant().getId());
				CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
				oldServant.setIsOnHold(outer);//调出状态
				servantService.update(oldServant);
			}
			//职务子集
			Post post = new Post();
			post.setServant(servant);//人员信息
			CodeInfo YES = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);// 是 CODE
			CodeInfo inOfficeCode = dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_TYPE_POST_STATUS);// 在任
			post.setTenureStatus(inOfficeCode);//在任职务
			CodeInfo NO = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);//否 CODE
			post.setHighestPostSign(NO);//最高职务
			post.setTenureName(temp.getTargetOrgan().getName());//任职机构名称
			post.setTenureCode(temp.getTargetOrgan().getCode());//任职机构代码
			post.setTakeDate(new Date());
			post.setAttribute(z.getAttribute());//职务属性
			if(z.getPostCode()!=null){
				post.setPostName(z.getPostName());//职务名称
				post.setPostCode(z.getPostCode());//职务代码
			}else{
				Servant oldServant = servantService.get(z.getServant().getId());
				post.setPostName(oldServant.getNowPostName());//职务名称
				post.setPostCode(oldServant.getNowPostCode());//职务代码
			}
			postService.saveOrUpdate(post);
			//增加简历子集信息
			Experience experience = new Experience();
			experience.setServant(servant);//人员信息
			experience.setFormerUnit(temp.getTargetOrgan().getName());//所在单位
			experience.setFormerJob(post.getPostName());//担任职务
			experience.setStartDate(new Date());//开始时间
			experienceService.save(experience);
			//职级子集
			JobLevel jobLevel = new JobLevel();
			jobLevel.setServant(servant);//人员信息
			jobLevel.setCurrentIdentification(YES);//现行职级
			jobLevel.setName(z.getJobLevelName());//职级名称
			jobLevel.setCode(z.getJobLevelCode());//职级代码
			jobLevel.setIsLeader(z.getIsLeader());//是否领导
			jobLevel.setRealJobLevelCode(z.getRealJobLevelCode());//真实占编的职级code
			jobLevel.setRealLeader(z.getRealLeader());//真实占编的是否领导值
			jobLevelService.save(jobLevel);
			
			IntoMgr into = new IntoMgr();//转入子集信息
			into.setServant(servant);//人员信息
			into.setEnterTheUnitChangeType(z.getEnterTheUnitChangeType());//进入本单位变动类别
	//		into.setEnterReason(z.getEnterReason());//进入本单位原因
			into.setEnterTheUnitDate(z.getEnterTheUnitDate());//进入本单位日期
			into.setFormerUnitName(z.getFormerUnitName());//进入本单位前工作单位名称
			into.setFormerUnitJobName(z.getFormerUnitJobName());//在原单位职务名称
			into.setFormerUnitRank(z.getFormerUnitRank());//在原单位职务级别
			intoMgrService.save(into);
			
			if(ZhuanRenTLBIntoBatch.AREA_THIS.equals(temp.getAreaType())){//本区转任才新增一条转出子集
				OutMgr out = new OutMgr();//转出子集信息
				ZhuanRenTLBOut o = zhuanRenTLBOutService.get(z.getZhuanRenTLBOut().getId());
				out.setServant(servant);//人员基本信息
				out.setCategory(o.getCategory());//调出本单位类别
				out.setReasonType(o.getReasonType());//调动原因
				out.setOutDate(o.getOutDate());//调出本单位日期
				out.setGotoUnitName(o.getGotoUnitName());//调往单位名称
				out.setProposeType(o.getProposeType());//提出调动类型
				out.setRemark(o.getRemark());//调出备注
				outMgrService.save(out);
			}
			//进出管理
			ManagerRecordDTO dto = new ManagerRecordDTO(servant.getId(),ManagerRecord.HUMAN_TLZR);
			ManagerInRecordEvent event = new ManagerInRecordEvent(dto);
			EventManager.send(event);
		}
	}
	/**
	 * @Title: createOut 
	 * @Description: 发起同类别转任，转出流程
	 * @param id
	 * @return: void
	 */
	private void createOut(ZhuanRenTLBIntoBatch temp){
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		d.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> list = zhuanRenTLBIntoService.findByCriteria(d);
		for(ZhuanRenTLBInto z : list){
			DetachedCriteria d2 = DetachedCriteria.forClass(ZhuanRenTLBOut.class);
			d2.add(Restrictions.eq("zhuanRenTLBInto", z));
			List<ZhuanRenTLBOut> list2 = zhuanRenTLBOutService.findByCriteria(d2);
			if(list2==null||list2.size()==0){
				ZhuanRenTLBOut out = new ZhuanRenTLBOut();
				out.setZhuanRenTLBInto(z);//关联转入数据
				out.setServant(z.getServant());//人员信息
				out.setOutDate(temp.getEnterTheUnitDate());//调出时间
				out.setGotoUnitName(temp.getTargetOrgan().getName());//调往单位名称
//				out.setReasonType(z.getEnterReason());//调动原因
				zhuanRenTLBOutService.save(out);
				ZhuanRenTLBInto zz = zhuanRenTLBIntoService.get(z.getId());
				zz.setZhuanRenTLBOut(out);
				zhuanRenTLBIntoService.saveOrUpdate(zz);
			}
		}
	}
	/**
	 * @Title: createIntroduction 
	 * @Description: 生成该批次的介绍信  每个人员一个介绍信，一个编号
	 * @param id		批次id
	 * @return: void
	 */
	private void createIntroduction(String id){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String templet = savePath+"\\static\\templates\\introduce.xls";//模板路径
		String folder = ReadProperties.getInstance().FTP_DIR_NAME_MATERIAL;//ftp文件路径
		
		Map<String,Object> params = new HashMap<>();//所有参数
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ZhuanRenTLBIntoBatch batch = get(id);
		params.put("targetOrgan", batch.getTargetOrgan()==null?"":batch.getTargetOrgan().getName());//目标单位名称
		params.put("enterTheUnitDate", batch.getEnterTheUnitDate()==null?"":sdf.format(batch.getEnterTheUnitDate()));//报道日期
		
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenTLBInto.class);
		d.add(Restrictions.eq("zhuanRenTLBIntoBatch.id", id));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenTLBInto> list = zhuanRenTLBIntoService.findByCriteria(d);//批次下所有转任人员信息
		params.put("sumNumber", "壹");//转任人数
		params.put("now", sdf.format(new Date()));//打印介绍信时间
		params.put("busType", "同类别转任");//转移原因
		//当前操作人
		params.put("userName", SecurityUtils.getPrincipal().getName());
		for(int i =0;i<list.size();i++){
			ZhuanRenTLBInto into = list.get(i);
			params.put("sourceOrgan", into.getFormerUnitName());//源单位名称
			Material m = new Material();//材料信息 一定要生成
			try {
				m.setBusId(into.getId());
				m.setBusType("ZhuanRenTLBInto");
				m.setBusName("同类别转入");
				List<String[]> dataList = new ArrayList<>();//所有人员信息
				String[] p = new String[6];
				p[2] = into.getFormerUnitName();//源单位名称
				if(ZhuanRenTLBIntoBatch.AREA_THIS.equals(into.getZhuanRenTLBIntoBatch().getAreaType())){//本区
					if(i==0){
						params.put("name", list.get(0).getServant().getName());
					}
					p[0] = into.getServant().getName();//转任人员名称
					p[1] = into.getServant().getSex()==null?"":into.getServant().getSex().getName();//调任人员性别
					p[4] = into.getServant().getNowPostName()==null?"":into.getServant().getNowPostName();//原职务
					p[5] = into.getJobLevelName()==null?"":into.getJobLevelName();//原级别
				}else{
					if(i==0){
						params.put("name", into.getName());
					}
					p[0] = into.getName();//转任人员名称
					p[1] = into.getSex()==null?"":into.getSex().getName();//性别
					p[4] = "";//原职务
					p[5] = "";//原级别
				}
				dataList.add(p);
				
				Map<String,String> number = ofcFlowNumberService.executeNumber("ZhuanRenTLB", into.getId());//介绍信编号
				params.put("number", number.get("year")+"字第"+number.get("number"));//介绍信编号
				//编号转换为大写
				String cnYear = Number2CN.convert(number.get("year"));
				String cnNumber = Number2CN.convert(number.get("number"));
				params.put("cnNumber", cnYear+"字第"+cnNumber+"号");//介绍信编号大写
				params.put("listData", dataList);//list数据需要用list标签

				String uuid = UUID.randomUUID().toString();
				String path = savePath+"\\static\\templates\\"+uuid+".xls";//生成excel文件路径，临时存放，上传到ftp服务器之后会删除
				ExcelUtilsPOI.replaceModel(params, templet,path, 2,null);//替换模板数据 生成excel到tomcat服务器
				
				//保存文件到ftp服务器
				String fileName = uuid+".xls";
				
				File file = new File(path);
				if(FtpTool.ftpUpload(folder, file, fileName)){//文件成功上传到ftp，删除本地文件并保存材料表数据
					m.setFlowNumber(number.get("number"));
					m.setName(dataList.get(0)[0]+"转任介绍信"+".xls");
					m.setFtpFilefolder(folder);
					m.setFtpFileName(fileName);
					file.delete();//删除文件
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			materialService.save(m);
		}
	}
}
