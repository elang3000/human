
package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: AssessmentFlowCollect
 * @Description: 单位人员考核详情
 * @author: youyd
 * @date: 2018年9月7日 下午2:56:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ASSESSMENT_FLOW_DETAIL")
public class AssessmentDetail extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 1L;
	
	//已经考核
	public static final Integer NOASSESS=0;
	
	//未考核
	public static final Integer YESASSESS=1;

	//考核人员
	@ManyToOne
	@JoinColumn(name = "SERVANT") 
	private Servant servant;
	
	
	//考核单
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSESSMENT_FLOW_COLLECT") 
	private AssessmentFlowCollect assessmentFlowCollect; 
	
	//考核比例单
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSESSMENT_FLOW_UNIT_PERCENT") 
	private AssessmentFlowUnitPercent assessmentFlowUnitPercent;
	
	//考核结果
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESULT") 
	private CodeInfo result;
		
	//备注
	@Column(name = "REMARK", length = 2000)
	private String remark;
		
	//考核状态  1--确认考核 0未确认
	@Column(name = "STATUS")  
	private Integer status;
		
	//是否嘉奖  1--是  0--否
	@Column(name = "IS_COMMEND")
	private String iscommend;

	//是否记三等功  1--是  0--否
	@Column(name = "IS_TCREDIT")  
	private String iscredit;

	
	public Servant getServant() {
		
		return servant;
	}

	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}


	
	public AssessmentFlowCollect getAssessmentFlowCollect() {
		
		return assessmentFlowCollect;
	}

	
	public void setAssessmentFlowCollect(AssessmentFlowCollect assessmentFlowCollect) {
		
		this.assessmentFlowCollect = assessmentFlowCollect;
	}

	
	public AssessmentFlowUnitPercent getAssessmentFlowUnitPercent() {
		
		return assessmentFlowUnitPercent;
	}

	
	public void setAssessmentFlowUnitPercent(AssessmentFlowUnitPercent assessmentFlowUnitPercent) {
		
		this.assessmentFlowUnitPercent = assessmentFlowUnitPercent;
	}

	
	public CodeInfo getResult() {
		
		return result;
	}

	
	public void setResult(CodeInfo result) {
		
		this.result = result;
	}

	
	public String getRemarks() {
		
		return remark;
	}

	
	public void setRemarks(String remarks) {
		
		this.remark = remarks;
	}

	
	public Integer getStatus() {
		
		return status;
	}

	
	public void setStatus(Integer status) {
		
		this.status = status;
	}

	
	public String getIscommend() {
		
		return iscommend;
	}

	
	public void setIscommend(String iscommend) {
		
		this.iscommend = iscommend;
	}

	
	public String getIscredit() {
		
		return iscredit;
	}

	
	public void setIscredit(String iscredit) {
		
		this.iscredit = iscredit;
	}
	
	
	
}
