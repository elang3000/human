/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProbationTimeLength.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 试用期时长 管理
 * 创建人: tzy
 * 创建时间: 2018年9月6日 下午2:30:44
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年9月6日 下午2:30:44
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: ProbationTimeLength
 * @Description: 试用期时长 管理
 * @author: tzy
 * @date: 2018年9月6日 下午2:30:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_PROBATION_TIME_LENGTH")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProbationTimeLength extends GenericEntity {
	
	private static final long serialVersionUID = -1197466507530402349L;
	
	/**
	 * @fieldName: probationDate
	 * @fieldType: Integer
	 * @Description: 试用时长 月份
	 */
	@Column(name = "ALLOW_WEAVE_NUM")
	private Integer probationDate;
	
	/**
	 * @fieldName: remark
	 * @fieldType: java.lang.String
	 * @Description: 备注
	 */
	@Column(name = "REMARK", length = 400)
	private String remark;
	
	public Integer getProbationDate() {
		
		return probationDate;
	}
	
	public void setProbationDate(Integer probationDate) {
		
		this.probationDate = probationDate;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
}
