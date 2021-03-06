/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignServantQueryParam.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.dto.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月7日 下午3:13:09 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月7日 下午3:13:09 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.dto.ofcflow;

import java.io.Serializable;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/** 
 * @ClassName: ResignServantQueryParam 
 * @Description: 处分查询参数
 * @author: lihao
 * @date: 2018年9月7日 下午3:13:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class PunishServantQueryParam implements Serializable {

	private static final long serialVersionUID = 5300637880805815479L;
	
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
	 * @fieldName: punishFileName
	 * @fieldType: java.lang.String
	 * @Description: 处分文件号
	 */
	private String punishFileName;
	
	/**
	 * @fieldName: punishReason
	 * @fieldType: java.lang.String
	 * @Description: 处分原因（A14 A14317） DM021
	 */
	private CodeInfo punishReason;

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
	 * @return the punishReason
	 */
	public CodeInfo getPunishReason() {
		return punishReason;
	}

	/**
	 * @param punishReason the punishReason to set
	 */
	public void setPunishReason(CodeInfo punishReason) {
		this.punishReason = punishReason;
	}
}
