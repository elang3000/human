package com.wondersgroup.human.dto.businesspersonel;

import java.io.Serializable;

import com.wondersgroup.framework.dict.bo.CodeInfo;

public class BusinessQueryParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6023882257129983721L;

	
	private String name;//姓名
	private String cardNo;//身份证号
	private CodeInfo sex;//性别
	private String departName;//部门名称
	private String ageMax;//年龄上限
	private String ageMin;//年龄下限
	private String code1;
	private String code2;
	private String code3;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public CodeInfo getSex() {
		return sex;
	}
	public void setSex(CodeInfo sex) {
		this.sex = sex;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getAgeMax() {
		return ageMax;
	}
	public void setAgeMax(String ageMax) {
		this.ageMax = ageMax;
	}
	public String getAgeMin() {
		return ageMin;
	}
	public void setAgeMin(String ageMin) {
		this.ageMin = ageMin;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public String getCode3() {
		return code3;
	}
	public void setCode3(String code3) {
		this.code3 = code3;
	}
	
	
	
	

}
