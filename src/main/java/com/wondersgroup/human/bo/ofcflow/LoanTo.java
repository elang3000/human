/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: LoanTo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 借调信息
 * 创建人: tzy
 * 创建时间: 2018年9月25日 下午2:37:04
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年9月25日 下午2:37:04
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: LoanTo
 * @Description: 借调信息
 * @author: tzy
 * @date: 2018年9月25日 下午2:37:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_LOAN_TO")
public class LoanTo extends GenericEntity {
	
	private static final long serialVersionUID = 5805368140489576886L;
	
	/**
	 * 待备案
	 */
	public static final int STATUS_STATE = 1;
	
	/**
	 * 已备案
	 */
	public static final int STATUS_FINISH = 2;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: SourceOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 借出单位
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOURCEORGAN_ID")
	private OrganNode sourceOrgan;
	
	/**
	 * @fieldName: targetOrgan
	 * @fieldType: java.lang.String
	 * @Description: 去向单位名称。
	 */
	@Column(name = "TARGET_ORGAN", length = 120)
	private String targetOrgan;
	
	/**
	 * @fieldName: startDate
	 * @fieldType: java.util.Date
	 * @Description: 借调开始时间。
	 */
	@Column(name = "START_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 备注。
	 */
	@Column(name = "remark", length = 400)
	private String remark;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 状态。
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}
	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public String getTargetOrgan() {
		
		return targetOrgan;
	}
	
	public void setTargetOrgan(String targetOrgan) {
		
		this.targetOrgan = targetOrgan;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}
	
	public Date getStartDate() {
		
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		
		this.startDate = startDate;
	}
	
}
