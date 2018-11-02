/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseNationality.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 国籍
 * 创建人: jiang
 * 创建时间: 2018年9月10日10:13:18
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日10:13:21
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: BaseNationality
 * @Description: 国标 国籍
 * @author: jiang
 * @date: 2018年9月10日10:13:24
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseNationality<T> extends GenericEntity {
	
	private static final long serialVersionUID = -4464529327548582864L;
	
	/**
	 * @fieldName: 取得国籍名称
	 * @fieldType: java.lang.String
	 * @Description: 该人所取得的国籍的名称。
	 */
	@Column(name = "A35001", length = 120)
	private String gainNationalityName;
	
	/**
	 * @fieldName: 取得该国籍的起始日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得该国籍的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A35004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date gainBeginDate;
	
	/**
	 * @fieldName: 取消该国籍的日期
	 * @fieldType: java.util.Date
	 * @Description: 该人被取消该国国籍的日期。GB/T 7408-2005
	 */
	@Column(name = "A35007")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date invalidDate;
	
	/**
	 * @fieldName: 国籍备注
	 * @fieldType: java.lang.String
	 * @Description: 有关该人该国籍的补充说明事项。
	 */
	@Column(name = "A35011", length = 2000)
	private String remarks;
	
	public String getGainNationalityName() {
		
		return gainNationalityName;
	}
	
	public void setGainNationalityName(String gainNationalityName) {
		
		this.gainNationalityName = gainNationalityName;
	}
	
	public Date getGainBeginDate() {
		
		return gainBeginDate;
	}
	
	public void setGainBeginDate(Date gainBeginDate) {
		
		this.gainBeginDate = gainBeginDate;
	}
	
	public Date getInvalidDate() {
		
		return invalidDate;
	}
	
	public void setInvalidDate(Date invalidDate) {
		
		this.invalidDate = invalidDate;
	}
	
	public String getRemarks() {
		
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		
		this.remarks = remarks;
	}
	
}
