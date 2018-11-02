/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.organization
 * 描述: TODO
 * 创建人: jiang
 * 创建时间: 2018年9月12日15:06:01
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月12日15:06:04
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.vo.organization;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.human.bo.organization.OrgInfo;

/**
 * @ClassName: OrgInfoVO
 * @Description: TODO
 * @author: jiang
 * @date: 2018年9月12日15:06:08
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */

public class OrgInfoVO {
	
	private String id;
	
	/**
	 * @fieldName: 单位名称
	 * @fieldType: java.lang.String
	 * @Description: 单位的全称。
	 */
	private String unitBasicName;
	
	/**
	 * @fieldName: 单位简称
	 * @fieldType: java.lang.String
	 * @Description: 该单位规范化的简称。
	 */
	private String unitBasicSimpleName;
	
	/**
	 * @fieldName: 单位负责人
	 * @fieldType: java.lang.String
	 * @Description: 该单位的法人或主要负责人的姓名全称。GB 32100-2015
	 */
	private String corporation;
	
	/**
	 * @fieldName: 统一社会信用代码
	 * @fieldType: java.lang.String
	 * @Description: 由 GB 32100 规定的统一社会信用代码。GB 32100-2015
	 */
	private String xydm;
	
	/**
	 * @fieldName: 成立日期
	 * @fieldType: java.util.Date
	 * @Description: 机构实际成立的日期。GB/T 7408-2005
	 */
	private String buildDate;
	
	private String organNodeType;
	
	
	public OrgInfoVO() {
		
	}
	
	public OrgInfoVO(OrgInfo orgInfo) {
		this.id = orgInfo.getId();
		this.unitBasicName = orgInfo.getUnitBasicName();
		this.unitBasicSimpleName = orgInfo.getUnitBasicSimpleName();
		this.xydm = orgInfo.getXydm();
		this.corporation = orgInfo.getCorporation();
		if(orgInfo.getBuildDate() !=null){
			this.buildDate = new SimpleDateFormat("yyyy-MM-dd").format(orgInfo.getBuildDate());
		}
		if(orgInfo.getOrgan()!= null){
			this.organNodeType = orgInfo.getOrgan().getOrganNodeType().getCode();
		}
		
	}
	
	
	public String getUnitBasicName() {
		
		return unitBasicName;
	}

	
	public void setUnitBasicName(String unitBasicName) {
		
		this.unitBasicName = unitBasicName;
	}

	public String getOrganNodeType() {
		
		return organNodeType;
	}
	
	public void setOrganNodeType(String organNodeType) {
		
		this.organNodeType = organNodeType;
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getUnitBasicSimpleName() {
		
		return unitBasicSimpleName;
	}

	
	public void setUnitBasicSimpleName(String unitBasicSimpleName) {
		
		this.unitBasicSimpleName = unitBasicSimpleName;
	}

	
	public String getCorporation() {
		
		return corporation;
	}

	
	public void setCorporation(String corporation) {
		
		this.corporation = corporation;
	}

	
	public String getXydm() {
		
		return xydm;
	}

	
	public void setXydm(String xydm) {
		
		this.xydm = xydm;
	}

	
	public String getBuildDate() {
		
		return buildDate;
	}

	
	public void setBuildDate(String buildDate) {
		
		this.buildDate = buildDate;
	}
	
}
