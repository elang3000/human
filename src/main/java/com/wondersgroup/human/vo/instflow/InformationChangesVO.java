package com.wondersgroup.human.vo.instflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.instflow.InformationChange;

public class InformationChangesVO {

	private String id;
	
	//职级代码
	private CodeInfo code;
	//职级名称
	private String name;
	//简历描述
	private String introduce;
	
	//职级终止日期
	private String approvalDate;
	//职级批准日期
	private String endDate;
	
	//职级文号
	private String approvalNo;
	
	//职级标识
	//private CodeInfo currentIdentification;
	
	//审核状态
	private String status;
	
	
	
	private String sex;
	private String name1;//姓名
	private String cardNo;
	
	public InformationChangesVO(){
		
	}
	
	public  InformationChangesVO(InformationChange in){
	//	this.departName = in.getPublicInstitution().getName();
		this.name1 =in.getPublicInstitution().getName();
		this.cardNo = in.getPublicInstitution().getCardNoView();
		if(in.getPublicInstitution().getSex() != null){
			this.sex = in.getPublicInstitution().getSex().getName();
		}
		
		this.id =in.getId();
		this.code = in.getCode();
		this.name = in.getName();
		this.introduce = in.getIntroduce();
		this.approvalNo = in.getApprovalNo();
	//	this.currentIdentification = in.getCurrentIdentification();
		
		if (in.getApprovalDate() != null) {
			this.approvalDate = new SimpleDateFormat("yyyy-MM-dd").format(in.getApprovalDate());
		}
		if(in.getEndDate() != null){
			this.endDate = new SimpleDateFormat("yyyy-MM-dd").format(in.getEndDate());
		}
		
		
		this.status=converState(in.getPlanState());
		
		
	}

	private String converState(Integer state) {
		if(state == null){
			return "流程办结";
		}else if(state == 1){
			return "待上级单位初审";
			
		}else if (state == 2){
		
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

	

	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CodeInfo getCode() {
		return code;
	}

	public void setCode(CodeInfo code) {
		this.code = code;
	}

	public String getApprovalNo() {
		return approvalNo;
	}

	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}

	/*public CodeInfo getCurrentIdentification() {
		return currentIdentification;
	}

	public void setCurrentIdentification(CodeInfo currentIdentification) {
		this.currentIdentification = currentIdentification;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	

	

	
	
	
	
}
