/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoFlow.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.organization
 * 描述: 单位基本情况维护流程表单
 * 创建人: jiang
 * 创建时间: 2018年9月13日09:40:52
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
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
import com.wondersgroup.human.bo.organization.base.BaseUnitInfo;

/**
 * 机构信息
 * @ClassName: OrgInfoMgrFlow
 * @Description: 单位基本情况维护流程表单
 * @author: jiang
 * @date: 2018年9月13日10:39:30
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_FLOW_ORGINFO_MGR")
public class OrgInfoMgrFlow extends BaseUnitInfo<OrgInfoMgrFlow> {
	
	/**
	 * 待提交申请信息
	 */
	public static final int STATUS_ORG_INFO_MGR_FLOW_STATE = 0;
	
	/**
	 * 待上级审核
	 */
	public static final int STATUS_ORG_INFO_MGR_FLOW_TRIAL1 = 1;
	
	/**
	 * 审核通过
	 */
	public static final int STATUS_ORG_INFO_MGR_FLOW_TRIAL2 = 2;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
		power.put("APPLY_WAIT_SUBMIT",STATUS_ORG_INFO_MGR_FLOW_STATE);
		power.put("APPLY_WAIT_UP_APPROVAL", STATUS_ORG_INFO_MGR_FLOW_TRIAL1);
		
		power.put("ADJUST_WAIT_SUBMIT",STATUS_ORG_INFO_MGR_FLOW_STATE);
		power.put("ADJUST_WAIT_UP_APPROVAL", STATUS_ORG_INFO_MGR_FLOW_TRIAL1);
	}
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4603193735928802138L;

	/**
	 * @fieldName: 单位所在行政区划(冗余字段保存全名称)
	 * @fieldType: java.lang.String
	 * @Description: 该单位所在的（或注册地所在的）当前国家县级及县级以上行政区划的名称。
	 */
	@Column(name = "B01017A", length = 200)
	private String unitDistrictName;
	
	/**
	 * *
	 * 关联组织机构
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANID")
	private OrganNode organ;
	
	/**
	 * *
	 * 父类组织机构
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ORGANID")
	private OrganNode parentOrgan;
	
	/**
	 * * 机构类别
	 **/
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "B0177")
	private CodeInfo orgCategory;
	
	/**
	 * * 操作类型
	 **/
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "OPTION_TYPE")
	private CodeInfo optionType;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;

	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态
	 */
	@Column(name = "STATUS")
	private Integer status;

	
	public String getUnitDistrictName() {
		
		return unitDistrictName;
	}

	
	public void setUnitDistrictName(String unitDistrictName) {
		
		this.unitDistrictName = unitDistrictName;
	}

	
	public OrganNode getOrgan() {
		
		return organ;
	}

	
	public void setOrgan(OrganNode organ) {
		
		this.organ = organ;
	}

	
	public CodeInfo getOrgCategory() {
		
		return orgCategory;
	}

	
	public void setOrgCategory(CodeInfo orgCategory) {
		
		this.orgCategory = orgCategory;
	}

	
	public CodeInfo getOptionType() {
		
		return optionType;
	}

	
	public void setOptionType(CodeInfo optionType) {
		
		this.optionType = optionType;
	}

	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}

	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}

	
	public Integer getStatus() {
		
		return status;
	}

	
	public void setStatus(Integer status) {
		
		this.status = status;
	}


	
	public OrganNode getParentOrgan() {
		
		return parentOrgan;
	}


	
	public void setParentOrgan(OrganNode parentOrgan) {
		
		this.parentOrgan = parentOrgan;
	}
	
}
