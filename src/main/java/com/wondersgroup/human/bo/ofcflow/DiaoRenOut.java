/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DiaoRenOutMgr.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 调任流程 调出情况信息
 * 创建人: tzy
 * 创建时间: 2018年7月30日 下午2:01:43
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月30日 下午2:01:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: DiaoRenOut
 * @Description: 调任流程 调出情况信息
 * @author: tzy
 * @date: 2018年7月30日 下午2:01:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DIAOREN_OUT_C")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DiaoRenOut extends BaseEventOut<DiaoRenOut> {
	
	private static final long serialVersionUID = -9008077326672614312L;
	
	/**
	 * @fieldName: diaoRenOutBatch
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DiaoRenOutBatch
	 * @Description: 调任 批量数据表
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DIAORENOUTBATCH_ID")
	private DiaoRenOutBatch diaoRenOutBatch;
	
	/**
	 * @fieldName: diaoRenInto
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DiaoRenInto
	 * @Description: 调入情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DIAORENINTO_ID")
	private DiaoRenInto diaoRenInto;
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PERSON_TYPE")
	private CodeInfo personType;
	
	/**
	 * @fieldName: postCode
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 拟任职务代码,GB/T 12403-1990 该人担任职务的代码。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "POST_CODE")
	private CodeInfo postCode;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 拟任职务名称 ,该人担任职务的具体名称。
	 */
	@Column(name = "POST_NAME", length = 80)
	private String postName;
	
	/**
	 * @fieldName: attribute
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 拟任职务属性,DM049 担任领导职务或非领导职务的情况。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ATTRIBUTE")
	private CodeInfo attribute;
	
	/**
	 * @fieldName: 职级名称
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级名称。
	 */
	@Column(name = "JOBLEVEL_NAME")
	private String jobLevelName;
	
	/**
	 * @fieldName: 职级代码
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级代码。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOBLEVEL_CODE")
	private CodeInfo jobLevelCode;
	
	public DiaoRenInto getDiaoRenInto() {
		
		return diaoRenInto;
	}
	
	public void setDiaoRenInto(DiaoRenInto diaoRenInto) {
		
		this.diaoRenInto = diaoRenInto;
	}
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
	}
	
	public CodeInfo getPostCode() {
		
		return postCode;
	}
	
	public void setPostCode(CodeInfo postCode) {
		
		this.postCode = postCode;
	}
	
	public String getPostName() {
		
		return postName;
	}
	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}
	
	public CodeInfo getAttribute() {
		
		return attribute;
	}
	
	public void setAttribute(CodeInfo attribute) {
		
		this.attribute = attribute;
	}
	
	public String getJobLevelName() {
		
		return jobLevelName;
	}
	
	public void setJobLevelName(String jobLevelName) {
		
		this.jobLevelName = jobLevelName;
	}
	
	public CodeInfo getJobLevelCode() {
		
		return jobLevelCode;
	}
	
	public void setJobLevelCode(CodeInfo jobLevelCode) {
		
		this.jobLevelCode = jobLevelCode;
	}
	
	public DiaoRenOutBatch getDiaoRenOutBatch() {
		
		return diaoRenOutBatch;
	}
	
	public void setDiaoRenOutBatch(DiaoRenOutBatch diaoRenOutBatch) {
		
		this.diaoRenOutBatch = diaoRenOutBatch;
	}
}
