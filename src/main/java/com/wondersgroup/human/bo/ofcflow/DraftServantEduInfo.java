/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantEduInfo.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: 拟录用人员信息学历学位子集表
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:01:04 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:01:04 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/** 
 * @ClassName: DraftServantEduInfo 
 * @Description: 拟录用人员信息学历学位子集表
 * @author: GP
 * @date: 2018年6月26日 上午10:01:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name="HUMAN_DRAFT_EDU_INFO")
public class DraftServantEduInfo extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 7003660970612776969L;

	/**
	 * @fieldName: draftServantId
	 * @fieldType: java.lang.String
	 * @Description: 拟录用人员ID。
	 */
	@Column(name = "DRAFT_SERVANT_ID")
	private String draftServantId;
	
	/**
	 * @fieldName: registerId
	 * @fieldType: java.lang.String
	 * @Description: 记录编号（个人序号）。
	 */
	@Column(name = "REGISTER_ID")
	private String registerId;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名。
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 身份证。
	 */
	@Column(name = "CARD_NO")
	private String cardNo;
	
	/**
	 * @fieldName: educationCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 学历代码 ,由国家认可的该人在国内、外各类教育机构接受正式教育并取得学历证书的学习经历代码。GB/T 4658-2006 学历代码
	 */
	@ManyToOne
	@JoinColumn(name = "EDUCATION_CODE")
	private CodeInfo educationCode;
	
	/**
	 * @fieldName: educationName
	 * @fieldType: java.lang.String
	 * @Description: 学历名称。
	 */
	@Column(name = "EDUCATION_NAME")
	private String educationName;
	
	/**
	 * @fieldName: degreeCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 学位代码 ,该人完成一定学历教育后所取得的学位代码。GB/T 6864-2003 学位代码
	 */
	@ManyToOne
	@JoinColumn(name = "DEGREE_CODE")
	private CodeInfo degreeCode;
	
	/**
	 * @fieldName: degreeName
	 * @fieldType: java.lang.String
	 * @Description: 学位名称。
	 */
	@Column(name = "DEGREE_NAME")
	private String degreeName;
	
	/**
	 * @fieldName: studyForm
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 学习形式
	 */
	@ManyToOne
	@JoinColumn(name = "STUDY_FORM")
	private CodeInfo studyForm;
	
	/**
	 * @fieldName: eductionalSystem
	 * @fieldType: java.lang.Long
	 * @Description: 学制年限。
	 */
	@Column(name = "EDUCTIONAL_SYSTEM")
	private Long eductionalSystem;
	
	/**
	 * @fieldName: graduateDate
	 * @fieldType: java.util.Date
	 * @Description: 毕<肄>业日期
	 */
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column(name = "GRADUATE_DATE")
	private Date graduateDate;
	
	/**
	 * @fieldName: schoolName
	 * @fieldType: java.lang.String
	 * @Description: 学校（单位名称）。
	 */
	@Column(name = "SCHOOL_NAME")
	private String schoolName;
	
	/**
	 * @fieldName: profession
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 所学专业，该人取得学历所学专业的分类    GB/T 16835-1997
	 */
	@ManyToOne
	@JoinColumn(name = "PROFESSION")
	private CodeInfo profession;
	
	/**
	 * @fieldName: professionName
	 * @fieldType: java.lang.String
	 * @Description: 所学专业名称。
	 */
	@Column(name = "PROFESSION_NAME")
	private String professionName;

	
	public String getDraftServantId() {
		
		return draftServantId;
	}

	
	public void setDraftServantId(String draftServantId) {
		
		this.draftServantId = draftServantId;
	}

	
	public String getRegisterId() {
		
		return registerId;
	}

	
	public void setRegisterId(String registerId) {
		
		this.registerId = registerId;
	}

	
	public String getName() {
		
		return name;
	}

	
	public void setName(String name) {
		
		this.name = name;
	}

	
	public String getCardNo() {
		
		return cardNo;
	}

	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}

	
	public CodeInfo getEducationCode() {
		
		return educationCode;
	}

	
	public void setEducationCode(CodeInfo educationCode) {
		
		this.educationCode = educationCode;
	}

	
	public String getEducationName() {
		
		return educationName;
	}

	
	public void setEducationName(String educationName) {
		
		this.educationName = educationName;
	}

	
	public CodeInfo getDegreeCode() {
		
		return degreeCode;
	}

	
	public void setDegreeCode(CodeInfo degreeCode) {
		
		this.degreeCode = degreeCode;
	}

	
	public String getDegreeName() {
		
		return degreeName;
	}

	
	public void setDegreeName(String degreeName) {
		
		this.degreeName = degreeName;
	}

	
	public CodeInfo getStudyForm() {
		
		return studyForm;
	}

	
	public void setStudyForm(CodeInfo studyForm) {
		
		this.studyForm = studyForm;
	}

	
	public Long getEductionalSystem() {
		
		return eductionalSystem;
	}

	
	public void setEductionalSystem(Long eductionalSystem) {
		
		this.eductionalSystem = eductionalSystem;
	}

	
	public Date getGraduateDate() {
		
		return graduateDate;
	}

	
	public void setGraduateDate(Date graduateDate) {
		
		this.graduateDate = graduateDate;
	}

	
	public String getSchoolName() {
		
		return schoolName;
	}

	
	public void setSchoolName(String schoolName) {
		
		this.schoolName = schoolName;
	}

	
	public CodeInfo getProfession() {
		
		return profession;
	}

	
	public void setProfession(CodeInfo profession) {
		
		this.profession = profession;
	}

	
	public String getProfessionName() {
		
		return professionName;
	}

	
	public void setProfessionName(String professionName) {
		
		this.professionName = professionName;
	}
	
	

}
