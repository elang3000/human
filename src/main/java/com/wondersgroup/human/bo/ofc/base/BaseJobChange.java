/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseJobChange.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 职务变动情况
 * 创建人: jiang
 * 创建时间: 2018年9月7日11:41:41
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月7日11:41:45
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

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
 * @ClassName: BaseJobChange
 * @Description: 国标 职务变动情况
 * @author: jiang
 * @date: 2018年9月7日11:41:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseJobChange<T> extends GenericEntity {
	
	private static final long serialVersionUID = 1283359856127707034L;
	
	/**
	 * @fieldName: 原单位名称
	 * @fieldType: java.lang.String
	 * @Description: 与该人原任职务相对应的工作机构及工作机构部门名称。
	 */
	@Column(name = "A52001A", length = 120)
	private String formerUnitName;
	
	/**
	 * @fieldName: 原单位代码
	 * @fieldType: java.lang.String
	 * @Description: 与该人原任职务相对应的工作机构及工作机构部门名称。GB 32100-2015
	 */
	@Column(name = "A52001B", length = 50)
	private String formerUnitCode;
	
	/**
	 * @fieldName: 新单位名称
	 * @fieldType: java.lang.String
	 * @Description: 与该人新任职务相对应的工作机构及工作机构部门名称。
	 */
	@Column(name = "A52004A", length = 120)
	private String newUnitName;
	
	/**
	 * @fieldName: 新单位代码
	 * @fieldType: java.lang.String
	 * @Description: 与该人新任职务相对应的工作机构及工作机构部门代码。GB 32100-2015
	 */
	@Column(name = "A52004B", length = 50)
	private String newUnitCode;
	
	/**
	 * @fieldName: 原职务名称
	 * @fieldType: java.lang.String
	 * @Description: 该人原任职务的名称。
	 */
	@Column(name = "A52008A", length = 80)
	private String formerPostName;
	
	/**
	 * @fieldName: 原职务代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人原任职务的代码。GB/T 12403-1990
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52008B")
	private CodeInfo formerPostCode;
	
	/**
	 * @fieldName: 新职务名称
	 * @fieldType: java.lang.String
	 * @Description: 该人新任职务的名称。
	 */
	@Column(name = "A52012A", length = 80)
	private String newPostName;
	
	/**
	 * @fieldName: 新职务代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务的代码。GB/T 12403-1990
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52012B")
	private CodeInfo newPostCode;
	
	/**
	 * @fieldName: 原职务级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人原任职务的等级。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52015")
	private CodeInfo formerJobLevel;
	
	/**
	 * @fieldName: 新职务级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务的等级。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52018")
	private CodeInfo newJobLevel;
	
	/**
	 * @fieldName: 原职务类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人原任职务在相应的工作机构的领导班子中的职务类别。DM049
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52021")
	private CodeInfo formerPostAttribute;
	
	/**
	 * @fieldName: 新职务类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务在相应的工作机构的领导班子中的职务类别。DM049
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52024")
	private CodeInfo newPostAttribute;
	
	/**
	 * @fieldName:职务变动日期
	 * @fieldType: java.util.Date
	 * @Description: 该人此次职务变动发生的日期，一般为由具有法定管理权限的机构签发
	 *               的文件确定的该职务任职或免职的日期或由会议决定该职务任职或免职的日期。GB/T 7408-2005
	 */
	@Column(name = "A52027")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postChangeDate;
	
	/**
	 * @fieldName: 职务变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务相对于原任职务变动的类别划分。DM006
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52031")
	private CodeInfo postTenureChange;
	
	/**
	 * @fieldName: 是否达到任职年限标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人该职务变动是否达到任职年限的标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52037")
	private CodeInfo deadlineFlag;
	
	/**
	 * @fieldName:现任职级确定日期
	 * @fieldType: java.util.Date
	 * @Description: 该人现任职务等级确定的日期。GB/T 7408-2005
	 */
	@Column(name = "A52044")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date jobLevelConfirmDate;
	
	/**
	 * @fieldName: 晋级原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人晋级的原因。DM113
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52047")
	private CodeInfo promotionType;
	
	/**
	 * @fieldName: 降级原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人降级的原因。DM114
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52051")
	private CodeInfo degradeType;
	
	/**
	 * @fieldName: 因处分延长晋级标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否因处分而延长晋级。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52054")
	private CodeInfo punishmentExtendPromotionFlag;
	
	/**
	 * @fieldName: 延长晋级时间
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人延长晋级的时间段长度。DM115
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A52057")
	private CodeInfo extendPromotionTime;
	
	public String getFormerUnitName() {
		
		return formerUnitName;
	}
	
	public void setFormerUnitName(String formerUnitName) {
		
		this.formerUnitName = formerUnitName;
	}
	
	public String getFormerUnitCode() {
		
		return formerUnitCode;
	}
	
	public void setFormerUnitCode(String formerUnitCode) {
		
		this.formerUnitCode = formerUnitCode;
	}
	
	public String getNewUnitName() {
		
		return newUnitName;
	}
	
	public void setNewUnitName(String newUnitName) {
		
		this.newUnitName = newUnitName;
	}
	
	public String getNewUnitCode() {
		
		return newUnitCode;
	}
	
	public void setNewUnitCode(String newUnitCode) {
		
		this.newUnitCode = newUnitCode;
	}
	
	public String getFormerPostName() {
		
		return formerPostName;
	}
	
	public void setFormerPostName(String formerPostName) {
		
		this.formerPostName = formerPostName;
	}
	
	public CodeInfo getFormerPostCode() {
		
		return formerPostCode;
	}
	
	public void setFormerPostCode(CodeInfo formerPostCode) {
		
		this.formerPostCode = formerPostCode;
	}
	
	public String getNewPostName() {
		
		return newPostName;
	}
	
	public void setNewPostName(String newPostName) {
		
		this.newPostName = newPostName;
	}
	
	public CodeInfo getNewPostCode() {
		
		return newPostCode;
	}
	
	public void setNewPostCode(CodeInfo newPostCode) {
		
		this.newPostCode = newPostCode;
	}
	
	public CodeInfo getFormerJobLevel() {
		
		return formerJobLevel;
	}
	
	public void setFormerJobLevel(CodeInfo formerJobLevel) {
		
		this.formerJobLevel = formerJobLevel;
	}
	
	public CodeInfo getNewJobLevel() {
		
		return newJobLevel;
	}
	
	public void setNewJobLevel(CodeInfo newJobLevel) {
		
		this.newJobLevel = newJobLevel;
	}
	
	public CodeInfo getFormerPostAttribute() {
		
		return formerPostAttribute;
	}
	
	public void setFormerPostAttribute(CodeInfo formerPostAttribute) {
		
		this.formerPostAttribute = formerPostAttribute;
	}
	
	public CodeInfo getNewPostAttribute() {
		
		return newPostAttribute;
	}
	
	public void setNewPostAttribute(CodeInfo newPostAttribute) {
		
		this.newPostAttribute = newPostAttribute;
	}
	
	public Date getPostChangeDate() {
		
		return postChangeDate;
	}
	
	public void setPostChangeDate(Date postChangeDate) {
		
		this.postChangeDate = postChangeDate;
	}
	
	public CodeInfo getPostTenureChange() {
		
		return postTenureChange;
	}
	
	public void setPostTenureChange(CodeInfo postTenureChange) {
		
		this.postTenureChange = postTenureChange;
	}
	
	public CodeInfo getDeadlineFlag() {
		
		return deadlineFlag;
	}
	
	public void setDeadlineFlag(CodeInfo deadlineFlag) {
		
		this.deadlineFlag = deadlineFlag;
	}
	
	public Date getJobLevelConfirmDate() {
		
		return jobLevelConfirmDate;
	}
	
	public void setJobLevelConfirmDate(Date jobLevelConfirmDate) {
		
		this.jobLevelConfirmDate = jobLevelConfirmDate;
	}
	
	public CodeInfo getPromotionType() {
		
		return promotionType;
	}
	
	public void setPromotionType(CodeInfo promotionType) {
		
		this.promotionType = promotionType;
	}
	
	public CodeInfo getDegradeType() {
		
		return degradeType;
	}
	
	public void setDegradeType(CodeInfo degradeType) {
		
		this.degradeType = degradeType;
	}
	
	public CodeInfo getPunishmentExtendPromotionFlag() {
		
		return punishmentExtendPromotionFlag;
	}
	
	public void setPunishmentExtendPromotionFlag(CodeInfo punishmentExtendPromotionFlag) {
		
		this.punishmentExtendPromotionFlag = punishmentExtendPromotionFlag;
	}
	
	public CodeInfo getExtendPromotionTime() {
		
		return extendPromotionTime;
	}
	
	public void setExtendPromotionTime(CodeInfo extendPromotionTime) {
		
		this.extendPromotionTime = extendPromotionTime;
	}
	
}
