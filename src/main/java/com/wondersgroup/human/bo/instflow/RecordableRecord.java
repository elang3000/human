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

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;

/**
 * @ClassName: RecordableRecord
 * @Description: 人员 离退备案
 * @author: ghk
 * @date: 2018年9月12日 上午9:39:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_INST_RECORDABLERECORD")
public class RecordableRecord extends GenericEntity {
	
	

	private static final long serialVersionUID = 1L;
	
	// 待提交备案计划
	@Transient
	public final static Integer INST_INFO_RECORDABLE_STATE_POST = 0;
	// 待上级单位确定
	@Transient
	public final static Integer INST_INFO_RECORDABLE_STATE_LEADER = 1;
	
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
			power.put("REPORT_INFO_RECORD_APPLY",INST_INFO_RECORDABLE_STATE_POST);
			power.put("REPORT_UP_LEADER_RECORDABLE", INST_INFO_RECORDABLE_STATE_LEADER);
	}
	
	/**
	 * @fieldName: publicInstitution
	 * @fieldType: PublicInstitution
	 * @Description: 人员信息表
	 */
	@OneToOne
	private PublicInstitution publicInstitution;
	
	/**
	 * @fieldName: recordOrgan
	 * @fieldType: OrganNode
	 * @Description: 离退人当前机构
	 */
	@OneToOne
	private OrganNode recordOrgan;
	
	
	/**
	 * @fieldName: planState
	 * @fieldType: Integer
	 * @Description:审核 状态
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
	 * @Description: 审批意见
	 */
	@Column(name = "OPINION", length = 255)
	private String opinion;
	
	
	/**
	 * @fieldName: recordDate
	 * @fieldType: java.util.Date
	 * @Description: 离退日期
	 */
	@Column(name = "SH_RECORD_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date recordDate;
	
	

	/**
	 * @fieldName: recodeWay
	 * @fieldType: java.lang.String
	 * @Description: 离退备案类型。 SY101
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_RECODE_WAY")
	private CodeInfo recodeWay;
	
	
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	
	//private String operationStatus; //审核状态   1|| 


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


	public OrganNode getRecordOrgan() {
		return recordOrgan;
	}


	public void setRecordOrgan(OrganNode recordOrgan) {
		this.recordOrgan = recordOrgan;
	}


	public Date getRecordDate() {
		return recordDate;
	}


	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	
	public CodeInfo getRecodeWay() {
		return recodeWay;
	}


	public void setRecodeWay(CodeInfo recodeWay) {
		this.recodeWay = recodeWay;
	}

	
	
}
