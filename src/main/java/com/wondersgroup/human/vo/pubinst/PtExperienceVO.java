/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ExperienceVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-简历VO
 * 创建人: jiang
 * 创建时间: 2018年7月2日15:18:53
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日15:18:56
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.pubinst;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Experience;
import com.wondersgroup.human.bo.pubinst.PtExperience;

/**
 * @ClassName: ExperienceVO
 * @Description: 人员信息子表-简历VO
 * @author: jiang
 * @date: 2018年7月2日15:19:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PtExperienceVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 曾在单位
	 * @fieldType: java.lang.String
	 * @Description: 在某一日期内具体工作单位的名称。
	 */
	private String formerUnit;
	
	/**
	 * @fieldName: 曾在单位起始日期
	 * @fieldType: java.util.Date
	 * @Description: 在某一单位（部门）工作的开始日期。GB/T 7408-2005
	 */
	private String startDate;
	
	/**
	 * @fieldName: 曾在单位终止日期
	 * @fieldType: java.util.Date
	 * @Description: 在某一单位（部门）工作的结束日期。GB/T 7408-2005
	 */
	private String endDate;
	
	/**
	 * @fieldName: 曾经从事工作或担任职务
	 * @fieldType: java.lang.String
	 * @Description: 在某一单位（部门）工作（学习）期间所从事的工作或担任的职务。
	 */
	private String formerJob;
	
	public PtExperienceVO() {
		
	}
	
	/**
	 * @Title:ExperienceVO
	 * @Description:将Experience bo转换为vo
	 * @param e 转换的Experience BO对象
	 */
	public PtExperienceVO(PtExperience e) {
		this.id = e.getId();
		this.formerUnit = e.getFormerUnit();
		this.formerJob = e.getFormerJob();
		if (e.getStartDate() != null) {
			this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(e.getStartDate());
		}
		if (e.getEndDate() != null) {
			this.endDate = new SimpleDateFormat("yyyy-MM-dd").format(e.getEndDate());
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getFormerUnit() {
		
		return formerUnit;
	}

	
	public void setFormerUnit(String formerUnit) {
		
		this.formerUnit = formerUnit;
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

	
	public String getFormerJob() {
		
		return formerJob;
	}

	
	public void setFormerJob(String formerJob) {
		
		this.formerJob = formerJob;
	}
	
}
