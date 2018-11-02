/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseUnitInfo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 单位基本情况
 * 创建人: jiang
 * 创建时间: 2018年9月11日11:00:30
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月11日11:00:33
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.organization.base;

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
 * @ClassName: BaseUnitInfo
 * @Description: 国标 单位基本情况
 * @author: jiang
 * @date: 2018年9月11日11:00:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseUnitInfo<T> extends GenericEntity {
	
	private static final long serialVersionUID = -8047509261176451496L;
	
	/**
	 * @fieldName: 单位名称
	 * @fieldType: java.lang.String
	 * @Description: 单位的全称。
	 */
	@Column(name = "B01001", length = 120)
	private String unitBasicName;
	
	/**
	 * @fieldName: 单位简称
	 * @fieldType: java.lang.String
	 * @Description: 该单位规范化的简称。
	 */
	@Column(name = "B01004", length = 120)
	private String unitBasicSimpleName;
	
	/**
	 * @fieldName: 单位缩名
	 * @fieldType: java.lang.String
	 * @Description: 该单位的缩写名称代号，拼音字母或英文字符的缩写。
	 */
	@Column(name = "B01007", length = 120)
	private String unitBasicShortName;
	
	/**
	 * @fieldName: 统一社会信用代码
	 * @fieldType: java.lang.String
	 * @Description: 由 GB 32100 规定的统一社会信用代码。GB 32100-2015
	 */
	@Column(name = "B01014", length = 18)
	private String xydm;
	
	/**
	 * @fieldName: 单位所在行政区划
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该单位所在的（或注册地所在的）当前国家县级及县级以上行政区划的代码。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01017")
	private CodeInfo unitDistrict;
	
	/**
	 * @fieldName: 经济类型
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 按单位所有制性质和经营方式划分的类别。GB/T 12402-2000
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01023")
	private CodeInfo jjlx;
	
	/**
	 * @fieldName: 单位隶属关系层次
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该单位按国家行政分级管理的系统隶属层次。GB/T 12404-1997
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01025")
	private CodeInfo unitSubjectionLevel;
	
	/**
	 * @fieldName: 单位级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 经机构编制管理部门批准的该单位的级别。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01027")
	private CodeInfo unitLevel;
	
	/**
	 * @fieldName: 附属关系类型
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 单位属于国务院直属局、部委管理的国家局以及委、厅下局的情况。DM046
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01028")
	private CodeInfo unitSubsidiary;
	
	/**
	 * @fieldName: 单位驻在国家
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 单位所在国家。GB/T 2659-2000
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01029")
	private CodeInfo permanentState;
	
	/**
	 * @fieldName: 单位性质类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该单位性质类别的划分。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01031")
	private CodeInfo unitPropertyLevel;
	
	/**
	 * @fieldName: 单位所属行业
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该单位所属的国民经济行业类别。GB/T 4754-2011
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01034")
	private CodeInfo unitIndustry;
	
	/**
	 * @fieldName: 单位工作职能
	 * @fieldType: java.lang.String
	 * @Description: 该单位的职责范围及工作内容。GB 32100-2015
	 */
	@Column(name = "B01037", length = 2000)
	private String unitWorkFunction;
	
	/**
	 * @fieldName: 35 岁以下在岗职工数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位职工中 35 岁以下的在岗职工总人数。
	 */
	@Column(name = "B01040", length = 2)
	private Integer belowtfNum;
	
	/**
	 * @fieldName: 在岗职工数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位的在岗职工的总人数。
	 */
	@Column(name = "B01042", length = 2)
	private Integer onguardNum;
	
	/**
	 * @fieldName: 在岗职工中工人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位在岗职工中的工人总数。
	 */
	@Column(name = "B01043", length = 2)
	private Integer onguardWorkerNum;
	
	/**
	 * @fieldName: 隶属单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人在行政关系上所隶属于的上级主管单位名称。
	 */
	@Column(name = "B01044A", length = 120)
	private String unitSubordinateName;
	
	/**
	 * @fieldName: 隶属单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人在行政关系上所隶属于的上级主管单位代码。GB 32100-2015
	 */
	@Column(name = "B01044B", length = 18)
	private String unitSubordinateCode;
	
	/**
	 * @fieldName: 在岗专业技术人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位在岗的专业技术人员的总数。
	 */
	@Column(name = "B01045", length = 2)
	private Integer professionalTechnicianNum;
	
	/**
	 * @fieldName: 领导班子主管单位名称
	 * @fieldType: java.lang.String
	 * @Description: 主管该单位领导班子组成人员职务任免的单位名称。
	 */
	@Column(name = "B01051A", length = 120)
	private String unitLeaderDirectorName;
	
	/**
	 * @fieldName: 领导班子主管单位代码
	 * @fieldType: java.lang.String
	 * @Description: 主管该单位领导班子组成人员职务任免的单位代码。GB 32100-2015
	 */
	@Column(name = "B01051B", length = 18)
	private String unitLeaderDirectorCode;
	
	/**
	 * @fieldName: 领导班子协管单位名称
	 * @fieldType: java.lang.String
	 * @Description: 协助管理该单位领导班子组成人员职务任免的单位名称。
	 */
	@Column(name = "B01054A", length = 120)
	private String unitLeaderAssistName;
	
	/**
	 * @fieldName: 领导班子协管单位代码
	 * @fieldType: java.lang.String
	 * @Description: 协助管理该单位领导班子组成人员职务任免的单位代码。GB 32100-2015
	 */
	@Column(name = "B01054B", length = 18)
	private String unitLeaderAssistCode;
	
	/**
	 * @fieldName: 归口管理单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该单位所归口于的行业管理的单位名称。
	 */
	@Column(name = "B01057A", length = 120)
	private String unitReturnManagementName;
	
	/**
	 * @fieldName: 归口管理单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该单位所归口于的行业管理的单位代码。GB 32100-2015
	 */
	@Column(name = "B01057B", length = 18)
	private String unitReturnManagementCode;
	
	/**
	 * @fieldName: 单位负责人
	 * @fieldType: java.lang.String
	 * @Description: 该单位的法人或主要负责人的姓名全称。GB 32100-2015
	 */
	@Column(name = "B01061", length = 120)
	private String corporation;
	
	/**
	 * @fieldName: 批准成立日期
	 * @fieldType: java.util.Date
	 * @Description: 本机构被批准的成立日期。GB/T 7408-2005
	 */
	@Column(name = "B01064")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approveDate;
	
	/**
	 * @fieldName: 批准成立文号
	 * @fieldType: java.lang.String
	 * @Description: 机构成立的正式批准文号。
	 */
	@Column(name = "B01067", length = 48)
	private String approveNo;
	
	/**
	 * @fieldName: 成立日期
	 * @fieldType: java.util.Date
	 * @Description: 机构实际成立的日期。GB/T 7408-2005
	 */
	@Column(name = "B01068")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date buildDate;
	
	/**
	 * @fieldName: 单位成立批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准成立该单位的单位名称。
	 */
	@Column(name = "B01071A", length = 120)
	private String approveUnitName;
	
	/**
	 * @fieldName: 单位成立批准单位代码
	 * @fieldType: java.lang.String
	 * @Description: 批准成立该单位的单位代码。GB 32100-2015
	 */
	@Column(name = "B01071B", length = 18)
	private String approveUnitCode;
	
	/**
	 * @fieldName: 单位撤销批准日期
	 * @fieldType: java.util.Date
	 * @Description: 批准撤销该单位的日期。GB/T 7408-2005
	 */
	@Column(name = "B01081")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date revokeDate;
	
	/**
	 * @fieldName: 批准撤消文号
	 * @fieldType: java.lang.String
	 * @Description: 机构被撤销的正式批准文件文号。GB 32100-2015
	 */
	@Column(name = "B01084", length = 48)
	private String revokeNo;
	
	/**
	 * @fieldName: 单位撤销批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 机构被撤销的批准单位。
	 */
	@Column(name = "B01087A", length = 120)
	private String revokeUnitName;
	
	/**
	 * @fieldName: 单位撤销批准单位代码
	 * @fieldType: java.lang.String
	 * @Description: 批准撤销该单位的单位代码。GB 32100-2015
	 */
	@Column(name = "B01087B", length = 18)
	private String revokeUnitCode;
	
	/**
	 * @fieldName: 法人单位标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该单位是否为法人单位的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01094")
	private CodeInfo corporateIdentity;
	
	/**
	 * @fieldName: 单位说明
	 * @fieldType: java.lang.String
	 * @Description: 该单位的补充说明。GB 32100-2015
	 */
	@Column(name = "B01097", length = 2000)
	private String unitInstruction;
	
	/**
	 * @fieldName: 拨款形式
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位经费的来源形式，分为全额拨款、差额拨款和自收自支三种形式。DM047
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B01260")
	private CodeInfo appropriation;
	
	public String getUnitBasicName() {
		
		return unitBasicName;
	}
	
	public void setUnitBasicName(String unitBasicName) {
		
		this.unitBasicName = unitBasicName;
	}
	
	public String getUnitBasicSimpleName() {
		
		return unitBasicSimpleName;
	}
	
	public void setUnitBasicSimpleName(String unitBasicSimpleName) {
		
		this.unitBasicSimpleName = unitBasicSimpleName;
	}
	
	public String getUnitBasicShortName() {
		
		return unitBasicShortName;
	}
	
	public void setUnitBasicShortName(String unitBasicShortName) {
		
		this.unitBasicShortName = unitBasicShortName;
	}
	
	public String getXydm() {
		
		return xydm;
	}
	
	public void setXydm(String xydm) {
		
		this.xydm = xydm;
	}
	
	public CodeInfo getUnitDistrict() {
		
		return unitDistrict;
	}

	
	public void setUnitDistrict(CodeInfo unitDistrict) {
		
		this.unitDistrict = unitDistrict;
	}

	public CodeInfo getJjlx() {
		
		return jjlx;
	}
	
	public void setJjlx(CodeInfo jjlx) {
		
		this.jjlx = jjlx;
	}
	
	public CodeInfo getUnitSubjectionLevel() {
		
		return unitSubjectionLevel;
	}
	
	public void setUnitSubjectionLevel(CodeInfo unitSubjectionLevel) {
		
		this.unitSubjectionLevel = unitSubjectionLevel;
	}
	
	public CodeInfo getUnitLevel() {
		
		return unitLevel;
	}
	
	public void setUnitLevel(CodeInfo unitLevel) {
		
		this.unitLevel = unitLevel;
	}
	
	public CodeInfo getUnitSubsidiary() {
		
		return unitSubsidiary;
	}
	
	public void setUnitSubsidiary(CodeInfo unitSubsidiary) {
		
		this.unitSubsidiary = unitSubsidiary;
	}
	
	public CodeInfo getPermanentState() {
		
		return permanentState;
	}
	
	public void setPermanentState(CodeInfo permanentState) {
		
		this.permanentState = permanentState;
	}
	
	public CodeInfo getUnitPropertyLevel() {
		
		return unitPropertyLevel;
	}
	
	public void setUnitPropertyLevel(CodeInfo unitPropertyLevel) {
		
		this.unitPropertyLevel = unitPropertyLevel;
	}
	
	public CodeInfo getUnitIndustry() {
		
		return unitIndustry;
	}
	
	public void setUnitIndustry(CodeInfo unitIndustry) {
		
		this.unitIndustry = unitIndustry;
	}
	
	public String getUnitWorkFunction() {
		
		return unitWorkFunction;
	}
	
	public void setUnitWorkFunction(String unitWorkFunction) {
		
		this.unitWorkFunction = unitWorkFunction;
	}
	
	public Integer getBelowtfNum() {
		
		return belowtfNum;
	}
	
	public void setBelowtfNum(Integer belowtfNum) {
		
		this.belowtfNum = belowtfNum;
	}
	
	public Integer getOnguardNum() {
		
		return onguardNum;
	}
	
	public void setOnguardNum(Integer onguardNum) {
		
		this.onguardNum = onguardNum;
	}
	
	public Integer getOnguardWorkerNum() {
		
		return onguardWorkerNum;
	}
	
	public void setOnguardWorkerNum(Integer onguardWorkerNum) {
		
		this.onguardWorkerNum = onguardWorkerNum;
	}
	
	public String getUnitSubordinateName() {
		
		return unitSubordinateName;
	}
	
	public void setUnitSubordinateName(String unitSubordinateName) {
		
		this.unitSubordinateName = unitSubordinateName;
	}
	
	public String getUnitSubordinateCode() {
		
		return unitSubordinateCode;
	}
	
	public void setUnitSubordinateCode(String unitSubordinateCode) {
		
		this.unitSubordinateCode = unitSubordinateCode;
	}
	
	public Integer getProfessionalTechnicianNum() {
		
		return professionalTechnicianNum;
	}
	
	public void setProfessionalTechnicianNum(Integer professionalTechnicianNum) {
		
		this.professionalTechnicianNum = professionalTechnicianNum;
	}
	
	public String getUnitLeaderDirectorName() {
		
		return unitLeaderDirectorName;
	}
	
	public void setUnitLeaderDirectorName(String unitLeaderDirectorName) {
		
		this.unitLeaderDirectorName = unitLeaderDirectorName;
	}
	
	public String getUnitLeaderDirectorCode() {
		
		return unitLeaderDirectorCode;
	}
	
	public void setUnitLeaderDirectorCode(String unitLeaderDirectorCode) {
		
		this.unitLeaderDirectorCode = unitLeaderDirectorCode;
	}
	
	public String getUnitLeaderAssistName() {
		
		return unitLeaderAssistName;
	}
	
	public void setUnitLeaderAssistName(String unitLeaderAssistName) {
		
		this.unitLeaderAssistName = unitLeaderAssistName;
	}
	
	public String getUnitLeaderAssistCode() {
		
		return unitLeaderAssistCode;
	}
	
	public void setUnitLeaderAssistCode(String unitLeaderAssistCode) {
		
		this.unitLeaderAssistCode = unitLeaderAssistCode;
	}
	
	public String getUnitReturnManagementName() {
		
		return unitReturnManagementName;
	}
	
	public void setUnitReturnManagementName(String unitReturnManagementName) {
		
		this.unitReturnManagementName = unitReturnManagementName;
	}
	
	public String getUnitReturnManagementCode() {
		
		return unitReturnManagementCode;
	}
	
	public void setUnitReturnManagementCode(String unitReturnManagementCode) {
		
		this.unitReturnManagementCode = unitReturnManagementCode;
	}
	
	public String getCorporation() {
		
		return corporation;
	}
	
	public void setCorporation(String corporation) {
		
		this.corporation = corporation;
	}
	
	public Date getApproveDate() {
		
		return approveDate;
	}
	
	public void setApproveDate(Date approveDate) {
		
		this.approveDate = approveDate;
	}
	
	public String getApproveNo() {
		
		return approveNo;
	}
	
	public void setApproveNo(String approveNo) {
		
		this.approveNo = approveNo;
	}
	
	public Date getBuildDate() {
		
		return buildDate;
	}
	
	public void setBuildDate(Date buildDate) {
		
		this.buildDate = buildDate;
	}
	
	public String getApproveUnitName() {
		
		return approveUnitName;
	}
	
	public void setApproveUnitName(String approveUnitName) {
		
		this.approveUnitName = approveUnitName;
	}
	
	public String getApproveUnitCode() {
		
		return approveUnitCode;
	}
	
	public void setApproveUnitCode(String approveUnitCode) {
		
		this.approveUnitCode = approveUnitCode;
	}
	
	public Date getRevokeDate() {
		
		return revokeDate;
	}
	
	public void setRevokeDate(Date revokeDate) {
		
		this.revokeDate = revokeDate;
	}
	
	public String getRevokeNo() {
		
		return revokeNo;
	}
	
	public void setRevokeNo(String revokeNo) {
		
		this.revokeNo = revokeNo;
	}
	
	public String getRevokeUnitName() {
		
		return revokeUnitName;
	}
	
	public void setRevokeUnitName(String revokeUnitName) {
		
		this.revokeUnitName = revokeUnitName;
	}
	
	public String getRevokeUnitCode() {
		
		return revokeUnitCode;
	}
	
	public void setRevokeUnitCode(String revokeUnitCode) {
		
		this.revokeUnitCode = revokeUnitCode;
	}
	
	public CodeInfo getCorporateIdentity() {
		
		return corporateIdentity;
	}
	
	public void setCorporateIdentity(CodeInfo corporateIdentity) {
		
		this.corporateIdentity = corporateIdentity;
	}
	
	public String getUnitInstruction() {
		
		return unitInstruction;
	}
	
	public void setUnitInstruction(String unitInstruction) {
		
		this.unitInstruction = unitInstruction;
	}
	
	public CodeInfo getAppropriation() {
		
		return appropriation;
	}
	
	public void setAppropriation(CodeInfo appropriation) {
		
		this.appropriation = appropriation;
	}
	
}
