/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.organization
 * 描述: 机构信息类
 * 创建人: jiang
 * 创建时间: 2018年9月12日14:51:26
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.organization;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.organization.base.BaseUnitInfo;

/**
 * 机构信息
 * @ClassName: OrgInfo
 * @Description: 单位基本情况
 * @author: jiang
 * @date: 2018年9月12日14:51:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "B01")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgInfo extends BaseUnitInfo<OrgInfo> {
	
	private static final long serialVersionUID = -5719647342006337151L;
	
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
	@ManyToOne
	@JoinColumn(name = "ORGANID")
	private OrganNode organ;
	
	/**
	 * * 机构类别
	 **/
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "B0177")
	private CodeInfo orgCategory;

	
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
	
}
