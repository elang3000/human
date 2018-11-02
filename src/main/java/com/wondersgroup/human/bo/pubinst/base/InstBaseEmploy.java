/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseEmploy.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 录聘情况
 * 创建人: jiang
 * 创建时间: 2018年5月31日 下午2:10:42
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年5月28日 下午6:09:22
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

@MappedSuperclass
/**
 * @ClassName: BaseEmploy
 * @Description: 国标 录聘情况
 * @author: jiang
 * @date: 2018年5月31日 下午2:10:42
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 * @param <T>
 */
public class InstBaseEmploy<T> extends GenericEntity {
	
	private static final long serialVersionUID = 5343543723031778298L;
	
	/**
	 * @fieldName: 录（聘）标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员、干部队伍录用或聘用状况。DM035。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A03004")
	private CodeInfo identification;
	
	/**
	 * @fieldName: 录（聘）用来源
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 进入公务员、干部队伍前该人的社会身份。GB/T 12405-2008。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A03005")
	private CodeInfo source;
	
	/**
	 * @fieldName: 录聘日期
	 * @fieldType: java.util.Date
	 * @Description: 该人进入公务员、干部队伍的具体日期。GB/T 7408-2005。
	 */
	@Column(name = "A03006")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date employDate;
	
	/**
	 * @fieldName: 进入公务员、干部队伍方式
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人进入公务员、干部队伍的方式。如考试录用，国家统一分配，招聘等。DM079
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A03008")
	private CodeInfo interType;
	
	/**
	 * @fieldName: 劳动合同制用工形式
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 用人单位依据现行法律规定, 与劳动者通过签订劳动合同确定劳动关系, 明确双方权利和义务的用人形式。GB/T 16502-1996
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A03009")
	private CodeInfo workingForm;
	
	/**
	 * @fieldName: 职业类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人所从事工作的种类。GB/T 6565-2009
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A03010")
	private CodeInfo occupationalCategory;
	
	/**
	 * @fieldName: 录用考试准考证号
	 * @fieldType: java.lang.String
	 * @Description: 该人报考公务员或选调生考试的准考证号。
	 */
	@Column(name = "A03011", length = 50)
	private String admissionTicket;
	
	/**
	 * @fieldName: 专业能力测试成绩
	 * @fieldType: java.lang.Float
	 * @Description: 该人参加公务员或选调生考试录用专业能力测试成绩。
	 */
	@Column(name = "A03014", length = 2)
	private Float aptitudeTestScore;
	
	/**
	 * @fieldName: 公共科目笔试成绩
	 * @fieldType: java.lang.Float
	 * @Description: 该人参加公务员或选调生考试录用公共科目笔试成绩。
	 */
	@Column(name = "A03017", length = 2)
	private Float publicSubjectTestScore;
	
	/**
	 * @fieldName: 笔试（行政职业能力测试）成绩
	 * @fieldType: java.lang.Float
	 * @Description: 该人参加公务员或选调生考试录用行政职业能力的测验笔试的成绩。
	 */
	@Column(name = "A03021", length = 2)
	private Float writtenExamTestScore;
	
	/**
	 * @fieldName: 面试成绩
	 * @fieldType: java.lang.Float
	 * @Description: 该人参加公务员或选调生考试中取得的面试成绩。
	 */
	@Column(name = "A03024", length = 2)
	private Float interviewScore;
	
	/**
	 * @fieldName: 其他科目成绩
	 * @fieldType: java.lang.Float
	 * @Description: 该人参加公务员或选调生考试中取得的其他科目成绩。
	 */
	@Column(name = "A03027", length = 2)
	private Float otherSubjectScore;
	
	/**
	 * @fieldName: 批准录用部门
	 * @fieldType: java.lang.String
	 * @Description: 批准该人录用公务员主管部门的名称。
	 */
	@Column(name = "A03031", length = 120)
	private String approveEmployDept;
	
	/**
	 * @fieldName: 批准录用日期
	 * @fieldType: java.util.Date
	 * @Description: 主管部门批准该人录用公务员的时间。GB/T 7408-2005。
	 */
	@Column(name = "A03033")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approveEmployDate;
	
	/**
	 * @fieldName: 录用部门
	 * @fieldType: java.lang.String
	 * @Description: 该人被录用的部门名称。
	 */
	@Column(name = "A03035", length = 120)
	private String employDept;
	
	/**
	 * @fieldName: 进入公务员、干部队伍日期
	 * @fieldType: java.util.Date
	 * @Description: 该人进入公务员队伍的时间。GB/T 7408-2005。
	 */
	@Column(name = "A03053")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date interDate;
	
	/**
	 * @fieldName: 离职日期
	 * @fieldType: java.util.Date
	 * @Description: 该人的离职日期。GB/T 7408-2005。
	 */
	@Column(name = "A03065")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date leaveDate;
	
