/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitYearPlanVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lh   
 * 创建时间: 2018年9月18日 上午9:57:48 
 * 版本号: V1.0
 * 修改人：lh 
 * 修改时间：2018年9月18日 上午9:57:48 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.AbroadPlan;

/** 
 * @ClassName: RecruitYearPlanVO 
 * @Description: TODO
 * @author: lh
 * @date: 2018年9月18日 上午9:57:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class AbroadPlanVO {
  
	private String id;
	private String name;//申请人姓名
	private Integer day;//停留时间
	private String yearPlanName;//出访组团名称
	private String status;//
	private String country;//出访国家（地区）
	private Integer statusSign;//
	private String departName;//申请人单位
	private String postName;//申请人职务
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getYearPlanName() {
		return yearPlanName;
	}
	public void setYearPlanName(String yearPlanName) {
		this.yearPlanName = yearPlanName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getStatusSign() {
		return statusSign;
	}
	public void setStatusSign(Integer statusSign) {
		this.statusSign = statusSign;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public AbroadPlanVO(AbroadPlan a){
		this.id = a.getId();
		this.name = a.getServant().getName();
		this.yearPlanName = a.getYearPlan().getName();
		this.day = a.getYearPlan().getDay();
		this.country = a.getYearPlan().getCountry();
		this.statusSign = a.getStatus();
		this.status = convertState(a.getStatus());
		this.postName = a.getServant().getNowPostName();
		this.departName = a.getServant().getDepartName();
	}
	/** 
	 * @Title: convertState 
	 * @Description: TODO
	 * @param state2
	 * @return
	 * @return: String
	 */
	private String convertState(int state) {
		if (state == 1) {
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
}
