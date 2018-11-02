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

package com.wondersgroup.human.vo.instflow;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.bo.ofcflow.RecruitEmployPlan;

/**
 * @ClassName: RecruitEmployPlanVO
 * @Description:人员录入登记VO
 * @author: wangzhanfei
 * @date: 2018年5月30日 上午10:57:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class MemberInfoRegisterVO {
	
	private String id;
	
	private String name;
	
	private String employOrgan;
	
	
	private String remark;
	
	private String planState;
	

	public MemberInfoRegisterVO() {
		
	}
	
	public MemberInfoRegisterVO(MemberInfoRegister member) {
		this.id = member.getId();
		if (StringUtils.isNotBlank(member.getRemark())) {
			this.remark = member.getRemark();
		}
		this.employOrgan = member.getEmployOrgan().getName();
		this.planState = convertState(member.getPlanState());
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

	public String getEmployOrgan() {
		return employOrgan;
	}

	public void setEmployOrgan(String employOrgan) {
		this.employOrgan = employOrgan;
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
	
	
}
