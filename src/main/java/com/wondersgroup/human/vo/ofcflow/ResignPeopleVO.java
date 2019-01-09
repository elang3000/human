/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignPeopleVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月20日 上午11:27:19 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月20日 上午11:27:19 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.ResignPeople;

/** 
 * @ClassName: ResignPeopleVO 
 * @Description: 辞职人员VO
 * @author: lihao
 * @date: 2018年12月20日 上午11:27:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ResignPeopleVO {
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名 ,在公安户籍管理部门登记注册、人事档案中记载的、正在使用的该人姓名全称。
	 */
	private String name;

	/**
	 * @fieldName: sex
	 * @fieldType: java.lang.String
	 * @Description: 性别。
	 */
	private String sex;

	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 公民身份证号 ,公安机关对（公民）居民给出的唯一的、终身不变的法定号码。
	 */
	private String cardNo;

	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位名称 ,该人人事关系所在的单位名称。
	 */
	private String departName;
	
	/**
	 * @fieldName: reason
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 辞职原因。该人该次被辞职的原因。DM009
	 */
	private String reason;
	
	/**
	 * @fieldName: resignWhereabouts
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 辞职去向。该人辞职后所去单位的性质。DM141
	 */
	private String resignWhereabouts;
	
	/**
	 * @fieldName: resignPlan
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 批次信息
	 */
	private String resignPlan;
	
	/**
	 * @fieldName: status
	 * @fieldType: String
	 * @Description: 状态
	 */
	private String status;

	public ResignPeopleVO(ResignPeople r){
		this.id = r.getId();
		this.name = r.getServant().getName();
		if (r.getServant().getSex() != null) {
			this.sex = r.getServant().getSex().getName();
		}
		this.cardNo = r.getServant().getCardNoView();
		this.departName = r.getServant().getDepartName();
		if(r.getReason()!=null){
			this.reason = r.getReason().getName();
		}else{
			this.reason = "-";
		}
		if(r.getResignWhereabouts()!=null){
			this.resignWhereabouts = r.getResignWhereabouts().getName();
		}else{
			this.resignWhereabouts = "-";
		}
		this.status = convertState(r.getPlan().getStatus());
	}
	
	public String convertState(int state) {
		if (state == 1) {
			return "待提交辞职信息";
		} else if (state == 2) {
			return "待区人事主管部门确认";
		} else if (state == 3) {
			return "待区人事主管部门打印";
		} else if (state == 4) {
			return "区人事主管部门已备案";
		} else {
			return "";
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the resignWhereabouts
	 */
	public String getResignWhereabouts() {
		return resignWhereabouts;
	}

	/**
	 * @param resignWhereabouts the resignWhereabouts to set
	 */
	public void setResignWhereabouts(String resignWhereabouts) {
		this.resignWhereabouts = resignWhereabouts;
	}

	/**
	 * @return the resignPlan
	 */
	public String getResignPlan() {
		return resignPlan;
	}

	/**
	 * @param resignPlan the resignPlan to set
	 */
	public void setResignPlan(String resignPlan) {
		this.resignPlan = resignPlan;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
