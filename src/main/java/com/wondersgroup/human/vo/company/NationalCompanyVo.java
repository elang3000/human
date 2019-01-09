package com.wondersgroup.human.vo.company;

import com.wondersgroup.human.bo.company.NationalCompany;

public class NationalCompanyVo {

	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名 ,在公安户籍管理部门登记注册、人事档案中记载的、正在使用的该人姓名全称。
	 */
	private String name;
	
	/**
	 * @fieldName: sex
	 * @fieldType: java.lang.String
	 * @Description: 性别。
	 */
	private String sex;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 公民身份证号 ,公安机关对（公民）居民给出的唯一的、终身不变的法定号码。
	 */
	private String cardNo;
	
	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位名称 ,该人人事关系所在的单位名称。
	 */
	private String departName;
	
	/**
	 * @fieldName: nowPostRank
	 * @fieldType: java.lang.String
	 * @Description: 现职务层次
	 */
	private String nowPostName;
	
	/**
	 * @fieldName: isOnHold
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员管理状态,DM200 该人在管理上的在职、离退等状态。
	 */
	private String isOnHold;
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	private String personType;
	
	/**
	 * @fieldName: politics
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 政治面貌 ,该人当前的政治面貌代码。GBT_4762_1984 政治面貌代码
	 */
	private String politics;
	
	/**
	 * @fieldName: topEducation
	 * @fieldType: java.lang.String
	 * @Description: 最高学历（自动填写）,该人取得的最高学历
	 */
	private String topEducation;
	
	
	/**
	 * @fieldName: topDegree
	 * @fieldType: java.lang.String
	 * @Description: 最高学位（自动填写）,该人取得的最高学位
	 */
	private String topDegree;
	
	
	public NationalCompanyVo(){
		
	}
	
	/*
	 * @Description:将servant bo转换为vo
	 * @param s  转换的Servant BO对象
	 */
	
	public  NationalCompanyVo(NationalCompany n){
		
		this.id=n.getId();
		this.name=n.getName();
		if(n.getSex()!=null){
			this.sex=n.getSex().getName();
		}
		this.cardNo=n.getCardNoView();
		this.departName=n.getDepartName();
        this.nowPostName=n.getNowPostName();
        this.topEducation = n.getTopEducation();
        this.topDegree = n.getTopDegree();
		if(n.getIsOnHold()!=null){
			this.isOnHold=n.getIsOnHold().getName();
		}
		if (n.getPersonType() != null) {
			this.personType = n.getPersonType().getName();
		}
		if (n.getPolitics() != null) {
			this.politics = n.getPolitics().getName();
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getNowPostName() {
		return nowPostName;
	}

	public void setNowPostName(String nowPostName) {
		this.nowPostName = nowPostName;
	}

	public String getIsOnHold() {
		return isOnHold;
	}

	public void setIsOnHold(String isOnHold) {
		this.isOnHold = isOnHold;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
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
	
	
	
	
	
	
	
	
	
	

}
