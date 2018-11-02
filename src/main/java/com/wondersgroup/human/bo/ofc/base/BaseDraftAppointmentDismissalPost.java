/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseDraftAppointmentDismissalPost.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 拟任拟免职务
 * 创建人: jiang
 * 创建时间: 2018年6月25日09:02:15
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月25日09:02:19
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
 * @ClassName: BaseDraftAppointmentDismissalPost
 * @Description: 国标 拟任拟免职务
 * @author: jiang
 * @date: 2018年6月25日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseDraftAppointmentDismissalPost<T> extends GenericEntity {

	private static final long serialVersionUID = 4243667922862227977L;
	
	/**
	 * @fieldName: 拟任免机构名称
	 * @fieldType: java.lang.String
	 * @Description: 与拟任免职务相对应的工作机构及工作机构部门名称。
	 */
	@Column(name = "A53001A", length = 120)
	private String orgName;
	
	/**
	 * @fieldName: 拟任免机构代码
	 * @fieldType: java.lang.String
	 * @Description: 与拟任免职务相对应的工作机构及工作机构部门代码。GB 32100-2015
	 */
	@Column(name = "A53001B", length = 18)
	private String orgCode;
	
	/**
	 * @fieldName: 拟任免职务名称
	 * @fieldType: java.lang.String
	 * @Description: 该人拟任或拟免的职务名称。GB 32100-2015
	 */
	@Column(name = "A53005A", length = 80)
	private String postName;
	
	/**
	 * @fieldName: 拟任免职务代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人拟任或拟免的职务的代码。GB/T 12403-1990
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A53005B")
	private CodeInfo postCode;

	
	public String getOrgName() {
		
		return orgName;
	}

	
	public void setOrgName(String orgName) {
		
		this.orgName = orgName;
	}

	
	public String getOrgCode() {
		
		return orgCode;
	}

	
	public void setOrgCode(String orgCode) {
		
		this.orgCode = orgCode;
	}

	
	public String getPostName() {
		
		return postName;
	}

	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}

	
	public CodeInfo getPostCode() {
		
		return postCode;
	}

	
	public void setPostCode(CodeInfo postCode) {
		
		this.postCode = postCode;
	}
	
	
}
