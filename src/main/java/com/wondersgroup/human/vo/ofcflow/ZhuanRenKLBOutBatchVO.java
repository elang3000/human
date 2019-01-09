/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenKLBIntoVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 跨类别转任 批量转出情况信息 VO
 * 创建人: tzy
 * 创建时间: 2018年8月1日 上午11:02:16
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月1日 上午11:02:16
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOutBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBIntoBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutBatch;

/**
 * @ClassName: ZhuanRenKLBIntoVO
 * @Description: 跨类别转任 批量转出情况信息 VO
 * @author: tzy
 * @date: 2018年8月1日 上午11:02:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ZhuanRenKLBOutBatchVO {
	
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
	
	public ZhuanRenKLBOutBatchVO() {
		
	}
	
	public ZhuanRenKLBOutBatchVO(ZhuanRenKLBOutBatch z) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = z.getId();
		this.createTime = sdf.format(z.getCreateTime());
		if (z.getSourceOrgan() != null) {
			this.sourceOrgan = z.getSourceOrgan().getName();
		}
		// 如果已经提交流程，并且是起始节点，设置流程状态为99，不能编辑和删除
		if (z.getFlowRecord() != null && z.getStatus() == ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE) {
			this.status = "99";
		} else {
			this.status = String.valueOf(z.getStatus());
		}
		this.statusName = convertState(z.getStatus());
	}
	
	public String convertState(int state) {
		
		if (state == ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_STATE) {
			return "待发起转出流程";
		} else if (state == ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_TRIAL) {
			return "待转出单位确认";
		} else if (state == ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_TRIAL_1) {
			return "待区人事主管部门一级审核";
		} else if (state == ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_TRIAL_2) {
			return "待区人事主管部门二级审核";
		} else if (state == ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_TRIAL_3) {
			return "待区人事主管部门三级审核";
		} else if (state == ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_TRIAL_4) {
			return "待区人事主管部门四级审核";
		} else if (state == ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_PRINT) {
			return "待区人事主管部门打印电子介绍信";
		} else if (state == ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_FINISH) {
			return "已完成人员转出";
		} else if (state == FlowRecord.BUS_STOP) {
			return "业务被中止";
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
