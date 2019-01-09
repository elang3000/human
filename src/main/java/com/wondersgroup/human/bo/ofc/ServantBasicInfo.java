/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServatBasicInfo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述: 公务员基础信息视图对象
 * 创建人: Wonders-Rain
 * 创建时间: 2018年9月24日 下午3:35:14
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年9月24日 下午3:35:14
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: ServatBasicInfo
 * @Description: 公务员基础信息视图对象
 * @author: Wonders-Rain
 * @date: 2018年9月24日 下午3:35:14
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "V_SERVANT_BASIC_INFO")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServantBasicInfo implements Serializable {
	
	private static final long serialVersionUID = 1352441589595934768L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ID_NUMBER")
	private String idNumber;
	
	@Transient
	private String idNumberStr;
	
	@Column(name = "DEPART_ID")
	private String departId;
	
	@Column(name = "DEPART_NAME")
	private String departName;
	
	@Column(name = "SEX_CODE")
	private String sexCode;
	
	@Column(name = "SEX_NAME")
	private String sexName;
	
	@Column(name = "NATION_CODE")
	private String nationCode;
	
	@Column(name = "NATION_NAME")
	private String nationName;
	
	@Column(name = "YEARS")
	private String years;
	
	@Column(name = "JL_CODE")
	private String postLevelCode;
	
	@Column(name = "JL_NAME")
	private String postLevelName;
	
	@Column(name = "POST_CODE")
	private String postCode;
	
	@Column(name = "POST_NAME")
	private String postName;
	
	@Column(name = "IS_LEADER_POST_CODE")
	private String isLeaderPostCode;
	
	@Column(name = "IS_LEADER_POST_NAME")
	private String isLeaderPostName;
	
	@Column(name = "POST_YEARS")
	private String postYears;
	
	@Column(name = "EDUCATION_CODE")
	private String educationCode;
	
	@Column(name = "EDUCATION_NAME")
	private String educationName;
	
	@Column(name = "DEGREE_CODE")
	private String degreeCode;
	
	@Column(name = "DEGREE_NAME")
	private String degreeName;
	
	@Column(name = "IS_HOLD_CODE")
	private String isHoldCode;
	
	@Column(name = "IS_HOLD_NAME")
	private String isHoldName;
	
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
	
	public String getIdNumber() {
		
		return idNumber;
	}
	
	public void setIdNumber(String idNumber) {
		
		this.idNumber = idNumber;
	}
	
	public String getDepartId() {
		
		return departId;
	}
	
	public void setDepartId(String departId) {
		
		this.departId = departId;
	}
	
	public String getDepartName() {
		
		return departName;
	}
	
	public void setDepartName(String departName) {
		
		this.departName = departName;
	}
	
	public String getSexCode() {
		
		return sexCode;
	}
	
	public void setSexCode(String sexCode) {
		
		this.sexCode = sexCode;
	}
	
	public String getSexName() {
		
		return sexName;
	}
	
	public void setSexName(String sexName) {
		
		this.sexName = sexName;
	}
	
	public String getNationCode() {
		
		return nationCode;
	}
	
	public void setNationCode(String nationCode) {
		
		this.nationCode = nationCode;
	}
	
	public String getNationName() {
		
		return nationName;
	}
	
	public void setNationName(String nationName) {
		
		this.nationName = nationName;
	}
	
	public String getYears() {
		
		return years;
	}
	
	public void setYears(String years) {
		
		this.years = years;
	}
	
	public String getPostLevelCode() {
		
		return postLevelCode;
	}
	
	public void setPostLevelCode(String postLevelCode) {
		
		this.postLevelCode = postLevelCode;
	}
	
	public String getPostLevelName() {
		
		return postLevelName;
	}
	
	public void setPostLevelName(String postLevelName) {
		
		this.postLevelName = postLevelName;
	}
	
	public String getPostCode() {
		
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		
		this.postCode = postCode;
	}
	
	public String getPostName() {
		
		return postName;
	}
	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}
	
	public String getIsLeaderPostCode() {
		
		return isLeaderPostCode;
	}
	
	public void setIsLeaderPostCode(String isLeaderPostCode) {
		
		this.isLeaderPostCode = isLeaderPostCode;
	}
	
	public String getIsLeaderPostName() {
		
		return isLeaderPostName;
	}
	
	public void setIsLeaderPostName(String isLeaderPostName) {
		
		this.isLeaderPostName = isLeaderPostName;
	}
	
	public String getPostYears() {
		
		return postYears;
	}
	
	public void setPostYears(String postYears) {
		
		this.postYears = postYears;
	}
	
	public String getEducationCode() {
		
		return educationCode;
	}
	
	public void setEducationCode(String educationCode) {
		
		this.educationCode = educationCode;
	}
	
	public String getEducationName() {
		
		return educationName;
	}
	
	public void setEducationName(String educationName) {
		
		this.educationName = educationName;
	}
	
	public String getDegreeCode() {
		
		return degreeCode;
	}
	
	public void setDegreeCode(String degreeCode) {
		
		this.degreeCode = degreeCode;
	}
	
	public String getDegreeName() {
		
		return degreeName;
	}
	
	public void setDegreeName(String degreeName) {
		
		this.degreeName = degreeName;
	}
	
	public String getIsHoldCode() {
		
		return isHoldCode;
	}
	
	public void setIsHoldCode(String isHoldCode) {
		
		this.isHoldCode = isHoldCode;
	}
	
	public String getIsHoldName() {
		
		return isHoldName;
	}
	
	public void setIsHoldName(String isHoldName) {
		
		this.isHoldName = isHoldName;
	}

	
	public String getIdNumberStr() {
		
		if (StringUtils.isBlank(this.getIdNumber())) {
			return "";
		} else {
			if (this.getIdNumber().length() <= 4) {
				return "XXXX";
			} else {
				this.idNumberStr = StringUtils.substring(this.getIdNumber(), 0, (this.getIdNumber().length() - 4)) + "XXXX";
				return idNumberStr;
			}
		}
	}

	
	public void setIdNumberStr(String idNumberStr) {
		
		this.idNumberStr = idNumberStr;
	}
	
}
