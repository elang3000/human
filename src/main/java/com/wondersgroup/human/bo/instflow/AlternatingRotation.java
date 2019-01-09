/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RecruitEmployPlan.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 职位简章招录计划
 * 创建人: wangzhanfei
 * 创建时间: 2018年5月28日 上午9:39:43
 * 版本号: V1.0
 * 修改人：wangzhanfei
 * 修改时间：2018年5月28日 上午9:39:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.instflow;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.pubinst.PtPost;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;

/**
 * @ClassName: AlternatingRotation
 * @Description: 事业单位人员交流轮岗
 * @author: lyf
 * @date: 2018年9月12日 上午9:39:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_INST_ALTERNATINGROTATION")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlternatingRotation extends GenericEntity {
	
	private static final long serialVersionUID = 8469109965003073736L;
	
	// 待提交招录计划
	@Transient
	public final static Integer INST_INFO_STATE_POST = 0;
	// 待上级单位初审
	@Transient
	public final static Integer INST_INFO_STATE_LEADER = 1;
	
	// 待区人事主管部门终审
	@Transient
	public final static Integer INST_INFO_STATE_END = 2;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
		power.put("REPORT_INFO_ALTERROTATION_APPLY",INST_INFO_STATE_POST);
		power.put("REPORT_UP_LEADER_ALTERROTATION", INST_INFO_STATE_LEADER);
		power.put("REPORT_QU_ALTERROTATION", INST_INFO_STATE_END);
	}
	
	/**
	 * @fieldName: publicInstitution
	 * @fieldType: PublicInstitution
	 * @Description: 人员信息表
	 */
	@OneToOne
	private PublicInstitution publicInstitution;
	
	
	/**
	 * @fieldName: alterRotaOrgan
	 * @fieldType: OrganNode
	 * @Description: 交流单位
	 */
	@OneToOne
	private OrganNode alterRotaOrgan;
	
	
	/**
	 * @fieldName: oldDepartmentName
	 * @fieldType: String
	 * @Description: 之前的单位名称
	 */
	@Column(name = "OLDDEPARTMENTNAME", length = 255)
	private String oldDepartmentName;

	


	/**
	 * @fieldName: oldOrgan
	 * @fieldType: OrganNode
	 * @Description: 之前的单位
	 */
	@OneToOne
	private OrganNode oldOrgan;
	
	public String getOldDepartmentName() {
		return oldDepartmentName;
	}


	public void setOldDepartmentName(String oldDepartmentName) {
		this.oldDepartmentName = oldDepartmentName;
	}


	/**
	 * @fieldName: planState
	 * @fieldType: Integer
	 * @Description: 状态
	 */
	@Column(name = "PLAN_STATE")
	private Integer planState;
	
	
	
	/**
	 * @fieldName: remark
	 * @fieldType: String
	 * @Description: 备注
	 */
	@Column(name = "REMARK", length = 255)
	private String remark;
	
	/**
	 * @fieldName: opinion
	 * @fieldType: String
	 * @Description: 备注
	 */
	@Column(name = "OPINION", length = 255)
	private String opinion;
	
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/**
	 * @fieldName: alterRotaReason
	 * @fieldType: String
	 * @Description: 变动原因
	 */
	@Column(name = "ALTERROTAREASON", length = 255)
	private String alterrotaReason;
	
	
	/**
	 * @fieldName: alterrotaDate
	 * @fieldType: java.lang.String
	 * @Description: 变动日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SH_ALTERROTA_DATE")
	private Date alterrotaDate;
	
	
	/**
	 * 职务信息
	 * @fieldName: communicationSign
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 交流标识，DM215 免去该职务时是否交流任职的标识。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "communicationsign")
	private CodeInfo communicationSign;
	
	
	/**
	 * @fieldName: postCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职务代码,GB/T 12403-1990 该人担任职务的代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "postcode")
	private CodeInfo postCode;
	
	
	/**
	 * @fieldName: attribute
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职务属性,DM049 担任领导职务或非领导职务的情况。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "attribute")
	private CodeInfo attribute;


	public CodeInfo getAttribute() {
		return attribute;
	}


	public void setAttribute(CodeInfo attribute) {
		this.attribute = attribute;
	}


	public PublicInstitution getPublicInstitution() {
		return publicInstitution;
	}


	public void setPublicInstitution(PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
	}

	public Integer getPlanState() {
		return planState;
	}


	public void setPlanState(Integer planState) {
		this.planState = planState;
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


	public String getOpinion() {
		return opinion;
	}


	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}


	public OrganNode getAlterRotaOrgan() {
		return alterRotaOrgan;
	}


	public void setAlterRotaOrgan(OrganNode alterRotaOrgan) {
		this.alterRotaOrgan = alterRotaOrgan;
	}


	public OrganNode getOldOrgan() {
		return oldOrgan;
	}


	public void setOldOrgan(OrganNode oldOrgan) {
		this.oldOrgan = oldOrgan;
	}


	public String getAlterrotaReason() {
		return alterrotaReason;
	}


	public void setAlterrotaReason(String alterrotaReason) {
		this.alterrotaReason = alterrotaReason;
	}


	public Date getAlterrotaDate() {
		return alterrotaDate;
	}


	public void setAlterrotaDate(Date alterrotaDate) {
		this.alterrotaDate = alterrotaDate;
	}


	public CodeInfo getCommunicationSign() {
		return communicationSign;
	}


	public void setCommunicationSign(CodeInfo communicationSign) {
		this.communicationSign = communicationSign;
	}


	public CodeInfo getPostCode() {
		return postCode;
	}


	public void setPostCode(CodeInfo postCode) {
		this.postCode = postCode;
	}

	
	
}
