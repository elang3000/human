/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseArchive.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 人事档案管理
 * 创建人: jiang
 * 创建时间: 2018年9月10日10:53:13
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日10:53:16
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
 * @ClassName: BaseArchive
 * @Description: 国标 人事档案管理
 * @author: jiang
 * @date: 2018年9月10日10:53:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseArchive<T> extends GenericEntity {

	private static final long serialVersionUID = 3875976825990114282L;
	
	/**
	 * @fieldName: 档案转入日期
	 * @fieldType: java.util.Date
	 * @Description: 组织、干部、人事部门正式签收档案的日期。GB/T 7408-2005
	 */
	@Column(name = "A38001")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date intoDate;
	
	/**
	 * @fieldName: 档案来处名称
	 * @fieldType: java.lang.String
	 * @Description: 转递该档案的组织、干部、人事部门名称。
	 */
	@Column(name = "A38004A", length = 120)
	private String comeName;
	
	/**
	 * @fieldName: 档案来处代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 转来该人档案的组织、干部、人事部门代码。GB 32100-2015
	 */
	@Column(name = "A38004B", length = 18)
	private String comeCode;
	
	/**
	 * @fieldName: 档案管理单位名称
	 * @fieldType: java.lang.String
	 * @Description: 档案管理的组织机构的名称。
	 */
	@Column(name = "A38007A", length = 120)
	private String mgrUnitName;
	
	/**
	 * @fieldName: 档案管理单位代码
	 * @fieldType: java.lang.String
	 * @Description: 当前管理该人档案单位的法人和其他组织统一社会信用代码。GB 32100-2015
	 */
	@Column(name = "A38007B", length = 120)
	private String mgrUnitCode;
	
	/**
	 * @fieldName: 档案类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人事档案的版本类型。DM033
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A38010")
	private CodeInfo archiveType;
	
	/**
	 * @fieldName: 档案编号
	 * @fieldType: java.lang.String
	 * @Description: 存档案部门为该人档案确定的管理编号。
	 */
	@Column(name = "A38011", length = 6)
	private String archiveCode;
	
	/**
	 * @fieldName: 档案版本类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 档案管理部门保管的该人档案的版本类型。DM033
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A38014")
	private CodeInfo archiveVersionType;
	
	/**
	 * @fieldName: 档案转出日期
	 * @fieldType: java.util.Date
	 * @Description: 组织、干部、人事部门将档案送交机要收发部门的日期。GB/T 7408-2005
	 */
	@Column(name = "A38017")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date outDate;
	
	/**
	 * @fieldName: 档案去处名称
	 * @fieldType: java.lang.String
	 * @Description: 组织、干部、人事部门将该人档案发往的新的管理单位的名称。
	 */
	@Column(name = "A38021A", length = 120)
	private String goName;
	
	/**
	 * @fieldName: 档案去处代码
	 * @fieldType: java.lang.String
	 * @Description: 组织、干部、人事部门将该人档案发往的新的管理单位的代码。GB 32100-2015
	 */
	@Column(name = "A38021B", length = 18)
	private String goCode;
	
	/**
	 * @fieldName: 档案备注
	 * @fieldType: java.lang.String
	 * @Description: 对档案有关情况的说明。
	 */
	@Column(name = "A38024", length = 2000)
	private String remaks;
	
	/**
	 * @fieldName: 正本卷数
	 * @fieldType: java.lang.Float
	 * @Description: 档案管理部门为该人档案确定的正本卷数。
	 */
	@Column(name = "A38027", length = 2)
	private Integer originalNum;
	
	/**
	 * @fieldName: 副本卷数
	 * @fieldType: java.lang.Float
	 * @Description: 档案管理部门为该人档案确定的副本卷数。
	 */
	@Column(name = "A38031", length = 2)
	private Integer copyNum;
	
	/**
	 * @fieldName: 档案位置
	 * @fieldType: java.lang.String
	 * @Description: 档案管理部门为该档案材料确定的存放位置，一般为 XX 柜 XX 层 XX 号。
	 */
	@Column(name = "A38034", length = 40)
	private String site;
	
	/**
	 * @fieldName: 档案条形码号
	 * @fieldType: java.lang.String
	 * @Description: 档案管理部门为该档案确定的条形码编号。
	 */
	@Column(name = "A38037", length = 48)
	private String carcodeNum;
	
	/**
	 * @fieldName: 是否建立数字扫描档案
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否建立了数字扫描档案的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A38051")
	private CodeInfo digitalScanningFlag;

	
	public Date getIntoDate() {
		
		return intoDate;
	}

	
	public void setIntoDate(Date intoDate) {
		
		this.intoDate = intoDate;
	}

	
	public String getComeName() {
		
		return comeName;
	}

	
	public void setComeName(String comeName) {
		
		this.comeName = comeName;
	}

	
	public String getComeCode() {
		
		return comeCode;
	}

	
	public void setComeCode(String comeCode) {
		
		this.comeCode = comeCode;
	}

	
	public String getMgrUnitName() {
		
		return mgrUnitName;
	}

	
	public void setMgrUnitName(String mgrUnitName) {
		
		this.mgrUnitName = mgrUnitName;
	}

	
	public String getMgrUnitCode() {
		
		return mgrUnitCode;
	}

	
	public void setMgrUnitCode(String mgrUnitCode) {
		
		this.mgrUnitCode = mgrUnitCode;
	}

	
	public CodeInfo getArchiveType() {
		
		return archiveType;
	}

	
	public void setArchiveType(CodeInfo archiveType) {
		
		this.archiveType = archiveType;
	}

	
	public String getArchiveCode() {
		
		return archiveCode;
	}

	
	public void setArchiveCode(String archiveCode) {
		
		this.archiveCode = archiveCode;
	}

	
	public CodeInfo getArchiveVersionType() {
		
		return archiveVersionType;
	}

	
	public void setArchiveVersionType(CodeInfo archiveVersionType) {
		
		this.archiveVersionType = archiveVersionType;
	}

	
	public Date getOutDate() {
		
		return outDate;
	}

	
	public void setOutDate(Date outDate) {
		
		this.outDate = outDate;
	}

	
	public String getGoName() {
		
		return goName;
	}

	
	public void setGoName(String goName) {
		
		this.goName = goName;
	}

	
	public String getGoCode() {
		
		return goCode;
	}

	
	public void setGoCode(String goCode) {
		
		this.goCode = goCode;
	}

	
	public String getRemaks() {
		
		return remaks;
	}

	
	public void setRemaks(String remaks) {
		
		this.remaks = remaks;
	}

	
	public Integer getOriginalNum() {
		
		return originalNum;
	}

	
	public void setOriginalNum(Integer originalNum) {
		
		this.originalNum = originalNum;
	}

	
	public Integer getCopyNum() {
		
		return copyNum;
	}

	
	public void setCopyNum(Integer copyNum) {
		
		this.copyNum = copyNum;
	}

	
	public String getSite() {
		
		return site;
	}

	
	public void setSite(String site) {
		
		this.site = site;
	}

	
	public String getCarcodeNum() {
		
		return carcodeNum;
	}

	
	public void setCarcodeNum(String carcodeNum) {
		
		this.carcodeNum = carcodeNum;
	}

	
	public CodeInfo getDigitalScanningFlag() {
		
		return digitalScanningFlag;
	}

	
	public void setDigitalScanningFlag(CodeInfo digitalScanningFlag) {
		
		this.digitalScanningFlag = digitalScanningFlag;
	}
	
}

