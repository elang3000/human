/**   
 * Copyright © 2019 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: QuickQueryParam.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.dto.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2019年1月7日 下午4:01:30 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2019年1月7日 下午4:01:30 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.dto.analysis;

import java.io.Serializable;

/** 
 * @ClassName: QuickQueryParam 
 * @Description: 综合查询条件参数
 * @author: lihao
 * @date: 2019年1月7日 下午4:01:30
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class QuickQueryParam implements Serializable{

	private static final long serialVersionUID = -6075797832316814832L;
	
	private String name;//姓名
	private String departName;//部门名称
	private String qucikA;//快捷查询学历
	private String qucikB;//快捷查询年龄
	private String qucikC;//快捷查询性别
	private String qucikD;//快捷查询党派
	private String qucikE;//快捷查询民族
	private String qucikF;//快捷查询专业
	private String qucikG;//快捷查询学校种类
	private String qucikH;//快捷查询职务层次
	private String qucikI;//快捷查询人员状态
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
	 * @return the qucikA
	 */
	public String getQucikA() {
		return qucikA;
	}
	/**
	 * @param qucikA the qucikA to set
	 */
	public void setQucikA(String qucikA) {
		this.qucikA = qucikA;
	}
	/**
	 * @return the qucikB
	 */
	public String getQucikB() {
		return qucikB;
	}
	/**
	 * @param qucikB the qucikB to set
	 */
	public void setQucikB(String qucikB) {
		this.qucikB = qucikB;
	}
	/**
	 * @return the qucikC
	 */
	public String getQucikC() {
		return qucikC;
	}
	/**
	 * @param qucikC the qucikC to set
	 */
	public void setQucikC(String qucikC) {
		this.qucikC = qucikC;
	}
	/**
	 * @return the qucikD
	 */
	public String getQucikD() {
		return qucikD;
	}
	/**
	 * @param qucikD the qucikD to set
	 */
	public void setQucikD(String qucikD) {
		this.qucikD = qucikD;
	}
	/**
	 * @return the qucikE
	 */
	public String getQucikE() {
		return qucikE;
	}
	/**
	 * @param qucikE the qucikE to set
	 */
	public void setQucikE(String qucikE) {
		this.qucikE = qucikE;
	}
	/**
	 * @return the qucikF
	 */
	public String getQucikF() {
		return qucikF;
	}
	/**
	 * @param qucikF the qucikF to set
	 */
	public void setQucikF(String qucikF) {
		this.qucikF = qucikF;
	}
	/**
	 * @return the qucikG
	 */
	public String getQucikG() {
		return qucikG;
	}
	/**
	 * @return the qucikI
	 */
	public String getQucikI() {
		return qucikI;
	}
	/**
	 * @param qucikI the qucikI to set
	 */
	public void setQucikI(String qucikI) {
		this.qucikI = qucikI;
	}
	/**
	 * @param qucikG the qucikG to set
	 */
	public void setQucikG(String qucikG) {
		this.qucikG = qucikG;
	}
	/**
	 * @return the qucikH
	 */
	public String getQucikH() {
		return qucikH;
	}
	/**
	 * @param qucikH the qucikH to set
	 */
	public void setQucikH(String qucikH) {
		this.qucikH = qucikH;
	}
}
