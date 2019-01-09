/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PunishVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc 
 * 描述: 惩戒vo
 * 创建人: lihao   
 * 创建时间: 2018年7月12日 上午10:43:53 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年7月12日 上午10:43:53 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.RewardAndPunish;

/**
 * @ClassName: PunishVO
 * @Description: 惩戒vo
 * @author: lihao
 * @date: 2018年7月12日上午10:43:53 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public class PunishVO {

	private String id;
	
	/**
	 * @fieldName: 
	 * @fieldType: java.lang.String
	 * @Description: 惩戒人员姓名
	 */
	private String name;
	
	/**
	 * @fieldName: 
	 * @fieldType: java.lang.String
	 * @Description: 惩戒人员姓名身份证
	 */
	private String cardNo;
	
	/**
	 * @fieldName: 
	 * @fieldType: java.lang.String
	 * @Description: 惩戒人员姓名性别
	 */
	private String sex;
	/**
	 * @fieldName: 人员受处分决定原始文件
	 * @fieldType: java.lang.String
	 * @Description: 决定对该人进行处分的原始文件号。
	 */
	private String punishNo;
	
	/**
	 * @fieldName: 人员受奖惩类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人因某种业绩成就受到党和国家某级组织给予的奖励或惩处的级别。DM019
	 */
	private String category;

	/**
	 * @fieldName: 人员受惩戒名称
	 * @fieldType: java.lang.String
	 * @Description: 该人受到党和国家某级组织给予的某类惩戒的名称。
	 */
	private String punishName;

	/**
	 * @fieldName: 人员受惩戒批准日期
	 * @fieldType: java.util.Date
	 * @Description: 党和国家某级组织批准给予该人该处分的日期。GB/T 7408-2005
	 */
	private String punishApprovalDate;

	/**
	 * @fieldName: 人员受惩戒批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准给予该人该处分的组织单位名称。
	 */
	private String punishApprovalUnitName;

	/**
	 * @fieldName: 人员受惩戒原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 因何种过失、错误受到党和国家某级组织给予该人的处罚。DM021
	 */
	private String punishReason;

	/**
	 * @fieldName: 人员受惩戒说明
	 * @fieldType: java.lang.String
	 * @Description: 该人因何种过失、错误受到该惩戒及其解除、撤销等有关事项的说明。
	 */
	private String punishDescription;

	/**
	 * @fieldName: 人员受惩戒解除日期
	 * @fieldType: java.util.Date
	 * @Description: 解除该人该惩戒的日期。GB/T 7408-2005
	 */
	private String punishRevokeDate;

	/**
	 * @fieldName: 是否监察机关直接给予惩戒的标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 对触犯刑律的犯罪行为，由监察机关直接给予的处罚。DM063
	 */
	private String punishIdentification;

	/**
	 * @fieldName: 是否受处分标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否获得处分的标识。DM215
	 */
	private String punishmentIdentification;
	
	public PunishVO(){
		
	}
	
	public PunishVO(RewardAndPunish r){
		this.id = r.getId();
		this.punishNo = r.getPunishNo();
		this.punishName = r.getPunishName();
		if (r.getPunishApprovalDate() != null) {
			this.punishApprovalDate = new SimpleDateFormat("yyyy-MM-dd").format(r.getPunishApprovalDate());
		}
		this.punishApprovalUnitName = r.getPunishApprovalUnitName();
		if (r.getPunishReason() != null) {
			this.punishReason = r.getPunishReason().getName();
		}
		this.punishDescription = r.getPunishDescription();
		if (r.getPunishRevokeDate() != null) {
			this.punishRevokeDate = new SimpleDateFormat("yyyy-MM-dd").format(r.getPunishRevokeDate());
		}
		if (r.getPunishIdentification() != null) {
			this.punishIdentification = r.getPunishIdentification().getName();
		}
		if (r.getPunishmentIdentification()!= null) {
			this.punishmentIdentification = r.getPunishmentIdentification().getName();
		}
		if(r.getServant()!= null){
			this.name = r.getServant().getName();
			if(r.getServant().getSex()!=null){
				this.sex=r.getServant().getSex().getName();
			}
			this.cardNo=r.getServant().getCardNoView();
		}
		if (r.getCategory() != null) {
			this.category = r.getCategory().getName();
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
	 * @return the punishNo
	 */
	public String getPunishNo() {
		return punishNo;
	}

	/**
	 * @param punishNo the punishNo to set
	 */
	public void setPunishNo(String punishNo) {
		this.punishNo = punishNo;
	}

	/**
	 * @return the punishName
	 */
	public String getPunishName() {
		return punishName;
	}

	/**
	 * @param punishName the punishName to set
	 */
	public void setPunishName(String punishName) {
		this.punishName = punishName;
	}

	/**
	 * @return the punishApprovalDate
	 */
	public String getPunishApprovalDate() {
		return punishApprovalDate;
	}

	/**
	 * @param punishApprovalDate the punishApprovalDate to set
	 */
	public void setPunishApprovalDate(String punishApprovalDate) {
		this.punishApprovalDate = punishApprovalDate;
	}

	/**
	 * @return the punishApprovalUnitName
	 */
	public String getPunishApprovalUnitName() {
		return punishApprovalUnitName;
	}

	/**
	 * @param punishApprovalUnitName the punishApprovalUnitName to set
	 */
	public void setPunishApprovalUnitName(String punishApprovalUnitName) {
		this.punishApprovalUnitName = punishApprovalUnitName;
	}

	/**
	 * @return the punishReason
	 */
	public String getPunishReason() {
		return punishReason;
	}

	/**
	 * @param punishReason the punishReason to set
	 */
	public void setPunishReason(String punishReason) {
		this.punishReason = punishReason;
	}

	/**
	 * @return the punishDescription
	 */
	public String getPunishDescription() {
		return punishDescription;
	}

	/**
	 * @param punishDescription the punishDescription to set
	 */
	public void setPunishDescription(String punishDescription) {
		this.punishDescription = punishDescription;
	}

	/**
	 * @return the punishRevokeDate
	 */
	public String getPunishRevokeDate() {
		return punishRevokeDate;
	}

	/**
	 * @param punishRevokeDate the punishRevokeDate to set
	 */
	public void setPunishRevokeDate(String punishRevokeDate) {
		this.punishRevokeDate = punishRevokeDate;
	}

	/**
	 * @return the punishIdentification
	 */
	public String getPunishIdentification() {
		return punishIdentification;
	}

	/**
	 * @param punishIdentification the punishIdentification to set
	 */
	public void setPunishIdentification(String punishIdentification) {
		this.punishIdentification = punishIdentification;
	}

	/**
	 * @return the punishmentIdentification
	 */
	public String getPunishmentIdentification() {
		return punishmentIdentification;
	}

	/**
	 * @param punishmentIdentification the punishmentIdentification to set
	 */
	public void setPunishmentIdentification(String punishmentIdentification) {
		this.punishmentIdentification = punishmentIdentification;
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

	
	public String getCategory() {
		
		return category;
	}

	
	public void setCategory(String category) {
		
		this.category = category;
	}
	
}
