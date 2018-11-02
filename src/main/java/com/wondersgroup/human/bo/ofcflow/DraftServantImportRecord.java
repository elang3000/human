/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DraftServantImportRecord.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 
 * 创建人: GP
 * 创建时间: 2018年6月26日 上午9:36:16
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2018年6月26日 上午9:36:16
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: DraftServantImportRecord
 * @Description: 拟录用导入记录表
 * @author: GP
 * @date: 2018年6月26日 上午9:36:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DRAFT_IMPORT_RD")
public class DraftServantImportRecord extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = -3317840966944377393L;
	
	/**
	 * @fieldName: yearTip
	 * @fieldType: java.lang.Integer
	 * @Description: 年份。
	 */
	@Column(name = "YEAR_TIP")
	private Integer yearTip;
	
	/**
	 * @fieldName: fileName
	 * @fieldType: java.lang.String
	 * @Description: 文件名称。
	 */
	@Column(name = "FILE_NAME")
	private String fileName;
	
	/**
	 * @fieldName: importDate
	 * @fieldType: java.util.Date
	 * @Description: 导入时间
	 */
	@Column(name = "IMPORT_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date importDate;
	
	/**
	 * @fieldName: createUserName
	 * @fieldType: java.util.Date
	 * @Description: 创建者
	 */
	@Column(name = "CREATE_USER_NAME")
	private String createUserName;
	
	/**
	 * @fieldName: publish
	 * @fieldType: java.lang.Integer
	 * @Description: 状态 0:未发布 1：已发布      DraftServant中常量值
	 */
	@Column(name = "PUBLISH")
	private Integer publish;
	
	
	/**
	 * 导入成功记录数
	 */
	@Column(name="IMPORT_NUM")
	private Integer importNum;
	
	/**
	 * 导入失败记录数据
	 */
	@Column(name="FAIL_STR",length=2048)
	private String failStr;
	
	/**
	 * 上传文件类型
	 */
	@Column(name="SERVANTTYPE")
	private String servantType;

	public String getServantType() {
		return servantType;
	}

	public void setServantType(String servantType) {
		this.servantType = servantType;
	}

	public Integer getImportNum() {
		return importNum;
	}

	public void setImportNum(Integer importNum) {
		this.importNum = importNum;
	}

	public String getFailStr() {
		return failStr;
	}

	public void setFailStr(String failStr) {
		this.failStr = failStr;
	}

	public Integer getYearTip() {
		
		return yearTip;
	}
	
	public void setYearTip(Integer yearTip) {
		
		this.yearTip = yearTip;
	}
	
	public String getFileName() {
		
		return fileName;
	}
	
	public void setFileName(String fileName) {
		
		this.fileName = fileName;
	}
	
	public Date getImportDate() {
		
		return importDate;
	}
	
	public void setImportDate(Date importDate) {
		
		this.importDate = importDate;
	}
	
	public String getCreateUserName() {
		
		return createUserName;
	}
	
	public void setCreateUserName(String createUserName) {
		
		this.createUserName = createUserName;
	}
	
	public Integer getPublish() {
		
		return publish;
	}
	
	public void setPublish(Integer publish) {
		
		this.publish = publish;
	}
}
