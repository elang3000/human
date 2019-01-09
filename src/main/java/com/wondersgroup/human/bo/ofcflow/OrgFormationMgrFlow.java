/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgFormationMgrFlow.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.organization
 * 描述: 单位编制情况维护流程表单
 * 创建人: jiang
 * 创建时间: 2018年9月13日09:40:52
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.bo.organization.base.BaseUnitFormation;

/**
 * 机构信息
 * @ClassName: OrgFormationMgrFlow
 * @Description: 单位编制情况维护流程表单
 * @author: jiang
 * @date: 2018年9月19日11:33:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_FLOW_ORG_FORMATION_MGR")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgFormationMgrFlow extends BaseUnitFormation<OrgFormationMgrFlow> {
	
	/**
	 * 待提交申请信息
	 */
	public static final int STATUS_ORG_FORMATION_MGR_FLOW_STATE = 0;
	
	/**
	 * 待上级审核
	 */
	public static final int STATUS_ORG_FORMATION_MGR_FLOW_TRIAL1 = 1;
	
	/**
	 * 审核通过
	 */
	public static final int STATUS_ORG_FORMATION_MGR_FLOW_TRIAL2 = 2;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		power.put("FORMATION_ADJUST_WAIT_SUBMIT", STATUS_ORG_FORMATION_MGR_FLOW_STATE);
		power.put("FORMATION_ADJUST_WAIT_UP_APPROVAL", STATUS_ORG_FORMATION_MGR_FLOW_TRIAL1);
	}
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4603193733428802138L;
	
	/**
	 * *
	 * 关联单位
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGINFO_ID")
	private OrgInfo orgInfo;
	
	/**
	 * * 操作类型
	 **/
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "OPTION_TYPE")
	private CodeInfo optionType;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	/**
	 * @fieldName: planPath
	 * @fieldType: java.lang.String
	 * @Description: 三定方案 文件路径。
	 */
	@Column(name = "PlAN_PATH", length = 2000)
	private String planPath;
	
	
	/**
	 * @fieldName: approveChuNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 处级人数
	 */
	@Column(name = "CHU_A")
	private Integer approveChuNum = 0;
	
	/**
	 * @fieldName: realChuNum
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 处级人数
	 */
	@Column(name = "CHU_B")
	private Integer realChuNum = 0;
	
	/**
	 * @fieldName: approvePlusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 正科级领导人数
	 */
	@Column(name = "PLUS_KE_LEADER_A")
	private Integer approvePlusKeLeaderNum = 0;
	
	/**
	 * @fieldName: realPlusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 正科级领导人数
	 */
	@Column(name = "PLUS_KE_LEADER_B")
	private Integer realPlusKeLeaderNum = 0;
	
	/**
	 * @fieldName: approvePlusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 正科级非领导人数
	 */
	@Column(name = "PLUS_KE_NOLEADER_A")
	private Integer approvePlusKeNoLeaderNum = 0;
	
	/**
	 * @fieldName: realPlusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 正科级非领导人数
	 */
	@Column(name = "PLUS_KE_NOLEADER_B")
	private Integer realPlusKeNoLeaderNum = 0;
	
	/**
	 * @fieldName: approveMinusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 副科级领导人数
	 */
	@Column(name = "MINUS_KE_LEADER_A")
	private Integer approveMinusKeLeaderNum = 0;
	
	/**
	 * @fieldName: realMinusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 副科级领导人数
	 */
	@Column(name = "MINUS_KE_LEADER_B")
	private Integer realMinusKeLeaderNum = 0;
	
	/**
	 * @fieldName: approveMinusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 副科级非领导人数
	 */
	@Column(name = "MINUS_KE_NOLEADER_A")
	private Integer approveMinusKeNoLeaderNum = 0;
	
	/**
	 * @fieldName: realMinusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 副科级非领导人数
	 */
	@Column(name = "MINUS_KE_NOLEADER_B")
	private Integer realMinusKeNoLeaderNum = 0;

	
	public OrgInfo getOrgInfo() {
		
		return orgInfo;
	}

	
	public void setOrgInfo(OrgInfo orgInfo) {
		
		this.orgInfo = orgInfo;
	}

	
	public CodeInfo getOptionType() {
		
		return optionType;
	}

	
	public void setOptionType(CodeInfo optionType) {
		
		this.optionType = optionType;
	}

	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}

	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}

	
	public Integer getStatus() {
		
		return status;
	}

	
	public void setStatus(Integer status) {
		
		this.status = status;
	}

	
	public String getPlanPath() {
		
		return planPath;
	}

	
	public void setPlanPath(String planPath) {
		
		this.planPath = planPath;
	}

	
	public Integer getApproveChuNum() {
		
		return approveChuNum;
	}

	
	public void setApproveChuNum(Integer approveChuNum) {
		
		this.approveChuNum = approveChuNum;
	}

	
	public Integer getRealChuNum() {
		
		return realChuNum;
	}

	
	public void setRealChuNum(Integer realChuNum) {
		
		this.realChuNum = realChuNum;
	}

	
	public Integer getApprovePlusKeLeaderNum() {
		
		return approvePlusKeLeaderNum;
	}

	
	public void setApprovePlusKeLeaderNum(Integer approvePlusKeLeaderNum) {
		
		this.approvePlusKeLeaderNum = approvePlusKeLeaderNum;
	}

	
	public Integer getRealPlusKeLeaderNum() {
		
		return realPlusKeLeaderNum;
	}

	
	public void setRealPlusKeLeaderNum(Integer realPlusKeLeaderNum) {
		
		this.realPlusKeLeaderNum = realPlusKeLeaderNum;
	}

	
	public Integer getApprovePlusKeNoLeaderNum() {
		
		return approvePlusKeNoLeaderNum;
	}

	
	public void setApprovePlusKeNoLeaderNum(Integer approvePlusKeNoLeaderNum) {
		
		this.approvePlusKeNoLeaderNum = approvePlusKeNoLeaderNum;
	}

	
	public Integer getRealPlusKeNoLeaderNum() {
		
		return realPlusKeNoLeaderNum;
	}

	
	public void setRealPlusKeNoLeaderNum(Integer realPlusKeNoLeaderNum) {
		
		this.realPlusKeNoLeaderNum = realPlusKeNoLeaderNum;
	}

	
	public Integer getApproveMinusKeLeaderNum() {
		
		return approveMinusKeLeaderNum;
	}

	
	public void setApproveMinusKeLeaderNum(Integer approveMinusKeLeaderNum) {
		
		this.approveMinusKeLeaderNum = approveMinusKeLeaderNum;
	}

	
	public Integer getRealMinusKeLeaderNum() {
		
		return realMinusKeLeaderNum;
	}

	
	public void setRealMinusKeLeaderNum(Integer realMinusKeLeaderNum) {
		
		this.realMinusKeLeaderNum = realMinusKeLeaderNum;
	}

	
	public Integer getApproveMinusKeNoLeaderNum() {
		
		return approveMinusKeNoLeaderNum;
	}

	
	public void setApproveMinusKeNoLeaderNum(Integer approveMinusKeNoLeaderNum) {
		
		this.approveMinusKeNoLeaderNum = approveMinusKeNoLeaderNum;
	}

	
	public Integer getRealMinusKeNoLeaderNum() {
		
		return realMinusKeNoLeaderNum;
	}

	
	public void setRealMinusKeNoLeaderNum(Integer realMinusKeNoLeaderNum) {
		
		this.realMinusKeNoLeaderNum = realMinusKeNoLeaderNum;
	}

}
