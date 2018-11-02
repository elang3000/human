/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignServant.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月20日 下午4:23:44 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月20日 下午4:23:44 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: ResignServant
 * @Description: 辞职信息
 * @author: lihao
 * @date: 2018年6月20日下午4:23:44 @version [版本号, YYYY-MM-DD] @see       [相关类/方法] @since     [产品/模块版本]
 */
@Entity(name = "HUMAN_RESIGN")
public class ResignServant extends GenericEntity {

	private static final long serialVersionUID = -5282255858407855679L;

	// 提交辞职信息
	@Transient
	public final static Integer RESIGN_EMPLOY_APPLY = 1;
	// 区人事部门备案
	@Transient
	public final static Integer RESIGN_EMPLOY_CONFIRM = 2;
	// 区人事主管部门备案通过
	@Transient
	public final static Integer DEATH_EMPLOY_DONE = 3;

	/**
	 * 权限代码map key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();

	static {
		power.put("RESIGN_EMPLOY_APPLY", RESIGN_EMPLOY_APPLY);
		power.put("RESIGN_EMPLOY_CONFIRM", RESIGN_EMPLOY_CONFIRM);
	}

	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;

	/**
	 * @fieldName: reason
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 辞职原因。该人该次被辞职的原因。DM009_5
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A51004")
	private CodeInfo reason;

	/**
	 * @fieldName: resignWhereabouts
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 辞职去向。该人辞职后所去单位的性质。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A51005")
	private CodeInfo resignWhereabouts;

	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 备注。
	 */
	@Column(name = "remark", length = 2000)
	private String remark;

	/**
	 * @fieldName: resignDate
	 * @fieldType: java.lang.String
	 * @Description: 发起辞职时间。
	 */
	@Column(name = "resign_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date resignDate;

	/**
	 * @fieldName: resignDate
	 * @fieldType: java.lang.String
	 * @Description: 转移时间。
	 */
	@Column(name = "A51014")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date approvalResignDate;

	/**
	 * @fieldName: unitName
	 * @fieldType: java.lang.String
	 * @Description: 辞职（辞退）人员所在单位。供给关系
	 */
	@Column(name = "UNIT_NAME", length = 180)
	private String unitName;

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
	@Column(name = "NUMBER", length = 180)
	private String number;
	
	/**
	 * @fieldName: unitName
	 * @fieldType: java.lang.String
	 * @Description: 辞去公职的批准通知书ftp
	 */
	@Column(name = "APPROVAL_NOTICE_FTP", length = 180)
	private String approvalNoticeFtp;
	
	/**
	 * @fieldName: unitName
	 * @fieldType: java.lang.String
	 * @Description: 辞去公职的批复ftp
	 */
	@Column(name = "REPLY_FTP", length = 180)
	private String replyFtp;
	
	/**
	 * @fieldName: noticeFtp
	 * @fieldType: java.lang.String
	 * @Description: 辞职公职的通知ftp
	 */
	@Column(name = "NOTICE_FTP", length = 180)
	private String noticeFtp;
	
	/**
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 人员单位
	 */
	@OneToOne
	private OrganNode organ;

	/**
	 * @return the servant
	 */
	public Servant getServant() {
		return servant;
	}

	/**
	 * @param servant
	 *            the servant to set
	 */
	public void setServant(Servant servant) {
		this.servant = servant;
	}

	/**
	 * @return the reason
	 */
	public CodeInfo getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(CodeInfo reason) {
		this.reason = reason;
	}

	/**
	 * @return the resignWhereabouts
	 */
	public CodeInfo getResignWhereabouts() {
		return resignWhereabouts;
	}

	/**
	 * @param resignWhereabouts
	 *            the resignWhereabouts to set
	 */
	public void setResignWhereabouts(CodeInfo resignWhereabouts) {
		this.resignWhereabouts = resignWhereabouts;
	}


	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the resignDate
	 */
	public Date getResignDate() {
		return resignDate;
	}

	/**
	 * @param resignDate
	 *            the resignDate to set
	 */
	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
	}

	/**
	 * @return the approvalResignDate
	 */
	public Date getApprovalResignDate() {
		return approvalResignDate;
	}

	/**
	 * @param approvalResignDate
	 *            the approvalResignDate to set
	 */
	public void setApprovalResignDate(Date approvalResignDate) {
		this.approvalResignDate = approvalResignDate;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName
	 *            the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the flowRecord
	 */
	public FlowRecord getFlowRecord() {
		return flowRecord;
	}

	/**
	 * @param flowRecord
	 *            the flowRecord to set
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
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the approvalNoticeFtp
	 */
	public String getApprovalNoticeFtp() {
		return approvalNoticeFtp;
	}

	/**
	 * @param approvalNoticeFtp the approvalNoticeFtp to set
	 */
	public void setApprovalNoticeFtp(String approvalNoticeFtp) {
		this.approvalNoticeFtp = approvalNoticeFtp;
	}

	/**
	 * @return the replyFtp
	 */
	public String getReplyFtp() {
		return replyFtp;
	}

	/**
	 * @param replyFtp the replyFtp to set
	 */
	public void setReplyFtp(String replyFtp) {
		this.replyFtp = replyFtp;
	}

	/**
	 * @return the noticeFtp
	 */
	public String getNoticeFtp() {
		return noticeFtp;
	}

	/**
	 * @param noticeFtp the noticeFtp to set
	 */
	public void setNoticeFtp(String noticeFtp) {
		this.noticeFtp = noticeFtp;
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
