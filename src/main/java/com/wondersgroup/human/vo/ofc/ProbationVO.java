/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProbationVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-职务VO
 * 创建人: jiang
 * 创建时间: 2018年6月11日15:34:53
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月11日15:34:58
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Probation;

/**
 * @ClassName: ProbationVO
 * @Description: 人员信息子表-录聘VO
 * @author: jiang
 * @date: 2018年6月8日14:50:53
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ProbationVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 试用类别
	 * @fieldType: java.lang.String
	 * @Description: 根据录取情况划分的试用类别。如：试用、录用等。DM058
	 */
	private String type;
	
	/**
	 * @fieldName: 试用单位及职务
	 * @fieldType: java.lang.String
	 * @Description: 试用的单位及职务的名称。
	 */
	private String unitName;
	
	/**
	 * @fieldName: 试用起始日期
	 * @fieldType: java.lang.String
	 * @Description: 试用的起始日期。GB/T 7408-2005
	 */
	private String startDate;
	
	/**
	 * @fieldName: 试用终止日期
	 * @fieldType: java.lang.String
	 * @Description: 试用的截止日期。GB/T 7408-2005
	 */
	private String endDate;
	
	/**
	 * @fieldName: 试用期满考核结论
	 * @fieldType: java.lang.String
	 * @Description: 试用结束时，用人单位对被试用人在试用期间工作情况考核结果。DM018
	 */
	private String conclusion;
	
	/**
	 * @fieldName: 入职转正日期
	 * @fieldType: java.util.Date
	 * @Description: 公务员、干部在试用一年之后的批准转正日期。GB/T 7408-2005
	 */
	private String becomeDate;
	
	/**
	 * @fieldName: 入职转正批准文号
	 * @fieldType: java.lang.String
	 * @Description: 公务员、干部在试用一年之后批准转正的批准文号。
	 */
	private String becomeNo;
	
	public ProbationVO() {
		
	}
	
	/**
	 * @Title:EmployVO
	 * @Description:将Employ bo转换为vo
	 * @param s 转换的Employ BO对象
	 */
	public ProbationVO(Probation s) {
		this.id = s.getId();
		this.unitName = s.getUnitName();
		this.becomeNo = s.getBecomeNo();
		
		if (s.getType() != null) {
			this.type = s.getType().getName();
		}
		if (s.getConclusion() != null) {
			this.conclusion = s.getConclusion().getName();
		}
		
		if (s.getStartDate() != null) {
			this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(s.getStartDate());
		}
		if (s.getEndDate() != null) {
			this.endDate = new SimpleDateFormat("yyyy-MM-dd").format(s.getEndDate());
		}
		if (s.getBecomeDate() != null) {
			this.becomeDate = new SimpleDateFormat("yyyy-MM-dd").format(s.getBecomeDate());
		}
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getType() {
		
		return type;
	}

	
	public void setType(String type) {
		
		this.type = type;
	}

	
	public String getUnitName() {
		
		return unitName;
	}

	
	public void setUnitName(String unitName) {
		
		this.unitName = unitName;
	}

	
	public String getStartDate() {
		
		return startDate;
	}

	
	public void setStartDate(String startDate) {
		
		this.startDate = startDate;
	}

	
	public String getEndDate() {
		
		return endDate;
	}

	
	public void setEndDate(String endDate) {
		
		this.endDate = endDate;
	}

	
	public String getConclusion() {
		
		return conclusion;
	}

	
	public void setConclusion(String conclusion) {
		
		this.conclusion = conclusion;
	}

	
	public String getBecomeDate() {
		
		return becomeDate;
	}

	
	public void setBecomeDate(String becomeDate) {
		
		this.becomeDate = becomeDate;
	}

	
	public String getBecomeNo() {
		
		return becomeNo;
	}

	
	public void setBecomeNo(String becomeNo) {
		
		this.becomeNo = becomeNo;
	}
	
}
