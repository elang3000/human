/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OfcFlowNumber.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 流程信息打印编号
 * 创建人: tzy
 * 创建时间: 2018年12月3日 上午9:29:18
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年12月3日 上午9:29:18
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: OfcFlowNumber
 * @Description: 流程信息打印编号
 * @author: tzy
 * @date: 2018年12月3日 上午9:29:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_OFCFLOW_NUMBER")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OfcFlowNumber extends GenericEntity {
	
	private static final long serialVersionUID = 904213651900803725L;
	
	/**
	 * @fieldName: flowYear
	 * @fieldType: String
	 * @Description: 年度
	 */
	@Column(name = "FLOW_YEAR", length = 4)
	private int flowYear;
	
	/**
	 * @fieldName: flowNumber
	 * @fieldType: String
	 * @Description: 年度内的编号
	 */
	@Column(name = "FLOW_NUMBER", length = 10)
	private int flowNumber;
	
	/**
	 * @fieldName: busType
	 * @fieldType: String
	 * @Description: 业务类型
	 */
	@Column(name = "BUS_TYPE", length = 20)
	private String busType;
	
	/**
	 * @fieldName: busId
	 * @fieldType: String
	 * @Description: 业务ID
	 */
	@Column(name = "BUS_ID", length = 64)
	private String busId;
	
	public String getBusType() {
		
		return busType;
	}
	
	public void setBusType(String busType) {
		
		this.busType = busType;
	}
	
	public String getBusId() {
		
		return busId;
	}
	
	public void setBusId(String busId) {
		
		this.busId = busId;
	}
	
	public int getFlowNumber() {
		
		return flowNumber;
	}
	
	public void setFlowNumber(int flowNumber) {
		
		this.flowNumber = flowNumber;
	}
	
	public int getFlowYear() {
		
		return flowYear;
	}
	
	public void setFlowYear(int flowYear) {
		
		this.flowYear = flowYear;
	}
	
}
