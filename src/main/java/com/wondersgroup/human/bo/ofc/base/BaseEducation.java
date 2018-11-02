/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseEducation.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 学历
 * 创建人: jiang
 * 创建时间: 2018年6月22日14:31:45
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月22日14:31:50
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
 * @ClassName: BaseEducation
 * @Description: 国标 学历
 * @author: jiang
 * @date: 2018年6月22日14:32:05
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseEducation<T> extends GenericEntity {
	
	private static final long serialVersionUID = 8353483665914441174L;
	
	/**
	 * @fieldName: 学历名称
	 * @fieldType: java.lang.String
	 * @Description: 由国家认可的该人在国内、外各类教育机构接受正式教育并取得学历证书的学习经历名称。
	 */
	@Column(name = "A08002A", length = 40)
	private String name;
	
	/**
	 * @fieldName: 学历代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 由国家认可的该人在国内、外各类教育机构接受正式教育并取得学历证书的学习经历代码。GB/T 4658-2006
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A08002B")
	private CodeInfo code;
	
	/**
	 * @fieldName: 入学日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得学历学习的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A08004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enterDate;
	
	/**
	 * @fieldName: 学历证书号
	 * @fieldType: java.lang.String
	 * @Description: 取得学历证书号码。
	 */
	@Column(name = "A08006", length = 20)
	private String educationNo;
	
	/**
	 * @fieldName: 毕（肄、结）业日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得学历学习的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A08007")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date graduateDate;
	
	/**
	 * @fieldName: 学制
	 * @fieldType: java.lang.String
	 * @Description: 接受学历教育在校学习完成学业的规定年限。
	 */
	@Column(name = "A08011", length = 20)
	private String eductionalSystem;
	
	/**
	 * @fieldName: 学校（单位）名称
	 * @fieldType: java.lang.String
	 * @Description: 该人接受该阶段教育并获得有关证书时的学校、院系或单位名称。
	 */
	@Column(name = "A08014", length = 120)
	private String shoolName;
	
	/**
	 * @fieldName: 学校（单位）所在行政区划
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人学习的学校（单位）所在的当前国家县级及县级以上行政区划。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A08017")
	private CodeInfo shoolAreaCode;
	
	/**
	 * @fieldName:教育类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 取得学历学习的方式。DM024
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A08020")
	private CodeInfo eductionalType;
	
	/**
	 * @fieldName:学历从学单位类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该阶段学习的从学单位的类别。DM022
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A08021")
	private CodeInfo unitType;
	
	/**
	 * @fieldName: 所学专业名称
	 * @fieldType: java.lang.String
	 * @Description: 该人取得学历所学专业的具体名称。
	 */
	@Column(name = "A08024", length = 80)
	private String professionName;
	
	/**
	 * @fieldName: 所学专业类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人取得学历所学专业的分类。GB/T 16835-1997
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A08027")
	private CodeInfo professionCode;
	
	/**
	 * @fieldName: 学历学习完成情况
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该阶段学习完成情况。DM025
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A08031")
	private CodeInfo complete;
	
	/**
	 * @fieldName: 最高学历标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该学历是否为组织认定的该人当前最高学历的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A08034")
	private CodeInfo topFlag;
	
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
	
	public Date getEnterDate() {
		
		return enterDate;
	}
	
	public void setEnterDate(Date enterDate) {
		
		this.enterDate = enterDate;
	}
	
	public String getEducationNo() {
		
		return educationNo;
	}
	
	public void setEducationNo(String educationNo) {
		
		this.educationNo = educationNo;
	}
	
	public Date getGraduateDate() {
		
		return graduateDate;
	}
	
	public void setGraduateDate(Date graduateDate) {
		
		this.graduateDate = graduateDate;
	}
	
	public String getEductionalSystem() {
		
		return eductionalSystem;
	}
	
	public void setEductionalSystem(String eductionalSystem) {
		
		this.eductionalSystem = eductionalSystem;
	}
	
	public String getShoolName() {
		
		return shoolName;
	}
	
	public void setShoolName(String shoolName) {
		
		this.shoolName = shoolName;
	}
	
	public CodeInfo getShoolAreaCode() {
		
		return shoolAreaCode;
	}
	
	public void setShoolAreaCode(CodeInfo shoolAreaCode) {
		
		this.shoolAreaCode = shoolAreaCode;
	}
	
	public CodeInfo getEductionalType() {
		
		return eductionalType;
	}
	
	public void setEductionalType(CodeInfo eductionalType) {
		
		this.eductionalType = eductionalType;
	}
	
	public CodeInfo getUnitType() {
		
		return unitType;
	}
	
	public void setUnitType(CodeInfo unitType) {
		
		this.unitType = unitType;
	}
	
	public String getProfessionName() {
		
		return professionName;
	}
	
	public void setProfessionName(String professionName) {
		
		this.professionName = professionName;
	}
	
	public CodeInfo getProfessionCode() {
		
		return professionCode;
	}
	
	public void setProfessionCode(CodeInfo professionCode) {
		
		this.professionCode = professionCode;
	}
	
	public CodeInfo getComplete() {
		
		return complete;
	}
	
	public void setComplete(CodeInfo complete) {
		
		this.complete = complete;
	}
	
	public CodeInfo getTopFlag() {
		
		return topFlag;
	}
	
	public void setTopFlag(CodeInfo topFlag) {
		
		this.topFlag = topFlag;
	}
	
}
