/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgFormationHistory.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.organization
 * 描述: 机构编制历史调整类
 * 创建人: jiang
 * 创建时间: 2018年9月20日17:35:53
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.organization;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.ofcflow.OrgFormationMgrFlow;
import com.wondersgroup.human.bo.organization.base.BaseUnitFormation;

/**
 * 机构编制信息
 * @ClassName: OrgFormationHistory
 * @Description: 单位编制历史调整类
 * @author: jiang
 * @date: 2018年9月20日17:35:56
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "B02_HIS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgFormationHistory extends BaseUnitFormation<OrgFormationHistory> {
	
	private static final long serialVersionUID = -5719647342006375651L;
	
	/**
	 * *
	 * 关联单位基本信息
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGINFO_ID")
	private OrgInfo orgInfo;
	
	/**
	 * *
	 * 关联单位编制调整申请表
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_FORMATION_FLOW_ID")
	private OrgFormationMgrFlow orgFormationMgrFlow;
	
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

	
	public OrgFormationMgrFlow getOrgFormationMgrFlow() {
		
		return orgFormationMgrFlow;
	}

	
	public void setOrgFormationMgrFlow(OrgFormationMgrFlow orgFormationMgrFlow) {
		
		this.orgFormationMgrFlow = orgFormationMgrFlow;
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
