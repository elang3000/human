/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RecruitEmployPlanVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 招录计划VO
 * 创建人: wangzhanfei
 * 创建时间: 2018年5月30日 上午10:57:27
 * 版本号: V1.0
 * 修改人：wangzhanfei
 * 修改时间：2018年5月30日 上午10:57:27
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan;

/**
 * @ClassName: RecruitEmployPlanVO
 * @Description:招录计划VO
 * @author: wangzhanfei
 * @date: 2018年5月30日 上午10:57:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class RecruitEmployPlanVO {
	
	private String id;
	
	private String yearPlan;
	
	private String recruitOrgan;
	
	private String employOrgan;
	
	private String allowWeaveNum;
	
	private String realNum;
	
	private String thisYearLackWeaveNum;
	
	private String chiefLackWeaveNum;
	
	private String planCutNum;
	
	private String recuritType;
	
	private String remark;
	
	private String planState;
	
	private String planStateSign;
	
	private String planEmployNum;
	
	private String endEmployNum;
	
	private String firstEmployNum;
	
	public RecruitEmployPlanVO() {
		
	}
	
	public RecruitEmployPlanVO(RecruitEmployPlan plan) {
		this.id = plan.getId();
		if(plan.getYearPlan()!=null){
			this.yearPlan = plan.getYearPlan().getName();
		}
		if (plan.getPlanCutNum() != null) {
			this.planCutNum = plan.getPlanCutNum().toString();
		}
		if (StringUtils.isNotBlank(plan.getRemark())) {
			this.remark = plan.getRemark();
		}
		if (plan.getRecuritType() != null) {
			this.recuritType = plan.getRecuritType().getName();
		}
		// OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId())
		if(plan.getRecruitOrgan()!=null){
			this.recruitOrgan = plan.getRecruitOrgan().getName();
		}
		if(plan.getEmployOrgan()!=null){
			this.employOrgan = plan.getEmployOrgan().getName();
		}
		this.allowWeaveNum = String.valueOf(plan.getAllowWeaveNum() == null ? "" : plan.getAllowWeaveNum());
		this.realNum = String.valueOf(plan.getRealNum() == null ? "" : plan.getRealNum());
		this.thisYearLackWeaveNum = String
		        .valueOf(plan.getThisYearLackWeaveNum() == null ? "" : plan.getThisYearLackWeaveNum());
		this.chiefLackWeaveNum = plan.getChiefLackWeaveNum() == null ? "" : String.valueOf(plan.getChiefLackWeaveNum());
		this.planEmployNum = plan.getPlanEmployNum() == null ? "" : String.valueOf(plan.getPlanEmployNum());
		this.firstEmployNum = plan.getFirstEmployNum() == null ? "" : String.valueOf(plan.getFirstEmployNum());
		this.endEmployNum = plan.getEndEmployNum() == null ? "" : String.valueOf(plan.getEndEmployNum());
		this.planStateSign = String.valueOf(plan.getPlanState());
		this.planState = convertState(plan.getPlanState());
	}
	
	public String convertState(int state) {
		
		if (state == 0) {
			return "待提交招录计划";
		} else if (state == 1) {
			return "待上级单位初审";
		} else if (state == 2) {
			return "待区人事主管部门一级初审";
		} else if (state == 3) {
			return "待区人事主管部门二级初审";
		} else if (state == 4) {
			return "待区人事主管部门三级初审";
		} else if (state == 5) {
			return "待区人事主管部门四级初审";
		} else if (state == 6) {
			return "待区编办终审";
		} else if (state == 7) {
			return "待区人事主管部门确认";
		} else if (state == 8) {
			return "区人事主管部门确认通过，待上报职位";
		} else if (state == 9) {
			return "职位上报，待上级单位审核";
		} else if (state == 10) {
			return "职位上报，待区人事主管部门一级审核";
		} else if (state == 11) {
			return "职位上报，待区人事主管部门二级审核";
		} else if (state == 12) {
			return "职位上报，待区人事主管部门三级审核";
		} else if (state == 13) {
			return "职位上报，待区人事主管部门四级审核";
		} else if (state == 14) {
			return "职位上报，区人事主管部门审核通过";
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
	
	public String getYearPlan() {
		
		return yearPlan;
	}
	
	public void setYearPlan(String yearPlan) {
		
		this.yearPlan = yearPlan;
	}
	
	public String getRecruitOrgan() {
		
		return recruitOrgan;
	}
	
	public void setRecruitOrgan(String recruitOrgan) {
		
		this.recruitOrgan = recruitOrgan;
	}
	
	public String getEmployOrgan() {
		
		return employOrgan;
	}
	
	public void setEmployOrgan(String employOrgan) {
		
		this.employOrgan = employOrgan;
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
	
	public String getRecuritType() {
		
		return recuritType;
	}
	
	public void setRecuritType(String recuritType) {
		
		this.recuritType = recuritType;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public String getPlanState() {
		
		return planState;
	}
	
	public void setPlanState(String planState) {
		
		this.planState = planState;
	}
	
	public String getPlanEmployNum() {
		
		return planEmployNum;
	}
	
	public void setPlanEmployNum(String planEmployNum) {
		
		this.planEmployNum = planEmployNum;
	}
	
	public String getChiefLackWeaveNum() {
		
		return chiefLackWeaveNum;
	}
	
	public void setChiefLackWeaveNum(String chiefLackWeaveNum) {
		
		this.chiefLackWeaveNum = chiefLackWeaveNum;
	}
	
	public String getPlanStateSign() {
		
		return planStateSign;
	}
	
	public void setPlanStateSign(String planStateSign) {
		
		this.planStateSign = planStateSign;
	}
	
	public String getEndEmployNum() {
		
		return endEmployNum;
	}
	
	public void setEndEmployNum(String endEmployNum) {
		
		this.endEmployNum = endEmployNum;
	}
	
	public String getFirstEmployNum() {
		
		return firstEmployNum;
	}
	
	public void setFirstEmployNum(String firstEmployNum) {
		
		this.firstEmployNum = firstEmployNum;
	}
	
}
