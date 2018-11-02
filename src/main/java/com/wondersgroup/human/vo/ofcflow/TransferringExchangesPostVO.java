/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: TransferringExchangesPostVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 选调职位信息 VO
 * 创建人: tzy
 * 创建时间: 2018年8月14日 上午10:29:59
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月14日 上午10:29:59
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.TransferringExchangesPost;

/**
 * @ClassName: TransferringExchangesPostVO
 * @Description: 选调职位信息 VO
 * @author: tzy
 * @date: 2018年8月14日 上午10:29:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class TransferringExchangesPostVO {
	
	private String id;
	
	/**
	 * @Description: 职务名称。
	 */
	private String name;
	
	/**
	 * @Description: 计划选调人数
	 */
	private String planEmployNum;
	
	/**
	 * @Description: 职务类别
	 */
	private String postType;
	
	/**
	 * @Description: 最低工作年限
	 */
	private String workYear;
	
	/**
	 * @Description: 专业类别
	 */
	private String professional;
	
	/**
	 * @Description: 最低学历要求
	 */
	private String education;
	
	/**
	 * @Description: 最低学位要求
	 */
	private String degree;
	
	/**
	 * @Description: 政治面貌
	 */
	private String politicalStatus;
	
	public TransferringExchangesPostVO() {
		
	}
	
	public TransferringExchangesPostVO(TransferringExchangesPost t) {
		this.id = t.getId();
		this.name = t.getName();
		this.planEmployNum = String.valueOf(t.getPlanEmployNum());
		if (t.getPostType() != null) {
			this.postType = t.getPostType().getName();
		}
		if (t.getWorkYear() != null) {
			this.workYear = t.getWorkYear().getName();
		}
		if (t.getProfessional() != null) {
			this.professional = t.getProfessional().getName();
		}
		if (t.getEducation() != null) {
			this.education = String.valueOf(t.getEducation().getName());
		}
		if (t.getDegree() != null) {
			this.degree = t.getDegree().getName();
		}
		if (t.getPoliticalStatus() != null) {
			this.politicalStatus = t.getPoliticalStatus().getName();
		}
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
	
	public String getPlanEmployNum() {
		
		return planEmployNum;
	}
	
	public void setPlanEmployNum(String planEmployNum) {
		
		this.planEmployNum = planEmployNum;
	}
	
	public String getPostType() {
		
		return postType;
	}
	
	public void setPostType(String postType) {
		
		this.postType = postType;
	}
	
	public String getWorkYear() {
		
		return workYear;
	}
	
	public void setWorkYear(String workYear) {
		
		this.workYear = workYear;
	}
	
	public String getProfessional() {
		
		return professional;
	}
	
	public void setProfessional(String professional) {
		
		this.professional = professional;
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
	
	public String getPoliticalStatus() {
		
		return politicalStatus;
	}
	
	public void setPoliticalStatus(String politicalStatus) {
		
		this.politicalStatus = politicalStatus;
	}
}
