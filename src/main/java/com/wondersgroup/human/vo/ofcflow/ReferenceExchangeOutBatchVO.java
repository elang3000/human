/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeOutBatchVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年12月20日 下午4:46:06 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年12月20日 下午4:46:06 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOutBatch;

/** 
 * @ClassName: ReferenceExchangeOutBatchVO 
 * @Description: TODO	参公交流VO
 * @author: GP
 * @date: 2018年12月20日 下午4:46:06
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ReferenceExchangeOutBatchVO {
private String id;
	
	/**
	 * @Description: 转出单位
	 */
	private String sourceOrgan;
	
	/**
	 * @Description: 状态。
	 */
	private String status;
	
	/**
	 * @Description: 状态名称。
	 */
	private String statusName;
	
	/**
	 * @Description: 创建时间。
	 */
	private String createTime;
	
	public ReferenceExchangeOutBatchVO() {
		
	}
	
	public ReferenceExchangeOutBatchVO(ReferenceExchangeOutBatch z) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = z.getId();
		this.createTime = sdf.format(z.getCreateTime());
		if (z.getSourceOrgan() != null) {
			this.sourceOrgan = z.getSourceOrgan().getName();
		}
		if (z.getStatus()==null) {
			this.status = "99";
		}
		else{
			// 如果已经提交流程，并且是起始节点，设置流程状态为99，不能编辑和删除
			if (z.getFlowRecord() != null && z.getStatus() == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE) {
				this.status = "99";
			} else {
				this.status = String.valueOf(z.getStatus());
			}
		}
		if (z.getStatus()==null) {
			this.statusName = convertState(0);
		}
		else{
			this.statusName = convertState(z.getStatus());
		}
		
	}
	
	public String convertState(int state) {
//		if (state==null){
//			return "待发起转出流程";
//		}
		if (state == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE) {
			return "待发起转出流程";
		} else if (state == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_TRIAL) {
			return "待转出单位确认";
		} else if (state == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_TRIAL_1) {
			return "待区人事主管部门一级审核";
		} else if (state == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_TRIAL_2) {
			return "待区人事主管部门二级审核";
		} else if (state == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_TRIAL_3) {
			return "待区人事主管部门三级审核";
		} else if (state == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_TRIAL_4) {
			return "待区人事主管部门四级审核";
		} else if (state == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_TRIAL_5) {
			return "待区人事主管部门打印介绍信";
		}else if (state == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_FINISH) {
			return "已完成人员转出";
		} else {
			return "";
		}
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getSourceOrgan() {
		
		return sourceOrgan;
	}

	
	public void setSourceOrgan(String sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}

	
	public String getStatus() {
		
		return status;
	}

	
	public void setStatus(String status) {
		
		this.status = status;
	}

	
	public String getStatusName() {
		
		return statusName;
	}

	
	public void setStatusName(String statusName) {
		
		this.statusName = statusName;
	}

	
	public String getCreateTime() {
		
		return createTime;
	}

	
	public void setCreateTime(String createTime) {
		
		this.createTime = createTime;
	}
}
