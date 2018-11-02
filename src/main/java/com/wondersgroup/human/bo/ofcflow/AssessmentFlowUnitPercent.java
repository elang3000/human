
package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.organization.OrgInfo;

/**
 * @ClassName: AssessmentFlowCollect
 * @Description: 考核单位的指标量
 * @author: youyd
 * @date: 2018年9月7日 下午2:56:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ASSESSMENT_FLOW_UNIT_PERCENT")
public class AssessmentFlowUnitPercent extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 1L;
	
	//流程 年度考核奖励 步骤1
	public static final String FLOW_ASSESS_STEP1 = "STATUS_ASSESS_STEP1";
	
	//流程 年度考核奖励 步骤2
	public static final String FLOW_ASSESS_STEP2 = "STATUS_ASSESS_STEP2";
	
	public static final String FLOW_ASSESS_STEP3 = "STATUS_ASSESS_STEP3";
	
	public static final String FLOW_ASSESS_STEP4 = "STATUS_ASSESS_STEP4";
	
	public static final String FLOW_ASSESS_STEP5 = "STATUS_ASSESS_STEP5";
	
	public static final String FLOW_ASSESS_STEP6 = "STATUS_ASSESS_STEP6";
	
	public static final String FLOW_ASSESS_STEP7 = "STATUS_ASSESS_STEP7";
	
	public static final String FLOW_ASSESS_STEP8 = "STATUS_ASSESS_STEP8";
	
	public static final String FLOW_ASSESS_STEP9 = "STATUS_ASSESS_STEP9";
	
	public static final String FLOW_ASSESS_STEP10 = "STATUS_ASSESS_STEP10";
	
	public static final String FLOW_ASSESS_STEP11 = "STATUS_ASSESS_STEP11";
	
	//流程 年度考核奖励 步骤12
	public static final String FLOW_ASSESS_STEP12 = "STATUS_ASSESS_STEP12";
	
	// 考核单位
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGNODE")
	private OrganNode orgNode;
	
	@Column(name = "ORGNAME")
	private String orgName;
	
	// 考核描述
	@Column(name = "REMARK", length = 2000)
	private String remark;
	
	// 拟考核优秀人员数量
	@Column(name = "DRAFT_OUTSTANDING_NUMB")
	private Integer draftOutstandingNumb;
	
	// 人社局指定优秀人员数量
	@Column(name = "OUTSTANDING_NUMB")
	private Integer outstandingNumb;
	
	// 单位申请优秀人数
	@Column(name = "UNIT_OUTSTANDING_NUMB")
	private Integer unitOutStandingNumb;
	
	// 考核单
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSESSMENT_FLOW_COLLECT")
	private AssessmentFlowCollect assessmentFlowCollect;
	
	// 考核状态 1--确认考核 0未确认
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
	
	// 考核状态       -1 未开始流程  0开始流程 1完成流程
	@Column(name = "FLOW_STATUS")
	private Integer flowStatus;
	
	
	public Integer getFlowStatus() {
		
		return flowStatus;
	}

	
	public void setFlowStatus(Integer flowStatus) {
		
		this.flowStatus = flowStatus;
	}

	public String getOrgName() {
		
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		
		this.orgName = orgName;
	}
	
	public OrganNode getOrgNode() {
		
		return orgNode;
	}
	
	public void setOrgNode(OrganNode orgNode) {
		
		this.orgNode = orgNode;
	}
	
	public Integer getDraftOutstandingNumb() {
		
		return draftOutstandingNumb;
	}
	
	public void setDraftOutstandingNumb(Integer draftOutstandingNumb) {
		
		this.draftOutstandingNumb = draftOutstandingNumb;
	}
	
	public Integer getOutstandingNumb() {
		
		return outstandingNumb;
	}
	
	public void setOutstandingNumb(Integer outstandingNumb) {
		
		this.outstandingNumb = outstandingNumb;
	}
	
	public AssessmentFlowCollect getAssessmentFlowCollect() {
		
		return assessmentFlowCollect;
	}
	
	public void setAssessmentFlowCollect(AssessmentFlowCollect assessmentFlowCollect) {
		
		this.assessmentFlowCollect = assessmentFlowCollect;
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
	
	public Integer getUnitOutStandingNumb() {
		
		return unitOutStandingNumb;
	}
	
	public void setUnitOutStandingNumb(Integer unitOutStandingNumb) {
		
		this.unitOutStandingNumb = unitOutStandingNumb;
	}
	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}
	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}
	
}
