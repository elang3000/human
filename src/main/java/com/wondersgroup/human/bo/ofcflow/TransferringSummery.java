/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: TransferringexchangesSummery.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 选调交流汇总信息表
 * 创建人: tzy
 * 创建时间: 2018年8月16日 上午9:58:19
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月16日 上午9:58:19
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;

/**
 * @ClassName: TransferringexchangesSummery
 * @Description: 选调交流汇总信息表
 * @author: tzy
 * @date: 2018年8月16日 上午9:58:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_XUANDIAO_SUMMERY")
public class TransferringSummery extends GenericEntity {
	
	private static final long serialVersionUID = -6009329577255239499L;
	
	// 待提交
	@Transient
	public final static int RECRUIT_EMPLOY_PLAN_STATE_POST = 0;
	
	// 待审批
	@Transient
	public final static int RECRUIT_EMPLOY_PLAN_STATE_DSP = 1;
	
	// 审批通过
	@Transient
	public final static int RECRUIT_EMPLOY_PLAN_STATE_PASSED = 2;
	
	// 审批不通过
	@Transient
	public final static int RECRUIT_EMPLOY_PLAN_STATE_REFUSE = 3;
	
	// 待人员导入
	@Transient
	public final static int RECRUIT_EMPLOY_PLAN_STATE_DR = 9;
	
	// 待人员上报
	@Transient
	public final static int RECRUIT_EMPLOY_PLAN_STATE_SB = 4;
	
	// 待人员审批
	@Transient
	public final static int RECRUIT_EMPLOY_PLAN_STATE_SP = 5;
	
	// 人员审批通过
	@Transient
	public final static int RECRUIT_EMP_PLAN_STATE_PASSED = 6;
	
	// 人员审批不通过
	@Transient
	public final static int RECRUIT_EMP_PLAN_STATE_REFUSE = 7;
	
	// 归档
	@Transient
	public final static int RECRUIT_EMPLOY_PLAN_STATE_ARCHIVE = 8;
	
	/**
	 * @fieldName: employOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 选调机关
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECRUITORGAN_ID")
	private OrganNode recruitOrgan;
	
	/**
	 * @fieldName: allowWeaveNum
	 * @fieldType: java.lang.int
	 * @Description: 核定编制数
	 */
	@Column(name = "ALLOW_WEAVE_NUM")
	private int allowWeaveNum;
	
	/**
	 * @fieldName: realNum
	 * @fieldType: java.lang.int
	 * @Description: 实有人数
	 */
	@Column(name = "REAL_NUM")
	private int realNum;
	
	/**
	 * @fieldName: thisYearLackWeaveNum
	 * @fieldType: java.lang.int
	 * @Description: 本年度缺编人员
	 */
	@Column(name = "THIS_YEAR_LACK_WEAVE_NUM")
	private int thisYearLackWeaveNum;
	
	/**
	 * @fieldName: planCutNum
	 * @fieldType: java.lang.int
	 * @Description: 计划减员人数
	 */
	@Column(name = "PLAN_CUT_NUM")
	private int planCutNum;
	
	/**
	 * @fieldName: planEmployNum
	 * @fieldType: java.lang.int
	 * @Description: 计划选调人数
	 */
	@Column(name = "PLAN_EMPLOY_NUM")
	private int planEmployNum;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.int
	 * @Description: 状态
	 */
	@Column(name = "STATUS")
	private int status = RECRUIT_EMPLOY_PLAN_STATE_POST;
	
	/**
	 * @fieldName: recuritType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 编制类型
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECURITTYPE_ID")
	private CodeInfo recuritType;
	
	
	public OrganNode getRecruitOrgan() {
		
		return recruitOrgan;
	}
	
	public void setRecruitOrgan(OrganNode recruitOrgan) {
		
		this.recruitOrgan = recruitOrgan;
	}
	
	public int getAllowWeaveNum() {
		
		return allowWeaveNum;
	}
	
	public void setAllowWeaveNum(int allowWeaveNum) {
		
		this.allowWeaveNum = allowWeaveNum;
	}
	
	public int getRealNum() {
		
		return realNum;
	}
	
	public void setRealNum(int realNum) {
		
		this.realNum = realNum;
	}
	
	public int getThisYearLackWeaveNum() {
		
		return thisYearLackWeaveNum;
	}
	
	public void setThisYearLackWeaveNum(int thisYearLackWeaveNum) {
		
		this.thisYearLackWeaveNum = thisYearLackWeaveNum;
	}
	
	public int getPlanCutNum() {
		
		return planCutNum;
	}
	
	public void setPlanCutNum(int planCutNum) {
		
		this.planCutNum = planCutNum;
	}
	
	public int getPlanEmployNum() {
		
		return planEmployNum;
	}
	
	public void setPlanEmployNum(int planEmployNum) {
		
		this.planEmployNum = planEmployNum;
	}
	
	public int getStatus() {
		
		return status;
	}
	
	public void setStatus(int status) {
		
		this.status = status;
	}
	
	public CodeInfo getRecuritType() {
		
		return recuritType;
	}
	
	public void setRecuritType(CodeInfo recuritType) {
		
		this.recuritType = recuritType;
	}
	
}
