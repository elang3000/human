/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ReferenceExchangeOut.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 参公交流 调出
 * 创建人: tzy
 * 创建时间: 2018年8月6日 下午2:50:43
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月6日 下午2:50:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.organization.bo.OrganNode;

/**
 * @ClassName: ReferenceExchangeOut
 * @Description: 参公交流 调出
 * @author: tzy
 * @date: 2018年8月6日 下午2:50:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_REFERENCE_EXCHANGE_OUT")
public class ReferenceExchangeOut extends BaseEventOut<ReferenceExchangeOut> {
	
	private static final long serialVersionUID = -7848542299926456057L;
	
	/**
	 * @fieldName: SourceOrgan
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 原单位信息
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOURCEORGAN_ID")
	private OrganNode sourceOrgan;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态。
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name="BATCH")
	private ReferenceExchangeOutBatch referenceExchangeOutBatch;
	
	/**
	 * @fieldName: referenceExchange
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ReferenceExchange
	 * @Description: 转入情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "referenceExchange")
	private ReferenceExchange referenceExchange;
	/**
	 * 待发起参公交流调出流程
	 */
	public static final int STATUS_DIAOCHU_STATE = 0;
	
	/**
	 * 待调出单位备案
	 */
	public static final int STATUS_DIAOCHU_CONFIRM = 1;
	
	/**
	 * 人员调出信息已备案
	 */
	public static final int STATUS_DIAOCHU_FINISH = 2;
	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}

	
	public ReferenceExchangeOutBatch getReferenceExchangeOutBatch() {
		
		return referenceExchangeOutBatch;
	}

	
	public void setReferenceExchangeOutBatch(ReferenceExchangeOutBatch referenceExchangeOutBatch) {
		
		this.referenceExchangeOutBatch = referenceExchangeOutBatch;
	}

	
	public ReferenceExchange getReferenceExchange() {
		
		return referenceExchange;
	}

	
	public void setReferenceExchange(ReferenceExchange referenceExchange) {
		
		this.referenceExchange = referenceExchange;
	}
	
	
}
