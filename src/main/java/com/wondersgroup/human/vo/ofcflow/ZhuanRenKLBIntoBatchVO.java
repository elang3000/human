/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenKLBBatchVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 跨类别转任 批量数据表 VO
 * 创建人: tzy
 * 创建时间: 2018年12月7日 上午9:58:01
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年12月7日 上午9:58:01
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoBatch;

/**
 * @ClassName: ZhuanRenKLBBatchVO
 * @Description: 跨类别转任 批量数据表 VO
 * @author: tzy
 * @date: 2018年12月7日 上午9:58:01
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ZhuanRenKLBIntoBatchVO {
	
	/**
	 * @Description: ID
	 */
	private String id;
	
	/**
	 * @Description: 转任地区：1-本区 2-外区。
	 */
	private String areaType;
	
	/**
	 * @Description: 原单位信息
	 */
	private String sourceOrgan;
	
	/**
	 * @Description: 转入单位信息
	 */
	private String targetOrgan;
	
	/**
	 * @Description: 核定编制数
	 */
	private String allowWeaveNum;
	
	/**
	 * @Description: 实有人数
	 */
	private String realNum;
	
	/**
	 * @Description: 机构缺编数
	 */
	private String thisYearLackWeaveNum;
	
	/**
	 * @Description: 转任总人数。
	 */
	private String sumNumber;
	
	/**
	 * @Description: 报道日期。
	 */
	private String enterTheUnitDate;
	
	/**
	 * @Description: 状态。
	 */
	private String status;
	
	/**
	 * @Description: 状态名称。
	 */
	private String statusName;
	
	public ZhuanRenKLBIntoBatchVO() {
		
	}
	
	public ZhuanRenKLBIntoBatchVO(ZhuanRenKLBIntoBatch z) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = z.getId();
		if (ZhuanRenKLBIntoBatch.AREA_THIS.equals(z.getAreaType())) {
			this.areaType = "本区转任";
		} else if (ZhuanRenKLBIntoBatch.AREA_OUTER.equals(z.getAreaType())) {
			this.areaType = "外区转任";
		}
		if (z.getUnitPlanningTotal() != null) {
			this.allowWeaveNum = String.valueOf(z.getUnitPlanningTotal());
		}
		if (z.getActualNumber() != null) {
			this.realNum = String.valueOf(z.getActualNumber());
		}
		if (z.getVacancyExcessNumber() != null) {
			this.thisYearLackWeaveNum = String.valueOf(z.getVacancyExcessNumber());
		}
		if (z.getSumNumber() != null) {
			this.sumNumber = String.valueOf(z.getSumNumber());
		}
		if (z.getEnterTheUnitDate() != null) {
			this.enterTheUnitDate = sdf.format(z.getEnterTheUnitDate());
		}
		if (z.getTargetOrgan() != null) {
			this.targetOrgan = z.getTargetOrgan().getName();
		}
		// 如果已经提交流程，并且是起始节点，设置流程状态为99，不能编辑和删除
		if (z.getFlowRecord() != null && z.getStatus() == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_STATE) {
			this.status = "99";
		} else {
			this.status = String.valueOf(z.getStatus());
		}
		this.statusName = convertState(z.getStatus());
	}
	
	public String convertState(int state) {
		
		if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_STATE) {
			return "待提交转任申请";
		} else if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_TRIAL) {
			return "待上级单位审核";
		} else if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_TRIAL_1) {
			return "待区人事主管部门一级审核";
		} else if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_TRIAL_2) {
			return "待区人事主管部门二级审核";
		} else if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_TRIAL_3) {
			return "待区人事主管部门三级审核";
		} else if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_TRIAL_4) {
			return "待区人事主管部门四级审核";
		} else if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_AGREE) {
			return "待转出单位同意";
		} else if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_PRINT) {
			return "待区人事主管部门打印电子介绍信";
		} else if (state == ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_FINISH) {
			return "人员信息已入库";
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
	
	public String getAreaType() {
		
		return areaType;
	}
	
	public void setAreaType(String areaType) {
		
		this.areaType = areaType;
	}
	
	public String getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(String sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public String getAllowWeaveNum() {
		
		return allowWeaveNum;
	}
	
	public void setAllowWeaveNum(String allowWeaveNum) {
		
		this.allowWeaveNum = allowWeaveNum;
	}
	
	public String getRealNum() {
		
		return realNum;
	}
	
	public void setRealNum(String realNum) {
		
		this.realNum = realNum;
	}
	
	public String getThisYearLackWeaveNum() {
		
		return thisYearLackWeaveNum;
	}
	
	public void setThisYearLackWeaveNum(String thisYearLackWeaveNum) {
		
		this.thisYearLackWeaveNum = thisYearLackWeaveNum;
	}
	
	public String getSumNumber() {
		
		return sumNumber;
	}
	
	public void setSumNumber(String sumNumber) {
		
		this.sumNumber = sumNumber;
	}
	
	public String getEnterTheUnitDate() {
		
		return enterTheUnitDate;
	}
	
	public void setEnterTheUnitDate(String enterTheUnitDate) {
		
		this.enterTheUnitDate = enterTheUnitDate;
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
	
	public String getTargetOrgan() {
		
		return targetOrgan;
	}
	
	public void setTargetOrgan(String targetOrgan) {
		
		this.targetOrgan = targetOrgan;
	}
	
}
