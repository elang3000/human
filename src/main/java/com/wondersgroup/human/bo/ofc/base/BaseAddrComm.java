/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseAddrComm.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 地址与通信
 * 创建人: jiang
 * 创建时间: 2018年9月10日10:22:40
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日10:22:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: BaseAddrComm
 * @Description: 国标 地址与通信
 * @author: jiang
 * @date: 2018年9月10日10:22:46
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseAddrComm<T> extends GenericEntity {
	
	private static final long serialVersionUID = -1206080898916717221L;
	
	/**
	 * @fieldName: 工作单位通信地址
	 * @fieldType: java.lang.String
	 * @Description: 该人现工作单位所在的通信地址。
	 */
	@Column(name = "A37001", length = 80)
	private String unitCommAddr;
	
	/**
	 * @fieldName: 工作单位邮政编码
	 * @fieldType: java.lang.String
	 * @Description: 该人现工作单位所在地的邮政编码。
	 */
	@Column(name = "A37004", length = 6)
	private String unitPostalCode;
	
	/**
	 * @fieldName: 办公电话
	 * @fieldType: java.lang.String
	 * @Description: 该人现办公地点的电话号码。
	 */
	@Column(name = "A37005", length = 20)
	private String officeTel;
	
	/**
	 * @fieldName: 住宅电话
	 * @fieldType: java.lang.String
	 * @Description: 该人现居住地点的电话号码。
	 */
	@Column(name = "A37006", length = 20)
	private String residentialTel;
	
	/**
	 * @fieldName: 移动电话
	 * @fieldType: java.lang.String
	 * @Description: 该人使用移动通讯的电话号码。
	 */
	@Column(name = "A37007", length = 20)
	private String mobilePhone;
	
	/**
	 * @fieldName: 电子邮箱
	 * @fieldType: java.lang.String
	 * @Description: 该人的电子邮箱地址。
	 */
	@Column(name = "A37008", length = 100)
	private String email;
	
	/**
	 * @fieldName: 秘书电话
	 * @fieldType: java.lang.String
	 * @Description: 该人秘书的联系电话。
	 */
	@Column(name = "A37009", length = 20)
	private String secretarialTel;
	
	/**
	 * @fieldName: 家庭住址
	 * @fieldType: java.lang.String
	 * @Description: 该人家庭住宅所在的具体地址。
	 */
	@Column(name = "A37011", length = 120)
	private String homeAddr;
	
	/**
	 * @fieldName: 住址邮政编码
	 * @fieldType: java.lang.String
	 * @Description: 该人住宅所在地的邮政编码。
	 */
	@Column(name = "A37014", length = 6)
	private String homePostalCode;
	
	/**
	 * @fieldName: 联系电话
	 * @fieldType: java.lang.String
	 * @Description: 不区分移动电话、固定电话等类型的电话号码，可包括多个。
	 */
	@Column(name = "A37017", length = 50)
	private String contactTel;
	
	public String getUnitCommAddr() {
		
		return unitCommAddr;
	}
	
	public void setUnitCommAddr(String unitCommAddr) {
		
		this.unitCommAddr = unitCommAddr;
	}
	
	public String getUnitPostalCode() {
		
		return unitPostalCode;
	}
	
	public void setUnitPostalCode(String unitPostalCode) {
		
		this.unitPostalCode = unitPostalCode;
	}
	
	public String getOfficeTel() {
		
		return officeTel;
	}
	
	public void setOfficeTel(String officeTel) {
		
		this.officeTel = officeTel;
	}
	
	public String getResidentialTel() {
		
		return residentialTel;
	}
	
	public void setResidentialTel(String residentialTel) {
		
		this.residentialTel = residentialTel;
	}
	
	public String getMobilePhone() {
		
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		
		this.mobilePhone = mobilePhone;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public String getSecretarialTel() {
		
		return secretarialTel;
	}
	
	public void setSecretarialTel(String secretarialTel) {
		
		this.secretarialTel = secretarialTel;
	}
	
	public String getHomeAddr() {
		
		return homeAddr;
	}
	
	public void setHomeAddr(String homeAddr) {
		
		this.homeAddr = homeAddr;
	}
	
	public String getHomePostalCode() {
		
		return homePostalCode;
	}
	
	public void setHomePostalCode(String homePostalCode) {
		
		this.homePostalCode = homePostalCode;
	}
	
	public String getContactTel() {
		
		return contactTel;
	}
	
	public void setContactTel(String contactTel) {
		
		this.contactTel = contactTel;
	}
	
}
