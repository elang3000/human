/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServantVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员基本信息VO
 * 创建人: tzy
 * 创建时间: 2018年5月28日 上午10:06:33
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月28日 上午10:06:33
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.socialworker;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.socialworker.SocialWorker;

/**
 * @ClassName: ServantVO
 * @Description: 人员基本信息VO
 * @author: tzy
 * @date: 2018年5月28日 上午10:06:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class SocialWorkerVO {
	
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
	
	
	public SocialWorkerVO(){
		
	}
	
	/**
	 * @Title:ServantVO
	 * @Description:将servant bo转换为vo
	 * @param s  转换的Servant BO对象
	 */
	public SocialWorkerVO(SocialWorker s){
		this.id=s.getId();
		this.name=s.getName();
		if(s.getSex()!=null){
			this.sex=s.getSex().getName();
		}
		//this.cardNo=s.getCardNo();
		this.cardNo = s.getCardNoView();//身份证加密
		this.departName=s.getDepartName();
        this.nowPostName=s.getNowPostName();
        this.topEducation = s.getTopEducation();
        this.topDegree = s.getTopDegree();
		if(s.getIsOnHold()!=null){
			this.isOnHold=s.getIsOnHold().getName();
		}
		if (s.getPersonType() != null) {
			this.personType = s.getPersonType().getName();
		}
		if (s.getPolitics() != null) {
			this.politics = s.getPolitics().getName();
		}
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

	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
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
