/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenTLBOutMgr.java
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
 * @ClassName: ZhuanRenTLBOutMgr
 * @Description: 同类别转任 转出情况信息
 * @author: tzy
 * @date: 2018年8月6日 下午2:50:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ZHUANREN_TLB_OUT_C")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZhuanRenTLBOut extends BaseEventOut<ZhuanRenTLBOut> {
	
	private static final long serialVersionUID = -7848542299926456006L;
	
	/**
	 * @fieldName: zhuanRenTLBOutBatch
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutBatch
	 * @Description: 同类别转任 批量数据表
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ZHUANRENTLBOUTBATCH_ID")
	private ZhuanRenTLBOutBatch zhuanRenTLBOutBatch;
	
	/**
	 * @fieldName: zhuanRenTLBInto
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto
	 * @Description: 转入情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ZHUANRENTLBINTO_ID")
	private ZhuanRenTLBInto zhuanRenTLBInto;
	
	public ZhuanRenTLBInto getZhuanRenTLBInto() {
		
		return zhuanRenTLBInto;
	}
	
	public void setZhuanRenTLBInto(ZhuanRenTLBInto zhuanRenTLBInto) {
		
		this.zhuanRenTLBInto = zhuanRenTLBInto;
	}
	
	public ZhuanRenTLBOutBatch getZhuanRenTLBOutBatch() {
		
		return zhuanRenTLBOutBatch;
	}
	
	public void setZhuanRenTLBOutBatch(ZhuanRenTLBOutBatch zhuanRenTLBOutBatch) {
		
		this.zhuanRenTLBOutBatch = zhuanRenTLBOutBatch;
	}
	
}
