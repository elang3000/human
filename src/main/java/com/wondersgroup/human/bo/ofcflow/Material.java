/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Material.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 材料表
 * 创建人: tzy
 * 创建时间: 2018年12月25日 下午5:03:02
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年12月25日 下午5:03:02
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: Material
 * @Description: 材料表
 * @author: tzy
 * @date: 2018年12月25日 下午5:03:02
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_MATERIAL")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Material extends GenericEntity {
	
	private static final long serialVersionUID = -5510165592620667598L;
	
	/**
	 * @fieldName: busId
	 * @fieldType: java.lang.String
	 * @Description: 业务数据ID。
	 */
	@Column(name = "BUS_ID")
	private String busId;
	
	/**
	 * @fieldName: busName
	 * @fieldType: java.lang.String
	 * @Description: 业务名称
	 */
	@Column(name = "BUS_NAME", length = 255)
	private String busName;
	
	/**
	 * @fieldName: busType
	 * @fieldType: java.lang.String
	 * @Description: 业务类型标识。
	 */
	@Column(name = "BUS_TYPE", length = 60)
	private String busType;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 材料名称，如果一个人员有多个材料，材料名称用英文冒号（:）隔开
	 */
	@Column(name = "NAME", length = 255)
	private String name;
	
	/**
	 * @fieldName: flowNumber
	 * @fieldType: java.lang.String
	 * @Description: 材料名称
	 */
	@Column(name = "FLOW_NUMBER", length = 20)
	private String flowNumber;
	
	/**
	 * @fieldName: ftpFilefolder
	 * @fieldType: java.lang.String
	 * @Description: 材料ftp目录
	 */
	@Column(name = "FTP_FILE_FOLDER", length = 40)
	private String ftpFilefolder;
	
	/**
	 * @fieldName: ftpFileName
	 * @fieldType: java.lang.String
	 * @Description: 材料ftp文件名，如果一个人员有多个材料，文件名用英文冒号（:）隔开
	 */
	@Column(name = "FTP_FILE_NAME", length = 1000)
	private String ftpFileName;
	
	/**
	 * @fieldName: folderName
	 * @fieldType: java.lang.String
	 * @Description: 文件夹名称（针对一个人有多个文件建立一个文件夹放进去）
	 */
	@Column(name = "FOLDER_NAME", length = 20)
	private String folderName;
	
	public String getBusId() {
		
		return busId;
	}
	
	public void setBusId(String busId) {
		
		this.busId = busId;
	}
	
	public String getBusName() {
		
		return busName;
	}
	
	public void setBusName(String busName) {
		
		this.busName = busName;
	}
	
	public String getBusType() {
		
		return busType;
	}
	
	public void setBusType(String busType) {
		
		this.busType = busType;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getFlowNumber() {
		
		return flowNumber;
	}
	
	public void setFlowNumber(String flowNumber) {
		
		this.flowNumber = flowNumber;
	}
	
	public String getFtpFilefolder() {
		
		return ftpFilefolder;
	}
	
	public void setFtpFilefolder(String ftpFilefolder) {
		
		this.ftpFilefolder = ftpFilefolder;
	}
	
	public String getFtpFileName() {
		
		return ftpFileName;
	}
	
	public void setFtpFileName(String ftpFileName) {
		
		this.ftpFileName = ftpFileName;
	}

	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
}
