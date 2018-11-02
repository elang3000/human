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
import java.text.SimpleDateFormat;
import com.wondersgroup.human.bo.ofcflow.AbroadYearPlan;

/** 
 * @ClassName: RecruitYearPlanVO 
 * @Description: TODO
 * @author: lh
 * @date: 2018年9月18日 上午9:57:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class AbroadYearPlanVO {
  
	private String id;
	private String name;
	private String startDate;
	private String endDate;
	private String state;
	private String country;
	private Integer day;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public AbroadYearPlanVO(AbroadYearPlan a){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		this.id = a.getId();
		this.name = a.getName();
		this.country = a.getCountry();
		if (a.getStartDate() != null) {
			this.startDate = sdf.format(a.getStartDate());
		}
		if (a.getEndDate() != null) {
			this.endDate = sdf.format(a.getEndDate());
		}
		this.state = convertState(a.getState());
		this.day = a.getDay();
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
			return "开启";
		} else {
			return "关闭";
		}
	} 
}
