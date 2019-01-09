/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PunishServant.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月21日 下午3:09:55 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月21日 下午3:09:55 
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
import com.wondersgroup.human.bo.ofc.Servant;

/** 
 * @ClassName: PunishServant 
 * @Description: 处分事务表
 * @author: lihao
 * @date: 2018年9月21日 下午3:09:55
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_PUNISH")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PunishServant extends GenericEntity {

	private static final long serialVersionUID = 370048502682652986L;
	
	// 提交死亡信息
	@Transient
	public final static Integer PUNISH_SERVANT_STEP1 = 1;
	// 区人事部门备案
	@Transient
	public final static Integer PUNISH_SERVANT_STEP2 = 2;
	//区人事主管部门备案通过
	@Transient
	public final static Integer PUNISH_SERVANT_PASS = 3;
	
	//处分未解除
	@Transient
	public final static Integer PUNISH_SIGN = 0;
	
	//处分解除
	@Transient
	public final static Integer PUNISH_SIGN_PASS = 1;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
		power.put("PUNISH_SERVANT_STEP1",PUNISH_SERVANT_STEP1);
		power.put("PUNISH_SERVANT_STEP2", PUNISH_SERVANT_STEP2);
	}

	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 人员单位
	 */
	@OneToOne
	private OrganNode organ;
	
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
	 * @fieldName: content
	 * @fieldType: java.lang.String
	 * @Description: 事项内容。
	 */
	@Column(name = "CONTENT", length = 2000)
	private String content;
	
	/**
	 * @fieldName: punishApprovalDate
	 * @fieldType: java.lang.String
	 * @Description: 处分批准时间。
	 */
	@Column(name = "PUNISH_APPROVAL_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date punishApprovalDate;
	
	/**
	 * @fieldName: punishApprovalDate
	 * @fieldType: java.lang.String
	 * @Description: 处分批准结束时间。
	 */
	@Column(name = "PUNISH_APPROVAL_END_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date punishApprovalEndDate;
	
	/**
	 * @fieldName: startDate
	 * @fieldType: java.lang.String
	 * @Description: 处分预警时间。
	 */
	@Column(name = "WARN_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date warnDate;
	
	/**
	 * @fieldName: punishYear
	 * @fieldType: Integer
	 * @Description: 处分期限（月）
	 */
	@JoinColumn(name = "PUNISH_YEAR")
	private Integer punishYear;
	
	/**
	 * @fieldName: punishCode
	 * @fieldType: java.lang.String
	 * @Description: 处分名称
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PUNISH_CODE")
	private CodeInfo punishCode;
	
	/**
	 * @fieldName: punishReason
	 * @fieldType: java.lang.String
	 * @Description: 处分原因（A14 A14317） DM021
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PUNISH_REASON")
	private CodeInfo punishReason;
	
	/**
	 * @fieldName: sign
	 * @fieldType: Integer
	 * @Description: 处分接触预警标识 0-未解除  1-已解除
	 */
	@JoinColumn(name = "PUNISH_SIGN")
	private Integer sign;
	
	/**
	 * @fieldName: punishFileName
	 * @fieldType: java.lang.String
	 * @Description: 处分文件号
	 */
	@Column(name = "PUNISH_FILE_NAME", length = 180)
	private String punishFileName;

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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the punishApprovalDate
	 */
	public Date getPunishApprovalDate() {
		return punishApprovalDate;
	}

	/**
	 * @param punishApprovalDate the punishApprovalDate to set
	 */
	public void setPunishApprovalDate(Date punishApprovalDate) {
		this.punishApprovalDate = punishApprovalDate;
	}

	/**
	 * @return the punishApprovalEndDate
	 */
	public Date getPunishApprovalEndDate() {
		return punishApprovalEndDate;
	}

	/**
	 * @param punishApprovalEndDate the punishApprovalEndDate to set
	 */
	public void setPunishApprovalEndDate(Date punishApprovalEndDate) {
		this.punishApprovalEndDate = punishApprovalEndDate;
	}

	/**
	 * @return the warnDate
	 */
	public Date getWarnDate() {
		return warnDate;
	}

	/**
	 * @param warnDate the warnDate to set
	 */
	public void setWarnDate(Date warnDate) {
		this.warnDate = warnDate;
	}

	/**
	 * @return the punishYear
	 */
	public Integer getPunishYear() {
		return punishYear;
	}

	/**
	 * @param punishYear the punishYear to set
	 */
	public void setPunishYear(Integer punishYear) {
		this.punishYear = punishYear;
	}

	/**
	 * @return the punishCode
	 */
	public CodeInfo getPunishCode() {
		return punishCode;
	}

	/**
	 * @param punishCode the punishCode to set
	 */
	public void setPunishCode(CodeInfo punishCode) {
		this.punishCode = punishCode;
	}

	/**
	 * @return the punishReason
	 */
	public CodeInfo getPunishReason() {
		return punishReason;
	}

	/**
	 * @param punishReason the punishReason to set
	 */
	public void setPunishReason(CodeInfo punishReason) {
		this.punishReason = punishReason;
	}

	/**
	 * @return the sign
	 */
	public Integer getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(Integer sign) {
		this.sign = sign;
	}

	/**
	 * @return the punishFileName
	 */
	public String getPunishFileName() {
		return punishFileName;
	}

	/**
	 * @param punishFileName the punishFileName to set
	 */
	public void setPunishFileName(String punishFileName) {
		this.punishFileName = punishFileName;
	}
}
