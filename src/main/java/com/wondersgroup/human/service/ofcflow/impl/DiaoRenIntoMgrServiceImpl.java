/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaoRenIntoMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 调任流程 调入情况信息-服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月30日 下午4:15:19 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月30日 下午4:15:19 
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
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.bo.ofc.IntoMgr;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DiaoRenIntoMgr;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOutMgr;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.repository.ofcflow.ZhuanRenTLBIntoMgrRepository;
import com.wondersgroup.human.service.company.NationalCompanyService;
import com.wondersgroup.human.service.ofc.IntoMgrService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DiaoRenIntoMgrService;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutMgrService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.ofcflow.DiaoRenIntoMgrVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/** 
 * @ClassName: DiaoRenIntoMgrServiceImpl 
 * @Description: 调任流程 调入情况信息-服务实现类
 * @author: tzy
 * @date: 2018年7月30日 下午4:15:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DiaoRenIntoMgrServiceImpl extends GenericServiceImpl<DiaoRenIntoMgr> implements DiaoRenIntoMgrService{
	@Autowired
	private DiaoRenOutMgrService diaoRenOutMgrService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	private ServantService servantService;

	/**
	 * 读取message.properties配置文件数据
	 */
	@Autowired
	private MessageSource messageSource;
	/**
	 * 事业单位人员信息service
	 */
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	/**
	 * 国企职工人员信息service
	 */
	@Autowired
	private NationalCompanyService nationalCompanyService;
	@Autowired
	private IntoMgrService intoMgrService;
	@Autowired
	private OutMgrService outMgrService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private ZhuanRenTLBIntoMgrRepository repository;
	@Autowired
	private PostService postService;

	@Autowired
	private FormationControlService formationControlService;
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 调换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<DiaoRenIntoMgrVO>
	 */
	public Page<DiaoRenIntoMgrVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<DiaoRenIntoMgr> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<DiaoRenIntoMgrVO> voList = new ArrayList<>();
		for(DiaoRenIntoMgr s:temppage.getResult()){
			DiaoRenIntoMgrVO vo = new DiaoRenIntoMgrVO(s);
			voList.add(vo);
		}
		Page<DiaoRenIntoMgrVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: createServant
	 * @Description: 更新人员基本信息和调入调出子集信息
	 * @param id
	 * @return: void
	 */
	public void createServant(DiaoRenIntoMgr z){
		Servant servant = new Servant();//人员基本信息
		if(DiaoRenIntoMgr.AREA_OUTER.equals(z.getAreaType())){//外区调任，需要生成人员基本信息和调入调出、职务子集信息
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
		}else{//如果是本区调任，更新该人员原数据的在职状态为调出，并生成新数据和调入调出、职务子集信息
			Map<String,String> params = new HashMap<>();//存储过程入参
			List<String> backList = new ArrayList<>();//返回参数名
			backList.add("SERVANTNEWID");
			backList.add("prm_AppCode");
			backList.add("prm_ErrorMsg");
			String procedureName = "";//存储过程名称
			if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(z.getSourceType())){//事业人员
				params.put("SERVANTID", z.getPublicInstitution().getId());
				procedureName = "COPY_PUBLICINSTITUTION";
			}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(z.getSourceType())){//国企职工
				params.put("SERVANTID", z.getNationalCompany().getId());
				procedureName = "COPY_NATIONALCOMPANY";
			}
			ProcedureOutputs out = repository.executeStoreProcedure(procedureName, params,backList);//调用复制人员存储过程，返回新生成人员id
			String servantNewId = (String)out.getOutputParameterValue(backList.get(0));//新生成人员数据id
			//生成新数据
			servant= servantService.get(servantNewId);
			servant.setDepartId(z.getTargetOrgan().getId());//单位id
			servant.setDepartName(z.getTargetOrgan().getName());//单位名称
			servant.setDepartCode(z.getTargetOrgan().getCode());//单位代码
			servant.setPersonType(z.getPersonType());//人员类别
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
			servant.setIsOnHold(isOnHold);//在职状态
			servant.setNowPostName(z.getPostName());//职务名称
			servant.setNowPostCode(z.getPostCode());//职务代码
			servant.setNowPostAttribute(z.getAttribute());//职务属性
			servantService.update(servant);
			//修改原数据状态为调出
			if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(z.getSourceType())){//事业人员
				PublicInstitution oldServant = publicInstitutionService.get(z.getPublicInstitution().getId());
				CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
				oldServant.setIsOnHold(outer);//调出状态
				publicInstitutionService.update(oldServant);
			}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(z.getSourceType())){//国企职工
				NationalCompany oldServant = nationalCompanyService.get(z.getNationalCompany().getId());
				CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
				oldServant.setIsOnHold(outer);//调出状态
				nationalCompanyService.update(oldServant);
			}
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
		post.setIsLowToHigh(z.getIsLowToHigh());//高职低配
		postService.saveOrUpdate(post);
		
		IntoMgr into = new IntoMgr();//调入子集信息
		into.setServant(servant);//人员信息
		into.setEnterTheUnitChangeType(z.getEnterTheUnitChangeType());//进入本单位变动类别
		into.setEnterReason(z.getEnterReason());//进入本单位原因
		into.setEnterTheUnitDate(z.getEnterTheUnitDate());//进入本单位日期
		into.setFormerUnitName(z.getFormerUnitName());//进入本单位前工作单位名称
		into.setFormerUnitJobName(z.getFormerUnitJobName());//在原单位职务名称
		into.setFormerUnitRank(z.getFormerUnitRank());//在原单位职务级别
		into.setIntoBasicWorkTime(z.getIntoBasicWorkTime());//进入本单位时基层工作经历时间（月）
		intoMgrService.save(into);
		
		if(DiaoRenIntoMgr.AREA_THIS.equals(z.getAreaType())){//本区调任才新增一条调出子集
			OutMgr out = new OutMgr();//调出子集信息
			DiaoRenOutMgr o = diaoRenOutMgrService.get(z.getDiaoRenOutMgr().getId());
			out.setServant(servant);//人员基本信息
			out.setCategory(o.getCategory());//调出本单位类别
			out.setReasonType(o.getReasonType());//调动原因
			out.setOutDate(o.getOutDate());//调出本单位日期
			out.setGotoUnitName(o.getGotoUnitName());//调往单位名称
			out.setProposeType(o.getProposeType());//提出调动类型
			out.setRemark(o.getRemark());//调出备注
			outMgrService.save(out);
		}
	}
	/**
	 * @Title: createOut 
	 * @Description: 发起调出流程
	 * @param id
	 * @return: void
	 */
	public DiaoRenOutMgr createOut(DiaoRenIntoMgr z){
		DetachedCriteria d = DetachedCriteria.forClass(DiaoRenOutMgr.class);
		d.add(Restrictions.eq("diaoRenIntoMgr", z));
		List<DiaoRenOutMgr> list = diaoRenOutMgrService.findByCriteria(d);
		if(list==null||list.size()==0){
			DiaoRenOutMgr out = new DiaoRenOutMgr();
			out.setDiaoRenIntoMgr(z);//关联调入数据
			out.setServant(z.getServant());//人员信息
			out.setOutDate(z.getEnterTheUnitDate());//调出时间
			out.setGotoUnitName(z.getTargetOrgan().getName());//调往单位名称
			diaoRenOutMgrService.save(out);
			return out;
		}
		return null;
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 审批本区调任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(DiaoRenIntoMgr temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(DiaoRenIntoMgr.STATUS_DIAOREN_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			//编控
			//校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
			formationControlService.queryJudgeFormationNum(temp.getTargetOrgan().getId());
			//启动流程，锁未调入编制
			formationControlService.executeLockIntoFormationNum(temp.getTargetOrgan().getId());
			//锁未调出编制
			formationControlService.executeLockOutFormationNum(temp.getSourceOrgan().getId());
			
			//职务
			//校验职务
			JudgePostResult j = formationControlService.queryJudgePostNum(temp.getTargetOrgan().getId(), temp.getPostCode().getCode());
			temp.setIsLowToHigh(j.isLowToHigh);//放入高职低配
			//锁职务调入数
			formationControlService.executeLockPostIntoNum(temp.getTargetOrgan().getId(), temp.getPostCode().getCode(), temp.getIsLowToHigh());
			//锁职务调出数
			formationControlService.executeLockPostOutNum(temp.getSourceOrgan().getId(), temp.getPostCode().getCode(), temp.getIsLowToHigh());
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			String busName = "本区";
			if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(temp.getSourceType())){
				busName+="事业";
			}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(temp.getSourceType())){
				busName+="国企职工";
			}
			flow.setBusName(busName+"人员调入"+userOrg.getName());//流程业务名称
			flow.setBusType("DiaoRenIntoMgr_THIS");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_DIAOREN_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(DiaoRenIntoMgr.STATUS_DIAOREN_TRIAL_4==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事审核通过之后，生成调出单位待办信息，生成调出数据
				DiaoRenOutMgr out = createOut(temp);//生成调出信息
				if(out!=null){
					temp.setDiaoRenOutMgr(out);
				}
				flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_INS);//生成事业人员待办
				flow = workflowService.completeFlowRecordByAppoint(flow,temp.getSourceOrgan());
			}else if(DiaoRenIntoMgr.STATUS_DIAOREN_AGREE==temp.getStatus()&&FlowRecord.PASS.equals(r)){//调出单位同意，保存调出信息
				DiaoRenOutMgr outTemp = temp.getDiaoRenOutMgr();
				DiaoRenOutMgr out = diaoRenOutMgrService.load(outTemp.getId());
				BeanUtils.copyPropertiesIgnoreNull(outTemp, out);
				DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
				diaoRenOutMgrService.update(out);
				flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV);//生成公务员待办
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}else if(DiaoRenIntoMgr.STATUS_DIAOREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事主管部门打印电子介绍信之后，生成调入单位待办，更新调入调出子集信息
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
			//职务
			formationControlService.executeUnlockPostIntoNum(temp.getTargetOrgan().getId(),temp.getPostCode().getCode(),temp.getIsLowToHigh());//1.解锁职务调入数
			formationControlService.executeUnlockPostOutNum(temp.getSourceOrgan().getId(),temp.getPostCode().getCode(),temp.getIsLowToHigh());//2.解锁职务调出数
			formationControlService.executeIntoPost(temp.getTargetOrgan().getId(),temp.getPostCode().getCode(),temp.getIsLowToHigh());//3.增加调入单位实际职务数
			formationControlService.executeOutPost(temp.getSourceOrgan().getId(),temp.getPostCode().getCode(),temp.getIsLowToHigh());//4.减少调出单位实际职务数
			
			temp.setStatus(DiaoRenIntoMgr.STATUS_DIAOREN_FINISH);
			temp.setFlowRecord(null);//修改当前业务的流程节点
			
			//发送通知
			String title = messageSource.getMessage("diaoRenTitle", new Object[]{temp.getServant().getName()}, Locale.CHINESE);
			String content = messageSource.getMessage("diaoRenContent", new Object[]{temp.getServant().getName()}, Locale.CHINESE);
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "")));
		}else{
			temp.setStatus(DiaoRenIntoMgr.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
	/**
	 * @Title: saveFlowOuter 
	 * @Description: 审批外区调任
	 * @param temp
	 * @return: void
	 */
	public void saveFlowOuter(DiaoRenIntoMgr temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(DiaoRenIntoMgr.STATUS_DIAOREN_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
			formationControlService.queryJudgeFormationNum(temp.getTargetOrgan().getId());
			//启动流程，锁未调入编制
			formationControlService.executeLockIntoFormationNum(temp.getTargetOrgan().getId());
			
			//职务
			//校验职务
			JudgePostResult j = formationControlService.queryJudgePostNum(temp.getTargetOrgan().getId(), temp.getPostCode().getCode());
			temp.setIsLowToHigh(j.isLowToHigh);//放入高职低配
			//锁职务调入数
			formationControlService.executeLockPostIntoNum(temp.getTargetOrgan().getId(), temp.getPostCode().getCode(), temp.getIsLowToHigh());
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("外区人员调入"+userOrg.getName());//流程业务名称
			flow.setBusType("DiaoRenIntoMgr_OUTER");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_DIAOREN_OUTER_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(DiaoRenIntoMgr.STATUS_DIAOREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事主管部门打印电子介绍信之后，生成调入单位待办，更新调入调出子集信息
				createServant(temp);//更新人员基本信息
			}
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(flow==null&&FlowRecord.PASS.equals(r)){//流程最后环节
			//流程结束，改变编制
			formationControlService.executeUnlockIntoFormationNum(temp.getTargetOrgan().getId());//1.解锁调入单位未调入编制
			formationControlService.executeIntoFormation(temp.getTargetOrgan().getId());//2.增加调入单位实际编制数
			
			//职务
			formationControlService.executeUnlockPostIntoNum(temp.getTargetOrgan().getId(),temp.getPostCode().getCode(),temp.getIsLowToHigh());//1.解锁职务调入数
			formationControlService.executeIntoPost(temp.getTargetOrgan().getId(),temp.getPostCode().getCode(),temp.getIsLowToHigh());//2.增加调入单位实际职务数
			
			temp.setStatus(DiaoRenIntoMgr.STATUS_DIAOREN_FINISH);
			temp.setFlowRecord(null);//修改当前业务的流程节点
			
			//发送通知
			String title = messageSource.getMessage("diaoRenTitle", new Object[]{temp.getName()}, Locale.CHINESE);
			String content = messageSource.getMessage("diaoRenContent", new Object[]{temp.getName()}, Locale.CHINESE);
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "")));
		}else{
			temp.setStatus(DiaoRenIntoMgr.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
}
