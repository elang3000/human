/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseProbation.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 试用情况
 * 创建人: jiang
 * 创建时间: 2018年6月11日14:55:08
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月11日14:55:13
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: BaseProbation
 * @Description: 国标 试用情况
 * @author: jiang
 * @date: 2018年6月11日14:55:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseProbation<T> extends GenericEntity {
	
	private static final long serialVersionUID = 6628367497437606898L;
	
	/**
	 * @fieldName: 试用类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 根据录取情况划分的试用类别。如：试用、录用等。DM058
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A04005")
	private CodeInfo type;
	
	/**
	 * @fieldName: 试用单位及职务
	 * @fieldType: java.lang.String
	 * @Description: 试用的单位及职务的名称。
	 */
	@Column(name = "A04006", length = 500)
	private String unitName;
	
	/**
	 * @fieldName: 试用起始日期
	 * @fieldType: java.util.Date
	 * @Description: 试用的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A04010")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	/**
	 * @fieldName: 试用终止日期
	 * @fieldType: java.util.Date
	 * @Description: 试用的截止日期。GB/T 7408-2005
	 */
	@Column(name = "A04015")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	/**
	 * @fieldName: 试用期满考核结论
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 试用结束时，用人单位对被试用人在试用期间工作情况考核结果。DM018
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A04020")
	private CodeInfo conclusion;
	
	/**
	 * @fieldName: 入职转正日期
	 * @fieldType: java.util.Date
	 * @Description: 公务员、干部在试用一年之后的批准转正日期。GB/T 7408-2005
	 */
	@Column(name = "A04025")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date becomeDate;
	
	/**
	 * @fieldName: 入职转正批准文号
	 * @fieldType: java.lang.String
	 * @Description: 公务员、干部在试用一年之后批准转正的批准文号。
	 */
	@Column(name = "A04027", length = 48)
	private String becomeNo;

	
	public CodeInfo getType() {
		
		return type;
	}

	
	public void setType(CodeInfo type) {
		
		this.type = type;
	}

	
	public String getUnitName() {
		
		return unitName;
	}

	
	public void setUnitName(String unitName) {
		
		this.unitName = unitName;
	}

	
	public Date getStartDate() {
		
		return startDate;
	}

	
	public void setStartDate(Date startDate) {
		
		this.startDate = startDate;
	}

	
	public Date getEndDate() {
		
		return endDate;
	}

	
	public void setEndDate(Date endDate) {
		
		this.endDate = endDate;
	}

	
	public CodeInfo getConclusion() {
		
		return conclusion;
	}

	
	public void setConclusion(CodeInfo conclusion) {
		
		this.conclusion = conclusion;
	}

	
	public Date getBecomeDate() {
		
		return becomeDate;
	}

	
	public void setBecomeDate(Date becomeDate) {
		
		this.becomeDate = becomeDate;
	}

	
	public String getBecomeNo() {
		
		return becomeNo;
	}

	
	public void setBecomeNo(String becomeNo) {
		
		this.becomeNo = becomeNo;
	}
	
}
