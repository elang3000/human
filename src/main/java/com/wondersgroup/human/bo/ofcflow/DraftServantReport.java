/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DraftServantReport.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 录用流程表
 * 创建人: tzy
 * 创建时间: 2018年7月20日 上午10:11:17
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月20日 上午10:11:17
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;

/**
 * @ClassName: DraftServantReport
 * @Description: 录用流程表
 * @author: tzy
 * @date: 2018年7月20日 上午10:11:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DRAFT_SERVANT_REPORT")
public class DraftServantReport extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description:
	 */
	private static final long serialVersionUID = -800984934324509824L;
	
	public static final Integer SERVANTREPORT_AGREE=1;
	
	public static final Integer SERVANTREPORT_DISAGREE=0;
	
	/**
	 * @fieldName: createDeptId
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 表单创建部门ID。
	 */
	@OneToOne
	@JoinColumn(name = "CREATE_DEPT_ID")
	private OrganNode createDept;
	
	/**
	 * @fieldName: askForFilePathGWY
	 * @fieldType: java.lang.String
	 * @Description: 公务员请示或函FTP路径。
	 */
	@Column(name = "ASK_FOR_FILE_PATH_GWY")
	private String askForFilePathGWY;
	
	/**
	 * @fieldName: askForFilePathCG
	 * @fieldType: java.lang.String
	 * @Description: 参公请示或函FTP路径。
	 */
	@Column(name = "ASK_FOR_FILE_PATH_CG")
	private String askForFilePathCG;
	
	/**
	 * @fieldName: repostFilePath
	 * @fieldType: java.lang.String
	 * @Description: 公务员审核报告FTP路径。
	 */
	@Column(name = "REPORT_FILE_PATH_GWY")
	private String repostFilePathGWY;
	/**
	 * @fieldName: repostFilePath
	 * @fieldType: java.lang.String
	 * @Description: 参公审核报告FTP路径。
	 */
	@Column(name = "REPORT_FILE_PATH_CG")
	private String repostFilePathCG;
	
	/**
	 * @fieldName: noTozFilePathGWY
	 * @fieldType: java.lang.String
	 * @Description: 公务员非310人员汇总文件路径。
	 */
	@Column(name = "NO_310_FILE_PATH_GWY")
	private String no310FilePathGWY;
	
	/**
	 * @fieldName: noTozFilePathGWY
	 * @fieldType: java.lang.String
	 * @Description: 公务员非310人员汇总文件路径。
	 */
	@Column(name = "NO_310_FILE_PATH_CG")
	private String no310FilePathCG;
	
	/***************************************************************************
	 * 流程相关属性
	 **************************************************************************/
	/**
	 * @fieldName: pinstanceId
	 * @fieldType: java.lang.String
	 * @Description: 流程实例ID。
	 */
	@Column(name = "PINSTANCE_ID")
	private String pinstanceId;
	
	/**
	 * 此次汇总总人数
	 */
	@Column(name="TOTAL")
	private Integer total;
	/**
	 * 此次汇总总人数
	 */
	@Column(name="TOTAL310")
	private Integer total310;
	
	/**
	 * 公务员总人数
	 */
	@Column(name="TOTAL_GWY")
	private Integer totalGwy;
	
	/**
	 * 公务员310人数
	 */
	@Column(name="TOTAL_GWY310")
	private Integer totalGwy310;
	
	/**
	 * 参公总人数
	 */
	@Column(name="TOTAL_CG")
	private Integer totalCg;
	
	/**
	 * 参公310人数
	 */
	@Column(name="TOTAL_CG310")
	private Integer totalCg310;
	
	/**
	 *请示文件名 
	 */
	@Column(name="REQUEST_FILENAME")
	private String requestFileName;
	
	
	/**
	 * 单位名称
	 */
	@Column(name="UNIT_NAME")
	private String unitName;
	
	/**
	 * 编号
	 */
	@Column(name="SERIAL_NUMBER")
	private String  serialNumber;
	
	/**
	 * 签发人
	 */
	@Column(name="SIGNER")
	private String signer;
	
	/**
	 * 是否市局同意
	 */
	@Column(name="ISAGREE")
	private Integer isAgree;
	
	/**
	 * @fieldName: workitemId
	 * @fieldType: java.lang.String
	 * @Description: 工作项ID。
	 */
	@Transient
	private String workitemId;
	
	/**
	 * @fieldName: userId
	 * @fieldType: java.lang.String
	 * @Description: 用户ID。
	 */
	@Transient
	private String userId;
	
	/**
	 * @fieldName: modelId
	 * @fieldType: java.lang.String
	 * @Description: 流程模型ID。
	 */
	@Transient
	private String modelId;
	
	/**
	 * @fieldName: activityName
	 * @fieldType: java.lang.String
	 * @Description: 活动名称。
	 */
	@Transient
	private String activityName;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.String
	 * @Description: 流程状态。
	 */
	@Column(name = "STATUS")
	private String status;
	
	
	public String getPinstanceId() {
		
		return pinstanceId;
	}
	
	public void setPinstanceId(String pinstanceId) {
		
		this.pinstanceId = pinstanceId;
	}
	
	public String getWorkitemId() {
		
		return workitemId;
	}
	
	public void setWorkitemId(String workitemId) {
		
		this.workitemId = workitemId;
	}
	
	public String getUserId() {
		
		return userId;
	}
	
	public void setUserId(String userId) {
		
		this.userId = userId;
	}
	
	public String getModelId() {
		
		return modelId;
	}
	
	public void setModelId(String modelId) {
		
		this.modelId = modelId;
	}
	
	public String getActivityName() {
		
		return activityName;
	}
	
	public void setActivityName(String activityName) {
		
		this.activityName = activityName;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public OrganNode getCreateDept() {
		
		return createDept;
	}
	
	public void setCreateDept(OrganNode createDept) {
		
		this.createDept = createDept;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotal310() {
		return total310;
	}

	public void setTotal310(Integer total310) {
		this.total310 = total310;
	}

	public Integer getTotalGwy() {
		return totalGwy;
	}

	public void setTotalGwy(Integer totalGwy) {
		this.totalGwy = totalGwy;
	}

	public Integer getTotalGwy310() {
		return totalGwy310;
	}

	public void setTotalGwy310(Integer totalGwy310) {
		this.totalGwy310 = totalGwy310;
	}

	public Integer getTotalCg() {
		return totalCg;
	}

	public void setTotalCg(Integer totalCg) {
		this.totalCg = totalCg;
	}

	public Integer getTotalCg310() {
		return totalCg310;
	}

	public void setTotalCg310(Integer totalCg310) {
		this.totalCg310 = totalCg310;
	}

	public String getRequestFileName() {
		return requestFileName;
	}

	public void setRequestFileName(String requestFileName) {
		this.requestFileName = requestFileName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getAskForFilePathGWY() {
		return askForFilePathGWY;
	}

	public void setAskForFilePathGWY(String askForFilePathGWY) {
		this.askForFilePathGWY = askForFilePathGWY;
	}

	public String getAskForFilePathCG() {
		return askForFilePathCG;
	}

	public void setAskForFilePathCG(String askForFilePathCG) {
		this.askForFilePathCG = askForFilePathCG;
	}

	public String getRepostFilePathGWY() {
		return repostFilePathGWY;
	}

	public void setRepostFilePathGWY(String repostFilePathGWY) {
		this.repostFilePathGWY = repostFilePathGWY;
	}

	public String getRepostFilePathCG() {
		return repostFilePathCG;
	}

	public void setRepostFilePathCG(String repostFilePathCG) {
		this.repostFilePathCG = repostFilePathCG;
	}

	public String getNo310FilePathGWY() {
		return no310FilePathGWY;
	}

	public void setNo310FilePathGWY(String no310FilePathGWY) {
		this.no310FilePathGWY = no310FilePathGWY;
	}

	public String getNo310FilePathCG() {
		return no310FilePathCG;
	}

	public void setNo310FilePathCG(String no310FilePathCG) {
		this.no310FilePathCG = no310FilePathCG;
	}

	public Integer getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(Integer isAgree) {
		this.isAgree = isAgree;
	}
	
	
}
