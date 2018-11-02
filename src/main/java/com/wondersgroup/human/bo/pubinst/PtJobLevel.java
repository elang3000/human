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

package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.base.BaseJobLevel;

/**
 * 
 * @ClassName:  JobLevel   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: bianyf
 * @date:   2018年8月20日 下午12:21:32   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "C05")
public class PtJobLevel extends BaseJobLevel<PtJobLevel> {
	
	private static final long serialVersionUID = -70237391712662148L;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@JoinColumn(name="PUBLICINSTITUTION_ID")
	private PublicInstitution publicInstitution;

	
	
	/**
	 * @fieldName: 现行职级状态
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人担任职务或享受职级的状态。*ZB14—2016/RZZT《任职状态代码》
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A0524")
	private CodeInfo status;
	

	
	public CodeInfo getStatus() {
		
		return status;
	}
	
	public void setStatus(CodeInfo status) {
		
		this.status = status;
	}
	
	public PublicInstitution getPublicInstitution() {
		return publicInstitution;
	}

	public void setPublicInstitution(PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
	}
	
}
