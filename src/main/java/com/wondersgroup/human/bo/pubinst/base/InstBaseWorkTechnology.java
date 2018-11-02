package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: InstBaseWorkTechnology
 * @Description: 国标  工人技术等级
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseWorkTechnology<T> extends GenericEntity {

	private static final long serialVersionUID = 1938654500487974545L;
	
	
	
	/**
	 * @fieldName: workTechnologyRank
	 * @fieldType: java.lang.String
	 * @Description: 工人技术等级
	 */
	@Column(name = "A27001", length = 120)
	private String workTechnologyRank;
	
	
	/**
	 * @fieldName: careerName
	 * @fieldType: java.lang.String
	 * @Description: 职业（专业、工种）名称
	 */
	@Column(name = "A27003", length = 120)
	private String careerName;
	
	/**
	 * @fieldName: randAssessmentDate
	 * @fieldType: java.util.Date
	 * @Description: 工人技术等级评定日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A27004")
	@Temporal(TemporalType.DATE)
	private Date randAssessmentDate;
	
	
	/**
	 * @fieldName: qualificationName
	 * @fieldType: java.lang.String
	 * @Description: 职业（专业、工种）资格名称
	 */
	@Column(name = "A27005", length = 120)
	private String qualificationName;
	
	/**
	 * @fieldName: certificateCode
	 * @fieldType: java.lang.String
	 * @Description: 职业（专业、工种）资格证书编号
	 */
	@Column(name = "A27007", length = 120)
	private String certificateCode;
	
	
	/**
	 * @fieldName: rankAssessmentUnitName
	 * @fieldType: java.lang.String
	 * @Description: 工人技术等级评定单位名称
	 */
	@Column(name = "A27007A", length = 120)
	private String rankAssessmentUnitName;
	
	/**
	 * @fieldName: rankAssessmentUnitCode
	 * @fieldType: java.lang.String
	 * @Description: 工人技术等级评定单位代码
	 */
	@Column(name = "A27007B", length = 120)
	private String rankAssessmentUnitCode;
	
	/**
	 * @fieldName: assessmentDate
	 * @fieldType: java.util.Date
	 * @Description: 取得职业（专业、工种）资格的日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A27010")
	@Temporal(TemporalType.DATE)
	private Date assessmentDate;
	
	/**
	 * @fieldName: rankNowStatus
	 * @fieldType: java.lang.String
	 * @Description: 工人技术等级当前状态
	 */
	@Column(name = "A27011", length = 40)
	private String rankNowStatus;
	
	/**
	 * @fieldName: theoreticalAssessment
	 * @fieldType: java.lang.Integer
	 * @Description: 职业资格理论知识考核成绩
	 */
	@Column(name = "A27012", length = 2)
	private Integer theoreticalAssessment;
	
	
	/**
	 * @fieldName: operationalScore
	 * @fieldType: java.lang.Integer
	 * @Description: 职业资格操作知识考核成绩
	 */
	@Column(name = "A27013", length = 2)
	private Integer operationalScore;

	/**
	 * @fieldName: rankStopDate
	 * @fieldType: java.util.Date
	 * @Description: 工人技术等级截止日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A27014")
	@Temporal(TemporalType.DATE)
	private Date rankStopDate;
	
	/**
	 * @fieldName: theoreticalAssessment
	 * @fieldType: java.lang.Integer
	 * @Description: 职业（专业、工种）资格证书核发单位名称
	 */
	@Column(name = "A27015", length = 2)
    private String certificateIssuingUnit;
	

	/**
	 * @fieldName: assessmentScore
	 * @fieldType: java.lang.Integer
	 * @Description: 职业资格评定（复核）成绩
	 */
	@Column(name = "A27016", length = 2)
	private Integer assessmentScore;

	/**
	 * @fieldName: rankChangeCategory
	 * @fieldType: java.lang.String
	 * @Description: 工人技术等级变动类别
	 */
	@Column(name = "A27017", length = 2)
    private String rankChangeCategory;

	public String getWorkTechnologyRank() {
		return workTechnologyRank;
	}

	public void setWorkTechnologyRank(String workTechnologyRank) {
		this.workTechnologyRank = workTechnologyRank;
	}

	public String getCareerName() {
		return careerName;
	}

	public void setCareerName(String careerName) {
		this.careerName = careerName;
	}

	public Date getRandAssessmentDate() {
		return randAssessmentDate;
	}

	public void setRandAssessmentDate(Date randAssessmentDate) {
		this.randAssessmentDate = randAssessmentDate;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public String getRankAssessmentUnitName() {
		return rankAssessmentUnitName;
	}

	public void setRankAssessmentUnitName(String rankAssessmentUnitName) {
		this.rankAssessmentUnitName = rankAssessmentUnitName;
	}

	public String getRankAssessmentUnitCode() {
		return rankAssessmentUnitCode;
	}

	public void setRankAssessmentUnitCode(String rankAssessmentUnitCode) {
		this.rankAssessmentUnitCode = rankAssessmentUnitCode;
	}

	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public String getRankNowStatus() {
		return rankNowStatus;
	}

	public void setRankNowStatus(String rankNowStatus) {
		this.rankNowStatus = rankNowStatus;
	}

	public Integer getTheoreticalAssessment() {
		return theoreticalAssessment;
	}

	public void setTheoreticalAssessment(Integer theoreticalAssessment) {
		this.theoreticalAssessment = theoreticalAssessment;
	}


	public Integer getOperationalScore() {
		return operationalScore;
	}

	public void setOperationalScore(Integer operationalScore) {
		this.operationalScore = operationalScore;
	}

	public Date getRankStopDate() {
		return rankStopDate;
	}

	public void setRankStopDate(Date rankStopDate) {
		this.rankStopDate = rankStopDate;
	}

	public String getCertificateIssuingUnit() {
		return certificateIssuingUnit;
	}

	public void setCertificateIssuingUnit(String certificateIssuingUnit) {
		this.certificateIssuingUnit = certificateIssuingUnit;
	}

	public Integer getAssessmentScore() {
		return assessmentScore;
	}

	public void setAssessmentScore(Integer assessmentScore) {
		this.assessmentScore = assessmentScore;
	}

	public String getRankChangeCategory() {
		return rankChangeCategory;
	}

	public void setRankChangeCategory(String rankChangeCategory) {
		this.rankChangeCategory = rankChangeCategory;
	}
	
	
	

}
