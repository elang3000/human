/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerRecord.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.dto.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月9日 下午3:28:42 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月9日 下午3:28:42 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.dto.ofc;

import java.util.Date;

/**
 * @ClassName: ManagerRecord
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日下午3:28:42 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public class ManagerRecordDTO {

	/**
	 * @fieldName: humanID
	 * @fieldType: java.lang.String
	 * @Description: 人事信息ID。
	 */
	private String servantId;

	/**
	 * @fieldName: recordType
	 * @fieldType: java.lang.String
	 * @Description: 记录类型名称。
	 */
	private String recordType;

	/**
	 * @fieldName: unitType
	 * @fieldType: java.lang.String
	 * @Description: 进出管理类型。(0：进、1：出、2：管理)
	 */
	private Integer itemType;

	/**
	 * @fieldName: recordTime
	 * @fieldType: java.util.DATE
	 * @Description: 记录时间。
	 */
	private Date recordTime;

	/**
	 * @fieldName: businessEntityId
	 * @fieldType: java.lang.String
	 * @Description: 业务实例ID。
	 */
	private String businessEntityId;

	/**
	 * @fieldName: businessEntityTable
	 * @fieldType: java.lang.String
	 * @Description: 业务实例表。
	 */
	private String businessEntityTable;

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
	 * @fieldName: ext6
	 * @fieldType: java.lang.Integer
	 * @Description: 扩展6
	 */
	private Integer ext6;

	/**
	 * @fieldName: humanID
	 * @fieldType: java.lang.String
	 * @Description: 当时单位ID。
	 */
	private String organId;
	
	/**
	 * @fieldName: humanID
	 * @fieldType: java.lang.String
	 * @Description: 当时单位名。
	 */
	private String organName;
	
	public ManagerRecordDTO(String servantId,String recordType){
		super();
		this.servantId = servantId;
		this.recordType = recordType;
	}
	
	/**
	 * @return the servantId
	 */
	public String getServantId() {
		return servantId;
	}

	/**
	 * @param servantId the servantId to set
	 */
	public void setServantId(String servantId) {
		this.servantId = servantId;
	}

	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the itemType
	 */
	public Integer getItemType() {
		return itemType;
	}

	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the recordTime
	 */
	public Date getRecordTime() {
		return recordTime;
	}

	/**
	 * @param recordTime the recordTime to set
	 */
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	/**
	 * @return the businessEntityId
	 */
	public String getBusinessEntityId() {
		return businessEntityId;
	}

	/**
	 * @param businessEntityId the businessEntityId to set
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
	 * @param businessEntityTable the businessEntityTable to set
	 */
	public void setBusinessEntityTable(String businessEntityTable) {
		this.businessEntityTable = businessEntityTable;
	}

	/**
	 * @return the ext1
	 */
	public String getExt1() {
		return ext1;
	}

	/**
	 * @param ext1 the ext1 to set
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
	 * @param ext2 the ext2 to set
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
	 * @param ext3 the ext3 to set
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
	 * @param ext4 the ext4 to set
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
	 * @param ext5 the ext5 to set
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
	 * @param ext6 the ext6 to set
	 */
	public void setExt6(Integer ext6) {
		this.ext6 = ext6;
	}

	/**
	 * @return the organId
	 */
	public String getOrganId() {
		return organId;
	}

	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(String organId) {
		this.organId = organId;
	}

	/**
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}
}
