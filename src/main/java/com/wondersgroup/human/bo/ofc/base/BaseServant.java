/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseServant.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 中组部 人员基本信息
 * 创建人: tzy
 * 创建时间: 2018年5月18日 下午2:05:31
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月18日 下午2:05:31
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: Servant
 * @Description: 中组部 人员基本信息
 * @author: tzy
 * @date: 2018年5月18日 下午2:05:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseServant<T> extends GenericEntity {
	
	private static final long serialVersionUID = -2529543577494815724L;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名 ,在公安户籍管理部门登记注册、人事档案中记载的、正在使用的该人姓名全称。
	 */
	@Column(name = "A01001", length = 50)
	private String name;
	
	/**
	 * @fieldName: pinYinName
	 * @fieldType: java.lang.String
	 * @Description: 姓名拼音 ,该人姓名全称的拼音
	 */
	@Column(name = "A01002A", length = 60)
	private String pinYinName;
	
	/**
	 * @fieldName: shortName
	 * @fieldType: java.lang.String
	 * @Description: 姓名拼音简写 ,该人姓名全称的拼音首字母缩写，多于六个汉字的取前六个汉字的拼音首字母。
	 */
	@Column(name = "A01002B", length = 6)
	private String shortName;
	
	/**
	 * @fieldName: sex
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 性别 ,该人的基本生理特征。GB/T 2261.1-2003 人的性别代码
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01004")
	private CodeInfo sex;
	
	/**
	 * @fieldName: birthDate
	 * @fieldType: java.util.Date
	 * @Description: 出生日期，GB/T 7408-2005 该人在公安户籍管理部门登记注册的、在人事档案中记载的并经组织、干部、人事部门确认的出生年月日。
	 */
	@Column(name = "A01007")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	/**
	 * @fieldName: nativePlaceName
	 * @fieldType: java.lang.String
	 * @Description: 籍贯名称 ,该人祖居所在地的当前县级及县级以上国家行政区划名称
	 */
	@Column(name = "A01011A", length = 80)
	private String nativePlaceName;
	
	/**
	 * @fieldName: nativePlace
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 籍贯代码,GB/T 2260-2007 该人祖居所在地的当前县级及县级以上国家行政区划代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01011B")
	private CodeInfo nativePlace;
	
	/**
	 * @fieldName: birthPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 出生地名称,该人出生时所在地的当前县级及县级以上国家行政区划名称
	 */
	@Column(name = "A01014A", length = 80)
	private String birthPlaceName;
	
	/**
	 * @fieldName: birthPlace
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 出生地代码 ,GB/T 2260-2007 该人出生时所在地的当前国家政区代码。
	 */
	@JsonIgnoreProperties(value = {
	        "handler", "hibernateLazyInitializer", "fieldHandler"
	})
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01014B")
	private CodeInfo birthPlace;
	
	/**
	 * @fieldName: nation
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 民族 ,GB 3304-1991 该人归属的、国家认可的、在公安户籍管理部门登记注册的民族。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01017")
	private CodeInfo nation;
	
	/**
	 * @fieldName: health
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 健康状况,GB/T 2261.3-2003 该人身体基本状况及能适应工作的程度。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01027")
	private CodeInfo health;
	
	/**
	 * @fieldName: healthDescribe
	 * @fieldType: java.lang.String
	 * @Description: 健康状况描述 ,该人身体基本生理机能状况及能适应工作程度的补充描述。
	 */
	@Column(name = "A01028", length = 400)
	private String healthDescribe;
	
	/**
	 * @fieldName: marriage
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 婚姻状况,GB/T 2261.2-2003 该人当前婚姻状况。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01031")
	private CodeInfo marriage;
	
	/**
	 * @fieldName: attendDate
	 * @fieldType: java.util.Date
	 * @Description: 参加工作日期，GB/T 7408-2005 经组织、干部、人事、劳动部门审定的该人参加工作起始日期。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A01034")
	@Temporal(TemporalType.DATE)
	private Date attendDate;
	
	/**
	 * @fieldName: workYearCorrect
	 * @fieldType: java.lang.Integer
	 * @Description: 工龄计算矫正值，该人工龄计算时的增减年数，工龄有间断者，则为负值；有特殊经历或特殊工种按政策增加计算工龄者，则为正值；无增减者为零。
	 */
	@Column(name = "A01037", length = 2)
	private Integer workYearCorrect;
	
	/**
	 * @fieldName: attendDate
	 * @fieldType: java.util.Date
	 * @Description: 进入本系统工作日期，GB/T 7408-2005 进入本系统工作的实际报到日期，以人事部门记载为准。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A01040")
	@Temporal(TemporalType.DATE)
	private Date entryDate;
	
	/**
	 * @fieldName: workYear
	 * @fieldType: java.lang.Integer
	 * @Description: 连续工龄，职工在企业、事业、国家机关连续工作并以工资收入作为全部或主要生活来源的工作时间。
	 */
	@Column(name = "A01044", length = 2)
	private Integer workYear;
	
	/**
	 * @fieldName: treatmentLevel
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 享受待遇级别 ,GB/T 12407-2008 经某级党委、政府及其组织、干部人事管理部门批准享受的各种待遇级别，包括政治待遇或生活待遇。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01048")
	private CodeInfo treatmentLevel;
	
	/**
	 * @fieldName: personalIdentity
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 个人身份 ,GB/T 2261.4-2003 该人按人员管理规定划分的个人身份。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01051")
	private CodeInfo personalIdentity;
	
	/**
	 * @fieldName: identification
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 个人身份特殊项标识 ,DM037 该人是人大代表、政协委员、学部委员等情况。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01052")
	private CodeInfo identification;
	
	/**
	 * @fieldName: indentityBegin
	 * @fieldType: java.util.Date
	 * @Description: 现身份起始日期，GB/T 7408-2005 经劳动、人事部门批准取得现身份的具体日期，自批准之日计算。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A01054")
	@Temporal(TemporalType.DATE)
	private Date indentityBegin;
	
	/**
	 * @fieldName: servantLaw
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 参照公务员法管理标识,DM215 该人是否参照公务员法管理。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01057")
	private CodeInfo servantLaw;
	
	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位名称 ,该人人事关系所在的单位名称。
	 */
	@Column(name = "A01057A", length = 120)
	private String departName;
	
	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位名称 ,该人人事关系所在的单位名称。
	 */
	@Column(name = "A01057B", length = 120)
	private String departCode;
	
	/**
	 * @fieldName: isLeavePost
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 离岗退养标识,DM215 该人是否是离岗退养。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01058")
	private CodeInfo isLeavePost;
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01060")
	private CodeInfo personType;
	
	/**
	 * @fieldName: personAdministration
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位行政区划,该人人事关系所在单位所在的当前国家县级及县级以上行政区划名称。
	 */
	@Column(name = "A01061", length = 80)
	private String personAdministration;
	
	/**
	 * @fieldName: personSubjection
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人事关系所在单位隶属关系,GB/T 12404-1997 该人人事关系所在单位按国家行政分级管理的系统隶属层次
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01062")
	private CodeInfo personSubjection;
	
	/**
	 * @fieldName: isOnHold
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员管理状态,DM200 该人在管理上的在职、离退等状态。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01063")
	private CodeInfo isOnHold;
	
	/**
	 * @fieldName: levelManage
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员管理类别,DM203 该人被认定的管理层级。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01065")
	private CodeInfo levelManage;
	
	/**
	 * @fieldName: personUnitLevel
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人事关系所在单位级别,DM141 经机构编制管理部门批准的该人人事关系所在单位的级别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01067")
	private CodeInfo personUnitLevel;
	
	/**
	 * @fieldName: personUnitType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人事关系所在单位性质类别,DM142 该人人事关系所在单位性质的分类。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01071")
	private CodeInfo personUnitType;
	
	/**
	 * @fieldName: cardNoEd
	 * @fieldType: java.lang.String
	 * @Description: 曾用公民身份号码 ,该人曾用的公民身份号码。
	 */
	@Column(name = "A01073", length = 18)
	private String cardNoEd;
	
	/**
	 * @fieldName: personUnitIndustry
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人事关系所在单位所属行业,GB/T 4754-2011 该人人事关系所在单位属于某类行业
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01074")
	private CodeInfo personUnitIndustry;
	
	/**
	 * @fieldName: residenceNature
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 户口性质,DM191 经公安户籍管理部门确认的该人当前户口管理类型
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01077")
	private CodeInfo residenceNature;
	
	/**
	 * @fieldName: residencePlace
	 * @fieldType: java.lang.String
	 * @Description: 户籍所在地 ,该人户籍所在地的当前国家县级及县级以上行政区划名称。
	 */
	@Column(name = "A01081", length = 80)
	private String residencePlace;
	
	/**
	 * @fieldName: cardType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 个人身份有效证件名称,DM075 该人持有的个人身份有效证件的名称。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01083")
	private CodeInfo cardType;
	
	/**
	 * @fieldName: cardTypeNo
	 * @fieldType: java.lang.String
	 * @Description: 个人身份有效证件号码 ,该人持有的个人身份有效证件的号码。
	 */
	@Column(name = "A01084", length = 18)
	private String cardTypeNo;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 公民身份证号 ,公安机关对（公民）居民给出的唯一的、终身不变的法定号码。
	 */
	@Column(name = "A01085", length = 18)
	private String cardNo;
	
	/**
	 * @fieldName: personRemark
	 * @fieldType: java.lang.String
	 * @Description: 个人基本情况备注 ,该人的进一步说明
	 */
	@Column(name = "A01086", length = 400)
	private String personRemark;
	
	/**
	 * @fieldName: expertise
	 * @fieldType: java.lang.String
	 * @Description: 专长 ,该人从事专业外的业务专长
	 */
	@Column(name = "A01087", length = 100)
	private String expertise;
	
	/**
	 * @fieldName: interested
	 * @fieldType: java.lang.String
	 * @Description: 爱好 ,该人的业余爱好。
	 */
	@Column(name = "A01088", length = 100)
	private String interested;
	
	/**
	 * @fieldName: highestPostEd
	 * @fieldType: java.lang.String
	 * @Description: 曾担任的最高职务,该人曾担任的领导人职务名称
	 */
	@Column(name = "A01090", length = 100)
	private String highestPostEd;
	
	/**
	 * @fieldName: startHighestLevel
	 * @fieldType: java.util.Date
	 * @Description: 开始担任最高行政级别时间，GB/T 7408-2005 该人开始担任其最高行政级别的时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM")
	@Column(name = "A01094")
	@Temporal(TemporalType.DATE)
	private Date startHighestLevel;
	
	/**
	 * @fieldName: dieDate
	 * @fieldType: java.util.Date
	 * @Description: 去世时间，GB/T 7408-2005 该人的去世时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A01095")
	@Temporal(TemporalType.DATE)
	private Date dieDate;
	
	/**
	 * @fieldName: personPhotoName
	 * @fieldType: java.lang.String
	 * @Description: 个人照片名称 ,该人照片的名称。
	 */
	@Column(name = "A01097", length = 80)
	private String personPhotoName;
	
	/**
	 * @fieldName: staySign
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 待任标志 ,DM215 该人是否待任的标识
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01099")
	private CodeInfo staySign;
	
	/**
	 * @fieldName: grassRootYear
	 * @fieldType: java.lang.Integer
	 * @Description: 基层工作经历时间，该人在基层工作的年限。
	 */
	@Column(name = "A01103", length = 2)
	private Float grassRootYear;
	
	/**
	 * @fieldName: isGrassRoot
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否具有基层工作经历 ,DM215 是否具有基层工作经验标识
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01107")
	private CodeInfo isGrassRoot;
	
	/**
	 * @fieldName: grassRootUnit
	 * @fieldType: java.lang.String
	 * @Description: 基层工作单位,该人曾在基层工作的单位。
	 */
	@Column(name = "A01108", length = 120)
	private String grassRootUnit;
	
	/**
	 * @fieldName: statisticsDeptCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 统计关系所在单位代码 ,GB 32100-2015 该人统计关系所在单位的编码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A01109")
	private CodeInfo statisticsDeptCode;
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getPinYinName() {
		
		return pinYinName;
	}
	
	public void setPinYinName(String pinYinName) {
		
		this.pinYinName = pinYinName;
	}
	
	public String getShortName() {
		
		return shortName;
	}
	
	public void setShortName(String shortName) {
		
		this.shortName = shortName;
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
	
	public CodeInfo getHealth() {
		
		return health;
	}
	
	public void setHealth(CodeInfo health) {
		
		this.health = health;
	}
	
	public String getHealthDescribe() {
		
		return healthDescribe;
	}
	
	public void setHealthDescribe(String healthDescribe) {
		
		this.healthDescribe = healthDescribe;
	}
	
	public CodeInfo getMarriage() {
		
		return marriage;
	}
	
	public void setMarriage(CodeInfo marriage) {
		
		this.marriage = marriage;
	}
	
	public Date getAttendDate() {
		
		return attendDate;
	}
	
	public void setAttendDate(Date attendDate) {
		
		this.attendDate = attendDate;
	}
	
	public Date getEntryDate() {
		
		return entryDate;
	}
	
	public void setEntryDate(Date entryDate) {
		
		this.entryDate = entryDate;
	}
	
	public CodeInfo getTreatmentLevel() {
		
		return treatmentLevel;
	}
	
	public void setTreatmentLevel(CodeInfo treatmentLevel) {
		
		this.treatmentLevel = treatmentLevel;
	}
	
	public CodeInfo getPersonalIdentity() {
		
		return personalIdentity;
	}
	
	public void setPersonalIdentity(CodeInfo personalIdentity) {
		
		this.personalIdentity = personalIdentity;
	}
	
	public CodeInfo getIdentification() {
		
		return identification;
	}
	
	public void setIdentification(CodeInfo identification) {
		
		this.identification = identification;
	}
	
	public Date getIndentityBegin() {
		
		return indentityBegin;
	}
	
	public void setIndentityBegin(Date indentityBegin) {
		
		this.indentityBegin = indentityBegin;
	}
	
	public CodeInfo getServantLaw() {
		
		return servantLaw;
	}
	
	public void setServantLaw(CodeInfo servantLaw) {
		
		this.servantLaw = servantLaw;
	}
	
	public String getDepartName() {
		
		return departName;
	}
	
	public void setDepartName(String departName) {
		
		this.departName = departName;
	}
	
	public CodeInfo getIsLeavePost() {
		
		return isLeavePost;
	}
	
	public void setIsLeavePost(CodeInfo isLeavePost) {
		
		this.isLeavePost = isLeavePost;
	}
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
	}
	
	public String getPersonAdministration() {
		
		return personAdministration;
	}
	
	public void setPersonAdministration(String personAdministration) {
		
		this.personAdministration = personAdministration;
	}
	
	public CodeInfo getPersonSubjection() {
		
		return personSubjection;
	}
	
	public void setPersonSubjection(CodeInfo personSubjection) {
		
		this.personSubjection = personSubjection;
	}
	
	public CodeInfo getIsOnHold() {
		
		return isOnHold;
	}
	
	public void setIsOnHold(CodeInfo isOnHold) {
		
		this.isOnHold = isOnHold;
	}
	
	public CodeInfo getLevelManage() {
		
		return levelManage;
	}
	
	public void setLevelManage(CodeInfo levelManage) {
		
		this.levelManage = levelManage;
	}
	
	public CodeInfo getPersonUnitLevel() {
		
		return personUnitLevel;
	}
	
	public void setPersonUnitLevel(CodeInfo personUnitLevel) {
		
		this.personUnitLevel = personUnitLevel;
	}
	
	public CodeInfo getPersonUnitType() {
		
		return personUnitType;
	}
	
	public void setPersonUnitType(CodeInfo personUnitType) {
		
		this.personUnitType = personUnitType;
	}
	
	public String getCardNoEd() {
		
		return cardNoEd;
	}
	
	public void setCardNoEd(String cardNoEd) {
		
		this.cardNoEd = cardNoEd;
	}
	
	public CodeInfo getPersonUnitIndustry() {
		
		return personUnitIndustry;
	}
	
	public void setPersonUnitIndustry(CodeInfo personUnitIndustry) {
		
		this.personUnitIndustry = personUnitIndustry;
	}
	
	public CodeInfo getResidenceNature() {
		
		return residenceNature;
	}
	
	public void setResidenceNature(CodeInfo residenceNature) {
		
		this.residenceNature = residenceNature;
	}
	
	public String getResidencePlace() {
		
		return residencePlace;
	}
	
	public void setResidencePlace(String residencePlace) {
		
		this.residencePlace = residencePlace;
	}
	
	public CodeInfo getCardType() {
		
		return cardType;
	}
	
	public void setCardType(CodeInfo cardType) {
		
		this.cardType = cardType;
	}
	
	public String getCardTypeNo() {
		
		return cardTypeNo;
	}
	
	public void setCardTypeNo(String cardTypeNo) {
		
		this.cardTypeNo = cardTypeNo;
	}
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public String getPersonRemark() {
		
		return personRemark;
	}
	
	public void setPersonRemark(String personRemark) {
		
		this.personRemark = personRemark;
	}
	
	public String getExpertise() {
		
		return expertise;
	}
	
	public void setExpertise(String expertise) {
		
		this.expertise = expertise;
	}
	
	public String getInterested() {
		
		return interested;
	}
	
	public void setInterested(String interested) {
		
		this.interested = interested;
	}
	
	public String getHighestPostEd() {
		
		return highestPostEd;
	}
	
	public void setHighestPostEd(String highestPostEd) {
		
		this.highestPostEd = highestPostEd;
	}
	
	public Date getStartHighestLevel() {
		
		return startHighestLevel;
	}
	
	public void setStartHighestLevel(Date startHighestLevel) {
		
		this.startHighestLevel = startHighestLevel;
	}
	
	public Date getDieDate() {
		
		return dieDate;
	}
	
	public void setDieDate(Date dieDate) {
		
		this.dieDate = dieDate;
	}
	
	public String getPersonPhotoName() {
		
		return personPhotoName;
	}
	
	public void setPersonPhotoName(String personPhotoName) {
		
		this.personPhotoName = personPhotoName;
	}
	
	public CodeInfo getStaySign() {
		
		return staySign;
	}
	
	public void setStaySign(CodeInfo staySign) {
		
		this.staySign = staySign;
	}
	
	public Float getGrassRootYear() {
		
		return grassRootYear;
	}
	
	public void setGrassRootYear(Float grassRootYear) {
		
		this.grassRootYear = grassRootYear;
	}
	
	public CodeInfo getIsGrassRoot() {
		
		return isGrassRoot;
	}
	
	public void setIsGrassRoot(CodeInfo isGrassRoot) {
		
		this.isGrassRoot = isGrassRoot;
	}
	
	public String getGrassRootUnit() {
		
		return grassRootUnit;
	}
	
	public void setGrassRootUnit(String grassRootUnit) {
		
		this.grassRootUnit = grassRootUnit;
	}
	
	public CodeInfo getStatisticsDeptCode() {
		
		return statisticsDeptCode;
	}
	
	public void setStatisticsDeptCode(CodeInfo statisticsDeptCode) {
		
		this.statisticsDeptCode = statisticsDeptCode;
	}
	
	public Integer getWorkYearCorrect() {
		
		return workYearCorrect;
	}
	
	public void setWorkYearCorrect(Integer workYearCorrect) {
		
		this.workYearCorrect = workYearCorrect;
	}
	
	public Integer getWorkYear() {
		
		return workYear;
	}
	
	public void setWorkYear(Integer workYear) {
		
		this.workYear = workYear;
	}
	
	public String getDepartCode() {
		
		return departCode;
	}
	
	public void setDepartCode(String departCode) {
		
		this.departCode = departCode;
	}
	
}
