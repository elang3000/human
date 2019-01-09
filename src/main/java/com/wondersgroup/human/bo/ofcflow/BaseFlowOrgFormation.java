/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseFlowOrgFormation.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 流程模块中展示的编制信息情况
 * 创建人: tzy
 * 创建时间: 2018年12月24日 上午10:09:07
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年12月24日 上午10:09:07
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: BaseFlowOrgFormation
 * @Description: 流程模块中展示的编制信息情况
 * @author: tzy
 * @date: 2018年12月24日 上午10:09:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseFlowOrgFormation<T> extends GenericEntity {
	
	private static final long serialVersionUID = 7000337337463629148L;
	
	/**
	 * @fieldName: 批准编制总数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门核定批准该单位（机构）的人员编制人数。
	 */
	@Column(name = "B02024", length = 2)
	private Integer unitPlanningTotal;
	
	/**
	 * @fieldName: 实有人数
	 * @fieldType: java.lang.Integer
	 * @Description: 本机构内占编制的实有人数。
	 */
	@Column(name = "B02110", length = 2)
	private Integer actualNumber;
	
	/**
	 * @fieldName: 空编人数
	 * @fieldType: java.lang.Integer
	 * @Description: 本机构内空编或超编人数。
	 */
	@Column(name = "B02115", length = 2)
	private Integer vacancyExcessNumber;
	
	/**
	 * @fieldName: notIntoNum
	 * @fieldType: java.lang.Integer
	 * @Description: 供给关系尚未调入人员
	 */
	@Column(name = "NOT_INTO_NUM")
	private Integer notIntoNum;
	
	/**
	 * @fieldName: notOutNum
	 * @fieldType: java.lang.Integer
	 * @Description: 供给关系尚未调出人员
	 */
	@Column(name = "NOT_OUT_NUM")
	private Integer notOutNum;
	
	/**
	 * @fieldName: approveChuNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 处级人数
	 */
	@Column(name = "CHU_A")
	private Integer approveChuNum;
	
	/**
	 * @fieldName: realChuNum
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 处级人数
	 */
	@Column(name = "CHU_B")
	private Integer realChuNum;
	
	/**
	 * @fieldName: approvePlusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 正科级领导人数
	 */
	@Column(name = "PLUS_KE_LEADER_A")
	private Integer approvePlusKeLeaderNum;
	
	/**
	 * @fieldName: realPlusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 正科级领导人数
	 */
	@Column(name = "PLUS_KE_LEADER_B")
	private Integer realPlusKeLeaderNum;
	
	/**
	 * @fieldName: notIntoPlusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调入 正科级领导人数
	 */
	@Column(name = "NOT_INTO_PLUS_KE_LEADER")
	private Integer notIntoPlusKeLeaderNum;
	
	/**
	 * @fieldName: notOutPlusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调出 正科级领导人数
	 */
	@Column(name = "NOT_OUT_PLUS_KE_LEADER")
	private Integer notOutPlusKeLeaderNum;
	
	/**
	 * @fieldName: approvePlusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 正科级非领导人数
	 */
	@Column(name = "PLUS_KE_NOLEADER_A")
	private Integer approvePlusKeNoLeaderNum;
	
	/**
	 * @fieldName: realPlusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 正科级非领导人数
	 */
	@Column(name = "PLUS_KE_NOLEADER_B")
	private Integer realPlusKeNoLeaderNum;
	
	/**
	 * @fieldName: notIntoPlusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调入 正科级非领导人数
	 */
	@Column(name = "NOT_INTO_PLUS_KE_NOLEADER")
	private Integer notIntoPlusKeNoLeaderNum;
	
	/**
	 * @fieldName: notOutPlusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调出 正科级非领导人数
	 */
	@Column(name = "NOT_OUT_PLUS_KE_NOLEADER")
	private Integer notOutPlusKeNoLeaderNum;
	
	/**
	 * @fieldName: approveMinusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 副科级领导人数
	 */
	@Column(name = "MINUS_KE_LEADER_A")
	private Integer approveMinusKeLeaderNum;
	
	/**
	 * @fieldName: realMinusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 副科级领导人数
	 */
	@Column(name = "MINUS_KE_LEADER_B")
	private Integer realMinusKeLeaderNum;
	
	/**
	 * @fieldName: notIntoMinusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调入 副科级领导人数
	 */
	@Column(name = "NOT_INTO_MINUS_KE_LEADER")
	private Integer notIntoMinusKeLeaderNum;
	
	/**
	 * @fieldName: notOutMinusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调出 副科级领导人数
	 */
	@Column(name = "NOT_OUT_MINUS_KE_LEADER")
	private Integer notOutMinusKeLeaderNum;
	
	/**
	 * @fieldName: approveMinusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 副科级非领导人数
	 */
	@Column(name = "MINUS_KE_NOLEADER_A")
	private Integer approveMinusKeNoLeaderNum;
	
	/**
	 * @fieldName: realMinusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 副科级非领导人数
	 */
	@Column(name = "MINUS_KE_NOLEADER_B")
	private Integer realMinusKeNoLeaderNum;
	
	/**
	 * @fieldName: notIntoMinusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调入 副科级非领导人数
	 */
	@Column(name = "NOT_INTO_MINUS_KE_NOLEADER")
	private Integer notIntoMinusKeNoLeaderNum;
	
	/**
	 * @fieldName: notOutMinusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description: 未调出 副科级非领导人数
	 */
	@Column(name = "NOT_OUT_MINUS_KE_NOLEADER")
	private Integer notOutMinusKeNoLeaderNum;
	
	public Integer getActualNumber() {
		
		return actualNumber;
	}
	
	public void setActualNumber(Integer actualNumber) {
		
		this.actualNumber = actualNumber;
	}
	
	public Integer getVacancyExcessNumber() {
		
		return vacancyExcessNumber;
	}
	
	public void setVacancyExcessNumber(Integer vacancyExcessNumber) {
		
		this.vacancyExcessNumber = vacancyExcessNumber;
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
	
	public Integer getUnitPlanningTotal() {
		
		return unitPlanningTotal;
	}
	
	public void setUnitPlanningTotal(Integer unitPlanningTotal) {
		
		this.unitPlanningTotal = unitPlanningTotal;
	}
	
}
