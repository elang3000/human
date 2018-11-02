/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: TransferringSummeryVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 选调交流汇总信息表 VO
 * 创建人: tzy
 * 创建时间: 2018年8月16日 上午11:29:10
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月16日 上午11:29:10
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.TransferringSummery;

/**
 * @ClassName: TransferringSummeryVO
 * @Description: 选调交流汇总信息表 VO
 * @author: tzy
 * @date: 2018年8月16日 上午11:29:10
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class TransferringSummeryVO {
	
	/**
	 * @Description: ID
	 */
	private String id;
	
	/**
	 * @Description: 选调机关
	 */
	private String recruitOrgan;
	
	/**
	 * @Description: 核定编制数
	 */
	private String allowWeaveNum;
	
	/**
	 * @Description: 实有人数
	 */
	private String realNum;
	
	/**
	 * @Description: 本年度缺编人员
	 */
	private String thisYearLackWeaveNum;
	
	/**
	 * @Description: 计划减员人数
	 */
	private String planCutNum;
	
	/**
	 * @Description: 计划选调人数
	 */
	private String planEmployNum;
	
	/**
	 * @Description: 状态
	 */
	private String status;
	
	/**
	 * @Description: 编制类型
	 */
	private String recuritType;
	
	public TransferringSummeryVO() {
		
	}
	
	public TransferringSummeryVO(TransferringSummery t) {
		this.id = t.getId();
		if (t.getRecruitOrgan() != null) {
			this.recruitOrgan = t.getRecruitOrgan().getAllName();
		}
		this.allowWeaveNum = String.valueOf(t.getAllowWeaveNum());
		this.realNum = String.valueOf(t.getRealNum());
		this.thisYearLackWeaveNum = String.valueOf(t.getThisYearLackWeaveNum());
		this.planCutNum = String.valueOf(t.getPlanCutNum());
		this.planEmployNum = String.valueOf(t.getPlanEmployNum());
		if(t.getRecuritType()!=null){
			this.recuritType = t.getRecuritType().getName();
		}
		if (t.getStatus() == 0) {
			this.status = "待提交";
		} else if (t.getStatus() == 1) {
			this.status = "待审批";
		} else if (t.getStatus() == 2) {
			this.status = "审批通过";
		} else if (t.getStatus() == 3) {
			this.status = "审批不通过";
		} else if (t.getStatus() == 4) {
			this.status = "待人员上报";
		} else if (t.getStatus() == 5) {
			this.status = "待人员审批";
		} else if (t.getStatus() == 6) {
			this.status = "人员审批通过";
		} else if (t.getStatus() == 7) {
			this.status = "人员审批不通过";
		} else if (t.getStatus() == 8) {
			this.status = "归档";
		} else if (t.getStatus() == 9) {
			this.status = "待人员导入";
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getRecruitOrgan() {
		
		return recruitOrgan;
	}
	
	public void setRecruitOrgan(String recruitOrgan) {
		
		this.recruitOrgan = recruitOrgan;
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
	
	public String getPlanCutNum() {
		
		return planCutNum;
	}
	
	public void setPlanCutNum(String planCutNum) {
		
		this.planCutNum = planCutNum;
	}
	
	public String getPlanEmployNum() {
		
		return planEmployNum;
	}
	
	public void setPlanEmployNum(String planEmployNum) {
		
		this.planEmployNum = planEmployNum;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public String getRecuritType() {
		
		return recuritType;
	}
	
	public void setRecuritType(String recuritType) {
		
		this.recuritType = recuritType;
	}
}
