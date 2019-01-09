/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgFormation.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.organization
 * 描述: 机构编制信息类
 * 创建人: jiang
 * 创建时间: 2018年9月19日10:35:59
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
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.organization.base.BaseUnitFormation;

/**
 * 机构编制信息
 * @ClassName: OrgFormation
 * @Description: 单位编制情况
 * @author: jiang
 * @date: 2018年9月19日10:36:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "B02")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgFormation extends BaseUnitFormation<OrgFormation> {
	
	private static final long serialVersionUID = -5719647342006337151L;
	
	@Version
	@Column(name = "VERSION")
	private Integer version;
	
	/**
	 * *
	 * 关联单位
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGINFO_ID")
	private OrgInfo orgInfo;
	
	/**
	 * @fieldName: planPath
	 * @fieldType: java.lang.String
	 * @Description: 三定方案 文件路径。
	 */
	@Column(name = "PlAN_PATH", length = 2000)
	private String planPath;
	
	/**
	 * @fieldName: notIntoNum
	 * @fieldType: java.lang.Integer
	 * @Description: 供给关系尚未调入人员
	 */
	@Column(name = "NOT_INTO_NUM")
	private Integer notIntoNum = 0;
	
	/**
	 * @fieldName: notOutNum
	 * @fieldType: java.lang.Integer
	 * @Description: 供给关系尚未调出人员
	 */
	@Column(name = "NOT_OUT_NUM")
	private Integer notOutNum = 0;
	
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
	 * @fieldName: notIntoPlusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调入 正科级领导人数
	 */
	@Column(name = "NOT_INTO_PLUS_KE_LEADER")
	private Integer notIntoPlusKeLeaderNum = 0;
	
	/**
	 * @fieldName: notOutPlusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调出 正科级领导人数
	 */
	@Column(name = "NOT_OUT_PLUS_KE_LEADER")
	private Integer notOutPlusKeLeaderNum = 0;
	
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
	 * @fieldName: notIntoPlusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调入 正科级非领导人数
	 */
	@Column(name = "NOT_INTO_PLUS_KE_NOLEADER")
	private Integer notIntoPlusKeNoLeaderNum = 0;
	
	/**
	 * @fieldName: notOutPlusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调出 正科级非领导人数
	 */
	@Column(name = "NOT_OUT_PLUS_KE_NOLEADER")
	private Integer notOutPlusKeNoLeaderNum = 0;
	
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
	 * @fieldName: notIntoMinusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调入 副科级领导人数
	 */
	@Column(name = "NOT_INTO_MINUS_KE_LEADER")
	private Integer notIntoMinusKeLeaderNum = 0;
	
	/**
	 * @fieldName: notOutMinusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调出 副科级领导人数
	 */
	@Column(name = "NOT_OUT_MINUS_KE_LEADER")
	private Integer notOutMinusKeLeaderNum = 0;
	
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
	
	/**
	 * @fieldName: notIntoMinusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调入 副科级非领导人数
	 */
	@Column(name = "NOT_INTO_MINUS_KE_NOLEADER")
	private Integer notIntoMinusKeNoLeaderNum = 0;
	
	/**
	 * @fieldName: notOutMinusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调出 副科级非领导人数
	 */
	@Column(name = "NOT_OUT_MINUS_KE_NOLEADER")
	private Integer notOutMinusKeNoLeaderNum = 0;

	
	public Integer getVersion() {
		
		return version;
	}

	
	public void setVersion(Integer version) {
		
		this.version = version;
	}

	
	public OrgInfo getOrgInfo() {
		
		return orgInfo;
	}

	
	public void setOrgInfo(OrgInfo orgInfo) {
		
		this.orgInfo = orgInfo;
	}

	
	public String getPlanPath() {
		
		return planPath;
	}

	
	public void setPlanPath(String planPath) {
		
		this.planPath = planPath;
	}

	
	public Integer getNotIntoNum() {
		
		return notIntoNum;
	}

	
	public void setNotIntoNum(Integer notIntoNum) {
		
		this.notIntoNum = notIntoNum;
	}

	
	public Integer getNotOutNum() {
		
		return notOutNum;
	}

	
	public void setNotOutNum(Integer notOutNum) {
		
		this.notOutNum = notOutNum;
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

	
	public Integer getNotIntoPlusKeLeaderNum() {
		
		return notIntoPlusKeLeaderNum;
	}

	
	public void setNotIntoPlusKeLeaderNum(Integer notIntoPlusKeLeaderNum) {
		
		this.notIntoPlusKeLeaderNum = notIntoPlusKeLeaderNum;
	}

	
	public Integer getNotOutPlusKeLeaderNum() {
		
		return notOutPlusKeLeaderNum;
	}

	
	public void setNotOutPlusKeLeaderNum(Integer notOutPlusKeLeaderNum) {
		
		this.notOutPlusKeLeaderNum = notOutPlusKeLeaderNum;
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

	
	public Integer getNotIntoPlusKeNoLeaderNum() {
		
		return notIntoPlusKeNoLeaderNum;
	}

	
	public void setNotIntoPlusKeNoLeaderNum(Integer notIntoPlusKeNoLeaderNum) {
		
		this.notIntoPlusKeNoLeaderNum = notIntoPlusKeNoLeaderNum;
	}

	
	public Integer getNotOutPlusKeNoLeaderNum() {
		
		return notOutPlusKeNoLeaderNum;
	}

	
	public void setNotOutPlusKeNoLeaderNum(Integer notOutPlusKeNoLeaderNum) {
		
		this.notOutPlusKeNoLeaderNum = notOutPlusKeNoLeaderNum;
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

	
	public Integer getNotIntoMinusKeLeaderNum() {
		
		return notIntoMinusKeLeaderNum;
	}

	
	public void setNotIntoMinusKeLeaderNum(Integer notIntoMinusKeLeaderNum) {
		
		this.notIntoMinusKeLeaderNum = notIntoMinusKeLeaderNum;
	}

	
	public Integer getNotOutMinusKeLeaderNum() {
		
		return notOutMinusKeLeaderNum;
	}

	
	public void setNotOutMinusKeLeaderNum(Integer notOutMinusKeLeaderNum) {
		
		this.notOutMinusKeLeaderNum = notOutMinusKeLeaderNum;
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

	
	public Integer getNotIntoMinusKeNoLeaderNum() {
		
		return notIntoMinusKeNoLeaderNum;
	}

	
	public void setNotIntoMinusKeNoLeaderNum(Integer notIntoMinusKeNoLeaderNum) {
		
		this.notIntoMinusKeNoLeaderNum = notIntoMinusKeNoLeaderNum;
	}

	
	public Integer getNotOutMinusKeNoLeaderNum() {
		
		return notOutMinusKeNoLeaderNum;
	}

	
	public void setNotOutMinusKeNoLeaderNum(Integer notOutMinusKeNoLeaderNum) {
		
		this.notOutMinusKeNoLeaderNum = notOutMinusKeNoLeaderNum;
	}
	
}
