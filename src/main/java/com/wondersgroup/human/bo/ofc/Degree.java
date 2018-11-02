/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Degree.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 学位
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:05:18
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:05:21
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.human.bo.ofc.base.BaseDegree;

/**
 * @ClassName: Degree
 * @Description: 上海 学位
 * @author: jiang
 * @date: 2018年7月2日14:05:34
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "A09")
public class Degree extends BaseDegree<Degree> {
	
	private static final long serialVersionUID = -4156213718845762776L;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}
	
}
