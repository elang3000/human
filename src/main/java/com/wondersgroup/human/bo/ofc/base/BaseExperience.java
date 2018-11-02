/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseExperience.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 简历（工作经历）
 * 创建人: jiang
 * 创建时间: 2018年6月26日10:11:18
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月26日10:11:21
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
 * @ClassName: BaseExperience
 * @Description: 国标 简历（工作经历）
 * @author: jiang
 * @date: 2018年6月26日10:11:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseExperience<T> extends GenericEntity {
	
	private static final long serialVersionUID = -3371520482047679359L;
	
	
	/**
	 * @fieldName: 简历
	 * @fieldType: java.lang.String
	 * @Description: 对该人工作、学习简要经历及主要业绩的文字描述，包括起止年月、所在单位、担任职务或从事工作等内容。
	 */
	@Column(name = "A17001", length = 2000)
	private String introduce;
	
	/**
	 * @fieldName: 曾在单位起始日期
	 * @fieldType: java.util.Date
	 * @Description: 在某一单位（部门）工作的开始日期。GB/T 7408-2005
	 */
	@Column(name = "A17005")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	/**
	 * @fieldName: 曾在单位终止日期
	 * @fieldType: java.util.Date
	 * @Description: 在某一单位（部门）工作的结束日期。GB/T 7408-2005
	 */
	@Column(name = "A17010")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	
	/**
	 * @fieldName: 曾在单位
	 * @fieldType: java.lang.String
	 * @Description: 在某一日期内具体工作单位的名称。
	 */
	@Column(name = "A17015", length = 1000)
	private String formerUnit;
	
	
	/**
	 * @fieldName: 曾在单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人曾在单位的代码。GB 32100-2015
	 */
	@Column(name = "A17016", length = 40)
	private String formerUnitCode;
	
	
	/**
	 * @fieldName: 曾经从事工作或担任职务
	 * @fieldType: java.lang.String
	 * @Description: 在某一单位（部门）工作（学习）期间所从事的工作或担任的职务。
	 */
	@Column(name = "A17020", length = 1000)
	private String formerJob;
	
	/**
	 * @fieldName: 曾在单位职务类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人曾在单位担任的职务类别。DM049
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A17022")
	private CodeInfo formerJobCategory;
	
	
	/**
	 * @fieldName: 简历证明人
	 * @fieldType: java.lang.String
	 * @Description: 能够证明该人在某单位从事学习、工作及担任某种职务的人员姓名。
	 */
	@Column(name = "A17025", length = 50)
	private String reterence;


	
	public String getIntroduce() {
		
		return introduce;
	}


	
	public void setIntroduce(String introduce) {
		
		this.introduce = introduce;
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


	
	public String getFormerUnit() {
		
		return formerUnit;
	}


	
	public void setFormerUnit(String formerUnit) {
		
		this.formerUnit = formerUnit;
	}


	
	public String getFormerUnitCode() {
		
		return formerUnitCode;
	}


	
	public void setFormerUnitCode(String formerUnitCode) {
		
		this.formerUnitCode = formerUnitCode;
	}


	
	public String getFormerJob() {
		
		return formerJob;
	}


	
	public void setFormerJob(String formerJob) {
		
		this.formerJob = formerJob;
	}


	
	public CodeInfo getFormerJobCategory() {
		
		return formerJobCategory;
	}


	
	public void setFormerJobCategory(CodeInfo formerJobCategory) {
		
		this.formerJobCategory = formerJobCategory;
	}


	
	public String getReterence() {
		
		return reterence;
	}


	
	public void setReterence(String reterence) {
		
		this.reterence = reterence;
	}
	
	
}
