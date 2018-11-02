/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RetireServantQueryParam.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.dto.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月1日 下午2:27:31 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月1日 下午2:27:31 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.dto.ofcflow;

import java.io.Serializable;

import com.wondersgroup.framework.dict.bo.CodeInfo;

/** 
 * @ClassName: RetireServantQueryParam 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年9月1日 下午2:27:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class RetireServantQueryParam implements Serializable {

	private static final long serialVersionUID = -120598621760721524L;
	
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
	 * @fieldName: sex
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 性别 ,该人的基本生理特征。GB/T 2261.1-2003 人的性别代码
	 */
	private CodeInfo sex;
	/**
	 * @fieldName: age
	 * @fieldType: java.lang.Integer
	 * @Description: 年龄大小
	 */
	private Integer age;
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
	public CodeInfo getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(CodeInfo sex) {
		this.sex = sex;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
}
