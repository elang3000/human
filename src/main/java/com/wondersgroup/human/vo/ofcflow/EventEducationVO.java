/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EventEducationVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 流程信息 学历 VO
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

import com.wondersgroup.human.bo.ofcflow.EventEducation;

/**
 * @ClassName: EventEducationVO
 * @Description: 流程信息 学历 VO
 * @author: tzy
 * @date: 2018年8月2日 下午3:18:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class EventEducationVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * @fieldName: 学历名称
	 */
	private String name;
	
	/**
	 * @fieldName: 入学日期
	 */
	private String enterDate;
	
	/**
	 * @fieldName: 毕（肄、结）业日期
	 */
	private String graduateDate;
	
	/**
	 * @fieldName: 学历证书号
	 */
	private String educationNo;
	
	/**
	 * @fieldName: 学校（单位）名称
	 */
	private String shoolName;
	
	/**
	 * @fieldName: 所学专业名称
	 */
	private String professionName;
	
	public EventEducationVO() {
		
	}
	
	public EventEducationVO(EventEducation e) {
		this.id = e.getId();
		this.name = e.getName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (e.getEnterDate() != null) {
			this.enterDate = sdf.format(e.getEnterDate());
		}
		if (e.getGraduateDate() != null) {
			this.graduateDate = sdf.format(e.getGraduateDate());
		}
		this.educationNo = e.getEducationNo();
		this.shoolName = e.getShoolName();
		this.professionName = e.getProfessionName();
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
	
	public String getGraduateDate() {
		
		return graduateDate;
	}
	
	public void setGraduateDate(String graduateDate) {
		
		this.graduateDate = graduateDate;
	}
	
	public String getEducationNo() {
		
		return educationNo;
	}
	
	public void setEducationNo(String educationNo) {
		
		this.educationNo = educationNo;
	}
	
	public String getShoolName() {
		
		return shoolName;
	}
	
	public void setShoolName(String shoolName) {
		
		this.shoolName = shoolName;
	}
	
	public String getProfessionName() {
		
		return professionName;
	}
	
	public void setProfessionName(String professionName) {
		
		this.professionName = professionName;
	}
	
}
