/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitYearPlan.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午10:20:25 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午10:20:25 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/** 
 * @ClassName: RecruitYearPlan 
 * @Description: 年度计划
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午10:20:25
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_RECRUIT_YEAR_PLAN")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RecruitYearPlan extends GenericEntity{
	
	private static final long serialVersionUID = 3318219165267089160L;
	
	@Transient
	public final static Integer RECRUIT_YEAR_PLAN_STATE_ENABLED = 0;
	
	@Transient
	public final static Integer RECRUIT_YEAR_PLAN_STATE_DISABLED = 1;
	
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: TODO
	 *
	 */
	@Column(name="NAME",length=255)
	private String name;
	
	/**
	 * @fieldName: startDate
	 * @fieldType: Date
	 * @Description: TODO
	 *
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date startDate;
	
	/**
	 * @fieldName: endDate
	 * @fieldType: Date
	 * @Description: TODO
	 *
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date endDate;
	
	/**
	 * @fieldName: state
	 * @fieldType: Integer
	 * @Description: 1 启用 0 停用
	 *
	 */
	private Integer state = 0;
	
	/**
	 * @fieldName: description
	 * @fieldType: String
	 * @Description: TODO
	 *
	 */
	@Column(name="DESCRIPTION")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
