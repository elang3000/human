/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgFormationVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.organization
 * 描述: TODO
 * 创建人: jiang
 * 创建时间: 2018年9月19日11:06:47
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月19日11:06:50
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.vo.organization;

import java.text.SimpleDateFormat;

import javax.persistence.Column;

import com.wondersgroup.human.bo.organization.OrgFormation;

/**
 * @ClassName: OrgFormationVO
 * @Description: TODO
 * @author: jiang
 * @date: 2018年9月19日11:06:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */

public class OrgFormationCountVO {
	
	private String id;
	
	/**
	 * @fieldName: 批准编制日期
	 * @fieldType: java.util.Date
	 * @Description: 由上级编制部门批准编制的日期。GB/T 7408-2005
	 */
	private String approveDate;
	
	/**
	 * @fieldName: 批准编制文号
	 * @fieldType: java.lang.String
	 * @Description: 批准编制的正式批准文件的文号。
	 */
	private String approveNo;
	
	/**
	 * @fieldName: 编制批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准编制的上级编制部门的单位名称。
	 */
	private String approveUnitName;
	
	/**
	 * @fieldName: 编制性质
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 占该单位主体部分的人员编制的类别划分。DM143
	 */
	private String unitPlanningProperty;
	
	/**
	 * @fieldName: 核定批准编制总数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门核定批准该单位（机构）的人员编制人数。
	 */
	private Integer unitPlanningTotal;
	
	/**
	 * @fieldName: 单位名称
	 * @fieldType: java.lang.String
	 * @Description: 单位名称。
	 */
	private String unitBasicSimpleName;
	
	/**
	 * @fieldName: 实有人数
	 * @fieldType: java.lang.Integer
	 * @Description: 本机构内占编制的实有人数。
	 */
	private Integer actualNumber;
	
	/**
	 * @fieldName: 处级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内处级以及与处级相当职级的人员总数。
	 */
	private Integer divisionChiefLevelNumber;
	
	/**
	 * @fieldName: 科级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内科级以及与科级相当职级的人员总数。
	 */
	private Integer sectionChiefLevelNumber;
	
	/**
	 * @fieldName: 副科级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内副科级以及与副科级相当职级的人员总数。
	 */
	private Integer deputySectionChiefLevelNumber;
	
	/**
	 * @fieldName: 科员、办事员及其他人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内科员、办事员及其他人员总数。
	 */
	private Integer staffMembersNumber;

	/**
	 * @fieldName: 实有 科级及相当者人数（非领导）
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 单位内科级以及与科级相当职级的人员总数（非领导）。
	 */
	private Integer nonLeaderSectionChiefLevelNumber;

	/**
	 * 供给关系尚未调入人员
	 */
	private Integer notIntoNum = 0;
	
	
	public OrgFormationCountVO() {
		
	}
	
	public OrgFormationCountVO(OrgFormation orgFormation) {
		this.id = orgFormation.getOrgInfo().getOrgan().getId();
		this.approveNo = orgFormation.getApproveNo();
		this.approveUnitName = orgFormation.getApproveUnitName();
		this.unitPlanningTotal = orgFormation.getUnitPlanningTotal();
		this.actualNumber = orgFormation.getActualNumber();
		this.divisionChiefLevelNumber = orgFormation.getDivisionChiefLevelNumber();
		this.sectionChiefLevelNumber = orgFormation.getSectionChiefLevelNumber();
		this.nonLeaderSectionChiefLevelNumber=orgFormation.getNonLeaderSectionChiefLevelNumber();
		this.notIntoNum=orgFormation.getNotIntoNum();
		if(orgFormation.getOrgInfo()!=null)
			this.unitBasicSimpleName=orgFormation.getOrgInfo().getUnitBasicName();
		
		if(orgFormation.getApproveDate() !=null){
			this.approveDate = new SimpleDateFormat("yyyy-MM-dd").format(orgFormation.getApplyDate());
		}
		if(orgFormation.getUnitPlanningProperty()!= null){
			this.unitPlanningProperty = orgFormation.getUnitPlanningProperty().getName();
		}
		
	}


	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getApproveDate() {
		
		return approveDate;
	}

	
	public void setApproveDate(String approveDate) {
		
		this.approveDate = approveDate;
	}

	
	public String getApproveNo() {
		
		return approveNo;
	}

	
	public void setApproveNo(String approveNo) {
		
		this.approveNo = approveNo;
	}

	
	public String getApproveUnitName() {
		
		return approveUnitName;
	}

	
	public void setApproveUnitName(String approveUnitName) {
		
		this.approveUnitName = approveUnitName;
	}

	
	public String getUnitPlanningProperty() {
		
		return unitPlanningProperty;
	}

	
	public void setUnitPlanningProperty(String unitPlanningProperty) {
		
		this.unitPlanningProperty = unitPlanningProperty;
	}

	
	public Integer getUnitPlanningTotal() {
		
		return unitPlanningTotal;
	}

	
	public void setUnitPlanningTotal(Integer unitPlanningTotal) {
		
		this.unitPlanningTotal = unitPlanningTotal;
	}

	public String getUnitBasicSimpleName() {
		
		return unitBasicSimpleName;
	}

	public void setUnitBasicSimpleName(String unitBasicSimpleName) {
		
		this.unitBasicSimpleName = unitBasicSimpleName;
	}

	
	public Integer getActualNumber() {
		
		return actualNumber;
	}

	
	public void setActualNumber(Integer actualNumber) {
		
		this.actualNumber = actualNumber;
	}

	
	public Integer getDivisionChiefLevelNumber() {
		
		return divisionChiefLevelNumber;
	}

	
	public void setDivisionChiefLevelNumber(Integer divisionChiefLevelNumber) {
		
		this.divisionChiefLevelNumber = divisionChiefLevelNumber;
	}

	
	public Integer getSectionChiefLevelNumber() {
		
		return sectionChiefLevelNumber;
	}

	
	public void setSectionChiefLevelNumber(Integer sectionChiefLevelNumber) {
		
		this.sectionChiefLevelNumber = sectionChiefLevelNumber;
	}

	
	public Integer getDeputySectionChiefLevelNumber() {
		
		return deputySectionChiefLevelNumber;
	}

	
	public void setDeputySectionChiefLevelNumber(Integer deputySectionChiefLevelNumber) {
		
		this.deputySectionChiefLevelNumber = deputySectionChiefLevelNumber;
	}

	
	public Integer getStaffMembersNumber() {
		//实有人数-处级实有-科级实有-科级非领导实有
		return this.actualNumber-divisionChiefLevelNumber-sectionChiefLevelNumber-nonLeaderSectionChiefLevelNumber;
	}

	
	public void setStaffMembersNumber(Integer staffMembersNumber) {
		
		this.staffMembersNumber = staffMembersNumber;
	}


	public Integer getNonLeaderSectionChiefLevelNumber() {
		return nonLeaderSectionChiefLevelNumber;
	}

	public void setNonLeaderSectionChiefLevelNumber(Integer nonLeaderSectionChiefLevelNumber) {
		this.nonLeaderSectionChiefLevelNumber = nonLeaderSectionChiefLevelNumber;
	}

	public Integer getNotIntoNum() {
		return notIntoNum;
	}

	public void setNotIntoNum(Integer notIntoNum) {
		this.notIntoNum = notIntoNum;
	}
}
