package com.wondersgroup.human.bo.pubinst.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: InstBaseAddressCommunication
 * @Description: 国标  地址与通信
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseAddressCommunication<T> extends GenericEntity {

	private static final long serialVersionUID = 1938654500487974545L;
	
	
	/**
	 * @fieldName: workCommunicationAddress
	 * @fieldType: java.lang.String
	 * @Description: 工作单位通信地址
	 */
	@Column(name = "A37001", length = 120)
	private String unitCommunicationAddress;
	
	
	/**
	 * @fieldName: unitPostalCode
	 * @fieldType: java.lang.String
	 * @Description: 工作单位邮政编码
	 */
	@Column(name = "A37004", length = 120)
    private String unitPostalCode;

	
	/**
	 * @fieldName: workTel
	 * @fieldType: java.lang.String
	 * @Description: 办公电话
	 */
	@Column(name = "A37005", length = 50)
    private String workTel;
	
	/**
	 * @fieldName: familyTel
	 * @fieldType: java.lang.String
	 * @Description: 住宅电话
	 */
	@Column(name = "A37006", length = 50)
    private String familyTel;
	
	
	/**
	 * @fieldName: mobilePhone
	 * @fieldType: java.lang.String
	 * @Description: 移动电话
	 */
	@Column(name = "A37007", length = 50)
    private String mobilePhone;
	
	/**
	 * @fieldName: email
	 * @fieldType: java.lang.String
	 * @Description: 电子邮箱
	 */
	@Column(name = "A37008", length = 50)
    private String email;
	

	/**
	 * @fieldName: secretaryPhone
	 * @fieldType: java.lang.String
	 * @Description: 秘书电话
	 */
	@Column(name = "A37009", length = 50)
	private String secretaryPhone;
	
	/**
	 * @fieldName: familyAddress
	 * @fieldType: java.lang.String
	 * @Description: 家庭地址
	 */
	@Column(name = "A37011", length = 50)
	private String familyAddress;
	
	
	/**
	 * @fieldName: postalCode
	 * @fieldType: java.lang.String
	 * @Description: 住址邮政编码
	 */
	@Column(name = "A37014", length = 50)
	private String postalCode;
	
	/**
	 * @fieldName: linkPhone
	 * @fieldType: java.lang.String
	 * @Description: 联系电话
	 */
	@Column(name = "A37017", length = 50)
	private String linkPhone;

	public String getUnitCommunicationAddress() {
		return unitCommunicationAddress;
	}

	public void setUnitCommunicationAddress(String unitCommunicationAddress) {
		this.unitCommunicationAddress = unitCommunicationAddress;
	}

	public String getUnitPostalCode() {
		return unitPostalCode;
	}

	public void setUnitPostalCode(String unitPostalCode) {
		this.unitPostalCode = unitPostalCode;
	}

	public String getWorkTel() {
		return workTel;
	}

	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}

	public String getFamilyTel() {
		return familyTel;
	}

	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
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

	public String getSecretaryPhone() {
		return secretaryPhone;
	}

	public void setSecretaryPhone(String secretaryPhone) {
		this.secretaryPhone = secretaryPhone;
	}

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	
}
