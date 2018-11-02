/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseAssessment.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 考核
 * 创建人: jiang
 * 创建时间: 2018年6月26日09:12:05
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月26日09:12:12
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
 * @ClassName: BaseAssessment
 * @Description: 国标 考核
 * @author: jiang
 * @date: 2018年6月25日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseAssessment<T> extends GenericEntity {
	
	private static final long serialVersionUID = -3371520482047679359L;
	
	/**
	 * @fieldName: 考核类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 对该人考核的类别。DM017
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A15001")
	private CodeInfo category;
	
	/**
	 * @fieldName: 考核起始日期
	 * @fieldType: java.util.Date
	 * @Description: 对该人进行考核的时间段的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A15004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	/**
	 * @fieldName: 考核截止日期
	 * @fieldType: java.util.Date
	 * @Description: 由组织、干部、人事部门组派的考察组对干部进行考察（考核）的截止日期。GB/T 7408-2005
	 */
	@Column(name = "A15005")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	/**
	 * @fieldName: 考核组织名称
	 * @fieldType: java.lang.String
	 * @Description: 对该人进行考核的组织的名称。
	 */
	@Column(name = "A15007", length = 120)
	private String organizationName;
	
	
	
	/**
	 * @fieldName: 考核意见
	 * @fieldType: java.lang.String
	 * @Description: 对该人的德、能、勤、绩、廉等方面表现的评价意见。
	 */
	@Column(name = "A15011", length = 2000)
	private String opinion;
	
	/**
	 * @fieldName: 考核组织成员姓名
	 * @fieldType: java.lang.String
	 * @Description: 直接参与对该人考核的组织成员的姓名全称。
	 */
	@Column(name = "A15014", length = 50)
	private String allParticipantName;
	
	
	/**
	 * @fieldName: 考核结论类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 对该人考核的结论意见的分类。DM018
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A15017")
	private CodeInfo conclusionCategory;
	
	/**
	 * @fieldName: 考核年度
	 * @fieldType: java.util.Date
	 * @Description: 进行该次考核的年度。GB/T 7408-2005
	 */
	@Column(name = "A15021")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy")
	private Date assessmentYear;
	
	/**
	 * @fieldName: 未参加年度考核原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人未参加年度考核的原由。DM057
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A15030")
	private CodeInfo uncommittedReason;
	
	/**
	 * @fieldName: 年度考核不计考核等次或不称职（不合格）的年限
	 * @fieldType: java.lang.Integer
	 * @Description: 该人年度考核不计考核等次或不称职（不合格）的年限。
	 */
	@Column(name = "A15047", length = 2)
	private Integer unqualifiedYears;
	
	/**
	 * @fieldName: 参加考核不定等次的原因
	 * @fieldType: java.lang.String
	 * @Description: 该人参加考核但不定等次的原因的说明。
	 */
	@Column(name = "A15051", length = 2000)
	private String uncertainLvlReason;

	
	public CodeInfo getCategory() {
		
		return category;
	}

	
	public void setCategory(CodeInfo category) {
		
		this.category = category;
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

	
	public String getOrganizationName() {
		
		return organizationName;
	}

	
	public void setOrganizationName(String organizationName) {
		
		this.organizationName = organizationName;
	}

	
	public String getOpinion() {
		
		return opinion;
	}

	
	public void setOpinion(String opinion) {
		
		this.opinion = opinion;
	}

	
	public String getAllParticipantName() {
		
		return allParticipantName;
	}

	
	public void setAllParticipantName(String allParticipantName) {
		
		this.allParticipantName = allParticipantName;
	}

	
	public CodeInfo getConclusionCategory() {
		
		return conclusionCategory;
	}

	
	public void setConclusionCategory(CodeInfo conclusionCategory) {
		
		this.conclusionCategory = conclusionCategory;
	}

	
	public Date getAssessmentYear() {
		
		return assessmentYear;
	}

	
	public void setAssessmentYear(Date assessmentYear) {
		
		this.assessmentYear = assessmentYear;
	}

	
	public CodeInfo getUncommittedReason() {
		
		return uncommittedReason;
	}

	
	public void setUncommittedReason(CodeInfo uncommittedReason) {
		
		this.uncommittedReason = uncommittedReason;
	}

	
	public Integer getUnqualifiedYears() {
		
		return unqualifiedYears;
	}

	
	public void setUnqualifiedYears(Integer unqualifiedYears) {
		
		this.unqualifiedYears = unqualifiedYears;
	}

	
	public String getUncertainLvlReason() {
		
		return uncertainLvlReason;
	}

	
	public void setUncertainLvlReason(String uncertainLvlReason) {
		
		this.uncertainLvlReason = uncertainLvlReason;
	}
	
}