	/**
	 * @fieldName: 录用职位
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人被录用时的职位名称。GB/T 7408-2005。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A03080")
	private CodeInfo employJob;
	
	/**
	 * @fieldName: 笔试（申论）成绩
	 * @fieldType: java.lang.Float
	 * @Description: 该人参加公务员或选调生考试录用申论笔试的成绩。
	 */
	@Column(name = "A03095", length = 2)
	private Float explainingScore;
	
	/**
	 * @fieldName: 笔试（专业科目）成绩
	 * @fieldType: java.lang.Float
	 * @Description: 该人参加公务员或选调生考试专业科目笔试的成绩。
	 */
	@Column(name = "A03099", length = 2)
	private Float professionalSubjectScore;
	
	public CodeInfo getIdentification() {
		
		return identification;
	}
	
	public void setIdentification(CodeInfo identification) {
		
		this.identification = identification;
	}
	
	public CodeInfo getSource() {
		
		return source;
	}
	
	public void setSource(CodeInfo source) {
		
		this.source = source;
	}
	
	public Date getEmployDate() {
		
		return employDate;
	}
	
	public void setEmployDate(Date employDate) {
		
		this.employDate = employDate;
	}
	
	public CodeInfo getInterType() {
		
		return interType;
	}
	
	public void setInterType(CodeInfo interType) {
		
		this.interType = interType;
	}
	
	public CodeInfo getWorkingForm() {
		
		return workingForm;
	}
	
	public void setWorkingForm(CodeInfo workingForm) {
		
		this.workingForm = workingForm;
	}
	
	public CodeInfo getOccupationalCategory() {
		
		return occupationalCategory;
	}
	
	public void setOccupationalCategory(CodeInfo occupationalCategory) {
		
		this.occupationalCategory = occupationalCategory;
	}
	
	public String getAdmissionTicket() {
		
		return admissionTicket;
	}
	
	public void setAdmissionTicket(String admissionTicket) {
		
		this.admissionTicket = admissionTicket;
	}
	
	public Float getAptitudeTestScore() {
		
		return aptitudeTestScore;
	}
	
	public void setAptitudeTestScore(Float aptitudeTestScore) {
		
		this.aptitudeTestScore = aptitudeTestScore;
	}
	
	public Float getPublicSubjectTestScore() {
		
		return publicSubjectTestScore;
	}
	
	public void setPublicSubjectTestScore(Float publicSubjectTestScore) {
		
		this.publicSubjectTestScore = publicSubjectTestScore;
	}
	
	public Float getWrittenExamTestScore() {
		
		return writtenExamTestScore;
	}
	
	public void setWrittenExamTestScore(Float writtenExamTestScore) {
		
		this.writtenExamTestScore = writtenExamTestScore;
	}
	
	public Float getInterviewScore() {
		
		return interviewScore;
	}
	
	public void setInterviewScore(Float interviewScore) {
		
		this.interviewScore = interviewScore;
	}
	
	public Float getOtherSubjectScore() {
		
		return otherSubjectScore;
	}
	
	public void setOtherSubjectScore(Float otherSubjectScore) {
		
		this.otherSubjectScore = otherSubjectScore;
	}
	
	public String getApproveEmployDept() {
		
		return approveEmployDept;
	}
	
	public void setApproveEmployDept(String approveEmployDept) {
		
		this.approveEmployDept = approveEmployDept;
	}
	
	public Date getApproveEmployDate() {
		
		return approveEmployDate;
	}
	
	public void setApproveEmployDate(Date approveEmployDate) {
		
		this.approveEmployDate = approveEmployDate;
	}
	
	public String getEmployDept() {
		
		return employDept;
	}
	
	public void setEmployDept(String employDept) {
		
		this.employDept = employDept;
	}
	
	public Date getInterDate() {
		
		return interDate;
	}
	
	public void setInterDate(Date interDate) {
		
		this.interDate = interDate;
	}
	
	public Date getLeaveDate() {
		
		return leaveDate;
	}
	
	public void setLeaveDate(Date leaveDate) {
		
		this.leaveDate = leaveDate;
	}
	
	public CodeInfo getEmployJob() {
		
		return employJob;
	}
	
	public void setEmployJob(CodeInfo employJob) {
		
		this.employJob = employJob;
	}
	
	public Float getExplainingScore() {
		
		return explainingScore;
	}
	
	public void setExplainingScore(Float explainingScore) {
		
		this.explainingScore = explainingScore;
	}
	
	public Float getProfessionalSubjectScore() {
		
		return professionalSubjectScore;
	}
	
	public void setProfessionalSubjectScore(Float professionalSubjectScore) {
		
		this.professionalSubjectScore = professionalSubjectScore;
	}
	
}
