/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RewardAndPunish.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年7月12日 上午10:12:05 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年7月12日 上午10:12:05 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.company;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.ofc.base.BaseRewardAndPunish;

/**
 * 
 * @ClassName:  RewardAndPunish   
 * @Description:奖惩情况   
 * @author: bianyf
 * @date:   2018年8月20日 下午3:51:24   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "D14")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CyRewardAndPunish extends BaseRewardAndPunish<CyRewardAndPunish> {

	private static final long serialVersionUID = -861904002381493320L;

	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@JoinColumn(name="NATIONALCOMPANY_ID")
	private NationalCompany nationalCompany;
	
	/**
	 * @fieldName: type
	 * @fieldType: java.lang.Integer
	 * @Description: 记录类型：0奖励；1处分。
	 */
	@Column(name = "R_P_TYPE", length = 4)
	private Integer type;

	
	

	public NationalCompany getNationalCompany() {
		return nationalCompany;
	}

	public void setNationalCompany(NationalCompany nationalCompany) {
		this.nationalCompany = nationalCompany;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
}
