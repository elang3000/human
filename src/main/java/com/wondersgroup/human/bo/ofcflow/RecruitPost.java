/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RecruitPost.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 职务简章-职位信息
 * 创建人: wangzhanfei
 * 创建时间: 2018年5月28日 上午10:23:51
 * 版本号: V1.0
 * 修改人：wangzhanfei
 * 修改时间：2018年5月28日 上午10:23:51
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: RecruitPost
 * @Description: 职务简章-职位信息
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午10:23:51
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_RECRUIT_POST")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RecruitPost extends GenericEntity {
	
	private static final long serialVersionUID = 8542437761364926131L;
	
	/**
	 * @fieldName: plan
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan
	 * @Description: 招录计划信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID")
	private RecruitEmployPlan plan;
	
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 职位名称
	 */
	@Column(name = "NAME", length = 255, nullable = false)
	private String name;
	
	/**
	 * @fieldName: description
	 * @fieldType: String
	 * @Description: 职位简介
	 */
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;
	
	/**
	 * @fieldName: planEmployNum
	 * @fieldType: Integer
	 * @Description: 招录人数
	 */
	@Column(name = "PLAN_EMPLOY_NUM")
	private Integer planEmployNum;
	
	/**
	 * @fieldName: objectType
	 * @fieldType: CodeInfo
	 * @Description: 招考对象
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private CodeInfo objectType;
	
	/**
	 * @fieldName: workYear
	 * @fieldType: CodeInfo
	 * @Description: 最低工作年限
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private CodeInfo workYear;
	
	/**
	 * @fieldName: education
	 * @fieldType: CodeInfo
	 * @Description: 学历要求
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private CodeInfo education;
	
	/**
	 * @fieldName: degree
	 * @fieldType: CodeInfo
	 * @Description: 学位要求
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private CodeInfo degree;
	
	/**
	 * @fieldName: politicalStatus
	 * @fieldType: CodeInfo
	 * @Description: 政治面貌
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private CodeInfo politicalStatus;
	
	/**
	 * @fieldName: other
	 * @fieldType: String
	 * @Description: 其他条件
	 */
	@Column(name = "OTHER", length = 2000)
	private String other;
	
	/**
	 * @fieldName: majorSubject
	 * @fieldType: String
	 * @Description: 专业考试科目
	 */
	@Column(name = "MAJORSUBJECT", length = 100)
	private String majorSubject;
	
	/**
	 * @fieldName: remark
	 * @fieldType: String
	 * @Description: 备注
	 */
	@Column(name = "REMARK", length = 2000)
	private String remark;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 本科阶段专业
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNDERGRADUATECOURSE")
	private CodeInfo undergraduateCourse;
	
	/**
	 * @fieldName: graduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 研究生阶段专业
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRADUATECOURSE")
	private CodeInfo graduateCourse;
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	public Integer getPlanEmployNum() {
		
		return planEmployNum;
	}
	
	public void setPlanEmployNum(Integer planEmployNum) {
		
		this.planEmployNum = planEmployNum;
	}
	
	public CodeInfo getObjectType() {
		
		return objectType;
	}
	
	public void setObjectType(CodeInfo objectType) {
		
		this.objectType = objectType;
	}
	
	public CodeInfo getWorkYear() {
		
		return workYear;
	}
	
	public void setWorkYear(CodeInfo workYear) {
		
		this.workYear = workYear;
	}
	
	public CodeInfo getEducation() {
		
		return education;
	}
	
	public void setEducation(CodeInfo education) {
		
		this.education = education;
	}
	
	public CodeInfo getDegree() {
		
		return degree;
	}
	
	public void setDegree(CodeInfo degree) {
		
		this.degree = degree;
	}
	
	public CodeInfo getPoliticalStatus() {
		
		return politicalStatus;
	}
	
	public void setPoliticalStatus(CodeInfo politicalStatus) {
		
		this.politicalStatus = politicalStatus;
	}
	
	public String getOther() {
		
		return other;
	}
	
	public void setOther(String other) {
		
		this.other = other;
	}
	
	public String getMajorSubject() {
		
		return majorSubject;
	}
	
	public void setMajorSubject(String majorSubject) {
		
		this.majorSubject = majorSubject;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public RecruitEmployPlan getPlan() {
		
		return plan;
	}
	
	public void setPlan(RecruitEmployPlan plan) {
		
		this.plan = plan;
	}
	
	public CodeInfo getUndergraduateCourse() {
		
		return undergraduateCourse;
	}
	
	public void setUndergraduateCourse(CodeInfo undergraduateCourse) {
		
		this.undergraduateCourse = undergraduateCourse;
	}
	
	public CodeInfo getGraduateCourse() {
		
		return graduateCourse;
	}
	
	public void setGraduateCourse(CodeInfo graduateCourse) {
		
		this.graduateCourse = graduateCourse;
	}
	
}
