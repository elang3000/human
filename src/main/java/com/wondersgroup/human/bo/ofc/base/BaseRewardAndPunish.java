/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseRewardAndPunish.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 奖惩情况
 * 创建人: jiang
 * 创建时间: 2018年6月25日14:54:25
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月25日14:54:30
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
 * @ClassName: BaseRewardAndPunish
 * @Description: 国标 奖惩情况
 * @author: jiang
 * @date: 2018年6月25日14:54:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseRewardAndPunish<T> extends GenericEntity {
	
	
	private static final long serialVersionUID = -6017555500484696749L;

	/**
	 * @fieldName: 人员受奖惩综述
	 * @fieldType: java.lang.String
	 * @Description: 对该人在工作、学习过程中因成绩突出而受到各级组织机构授予的精神、物质奖励，或者由于错误或过失而受到的惩戒情况的文字描述。
	 */
	@Column(name = "A14001", length = 2000)
	private String reviews;
	
	/**
	 * @fieldName: 人员受奖惩类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人因某种业绩成就受到党和国家某级组织给予的奖励或惩处的级别。DM019
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14201")
	private CodeInfo category;
	
	/**
	 * @fieldName: 人员受奖励决定原始文件
	 * @fieldType: java.lang.String
	 * @Description: 决定对该人进行奖励的原始文件号。
	 */
	@Column(name = "A14202", length = 40)
	private String rewardNo;
	
	/**
	 * @fieldName: 人员受奖励名称
	 * @fieldType: java.lang.String
	 * @Description: 党和国家某级组织授予该人的奖励及荣誉称号的名称。
	 */
	@Column(name = "A14205A", length = 40)
	private String rewardName;
	
	/**
	 * @fieldName: 人员受奖励代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人受到党和国家某级组织给予的某类奖励及荣誉称号的代码。GB/T 8563.1-2005
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14205B")
	private CodeInfo rewardCode;
	
	/**
	 * @fieldName: 荣誉称号级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 党和国家某级组织授予该人的荣誉称号的级别。GB/T 8563.2-2005
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14208B")
	private CodeInfo honoraryLevel;
	
	
	/**
	 * @fieldName: 人员奖励批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准授予奖励的某级组织机构的名称。
	 */
	@Column(name = "A14214A", length = 255)
	private String rewardApprovalUnitName;
	
	/**
	 * @fieldName: 人员奖励批准单位代码
	 * @fieldType: java.lang.String
	 * @Description: 批准给予该人该奖励的组织单位代码。GB 32100-2015
	 */
	@Column(name = "A14214B", length = 40)
	private String rewardApprovalUnitCode;
	
	
	/**
	 * @fieldName: 人员奖励批准单位级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 批准给予该人该奖励的组织单位的级别。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14217")
	private CodeInfo rewardApprovalUnitLevel;
	
	/**
	 * @fieldName: 荣誉称号名称
	 * @fieldType: java.lang.String
	 * @Description: 受到党和国家某级组织授予该人的荣誉称号的名称。
	 */
	@Column(name = "A14220", length = 255)
	private String honoraryName;
	
	
	/**
	 * @fieldName: 人员受奖励原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人因何种业绩成就受到党和国家某级组织授予的奖励。DM021
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14221")
	private CodeInfo rewardReason;
	
	/**
	 * @fieldName: 人员受奖励说明
	 * @fieldType: java.lang.String
	 * @Description: 该人因何种业绩受到该奖励的说明。
	 */
	@Column(name = "A14224", length = 2000)
	private String rewardDescription;
	
	/**
	 * @fieldName: 人员奖励批准日期
	 * @fieldType: java.util.Date
	 * @Description: 党和国家某级组织批准授予该人奖励的日期。GB/T 7408-2005
	 */
	@Column(name = "A14225")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rewardApprovalDate;
	
	/**
	 * @fieldName: 人员奖励撤销日期
	 * @fieldType: java.util.Date
	 * @Description: 撤销该人该奖励的日期。GB/T 7408-2005
	 */
	@Column(name = "A14227")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rewardRevokeDate;
	
	/**
	 * @fieldName: 荣誉称号授予单位
	 * @fieldType: java.lang.String
	 * @Description: 授予荣誉称号的某级组织机构的名称。
	 */
	@Column(name = "A14230", length = 120)
	private String honoraryGrantingUnit;
	
	/**
	 * @fieldName: 人员奖励撤销原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 撤销该人该奖励的原因。DM189
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14231")
	private CodeInfo rewardRevokeReason;
	
	/**
	 * @fieldName: 人员受处分决定原始文件
	 * @fieldType: java.lang.String
	 * @Description: 决定对该人进行处分的原始文件号。
	 */
	@Column(name = "A14302", length = 40)
	private String punishNo;
	
	/**
	 * @fieldName: 人员受惩戒名称
	 * @fieldType: java.lang.String
	 * @Description: 该人受到党和国家某级组织给予的某类惩戒的名称。
	 */
	@Column(name = "A14305A", length = 120)
	private String punishName;
	
	/**
	 * @fieldName: 人员受惩戒代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人受到党和国家某级组织给予的某类惩戒的代码。GB/T 8563.3
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14305B")
	private CodeInfo punishCode;
	
	/**
	 * @fieldName: 人员受惩戒批准日期
	 * @fieldType: java.util.Date
	 * @Description: 党和国家某级组织批准给予该人该处分的日期。GB/T 7408-2005
	 */
	@Column(name = "A14307")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date punishApprovalDate;
	
	/**
	 * @fieldName: 人员受惩戒批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准给予该人该处分的组织单位名称。
	 */
	@Column(name = "A14311A", length = 120)
	private String punishApprovalUnitName;
	
	/**
	 * @fieldName: 人员受惩戒批准单位代码
	 * @fieldType: java.lang.String
	 * @Description: 批准对该人进行处分的单位的代码。GB 32100-2015
	 */
	@Column(name = "A14311B", length = 40)
	private String punishApprovalUnitCode;
	
	
	/**
	 * @fieldName: 人员受惩戒批准单位级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 批准给予该人该惩戒的组织单位的级别。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14314")
	private CodeInfo punishApprovalUnitLevel;
	
	/**
	 * @fieldName: 人员受惩戒原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 因何种过失、错误受到党和国家某级组织给予该人的处罚。DM021
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14317")
	private CodeInfo punishReason;
	
	/**
	 * @fieldName: 人员受惩戒说明
	 * @fieldType: java.lang.String
	 * @Description: 该人因何种过失、错误受到该惩戒及其解除、撤销等有关事项的说明。
	 */
	@Column(name = "A14321", length = 2000)
	private String punishDescription;
	
	/**
	 * @fieldName: 人员受惩戒解除日期
	 * @fieldType: java.util.Date
	 * @Description: 解除该人该惩戒的日期。GB/T 7408-2005
	 */
	@Column(name = "A14324")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date punishRevokeDate;
	
	/**
	 * @fieldName: 是否监察机关直接给予惩戒的标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 对触犯刑律的犯罪行为，由监察机关直接给予的处罚。DM063
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14330")
	private CodeInfo punishIdentification;
	
	/**
	 * @fieldName: 是否受处分标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否获得处分的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A14351")
	private CodeInfo punishmentIdentification;

	
	public String getReviews() {
		
		return reviews;
	}

	
	public void setReviews(String reviews) {
		
		this.reviews = reviews;
	}

	
	public CodeInfo getCategory() {
		
		return category;
	}

	
	public void setCategory(CodeInfo category) {
		
		this.category = category;
	}

	
	public String getRewardNo() {
		
		return rewardNo;
	}

	
	public void setRewardNo(String rewardNo) {
		
		this.rewardNo = rewardNo;
	}

	
	public String getRewardName() {
		
		return rewardName;
	}

	
	public void setRewardName(String rewardName) {
		
		this.rewardName = rewardName;
	}

	
	public CodeInfo getRewardCode() {
		
		return rewardCode;
	}

	
	public void setRewardCode(CodeInfo rewardCode) {
		
		this.rewardCode = rewardCode;
	}

	
	public CodeInfo getHonoraryLevel() {
		
		return honoraryLevel;
	}

	
	public void setHonoraryLevel(CodeInfo honoraryLevel) {
		
		this.honoraryLevel = honoraryLevel;
	}

	
	public String getRewardApprovalUnitName() {
		
		return rewardApprovalUnitName;
	}

	
	public void setRewardApprovalUnitName(String rewardApprovalUnitName) {
		
		this.rewardApprovalUnitName = rewardApprovalUnitName;
	}

	
	public String getRewardApprovalUnitCode() {
		
		return rewardApprovalUnitCode;
	}

	
	public void setRewardApprovalUnitCode(String rewardApprovalUnitCode) {
		
		this.rewardApprovalUnitCode = rewardApprovalUnitCode;
	}

	
	public CodeInfo getRewardApprovalUnitLevel() {
		
		return rewardApprovalUnitLevel;
	}

	
	public void setRewardApprovalUnitLevel(CodeInfo rewardApprovalUnitLevel) {
		
		this.rewardApprovalUnitLevel = rewardApprovalUnitLevel;
	}

	
	public String getHonoraryName() {
		
		return honoraryName;
	}

	
	public void setHonoraryName(String honoraryName) {
		
		this.honoraryName = honoraryName;
	}

	
	public CodeInfo getRewardReason() {
		
		return rewardReason;
	}

	
	public void setRewardReason(CodeInfo rewardReason) {
		
		this.rewardReason = rewardReason;
	}

	
	public String getRewardDescription() {
		
		return rewardDescription;
	}

	
	public void setRewardDescription(String rewardDescription) {
		
		this.rewardDescription = rewardDescription;
	}

	
	public Date getRewardApprovalDate() {
		
		return rewardApprovalDate;
	}

	
	public void setRewardApprovalDate(Date rewardApprovalDate) {
		
		this.rewardApprovalDate = rewardApprovalDate;
	}

	
	public Date getRewardRevokeDate() {
		
		return rewardRevokeDate;
	}

	
	public void setRewardRevokeDate(Date rewardRevokeDate) {
		
		this.rewardRevokeDate = rewardRevokeDate;
	}

	
	public String getHonoraryGrantingUnit() {
		
		return honoraryGrantingUnit;
	}

	
	public void setHonoraryGrantingUnit(String honoraryGrantingUnit) {
		
		this.honoraryGrantingUnit = honoraryGrantingUnit;
	}

	
	public CodeInfo getRewardRevokeReason() {
		
		return rewardRevokeReason;
	}

	
	public void setRewardRevokeReason(CodeInfo rewardRevokeReason) {
		
		this.rewardRevokeReason = rewardRevokeReason;
	}

	
	public String getPunishNo() {
		
		return punishNo;
	}

	
	public void setPunishNo(String punishNo) {
		
		this.punishNo = punishNo;
	}

	
	public String getPunishName() {
		
		return punishName;
	}

	
	public void setPunishName(String punishName) {
		
		this.punishName = punishName;
	}

	
	public CodeInfo getPunishCode() {
		
		return punishCode;
	}

	
	public void setPunishCode(CodeInfo punishCode) {
		
		this.punishCode = punishCode;
	}

	
	public Date getPunishApprovalDate() {
		
		return punishApprovalDate;
	}

	
	public void setPunishApprovalDate(Date punishApprovalDate) {
		
		this.punishApprovalDate = punishApprovalDate;
	}

	
	public String getPunishApprovalUnitName() {
		
		return punishApprovalUnitName;
	}

	
	public void setPunishApprovalUnitName(String punishApprovalUnitName) {
		
		this.punishApprovalUnitName = punishApprovalUnitName;
	}

	
	public String getPunishApprovalUnitCode() {
		
		return punishApprovalUnitCode;
	}

	
	public void setPunishApprovalUnitCode(String punishApprovalUnitCode) {
		
		this.punishApprovalUnitCode = punishApprovalUnitCode;
	}

	
	public CodeInfo getPunishApprovalUnitLevel() {
		
		return punishApprovalUnitLevel;
	}

	
	public void setPunishApprovalUnitLevel(CodeInfo punishApprovalUnitLevel) {
		
		this.punishApprovalUnitLevel = punishApprovalUnitLevel;
	}

	
	public CodeInfo getPunishReason() {
		
		return punishReason;
	}

	
	public void setPunishReason(CodeInfo punishReason) {
		
		this.punishReason = punishReason;
	}

	
	public String getPunishDescription() {
		
		return punishDescription;
	}

	
	public void setPunishDescription(String punishDescription) {
		
		this.punishDescription = punishDescription;
	}

	
	public Date getPunishRevokeDate() {
		
		return punishRevokeDate;
	}

	
	public void setPunishRevokeDate(Date punishRevokeDate) {
		
		this.punishRevokeDate = punishRevokeDate;
	}

	
	public CodeInfo getPunishIdentification() {
		
		return punishIdentification;
	}

	
	public void setPunishIdentification(CodeInfo punishIdentification) {
		
		this.punishIdentification = punishIdentification;
	}

	
	public CodeInfo getPunishmentIdentification() {
		
		return punishmentIdentification;
	}

	
	public void setPunishmentIdentification(CodeInfo punishmentIdentification) {
		
		this.punishmentIdentification = punishmentIdentification;
	}
	
}
