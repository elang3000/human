/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Experience.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 简历
 * 创建人: jiang
 * 创建时间: 2018年7月2日15:12:22
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日15:12:24
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.human.bo.pubinst.base.InstBaseExperience;

/**
 * 
 * @ClassName:  Experience   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: bianyf
 * @date:   2018年8月20日 下午12:20:19   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "C17")
public class PtExperience extends InstBaseExperience<PtExperience> {
	
	private static final long serialVersionUID = 371065533385437124L;
	
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
	
}
