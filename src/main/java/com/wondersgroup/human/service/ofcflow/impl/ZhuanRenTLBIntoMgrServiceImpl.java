/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBIntoMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 同类别转任 调入情况信息 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月1日 上午11:00:49 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月1日 上午11:00:49 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
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
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.IntoMgr;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.EventDegree;
import com.wondersgroup.human.bo.ofcflow.EventEducation;
import com.wondersgroup.human.bo.ofcflow.EventExperience;
import com.wondersgroup.human.bo.ofcflow.EventFamily;
import com.wondersgroup.human.bo.ofcflow.EventPost;
import com.wondersgroup.human.bo.ofcflow.EventRewardAndPunish;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBIntoMgr;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutMgr;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerOutRecordEvent;
import com.wondersgroup.human.repository.ofcflow.ZhuanRenTLBIntoMgrRepository;
import com.wondersgroup.human.service.ofc.IntoMgrService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.EventDegreeService;
import com.wondersgroup.human.service.ofcflow.EventEducationService;
import com.wondersgroup.human.service.ofcflow.EventExperienceService;
import com.wondersgroup.human.service.ofcflow.EventFamilyService;
import com.wondersgroup.human.service.ofcflow.EventPostService;
import com.wondersgroup.human.service.ofcflow.EventRewardAndPunishService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenTLBIntoMgrService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenTLBOutMgrService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBIntoMgrVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/** 
 * @ClassName: ZhuanRenTLBIntoMgrServiceImpl 
 * @Description: 同类别转任 转入情况信息 服务实现类
 * @author: tzy
 * @date: 2018年8月1日 上午11:00:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ZhuanRenTLBIntoMgrServiceImpl extends GenericServiceImpl<ZhuanRenTLBIntoMgr> implements ZhuanRenTLBIntoMgrService{
	
	@Autowired
	private EventDegreeService eventDegreeService;
	@Autowired
	private EventEducationService eventEducationService;
	@Autowired
	private EventExperienceService eventExperienceService;
	@Autowired
	private EventFamilyService eventFamilyService;
	@Autowired
	private EventPostService eventPostService;
	@Autowired
	private EventRewardAndPunishService eventRewardAndPunishService;
	@Autowired
	private ZhuanRenTLBOutMgrService zhuanRenTLBOutMgrService;
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
	private CodeInfoService codeInfoService;
	@Autowired
	private ZhuanRenTLBIntoMgrRepository repository;
	@Autowired
	private PostService postService;
	@Autowired
	private JobLevelService jobLevelService;
	@Autowired
	private FormationControlService formationControlService;

	/**
	 * 读取message.properties配置文件数据
	 */
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ZhuanRenTLBIntoMgrVO>
	 */
	public Page<ZhuanRenTLBIntoMgrVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<ZhuanRenTLBIntoMgr> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ZhuanRenTLBIntoMgrVO> voList = new ArrayList<>();
		for(ZhuanRenTLBIntoMgr s:temppage.getResult()){
			ZhuanRenTLBIntoMgrVO vo = new ZhuanRenTLBIntoMgrVO(s);
			voList.add(vo);
		}
		Page<ZhuanRenTLBIntoMgrVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: remove 
	 * @Description: 删除 转任调入信息并删除根据该人员姓名和身份证号删除子集
	 * @return: void
	 */
	public void remove(String id){
		ZhuanRenTLBIntoMgr z = load(id);
		
		List<Predicate> filter = new ArrayList<>();//查询条件
		filter.add(new Predicate("personName", Operator.EQ,z.getName(), ""));
		filter.add(new Predicate("cardNo", Operator.EQ,z.getCardNo(), ""));
		
		List<EventDegree> s = eventDegreeService.findByFilter(filter);//学位子集
		for(EventDegree e:s){
			eventDegreeService.delete(e);
		}
		List<EventEducation> u = eventEducationService.findByFilter(filter);//学历子集
		for(EventEducation e:u){
			eventEducationService.delete(e);
		}
		List<EventExperience> p = eventExperienceService.findByFilter(filter);//简历子集
		for(EventExperience e:p){
			eventExperienceService.delete(e);
		}
		List<EventFamily> f = eventFamilyService.findByFilter(filter);//家庭成员子集
		for(EventFamily e:f){
			eventFamilyService.delete(e);
		}
		List<EventPost> o = eventPostService.findByFilter(filter);//职务子集
		for(EventPost e:o){
			eventPostService.delete(e);
		}
		List<EventRewardAndPunish> a = eventRewardAndPunishService.findByFilter(filter);//奖惩子集
		for(EventRewardAndPunish e:a){
			eventRewardAndPunishService.delete(e);
		}
		delete(z);
	}
	
	/**
	 * @Title: createServant
	 * @Description: 更新人员基本信息和转入转出子集信息
	 * @param id
	 * @return: void
	 */
	public void createServant(ZhuanRenTLBIntoMgr z){
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
			
			servant.setDepartId(z.getTargetOrgan().getId());//单位id
			servant.setDepartName(z.getTargetOrgan().getName());//单位名称
			servant.setDepartCode(z.getTargetOrgan().getCode());//单位代码
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
			servant.setIsOnHold(isOnHold);//在职状态
			servant.setNowPostName(z.getPostName());//职务名称
			servant.setNowPostCode(z.getPostCode());//职务代码
			servant.setNowPostAttribute(z.getAttribute());//职务属性
			servantService.save(servant);
		}else{//如果是本区转任，更新该人员原数据的在职状态为调出，并生成新数据和转入转出、职务子集信息
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
			servant.setDepartId(z.getTargetOrgan().getId());//单位id
			servant.setDepartName(z.getTargetOrgan().getName());//单位名称
			servant.setDepartCode(z.getTargetOrgan().getCode());//单位代码
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
		post.setNowPostSign(YES);//现任职务
		CodeInfo NO = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);//否 CODE
		post.setHighestPostSign(NO);//最高职务
		post.setTenureName(z.getTargetOrgan().getName());//任职机构名称
		post.setTenureCode(z.getTargetOrgan().getCode());//任职机构代码
		post.setTakeDate(new Date());
		post.setAttribute(z.getAttribute());//职务属性
		post.setPostName(z.getPostName());//职务名称
		post.setPostCode(z.getPostCode());//职务代码
		postService.save(post);
		//职级子集
		JobLevel jobLevel = new JobLevel();
		jobLevel.setServant(servant);//人员信息
		jobLevel.setCurrentIdentification(YES);//现行职级
		jobLevel.setName(z.getJobLevelName());//职级名称
		jobLevel.setCode(z.getJobLevelCode());//职级代码
		jobLevel.setIsLowToHigh(z.getIsLowToHigh());//高职低配
		jobLevelService.save(jobLevel);
		
		IntoMgr into = new IntoMgr();//转入子集信息
		into.setServant(servant);//人员信息
		into.setEnterTheUnitChangeType(z.getEnterTheUnitChangeType());//进入本单位变动类别
		into.setEnterReason(z.getEnterReason());//进入本单位原因
		into.setEnterTheUnitDate(z.getEnterTheUnitDate());//进入本单位日期
		into.setFormerUnitName(z.getFormerUnitName());//进入本单位前工作单位名称
		into.setFormerUnitJobName(z.getFormerUnitJobName());//在原单位职务名称
		into.setFormerUnitRank(z.getFormerUnitRank());//在原单位职务级别
		into.setIntoBasicWorkTime(z.getIntoBasicWorkTime());//进入本单位时基层工作经历时间（月）
		intoMgrService.save(into);
		
		if(ZhuanRenTLBIntoMgr.AREA_THIS.equals(z.getAreaType())){//本区转任才新增一条转出子集
			OutMgr out = new OutMgr();//转出子集信息
			ZhuanRenTLBOutMgr o = zhuanRenTLBOutMgrService.get(z.getZhuanRenTLBOutMgr().getId());
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
		ManagerOutRecordEvent event = new ManagerOutRecordEvent(dto);
		EventManager.send(event);
	}
	/**
	 * @Title: createOut 
	 * @Description: 发起同类别转任，转出流程
	 * @param id
	 * @return: void
	 */
	public ZhuanRenTLBOutMgr createOut(ZhuanRenTLBIntoMgr z){
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenTLBOutMgr.class);
		d.add(Restrictions.eq("zhuanRenTLBIntoMgr", z));
		List<ZhuanRenTLBOutMgr> list = zhuanRenTLBOutMgrService.findByCriteria(d);
		if(list==null||list.size()==0){
			ZhuanRenTLBOutMgr out = new ZhuanRenTLBOutMgr();
			out.setZhuanRenTLBIntoMgr(z);//关联转入数据
			out.setServant(z.getServant());//人员信息
			out.setOutDate(z.getEnterTheUnitDate());//调出时间
			out.setGotoUnitName(z.getTargetOrgan().getName());//调往单位名称
			out.setReasonType(z.getEnterReason());//调动原因
			zhuanRenTLBOutMgrService.save(out);
			return out;
		}
		return null;
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 审批本区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ZhuanRenTLBIntoMgr temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		JobLevel tempJ = jobLevelService.getJobLevelByServantId(temp.getServant().getId());//查询当前人员的现行职级
		
		FlowRecord flow;
		if(ZhuanRenTLBIntoMgr.STATUS_ZHUANREN_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
			formationControlService.queryJudgeFormationNum(temp.getTargetOrgan().getId());
			//启动流程，锁未调入编制
			formationControlService.executeLockIntoFormationNum(temp.getTargetOrgan().getId());
			//锁未调出编制
			formationControlService.executeLockOutFormationNum(temp.getSourceOrgan().getId());

			//职级
			//校验职级
			CodeInfo tempPost = codeInfoService.get(temp.getJobLevelCode().getId());
			temp.setJobLevelCode(tempPost);
			JudgePostResult j = formationControlService.queryJudgePostNum(temp.getTargetOrgan().getId(), temp.getJobLevelCode().getCode());
			temp.setIsLowToHigh(j.isLowToHigh);//放入高职低配
			//锁职级调入数
			formationControlService.executeLockPostIntoNum(temp.getTargetOrgan().getId(), temp.getJobLevelCode().getCode(), temp.getIsLowToHigh());
			//锁职级调出数
			formationControlService.executeLockPostOutNum(temp.getSourceOrgan().getId(), tempJ.getCode().getCode(), tempJ.getIsLowToHigh());
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("本区人员同类别转入"+userOrg.getName());//流程业务名称
			flow.setBusType("ZhuanRenTLBIntoMgr_THIS");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_ZHUANREN_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ZhuanRenTLBIntoMgr.STATUS_ZHUANREN_TRIAL_4==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事审核通过之后，生成转出单位待办信息，生成转出数据
				ZhuanRenTLBOutMgr out = createOut(temp);//生成转出信息
				if(out!=null){
					temp.setZhuanRenTLBOutMgr(out);
				}
				flow = workflowService.completeFlowRecordByAppoint(flow,temp.getSourceOrgan());
			}else if(ZhuanRenTLBIntoMgr.STATUS_ZHUANREN_AGREE==temp.getStatus()&&FlowRecord.PASS.equals(r)){//转出单位同意，保存转出信息
				ZhuanRenTLBOutMgr outTemp = temp.getZhuanRenTLBOutMgr();
				ZhuanRenTLBOutMgr out = zhuanRenTLBOutMgrService.load(outTemp.getId());
				BeanUtils.copyPropertiesIgnoreNull(outTemp, out);
				DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
				zhuanRenTLBOutMgrService.update(out);
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}else if(ZhuanRenTLBIntoMgr.STATUS_ZHUANREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事主管部门打印电子介绍信之后，生成转入单位待办，更新转入转出子集信息
				createServant(temp);//更新人员基本信息
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}else{
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}
		}
		if(flow==null&&FlowRecord.PASS.equals(r)){//流程最后环节
			//流程结束，改变编制
			formationControlService.executeUnlockIntoFormationNum(temp.getTargetOrgan().getId());//1.解锁调入单位未调入编制
			formationControlService.executeUnlockOutFormationNum(temp.getSourceOrgan().getId());//2.解锁调出单位未调出编制
			formationControlService.executeIntoFormation(temp.getTargetOrgan().getId());//3.增加调入单位实际编制数
			formationControlService.executeOutFormation(temp.getSourceOrgan().getId());//4.减少调出单位实际编制数
			//职级
			formationControlService.executeUnlockPostIntoNum(temp.getTargetOrgan().getId(),temp.getJobLevelCode().getCode(),temp.getIsLowToHigh());//1.解锁职级调入数
			formationControlService.executeUnlockPostOutNum(temp.getSourceOrgan().getId(),tempJ.getCode().getCode(), tempJ.getIsLowToHigh());//2.解锁职级调出数
//			formationControlService.executeIntoPost(temp.getTargetOrgan().getId(),temp.getJobLevelCode().getCode(),temp.getIsLowToHigh());//3.增加调入单位实际职级数
			formationControlService.executeOutPost(temp.getSourceOrgan().getId(),tempJ.getCode().getCode(), tempJ.getIsLowToHigh());//4.减少调出单位实际职级数
			
			temp.setStatus(ZhuanRenTLBIntoMgr.STATUS_ZHUANREN_FINISH);
			temp.setFlowRecord(null);//修改当前业务的流程节点
			
			//发送通知
			String title = messageSource.getMessage("zhuanRenTitle", new Object[]{temp.getServant().getName()}, Locale.CHINESE);
			String content = messageSource.getMessage("zhuanRenContent", new Object[]{temp.getServant().getName()}, Locale.CHINESE);
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "")));
		}else{
			temp.setStatus(ZhuanRenTLBIntoMgr.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
	/**
	 * @Title: saveFlowOuter 
	 * @Description: 审批外区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlowOuter(ZhuanRenTLBIntoMgr temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(ZhuanRenTLBIntoMgr.STATUS_ZHUANREN_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
			formationControlService.queryJudgeFormationNum(temp.getTargetOrgan().getId());
			//启动流程，锁未调入编制
			formationControlService.executeLockIntoFormationNum(temp.getTargetOrgan().getId());

			//职级
			//校验职级
			CodeInfo tempPost = codeInfoService.get(temp.getJobLevelCode().getId());
			temp.setJobLevelCode(tempPost);
			JudgePostResult j = formationControlService.queryJudgePostNum(temp.getTargetOrgan().getId(), temp.getJobLevelCode().getCode());
			temp.setIsLowToHigh(j.isLowToHigh);//放入高职低配
			//锁职级调入数
			formationControlService.executeLockPostIntoNum(temp.getTargetOrgan().getId(), temp.getJobLevelCode().getCode(), temp.getIsLowToHigh());
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("外区人员转入"+userOrg.getName());//流程业务名称
			flow.setBusType("ZhuanRenTLBIntoMgr_OUTER");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_ZHUANREN_OUTER_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ZhuanRenTLBIntoMgr.STATUS_ZHUANREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事主管部门打印电子介绍信之后，生成转入单位待办，更新转入转出子集信息
				createServant(temp);//更新人员基本信息
			}
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(flow==null&&FlowRecord.PASS.equals(r)){//流程最后环节
			//流程结束，改变编制
			formationControlService.executeUnlockIntoFormationNum(temp.getTargetOrgan().getId());//1.解锁调入单位未调入编制
			formationControlService.executeIntoFormation(temp.getTargetOrgan().getId());//2.增加调入单位实际编制数

			//职级
			formationControlService.executeUnlockPostIntoNum(temp.getTargetOrgan().getId(),temp.getJobLevelCode().getCode(),temp.getIsLowToHigh());//1.解锁职级调入数
//			formationControlService.executeIntoPost(temp.getTargetOrgan().getId(),temp.getJobLevelCode().getCode(),temp.getIsLowToHigh());//2.增加调入单位实际职级数
			
			temp.setStatus(ZhuanRenTLBIntoMgr.STATUS_ZHUANREN_FINISH);
			temp.setFlowRecord(null);//修改当前业务的流程节点
			
			//发送通知
			String title = messageSource.getMessage("zhuanRenTitle", new Object[]{temp.getName()}, Locale.CHINESE);
			String content = messageSource.getMessage("zhuanRenContent", new Object[]{temp.getName()}, Locale.CHINESE);
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "")));
		}else{
			temp.setStatus(ZhuanRenTLBIntoMgr.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
}
