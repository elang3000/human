/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月20日 下午4:48:35 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月20日 下午4:48:35 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.framework.core.bo.Page;
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
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ResignServant;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.dto.ofcflow.ResignServantQueryParam;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerOutRecordEvent;
import com.wondersgroup.human.event.record.ServantHumamKeepRecordEvent;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.ResignServantService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.ResignVO;

/** 
 * @ClassName: ResignServantServiceImpl 
 * @Description: 辞职服务接口实现类
 * @author: lihao
 * @date: 2018年6月20日 下午4:48:35
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ResignServantServiceImpl extends GenericServiceImpl<ResignServant> implements ResignServantService{
	
	//全路径
	private String ftpurl="/human/ofcflow/resign/";

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
	
	/** (non Javadoc) 
	 * @Title: saveResign
	 * @Description: 审批辞职信息
	 * @param temp 
	 * @see com.wondersgroup.human.service.ofcflow.ResignServantService#saveResign(com.wondersgroup.human.bo.ofcflow.ResignServant) 
	 */
	@Override
	public void saveResign(ResignServant temp,String opinion,String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		Servant s = servantService.get(temp.getServant().getId());
		JobLevel jobLevel = jobLevelService.getJobLevelByServantId(temp.getServant().getId());
		
		FlowRecord flow;
		if(ResignServant.RESIGN_EMPLOY_APPLY==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			formationControlService.executeLockOutFormationNum(s.getDepartId());//锁为调出编制
			formationControlService.executeLockPostOutNum(s.getDepartId(), jobLevel.getCode().getCode(), jobLevel.getIsLowToHigh());
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"辞职");//流程业务名称
			flow.setBusType("ResignServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV); //分类公务员单位
			flow = workflowService.createFlowRecord(flow, "RESIGN_EMPLOY_APPLY");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(ResignServant.RESIGN_EMPLOY_CONFIRM == temp.getStatus()&&FlowRecord.PASS.equals(r)){//辞职最后环节
			formationControlService.executeUnlockOutFormationNum(s.getDepartId());
			formationControlService.executeOutFormation(s.getDepartId());//2.减少调出单位实际编制数
			
			formationControlService.executeUnlockPostOutNum(s.getDepartId(), jobLevel.getCode().getCode(), jobLevel.getIsLowToHigh());
			formationControlService.executeOutPost(s.getDepartId(), jobLevel.getCode().getCode(), jobLevel.getIsLowToHigh());
			
			temp.setStatus(ResignServant.DEATH_EMPLOY_DONE);//通过
			temp.setFlowRecord(null);//修改当前业务的流程节点
			try {
				// 1.创建辞职批复文档,保存文档路径信息====================================================
				// 1.设置文档参数

				String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(temp.getApprovalResignDate()).toString().split("-");
				Integer year = Integer.parseInt(strNow[0]);
				Integer month = Integer.parseInt(strNow[1]);
				Integer day = Integer.parseInt(strNow[2]);
				
				Map<String, Object> czpfMap = new HashMap<String, Object>();
				czpfMap.put("nyear", DateUtils.getYear());//年号
				czpfMap.put("nnumber", temp.getNumber());//编号
				czpfMap.put("nname", s.getName());//姓名
				czpfMap.put("nunit", temp.getUnitName());//单位
				czpfMap.put("nappyear", year);
				czpfMap.put("nappmonth", month);
				czpfMap.put("nappday", day);
				//生成参公请示文档
				File czpfFile = WordUtils.createMillCertificateWord(czpfMap, "czpf.ftl");
				
				String czpfFileName=new Date().getTime()+".doc";
				//上传文档至ftp
				FtpTool.ftpUpload(ftpurl, czpfFile, czpfFileName);
				//设置文档ftp路径 
				temp.setReplyFtp(czpfFileName);
				
				// 2.创建辞职批准通知文档,保存文档路径信息====================================================
				// 1.设置文档参数
				
				Map<String, Object> czpztzMap = new HashMap<String, Object>();
				czpztzMap.put("nyear", DateUtils.getYear());//年号
				czpztzMap.put("nnumber", temp.getNumber());//编号
				czpztzMap.put("nname", s.getName());//姓名
				czpztzMap.put("npost", s.getDepartName()+s.getNowPostName());//单位+职务
				czpztzMap.put("napproval", temp.getApprovalResignDate());//批准辞职时间
				czpztzMap.put("nagent", user.getName());//经办人
				czpztzMap.put("nappyear", year);
				czpztzMap.put("nappmonth", month);
				czpztzMap.put("nappday", day);
				//生成参公请示文档
				File czpztzFile = WordUtils.createMillCertificateWord(czpztzMap, "czpztz.ftl");
				
				String czpztzFileName=new Date().getTime()+".doc";
				//上传文档至ftp
				FtpTool.ftpUpload(ftpurl, czpztzFile, czpztzFileName);
				//设置文档ftp路径 
				temp.setApprovalNoticeFtp(czpztzFileName);
				
				// 3.创建辞职通知,保存文档路径信息====================================================
				// 1.设置文档参数
				
				Map<String, Object> cztzMap = new HashMap<String, Object>();
				cztzMap.put("nnumber", temp.getNumber());//编号
				cztzMap.put("nname", s.getName());//姓名
				cztzMap.put("npost", s.getDepartName());//单位
				cztzMap.put("npostname", (s.getNowPostName()!=null)?s.getNowPostName():"");//职务
				cztzMap.put("nresigndate", temp.getResignDate());//辞职时间
				cztzMap.put("napproval", temp.getApprovalResignDate());//批准辞职时间
				cztzMap.put("nreason", temp.getReason().getName());//批准辞职时间
				//生成参公请示文档
				
				File cztzFile = WordUtils.createMillCertificateWord(cztzMap, "cztz.ftl");
				
				String cztzFileName=new Date().getTime()+".doc";
				//上传文档至ftp
				FtpTool.ftpUpload(ftpurl, cztzFile, cztzFileName);
				//设置文档ftp路径 
				temp.setNoticeFtp(cztzFileName);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			createServant(temp);
		}else{
			temp.setStatus(ResignServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}

	/**  
	 * @see com.wondersgroup.human.service.ofcflow.ResignServantService#pageList(com.wondersgroup.human.vo.ofcflow.ResignVO, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ResignVO> pageList(ResignServantQueryParam param, Integer page, Integer limit) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(ResignServant.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			s.add(Restrictions.like("s.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			s.add(Restrictions.like("s.cardNo", param.getCardNo(), MatchMode.ANYWHERE));
		}
		if (param.getReason() != null && StringUtils.isNotBlank(param.getReason().getId())) {// 辞职原因
			DetachedCriteria re = detachedcriteria.createAlias("reason", "re");
			re.add(Restrictions.eq("re.id", param.getReason().getId()));
		}
		
		if(x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)){//如果x是人社局
			detachedcriteria.add(Restrictions.eq("lastOperator", SecurityUtils.getUserId()));
		}else{
			detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		}
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("resignDate"));
		Page<ResignServant> resignPage = this.findByCriteria(detachedcriteria, page, limit);
		List<ResignVO> voList = new ArrayList<>();
		for (ResignServant rs : resignPage.getResult()) {
			ResignVO vo = new ResignVO(rs);
			voList.add(vo);
		}
		Page<ResignVO> result = new Page<>(resignPage.getStart(), resignPage.getCurrentPageSize(),
				resignPage.getTotalSize(), resignPage.getPageSize(), voList);
		return result;
	}

	
	public void createServant(ResignServant temp){
		//主表
		Servant s = servantService.get(temp.getServant().getId());
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("21", "DM200");// 死亡CODE
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
		out.setGotoUnitName(temp.getResignWhereabouts().getName());//调往单位名称(去向)
		out.setProposeType(proposeType);//提出调动类型
		out.setRemark(temp.getRemark());//调出备注
		DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
		
		outMgrService.save(out);
		
		ManagerRecordDTO dto = new ManagerRecordDTO(s.getId(),ManagerRecord.HUMAN_CZ);
		ManagerOutRecordEvent event = new ManagerOutRecordEvent(dto);
		EventManager.send(event);
		
		HumankeepRecordDTO dto2 = new HumankeepRecordDTO(s.getId(),HumanKeepRecord.KEEP_CZ);
		ServantHumamKeepRecordEvent event2 = new ServantHumamKeepRecordEvent(dto2);	
		EventManager.send(event2);
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.ResignServantService#getByServantId(java.lang.String) 
	 */
	@Override
	public ResignServant getByServantId(String servantId) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(ResignServant.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		s.add(Restrictions.eq("s.id", servantId));
		detachedcriteria.add(Restrictions.eq("removed", false));
		
		List<ResignServant> list = this.findByCriteria(detachedcriteria);
		
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
