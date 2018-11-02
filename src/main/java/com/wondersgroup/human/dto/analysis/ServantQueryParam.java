/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantQueryParam.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.dto.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年8月31日 下午3:03:17 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年8月31日 下午3:03:17 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.dto.analysis;

import java.io.Serializable;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: ServantQueryParam
 * @Description: 综合查询人员DTO
 * @author: lihao
 * @date: 2018年8月31日下午3:03:17 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public class ServantQueryParam implements Serializable {

	private static final long serialVersionUID = -1966563737214739436L;
	
	private String name;//姓名
	private String cardNo;//身份证号
	private CodeInfo sex;//性别
	private String departName;//部门名称
	private String ageMax;//年龄上限
	private String ageMin;//年龄下限
	private String code1;
	private String code2;
	private String code3;
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
	 * @return the ageMax
	 */
	public String getAgeMax() {
		return ageMax;
	}
	/**
	 * @param ageMax the ageMax to set
	 */
	public void setAgeMax(String ageMax) {
		this.ageMax = ageMax;
	}
	/**
	 * @return the ageMin
	 */
	public String getAgeMin() {
		return ageMin;
	}
	/**
	 * @param ageMin the ageMin to set
	 */
	public void setAgeMin(String ageMin) {
		this.ageMin = ageMin;
	}
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
