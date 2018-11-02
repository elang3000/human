/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EventExperienceVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 流程信息 简历 VO
 * 创建人: tzy
 * 创建时间: 2018年8月2日 下午3:18:44
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月2日 下午3:18:44
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofcflow.EventExperience;

/**
 * @ClassName: EventExperienceVO
 * @Description: 流程信息 简历 VO
 * @author: tzy
 * @date: 2018年8月2日 下午3:18:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class EventExperienceVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * @fieldName: 简历
	 */
	private String introduce;
	
	/**
	 * @fieldName: 曾在单位起始日期
	 */
	private String startDate;
	
	/**
	 * @fieldName: 曾在单位终止日期
	 */
	private String endDate;
	
	/**
	 * @fieldName: 曾在单位
	 */
	private String formerUnit;
	
	/**
	 * @fieldName: 曾经从事工作或担任职务
	 */
	private String formerJob;
	
	/**
	 * @fieldName: 曾在单位职务类别
	 */
	private String formerJobCategory;
	
	public EventExperienceVO() {
		
	}
	
	public EventExperienceVO(EventExperience e) {
		this.id = e.getId();
		this.introduce = e.getIntroduce();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (e.getStartDate() != null) {
			this.startDate = sdf.format(e.getStartDate());
		}
		if (e.getEndDate() != null) {
			this.endDate = sdf.format(e.getEndDate());
		}
		this.formerUnit = e.getFormerUnit();
		this.formerJob = e.getFormerJob();
		if (e.getFormerJobCategory() != null) {
			this.formerJobCategory = e.getFormerJobCategory().getName();
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getIntroduce() {
		
		return introduce;
	}
	
	public void setIntroduce(String introduce) {
		
		this.introduce = introduce;
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
	
	public String getFormerUnit() {
		
		return formerUnit;
	}
	
	public void setFormerUnit(String formerUnit) {
		
		this.formerUnit = formerUnit;
	}
	
	public String getFormerJob() {
		
		return formerJob;
	}
	
	public void setFormerJob(String formerJob) {
		
		this.formerJob = formerJob;
	}
	
	public String getFormerJobCategory() {
		
		return formerJobCategory;
	}
	
	public void setFormerJobCategory(String formerJobCategory) {
		
		this.formerJobCategory = formerJobCategory;
	}
}
