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
package com.wondersgroup.human.dto.pubinst;

import java.util.Date;

import javax.persistence.Column;

/**
 * @ClassName: ManagerRecord
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年6月9日
 *        下午3:28:42 @version [版本号, YYYY-MM-DD] @see       [相关类/方法] @since     [产品/模块版本]
 */
public class PtManagerRecordDTO {

	/**
	 * @fieldName: humanID
	 * @fieldType: java.lang.String
	 * @Description: 人事信息ID。
	 */
	private String humanId;

	/**
	 * @fieldName: recordType
	 * @fieldType: java.lang.String
	 * @Description: 人事管理类型。
	 */
	private String recordType;

	/**
	 * @fieldName: managerType
	 * @fieldType: java.lang.String
	 * @Description: 进出管理类型。
	 */
	private String managerType;

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
	 * @fieldName: status
	 * @fieldType: java.lang.String
	 * @Description: 审批状态。
	 */
	private String status;

	/**
	 * @fieldName: areaExamineOpinion
	 * @fieldType: java.lang.String
	 * @Description: 上级领导审批意见。
	 */
	private String leaderExamineOpinion;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 上级领导审批人。
	 */
	private String leaderExamineAudit;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 上级领导审批时间。
	 */
	private Date leaderExamineTime;

	/**
	 * @fieldName: areaExamineOpinion
	 * @fieldType: java.lang.String
	 * @Description: 区审批意见。
	 */
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
	private Date areaExamineTime;

	/**
	 * @fieldName: areaExamineOpinion
	 * @fieldType: java.lang.String
	 * @Description: 市审批意见。
	 */
	private String cityExamineOpinion;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 区审批人。
	 */
	private String cityExamineAudit;

	/**
	 * @fieldName: areaExamineAudit
	 * @fieldType: java.lang.String
	 * @Description: 区审批时间。
	 */
	private Date cityExamineTime;

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
	 * @return the managerType
	 */
	public String getManagerType() {
		return managerType;
	}

	/**
	 * @param managerType
	 *            the managerType to set
	 */
	public void setManagerType(String managerType) {
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
