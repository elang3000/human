package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: DutyChange
 * @Description: 职务变动情况
 * @author: LYF
 * @date: 2018年9月6日 下午6:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseDutyChange<T> extends GenericEntity{

	
	private static final long serialVersionUID = 3101038021728316010L;
	
	/**
	 * @fieldName: formerCompanyName
	 * @fieldType: java.lang.String
	 * @Description: 原先单位名称.与该人原任职务相对应的工作机构及工作机构部门名称。
	 */
	@Column(name = "A52001A", length = 120)
	private String formerCompanyName;
	
	/**
	 * @fieldName: formerCompanyCode
	 * @fieldType: java.lang.String
	 * @Description: 原先单位代码.与该人原任职务相对应的工作机构及工作机构部门代码。
	 */
	@Column(name = "A52001B", length = 120)
	private String formerCompanyCode;
	
	/**
	 * @fieldName: newCompanyName
	 * @fieldType: java.lang.String
	 * @Description: 新单位名称.与该人新任职务相对应的工作机构及工作机构部门名称
	 */
	@Column(name = "A52004A", length = 120)
	private String newCompanyName;
	
	/**
	 * @fieldName: newCompanyCode
	 * @fieldType: java.lang.String
	 * @Description: 新单位代码,.与该人新任职务相对应的工作机构及工作机构部门代码
	 */
	@Column(name = "A52004B", length = 120)
	private String newCompanyCode;
	
	/**
	 * @fieldName: formerDutyName
	 * @fieldType: java.lang.String
	 * @Description:原职务名称.该人原任职务的名称。
	 */
	@Column(name = "A52008A", length = 120)
	private String formerDutyName;
	
	/**
	 * @fieldName: formerDutyCode
	 * @fieldType: java.lang.String
	 * @Description:原职务代码.该人原任职务的代码.
	 */
	@Column(name = "A52008B", length = 120)
	private String formerDutyCode;
	
	/**
	 * @fieldName: newDutyName
	 * @fieldType: java.lang.String
	 * @Description:新职务名称.该人新任职务的名称。
	 */
	@Column(name = "A52012A", length = 120)
	private String newDutyName;
	
	/**
	 * @fieldName: newDutyCode
	 * @fieldType: java.lang.String
	 * @Description:新职务代码.该人新任职务的代码.
	 */
	@Column(name = "A52012B", length = 120)
	private String newDutyCode;
	
	/**
	 * @fieldName: formerDutyRank
	 * @fieldType: java.lang.String
	 * @Description:原职务级别.该人原任职务的级别.
	 */
	@Column(name = "A52015", length = 120)
	private String formerDutyRank;
	
	/**
	 * @fieldName: newDutyRank
	 * @fieldType: java.lang.String
	 * @Description:新职务级别.该人新任职务的级别.
	 */
	@Column(name = "A52018", length = 120)
	private String newDutyRank;
	
	/**
	 * @fieldName: formerDutyClass
	 * @fieldType: java.lang.String
	 * @Description:原职务类别.该人原任职务的类别;该人原任职务在相应的工作机构的领导班子中的职务类别。
	 */
	@Column(name = "A52021", length = 120)
	private String formerDutyClass;
	
	/**
	 * @fieldName: newDutyClass
	 * @fieldType: java.lang.String
	 * @Description:新职务类别.该人原任职务的类别;该人新任职务在相应的工作机构的领导班子中的职务类别。
	 */
	@Column(name = "A52024", length = 120)
	private String newDutyClass;
	
	/**
	 * @fieldName: dutyChangeDate
	 * @fieldType: java.util.Date
	 * @Description:职务变动日期.该人此次职务变动发生的日期，一般为由具有法定管理权限的机构签发的文件确定的该职务任职或免职的日期或由会议决定该职务任职或免职的日期。
	 */
	@Column(name = "A52027")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dutyChangeDate;
	
	
	/**
	 * @fieldName: dutyChangeClass
	 * @fieldType: java.lang.String
	 * @Description:职务变动类别.该人新任职务相对于原任职务变动的类别划分。
	 */
	@Column(name = "A52031", length = 120)
	private String dutyChangeClass;
	
	/**
	 * @fieldName: officeTermSign
	 * @fieldType: java.lang.String
	 * @Description:是否达到任职年限标识.该人该职务变动是否达到任职年限的标识。DM215
	 */
	@Column(name = "A52037", length = 120)
	private String officeTermSign;
	
	
	/**
	 * @fieldName: newDutyRankDate
	 * @fieldType: java.util.Date
	 * @Description:现任职级确定日期.该人现任职务等级确定的日期。GB/T 7408-2005
	 */
	@Column(name = "A52044")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date newDutyRankDate;
	
	/**
	 * @fieldName: promotionReason
	 * @fieldType: java.lang.String
	 * @Description:晋升原因
	 */
	@Column(name = "A52047", length = 120)
	private String promotionReason;

	
	/**
	 * @fieldName: demotionReason
	 * @fieldType: java.lang.String
	 * @Description:降级原因
	 */
	@Column(name = "A52051", length = 120)
	private String demotionReason;
	
	

	/**
	 * @fieldName: punishLengthenPromotion
	 * @fieldType: java.lang.String
	 * @Description:因处分延长晋升标识.该人是否因处分而延长晋级。
	 */
	@Column(name = "A52054", length = 120)
	private String punishLengthenPromotion;
	
	/**
	 * @fieldName: lengthenPromotionDate
	 * @fieldType: java.lang.String
	 * @Description:延长晋级时间
	 */
	@Column(name = "A52057", length = 120)
	private String lengthenPromotionDate;
	
	
	
	

	public String getFormerCompanyName() {
		return formerCompanyName;
	}

	public void setFormerCompanyName(String formerCompanyName) {
		this.formerCompanyName = formerCompanyName;
	}

	public String getFormerCompanyCode() {
		return formerCompanyCode;
	}

	public void setFormerCompanyCode(String formerCompanyCode) {
		this.formerCompanyCode = formerCompanyCode;
	}

	public String getNewCompanyName() {
		return newCompanyName;
	}

	public void setNewCompanyName(String newCompanyName) {
		this.newCompanyName = newCompanyName;
	}

	public String getNewCompanyCode() {
		return newCompanyCode;
	}

	public void setNewCompanyCode(String newCompanyCode) {
		this.newCompanyCode = newCompanyCode;
	}

	public String getFormerDutyName() {
		return formerDutyName;
	}

	public void setFormerDutyName(String formerDutyName) {
		this.formerDutyName = formerDutyName;
	}

	public String getFormerDutyCode() {
		return formerDutyCode;
	}

	public void setFormerDutyCode(String formerDutyCode) {
		this.formerDutyCode = formerDutyCode;
	}

	public String getNewDutyName() {
		return newDutyName;
	}

	public void setNewDutyName(String newDutyName) {
		this.newDutyName = newDutyName;
	}

	public String getNewDutyCode() {
		return newDutyCode;
	}

	public void setNewDutyCode(String newDutyCode) {
		this.newDutyCode = newDutyCode;
	}

	public String getFormerDutyRank() {
		return formerDutyRank;
	}

	public void setFormerDutyRank(String formerDutyRank) {
		this.formerDutyRank = formerDutyRank;
	}

	public String getNewDutyRank() {
		return newDutyRank;
	}

	public void setNewDutyRank(String newDutyRank) {
		this.newDutyRank = newDutyRank;
	}

	public String getFormerDutyClass() {
		return formerDutyClass;
	}

	public void setFormerDutyClass(String formerDutyClass) {
		this.formerDutyClass = formerDutyClass;
	}

	public String getNewDutyClass() {
		return newDutyClass;
	}

	public void setNewDutyClass(String newDutyClass) {
		this.newDutyClass = newDutyClass;
	}

	public Date getDutyChangeDate() {
		return dutyChangeDate;
	}

	public void setDutyChangeDate(Date dutyChangeDate) {
		this.dutyChangeDate = dutyChangeDate;
	}

	public String getDutyChangeClass() {
		return dutyChangeClass;
	}

	public void setDutyChangeClass(String dutyChangeClass) {
		this.dutyChangeClass = dutyChangeClass;
	}

	public String getOfficeTermSign() {
		return officeTermSign;
	}

	public void setOfficeTermSign(String officeTermSign) {
		this.officeTermSign = officeTermSign;
	}

	public Date getNewDutyRankDate() {
		return newDutyRankDate;
	}

	public void setNewDutyRankDate(Date newDutyRankDate) {
		this.newDutyRankDate = newDutyRankDate;
	}

	public String getPromotionReason() {
		return promotionReason;
	}

	public void setPromotionReason(String promotionReason) {
		this.promotionReason = promotionReason;
	}

	public String getDemotionReason() {
		return demotionReason;
	}

	public void setDemotionReason(String demotionReason) {
		this.demotionReason = demotionReason;
	}

	public String getPunishLengthenPromotion() {
		return punishLengthenPromotion;
	}

	public void setPunishLengthenPromotion(String punishLengthenPromotion) {
		this.punishLengthenPromotion = punishLengthenPromotion;
	}

	public String getLengthenPromotionDate() {
		return lengthenPromotionDate;
	}

	public void setLengthenPromotionDate(String lengthenPromotionDate) {
		this.lengthenPromotionDate = lengthenPromotionDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
