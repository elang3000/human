/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPeople.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月13日 下午2:06:52 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月13日 下午2:06:52 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofc.Servant;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/** 
 * @ClassName: TrainPeople 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年11月13日 下午2:06:52
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity
@Table(name="HUMAN_TRAIN_PEOPLE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainPeople extends GenericEntity{

	private static final long serialVersionUID = 5973610532369306084L;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;

	/**
	 * @fieldName: plan
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan
	 * @Description: 培训信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAIN_ID")
	private TrainServant train;
	
	/**
	 * *
	 * 培训时所在单位
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANID")
	private OrganNode organ;

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

	/**
	 * @return the train
	 */
	public TrainServant getTrain() {
		return train;
	}

	/**
	 * @param train the train to set
	 */
	public void setTrain(TrainServant train) {
		this.train = train;
	}

	/**
	 * @return the organ
	 */
	public OrganNode getOrgan() {
		return organ;
	}

	/**
	 * @param organ the organ to set
	 */
	public void setOrgan(OrganNode organ) {
		this.organ = organ;
	}
}
