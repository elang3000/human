/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: FamilyVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-家庭VO
 * 创建人: jiang
 * 创建时间: 2018年8月20日10:26:56
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月20日10:26:58
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.pubinst;

import java.text.SimpleDateFormat;

import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.pubinst.PtFamily;

/**
 * @ClassName: FamilyVO
 * @Description: 人员信息子表-家庭VO
 * @author: jiang
 * @date: 2018年8月20日10:27:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PtFamilyVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 家庭成员或社会关系人姓名
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人的姓名全称。
	 */
	private String name;
	
	/**
	 * @fieldName: 家庭成员或社会关系人关系名称
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人与该人的关系名称。
	 */
	private String relationName;
	
	/**
	 * @fieldName: 家庭成员或社会关系人出生日期
	 * @fieldType: java.util.Date
	 * @Description: 家庭成员或社会关系人的出生日期。GB/T 7408-2005
	 */
	private String birthDate;
	
	/**
	 * @fieldName: 家庭成员或社会关系人工作单位及职务
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人当前所在的工作单位名称及职务。
	 */
	private String unitAndJob;
	
	/**
	 * @fieldName: 家庭成员或社会关系人性别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人员的性别。GB/T2261.1—2003
	 */
	private String sex;
	
	/**
	 * @fieldName: 家庭成员或社会关系人公民身份号码
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人的公民身份号码。
	 */
	private String identityNo;
	
	/**
	 * @fieldName: 家庭成员或社会关系人民族
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人的民族名称。GB 3304-91
	 */
	private String nation;
	
	/**
	 * @fieldName: 家庭成员或社会关系人政治面貌
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人的政治面貌。
	 */
	private String politicalName;
	
	/**
	 * @fieldName: 家庭成员或社会关系人职务
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员及社会关系人员担任的职务。
	 */
	private String post;
	
	/**
	 * @fieldName: 家庭成员或社会关系人序号
	 * @fieldType: java.lang.Integer
	 * @Description: 根据有关规定该人在家庭成员及社会关系当中的排序号。
	 */
	private String sortNo;
	
	
	public PtFamilyVO() {
		
	}
	
	/**
	 * @Title:FamilyVO
	 * @Description:将Family bo转换为vo
	 * @param s 转换的Family BO对象
	 */
	public PtFamilyVO(PtFamily s) {
		this.id = s.getId();
		this.name = s.getName();
		this.relationName = s.getRelationCode().getName();
		this.unitAndJob = s.getUnitAndJob();
		this.identityNo = s.getIdentityNo();
		this.politicalName = s.getPoliticalName();
		if(s.getSortNo()!=null){
			this.sortNo = s.getSortNo().toString();
		}
		this.post = s.getPost();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getBirthDate() != null) {
			this.birthDate = sdf.format(s.getBirthDate());
		}
		if (s.getSex() != null) {
			this.sex = s.getSex().getName();
		}
		if (s.getNation() != null) {
			this.nation = s.getNation().getName();
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

	
	public String getNation() {
		
		return nation;
	}

	
	public void setNation(String nation) {
		
		this.nation = nation;
	}

	
	public String getPoliticalName() {
		
		return politicalName;
	}

	
	public void setPoliticalName(String politicalName) {
		
		this.politicalName = politicalName;
	}

	
	public String getPost() {
		
		return post;
	}

	
	public void setPost(String post) {
		
		this.post = post;
	}

	
	public String getSortNo() {
		
		return sortNo;
	}

	
	public void setSortNo(String sortNo) {
		
		this.sortNo = sortNo;
	}
	
}
