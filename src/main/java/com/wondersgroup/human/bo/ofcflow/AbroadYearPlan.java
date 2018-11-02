/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPlan.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月27日 下午4:34:29 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月27日 下午4:34:29 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import com.wondersgroup.framework.core.bo.GenericEntity;

/** 
 * @ClassName: AbroadYearPlan 
 * @Description: 因公出国计划
 * @author: lihao
 * @date: 2018年9月27日 下午4:34:29
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_ABROAD_YEAR_PLAN")
public class AbroadYearPlan extends GenericEntity {

	private static final long serialVersionUID = 5628793259105657724L;
	
	@Transient
	public final static Integer RECRUIT_YEAR_PLAN_STATE_ENABLED = 0;
	
	@Transient
	public final static Integer RECRUIT_YEAR_PLAN_STATE_DISABLED = 1;
	
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 出国境团组名称
	 *
	 */
	@Column(name="NAME",length=255)
	private String name;
	
	/**
	 * @fieldName: state
	 * @fieldType: Integer
	 * @Description: 1 启用 0 停用
	 *
	 */
	private Integer state = 0;
	
	/**
	 * @fieldName: country
	 * @fieldType: String
	 * @Description: 出访国家
	 *
	 */
	@Column(name="COUNTRY",length=255)
	private String country;
	
	/**
	 * @fieldName: day
	 * @fieldType: Integer
	 * @Description: 停留天数
	 *
	 */
	@Column(name="DAY")
	private Integer day;
	
	/**
	 * @fieldName: startDate
	 * @fieldType: Date
	 * @Description: 开始时间
	 *
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date startDate;
	
	/**
	 * @fieldName: endDate
	 * @fieldType: Date
	 * @Description: 结束时间
	 *
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date endDate;
	
	/**
	 * @fieldName: approveStartDate
	 * @fieldType: Date
	 * @Description: 批准开始有效期
	 *
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="APPROVE_START_DATE")
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date approveStartDate;
	
	/**
	 * @fieldName: approveEndDate
	 * @fieldType: Date
	 * @Description: 批准结束有效期
	 *
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="APPROVE_END_DATE")
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date approveEndDate;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the approveStartDate
	 */
	public Date getApproveStartDate() {
		return approveStartDate;
	}

	/**
	 * @param approveStartDate the approveStartDate to set
	 */
	public void setApproveStartDate(Date approveStartDate) {
		this.approveStartDate = approveStartDate;
	}

	/**
	 * @return the approveEndDate
	 */
	public Date getApproveEndDate() {
		return approveEndDate;
	}

	/**
	 * @param approveEndDate the approveEndDate to set
	 */
	public void setApproveEndDate(Date approveEndDate) {
		this.approveEndDate = approveEndDate;
	}
}
