package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: OrganizationInspect
 * @Description: 组织考察情况
 * @author: LYF
 * @date: 2018年9月6日 下午17:39:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseOrganizationInspect<T>extends GenericEntity {

	
	private static final long serialVersionUID = 3100796853903724103L;
	
	
	
	
	/**
	 * @fieldName: inspectClass
	 * @fieldType: java.lang.String
	 * @Description:考察类别. 考察（考核）的不同类别。
	 */
	@Column(name = "A47001", length = 120)
	private String inspectClass;
	
	
	/**
	 * @fieldName: inspectDate
	 * @fieldType: java.util.Date
	 * @Description:考察日期.由组织、干部、人事部门组派的考察组对干部进行考察（考核）的起始日期。GB/T 7408-2005
	 */
	@Column(name = "A47004")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inspectDate;
	
	
	/**
	 * @fieldName: inspectOrganizationName
	 * @fieldType: java.lang.String
	 * @Description:考察组织名称.
	 */
	@Column(name = "A47007", length = 120)
	private String inspectOrganizationName;
	
	/**
	 * @fieldName: inspectIdea
	 * @fieldType: java.lang.String
	 * @Description:考察意见.由组织、干部、人事部门组派的考察组对被考察人的德、才、勤、绩诸方面的评价意。
	 */
	@Column(name = "A47011", length = 120)
	private String inspectIdea;
	
	/**
	 * @fieldName: inspecterName
	 * @fieldType: java.lang.String
	 * @Description:考察人姓名.直接参与对该人考察的组织成员的姓名全称。
	 */
	@Column(name = "A47014", length = 120)
	private String inspecterName;
	
	/**
	 * @fieldName: inspectWay
	 * @fieldType: java.lang.String
	 * @Description:考察方式标识.组织对该人考察的各种方法的标识
	 */
	@Column(name = "A47017", length = 120)
	private String inspectWay;
	
	
	
	

	public String getInspectClass() {
		return inspectClass;
	}

	public void setInspectClass(String inspectClass) {
		this.inspectClass = inspectClass;
	}

	public Date getInspectDate() {
		return inspectDate;
	}

	public void setInspectDate(Date inspectDate) {
		this.inspectDate = inspectDate;
	}

	public String getInspectOrganizationName() {
		return inspectOrganizationName;
	}

	public void setInspectOrganizationName(String inspectOrganizationName) {
		this.inspectOrganizationName = inspectOrganizationName;
	}

	public String getInspectIdea() {
		return inspectIdea;
	}

	public void setInspectIdea(String inspectIdea) {
		this.inspectIdea = inspectIdea;
	}

	public String getInspecterName() {
		return inspecterName;
	}

	public void setInspecterName(String inspecterName) {
		this.inspecterName = inspecterName;
	}

	public String getInspectWay() {
		return inspectWay;
	}

	public void setInspectWay(String inspectWay) {
		this.inspectWay = inspectWay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
}
