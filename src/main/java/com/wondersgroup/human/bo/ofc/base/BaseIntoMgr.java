/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseIntoMgr.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 进入（调入情况）
 * 创建人: jiang
 * 创建时间: 2018年6月27日14:48:41
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月27日14:48:45
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
 * @ClassName: BaseIntoMgr
 * @Description: 国标 进入（调入情况）
 * @author: jiang
 * @date: 2018年6月27日14:49:10
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseIntoMgr<T> extends GenericEntity {
	
	private static final long serialVersionUID = 1616942279226563550L;
	
	/**
	 * @fieldName: 进入本地区日期
	 * @fieldType: java.util.Date
	 * @Description: 该人进入现工作单位所在县级及县级以上行政区划的日期。GB/T 7408-2005
	 */
	@Column(name = "A29001")
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
	@Column(name = "A29007")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enterTheUnitDate;
	
	/**
	 * @fieldName: 进入本单位变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 分为国家统一分配、调动、招收录用情况等。GB/T 12405-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29012")
	private CodeInfo enterTheUnitChangeType;
	
	/**
	 * @fieldName: 进入本单位前个人身份
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 进入本单位前的个人身份。GB/T 2261.4-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29014")
	private CodeInfo formerPersonalIdentity;
	
	/**
	 * @fieldName: 进入本单位原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调入本单位原因。DM015
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29017")
	private CodeInfo enterReason;
	
	/**
	 * @fieldName: 进入本单位前工作单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人进入本单位前原工作单位名称。
	 */
	@Column(name = "A29021A", length = 120)
	private String formerUnitName;
	
	/**
	 * @fieldName: 进入本单位前工作单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人进入本单位前原工作单位代码。
	 */
	@Column(name = "A29021B", length = 18)
	private String formerUnitCode;
	
	/**
	 * @fieldName: 进入本单位前工作单位所在行政区划
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位所在的当前国家县级及县级以上行政区划。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29024")
	private CodeInfo formerUnitAreaCode;
	
	/**
	 * @fieldName: 进入本单位前工作单位隶属关系
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位按国家行政分级管理的系统隶属层次。GB/T 12404-1997
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29028")
	private CodeInfo formerUnitBelongRelationship;
	
	/**
	 * @fieldName: 进入本单位前工作单位级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位的级别。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29031")
	private CodeInfo formerUnitLevel;
	
	/**
	 * @fieldName: 进入本单位前工作单位性质类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位性质的类别。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29034")
	private CodeInfo formerUnitNature;
	
	/**
	 * @fieldName: 进入本单位前工作单位所属行业
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位所属的某类行业。GB/T 4754-2011
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29037")
	private CodeInfo formerUnitIndustry;
	
	/**
	 * @fieldName: 在原单位职务名称
	 * @fieldType: java.lang.String
	 * @Description: 该人在原单位中所担任的职务名称。
	 */
	@Column(name = "A29041", length = 80)
	private String formerUnitJobName;
	
	/**
	 * @fieldName: 在原单位职务级别
	 * @fieldType: java.lang.String
	 * @Description: 该人在原单位中所担任的职务层次。
	 */
	@Column(name = "A29044", length = 80)
	private String formerUnitRank;
	
	/**
	 * @fieldName: 进入本单位时基层工作经历时间(月)
	 * @fieldType: java.lang.Integer
	 * @Description: 该人通过录用、调任、转任、军转干部安置等方式进入本单位时，具有的基层工作经历时间。
	 */
	@Column(name = "A29047", length = 4)
	private Integer intoBasicWorkTime;
	
	/**
	 * @fieldName: 选调生初始工作单位
	 * @fieldType: java.lang.String
	 * @Description: 该人选调为选调生时的实际开始工作单位的简要描述。
	 */
	@Column(name = "A29072", length = 200)
	private String selectedGraduateInitUnit;
	
	/**
	 * @fieldName: 选调生在基层乡镇、企业工作时间(月)
	 * @fieldType: java.lang.Integer
	 * @Description: 该选调生具有的基层乡镇、企业工作经历时间。
	 */
	@Column(name = "A29073", length = 4)
	private Integer selectedGraduateBasicWorkTime;
	
	/**
	 * @fieldName: 进入本单位前工作单位管理类别性质
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入本单位前原工作单位的管理类别性质。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29121")
	private CodeInfo formerUnitMgrNature;
	
	/**
	 * @fieldName: 遴选时间
	 * @fieldType: java.util.Date
	 * @Description: 该人通过公开遴选进入中央或省级机关的实际日期。GB/T 7408-2005
	 */
	@Column(name = "A29307")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date selectionDate;
	
	/**
	 * @fieldName: 是否考试录用人员
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否考试录用人员标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29314")
	private CodeInfo isExamEmploy;
	
	/**
	 * @fieldName: 公务员录用日期
	 * @fieldType: java.util.Date
	 * @Description: 该人录用为公务员或参照管理人员的具体日期。GB/T 7408-2005
	 */
	@Column(name = "A29317")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date employDate;
	
	/**
	 * @fieldName: 录用时政治面貌
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员的政治面貌。
	 */
	@Column(name = "A29321", length = 40)
	private String employPoliticalStatus;
	
	/**
	 * @fieldName: 录用时学历名称
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员前接受正式教育并取得学历证书的学习经历名称。
	 */
	@Column(name = "A29324A", length = 40)
	private String employEducationName;
	
	/**
	 * @fieldName: 录用时学历代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人录用为公务员或参照管理人员前接受正式教育并取得学历证书的学习经历代码。GB/T 4658-2006
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29324B")
	private CodeInfo employEducationCode;
	
	/**
	 * @fieldName: 录用时学位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员前完成一定学历教育后所取得的学位名称。
	 */
	@Column(name = "A29327A", length = 40)
	private String employDegreeName;
	
	/**
	 * @fieldName: 录用时学位代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人录用为公务员或参照管理人员前完成一定学历教育后所取得的学位代码。GB/T 6864-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29327B")
	private CodeInfo employDegreeCode;
	
	/**
	 * @fieldName: 录用时基层工作时间(年)
	 * @fieldType: java.lang.Integer
	 * @Description: 该人录用为公务员或参照管理人员时在基层单位的工作经历时间。
	 */
	@Column(name = "A29334", length = 3)
	private Integer employBasicWorkTime;
	
	/**
	 * @fieldName: 人员来源情况
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员时的来源情况。
	 */
	@Column(name = "A29337", length = 120)
	private String sourceOfpersonnel;
	
	/**
	 * @fieldName: 进入选调生日期
	 * @fieldType: java.util.Date
	 * @Description: 该人选调为选调生的具体日期。GB/T 7408-2005
	 */
	@Column(name = "A29341")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date intoSelecterDate;
	
	/**
	 * @fieldName: 选调时政治面貌
	 * @fieldType: java.lang.String
	 * @Description: 该人选调为选调生时的政治面貌。
	 */
	@Column(name = "A29344", length = 40)
	private String selectedPoliticalStatus;
	
	/**
	 * @fieldName: 选调时学历名称
	 * @fieldType: java.lang.String
	 * @Description: 该人选调为选调生前接受正式教育并取得学历证书的学习经历名称。
	 */
	@Column(name = "A29347A", length = 40)
	private String selectedEducationName;
	
	/**
	 * @fieldName: 选调时学历代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人选调为选调生前接受正式教育并取得学历证书的学习经历代码。GB/T 4658-2006
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29347B")
	private CodeInfo selectedEducationCode;
	
	/**
	 * @fieldName: 选调时学位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人选调为选调生前完成一定学历教育后所取得的学位名称。
	 */
	@Column(name = "A29351A", length = 40)
	private String selectedDegreeName;
	
	/**
	 * @fieldName: 选调时学位代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人选调为选调生前完成一定学历教育后所取得的学位代码。GB/T 6864-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A29351B")
	private CodeInfo selectedDegreeCode;
	
	/**
	 * @fieldName: 公开选调日期
	 * @fieldType: java.util.Date
	 * @Description: 该人通过公开选调进入中央或省级机关的具体时间。GB/T 7408-2005
	 */
	@Column(name = "A29354")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date openSelectedDate;
	
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
	
}
