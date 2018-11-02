/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Servant.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述: 上海 人员基本信息
 * 创建人: tzy
 * 创建时间: 2018年5月18日 下午2:05:31
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月18日 下午2:05:31
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.company;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.base.BaseServant;

/**
 * @ClassName: NationalCompany
 * @Description:国企人员信息基表
 * @author: bianyf
 * @date: 2018年8月20日 下午3:34:26
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "D01")
public class NationalCompany extends BaseServant<NationalCompany> {
	
	private static final long serialVersionUID = -3546598474369158295L;
	
	/**
	 * @fieldName: reportDate
	 * @fieldType: java.util.Date
	 * @Description: 上报时间（非上海字段）
	 */
	@Column(name = "SH_REPORT_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date reportDate;
	
	/**
	 * @fieldName: departId
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位id
	 */
	@Column(name = "DEPARTID", length = 64)
	private String departId;
	
	/**
	 * @fieldName: growPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 成长地 。
	 */
	@Column(name = "SH_A0115A", length = 120)
	private String growPlaceName;
	
	/**
	 * @fieldName: growPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 成长地 。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A0115B")
	private CodeInfo growPlace;
	
	/**
	 * @fieldName: politics
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 政治面貌 ,该人当前的政治面貌代码。GBT_4762_1984 政治面貌代码
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A2205")
	private CodeInfo politics;
	
	/**
	 * @fieldName: joinParty
	 * @fieldType: java.util.Date
	 * @Description: 入党时间，该人参加中国共产党的日期。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SH_JOIN_PARTY")
	@Temporal(TemporalType.DATE)
	private Date joinParty;
	
	/**
	 * @fieldName: secondParty
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 第二党派 ,该人当前参加的第二党派名称代码。是中共党员者，中共列入第一党派。GB 4762-84 政治面貌代码
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_SECOND_PARTY")
	private CodeInfo secondParty;
	
	/**
	 * @fieldName: thirdParty
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 第三党派 ,该人当前参加的第三党派名称代码。是中共党员者，中共列入第一党派。GB 4762-84 政治面貌代码
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_THIRD_PARTY")
	private CodeInfo thirdParty;
	
	/**
	 * @fieldName: compilingType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 编制类型,该人使用编制的类型。编制类型代码
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_S0162")
	private CodeInfo compilingType;
	
	/**
	 * @fieldName: expertisePost
	 * @fieldType: java.lang.String
	 * @Description: 专业技术职务 ,由主管部门评定的中级以上专业技术职务。
	 */
	@Column(name = "SH_EXPERTOSE_POST", length = 180)
	private String expertisePost;
	
	/**
	 * @fieldName: qualifications
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 专业技术类公务员任职资格 ,对专业技术类公务员专业技术能力和水平的认定。ZB139—2016/ZYJSRZZG《专业技术类公务员任职资格代码》
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A0122")
	private CodeInfo qualifications;
	
	/**
	 * @fieldName: photoPath
	 * @fieldType: java.lang.String
	 * @Description: 照片路径
	 */
	@Column(name = "SH_photoPath", length = 200)
	private String photoPath;
	
	/**
	 * @fieldName: officePhone
	 * @fieldType: java.lang.String
	 * @Description: 办公电话，该人现办公地点的电话号码。
	 */
	@Column(name = "SH_A37005", length = 20)
	private String officePhone;
	
	/**
	 * @fieldName: homePhone
	 * @fieldType: java.lang.String
	 * @Description: 住宅电话，该人现居住地点的电话号码。
	 */
	@Column(name = "SH_A0138", length = 32)
	private String homePhone;
	
	/**
	 * @fieldName: mobilePhone
	 * @fieldType: java.lang.String
	 * @Description: 移动电话，该人使用移动通讯的电话号码。
	 */
	@Column(name = "SH_A0148", length = 64)
	private String mobilePhone;
	
	/**
	 * @fieldName: email
	 * @fieldType: java.lang.String
	 * @Description: 电子邮箱，该人的电子邮箱地址。
	 */
	@Column(name = "SH_A37008", length = 60)
	private String email;
	
	/**
	 * @fieldName: secretaryPhone
	 * @fieldType: java.lang.String
	 * @Description: 秘书电话，该人秘书的联系电话。
	 */
	@Column(name = "SH_A37009", length = 20)
	private String secretaryPhone;
	
	/**
	 * @fieldName: homeAddress
	 * @fieldType: java.lang.String
	 * @Description: 家庭住址，该人家庭住宅所在的具体地址。
	 */
	@Column(name = "SH_A0137", length = 120)
	private String homeAddress;
	
	/**
	 * @fieldName: addressPost
	 * @fieldType: java.lang.String
	 * @Description: 住址邮政编码，该人住宅所在地的邮政编码。
	 */
	@Column(name = "SH_A37014", length = 6)
	private String addressPost;
	
	/**
	 * @fieldName: nowPostLevel
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 现职级（自动填写） ,该人目前所任的职级。ZB148——2016/ZJDM《职级代码》
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A0192E")
	private CodeInfo nowJobLevel;
	
	/**
	 * @fieldName: servantRegisterDate
	 * @fieldType: java.util.Date
	 * @Description: 公务员登记时间（自动填写），该人进行公务员登记的具体时间。
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SH_A2949")
	@Temporal(TemporalType.DATE)
	private Date servantRegisterDate;
	
	/**
	 * @fieldName: topEducation
	 * @fieldType: java.lang.String
	 * @Description: 最高学历（自动填写）,该人取得的最高学历
	 */
	@Column(name = "SH_ZGXL", length = 60)
	private String topEducation;
	
	/**
	 * @fieldName: topDegree
	 * @fieldType: java.lang.String
	 * @Description: 最高学位（自动填写）,该人取得的最高学位
	 */
	@Column(name = "SH_ZGXW", length = 60)
	private String topDegree;
	
	/**
	 * @fieldName: topAcademyDegree
	 * @fieldType: java.lang.String
	 * @Description: 最高学历，学校毕业时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SH_A0430")
	private Date graduateDate;
	
	/**
	 * @fieldName: doingSpecialty
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 从事专业 GB/T 16835-1997
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A0164")
	private CodeInfo doingSpecialty;
	
	/**
	 * @fieldName: nowPostName
	 * @fieldType: java.lang.String
	 * @Description: 职务名称 ,该人担任职务的具体名称。
	 */
	@Column(name = "SH_A0704", length = 120)
	private String nowPostName;
	
	/**
	 * @fieldName: nowPostCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职务代码,GB/T 12403-1990 该人担任职务的代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A0705")
	private CodeInfo nowPostCode;
	
	/**
	 * @fieldName: nowPostAttribute
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 现任职务属性,DM049 担任领导职务或非领导职务的情况。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A0702")
	private CodeInfo nowPostAttribute;
	
	/**
	 * @fieldName: salaryLevel
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 登记后所定级别 ,该人执行的级别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A5917")
	private CodeInfo registerLevel;
	
	@Transient
	private String graduateDateStr;
	
	public String getGrowPlaceName() {
		
		return growPlaceName;
	}
	
	public void setGrowPlaceName(String growPlaceName) {
		
		this.growPlaceName = growPlaceName;
	}
	
	public CodeInfo getGrowPlace() {
		
		return growPlace;
	}
	
	public void setGrowPlace(CodeInfo growPlace) {
		
		this.growPlace = growPlace;
	}
	
	public CodeInfo getPolitics() {
		
		return politics;
	}
	
	public void setPolitics(CodeInfo politics) {
		
		this.politics = politics;
	}
	
	public Date getJoinParty() {
		
		return joinParty;
	}
	
	public void setJoinParty(Date joinParty) {
		
		this.joinParty = joinParty;
	}
	
	public CodeInfo getSecondParty() {
		
		return secondParty;
	}
	
	public void setSecondParty(CodeInfo secondParty) {
		
		this.secondParty = secondParty;
	}
	
	public CodeInfo getThirdParty() {
		
		return thirdParty;
	}
	
	public void setThirdParty(CodeInfo thirdParty) {
		
		this.thirdParty = thirdParty;
	}
	
	public CodeInfo getRegisterLevel() {
		
		return registerLevel;
	}
	
	public void setRegisterLevel(CodeInfo registerLevel) {
		
		this.registerLevel = registerLevel;
	}
	
	public CodeInfo getCompilingType() {
		
		return compilingType;
	}
	
	public void setCompilingType(CodeInfo compilingType) {
		
		this.compilingType = compilingType;
	}
	
	public String getExpertisePost() {
		
		return expertisePost;
	}
	
	public void setExpertisePost(String expertisePost) {
		
		this.expertisePost = expertisePost;
	}
	
	public CodeInfo getQualifications() {
		
		return qualifications;
	}
	
	public void setQualifications(CodeInfo qualifications) {
		
		this.qualifications = qualifications;
	}
	
	public String getPhotoPath() {
		
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		
		this.photoPath = photoPath;
	}
	
	public String getOfficePhone() {
		
		return officePhone;
	}
	
	public void setOfficePhone(String officePhone) {
		
		this.officePhone = officePhone;
	}
	
	public String getHomePhone() {
		
		return homePhone;
	}
	
	public void setHomePhone(String homePhone) {
		
		this.homePhone = homePhone;
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
	
	public String getHomeAddress() {
		
		return homeAddress;
	}
	
	public void setHomeAddress(String homeAddress) {
		
		this.homeAddress = homeAddress;
	}
	
	public String getAddressPost() {
		
		return addressPost;
	}
	
	public void setAddressPost(String addressPost) {
		
		this.addressPost = addressPost;
	}
	
	public CodeInfo getNowJobLevel() {
		
		return nowJobLevel;
	}
	
	public void setNowJobLevel(CodeInfo nowJobLevel) {
		
		this.nowJobLevel = nowJobLevel;
	}
	
	public Date getServantRegisterDate() {
		
		return servantRegisterDate;
	}
	
	public void setServantRegisterDate(Date servantRegisterDate) {
		
		this.servantRegisterDate = servantRegisterDate;
	}
	
	public String getTopEducation() {
		
		return topEducation;
	}
	
	public void setTopEducation(String topEducation) {
		
		this.topEducation = topEducation;
	}
	
	public String getTopDegree() {
		
		return topDegree;
	}
	
	public void setTopDegree(String topDegree) {
		
		this.topDegree = topDegree;
	}
	
	public Date getGraduateDate() {
		
		return graduateDate;
	}
	
	public void setGraduateDate(Date graduateDate) {
		
		this.graduateDate = graduateDate;
	}
	
	public CodeInfo getDoingSpecialty() {
		
		return doingSpecialty;
	}
	
	public void setDoingSpecialty(CodeInfo doingSpecialty) {
		
		this.doingSpecialty = doingSpecialty;
	}
	
	public String getNowPostName() {
		
		return nowPostName;
	}
	
	public void setNowPostName(String nowPostName) {
		
		this.nowPostName = nowPostName;
	}
	
	public CodeInfo getNowPostCode() {
		
		return nowPostCode;
	}
	
	public void setNowPostCode(CodeInfo nowPostCode) {
		
		this.nowPostCode = nowPostCode;
	}
	
	public CodeInfo getNowPostAttribute() {
		
		return nowPostAttribute;
	}
	
	public void setNowPostAttribute(CodeInfo nowPostAttribute) {
		
		this.nowPostAttribute = nowPostAttribute;
	}
	
	public String getGraduateDateStr() {
		
		if (this.graduateDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			graduateDateStr = sdf.format(this.graduateDate);
		}
		return graduateDateStr;
	}
	
	public void setGraduateDateStr(String graduateDateStr) {
		
		this.graduateDateStr = graduateDateStr;
	}
	
	public Date getReportDate() {
		
		return reportDate;
	}
	
	public void setReportDate(Date reportDate) {
		
		this.reportDate = reportDate;
	}
	
	public String getDepartId() {
		
		return departId;
	}
	
	public void setDepartId(String departId) {
		
		this.departId = departId;
	}
	
}
