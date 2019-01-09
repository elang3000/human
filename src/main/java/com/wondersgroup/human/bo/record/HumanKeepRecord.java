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

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: HumanKeepRecord
 * @Description: 人事业务备案信息基础类
 * @author: Wonders-Rain
 * @date: 2018年5月24日下午12:56:00 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_KEEP_RECORD")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HumanKeepRecord extends GenericEntity {
	//备案类型：公务员调任调出
	@Transient
	public static final String KEEP_DRDC  = "KEEP_DRDC";
	
	//备案类型：同类别转任转出
	@Transient
	public static final String KEEP_TLZC  = "KEEP_TLZC";
	
	//备案类型：跨类别转任转出
	@Transient
	public static final String KEEP_KLZC  = "KEEP_KLZC";
	
	//备案类型：参公交流调出
	@Transient
	public static final String KEEP_CGDC  = "KEEP_CGDC";
	
	//备案类型：借调
	@Transient
	public static final String KEEP_JD  = "KEEP_JD";
	
	//备案类型：挂职锻炼
	@Transient
	public static final String KEEP_GZDL  = "KEEP_GZDL";
		
	//备案类型：公务员辞职
	@Transient
	public static final String KEEP_CZ  = "KEEP_CZ";
	
	//备案类型：公务员死亡
	@Transient
	public static final String KEEP_SW  = "KEEP_SW";
	
	//备案类型：公务员处分
	@Transient
	public static final String KEEP_CF  = "KEEP_CF";

	private static final long serialVersionUID = -6690866633213380395L;

	/**
	 * @fieldName: humanID
	 * @fieldType: java.lang.String
	 * @Description: 人事信息ID。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;

	/**
	 * @fieldName: humanID
	 * @fieldType: java.lang.String
	 * @Description: 当时单位ID。
	 */
	@Column(name = "ORGAN_ID", length = 120)
	private String organId;
	
	/**
	 * @fieldName: humanID
	 * @fieldType: java.lang.String
	 * @Description: 当时单位名。
	 */
	@Column(name = "ORGAN_NAME", length = 120)
	private String organName;

	/**
	 * @fieldName: recordType
	 * @fieldType: java.lang.String
	 * @Description: 备案类型
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "RECORD_TYPE")
	private CodeInfo recordType;

	/**
	 * @fieldName: recordTime
	 * @fieldType: java.util.DATE
	 * @Description: 记录时间。
	 */
	@Column(name = "RECORD_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date recordTime;
	
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
	 * @return the servant
	 */
	public Servant getServant() {
		return servant;
	}

	/**
	 * @param servant the servant to set
	 */
	public void setServant(Servant servant) {
		this.servant = servant;
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
