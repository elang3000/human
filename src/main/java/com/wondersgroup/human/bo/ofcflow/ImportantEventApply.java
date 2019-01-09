/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ImportantEventApply.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 重大事项申请
 * 创建人: jiang
 * 创建时间: 2018年12月14日11:10:00
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月14日11:10:05
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: ImportantEventApply
 * @Description: 重大事项申请
 * @author: jiang
 * @date: 2018年12月14日11:10:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_IMPORTANT_EVENT_APPLY")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImportantEventApply extends GenericEntity {
	
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 5514622210184945076L;
	

	/**
	 * @fieldName: eventTitle
	 * @fieldType: java.lang.String
	 * @Description: 事项标题。
	 */
	@Column(name = "EVENT_TITLE", length = 120)
	private String eventTitle;
	
	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 事项说明。
	 */
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;
	
	/**
	 * @fieldName: eventType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事项类型。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "EVENT_TYPE")
	private CodeInfo eventType;
	
	/**
	 * @fieldName: applyUser
	 * @fieldType: com.wondersgroup.framework.security.bo.SecurityUser
	 * @Description: 申请人。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLY_USER_ID")
	private SecurityUser applyUser;
	
	/**
	 * @fieldName: applyOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 申请单位。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLY_ORG_ID")
	private OrganNode applyOrgan;
	
	/**
	 * @fieldName: applyUser
	 * @fieldType: com.wondersgroup.framework.security.bo.SecurityUser
	 * @Description: 事项经办人。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EVENT_OPERATOR_ID")
	private Servant eventOperator;
	
	/**
	 * @fieldName: filePath
	 * @fieldType: java.lang.String
	 * @Description: 文件路径。
	 */
	@Column(name = "ATTACHMENT_PATH", length = 2000)
	private String filePath;
	
	/**
	 * 待提交申请
	 */
	public static final int STATUS_IMPORTANT_EVENT_STATE = 0;
	
	/**
	 * 待上级单位审核
	 */
	public static final int STATUS_IMPORTANT_EVENT_TRIAL_1 = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_IMPORTANT_EVENT_TRIAL_2 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_IMPORTANT_EVENT_TRIAL_3 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_IMPORTANT_EVENT_TRIAL_4 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_IMPORTANT_EVENT_TRIAL_5 = 5;
	
	/**
	 * 审核通过，指定办理人员
	 */
	public static final int STATUS_IMPORTANT_EVENT_TRIAL_6 = 6;
	
	/**
	 * 审核通过
	 */
	public static final int STATUS_IMPORTANT_EVENT_TRIAL_7 = 7;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		power.put("STATUS_IMPORTANT_EVENT_STATE", STATUS_IMPORTANT_EVENT_STATE);
		power.put("STATUS_IMPORTANT_EVENT_TRIAL_1", STATUS_IMPORTANT_EVENT_TRIAL_1);
		power.put("STATUS_IMPORTANT_EVENT_TRIAL_2", STATUS_IMPORTANT_EVENT_TRIAL_2);
		power.put("STATUS_IMPORTANT_EVENT_TRIAL_3", STATUS_IMPORTANT_EVENT_TRIAL_3);
		power.put("STATUS_IMPORTANT_EVENT_TRIAL_4", STATUS_IMPORTANT_EVENT_TRIAL_4);
		power.put("STATUS_IMPORTANT_EVENT_TRIAL_5", STATUS_IMPORTANT_EVENT_TRIAL_5);
		power.put("STATUS_IMPORTANT_EVENT_TRIAL_6", STATUS_IMPORTANT_EVENT_TRIAL_6);
	}
	
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

	
	public String getEventTitle() {
		
		return eventTitle;
	}

	
	public void setEventTitle(String eventTitle) {
		
		this.eventTitle = eventTitle;
	}

	
	public String getDescription() {
		
		return description;
	}

	
	public void setDescription(String description) {
		
		this.description = description;
	}

	
	public CodeInfo getEventType() {
		
		return eventType;
	}

	
	public void setEventType(CodeInfo eventType) {
		
		this.eventType = eventType;
	}

	
	public SecurityUser getApplyUser() {
		
		return applyUser;
	}

	
	public void setApplyUser(SecurityUser applyUser) {
		
		this.applyUser = applyUser;
	}

	
	public OrganNode getApplyOrgan() {
		
		return applyOrgan;
	}

	
	public void setApplyOrgan(OrganNode applyOrgan) {
		
		this.applyOrgan = applyOrgan;
	}

	
	public Servant getEventOperator() {
		
		return eventOperator;
	}

	
	public void setEventOperator(Servant eventOperator) {
		
		this.eventOperator = eventOperator;
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
	
}
