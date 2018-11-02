/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPlan.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月12日 下午3:37:03 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月12日 下午3:37:03 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/** 
 * @ClassName: TrainPlan 
 * @Description: 培训项目内容
 * @author: lihao
 * @date: 2018年9月12日 下午3:37:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

@Entity
@Table(name="HUMAN_TRAIN_PLAN")
public class TrainPlan extends GenericEntity {
	
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
	

	private static final long serialVersionUID = 2017915373158690599L;
	
	/**
	 * @fieldName: yearPlan
	 * @fieldType: RecruitYearPlan
	 * @Description: 年度招聘计划
	 */
	@OneToOne
	private TrainYearPlan yearPlan;
	
	/**
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 培训机关
	 */
	@OneToOne
	private OrganNode trainOrgan;
	
	/**
	 * @fieldName: employOrgan
	 * @fieldType: OrganNode
	 * @Description: 录入机关
	 */
	@OneToOne
	private OrganNode inputOrgan;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: String
	 * @Description: 培训名
	 *
	 */
	@Column(name = "TRAIN_NAME", length = 255, nullable = false)
	private String trainName;

	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训学时
	 *
	 */
	@Column(name = "HOURS", nullable = false)
	private Integer hours;
	
	/**
	 * @fieldName: description
	 * @fieldType: String
	 * @Description: 培训描述
	 *
	 */
	@Column(name = "DESCRIPTION", length = 2000, nullable = false)
	private String description;
	
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
	 * @fieldName: trainPerson
	 * @fieldType: Set<RecruitPost>
	 * @Description: 个人培训信息
	 */
	@OneToMany(cascade = {
	        CascadeType.ALL
	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAIN_ID")
	private Set<TrainPerson> trainPerson = new HashSet<TrainPerson>();
	
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
	@JoinColumn(name = "status")
	private Integer status;
	
	/**
	 * @return the trainPerson
	 */
	public Set<TrainPerson> getTrainPerson() {
		return trainPerson;
	}

	/**
	 * @param trainPerson the trainPerson to set
	 */
	public void setTrainPerson(Set<TrainPerson> trainPerson) {
		this.trainPerson = trainPerson;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the yearPlan
	 */
	public TrainYearPlan getYearPlan() {
		return yearPlan;
	}

	/**
	 * @param yearPlan the yearPlan to set
	 */
	public void setYearPlan(TrainYearPlan yearPlan) {
		this.yearPlan = yearPlan;
	}

	/**
	 * @return the trainOrgan
	 */
	public OrganNode getTrainOrgan() {
		return trainOrgan;
	}

	/**
	 * @param trainOrgan the trainOrgan to set
	 */
	public void setTrainOrgan(OrganNode trainOrgan) {
		this.trainOrgan = trainOrgan;
	}

	/**
	 * @return the inputOrgan
	 */
	public OrganNode getInputOrgan() {
		return inputOrgan;
	}

	/**
	 * @param inputOrgan the inputOrgan to set
	 */
	public void setInputOrgan(OrganNode inputOrgan) {
		this.inputOrgan = inputOrgan;
	}
}
