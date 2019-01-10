/**
 * Copyright © 2019 . All rights reserved.万达信息股份有限公司
 * 文件名: PeopleGenericy.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述: TODO
 * 创建人: GP
 * 创建时间: 2019年1月7日 下午4:30:16
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2019年1月7日 下午4:30:16
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.bo.ofc;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: PeopleGenericy
 * @Description: TODO
 * @author: GP
 * @date: 2019年1月7日 下午4:30:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity(name = "V_PEOPLE_INFOS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class PeopleGenericy {
	
	@Id
	@Column(name = "id")
	private String id;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名 ,在公安户籍管理部门登记注册、人事档案中记载的、正在使用的该人姓名全称。
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * @fieldName: sex
	 * @fieldType: java.lang.String
	 * @Description: 性别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "sex")
	private CodeInfo sex;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 公民身份证号 ,公安机关对（公民）居民给出的唯一的、终身不变的法定号码。
	 */
	@Column(name = "cardNo")
	private String cardNo;
	
	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位名称 ,该人人事关系所在的单位名称。
	 */
	@Column(name = "departName")
	private String departName;
	
	/**
	 * @fieldName: jobLevel
	 * @fieldType: java.lang.String
	 * @Description: 现职务级别
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "jobLevel")
	private CodeInfo jobLevel;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	@Column(name = "postName")
	private String postName;
	
	/**
	 * @fieldName: postAttributeName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "postAttributeName")
	private CodeInfo postAttributeName;
	
	/**
	 * @fieldName: isOnHold
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员管理状态,DM200 该人在管理上的在职、离退等状态。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "isOnHold")
	private CodeInfo isOnHold;
	
	
	@Column(name = "itype")
	private String itype;
	
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
	
	
	public String getPostName() {
		
		return postName;
	}
	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}
	
	
	
	
	public CodeInfo getSex() {
		
		return sex;
	}

	
	public void setSex(CodeInfo sex) {
		
		this.sex = sex;
	}

	
	public CodeInfo getJobLevel() {
		
		return jobLevel;
	}

	
	public void setJobLevel(CodeInfo jobLevel) {
		
		this.jobLevel = jobLevel;
	}

	
	public CodeInfo getPostAttributeName() {
		
		return postAttributeName;
	}

	
	public void setPostAttributeName(CodeInfo postAttributeName) {
		
		this.postAttributeName = postAttributeName;
	}

	
	public CodeInfo getIsOnHold() {
		
		return isOnHold;
	}

	
	public void setIsOnHold(CodeInfo isOnHold) {
		
		this.isOnHold = isOnHold;
	}

	public String getItype() {
		
		return itype;
	}

	
	public void setItype(String itype) {
		
		this.itype = itype;
	}
	
	
	
}
