/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenTLB.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 同类别转任
 * 创建人: tzy
 * 创建时间: 2018年7月31日 下午5:03:42
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月31日 下午5:03:42
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/**
 * @ClassName: ZhuanRenTLB
 * @Description: 同类别转任 转入情况信息
 * @author: tzy
 * @date: 2018年7月31日 下午5:03:42
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ZHUANREN_TLB_INTO")
public class ZhuanRenTLBIntoMgr extends BaseEventIntoMgr<ZhuanRenTLBIntoMgr> {
	
	private static final long serialVersionUID = 4543324423203002791L;
	
	/**
	 * 本区
	 */
	public static final String AREA_THIS = "1";
	
	/**
	 * 外区
	 */
	public static final String AREA_OUTER = "2";
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PERSON_TYPE")
	private CodeInfo personType;
	
	// 转任信息
	/**
	 * @fieldName: zhuanRenTLBOutMgr
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutMgr
	 * @Description: 转出情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ZHUANRENTLBOUTMGR_ID")
	private ZhuanRenTLBOutMgr zhuanRenTLBOutMgr;
	
	/**
	 * @fieldName: areaType
	 * @fieldType: java.lang.String
	 * @Description: 转任地区：1-本区 2-外区。
	 */
	@Column(name = "AREATYPE")
	private String areaType;
	
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
	 * @Description: 转入所在单位信息
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGETORGAN_ID")
	private OrganNode TargetOrgan;
	
	/**
	 * @fieldName: adminAuthNum
	 * @fieldType: java.lang.long
	 * @Description: 转入单位：行政编制数
	 */
	@Column(name = "ADMINAUTH_NUM")
	private long adminAuthNum;
	
	/**
	 * @fieldName: inFactNum
	 * @fieldType: java.lang.long
	 * @Description: 转入单位：行政编制数-实有人数
	 */
	@Column(name = "INFACT_NUM")
	private long inFactNum;
	
	/**
	 * @fieldName: vacancyNum
	 * @fieldType: java.lang.long
	 * @Description: 转入单位：行政编制数-空缺人数
	 */
	@Column(name = "VACANCY_NUM")
	private long vacancyNum;
	
	/**
	 * @fieldName: postNum
	 * @fieldType: java.lang.long
	 * @Description: 转入单位：职务职数
	 */
	@Column(name = "POST_NUM")
	private long postNum;
	
	/**
	 * @fieldName: postInFactNum
	 * @fieldType: java.lang.long
	 * @Description: 转入单位：职务职数-实有人数
	 */
	@Column(name = "POST_INFACT_NUM")
	private long postInFactNum;
	
	/**
	 * @fieldName: postVacancyNum
	 * @fieldType: java.lang.long
	 * @Description: 转入单位：职务职数-空缺人数
	 */
	@Column(name = "POST_VACANCY_NUM")
	private long postVacancyNum;
	
	/**
	 * @fieldName: postCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 拟任职务代码,GB/T 12403-1990 该人担任职务的代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "POST_CODE")
	private CodeInfo postCode;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 拟任职务名称 ,该人担任职务的具体名称。
	 */
	@Column(name = "POST_NAME", length = 80)
	private String postName;
	
	/**
	 * @fieldName: attribute
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 拟任职务属性,DM049 担任领导职务或非领导职务的情况。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ATTRIBUTE")
	private CodeInfo attribute;
	
	/**
	 * @fieldName: causeType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 字典CODE：causeType 转任事由：1--工作需要、2--军属随调、3--解决家庭生活困难。其中工作需要和解决家庭生活困难需注明具体事由。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CAUSETYPE")
	private CodeInfo causeType;
	
	/**
	 * @fieldName: specificCause
	 * @fieldType: java.lang.String
	 * @Description: 转任具体事由。
	 */
	@Column(name = "SPECIFIC_CAUSE", length = 400)
	private String specificCause;
	
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
	
	/* 本区转任流程 */
	/**
	 * 待提交转任申请
	 */
	public static final int STATUS_ZHUANREN_STATE = 0;
	
	/**
	 * 待上级单位审核
	 */
	public static final int STATUS_ZHUANREN_TRIAL = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_ZHUANREN_TRIAL_1 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_ZHUANREN_TRIAL_2 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_ZHUANREN_TRIAL_3 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_ZHUANREN_TRIAL_4 = 5;
	
	/**
	 * 待转出单位同意
	 */
	public static final int STATUS_ZHUANREN_AGREE = 6;
	
	/**
	 * 待区人事主管部门打印电子介绍信
	 */
	public static final int STATUS_ZHUANREN_PRINT = 7;
	
	/**
	 * 人员信息已入库
	 */
	public static final int STATUS_ZHUANREN_FINISH = 8;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		//本区转入流程
		power.put("STATUS_ZHUANREN_STATE", STATUS_ZHUANREN_STATE);
		power.put("STATUS_ZHUANREN_TRIAL", STATUS_ZHUANREN_TRIAL);
		power.put("STATUS_ZHUANREN_TRIAL_1", STATUS_ZHUANREN_TRIAL_1);
		power.put("STATUS_ZHUANREN_TRIAL_2", STATUS_ZHUANREN_TRIAL_2);
		power.put("STATUS_ZHUANREN_TRIAL_3", STATUS_ZHUANREN_TRIAL_3);
		power.put("STATUS_ZHUANREN_TRIAL_4", STATUS_ZHUANREN_TRIAL_4);
		power.put("STATUS_ZHUANREN_AGREE", STATUS_ZHUANREN_AGREE);
		power.put("STATUS_ZHUANREN_PRINT", STATUS_ZHUANREN_PRINT);
		
		//外区转入流程
		power.put("STATUS_ZHUANREN_OUTER_STATE", STATUS_ZHUANREN_STATE);
		power.put("STATUS_ZHUANREN_OUTER_TRIAL", STATUS_ZHUANREN_TRIAL);
		power.put("STATUS_ZHUANREN_OUTER_TRIAL_1", STATUS_ZHUANREN_TRIAL_1);
		power.put("STATUS_ZHUANREN_OUTER_TRIAL_2", STATUS_ZHUANREN_TRIAL_2);
		power.put("STATUS_ZHUANREN_OUTER_TRIAL_3", STATUS_ZHUANREN_TRIAL_3);
		power.put("STATUS_ZHUANREN_OUTER_TRIAL_4", STATUS_ZHUANREN_TRIAL_4);
		power.put("STATUS_ZHUANREN_OUTER_PRINT", STATUS_ZHUANREN_PRINT);
	}
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
	}
	
	public long getAdminAuthNum() {
		
		return adminAuthNum;
	}
	
	public void setAdminAuthNum(long adminAuthNum) {
		
		this.adminAuthNum = adminAuthNum;
	}
	
	public long getInFactNum() {
		
		return inFactNum;
	}
	
	public void setInFactNum(long inFactNum) {
		
		this.inFactNum = inFactNum;
	}
	
	public long getVacancyNum() {
		
		return vacancyNum;
	}
	
	public void setVacancyNum(long vacancyNum) {
		
		this.vacancyNum = vacancyNum;
	}
	
	public long getPostNum() {
		
		return postNum;
	}
	
	public void setPostNum(long postNum) {
		
		this.postNum = postNum;
	}
	
	public long getPostInFactNum() {
		
		return postInFactNum;
	}
	
	public void setPostInFactNum(long postInFactNum) {
		
		this.postInFactNum = postInFactNum;
	}
	
	public long getPostVacancyNum() {
		
		return postVacancyNum;
	}
	
	public void setPostVacancyNum(long postVacancyNum) {
		
		this.postVacancyNum = postVacancyNum;
	}
	
	public CodeInfo getPostCode() {
		
		return postCode;
	}
	
	public void setPostCode(CodeInfo postCode) {
		
		this.postCode = postCode;
	}
	
	public String getPostName() {
		
		return postName;
	}
	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}
	
	public CodeInfo getAttribute() {
		
		return attribute;
	}
	
	public void setAttribute(CodeInfo attribute) {
		
		this.attribute = attribute;
	}
	
	public CodeInfo getCauseType() {
		
		return causeType;
	}
	
	public void setCauseType(CodeInfo causeType) {
		
		this.causeType = causeType;
	}
	
	public String getSpecificCause() {
		
		return specificCause;
	}
	
	public void setSpecificCause(String specificCause) {
		
		this.specificCause = specificCause;
	}
	
	public OrganNode getTargetOrgan() {
		
		return TargetOrgan;
	}
	
	public void setTargetOrgan(OrganNode targetOrgan) {
		
		TargetOrgan = targetOrgan;
	}
	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public String getAreaType() {
		
		return areaType;
	}
	
	public void setAreaType(String areaType) {
		
		this.areaType = areaType;
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
	
	public ZhuanRenTLBOutMgr getZhuanRenTLBOutMgr() {
		
		return zhuanRenTLBOutMgr;
	}
	
	public void setZhuanRenTLBOutMgr(ZhuanRenTLBOutMgr zhuanRenTLBOutMgr) {
		
		this.zhuanRenTLBOutMgr = zhuanRenTLBOutMgr;
	}
	
}
