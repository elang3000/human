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

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class ServantBasicInfo implements Serializable {
	
	private static final long serialVersionUID = 1352441589595934768L;
	
	@Embedded
	@Id
	@JsonIgnore
	ServantBasicInfoContent content;
	
	@Transient
	private String id;
	
	@Transient
	private String name;
	
	@Transient
	private String idNumber;
	
	@Transient
	private String departId;
	
	@Transient
	private String departName;
	
	@Transient
	private String sexCode;
	
	@Transient
	private String sexName;
	
	@Transient
	private String nationCode;
	
	@Transient
	private String nationName;
	
	@Transient
	private String years;
	
	@Transient
	private String postLevelCode;
	
	@Transient
	private String postLevelName;
	
	@Transient
	private String postCode;
	
	@Transient
	private String postName;
	
	@Transient
	private String isLeaderPostCode;
	
	@Transient
	private String isLeaderPostName;
	
	@Transient
	private String postYears;
	
	@Transient
	private String educationCode;
	
	@Transient
	private String educationName;
	
	@Transient
	private String degreeCode;
	
	@Transient
	private String degreeName;
	
	@Transient
	private String isHoldCode;
	
	@Transient
	private String isHoldName;
	
	public ServantBasicInfoContent getContent() {
		
		return content;
	}
	
	public void setContent(ServantBasicInfoContent content) {
		
		this.content = content;
	}
	
	public String getId() {
		
		return getContent().getId();
	}
	
	public String getName() {
		
		return getContent().getName();
	}
	
	public String getIdNumber() {
		
		return getContent().getIdNumber();
	}
	
	public String getDepartId() {
		
		return getContent().getDepartId();
	}
	
	public String getDepartName() {
		
		return getContent().getDepartName();
	}
	
	public String getSexCode() {
		
		return getContent().getSexCode();
	}
	
	public String getSexName() {
		
		return getContent().getSexName();
	}
	
	public String getNationCode() {
		
		return getContent().getNationCode();
	}
	
	public String getNationName() {
		
		return getContent().getNationName();
	}
	
	public String getYears() {
		
		return getContent().getYears();
	}
	
	public String getPostLevelCode() {
		
		return getContent().getPostLevelCode();
	}
	
	public String getPostLevelName() {
		
		return getContent().getPostLevelName();
	}
	
	public String getPostCode() {
		
		return getContent().getPostCode();
	}
	
	public String getPostName() {
		
		return getContent().getPostName();
	}
	
	public String getIsLeaderPostCode() {
		
		return getContent().getIsLeaderPostCode();
	}
	
	public String getIsLeaderPostName() {
		
		return getContent().getIsLeaderPostName();
	}
	
	public String getPostYears() {
		
		return getContent().getPostYears();
	}
	
	public String getEducationCode() {
		
		return getContent().getEducationCode();
	}
	
	public String getEducationName() {
		
		return getContent().getEducationName();
	}
	
	public String getDegreeCode() {
		
		return getContent().getDegreeCode();
	}
	
	public String getDegreeName() {
		
		return getContent().getDegreeName();
	}
	
	public String getIsHoldCode() {
		
		return getContent().getIsHoldCode();
	}
	
	public String getIsHoldName() {
		
		return getContent().getIsHoldName();
	}
	
}
