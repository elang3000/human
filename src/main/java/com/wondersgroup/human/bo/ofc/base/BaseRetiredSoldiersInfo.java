/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseRetiredSoldiersInfo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 退出现役军人（武警）情况
 * 创建人: jiang
 * 创建时间: 2018年9月7日15:04:00
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月7日15:04:05
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
 * @ClassName: BaseRetiredSoldiersInfo
 * @Description: 国标 退出现役军人（武警）情况
 * @author: jiang
 * @date: 2018年9月7日15:04:08
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseRetiredSoldiersInfo<T> extends GenericEntity {
	
	private static final long serialVersionUID = 4529142719775136899L;

	/**
	 * @fieldName: 退出现役军人（武警）标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人退出现役军人（武警）的标识。DM078
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26001")
	private CodeInfo retiredType;
	
	/**
	 * @fieldName: 入伍日期
	 * @fieldType: java.util.Date
	 * @Description: 地方人武部门征兵办公室批准入伍的日期。GB/T 7408-2005
	 */
	@Column(name = "A26004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enlistDate;
	
	/**
	 * @fieldName: 入伍地
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 参军的详细地点，填至县级。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26007")
	private CodeInfo enlistAddress;
	
	/**
	 * @fieldName: 批准退出现役日期
	 * @fieldType: java.util.Date
	 * @Description: 部队批准退出现役的日期。GB/T 7408-2005
	 */
	@Column(name = "A26011")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approvalRetiredDate;
	
	/**
	 * @fieldName: 退出现役批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准该人退出现役的部队番号及单位名称。
	 */
	@Column(name = "A26014A", length = 120)
	private String approvalRetiredUnitName;
	
	/**
	 * @fieldName: 退出现役批准单位代码
	 * @fieldType: java.lang.String
	 * @Description: 批准该人退出现役的部队番号及单位代码。GB 32100-2015
	 */
	@Column(name = "A26014B", length = 18)
	private String approvalRetiredUnitCode;
	
	/**
	 * @fieldName: 退出现役年份
	 * @fieldType: java.lang.String
	 * @Description: 该人退出现役的年份。GB/T 7408-2005
	 */
	@Column(name = "A26016", length = 4)
	private Integer retiredYear;
	
	/**
	 * @fieldName: 退出现役时工作单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人退出现役时在军队（武警部队）任职的具体单位名称。
称
	 */
	@Column(name = "A26017A", length = 120)
	private String quitCurrentUnitName;
	
	/**
	 * @fieldName: 退出现役时工作单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人退出现役时在军队（武警部队）任职的具体单位代码。GB 32100-2015
	 */
	@Column(name = "A26017B", length = 18)
	private String quitCurrentUnitCode;
	
	/**
	 * @fieldName: 退出现役时工作单位所在行政区划
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人退出现役时工作单位所在的当前国家县级及县级以上行政区划。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26021")
	private CodeInfo quitCurrentUnitAreaCode;
	
	/**
	 * @fieldName: 退出现役时职务
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 转业前该人在军队的职务。GB/T 12403-1990
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26025")
	private CodeInfo retiredPost;
	
	/**
	 * @fieldName: 退出现役时军衔
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 转业前该人在军队的军衔。DM032
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26027")
	private CodeInfo retiredMilitaryRank;
	
	/**
	 * @fieldName: 退出现役时职级
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 转业前该人在军队时的职务级别。DM076
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26031")
	private CodeInfo retiredJobLevel;
	
	/**
	 * @fieldName: 退出现役时专业技术职务
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 转业前该人在军队的专业技术等级。GB/T 8561-2001
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26034")
	private CodeInfo retiredCompetence;
	
	/**
	 * @fieldName: 退出现役时工资类别档次
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 转业前部队确定的工资档次。DM091
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26037")
	private CodeInfo retiredSalaryLevel;
	
	/**
	 * @fieldName: 是否服预备役标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人退出现役后服预备役情况的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A26041")
	private CodeInfo performBeforehandMilitaryServiceFlag;

	
	public CodeInfo getRetiredType() {
		
		return retiredType;
	}

	
	public void setRetiredType(CodeInfo retiredType) {
		
		this.retiredType = retiredType;
	}

	
	public Date getEnlistDate() {
		
		return enlistDate;
	}

	
	public void setEnlistDate(Date enlistDate) {
		
		this.enlistDate = enlistDate;
	}

	
	public CodeInfo getEnlistAddress() {
		
		return enlistAddress;
	}

	
	public void setEnlistAddress(CodeInfo enlistAddress) {
		
		this.enlistAddress = enlistAddress;
	}

	
	public Date getApprovalRetiredDate() {
		
		return approvalRetiredDate;
	}

	
	public void setApprovalRetiredDate(Date approvalRetiredDate) {
		
		this.approvalRetiredDate = approvalRetiredDate;
	}

	
	public String getApprovalRetiredUnitName() {
		
		return approvalRetiredUnitName;
	}

	
	public void setApprovalRetiredUnitName(String approvalRetiredUnitName) {
		
		this.approvalRetiredUnitName = approvalRetiredUnitName;
	}

	
	public String getApprovalRetiredUnitCode() {
		
		return approvalRetiredUnitCode;
	}

	
	public void setApprovalRetiredUnitCode(String approvalRetiredUnitCode) {
		
		this.approvalRetiredUnitCode = approvalRetiredUnitCode;
	}

	
	public Integer getRetiredYear() {
		
		return retiredYear;
	}

	
	public void setRetiredYear(Integer retiredYear) {
		
		this.retiredYear = retiredYear;
	}

	
	public String getQuitCurrentUnitName() {
		
		return quitCurrentUnitName;
	}

	
	public void setQuitCurrentUnitName(String quitCurrentUnitName) {
		
		this.quitCurrentUnitName = quitCurrentUnitName;
	}

	
	public String getQuitCurrentUnitCode() {
		
		return quitCurrentUnitCode;
	}

	
	public void setQuitCurrentUnitCode(String quitCurrentUnitCode) {
		
		this.quitCurrentUnitCode = quitCurrentUnitCode;
	}

	
	public CodeInfo getQuitCurrentUnitAreaCode() {
		
		return quitCurrentUnitAreaCode;
	}

	
	public void setQuitCurrentUnitAreaCode(CodeInfo quitCurrentUnitAreaCode) {
		
		this.quitCurrentUnitAreaCode = quitCurrentUnitAreaCode;
	}

	
	public CodeInfo getRetiredPost() {
		
		return retiredPost;
	}

	
	public void setRetiredPost(CodeInfo retiredPost) {
		
		this.retiredPost = retiredPost;
	}

	
	public CodeInfo getRetiredMilitaryRank() {
		
		return retiredMilitaryRank;
	}

	
	public void setRetiredMilitaryRank(CodeInfo retiredMilitaryRank) {
		
		this.retiredMilitaryRank = retiredMilitaryRank;
	}

	
	public CodeInfo getRetiredJobLevel() {
		
		return retiredJobLevel;
	}

	
	public void setRetiredJobLevel(CodeInfo retiredJobLevel) {
		
		this.retiredJobLevel = retiredJobLevel;
	}

	
	public CodeInfo getRetiredCompetence() {
		
		return retiredCompetence;
	}

	
	public void setRetiredCompetence(CodeInfo retiredCompetence) {
		
		this.retiredCompetence = retiredCompetence;
	}

	
	public CodeInfo getRetiredSalaryLevel() {
		
		return retiredSalaryLevel;
	}

	
	public void setRetiredSalaryLevel(CodeInfo retiredSalaryLevel) {
		
		this.retiredSalaryLevel = retiredSalaryLevel;
	}

	
	public CodeInfo getPerformBeforehandMilitaryServiceFlag() {
		
		return performBeforehandMilitaryServiceFlag;
	}

	
	public void setPerformBeforehandMilitaryServiceFlag(CodeInfo performBeforehandMilitaryServiceFlag) {

		this.performBeforehandMilitaryServiceFlag = performBeforehandMilitaryServiceFlag;
	}
	
	
}
