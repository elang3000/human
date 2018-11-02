package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: InstBaseExpertsOrScholars
 * @Description: 国标 来（回）华定居工作的专家、学者
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseWithdrawalSoldier<T> extends GenericEntity {

	private static final long serialVersionUID = 1938654500487974545L;
	
	/**
	 * @fieldName: withdrawalLabel
	 * @fieldType: java.lang.String
	 * @Description: 该人退出现役军人（武警）标识
	 */
	@Column(name = "A26001", length = 120)
	private String withdrawalLabel;
	
	/**
	 * @fieldName: enlistingDate
	 * @fieldType: java.util.Date
	 * @Description: 入伍日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A26004")
	@Temporal(TemporalType.DATE)
	private Date enlistingDate;
	
	
	/**
	 * @fieldName: enlistingPlace
	 * @fieldType: java.lang.String
	 * @Description: 入伍地
	 */
	@Column(name = "A26007", length = 120)
	private String enlistingPlace;
	
	
	/**
	 * @fieldName: approvedDate
	 * @fieldType: java.util.Date
	 * @Description: 批准退出现役日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A26011")
	@Temporal(TemporalType.DATE)
	private Date approvedDate;
	
	
	/**
	 * @fieldName: approvedDate
	 * @fieldType: jjava.lang.String
	 * @Description: 批准退出现役日期
	 */
	@Column(name = "A26014A", length = 120)
	private String approvedUnitName;
	
	/**
	 * @fieldName: approvedUnitCode
	 * @fieldType: java.lang.String
	 * @Description: 批准退出现役日期
	 */
	@Column(name = "A26014B", length = 120)
	private String approvedUnitCode;
	
	
	/**
	 * @fieldName: approvedDate
	 * @fieldType: java.util.Date
	 * @Description: 退出现役年份
	 */
	@DateTimeFormat(pattern = "yyyy")
	@Column(name = "A26016")
	@Temporal(TemporalType.DATE)
	private Date withdrawalYear;
	
	
	/**
	 * @fieldName: withdrawalUnitName
	 * @fieldType: jjava.lang.String
	 * @Description:退出现役时工作单位名称
	 */
	@Column(name = "A26017A", length = 120)
	private String withdrawalUnitName;
	
	/**
	 * @fieldName: withdrawalUnitCode
	 * @fieldType: java.lang.String
	 * @Description:退出现役时工作单位代码
	 */
	@Column(name = "A26017B", length = 120)
	private String withdrawalUnitCode;
	
	/**
	 * @fieldName: withdrawalAdministrative
	 * @fieldType: java.lang.String
	 * @Description:退出现役时工作单位所在行政区划
	 */
	@Column(name = "A26021", length = 120)
	private String withdrawalAdministrative;
	
	/**
	 * @fieldName: withdrawalPost
	 * @fieldType: java.lang.String
	 * @Description:退出现役时职务
	 */
	@Column(name = "A26025", length = 120)
	private String withdrawalPost;
	
	/**
	 * @fieldName: withdrawalMilitaryRank
	 * @fieldType: java.lang.String
	 * @Description:退出现役时军衔
	 */
	@Column(name = "A26027", length = 120)
	private String withdrawalMilitaryRank;
	
	/**
	 * @fieldName: withdrawalRank
	 * @fieldType: java.lang.String
	 * @Description:退出现役时职级
	 */
	@Column(name = "A26031", length = 120)
	private String withdrawalRank;
	
	/**
	 * @fieldName: specializedTechnical
	 * @fieldType: java.lang.String
	 * @Description:退出现役时专业技术职务
	 */
	@Column(name = "A26034", length = 120)
	private String specializedTechnical;
	
	/**
	 * @fieldName: salaryCategory
	 * @fieldType: java.lang.String
	 * @Description:退出现役时工资类别档次
	 */
	@Column(name = "A26037", length = 120)
	private String salaryCategory;
	
	/**
	 * @fieldName: preliminaryLabel
	 * @fieldType: java.lang.String
	 * @Description:是否服预备役标识
	 */
	@Column(name = "A26041", length = 120)
	private String preliminaryLabel;

	public String getWithdrawalLabel() {
		return withdrawalLabel;
	}

	public void setWithdrawalLabel(String withdrawalLabel) {
		this.withdrawalLabel = withdrawalLabel;
	}

	public Date getEnlistingDate() {
		return enlistingDate;
	}

	public void setEnlistingDate(Date enlistingDate) {
		this.enlistingDate = enlistingDate;
	}

	public String getEnlistingPlace() {
		return enlistingPlace;
	}

	public void setEnlistingPlace(String enlistingPlace) {
		this.enlistingPlace = enlistingPlace;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedUnitName() {
		return approvedUnitName;
	}

	public void setApprovedUnitName(String approvedUnitName) {
		this.approvedUnitName = approvedUnitName;
	}

	public String getApprovedUnitCode() {
		return approvedUnitCode;
	}

	public void setApprovedUnitCode(String approvedUnitCode) {
		this.approvedUnitCode = approvedUnitCode;
	}

	public Date getWithdrawalYear() {
		return withdrawalYear;
	}

	public void setWithdrawalYear(Date withdrawalYear) {
		this.withdrawalYear = withdrawalYear;
	}

	public String getWithdrawalUnitName() {
		return withdrawalUnitName;
	}

	public void setWithdrawalUnitName(String withdrawalUnitName) {
		this.withdrawalUnitName = withdrawalUnitName;
	}

	public String getWithdrawalUnitCode() {
		return withdrawalUnitCode;
	}

	public void setWithdrawalUnitCode(String withdrawalUnitCode) {
		this.withdrawalUnitCode = withdrawalUnitCode;
	}

	public String getWithdrawalAdministrative() {
		return withdrawalAdministrative;
	}

	public void setWithdrawalAdministrative(String withdrawalAdministrative) {
		this.withdrawalAdministrative = withdrawalAdministrative;
	}

	public String getWithdrawalPost() {
		return withdrawalPost;
	}

	public void setWithdrawalPost(String withdrawalPost) {
		this.withdrawalPost = withdrawalPost;
	}

	public String getWithdrawalMilitaryRank() {
		return withdrawalMilitaryRank;
	}

	public void setWithdrawalMilitaryRank(String withdrawalMilitaryRank) {
		this.withdrawalMilitaryRank = withdrawalMilitaryRank;
	}

	public String getWithdrawalRank() {
		return withdrawalRank;
	}

	public void setWithdrawalRank(String withdrawalRank) {
		this.withdrawalRank = withdrawalRank;
	}

	public String getSpecializedTechnical() {
		return specializedTechnical;
	}

	public void setSpecializedTechnical(String specializedTechnical) {
		this.specializedTechnical = specializedTechnical;
	}

	public String getSalaryCategory() {
		return salaryCategory;
	}

	public void setSalaryCategory(String salaryCategory) {
		this.salaryCategory = salaryCategory;
	}

	public String getPreliminaryLabel() {
		return preliminaryLabel;
	}

	public void setPreliminaryLabel(String preliminaryLabel) {
		this.preliminaryLabel = preliminaryLabel;
	}
	
	

}
