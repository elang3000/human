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

public class OrgFormationVO {
	
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
	 * @fieldName: 批准编制总数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门核定批准该单位（机构）的人员编制人数。
	 */
	private Integer unitPlanningTotal;
	
	
	public OrgFormationVO() {
		
	}
	
	public OrgFormationVO(OrgFormation orgFormation) {
		this.id = orgFormation.getId();
		this.approveNo = orgFormation.getApproveNo();
		this.approveUnitName = orgFormation.getApproveUnitName();
		this.unitPlanningTotal = orgFormation.getUnitPlanningTotal();
		
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
	
	
	
}
