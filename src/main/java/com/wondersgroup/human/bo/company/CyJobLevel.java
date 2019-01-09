/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: JobLevel.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 试用信息
 * 创建人: jiang
 * 创建时间: 2018年6月12日09:44:56
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日09:44:59
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.company;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.base.BaseJobLevel;

/**
 * 
 * @ClassName:  JobLevel   
 * @Description:职务信息   
 * @author: bianyf
 * @date:   2018年8月20日 下午3:41:42   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "D05")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CyJobLevel extends BaseJobLevel<CyJobLevel> {
	
	private static final long serialVersionUID = -70237391712662148L;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@JoinColumn(name="NATIONALCOMPANY_ID")
	private NationalCompany nationalCompany;
	
	/**
	 * @fieldName: 现行职级状态
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人担任职务或享受职级的状态。*ZB14—2016/RZZT《任职状态代码》
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A0524")
	private CodeInfo status;
	
	
	

	public NationalCompany getNationalCompany() {
		return nationalCompany;
	}

	public void setNationalCompany(NationalCompany nationalCompany) {
		this.nationalCompany = nationalCompany;
	}

	public CodeInfo getStatus() {
		
		return status;
	}
	
	public void setStatus(CodeInfo status) {
		
		this.status = status;
	}
	
}
