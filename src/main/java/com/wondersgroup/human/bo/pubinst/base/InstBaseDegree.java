/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseDegree.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 学位
 * 创建人: jiang
 * 创建时间: 2018年6月25日09:02:15
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月25日09:02:19
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.pubinst.base;

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
 * @ClassName: BaseDegree
 * @Description: 国标 学位
 * @author: jiang
 * @date: 2018年6月25日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseDegree<T> extends GenericEntity {
	
	private static final long serialVersionUID = 5960121216839581697L;
	
	/**
	 * @fieldName: 学位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人完成一定学历教育后所取得的学位名称。
	 */
	@Column(name = "A09001A", length = 40)
	private String name;
	
	/**
	 * @fieldName: 学位代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人完成一定学历教育后所取得的学位代码。GB/T 6864-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A09001B")
	private CodeInfo code;
	
	/**
	 * @fieldName: 学位授予日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得的学位证书的签发日期。GB/T 7408-2005
	 */
	@Column(name = "A09004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date grantDate;
	
	/**
	 * @fieldName: 学位授予单位
	 * @fieldType: java.lang.String
	 * @Description: 授予该人学位的单位名称。
	 */
	@Column(name = "A09007", length = 120)
	private String grantUnit;
	
	/**
	 * @fieldName: 学位授予单位所在行政区划
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人学位授予单位所在的当前国家县级及县级以上行政区划。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A09011")
	private CodeInfo grantUnitAreaCode;
	
	/**
	 * @fieldName: 最高学位标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该学位是否为组织认定的该人当前最高学位的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A09014")
	private CodeInfo topFlag;
	
	/**
	 * @fieldName: 学位证书号
	 * @fieldType: java.lang.String
	 * @Description: 取得学位证书的号码。
	 */
	@Column(name = "A09043", length = 20)
	private String degreeNo;
	
	/**
	 * @fieldName: 学位授予国家（地区）
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 授予学位单位的所在国家或地区名称。GB/T 2659-2000
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A09050")
	private CodeInfo grantCountryAreaCode;
	
	/**
	 * @fieldName: 第二学位
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人完成一定学历教育后所取得的首要学位之外的第二个学位的代码。GB/T 6864-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A09061")
	private CodeInfo secondCode;

	
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

	
	public Date getGrantDate() {
		
		return grantDate;
	}

	
	public void setGrantDate(Date grantDate) {
		
		this.grantDate = grantDate;
	}

	
	public String getGrantUnit() {
		
		return grantUnit;
	}

	
	public void setGrantUnit(String grantUnit) {
		
		this.grantUnit = grantUnit;
	}

	
	public CodeInfo getGrantUnitAreaCode() {
		
		return grantUnitAreaCode;
	}

	
	public void setGrantUnitAreaCode(CodeInfo grantUnitAreaCode) {
		
		this.grantUnitAreaCode = grantUnitAreaCode;
	}

	
	public CodeInfo getTopFlag() {
		
		return topFlag;
	}

	
	public void setTopFlag(CodeInfo topFlag) {
		
		this.topFlag = topFlag;
	}

	
	public String getDegreeNo() {
		
		return degreeNo;
	}

	
	public void setDegreeNo(String degreeNo) {
		
		this.degreeNo = degreeNo;
	}

	
	public CodeInfo getGrantCountryAreaCode() {
		
		return grantCountryAreaCode;
	}

	
	public void setGrantCountryAreaCode(CodeInfo grantCountryAreaCode) {
		
		this.grantCountryAreaCode = grantCountryAreaCode;
	}

	
	public CodeInfo getSecondCode() {
		
		return secondCode;
	}

	
	public void setSecondCode(CodeInfo secondCode) {
		
		this.secondCode = secondCode;
	}
	
}
