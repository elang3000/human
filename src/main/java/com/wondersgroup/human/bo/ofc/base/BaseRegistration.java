/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseRegistration.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 公务员登记
 * 创建人: jiang
 * 创建时间: 2018年6月26日15:08:06
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月26日15:08:10
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
 * @ClassName: BaseRegistration
 * @Description: 国标 公务员的登记
 * @author: jiang
 * @date: 2018年6月26日15:08:25
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseRegistration<T> extends GenericEntity {

	private static final long serialVersionUID = -4621664898056326542L;
	
	/**
	 * @fieldName: 公务员登记标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员登记情况的标识。DM084
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A61001")
	private CodeInfo registrationCode;
	
	/**
	 * @fieldName: 列入公务员法实施范围管理类别标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人列入公务员法实施范围管理的标识。DM085
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A61009")
	private CodeInfo manageCode;
	
	/**
	 * @fieldName: 公务员登记日期
	 * @fieldType: java.util.Date
	 * @Description: 该人进行公务员登记的具体日期。GB/T 7408-2005
	 */
	@Column(name = "A61049")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;

	
	public CodeInfo getRegistrationCode() {
		
		return registrationCode;
	}

	
	public void setRegistrationCode(CodeInfo registrationCode) {
		
		this.registrationCode = registrationCode;
	}

	
	public CodeInfo getManageCode() {
		
		return manageCode;
	}

	
	public void setManageCode(CodeInfo manageCode) {
		
		this.manageCode = manageCode;
	}

	
	public Date getRegistrationDate() {
		
		return registrationDate;
	}

	
	public void setRegistrationDate(Date registrationDate) {
		
		this.registrationDate = registrationDate;
	}
	
}
