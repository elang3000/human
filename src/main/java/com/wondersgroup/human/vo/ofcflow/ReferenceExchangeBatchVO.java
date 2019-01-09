/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ReferenceExchangeBatchVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: TODO
 * 创建人: GP
 * 创建时间: 2018年12月17日 上午11:22:26
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2018年12月17日 上午11:22:26
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import javax.persistence.Column;

import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeBatch;

/**
 * @ClassName: ReferenceExchangeBatchVO
 * @Description: TODO 参公交流VO
 * @author: GP
 * @date: 2018年12月17日 上午11:22:26
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ReferenceExchangeBatchVO {
	
	public ReferenceExchangeBatchVO() {
	}
	/**
	 * 主键ID
	 */
	private String id;
	
	/**
	 * 交流人员来源单位（源单位）
	 */
	private String sourceOrgan;
	
	/**
	 * 交流区域 本区、外区
	 */
	private String areaType;
	
	/**
	 * @fieldName: allowWeaveNum
	 * @fieldType: Integer
	 * @Description: 核定编制数
	 */
	private String allowWeaveNum;
	
	/**
	 * @fieldName: realNum
	 * @fieldType: Integer
	 * @Description: 实有人数
	 */
	private String realNum;
	
	/**
	 * 交流总人数
	 */
	private String sumNumber;
	
	/**
	 * @fieldName: thisYearLackWeaveNum
	 * @fieldType: Integer
	 * @Description: 机构缺编数
	 */
	private String thisYearLackWeaveNum;
	
	/**
	 * 申请日期
	 */
	private String requestDate;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 状态名称
	 */
	private String statusName;
	
	/**
	 * 初始化VO赋值
	 * @Title:ReferenceExchangeBatchVO
	 * @Description:TODO 
	 * @param referenceExchangeBatch
	 */
	public ReferenceExchangeBatchVO(ReferenceExchangeBatch referenceExchangeBatch) {
		this.id=referenceExchangeBatch.getId();
		if ("1".equals(referenceExchangeBatch.getAreaType())) {
			this.areaType = "本区参公交流";
		} else {
			this.areaType = "外区参公交流";
		}
		if (referenceExchangeBatch.getSourceOrgan()!=null) {
			this.sourceOrgan=referenceExchangeBatch.getSourceOrgan().getAllName();
		}
		else{
			if("2".equals(referenceExchangeBatch.getAreaType()))
				this.sourceOrgan="外区单位";
		}
		if (referenceExchangeBatch.getUnitPlanningTotal() != null) {
			this.allowWeaveNum = String.valueOf(referenceExchangeBatch.getUnitPlanningTotal());
		}
		if (referenceExchangeBatch.getActualNumber() != null) {
			this.realNum = String.valueOf(referenceExchangeBatch.getActualNumber());
		}
		if (referenceExchangeBatch.getVacancyExcessNumber() != null) {
			this.thisYearLackWeaveNum = String.valueOf(referenceExchangeBatch.getVacancyExcessNumber());
		}
		if (referenceExchangeBatch.getSumNumber() != null) {
			this.sumNumber = String.valueOf(referenceExchangeBatch.getSumNumber());
		}
		this.status=referenceExchangeBatch.getStatus().toString();
		this.statusName = convertState(referenceExchangeBatch.getStatus());
		if (referenceExchangeBatch.getApplyDate()!=null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.requestDate=sdf.format(referenceExchangeBatch.getApplyDate());
		}
		else
			this.requestDate="";
	}
	
	/**
	 * 设置状态名称
	 * @Title: convertState 
	 * @Description: TODO	设置状态名称
	 * @param status
	 * @return
	 * @return: String
	 */
	private String convertState(int status) {
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_STATE) { return "待提交参公交流申请"; }
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_TRIAL) { return "待上级单位审核"; }
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_TRIAL_1) { return "待区人事主管部门一级审核"; }
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_TRIAL_2) { return "待区人事主管部门二级审核"; }
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_TRIAL_3) { return "待区人事主管部门三级审核"; }
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_TRIAL_4) { return "待区人事主管部门四级审核"; }
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_AGREE) { return "待参公单位同意"; }
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_PRINT) { return "待区人事主管部门打印电子介绍信"; }
		if (status == ReferenceExchangeBatch.STATUS_EXCHANGE_FINISH) { return "已完成申请"; }
		return "默认状态";
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

	public String getAreaType() {
		
		return areaType;
	}

	
	public void setAreaType(String areaType) {
		
		this.areaType = areaType;
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

	
	public String getSumNumber() {
		
		return sumNumber;
	}

	
	public void setSumNumber(String sumNumber) {
		
		this.sumNumber = sumNumber;
	}

	
	public String getThisYearLackWeaveNum() {
		
		return thisYearLackWeaveNum;
	}

	
	public void setThisYearLackWeaveNum(String thisYearLackWeaveNum) {
		
		this.thisYearLackWeaveNum = thisYearLackWeaveNum;
	}

	
	public String getRequestDate() {
		
		return requestDate;
	}

	
	public void setRequestDate(String requestDate) {
		
		this.requestDate = requestDate;
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
	
	
}
