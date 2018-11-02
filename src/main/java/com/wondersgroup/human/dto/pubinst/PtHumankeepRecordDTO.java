/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: HumankeepRecordDTO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.dto.record 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月5日 下午6:24:09 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月5日 下午6:24:09 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.dto.pubinst;

import java.util.Date;

/**
 * @ClassName: HumankeepRecordDTO
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月5日
 *        下午6:24:09 @version [版本号, YYYY-MM-DD] @see       [相关类/方法] @since     [产品/模块版本]
 */
public class PtHumankeepRecordDTO {

	/**
	 * @fieldName: humanId
	 * @fieldType: java.lang.String
	 * @Description: 人事信息ID
	 */
	private String humanId;

	/**
	 * @fieldName: unitType
	 * @fieldType: java.lang.String
	 * @Description: 人事单位类型
	 */
	private String unitType;

	/**
	 * @fieldName: recordType
	 * @fieldType: java.lang.String
	 * @Description: 备案类型
	 */
	private String recordType;

	/**
	 * @fieldName: businessEntityId
	 * @fieldType: java.lang.String
	 * @Description: 业务实例ID
	 */
	private String businessEntityId;

	/**
	 * @fieldName: businessEntityTable
	 * @fieldType: java.lang.String
	 * @Description: 业务实例表名称
	 */
	private String businessEntityTable;

	/**
	 * @fieldName: returned
	 * @fieldType: java.lang.String
	 * @Description: 是否退回备案操作
	 */
	private String returned;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 描述信息
	 */
	private String description;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 扩展1
	 */
	private String ext1;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 扩展2
	 */
	private String ext2;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 扩展3
	 */
	private String ext3;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.Date
	 * @Description: 扩展4
	 */
	private Date ext4;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.Date
	 * @Description: 扩展5
	 */
	private Date ext5;

	/**
	 * @fieldType: java.lang.Integer
	 * @Description: 扩展6
	 */
	private Integer ext6;

	public PtHumankeepRecordDTO() {

	}

	/**
	 * @return the humanId
	 */
	public String getHumanId() {
		return humanId;
	}

	/**
	 * @param humanId
	 *            the humanId to set
	 */
	public void setHumanId(String humanId) {
		this.humanId = humanId;
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
	 * @return the businessEntityId
	 */
	public String getBusinessEntityId() {
		return businessEntityId;
	}

	/**
	 * @param businessEntityId
	 *            the businessEntityId to set
	 */
	public void setBusinessEntityId(String businessEntityId) {
		this.businessEntityId = businessEntityId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the ext1
	 */
	public String getExt1() {
		return ext1;
	}

	/**
	 * @param ext1
	 *            the ext1 to set
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	/**
	 * @return the ext2
	 */
	public String getExt2() {
		return ext2;
	}

	/**
	 * @param ext2
	 *            the ext2 to set
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	/**
	 * @return the ext3
	 */
	public String getExt3() {
		return ext3;
	}

	/**
	 * @param ext3
	 *            the ext3 to set
	 */
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	/**
	 * @return the ext4
	 */
	public Date getExt4() {
		return ext4;
	}

	/**
	 * @param ext4
	 *            the ext4 to set
	 */
	public void setExt4(Date ext4) {
		this.ext4 = ext4;
	}

	/**
	 * @return the ext5
	 */
	public Date getExt5() {
		return ext5;
	}

	/**
	 * @param ext5
	 *            the ext5 to set
	 */
	public void setExt5(Date ext5) {
		this.ext5 = ext5;
	}

	/**
	 * @return the ext6
	 */
	public Integer getExt6() {
		return ext6;
	}

	/**
	 * @param ext6
	 *            the ext6 to set
	 */
	public void setExt6(Integer ext6) {
		this.ext6 = ext6;
	}

}
