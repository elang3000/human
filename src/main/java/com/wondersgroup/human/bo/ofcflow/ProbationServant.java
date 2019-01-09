/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProbationServant.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 公务员试用期
 * 创建人: tzy
 * 创建时间: 2018年7月25日 下午1:54:13
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月25日 下午1:54:13
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: ProbationServant
 * @Description: 公务员试用期
 * @author: tzy
 * @date: 2018年7月25日 下午1:54:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_PROBATION_SERVANT")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProbationServant extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description:
	 */
	private static final long serialVersionUID = 227941215524177823L;
	
	/**
	 * 是否发送消息/取消标识
	 * 未发送/未取消/不是延期
	 */
	public static final String UN_SEND = "0";
	
	/**
	 * 是否发送消息/取消标识
	 * 已发送/已取消/是延期
	 */
	public static final String SEND_ED = "1";
	
	/**
	 * 待提交试用期信息
	 */
	public static final int STATUS_EMPLOY_STATE = 0;
	
	/**
	 * 待上级单位审核
	 */
	public static final int STATUS_EMPLOY_TRIAL = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_EMPLOY_TRIAL_1 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_EMPLOY_TRIAL_2 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_EMPLOY_TRIAL_3 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_EMPLOY_TRIAL_4 = 5;
	
	/**
	 * 区人事主管部门审核通过
	 */
	public static final int STATUS_EMPLOY_TRIAL_PASS = 6;
	
	/**
	 * 取消录用，区人事主管部门备案确认
	 */
	public static final int STATUS_EMPLOY_TRIAL_CONFIRM = 7;
	
	/**
	 * 取消录用，区人事主管部门已备案确认
	 */
	public static final int STATUS_EMPLOY_TRIAL_CONFIRM_DONE = 8;
	
	/**
	 * @fieldName: isLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 职级属性 是否领导，用于区分正科级和副科级。CommonConst.YES/CommonConst.NO
	 */
	@Column(name = "IS_LEADER", length = 1)
	private Integer isLeader;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 公务员基本信息表，人员录用进来在信息维护中显示，在职状态为试用期
	 */
	@OneToOne
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: draftServant
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DraftServant
	 * @Description: 公务员录用表
	 */
	@OneToOne
	@JoinColumn(name = "DRAFT_SERVANT_ID")
	private DraftServant draftServant;
	
	/**
	 * @fieldName: probationStartDate
	 * @fieldType: java.util.Date
	 * @Description: 试用期开始时间
	 */
	@Column(name = "PROBATION_STARTDATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date probationStartDate;
	
	/**
	 * @fieldName: probationEndDate
	 * @fieldType: java.util.Date
	 * @Description: 试用期结束时间
	 */
	@Column(name = "PROBATION_ENDDATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date probationEndDate;
	
	/**
	 * @fieldName: probationDate
	 * @fieldType: Integer
	 * @Description: 试用时长 月份
	 */
	@Column(name = "ALLOW_WEAVE_NUM")
	private Integer probationDate;
	
	/**
	 * @fieldName: probationStatus
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 试用期考核状态
	 */
	@ManyToOne
	@JoinColumn(name = "PROBATION_STATUS")
	private CodeInfo probationStatus;
	
	/**
	 * @fieldName: cancelReason
	 * @fieldType: java.lang.String
	 * @Description: 取消原因。
	 */
	@Column(name = "CANCEL_REASON")
	private String cancelReason;
	
	/**
	 * @fieldName: cancelLetterPath
	 * @fieldType: java.lang.String
	 * @Description: 关于取消XX同志公务员录用的请示 文件路径。
	 */
	@Column(name = "CANCEL_LETTER_PATH")
	private String cancelLetterPath;
	
	/**
	 * @fieldName: cancelWrittenPath
	 * @fieldType: java.lang.String
	 * @Description: 考生试用期内取消录用的书面申请。
	 */
	@Column(name = "CANCEL_WRITTEN_PATH")
	private String cancelWrittenPath;
	
	/**
	 * @fieldName: cancelRecordsPath
	 * @fieldType: java.lang.String
	 * @Description: 新录用公务员取消录用审批备案表。
	 */
	@Column(name = "CANCEL_RECORDS_PATH")
	private String cancelRecordsPath;
	
	/**
	 * @fieldName: cancelFlag
	 * @fieldType: java.lang.String
	 * @Description: 取消标识。
	 */
	@Column(name = "CANCEL_FLAG", length = 1)
	private String cancelFlag;
	
	/**
	 * @fieldName: reply
	 * @fieldType: java.lang.String
	 * @Description: 审核批复。
	 */
	@Column(name = "REPLY")
	private String reply;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	/**
	 * @fieldName: 试用期满考核结论
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 试用结束时，用人单位对被试用人在试用期间工作情况考核结果。DM018
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A04020")
	private CodeInfo checkResult;
	
	/**
	 * @fieldName: formalDate
	 * @fieldType: java.util.Date
	 * @Description: 入职转正日期
	 */
	@Column(name = "A04025")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date formalDate;
	
	/**
	 * @fieldName: formalNumber
	 * @fieldType: java.lang.String
	 * @Description: 公务员登记号
	 */
	@Column(name = "A04027")
	private String formalNumber;
	
	/**
	 * @fieldName: issend
	 * @fieldType: java.lang.String
	 * @Description: 是否向相关单位发送消息
	 */
	@Column(name = "issend", length = 1)
	private String issend;
	
	/**
	 * @fieldName: delayDate
	 * @fieldType: java.lang.Integer
	 * @Description: 延期时长
	 */
	@Column(name = "DELAYDATE")
	private Integer delayDate;
	
	/**
	 * @fieldName: delayReason
	 * @fieldType: java.lang.String
	 * @Description: 延期理由
	 */
	@Column(name = "DELAYREASON", length = 400)
	private String delayReason;
	
	/**
	 * @fieldName: delayReasonDone
	 * @fieldType: java.lang.Integer
	 * @Description: 延期时长 流程结束填入
	 */
	@Column(name = "DELAYDATE_DONE")
	private Integer delayDateDone;
	
	/**
	 * @fieldName: delayReasonDone
	 * @fieldType: java.lang.String
	 * @Description: 延期理由 流程结束填入
	 */
	@Column(name = "DELAYREASON_DONE", length = 400)
	private String delayReasonDone;
	
	/**
	 * @fieldName: isDelay
	 * @fieldType: java.lang.String
	 * @Description: 是否延期 1：是 0：否
	 */
	@Column(name = "IS_DELAY", length = 1)
	private String isDelay;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		power.put("STATUS_EMPLOY_STATE", STATUS_EMPLOY_STATE);
		power.put("STATUS_EMPLOY_TRIAL", STATUS_EMPLOY_TRIAL);
		power.put("STATUS_EMPLOY_TRIAL_1", STATUS_EMPLOY_TRIAL_1);
		power.put("STATUS_EMPLOY_TRIAL_2", STATUS_EMPLOY_TRIAL_2);
		power.put("STATUS_EMPLOY_TRIAL_3", STATUS_EMPLOY_TRIAL_3);
		power.put("STATUS_EMPLOY_TRIAL_4", STATUS_EMPLOY_TRIAL_4);
		power.put("STATUS_EMPLOY_TRIAL_PASS", STATUS_EMPLOY_TRIAL_PASS);
		// 取消录用
		power.put("STATUS_EMPLOY_STATE_2", STATUS_EMPLOY_STATE);
		power.put("STATUS_EMPLOY_TRIAL_CONFIRM", STATUS_EMPLOY_TRIAL_CONFIRM);
	}
	
	public DraftServant getDraftServant() {
		
		return draftServant;
	}
	
	public void setDraftServant(DraftServant draftServant) {
		
		this.draftServant = draftServant;
	}
	
	public Date getProbationStartDate() {
		
		return probationStartDate;
	}
	
	public void setProbationStartDate(Date probationStartDate) {
		
		this.probationStartDate = probationStartDate;
	}
	
	public Date getProbationEndDate() {
		
		return probationEndDate;
	}
	
	public void setProbationEndDate(Date probationEndDate) {
		
		this.probationEndDate = probationEndDate;
	}
	
	public CodeInfo getProbationStatus() {
		
		return probationStatus;
	}
	
	public void setProbationStatus(CodeInfo probationStatus) {
		
		this.probationStatus = probationStatus;
	}
	
	public String getCancelReason() {
		
		return cancelReason;
	}
	
	public void setCancelReason(String cancelReason) {
		
		this.cancelReason = cancelReason;
	}
	
	public String getCancelLetterPath() {
		
		return cancelLetterPath;
	}
	
	public void setCancelLetterPath(String cancelLetterPath) {
		
		this.cancelLetterPath = cancelLetterPath;
	}
	
	public String getCancelRecordsPath() {
		
		return cancelRecordsPath;
	}
	
	public void setCancelRecordsPath(String cancelRecordsPath) {
		
		this.cancelRecordsPath = cancelRecordsPath;
	}
	
	public String getReply() {
		
		return reply;
	}
	
	public void setReply(String reply) {
		
		this.reply = reply;
	}
	
	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}
	
	public Date getFormalDate() {
		
		return formalDate;
	}
	
	public void setFormalDate(Date formalDate) {
		
		this.formalDate = formalDate;
	}
	
	public String getFormalNumber() {
		
		return formalNumber;
	}
	
	public void setFormalNumber(String formalNumber) {
		
		this.formalNumber = formalNumber;
	}
	
	public String getIssend() {
		
		return issend;
	}
	
	public void setIssend(String issend) {
		
		this.issend = issend;
	}
	
	public CodeInfo getCheckResult() {
		
		return checkResult;
	}
	
	public void setCheckResult(CodeInfo checkResult) {
		
		this.checkResult = checkResult;
	}
	
	public String getCancelFlag() {
		
		return cancelFlag;
	}
	
	public void setCancelFlag(String cancelFlag) {
		
		this.cancelFlag = cancelFlag;
	}
	
	public String getDelayReason() {
		
		return delayReason;
	}
	
	public void setDelayReason(String delayReason) {
		
		this.delayReason = delayReason;
	}
	
	public Integer getProbationDate() {
		
		return probationDate;
	}
	
	public void setProbationDate(Integer probationDate) {
		
		this.probationDate = probationDate;
	}
	
	public String getCancelWrittenPath() {
		
		return cancelWrittenPath;
	}
	
	public void setCancelWrittenPath(String cancelWrittenPath) {
		
		this.cancelWrittenPath = cancelWrittenPath;
	}
	
	public Integer getDelayDate() {
		
		return delayDate;
	}
	
	public void setDelayDate(Integer delayDate) {
		
		this.delayDate = delayDate;
	}
	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}
	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}
	
	public Integer getDelayDateDone() {
		
		return delayDateDone;
	}
	
	public void setDelayDateDone(Integer delayDateDone) {
		
		this.delayDateDone = delayDateDone;
	}
	
	public String getDelayReasonDone() {
		
		return delayReasonDone;
	}
	
	public void setDelayReasonDone(String delayReasonDone) {
		
		this.delayReasonDone = delayReasonDone;
	}
	
	public String getIsDelay() {
		
		return isDelay;
	}
	
	public void setIsDelay(String isDelay) {
		
		this.isDelay = isDelay;
	}
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}
	
	public Integer getIsLeader() {
		
		return isLeader;
	}
	
	public void setIsLeader(Integer isLeader) {
		
		this.isLeader = isLeader;
	}
	
}
