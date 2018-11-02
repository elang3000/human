/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Post.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 试用信息
 * 创建人: jiang
 * 创建时间: 2018年6月11日15:07:37
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月11日15:07:44
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.human.bo.ofc.base.BaseProbation;

/**
 * @ClassName: Probation
 * @Description: 上海 试用信息
 * @author: jiang
 * @date: 2018年6月11日15:29:28
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "A04")
public class Probation extends BaseProbation<Probation> {
	
	
	private static final long serialVersionUID = -7723149048609461891L;

	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: 试用单位代码
	 * @fieldType: java.lang.String
	 * @Description: 试用单位代码。
	 */
	@Column(name = "SH_B0110")
	private String unitCode;

	
	public Servant getServant() {
		
		return servant;
	}

	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}

	
	public String getUnitCode() {
		
		return unitCode;
	}

	
	public void setUnitCode(String unitCode) {
		
		this.unitCode = unitCode;
	}
	
}
