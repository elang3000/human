/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseCompetence.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 专业技术任职资格情况
 * 创建人: jiang
 * 创建时间: 2018年6月12日15:39:51
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日15:39:55
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.pubinst.base;

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
 * @ClassName: BaseCompetence
 * @Description: 国标 专业技术任职资格情况
 * @author: jiang
 * @date: 2018年6月11日14:55:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseCompetence<T> extends GenericEntity {
	

	
	private static final long serialVersionUID = -6460355372990294331L;

	/**
	 * @fieldName: 专业技术任职资格名称
	 * @fieldType: java.lang.String
	 * @Description: 职称的中文名称。
	 */
	@Column(name = "A06001A", length = 500)
	private String name;
	
	/**
	 * @fieldName: 专业技术任职资格代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人由专业技术职务任职资格评审委员会评审并正式批准或参加国家统一专业技术职务资格考试合格而取得的专业技术职务资格名称。GB/T 8561-2001
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A06001B")
	private CodeInfo code;
	
	/**
	 * @fieldName: 专业技术任职资格级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职称的级别标识。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A06002")
	private CodeInfo lvl;
	
	/**
	 * @fieldName: 获得专业技术任职资格日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得专业技术职务任职资格的正式批准日期，以资格证书为准。GB/T 7408-2005
	 */
	@Column(name = "A06004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date gainDate;
	
	/**
	 * @fieldName: 获得专业技术任职资格途径
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人取得专业技术职务任职资格的途径。DM010
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A06007")
	private CodeInfo gainWay;
	
	/**
	 * @fieldName: 专业技术任职资格评委会或考试名称
	 * @fieldType: java.lang.String
	 * @Description: 该人取得该专业技术职务任职资格通过的评委会或考试的全称。
	 */
	@Column(name = "A06011", length = 500)
	private String jury;
	
	/**
	 * @fieldName: 专业技术任职资格审批单位
	 * @fieldType: java.lang.String
	 * @Description: 专业技术人员取得专业技术资格的审批单位名称。
	 */
	@Column(name = "A06013", length = 120)
	private String approvalUnit;
	
	
	/**
	 * @fieldName: 聘任专业技术职务
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 正式聘任的专业技术职务代码。。GB/T 8561-2001
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A06020")
	private CodeInfo appointmentCompetence;
	
	/**
	 * @fieldName: 聘任专业技术职务起始日期
	 * @fieldType: java.util.Date
	 * @Description: 专业技术职务聘任通知和聘书所认定的聘任开始日期。GB/T 7408-2005
	 */
	@Column(name = "A06025")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appointmentStartDate;
	
	/**
	 * @fieldName: 聘任专业技术职务终止日期
	 * @fieldType: java.util.Date
	 * @Description: 专业技术职务聘任通知和聘书所认定的聘任结束日期。GB/T 7408-2005
	 */
	@Column(name = "A06030")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appointmentEndDate;
	
	/**
	 * @fieldName: 聘任专业技术职务单位
	 * @fieldType: java.lang.String
	 * @Description: 聘任专业技术职务的具体单位名称。
	 */
	@Column(name = "A06035", length = 48)
	private String appointmentUnit;
	
	/**
	 * @fieldName: 聘任专业技术职务情况
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否聘任的聘任情况。DM011
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A06040")
	private CodeInfo appointmentstatus;
	
	/**
	 * @fieldName: 专业技术专长
	 * @fieldType: java.lang.String
	 * @Description: 专业技术人员在从事本专业内的特有专长。
	 */
	@Column(name = "A06045", length = 48)
	private String speciality;
	
	/**
	 * @fieldName: 专业技术职务任职变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: DM081
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A06051")
	private CodeInfo serveType;
	
	/**
	 * @fieldName: 专业技术职务免职变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: DM081
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A06053")
	private CodeInfo dismissal;

	
	public String getName() {
		
		return name;
	}

	
	public void setName(String name) {
		
		this.name = name;
	}

	
	public CodeInfo getCode() {
		
		return code;
	}

	
	public void setCode(CodeInfo code) {
		
		this.code = code;
	}

	
	public CodeInfo getLvl() {
		
		return lvl;
	}

	
	public void setLvl(CodeInfo lvl) {
		
		this.lvl = lvl;
	}

	
	public Date getGainDate() {
		
		return gainDate;
	}

	
	public void setGainDate(Date gainDate) {
		
		this.gainDate = gainDate;
	}

	
	public CodeInfo getGainWay() {
		
		return gainWay;
	}

	
	public void setGainWay(CodeInfo gainWay) {
		
		this.gainWay = gainWay;
	}

	
	public String getJury() {
		
		return jury;
	}

	
	public void setJury(String jury) {
		
		this.jury = jury;
	}

	
	public String getApprovalUnit() {
		
		return approvalUnit;
	}

	
	public void setApprovalUnit(String approvalUnit) {
		
		this.approvalUnit = approvalUnit;
	}

	
	public CodeInfo getAppointmentCompetence() {
		
		return appointmentCompetence;
	}

	
	public void setAppointmentCompetence(CodeInfo appointmentCompetence) {
		
		this.appointmentCompetence = appointmentCompetence;
	}

	
	public Date getAppointmentStartDate() {
		
		return appointmentStartDate;
	}

	
	public void setAppointmentStartDate(Date appointmentStartDate) {
		
		this.appointmentStartDate = appointmentStartDate;
	}

	
	public Date getAppointmentEndDate() {
		
		return appointmentEndDate;
	}

	
	public void setAppointmentEndDate(Date appointmentEndDate) {
		
		this.appointmentEndDate = appointmentEndDate;
	}

	
	public String getAppointmentUnit() {
		
		return appointmentUnit;
	}

	
	public void setAppointmentUnit(String appointmentUnit) {
		
		this.appointmentUnit = appointmentUnit;
	}

	
	public CodeInfo getAppointmentstatus() {
		
		return appointmentstatus;
	}

	
	public void setAppointmentstatus(CodeInfo appointmentstatus) {
		
		this.appointmentstatus = appointmentstatus;
	}

	
	public String getSpeciality() {
		
		return speciality;
	}

	
	public void setSpeciality(String speciality) {
		
		this.speciality = speciality;
	}

	
	public CodeInfo getServeType() {
		
		return serveType;
	}

	
	public void setServeType(CodeInfo serveType) {
		
		this.serveType = serveType;
	}

	
	public CodeInfo getDismissal() {
		
		return dismissal;
	}

	
	public void setDismissal(CodeInfo dismissal) {
		
		this.dismissal = dismissal;
	}

}
