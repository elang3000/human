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

package com.wondersgroup.human.bo.ofc;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.common.contant.CommonConst;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.base.BaseJobLevel;

/**
 * @ClassName: JobLevel
 * @Description: 上海 职级信息
 * @author: jiang
 * @date: 2018年6月12日09:45:23
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "A05")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JobLevel extends BaseJobLevel<JobLevel> {

	private static final long serialVersionUID = -70237391712662148L;
	public static final String LEADER = "领导";
	public static final String NOTLEADER = "非领导";

	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: 现行职级状态
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人担任职务或享受职级的状态。*ZB14—2016/RZZT《任职状态代码》
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A0524")
	private CodeInfo status;
	
	/**
	 * @fieldName: isLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 是否是领导属性
	 */
	@Column(name = "IS_LEADER")
	private Integer isLeader;
	
	/**
	 * @fieldName: realJobLevelCode
	 * @fieldType: java.lang.String
	 * @Description: 占编时 使用的职级代码
	 */
	@Column(name = "REAL_JOB_LVL_CODE")
	private String realJobLevelCode;
	
	/**
	 * @fieldName: realLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 占编时 是否是领导属性
	 */
	@Column(name = "REAL_LEADER")
	private Integer realLeader;
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}
	
	public CodeInfo getStatus() {
		
		return status;
	}
	
	public void setStatus(CodeInfo status) {
		
		this.status = status;
	}

	
	public Integer getIsLeader() {
		
		return isLeader;
	}

	public String getIsLeaderStr(){
		if(isLeader== CommonConst.YES){
			return LEADER;
		}else if(isLeader== CommonConst.NO){
			return NOTLEADER;
		}else{
			return "";
		}
	}

	
	public void setIsLeader(Integer isLeader) {
		
		this.isLeader = isLeader;
	}

	
	public String getRealJobLevelCode() {
		
		return realJobLevelCode;
	}

	
	public void setRealJobLevelCode(String realJobLevelCode) {
		
		this.realJobLevelCode = realJobLevelCode;
	}

	
	public Integer getRealLeader() {
		
		return realLeader;
	}

	
	public void setRealLeader(Integer realLeader) {
		
		this.realLeader = realLeader;
	}
	
}
