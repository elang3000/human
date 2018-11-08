/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaoRenOutMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 调任流程 调出情况信息-服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年10月16日 下午3:09:28 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月16日 下午3:09:28 
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
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DiaoRenIntoMgr;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOutMgr;
import com.wondersgroup.human.bo.pubinst.PtOutMgr;
import com.wondersgroup.human.bo.pubinst.PtPost;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.repository.ofcflow.ZhuanRenTLBIntoMgrRepository;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutMgrService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.service.pubinst.PtOutMgrService;
import com.wondersgroup.human.service.pubinst.PtPostService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.ofcflow.DiaoRenOutMgrVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/** 
 * @ClassName: DiaoRenOutMgrServiceImpl 
 * @Description: 调任流程 调出情况信息-服务实现类
 * @author: tzy
 * @date: 2018年10月16日 下午3:09:28
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DiaoRenOutMgrServiceImpl extends GenericServiceImpl<DiaoRenOutMgr> implements DiaoRenOutMgrService{
	
	@Autowired
	private ServantService servantService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private OutMgrService outMgrService;
	@Autowired
	private PtOutMgrService ptoutMgrService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	private ZhuanRenTLBIntoMgrRepository repository;
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	@Autowired
	private PtPostService ptPostService;
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
	 * @return: Page<DiaoRenOutMgrVO>
	 */
	public Page<DiaoRenOutMgrVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<DiaoRenOutMgr> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<DiaoRenOutMgrVO> voList = new ArrayList<>();
		for(DiaoRenOutMgr s:temppage.getResult()){
			DiaoRenOutMgrVO vo = new DiaoRenOutMgrVO(s);
			voList.add(vo);
		}
		Page<DiaoRenOutMgrVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: createPersonPt
	 * @Description: 更新事业人员基本信息和调入调出子集信息
	 * @param id
	 * @return: void
	 */
	public void createPersonPt(DiaoRenOutMgr z){
		Map<String,String> params = new HashMap<>();//存储过程入参
		List<String> backList = new ArrayList<>();//返回参数名
		backList.add("SERVANTNEWID");
		backList.add("prm_AppCode");
		backList.add("prm_ErrorMsg");
		String procedureName = "";//存储过程名称
		params.put("SERVANTID", z.getServant().getId());
		procedureName = "COPY_SERVANT_TO_PT";
		ProcedureOutputs out2 = repository.executeStoreProcedure(procedureName, params,backList);//调用公务员复制为事业人员的存储过程，返回新生成事业人员id
		String servantNewId = (String)out2.getOutputParameterValue(backList.get(0));//新生成事业人员数据id
		//生成新数据
		PublicInstitution servant= publicInstitutionService.get(servantNewId);
		servant.setDepartId(z.getTargetOrgan().getId());//单位id
		servant.setDepartName(z.getTargetOrgan().getName());//单位名称
		servant.setDepartCode(z.getTargetOrgan().getCode());//单位代码
		servant.setPersonType(z.getPersonType());//人员类别
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		servant.setIsOnHold(isOnHold);//在职状态
		servant.setNowPostName(z.getPostName());//职务名称
		servant.setNowPostCode(z.getPostCode());//职务代码
		servant.setNowPostAttribute(z.getAttribute());//职务属性
		publicInstitutionService.update(servant);
		//修改原数据状态为调出
		Servant oldServant = servantService.get(z.getServant().getId());
		CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
		oldServant.setIsOnHold(outer);//调出状态
		servantService.update(oldServant);
		//职务子集
		PtPost post = new PtPost();
		post.setPublicInstitution(servant);//人员信息
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
		ptPostService.saveOrUpdate(post);
		
		PtOutMgr out = new PtOutMgr();//调出子集信息
		out.setPublicInstitution(servant);//人员基本信息
		out.setCategory(z.getCategory());//调出本单位类别
		out.setReasonType(z.getReasonType());//调动原因
		out.setOutDate(z.getOutDate());//调出本单位日期
		out.setGotoUnitName(z.getGotoUnitName());//调往单位名称
		out.setProposeType(z.getProposeType());//提出调动类型
		out.setRemark(z.getRemark());//调出备注
		ptoutMgrService.save(out);
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 审批本区调任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(DiaoRenOutMgr temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(DiaoRenOutMgr.STATUS_DIAOCHU_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
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
			String busName = "";
			if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(temp.getTargetType())){
				busName+="到事业单位";
			}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(temp.getTargetType())){
				busName+="到国企单位";
			}
			flow.setBusName(temp.getSourceOrgan().getName()+"人员调出"+busName);//流程业务名称
			flow.setBusType("DiaoRenOutMgr_THIS");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_DIAOCHU_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(DiaoRenOutMgr.STATUS_DIAOCHU_TRIAL_4==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事审批通过后。生成调入单位待办
				flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_INS);//生成事业人员待办
				flow = workflowService.completeFlowRecordByAppoint(flow,temp.getTargetOrgan());
			}else if(DiaoRenOutMgr.STATUS_DIAOCHU_AGREE==temp.getStatus()&&FlowRecord.PASS.equals(r)){//调入单位确认后，更新人员基本信息和调入调出子集信息
				createPersonPt(temp);//更新人员基本信息
				flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV);//生成公务员待办
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}else{
				flow = workflowService.completeWorkItem(flow);
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
			
			temp.setStatus(DiaoRenOutMgr.STATUS_DIAOCHU_FINISH);
			temp.setFlowRecord(null);//修改当前业务的流程节点
			
			//发送通知
			String title = messageSource.getMessage("diaoRenTitle", new Object[]{temp.getServant().getName()}, Locale.CHINESE);
			String content = messageSource.getMessage("diaoRenContent", new Object[]{temp.getServant().getName()}, Locale.CHINESE);
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "")));
		}else{
			temp.setStatus(DiaoRenOutMgr.power.get(flow.getOperationCode()));//实际有权限的操作节点
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
	public void saveFlowOuter(DiaoRenOutMgr temp){
		//锁未调出编制
		if(DiaoRenOutMgr.STATUS_DIAOCHU_STATE_OUTER==temp.getStatus()){
			formationControlService.executeLockOutFormationNum(temp.getSourceOrgan().getId());
		}
		
		if(temp.getStatus()==DiaoRenOutMgr.STATUS_DIAOCHU_CONFIRM_OUTER){
			//流程结束，改变编制
			formationControlService.executeUnlockOutFormationNum(temp.getSourceOrgan().getId());//1.解锁调出单位未调出编制
			formationControlService.executeOutFormation(temp.getSourceOrgan().getId());//2.减少调出单位实际编制数
			//修改原数据状态为调出
			Servant oldServant = servantService.get(temp.getServant().getId());
			CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
			oldServant.setIsOnHold(outer);//调出状态
			servantService.update(oldServant);
			//转出子集信息
			OutMgr out = new OutMgr();
			out.setServant(oldServant);//人员基本信息
			out.setCategory(temp.getCategory());//调出本单位类别
			out.setReasonType(temp.getReasonType());//调动原因
			out.setOutDate(temp.getOutDate());//调出本单位日期
			out.setGotoUnitName(temp.getGotoUnitName());//调往单位名称
			out.setProposeType(temp.getProposeType());//提出调动类型
			out.setRemark(temp.getRemark());//调出备注
			outMgrService.save(out);
			
			//发送通知
			String title = messageSource.getMessage("diaoRenTitle", new Object[]{temp.getServant().getName()}, Locale.CHINESE);
			String content = messageSource.getMessage("diaoRenContent", new Object[]{temp.getServant().getName()}, Locale.CHINESE);
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "")));
		}
		temp.setStatus(temp.getStatus()+1);//流程状态加1，到下一个节点
		saveOrUpdate(temp);
	}
}
