/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantReportServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 录用信息上报 服务接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月20日 下午2:29:32 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月20日 下午2:29:32 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.provider.DictCacheProvider;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.human.bo.ofc.Education;
import com.wondersgroup.human.bo.ofc.Employ;
import com.wondersgroup.human.bo.ofc.Experience;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantEduInfo;
import com.wondersgroup.human.bo.ofcflow.DraftServantRelationReport;
import com.wondersgroup.human.bo.ofcflow.DraftServantReport;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;
import com.wondersgroup.human.bo.ofcflow.ProbationTimeLength;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.service.ofc.EducationService;
import com.wondersgroup.human.service.ofc.EmployService;
import com.wondersgroup.human.service.ofc.ExperienceService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DraftServantEduInfoService;
import com.wondersgroup.human.service.ofcflow.DraftServantRelationReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.service.ofcflow.ProbationServantService;
import com.wondersgroup.human.service.ofcflow.ProbationTimeLengthService;
import com.wondersgroup.human.service.organization.FormationControlService;

/** 
 * @ClassName: DraftServantReportServiceImpl 
 * @Description: 录用信息上报 服务接口实现类
 * @author: tzy
 * @date: 2018年7月20日 下午2:29:32
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DraftServantReportServiceImpl extends GenericServiceImpl<DraftServantReport> implements DraftServantReportService{
	
	@Autowired
	private DraftServantService draftServantService;
	@Autowired
	private DraftServantRelationReportService relationReportService;
	@Autowired
	private ProbationServantService probationServantService;
	@Autowired
	private CodeInfoService codeInfoService;
	@Autowired
	private ProbationTimeLengthService probationTimeLengthService;
	@Autowired
	private FormationControlService formationControlService;
	@Autowired
	private DraftServantEduInfoService draftServantEduInfoService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private EmployService employService;
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private EducationService educationService;
	
	
	/**
	 * @Title: createReportWork 
	 * @Description: 保存上报信息，启动流程
	 * @param draftServantList	上报的录用人员id，逗号隔开形式
	 * @param type	0：暂存	1：提交
	 * @param report	上报信息，两个附件的ftp路径
	 * @return: void
	 */
	public void createReportWork(List<DraftServant> draftServantList,String type,DraftServantReport report){
		if(StringUtils.isNotBlank(report.getId())){
		    //编辑
			DraftServantReport r = load(report.getId());
			BeanUtils.copyPropertiesIgnoreNull(report, r);
			update(r);
		}else{
		    //新增
			report.setStatus("");
			save(report);//保存上报信息
			for (DraftServant ds : draftServantList) {
				//将报告和录用人员信息关联
				DraftServantRelationReport relation = new DraftServantRelationReport();
				relation.setReport(report);
				relation.setDraftServant(ds);
				relationReportService.save(relation);
				//然后将拟录用人员的状态改为审核中
				ds.setStatus(DraftServant.STATUS_SUBMIT);
				draftServantService.update(ds);
			}
		}
		
	}
	/**
	 * @Title: addProbationServant 
	 * @Description: 录用数据进入试用期
	 * @param reportId	上报信息ID
	 * @return
	 * @return: boolean
	 */
	public boolean addProbationServant(String reportId){
		try {
			List<QueryParameter> listqueryparameter=new ArrayList<QueryParameter>();
			listqueryparameter.add(new QueryParameter("removed", false));
			listqueryparameter.add(new QueryParameter("reportId", reportId));
			List<DraftServantRelationReport> rlist =relationReportService.findByHQL("from DraftServantRelationReport where removed=:removed and report.id=:reportId ",listqueryparameter);
			ProbationServant bp;
			for (DraftServantRelationReport draftServantRelationReport : rlist) {
				if(DraftServant.EMPLOY_RESULT_EMPLOY_ID.equals(draftServantRelationReport.getDraftServant().getEmploySituation().getCode())){
					bp = new ProbationServant();
					bp.setDraftServant(draftServantRelationReport.getDraftServant());
					Date now = new Date();
					bp.setProbationStartDate(now);
					Calendar cal = Calendar.getInstance();
				    cal.setTime(now);//设置起时间
				    cal.add(Calendar.YEAR, 1);//增加一年，试用期默认一年
				    bp.setProbationDate(12);
					bp.setProbationEndDate(cal.getTime());
					bp.setStatus(ProbationServant.STATUS_EMPLOY_STATE);
					bp.setIssend(ProbationServant.UN_SEND);
					probationServantService.save(bp);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<DraftServant> getDraftServantByTypeInIds(String ids, int servantType) {

		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServant where removed=:removed and publish=:publish and servantType=:servantType");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("publish", DraftServant.EMPLOY_PUBLISH);
//		listqueryparameter.add(queryParameteritem);
//		queryParameteritem=new QueryParameter("status", DraftServant.STATUS_EMPLOY_COLLECT);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("servantType", servantType);
		listqueryparameter.add(queryParameteritem);
		if(ids.contains(",")){
			hql.append(" and id in ( ");
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				if(i!=0){
					hql.append(",:id").append(i);
				}else{
					hql.append(":id").append(i);
				}
				queryParameteritem=new QueryParameter("id"+i, idsArr[i]);
				listqueryparameter.add(queryParameteritem);
			}
			hql.append(" ) ");
		}else{
			hql.append(" and id in (:ids) ");
			queryParameteritem=new QueryParameter("ids", ids);
			listqueryparameter.add(queryParameteritem);
		}
		hql.append( " order by createTime desc");
		
		List<DraftServant> list = this.findByHQL(hql.toString(), listqueryparameter, DraftServant.class);
		
		return list;
	}
	
	
	@Override
	public void updateAgreeServantReport(String id) {
		DraftServantReport draftServantReport = this.get(id);
		draftServantReport.setIsAgree(DraftServantReport.SERVANTREPORT_AGREE);
		update(draftServantReport);
		
	}
	
	/**
	 * @Title: getServantNotMemory 
	 * @Description: 数据导入人员基本信息表：DraftServant-->(Servant,Employ),ProbationServant-->Probation,DraftServantEduInfo-->Education
	 * @param id	试用期表 id
	 * @return: void
	 */
	public Servant getServantNotMemory(String id){
		DraftServant d = draftServantService.get(id);//拟录用 数据
		// 根据基础信息ID 获取学历学位子集
		DetachedCriteria criteria = DetachedCriteria.forClass(DraftServantEduInfo.class);
		criteria.add(Restrictions.eq("draftServantId", id));
		List<DraftServantEduInfo> dselist = draftServantEduInfoService.findByCriteria(criteria);//学历子集
		
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
		servant.setIsOnHold(dictableService.getCodeInfoByCode("6", DictTypeCodeContant.CODE_HUMAN_STATUS));// 试用期CODE
		if(d.getServantType()==1){//公务员
			List<CodeInfo> typeList = DictCacheProvider.getCodeInfoByCodeTypeAndParentCode(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, "1");//人员类别为公务员的CODE
			servant.setPersonType(typeList.get(0));
		}else if(d.getServantType()==2){//参公人员
			List<CodeInfo> typeList = DictCacheProvider.getCodeInfoByCodeTypeAndParentCode(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, "2");//人员类别为参照管理人员的CODE
			servant.setPersonType(typeList.get(0));
		}
		servantService.save(servant);//保存基本信息
		
		//录用子集
		Employ employ = new Employ();
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
		employ.setEmployDate(d.getProbationStartTime());//录用时间（取的试用期开始时间）
		employService.save(employ);//保存录用信息
		
		//增加简历子集信息
		Experience experience = new Experience();
		experience.setServant(servant);//人员信息
		experience.setFormerUnit(d.getDraftUnitName());//所在单位
		experience.setStartDate(new Date());//开始时间
		experienceService.save(experience);
		
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
		return servant;
	}
	/**
	 * (non Javadoc) 
	 * @Title: addProbationServantSingle
	 * @Description: 单个人员录用进入试用期
	 * @param servantId
	 * @return 
	 * @see com.wondersgroup.human.service.ofcflow.DraftServantReportService#addProbationServantSingle(java.lang.String)
	 */
	@Override
	public boolean addProbationServantSingle(String servantId) {
		DraftServant draftServant = draftServantService.get(servantId);
		//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
		String orgId = draftServant.getDraftUnit().getOrgan().getId();
		formationControlService.queryJudgeFormationNum(orgId);
		//启动流程，锁未调入编制
		formationControlService.executeLockIntoFormationNum(orgId);
		//职级
		//校验职级  试用期进来的人员默认 科员级
		formationControlService.queryJudgePostNum(orgId, FormationControl.CLERK,CommonConst.NO);
		//锁职级调入数
		formationControlService.executeLockPostIntoNum(orgId, FormationControl.CLERK, CommonConst.NO);
		try {
				
				if(DraftServant.EMPLOY_RESULT_EMPLOY_ID.equals(codeInfoService.get(draftServant.getEmploySituation().getId()).getCode())){
					//人员进入A01表，在职状态为 试用期
					Servant servant = getServantNotMemory(servantId);
					
					ProbationServant bp = new ProbationServant();
					bp.setIsLeader(CommonConst.NO);//放入是否领导，科员级都是非领导
					bp.setServant(servant);
					bp.setDraftServant(draftServant);
					bp.setProbationStartDate(draftServant.getProbationStartTime());
					Calendar cal = Calendar.getInstance();
				    cal.setTime(draftServant.getProbationStartTime());//设置起时间
				    
				    //查询试用期时长
				    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProbationTimeLength.class);
				    List<ProbationTimeLength> list = probationTimeLengthService.findByCriteria(detachedCriteria);
				    int month = 12;//默认试用期时长为12个月
					if(list!=null&&list.size()>0){
						month=list.get(0).getProbationDate();
					}
				    cal.add(Calendar.MONTH, month);//根据试用期时长计算试用期结束时间
				    bp.setProbationDate(month);
					bp.setProbationEndDate(cal.getTime());
					bp.setStatus(ProbationServant.STATUS_EMPLOY_STATE);
					bp.setIssend(ProbationServant.UN_SEND);
					bp.setCancelFlag(ProbationServant.UN_SEND);
					bp.setIsDelay(ProbationServant.UN_SEND);
					probationServantService.save(bp);
				}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
}
