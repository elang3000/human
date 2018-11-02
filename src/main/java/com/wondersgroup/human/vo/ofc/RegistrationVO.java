/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RegistrationVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-学位VO
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:08:15
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:08:18
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import javax.persistence.Column;

import com.wondersgroup.human.bo.ofc.Registration;

/**
 * @ClassName: RegistrationVO
 * @Description: 人员信息子表-学位VO
 * @author: jiang
 * @date: 2018年7月2日14:08:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class RegistrationVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 公务员登记标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员登记情况的标识。DM084
	 */
	private String registrationCode;
	
	/**
	 * @fieldName: 列入公务员法实施范围管理类别标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人列入公务员法实施范围管理的标识。DM085
	 */
	private String manageCode;
	
	/**
	 * @fieldName: 公务员登记日期
	 * @fieldType: java.util.Date
	 * @Description: 该人进行公务员登记的具体日期。GB/T 7408-2005
	 */
	private String registrationDate;
	
	
	/**
	 * @fieldName: registrationNO
	 * @fieldType: java.lang.String
	 * @Description: 登记号
	 */
	private String registrationNO;
	
	public RegistrationVO() {
		
	}
	
	/**
	 * @Title:RegistrationVO
	 * @Description:将Registration bo转换为vo
	 * @param e 转换的Registration BO对象
	 */
	public RegistrationVO(Registration e) {
		this.id = e.getId();
		this.registrationNO = e.getRegistrationNO();
		if (e.getRegistrationDate() != null) {
			this.registrationDate = new SimpleDateFormat("yyyy-MM-dd").format(e.getRegistrationDate());
		}
		if (e.getRegistrationCode() != null) {
			this.registrationCode = e.getRegistrationCode().getName();
		}
		if (e.getManageCode() != null) {
			this.manageCode = e.getManageCode().getName();
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getRegistrationCode() {
		
		return registrationCode;
	}
	
	public void setRegistrationCode(String registrationCode) {
		
		this.registrationCode = registrationCode;
	}
	
	public String getManageCode() {
		
		return manageCode;
	}
	
	public void setManageCode(String manageCode) {
		
		this.manageCode = manageCode;
	}
	
	public String getRegistrationDate() {
		
		return registrationDate;
	}
	
	public void setRegistrationDate(String registrationDate) {
		
		this.registrationDate = registrationDate;
	}

	
	public String getRegistrationNO() {
		
		return registrationNO;
	}

	
	public void setRegistrationNO(String registrationNO) {
		
		this.registrationNO = registrationNO;
	}
	
}
