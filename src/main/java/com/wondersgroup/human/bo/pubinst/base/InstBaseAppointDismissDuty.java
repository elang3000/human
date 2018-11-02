package com.wondersgroup.human.bo.pubinst.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: AppointDismissDuty
 * @Description: 拟任拟免职务
 * @author: LYF
 * @date: 2018年9月6日 下午6:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */

@MappedSuperclass
public class InstBaseAppointDismissDuty<T> extends GenericEntity{
	
	
	
	
	private static final long serialVersionUID = -853352967567941895L;


	/**
	 * @fieldName: appointDismissOrganizationName
	 * @fieldType: java.lang.String
	 * @Description: 拟任免机构名称.与拟任免职务相对应的工作机构及工作机构部门名称。
	 */
	@Column(name = "A53001A", length = 120)
	private String appointDismissOrganizationName;

	
	/**
	 * @fieldName: appointDismissOrganizationName
	 * @fieldType: java.lang.String
	 * @Description: 拟任免机构代码.GB 32100-2015.与拟任免职务相对应的工作机构及工作机构部门代码。
	 */
	@Column(name = "A53001B", length = 120)
	private String appointDismissOrganizationCode;
	
	
	/**
	 * @fieldName: appointDismissDutyName
	 * @fieldType: java.lang.String
	 * @Description: 拟任免职务名称.该人拟任或拟免的职务名称。
	 */
	@Column(name = "A53005A", length = 120)
	private String appointDismissDutyName;
	
	
	/**
	 * @fieldName: appointDismissDutyName
	 * @fieldType: java.lang.String
	 * @Description: 拟任免职务代码.GB/T 12403-1990.该人拟任或拟免的职务的代码。
	 */
	@Column(name = "A53005B", length = 120)
	private String appointDismissDutyCode;


	public String getAppointDismissOrganizationName() {
		return appointDismissOrganizationName;
	}


	public void setAppointDismissOrganizationName(String appointDismissOrganizationName) {
		this.appointDismissOrganizationName = appointDismissOrganizationName;
	}


	public String getAppointDismissOrganizationCode() {
		return appointDismissOrganizationCode;
	}


	public void setAppointDismissOrganizationCode(String appointDismissOrganizationCode) {
		this.appointDismissOrganizationCode = appointDismissOrganizationCode;
	}


	public String getAppointDismissDutyName() {
		return appointDismissDutyName;
	}


	public void setAppointDismissDutyName(String appointDismissDutyName) {
		this.appointDismissDutyName = appointDismissDutyName;
	}


	public String getAppointDismissDutyCode() {
		return appointDismissDutyCode;
	}


	public void setAppointDismissDutyCode(String appointDismissDutyCode) {
		this.appointDismissDutyCode = appointDismissDutyCode;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
