/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeOutBatch.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年12月20日 下午4:42:41 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年12月20日 下午4:42:41 
 * 修改任务号
 * 修改内容：TODO
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

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/** 
 * @ClassName: ReferenceExchangeOutBatch 
 * @Description: TODO	参公交流转出
 * @author: GP
 * @date: 2018年12月20日 下午4:42:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_REFERENCE_EXCHANGE_BATCH_OUT")
public class ReferenceExchangeOutBatch extends GenericEntity{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 6498094420332694297L;

	/**
	 * @fieldName: SourceOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 原单位信息
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
	 * 待发起转出流程
	 */
	public static final int STATUS_EXCHANGE_OUT_STATE = 0;
	
	/**
	 * 待转出单位确认
	 */
	public static final int STATUS_EXCHANGE_OUT_TRIAL = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_EXCHANGE_OUT_TRIAL_1 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_EXCHANGE_OUT_TRIAL_2 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_EXCHANGE_OUT_TRIAL_3 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_EXCHANGE_OUT_TRIAL_4 = 5;
	
	/**
	 * 待区人事主管部门打印介绍信
	 */
	public static final int STATUS_EXCHANGE_OUT_TRIAL_5 = 6;
	
	/**
	 * 已完成人员转出
	 */
	public static final int STATUS_EXCHANGE_OUT_FINISH = 7;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		power.put("STATUS_EXCHANGE_OUT_STATE", STATUS_EXCHANGE_OUT_STATE);
		power.put("STATUS_EXCHANGE_OUT_TRIAL", STATUS_EXCHANGE_OUT_TRIAL);
		power.put("STATUS_EXCHANGE_OUT_TRIAL_1", STATUS_EXCHANGE_OUT_TRIAL_1);
		power.put("STATUS_EXCHANGE_OUT_TRIAL_2", STATUS_EXCHANGE_OUT_TRIAL_2);
		power.put("STATUS_EXCHANGE_OUT_TRIAL_3", STATUS_EXCHANGE_OUT_TRIAL_3);
		power.put("STATUS_EXCHANGE_OUT_TRIAL_4", STATUS_EXCHANGE_OUT_TRIAL_4);
		power.put("STATUS_EXCHANGE_OUT_TRIAL_5", STATUS_EXCHANGE_OUT_TRIAL_5);
	}

	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}

	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
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
}
