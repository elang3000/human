/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseObservation.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 组织考察情况
 * 创建人: jiang
 * 创建时间: 2018年9月10日14:38:29
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日14:38:32
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: BaseObservation
 * @Description: 国标 组织考察情况
 * @author: jiang
 * @date: 2018年9月10日14:38:35
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseObservation<T> extends GenericEntity {
	
	private static final long serialVersionUID = -8686435087106262319L;
	
	/**
	 * @fieldName: 考察类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 考察（考核）的不同类别。DM017
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A47001")
	private CodeInfo observationType;
	
	/**
	 * @fieldName: 考察日期
	 * @fieldType: java.util.Date
	 * @Description: 由组织、干部、人事部门组派的考察组对干部进行考察（考核）的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A47004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date observationDate;
	
	/**
	 * @fieldName: 考察组织名称
	 * @fieldType: java.lang.String
	 * @Description: 由组织、干部、人事、纪检等部门组派的对该人进行考察的组织名称。
	 */
	@Column(name = "A47007", length = 120)
	private String observationOrgName;
	
	/**
	 * @fieldName: 考察意见
	 * @fieldType: java.lang.String
	 * @Description: 由组织、干部、人事部门组派的考察组对被考察人的德、才、勤、绩诸方面的评价意。
	 */
	@Column(name = "A47011", length = 2000)
	private String observationOpinion;
	
	/**
	 * @fieldName: 考察人姓名
	 * @fieldType: java.lang.String
	 * @Description: 直接参与对该人考察的组织成员的姓名全称。
	 */
	@Column(name = "A47014", length = 50)
	private String observationMen;
	
	/**
	 * @fieldName: 考察方法标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 组织对该人考察的各种方法的标识。DM034
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A47017")
	private CodeInfo observationFunc;
	
	public CodeInfo getObservationType() {
		
		return observationType;
	}
	
	public void setObservationType(CodeInfo observationType) {
		
		this.observationType = observationType;
	}
	
	public Date getObservationDate() {
		
		return observationDate;
	}
	
	public void setObservationDate(Date observationDate) {
		
		this.observationDate = observationDate;
	}
	
	public String getObservationOrgName() {
		
		return observationOrgName;
	}
	
	public void setObservationOrgName(String observationOrgName) {
		
		this.observationOrgName = observationOrgName;
	}
	
	public String getObservationOpinion() {
		
		return observationOpinion;
	}
	
	public void setObservationOpinion(String observationOpinion) {
		
		this.observationOpinion = observationOpinion;
	}
	
	public String getObservationMen() {
		
		return observationMen;
	}
	
	public void setObservationMen(String observationMen) {
		
		this.observationMen = observationMen;
	}
	
	public CodeInfo getObservationFunc() {
		
		return observationFunc;
	}
	
	public void setObservationFunc(CodeInfo observationFunc) {
		
		this.observationFunc = observationFunc;
	}
	
}
