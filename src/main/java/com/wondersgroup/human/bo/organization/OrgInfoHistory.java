/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoHistory.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.organization
 * 描述: 单位信息历史调整类
 * 创建人: jiang
 * 创建时间: 2018年9月20日17:18:14
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.organization;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofcflow.OrgInfoMgrFlow;
import com.wondersgroup.human.bo.organization.base.BaseUnitInfo;

/**
 * @ClassName: OrgInfoHistory
 * @Description: 单位信息历史调整类
 * @author: jiang
 * @date: 2018年9月20日17:18:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "B01_HIS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgInfoHistory extends BaseUnitInfo<OrgInfoHistory> {
	
	private static final long serialVersionUID = -5719683642006337151L;
	
	/**
	 * *
	 * 关联单位基本信息
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGINFO_ID")
	private OrgInfo orgInfo;
	
	/**
	 * *
	 * 关联单位基本信息申请表
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_MGR_FLOW_ID")
	private OrgInfoMgrFlow orgInfoMgrFlow;
	
	/**
	 * * 机构类别
	 **/
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "B0177")
	private CodeInfo orgCategory;
	
	public OrgInfo getOrgInfo() {
		
		return orgInfo;
	}
	
	public void setOrgInfo(OrgInfo orgInfo) {
		
		this.orgInfo = orgInfo;
	}
	
	public OrgInfoMgrFlow getOrgInfoMgrFlow() {
		
		return orgInfoMgrFlow;
	}
	
	public void setOrgInfoMgrFlow(OrgInfoMgrFlow orgInfoMgrFlow) {
		
		this.orgInfoMgrFlow = orgInfoMgrFlow;
	}

	
	public CodeInfo getOrgCategory() {
		
		return orgCategory;
	}

	
	public void setOrgCategory(CodeInfo orgCategory) {
		
		this.orgCategory = orgCategory;
	}
	
}
