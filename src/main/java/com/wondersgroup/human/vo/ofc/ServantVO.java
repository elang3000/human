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

package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: ServantVO
 * @Description: 人员基本信息VO
 * @author: tzy
 * @date: 2018年5月28日 上午10:06:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ServantVO {
	
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
	 * @Description: 人事关系所在单位ID。
	 */
	private String departId;
	
	/**
	 * @fieldName: jobLevel
	 * @fieldType: java.lang.String
	 * @Description: 现职务级别
	 */
	private String jobLevel;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	private String postName;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	private String postAttributeName;
	
	/**
	 * @fieldName: isOnHold
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员管理状态,DM200 该人在管理上的在职、离退等状态。
	 */
	private String isOnHold;
	
	/**
	 * @Description: 出生日期。
	 */
	private String birthDate;
	
	/**
	 * @Description: 民族。
	 */
	private String nation;
	
	/**
	 * @Description: 政治面貌。
	 */
	private String politics;
	
	/**
	 * @Description: 出生地。
	 */
	private String birthPlaceName;
	
	/**
	 * @Description: 籍贯。
	 */
	private String nativePlaceName;
	
	/**
	 * @Description: 人员类别。
	 */
	private String personType;
	
	/**
	 * @Description: 健康状况。
	 */
	private String health;
	
	/**
	 * @Description: 照片。
	 */
	private String photoPath;
	
	/**
	 * @Description: 列表复选框回显。
	 */
	@JsonProperty(value = "LAY_CHECKED")
	private Boolean LAY_CHECKED;
	
	public ServantVO() {
		
	}
	
	/**
	 * @Title:ServantVO
	 * @Description:将servant bo转换为vo
	 * @param s 转换的Servant BO对象
	 */
	public ServantVO(Servant s) {
		this.id = s.getId();
		this.name = s.getName();
		this.postName = s.getNowPostName();
		if (s.getSex() != null) {
			this.sex = s.getSex().getName();
		}
		this.cardNo = s.getCardNoView();
		this.departName = s.getDepartName();
		this.departId = s.getDepartId();
		if (s.getNowJobLevel() != null) {
			this.jobLevel = s.getNowJobLevel().getName();
		}
		if (s.getIsOnHold() != null) {
			this.isOnHold = s.getIsOnHold().getName();
		}
		if (s.getNowPostAttribute() != null) {
			this.postAttributeName = s.getNowPostAttribute().getName();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getBirthDate() != null) {
			this.birthDate = sdf.format(s.getBirthDate());
		}
		if (s.getNation() != null) {
			this.nation = s.getNation().getName();
		}
		if (s.getPolitics() != null) {
			this.politics = s.getPolitics().getName();
		}
		this.birthPlaceName = s.getBirthPlaceName();
		this.nativePlaceName = s.getNativePlaceName();
		this.photoPath = s.getPhotoPath();
		if (s.getPersonType() != null) {
			this.personType = s.getPersonType().getName();
		}
		if (s.getHealth() != null) {
			this.health = s.getHealth().getName();
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
	
	public String getJobLevel() {
		
		return jobLevel;
	}
	
	public void setJobLevel(String jobLevel) {
		
		this.jobLevel = jobLevel;
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
	
	public String getPostAttributeName() {
		
		return postAttributeName;
	}
	
	public void setPostAttributeName(String postAttributeName) {
		
		this.postAttributeName = postAttributeName;
	}
	
	public String getDepartId() {
		
		return departId;
	}
	
	public void setDepartId(String departId) {
		
		this.departId = departId;
	}
	
	public String getBirthDate() {
		
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		
		this.birthDate = birthDate;
	}
	
	public String getNation() {
		
		return nation;
	}
	
	public void setNation(String nation) {
		
		this.nation = nation;
	}
	
	public String getPolitics() {
		
		return politics;
	}
	
	public void setPolitics(String politics) {
		
		this.politics = politics;
	}
	
	public String getBirthPlaceName() {
		
		return birthPlaceName;
	}
	
	public void setBirthPlaceName(String birthPlaceName) {
		
		this.birthPlaceName = birthPlaceName;
	}
	
	public String getNativePlaceName() {
		
		return nativePlaceName;
	}
	
	public void setNativePlaceName(String nativePlaceName) {
		
		this.nativePlaceName = nativePlaceName;
	}
	
	public String getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(String personType) {
		
		this.personType = personType;
	}
	
	public String getHealth() {
		
		return health;
	}
	
	public void setHealth(String health) {
		
		this.health = health;
	}
	
	public String getPhotoPath() {
		
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		
		this.photoPath = photoPath;
	}
	
	public Boolean getLAY_CHECKED() {
		
		return LAY_CHECKED;
	}
	
	public void setLAY_CHECKED(Boolean lAY_CHECKED) {
		
		LAY_CHECKED = lAY_CHECKED;
	}
}
