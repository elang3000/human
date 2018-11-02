/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantParam.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.dto.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年10月24日 上午9:35:23 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年10月24日 上午9:35:23 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.dto.analysis;

import java.io.Serializable;

/** 
 * @ClassName: ServantParam 
 * @Description: 综合查询循环条件类
 * @author: lihao
 * @date: 2018年10月24日 上午9:35:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ServantParam implements Serializable{

	private static final long serialVersionUID = -8472096760999357209L;
	private String code1;
	private String code2;
	private String code3;
	/**
	 * @return the code1
	 */
	public String getCode1() {
		return code1;
	}
	/**
	 * @param code1 the code1 to set
	 */
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	/**
	 * @return the code2
	 */
	public String getCode2() {
		return code2;
	}
	/**
	 * @param code2 the code2 to set
	 */
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	/**
	 * @return the code3
	 */
	public String getCode3() {
		return code3;
	}
	/**
	 * @param code3 the code3 to set
	 */
	public void setCode3(String code3) {
		this.code3 = code3;
	}
}
