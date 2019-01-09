/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseEventIntoMgr.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 调入情况和人员基本信息 调任和转任流程共同使用
 * 创建人: tzy
 * 创建时间: 2018年8月1日 上午10:39:51
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月1日 上午10:39:51
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: BaseEventIntoMgr
 * @Description: 调入情况和人员基本信息 调任和转任流程共同使用
 * @author: tzy
 * @date: 2018年8月1日 上午10:39:51
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseEventInto<T> extends GenericEntity {
	
	private static final long serialVersionUID = 6228280994777281146L;
	
	/**
	 * @fieldName: realJobLevelCode
	 * @fieldType: java.lang.String
	 * @Description: 原单位真实占编：职级代码
	 */
	@Column(name = "SOURCE_REAL_JOB_LVL_CODE")
	private String sourceRealJobLevelCode;
	
	/**
	 * @fieldName: realLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 原单位真实占编：职级属性 是否领导，用于区分正科级和副科级。CommonConst.YES/CommonConst.NO
	 */
	@Column(name = "SOURCE_REAL_LEADER")
	private Integer sourceRealLeader;
	
	/**
	 * @fieldName: realJobLevelCode
	 * @fieldType: java.lang.String
	 * @Description: 真实占编：职级代码
	 */
	@Column(name = "REAL_JOB_LVL_CODE")
	private String realJobLevelCode;
	
	/**
	 * @fieldName: realLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 真实占编：职级属性 是否领导，用于区分正科级和副科级。CommonConst.YES/CommonConst.NO
	 */
	@Column(name = "REAL_LEADER")
	private Integer realLeader;
	
	/**
	 * @fieldName: isLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 职级属性 是否领导，用于区分正科级和副科级。CommonConst.YES/CommonConst.NO
	 */
	@Column(name = "IS_LEADER", length = 1)
	private Integer isLeader;
	
	/**
	 * @fieldName: isLeaderView
	 * @fieldType: java.lang.String
	 * @Description: 职级属性 是否领导 用于显示。
	 */
	@Transient
	private String isLeaderView;
	
	/**
	 * @fieldName: 职级名称
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级名称。
	 */
	@Column(name = "JOBLEVEL_NAME")
	private String jobLevelName;
	
	/**
	 * @fieldName: 职级代码
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级代码。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOBLEVEL_CODE")
	private CodeInfo jobLevelCode;
	
	// 人员基本信息
	/**
	 * @fieldName: photoPath
	 * @fieldType: java.lang.String
	 * @Description: 照片路径
	 */
	@Column(name = "PHOTO_PATH", length = 200)
	private String photoPath;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
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
	@Column(name = "CARDNO")
	private String cardNo;
	
	/**
	 * @fieldName: cardNoView
	 * @fieldType: java.lang.String
	 * @Description: 公务员身份证号加密显示。
	 */
	@Transient
	private String cardNoView;
	
	/**
	 * @fieldName: politics
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 性别
	 */
	@ManyToOne
	@JoinColumn(name = "SEX")
	private CodeInfo sex;
	
	/**
	 * @fieldName: birthDate
	 * @fieldType: java.util.Date
	 * @Description: 出生日期，GB/T 7408-2005 该人在公安户籍管理部门登记注册的、在人事档案中记载的并经组织、干部、人事部门确认的出生年月日。
	 */
	@Column(name = "BIRTH_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	/**
	 * @fieldName: nativePlaceName
	 * @fieldType: java.lang.String
	 * @Description: 籍贯名称 ,该人祖居所在地的当前县级及县级以上国家行政区划名称
	 */
	@Column(name = "NATIVE_PLACENAME", length = 80)
	private String nativePlaceName;
	
	/**
	 * @fieldName: nativePlace
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 籍贯代码,GB/T 2260-2007 该人祖居所在地的当前县级及县级以上国家行政区划代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "NATIVE_PLACE")
	private CodeInfo nativePlace;
	
	/**
	 * @fieldName: birthPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 出生地名称,该人出生时所在地的当前县级及县级以上国家行政区划名称
	 */
	@Column(name = "BIRTH_PLACENAME", length = 80)
	private String birthPlaceName;
	
	/**
	 * @fieldName: birthPlace
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 出生地代码 ,GB/T 2260-2007 该人出生时所在地的当前国家政区代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "BIRTH_PLACE")
	private CodeInfo birthPlace;
	
	/**
	 * @fieldName: nation
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 民族 ,GB 3304-1991 该人归属的、国家认可的、在公安户籍管理部门登记注册的民族。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "NATION")
	private CodeInfo nation;
	
	/**
	 * @fieldName: politics
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 政治面貌 ,该人当前的政治面貌代码。GB 4762-84 政治面貌代码
	 */
	@ManyToOne
	@JoinColumn(name = "POLITICS")
	private CodeInfo politics;
	
	/**
	 * @fieldName: health
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 健康状况
	 */
	@ManyToOne
	@JoinColumn(name = "HEALTH")
	private CodeInfo health;
	
	/*
	 * 调入情况信息
	 */
	/**
	 * @fieldName: 进入本地区日期
	 * @fieldType: java.util.Date
	 * @Description: 该人进入现工作单位所在县级及县级以上行政区划的日期。GB/T 7408-2005
	 */
	@Column(name = "ENTERLOCAL_REGION_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enterLocalRegionDate;
	
	/**
	 * @fieldName: 进入本行业、本系统日期
	 * @fieldType: java.util.Date
	 * @Description: 该人进入本行业、本系统工作的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A29004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enterTheSystemDate;
	
	/**
	 * @fieldName: 进入本单位日期
	 * @fieldType: java.util.Date
	 * @Description: 由本单位人事管理部门认定的该人进入本单位工作的日期。GB/T 7408-2005
	 */
	@Column(name = "ENTERTHE_SYSTEM_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enterTheUnitDate;
	
	/**
	 * @fieldName: 进入本单位变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 分为国家统一分配、调动、招收录用情况等。GB/T 12405-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTER_THEUNIT_CHANGE_TYPE")
	private CodeInfo enterTheUnitChangeType;
	
	/**
	 * @fieldName: 进入本单位前个人身份
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 进入本单位前的个人身份。GB/T 2261.4-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMER_PERSONAL_IDENTITY")
	private CodeInfo formerPersonalIdentity;
	
	/**
	 * @fieldName: 进入本单位原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调入本单位原因。DM015
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTER_REASON")
	private CodeInfo enterReason;
	
	/**
	 * @fieldName: 进入本单位前工作单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人进入本单位前原工作单位名称。
	 */
	@Column(name = "FORMER_UNITNAME", length = 120)
	private String formerUnitName;
	
	/**
	 * @fieldName: 进入本单位前工作单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人进入本单位前原工作单位代码。
	 */
	@Column(name = "FORMER_UNITCODE", length = 40)
	private String formerUnitCode;
	
	/**
	 * @fieldName: 进入本单位前工作单位所在行政区划
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位所在的当前国家县级及县级以上行政区划。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMER_UNITAREA_CODE")
	private CodeInfo formerUnitAreaCode;
	
	/**
	 * @fieldName: 进入本单位前工作单位隶属关系
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位按国家行政分级管理的系统隶属层次。GB/T 12404-1997
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMER_UNITBELONG_RELATIONSHIP")
	private CodeInfo formerUnitBelongRelationship;
	
	/**
	 * @fieldName: 进入本单位前工作单位级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位的级别。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMER_UNITLEVEL")
	private CodeInfo formerUnitLevel;
	
	/**
	 * @fieldName: 进入本单位前工作单位性质类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位性质的类别。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMER_UNITNATURE")
	private CodeInfo formerUnitNature;
	
	/**
	 * @fieldName: 进入本单位前工作单位所属行业
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位所属的某类行业。GB/T 4754-2011
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMER_UNITINDUSTRY")
	private CodeInfo formerUnitIndustry;
	
	/**
	 * @fieldName: 在原单位职务名称
	 * @fieldType: java.lang.String
	 * @Description: 该人在原单位中所担任的职务名称。
	 */
	@Column(name = "FORMER_UNITJOBNAME", length = 80)
	private String formerUnitJobName;
	
	/**
	 * @fieldName: 在原单位职务级别
	 * @fieldType: java.lang.String
	 * @Description: 该人在原单位中所担任的职务层次。
	 */
	@Column(name = "FORMER_UNITRANK", length = 80)
	private String formerUnitRank;
	
	/**
	 * @fieldName: 进入本单位时基层工作经历时间(月)
	 * @fieldType: java.lang.Integer
	 * @Description: 该人通过录用、调任、转任、军转干部安置等方式进入本单位时，具有的基层工作经历时间。
	 */
	@Column(name = "INTO_BASIC_WORKTIME", length = 4)
	private Integer intoBasicWorkTime;
	
	/**
	 * @fieldName: 选调生初始工作单位
	 * @fieldType: java.lang.String
	 * @Description: 该人选调为选调生时的实际开始工作单位的简要描述。
	 */
	@Column(name = "SELECTED_GRADUATE_INITUNIT", length = 200)
	private String selectedGraduateInitUnit;
	
	/**
	 * @fieldName: 选调生在基层乡镇、企业工作时间(月)
	 * @fieldType: java.lang.Integer
	 * @Description: 该选调生具有的基层乡镇、企业工作经历时间。
	 */
	@Column(name = "SELECTED_GRADUATE_BASIC_WORKTIME", length = 4)
	private Integer selectedGraduateBasicWorkTime;
	
	/**
	 * @fieldName: 进入本单位前工作单位管理类别性质
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位的管理类别性质。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMER_UNITMGR_NATURE")
	private CodeInfo formerUnitMgrNature;
	
	/**
	 * @fieldName: 遴选时间
	 * @fieldType: java.util.Date
	 * @Description: 该人通过公开遴选进入中央或省级机关的实际日期。GB/T 7408-2005
	 */
	@Column(name = "SELECTION_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date selectionDate;
	
	/**
	 * @fieldName: 是否考试录用人员
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否考试录用人员标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IS_EXAM_EMPLOY")
	private CodeInfo isExamEmploy;
	
	/**
	 * @fieldName: 公务员录用日期
	 * @fieldType: java.util.Date
	 * @Description: 该人录用为公务员或参照管理人员的具体日期。GB/T 7408-2005
	 */
	@Column(name = "EMPLOY_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date employDate;
	
	/**
	 * @fieldName: 录用时政治面貌
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员的政治面貌。
	 */
	@Column(name = "EMPLOY_POLITICAL_STATUS", length = 40)
	private String employPoliticalStatus;
	
	/**
	 * @fieldName: 录用时学历名称
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员前接受正式教育并取得学历证书的学习经历名称。
	 */
	@Column(name = "EMPLOY_EDUCATION_NAME", length = 40)
	private String employEducationName;
	
	/**
	 * @fieldName: 录用时学历代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人录用为公务员或参照管理人员前接受正式教育并取得学历证书的学习经历代码。GB/T 4658-2006
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOY_EDUCATION_CODE")
	private CodeInfo employEducationCode;
	
	/**
	 * @fieldName: 录用时学位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员前完成一定学历教育后所取得的学位名称。
	 */
	@Column(name = "EMPLOY_DEGREE_NAME", length = 40)
	private String employDegreeName;
	
	/**
	 * @fieldName: 录用时学位代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人录用为公务员或参照管理人员前完成一定学历教育后所取得的学位代码。GB/T 6864-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOY_DEGREE_CODE")
	private CodeInfo employDegreeCode;
	
	/**
	 * @fieldName: 录用时基层工作时间(年)
	 * @fieldType: java.lang.Integer
	 * @Description: 该人录用为公务员或参照管理人员时在基层单位的工作经历时间。
	 */
	@Column(name = "EMPLOY_BASICWORK_TIME", length = 3)
	private Integer employBasicWorkTime;
	
	/**
	 * @fieldName: 人员来源情况
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员时的来源情况。
	 */
	@Column(name = "SOURCEOF_PERSONNEL", length = 120)
	private String sourceOfpersonnel;
	
	/**
	 * @fieldName: 进入选调生日期
	 * @fieldType: java.util.Date
	 * @Description: 该人选调为选调生的具体日期。GB/T 7408-2005
	 */
	@Column(name = "INTO_SELECTER_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date intoSelecterDate;
	
	/**
	 * @fieldName: 选调时政治面貌
	 * @fieldType: java.lang.String
	 * @Description: 该人选调为选调生时的政治面貌。
	 */
	@Column(name = "SELECTED_POLITICAL_STATUS", length = 40)
	private String selectedPoliticalStatus;
	
	/**
	 * @fieldName: 选调时学历名称
	 * @fieldType: java.lang.String
	 * @Description: 该人选调为选调生前接受正式教育并取得学历证书的学习经历名称。
	 */
	@Column(name = "SELECTED_EDUCATIONNAME", length = 40)
	private String selectedEducationName;
	
	/**
	 * @fieldName: 选调时学历代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人选调为选调生前接受正式教育并取得学历证书的学习经历代码。GB/T 4658-2006
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SELECTED_EDUCATIONCODE")
	private CodeInfo selectedEducationCode;
	
	/**
	 * @fieldName: 选调时学位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人选调为选调生前完成一定学历教育后所取得的学位名称。
	 */
	@Column(name = "SELECTED_DEGREENAME", length = 40)
	private String selectedDegreeName;
	
	/**
	 * @fieldName: 选调时学位代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人选调为选调生前完成一定学历教育后所取得的学位代码。GB/T 6864-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SELECTED_DEGREECODE")
	private CodeInfo selectedDegreeCode;
	
	/**
	 * @fieldName: 公开选调日期
	 * @fieldType: java.util.Date
	 * @Description: 该人通过公开选调进入中央或省级机关的具体时间。GB/T 7408-2005
	 */
	@Column(name = "OPEN_SELECTED_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date openSelectedDate;
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
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
	
	public Date getEnterLocalRegionDate() {
		
		return enterLocalRegionDate;
	}
	
	public void setEnterLocalRegionDate(Date enterLocalRegionDate) {
		
		this.enterLocalRegionDate = enterLocalRegionDate;
	}
	
	public Date getEnterTheSystemDate() {
		
		return enterTheSystemDate;
	}
	
	public void setEnterTheSystemDate(Date enterTheSystemDate) {
		
		this.enterTheSystemDate = enterTheSystemDate;
	}
	
	public Date getEnterTheUnitDate() {
		
		return enterTheUnitDate;
	}
	
	public void setEnterTheUnitDate(Date enterTheUnitDate) {
		
		this.enterTheUnitDate = enterTheUnitDate;
	}
	
	public CodeInfo getEnterTheUnitChangeType() {
		
		return enterTheUnitChangeType;
	}
	
	public void setEnterTheUnitChangeType(CodeInfo enterTheUnitChangeType) {
		
		this.enterTheUnitChangeType = enterTheUnitChangeType;
	}
	
	public CodeInfo getFormerPersonalIdentity() {
		
		return formerPersonalIdentity;
	}
	
	public void setFormerPersonalIdentity(CodeInfo formerPersonalIdentity) {
		
		this.formerPersonalIdentity = formerPersonalIdentity;
	}
	
	public CodeInfo getEnterReason() {
		
		return enterReason;
	}
	
	public void setEnterReason(CodeInfo enterReason) {
		
		this.enterReason = enterReason;
	}
	
	public String getFormerUnitName() {
		
		return formerUnitName;
	}
	
	public void setFormerUnitName(String formerUnitName) {
		
		this.formerUnitName = formerUnitName;
	}
	
	public String getFormerUnitCode() {
		
		return formerUnitCode;
	}
	
	public void setFormerUnitCode(String formerUnitCode) {
		
		this.formerUnitCode = formerUnitCode;
	}
	
	public CodeInfo getFormerUnitAreaCode() {
		
		return formerUnitAreaCode;
	}
	
	public void setFormerUnitAreaCode(CodeInfo formerUnitAreaCode) {
		
		this.formerUnitAreaCode = formerUnitAreaCode;
	}
	
	public CodeInfo getFormerUnitBelongRelationship() {
		
		return formerUnitBelongRelationship;
	}
	
	public void setFormerUnitBelongRelationship(CodeInfo formerUnitBelongRelationship) {
		
		this.formerUnitBelongRelationship = formerUnitBelongRelationship;
	}
	
	public CodeInfo getFormerUnitLevel() {
		
		return formerUnitLevel;
	}
	
	public void setFormerUnitLevel(CodeInfo formerUnitLevel) {
		
		this.formerUnitLevel = formerUnitLevel;
	}
	
	public CodeInfo getFormerUnitNature() {
		
		return formerUnitNature;
	}
	
	public void setFormerUnitNature(CodeInfo formerUnitNature) {
		
		this.formerUnitNature = formerUnitNature;
	}
	
	public CodeInfo getFormerUnitIndustry() {
		
		return formerUnitIndustry;
	}
	
	public void setFormerUnitIndustry(CodeInfo formerUnitIndustry) {
		
		this.formerUnitIndustry = formerUnitIndustry;
	}
	
	public String getFormerUnitJobName() {
		
		return formerUnitJobName;
	}
	
	public void setFormerUnitJobName(String formerUnitJobName) {
		
		this.formerUnitJobName = formerUnitJobName;
	}
	
	public String getFormerUnitRank() {
		
		return formerUnitRank;
	}
	
	public void setFormerUnitRank(String formerUnitRank) {
		
		this.formerUnitRank = formerUnitRank;
	}
	
	public Integer getIntoBasicWorkTime() {
		
		return intoBasicWorkTime;
	}
	
	public void setIntoBasicWorkTime(Integer intoBasicWorkTime) {
		
		this.intoBasicWorkTime = intoBasicWorkTime;
	}
	
	public String getSelectedGraduateInitUnit() {
		
		return selectedGraduateInitUnit;
	}
	
	public void setSelectedGraduateInitUnit(String selectedGraduateInitUnit) {
		
		this.selectedGraduateInitUnit = selectedGraduateInitUnit;
	}
	
	public Integer getSelectedGraduateBasicWorkTime() {
		
		return selectedGraduateBasicWorkTime;
	}
	
	public void setSelectedGraduateBasicWorkTime(Integer selectedGraduateBasicWorkTime) {
		
		this.selectedGraduateBasicWorkTime = selectedGraduateBasicWorkTime;
	}
	
	public CodeInfo getFormerUnitMgrNature() {
		
		return formerUnitMgrNature;
	}
	
	public void setFormerUnitMgrNature(CodeInfo formerUnitMgrNature) {
		
		this.formerUnitMgrNature = formerUnitMgrNature;
	}
	
	public Date getSelectionDate() {
		
		return selectionDate;
	}
	
	public void setSelectionDate(Date selectionDate) {
		
		this.selectionDate = selectionDate;
	}
	
	public CodeInfo getIsExamEmploy() {
		
		return isExamEmploy;
	}
	
	public void setIsExamEmploy(CodeInfo isExamEmploy) {
		
		this.isExamEmploy = isExamEmploy;
	}
	
	public Date getEmployDate() {
		
		return employDate;
	}
	
	public void setEmployDate(Date employDate) {
		
		this.employDate = employDate;
	}
	
	public String getEmployPoliticalStatus() {
		
		return employPoliticalStatus;
	}
	
	public void setEmployPoliticalStatus(String employPoliticalStatus) {
		
		this.employPoliticalStatus = employPoliticalStatus;
	}
	
	public String getEmployEducationName() {
		
		return employEducationName;
	}
	
	public void setEmployEducationName(String employEducationName) {
		
		this.employEducationName = employEducationName;
	}
	
	public CodeInfo getEmployEducationCode() {
		
		return employEducationCode;
	}
	
	public void setEmployEducationCode(CodeInfo employEducationCode) {
		
		this.employEducationCode = employEducationCode;
	}
	
	public String getEmployDegreeName() {
		
		return employDegreeName;
	}
	
	public void setEmployDegreeName(String employDegreeName) {
		
		this.employDegreeName = employDegreeName;
	}
	
	public CodeInfo getEmployDegreeCode() {
		
		return employDegreeCode;
	}
	
	public void setEmployDegreeCode(CodeInfo employDegreeCode) {
		
		this.employDegreeCode = employDegreeCode;
	}
	
	public Integer getEmployBasicWorkTime() {
		
		return employBasicWorkTime;
	}
	
	public void setEmployBasicWorkTime(Integer employBasicWorkTime) {
		
		this.employBasicWorkTime = employBasicWorkTime;
	}
	
	public String getSourceOfpersonnel() {
		
		return sourceOfpersonnel;
	}
	
	public void setSourceOfpersonnel(String sourceOfpersonnel) {
		
		this.sourceOfpersonnel = sourceOfpersonnel;
	}
	
	public Date getIntoSelecterDate() {
		
		return intoSelecterDate;
	}
	
	public void setIntoSelecterDate(Date intoSelecterDate) {
		
		this.intoSelecterDate = intoSelecterDate;
	}
	
	public String getSelectedPoliticalStatus() {
		
		return selectedPoliticalStatus;
	}
	
	public void setSelectedPoliticalStatus(String selectedPoliticalStatus) {
		
		this.selectedPoliticalStatus = selectedPoliticalStatus;
	}
	
	public String getSelectedEducationName() {
		
		return selectedEducationName;
	}
	
	public void setSelectedEducationName(String selectedEducationName) {
		
		this.selectedEducationName = selectedEducationName;
	}
	
	public CodeInfo getSelectedEducationCode() {
		
		return selectedEducationCode;
	}
	
	public void setSelectedEducationCode(CodeInfo selectedEducationCode) {
		
		this.selectedEducationCode = selectedEducationCode;
	}
	
	public String getSelectedDegreeName() {
		
		return selectedDegreeName;
	}
	
	public void setSelectedDegreeName(String selectedDegreeName) {
		
		this.selectedDegreeName = selectedDegreeName;
	}
	
	public CodeInfo getSelectedDegreeCode() {
		
		return selectedDegreeCode;
	}
	
	public void setSelectedDegreeCode(CodeInfo selectedDegreeCode) {
		
		this.selectedDegreeCode = selectedDegreeCode;
	}
	
	public Date getOpenSelectedDate() {
		
		return openSelectedDate;
	}
	
	public void setOpenSelectedDate(Date openSelectedDate) {
		
		this.openSelectedDate = openSelectedDate;
	}
	
	public CodeInfo getHealth() {
		
		return health;
	}
	
	public void setHealth(CodeInfo health) {
		
		this.health = health;
	}
	
	public String getJobLevelName() {
		
		return jobLevelName;
	}
	
	public void setJobLevelName(String jobLevelName) {
		
		this.jobLevelName = jobLevelName;
	}
	
	public CodeInfo getJobLevelCode() {
		
		return jobLevelCode;
	}
	
	public void setJobLevelCode(CodeInfo jobLevelCode) {
		
		this.jobLevelCode = jobLevelCode;
	}
	
	public String getPhotoPath() {
		
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		
		this.photoPath = photoPath;
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
	
	public Integer getIsLeader() {
		
		return isLeader;
	}
	
	public void setIsLeader(Integer isLeader) {
		
		this.isLeader = isLeader;
	}
	
	public String getRealJobLevelCode() {
		
		return realJobLevelCode;
	}
	
	public void setRealJobLevelCode(String realJobLevelCode) {
		
		this.realJobLevelCode = realJobLevelCode;
	}
	
	public Integer getRealLeader() {
		
		return realLeader;
	}
	
	public void setRealLeader(Integer realLeader) {
		
		this.realLeader = realLeader;
	}
	
	public void setCardNoView(String cardNoView) {
		
		this.cardNoView = cardNoView;
	}
	
	public String getIsLeaderView() {
		
		if (this.getIsLeader() == null) {
			this.isLeaderView = "";
		} else {
			if (this.getIsLeader() == CommonConst.YES) {
				this.isLeaderView = "领导";
			} else if (this.getIsLeader() == CommonConst.NO) {
				this.isLeaderView = "非领导";
			} else {
				this.isLeaderView = "";
			}
		}
		return isLeaderView;
	}
	
	public String getSourceRealJobLevelCode() {
		
		return sourceRealJobLevelCode;
	}
	
	public void setSourceRealJobLevelCode(String sourceRealJobLevelCode) {
		
		this.sourceRealJobLevelCode = sourceRealJobLevelCode;
	}
	
	public Integer getSourceRealLeader() {
		
		return sourceRealLeader;
	}
	
	public void setSourceRealLeader(Integer sourceRealLeader) {
		
		this.sourceRealLeader = sourceRealLeader;
	}
	
}
