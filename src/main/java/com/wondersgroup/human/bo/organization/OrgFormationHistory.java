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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	 * 关联单位基本信息申请表
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_FORMATION_FLOW_ID")
	private OrgFormationMgrFlow orgFormationMgrFlow;
	
	/**
	 * @fieldName: 定编 处级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编  单位内处级以及与处级相当职级的人员总数。
	 */
	@Column(name = "B02325_A", length = 2)
	private Integer approveDivisionChiefLevelNumber;
	
	/**
	 * @fieldName: 缺（超）编  处级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 缺（超）编  单位内处级以及与处级相当职级的人员总数。
	 */
	@Column(name = "B02325_B", length = 2)
	private Integer vacancyDivisionChiefLevelNumber;
	
	/**
	 * @fieldName: 定编 副处级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 单位内副处级以及与副处级相当职级的人员总数。
	 */
	@Column(name = "B02330_A", length = 2)
	private Integer approveDeputyDivisionChiefLevelNumber;
	
	/**
	 * @fieldName: 缺（超）编 副处级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 缺（超）编 单位内副处级以及与副处级相当职级的人员总数。
	 */
	@Column(name = "B02330_B", length = 2)
	private Integer vacancyDeputyDivisionChiefLevelNumber;
	
	/**
	 * @fieldName: 定编 科级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 单位内科级以及与科级相当职级的人员总数。
	 */
	@Column(name = "B02335_A", length = 2)
	private Integer approveSectionChiefLevelNumber;
	
	/**
	 * @fieldName: 缺（超）编 科级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 缺（超）编 单位内科级以及与科级相当职级的人员总数。
	 */
	@Column(name = "B02335_B", length = 2)
	private Integer vacancySectionChiefLevelNumber;
	
	/**
	 * @fieldName: 定编 副科级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 单位内副科级以及与副科级相当职级的人员总数。
	 */
	@Column(name = "B02340_A", length = 2)
	private Integer approveDeputySectionChiefLevelNumber;
	
	/**
	 * @fieldName: 缺（超）编 副科级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 缺（超）编 单位内副科级以及与副科级相当职级的人员总数。
	 */
	@Column(name = "B02340_B", length = 2)
	private Integer vacancyDeputySectionChiefLevelNumber;
	
	/**
	 * @fieldName: 定编 科员、办事员及其他人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 单位内科员、办事员及其他人员总数。
	 */
	@Column(name = "B02345_A", length = 2)
	private Integer approveStaffMembersNumber;
	
	/**
	 * @fieldName: 缺（超）编 科员、办事员及其他人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 单位内科员、办事员及其他人员总数。
	 */
	@Column(name = "B02345_B", length = 2)
	private Integer vacancyStaffMembersNumber;
	
	/**
	 * @fieldName: 定编 科级及相当者人数（非领导）
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 单位内科级以及与科级相当职级的人员总数（非领导）。
	 */
	@Column(name = "B02335_NL_A", length = 2)
	private Integer approveNonLeaderSectionChiefLevelNumber;
	
	/**
	 * @fieldName: 实有 科级及相当者人数（非领导）
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 单位内科级以及与科级相当职级的人员总数（非领导）。
	 */
	@Column(name = "B02335_NL", length = 2)
	private Integer nonLeaderSectionChiefLevelNumber;
	
	/**
	 * @fieldName: 缺（超）编 科级及相当者人数（非领导）
	 * @fieldType: java.lang.Integer
	 * @Description: 缺（超）编 单位内科级以及与科级相当职级的人员总数（非领导）。
	 */
	@Column(name = "B02335_NL_B", length = 2)
	private Integer vacancyNonLeaderSectionChiefLevelNumber;
	
	/**
	 * @fieldName: 定编 副科级及相当者人数（非领导）
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 单位内副科级以及与副科级相当职级的人员总数（非领导）。
	 */
	@Column(name = "B02340_NL_A", length = 2)
	private Integer approveNonLeaderDeputySectionChiefLevelNumber;
	
	/**
	 * @fieldName: 实有 副科级及相当者人数（非领导）
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 单位内副科级以及与副科级相当职级的人员总数（非领导）。
	 */
	@Column(name = "B02340_NL", length = 2)
	private Integer nonLeaderDeputySectionChiefLevelNumber;
	
	/**
	 * @fieldName: 缺（超）编 副科级及相当者人数（非领导）
	 * @fieldType: java.lang.Integer
	 * @Description: 缺（超）编 单位内副科级以及与副科级相当职级的人员总数（非领导）。
	 */
	@Column(name = "B02340_NL_B", length = 2)
	private Integer vacancyNonLeaderDeputySectionChiefLevelNumber;
	
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

	
	public Integer getApproveDivisionChiefLevelNumber() {
		
		return approveDivisionChiefLevelNumber;
	}

	
	public void setApproveDivisionChiefLevelNumber(Integer approveDivisionChiefLevelNumber) {
		
		this.approveDivisionChiefLevelNumber = approveDivisionChiefLevelNumber;
	}

	
	public Integer getVacancyDivisionChiefLevelNumber() {
		
		return vacancyDivisionChiefLevelNumber;
	}

	
	public void setVacancyDivisionChiefLevelNumber(Integer vacancyDivisionChiefLevelNumber) {
		
		this.vacancyDivisionChiefLevelNumber = vacancyDivisionChiefLevelNumber;
	}

	
	public Integer getApproveDeputyDivisionChiefLevelNumber() {
		
		return approveDeputyDivisionChiefLevelNumber;
	}

	
	public void setApproveDeputyDivisionChiefLevelNumber(Integer approveDeputyDivisionChiefLevelNumber) {
		
		this.approveDeputyDivisionChiefLevelNumber = approveDeputyDivisionChiefLevelNumber;
	}

	
	public Integer getVacancyDeputyDivisionChiefLevelNumber() {
		
		return vacancyDeputyDivisionChiefLevelNumber;
	}

	
	public void setVacancyDeputyDivisionChiefLevelNumber(Integer vacancyDeputyDivisionChiefLevelNumber) {
		
		this.vacancyDeputyDivisionChiefLevelNumber = vacancyDeputyDivisionChiefLevelNumber;
	}

	
	public Integer getApproveSectionChiefLevelNumber() {
		
		return approveSectionChiefLevelNumber;
	}

	
	public void setApproveSectionChiefLevelNumber(Integer approveSectionChiefLevelNumber) {
		
		this.approveSectionChiefLevelNumber = approveSectionChiefLevelNumber;
	}

	
	public Integer getVacancySectionChiefLevelNumber() {
		
		return vacancySectionChiefLevelNumber;
	}

	
	public void setVacancySectionChiefLevelNumber(Integer vacancySectionChiefLevelNumber) {
		
		this.vacancySectionChiefLevelNumber = vacancySectionChiefLevelNumber;
	}

	
	public Integer getApproveDeputySectionChiefLevelNumber() {
		
		return approveDeputySectionChiefLevelNumber;
	}

	
	public void setApproveDeputySectionChiefLevelNumber(Integer approveDeputySectionChiefLevelNumber) {
		
		this.approveDeputySectionChiefLevelNumber = approveDeputySectionChiefLevelNumber;
	}

	
	public Integer getVacancyDeputySectionChiefLevelNumber() {
		
		return vacancyDeputySectionChiefLevelNumber;
	}

	
	public void setVacancyDeputySectionChiefLevelNumber(Integer vacancyDeputySectionChiefLevelNumber) {
		
		this.vacancyDeputySectionChiefLevelNumber = vacancyDeputySectionChiefLevelNumber;
	}

	
	public Integer getApproveStaffMembersNumber() {
		
		return approveStaffMembersNumber;
	}

	
	public void setApproveStaffMembersNumber(Integer approveStaffMembersNumber) {
		
		this.approveStaffMembersNumber = approveStaffMembersNumber;
	}

	
	public Integer getVacancyStaffMembersNumber() {
		
		return vacancyStaffMembersNumber;
	}

	
	public void setVacancyStaffMembersNumber(Integer vacancyStaffMembersNumber) {
		
		this.vacancyStaffMembersNumber = vacancyStaffMembersNumber;
	}

	
	public Integer getApproveNonLeaderSectionChiefLevelNumber() {
		
		return approveNonLeaderSectionChiefLevelNumber;
	}

	
	public void setApproveNonLeaderSectionChiefLevelNumber(Integer approveNonLeaderSectionChiefLevelNumber) {
		
		this.approveNonLeaderSectionChiefLevelNumber = approveNonLeaderSectionChiefLevelNumber;
	}

	
	public Integer getNonLeaderSectionChiefLevelNumber() {
		
		return nonLeaderSectionChiefLevelNumber;
	}

	
	public void setNonLeaderSectionChiefLevelNumber(Integer nonLeaderSectionChiefLevelNumber) {
		
		this.nonLeaderSectionChiefLevelNumber = nonLeaderSectionChiefLevelNumber;
	}

	
	public Integer getVacancyNonLeaderSectionChiefLevelNumber() {
		
		return vacancyNonLeaderSectionChiefLevelNumber;
	}

	
	public void setVacancyNonLeaderSectionChiefLevelNumber(Integer vacancyNonLeaderSectionChiefLevelNumber) {
		
		this.vacancyNonLeaderSectionChiefLevelNumber = vacancyNonLeaderSectionChiefLevelNumber;
	}

	
	public Integer getApproveNonLeaderDeputySectionChiefLevelNumber() {
		
		return approveNonLeaderDeputySectionChiefLevelNumber;
	}

	
	public void setApproveNonLeaderDeputySectionChiefLevelNumber(Integer approveNonLeaderDeputySectionChiefLevelNumber) {
		
		this.approveNonLeaderDeputySectionChiefLevelNumber = approveNonLeaderDeputySectionChiefLevelNumber;
	}

	
	public Integer getNonLeaderDeputySectionChiefLevelNumber() {
		
		return nonLeaderDeputySectionChiefLevelNumber;
	}

	
	public void setNonLeaderDeputySectionChiefLevelNumber(Integer nonLeaderDeputySectionChiefLevelNumber) {
		
		this.nonLeaderDeputySectionChiefLevelNumber = nonLeaderDeputySectionChiefLevelNumber;
	}

	
	public Integer getVacancyNonLeaderDeputySectionChiefLevelNumber() {
		
		return vacancyNonLeaderDeputySectionChiefLevelNumber;
	}

	
	public void setVacancyNonLeaderDeputySectionChiefLevelNumber(Integer vacancyNonLeaderDeputySectionChiefLevelNumber) {
		
		this.vacancyNonLeaderDeputySectionChiefLevelNumber = vacancyNonLeaderDeputySectionChiefLevelNumber;
	}
	
}
