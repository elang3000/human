/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignPlan.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月19日 下午4:54:09 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月19日 下午4:54:09 
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/** 
 * @ClassName: ResignPlan 
 * @Description: 辞职批次
 * @author: lihao
 * @date: 2018年12月19日 下午4:54:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_RESIGN_PLAN")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ResignPlan  extends GenericEntity {

	private static final long serialVersionUID = 2153762865608358536L;
	
	// 待提交辞职信息
	@Transient
	public final static Integer RESIGN_EMPLOY_APPLYB = 1;
	// 待区人事主管部门确认
	@Transient
	public final static Integer RESIGN_EMPLOY_CONFIRMB = 2;
	// 待区人事主管部门打印
	@Transient
	public final static Integer RESIGN_EMPLOY_PRINTB = 3;
	// 区人事主管部门已备案
	@Transient
	public final static Integer RESIGN_EMPLOY_DONEB = 4;
	
	/**
	 * 权限代码map key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();

	static {
		power.put("RESIGN_EMPLOY_APPLYB", RESIGN_EMPLOY_APPLYB);
		power.put("RESIGN_EMPLOY_CONFIRMB", RESIGN_EMPLOY_CONFIRMB);
		power.put("RESIGN_EMPLOY_PRINTB", RESIGN_EMPLOY_PRINTB);
	}
	
	/**
	 * @fieldName: resingName
	 * @fieldType: java.lang.String
	 * @Description: 辞职批次名称
	 */
	@Column(name = "RESIGN_NAME", length = 180)
	private String resignName;
	
	/**
	 * @fieldName: resingNumber
	 * @fieldType: Integer
	 * @Description: 人数
	 */
	@Column(name = "RESIGN_NUMBER", length = 4)
	private Integer resignNumber;
	
	/**
	 * @fieldName: resignDate
	 * @fieldType: java.lang.String
	 * @Description: 辞职时间。
	 */
	@Column(name = "resign_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date resignDate;
	
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
	 * @fieldName: number
	 * @fieldType: java.lang.String
	 * @Description: 文件编号
	 */
	@Column(name = "RESIGN_FTP", length = 2000)
	private String resignFtp;
	
	/**
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 人员单位
	 */
	@OneToOne
	private OrganNode organ;

	/**
	 * @return the resignDate
	 */
	public Date getResignDate() {
		return resignDate;
	}

	/**
	 * @param resignDate the resignDate to set
	 */
	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
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
	 * @return the resignFtp
	 */
	public String getResignFtp() {
		return resignFtp;
	}

	/**
	 * @param resignFtp the resignFtp to set
	 */
	public void setResignFtp(String resignFtp) {
		this.resignFtp = resignFtp;
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

	/**
	 * @return the resignName
	 */
	public String getResignName() {
		return resignName;
	}

	/**
	 * @param resignName the resignName to set
	 */
	public void setResignName(String resignName) {
		this.resignName = resignName;
	}

	/**
	 * @return the resignNumber
	 */
	public Integer getResignNumber() {
		return resignNumber;
	}

	/**
	 * @param resignNumber the resignNumber to set
	 */
	public void setResignNumber(Integer resignNumber) {
		this.resignNumber = resignNumber;
	}
}
