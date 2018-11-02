/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseWorkerTechnologyInfo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 工人技术等级
 * 创建人: jiang
 * 创建时间: 2018年9月7日15:39:58
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月7日15:40:01
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
 * @ClassName: BaseWorkerTechnologyInfo
 * @Description: 国标 工人技术等级
 * @author: jiang
 * @date: 2018年9月7日15:40:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseWorkerTechnologyInfo<T> extends GenericEntity {
	
	private static final long serialVersionUID = -1011350859858650410L;
	
	/**
	 * @fieldName: 工人技术等级
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该工人通过正规途径获得的技术等级。DM012
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A27001")
	private CodeInfo technicalLevel;
	
	/**
	 * @fieldName: 职业（专业、工种）名称
	 * @fieldType: java.lang.String
	 * @Description: 该人所从事的职业（专业、工种）名称。
	 */
	@Column(name = "A27003", length = 60)
	private String occupationName;
	
	/**
	 * @fieldName: 工人技术等级评定日期
	 * @fieldType: java.util.Date
	 * @Description: 确定该工人技术等级的日期或经工人技术等级鉴定机构评定该技术等级的日期。GB/T 7408-2005
	 */
	@Column(name = "A27004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date levelRatingDate;
	
	/**
	 * @fieldName: 职业（专业、工种）资格名称
	 * @fieldType: java.lang.String
	 * @Description: 国家职业资格证书所载明的等级和名称。工人技术等级职业资格。
	 */
	@Column(name = "A27005", length = 40)
	private String occupationalQualificationName;
	
	/**
	 * @fieldName: 职业（专业、工种）资格证书编号
	 * @fieldType: java.lang.String
	 * @Description: 职业（专业、工种）资格证书上所记载的证书编码。
	 */
	@Column(name = "A27007", length = 16)
	private String certificateNo;
	
	/**
	 * @fieldName: 工人技术等级评定单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该工人技术等级的评定单位或该工人技术等级评审机构名称。
	 */
	@Column(name = "A27007A", length = 120)
	private String ratingUnitName;
	
	/**
	 * @fieldName: 工人技术等级评定单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该工人技术等级的评定单位或该工人技术等级评审机构代码。GB 32100-2015
	 */
	@Column(name = "A27007B", length = 18)
	private String ratingUnitCode;
	
	/**
	 * @fieldName: 取得职业（专业、工种）资格的日期
	 * @fieldType: java.util.Date
	 * @Description: 国家职业资格证书上记载的发证日期。GB/T 7408-2005
	 */
	@Column(name = "A27010")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date gainQualificationsDate;
	
	/**
	 * @fieldName: 工人技术等级当前状态
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该工人技术等级当前是否在任的状态标识。DM007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A27011")
	private CodeInfo levelCurrentStatus;
	
	/**
	 * @fieldName: 职业资格理论知识考核成绩
	 * @fieldType: java.lang.Float
	 * @Description: 职业（专业、工种）资格证书上所记载的理论知识考核成绩。
	 */
	@Column(name = "A27012", length = 2)
	private Float knowledgeExamScore;
	
	/**
	 * @fieldName: 职业资格操作技能考核成绩
	 * @fieldType: java.lang.Float
	 * @Description: 职业资格证书上所记载的操作技能考核成绩。
	 */
	@Column(name = "A27013", length = 2)
	private Float skillExamScore;
	
	/**
	 * @fieldName: 工人技术等级截止日期
	 * @fieldType: java.util.Date
	 * @Description: 该工人技术等级的截止日期。GB/T 7408-2005
	 */
	@Column(name = "A27014")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date levelEndDate;
	
	/**
	 * @fieldName: 职业（专业、工种）资格证书核发单位名称
	 * @fieldType: java.lang.String
	 * @Description: 国家职业资格证书上所记载的发证单位名称。GB 32100-2015
	 */
	@Column(name = "A27015", length = 120)
	private String certificateIssuingUnitName;
	
	/**
	 * @fieldName: 职业资格评定（复核）成绩
	 * @fieldType: java.lang.Float
	 * @Description: 职业资格证书上所记载的评定（复核）成绩。
	 */
	@Column(name = "A27016", length = 2)
	private Float ratingCheckScore;
	
	/**
	 * @fieldName: 工人技术等级变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该工人技术等级变动的分类。DM121
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A27017")
	private CodeInfo levelChangeType;

	
	public CodeInfo getTechnicalLevel() {
		
		return technicalLevel;
	}

	
	public void setTechnicalLevel(CodeInfo technicalLevel) {
		
		this.technicalLevel = technicalLevel;
	}

	
	public String getOccupationName() {
		
		return occupationName;
	}

	
	public void setOccupationName(String occupationName) {
		
		this.occupationName = occupationName;
	}

	
	public Date getLevelRatingDate() {
		
		return levelRatingDate;
	}

	
	public void setLevelRatingDate(Date levelRatingDate) {
		
		this.levelRatingDate = levelRatingDate;
	}

	
	public String getOccupationalQualificationName() {
		
		return occupationalQualificationName;
	}

	
	public void setOccupationalQualificationName(String occupationalQualificationName) {
		
		this.occupationalQualificationName = occupationalQualificationName;
	}

	
	public String getCertificateNo() {
		
		return certificateNo;
	}

	
	public void setCertificateNo(String certificateNo) {
		
		this.certificateNo = certificateNo;
	}

	
	public String getRatingUnitName() {
		
		return ratingUnitName;
	}

	
	public void setRatingUnitName(String ratingUnitName) {
		
		this.ratingUnitName = ratingUnitName;
	}

	
	public String getRatingUnitCode() {
		
		return ratingUnitCode;
	}

	
	public void setRatingUnitCode(String ratingUnitCode) {
		
		this.ratingUnitCode = ratingUnitCode;
	}

	
	public Date getGainQualificationsDate() {
		
		return gainQualificationsDate;
	}

	
	public void setGainQualificationsDate(Date gainQualificationsDate) {
		
		this.gainQualificationsDate = gainQualificationsDate;
	}

	
	public CodeInfo getLevelCurrentStatus() {
		
		return levelCurrentStatus;
	}

	
	public void setLevelCurrentStatus(CodeInfo levelCurrentStatus) {
		
		this.levelCurrentStatus = levelCurrentStatus;
	}

	
	public Float getKnowledgeExamScore() {
		
		return knowledgeExamScore;
	}

	
	public void setKnowledgeExamScore(Float knowledgeExamScore) {
		
		this.knowledgeExamScore = knowledgeExamScore;
	}

	
	public Float getSkillExamScore() {
		
		return skillExamScore;
	}

	
	public void setSkillExamScore(Float skillExamScore) {
		
		this.skillExamScore = skillExamScore;
	}

	
	public Date getLevelEndDate() {
		
		return levelEndDate;
	}

	
	public void setLevelEndDate(Date levelEndDate) {
		
		this.levelEndDate = levelEndDate;
	}

	
	public String getCertificateIssuingUnitName() {
		
		return certificateIssuingUnitName;
	}

	
	public void setCertificateIssuingUnitName(String certificateIssuingUnitName) {
		
		this.certificateIssuingUnitName = certificateIssuingUnitName;
	}

	
	public Float getRatingCheckScore() {
		
		return ratingCheckScore;
	}

	
	public void setRatingCheckScore(Float ratingCheckScore) {
		
		this.ratingCheckScore = ratingCheckScore;
	}

	
	public CodeInfo getLevelChangeType() {
		
		return levelChangeType;
	}

	
	public void setLevelChangeType(CodeInfo levelChangeType) {
		
		this.levelChangeType = levelChangeType;
	}
	
	
}
