/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RecruitEmployPlan.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 职位简章招录计划
 * 创建人: wangzhanfei
 * 创建时间: 2018年5月28日 上午9:39:43
 * 修改内容：
 */

package com.wondersgroup.human.bo.instflow;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;

/**
 * @ClassName: MemberInfoRegister
 * @Description: 人员录用登记上报
 * @author: lyf
 * @date: 2018年9月7日 上午9:39:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_INST_REGISTER")
public class MemberInfoRegister extends GenericEntity {
	
	private static final long serialVersionUID = -2045142251901326764L;
	
	/**
	 * 招录计划状态
	 */
	// 待提交招录计划
	@Transient
	public final static Integer INST_INFO_REGISTER_STATE_POST = 0;
	// 待上级单位初审
	@Transient
	public final static Integer INST_INFO_REGISTER_STATE_LEADER = 1;
	
	// 待区人事主管部门终审
	@Transient
	public final static Integer INST_INFO_REGISTER_STATE_END = 2;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
		power.put("REPORT_INFO_REGISTER",INST_INFO_REGISTER_STATE_POST);
		power.put("REPORT_UP_LEADER_REGISTER", INST_INFO_REGISTER_STATE_LEADER);
		power.put("REPORT_QU_REGISTER", INST_INFO_REGISTER_STATE_END);
	}
	
	/**
	 * @fieldName: publicInstitution
	 * @fieldType: PublicInstitution
	 * @Description: 人员信息表
	 */
	@OneToOne
	private PublicInstitution publicInstitution;
	
	/**
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 招录机关
	 */
	@OneToOne
	private OrganNode recruitOrgan;
	
	/**
	 * @fieldName: employOrgan
	 * @fieldType: OrganNode
	 * @Description: 用人机关
	 */
	@OneToOne
	private OrganNode employOrgan;
	
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


	public PublicInstitution getPublicInstitution() {
		return publicInstitution;
	}


	public void setPublicInstitution(PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
	}


	public OrganNode getRecruitOrgan() {
		return recruitOrgan;
	}


	public void setRecruitOrgan(OrganNode recruitOrgan) {
		this.recruitOrgan = recruitOrgan;
	}


	public OrganNode getEmployOrgan() {
		return employOrgan;
	}


	public void setEmployOrgan(OrganNode employOrgan) {
		this.employOrgan = employOrgan;
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
	
	
	
}
