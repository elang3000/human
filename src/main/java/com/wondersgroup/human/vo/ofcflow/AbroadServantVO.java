/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadServantVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月13日 下午3:21:11 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月13日 下午3:21:11 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofcflow.AbroadServant;

/** 
 * @ClassName: AbroadServantVO 
 * @Description: 因公出国事项VO
 * @author: lihao
 * @date: 2018年12月13日 下午3:21:11
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class AbroadServantVO {
	
	private String id;
	private Integer day;//停留时间
	private String yearPlanName;//出访组团名称
	private String status;//
	private String country;//出访国家（地区）
	private Integer statusSign;//
	
	public AbroadServantVO(AbroadServant a){
		this.id = a.getId();
		this.yearPlanName = a.getYearPlan().getName();
		this.day = a.getYearPlan().getDay();
		this.country = a.getYearPlan().getCountry();
		// 如果已经提交流程，并且是起始节点，设置流程状态为99，不能编辑和删除
		if (a.getFlowRecord() != null && a.getStatus() == AbroadServant.STATUS_ABROAD_PLAN_STEP1) {
			this.statusSign = 99;
		} else {
			this.statusSign = a.getStatus();
		}
		this.status = convertState(a.getStatus());
	}
	
	private String convertState(int state) {
		if(state == FlowRecord.BUS_STOP){
			return "业务被中止";
		}else if (state == 1) {
			return "待提交因公出国政审";
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
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * @return the yearPlanName
	 */
	public String getYearPlanName() {
		return yearPlanName;
	}
	/**
	 * @param yearPlanName the yearPlanName to set
	 */
	public void setYearPlanName(String yearPlanName) {
		this.yearPlanName = yearPlanName;
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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
