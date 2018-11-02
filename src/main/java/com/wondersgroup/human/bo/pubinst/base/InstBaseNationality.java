package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: InstBaseNationality
 * @Description: 国标  国籍
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseNationality<T> extends GenericEntity {

	
	private static final long serialVersionUID = 1L;

	/**
	 * @fieldName: getNationalityName
	 * @fieldType: java.lang.String
	 * @Description: 取得国籍名称
	 */
	@Column(name = "A35001", length = 120)
	private String getNationalityName;
	
	
	/**
	 * @fieldName: getNationalityDate
	 * @fieldType: java.util.Date
	 * @Description: 取得该国籍的起始日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A35004")
	@Temporal(TemporalType.DATE)
	private Date getNationalityDate;
	
	/**
	 * @fieldName: cancelNationalityDate
	 * @fieldType: java.util.Date
	 * @Description: 取消该国籍的日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A35007")
	@Temporal(TemporalType.DATE)
	private Date cancelNationalityDate;
	
	
	/**
	 * @fieldName: nationalityRemark
	 * @fieldType: java.lang.String
	 * @Description: 过期备注
	 */
	@Column(name = "A35011", length = 120)
	private String nationalityRemark;


	public String getGetNationalityName() {
		return getNationalityName;
	}


	public void setGetNationalityName(String getNationalityName) {
		this.getNationalityName = getNationalityName;
	}


	public Date getGetNationalityDate() {
		return getNationalityDate;
	}


	public void setGetNationalityDate(Date getNationalityDate) {
		this.getNationalityDate = getNationalityDate;
	}


	public Date getCancelNationalityDate() {
		return cancelNationalityDate;
	}


	public void setCancelNationalityDate(Date cancelNationalityDate) {
		this.cancelNationalityDate = cancelNationalityDate;
	}


	public String getNationalityRemark() {
		return nationalityRemark;
	}


	public void setNationalityRemark(String nationalityRemark) {
		this.nationalityRemark = nationalityRemark;
	}
	
	
	

}
