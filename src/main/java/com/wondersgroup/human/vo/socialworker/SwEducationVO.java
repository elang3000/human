/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EducationVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-学历VO
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:37:35
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:37:38
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.socialworker;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.socialworker.SrEducation;

/**
 * @ClassName: EducationVO
 * @Description: 人员信息子表-学历VO
 * @author: jiang
 * @date: 2018年7月2日14:37:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class SwEducationVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 学历名称
	 * @fieldType: java.lang.String
	 * @Description: 由国家认可的该人在国内、外各类教育机构接受正式教育并取得学历证书的学习经历名称。
	 */
	private String name;
	
	/**
	 * @fieldName: 入学日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得学历学习的起始日期。GB/T 7408-2005
	 */
	private String enterDate;
	
	/**
	 * @fieldName: 学历证书号
	 * @fieldType: java.lang.String
	 * @Description: 取得学历证书号码。
	 */
	private String educationNo;
	
	/**
	 * @fieldName: 毕（肄、结）业日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得学历学习的起始日期。GB/T 7408-2005
	 */
	private String graduateDate;
	
	/**
	 * @fieldName: 学校（单位）名称
	 * @fieldType: java.lang.String
	 * @Description: 该人接受该阶段教育并获得有关证书时的学校、院系或单位名称。
	 */
	private String shoolName;
	
	public SwEducationVO() {
		
	}
	
	/**
	 * @Title:EducationVO
	 * @Description:将Education bo转换为vo
	 * @param s 转换的Education BO对象
	 */
	public SwEducationVO(SrEducation s) {
		this.id = s.getId();
		this.name = s.getName();
		this.educationNo = s.getEducationNo();
		this.shoolName = s.getShoolName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getEnterDate() != null) {
			this.enterDate = sdf.format(s.getEnterDate());
		}
		if (s.getGraduateDate() != null) {
			this.graduateDate = sdf.format(s.getGraduateDate());
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
	
	public String getEnterDate() {
		
		return enterDate;
	}
	
	public void setEnterDate(String enterDate) {
		
		this.enterDate = enterDate;
	}
	
	public String getEducationNo() {
		
		return educationNo;
	}
	
	public void setEducationNo(String educationNo) {
		
		this.educationNo = educationNo;
	}
	
	public String getGraduateDate() {
		
		return graduateDate;
	}
	
	public void setGraduateDate(String graduateDate) {
		
		this.graduateDate = graduateDate;
	}
	
	public String getShoolName() {
		
		return shoolName;
	}
	
	public void setShoolName(String shoolName) {
		
		this.shoolName = shoolName;
	}
	
}
