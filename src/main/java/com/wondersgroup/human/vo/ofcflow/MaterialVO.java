/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: MaterialVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月27日 下午3:47:09 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月27日 下午3:47:09 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.Material;

/** 
 * @ClassName: MaterialVO 
 * @Description: 材料vo
 * @author: lihao
 * @date: 2018年12月27日 下午3:47:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class MaterialVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: 材料id
	 */
	private String id;
	
	/**
	 * @fieldName: busId
	 * @fieldType: java.lang.String
	 * @Description: 业务ID
	 */
	private String busId;
	
	/**
	 * @fieldName: busType
	 * @fieldType: java.lang.String
	 * @Description: 业务类型
	 */
	private String busType;
	
	/**
	 * @fieldName: busName
	 * @fieldType: java.lang.String
	 * @Description: 业务名
	 */
	private String busName;
	
	/**
	 * @fieldName: flowNumber
	 * @fieldType: java.lang.String
	 * @Description: 业务编号
	 */
	private String flowNumber;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 材料名称，如果一个人员有多个材料，材料名称用英文冒号（:）隔开
	 */
	private String name;
	
	public MaterialVO(Material m){
		this.id = m.getId();
		this.busId = m.getBusId();
		this.busType = m.getBusType();
		this.busName = m.getBusName();
		this.flowNumber = m.getFlowNumber();
		this.name = m.getName();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the busId
	 */
	public String getBusId() {
		return busId;
	}

	/**
	 * @param busId the busId to set
	 */
	public void setBusId(String busId) {
		this.busId = busId;
	}

	/**
	 * @return the busType
	 */
	public String getBusType() {
		return busType;
	}

	/**
	 * @param busType the busType to set
	 */
	public void setBusType(String busType) {
		this.busType = busType;
	}

	/**
	 * @return the flowNumber
	 */
	public String getFlowNumber() {
		return flowNumber;
	}

	/**
	 * @param flowNumber the flowNumber to set
	 */
	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber;
	}

	/**
	 * @return the busName
	 */
	public String getBusName() {
		return busName;
	}

	/**
	 * @param busName the busName to set
	 */
	public void setBusName(String busName) {
		this.busName = busName;
	}

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
}
