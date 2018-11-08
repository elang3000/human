/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ReferenceExchange.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 参公交流
 * 创建人: tzy
 * 创建时间: 2018年9月27日 上午10:44:31
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年9月27日 上午10:44:31
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: ReferenceExchange
 * @Description: 参公交流
 * @author: tzy
 * @date: 2018年9月27日 上午10:44:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_REFERENCE_EXCHANGE")
public class ReferenceExchange extends GenericEntity {
	
	private static final long serialVersionUID = -6827056387518135996L;
	
	/**
	 * 本区
	 */
	public static final String AREA_THIS = "1";
	
	/**
	 * 外区
	 */
	public static final String AREA_OUTER = "2";
	
	// 人员基本信息
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名。
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 身份证。
	 */
	@Column(name = "CARDNO")
	private String cardNo;
	
	/**
	 * @fieldName: politics
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 性别
	 */
	@ManyToOne
	@JoinColumn(name = "SEX")
	private CodeInfo sex;
	
	/**
	 * @fieldName: birthDate
	 * @fieldType: java.util.Date
	 * @Description: 出生日期，GB/T 7408-2005 该人在公安户籍管理部门登记注册的、在人事档案中记载的并经组织、干部、人事部门确认的出生年月日。
	 */
	@Column(name = "BIRTH_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	/**
	 * @fieldName: nativePlaceName
	 * @fieldType: java.lang.String
	 * @Description: 籍贯名称 ,该人祖居所在地的当前县级及县级以上国家行政区划名称
	 */
	@Column(name = "NATIVE_PLACENAME", length = 80)
	private String nativePlaceName;
	
	/**
	 * @fieldName: nativePlace
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 籍贯代码,GB/T 2260-2007 该人祖居所在地的当前县级及县级以上国家行政区划代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "NATIVE_PLACE")
	private CodeInfo nativePlace;
	
	/**
	 * @fieldName: birthPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 出生地名称,该人出生时所在地的当前县级及县级以上国家行政区划名称
	 */
	@Column(name = "BIRTH_PLACENAME", length = 80)
	private String birthPlaceName;
	
	/**
	 * @fieldName: birthPlace
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 出生地代码 ,GB/T 2260-2007 该人出生时所在地的当前国家政区代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "BIRTH_PLACE")
	private CodeInfo birthPlace;
	
	/**
	 * @fieldName: nation
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 民族 ,GB 3304-1991 该人归属的、国家认可的、在公安户籍管理部门登记注册的民族。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "NATION")
	private CodeInfo nation;
	
	/**
	 * @fieldName: politics
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 政治面貌 ,该人当前的政治面貌代码。GB 4762-84 政治面貌代码
	 */
	@ManyToOne
	@JoinColumn(name = "POLITICS")
	private CodeInfo politics;
	
	/**
	 * @fieldName: health
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 健康状况
	 */
	@ManyToOne
	@JoinColumn(name = "HEALTH")
	private CodeInfo health;
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PERSON_TYPE")
	private CodeInfo personType;
	
	/**
	 * @fieldName: areaType
	 * @fieldType: java.lang.String
	 * @Description: 转任地区：1-本区 2-外区。
	 */
	@Column(name = "AREATYPE")
	private String areaType;
	
	// 交流信息
	/**
	 * @fieldName: sourceOrganName
	 * @fieldType: java.lang.String
	 * @Description: 原单位名称 外区人员交流使用
	 */
	@Column(name = "SOURCE_ORGAN_NAME")
	private String sourceOrganName;
	
	/**
	 * @fieldName: SourceOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 原单位信息
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOURCEORGAN_ID")
	private OrganNode sourceOrgan;
	
	/**
	 * @fieldName: TargetOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 交流单位名称
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGETORGAN_ID")
	private OrganNode targetOrgan;
	
	/**
	 * @fieldName: isLowToHigh
	 * @fieldType: java.lang.Boolean
	 * @Description: 职务是否高职低配。
	 */
	@Column(name = "IS_LOW_TO_HIGH")
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean isLowToHigh = false;
	
	/**
	 * @fieldName: postCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职务代码,GB/T 12403-1990 该人担任职务的代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "POST_CODE")
	private CodeInfo postCode;
	
	/**
	 * @fieldName: 交流原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人交流任职的原因。DM083
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REASON")
	private CodeInfo reason;
	
	/**
	 * @fieldName: filePath
	 * @fieldType: java.lang.String
	 * @Description: 附件FTP路径。
	 */
	@Column(name = "FILE_PATH", length = 2000)
	private String filePath;
	
	// 调出信息
	
	/**
	 * @fieldName: 调出本单位类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调出或离开本单位的情况分类。GB/T 12405-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY")
	private CodeInfo category;
	
	/**
	 * @fieldName: 调动原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人工作调动的原因。DM015
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REASONTYPE")
	private CodeInfo reasonType;
	
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
	
	/***************************************************************************
	 * 流程相关属性
	 **************************************************************************/
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态。
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/* 本区参公交流流程 */
	/**
	 * 待提交参公交流申请
	 */
	public static final int STATUS_EXCHANGE_STATE = 0;
	
	/**
	 * 待上级单位审核
	 */
	public static final int STATUS_EXCHANGE_TRIAL = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_EXCHANGE_TRIAL_1 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_EXCHANGE_TRIAL_2 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_EXCHANGE_TRIAL_3 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_EXCHANGE_TRIAL_4 = 5;
	
	/**
	 * 待参公单位同意
	 */
	public static final int STATUS_EXCHANGE_AGREE = 6;
	
	/**
	 * 待区人事主管部门打印电子介绍信
	 */
	public static final int STATUS_EXCHANGE_PRINT = 7;
	
	/**
	 * 已完成申请
	 */
	public static final int STATUS_EXCHANGE_FINISH = 8;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		// 本区参公交流流程
		power.put("STATUS_EXCHANGE_STATE", STATUS_EXCHANGE_STATE);
		power.put("STATUS_EXCHANGE_TRIAL", STATUS_EXCHANGE_TRIAL);
		power.put("STATUS_EXCHANGE_TRIAL_1", STATUS_EXCHANGE_TRIAL_1);
		power.put("STATUS_EXCHANGE_TRIAL_2", STATUS_EXCHANGE_TRIAL_2);
		power.put("STATUS_EXCHANGE_TRIAL_3", STATUS_EXCHANGE_TRIAL_3);
		power.put("STATUS_EXCHANGE_TRIAL_4", STATUS_EXCHANGE_TRIAL_4);
		power.put("STATUS_EXCHANGE_AGREE", STATUS_EXCHANGE_AGREE);
		power.put("STATUS_EXCHANGE_PRINT", STATUS_EXCHANGE_PRINT);
		
		// 外区参公交流流程
		power.put("STATUS_EXCHANGE_OUTER_STATE", STATUS_EXCHANGE_STATE);
		power.put("STATUS_EXCHANGE_OUTER_TRIAL", STATUS_EXCHANGE_TRIAL);
		power.put("STATUS_EXCHANGE_OUTER_TRIAL_1", STATUS_EXCHANGE_TRIAL_1);
		power.put("STATUS_EXCHANGE_OUTER_TRIAL_2", STATUS_EXCHANGE_TRIAL_2);
		power.put("STATUS_EXCHANGE_OUTER_TRIAL_3", STATUS_EXCHANGE_TRIAL_3);
		power.put("STATUS_EXCHANGE_OUTER_TRIAL_4", STATUS_EXCHANGE_TRIAL_4);
		power.put("STATUS_EXCHANGE_OUTER_PRINT", STATUS_EXCHANGE_PRINT);
	}
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
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
	
