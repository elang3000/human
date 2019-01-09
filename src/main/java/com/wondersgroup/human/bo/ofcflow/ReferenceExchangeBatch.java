/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeBatch.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年12月17日 上午10:52:23 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年12月17日 上午10:52:23 
 * 修改任务号
 * 修改内容：TODO
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
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/** 
 * @ClassName: ReferenceExchangeBatch 
 * @Description: 参公交流（导入）批量
 * @author: GP
 * @date: 2018年12月17日 上午10:52:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_REFERENCE_EXCHANGE_BATCH")
public class ReferenceExchangeBatch extends BaseFlowOrgFormation<ReferenceExchangeBatch>{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 7639523171205558543L;
	
	/**
	 * 本区
	 */
	public static final String AREA_THIS = "1";
	
	/**
	 * 外区
	 */
	public static final String AREA_OUTER = "2";
	
	/**
	 * @fieldName: areaType
	 * @fieldType: java.lang.String
	 * @Description: 参公交流地区：1-本区 2-外区。
	 */
	@Column(name = "AREATYPE")
	private String areaType;
	
	// 批次人数
		/**
		 * @fieldName: sumNumber
		 * @fieldType: java.lang.Integer
		 * @Description: 转任总人数。
		 */
		@Column(name = "SUMNUMBER", length = 4)
		private Integer sumNumber;
		
		/**
		 * @fieldName: plusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description:正科级（领导）转任人数。
		 */
		@Column(name = "PLUSKE_LEADER_NUM", length = 4)
		private Integer plusKeLeaderNum;
		
		/**
		 * @fieldName: plusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description:正科级（非领导）转任人数。
		 */
		@Column(name = "PLUSKE_NOLEADER_NUM", length = 4)
		private Integer plusKeNoLeaderNum;
		
		/**
		 * @fieldName: minusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description:副科级（领导）转任人数。
		 */
		@Column(name = "MINUSKE_LEADER_NUM", length = 4)
		private Integer minusKeLeaderNum;
		
		/**
		 * @fieldName: minusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description:副科级（非领导）转任人数。
		 */
		@Column(name = "MINUSKE_NOLEADER_NUM", length = 4)
		private Integer minusKeNoLeaderNum;
		
		/**
		 * @fieldName: clerkNumber
		 * @fieldType: java.lang.Integer
		 * @Description:科员转任人数。
		 */
		@Column(name = "CLERK_NUMBER", length = 4)
		private Integer clerkNumber;
		
		/**
		 * @fieldName: cClerkNumber
		 * @fieldType: java.lang.Integer
		 * @Description:办事员转任人数。
		 */
		@Column(name = "C_CLERK_NUMBER", length = 4)
		private Integer cClerkNumber;
	
	/**
	 * 交流人员目标单位（流程发起单位）
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGETORGAN_ID")
	private OrganNode targetOrgan;
	
	
	/**
	 * 交流人员来源单位
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOURCEORGAN_ID")
	private OrganNode sourceOrgan;
	
	/**
	 * @fieldName: filePath
	 * @fieldType: java.lang.String
	 * @Description: 附件FTP路径。
	 */
	@Column(name = "FILE_PATH", length = 2000)
	private String filePath;
	
	@Column(name = "APPLY_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date applyDate;
	
	/**
	 * @fieldName: 报道日期
	 * @fieldType: java.util.Date
	 * @Description: 由本单位人事管理部门认定的该人进入本单位工作的日期。GB/T 7408-2005
	 */
	@Column(name = "ENTERTHE_SYSTEM_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enterTheUnitDate;
	
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

	
	public String getAreaType() {
		
		return areaType;
	}

	
	public void setAreaType(String areaType) {
		
		this.areaType = areaType;
	}

	

	
	public String getFilePath() {
		
		return filePath;
	}

	
	public void setFilePath(String filePath) {
		
		this.filePath = filePath;
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


	
	public Date getApplyDate() {
		
		return applyDate;
	}


	
	public void setApplyDate(Date applyDate) {
		
		this.applyDate = applyDate;
	}


	
	public Integer getSumNumber() {
		
		return sumNumber;
	}


	
	public void setSumNumber(Integer sumNumber) {
		
		this.sumNumber = sumNumber;
	}

	public Integer getPlusKeLeaderNum() {
		
		return plusKeLeaderNum;
	}


	
	public void setPlusKeLeaderNum(Integer plusKeLeaderNum) {
		
		this.plusKeLeaderNum = plusKeLeaderNum;
	}


	
	public Integer getPlusKeNoLeaderNum() {
		
		return plusKeNoLeaderNum;
	}


	
	public void setPlusKeNoLeaderNum(Integer plusKeNoLeaderNum) {
		
		this.plusKeNoLeaderNum = plusKeNoLeaderNum;
	}


	
	public Integer getMinusKeLeaderNum() {
		
		return minusKeLeaderNum;
	}


	
	public void setMinusKeLeaderNum(Integer minusKeLeaderNum) {
		
		this.minusKeLeaderNum = minusKeLeaderNum;
	}


	
	public Integer getMinusKeNoLeaderNum() {
		
		return minusKeNoLeaderNum;
	}


	
	public void setMinusKeNoLeaderNum(Integer minusKeNoLeaderNum) {
		
		this.minusKeNoLeaderNum = minusKeNoLeaderNum;
	}


	public Integer getClerkNumber() {
		
		return clerkNumber;
	}


	
	public void setClerkNumber(Integer clerkNumber) {
		
		this.clerkNumber = clerkNumber;
	}


	
	public Integer getcClerkNumber() {
		
		return cClerkNumber;
	}


	
	public void setcClerkNumber(Integer cClerkNumber) {
		
		this.cClerkNumber = cClerkNumber;
	}


	public OrganNode getTargetOrgan() {
		
		return targetOrgan;
	}


	
	public void setTargetOrgan(OrganNode targetOrgan) {
		
		this.targetOrgan = targetOrgan;
	}


	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}


	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}


	
	public Date getEnterTheUnitDate() {
		
		return enterTheUnitDate;
	}


	
	public void setEnterTheUnitDate(Date enterTheUnitDate) {
		
		this.enterTheUnitDate = enterTheUnitDate;
	}
	
	
}
