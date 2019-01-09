/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenKLBOutMgr.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 同类别转任 转出情况信息
 * 创建人: tzy
 * 创建时间: 2018年8月6日 下午2:50:43
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月6日 下午2:50:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @ClassName: ZhuanRenKLBOutMgr
 * @Description: 同类别转任 转出情况信息
 * @author: tzy
 * @date: 2018年8月6日 下午2:50:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ZHUANREN_KLB_OUT_C")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZhuanRenKLBOut extends BaseEventOut<ZhuanRenKLBOut> {
	
	private static final long serialVersionUID = -7848542299926456006L;
	
	/**
	 * @fieldName: zhuanRenKLBOutBatch
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOutBatch
	 * @Description: 跨类别转任 批量数据表
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ZHUANRENKLBOUTBATCH_ID")
	private ZhuanRenKLBOutBatch zhuanRenKLBOutBatch;
	
	/**
	 * @fieldName: zhuanRenKLBInto
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBInto
	 * @Description: 转入情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ZHUANRENKLBINTO_ID")
	private ZhuanRenKLBInto zhuanRenKLBInto;
	
	public ZhuanRenKLBInto getZhuanRenKLBInto() {
		
		return zhuanRenKLBInto;
	}
	
	public void setZhuanRenKLBInto(ZhuanRenKLBInto zhuanRenKLBInto) {
		
		this.zhuanRenKLBInto = zhuanRenKLBInto;
	}
	
	public ZhuanRenKLBOutBatch getZhuanRenKLBOutBatch() {
		
		return zhuanRenKLBOutBatch;
	}
	
	public void setZhuanRenKLBOutBatch(ZhuanRenKLBOutBatch zhuanRenKLBOutBatch) {
		
		this.zhuanRenKLBOutBatch = zhuanRenKLBOutBatch;
	}
	
}
