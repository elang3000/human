/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPerson.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月12日 下午3:52:02 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月12日 下午3:52:02 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofc.Servant;

/** 
 * @ClassName: TrainPerson 
 * @Description: 个人培训信息
 * @author: lihao
 * @date: 2018年9月12日 下午3:52:02
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name="HUMAN_TRAIN_PERSON")
public class TrainPerson extends GenericEntity {

	private static final long serialVersionUID = 6223351005484668330L;
	
	//非出境培训code
	public final static String IS_ABROAD_CODE = "0";
	//全脱产培训code
	public final static String IS_NATURE_CODE = "1";
	//培训级别市级
	public final static String level1 = "1";
	//培训级别市级以上
	public final static String level2 = "4";
	//培训级别本单位
	public final static String level3 = "3";
	
	/**
	 * @fieldName: plan
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan
	 * @Description: 培训信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAIN_ID")
	private TrainPlan plan;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训学时
	 *
	 */
	@Column(name = "HOURS", nullable = false)
	private Integer hours;
	
	/**
	 * @fieldName: start
	 * @fieldType: java.lang.String
	 * @Description: 开始时间。
	 */
	@Column(name = "START_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	/**
	 * @fieldName: end
	 * @fieldType: java.lang.String
	 * @Description: 结束时间。
	 */
	@Column(name = "END_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训天数
	 *
	 */
	@Column(name = "DAYS", nullable = false)
	private Integer day;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 培训性质
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NATURE")
	private CodeInfo nature;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 培训组织单位级别
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEVEL")
	private CodeInfo level;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训经费（万元）
	 *
	 */
	@Column(name = "FUNDS", nullable = false)
	private Integer funds;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 是否境外培训
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IS_ABROAD")
	private CodeInfo isAbroad;
	
	/**
	 * @fieldName: dept
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 人员所在部门ID。
	 */
	@OneToOne
	@JoinColumn(name = "DEPT_ID")
	private OrganNode dept;

	/**
	 * @return the plan
	 */
	public TrainPlan getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(TrainPlan plan) {
		this.plan = plan;
	}

	/**
	 * @return the servant
	 */
	public Servant getServant() {
		return servant;
	}

	/**
	 * @param servant the servant to set
	 */
	public void setServant(Servant servant) {
		this.servant = servant;
	}

	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * @return the nature
	 */
	public CodeInfo getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(CodeInfo nature) {
		this.nature = nature;
	}

	/**
	 * @return the level
	 */
	public CodeInfo getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(CodeInfo level) {
		this.level = level;
	}

	/**
	 * @return the funds
	 */
	public Integer getFunds() {
		return funds;
	}

	/**
	 * @param funds the funds to set
	 */
	public void setFunds(Integer funds) {
		this.funds = funds;
	}

	/**
	 * @return the isAbroad
	 */
	public CodeInfo getIsAbroad() {
		return isAbroad;
	}

	/**
	 * @param isAbroad the isAbroad to set
	 */
	public void setIsAbroad(CodeInfo isAbroad) {
		this.isAbroad = isAbroad;
	}

	/**
	 * @return the dept
	 */
	public OrganNode getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(OrganNode dept) {
		this.dept = dept;
	}
}
