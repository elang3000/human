/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Transferringexchanges.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 单位招聘详情
 * 创建人: tzy
 * 创建时间: 2018年8月10日 上午11:01:10
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月10日 上午11:01:10
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;

/**
 * @ClassName: TransferringExchanges
 * @Description: 选调交流：单位招聘详情
 * @author: tzy
 * @date: 2018年8月10日 上午11:01:10
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_XUANDIAO")
public class TransferringExchanges extends GenericEntity {
	
	private static final long serialVersionUID = -3592299463582640693L;
	
	public final static int RECRUIT_EMPLOY_PLAN_LEVEL_CITY = 0;
	
	public final static int RECRUIT_EMPLOY_PLAN_LEVEL_AREA = 1;
	
	// 待上报职务
	public final static int RECRUIT_EMPLOY_PLAN_STATE_POST = 0;
	
	// 待上报
	public final static int RECRUIT_EMPLOY_PLAN_STATE_REPORT = 1;
	
	// 待上报审批
	public final static int RECRUIT_EMPLOY_PLAN_STATE_APPROVAL = 2;
	
	// 审批通过
	public final static int RECRUIT_EMPLOY_PLAN_STATE_PASSED = 3;
	
	// 审批不通过
	public final static int RECRUIT_EMPLOY_PLAN_STATE_REFUSE = 4;
	
	// 审批归档
	public final static int RECRUIT_EMPLOY_PLAN_STATE_ARCHIVE = 5;
	
	// 待汇总
	public final static int RECRUIT_EMPLOY_PLAN_STATE_SUMMARY = 6;
	
	// 待修改
	public final static int RECRUIT_EMPLOY_PLAN_STATE_MODIFY = 7;
	
	// 待审批
	public final static int RECRUIT_EMPLOY_PLAN_STATE_SBAPPROVAL = 8;
	
	/**
	 * @fieldName: employOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 选调机关
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECRUITORGAN_ID")
	private OrganNode recruitOrgan;
	
	/**
	 * @fieldName: employOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 用人机关
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYORGAN_ID")
	private OrganNode employOrgan;
	
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
	@OneToOne
	private CodeInfo recuritType;
	
	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 备注。
	 */
	@Column(name = "REMARK", length = 255)
	private String remark;
	
	/**
	 * @fieldName: reback
	 * @fieldType: java.lang.String
	 * @Description: 退回备注。
	 */
	@Column(name = "REBACK", length = 255)
	private String reback;
	
	/**
	 * @fieldName: contacter
	 * @fieldType: java.lang.String
	 * @Description: 联系人。
	 */
	@Column(name = "CONTACTER", length = 40)
	private String contacter;
	
	/**
	 * @fieldName: contactPhone
	 * @fieldType: java.lang.String
	 * @Description: 联系电话。
	 */
	@Column(name = "CONTACT_PHONE", length = 20)
	private String contactPhone;
	
	/**
	 * @fieldName: contactAddress
	 * @fieldType: java.lang.String
	 * @Description: 通讯地址。
	 */
	@Column(name = "CONTACT_ADDRESS", length = 255)
	private String contactAddress;
	
	/**
	 * @fieldName: consultPhone
	 * @fieldType: java.lang.String
	 * @Description: 咨询电话。
	 */
	@Column(name = "CONSULT_PHONE", length = 20)
	private String consultPhone;
	
	/**
	 * @fieldName: info
	 * @fieldType: java.util.Set<TransferringexchangesInfo>
	 * @Description: 计划选调人数信息。
	 * @OneToMany(cascade = {
	 *                    CascadeType.ALL
	 *                    }, fetch = FetchType.LAZY)
	 * @JoinColumn(name = "PLAN_ID")
	 *                  private Set<TransferringExchangesInfo> info = new
	 *                  HashSet<TransferringExchangesInfo>();
	 */
	
	/**
	 * @fieldName: post
	 * @fieldType: java.util.Set<TransferringexchangesPost>
	 * @Description: 计划选调职位信息。
	 */
	@OneToMany(cascade = {
	        CascadeType.ALL
	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID")
	private Set<TransferringExchangesPost> post = new HashSet<TransferringExchangesPost>();
	
	public OrganNode getEmployOrgan() {
		
		return employOrgan;
	}
	
	public void setEmployOrgan(OrganNode employOrgan) {
		
		this.employOrgan = employOrgan;
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
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public String getReback() {
		
		return reback;
	}
	
	public void setReback(String reback) {
		
		this.reback = reback;
	}
	
	public String getContacter() {
		
		return contacter;
	}
	
	public void setContacter(String contacter) {
		
		this.contacter = contacter;
	}
	
	public String getContactPhone() {
		
		return contactPhone;
	}
	
	public void setContactPhone(String contactPhone) {
		
		this.contactPhone = contactPhone;
	}
	
	public String getContactAddress() {
		
		return contactAddress;
	}
	
	public void setContactAddress(String contactAddress) {
		
		this.contactAddress = contactAddress;
	}
	
	public String getConsultPhone() {
		
		return consultPhone;
	}
	
	public void setConsultPhone(String consultPhone) {
		
		this.consultPhone = consultPhone;
	}
	
	public Set<TransferringExchangesPost> getPost() {
		
		return post;
	}
	
	public void setPost(Set<TransferringExchangesPost> post) {
		
		this.post = post;
	}
	
	public OrganNode getRecruitOrgan() {
		
		return recruitOrgan;
	}
	
	public void setRecruitOrgan(OrganNode recruitOrgan) {
		
		this.recruitOrgan = recruitOrgan;
	}
	
}
