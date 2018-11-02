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
import java.util.HashMap;
import java.util.Map;

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

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
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
@Table(name = "HUMAN_DIAOREN_INTO")
public class DiaoRenIntoMgr extends BaseEventIntoMgr<DiaoRenIntoMgr> {
	
	private static final long serialVersionUID = 2874248257427045623L;
	
	/**
	 * 本区
	 */
	public static final String AREA_THIS = "1";
	
	/**
	 * 外区
	 */
	public static final String AREA_OUTER = "2";
	
	/**
	 * 事业人员PublicInstitution
	 */
	public static final String SOURCE_TYPE_1 = "1";
	
	/**
	 * 国企职工NationalCompany
	 */
	public static final String SOURCE_TYPE_2 = "2";
	
	/**
	 * @fieldName: areaType
	 * @fieldType: java.lang.String
	 * @Description: 转任地区：1-本区 2-外区。
	 */
	@Column(name = "AREATYPE", length = 1)
	private String areaType;
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PERSON_TYPE")
	private CodeInfo personType;
	
	/**
	 * @fieldName: sourceType
	 * @fieldType: java.lang.String
	 * @Description: 人员来源类型：1-事业人员PublicInstitution 2-国企职工NationalCompany。
	 */
	@Column(name = "SOURCETYPE", length = 1)
	private String sourceType;
	
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
	 * @fieldName: SourceOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 原单位信息
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOURCEORGAN_ID")
	private OrganNode sourceOrgan;
	
	/**
	 * @fieldName: TargetOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 调入所在单位信息
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGETORGAN_ID")
	private OrganNode TargetOrgan;
	
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
	
	/***************************************************************************
	 * 流程相关属性
	 **************************************************************************/
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态。
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/**
	 * 待提出调任申请
	 */
	public static final int STATUS_DIAOREN_STATE = 0;
	
	/**
	 * 待上级单位审核
	 */
	public static final int STATUS_DIAOREN_TRIAL = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_DIAOREN_TRIAL_1 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_DIAOREN_TRIAL_2 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_DIAOREN_TRIAL_3 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_DIAOREN_TRIAL_4 = 5;
	
	/**
	 * 待调出单位同意
	 */
	public static final int STATUS_DIAOREN_AGREE = 6;
	
	/**
	 * 待区人事主管部门打印电子介绍信
	 */
	public static final int STATUS_DIAOREN_PRINT = 7;
	
	/**
	 * 人员信息已入库
	 */
	public static final int STATUS_DIAOREN_FINISH = 8;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		power.put("STATUS_DIAOREN_STATE", STATUS_DIAOREN_STATE);
		power.put("STATUS_DIAOREN_TRIAL", STATUS_DIAOREN_TRIAL);
		power.put("STATUS_DIAOREN_TRIAL_1", STATUS_DIAOREN_TRIAL_1);
		power.put("STATUS_DIAOREN_TRIAL_2", STATUS_DIAOREN_TRIAL_2);
		power.put("STATUS_DIAOREN_TRIAL_3", STATUS_DIAOREN_TRIAL_3);
		power.put("STATUS_DIAOREN_TRIAL_4", STATUS_DIAOREN_TRIAL_4);
		power.put("STATUS_DIAOREN_AGREE", STATUS_DIAOREN_AGREE);
		power.put("STATUS_DIAOREN_PRINT", STATUS_DIAOREN_PRINT);
		
		power.put("STATUS_DIAOREN_OUTER_STATE", STATUS_DIAOREN_STATE);
		power.put("STATUS_DIAOREN_OUTER_TRIAL", STATUS_DIAOREN_TRIAL);
		power.put("STATUS_DIAOREN_OUTER_TRIAL_1", STATUS_DIAOREN_TRIAL_1);
		power.put("STATUS_DIAOREN_OUTER_TRIAL_2", STATUS_DIAOREN_TRIAL_2);
		power.put("STATUS_DIAOREN_OUTER_TRIAL_3", STATUS_DIAOREN_TRIAL_3);
		power.put("STATUS_DIAOREN_OUTER_TRIAL_4", STATUS_DIAOREN_TRIAL_4);
		power.put("STATUS_DIAOREN_OUTER_PRINT", STATUS_DIAOREN_PRINT);
	}
	
	public String getAreaType() {
		
		return areaType;
	}
	
	public void setAreaType(String areaType) {
		
		this.areaType = areaType;
	}
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
	}
	
	public String getSourceType() {
		
		return sourceType;
	}
	
	public void setSourceType(String sourceType) {
		
		this.sourceType = sourceType;
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
	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public OrganNode getTargetOrgan() {
		
		return TargetOrgan;
	}
	
	public void setTargetOrgan(OrganNode targetOrgan) {
		
		TargetOrgan = targetOrgan;
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
	
	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}
	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}
	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}
	
	public DiaoRenOutMgr getDiaoRenOutMgr() {
		
		return diaoRenOutMgr;
	}
	
	public void setDiaoRenOutMgr(DiaoRenOutMgr diaoRenOutMgr) {
		
		this.diaoRenOutMgr = diaoRenOutMgr;
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
	
}
