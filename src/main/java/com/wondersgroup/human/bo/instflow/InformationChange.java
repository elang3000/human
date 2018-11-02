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
 * @ClassName: InformationChange
 * @Description: 事业人员信息变更审核
 * @author: ghk
 * @date: 2018年9月12日 上午9:39:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_INST_INFORMATION_CHANGE")
public class InformationChange extends GenericEntity {
	
	
	private static final long serialVersionUID = 336136207656118588L;
	
	
	// 待提交备案计划
	@Transient
	public final static Integer INST_INFO_INFORMATION_STATE_POST = 0;
	// 区人事管理部门审核
	@Transient
	public final static Integer INST_INFO_QU_INFORMATION_STATE = 1;
	
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
			power.put("REPORT_INFO_INFORMATION_ALTER_APPLY",INST_INFO_INFORMATION_STATE_POST);
			power.put("REPORT_QU_INFORMATION_ALTER", INST_INFO_QU_INFORMATION_STATE);
	}
	
	/**
	 * @fieldName: publicInstitution
	 * @fieldType: PublicInstitution
	 * @Description: 人员信息表
	 */
	@OneToOne
	private PublicInstitution publicInstitution;
	
	
	
	/**
	 * @fieldName: InformationChange
	 * @fieldType: InfoChangePublicInstitution
	 * @Description: 信息变动临时表
	 */
	@OneToOne
	private InfoChangePublicInstitution infoChangePublicInstitution;
	
	/**
	 * @fieldName: nowOrgan
	 * @fieldType: OrganNode
	 * @Description: 修改人员当前部门
	 */
	@OneToOne
	private OrganNode nowOrgan;
	
	
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
	 * @fieldName: changeDate
	 * @fieldType: java.util.Date
	 * @Description:  修改日期
	 */
	@Column(name = "INFORMATION_CHANGE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date  changeDate;
	
	
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	


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


	public OrganNode getNowOrgan() {
		return nowOrgan;
	}


	public void setNowOrgan(OrganNode nowOrgan) {
		this.nowOrgan = nowOrgan;
	}


	public Date getChangeDate() {
		return changeDate;
	}


	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}


	public InfoChangePublicInstitution getInfoChangePublicInstitution() {
		return infoChangePublicInstitution;
	}


	public void setInfoChangePublicInstitution(InfoChangePublicInstitution infoChangePublicInstitution) {
		this.infoChangePublicInstitution = infoChangePublicInstitution;
	}
	
	


	
	
	
	
}
