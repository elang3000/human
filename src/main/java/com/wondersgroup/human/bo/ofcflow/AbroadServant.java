/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadServant.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月10日 上午9:36:30 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月10日 上午9:36:30 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/** 
 * @ClassName: AbroadServant 
 * @Description: 各单位因公出国
 * @author: lihao
 * @date: 2018年12月10日 上午9:36:30
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_ABROAD")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AbroadServant extends GenericEntity {

	private static final long serialVersionUID = -3111701504677235141L;

	// 待提交因公出国计划
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_STEP1 = 1;
	// 待上级单位初审
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_STEP2 = 2;
	
	// 待区人事主管部门一级初审
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_STEP3 = 3;
	
	// 待区人事主管部门二级初审
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_STEP4 = 4;
	
	// 待区人事主管部门三级初审
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_STEP5 = 5;
	
	// 待区人事主管部门四级初审
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_STEP6 = 6;
	
	// 组织部复审
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_STEP7 = 7;
		
	// 区人事管理部门确认
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_STEP8 = 8;
	
	// 备案通过
	@Transient
	public final static Integer STATUS_ABROAD_PLAN_PASS = 9;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
		power.put("STATUS_ABROAD_PLAN_STEP1",STATUS_ABROAD_PLAN_STEP1);
		power.put("STATUS_ABROAD_PLAN_STEP2",STATUS_ABROAD_PLAN_STEP2);
		power.put("STATUS_ABROAD_PLAN_STEP3",STATUS_ABROAD_PLAN_STEP3);
		power.put("STATUS_ABROAD_PLAN_STEP4",STATUS_ABROAD_PLAN_STEP4);
		power.put("STATUS_ABROAD_PLAN_STEP5",STATUS_ABROAD_PLAN_STEP5);
		power.put("STATUS_ABROAD_PLAN_STEP6",STATUS_ABROAD_PLAN_STEP6);
		power.put("STATUS_ABROAD_PLAN_STEP7",STATUS_ABROAD_PLAN_STEP7);
		power.put("STATUS_ABROAD_PLAN_STEP8",STATUS_ABROAD_PLAN_STEP8);
		power.put("STATUS_ABROAD_PLAN_PASS",STATUS_ABROAD_PLAN_PASS);
		
	}
	
	/**
	 * @fieldName: yearPlan
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.AbroadYearPlan
	 * @Description: 出国信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ABROAD_YEAR_ID")
	private AbroadYearPlan yearPlan;
	
	/**
	 * @fieldName: unitName
	 * @fieldType: java.lang.String
	 * @Description: 电子表格FTP地址
	 */
	@Column(name = "ABROAD_FTP", length = 2000)
	private String abroadFtp;
	
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
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 人员单位
	 */
	@OneToOne
	private OrganNode organ;
	
	/**
	 * @return the yearPlan
	 */
	public AbroadYearPlan getYearPlan() {
		return yearPlan;
	}

	/**
	 * @param yearPlan the yearPlan to set
	 */
	public void setYearPlan(AbroadYearPlan yearPlan) {
		this.yearPlan = yearPlan;
	}

	/**
	 * @return the abroadFtp
	 */
	public String getAbroadFtp() {
		return abroadFtp;
	}

	/**
	 * @param abroadFtp the abroadFtp to set
	 */
	public void setAbroadFtp(String abroadFtp) {
		this.abroadFtp = abroadFtp;
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
	 * @return the organ
	 */
	public OrganNode getOrgan() {
		return organ;
	}

	/**
	 * @param organ the organ to set
	 */
	public void setOrgan(OrganNode organ) {
		this.organ = organ;
	}
}
