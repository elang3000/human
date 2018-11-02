/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: TransferringexchangesPost.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 选调职位信息
 * 创建人: tzy
 * 创建时间: 2018年8月10日 下午4:15:50
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月10日 下午4:15:50
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: TransferringexchangesPost
 * @Description: 选调职位信息
 * @author: tzy
 * @date: 2018年8月10日 下午4:15:50
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_XUANDIAO_POST")
public class TransferringExchangesPost extends GenericEntity {
	
	private static final long serialVersionUID = -7513434367770137617L;
	
	/**
	 * @fieldName: plan
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.TransferringExchanges
	 * @Description: 选调计划信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID")
	private TransferringExchanges plan;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 职务名称。
	 */
	@Column(name = "NAME", length = 255, nullable = false)
	private String name;
	
	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 职务简介。
	 */
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;
	
	/**
	 * @fieldName: planEmployNum
	 * @fieldType: java.lang.int
	 * @Description: 计划选调人数
	 */
	@Column(name = "PLAN_EMPLOY_NUM")
	private int planEmployNum;
	
	/**
	 * @fieldName: objectType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 选调对象
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo objectType;
	
	/**
	 * @fieldName: postType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职务类别
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo postType;
	
	/**
	 * @fieldName: workYear
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 最低工作年限
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo workYear;
	
	/**
	 * @fieldName: professional
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 专业类别
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo professional;
	
	/**
	 * @fieldName: education
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 最低学历要求
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo education;
	
	/**
	 * @fieldName: degree
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 最低学位要求
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo degree;
	
	/**
	 * @fieldName: politicalStatus
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 政治面貌
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo politicalStatus;
	
	/**
	 * @fieldName: other
	 * @fieldType: java.lang.String
	 * @Description: 其他要求。
	 */
	@Column(name = "OTHER", length = 2000)
	private String other;
	
	/**
	 * @fieldName: undergraduate
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 本科阶段专业
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo undergraduate;
	
	/**
	 * @fieldName: graduate
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 研究生阶段专业
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo graduate;
	
	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 备注。
	 */
	@Column(name = "REMARK", length = 2000)
	private String remark;
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	public int getPlanEmployNum() {
		
		return planEmployNum;
	}
	
	public void setPlanEmployNum(int planEmployNum) {
		
		this.planEmployNum = planEmployNum;
	}
	
	public CodeInfo getObjectType() {
		
		return objectType;
	}
	
	public void setObjectType(CodeInfo objectType) {
		
		this.objectType = objectType;
	}
	
	public CodeInfo getPostType() {
		
		return postType;
	}
	
	public void setPostType(CodeInfo postType) {
		
		this.postType = postType;
	}
	
	public CodeInfo getWorkYear() {
		
		return workYear;
	}
	
	public void setWorkYear(CodeInfo workYear) {
		
		this.workYear = workYear;
	}
	
	public CodeInfo getProfessional() {
		
		return professional;
	}
	
	public void setProfessional(CodeInfo professional) {
		
		this.professional = professional;
	}
	
	public CodeInfo getEducation() {
		
		return education;
	}
	
	public void setEducation(CodeInfo education) {
		
		this.education = education;
	}
	
	public CodeInfo getDegree() {
		
		return degree;
	}
	
	public void setDegree(CodeInfo degree) {
		
		this.degree = degree;
	}
	
	public CodeInfo getPoliticalStatus() {
		
		return politicalStatus;
	}
	
	public void setPoliticalStatus(CodeInfo politicalStatus) {
		
		this.politicalStatus = politicalStatus;
	}
	
	public String getOther() {
		
		return other;
	}
	
	public void setOther(String other) {
		
		this.other = other;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public TransferringExchanges getPlan() {
		
		return plan;
	}
	
	public void setPlan(TransferringExchanges plan) {
		
		this.plan = plan;
	}
	
	public CodeInfo getUndergraduate() {
		
		return undergraduate;
	}
	
	public void setUndergraduate(CodeInfo undergraduate) {
		
		this.undergraduate = undergraduate;
	}
	
	public CodeInfo getGraduate() {
		
		return graduate;
	}
	
	public void setGraduate(CodeInfo graduate) {
		
		this.graduate = graduate;
	}
}
