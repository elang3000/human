/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RecruitPostVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 职务简章-职位信息 VO
 * 创建人: wangzhanfei
 * 创建时间: 2018年5月30日 下午4:14:49
 * 版本号: V1.0
 * 修改人：wangzhanfei
 * 修改时间：2018年5月30日 下午4:14:49
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.RecruitPost;

/**
 * @ClassName: RecruitPostVO
 * @Description: 职务简章-职位信息 VO
 * @author: wangzhanfei
 * @date: 2018年5月30日 下午4:14:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class RecruitPostVO {
	
	private String id;
	
	private String name;
	
	private String objectType;
	
	private String planEmployNum;
	
	private String politicalStatus;
	
	private String workYear;
	
	private String education;
	
	private String degree;
	
	private String description;
	
	private String undergraduateCourse;
	
	private String graduateCourse;
	
	/**
	 * @Description: 专业考试科目
	 */
	private String majorSubject;
	
	public RecruitPostVO() {
		
	}
	
	public RecruitPostVO(RecruitPost plan) {
		this.id = plan.getId();
		this.name = plan.getName();
		if (plan.getObjectType() != null) {
			this.objectType = plan.getObjectType().getName();
		}
		if (plan.getPlanEmployNum() != null) {
			this.planEmployNum = String.valueOf(plan.getPlanEmployNum());
		}
		if (plan.getPoliticalStatus() != null) {
			this.politicalStatus = plan.getPoliticalStatus().getName();
		}
		if (plan.getWorkYear() != null) {
			this.workYear = plan.getWorkYear().getName();
		}
		if (plan.getEducation() != null) {
			this.education = plan.getEducation().getName();
		}
		if (plan.getDegree() != null) {
			this.degree = plan.getDegree().getName();
		}
		if (plan.getUndergraduateCourse() != null) {
			this.undergraduateCourse = plan.getUndergraduateCourse().getName();
		}
		if (plan.getGraduateCourse() != null) {
			this.graduateCourse = plan.getGraduateCourse().getName();
		}
		this.majorSubject = plan.getMajorSubject();
		this.description = plan.getDescription();
	}
	
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
	
	public String getObjectType() {
		
		return objectType;
	}
	
	public void setObjectType(String objectType) {
		
		this.objectType = objectType;
	}
	
	public String getPlanEmployNum() {
		
		return planEmployNum;
	}
	
	public void setPlanEmployNum(String planEmployNum) {
		
		this.planEmployNum = planEmployNum;
	}
	
	public String getPoliticalStatus() {
		
		return politicalStatus;
	}
	
	public void setPoliticalStatus(String politicalStatus) {
		
		this.politicalStatus = politicalStatus;
	}
	
	public String getWorkYear() {
		
		return workYear;
	}
	
	public void setWorkYear(String workYear) {
		
		this.workYear = workYear;
	}
	
	public String getEducation() {
		
		return education;
	}
	
	public void setEducation(String education) {
		
		this.education = education;
	}
	
	public String getDegree() {
		
		return degree;
	}
	
	public void setDegree(String degree) {
		
		this.degree = degree;
	}
	
	public String getMajorSubject() {
		
		return majorSubject;
	}
	
	public void setMajorSubject(String majorSubject) {
		
		this.majorSubject = majorSubject;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	public String getUndergraduateCourse() {
		
		return undergraduateCourse;
	}
	
	public void setUndergraduateCourse(String undergraduateCourse) {
		
		this.undergraduateCourse = undergraduateCourse;
	}
	
	public String getGraduateCourse() {
		
		return graduateCourse;
	}
	
	public void setGraduateCourse(String graduateCourse) {
		
		this.graduateCourse = graduateCourse;
	}
	
}
