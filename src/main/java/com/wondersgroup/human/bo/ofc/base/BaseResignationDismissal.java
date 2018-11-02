/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseResignationDismissal.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 辞职辞退情况
 * 创建人: jiang
 * 创建时间: 2018年9月10日15:47:52
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日15:47:56
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
 * @ClassName: BaseResignationDismissal
 * @Description: 国标 辞职辞退情况
 * @author: jiang
 * @date: 2018年9月10日15:48:00
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseResignationDismissal<T> extends GenericEntity {
	
	private static final long serialVersionUID = -3124206509647294631L;
	
	/**
	 * @fieldName: 辞去的职务
	 * @fieldType: java.lang.String
	 * @Description: 该人该次辞去或被辞的职务名称。
	 */
	@Column(name = "A51001", length = 80)
	private String offPost;
	
	/**
	 * @fieldName: 辞职原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人本次辞去职务的原因类别。DM009
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A51004")
	private CodeInfo resignationReason;
	
	/**
	 * @fieldName: 辞职去向
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人辞职后所去单位的性质。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A51005")
	private CodeInfo resignationWhereabout;
	
	/**
	 * @fieldName: 辞退原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该次被辞退的原因。DM068
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A51007")
	private CodeInfo dismissalReason;
	
	/**
	 * @fieldName: 辞去公职标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该次辞职是否同时辞去公职的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A51011")
	private CodeInfo resignationPublicOfficeFlag;
	
	/**
	 * @fieldName: 辞职（辞退）批准日期
	 * @fieldType: java.util.Date
	 * @Description: 具有法定管理权限的单位批准该人该次辞职（辞退）的日期。GB/T 7408-2005
	 */
	@Column(name = "A51014")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approvalDate;
	
	/**
	 * @fieldName: 引咎（责令）辞职时应付责任标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该次引咎（责令）辞职时应负的领导责任的标识。DM133
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A51017")
	private CodeInfo responsibilityFlag;
	
	/**
	 * @fieldName: 辞职辞退经济责任审计标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该次辞职或者被辞退公职、离职前，是否进行了经济责任审计的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A51021")
	private CodeInfo auditFlag;
	
	public String getOffPost() {
		
		return offPost;
	}
	
	public void setOffPost(String offPost) {
		
		this.offPost = offPost;
	}
	
	public CodeInfo getResignationReason() {
		
		return resignationReason;
	}
	
	public void setResignationReason(CodeInfo resignationReason) {
		
		this.resignationReason = resignationReason;
	}
	
	public CodeInfo getResignationWhereabout() {
		
		return resignationWhereabout;
	}
	
	public void setResignationWhereabout(CodeInfo resignationWhereabout) {
		
		this.resignationWhereabout = resignationWhereabout;
	}
	
	public CodeInfo getDismissalReason() {
		
		return dismissalReason;
	}
	
	public void setDismissalReason(CodeInfo dismissalReason) {
		
		this.dismissalReason = dismissalReason;
	}
	
	public CodeInfo getResignationPublicOfficeFlag() {
		
		return resignationPublicOfficeFlag;
	}
	
	public void setResignationPublicOfficeFlag(CodeInfo resignationPublicOfficeFlag) {
		
		this.resignationPublicOfficeFlag = resignationPublicOfficeFlag;
	}
	
	public Date getApprovalDate() {
		
		return approvalDate;
	}
	
	public void setApprovalDate(Date approvalDate) {
		
		this.approvalDate = approvalDate;
	}
	
	public CodeInfo getResponsibilityFlag() {
		
		return responsibilityFlag;
	}
	
	public void setResponsibilityFlag(CodeInfo responsibilityFlag) {
		
		this.responsibilityFlag = responsibilityFlag;
	}
	
	public CodeInfo getAuditFlag() {
		
		return auditFlag;
	}
	
	public void setAuditFlag(CodeInfo auditFlag) {
		
		this.auditFlag = auditFlag;
	}
	
}
