/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseEventOutMgr.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 调出情况和人员基本信息 调任和转任流程共同使用
 * 创建人: tzy
 * 创建时间: 2018年8月1日 上午10:46:03
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月1日 上午10:46:03
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

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
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: BaseEventOutMgr
 * @Description: 调出情况和人员基本信息 调任和转任流程共同使用
 * @author: tzy
 * @date: 2018年8月1日 上午10:46:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseEventOut<T> extends GenericEntity {
	
	private static final long serialVersionUID = 8679092571466076269L;
	
	/**
	 * @fieldName: realJobLevelCode
	 * @fieldType: java.lang.String
	 * @Description: 真实占编：职级代码
	 */
	@Column(name = "REAL_JOB_LVL_CODE")
	private String realJobLevelCode;
	
	/**
	 * @fieldName: realLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 真实占编：职级属性 是否领导，用于区分正科级和副科级。CommonConst.YES/CommonConst.NO
	 */
	@Column(name = "REAL_LEADER")
	private Integer realLeader;
	
	/**
	 * @fieldName: isLeaderView
	 * @fieldType: java.lang.String
	 * @Description: 职级属性 是否领导 用于显示。
	 */
	@Column(name = "IS_LEADER_VIEW")
	private String isLeaderView;
	
	/**
	 * @fieldName: 职级名称
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级名称。
	 */
	@Column(name = "JOBLEVEL_NAME")
	private String jobLevelName;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: 调出本单位类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调出或离开本单位的情况分类。GB/T 12405-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY")
	private CodeInfo category;
	
	/**
	 * @fieldName: 调出本单位日期
	 * @fieldType: java.util.Date
	 * @Description: 由组织、干部、人事、劳动部门签发的该人调出、退职、除名等文件的日期或者因其他原因实际离开的日期。GB/T 7408-2005
	 */
	@Column(name = "OUT_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date outDate;
	
	/**
	 * @fieldName: 调往单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人调往的工作单位的名称。
	 */
	@Column(name = "GOTO_UNIT_NAME", length = 120)
	private String gotoUnitName;
	
	/**
	 * @fieldName: 调往单位代码
	 * @fieldType: java.lang.String
	 * @Description: 该人调往的工作单位的代码。GB 32100-2015
	 */
	@Column(name = "GOTO_UNIT_CODE", length = 120)
	private String gotoUnitCode;
	
	/**
	 * @fieldName: 调往单位所在行政区划
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调往的工作单位所在的当前国家县级及县级以上行政区划。GB/T 2260-2007
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOTOUNIT_AREACODE")
	private CodeInfo gotoUnitAreaCode;
	
	/**
	 * @fieldName: 调往单位隶属关系
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 经机构编制管理部门批准的该人调往单位按国家行政分级管理的系统隶属层次。GB/T 12404-1997
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOTOUNIT_RELATIONSHIP")
	private CodeInfo gotoUnitRelationship;
	
	/**
	 * @fieldName: 调往单位级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调往单位的级别。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOTO_UNITLEVEL")
	private CodeInfo gotoUnitLevel;
	
	/**
	 * @fieldName: 调往单位性质类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调往单位性质的类别。DM142
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOTOUNIT_PROPERTIES")
	private CodeInfo gotoUnitProperties;
	
	/**
	 * @fieldName: 调往单位所属行业
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调往单位属于的某类行业。GB/T 4754-2011
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOTOUNIT_OCCUPATION")
	private CodeInfo gotoUnitOccupation;
	
	/**
	 * @fieldName: 调动原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人工作调动的原因。DM015
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REASONTYPE")
	private CodeInfo reasonType;
	
	/**
	 * @fieldName: 调出备注
	 * @fieldType: java.lang.String
	 * @Description: 有关该人调出或离开情况的补充说明。
	 */
	@Column(name = "REMARK", length = 120)
	private String remark;
	
	/**
	 * @fieldName: 提出调动类型
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调动时是由工作需要组织调动，还是由个人申请调动的情况。DM039
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROPOSETYPE")
	private CodeInfo proposeType;
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}
	
	public CodeInfo getCategory() {
		
		return category;
	}
	
	public void setCategory(CodeInfo category) {
		
		this.category = category;
	}
	
	public Date getOutDate() {
		
		return outDate;
	}
	
	public void setOutDate(Date outDate) {
		
		this.outDate = outDate;
	}
	
	public String getGotoUnitName() {
		
		return gotoUnitName;
	}
	
	public void setGotoUnitName(String gotoUnitName) {
		
		this.gotoUnitName = gotoUnitName;
	}
	
	public String getGotoUnitCode() {
		
		return gotoUnitCode;
	}
	
	public void setGotoUnitCode(String gotoUnitCode) {
		
		this.gotoUnitCode = gotoUnitCode;
	}
	
	public CodeInfo getGotoUnitAreaCode() {
		
		return gotoUnitAreaCode;
	}
	
	public void setGotoUnitAreaCode(CodeInfo gotoUnitAreaCode) {
		
		this.gotoUnitAreaCode = gotoUnitAreaCode;
	}
	
	public CodeInfo getGotoUnitRelationship() {
		
		return gotoUnitRelationship;
	}
	
	public void setGotoUnitRelationship(CodeInfo gotoUnitRelationship) {
		
		this.gotoUnitRelationship = gotoUnitRelationship;
	}
	
	public CodeInfo getGotoUnitLevel() {
		
		return gotoUnitLevel;
	}
	
	public void setGotoUnitLevel(CodeInfo gotoUnitLevel) {
		
		this.gotoUnitLevel = gotoUnitLevel;
	}
	
	public CodeInfo getGotoUnitProperties() {
		
		return gotoUnitProperties;
	}
	
	public void setGotoUnitProperties(CodeInfo gotoUnitProperties) {
		
		this.gotoUnitProperties = gotoUnitProperties;
	}
	
	public CodeInfo getGotoUnitOccupation() {
		
		return gotoUnitOccupation;
	}
	
	public void setGotoUnitOccupation(CodeInfo gotoUnitOccupation) {
		
		this.gotoUnitOccupation = gotoUnitOccupation;
	}
	
	public CodeInfo getReasonType() {
		
		return reasonType;
	}
	
	public void setReasonType(CodeInfo reasonType) {
		
		this.reasonType = reasonType;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public CodeInfo getProposeType() {
		
		return proposeType;
	}
	
	public void setProposeType(CodeInfo proposeType) {
		
		this.proposeType = proposeType;
	}
	
	public String getRealJobLevelCode() {
		
		return realJobLevelCode;
	}
	
	public void setRealJobLevelCode(String realJobLevelCode) {
		
		this.realJobLevelCode = realJobLevelCode;
	}
	
	public Integer getRealLeader() {
		
		return realLeader;
	}
	
	public void setRealLeader(Integer realLeader) {
		
		this.realLeader = realLeader;
	}
	
	public String getIsLeaderView() {
		
		return isLeaderView;
	}
	
	public void setIsLeaderView(String isLeaderView) {
		
		this.isLeaderView = isLeaderView;
	}
	
	public String getJobLevelName() {
		
		return jobLevelName;
	}
	
	public void setJobLevelName(String jobLevelName) {
		
		this.jobLevelName = jobLevelName;
	}
}
