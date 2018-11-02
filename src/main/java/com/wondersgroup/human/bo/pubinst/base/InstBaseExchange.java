package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: Exchange
 * @Description: 交流情况
 * @author: LYF
 * @date: 2018年9月6日 下午16:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseExchange<T> extends GenericEntity{

	
	private static final long serialVersionUID = -486308171525241267L;
	
	/**
	 * @fieldName: exchangeWay
	 * @fieldType: java.lang.String
	 * @Description:交流方式.该人交流任职的方式。
	 */
	@Column(name = "A49001", length = 120)
	private String exchangeWay;
	
	/**
	 * @fieldName: exchangeReason
	 * @fieldType: java.lang.String
	 * @Description:交流原因.该人交流任职的原因
	 */
	@Column(name = "A49004", length = 120)
	private String exchangeReason;
	
	/**
	 * @fieldName: exchangeReason
	 * @fieldType: java.lang.String
	 * @Description:交流去向.该人交流任职的去向
	 */
	@Column(name = "A49007", length = 120)
	private String exchangeTo;
	
	
	/**
	 * @fieldName: exchangeClass
	 * @fieldType: java.lang.String
	 * @Description:交流类别.公务员实行交流制度。交流类别分为调任、转任、轮换和挂职锻炼。
	 */
	@Column(name = "A49011", length = 120)
	private String exchangeClass;
	
	/**
	 * @fieldName: exchangeOffice
	 * @fieldType: java.lang.String
	 * @Description:交流任职情况
	 */
	@Column(name = "A49014", length = 120)
	private String exchangeOffice;
	
	/**
	 * @fieldName: exchangeBeginDate
	 * @fieldType: java.util.Date
	 * @Description: 交流开始日期
	 */
	@Column(name = "A51017")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exchangeBeginDate;
	

	/**
	 * @fieldName: exchangeEndDate
	 * @fieldType: java.util.Date
	 * @Description: 交流结束日期
	 */
	@Column(name = "A51021")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exchangeEndDate;
	
	/**
	 * @fieldName: exchangeBeforeDutyRank
	 * @fieldType: java.lang.String
	 * @Description:交流前职务级别.该人该次交流前所任职务级别。
	 */
	@Column(name = "A49025", length = 120)
	private String exchangeBeforeDutyRank;
	
	/**
	 * @fieldName: exchangeCompanyName
	 * @fieldType: java.lang.String
	 * @Description:交流目的单位名称.该人该次交流时所调入的单位名称。
	 */
	@Column(name = "A49027A", length = 120)
	private String exchangeCompanyName;
	
	/**
	 * @fieldName: exchangeCompanyCode
	 * @fieldType: java.lang.String
	 * @Description:交流目的单位代码.该人该次交流时所调入的单位代码.
	 */
	@Column(name = "A49027B", length = 120)
	private String exchangeCompanyCode;
	
	/**
	 * @fieldName: exchangeCompanyClass
	 * @fieldType: java.lang.String
	 * @Description:交流目的单位类别.该人该次交流时所调入的单位的性质类别的划分。
	 */
	@Column(name = "A49028", length = 120)
	private String exchangeCompanyClass;
	
	/**
	 * @fieldName: exchangeBeforeCompanyName
	 * @fieldType: java.lang.String
	 * @Description:交流前单位名称.
	 */
	@Column(name = "A49031A", length = 120)
	private String exchangeBeforeCompanyName;
	
	/**
	 * @fieldName: exchangeBeforeCompanyCode
	 * @fieldType: java.lang.String
	 * @Description:交流前单位代码.
	 */
	@Column(name = "A49031B", length = 120)
	private String exchangeBeforeCompanyCode;
	
	
	/**
	 * @fieldName: exchangeDutyClass
	 * @fieldType: java.lang.String
	 * @Description:交流担任职务类别.公务员交流时所担任职务的类型，划分为党内职务、行政职务、其他党派职务、社会团体职务。
	 */
	@Column(name = "A49045", length = 120)
	private String exchangeDutyClass;
	
	/**
	 * @fieldName: exchangeProperty
	 * @fieldType: java.lang.String
	 * @Description:交流性质.表示公务员交流性质的代码。
	 */
	@Column(name = "A49047", length = 120)
	private String exchangeProperty;
	
	
	/**
	 * @fieldName: debarbSeason
	 * @fieldType: java.lang.String
	 * @Description:回避原因.
	 */
	@Column(name = "A49049", length = 120)
	private String debarbSeason;
	
	/**
	 * @fieldName: debarbDate
	 * @fieldType: java.lang.String
	 * @Description:回避日期.
	 */
	@Column(name = "A49053", length = 120)
	private String debarbDate;
	
	
	

	public String getExchangeWay() {
		return exchangeWay;
	}

	public void setExchangeWay(String exchangeWay) {
		this.exchangeWay = exchangeWay;
	}

	public String getExchangeReason() {
		return exchangeReason;
	}

	public void setExchangeReason(String exchangeReason) {
		this.exchangeReason = exchangeReason;
	}

	public String getExchangeTo() {
		return exchangeTo;
	}

	public void setExchangeTo(String exchangeTo) {
		this.exchangeTo = exchangeTo;
	}

	public String getExchangeClass() {
		return exchangeClass;
	}

	public void setExchangeClass(String exchangeClass) {
		this.exchangeClass = exchangeClass;
	}

	public String getExchangeOffice() {
		return exchangeOffice;
	}

	public void setExchangeOffice(String exchangeOffice) {
		this.exchangeOffice = exchangeOffice;
	}

	public Date getExchangeBeginDate() {
		return exchangeBeginDate;
	}

	public void setExchangeBeginDate(Date exchangeBeginDate) {
		this.exchangeBeginDate = exchangeBeginDate;
	}

	public Date getExchangeEndDate() {
		return exchangeEndDate;
	}

	public void setExchangeEndDate(Date exchangeEndDate) {
		this.exchangeEndDate = exchangeEndDate;
	}

	public String getExchangeBeforeDutyRank() {
		return exchangeBeforeDutyRank;
	}

	public void setExchangeBeforeDutyRank(String exchangeBeforeDutyRank) {
		this.exchangeBeforeDutyRank = exchangeBeforeDutyRank;
	}

	public String getExchangeCompanyName() {
		return exchangeCompanyName;
	}

	public void setExchangeCompanyName(String exchangeCompanyName) {
		this.exchangeCompanyName = exchangeCompanyName;
	}

	public String getExchangeCompanyCode() {
		return exchangeCompanyCode;
	}

	public void setExchangeCompanyCode(String exchangeCompanyCode) {
		this.exchangeCompanyCode = exchangeCompanyCode;
	}

	public String getExchangeCompanyClass() {
		return exchangeCompanyClass;
	}

	public void setExchangeCompanyClass(String exchangeCompanyClass) {
		this.exchangeCompanyClass = exchangeCompanyClass;
	}

	public String getExchangeBeforeCompanyName() {
		return exchangeBeforeCompanyName;
	}

	public void setExchangeBeforeCompanyName(String exchangeBeforeCompanyName) {
		this.exchangeBeforeCompanyName = exchangeBeforeCompanyName;
	}

	public String getExchangeBeforeCompanyCode() {
		return exchangeBeforeCompanyCode;
	}

	public void setExchangeBeforeCompanyCode(String exchangeBeforeCompanyCode) {
		this.exchangeBeforeCompanyCode = exchangeBeforeCompanyCode;
	}

	public String getExchangeDutyClass() {
		return exchangeDutyClass;
	}

	public void setExchangeDutyClass(String exchangeDutyClass) {
		this.exchangeDutyClass = exchangeDutyClass;
	}

	public String getExchangeProperty() {
		return exchangeProperty;
	}

	public void setExchangeProperty(String exchangeProperty) {
		this.exchangeProperty = exchangeProperty;
	}

	public String getDebarbSeason() {
		return debarbSeason;
	}

	public void setDebarbSeason(String debarbSeason) {
		this.debarbSeason = debarbSeason;
	}

	public String getDebarbDate() {
		return debarbDate;
	}

	public void setDebarbDate(String debarbDate) {
		this.debarbDate = debarbDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
