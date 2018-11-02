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

package com.wondersgroup.human.vo.pubinst;

import com.wondersgroup.human.bo.pubinst.PublicInstitution;

/**
 * @ClassName: PublicInstitutionVO
 * @Description: 人员基本信息VO
 * @author: tzy
 * @date: 2018年5月28日 上午10:06:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PublicInstitutionVO {
	
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
	 * @fieldName: nowPostName
	 * @fieldType: java.lang.String
	 * @Description: 现职务层次
	 */
	private String postName;
	
	/**
	 * @fieldName: isOnHold
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员管理状态,DM200 该人在管理上的在职、离退等状态。
	 */
	private String isOnHold;
	
	public PublicInstitutionVO(){
		
	}
	
	/**
	 * @Title:ServantVO
	 * @Description:将servant bo转换为vo
	 * @param s  转换的Servant BO对象
	 */
	public PublicInstitutionVO(PublicInstitution p){
		this.id=p.getId();
		this.name=p.getName();
		if(p.getSex()!=null){
			this.sex=p.getSex().getName();
		}
		this.cardNo=p.getCardNo();
		this.departName=p.getDepartName();
		this.postName = p.getNowPostName();
		if(p.getIsOnHold()!=null){
			this.isOnHold=p.getIsOnHold().getName();
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

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

}