	public CodeInfo getSex() {
		
		return sex;
	}
	
	public void setSex(CodeInfo sex) {
		
		this.sex = sex;
	}
	
	public Date getBirthDate() {
		
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		
		this.birthDate = birthDate;
	}
	
	public String getNativePlaceName() {
		
		return nativePlaceName;
	}
	
	public void setNativePlaceName(String nativePlaceName) {
		
		this.nativePlaceName = nativePlaceName;
	}
	
	public CodeInfo getNativePlace() {
		
		return nativePlace;
	}
	
	public void setNativePlace(CodeInfo nativePlace) {
		
		this.nativePlace = nativePlace;
	}
	
	public String getBirthPlaceName() {
		
		return birthPlaceName;
	}
	
	public void setBirthPlaceName(String birthPlaceName) {
		
		this.birthPlaceName = birthPlaceName;
	}
	
	public CodeInfo getBirthPlace() {
		
		return birthPlace;
	}
	
	public void setBirthPlace(CodeInfo birthPlace) {
		
		this.birthPlace = birthPlace;
	}
	
	public CodeInfo getNation() {
		
		return nation;
	}
	
	public void setNation(CodeInfo nation) {
		
		this.nation = nation;
	}
	
	public CodeInfo getPolitics() {
		
		return politics;
	}
	
	public void setPolitics(CodeInfo politics) {
		
		this.politics = politics;
	}
	
	public CodeInfo getHealth() {
		
		return health;
	}
	
	public void setHealth(CodeInfo health) {
		
		this.health = health;
	}
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
	}
	
	public String getAreaType() {
		
		return areaType;
	}
	
	public void setAreaType(String areaType) {
		
		this.areaType = areaType;
	}
	
	public String getSourceOrganName() {
		
		return sourceOrganName;
	}
	
	public void setSourceOrganName(String sourceOrganName) {
		
		this.sourceOrganName = sourceOrganName;
	}
	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public CodeInfo getPostCode() {
		
		return postCode;
	}
	
	public void setPostCode(CodeInfo postCode) {
		
		this.postCode = postCode;
	}
	
	public CodeInfo getReason() {
		
		return reason;
	}
	
	public void setReason(CodeInfo reason) {
		
		this.reason = reason;
	}
	
	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}
	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}
	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}
	
	public String getFilePath() {
		
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		
		this.filePath = filePath;
	}
	
	public CodeInfo getCategory() {
		
		return category;
	}
	
	public void setCategory(CodeInfo category) {
		
		this.category = category;
	}
	
	public CodeInfo getReasonType() {
		
		return reasonType;
	}
	
	public void setReasonType(CodeInfo reasonType) {
		
		this.reasonType = reasonType;
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
	
	public OrganNode getTargetOrgan() {
		
		return targetOrgan;
	}
	
	public void setTargetOrgan(OrganNode targetOrgan) {
		
		this.targetOrgan = targetOrgan;
	}
	
	public Boolean getIsLowToHigh() {
		
		return isLowToHigh;
	}
	
	public void setIsLowToHigh(Boolean isLowToHigh) {
		
		this.isLowToHigh = isLowToHigh;
	}
	
}
