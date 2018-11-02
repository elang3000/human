/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: TransferringexchangesSummeryInfo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 选调交流 汇总信息和单位招聘详情 关联表
 * 创建人: tzy
 * 创建时间: 2018年8月16日 上午10:13:43
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月16日 上午10:13:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: TransferringexchangesSummeryInfo
 * @Description: 选调交流 汇总信息和单位招聘详情 关联表
 * @author: tzy
 * @date: 2018年8月16日 上午10:13:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_XUANDIAO_SUMMERY_INFO")
public class TransferringSummeryInfo extends GenericEntity {
	
	private static final long serialVersionUID = 8125396100251632195L;
	
	/**
	 * @fieldName: summery
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.TransferringexchangesSummery
	 * @Description: 选调汇总信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUMMERY_ID")
	private TransferringSummery summery;
	
	/**
	 * @fieldName: plan
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.TransferringExchanges
	 * @Description: 选调计划信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID")
	private TransferringExchanges plan;
	
	public TransferringSummery getSummery() {
		
		return summery;
	}
	
	public void setSummery(TransferringSummery summery) {
		
		this.summery = summery;
	}
	
	public TransferringExchanges getPlan() {
		
		return plan;
	}
	
	public void setPlan(TransferringExchanges plan) {
		
		this.plan = plan;
	}
}
