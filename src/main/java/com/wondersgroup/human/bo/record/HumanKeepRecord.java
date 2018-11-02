/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: HumanKeepRecord.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.record
 * 描述: 人事业务备案信息基础类
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月24日 下午12:56:00
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.record;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: HumanKeepRecord
 * @Description: 人事业务备案信息基础类
 * @author: Wonders-Rain
 * @date: 2018年5月24日下午12:56:00 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Entity(name = "HUMAN_KEEP_RECORD")
public class HumanKeepRecord extends GenericEntity {
	
	//人事管理类型：公务员晋升
	@Transient
	public static final String SERVANT_PROMOTE  = "recordType01";
		
	//人事管理类型：公务员免职
	@Transient
	public static final String SERVANT_DEPOSE = "recordType02";
	
	//人事管理类型：公务员降职
	@Transient 
	public static final String SERVANT_DEMOTE = "recordType03";
	
	//人事管理类型：公务员轮岗
	@Transient
	public static final String SERVANT_WORK_SHIFT = "recordType04";
		
	//人事管理类型：公务员辞职
	@Transient
	public static final String SERVANT_RESIGN = "recordType05";
	
	//人事管理类型：公务员处分
	@Transient 
	public static final String SERVANT_PUNISH = "recordType06";
	
	//人事管理类型：公务员辞退
	@Transient
	public static final String SERVANT_DISMISS = "recordType07";
		
	//人事管理类型：公务员退休
	@Transient
	public static final String SERVANT_RETIRE = "recordType08";
	
	//人事管理类型：公务员死亡
	@Transient 
	public static final String SERVANT_DEATH = "recordType09";
	
	//人事管理类型：事业人员晋升
	@Transient
	public static final String INSTITUTION_PROMOTE  = "recordType10";
		
	//人事管理类型：事业人员免职
	@Transient
	public static final String INSTITUTION_DEPOSE = "recordType11";
	
	//人事管理类型：事业人员降职
	@Transient 
	public static final String INSTITUTION_DEMOTE = "recordType12";
	
	//人事管理类型：事业人员轮岗
	@Transient
	public static final String INSTITUTION_WORK_SHIFT = "recordType13";
		
	//人事管理类型：事业人员辞职
	@Transient
	public static final String INSTITUTION_RESIGN = "recordType14";
	
	//人事管理类型：事业人员处分
	@Transient 
	public static final String INSTITUTION_PUNISH = "recordType15";
	
	//人事管理类型：事业人员辞退
	@Transient
	public static final String INSTITUTION_DISMISS = "recordType16";
		
	//人事管理类型：事业人员退休
	@Transient
	public static final String INSTITUTION_RETIRE = "recordType17";
	
	//人事管理类型：事业人员死亡
	@Transient 
	public static final String INSTITUTION_DEATH = "recordType18";
	
	//人事管理类型：参公人员晋升
	@Transient
	public static final String REFERENCE_SERVANT_PROMOTE  = "recordType19";
		
	//人事管理类型：参公人员免职
	@Transient
	public static final String REFERENCE_SERVANT_DEPOSE = "recordType20";
	
	//人事管理类型：参公人员降职
	@Transient 
	public static final String REFERENCE_SERVANT_DEMOTE = "recordType21";
	
	//人事管理类型：参公人员轮岗
	@Transient
	public static final String REFERENCE_SERVANT_WORK_SHIFT = "recordType22";
		
	//人事管理类型：参公人员辞职
	@Transient
	public static final String REFERENCE_SERVANT_RESIGN = "recordType23";
	
	//人事管理类型：参公人员处分
	@Transient 
	public static final String REFERENCE_SERVANT_PUNISH = "recordType24";
	
	//人事管理类型：参公人员辞退
	@Transient
	public static final String REFERENCE_SERVANT_DISMISS = "recordType25";
		
	//人事管理类型：参公人员退休
	@Transient
	public static final String REFERENCE_SERVANT_RETIRE = "recordType26";
	
	//人事管理类型：参公人员死亡
	@Transient 
	public static final String REFERENCE_SERVANT_DEATH = "recordType27";

	private static final long serialVersionUID = -6690866633213380395L;

	/**
	 * @fieldName: humanId
	 * @fieldType: java.lang.String
	 * @Description: 人事信息ID
	 */
	@Column(name = "HUMAN_ID", length = 40, nullable = false, insertable = true, updatable = false)
	private String humanId;

	/**
	 * @fieldName: unitType
	 * @fieldType: java.lang.String
	 * @Description: 人事单位类型
	 */
	@Column(name = "UNIT_TYPE", length = 40, insertable = true, updatable = false)
	private String unitType;

	/**
	 * @fieldName: recordType
	 * @fieldType: java.lang.String
	 * @Description: 备案类型
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "RECORD_TYPE")
	private CodeInfo recordType;

	/**
	 * @fieldName: businessEntityId
	 * @fieldType: java.lang.String
	 * @Description: 业务实例ID
	 */
	@Column(name = "BUSINESS_ENTITY_ID", length = 40, insertable = true, updatable = false)
	private String businessEntityId;

	/**
	 * @fieldName: businessEntityTable
	 * @fieldType: java.lang.String
	 * @Description: 业务实例表名称
	 */
	@Column(name = "BUSINESS_ENTITY_TABLE", length = 40, insertable = true, updatable = false)
	private String businessEntityTable;

	/**
	 * @fieldName: returned
	 * @fieldType: java.lang.String
	 * @Description: 是否退回备案操作
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "RETURNED")
	private CodeInfo returned;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 描述信息
	 */
	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 扩展1
	 */
	@Column(name = "EXT1", length = 255)
	private String ext1;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 扩展2
	 */
	@Column(name = "EXT2", length = 255)
	private String ext2;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.String
	 * @Description: 扩展3
	 */
	@Column(name = "EXT3", length = 255)
	private String ext3;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.Date
	 * @Description: 扩展4
	 */
	@Column(name = "EXT4")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ext4;

	/**
	 * @fieldName: description
	 * @fieldType: java.lang.Date
	 * @Description: 扩展5
	 */
	@Column(name = "EXT5")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ext5;

	/**
	 * @fieldName: ext6
	 * @fieldType: java.lang.Integer
	 * @Description: 扩展6
	 */
	@Column(name = "EXT6")
	private Integer ext6;

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
	 * @param unitType the unitType to set
	 */
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	/**
	 * @return the recordType
	 */
	public CodeInfo getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType
	 *            the recordType to set
	 */
	public void setRecordType(CodeInfo recordType) {
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
	public CodeInfo getReturned() {
		return returned;
	}

	/**
	 * @param returned
	 *            the returned to set
	 */
	public void setReturned(CodeInfo returned) {
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
