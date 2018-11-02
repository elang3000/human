/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EventFamilyVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 流程信息 家庭信息 VO
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

import com.wondersgroup.human.bo.ofcflow.EventFamily;

/**
 * @ClassName: EventFamilyVO
 * @Description: 流程信息 家庭信息 VO
 * @author: tzy
 * @date: 2018年8月2日 下午3:18:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class EventFamilyVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * @fieldName: 家庭成员或社会关系人姓名
	 */
	private String name;
	
	/**
	 * @fieldName: 家庭成员或社会关系人关系名称
	 */
	private String relationName;
	
	/**
	 * @fieldName: 家庭成员或社会关系人出生日期
	 */
	private String birthDate;
	
	/**
	 * @fieldName: 家庭成员或社会关系人工作单位及职务
	 */
	private String unitAndJob;
	
	/**
	 * @fieldName: 家庭成员或社会关系人性别
	 */
	private String sex;
	
	/**
	 * @fieldName: 家庭成员或社会关系人公民身份号码
	 */
	private String identityNo;
	
	public EventFamilyVO() {
		
	}
	
	public EventFamilyVO(EventFamily e) {
		this.id = e.getId();
		this.name = e.getName();
		this.relationName = e.getRelationName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (e.getBirthDate() != null) {
			this.birthDate = sdf.format(e.getBirthDate());
		}
		this.unitAndJob = e.getUnitAndJob();
		if (e.getSex() != null) {
			this.sex = e.getSex().getName();
		}
		this.identityNo = e.getIdentityNo();
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
	
	public String getRelationName() {
		
		return relationName;
	}
	
	public void setRelationName(String relationName) {
		
		this.relationName = relationName;
	}
	
	public String getBirthDate() {
		
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		
		this.birthDate = birthDate;
	}
	
	public String getUnitAndJob() {
		
		return unitAndJob;
	}
	
	public void setUnitAndJob(String unitAndJob) {
		
		this.unitAndJob = unitAndJob;
	}
	
	public String getSex() {
		
		return sex;
	}
	
	public void setSex(String sex) {
		
		this.sex = sex;
	}
	
	public String getIdentityNo() {
		
		return identityNo;
	}
	
	public void setIdentityNo(String identityNo) {
		
		this.identityNo = identityNo;
	}
	
}
