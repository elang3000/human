/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServant.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月25日 上午10:45:15 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月25日 上午10:45:15 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: RetireServant
 * @Description: 退休信息
 * @author: lihao
 * @date: 2018年6月25日上午10:45:15 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Entity(name = "HUMAN_RETIRE")
public class RetireServant extends GenericEntity {

	private static final long serialVersionUID = 9012526870688252512L;

	/**
	 * @fieldName: humanId
	 * @fieldType: java.lang.String
	 * @Description: 公务员ID。
	 */
	@Column(name = "hunman_id", length = 180)
	private String humanId;

	/**
	 * @fieldName: retireType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 退休类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "retire_type")
	private CodeInfo retireType;

	/**
	 * @fieldName: retireRemark
	 * @fieldType: java.lang.String
	 * @Description: 退休备注。
	 */
	@Column(name = "retire_remark", length = 2000)
	private String retireRemark;

	/**
	 * @fieldName: retireDate
	 * @fieldType: java.util.Date
	 * @Description: 退休时间。
	 */
	@Column(name = "retire_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date retireDate;
	
	/**
	 * @fieldName: retireAge
	 * @fieldType: java.lang.Integer
	 * @Description: 退休年龄。
	 */
	@Column(name = "retire_age")
	private Integer retireAge;


	/**
	 * @return the humanId
	 */
	public String getHumanId() {
		return humanId;
	}

	/**
	 * @param humanId
	 *            the humanId to set
	 */
	public void setHumanId(String humanId) {
		this.humanId = humanId;
	}

	/**
	 * @return the retireType
	 */
	public CodeInfo getRetireType() {
		return retireType;
	}

	/**
	 * @param retireType
	 *            the retireType to set
	 */
	public void setRetireType(CodeInfo retireType) {
		this.retireType = retireType;
	}

	/**
	 * @return the retireRemark
	 */
	public String getRetireRemark() {
		return retireRemark;
	}

	/**
	 * @param retireRemark
	 *            the retireRemark to set
	 */
	public void setRetireRemark(String retireRemark) {
		this.retireRemark = retireRemark;
	}

	/**
	 * @return the retireDate
	 */
	public Date getRetireDate() {
		return retireDate;
	}

	/**
	 * @param retireDate
	 *            the retireDate to set
	 */
	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}

	/**
	 * @return the retireAge
	 */
	public Integer getRetireAge() {
		return retireAge;
	}

	/**
	 * @param retireAge the retireAge to set
	 */
	public void setRetireAge(Integer retireAge) {
		this.retireAge = retireAge;
	}
	
}
