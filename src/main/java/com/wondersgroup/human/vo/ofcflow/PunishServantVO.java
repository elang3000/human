/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月6日 下午3:19:21 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月6日 下午3:19:21 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;
import com.wondersgroup.human.bo.ofcflow.PunishServant;

/** 
 * @ClassName: PunishVO 
 * @Description: 处分vo
 * @author: lihao
 * @date: 2018年9月25日 下午3:19:21
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class PunishServantVO {
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
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 公民身份证号 ,公安机关对（公民）居民给出的唯一的、终身不变的法定号码。
	 */
	private String cardNo;
	
	/**
	 * @fieldName: punishCode
	 * @fieldType: java.lang.String
	 * @Description: 处分名称
	 */
	private String punishCode;
	
	/**
	 * @fieldName: punishReason
	 * @fieldType: java.lang.String
	 * @Description: 处分原因（A14 A14317） DM021
	 */
	private String punishReason;
	
	/**
	 * @fieldName: sign
	 * @fieldType: Integer
	 * @Description: 处分接触预警标识 0-未解除  1-已解除
	 */
	private String sign;
	
	/**
	 * @fieldName: punishFileName
	 * @fieldType: java.lang.String
	 * @Description: 处分文件号
	 */
	private String punishFileName;
	
	/**
	 * @fieldName: punishApprovalDate
	 * @fieldType: java.lang.String
	 * @Description: 处分批准时间。
	 */
	private String punishApprovalDate;
	
	/**
	 * @fieldName: punishApprovalDate
	 * @fieldType: java.lang.String
	 * @Description: 处分批准结束时间。
	 */
	private String punishApprovalEndDate;
	
	/**
	 * @fieldName: punishYear
	 * @fieldType: Integer
	 * @Description: 处分期限（年）
	 */
	private Integer punishYear;
	
	/**
	 * @fieldName: status
	 * @fieldType: Integer
	 * @Description: 状态
	 */
	private String status;
	
	private Integer statusSign;
	public PunishServantVO(PunishServant p){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = p.getId();
		this.name = p.getServant().getName();
		this.cardNo = p.getServant().getCardNo();
		
		this.punishCode = p.getPunishCode().getName();
		this.punishReason = p.getPunishReason().getName();
		this.punishFileName = p.getPunishFileName();
		if (p.getPunishApprovalDate() != null) {
			this.punishApprovalDate = sdf.format(p.getPunishApprovalDate());
		}
		if (p.getPunishApprovalEndDate() != null) {
			this.punishApprovalEndDate = sdf.format(p.getPunishApprovalEndDate());
		}
		this.punishYear = p.getPunishYear();
		this.statusSign = p.getStatus();
		this.status = convertState(p.getStatus());
		this.sign = convertSign(p.getSign());
	}
	
	public String convertState(int state) {
		if (state == 1) {
			return "待提交处分信息";
		} else if (state == 2) {
			return "待区人事主管部门";
		} else if (state == 3) {
			return "区人事主管部门已备案确认";
		} else {
			return "";
		}
	}
	
	public String convertSign(int state) {
		if (state == 1) {
			return "处分中";
		} else if (state == 0) {
			return "处分结束";
		}{
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
	 * @return the punishCode
	 */
	public String getPunishCode() {
		return punishCode;
	}

	/**
	 * @param punishCode the punishCode to set
	 */
	public void setPunishCode(String punishCode) {
		this.punishCode = punishCode;
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
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
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

	/**
	 * @return the punishFileName
	 */
	public String getPunishFileName() {
		return punishFileName;
	}

	/**
	 * @param punishFileName the punishFileName to set
	 */
	public void setPunishFileName(String punishFileName) {
		this.punishFileName = punishFileName;
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
	 * @return the punishYear
	 */
	public Integer getPunishYear() {
		return punishYear;
	}

	/**
	 * @param punishYear the punishYear to set
	 */
	public void setPunishYear(Integer punishYear) {
		this.punishYear = punishYear;
	}

	/**
	 * @return the punishApprovalEndDate
	 */
	public String getPunishApprovalEndDate() {
		return punishApprovalEndDate;
	}

	/**
	 * @param punishApprovalEndDate the punishApprovalEndDate to set
	 */
	public void setPunishApprovalEndDate(String punishApprovalEndDate) {
		this.punishApprovalEndDate = punishApprovalEndDate;
	}

	/**
	 * @return the statusSign
	 */
	public Integer getStatusSign() {
		return statusSign;
	}

	/**
	 * @param statusSign the statusSign to set
	 */
	public void setStatusSign(Integer statusSign) {
		this.statusSign = statusSign;
	}
	
	
}
