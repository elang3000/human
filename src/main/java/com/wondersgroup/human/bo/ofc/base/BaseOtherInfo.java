/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseOtherInfo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 个人其他情况
 * 创建人: jiang
 * 创建时间: 2018年9月10日14:08:23
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日14:08:26
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
 * @ClassName: BaseOtherInfo
 * @Description: 国标 个人其他情况
 * @author: jiang
 * @date: 2018年9月10日14:08:30
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseOtherInfo<T> extends GenericEntity {
	
	private static final long serialVersionUID = 1016129351633187577L;

	/**
	 * @fieldName: 曾用名
	 * @fieldType: java.lang.String
	 * @Description: 该人曾正式使用过的姓名。
	 */
	@Column(name = "A39001", length = 50)
	private String beforeName;
	
	/**
	 * @fieldName: 烈军属标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否军人或烈士的直系亲属的标识。DM137
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39002")
	private CodeInfo martyrSoldierFmailyFlag;
	
	/**
	 * @fieldName: 是否带病提拔上岗
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否存在带病提拔上岗的情况。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39003")
	private CodeInfo diseasePromotionWorkFlag;
	
	/**
	 * @fieldName: 受处分干部标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否为受处分干部的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39004")
	private CodeInfo punishCadreFlag;
	
	/**
	 * @fieldName: 首次违法时间
	 * @fieldType: java.util.Date
	 * @Description: 该人的首次违法时间。GB/T 7408-2005
	 */
	@Column(name = "A39005")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstIllegalityDate;
	
	/**
	 * @fieldName: 首次违法职务
	 * @fieldType: java.lang.String
	 * @Description: 该人首次违法时担任的职务。
	 */
	@Column(name = "A39006", length = 50)
	private String firstIllegalityPost;
	
	/**
	 * @fieldName: 伤残军人标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否伤残军人的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39007")
	private CodeInfo disabledSoldierFlag;
	
	/**
	 * @fieldName: 被举报人标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人当前是否为被举报人。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39008")
	private CodeInfo beReportedFlag;
	
	/**
	 * @fieldName: 首次违法单位
	 * @fieldType: java.lang.String
	 * @Description: 该人首次违法时所在的单位中文名称。
	 */
	@Column(name = "A39009", length = 120)
	private String firstIllegalityUnitName;
	
	/**
	 * @fieldName: 案例干部标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人当前是否为案例干部。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39010")
	private CodeInfo caseCadreFlag;
	
	/**
	 * @fieldName: 侨胞标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人或其直系亲属是海外侨胞、台湾同胞、港澳同胞的标识。GB/T 2261.5-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39011")
	private CodeInfo overseasChineseFlag;
	
	/**
	 * @fieldName: 参加第二党派名称
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人当前参加的第二党派的名称。是中共党员者，中共列入第一党派。GB 4762-1984
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39021")
	private CodeInfo secondParty;
	
	/**
	 * @fieldName: 参加第二党派日期
	 * @fieldType: java.util.Date
	 * @Description: 该人参加该组织按照章程规定的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A39024")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinSecondPartyDate;
	
	/**
	 * @fieldName: 第三党派
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人的第三党派的标识。GB 4762-1984
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39027")
	private CodeInfo thirdParty;
	
	/**
	 * @fieldName: 政治面貌变异历史
	 * @fieldType: java.lang.String
	 * @Description: 记载该人何时曾经加入某党组织，何时因何故退出或离开该党组织。
	 */
	@Column(name = "A39031", length = 2000)
	private String politicalChangeHistory;
	
	/**
	 * @fieldName: 宗教信仰
	 * @fieldType: java.lang.String
	 * @Description: 该人当前或曾经有何宗教信仰。
	 */
	@Column(name = "A39034", length = 2000)
	private String religiousBelief;
	
	/**
	 * @fieldName: 婚姻历史
	 * @fieldType: java.lang.String
	 * @Description: 该人婚姻历史中一些需要说明的问题。
	 */
	@Column(name = "A39037", length = 2000)
	private String marriageHistory;
	
	/**
	 * @fieldName: 外文姓名
	 * @fieldType: java.lang.String
	 * @Description: 该人的外文姓名全称。
	 */
	@Column(name = "A39051", length = 2000)
	private String foreignName;
	
	/**
	 * @fieldName: 逝世标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否已逝世的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39054")
	private CodeInfo deathFlag;
	
	/**
	 * @fieldName: 基层项目人员标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人属于哪种服务基层项目人员标识。DM213
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39061")
	private CodeInfo baseProjectUserFlag;
	
	/**
	 * @fieldName: 是否退役士兵
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否退役士兵的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39064")
	private CodeInfo retiredSoldierFlag;
	
	/**
	 * @fieldName: 是否退役大学生士兵
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否退役大学生士兵的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39067")
	private CodeInfo retiredCollegeStudentSoldierFlag;
	
	/**
	 * @fieldName: 是否残疾人
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否残疾人的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39071")
	private CodeInfo disabledFlag;
	
	/**
	 * @fieldName: 是否有海外留学经历
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否具有海外留学经历的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39074")
	private CodeInfo studyAbroadFlag;
	
	/**
	 * @fieldName: 留学年限
	 * @fieldType: java.lang.Integer
	 * @Description: 该人完成该海外留学学历教育，在校学习完成学业的规定时间。
	 */
	@Column(name = "A39077", length = 2)
	private Integer studyAbroadTime;
	
	/**
	 * @fieldName: 是否有海外工作经历
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否具有海外工作经历的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A39081")
	private CodeInfo workAbroadFlag;
	
	/**
	 * @fieldName: 海外工作年限
	 * @fieldType: java.lang.Integer
	 * @Description: 该人在海外工作的工作经历时间。
	 */
	@Column(name = "A39084", length = 2)
	private Integer workAbroadTime;
	
	/**
	 * @fieldName: 取得国外（地区）永久居留权或国籍情况
	 * @fieldType: java.lang.String
	 * @Description: 该人取得国外（地区）永久居留权或国籍情况。
	 */
	@Column(name = "A39087", length = 2000)
	private String permanentResidenceSituation;

	
	public String getBeforeName() {
		
		return beforeName;
	}

	
	public void setBeforeName(String beforeName) {
		
		this.beforeName = beforeName;
	}

	
	public CodeInfo getMartyrSoldierFmailyFlag() {
		
		return martyrSoldierFmailyFlag;
	}

	
	public void setMartyrSoldierFmailyFlag(CodeInfo martyrSoldierFmailyFlag) {
		
		this.martyrSoldierFmailyFlag = martyrSoldierFmailyFlag;
	}

	
	public CodeInfo getDiseasePromotionWorkFlag() {
		
		return diseasePromotionWorkFlag;
	}

	
	public void setDiseasePromotionWorkFlag(CodeInfo diseasePromotionWorkFlag) {
		
		this.diseasePromotionWorkFlag = diseasePromotionWorkFlag;
	}

	
	public CodeInfo getPunishCadreFlag() {
		
		return punishCadreFlag;
	}

	
	public void setPunishCadreFlag(CodeInfo punishCadreFlag) {
		
		this.punishCadreFlag = punishCadreFlag;
	}

	
	public Date getFirstIllegalityDate() {
		
		return firstIllegalityDate;
	}

	
	public void setFirstIllegalityDate(Date firstIllegalityDate) {
		
		this.firstIllegalityDate = firstIllegalityDate;
	}

	
	public String getFirstIllegalityPost() {
		
		return firstIllegalityPost;
	}

	
	public void setFirstIllegalityPost(String firstIllegalityPost) {
		
		this.firstIllegalityPost = firstIllegalityPost;
	}

	
	public CodeInfo getDisabledSoldierFlag() {
		
		return disabledSoldierFlag;
	}

	
	public void setDisabledSoldierFlag(CodeInfo disabledSoldierFlag) {
		
		this.disabledSoldierFlag = disabledSoldierFlag;
	}

	
	public CodeInfo getBeReportedFlag() {
		
		return beReportedFlag;
	}

	
	public void setBeReportedFlag(CodeInfo beReportedFlag) {
		
		this.beReportedFlag = beReportedFlag;
	}

	
	public String getFirstIllegalityUnitName() {
		
		return firstIllegalityUnitName;
	}

	
	public void setFirstIllegalityUnitName(String firstIllegalityUnitName) {
		
		this.firstIllegalityUnitName = firstIllegalityUnitName;
	}

	
	public CodeInfo getCaseCadreFlag() {
		
		return caseCadreFlag;
	}

	
	public void setCaseCadreFlag(CodeInfo caseCadreFlag) {
		
		this.caseCadreFlag = caseCadreFlag;
	}

	
	public CodeInfo getOverseasChineseFlag() {
		
		return overseasChineseFlag;
	}

	
	public void setOverseasChineseFlag(CodeInfo overseasChineseFlag) {
		
		this.overseasChineseFlag = overseasChineseFlag;
	}

	
	public CodeInfo getSecondParty() {
		
		return secondParty;
	}

	
	public void setSecondParty(CodeInfo secondParty) {
		
		this.secondParty = secondParty;
	}

	
	public Date getJoinSecondPartyDate() {
		
		return joinSecondPartyDate;
	}

	
	public void setJoinSecondPartyDate(Date joinSecondPartyDate) {
		
		this.joinSecondPartyDate = joinSecondPartyDate;
	}

	
	public CodeInfo getThirdParty() {
		
		return thirdParty;
	}

	
	public void setThirdParty(CodeInfo thirdParty) {
		
		this.thirdParty = thirdParty;
	}

	
	public String getPoliticalChangeHistory() {
		
		return politicalChangeHistory;
	}

	
	public void setPoliticalChangeHistory(String politicalChangeHistory) {
		
		this.politicalChangeHistory = politicalChangeHistory;
	}

	
	public String getReligiousBelief() {
		
		return religiousBelief;
	}

	
	public void setReligiousBelief(String religiousBelief) {
		
		this.religiousBelief = religiousBelief;
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

	
	public CodeInfo getDeathFlag() {
		
		return deathFlag;
	}

	
	public void setDeathFlag(CodeInfo deathFlag) {
		
		this.deathFlag = deathFlag;
	}

	
	public CodeInfo getBaseProjectUserFlag() {
		
		return baseProjectUserFlag;
	}

	
	public void setBaseProjectUserFlag(CodeInfo baseProjectUserFlag) {
		
		this.baseProjectUserFlag = baseProjectUserFlag;
	}

	
	public CodeInfo getRetiredSoldierFlag() {
		
		return retiredSoldierFlag;
	}

	
	public void setRetiredSoldierFlag(CodeInfo retiredSoldierFlag) {
		
		this.retiredSoldierFlag = retiredSoldierFlag;
	}

	
	public CodeInfo getRetiredCollegeStudentSoldierFlag() {
		
		return retiredCollegeStudentSoldierFlag;
	}

	
	public void setRetiredCollegeStudentSoldierFlag(CodeInfo retiredCollegeStudentSoldierFlag) {
		
		this.retiredCollegeStudentSoldierFlag = retiredCollegeStudentSoldierFlag;
	}

	
	public CodeInfo getDisabledFlag() {
		
		return disabledFlag;
	}

	
	public void setDisabledFlag(CodeInfo disabledFlag) {
		
		this.disabledFlag = disabledFlag;
	}

	
	public CodeInfo getStudyAbroadFlag() {
		
		return studyAbroadFlag;
	}

	
	public void setStudyAbroadFlag(CodeInfo studyAbroadFlag) {
		
		this.studyAbroadFlag = studyAbroadFlag;
	}

	
	public Integer getStudyAbroadTime() {
		
		return studyAbroadTime;
	}

	
	public void setStudyAbroadTime(Integer studyAbroadTime) {
		
		this.studyAbroadTime = studyAbroadTime;
	}

	
	public CodeInfo getWorkAbroadFlag() {
		
		return workAbroadFlag;
	}

	
	public void setWorkAbroadFlag(CodeInfo workAbroadFlag) {
		
		this.workAbroadFlag = workAbroadFlag;
	}

	
	public Integer getWorkAbroadTime() {
		
		return workAbroadTime;
	}

	
	public void setWorkAbroadTime(Integer workAbroadTime) {
		
		this.workAbroadTime = workAbroadTime;
	}

	
	public String getPermanentResidenceSituation() {
		
		return permanentResidenceSituation;
	}

	
	public void setPermanentResidenceSituation(String permanentResidenceSituation) {
		
		this.permanentResidenceSituation = permanentResidenceSituation;
	}
	
}
