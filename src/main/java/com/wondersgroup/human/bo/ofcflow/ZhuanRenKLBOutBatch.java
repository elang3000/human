/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenKLBOutBatch.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 跨类别转任 批量转出数据表
 * 创建人: tzy
 * 创建时间: 2018年12月18日 上午9:57:17
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年12月18日 上午9:57:17
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
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/**
 * @ClassName: ZhuanRenKLBOutBatch
 * @Description: 跨类别转任 批量转出数据表
 * @author: tzy
 * @date: 2018年12月18日 上午9:57:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ZHUANREN_KLB_BATCH_OUT")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZhuanRenKLBOutBatch extends GenericEntity {
	
	private static final long serialVersionUID = -2312841411066771720L;
	
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
	
	/* 本区转任流程 */
	/**
	 * 待发起转出流程
	 */
	public static final int STATUS_ZHUANCHU_STATE = 0;
	
	/**
	 * 待转出单位确认
	 */
	public static final int STATUS_ZHUANCHU_TRIAL = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_ZHUANCHU_TRIAL_1 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_ZHUANCHU_TRIAL_2 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_ZHUANCHU_TRIAL_3 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_ZHUANCHU_TRIAL_4 = 5;
	
	/**
	 * 待区人事主管部门打印电子介绍信
	 */
	public static final int STATUS_ZHUANCHU_PRINT = 6;
	
	/**
	 * 已完成人员转出
	 */
	public static final int STATUS_ZHUANCHU_FINISH = 7;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		power.put("STATUS_ZHUANCHU_KLB_STATE", STATUS_ZHUANCHU_STATE);
		power.put("STATUS_ZHUANCHU_KLB_TRIAL", STATUS_ZHUANCHU_TRIAL);
		power.put("STATUS_ZHUANCHU_KLB_TRIAL_1", STATUS_ZHUANCHU_TRIAL_1);
		power.put("STATUS_ZHUANCHU_KLB_TRIAL_2", STATUS_ZHUANCHU_TRIAL_2);
		power.put("STATUS_ZHUANCHU_KLB_TRIAL_3", STATUS_ZHUANCHU_TRIAL_3);
		power.put("STATUS_ZHUANCHU_KLB_TRIAL_4", STATUS_ZHUANCHU_TRIAL_4);
		power.put("STATUS_ZHUANCHU_KLB_PRINT", STATUS_ZHUANCHU_PRINT);
	}
	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}
	
	public String getFilePath() {
		
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		
		this.filePath = filePath;
	}
	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}
	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}
}
