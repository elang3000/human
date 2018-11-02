
package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.AssessmentFlowUnitPercent;

public class AssessmentFlowUnitPercentEditVO {
	
	public AssessmentFlowUnitPercentEditVO(AssessmentFlowUnitPercent percent){
		this.id=percent.getId();
		this.remark=percent.getRemark();
		this.draftOutstandingNumb=percent.getDraftOutstandingNumb();
		this.OutstandingNumb=percent.getOutstandingNumb();
		this.status=percent.getStatus();
		this.orgName=percent.getOrgNode().getName();
		this.draftOutstandingPercent=percent.getAssessmentFlowCollect().getDraftOutstandingPercentStr();
	}
	
	private String id;
	// 考核描述
	private String remark;
	
	// 拟考核优秀人员数量
	private Integer draftOutstandingNumb;
	
	// 实际优秀人员数量
	private Integer OutstandingNumb;
	
	private Integer status;
	
	private String orgName;
	
	private String draftOutstandingPercent;
	
	public Integer getDraftOutstandingNumb() {
		
		return draftOutstandingNumb;
	}
	
	public void setDraftOutstandingNumb(Integer draftOutstandingNumb) {
		
		this.draftOutstandingNumb = draftOutstandingNumb;
	}
	
	public Integer getOutstandingNumb() {
		
		return OutstandingNumb;
	}
	
	public void setOutstandingNumb(Integer outstandingNumb) {
		
		OutstandingNumb = outstandingNumb;
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

	public String getOrgName() {
		
		return orgName;
	}

	public void setOrgName(String orgName) {
		
		this.orgName = orgName;
	}

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}

	public String getDraftOutstandingPercent() {
		
		return draftOutstandingPercent;
	}

	public void setDraftOutstandingPercent(String draftOutstandingPercent) {
		
		this.draftOutstandingPercent = draftOutstandingPercent;
	}
	
}
