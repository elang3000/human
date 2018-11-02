/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseWorkJob.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 工作岗位
 * 创建人: jiang
 * 创建时间: 2018年9月10日14:49:24
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日14:49:26
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
 * @ClassName: BaseWorkJob
 * @Description: 国标 工作岗位
 * @author: jiang
 * @date: 2018年9月10日14:49:29
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseWorkJob<T> extends GenericEntity {
	
	private static final long serialVersionUID = 1386786949002753003L;
	
	/**
	 * @fieldName: 工作岗位开始日期
	 * @fieldType: java.util.Date
	 * @Description: 上岗时的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A48001")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	/**
	 * @fieldName: 工作岗位截止日期
	 * @fieldType: java.util.Date
	 * @Description: 该人该岗位的终止日期。GB/T 7408-2005
	 */
	@Column(name = "A48004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	/**
	 * @fieldName: 工作岗位
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 经组织、干部、人事、劳动等部门认定的该人该岗位的名称。DM127
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A48007")
	private CodeInfo workJob;
	
	/**
	 * @fieldName: 是否当前岗位标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该岗位是否为该人当前最高职务或者在编制、工资关系单位所任职务对应的岗位的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A48011")
	private CodeInfo currentJobFlag;
	
	/**
	 * @fieldName: 岗位变化分类
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 岗位变化的分类情况。DM070
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A48105")
	private CodeInfo jobChangeCategory;
	
	/**
	 * @fieldName: 上岗方式
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 上岗的形式，如：选拔或竞聘上岗。DM070
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A48110")
	private CodeInfo onTheJobType;
	
	/**
	 * @fieldName: 岗位专业要求
	 * @fieldType: java.lang.String
	 * @Description: 岗位对专业的要求。
	 */
	@Column(name = "A48116", length = 2000)
	private String professionalRequirement;
	
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
	
	public CodeInfo getWorkJob() {
		
		return workJob;
	}
	
	public void setWorkJob(CodeInfo workJob) {
		
		this.workJob = workJob;
	}
	
	public CodeInfo getCurrentJobFlag() {
		
		return currentJobFlag;
	}
	
	public void setCurrentJobFlag(CodeInfo currentJobFlag) {
		
		this.currentJobFlag = currentJobFlag;
	}
	
	public CodeInfo getJobChangeCategory() {
		
		return jobChangeCategory;
	}
	
	public void setJobChangeCategory(CodeInfo jobChangeCategory) {
		
		this.jobChangeCategory = jobChangeCategory;
	}
	
	public CodeInfo getOnTheJobType() {
		
		return onTheJobType;
	}
	
	public void setOnTheJobType(CodeInfo onTheJobType) {
		
		this.onTheJobType = onTheJobType;
	}
	
	public String getProfessionalRequirement() {
		
		return professionalRequirement;
	}
	
	public void setProfessionalRequirement(String professionalRequirement) {
		
		this.professionalRequirement = professionalRequirement;
	}
	
}
