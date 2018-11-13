/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DiaoRenOutMgr.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 调任流程 调出情况信息
 * 创建人: tzy
 * 创建时间: 2018年7月30日 下午2:01:43
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月30日 下午2:01:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/**
 * @ClassName: DiaoRenOutMgr
 * @Description: 调任流程 调出情况信息
 * @author: tzy
 * @date: 2018年7月30日 下午2:01:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DIAOREN_OUT")
public class DiaoRenOutMgr extends BaseEventOutMgr<DiaoRenOutMgr> {
	
	private static final long serialVersionUID = -9008077326672614331L;
	
	/**
	 * 本区
	 */
	public static final String AREA_THIS = "1";
	
	/**
	 * 外区
	 */
	public static final String AREA_OUTER = "2";
	
	/**
	 * @fieldName: areaType
	 * @fieldType: java.lang.String
	 * @Description: 调任地区：1-本区 2-外区。
	 */
	@Column(name = "AREATYPE", length = 1)
	private String areaType;
	
	/**
	 * @fieldName: targetType
	 * @fieldType: java.lang.String
	 * @Description: 人员去向类型：1-事业人员PublicInstitution 2-国企职工NationalCompany。
	 */
	@Column(name = "TARGETTYPE", length = 1)
	private String targetType;
	
	/**
	 * @fieldName: diaoRenIntoMgr
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DiaoRenIntoMgr
	 * @Description: 调入情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DIAORENINTOMGR_ID")
	private DiaoRenIntoMgr diaoRenIntoMgr;
	
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
	 * @Description: 调往单位信息
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGETORGAN_ID")
	private OrganNode targetOrgan;
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PERSON_TYPE")
	private CodeInfo personType;
	
	/**
	 * @fieldName: isLowToHigh
	 * @fieldType: java.lang.Boolean
	 * @Description: 职务是否高职低配。
	 */
	@Column(name = "IS_LOW_TO_HIGH")
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean isLowToHigh = false;
	
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
	 * @fieldName: 职级名称
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级名称。
	 */
	@Column(name = "JOBLEVEL_NAME")
	private String jobLevelName;
	
	/**
	 * @fieldName: 职级代码
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级代码。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOBLEVEL_CODE")
	private CodeInfo jobLevelCode;
	
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
	public static final int STATUS_DIAOCHU_STATE = 0;
	
	/**
	 * 待上级单位审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL_1 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL_2 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL_3 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL_4 = 5;
	
	/**
	 * 待调入单位确认
	 */
	public static final int STATUS_DIAOCHU_AGREE = 6;
	
	/**
	 * 人员信息已入库
	 */
	public static final int STATUS_DIAOCHU_FINISH = 7;
	
	// 调出到外区，只需备案
	/**
	 * 待提出调任申请
	 */
	public static final int STATUS_DIAOCHU_STATE_OUTER = 10;
	
	/**
	 * 待调出单位备案
	 */
	public static final int STATUS_DIAOCHU_CONFIRM_OUTER = 11;
	
	/**
	 * 人员调出信息已备案
	 */
	public static final int STATUS_DIAOCHU_FINISH_OUTER = 12;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		power.put("STATUS_DIAOCHU_STATE", STATUS_DIAOCHU_STATE);
		power.put("STATUS_DIAOCHU_TRIAL", STATUS_DIAOCHU_TRIAL);
		power.put("STATUS_DIAOCHU_TRIAL_1", STATUS_DIAOCHU_TRIAL_1);
		power.put("STATUS_DIAOCHU_TRIAL_2", STATUS_DIAOCHU_TRIAL_2);
		power.put("STATUS_DIAOCHU_TRIAL_3", STATUS_DIAOCHU_TRIAL_3);
		power.put("STATUS_DIAOCHU_TRIAL_4", STATUS_DIAOCHU_TRIAL_4);
		power.put("STATUS_DIAOCHU_AGREE", STATUS_DIAOCHU_AGREE);
	}
	
	public String getAreaType() {
		
		return areaType;
	}
	
	public void setAreaType(String areaType) {
		
		this.areaType = areaType;
	}
	
	public String getTargetType() {
		
		return targetType;
	}
	
	public void setTargetType(String targetType) {
		
		this.targetType = targetType;
	}
	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public OrganNode getTargetOrgan() {
		
		return targetOrgan;
	}
	
	public void setTargetOrgan(OrganNode targetOrgan) {
		
		this.targetOrgan = targetOrgan;
	}
	
	public DiaoRenIntoMgr getDiaoRenIntoMgr() {
		
		return diaoRenIntoMgr;
	}
	
	public void setDiaoRenIntoMgr(DiaoRenIntoMgr diaoRenIntoMgr) {
		
		this.diaoRenIntoMgr = diaoRenIntoMgr;
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
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
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
	
	public Boolean getIsLowToHigh() {
		
		return isLowToHigh;
	}
	
	public void setIsLowToHigh(Boolean isLowToHigh) {
		
		this.isLowToHigh = isLowToHigh;
	}
	
	public String getJobLevelName() {
		
		return jobLevelName;
	}
	
	public void setJobLevelName(String jobLevelName) {
		
		this.jobLevelName = jobLevelName;
	}
	
	public CodeInfo getJobLevelCode() {
		
		return jobLevelCode;
	}
	
	public void setJobLevelCode(CodeInfo jobLevelCode) {
		
		this.jobLevelCode = jobLevelCode;
	}
	
}
