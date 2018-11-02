/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPlanVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月12日 下午5:28:58 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月12日 下午5:28:58 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;
import com.wondersgroup.human.bo.ofcflow.TrainPerson;
/** 
 * @ClassName: TrainPlanVO 
 * @Description: 培训学时vo
 * @author: lihao
 * @date: 2018年9月12日 下午5:28:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class TrainPersonVO {

	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: String
	 * @Description: 姓名
	 *
	 */
	private String name;

	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训学时
	 *
	 */
	private Integer hours;
	
	/**
	 * @fieldName: start
	 * @fieldType: java.lang.String
	 * @Description: 开始时间。
	 */
	private String startDate;
	
	/**
	 * @fieldName: end
	 * @fieldType: java.lang.String
	 * @Description: 结束时间。
	 */
	private String endDate;
	
	/**
	 * @fieldName: nature
	 * @fieldType: String
	 * @Description: 培训性质
	 */
	private String nature;
	
	/**
	 * @fieldName: level
	 * @fieldType: String
	 * @Description: 培训性质
	 */
	private String level;
	
	/**
	 * @fieldName: funds
	 * @fieldType: Integer
	 * @Description: 培训经费（万元）
	 *
	 */
	private Integer funds;
	
	/**
	 * @fieldName: isAbroad
	 * @fieldType: java.lang.String
	 * @Description: 是否境外。
	 */
	private String isAbroad;
	
	public TrainPersonVO(TrainPerson t){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		this.id = t.getId();
		this.name = t.getServant().getName();
		this.hours = t.getHours();
		if (t.getStartDate() != null) {
			this.startDate = sdf.format(t.getStartDate());
		}
		if (t.getEndDate() != null) {
			this.endDate = sdf.format(t.getEndDate());
		}
		if (t.getNature() != null) {
			this.nature = t.getNature().getName();
		}
		if (t.getLevel() != null) {
			this.level = t.getLevel().getName();
		}
		this.funds = t.getFunds();
		if (t.getIsAbroad() != null) {
			this.isAbroad = t.getIsAbroad().getName();
		}
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
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the funds
	 */
	public Integer getFunds() {
		return funds;
	}

	/**
	 * @param funds the funds to set
	 */
	public void setFunds(Integer funds) {
		this.funds = funds;
	}

	/**
	 * @return the isAbroad
	 */
	public String getIsAbroad() {
		return isAbroad;
	}

	/**
	 * @param isAbroad the isAbroad to set
	 */
	public void setIsAbroad(String isAbroad) {
		this.isAbroad = isAbroad;
	}
}
