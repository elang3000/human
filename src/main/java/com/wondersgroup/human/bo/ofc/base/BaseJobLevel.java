/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseJobLevel.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 职级情况
 * 创建人: jiang
 * 创建时间: 2018年6月12日09:21:20
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日09:21:23
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
 * @Description: 国标 职级情况
 * @author: jiang
 * @date: 2018年6月12日09:21:32
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseJobLevel<T> extends GenericEntity {
	
	private static final long serialVersionUID = 6409006793599074133L;
	
	/**
	 * @fieldName: 职级名称
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级名称。
	 */
	@Column(name = "A05002A")
	private String name;
	
	/**
	 * @fieldName: 职级代码
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级代码。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A05002B")
	private CodeInfo code;
	
	/**
	 * @fieldName: 职级批准日期
	 * @fieldType: java.util.Date
	 * @Description: 组织、干部、人事部门批准该人该职级的日期。GB/T 7408-2005
	 */
	@Column(name = "A05004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approvalDate;
	
	/**
	 * @fieldName: 职级批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准该人该职级的具体单位名称。
	 */
	@Column(name = "A05007A", length = 120)
	private String approvalUnitName;
	
	/**
	 * @fieldName: 职级批准单位代码
	 * @fieldType: java.lang.String
	 * @Description: 批准该人该职级的具体单位代码。
	 */
	@Column(name = "A05007B", length = 18)
	private String approvalUnitCode;
	
	/**
	 * @fieldName: 职级批准文号
	 * @fieldType: java.lang.String
	 * @Description: 批准该人该职级的文件的编号全称。
	 */
	@Column(name = "A05011", length = 48)
	private String approvalNo;
	
	/**
	 * @fieldName: 职级终止日期
	 * @fieldType: java.util.Date
	 * @Description: 该人该职级的终止日期。GB/T 7408-2005
	 */
	@Column(name = "A05017")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	/**
	 * @fieldName: 职级批准时所在单位及职务
	 * @fieldType: java.lang.String
	 * @Description: 该人该职级批准时所在的工作机构及工作机构部门名称和所担任的职务名称。
	 */
	@Column(name = "A05021", length = 500)
	private String unitAndPost;
	
	/**
	 * @fieldName: 现行职级标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该职级是否符合现行职级标准的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A05024")
	private CodeInfo currentIdentification;
	
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
	
	public Date getApprovalDate() {
		
		return approvalDate;
	}
	
	public void setApprovalDate(Date approvalDate) {
		
		this.approvalDate = approvalDate;
	}
	
	public String getApprovalUnitName() {
		
		return approvalUnitName;
	}
	
	public void setApprovalUnitName(String approvalUnitName) {
		
		this.approvalUnitName = approvalUnitName;
	}
	
	public String getApprovalUnitCode() {
		
		return approvalUnitCode;
	}
	
	public void setApprovalUnitCode(String approvalUnitCode) {
		
		this.approvalUnitCode = approvalUnitCode;
	}
	
	public String getApprovalNo() {
		
		return approvalNo;
	}
	
	public void setApprovalNo(String approvalNo) {
		
		this.approvalNo = approvalNo;
	}
	
	public Date getEndDate() {
		
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		
		this.endDate = endDate;
	}
	
	public String getUnitAndPost() {
		
		return unitAndPost;
	}
	
	public void setUnitAndPost(String unitAndPost) {
		
		this.unitAndPost = unitAndPost;
	}

	
	public CodeInfo getCurrentIdentification() {
		
		return currentIdentification;
	}

	
	public void setCurrentIdentification(CodeInfo currentIdentification) {
		
		this.currentIdentification = currentIdentification;
	}
	
}
