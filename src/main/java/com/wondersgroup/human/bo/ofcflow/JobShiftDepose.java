/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DraftServant.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 拟录用人员信息表
 * 创建人: GP
 * 创建时间: 2018年6月26日 上午9:47:22
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2018年6月26日 上午9:47:22
 * 修改任务号
 * 修改内容：
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
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: JobShiftDepose
 * @Description: 职务变动-免职-信息表
 * @author: youyd
 * @date: 2018年9月19日 上午9:47:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_SERVANT_JOBSHIFT_DEPOSE")
public class JobShiftDepose extends GenericEntity {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = -3044498544391892092L;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: Servant
	 * @Description: 免职人员
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT")
	private Servant servant;
	
	/**
	 * 免职的职位
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST")
	private Post post;
	
	/*
	 * 备注
	 */
	@Column(name = "REMARK", length = 240)
	private String remark;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/**
	 * @fieldName: sourceOrganNode
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 业务流发起组织部门
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private OrganNode sourceOrganNode;
	
	/**
	 * @fieldName: approvalNumber
	 * @fieldType: java.lang.String
	 * @Description:提名免职的单位名称,提名该人免职的具有法定管理权限的单位名称。
	 */
	@Column(name = "NOMINATION_DISMISSAL_NAME", length = 120)
	private String nominationDismissalName;
	
	/**
	 * @fieldName: nominationDismissalCode
	 * @fieldType: java.long.String
	 * @Description: 提名免职的单位代码,GB 32100-2015 提名该人免职的具有法定管理权限的单位代码。
	 */
	@Column(name = "NOMINATION_DISMISSAL_CODE")
	private String nominationDismissalCode;
	
