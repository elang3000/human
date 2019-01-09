/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OutMgr.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc 
 * 描述: 上海  调出情况
 * 创建人: tzy   
 * 创建时间: 2018年7月30日 上午10:22:41 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月30日 上午10:22:41 
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

import com.wondersgroup.human.bo.ofc.base.BaseOutMgr;

/** 
 * @ClassName: OutMgr 
 * @Description: 上海  调出情况
 * @author: tzy
 * @date: 2018年7月30日 上午10:22:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name="A30")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OutMgr extends BaseOutMgr<OutMgr>{

	private static final long serialVersionUID = 633183825295989050L;
	
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
