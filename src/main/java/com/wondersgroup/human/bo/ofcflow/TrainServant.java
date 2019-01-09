/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainServant.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月12日 下午4:47:07 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月12日 下午4:47:07 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/** 
 * @ClassName: TrainServant 
 * @Description: 培训bo
 * @author: lihao
 * @date: 2018年11月12日 下午4:47:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

@Entity
@Table(name="HUMAN_TRAIN")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainServant extends GenericEntity {

	private static final long serialVersionUID = 1772354336315657605L;

	// 待提交培训计划
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_STEP1 = 1;
	// 待上级单位初审
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_STEP2 = 2;
	
	// 待区人事主管部门一级初审
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_STEP3 = 3;
	
	// 待区人事主管部门二级初审
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_STEP4 = 4;
	
	// 待区人事主管部门三级初审
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_STEP5 = 5;
	
	// 待区人事主管部门四级初审
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_STEP6 = 6;
	
	// 组织部复审
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_STEP7 = 7;
		
	// 区人事管理部门确认并统计学时表和人员统计表
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_STEP8 = 8;
	
	// 备案通过
	@Transient
	public final static Integer STATUS_TRAIN_PLAN_PASS = 9;
	
	// 非人社局录入
	@Transient
	public final static Integer TRAIN_NOT_BY_RS = 0;
	
	// 人社局录入
	@Transient
	public final static Integer TRAIN_BY_RS = 1;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
		//非人社局流程
		power.put("STATUS_TRAIN_PLAN_STEP1",STATUS_TRAIN_PLAN_STEP1);
		power.put("STATUS_TRAIN_PLAN_STEP2",STATUS_TRAIN_PLAN_STEP2);
		power.put("STATUS_TRAIN_PLAN_STEP3",STATUS_TRAIN_PLAN_STEP3);
		power.put("STATUS_TRAIN_PLAN_STEP4",STATUS_TRAIN_PLAN_STEP4);
		power.put("STATUS_TRAIN_PLAN_STEP5",STATUS_TRAIN_PLAN_STEP5);
		power.put("STATUS_TRAIN_PLAN_STEP6",STATUS_TRAIN_PLAN_STEP6);
		power.put("STATUS_TRAIN_PLAN_STEP7",STATUS_TRAIN_PLAN_STEP7);
		power.put("STATUS_TRAIN_PLAN_STEP8",STATUS_TRAIN_PLAN_STEP8);
		power.put("STATUS_TRAIN_PLAN_PASS",STATUS_TRAIN_PLAN_PASS);
		
		//人社局流程
		power.put("STATUS_TRAIN_PLAN_BY_HUMAN_STEP1",STATUS_TRAIN_PLAN_STEP1);
		power.put("STATUS_TRAIN_PLAN_BY_HUMAN_STEP2",STATUS_TRAIN_PLAN_STEP8);
		power.put("STATUS_TRAIN_PLAN_PASS",STATUS_TRAIN_PLAN_PASS);
		
	}
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;

	/**
	 * @fieldName: status
	 * @fieldType: Integer
	 * @Description: 状态
	 */
	@JoinColumn(name = "STATUS")
	private Integer status;
	
	/**
	 * @fieldName: type
	 * @fieldType: Integer
	 * @Description: 类型（非人社局录入：0；人社局录入：1）
	 */
	@JoinColumn(name = "TYPE")
	private Integer type;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 培训类别   DM040
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDY_TYPE")
	private CodeInfo studyType;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 培训性质   0114
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NATURE")
	private CodeInfo nature;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 培训组织单位级别  TRAIN_LEVEL
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEVEL")
	private CodeInfo level;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 出国（境）学习（培训）标识  DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IS_ABROAD")
	private CodeInfo isAbroad;
	
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
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 主办单位
	 */
	@OneToOne
	private OrganNode hostOrgan;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: String
	 * @Description: 培训名
	 *
	 */
	@Column(name = "TRAIN_NAME", length = 255)
	private String trainName;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: String
	 * @Description: 培训机构名
	 *
	 */
	@Column(name = "TRAIN_ORGAN_NAME", length = 255)
	private String trainOrganName;
	
	/**
	 * @fieldName: undergraduateCourse
	 * @fieldType: CodeInfo
	 * @Description: 培训机构类别   DM022
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAIN_ORGAN_TYPE")
	private CodeInfo trainOrganType;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训学时
	 *
	 */
	@Column(name = "HOURS")
	private Integer hours;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训时长
	 *
	 */
	@Column(name = "DAYS")
	private Integer days;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: String
	 * @Description: 培训班名
	 *
	 */
	@Column(name = "TRAIN_CLASS_NAME", length = 255)
	private String trainClassName;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训经费（万元）
	 *
	 */
	@Column(name = "FUNDS")
	private Double funds;
	
	/**
	 * @return the funds
	 */
	public Double getFunds() {
		return funds;
	}

	/**
	 * @param funds the funds to set
	 */
	public void setFunds(Double funds) {
		this.funds = funds;
	}

	/**
	 * @return the flowRecord
	 */
	public FlowRecord getFlowRecord() {
		return flowRecord;
	}

	/**
	 * @param flowRecord the flowRecord to set
	 */
	public void setFlowRecord(FlowRecord flowRecord) {
		this.flowRecord = flowRecord;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the studyType
	 */
	public CodeInfo getStudyType() {
		return studyType;
	}

	/**
	 * @param studyType the studyType to set
	 */
	public void setStudyType(CodeInfo studyType) {
		this.studyType = studyType;
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
	 * @return the hostOrgan
	 */
	public OrganNode getHostOrgan() {
		return hostOrgan;
	}

	/**
	 * @param hostOrgan the hostOrgan to set
	 */
	public void setHostOrgan(OrganNode hostOrgan) {
		this.hostOrgan = hostOrgan;
	}

	/**
	 * @return the trainName
	 */
	public String getTrainName() {
		return trainName;
	}

	/**
	 * @param trainName the trainName to set
	 */
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	/**
	 * @return the trainOrganName
	 */
	public String getTrainOrganName() {
		return trainOrganName;
	}

	/**
	 * @param trainOrganName the trainOrganName to set
	 */
	public void setTrainOrganName(String trainOrganName) {
		this.trainOrganName = trainOrganName;
	}

	/**
	 * @return the trainOrganType
	 */
	public CodeInfo getTrainOrganType() {
		return trainOrganType;
	}

	/**
	 * @param trainOrganType the trainOrganType to set
	 */
	public void setTrainOrganType(CodeInfo trainOrganType) {
		this.trainOrganType = trainOrganType;
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
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(Integer days) {
		this.days = days;
	}

	/**
	 * @return the trainClassName
	 */
	public String getTrainClassName() {
		return trainClassName;
	}

	/**
	 * @param trainClassName the trainClassName to set
	 */
	public void setTrainClassName(String trainClassName) {
		this.trainClassName = trainClassName;
	}
}
