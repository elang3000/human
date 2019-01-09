/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignPeople.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月19日 下午4:59:27 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月19日 下午4:59:27 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofc.Servant;

/** 
 * @ClassName: ResignPeople 
 * @Description: 辞职人员
 * @author: lihao
 * @date: 2018年12月19日 下午4:59:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_RESIGN_PEOPLE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ResignPeople extends GenericEntity {

	private static final long serialVersionUID = 9141518092303357497L;

	/**
	 * @fieldName: yearPlan
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.AbroadYearPlan
	 * @Description: 出国信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESIGN_PLAN_ID")
	private ResignPlan plan;
	
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
	@JoinColumn(name = "REASON")
	private CodeInfo reason;

	/**
	 * @fieldName: resignWhereabouts
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 辞职去向。该人辞职后所去单位的性质。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "RESIGN_WHERE_ABOUTS")
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
	 * @Description: 辞职时间。
	 */
	@Column(name = "resign_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date resignDate;
	
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
	 * @param servant the servant to set
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
	 * @param reason the reason to set
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
	 * @param resignWhereabouts the resignWhereabouts to set
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
	 * @param remark the remark to set
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
	 * @param resignDate the resignDate to set
	 */
	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
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

	/**
	 * @return the plan
	 */
	public ResignPlan getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(ResignPlan plan) {
		this.plan = plan;
	}
}
