/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: PersonnelRecord.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述: 进出管理信息基础类
 * 创建人:
 * 创建时间: 2018年6月7日 下午5:43:46
 * 版本号: V1.0
 * 修改人：
 * 修改时间：2018年6月7日 下午5:43:46
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.socialworker;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: PersonnelRecord
 * @Description: 进出管理信息
 * @author: lihao
 * @date: 2018年6月7日下午5:43:46 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_MANAGE_RECORD_WORKER")
public class SrManagerRecord extends GenericEntity {

	// 进出管理：进
	@Transient
	public static final String MANAGER_MANAGER_TYPE_IN = "managerType01";

	// 进出管理：出
	@Transient
	public static final String MANAGER_MANAGER_TYPE_OUT = "managerType02";

	// 进出管理：管理
	@Transient
	public static final String MANAGER_MANAGER_TYPE_MANAGER = "managerType03";

	private static final long serialVersionUID = -1145573454260614055L;

	/**
	 * @fieldName: humanID
	 * @fieldType: java.lang.String
	 * @Description: 人事信息ID。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HUMAN_ID")
	private Servant servant;

	/**
	 * @fieldName: recordType
	 * @fieldType: java.lang.String
	 * @Description: 记录类型名称。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "RECORD_TYPE")
	private CodeInfo recordType;

	/**
	 * @fieldName: unitType
	 * @fieldType: java.lang.String
	 * @Description: 进出管理类型。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "UNIT_TYPE")
	private CodeInfo managerType;

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
	 * @fieldName: businessEntityId
	 * @fieldType: java.lang.String
	 * @Description: 业务实例ID。
	 */
	@Column(name = "BUSINESS_ENTITY_ID", length = 120)
	private String businessEntityId;

	/**
	 * @fieldName: businessEntityTable
	 * @fieldType: java.lang.String
	 * @Description: 业务实例表。
	 */
	@Column(name = "BUSINESS_ENTITY_Table", length = 120)
	private String businessEntityTable;

	/**
	 * @fieldName: status
	 * @fieldType: java.lang.String
	 * @Description: 审批状态。
	 */
	@Column(name = "STATUS", length = 120)
	private String status;

	/**
	 * @fieldName: areaExamineOpinion
	 * @fieldType: java.lang.String
	 * @Description: 上级领导审批意见。
	 */
	@Column(name = "LEADER_EXAMINE_OPINION", length = 120)
	private String leaderExamineOpinion;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 上级领导审批人。
	 */
	@Column(name = "LEADER_EXAMINE_AUDIT", length = 120)
	private String leaderExamineAudit;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 上级领导审批时间。
	 */
	@Column(name = "LEADER_EXAMINE_Time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date leaderExamineTime;

	/**
	 * @fieldName: areaExamineOpinion
	 * @fieldType: java.lang.String
	 * @Description: 区审批意见。
	 */
	@Column(name = "AREA_EXAMINE_OPINION", length = 120)
	private String areaExamineOpinion;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 区审批人。
	 */
	@Column(name = "AREA_EXAMINE_AUDIT", length = 120)
	private String areaExamineAudit;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 区审批时间。
	 */
	@Column(name = "AREA_EXAMINE_Time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date areaExamineTime;

	/**
	 * @fieldName: areaExamineOpinion
	 * @fieldType: java.lang.String
	 * @Description: 市审批意见。
	 */
	@Column(name = "CITY_EXAMINE_OPINION", length = 120)
	private String cityExamineOpinion;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 区审批人。
	 */
	@Column(name = "CITY_EXAMINE_AUDIT", length = 120)
	private String cityExamineAudit;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 区审批时间。
	 */
	@Column(name = "CITY_EXAMINE_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date cityExamineTime;

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
	 * @param servant
	 *            the servant to set
	 */
	public void setServant(Servant servant) {
		this.servant = servant;
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
	 * @return the managerType
	 */
	public CodeInfo getManagerType() {
		return managerType;
	}

	/**
	 * @param managerType
	 *            the managerType to set
	 */
	public void setManagerType(CodeInfo managerType) {
		this.managerType = managerType;
	}

	/**
	 * @return the recordTime
	 */
	public Date getRecordTime() {
		return recordTime;
	}

	/**
	 * @param recordTime
	 *            the recordTime to set
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the leaderExamineOpinion
	 */
	public String getLeaderExamineOpinion() {
		return leaderExamineOpinion;
	}

	/**
	 * @param leaderExamineOpinion
	 *            the leaderExamineOpinion to set
	 */
	public void setLeaderExamineOpinion(String leaderExamineOpinion) {
		this.leaderExamineOpinion = leaderExamineOpinion;
	}

	/**
	 * @return the leaderExamineAudit
	 */
	public String getLeaderExamineAudit() {
		return leaderExamineAudit;
	}

	/**
	 * @param leaderExamineAudit
	 *            the leaderExamineAudit to set
	 */
	public void setLeaderExamineAudit(String leaderExamineAudit) {
		this.leaderExamineAudit = leaderExamineAudit;
	}

	/**
	 * @return the leaderExamineTime
	 */
	public Date getLeaderExamineTime() {
		return leaderExamineTime;
	}

	/**
	 * @param leaderExamineTime
	 *            the leaderExamineTime to set
	 */
	public void setLeaderExamineTime(Date leaderExamineTime) {
		this.leaderExamineTime = leaderExamineTime;
	}

	/**
	 * @return the areaExamineOpinion
	 */
	public String getAreaExamineOpinion() {
		return areaExamineOpinion;
	}

	/**
	 * @param areaExamineOpinion
	 *            the areaExamineOpinion to set
	 */
	public void setAreaExamineOpinion(String areaExamineOpinion) {
		this.areaExamineOpinion = areaExamineOpinion;
	}

	/**
	 * @return the areaExamineAudit
	 */
	public String getAreaExamineAudit() {
		return areaExamineAudit;
	}

	/**
	 * @param areaExamineAudit
	 *            the areaExamineAudit to set
	 */
	public void setAreaExamineAudit(String areaExamineAudit) {
		this.areaExamineAudit = areaExamineAudit;
	}

	/**
	 * @return the areaExamineTime
	 */
	public Date getAreaExamineTime() {
		return areaExamineTime;
	}

	/**
	 * @param areaExamineTime
	 *            the areaExamineTime to set
	 */
	public void setAreaExamineTime(Date areaExamineTime) {
		this.areaExamineTime = areaExamineTime;
	}

	/**
	 * @return the cityExamineOpinion
	 */
	public String getCityExamineOpinion() {
		return cityExamineOpinion;
	}

	/**
	 * @param cityExamineOpinion
	 *            the cityExamineOpinion to set
	 */
	public void setCityExamineOpinion(String cityExamineOpinion) {
		this.cityExamineOpinion = cityExamineOpinion;
	}

	/**
	 * @return the cityExamineAudit
	 */
	public String getCityExamineAudit() {
		return cityExamineAudit;
	}

	/**
	 * @param cityExamineAudit
	 *            the cityExamineAudit to set
	 */
	public void setCityExamineAudit(String cityExamineAudit) {
		this.cityExamineAudit = cityExamineAudit;
	}

	/**
	 * @return the cityExamineTime
	 */
	public Date getCityExamineTime() {
		return cityExamineTime;
	}

	/**
	 * @param cityExamineTime
	 *            the cityExamineTime to set
	 */
	public void setCityExamineTime(Date cityExamineTime) {
		this.cityExamineTime = cityExamineTime;
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
