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
import com.wondersgroup.human.bo.ofcflow.TrainPlan;
/** 
 * @ClassName: TrainPlanVO 
 * @Description: 培训学时vo
 * @author: lihao
 * @date: 2018年9月12日 下午5:28:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class TrainPlanVO {

	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: String
	 * @Description: 培训名
	 *
	 */
	private String trainName;

	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训学时
	 *
	 */
	private Integer hours;
	
	/**
	 * @fieldName: description
	 * @fieldType: String
	 * @Description: 培训描述
	 *
	 */
	private String description;
	
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
	 * @fieldName: status
	 * @fieldType: String
	 * @Description: 状态
	 */
	private String status;
	
	private String trainOrgan;
	
	private String inputOrgan;
	
	private String yearPlan;
	
	private Integer statusSign;
	
	public TrainPlanVO(TrainPlan t){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		this.id = t.getId();
		this.trainName = t.getTrainName();
		this.hours = t.getHours();
		this.description = t.getDescription();
		if (t.getStartDate() != null) {
			this.startDate = sdf.format(t.getStartDate());
		}
		if (t.getEndDate() != null) {
			this.endDate = sdf.format(t.getEndDate());
		}
		this.trainOrgan = t.getTrainOrgan().getName();
		this.inputOrgan = t.getInputOrgan().getName();
		this.yearPlan = t.getYearPlan().getName();
		this.statusSign = t.getStatus();
		this.status = convertState(t.getStatus());
	}
	
	public String convertState(int state) {
		if (state == 1) {
			return "待提交培训考核";
		} else if (state == 2) {
			return "待上级单位初审";
		} else if (state == 3) {
			return "待区人事主管部门一级初审";
		} else if (state == 4) {
			return "待区人事主管部门二级初审";
		} else if (state == 5) {
			return "待区人事主管部门三级初审";
		} else if (state == 6) {
			return "待区人事主管部门四级初审";
		} else if (state == 7) {
			return "待组织部复审";
		} else if (state == 8) {
			return "待区人事管理部门确认";
		} else {
			return "已完成";
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
	 * @return the trainName
	 */
	public String getTrainName() {
		return trainName;
	}

	/**
	 * @param trainName the trainName to set
	 */
	public void setTrainName(String trainName) {
		this.trainName = trainName;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the trainOrgan
	 */
	public String getTrainOrgan() {
		return trainOrgan;
	}

	/**
	 * @param trainOrgan the trainOrgan to set
	 */
	public void setTrainOrgan(String trainOrgan) {
		this.trainOrgan = trainOrgan;
	}

	/**
	 * @return the inputOrgan
	 */
	public String getInputOrgan() {
		return inputOrgan;
	}

	/**
	 * @param inputOrgan the inputOrgan to set
	 */
	public void setInputOrgan(String inputOrgan) {
		this.inputOrgan = inputOrgan;
	}

	/**
	 * @return the yearPlan
	 */
	public String getYearPlan() {
		return yearPlan;
	}

	/**
	 * @param yearPlan the yearPlan to set
	 */
	public void setYearPlan(String yearPlan) {
		this.yearPlan = yearPlan;
	}

	/**
	 * @return the statusSign
	 */
	public Integer getStatusSign() {
		return statusSign;
	}

	/**
	 * @param statusSign the statusSign to set
	 */
	public void setStatusSign(Integer statusSign) {
		this.statusSign = statusSign;
	}
}
