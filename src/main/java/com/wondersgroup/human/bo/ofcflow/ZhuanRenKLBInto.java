/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenkLB.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 同类别转任
 * 创建人: tzy
 * 创建时间: 2018年7月31日 下午5:03:42
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月31日 下午5:03:42
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
 * @ClassName: ZhuanRenkLBInto
 * @Description: 同类别转任 转入情况信息
 * @author: tzy
 * @date: 2018年7月31日 下午5:03:42
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ZHUANREN_KLB_INTO_C")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZhuanRenKLBInto extends BaseEventInto<ZhuanRenKLBInto> {
	
	private static final long serialVersionUID = 4543324423203002791L;
	
	/**
	 * @fieldName: zhuanRenKLBIntoBatch
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoBatch
	 * @Description: 跨类别转任 批量数据表
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ZHUANRENKLBINTOBATCH_ID")
	private ZhuanRenKLBIntoBatch zhuanRenKLBIntoBatch;
	
	/**
	 * @fieldName: personType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PERSON_TYPE")
	private CodeInfo personType;
	
	// 转任信息
	/**
	 * @fieldName: zhuanRenkLBOut
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ZhuanRenkLBOut
	 * @Description: 转出情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ZHUANRENKLBOUT_ID")
	private ZhuanRenKLBOut zhuanRenKLBOut;
	
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
	 * @fieldName: causeType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 字典CODE：causeType 转任事由：1--工作需要、2--军属随调、3--解决家庭生活困难。其中工作需要和解决家庭生活困难需注明具体事由。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CAUSETYPE")
	private CodeInfo causeType;
	
	/**
	 * @fieldName: specificCause
	 * @fieldType: java.lang.String
	 * @Description: 转任具体事由。
	 */
	@Column(name = "SPECIFIC_CAUSE", length = 400)
	private String specificCause;
	
	public ZhuanRenKLBIntoBatch getZhuanRenKLBIntoBatch() {
		
		return zhuanRenKLBIntoBatch;
	}
	
	public void setZhuanRenKLBIntoBatch(ZhuanRenKLBIntoBatch zhuanRenKLBIntoBatch) {
		
		this.zhuanRenKLBIntoBatch = zhuanRenKLBIntoBatch;
	}
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
	}
	
	public ZhuanRenKLBOut getZhuanRenKLBOut() {
		
		return zhuanRenKLBOut;
	}
	
	public void setZhuanRenKLBOut(ZhuanRenKLBOut zhuanRenKLBOut) {
		
		this.zhuanRenKLBOut = zhuanRenKLBOut;
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
	
	public CodeInfo getCauseType() {
		
		return causeType;
	}
	
	public void setCauseType(CodeInfo causeType) {
		
		this.causeType = causeType;
	}
	
	public String getSpecificCause() {
		
		return specificCause;
	}
	
	public void setSpecificCause(String specificCause) {
		
		this.specificCause = specificCause;
	}
}
