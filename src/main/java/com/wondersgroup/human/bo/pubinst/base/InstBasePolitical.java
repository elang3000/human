package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: PtPolitical
 * @Description: 国标  政治面貌
 * @author: LYF
 * @date: 2018年9月6日 下午6:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBasePolitical<T> extends GenericEntity{

	private static final long serialVersionUID = 889092773449506072L;
	
	/**
	 * @fieldName: politicalName
	 * @fieldType: java.lang.String
	 * @Description: 政治面貌名称
	 */
	@Column(name = "A58001A", length = 120)
	private String politicalName;

	/**
	 * @fieldName: politicalCode
	 * @fieldType: java.lang.String
	 * @Description: 政治面貌名称代码
	 */
	@Column(name = "A58001B", length = 120)
	private String politicalCode;
	
	/**
	 * @fieldName: joinPartisanDate
	 * @fieldType: java.util.Date
	 * @Description: 参加党派日期。GB/T 7408-2005
	 */
	@Column(name = "A58005")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinPartisanDate;

	/*
	 * @fieldName: joinPartisanOrganization
	 * @fieldType: java.lang.String
	 * @Description: 参加党派时所在单位
	 */
	@Column(name = "A58015", length = 120)
	private String joinPartisanOrganization;
	
	
	/*
	 * @fieldName: politicalCategory
	 * @fieldType: java.lang.String
	 * @Description: 政治面貌异动类别 ;在党的历史上出现的入团转党、重新入党、自动脱党等特殊情况的类别。DM016
	 */
	@Column(name = "A58030", length = 120)
	private String politicalCategory;
	
	
	/*
	 * @fieldName: politicalReason 
	 * @fieldType: java.lang.String
	 * @Description: 政治面貌异动原因;不能按期转正或登记的原因
	 */
	@Column(name = "A58035", length = 120)
	private String politicalReason;


	
	
	public String getPoliticalName() {
		return politicalName;
	}


	public void setPoliticalName(String politicalName) {
		this.politicalName = politicalName;
	}


	public String getPoliticalCode() {
		return politicalCode;
	}


	public void setPoliticalCode(String politicalCode) {
		this.politicalCode = politicalCode;
	}


	public Date getJoinPartisanDate() {
		return joinPartisanDate;
	}


	public void setJoinPartisanDate(Date joinPartisanDate) {
		this.joinPartisanDate = joinPartisanDate;
	}


	public String getJoinPartisanOrganization() {
		return joinPartisanOrganization;
	}


	public void setJoinPartisanOrganization(String joinPartisanOrganization) {
		this.joinPartisanOrganization = joinPartisanOrganization;
	}


	public String getPoliticalCategory() {
		return politicalCategory;
	}


	public void setPoliticalCategory(String politicalCategory) {
		this.politicalCategory = politicalCategory;
	}


	public String getPoliticalReason() {
		return politicalReason;
	}


	public void setPoliticalReason(String politicalReason) {
		this.politicalReason = politicalReason;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
}
