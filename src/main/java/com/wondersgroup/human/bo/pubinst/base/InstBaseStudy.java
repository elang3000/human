/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseStudy.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 学习（培训、进修）
 * 创建人: jiang
 * 创建时间: 2018年6月25日09:27:09
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月25日09:27:14
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.pubinst.base;

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
 * @ClassName: BaseStudy
 * @Description: 国标 学习（培训、进修）
 * @author: jiang
 * @date: 2018年6月25日09:27:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseStudy<T> extends GenericEntity {
	
	private static final long serialVersionUID = -2907169552679522989L;
	
	/**
	 * @fieldName: 学习（培训、进修）类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加工作后接受的培训、进修等教育的类别。DM040
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11001")
	private CodeInfo category;
	
	/**
	 * @fieldName: 学习（培训、进修）方式
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加培训或进修时脱产（离岗）的状况。DM024
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11004")
	private CodeInfo mode;
	
	/**
	 * @fieldName: 学习（培训、进修）起始日期
	 * @fieldType: java.util.Date
	 * @Description: 实际学习的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A11007")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	/**
	 * @fieldName: 学习（培训、进修）结束日期
	 * @fieldType: java.util.Date
	 * @Description: 该人参加培训或进修的实际结束日期。GB/T 7408-2005
	 */
	@Column(name = "A11011")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	/**
	 * @fieldName: 学习（培训、进修）主办单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人参加培训或进修的主办单位名称。
	 */
	@Column(name = "A11014A", length = 120)
	private String hostUnitName;
	
	/**
	 * @fieldName: 学习（培训、进修）主办单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人参加学习、培训或进修的主办单位代码。GB 32100-2015
	 */
	@Column(name = "A11014B", length = 18)
	private String hostUnitCode;
	
	/**
	 * @fieldName: 学习（培训、进修）主办单位级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加学习、培训或进修的主办单位的级别。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11017")
	private CodeInfo hostUnitlvl;
	
	/**
	 * @fieldName: 学习（培训、进修）承办单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人参加学习、培训或进修的承办单位名称。
	 */
	@Column(name = "A11018A", length = 120)
	private String undertakingUnitName;
	
	/**
	 * @fieldName: 学习（培训、进修）承办单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人参加学习、培训或进修的承办单位代码。GB 32100-2015
	 */
	@Column(name = "A11018B", length = 18)
	private String undertakingUnitCode;
	
	/**
	 * @fieldName: 学习（培训、进修）国外合作单位
	 * @fieldType: java.lang.String
	 * @Description: 该人参加培训或进修的国外合作单位名称。
	 */
	@Column(name = "A11019", length = 120)
	private String foreignCooperationUnitName;
	
	/**
	 * @fieldName: 在学单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人学习培训时所在的教育部门或单位的名称。
	 */
	@Column(name = "A11021A", length = 120)
	private String studyUnitName;
	
	/**
	 * @fieldName: 在学单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人学习所在单位名称的代码。GB 32100-2015
	 */
	@Column(name = "A11021B", length = 18)
	private String studyUnitCode;
	
	/**
	 * @fieldName: 在学单位所在行政区划
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人从学单位所在的当前国家县级及县级以上行政区划。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11024")
	private CodeInfo studyUnitAreaCode;
	
	/**
	 * @fieldName: 在学单位类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人在学单位的类别划分。DM022
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11027")
	private CodeInfo currentUnitType;
	
	/**
	 * @fieldName: 学习（培训、进修）班名称
	 * @fieldType: java.lang.String
	 * @Description: 接受培训、进修所参加的培训班名称。
	 */
	@Column(name = "A11031", length = 100)
	private String studyClassName;
	
	/**
	 * @fieldName: 出国（境）学习主题
	 * @fieldType: java.lang.String
	 * @Description: 该人参加的出国（境）学习、培训或进修的主题。
	 */
	@Column(name = "A11032", length = 2000)
	private String abroadStudyTheme;
	
	/**
	 * @fieldName: 学习（培训、进修）班名称类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加学习、培训或进修班的类别。DM023
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11034")
	private CodeInfo studyClassType;
	
	/**
	 * @fieldName: 学习（培训、进修）班学员人数
	 * @fieldType: java.lang.Integer
	 * @Description: 该人参加的学习、培训或进修班的学员人数。
	 */
	@Column(name = "A11035", length = 6)
	private Integer studentsNum;
	
	/**
	 * @fieldName: 学习（培训、进修）专业名称
	 * @fieldType: java.lang.String
	 * @Description: 该人参加学习、培训或进修的专业名称。
	 */
	@Column(name = "A11037", length = 60)
	private String professionalName;
	
	/**
	 * @fieldName: 学习（培训、进修）专业类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加学习、培训或进修的专业类别。GB/T 16835-1997
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11041")
	private CodeInfo professionalType;
	
	/**
	 * @fieldName: 学习（培训、进修）完成情况
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加学习、培训或进修完成的情况。DM025
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11044")
	private CodeInfo completionSituation;
	
	/**
	 * @fieldName: 学习（培训、进修）内容
	 * @fieldType: java.lang.String
	 * @Description: 学习、培训或进修的内容简要说明。
	 */
	@Column(name = "A11045", length = 2000)
	private String learningContent;
	
	/**
	 * @fieldName: 学习（培训、进修）成绩
	 * @fieldType: java.lang.Float
	 * @Description: 公务员参加培训学习的成绩。
	 */
	@Column(name = "A11046", length = 2)
	private Float studyScore;
	
	/**
	 * @fieldName: 学习（培训、进修）时所在单位及职务
	 * @fieldType: java.lang.String
	 * @Description: 该人参加学习、培训或进修时所在的单位及职务。
	 */
	@Column(name = "A11047", length = 2000)
	private String currentUnitPost;
	
	/**
	 * @fieldName: 学习（培训、进修）性质
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加工作后接受的非学历教育。DM023
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11048")
	private CodeInfo studyNature;
	
	/**
	 * @fieldName: 出国（境）学习（培训）标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加的学习、培训是否为出国（境）培训。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11051")
	private CodeInfo isAbroadStudy;
	
	/**
	 * @fieldName: 在学单位所在国别（地区）代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加的学习、培训所在的国家（地区）的代码。GB/T 2659-2000
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A11054")
	private CodeInfo studyUnitCountryAreaCode;
	
	/**
	 * @fieldName: 培训时长(天)
	 * @fieldType: java.lang.Integer
	 * @Description: 该人参加的培训或进修的总时长（以天数为单位）。
	 */
	@Column(name = "A11057", length = 4)
	private Integer studyLengthOfTime;
	
	/**
	 * @fieldName: 学时
	 * @fieldType: java.lang.Integer
	 * @Description: 实际学习的累计学时（按小时计算）。
	 */
	@Column(name = "A11066", length = 4)
	private Integer studyHours;
	
	/**
	 * @fieldName: 学习（培训、进修）所学专业
	 * @fieldType: java.lang.String
	 * @Description: 实际学习的主要专业。
	 */
	@Column(name = "A11067", length = 60)
	private String professionalIntroduction;
	
	/**
	 * @fieldName: 县处级及以上干部近 5 年参加培训总月数
	 * @fieldType: java.lang.Integer
	 * @Description: 县处级及以上领导干部近 5 年参加干部教育培训的总月数。
	 */
	@Column(name = "A11071", length = 2)
	private Integer aboveCountyLevelTotalMonthsOfFiveYears;
	
	/**
	 * @fieldName: 县处级及以上干部近 5 年参加培训总学时
	 * @fieldType: java.lang.Integer
	 * @Description: 县处级及以上领导干部近 5 年参加干部教育培训的总学时数。
	 */
	@Column(name = "A11074", length = 4)
	private Integer aboveCountyLevelTotalHoursOfFiveYears;
	
	/**
	 * @fieldName: 县处级以下干部每年参加培训总天数
	 * @fieldType: java.lang.Integer
	 * @Description: 县处级以下干部每年参加干部教育培训的总天数。
	 */
	@Column(name = "A11077", length = 3)
	private Integer belowCountyLevelTotalDaysOfEveryYear;
	
	/**
	 * @fieldName: 县处级以下干部每年参加培训总学时
	 * @fieldType: java.lang.Integer
	 * @Description: 县处级以下干部每年参加干部教育培训的总学时。
	 */
	@Column(name = "A11081", length = 3)
	private Integer belowCountyLevelTotalHoursOfEveryYear;

	
	public CodeInfo getCategory() {
		
		return category;
	}

	
	public void setCategory(CodeInfo category) {
		
		this.category = category;
	}

	
	public CodeInfo getMode() {
		
		return mode;
	}

	
	public void setMode(CodeInfo mode) {
		
		this.mode = mode;
	}

	
	public Date getStartDate() {
		
		return startDate;
	}

	
	public void setStartDate(Date startDate) {
		
		this.startDate = startDate;
	}

	
	public Date getEndDate() {
		
		return endDate;
	}

	
	public void setEndDate(Date endDate) {
		
		this.endDate = endDate;
	}

	
	public String getHostUnitName() {
		
		return hostUnitName;
	}

	
	public void setHostUnitName(String hostUnitName) {
		
		this.hostUnitName = hostUnitName;
	}

	
	public String getHostUnitCode() {
		
		return hostUnitCode;
	}

	
	public void setHostUnitCode(String hostUnitCode) {
		
		this.hostUnitCode = hostUnitCode;
	}

	
	public CodeInfo getHostUnitlvl() {
		
		return hostUnitlvl;
	}

	
	public void setHostUnitlvl(CodeInfo hostUnitlvl) {
		
		this.hostUnitlvl = hostUnitlvl;
	}

	
	public String getUndertakingUnitName() {
		
		return undertakingUnitName;
	}

	
	public void setUndertakingUnitName(String undertakingUnitName) {
		
		this.undertakingUnitName = undertakingUnitName;
	}

	
	public String getUndertakingUnitCode() {
		
		return undertakingUnitCode;
	}

	
	public void setUndertakingUnitCode(String undertakingUnitCode) {
		
		this.undertakingUnitCode = undertakingUnitCode;
	}

	
	public String getForeignCooperationUnitName() {
		
		return foreignCooperationUnitName;
	}

	
	public void setForeignCooperationUnitName(String foreignCooperationUnitName) {
		
		this.foreignCooperationUnitName = foreignCooperationUnitName;
	}

	
	public String getStudyUnitName() {
		
		return studyUnitName;
	}

	
	public void setStudyUnitName(String studyUnitName) {
		
		this.studyUnitName = studyUnitName;
	}

	
	public String getStudyUnitCode() {
		
		return studyUnitCode;
	}

	
	public void setStudyUnitCode(String studyUnitCode) {
		
		this.studyUnitCode = studyUnitCode;
	}

	
	public CodeInfo getStudyUnitAreaCode() {
		
		return studyUnitAreaCode;
	}

	
	public void setStudyUnitAreaCode(CodeInfo studyUnitAreaCode) {
		
		this.studyUnitAreaCode = studyUnitAreaCode;
	}

	
	public CodeInfo getCurrentUnitType() {
		
		return currentUnitType;
	}

	
	public void setCurrentUnitType(CodeInfo currentUnitType) {
		
		this.currentUnitType = currentUnitType;
	}

	
	public String getStudyClassName() {
		
		return studyClassName;
	}

	
	public void setStudyClassName(String studyClassName) {
		
		this.studyClassName = studyClassName;
	}

	
	public String getAbroadStudyTheme() {
		
		return abroadStudyTheme;
	}

	
	public void setAbroadStudyTheme(String abroadStudyTheme) {
		
		this.abroadStudyTheme = abroadStudyTheme;
	}

	
	public CodeInfo getStudyClassType() {
		
		return studyClassType;
	}

	
	public void setStudyClassType(CodeInfo studyClassType) {
		
		this.studyClassType = studyClassType;
	}

	
	public Integer getStudentsNum() {
		
		return studentsNum;
	}

	
	public void setStudentsNum(Integer studentsNum) {
		
		this.studentsNum = studentsNum;
	}

	
	public String getProfessionalName() {
		
		return professionalName;
	}

	
	public void setProfessionalName(String professionalName) {
		
		this.professionalName = professionalName;
	}

	
	public CodeInfo getProfessionalType() {
		
		return professionalType;
	}

	
	public void setProfessionalType(CodeInfo professionalType) {
		
		this.professionalType = professionalType;
	}

	
	public CodeInfo getCompletionSituation() {
		
		return completionSituation;
	}

	
	public void setCompletionSituation(CodeInfo completionSituation) {
		
		this.completionSituation = completionSituation;
	}

	
	public String getLearningContent() {
		
		return learningContent;
	}

	
	public void setLearningContent(String learningContent) {
		
		this.learningContent = learningContent;
	}

	
	public Float getStudyScore() {
		
		return studyScore;
	}

	
	public void setStudyScore(Float studyScore) {
		
		this.studyScore = studyScore;
	}

	
	public String getCurrentUnitPost() {
		
		return currentUnitPost;
	}

	
	public void setCurrentUnitPost(String currentUnitPost) {
		
		this.currentUnitPost = currentUnitPost;
	}

	
	public CodeInfo getStudyNature() {
		
		return studyNature;
	}

	
	public void setStudyNature(CodeInfo studyNature) {
		
		this.studyNature = studyNature;
	}

	
	public CodeInfo getIsAbroadStudy() {
		
		return isAbroadStudy;
	}

	
	public void setIsAbroadStudy(CodeInfo isAbroadStudy) {
		
		this.isAbroadStudy = isAbroadStudy;
	}

	
	public CodeInfo getStudyUnitCountryAreaCode() {
		
		return studyUnitCountryAreaCode;
	}

	
	public void setStudyUnitCountryAreaCode(CodeInfo studyUnitCountryAreaCode) {
		
		this.studyUnitCountryAreaCode = studyUnitCountryAreaCode;
	}

	
	public Integer getStudyLengthOfTime() {
		
		return studyLengthOfTime;
	}

	
	public void setStudyLengthOfTime(Integer studyLengthOfTime) {
		
		this.studyLengthOfTime = studyLengthOfTime;
	}

	
	public Integer getStudyHours() {
		
		return studyHours;
	}

	
	public void setStudyHours(Integer studyHours) {
		
		this.studyHours = studyHours;
	}

	
	public String getProfessionalIntroduction() {
		
		return professionalIntroduction;
	}

	
	public void setProfessionalIntroduction(String professionalIntroduction) {
		
		this.professionalIntroduction = professionalIntroduction;
	}

	
	public Integer getAboveCountyLevelTotalMonthsOfFiveYears() {
		
		return aboveCountyLevelTotalMonthsOfFiveYears;
	}

	
	public void setAboveCountyLevelTotalMonthsOfFiveYears(Integer aboveCountyLevelTotalMonthsOfFiveYears) {
		
		this.aboveCountyLevelTotalMonthsOfFiveYears = aboveCountyLevelTotalMonthsOfFiveYears;
	}

	
	public Integer getAboveCountyLevelTotalHoursOfFiveYears() {
		
		return aboveCountyLevelTotalHoursOfFiveYears;
	}

	
	public void setAboveCountyLevelTotalHoursOfFiveYears(Integer aboveCountyLevelTotalHoursOfFiveYears) {
		
		this.aboveCountyLevelTotalHoursOfFiveYears = aboveCountyLevelTotalHoursOfFiveYears;
	}

	
	public Integer getBelowCountyLevelTotalDaysOfEveryYear() {
		
		return belowCountyLevelTotalDaysOfEveryYear;
	}

	
	public void setBelowCountyLevelTotalDaysOfEveryYear(Integer belowCountyLevelTotalDaysOfEveryYear) {
		
		this.belowCountyLevelTotalDaysOfEveryYear = belowCountyLevelTotalDaysOfEveryYear;
	}

	
	public Integer getBelowCountyLevelTotalHoursOfEveryYear() {
		
		return belowCountyLevelTotalHoursOfEveryYear;
	}

	
	public void setBelowCountyLevelTotalHoursOfEveryYear(Integer belowCountyLevelTotalHoursOfEveryYear) {
		
		this.belowCountyLevelTotalHoursOfEveryYear = belowCountyLevelTotalHoursOfEveryYear;
	}
	
}

