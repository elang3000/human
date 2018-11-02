/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RetireServantVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月26日 上午10:45:21 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月26日 上午10:45:21 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

/**
 * @ClassName: RetireServantVO
 * @Description: TODO
 * @author: lihao
 * @date: 2018年6月26日上午10:45:21 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public class RetireServantVO {
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
	 * @fieldName: jobLevel
	 * @fieldType: java.lang.String
	 * @Description: 现职务级别
	 */
	private String jobLevel;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	private String postName;

	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	private String postAttributeName;
	
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
	 * @return the jobLevel
	 */
	public String getJobLevel() {
		return jobLevel;
	}

	/**
	 * @param jobLevel the jobLevel to set
	 */
	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	/**
	 * @return the postName
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * @param postName the postName to set
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}

	/**
	 * @return the postAttributeName
	 */
	public String getPostAttributeName() {
		return postAttributeName;
	}

	/**
	 * @param postAttributeName the postAttributeName to set
	 */
	public void setPostAttributeName(String postAttributeName) {
		this.postAttributeName = postAttributeName;
	}
}
