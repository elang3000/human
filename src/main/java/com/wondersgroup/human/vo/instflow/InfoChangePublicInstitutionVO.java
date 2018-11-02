package com.wondersgroup.human.vo.instflow;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.human.bo.instflow.InfoChangePublicInstitution;

public class InfoChangePublicInstitutionVO {

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
	
	
	
	//人事关系所在单位名称
	private String departName;
	//现职务层次
	private String postName;
	//人员管理状态 ;该人在管理上是在职或离退状态
	private String isOnHold;

	
	public InfoChangePublicInstitutionVO(){
		
	}
	
	
	public InfoChangePublicInstitutionVO(InfoChangePublicInstitution ip){
		
		this.id =ip.getId();
		this.name=ip.getName();
		if(ip.getSex() != null){
			this.sex=ip.getSex().getName();
		}
		this.cardNo=ip.getCardNo();
		this.departName=ip.getDepartName();
		this.postName=ip.getNowPostName();
		if(ip.getIsOnHold()!=null){
			this.isOnHold=ip.getIsOnHold().getName();
		}
		
		
	}
	
	
}
