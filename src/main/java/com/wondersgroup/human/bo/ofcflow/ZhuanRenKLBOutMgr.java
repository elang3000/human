/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenKLBOutMgr.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 跨类别转任 转出情况信息
 * 创建人: tzy
 * 创建时间: 2018年9月20日 下午5:12:21
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年9月20日 下午5:12:21
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
 * @ClassName: ZhuanRenKLBOutMgr
 * @Description: 跨类别转任 转出情况信息
 * @author: tzy
 * @date: 2018年9月20日 下午5:12:21
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ZHUANREN_KLB_OUT")
public class ZhuanRenKLBOutMgr extends BaseEventOutMgr<ZhuanRenKLBOutMgr> {
	
	private static final long serialVersionUID = -7848542299926456006L;
	
	/**
	 * @fieldName: zhuanRenKLBIntoMgr
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoMgr
	 * @Description: 转入情况信息
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ZHUANRENKLBINTOMGR_ID")
	private ZhuanRenKLBIntoMgr zhuanRenKLBIntoMgr;
	
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
	
	/**
	 * 待发起转出流程
	 */
	public static final int STATUS_ZHUANCHU_STATE = 0;
	
	/**
	 * 待转出单位备案
	 */
	public static final int STATUS_ZHUANCHU_CONFIRM = 1;
	
	/**
	 * 人员转出信息已备案
	 */
	public static final int STATUS_ZHUANCHU_FINISH = 2;
	
	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}
	
	public OrganNode getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(OrganNode sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public ZhuanRenKLBIntoMgr getZhuanRenKLBIntoMgr() {
		
		return zhuanRenKLBIntoMgr;
	}
	
	public void setZhuanRenKLBIntoMgr(ZhuanRenKLBIntoMgr zhuanRenKLBIntoMgr) {
		
		this.zhuanRenKLBIntoMgr = zhuanRenKLBIntoMgr;
	}
	
}
