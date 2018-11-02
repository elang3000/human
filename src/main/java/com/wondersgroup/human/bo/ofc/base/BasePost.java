/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BasePost.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 职务信息
 * 创建人: tzy
 * 创建时间: 2018年5月28日 下午6:09:22
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月28日 下午6:09:22
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

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: BasePost
 * @Description: 国标 职务信息
 * @author: tzy
 * @date: 2018年5月28日 下午6:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BasePost<T> extends GenericEntity {
	
	private static final long serialVersionUID = 2001391925114791909L;
	
	/**
	 * @fieldName: tenureName
	 * @fieldType: java.lang.String
	 * @Description: 任职机构名称 ,任职单位的名称。
	 */
	@Column(name = "A02001A", length = 120)
	private String tenureName;
	
	/**
	 * @fieldName: tenureCode
	 * @fieldType: java.lang.String
	 * @Description: 任职机构代码,GB 32100-2015 该人任职机构的代码。
	 */
	@Column(name = "A02001B")
	private String tenureCode;
	
	/**
	 * @fieldName: tenureAdminCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职机构所在行政区划代码,GB/T 2260-2007 该人任职机构所在的当前国家县级及县级以上行政区划代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02003")
	private CodeInfo tenureAdminCode;
	
	/**
	 * @fieldName: tenureSubjection
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职机构隶属关系,GB/T 12404-1997 该人任职机构按国家行政分级管理的系统隶属层次。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02004")
	private CodeInfo tenureSubjection;
	
	/**
	 * @fieldName: takeDate
	 * @fieldType: java.util.Date
	 * @Description: 职务生效日期，GB/T 7408-2005 该职务经过某级人民代表大会选举通过或经过某级人大常委会讨论通过的任命生效日期。
	 */
	@Column(name = "A02006")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date takeDate;
	
	/**
	 * @fieldName: tenureLevel
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职机构级别,DM141 经机构编制管理部门批准的该人任职机构的级别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02007")
	private CodeInfo tenureLevel;
	
	/**
	 * @fieldName: attribute
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职务属性,DM049 担任领导职务或非领导职务的情况。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02008")
	private CodeInfo attribute;
	
	/**
	 * @fieldName: tenureNature
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职机构性质类别,DM142 该人任职机构性质的分类。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02009")
	private CodeInfo tenureNature;
	
	/**
	 * @fieldName: tenureIndustry
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职机构所属行业,GB/T 4754-2011 该人任职机构属于的某类行业。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02011")
	private CodeInfo tenureIndustry;
	
	/**
	 * @fieldName: nextYears
	 * @fieldType: java.lang.Integer
	 * @Description: 在下一级任职年限，该人担任下一级职务的任职年限。
	 */
	@Column(name = "A02014", length = 2)
	private Integer nextYears;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 职务名称 ,该人担任职务的具体名称。
	 */
	@Column(name = "A02016A", length = 80)
	private String postName;
	
	/**
	 * @fieldName: postCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职务代码,GB/T 12403-1990 该人担任职务的代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02016B")
	private CodeInfo postCode;
	
	/**
	 * @fieldName: shortPostName
	 * @fieldType: java.lang.String
	 * @Description: 职务简称 ,该人担任职务的简称。
	 */
	@Column(name = "A02016C", length = 10)
	private String shortPostName;
	
	/**
	 * @fieldName: postExplain
	 * @fieldType: java.lang.String
	 * @Description: 职务说明,该人所任职务需要补充说明的信息。
	 */
	@Column(name = "A02017", length = 400)
	private String postExplain;
	
	/**
	 * @fieldName: tenureRange
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职范围类别,DM218 所担任职务的类型，划分为党内职务、行政职务、其他党派职务、社会团体职务。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02019")
	private CodeInfo tenureRange;
	
	/**
	 * @fieldName: tenureClass
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职位分类,DM219 所任职务类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02020")
	private CodeInfo tenureClass;
	
	/**
	 * @fieldName: lawPersonLevel
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 政法人员职级,DM209 法官、检察官、警务技术人员、执法勤务警员等类别人员现任职务的职务等级。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02021")
	private CodeInfo lawPersonLevel;
	
	/**
	 * @fieldName: manyNo
	 * @fieldType: java.lang.Integer
	 * @Description: 多职务主次序号，个人在多职务情况下按重要程度排序。
	 */
	@Column(name = "A02023", length = 2)
	private Integer manyNo;
	
	/**
	 * @fieldName: collectiveNo
	 * @fieldType: java.lang.Integer
	 * @Description: 集体内排列顺序号，该人在领导班子内排列的顺序号或在所在集体内排列的顺序号。
	 */
	@Column(name = "A02025", length = 3)
	private Integer collectiveNo;
	
	/**
	 * @fieldName: workContentRange
	 * @fieldType: java.lang.String
	 * @Description: 主管（从事）工作,该人主管、 从事工作的范围。
	 */
	@Column(name = "A02029", length = 1000)
	private String workContentRange;
	
	/**
	 * @fieldName: workMajor
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 从事专业,GB/T 16835-1997 该人目前以主要时间和精力从事工作的专业名称或任职所属的专业领域。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02033")
	private CodeInfo workMajor;
	
	/**
	 * @fieldName: nominationUnitName
	 * @fieldType: java.lang.String
	 * @Description: 提名任职的单位名称,提名该人任职的具有法定管理权限的单位名称。
	 */
	@Column(name = "A02035A", length = 120)
	private String nominationUnitName;
	
	/**
	 * @fieldName: nominationDate
	 * @fieldType: java.util.Date
	 * @Description: 任职提名日期，GB/T 7408-2005 由具有法定管理权限的单位提名该人任职的日期。
	 */
	@Column(name = "A02037")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date nominationDate;
	
	/**
	 * @fieldName: nominationNumber
	 * @fieldType: java.lang.String
	 * @Description: 提名任职文号,提名该人任职的文件的编号全称。
	 */
	@Column(name = "A02039", length = 48)
	private String nominationNumber;
	
	/**
	 * @fieldName: approvalUnitName
	 * @fieldType: java.lang.String
	 * @Description:决定或批准任职的单位名称,决定或批准该人任职的单位名称。
	 */
	@Column(name = "A02041A", length = 120)
	private String approvalUnitName;
	
	/**
	 * @fieldName: approvalUnitCode
	 * @fieldType: java.lang.String
	 * @Description: 决定或批准任职的单位代码,GB 32100-2015 决定或批准任职的单位代码。
	 */
	@Column(name = "A02041B")
	private String approvalUnitCode;
	
	/**
	 * @fieldName: approvalDate
	 * @fieldType: java.util.Date
	 * @Description: 决定或批准任职的日期，GB/T 7408-2005 有具有法定管理权限的单位签发的文件确定的该人任职日期或由会议决定该人任职的日期。
	 */
	@Column(name = "A02043")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date approvalDate;
	
	/**
	 * @fieldName: approvalNumber
	 * @fieldType: java.lang.String
	 * @Description:决定或批准任职的文号,由具有法定管理权限的单位签发的有关该人的任职通知、会议决定、命令或公告任职的文件编号。
	 */
	@Column(name = "A02045", length = 120)
	private String approvalNumber;
	
	/**
	 * @fieldName: tenureMode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职方式,DM003 该人担任职务被任用的方式。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02047")
	private CodeInfo tenureMode;
	
	/**
	 * @fieldName: tenureReason
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职原因,DM004 该人任职原因的类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02049")
	private CodeInfo tenureReason;
	
	/**
	 * @fieldName: tenureChange
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职变动类别,DM006 该人当前新任的职务与原任职务的相对变动的类别划分。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02051")
	private CodeInfo tenureChange;
	
	/**
	 * @fieldName: approvalStartDate
	 * @fieldType: java.util.Date
	 * @Description: 连续任该职起始日期，GB/T 7408-2005 该人连续任该职务的起始日期。
	 */
	@Column(name = "A02053")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date approvalStartDate;
	
	/**
	 * @fieldName: tenureStatus
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职状态,DM007 表示该人是否任职或不任职。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02055")
	private CodeInfo tenureStatus;
	
	/**
	 * @fieldName: approvalNumber
	 * @fieldType: java.lang.String
	 * @Description:提名免职的单位名称,提名该人免职的具有法定管理权限的单位名称。
	 */
	@Column(name = "A02057A", length = 120)
	private String nominationDismissalName;
	
	/**
	 * @fieldName: nominationDismissalCode
	 * @fieldType: java.long.String
	 * @Description: 提名免职的单位代码,GB 32100-2015 提名该人免职的具有法定管理权限的单位代码。
	 */
	@Column(name = "A02057B")
	private String nominationDismissalCode;
	
	/**
	 * @fieldName: nominationDismissalDate
	 * @fieldType: java.util.Date
	 * @Description: 提出免职日期，GB/T 7408-2005 由具有法定管理权限的单位提名该人免职的日期。
	 */
	@Column(name = "A02059")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date nominationDismissalDate;
	
	/**
	 * @fieldName: nominationDismissalNumber
	 * @fieldType: java.lang.String
	 * @Description:提出免职文号,决定该人免职的文件的编号全称。
	 */
	@Column(name = "A02061", length = 48)
	private String nominationDismissalNumber;
	
	/**
	 * @fieldName: approvalDismissalName
	 * @fieldType: java.lang.String
	 * @Description:决定或批准免职的单位名称。
	 */
	@Column(name = "A02063A", length = 120)
	private String approvalDismissalName;
	
	/**
	 * @fieldName: approvalDismissalCode
	 * @fieldType: java.long.String
	 * @Description: 决定或批准该人免职的单位代码，GB 32100-2015。
	 */
	@Column(name = "A02063B")
	private String approvalDismissalCode;
	
	/**
	 * @fieldName: approvalDismissalDate
	 * @fieldType: java.util.Date
	 * @Description: 决定或批准免职的日期，GB/T 7408-2005 由具有法定管理权限的单位签发的文件确定的该人免职日期，或由会议决定、命令或公告发布该人免职的日期。
	 */
	@Column(name = "A02065")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date approvalDismissalDate;
	
	/**
	 * @fieldName: approvalDismissalNumber
	 * @fieldType: java.lang.String
	 * @Description:决定或批准免职的文号，由具有法定管理权限的单位签发的有关该人的免职通知、会议决定、命令或公告免职的文件编号。
	 */
	@Column(name = "A02067", length = 48)
	private String approvalDismissalNumber;
	
	/**
	 * @fieldName: dismissalType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 免职方式，DM008 免除干部职务的具体方式。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02069")
	private CodeInfo dismissalType;
	
	/**
	 * @fieldName: dismissalReason
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 免职原因类别，DM009 该人免职原因的类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02071")
	private CodeInfo dismissalReason;
	
	/**
	 * @fieldName: dismissalChange
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 免职变动类别，DM006 该人免职变动的类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02073")
	private CodeInfo dismissalChange;
	
	/**
	 * @fieldName: dismissalMode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 免职决定做出方式，DM163 对该人做出免职决定的方式。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02074")
	private CodeInfo dismissalMode;
	
	/**
	 * @fieldName: postCountSign
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职务统计标识，DM215 有多个兼职的情况下，该职务是否参加统计的标识。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02075")
	private CodeInfo postCountSign;
	
	/**
	 * @fieldName: demotionReason
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 降职原因代码，DM038 该人降职原因的类别代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02076")
	private CodeInfo demotionReason;
	
	/**
	 * @fieldName: communicationSign
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 交流标识，DM215 免去该职务时是否交流任职的标识。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02084")
	private CodeInfo communicationSign;
	
	/**
	 * @fieldName: partTimeSituation
	 * @fieldType: java.lang.String
	 * @Description:兼任情况，兼职情况的说明。
	 */
	@Column(name = "A02095", length = 400)
	private String partTimeSituation;
	
	/**
	 * @fieldName: tenureYears
	 * @fieldType: java.lang.Integer
	 * @Description: 任职年限，在单位任职的时间，以年为单位。
	 */
	@Column(name = "A02097", length = 2)
	private Integer tenureYears;
	
	/**
	 * @fieldName: isBreakPromotion
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否破格提拔，DM215 破格提拔的标识。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02151")
	private CodeInfo isBreakPromotion;
	
	/**
	 * @fieldName: isOpenSelect
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否公开遴选，DM215 是否公开遴选标识。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02191")
	private CodeInfo isOpenSelect;
	
	/**
	 * @fieldName: isOpenSelection
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否公开选调，DM215 是否公开选调标识。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A02192")
	private CodeInfo isOpenSelection;

	
	public String getTenureName() {
		
		return tenureName;
	}

	
	public void setTenureName(String tenureName) {
		
		this.tenureName = tenureName;
	}

	
	public String getTenureCode() {
		
		return tenureCode;
	}

	
	public void setTenureCode(String tenureCode) {
		
		this.tenureCode = tenureCode;
	}

	
	public CodeInfo getTenureAdminCode() {
		
		return tenureAdminCode;
	}

	
	public void setTenureAdminCode(CodeInfo tenureAdminCode) {
		
		this.tenureAdminCode = tenureAdminCode;
	}

	
	public CodeInfo getTenureSubjection() {
		
		return tenureSubjection;
	}

	
	public void setTenureSubjection(CodeInfo tenureSubjection) {
		
		this.tenureSubjection = tenureSubjection;
	}

	
	public Date getTakeDate() {
		
		return takeDate;
	}

	
	public void setTakeDate(Date takeDate) {
		
		this.takeDate = takeDate;
	}

	
	public CodeInfo getTenureLevel() {
		
		return tenureLevel;
	}

	
	public void setTenureLevel(CodeInfo tenureLevel) {
		
		this.tenureLevel = tenureLevel;
	}

	
	public CodeInfo getAttribute() {
		
		return attribute;
	}

	
	public void setAttribute(CodeInfo attribute) {
		
		this.attribute = attribute;
	}

	
	public CodeInfo getTenureNature() {
		
		return tenureNature;
	}

	
	public void setTenureNature(CodeInfo tenureNature) {
		
		this.tenureNature = tenureNature;
	}

	
	public CodeInfo getTenureIndustry() {
		
		return tenureIndustry;
	}

	
	public void setTenureIndustry(CodeInfo tenureIndustry) {
		
		this.tenureIndustry = tenureIndustry;
	}

	
	public Integer getNextYears() {
		
		return nextYears;
	}

	
	public void setNextYears(Integer nextYears) {
		
		this.nextYears = nextYears;
	}

	
	public String getPostName() {
		
		return postName;
	}

	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}

	
	public CodeInfo getPostCode() {
		
		return postCode;
	}

	
	public void setPostCode(CodeInfo postCode) {
		
		this.postCode = postCode;
	}

	
	public String getShortPostName() {
		
		return shortPostName;
	}

	
	public void setShortPostName(String shortPostName) {
		
		this.shortPostName = shortPostName;
	}

	
	public String getPostExplain() {
		
		return postExplain;
	}

	
	public void setPostExplain(String postExplain) {
		
		this.postExplain = postExplain;
	}

	
	public CodeInfo getTenureRange() {
		
		return tenureRange;
	}

	
	public void setTenureRange(CodeInfo tenureRange) {
		
		this.tenureRange = tenureRange;
	}

	
	public CodeInfo getTenureClass() {
		
		return tenureClass;
	}

	
	public void setTenureClass(CodeInfo tenureClass) {
		
		this.tenureClass = tenureClass;
	}

	
	public CodeInfo getLawPersonLevel() {
		
		return lawPersonLevel;
	}

	
	public void setLawPersonLevel(CodeInfo lawPersonLevel) {
		
		this.lawPersonLevel = lawPersonLevel;
	}

	
	public Integer getManyNo() {
		
		return manyNo;
	}

	
	public void setManyNo(Integer manyNo) {
		
		this.manyNo = manyNo;
	}

	
	public Integer getCollectiveNo() {
		
		return collectiveNo;
	}

	
	public void setCollectiveNo(Integer collectiveNo) {
		
		this.collectiveNo = collectiveNo;
	}

	
	public String getWorkContentRange() {
		
		return workContentRange;
	}

	
	public void setWorkContentRange(String workContentRange) {
		
		this.workContentRange = workContentRange;
	}

	
	public CodeInfo getWorkMajor() {
		
		return workMajor;
	}

	
	public void setWorkMajor(CodeInfo workMajor) {
		
		this.workMajor = workMajor;
	}

	
	public String getNominationUnitName() {
		
		return nominationUnitName;
	}

	
	public void setNominationUnitName(String nominationUnitName) {
		
		this.nominationUnitName = nominationUnitName;
	}

	
	public Date getNominationDate() {
		
		return nominationDate;
	}

	
	public void setNominationDate(Date nominationDate) {
		
		this.nominationDate = nominationDate;
	}

	
	public String getNominationNumber() {
		
		return nominationNumber;
	}

	
	public void setNominationNumber(String nominationNumber) {
		
		this.nominationNumber = nominationNumber;
	}

	
	public String getApprovalUnitName() {
		
		return approvalUnitName;
	}

	
	public void setApprovalUnitName(String approvalUnitName) {
		
		this.approvalUnitName = approvalUnitName;
	}

	
	public String getApprovalUnitCode() {
		
		return approvalUnitCode;
	}

	
	public void setApprovalUnitCode(String approvalUnitCode) {
		
		this.approvalUnitCode = approvalUnitCode;
	}

	
	public Date getApprovalDate() {
		
		return approvalDate;
	}

	
	public void setApprovalDate(Date approvalDate) {
		
		this.approvalDate = approvalDate;
	}

	
	public String getApprovalNumber() {
		
		return approvalNumber;
	}

	
	public void setApprovalNumber(String approvalNumber) {
		
		this.approvalNumber = approvalNumber;
	}

	
	public CodeInfo getTenureMode() {
		
		return tenureMode;
	}

	
	public void setTenureMode(CodeInfo tenureMode) {
		
		this.tenureMode = tenureMode;
	}

	
	public CodeInfo getTenureReason() {
		
		return tenureReason;
	}

	
	public void setTenureReason(CodeInfo tenureReason) {
		
		this.tenureReason = tenureReason;
	}

	
	public CodeInfo getTenureChange() {
		
		return tenureChange;
	}

	
	public void setTenureChange(CodeInfo tenureChange) {
		
		this.tenureChange = tenureChange;
	}

	
	public Date getApprovalStartDate() {
		
		return approvalStartDate;
	}

	
	public void setApprovalStartDate(Date approvalStartDate) {
		
		this.approvalStartDate = approvalStartDate;
	}

	
	public CodeInfo getTenureStatus() {
		
		return tenureStatus;
	}

	
	public void setTenureStatus(CodeInfo tenureStatus) {
		
		this.tenureStatus = tenureStatus;
	}

	
	public String getNominationDismissalName() {
		
		return nominationDismissalName;
	}

	
	public void setNominationDismissalName(String nominationDismissalName) {
		
		this.nominationDismissalName = nominationDismissalName;
	}

	
	public String getNominationDismissalCode() {
		
		return nominationDismissalCode;
	}

	
	public void setNominationDismissalCode(String nominationDismissalCode) {
		
		this.nominationDismissalCode = nominationDismissalCode;
	}

	
	public Date getNominationDismissalDate() {
		
		return nominationDismissalDate;
	}

	
	public void setNominationDismissalDate(Date nominationDismissalDate) {
		
		this.nominationDismissalDate = nominationDismissalDate;
	}

	
	public String getNominationDismissalNumber() {
		
		return nominationDismissalNumber;
	}

	
	public void setNominationDismissalNumber(String nominationDismissalNumber) {
		
		this.nominationDismissalNumber = nominationDismissalNumber;
	}

	
	public String getApprovalDismissalName() {
		
		return approvalDismissalName;
	}

	
	public void setApprovalDismissalName(String approvalDismissalName) {
		
		this.approvalDismissalName = approvalDismissalName;
	}

	
	public String getApprovalDismissalCode() {
		
		return approvalDismissalCode;
	}

	
	public void setApprovalDismissalCode(String approvalDismissalCode) {
		
		this.approvalDismissalCode = approvalDismissalCode;
	}

	
	public Date getApprovalDismissalDate() {
		
		return approvalDismissalDate;
	}

	
	public void setApprovalDismissalDate(Date approvalDismissalDate) {
		
		this.approvalDismissalDate = approvalDismissalDate;
	}

	
	public String getApprovalDismissalNumber() {
		
		return approvalDismissalNumber;
	}

	
	public void setApprovalDismissalNumber(String approvalDismissalNumber) {
		
		this.approvalDismissalNumber = approvalDismissalNumber;
	}

	
	public CodeInfo getDismissalType() {
		
		return dismissalType;
	}

	
	public void setDismissalType(CodeInfo dismissalType) {
		
		this.dismissalType = dismissalType;
	}

	
	public CodeInfo getDismissalReason() {
		
		return dismissalReason;
	}

	
	public void setDismissalReason(CodeInfo dismissalReason) {
		
		this.dismissalReason = dismissalReason;
	}

	
	public CodeInfo getDismissalChange() {
		
		return dismissalChange;
	}

	
	public void setDismissalChange(CodeInfo dismissalChange) {
		
		this.dismissalChange = dismissalChange;
	}

	
	public CodeInfo getDismissalMode() {
		
		return dismissalMode;
	}

	
	public void setDismissalMode(CodeInfo dismissalMode) {
		
		this.dismissalMode = dismissalMode;
	}

	
	public CodeInfo getPostCountSign() {
		
		return postCountSign;
	}

	
	public void setPostCountSign(CodeInfo postCountSign) {
		
		this.postCountSign = postCountSign;
	}

	
	public CodeInfo getDemotionReason() {
		
		return demotionReason;
	}

	
	public void setDemotionReason(CodeInfo demotionReason) {
		
		this.demotionReason = demotionReason;
	}

	
	public CodeInfo getCommunicationSign() {
		
		return communicationSign;
	}

	
	public void setCommunicationSign(CodeInfo communicationSign) {
		
		this.communicationSign = communicationSign;
	}

	
	public String getPartTimeSituation() {
		
		return partTimeSituation;
	}

	
	public void setPartTimeSituation(String partTimeSituation) {
		
		this.partTimeSituation = partTimeSituation;
	}

	
	public Integer getTenureYears() {
		
		return tenureYears;
	}

	
	public void setTenureYears(Integer tenureYears) {
		
		this.tenureYears = tenureYears;
	}

	
	public CodeInfo getIsBreakPromotion() {
		
		return isBreakPromotion;
	}

	
	public void setIsBreakPromotion(CodeInfo isBreakPromotion) {
		
		this.isBreakPromotion = isBreakPromotion;
	}

	
	public CodeInfo getIsOpenSelect() {
		
		return isOpenSelect;
	}

	
	public void setIsOpenSelect(CodeInfo isOpenSelect) {
		
		this.isOpenSelect = isOpenSelect;
	}

	
	public CodeInfo getIsOpenSelection() {
		
		return isOpenSelection;
	}

	
	public void setIsOpenSelection(CodeInfo isOpenSelection) {
		
		this.isOpenSelection = isOpenSelection;
	}
	
}
