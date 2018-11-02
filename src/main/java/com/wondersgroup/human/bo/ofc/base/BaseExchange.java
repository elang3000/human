/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseExchange.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 交流情况
 * 创建人: jiang
 * 创建时间: 2018年9月10日15:11:58
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日15:12:00
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
 * @ClassName: BaseExchange
 * @Description: 国标 交流情况
 * @author: jiang
 * @date: 2018年9月10日15:12:02
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseExchange<T> extends GenericEntity {
	
	private static final long serialVersionUID = 8452352101641987187L;
	
	/**
	 * @fieldName: 交流方式
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人交流任职的方式。DM192
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49001")
	private CodeInfo mode;
	
	/**
	 * @fieldName: 交流原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人交流任职的原因。DM083
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49004")
	private CodeInfo reason;
	
	/**
	 * @fieldName: 交流去向
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人交流任职的去向。DM082
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49007")
	private CodeInfo whereabouts;
	
	/**
	 * @fieldName: 交流类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员实行交流制度。交流类别分为调任、转任、轮换和挂职锻炼。DM014
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49011")
	private CodeInfo category;
	
	/**
	 * @fieldName: 交流任职情况
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人交流任职的情况。DM071
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49014")
	private CodeInfo tenureSituation;
	
	/**
	 * @fieldName: 交流开始日期
	 * @fieldType: java.util.Date
	 * @Description: 该人该次交流任职开始的日期。GB/T 7408-2005
	 */
	@Column(name = "A49017")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	/**
	 * @fieldName: 交流结束日期
	 * @fieldType: java.util.Date
	 * @Description: 该人该次交流任职结束的日期。GB/T 7408-2005
	 */
	@Column(name = "A49021")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	/**
	 * @fieldName: 交流前职务级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该次交流前所任职务级别。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49025")
	private CodeInfo beforePostLevel;
	
	/**
	 * @fieldName: 交流目的单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人该次交流时所调入的单位名称。
	 */
	@Column(name = "A49027A", length = 120)
	private String destinationUnitName;
	
	/**
	 * @fieldName: 交流目的单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人该次交流时所调入的单位代码。GB 32100-2015
	 */
	@Column(name = "A49027B", length = 18)
	private String destinationUnitCode;
	
	/**
	 * @fieldName: 交流目的单位类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该次交流时所调入的单位的性质类别的划分。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49028")
	private CodeInfo destinationUnitCategory;
	
	/**
	 * @fieldName: 交流前单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人该次交流时所调出的单位名称。
	 */
	@Column(name = "A49031A", length = 120)
	private String beforeUnitName;
	
	/**
	 * @fieldName: 交流前单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人该次交流时所调出的单位代码。GB 32100-2015
	 */
	@Column(name = "A49031B", length = 18)
	private String beforeUnitCode;
	
	/**
	 * @fieldName: 交流担任职务类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员交流时所担任职务的类型，划分为党内职务、行政职务、其他党派职务、社会团体职务。DM049
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49045")
	private CodeInfo bearPostCategory;
	
	/**
	 * @fieldName: 交流性质
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 表示公务员交流性质的代码。DM221
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49047")
	private CodeInfo nature;
	
	/**
	 * @fieldName: 回避原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员因何故回避。DM064
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A49049")
	private CodeInfo evasiveReason;
	
	/**
	 * @fieldName: 回避日期
	 * @fieldType: java.util.Date
	 * @Description: 公务员回避的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A49053")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date evasiveDate;
	
	public CodeInfo getMode() {
		
		return mode;
	}
	
	public void setMode(CodeInfo mode) {
		
		this.mode = mode;
	}
	
	public CodeInfo getReason() {
		
		return reason;
	}
	
	public void setReason(CodeInfo reason) {
		
		this.reason = reason;
	}
	
	public CodeInfo getWhereabouts() {
		
		return whereabouts;
	}
	
	public void setWhereabouts(CodeInfo whereabouts) {
		
		this.whereabouts = whereabouts;
	}
	
	public CodeInfo getCategory() {
		
		return category;
	}
	
	public void setCategory(CodeInfo category) {
		
		this.category = category;
	}
	
	public CodeInfo getTenureSituation() {
		
		return tenureSituation;
	}
	
	public void setTenureSituation(CodeInfo tenureSituation) {
		
		this.tenureSituation = tenureSituation;
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
	
	public CodeInfo getBeforePostLevel() {
		
		return beforePostLevel;
	}
	
	public void setBeforePostLevel(CodeInfo beforePostLevel) {
		
		this.beforePostLevel = beforePostLevel;
	}
	
	public String getDestinationUnitName() {
		
		return destinationUnitName;
	}
	
	public void setDestinationUnitName(String destinationUnitName) {
		
		this.destinationUnitName = destinationUnitName;
	}
	
	public String getDestinationUnitCode() {
		
		return destinationUnitCode;
	}
	
	public void setDestinationUnitCode(String destinationUnitCode) {
		
		this.destinationUnitCode = destinationUnitCode;
	}
	
	public CodeInfo getDestinationUnitCategory() {
		
		return destinationUnitCategory;
	}
	
	public void setDestinationUnitCategory(CodeInfo destinationUnitCategory) {
		
		this.destinationUnitCategory = destinationUnitCategory;
	}
	
	public String getBeforeUnitName() {
		
		return beforeUnitName;
	}
	
	public void setBeforeUnitName(String beforeUnitName) {
		
		this.beforeUnitName = beforeUnitName;
	}
	
	public String getBeforeUnitCode() {
		
		return beforeUnitCode;
	}
	
	public void setBeforeUnitCode(String beforeUnitCode) {
		
		this.beforeUnitCode = beforeUnitCode;
	}
	
	public CodeInfo getBearPostCategory() {
		
		return bearPostCategory;
	}
	
	public void setBearPostCategory(CodeInfo bearPostCategory) {
		
		this.bearPostCategory = bearPostCategory;
	}
	
	public CodeInfo getNature() {
		
		return nature;
	}
	
	public void setNature(CodeInfo nature) {
		
		this.nature = nature;
	}
	
	public CodeInfo getEvasiveReason() {
		
		return evasiveReason;
	}
	
	public void setEvasiveReason(CodeInfo evasiveReason) {
		
		this.evasiveReason = evasiveReason;
	}
	
	public Date getEvasiveDate() {
		
		return evasiveDate;
	}
	
	public void setEvasiveDate(Date evasiveDate) {
		
		this.evasiveDate = evasiveDate;
	}
	
}
