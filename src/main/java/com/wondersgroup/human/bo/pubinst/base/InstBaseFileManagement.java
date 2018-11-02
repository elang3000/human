package com.wondersgroup.human.bo.pubinst.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: InstBaseFileManagement
 * @Description: 国标  人事档案管理
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseFileManagement<T> extends GenericEntity {

	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @fieldName: fileIntoDate
	 * @fieldType: java.util.Date
	 * @Description: 档案转入日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "A38001")
	@Temporal(TemporalType.DATE)
	private Date fileIntoDate;
	

	/**
	 * @fieldName: fileIntoPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 档案来处名称
	 */
	@Column(name = "A38004A", length = 120)
	private String fileIntoPlaceName;
	

	/**
	 * @fieldName: fileIntoPlaceCode
	 * @fieldType: java.lang.String
	 * @Description: 档案来处代码
	 */
	@Column(name = "A38004B", length = 120)
	private String fileIntoPlaceCode;
	
	
	/**
	 * @fieldName: fileManageUnitName
	 * @fieldType: java.lang.String
	 * @Description: 档案管理单位名称
	 */
	@Column(name = "A38007A", length = 120)
	private String fileManageUnitName;
	
	/**
	 * @fieldName: fileManageUnitCode
	 * @fieldType: java.lang.String
	 * @Description: 档案管理单位代码
	 */
	@Column(name = "A38007B", length = 120)
	private String fileManageUnitCode;
	
	
	/**
	 * @fieldName: fileCategory
	 * @fieldType: java.lang.String
	 * @Description: 档案类别
	 */
	@Column(name = "A38010", length = 120)
	private String fileCategory;
	
	/**
	 * @fieldName: fileNo
	 * @fieldType: java.lang.String
	 * @Description: 档案编号
	 */
	@Column(name = "A38011", length = 120)
	private String fileNo;
	
	
	/**
	 * @fieldName: fileVersionCategory
	 * @fieldType: java.lang.String
	 * @Description: 档案版本类别
	 */
	@Column(name = "A38014", length = 120)
	private String fileVersionCategory;
	
	/**
	 * @fieldName: fileOutputDate
	 * @fieldType: java.lang.String
	 * @Description: 档案转出日期
	 */
	@Column(name = "A38017", length = 120)
	private String fileOutputDate;
	
	
	/**
	 * @fieldName: fileOutputPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 档案去处名称
	 */
	@Column(name = "A38021A", length = 120)
	private String fileOutputPlaceName;
	
	/**
	 * @fieldName: fileOutputPlaceCode
	 * @fieldType: java.lang.String
	 * @Description: 档案去处代码
	 */
	@Column(name = "A38021B", length = 120)
	private String fileOutputPlaceCode;
	
	/**
	 * @fieldName: fileRemark
	 * @fieldType: java.lang.String
	 * @Description: 档案备注
	 */
	@Column(name = "A38024", length = 120)
	private String fileRemark;
	
	/**
	 * @fieldName: originalVolume
	 * @fieldType: java.lang.Integer
	 * @Description: 正本卷数
	 */
	@Column(name = "A38027", length=2)
	private Integer originalVolume;
	
	
	/**
	 * @fieldName: copyVolume
	 * @fieldType: java.lang.Integer
	 * @Description: 副本卷数
	 */
	@Column(name = "A38031", length=2)
	private Integer copyVolume;
	
	
	/**
	 * @fieldName: filePosition
	 * @fieldType: java.lang.String
	 * @Description:档案位置
	 */
	@Column(name = "A38034", length = 120)
	private String filePosition;
	
	
	/**
	 * @fieldName: fileBarCodeNo
	 * @fieldType: java.lang.String
	 * @Description:档案条形码号
	 */
	@Column(name = "A38037", length = 120)
	private String fileBarCodeNo;
	
	
	/**
	 * @fieldName: digitalScanFile
	 * @fieldType: java.lang.String
	 * @Description:是否建立数字扫描档案
	 */
	@Column(name = "A38051", length = 120)
	private String digitalScanFile;


	public Date getFileIntoDate() {
		return fileIntoDate;
	}


	public void setFileIntoDate(Date fileIntoDate) {
		this.fileIntoDate = fileIntoDate;
	}


	public String getFileIntoPlaceName() {
		return fileIntoPlaceName;
	}


	public void setFileIntoPlaceName(String fileIntoPlaceName) {
		this.fileIntoPlaceName = fileIntoPlaceName;
	}


	public String getFileIntoPlaceCode() {
		return fileIntoPlaceCode;
	}


	public void setFileIntoPlaceCode(String fileIntoPlaceCode) {
		this.fileIntoPlaceCode = fileIntoPlaceCode;
	}


	public String getFileManageUnitName() {
		return fileManageUnitName;
	}


	public void setFileManageUnitName(String fileManageUnitName) {
		this.fileManageUnitName = fileManageUnitName;
	}


	public String getFileManageUnitCode() {
		return fileManageUnitCode;
	}


	public void setFileManageUnitCode(String fileManageUnitCode) {
		this.fileManageUnitCode = fileManageUnitCode;
	}


	public String getFileCategory() {
		return fileCategory;
	}


	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}


	public String getFileNo() {
		return fileNo;
	}


	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}


	public String getFileVersionCategory() {
		return fileVersionCategory;
	}


	public void setFileVersionCategory(String fileVersionCategory) {
		this.fileVersionCategory = fileVersionCategory;
	}


	public String getFileOutputDate() {
		return fileOutputDate;
	}


	public void setFileOutputDate(String fileOutputDate) {
		this.fileOutputDate = fileOutputDate;
	}


	public String getFileOutputPlaceName() {
		return fileOutputPlaceName;
	}


	public void setFileOutputPlaceName(String fileOutputPlaceName) {
		this.fileOutputPlaceName = fileOutputPlaceName;
	}


	public String getFileOutputPlaceCode() {
		return fileOutputPlaceCode;
	}


	public void setFileOutputPlaceCode(String fileOutputPlaceCode) {
		this.fileOutputPlaceCode = fileOutputPlaceCode;
	}


	public String getFileRemark() {
		return fileRemark;
	}


	public void setFileRemark(String fileRemark) {
		this.fileRemark = fileRemark;
	}


	public Integer getOriginalVolume() {
		return originalVolume;
	}


	public void setOriginalVolume(Integer originalVolume) {
		this.originalVolume = originalVolume;
	}


	public Integer getCopyVolume() {
		return copyVolume;
	}


	public void setCopyVolume(Integer copyVolume) {
		this.copyVolume = copyVolume;
	}


	public String getFilePosition() {
		return filePosition;
	}


	public void setFilePosition(String filePosition) {
		this.filePosition = filePosition;
	}


	public String getFileBarCodeNo() {
		return fileBarCodeNo;
	}


	public void setFileBarCodeNo(String fileBarCodeNo) {
		this.fileBarCodeNo = fileBarCodeNo;
	}


	public String getDigitalScanFile() {
		return digitalScanFile;
	}


	public void setDigitalScanFile(String digitalScanFile) {
		this.digitalScanFile = digitalScanFile;
	}
	
	
	
	
}
