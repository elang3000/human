/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: JobLevel.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 试用信息
 * 创建人: jiang
 * 创建时间: 2018年6月12日09:44:56
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日09:44:59
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.base.BaseJobLevel;

/**
 * @ClassName: JobLevel
 * @Description: 上海 职级信息
 * @author: jiang
 * @date: 2018年6月12日09:45:23
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "A05")
public class JobLevel extends BaseJobLevel<JobLevel> {
	
	private static final long serialVersionUID = -70237391712662148L;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: 现行职级状态
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人担任职务或享受职级的状态。*ZB14—2016/RZZT《任职状态代码》
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A0524")
	private CodeInfo status;
	
	@Column(name = "IS_LOW_TO_HIGH")
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean isLowToHigh = false;
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}
	
	public CodeInfo getStatus() {
		
		return status;
	}
	
	public void setStatus(CodeInfo status) {
		
		this.status = status;
	}
	
	public Boolean getIsLowToHigh() {
		
		return isLowToHigh;
	}
	
	public void setIsLowToHigh(Boolean isLowToHigh) {
		
		this.isLowToHigh = isLowToHigh;
	}
}