	/**
	 * @fieldName: nominationDismissalDate
	 * @fieldType: java.util.Date
	 * @Description: 提出免职日期，GB/T 7408-2005 由具有法定管理权限的单位提名该人免职的日期。
	 */
	@Column(name = "NOMINATION_DISMISSAL_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date nominationDismissalDate;
	
	/**
	 * @fieldName: nominationDismissalNumber
	 * @fieldType: java.lang.String
	 * @Description:提出免职文号,决定该人免职的文件的编号全称。
	 */
	@Column(name = "NOMINATION_DISMISSAL_NUMBER", length = 48)
	private String nominationDismissalNumber;
	
	/**
	 * @fieldName: approvalDismissalName
	 * @fieldType: java.lang.String
	 * @Description:决定或批准免职的单位名称。
	 */
	@Column(name = "APPROVAL_DISMISSAL_NAME", length = 120)
	private String approvalDismissalName;
	
	/**
	 * @fieldName: approvalDismissalCode
	 * @fieldType: java.long.String
	 * @Description: 决定或批准该人免职的单位代码，GB 32100-2015。
	 */
	@Column(name = "APPROVAL_DISMISSAL_CODE")
	private String approvalDismissalCode;
	
	/**
	 * @fieldName: approvalDismissalDate
	 * @fieldType: java.util.Date
	 * @Description: 决定或批准免职的日期，GB/T 7408-2005 由具有法定管理权限的单位签发的文件确定的该人免职日期，或由会议决定、命令或公告发布该人免职的日期。
	 */
	@Column(name = "APPROVAL_DISMISSAL_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date approvalDismissalDate;
	
	/**
	 * @fieldName: approvalDismissalNumber
	 * @fieldType: java.lang.String
	 * @Description:决定或批准免职的文号，由具有法定管理权限的单位签发的有关该人的免职通知、会议决定、命令或公告免职的文件编号。
	 */
	@Column(name = "APPROVAL_DISMISSAL_NUMBER", length = 48)
	private String approvalDismissalNumber;
	
	/**
	 * @fieldName: dismissalType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 免职方式，DM008 免除干部职务的具体方式。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DISMISSAL_TYPE")
	private CodeInfo dismissalType;
	
	/**
	 * @fieldName: dismissalReason
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 免职原因类别，DM009 该人免职原因的类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DISMISSAL_REASON")
	private CodeInfo dismissalReason;
	
	/**
	 * @fieldName: dismissalChange
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 免职变动类别，DM006 该人免职变动的类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DISMISSAL_CHANGE")
	private CodeInfo dismissalChange;
	
	/**
	 * @fieldName: dismissalMode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 免职决定做出方式，DM163 对该人做出免职决定的方式。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DISMISSAL_MODE")
	private CodeInfo dismissalMode;

	
	public Servant getServant() {
		
		return servant;
	}

	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}

	
	public Post getPost() {
		
		return post;
	}

	
	public void setPost(Post post) {
		
		this.post = post;
	}

	
	public String getRemark() {
		
		return remark;
	}

	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}

	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}

	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}

	
	public OrganNode getSourceOrganNode() {
		
		return sourceOrganNode;
	}

	
	public void setSourceOrganNode(OrganNode sourceOrganNode) {
		
		this.sourceOrganNode = sourceOrganNode;
	}

	
	public String getNominationDismissalName() {
		
		return nominationDismissalName;
	}

	
	public void setNominationDismissalName(String nominationDismissalName) {
		
		this.nominationDismissalName = nominationDismissalName;
	}

	
	public String getNominationDismissalCode() {
		
		return nominationDismissalCode;
	}

	
	public void setNominationDismissalCode(String nominationDismissalCode) {
		
		this.nominationDismissalCode = nominationDismissalCode;
	}

	
	public Date getNominationDismissalDate() {
		
		return nominationDismissalDate;
	}

	
	public void setNominationDismissalDate(Date nominationDismissalDate) {
		
		this.nominationDismissalDate = nominationDismissalDate;
	}

	
	public String getNominationDismissalNumber() {
		
		return nominationDismissalNumber;
	}

	
	public void setNominationDismissalNumber(String nominationDismissalNumber) {
		
		this.nominationDismissalNumber = nominationDismissalNumber;
	}

	
	public String getApprovalDismissalName() {
		
		return approvalDismissalName;
	}

	
	public void setApprovalDismissalName(String approvalDismissalName) {
		
		this.approvalDismissalName = approvalDismissalName;
	}

	
	public String getApprovalDismissalCode() {
		
		return approvalDismissalCode;
	}

	
	public void setApprovalDismissalCode(String approvalDismissalCode) {
		
		this.approvalDismissalCode = approvalDismissalCode;
	}

	
	public Date getApprovalDismissalDate() {
		
		return approvalDismissalDate;
	}

	
	public void setApprovalDismissalDate(Date approvalDismissalDate) {
		
		this.approvalDismissalDate = approvalDismissalDate;
	}

	
	public String getApprovalDismissalNumber() {
		
		return approvalDismissalNumber;
	}

	
	public void setApprovalDismissalNumber(String approvalDismissalNumber) {
		
		this.approvalDismissalNumber = approvalDismissalNumber;
	}

	
	public CodeInfo getDismissalType() {
		
		return dismissalType;
	}

	
	public void setDismissalType(CodeInfo dismissalType) {
		
		this.dismissalType = dismissalType;
	}

	
	public CodeInfo getDismissalReason() {
		
		return dismissalReason;
	}

	
	public void setDismissalReason(CodeInfo dismissalReason) {
		
		this.dismissalReason = dismissalReason;
	}

	
	public CodeInfo getDismissalChange() {
		
		return dismissalChange;
	}

	
	public void setDismissalChange(CodeInfo dismissalChange) {
		
		this.dismissalChange = dismissalChange;
	}

	
	public CodeInfo getDismissalMode() {
		
		return dismissalMode;
	}

	
	public void setDismissalMode(CodeInfo dismissalMode) {
		
		this.dismissalMode = dismissalMode;
	}
	
	

}
