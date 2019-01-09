/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenTLBOutBatch.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 公务员 批量调出数据表
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
 * @ClassName: ZhuanRenTLBOutBatch
 * @Description: 公务员调任 批量调出数据表
 * @author: tzy
 * @date: 2018年12月18日 上午9:57:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DIAOREN_BATCH_OUT")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DiaoRenOutBatch extends GenericEntity {
	
	private static final long serialVersionUID = -2312841411066771720L;
	
	/**
	 * @fieldName: areaType
	 * @fieldType: java.lang.String
	 * @Description: 调任地区：1-本区 2-外区。
	 */
	@Column(name = "AREATYPE", length = 1)
	private String areaType;
	
	/**
	 * @fieldName: targetType
	 * @fieldType: java.lang.String
	 * @Description: 人员去向类型：1-事业人员PublicInstitution 2-国企职工NationalCompany。
	 */
	@Column(name = "TARGETTYPE", length = 1)
	private String targetType;
	
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
	 * @Description: 调往单位信息
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGETORGAN_ID")
	private OrganNode targetOrgan;
	
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
	
	/**
	 * 待发起调出流程
	 */
	public static final int STATUS_DIAOCHU_STATE = 0;
	
	/**
	 * 待调出单位确认
	 */
	public static final int STATUS_DIAOCHU_TRIAL = 1;
	
	/**
	 * 待区人事主管部门一级审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL_1 = 2;
	
	/**
	 * 待区人事主管部门二级审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL_2 = 3;
	
	/**
	 * 待区人事主管部门三级审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL_3 = 4;
	
	/**
	 * 待区人事主管部门四级审核
	 */
	public static final int STATUS_DIAOCHU_TRIAL_4 = 5;
	
	/**
	 * 待调入单位确认
	 */
	public static final int STATUS_DIAOCHU_AGREE = 6;
	
	/**
	 * 待区人事主管部门打印电子介绍信
	 */
	public static final int STATUS_DIAOCHU_PRINT = 7;
	
	/**
	 * 已完成人员调出
	 */
	public static final int STATUS_DIAOCHU_FINISH = 8;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		// 调出到本区
		power.put("STATUS_DIAOCHU_STATE", STATUS_DIAOCHU_STATE);
		power.put("STATUS_DIAOCHU_TRIAL", STATUS_DIAOCHU_TRIAL);
		power.put("STATUS_DIAOCHU_TRIAL_1", STATUS_DIAOCHU_TRIAL_1);
		power.put("STATUS_DIAOCHU_TRIAL_2", STATUS_DIAOCHU_TRIAL_2);
		power.put("STATUS_DIAOCHU_TRIAL_3", STATUS_DIAOCHU_TRIAL_3);
		power.put("STATUS_DIAOCHU_TRIAL_4", STATUS_DIAOCHU_TRIAL_4);
		power.put("STATUS_DIAOCHU_AGREE", STATUS_DIAOCHU_AGREE);
		power.put("STATUS_DIAOCHU_PRINT", STATUS_DIAOCHU_PRINT);
		
		// 调出到外区
		power.put("STATUS_DIAOCHU_OUTER_STATE", STATUS_DIAOCHU_STATE);
		power.put("STATUS_DIAOCHU_OUTER_TRIAL", STATUS_DIAOCHU_TRIAL);
		power.put("STATUS_DIAOCHU_OUTER_TRIAL_1", STATUS_DIAOCHU_TRIAL_1);
		power.put("STATUS_DIAOCHU_OUTER_TRIAL_2", STATUS_DIAOCHU_TRIAL_2);
		power.put("STATUS_DIAOCHU_OUTER_TRIAL_3", STATUS_DIAOCHU_TRIAL_3);
		power.put("STATUS_DIAOCHU_OUTER_TRIAL_4", STATUS_DIAOCHU_TRIAL_4);
		power.put("STATUS_DIAOCHU_OUTER_PRINT", STATUS_DIAOCHU_PRINT);
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
	
	public String getAreaType() {
		
		return areaType;
	}
	
	public void setAreaType(String areaType) {
		
		this.areaType = areaType;
	}
	
	public String getTargetType() {
		
		return targetType;
	}
	
	public void setTargetType(String targetType) {
		
		this.targetType = targetType;
	}
	
	public OrganNode getTargetOrgan() {
		
		return targetOrgan;
	}
	
	public void setTargetOrgan(OrganNode targetOrgan) {
		
		this.targetOrgan = targetOrgan;
	}
}
