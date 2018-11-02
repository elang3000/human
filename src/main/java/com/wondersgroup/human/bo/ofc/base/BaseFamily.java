/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseFamily.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 家庭成员及社会关系
 * 创建人: jiang
 * 创建时间: 2018年6月26日11:09:02
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月26日11:09:06
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: BaseFamily
 * @Description: 国标 家庭成员及社会关系
 * @author: jiang
 * @date: 2018年6月26日11:09:11
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseFamily<T> extends GenericEntity {
	
	private static final long serialVersionUID = 7232509681518959282L;
	
	/**
	 * @fieldName: 家庭成员或社会关系人姓名
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人的姓名全称。
	 */
	@Column(name = "A36001", length = 50)
	private String name;
	
	/**
	 * @fieldName: 家庭成员或社会关系人关系名称
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人与该人的关系名称。
	 */
	@Column(name = "A36005A", length = 52)
	private String relationName;
	
	/**
	 * @fieldName: 家庭成员或社会关系人关系代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭现在主要成员或社会关系与该人的关系的代码。GB/T 4761-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36005B")
	private CodeInfo relationCode;
	
	/**
	 * @fieldName: 家庭成员或社会关系人出生日期
	 * @fieldType: java.util.Date
	 * @Description: 家庭成员或社会关系人的出生日期。GB/T 7408-2005
	 */
	@Column(name = "A36007")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	/**
	 * @fieldName: 家庭成员或社会关系人工作单位及职务
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人当前所在的工作单位名称及职务。
	 */
	@Column(name = "A36011", length = 2000)
	private String unitAndJob;
	
	/**
	 * @fieldName: 家庭成员或社会关系人性别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人员的性别。GB/T2261.1—2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36012")
	private CodeInfo sex;
	
	/**
	 * @fieldName: 家庭成员或社会关系人公民身份号码
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人的公民身份号码。
	 */
	@Column(name = "A36013", length = 18)
	private String identityNo;
	
	/**
	 * @fieldName: 家庭成员或社会关系人国籍
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人当前的国籍名称。GB/T 2659
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36017")
	private CodeInfo nationality;
	
	/**
	 * @fieldName: 家庭成员或社会关系人民族
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人的民族名称。GB 3304-91
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36021")
	private CodeInfo nation;
	
	/**
	 * @fieldName: 家庭成员或社会关系人学历
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人的最高学历。GB/T 4658-2006
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36025")
	private CodeInfo education;
	
	/**
	 * @fieldName: 家庭成员或社会关系人政治面貌
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人的政治面貌。
	 */
	@Column(name = "A36027A", length = 60)
	private String politicalName;
	
	/**
	 * @fieldName: 家庭成员或社会关系人政治面貌代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人的政治面貌代码。GB 4762—1984
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36027B")
	private CodeInfo politicalCode;
	
	/**
	 * @fieldName: 夫妻两地分居日期
	 * @fieldType: java.util.Date
	 * @Description: 夫妻两地分居的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A36030")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date separationStartDate;
	
	/**
	 * @fieldName: 家庭成员或社会关系人身份
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人按人员管理规定划分的个人身份。GB/T 2261.4-2003
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36031")
	private CodeInfo socialPosition;
	
	/**
	 * @fieldName: 家庭成员或社会关系人职务
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员及社会关系人员担任的职务。
	 */
	@Column(name = "A36032", length = 80)
	private String post;
	
	/**
	 * @fieldName: 家庭成员或社会关系人职级
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人当前或曾任的职级。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36038")
	private CodeInfo rank;
	
	/**
	 * @fieldName: 家庭成员或社会关系人现状
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人当前的生活、工作及在世状况。DM125
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A36041")
	private CodeInfo presentSituation;
	
	/**
	 * @fieldName: 家庭成员或社会关系人备注
	 * @fieldType: java.lang.String
	 * @Description: 有关该家庭成员和社会关系人情况的补充说明。
	 */
	@Column(name = "A36044", length = 80)
	private String remark;
	
	/**
	 * @fieldName: 家庭成员或社会关系人序号
	 * @fieldType: java.lang.Integer
	 * @Description: 根据有关规定该人在家庭成员及社会关系当中的排序号。
	 */
	@Column(name = "A36047", length = 2)
	private Integer sortNo;
	
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
	
	public CodeInfo getRelationCode() {
		
		return relationCode;
	}
	
	public void setRelationCode(CodeInfo relationCode) {
		
		this.relationCode = relationCode;
	}
	
	public Date getBirthDate() {
		
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		
		this.birthDate = birthDate;
	}
	
	public String getUnitAndJob() {
		
		return unitAndJob;
	}
	
	public void setUnitAndJob(String unitAndJob) {
		
		this.unitAndJob = unitAndJob;
	}
	
	public CodeInfo getSex() {
		
		return sex;
	}
	
	public void setSex(CodeInfo sex) {
		
		this.sex = sex;
	}
	
	public String getIdentityNo() {
		
		return identityNo;
	}
	
	public void setIdentityNo(String identityNo) {
		
		this.identityNo = identityNo;
	}
	
	public CodeInfo getNationality() {
		
		return nationality;
	}
	
	public void setNationality(CodeInfo nationality) {
		
		this.nationality = nationality;
	}
	
	public CodeInfo getNation() {
		
		return nation;
	}
	
	public void setNation(CodeInfo nation) {
		
		this.nation = nation;
	}
	
	public CodeInfo getEducation() {
		
		return education;
	}
	
	public void setEducation(CodeInfo education) {
		
		this.education = education;
	}
	
	public String getPoliticalName() {
		
		return politicalName;
	}
	
	public void setPoliticalName(String politicalName) {
		
		this.politicalName = politicalName;
	}
	
	public CodeInfo getPoliticalCode() {
		
		return politicalCode;
	}
	
	public void setPoliticalCode(CodeInfo politicalCode) {
		
		this.politicalCode = politicalCode;
	}
	
	public Date getSeparationStartDate() {
		
		return separationStartDate;
	}
	
	public void setSeparationStartDate(Date separationStartDate) {
		
		this.separationStartDate = separationStartDate;
	}
	
	public CodeInfo getSocialPosition() {
		
		return socialPosition;
	}
	
	public void setSocialPosition(CodeInfo socialPosition) {
		
		this.socialPosition = socialPosition;
	}
	
	public String getPost() {
		
		return post;
	}
	
	public void setPost(String post) {
		
		this.post = post;
	}
	
	public CodeInfo getRank() {
		
		return rank;
	}
	
	public void setRank(CodeInfo rank) {
		
		this.rank = rank;
	}
	
	public CodeInfo getPresentSituation() {
		
		return presentSituation;
	}
	
	public void setPresentSituation(CodeInfo presentSituation) {
		
		this.presentSituation = presentSituation;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public Integer getSortNo() {
		
		return sortNo;
	}
	
	public void setSortNo(Integer sortNo) {
		
		this.sortNo = sortNo;
	}
	
}
