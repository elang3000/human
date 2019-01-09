/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DiaoIntoMgr.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 调任流程 调入情况信息
 * 创建人: tzy
 * 创建时间: 2018年7月30日 下午1:58:20
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月30日 下午1:58:20
 * 修改任务号
 * 修改内容：
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

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;

/**
 * @ClassName: DiaoIntoMgr
 * @Description: 调任流程 调入情况信息
 * @author: tzy
 * @date: 2018年7月30日 下午1:58:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DIAOREN_INTO_C")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DiaoRenInto extends BaseEventInto<DiaoRenInto> {
	
	private static final long serialVersionUID = 2874248257427012623L;
	
	/**
	 * @fieldName: diaoRenIntoBatch
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DiaoRenIntoBatch
	 * @Description: 调任 批量数据表
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DIAORENINTOBATCH_ID")
	private DiaoRenIntoBatch diaoRenIntoBatch;
	
	/**
	 * @fieldName: diaoRenOut
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DiaoRenOut
	 * @Description: 转出情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DIAORENOUT_ID")
	private DiaoRenOut diaoRenOut;
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PERSON_TYPE")
	private CodeInfo personType;
	
	/**
	 * @fieldName: diaoRenOutMgr
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DiaoRenOutMgr
	 * @Description: 调出情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DIAORENOUTMGR_ID")
	private DiaoRenOutMgr diaoRenOutMgr;
	
	/**
	 * @fieldName: PublicInstitution
	 * @fieldType: com.wondersgroup.human.bo.pubinst.PublicInstitution
	 * @Description: 事业单位人员信息主表。
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUBLICINSTITUTION_ID")
	private PublicInstitution publicInstitution;
	
	/**
	 * @fieldName: NationalCompany
	 * @fieldType: com.wondersgroup.human.bo.company.NationalCompany
	 * @Description: 国企职工人员信息主表。
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NATIONALCOMPANY_ID")
	private NationalCompany nationalCompany;
	
	/**
	 * @fieldName: postCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 拟任职务代码,GB/T 12403-1990 该人担任职务的代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "POST_CODE")
	private CodeInfo postCode;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 原单位职级名称，用于显示。
	 */
	@Column(name = "SOURCE_REAL_JOBLEVEL_NAME", length = 80)
	private String sourceRealJobLevelName;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 拟任职务名称 ,该人担任职务的具体名称。
	 */
	@Column(name = "POST_NAME", length = 80)
	private String postName;
	
	/**
	 * @fieldName: attribute
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 拟任职务属性,DM049 担任领导职务或非领导职务的情况。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ATTRIBUTE")
	private CodeInfo attribute;
	
	/**
	 * @fieldName: 调入时间
	 * @fieldType: java.util.Date
	 * @Description: 调入时间。GB/T 7408-2005
	 */
	@Column(name = "INTODATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date intoDate;
	
	/**
	 * @fieldName: unitOpinion
	 * @fieldType: java.lang.String
	 * @Description: 单位意见。
	 */
	@Column(name = "UNITOPINION", length = 200)
	private String unitOpinion;
	
	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 备注。
	 */
	@Column(name = "REMARK", length = 200)
	private String remark;
	
	public DiaoRenIntoBatch getDiaoRenIntoBatch() {
		
		return diaoRenIntoBatch;
	}
	
	public void setDiaoRenIntoBatch(DiaoRenIntoBatch diaoRenIntoBatch) {
		
		this.diaoRenIntoBatch = diaoRenIntoBatch;
	}
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
	}
	
	public DiaoRenOutMgr getDiaoRenOutMgr() {
		
		return diaoRenOutMgr;
	}
	
	public void setDiaoRenOutMgr(DiaoRenOutMgr diaoRenOutMgr) {
		
		this.diaoRenOutMgr = diaoRenOutMgr;
	}
	
	public PublicInstitution getPublicInstitution() {
		
		return publicInstitution;
	}
	
	public void setPublicInstitution(PublicInstitution publicInstitution) {
		
		this.publicInstitution = publicInstitution;
	}
	
	public NationalCompany getNationalCompany() {
		
		return nationalCompany;
	}
	
	public void setNationalCompany(NationalCompany nationalCompany) {
		
		this.nationalCompany = nationalCompany;
	}
	
	public CodeInfo getPostCode() {
		
		return postCode;
	}
	
	public void setPostCode(CodeInfo postCode) {
		
		this.postCode = postCode;
	}
	
	public String getPostName() {
		
		return postName;
	}
	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}
	
	public CodeInfo getAttribute() {
		
		return attribute;
	}
	
	public void setAttribute(CodeInfo attribute) {
		
		this.attribute = attribute;
	}
	
	public Date getIntoDate() {
		
		return intoDate;
	}
	
	public void setIntoDate(Date intoDate) {
		
		this.intoDate = intoDate;
	}
	
	public String getUnitOpinion() {
		
		return unitOpinion;
	}
	
	public void setUnitOpinion(String unitOpinion) {
		
		this.unitOpinion = unitOpinion;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public DiaoRenOut getDiaoRenOut() {
		
		return diaoRenOut;
	}
	
	public void setDiaoRenOut(DiaoRenOut diaoRenOut) {
		
		this.diaoRenOut = diaoRenOut;
	}
	
	public String getSourceRealJobLevelName() {
		
		return sourceRealJobLevelName;
	}
	
	public void setSourceRealJobLevelName(String sourceRealJobLevelName) {
		
		this.sourceRealJobLevelName = sourceRealJobLevelName;
	}
}
