/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Family.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 家庭
 * 创建人: jiang
 * 创建时间: 2018年8月20日10:00:35
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月20日10:00:37
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.ofc.base.BaseFamily;

/**
 * @ClassName: Degree
 * @Description: 上海 家庭
 * @author: jiang
 * @date: 2018年7月2日14:05:34
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "A36")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Family extends BaseFamily<Family> {
	
	private static final long serialVersionUID = 2921653857869968956L;
	
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
