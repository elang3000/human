/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: AssessmentVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-考核VO
 * 创建人: jiang
 * 创建时间: 2018年7月2日10:06:19
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日10:06:22
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Assessment;

/**
 * @ClassName: AssessmentVO
 * @Description: 人员信息子表-考核VO
 * @author: jiang
 * @date: 2018年7月2日10:06:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class AssessmentVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 考核类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 对该人考核的类别。DM017
	 */
	private String category;
	
	/**
	 * @fieldName: 考核组织名称
	 * @fieldType: java.lang.String
	 * @Description: 对该人进行考核的组织的名称。
	 */
	private String organizationName;
	
	/**
	 * @fieldName: 考核年度
	 * @fieldType: java.util.Date
	 * @Description: 进行该次考核的年度。GB/T 7408-2005
	 */
	private String assessmentYear;
	
	/**
	 * @fieldName: 考核结论类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 对该人考核的结论意见的分类。DM018
	 */
	private String conclusionCategory;
	
	
	public AssessmentVO() {
		
	}
	
	/**
	 * @Title:AssessmentVO
	 * @Description:将Assessment bo转换为vo
	 * @param e 转换的Assessment BO对象
	 */
	public AssessmentVO(Assessment e) {
		this.id = e.getId();
		this.organizationName = e.getOrganizationName();
		if (e.getAssessmentYear() != null) {
			this.assessmentYear = new SimpleDateFormat("yyyy").format(e.getAssessmentYear());
		}
		if (e.getCategory() != null) {
			this.category = e.getCategory().getName();
		}
		if (e.getConclusionCategory() != null) {
			this.conclusionCategory = e.getConclusionCategory().getName();
		}
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getCategory() {
		
		return category;
	}

	
	public void setCategory(String category) {
		
		this.category = category;
	}

	
	public String getOrganizationName() {
		
		return organizationName;
	}

	
	public void setOrganizationName(String organizationName) {
		
		this.organizationName = organizationName;
	}

	
	public String getAssessmentYear() {
		
		return assessmentYear;
	}

	
	public void setAssessmentYear(String assessmentYear) {
		
		this.assessmentYear = assessmentYear;
	}

	
	public String getConclusionCategory() {
		
		return conclusionCategory;
	}

	
	public void setConclusionCategory(String conclusionCategory) {
		
		this.conclusionCategory = conclusionCategory;
	}
	
}
