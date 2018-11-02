package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: ResignDismiss
 * @Description: 辞职辞退情况
 * @author: LYF
 * @date: 2018年9月6日 下午6:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseResignDismiss<T> extends GenericEntity{

	
	private static final long serialVersionUID = 3615282456493727103L;

	/**
	 * @fieldName: resignDuty
	 * @fieldType: java.lang.String
	 * @Description:辞去的职务. 该人该次辞去或被辞的职务名称。
	 */
	@Column(name = "A51001", length = 120)
	private String resignDuty;
	
	/**
	 * @fieldName: resignReason
	 * @fieldType: java.lang.String
	 * @Description:辞职原因
	 */
	@Column(name = "A51004", length = 120)
	private String resignReason;
	
	/**
	 * @fieldName: resignTo
	 * @fieldType: java.lang.String
	 * @Description:辞职去向
	 */
	@Column(name = "A51005", length = 120)
	private String resignTo;
	
	/**
	 * @fieldName: dismissReason
	 * @fieldType: java.lang.String
	 * @Description:辞退原因
	 */
	@Column(name = "A51007", length = 120)
	private String dismissReason;
	
	
	/**
	 * @fieldName: dismissReason
	 * @fieldType: java.lang.String
	 * @Description:辞去公职标识.该人该次辞职是否同时辞去公职的标识。
	 */
	@Column(name = "A51011", length = 120)
	private String resignPublicOffice;
	
	/**
	 * @fieldName: resignRatifyDate
	 * @fieldType: java.util.Date
	 * @Description: 辞职(辞退)批准日期.
	 */
	@Column(name = "A51014")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date resignRatifyDate;
	
	/**
	 * @fieldName: resignResponsibility
	 * @fieldType: java.lang.String
	 * @Description:引咎辞职时应负的责任.该人该次引咎（责令）辞职时应负的领导责任的标识。
	 */
	@Column(name = "A51017", length = 120)
	private String resignResponsibility;
	
	/**
	 * @fieldName: resignEconomy
	 * @fieldType: java.lang.String
	 * @Description:辞职,辞退经济责任审计标识.该人该次辞职或者被辞退公职、离职前，是否进行了经济责任审计的标识。
	 */
	@Column(name = "A51021", length = 120)
	private String resignEconomy;

	
	
	
	
	public String getResignDuty() {
		return resignDuty;
	}

	public void setResignDuty(String resignDuty) {
		this.resignDuty = resignDuty;
	}

	public String getResignReason() {
		return resignReason;
	}

	public void setResignReason(String resignReason) {
		this.resignReason = resignReason;
	}

	public String getResignTo() {
		return resignTo;
	}

	public void setResignTo(String resignTo) {
		this.resignTo = resignTo;
	}

	public String getDismissReason() {
		return dismissReason;
	}

	public void setDismissReason(String dismissReason) {
		this.dismissReason = dismissReason;
	}

	public String getResignPublicOffice() {
		return resignPublicOffice;
	}

	public void setResignPublicOffice(String resignPublicOffice) {
		this.resignPublicOffice = resignPublicOffice;
	}

	public Date getResignRatifyDate() {
		return resignRatifyDate;
	}

	public void setResignRatifyDate(Date resignRatifyDate) {
		this.resignRatifyDate = resignRatifyDate;
	}

	public String getResignResponsibility() {
		return resignResponsibility;
	}

	public void setResignResponsibility(String resignResponsibility) {
		this.resignResponsibility = resignResponsibility;
	}

	public String getResignEconomy() {
		return resignEconomy;
	}

	public void setResignEconomy(String resignEconomy) {
		this.resignEconomy = resignEconomy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
}
