/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServant.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月22日 上午9:45:15 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月22日 上午9:45:15 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: DeathServant
 * @Description: 死亡信息
 * @author: lihao
 * @date: 2018年6月22日上午9:45:15 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DEATH")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeathServant extends GenericEntity {

	private static final long serialVersionUID = 2612452347986069583L;

	// 提交死亡信息
	@Transient
	public final static Integer DEATH_EMPLOY_APPLY = 1;
	// 区人事部门备案
	@Transient
	public final static Integer DEATH_EMPLOY_CONFIRM = 2;
	//区人事主管部门备案通过
	@Transient
	public final static Integer DEATH_EMPLOY_DONE = 3;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
		power.put("DEATH_EMPLOY_APPLY",DEATH_EMPLOY_APPLY);
		power.put("DEATH_EMPLOY_CONFIRM", DEATH_EMPLOY_CONFIRM);
	}
		
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: resignDate
	 * @fieldType: java.lang.String
	 * @Description: 发起死亡时间。
	 */
	@Column(name = "DEATH_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date deathDate;
	
	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 备注。
	 */
	@Column(name = "REMARK", length = 2000)
	private String remark;
	
	/**
	 * @fieldName: unitName
	 * @fieldType: java.lang.String
	 * @Description: 所在单位名称
	 */
	@Column(name = "UNIT_NAME", length = 180)
	private String unitName;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/**
	 * @fieldName: status
	 * @fieldType: Integer
	 * @Description: 状态
	 */
	@JoinColumn(name = "status")
	private Integer status;
	
	/**
	 * @fieldName: reason
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 死亡原因。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "reason")
	private CodeInfo reason;
	
	/**
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 人员单位
	 */
	@OneToOne
	private OrganNode organ;

	/**
	 * @return the servant
	 */
	public Servant getServant() {
		return servant;
	}

	/**
	 * @param servant the servant to set
	 */
	public void setServant(Servant servant) {
		this.servant = servant;
	}

	/**
	 * @return the deathDate
	 */
	public Date getDeathDate() {
		return deathDate;
	}

	/**
	 * @param deathDate the deathDate to set
	 */
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the flowRecord
	 */
	public FlowRecord getFlowRecord() {
		return flowRecord;
	}

	/**
	 * @param flowRecord the flowRecord to set
	 */
	public void setFlowRecord(FlowRecord flowRecord) {
		this.flowRecord = flowRecord;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the reason
	 */
	public CodeInfo getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(CodeInfo reason) {
		this.reason = reason;
	}

	/**
	 * @return the organ
	 */
	public OrganNode getOrgan() {
		return organ;
	}

	/**
	 * @param organ the organ to set
	 */
	public void setOrgan(OrganNode organ) {
		this.organ = organ;
	}
}
