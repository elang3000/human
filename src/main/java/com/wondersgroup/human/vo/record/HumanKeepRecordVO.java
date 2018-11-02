/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: HumanKeepRecordVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.record
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月29日 下午5:17:20
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月29日 下午5:17:20
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.vo.record;

/**
 * @ClassName: HumanKeepRecordVO
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年5月29日
 *        下午5:17:20 @version [版本号, YYYY-MM-DD] @see       [相关类/方法] @since     [产品/模块版本]
 */
public class HumanKeepRecordVO {

	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名
	 */
	private String name;

	/**
	 * @fieldName: sex
	 * @fieldType: java.lang.String
	 * @Description: 性别
	 */
	private String sex;

	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 身份证号
	 */
	private String cardNo;

	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 单位部门
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
	 * @fieldName: recordType
	 * @fieldType: java.lang.String
	 * @Description: 备案类型
	 */
	private String recordType;

	/**
	 * @fieldName: returned
	 * @fieldType: java.lang.String
	 * @Description: 备案状态
	 */
	private String returned;

	/**
	 * @fieldName: unitType
	 * @fieldType: java.lang.String
	 * @Description: 人事单位类型
	 */
	private String unitType;

	/**
	 * @fieldName: businessEntityTable
	 * @fieldType: java.lang.String
	 * @Description: 业务实例表名称
	 */
	private String businessEntityTable;

	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: 备案ID
	 */
	private String id;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
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
	 * @param sex
	 *            the sex to set
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
	 * @param cardNo
	 *            the cardNo to set
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
	 * @param departName
	 *            the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return the postName
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * @param postName
	 *            the postName to set
	 */
	public void setPostName(String postName) {
		this.postName = postName;
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

	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType
	 *            the recordType to set
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the returned
	 */
	public String getReturned() {
		return returned;
	}

	/**
	 * @param returned
	 *            the returned to set
	 */
	public void setReturned(String returned) {
		this.returned = returned;
	}

	/**
	 * @return the unitType
	 */
	public String getUnitType() {
		return unitType;
	}

	/**
	 * @param unitType
	 *            the unitType to set
	 */
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	/**
	 * @return the businessEntityTable
	 */
	public String getBusinessEntityTable() {
		return businessEntityTable;
	}

	/**
	 * @param businessEntityTable
	 *            the businessEntityTable to set
	 */
	public void setBusinessEntityTable(String businessEntityTable) {
		this.businessEntityTable = businessEntityTable;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
