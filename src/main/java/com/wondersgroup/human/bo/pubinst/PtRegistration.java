/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Registration.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 公务员登记
 * 创建人: jiang
 * 创建时间: 2018年8月22日15:29:59
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月22日15:30:01
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.ofc.base.BaseRegistration;

/**
 * @ClassName: Registration
 * @Description: 上海 公务员登记
 * @author: jiang
 * @date: 2018年7月2日14:05:34
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "C61")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PtRegistration extends BaseRegistration<PtRegistration> {
	
	
	private static final long serialVersionUID = 4164011285506814796L;
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@JoinColumn(name="PUBLICINSTITUTION_ID")
	private PublicInstitution publicInstitution;
	public PublicInstitution getPublicInstitution() {
		return publicInstitution;
	}
	public void setPublicInstitution(PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
