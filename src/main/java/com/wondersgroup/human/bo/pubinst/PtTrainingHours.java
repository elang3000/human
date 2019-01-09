/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: TrainingHours.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年6月13日 上午11:28:07
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年6月13日 上午11:28:07
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.bo.pubinst;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: TrainingHours
 * @Description: 培训学时考核
 * @author: Wonders-Rain
 * @date: 2018年6月13日 上午11:28:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "PUBLIC_TRAIN_HOURS_RECORD")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PtTrainingHours extends GenericEntity {
	
	private static final long serialVersionUID = 5316335517696256577L;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: String
	 * @Description: 培训名
	 *
	 */
	@Column(name = "TRAIN_NAME", length = 255, nullable = false)
	private String trainName;
	
	/**
	 * @fieldName: trainName
	 * @fieldType: Integer
	 * @Description: 培训课时
	 *
	 */
	@Column(name = "TRAIN_HOURS", nullable = false)
	private Integer hours;
	
	/**
	 * @fieldName: trainDetail
	 * @fieldType: String
	 * @Description: 培训详情
	 *
	 */
	@Column(name = "TRAIN_DETAIL", length = 2000, nullable = false)
	private String trainDetail;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "PUBLIC_TRAIN_HOURS_SERVANT", joinColumns = {
	        @JoinColumn(name = "TRAIN_HOURS_ID", referencedColumnName = "ID")
	}, inverseJoinColumns = {
	        @JoinColumn(name = "SERVANT_ID", referencedColumnName = "ID")
	})
	private Set<PublicInstitution> trainServants;
	
	public String getTrainName() {
		
		return trainName;
	}
	
	public void setTrainName(String trainName) {
		
		this.trainName = trainName;
	}
	
	public Integer getHours() {
		
		return hours;
	}
	
	public void setHours(Integer hours) {
		
		this.hours = hours;
	}
	
	public String getTrainDetail() {
		
		return trainDetail;
	}
	
	public void setTrainDetail(String trainDetail) {
		
		this.trainDetail = trainDetail;
	}
	
	public Set<PublicInstitution> getTrainServants() {
		
		return trainServants;
	}
	
	public void setTrainServants(Set<PublicInstitution> trainServants) {
		
		this.trainServants = trainServants;
	}
}
