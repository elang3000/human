/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainYearPlan.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月18日 上午9:23:49 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月18日 上午9:23:49 
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
 * @ClassName: TrainYearPlan 
 * @Description: 年度培训计划
 * @author: lihao
 * @date: 2018年9月18日 上午9:23:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name="HUMAN_TRAIN_YEAR_PLAN")
public class TrainYearPlan extends GenericEntity {

	private static final long serialVersionUID = -1226328639392033424L;
	
	@Transient
	public final static Integer TRAIN_YEAR_PLAN_STATE_ENABLED = 0;
	
	@Transient
	public final static Integer TRAIN_YEAR_PLAN_STATE_DISABLED = 1;
	
	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 名称
	 *
	 */
	@Column(name="NAME",length=255)
	private String name;
	
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

}
