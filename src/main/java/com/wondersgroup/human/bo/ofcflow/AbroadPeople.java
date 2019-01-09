/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPeople.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月10日 下午1:53:36 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月10日 下午1:53:36 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.human.bo.ofc.Servant;

/** 
 * @ClassName: AbroadPeople 
 * @Description: 因公出国人员
 * @author: lihao
 * @date: 2018年12月10日 下午1:53:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name = "HUMAN_ABROAD_PEOPLE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AbroadPeople extends GenericEntity {

	private static final long serialVersionUID = -1037408929896188179L;
	
	/**
	 * @fieldName: yearPlan
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.AbroadYearPlan
	 * @Description: 出国信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ABROAD_ID")
	private AbroadServant abroadServant;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @return the abroadServant
	 */
	public AbroadServant getAbroadServant() {
		return abroadServant;
	}

	/**
	 * @param abroadServant the abroadServant to set
	 */
	public void setAbroadServant(AbroadServant abroadServant) {
		this.abroadServant = abroadServant;
	}

	/**
	 * @return the servant
	 */
	public Servant getServant() {
		return servant;
	}

	/**
	 * @param servant the servant to set
	 */
	public void setServant(Servant servant) {
		this.servant = servant;
	}
}
