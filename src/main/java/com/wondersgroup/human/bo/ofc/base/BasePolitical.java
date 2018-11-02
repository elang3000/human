/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BasePolitical.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 政治面貌
 * 创建人: jiang
 * 创建时间: 2018年9月7日14:06:32
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月7日14:06:35
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
 * @ClassName: BasePolitical
 * @Description: 国标 政治面貌
 * @author: jiang
 * @date: 2018年9月7日14:06:38
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BasePolitical<T> extends GenericEntity {
	
	private static final long serialVersionUID = 3111024164422003597L;
	
	/**
	 * @fieldName: 政治面貌名称
	 * @fieldType: java.lang.String
	 * @Description: 该人当前政治面貌的中文名称。
	 */
	@Column(name = "A58001A", length = 30)
	private String name;
	
	/**
	 * @fieldName: 政治面貌代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务相对于原任职务变动的类别划分。GB/T 4762-1984
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A58001B")
	private CodeInfo code;
	
	/**
	 * @fieldName: 参加党派日期
	 * @fieldType: java.util.Date
	 * @Description: 参加党派日期。GB/T 7408-2005
	 */
	@Column(name = "A58005")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinPartisanDate;
	
	/**
	 * @fieldName:参加党派时所在单位
	 * @fieldType: java.lang.String
	 * @Description: 参加党派时所在的工作单位名称。
	 */
	@Column(name = "A58015", length = 120)
	private String joinPartisanUnitName;
	
	/**
	 * @fieldName: 政治面貌异动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 在党的历史上出现的入团转党、重新入党、自动脱党等特殊情况的类别。DM016
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A58030")
	private CodeInfo specialChangeCategory;
	
	/**
	 * @fieldName:政治面貌异动原因
	 * @fieldType: java.lang.String
	 * @Description: 不能按期转正或登记的原因。
	 */
	@Column(name = "A58035", length = 48)
	private String specialChangeReason;

	
	public String getName() {
		
		return name;
	}

	
	public void setName(String name) {
		
		this.name = name;
	}

	
	public CodeInfo getCode() {
		
		return code;
	}

	
	public void setCode(CodeInfo code) {
		
		this.code = code;
	}

	
	public Date getJoinPartisanDate() {
		
		return joinPartisanDate;
	}

	
	public void setJoinPartisanDate(Date joinPartisanDate) {
		
		this.joinPartisanDate = joinPartisanDate;
	}

	
	public String getJoinPartisanUnitName() {
		
		return joinPartisanUnitName;
	}

	
	public void setJoinPartisanUnitName(String joinPartisanUnitName) {
		
		this.joinPartisanUnitName = joinPartisanUnitName;
	}

	
	public CodeInfo getSpecialChangeCategory() {
		
		return specialChangeCategory;
	}

	
	public void setSpecialChangeCategory(CodeInfo specialChangeCategory) {
		
		this.specialChangeCategory = specialChangeCategory;
	}

	
	public String getSpecialChangeReason() {
		
		return specialChangeReason;
	}

	
	public void setSpecialChangeReason(String specialChangeReason) {
		
		this.specialChangeReason = specialChangeReason;
	}
	
}
