package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: InstBasePersonalOtherSituation
 * @Description: 国标  个人其他情况
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBasePersonalOtherSituation<T> extends GenericEntity {

	private static final long serialVersionUID = -6304150424719819309L;


	/**
	 * @fieldName: pastUseName
	 * @fieldType: java.lang.String
	 * @Description: 曾用名
	 */
	@Column(name = "A39001", length = 120)
	private String pastUseName;
	
	/**
	 * @fieldName: lieArmyLabel
	 * @fieldType: java.lang.String
	 * @Description: 烈军属标识
	 */
	@Column(name = "A39002", length = 120)
	private String lieArmyLabel;
	
	
	/**
	 * @fieldName: diseasePromote
	 * @fieldType: java.lang.String
	 * @Description: 是否带病提拔上岗
	 */
	@Column(name = "A39003", length = 120)
	private String diseasePromote;
	
	/**
	 * @fieldName: disciplinaryCadreLabel
	 * @fieldType: java.lang.String
	 * @Description: 受处分干部标识
	 */
	@Column(name = "A39004", length = 120)
    private String disciplinaryCadreLabel;
	

	/**
	 * @fieldName: firstIllegalTime
	 * @fieldType: java.util.Date
	 * @Description:首次违法时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A39005")
	@Temporal(TemporalType.DATE)
	private Date firstIllegalTime;

	/**
	 * @fieldName: firstIllegalPost
	 * @fieldType: java.lang.String
	 * @Description: 首次违法职务
	 */
	@Column(name = "A39006", length = 120)
    private String firstIllegalPost;
	
	/**
	 * @fieldName: disabledSoldierLabel
	 * @fieldType: java.lang.String
	 * @Description: 伤残军人标识
	 */
	@Column(name = "A39007", length = 120)
	private String disabledSoldierLabel;
	
	/**
	 * @fieldName: reportedPersonLabel
	 * @fieldType: java.lang.String
	 * @Description: 被举报人标识
	 */
	@Column(name = "A39008", length = 120)
	private String reportedPersonLabel;
	
	
	/**
	 * @fieldName: firstIllegalUnit
	 * @fieldType: java.lang.String
	 * @Description: 首次违法单位
	 */
	@Column(name = "A39009", length = 120)
    private String firstIllegalUnit;


	/**
	 * @fieldName: caseOfficerLabel
	 * @fieldType: java.lang.String
	 * @Description: 案例干部标识
	 */
	@Column(name = "A39010", length = 120)
    private String caseOfficerLabel;
	
	/**
	 * @fieldName: overseasChineseLabel
	 * @fieldType: java.lang.String
	 * @Description: 侨胞标识
	 */
	@Column(name = "A39011", length = 120)
    private String overseasChineseLabel;
	
	/**
	 * @fieldName: secondPartyName
	 * @fieldType: java.lang.String
	 * @Description: 参加第二党派名称
	 */
	@Column(name = "A39021", length = 120)
    private String secondPartyName;
	
	/**
	 * @fieldName: secondPartyTime
	 * @fieldType: java.util.Date
	 * @Description:参加第二党派日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A39024")
	@Temporal(TemporalType.DATE)
	private Date secondPartyTime;
	

	/**
	 * @fieldName: thirdParty
	 * @fieldType: java.lang.String
	 * @Description: 第三党派
	 */
	@Column(name = "A39027", length = 120)
    private String thirdParty;
	
	/**
	 * @fieldName: politicalVariabilityHis
	 * @fieldType: java.lang.String
	 * @Description: 政治面貌变异历史
	 */
	@Column(name = "A39031", length = 120)
    private String politicalVariabilityHis;
	
	
	/**
	 * @fieldName: faith
	 * @fieldType: java.lang.String
	 * @Description: 宗教信仰
	 */
	@Column(name = "A39034", length = 120)
    private String faith;
	
	/**
	 * @fieldName: marriageHistory
	 * @fieldType: java.lang.String
	 * @Description: 婚姻历史
	 */
	@Column(name = "A39037", length = 120)
    private String marriageHistory;
	
	/**
	 * @fieldName: foreignName
	 * @fieldType: java.lang.String
	 * @Description: 外文姓名
	 */
	@Column(name = "A39051", length = 120)
    private String foreignName;
	
	/**
	 * @fieldName: deathSign
	 * @fieldType: java.lang.String
	 * @Description:逝世标识
	 */
	@Column(name = "A39054", length = 120)
    private String deathSign;
	
	/**
	 * @fieldName: rassrootsProLabel
	 * @fieldType: java.lang.String
	 * @Description:基层项目人员标识
	 */
	@Column(name = "A39061", length = 120)
	private String rassrootsProLabel;
	
	/**
	 * @fieldName: retiredSoldiers
	 * @fieldType: java.lang.String
	 * @Description:是否退役士兵
	 */
	@Column(name = "A39064", length = 120)
    private String retiredSoldiers;
	
	/**
	 * @fieldName: retiredStudentSoldiers
	 * @fieldType: java.lang.String
	 * @Description:是否退役大学生士兵
	 */
	@Column(name = "A39067", length = 120)
	private String retiredStudentSoldiers;
	
	/**
	 * @fieldName: disabled
	 * @fieldType: java.lang.String
	 * @Description:是否残疾人
	 */
	@Column(name = "A39071", length = 120)
	private String disabled;
	
	/**
	 * @fieldName: overseasStudyExperience
	 * @fieldType: java.lang.String
	 * @Description:是否有海外留学经历
	 */
	@Column(name = "A39074", length = 120)
	private String overseasStudyExperience;
	
	/**
	 * @fieldName: overseasStudyTime
	 * @fieldType: java.lang.Integer
	 * @Description: 留学年限
	 */
	@Column(name = "A39077", length=2)
	private Integer overseasStudyTime;
	
	/**
	 * @fieldName: overseasWorkExperience
	 * @fieldType: java.lang.String
	 * @Description:是否有海外工作经历
	 */
	@Column(name = "A39081", length = 120)
	private String overseasWorkExperience;
	
	/**
	 * @fieldName: overseasWorkTime
	 * @fieldType: java.lang.Integer
	 * @Description: 海外工作年限
	 */
	@Column(name = "A39084", length=2)
	private Integer overseasWorkTime;
	

	/**
	 * @fieldName: permanentResOrnat
	 * @fieldType: java.lang.String
	 * @Description:取得国外（地区）永久居留权或国籍情况
	 */
	@Column(name = "A39087", length = 120)
	private String permanentResOrnat;


	public String getPastUseName() {
		return pastUseName;
	}


	public void setPastUseName(String pastUseName) {
		this.pastUseName = pastUseName;
	}


	public String getLieArmyLabel() {
		return lieArmyLabel;
	}


	public void setLieArmyLabel(String lieArmyLabel) {
		this.lieArmyLabel = lieArmyLabel;
	}


	public String getDiseasePromote() {
		return diseasePromote;
	}


	public void setDiseasePromote(String diseasePromote) {
		this.diseasePromote = diseasePromote;
	}


	public String getDisciplinaryCadreLabel() {
		return disciplinaryCadreLabel;
	}


	public void setDisciplinaryCadreLabel(String disciplinaryCadreLabel) {
		this.disciplinaryCadreLabel = disciplinaryCadreLabel;
	}


	public Date getFirstIllegalTime() {
		return firstIllegalTime;
	}


	public void setFirstIllegalTime(Date firstIllegalTime) {
		this.firstIllegalTime = firstIllegalTime;
	}


	public String getFirstIllegalPost() {
		return firstIllegalPost;
	}


	public void setFirstIllegalPost(String firstIllegalPost) {
		this.firstIllegalPost = firstIllegalPost;
	}


	public String getDisabledSoldierLabel() {
		return disabledSoldierLabel;
	}


	public void setDisabledSoldierLabel(String disabledSoldierLabel) {
		this.disabledSoldierLabel = disabledSoldierLabel;
	}


	public String getReportedPersonLabel() {
		return reportedPersonLabel;
	}


	public void setReportedPersonLabel(String reportedPersonLabel) {
		this.reportedPersonLabel = reportedPersonLabel;
	}


	public String getFirstIllegalUnit() {
		return firstIllegalUnit;
	}


	public void setFirstIllegalUnit(String firstIllegalUnit) {
		this.firstIllegalUnit = firstIllegalUnit;
	}


	public String getCaseOfficerLabel() {
		return caseOfficerLabel;
	}


	public void setCaseOfficerLabel(String caseOfficerLabel) {
		this.caseOfficerLabel = caseOfficerLabel;
	}


	public String getOverseasChineseLabel() {
		return overseasChineseLabel;
	}


	public void setOverseasChineseLabel(String overseasChineseLabel) {
		this.overseasChineseLabel = overseasChineseLabel;
	}


	public String getSecondPartyName() {
		return secondPartyName;
	}


	public void setSecondPartyName(String secondPartyName) {
		this.secondPartyName = secondPartyName;
	}


	public Date getSecondPartyTime() {
		return secondPartyTime;
	}


	public void setSecondPartyTime(Date secondPartyTime) {
		this.secondPartyTime = secondPartyTime;
	}


	public String getThirdParty() {
		return thirdParty;
	}


	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}


	public String getPoliticalVariabilityHis() {
		return politicalVariabilityHis;
	}


	public void setPoliticalVariabilityHis(String politicalVariabilityHis) {
		this.politicalVariabilityHis = politicalVariabilityHis;
	}


	public String getFaith() {
		return faith;
	}


	public void setFaith(String faith) {
		this.faith = faith;
	}


	public String getMarriageHistory() {
		return marriageHistory;
	}


	public void setMarriageHistory(String marriageHistory) {
		this.marriageHistory = marriageHistory;
	}


	public String getForeignName() {
		return foreignName;
	}


	public void setForeignName(String foreignName) {
		this.foreignName = foreignName;
	}


	public String getDeathSign() {
		return deathSign;
	}


	public void setDeathSign(String deathSign) {
		this.deathSign = deathSign;
	}


	public String getRassrootsProLabel() {
		return rassrootsProLabel;
	}


	public void setRassrootsProLabel(String rassrootsProLabel) {
		this.rassrootsProLabel = rassrootsProLabel;
	}


	public String getRetiredSoldiers() {
		return retiredSoldiers;
	}


	public void setRetiredSoldiers(String retiredSoldiers) {
		this.retiredSoldiers = retiredSoldiers;
	}


	public String getRetiredStudentSoldiers() {
		return retiredStudentSoldiers;
	}


	public void setRetiredStudentSoldiers(String retiredStudentSoldiers) {
		this.retiredStudentSoldiers = retiredStudentSoldiers;
	}


	public String getDisabled() {
		return disabled;
	}


	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}


	public String getOverseasStudyExperience() {
		return overseasStudyExperience;
	}


	public void setOverseasStudyExperience(String overseasStudyExperience) {
		this.overseasStudyExperience = overseasStudyExperience;
	}


	public Integer getOverseasStudyTime() {
		return overseasStudyTime;
	}


	public void setOverseasStudyTime(Integer overseasStudyTime) {
		this.overseasStudyTime = overseasStudyTime;
	}


	public String getOverseasWorkExperience() {
		return overseasWorkExperience;
	}


	public void setOverseasWorkExperience(String overseasWorkExperience) {
		this.overseasWorkExperience = overseasWorkExperience;
	}


	public Integer getOverseasWorkTime() {
		return overseasWorkTime;
	}


	public void setOverseasWorkTime(Integer overseasWorkTime) {
		this.overseasWorkTime = overseasWorkTime;
	}


	public String getPermanentResOrnat() {
		return permanentResOrnat;
	}


	public void setPermanentResOrnat(String permanentResOrnat) {
		this.permanentResOrnat = permanentResOrnat;
	}
	
	
}


