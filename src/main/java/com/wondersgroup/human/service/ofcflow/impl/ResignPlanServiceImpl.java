/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月20日 上午11:07:07 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月20日 上午11:07:07 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.config.ReadProperties;
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
import com.wondersgroup.framework.util.DateUtils;
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
import com.wondersgroup.human.bo.ofcflow.Material;
import com.wondersgroup.human.bo.ofcflow.ResignPeople;
import com.wondersgroup.human.bo.ofcflow.ResignPlan;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOut;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutBatch;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerOutRecordEvent;
import com.wondersgroup.human.event.record.ServantHumamKeepRecordEvent;
import com.wondersgroup.human.service.ofc.ExperienceService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.MaterialService;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.service.ofcflow.ResignPeopleService;
import com.wondersgroup.human.service.ofcflow.ResignPlanService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.ResignPlanVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/** 
 * @ClassName: ResignPlanServiceImpl 
 * @Description: 辞职批次服务接口实现类
 * @author: lihao
 * @date: 2018年12月20日 上午11:07:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ResignPlanServiceImpl extends GenericServiceImpl<ResignPlan> implements ResignPlanService{

	@Autowired
	private ServantService servantService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResignPeopleService resignPeopleService;
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
	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;
	@Autowired
	private MaterialService materialService;
	
	/**  
	 * @see com.wondersgroup.human.service.ofcflow.ResignPlanService#savePeople(com.wondersgroup.human.bo.ofcflow.ResignPlan, java.lang.String) 
	 */
	@Override
	public void savePeople(ResignPlan resignPlan, String servantIds) {
		String[] idArr = servantIds.split(",");
		if(StringUtils.isBlank(resignPlan.getId())){//如果没有保存批次信息，先保存
			resignPlan.setStatus(ResignPlan.RESIGN_EMPLOY_APPLYB);//流程状态，待提交
			resignPlan.setResignNumber(idArr.length);//获取人数
			save(resignPlan);
		}else{
			resignPlan = get(resignPlan.getId());
		}
		ResignPeople rp = null;
		for(String i : idArr){
			Servant servant = servantService.get(i);
			rp = new ResignPeople();
			DictUtils.operationCodeInfo(rp);//将CodeInfo中id为空的属性 设置为null
			rp.setServant(servant);
			rp.setPlan(resignPlan);
			resignPeopleService.save(rp);
		}
		
		int num = resignPeopleService.getResignPeopleCountByPlanId(resignPlan.getId()).size();
		resignPlan.setResignNumber(num);
		update(resignPlan);
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.ResignPlanService#resignList(com.wondersgroup.human.bo.ofcflow.ResignPlan, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ResignPlanVO> resignList(String resignName,Integer numLow,Integer numHigh, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(ResignPlan.class);
		
		if (StringUtils.isNotBlank(resignName)) {// 批次名字
			detachedcriteria.add(Restrictions.like("resignName", resignName, MatchMode.ANYWHERE));
		}
		if (numLow!=null) {// 批次人数最少
			detachedcriteria.add(Restrictions.ge("resignNumber", numLow));
		}
		
		if (numHigh!=null) {// 批次人数最多
			detachedcriteria.add(Restrictions.le("resignNumber", numHigh));
		}
		
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		Page<ResignPlan> resignPage = this.findByCriteria(detachedcriteria, page, limit);
		List<ResignPlanVO> voList = new ArrayList<>();
		for (ResignPlan rs : resignPage.getResult()) {
			ResignPlanVO vo = new ResignPlanVO(rs);
			voList.add(vo);
		}
		Page<ResignPlanVO> result = new Page<>(resignPage.getStart(), resignPage.getCurrentPageSize(),
				resignPage.getTotalSize(), resignPage.getPageSize(), voList);
		return result;
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.ResignPlanService#saveFlow(com.wondersgroup.human.bo.ofcflow.ResignPlan, java.lang.String, java.lang.String) 
	 */
	@Override
	public void saveFlow(ResignPlan temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		DetachedCriteria criteria = DetachedCriteria.forClass(ResignPeople.class);//查询该批次内人员信息
		criteria.add(Restrictions.eq("plan.id", temp.getId()));
		criteria.add(Restrictions.eq("removed", false));
		List<ResignPeople> list = resignPeopleService.findByCriteria(criteria);
		
		if(ResignPlan.RESIGN_EMPLOY_APPLYB==temp.getStatus()){//提交环节，1.校验数据是否填写，2.锁编
			//1.校验数据是否填写
			if(list.size()==0){
				throw new BusinessException("请添加人员信息！");
			}
			for(ResignPeople rp : list){
				if(rp.getReason()==null){
					throw new BusinessException("请先编辑所有人员的辞职信息！");
				}
			}
			//2.锁编
			executeLockOutFormationAndPost(temp);
		}
		
		FlowRecord flow;
		if(ResignPlan.RESIGN_EMPLOY_APPLYB==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("辞职");//流程业务名称
			flow.setBusType("ResignServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV); //分类公务员单位
			flow = workflowService.createFlowRecord(flow, "RESIGN_EMPLOY_APPLYB");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ResignPlan.RESIGN_EMPLOY_CONFIRMB==temp.getStatus()){
				createWord(list,temp);//创建文档
			}
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(ResignPlan.RESIGN_EMPLOY_PRINTB == temp.getStatus()&&FlowRecord.PASS.equals(r)){//辞职打印确认按钮
			temp.setStatus(ResignPlan.RESIGN_EMPLOY_DONEB);//通过
			temp.setFlowRecord(null);//修改当前业务的流程节点
			updateServant(list);//更新人员信息
			executeFinish(temp);//放编
		}else{
			temp.setStatus(ResignPlan.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}

	public void updateServant(List<ResignPeople> list){
		Servant s = null;
		for(ResignPeople rp:list){
			s = servantService.get(rp.getServant().getId());
			//修改人员isOnHold状态为21：辞职人员CODE
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("21", "DM200");// 辞职人员CODE
			s.setIsOnHold(isOnHold);
			servantService.update(s);
			
			//转出子集表
			CodeInfo category = dictableService.getCodeInfoByCode("92", "GBT_12405_2008");// 调出本单位类别-辞职
			CodeInfo reasonType = dictableService.getCodeInfoByCode("9", "DM015");// 调动原因-其他
			CodeInfo proposeType = dictableService.getCodeInfoByCode("9", "DM039");// 提出调动类型-其他
			
			OutMgr out = new OutMgr();
			out.setServant(s);//人员基本信息
			out.setCategory(category);//调出本单位类别
			out.setReasonType(reasonType);//调动原因
			out.setOutDate(new Date());//调出本单位日期
			out.setGotoUnitName(rp.getResignWhereabouts().getName());//调往单位名称(去向)
			out.setProposeType(proposeType);//提出调动类型
			out.setRemark(rp.getRemark());//调出备注
			DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
			outMgrService.save(out);
			
			//更新简历
			Experience e = experienceService.getLatestExperienceByServantId(s.getId());
			e.setEndDate(new Date());
			experienceService.update(e);
			
			//进出管理 出
			ManagerRecordDTO dto = new ManagerRecordDTO(s.getId(),ManagerRecord.HUMAN_CZ);
			ManagerOutRecordEvent event = new ManagerOutRecordEvent(dto);
			EventManager.send(event);
			
			//备案管理 辞职备案
			HumankeepRecordDTO dto2 = new HumankeepRecordDTO(s.getId(),HumanKeepRecord.KEEP_CZ);
			ServantHumamKeepRecordEvent event2 = new ServantHumamKeepRecordEvent(dto2);	
			EventManager.send(event2);
		}
	}
	
	
	/**
	 * @Title: executeLockOutFormationAndPost 
	 * @Description: 锁未调出编制
	 * @param temp
	 * @return: List<JudgePostResult> 原单位职级信息
	 */
	public void executeLockOutFormationAndPost(ResignPlan temp){
		List<JudgePostResult> list = new ArrayList<>();
		DetachedCriteria d = DetachedCriteria.forClass(ResignPeople.class);
		d.add(Restrictions.eq("plan.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ResignPeople> rpList = resignPeopleService.findByCriteria(d);
		
		JobLevel tempJ = null;
		for(ResignPeople rp : rpList){
			tempJ = jobLevelService.getJobLevelByServantId(rp.getServant().getId());//查询当前人员的现行职级
			if(StringUtils.isBlank(tempJ.getRealJobLevelCode())){
				throw new BusinessException("占编职级信息异常，请联系管理员！");
			}
			JudgePostResult j = new JudgePostResult(tempJ.getRealJobLevelCode(), tempJ.getRealLeader(),1);
			list.add(j);
		}
		formationControlService.executeLockOutFormationAndPost(temp.getOrgan().getId(), list);//锁未调出
	}
	
	/**
	 * @Title: executeFinish 
	 * @Description: 流程结束，真实占编，解锁未调出编制
	 * @param temp
	 * @return: void
	 */
	public void executeFinish(ResignPlan temp){
		DetachedCriteria d = DetachedCriteria.forClass(ResignPeople.class);
		d.add(Restrictions.eq("plan.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ResignPeople> rpList = resignPeopleService.findByCriteria(d);
		JobLevel tempJ = null;
		for(ResignPeople rp : rpList){
			tempJ = jobLevelService.getJobLevelByServantId(rp.getServant().getId());//查询当前人员的现行职级
			
			formationControlService.executeUnlockOutFormationNum(temp.getOrgan().getId());//1.解锁调出单位未调出编制
			formationControlService.executeOutFormation(temp.getOrgan().getId());//2.减少调出单位实际编制数
			
			formationControlService.executeUnlockPostOutNum(temp.getOrgan().getId(),tempJ.getRealJobLevelCode(), tempJ.getRealLeader());//1.解锁职级调出数
			formationControlService.executeOutPost(temp.getOrgan().getId(),tempJ.getRealJobLevelCode(), tempJ.getRealLeader());//2.减少调出单位实际职级数
		}
	}
	
	/**
	 * @Title: createWord 
	 * @Description: 创建文档
	 * @param temp
	 * @return: void
	 */
	public void createWord(List<ResignPeople> list,ResignPlan temp){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		String folder = ReadProperties.getInstance().FTP_DIR_NAME_MATERIAL;//ftp文件路径
		Servant s = null;
		String fileName = "";
		String fileFtpName = "";
		Material m = null;
		
		try {
			// 1.创建辞职批复文档,保存文档路径信息=============================================start
			// 1.设置文档参数
			for(ResignPeople rp:list){
				rp.setResignDate(temp.getResignDate());
				resignPeopleService.update(rp);
				
				s = servantService.get(rp.getServant().getId());
				Map<String,String> map = ofcFlowNumberService.executeNumber("ResignServant",rp.getId());
				String resignYear = map.get("year");
				String resignNum = map.get("number");

				String year = DateUtils.getYear();
				String month = DateUtils.getMonth();
				String day = DateUtils.getDay();
				
				Map<String, Object> czpfMap = new HashMap<String, Object>();
				czpfMap.put("nyear", resignYear);//年号
				czpfMap.put("nnumber", resignNum);//编号
				czpfMap.put("nname", s.getName());//姓名
				czpfMap.put("nunit", s.getDepartName());//单位
				czpfMap.put("nappyear", year);
				czpfMap.put("nappmonth", month);
				czpfMap.put("nappday", day);
				//生成参公请示文档
				File czpfFile = WordUtils.createMillCertificateWord(czpfMap, "czpf.ftl");
				
				String czpfFileName=new Date().getTime()+".doc";
				//上传文档至ftp
				FtpTool.ftpUpload(folder, czpfFile, czpfFileName);
				//设置文档ftp路径 
				rp.setReplyFtp(czpfFileName);
				//=============================================================================end
				
				// 2.创建辞职批准通知文档,保存文档路径信息=======================================start
				// 1.设置文档参数
				
				Map<String, Object> czpztzMap = new HashMap<String, Object>();
				czpztzMap.put("nyear", resignYear);//年号
				czpztzMap.put("nnumber", resignNum);//编号
				czpztzMap.put("nname", s.getName());//姓名
				czpztzMap.put("npost", s.getDepartName()+s.getNowPostName());//单位+职务
				czpztzMap.put("napproval", rp.getResignDate());//批准辞职时间
				czpztzMap.put("nagent", user.getName());//经办人
				czpztzMap.put("nday", new Date());//日期
				czpztzMap.put("nappyear", year);
				czpztzMap.put("nappmonth", month);
				czpztzMap.put("nappday", day);
				//生成参公请示文档
				File czpztzFile = WordUtils.createMillCertificateWord(czpztzMap, "czpztz.ftl");
				
				String czpztzFileName=new Date().getTime()+".doc";
				//上传文档至ftp
				FtpTool.ftpUpload(folder, czpztzFile, czpztzFileName);
				//设置文档ftp路径 
				rp.setApprovalNoticeFtp(czpztzFileName);
				//===============================================================================end
				
				// 3.创建辞职通知,保存文档路径信息================================================start
				// 1.设置文档参数
				
				Map<String, Object> cztzMap = new HashMap<String, Object>();
				cztzMap.put("nnumber", resignNum);//编号
				cztzMap.put("nname", s.getName());//姓名
				cztzMap.put("npost", s.getDepartName());//单位
				cztzMap.put("npostname", (s.getNowPostName()!=null)?s.getNowPostName():"");//职务
				cztzMap.put("nresigndate", rp.getResignDate());//辞职时间
				cztzMap.put("napproval", new Date());//批准辞职时间
				cztzMap.put("nreason", rp.getReason().getName());//批准辞职原因
				//生成参公请示文档
				
				File cztzFile = WordUtils.createMillCertificateWord(cztzMap, "cztz.ftl");
				
				String cztzFileName=new Date().getTime()+".doc";
				//上传文档至ftp
				FtpTool.ftpUpload(folder, cztzFile, cztzFileName);
				//设置文档ftp路径 
				rp.setNoticeFtp(cztzFileName);
				//==============================================================================end
				
				// 4.生成材料放入Material表中====================================================start
				fileName = s.getName()+"辞职批复.doc:"+s.getName()+"辞职批准通知.doc:"+s.getName()+"辞职通知.doc";
				fileFtpName = czpfFileName +":"+ czpztzFileName +":"+ cztzFileName ;
				m = new Material();//材料信息 一定要生成
				m.setBusId(rp.getId());
				m.setBusType("ResignServant");
				m.setBusName("辞职");
				m.setFlowNumber(resignNum);
				m.setName(fileName);
				m.setFtpFilefolder(folder);
				m.setFtpFileName(fileFtpName);
				m.setFolderName(s.getName());
				materialService.save(m);
				//=============================================================================end
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
