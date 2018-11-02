package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: JobPosition
 * @Description: 工作岗位
 * @author: LYF
 * @date: 2018年9月6日 17:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseJobPosition<T>extends GenericEntity {

	
	private static final long serialVersionUID = -1801537112512487116L;

	/**
	 * @fieldName: jobPositionBeginDate
	 * @fieldType: java.util.Date
	 * @Description: 工作岗位开始时间
	 */
	@Column(name = "A48001")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date jobPositionBeginDate;

	/**
	 * @fieldName: jobPositionEndDate
	 * @fieldType: java.util.Date
	 * @Description: 工作岗位结束时间
	 */
	@Column(name = "A48004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date jobPositionEndDate;
	
	/**
	 * @fieldName: jobPosition
	 * @fieldType: java.lang.String
	 * @Description: 工作岗位
	 */
	@Column(name = "A48007", length = 120)
	private String jobPosition;
	
	/**
	 * @fieldName: highestPosition
	 * @fieldType: java.lang.String
	 * @Description: 是否当前工作标识.该岗位是否为该人当前最高职务或者在编制、工资关系单位所任职务对应的岗位的标识。
	 */
	@Column(name = "A48011", length = 120)
	private String highestPosition;
	
	/**
	 * @fieldName: positionChangeClassify
	 * @fieldType: java.lang.String
	 * @Description: 岗位变化分类
	 */
	@Column(name = "A48105", length = 120)
	private String positionChangeClassify;
	
	/**
	 * @fieldName: positionWay
	 * @fieldType: java.lang.String
	 * @Description: 上岗方式
	 */
	@Column(name = "A48110", length = 120)
	private String positionWay;
	
	/**
	 * @fieldName: positionMajorAsk
	 * @fieldType: java.lang.String
	 * @Description: 上岗专业要求
	 */
	@Column(name = "A48116", length = 120)
	private String positionMajorAsk;

	public Date getJobPositionBeginDate() {
		return jobPositionBeginDate;
	}

	public void setJobPositionBeginDate(Date jobPositionBeginDate) {
		this.jobPositionBeginDate = jobPositionBeginDate;
	}

	public Date getJobPositionEndDate() {
		return jobPositionEndDate;
	}

	public void setJobPositionEndDate(Date jobPositionEndDate) {
		this.jobPositionEndDate = jobPositionEndDate;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getHighestPosition() {
		return highestPosition;
	}

	public void setHighestPosition(String highestPosition) {
		this.highestPosition = highestPosition;
	}

	public String getPositionChangeClassify() {
		return positionChangeClassify;
	}

	public void setPositionChangeClassify(String positionChangeClassify) {
		this.positionChangeClassify = positionChangeClassify;
	}

	public String getPositionWay() {
		return positionWay;
	}

	public void setPositionWay(String positionWay) {
		this.positionWay = positionWay;
	}

	public String getPositionMajorAsk() {
		return positionMajorAsk;
	}

	public void setPositionMajorAsk(String positionMajorAsk) {
		this.positionMajorAsk = positionMajorAsk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
}
