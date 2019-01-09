/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DraftServant.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 拟录用人员信息表
 * 创建人: GP
 * 创建时间: 2018年6月26日 上午9:47:22
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2018年6月26日 上午9:47:22
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.organization.OrgInfo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: DraftServant
 * @Description: 拟录用人员信息表
 * @author: GP
 * @date: 2018年6月26日 上午9:47:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DRAFT_SERVANT")
public class DraftServant extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description:
	 */
	private static final long serialVersionUID = 5341231399679526694L;
	
	// 录用情况标识-拟录用  INFO CODE:0
	public static final String EMPLOY_TYPE_DRAFT = "0";
	// 录用情况标识-录用  INFO CODE:1
	public static final String EMPLOY_RESULT_EMPLOY_ID = "1";
	// 录用情况标识  TYPE CODE:DraftServantEmployStatus
	public static final String EMPLOY_TYPE = "DraftServantEmployStatus";
	
	// 该录用是否发布，用于录用申请显示
	public static final int EMPLOY_PUBLISH = 1;
	
	public static final int EMPLOY_NO_PUBLISH = 0;
	
	// status
	// 已汇总
	public static final int STATUS_EMPLOY_COLLECT = 1;
	
	// 未汇总
	public static final int STATUS_EMPLOY_NO_COLLECT = 0;
	
	//是否确认电子介绍信-是
	public static final int ISELECLETTER_YES = 1;
	//是否确认电子介绍信-否
	public static final int ISELECLETTER_NO = 0;
	
	
	
	// 已上报审核
	public static final int STATUS_SUBMIT = 2;
	
	// 审核通过
	public static final int STATUS_PASS = 3;
	
	//是否上海人 -是
	public static final String yesStrCH="是";
	//是否上海人 -否
	public static final String noStrCH="否";
	
	/**
	 * @fieldName: importRecordId
	 * @fieldType: java.lang.String
	 * @Description: 导入记录ID。
	 */
	@Column(name = "IMPORT_RECORD_ID")
	private String importRecordId;
	
	/**
	 * @fieldName: yearTip
	 * @fieldType: java.lang.Integer
	 * @Description: 年度标识
	 */
	@Column(name = "YEAR_TIP")
	private Integer yearTip;
	
	/**
	 * @fieldName: registerId
	 * @fieldType: java.lang.String
	 * @Description: 记录编号（个人序号）。
	 */
	@Column(name = "REGISTER_ID")
	private String registerId;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名。
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 身份证。
	 */
	@Column(name = "CARD_NO")
	private String cardNo;
	
	/**
	 * @fieldName: sex
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 性别 ,人的性别的代码。GB/T 2261.1
	 */
	@ManyToOne
	@JoinColumn(name = "SEX")
	private CodeInfo sex;
	
	/**
	 * @fieldName: birthDate
	 * @fieldType: java.util.Date
	 * @Description: 出生日期
	 */
	@Column(name = "BIRTH_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	/**
	 * @fieldName: birthPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 出生地名称。
	 */
	@Column(name = "BIRTH_PALCE_NAME")
	private String birthPlaceName;
	
	/**
	 * @fieldName: birthPlace
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 出生地代码 ,该人出生时所在地的当前县级及县级以上国家行政区划代码。GB/T 2260-2007
	 */
	@ManyToOne
	@JoinColumn(name = "BIRTH_PLACE")
	private CodeInfo birthPlace;
	
	/**
	 * @fieldName: nativePlaceName
	 * @fieldType: java.lang.String
	 * @Description: 籍贯名称。
	 */
	@Column(name = "NATIVE_PALCE_NAME")
	private String nativePlaceName;
	
	/**
	 * @fieldName: nativePlace
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 籍贯 ,该人出生时所在地的当前县级及县级以上国家行政区划代码。GB/T 2260-2007
	 */
	@ManyToOne
	@JoinColumn(name = "NATIVE_PLACE")
	private CodeInfo nativePlace;
	
	/**
	 * @fieldName: nation
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 民族 ,该人归属的、国家认可的、在公安户籍管理部门登记注册的民族。GB 3304-1991
	 */
	@ManyToOne
	@JoinColumn(name = "NATION")
	private CodeInfo nation;
	
	/**
	 * @fieldName: politics
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 政治面貌 ,该人当前的政治面貌代码。GB/T 4762-1984
	 */
	@ManyToOne
	@JoinColumn(name = "POLITICS")
	private CodeInfo politics;
	
	/**
	 * @fieldName: attendDate
	 * @fieldType: java.util.Date
	 * @Description: 参加工作日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "ATTEND_DATE")
	@Temporal(TemporalType.DATE)
	private Date attendDate;
	
	/**
	 * @fieldName: grassRoots
	 * @fieldType: java.lang.Long
	 * @Description: 基层工作经历年限（年）
	 */
	@Column(name = "GRASS_ROOTS")
	private Long grassRoots;
	
	/**
	 * @fieldName: isRetiredSoldier
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否退役士兵, DM215
	 */
	@ManyToOne
	@JoinColumn(name = "IS_RETIRED_SOLDIER")
	private CodeInfo isRetiredSoldier;
	
	/**
	 * @fieldName: isRetiredCollegeStudentSoldier
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否退役大学生士兵 , DM215
	 */
	@ManyToOne
	@JoinColumn(name = "IS_RETIRED_COLLEGE_S_S")
	private CodeInfo isRetiredCollegeStudentSoldier;
	
	/**
	 * @fieldName: isdisabled
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否残疾人, DM215
	 */
	@ManyToOne
	@JoinColumn(name = "IS_DISABLED")
	private CodeInfo isdisabled;
	
	/**
	 * @fieldName: isStudyAbroad
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否有海外留学经历, DM215
	 */
	@ManyToOne
	@JoinColumn(name = "IS_STUDY_ABROAD")
	private CodeInfo isStudyAbroad;
	
	/**
	 * @fieldName: studyAbroadTime
	 * @fieldType: java.lang.Integer
	 * @Description: 留学年限
	 */
	@Column(name = "STUDY_ABROAD_TIME")
	private Integer studyAbroadTime;
	
	/**
	 * @fieldName: isWorkAbroad
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否有海外工作经历, DM215
	 */
	@ManyToOne
	@JoinColumn(name = "IS_WORK_ABROAD")
	private CodeInfo isWorkAbroad;
	
	/**
	 * @fieldName: workAbroadTime
	 * @fieldType: java.lang.Integer
	 * @Description: 海外工作年限
	 */
	@Column(name = "WORK_ABROAD_TIME")
	private Integer workAbroadTime;
	
	/**
	 * @fieldName: source
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 来源, 进入公务员、干部队伍前该人的社会身份，GB/T 12405-2008
	 */
	@ManyToOne
	@JoinColumn(name = "SOURCE")
	private CodeInfo source;
	
	/**
	 * @fieldName: draftUnitName
	 * @fieldType: java.lang.String
	 * @Description: 拟录用单位名称。
	 */
	@Column(name = "DRAFT_UNIT_NAME")
	private String draftUnitName;
	
	/**
	 * @fieldName: draftUnitId
	 * @fieldType: java.lang.String
	 * @Description: 拟录用单位id。
	 */
	@ManyToOne
	@JoinColumn(name = "DRAFT_UNIT_ID",  nullable = true)
	private OrgInfo draftUnit;
	
	/**
	 * @fieldName: draftDeptName
	 * @fieldType: java.lang.String
	 * @Description: 拟录用部门名称。
	 */
	@Column(name = "DRAFT_DEPT_NAME")
	private String draftDeptName;
	
	/**
	 * @fieldName: draftDeptId
	 * @fieldType: java.lang.String
	 * @Description: 拟录用部门id。
	 */
	@Column(name = "DRAFT_DEPT_ID")
	private String draftDeptId;
	
	/**
	 * @fieldName: employJobName
	 * @fieldType: java.lang.String
	 * @Description: 职位名称。
	 */
	@Column(name = "EMPLOY_JOB_NAME")
	private String employJobName;
	
	/**
	 * @fieldName: ticketId
	 * @fieldType: java.lang.String
	 * @Description: 录用考试准考证号。
	 */
	@Column(name = "TICKETID")
	private String ticketId;
	
	/**
	 * @fieldName: aptitudeTestScore
	 * @fieldType: java.lang.Float
	 * @Description: 专业能力测试成绩
	 */
	@Column(name = "APTITUDE_S")
	private Float aptitudeTestScore;
	
	/**
	 * @fieldName: publicSubjectTestScore
	 * @fieldType: java.lang.Float
	 * @Description: 公共科目笔试成绩
	 */
	@Column(name = "PUBLIC_SUBJECT_S")
	private Float publicSubjectTestScore;
	
	/**
	 * @fieldName: writtenExamTestScore
	 * @fieldType: java.lang.Float
	 * @Description: 笔试（行政职业能力测试）成绩
	 */
	@Column(name = "WRITTEN_EXAM_S")
	private Float writtenExamTestScore;
	
	/**
	 * @fieldName: explainingScore
	 * @fieldType: java.lang.Float
	 * @Description: 笔试（申论）成绩
	 */
	@Column(name = "EXPLAINING_S")
	private Float explainingScore;
	
	/**
	 * @fieldName: professionalSubjectScore
	 * @fieldType: java.lang.Float
	 * @Description: 笔试（专业科目）成绩
	 */
	@Column(name = "PROFESSIONAL_SUBJECT_S")
	private Float professionalSubjectScore;
	
	/**
	 * @fieldName: interviewScore
	 * @fieldType: java.lang.Float
	 * @Description: 面试成绩
	 */
	@Column(name = "INTERVIEW_S")
	private Float interviewScore;
	
	/**
	 * @fieldName: otherSubjectScore
	 * @fieldType: java.lang.Float
	 * @Description: 其他科目成绩
	 */
	@Column(name = "OTHER_SUBJECT_S")
	private Float otherSubjectScore;
	
	/**
	 * @fieldName: physicalEXAM
	 * @fieldType: java.lang.String
	 * @Description: 体检。
	 */
	@Column(name = "PHYSICAL_EXAM")
	private String physicalEXAM;
	
	/**
	 * @fieldName: politicalExam
	 * @fieldType: java.lang.String
	 * @Description: 政审考核。
	 */
	@Column(name = "POLITICAL_EXAM")
	private String politicalExam;
	
	/**
	 * @fieldName: importFlag
	 * @fieldType: java.lang.String
	 * @Description: 导入标识 “YYYYMMDD”。
	 */
	@Column(name = "IMPORT_FLAG")
	private String importFlag;
	
	/**
	 * @fieldName: publish
	 * @fieldType: java.lang.String
	 * @Description: 状态 0:未发布 1：已发布 常量上方。
	 */
	@Column(name = "PUBLISH")
	private Integer publish;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.String
	 * @Description: 状态 0:未汇总 1：已汇总 常量上方
	 */
	@Column(name = "STATUS")
	private Integer status;

	//导入状态 0未导入 1导入成功
	@Column(name="IMPORTSTATUS")
	private Integer importStatus;

	//导入状态描述
	@Column(name="IMPORTSTATUSSTR")
	private String importStatusStr;

	// ---------------------录用信息----------------------
	/**
	 * @fieldName: employSituation
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 录用情况	CODE:DraftServantEmployStatus
	 */
	@ManyToOne
	@JoinColumn(name = "EMPOLY_SITUATION")
	private CodeInfo employSituation;
	
	/**
	 * @fieldName: employComment
	 * @fieldType: java.lang.String
	 * @Description: 录用鉴定（评语）
	 */
	@Column(name = "EMPLOY_COMMENT")
	private String employComment;
	
	/**
	 * @fieldName: employResult
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 录用标识 , DM035
	 */
	@ManyToOne
	@JoinColumn(name = "EMPOLY_RESULT")
	private CodeInfo employResult;
	
	/**
	 * @fieldName: isBasePractice
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否免基层实习 , DM215
	 */
	@ManyToOne
	@JoinColumn(name = "IS_BASE_PRACTICE")
	private CodeInfo isBasePractice;
	
	/**
	 * @fieldName: yuanUnitName
	 * @fieldType: java.lang.String
	 * @Description: 原单位名称。
	 */
	@Column(name = "YUAN_UNIT_NAME")
	private String yuanUnitName;
	
	/**
	 * @fieldName: isBasePractice
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 原单位性质 , DM142
	 */
	@ManyToOne
	@JoinColumn(name = "YUAN_UNIT_TYPE")
	private CodeInfo yuanUnitType;
	
	/**
	 * @fieldName: deptOpinionDate
	 * @fieldType: java.util.Date
	 * @Description: 所在单位意见时间
	 */
	@Column(name = "DEPT_OPINION_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date deptOpinionDate;
	
	/**
	 * @fieldName: unitOpinionDate
	 * @fieldType: java.util.Date
	 * @Description: 上级单位意见时间
	 */
	@Column(name = "UNIT_OPINION_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date unitOpinionDate;
	
	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 备注。
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 上级主管。
	 */
	@Column(name = "SUPERVISOR")
	private String supervisor;
	
	/**
	 * 录用人员的类型 1公务员 2参公人员
	 */
	@Column(name="SERVANTTYPE")
	private Integer servantType;
	
	/**
	 * 学历
	 */
	@ManyToOne
	@JoinColumn(name = "DEGREE")
	private CodeInfo degree;

	//本科毕业院校
	@Column(name="UNDERGRADUATE_NAME")
	private String undergraduateName;

	/**
	 * 本科毕业时间
	 */
	@Column(name = "UNDER_GRADUATE_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date undergraduateTime;





	//硕士毕业院校
	@Column(name="MASTER_GRADUATE_NAME")
	private String mastergraduateName;


	/**
	 * 硕士毕业时间
	 */
	@Column(name = "MASTER_GRADUATE_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date mastergraduateTime;


	//博士毕业院校
	@Column(name = "DOCTOR_GRADUATE_NAME")
	private String doctorgraduateName;


	/**
	 * 博士毕业时间
	 */
	@Column(name = "DOCTOR_GRADUATE_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date doctorgraduateTime;

	
	/**
	 * 考生身份
	 */
	@Column(name="EXAMINEE_STATUS")
	private String examineeStatus;

	
	/**
	 * 试用期开始时间
	 */
	@Column(name = "PROBATION_START_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date probationStartTime;
	
	/**
	 * 是否确认电子介绍信
	 */
	@Column(name = "IS_ELEC_LETTER")
	private Integer isElecLetter;

	/**
	 * @fieldName: cardNoView
	 * @fieldType: java.lang.String
	 * @Description: 公务员身份证号加密显示。
	 */
	@Transient
	private String cardNoView;


	//电话号码
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	//注册单位
	@ManyToOne
	@JoinColumn(name = "REGISTER")
	private CodeInfo register;


	//户籍地址
	@Column(name = "RESIDENCE_PLACE", length = 80)
	private String residencePlace;

	//是否加入上海户籍
	@ManyToOne
	@JoinColumn(name = "IS_JOIN_RESIDENCE")
	private CodeInfo isJoinResidence;

	//通讯地址
	@Column(name = "HOME_ADDRESS", length = 120)
	private String homeAddress;

	//是否大学生村官
	@ManyToOne
	@JoinColumn(name = "COLLEGE_VILLAGE_OFFICER")
	private CodeInfo collegeVillageOfficer;

	//是否应届
	@ManyToOne
	@JoinColumn(name = "IS_GRADUATING")
	private CodeInfo isGraduating;

	//是否持有上海居住证
	@ManyToOne
	@JoinColumn(name = "IS_RESIDENCE_PERMIT")
	private CodeInfo isResidencePermit;


	/**
	 * @fieldName:教育类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 取得学历学习的方式。DM024
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A08020")
	private CodeInfo eductionalType;

	public String getUndergraduateName() {
		return undergraduateName;
	}

	public void setUndergraduateName(String undergraduateName) {
		this.undergraduateName = undergraduateName;
	}

	public String getMastergraduateName() {
		return mastergraduateName;
	}

	public void setMastergraduateName(String mastergraduateName) {
		this.mastergraduateName = mastergraduateName;
	}

	public String getDoctorgraduateName() {
		return doctorgraduateName;
	}

	public void setDoctorgraduateName(String doctorgraduateName) {
		this.doctorgraduateName = doctorgraduateName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CodeInfo getRegister() {
		return register;
	}

	public void setRegister(CodeInfo register) {
		this.register = register;
	}

	public String getResidencePlace() {
		return residencePlace;
	}

	public void setResidencePlace(String residencePlace) {
		this.residencePlace = residencePlace;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * 
	 * @Title: isShanghai 
	 * @Description: 判断是否为上海人
	 * @return
	 * @return: String
	 */
	public String getIsShanghai(){
		if(this.cardNo==null){
			return null;
		}else{
			if(this.cardNo.startsWith("310")){
				return yesStrCH;
			}else{
				return noStrCH;
			}
		}
	}

	public String getCardNoView() {

		if (StringUtils.isBlank(this.getCardNo())) {
			return "";
		} else {
			if (this.getCardNo().length() <= 4) {
				return "XXXX";
			} else {
				this.cardNoView = StringUtils.substring(this.getCardNo(), 0, (this.getCardNo().length() - 4)) + "XXXX";
				return cardNoView;
			}
		}
	}

	public Integer getIsElecLetter() {
		return isElecLetter;
	}

	public void setIsElecLetter(Integer isElecLetter) {
		this.isElecLetter = isElecLetter;
	}
	
	public Date getProbationStartTime() {
		return probationStartTime;
	}

	public void setProbationStartTime(Date probationStartTime) {
		this.probationStartTime = probationStartTime;
	}

	public Integer getServantType() {
		return servantType;
	}

	public void setServantType(Integer servantType) {
		this.servantType = servantType;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getImportRecordId() {
		
		return importRecordId;
	}
	
	public void setImportRecordId(String importRecordId) {
		
		this.importRecordId = importRecordId;
	}
	
	public Integer getYearTip() {
		
		return yearTip;
	}
	
	public void setYearTip(Integer yearTip) {
		
		this.yearTip = yearTip;
	}
	
	public String getRegisterId() {
		
		return registerId;
	}
	
	public void setRegisterId(String registerId) {
		
		this.registerId = registerId;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public CodeInfo getSex() {
		
		return sex;
	}
	
	public void setSex(CodeInfo sex) {
		
		this.sex = sex;
	}
	
	public Date getBirthDate() {
		
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		
		this.birthDate = birthDate;
	}
	
	public String getBirthPlaceName() {
		
		return birthPlaceName;
	}
	
	public void setBirthPlaceName(String birthPlaceName) {
		
		this.birthPlaceName = birthPlaceName;
	}
	
	public CodeInfo getBirthPlace() {
		
		return birthPlace;
	}
	
	public void setBirthPlace(CodeInfo birthPlace) {
		
		this.birthPlace = birthPlace;
	}
	
	public String getNativePlaceName() {
		
		return nativePlaceName;
	}
	
	public void setNativePlaceName(String nativePlaceName) {
		
		this.nativePlaceName = nativePlaceName;
	}
	
	public CodeInfo getNativePlace() {
		
		return nativePlace;
	}
	
	public void setNativePlace(CodeInfo nativePlace) {
		
		this.nativePlace = nativePlace;
	}
	
	public CodeInfo getNation() {
		
		return nation;
	}
	
	public void setNation(CodeInfo nation) {
		
		this.nation = nation;
	}
	
	public CodeInfo getPolitics() {
		
		return politics;
	}
	
	public void setPolitics(CodeInfo politics) {
		
		this.politics = politics;
	}
	
	public Date getAttendDate() {
		
		return attendDate;
	}
	
	public void setAttendDate(Date attendDate) {
		
		this.attendDate = attendDate;
	}
	
	public Long getGrassRoots() {
		
		return grassRoots;
	}
	
	public void setGrassRoots(Long grassRoots) {
		
		this.grassRoots = grassRoots;
	}
	
	public CodeInfo getIsRetiredSoldier() {
		
		return isRetiredSoldier;
	}
	
	public void setIsRetiredSoldier(CodeInfo isRetiredSoldier) {
		
		this.isRetiredSoldier = isRetiredSoldier;
	}
	
	public CodeInfo getIsRetiredCollegeStudentSoldier() {
		
		return isRetiredCollegeStudentSoldier;
	}
	
	public void setIsRetiredCollegeStudentSoldier(CodeInfo isRetiredCollegeStudentSoldier) {
		
		this.isRetiredCollegeStudentSoldier = isRetiredCollegeStudentSoldier;
	}
	
	public CodeInfo getIsdisabled() {
		
		return isdisabled;
	}
	
	public void setIsdisabled(CodeInfo isdisabled) {
		
		this.isdisabled = isdisabled;
	}
	
	public CodeInfo getIsStudyAbroad() {
		
		return isStudyAbroad;
	}
	
	public void setIsStudyAbroad(CodeInfo isStudyAbroad) {
		
		this.isStudyAbroad = isStudyAbroad;
	}
	
	public Integer getStudyAbroadTime() {
		
		return studyAbroadTime;
	}
	
	public void setStudyAbroadTime(Integer studyAbroadTime) {
		
		this.studyAbroadTime = studyAbroadTime;
	}
	
	public CodeInfo getIsWorkAbroad() {
		
		return isWorkAbroad;
	}
	
	public void setIsWorkAbroad(CodeInfo isWorkAbroad) {
		
		this.isWorkAbroad = isWorkAbroad;
	}
	
	public Integer getWorkAbroadTime() {
		
		return workAbroadTime;
	}
	
	public void setWorkAbroadTime(Integer workAbroadTime) {
		
		this.workAbroadTime = workAbroadTime;
	}
	
	public CodeInfo getSource() {
		
		return source;
	}
	
	public void setSource(CodeInfo source) {
		
		this.source = source;
	}
	
	public String getDraftUnitName() {
		
		return draftUnitName;
	}
	
	public void setDraftUnitName(String draftUnitName) {
		
		this.draftUnitName = draftUnitName;
	}
	
	public OrgInfo getDraftUnit() {
		
		return draftUnit;
	}
	
	public void setDraftUnit(OrgInfo draftUnitId) {
		
		this.draftUnit = draftUnitId;
	}
	
	public String getDraftDeptName() {
		
		return draftDeptName;
	}
	
	public void setDraftDeptName(String draftDeptName) {
		
		this.draftDeptName = draftDeptName;
	}
	
	public String getDraftDeptId() {
		
		return draftDeptId;
	}
	
	public void setDraftDeptId(String draftDeptId) {
		
		this.draftDeptId = draftDeptId;
	}
	
	public String getEmployJobName() {
		
		return employJobName;
	}
	
	public void setEmployJobName(String employJobName) {
		
		this.employJobName = employJobName;
	}
	
	public String getTicketId() {
		
		return ticketId;
	}
	
	public void setTicketId(String ticketId) {
		
		this.ticketId = ticketId;
	}
	
	public Float getAptitudeTestScore() {
		
		return aptitudeTestScore;
	}
	
	public void setAptitudeTestScore(Float aptitudeTestScore) {
		
		this.aptitudeTestScore = aptitudeTestScore;
	}
	
	public Float getPublicSubjectTestScore() {
		
		return publicSubjectTestScore;
	}
	
	public void setPublicSubjectTestScore(Float publicSubjectTestScore) {
		
		this.publicSubjectTestScore = publicSubjectTestScore;
	}
	
	public Float getWrittenExamTestScore() {
		
		return writtenExamTestScore;
	}
	
	public void setWrittenExamTestScore(Float writtenExamTestScore) {
		
		this.writtenExamTestScore = writtenExamTestScore;
	}
	
	public Float getExplainingScore() {
		
		return explainingScore;
	}
	
	public void setExplainingScore(Float explainingScore) {
		
		this.explainingScore = explainingScore;
	}
	
	public Float getProfessionalSubjectScore() {
		
		return professionalSubjectScore;
	}
	
	public void setProfessionalSubjectScore(Float professionalSubjectScore) {
		
		this.professionalSubjectScore = professionalSubjectScore;
	}
	
	public Float getInterviewScore() {
		
		return interviewScore;
	}
	
	public void setInterviewScore(Float interviewScore) {
		
		this.interviewScore = interviewScore;
	}
	
	public Float getOtherSubjectScore() {
		
		return otherSubjectScore;
	}
	
	public void setOtherSubjectScore(Float otherSubjectScore) {
		
		this.otherSubjectScore = otherSubjectScore;
	}
	
	public String getPhysicalEXAM() {
		
		return physicalEXAM;
	}
	
	public void setPhysicalEXAM(String physicalEXAM) {
		
		this.physicalEXAM = physicalEXAM;
	}
	
	public String getPoliticalExam() {
		
		return politicalExam;
	}
	
	public void setPoliticalExam(String politicalExam) {
		
		this.politicalExam = politicalExam;
	}
	
	public String getImportFlag() {
		
		return importFlag;
	}
	
	public void setImportFlag(String importFlag) {
		
		this.importFlag = importFlag;
	}
	
	public Integer getPublish() {
		
		return publish;
	}
	
	public void setPublish(Integer publish) {
		
		this.publish = publish;
	}
	
	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}
	
	public String getEmployComment() {
		
		return employComment;
	}
	
	public void setEmployComment(String employComment) {
		
		this.employComment = employComment;
	}
	
	public CodeInfo getEmployResult() {
		
		return employResult;
	}
	
	public void setEmployResult(CodeInfo employResult) {
		
		this.employResult = employResult;
	}
	
	public CodeInfo getIsBasePractice() {
		
		return isBasePractice;
	}
	
	public void setIsBasePractice(CodeInfo isBasePractice) {
		
		this.isBasePractice = isBasePractice;
	}
	
	public String getYuanUnitName() {
		
		return yuanUnitName;
	}
	
	public void setYuanUnitName(String yuanUnitName) {
		
		this.yuanUnitName = yuanUnitName;
	}
	
	public CodeInfo getYuanUnitType() {
		
		return yuanUnitType;
	}
	
	public void setYuanUnitType(CodeInfo yuanUnitType) {
		
		this.yuanUnitType = yuanUnitType;
	}
	
	public Date getDeptOpinionDate() {
		
		return deptOpinionDate;
	}
	
	public void setDeptOpinionDate(Date deptOpinionDate) {
		
		this.deptOpinionDate = deptOpinionDate;
	}
	
	public Date getUnitOpinionDate() {
		
		return unitOpinionDate;
	}
	
	public void setUnitOpinionDate(Date unitOpinionDate) {
		
		this.unitOpinionDate = unitOpinionDate;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public CodeInfo getEmploySituation() {
		
		return employSituation;
	}
	
	public void setEmploySituation(CodeInfo employSituation) {
		
		this.employSituation = employSituation;
	}


	public Date getUndergraduateTime() {
		return undergraduateTime;
	}

	public void setUndergraduateTime(Date undergraduateTime) {
		this.undergraduateTime = undergraduateTime;
	}

	public Date getMastergraduateTime() {
		return mastergraduateTime;
	}

	public void setMastergraduateTime(Date mastergraduateTime) {
		this.mastergraduateTime = mastergraduateTime;
	}

	public Date getDoctorgraduateTime() {
		return doctorgraduateTime;
	}

	public void setDoctorgraduateTime(Date doctorgraduateTime) {
		this.doctorgraduateTime = doctorgraduateTime;
	}

	public CodeInfo getDegree() {
		return degree;
	}

	public void setDegree(CodeInfo degree) {
		this.degree = degree;
	}

	public String getExamineeStatus() {
		return examineeStatus;
	}

	public void setExamineeStatus(String examineeStatus) {
		this.examineeStatus = examineeStatus;
	}


	public Integer getImportStatus() {
		return importStatus;
	}

	public void setImportStatus(Integer importStatus) {
		this.importStatus = importStatus;
	}

	public String getImportStatusStr() {
		return importStatusStr;
	}

	public void setImportStatusStr(String importStatusStr) {
		this.importStatusStr = importStatusStr;
	}

	public CodeInfo getCollegeVillageOfficer() {
		return collegeVillageOfficer;
	}

	public void setCollegeVillageOfficer(CodeInfo collegeVillageOfficer) {
		this.collegeVillageOfficer = collegeVillageOfficer;
	}

	public CodeInfo getIsGraduating() {
		return isGraduating;
	}

	public void setIsGraduating(CodeInfo isGraduating) {
		this.isGraduating = isGraduating;
	}

	public CodeInfo getIsResidencePermit() {
		return isResidencePermit;
	}

	public void setIsResidencePermit(CodeInfo isResidencePermit) {
		this.isResidencePermit = isResidencePermit;
	}

	public CodeInfo getIsJoinResidence() {
		return isJoinResidence;
	}

	public void setIsJoinResidence(CodeInfo isJoinResidence) {
		this.isJoinResidence = isJoinResidence;
	}

	public CodeInfo getEductionalType() {
		return eductionalType;
	}

	public void setEductionalType(CodeInfo eductionalType) {
		this.eductionalType = eductionalType;
	}
}
