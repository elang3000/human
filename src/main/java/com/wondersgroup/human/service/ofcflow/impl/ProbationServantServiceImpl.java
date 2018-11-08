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

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Education;
import com.wondersgroup.human.bo.ofc.Employ;
import com.wondersgroup.human.bo.ofc.Probation;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantEduInfo;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;
import com.wondersgroup.human.repository.ofcflow.ProbationServantRepository;
import com.wondersgroup.human.service.ofc.EducationService;
import com.wondersgroup.human.service.ofc.EmployService;
import com.wondersgroup.human.service.ofc.ProbationService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DraftServantEduInfoService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.service.ofcflow.ProbationServantService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.ofcflow.ProbationServantVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
	private EmployService employService;
	
	@Autowired
	private ProbationService probationService;
	
	@Autowired
	private EducationService educationService;
	
	@Autowired
	private DraftServantService draftServantService;
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private DraftServantEduInfoService draftServantEduInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private DictableService dictableService;

	@Autowired
	private ProbationServantRepository probationServantRepository;

	@Autowired
	private FormationControlService formationControlService;
	
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
	 * @Title: importServant 
	 * @Description: 数据导入人员基本信息表：DraftServant-->(Servant,Employ),ProbationServant-->Probation,DraftServantEduInfo-->Education
	 * @param id	试用期表 id
	 * @return: void
	 */
	public void getServantNotMemory(String id){
		ProbationServant p = get(id);
		DraftServant d = draftServantService.load(p.getDraftServant().getId());//拟录用 数据
		// 根据基础信息ID 获取学历学位子集
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate params = new Predicate("draftServantId", Operator.EQ, d.getId(), "");
		filter.add(params);
		List<DraftServantEduInfo> dselist = draftServantEduInfoService.findByFilter(filter);//学历子集
		
		Servant servant = new Servant();//人员基本信息
		servant.setName(d.getName());//姓名
		servant.setCardNo(d.getCardNo());//身份证号
		servant.setSex(d.getSex());//性别
		servant.setBirthDate(d.getBirthDate());//出生日期
		servant.setBirthPlaceName(d.getBirthPlaceName());//出生地名称
		servant.setBirthPlace(d.getBirthPlace());//出生地代码
		servant.setNativePlaceName(d.getNativePlaceName());//籍贯名称
		servant.setNativePlace(d.getNativePlace());//籍贯代码
		servant.setNation(d.getNation());//民族
		servant.setPolitics(d.getPolitics());//政治面貌
		servant.setAttendDate(d.getAttendDate());//参加工作日期
		servant.setDepartName(d.getDraftUnitName());//拟录用单位名称
		servant.setDepartId(d.getDraftUnit().getOrgan().getId());//拟录用单位id
		servant.setIsOnHold(dictableService.getCodeInfoByCode("1", "DM200"));// 在职CODE
		servantService.save(servant);//保存基本信息
		
		Employ employ = new Employ();//录用信息
		employ.setServant(servant);//关联基本信息
		employ.setIsRetiredSoldier(d.getIsRetiredSoldier());//是否退役士兵
		employ.setIsRetiredCollegeStudentSoldier(d.getIsRetiredCollegeStudentSoldier());//是否退役大学生士兵
		employ.setIsdisabled(d.getIsdisabled());//是否残疾人
		employ.setIsStudyAbroad(d.getIsStudyAbroad());//是否有海外留学经历
		employ.setStudyAbroadTime(d.getStudyAbroadTime());//留学年限
		employ.setIsWorkAbroad(d.getIsWorkAbroad());//是否有海外工作经历
		employ.setWorkAbroadTime(d.getWorkAbroadTime());//海外工作年限
		employ.setSource(d.getSource());//来源
		employ.setAdmissionTicket(d.getTicketId());//录用考试准考证号
		employ.setAptitudeTestScore(d.getAptitudeTestScore());//专业能力测试成绩
		employ.setPublicSubjectTestScore(d.getPublicSubjectTestScore());//公共科目笔试成绩
		employ.setWrittenExamTestScore(d.getWrittenExamTestScore());//笔试（行政职业能力测试）成绩
		employ.setExplainingScore(d.getExplainingScore());//笔试（申论）成绩
		employ.setProfessionalSubjectScore(d.getProfessionalSubjectScore());//笔试（专业科目）成绩
		employ.setInterviewScore(d.getInterviewScore());//面试成绩
		employ.setOtherSubjectScore(d.getOtherSubjectScore());//其他科目成绩
		employ.setIdentification(d.getEmployResult());//录用标识
		employ.setEmployDate(p.getProbationStartDate());//录用时间（取的试用期开始时间）
		employService.save(employ);//保存录用信息
		
		Probation probation = new Probation();//试用情况
		probation.setServant(servant);//关联基本信息
		probation.setStartDate(p.getProbationStartDate());//试用开始时间
		probation.setEndDate(p.getProbationEndDate());//试用结束时间
		probation.setBecomeDate(p.getFormalDate());//入职转正日期
		probation.setBecomeNo(p.getFormalNumber());//入职转正批准文号
		probation.setUnitName(d.getDraftUnitName());//试用单位
		probation.setConclusion(p.getCheckResult());//试用期满考核结论
		probationService.save(probation);//保存试用信息
		
		if(dselist!=null&&dselist.size()>0){
			for(DraftServantEduInfo e : dselist){//遍历学历信息保存
				Education education = new Education();
				education.setServant(servant);//关联基本信息
				education.setEducationNo(e.getRegisterId());//编号
				education.setCode(e.getEducationCode());//学历代码
				education.setName(e.getEducationName());//学历名称
				if(e.getEductionalSystem()!=null){
					education.setEductionalSystem(String.valueOf(e.getEductionalSystem()));//学制
				}
				education.setGraduateDate(e.getGraduateDate());//毕<肄>业日期
				education.setShoolName(e.getSchoolName());//学校名称
				education.setProfessionCode(e.getProfession());//所学专业
				education.setProfessionName(e.getProfessionName());//所学专业名称
				educationService.save(education);//保存学历信息
			}
		}
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
			flow.setBusName(userOrg.getName()+"试用期信息");//流程业务名称
			flow.setBusType("ProbationServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_EMPLOY_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(ProbationServant.STATUS_EMPLOY_TRIAL_4 == temp.getStatus()&&FlowRecord.PASS.equals(r)){//试用期审核最后环节
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
			    temp.setDelayReasonDone(temp.getDelayReason());//设置延期理由，作为流程结束显示
			    temp.setIsDelay(ProbationServant.SEND_ED);//设置是延期流程结束
			    
			    temp.setStatus(ProbationServant.STATUS_EMPLOY_STATE);//延长试用期审批通过，试用期结束时 可再次发起试用期流程
			    //清除延长试用期信息
			    temp.setProbationStatus(null);
			    temp.setDelayDate(null);
			    temp.setProbationStatus(null);
			    temp.setDelayReason(null);
			    
			    ProbationServant probation = new ProbationServant();
			    BeanUtils.copyPropertiesIgnoreNull(temp,probation);
			    probation.setId(null);
			    save(probation);//新增一条相同数据，使其是新流程，删除原数据
			    delete(temp);
			}else{
				//流程结束，改变编制
				formationControlService.executeUnlockIntoFormationNum(temp.getDraftServant().getDraftUnit().getOrgan().getId());//1.解锁调入单位未调入编制
				formationControlService.executeIntoFormation(temp.getDraftServant().getDraftUnit().getOrgan().getId());//2.增加调入单位实际编制数
				
				temp.setStatus(ProbationServant.STATUS_EMPLOY_TRIAL_PASS);//试用期审批通过
				getServantNotMemory(temp.getId());//人员信息进入正式库
			}
			temp.setFlowRecord(null);//该业务没有待办流程节点 
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
			flow.setBusName(userOrg.getName()+"试用期取消录用");//流程业务名称
			flow.setBusType("CancelProbationServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_EMPLOY_STATE_2");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(ProbationServant.STATUS_EMPLOY_TRIAL_CONFIRM == temp.getStatus()&&FlowRecord.PASS.equals(r)){//试用期审核最后环节
			temp.setStatus(ProbationServant.STATUS_EMPLOY_TRIAL_CONFIRM_DONE);//取消录用，区人事主管部门已备案确认
			temp.setCancelFlag(ProbationServant.SEND_ED);//取消录用标识
			temp.setFlowRecord(null);//该业务没有待办流程节点 
			
			//流程结束，改变编制
			formationControlService.executeUnlockIntoFormationNum(temp.getDraftServant().getDraftUnit().getOrgan().getId());//1.解锁调入单位未调入编制
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
