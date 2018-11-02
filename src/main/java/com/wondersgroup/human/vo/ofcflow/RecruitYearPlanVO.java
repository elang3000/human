/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitYearPlanVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月29日 上午9:57:48 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月29日 上午9:57:48 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

/** 
 * @ClassName: RecruitYearPlanVO 
 * @Description: TODO
 * @author: wangzhanfei
 * @date: 2018年5月29日 上午9:57:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class RecruitYearPlanVO {
  
	private String id;
	private String name;
	private String startDate;
	private String endDate;
	private String state;
	private String description;
	
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
