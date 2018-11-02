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
 * @ClassName: InstBaseFamilySocialRelation
 * @Description: 国标 家庭成员及社会关系
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseFamilySocialRelation<T> extends GenericEntity {

	private static final long serialVersionUID = -8421472768826014967L;


	/**
	 * @fieldName: memberName
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人姓名
	 */
	@Column(name = "A36001", length = 50)
	private String memberName;
	
	/**
	 * @fieldName: memberRelationName
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人关系名称
	 */
	@Column(name = "A36005A", length = 50)
	private String memberRelationName;
	
	
	/**
	 * @fieldName: memberRelationCode
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人关系代码
	 */
	@Column(name = "A36005B", length = 50)
	private String memberRelationCode;
	
	
	/**
	 * @fieldName: memberBirthDate
	 * @fieldType: java.util.Date
	 * @Description: 家庭成员或社会关系人出生日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A36007")
	@Temporal(TemporalType.DATE)
	private Date memberBirthDate;
	
	/**
	 * @fieldName: sex
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 家庭成员或社会关系人性别
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "A36012")
	private CodeInfo sex;
	
	
	
	/**
	 * @fieldName: memberIDCard
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人公民身份号码
	 */
	@Column(name = "A36013", length = 50)
	private String memberIDCard;
	
	/**
	 * @fieldName: memberNationality
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人国籍
	 */
	@Column(name = "A36017", length = 50)
	private String memberNationality;
	
	
	/**
	 * @fieldName: memberFamousFamily
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人民族S
	 */
	@Column(name = "A36021", length = 50)
	private String memberFamousFamily;
	

	/**
	 * @fieldName: memberEducation
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人学历
	 */
	@Column(name = "A36025", length = 50)
	private String memberEducation;
	
	
	/**
	 * @fieldName: politicalStatus
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人政治面貌
	 */
	@Column(name = "A36027A", length = 50)
	private String politicalStatus;
	
	
	/**
	 * @fieldName: politicalStatusCode
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人政治面貌代码
	 */
	@Column(name = "A36027B", length = 50)
	private String politicalStatusCode;
	

	/**
	 * @fieldName: husWifeSeparationDate
	 * @fieldType: java.util.Date
	 * @Description: 夫妻两地分居日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A36030")
	@Temporal(TemporalType.DATE)
	private Date husWifeSeparationDate;
	
	
	/**
	 * @fieldName: memberIdentity
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人身份
	 */
	@Column(name = "A36031", length = 50)
	private String memberIdentity;

	/**
	 * @fieldName: memberPost
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人职务
	 */
	@Column(name = "A36032", length = 50)
	private String memberPost;
	
	/**
	 * @fieldName: memberRank
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人职级
	 */
	@Column(name = "A36038", length = 50)
	private String memberRank;
	
	/**
	 * @fieldName: memberNowStatus
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人现状
	 */
	@Column(name = "A36041", length = 50)
	private String memberNowStatus;
	
	/**
	 * @fieldName: memberRemark
	 * @fieldType: java.lang.String
	 * @Description: 家庭成员或社会关系人备注
	 */
	@Column(name = "A36044", length = 50)
	private String memberRemark;
	
	
	/**
	 * @fieldName: memberRelationNo
	 * @fieldType: java.lang.Integer
	 * @Description: 家庭成员或社会关系人序号
	 */
	@Column(name = "A36047",length=2)
	private Integer memberRelationNo;


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberRelationName() {
		return memberRelationName;
	}


	public void setMemberRelationName(String memberRelationName) {
		this.memberRelationName = memberRelationName;
	}


	public String getMemberRelationCode() {
		return memberRelationCode;
	}


	public void setMemberRelationCode(String memberRelationCode) {
		this.memberRelationCode = memberRelationCode;
	}


	public Date getMemberBirthDate() {
		return memberBirthDate;
	}


	public void setMemberBirthDate(Date memberBirthDate) {
		this.memberBirthDate = memberBirthDate;
	}


	public CodeInfo getSex() {
		return sex;
	}


	public void setSex(CodeInfo sex) {
		this.sex = sex;
	}


	public String getMemberIDCard() {
		return memberIDCard;
	}


	public void setMemberIDCard(String memberIDCard) {
		this.memberIDCard = memberIDCard;
	}


	public String getMemberNationality() {
		return memberNationality;
	}


	public void setMemberNationality(String memberNationality) {
		this.memberNationality = memberNationality;
	}


	public String getMemberFamousFamily() {
		return memberFamousFamily;
	}


	public void setMemberFamousFamily(String memberFamousFamily) {
		this.memberFamousFamily = memberFamousFamily;
	}


	public String getMemberEducation() {
		return memberEducation;
	}


	public void setMemberEducation(String memberEducation) {
		this.memberEducation = memberEducation;
	}


	public String getPoliticalStatus() {
		return politicalStatus;
	}


	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}


	public String getPoliticalStatusCode() {
		return politicalStatusCode;
	}


	public void setPoliticalStatusCode(String politicalStatusCode) {
		this.politicalStatusCode = politicalStatusCode;
	}


	public Date getHusWifeSeparationDate() {
		return husWifeSeparationDate;
	}


	public void setHusWifeSeparationDate(Date husWifeSeparationDate) {
		this.husWifeSeparationDate = husWifeSeparationDate;
	}


	public String getMemberIdentity() {
		return memberIdentity;
	}


	public void setMemberIdentity(String memberIdentity) {
		this.memberIdentity = memberIdentity;
	}


	public String getMemberPost() {
		return memberPost;
	}


	public void setMemberPost(String memberPost) {
		this.memberPost = memberPost;
	}


	public String getMemberRank() {
		return memberRank;
	}


	public void setMemberRank(String memberRank) {
		this.memberRank = memberRank;
	}


	public String getMemberNowStatus() {
		return memberNowStatus;
	}


	public void setMemberNowStatus(String memberNowStatus) {
		this.memberNowStatus = memberNowStatus;
	}


	public String getMemberRemark() {
		return memberRemark;
	}


	public void setMemberRemark(String memberRemark) {
		this.memberRemark = memberRemark;
	}


	public Integer getMemberRelationNo() {
		return memberRelationNo;
	}


	public void setMemberRelationNo(Integer memberRelationNo) {
		this.memberRelationNo = memberRelationNo;
	}



}
