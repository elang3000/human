package com.wondersgroup.human.vo.instflow;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.human.bo.instflow.InformationChange;

public class InformationChangesVO {

	private String id;
	
	
	private String name;
	
	private String sex;
	
	//身份证号
	private String cardNo;
	
	//备注信息
	private String remark;
	
	//修改日期
	private Date changeDate;
	
	//修改人员当前单位
	private String nowOrgan;
	
	//审核状态
	private String planState;
	
	public InformationChangesVO(){
		
	}
	
	public  InformationChangesVO(InformationChange in){
		this.id =in.getId();
		if(StringUtils.isNotBlank(in.getRemark())){
			this.remark =in.getRemark();
		}
		this.nowOrgan = in.getNowOrgan().getName();
		this.changeDate=in.getChangeDate();
		this.planState=converState(in.getPlanState());
		
		
	}

	private String converState(int state) {
		if(state == 0){
			return "待提交人员信息变更审核";
		}else if (state == 1){
			return "待区人事部门审核";
		}else{
			return "";
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getPlanState() {
		return planState;
	}

	public void setPlanState(String planState) {
		this.planState = planState;
	}
	
	
	
	
	
}
