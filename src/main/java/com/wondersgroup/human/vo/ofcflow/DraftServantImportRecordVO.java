/**   
  * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantImportRecordVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年6月26日 下午2:20:02 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 下午2:20:02 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.common.utils.DataToStrTools;
import com.wondersgroup.human.bo.ofcflow.DraftServantImportRecord;
import com.wondersgroup.human.enums.ServantTypeEnum;

/** 
 * @ClassName: DraftServantImportRecordVO 
 * @Description: TODO
 * @author: GP
 * @date: 2018年6月26日 下午2:20:02
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class DraftServantImportRecordVO {
	
	/**
	 * ID	
	 */
	private String id;
	/**
	 * 年份	
	 */
	private String yearTip;
	/**文件名称
	 * 	
	 */
	private String fileName;
	/**
	 * 导入日期	
	 */
	private String importDate;
	/**
	 * 创建者	
	 */
	private String createUserName;
	/**
	 * 状态
	 */
	private String publish;
	/**
	 * 上传文件类型
	 */
	private String servantType;
	
	public String getYearTip() {
		
		return yearTip;
	}
	
	public void setYearTip(String yearTip) {
		
		this.yearTip = yearTip;
	}
	
	public String getFileName() {
		
		return fileName;
	}
	
	public void setFileName(String fileName) {
		
		this.fileName = fileName;
	}
	
	public String getImportDate() {
		
		return importDate;
	}
	
	public void setImportDate(String importDate) {
		
		this.importDate = importDate;
	}
	
	public String getCreateUserName() {
		
		return createUserName;
	}
	
	public void setCreateUserName(String createUserName) {
		
		this.createUserName = createUserName;
	}
	
	public String getPublish() {
		
		return publish;
	}
	
	public void setPublish(String publish) {
		
		this.publish = publish;
	}
	
	public DraftServantImportRecordVO() {
		
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public DraftServantImportRecordVO(DraftServantImportRecord draftServantImportRecord){
		this.id=draftServantImportRecord.getId();
		this.yearTip=draftServantImportRecord.getYearTip()+"";
		this.createUserName=draftServantImportRecord.getCreateUserName();
		this.fileName=draftServantImportRecord.getFileName();
		this.importDate=DataToStrTools.DateToStr(draftServantImportRecord.getImportDate(), "yyyy-MM-dd HH:mm:ss");
		this.servantType=draftServantImportRecord.getServantType();
		if (draftServantImportRecord.getPublish()==0) {
			this.publish="未发布";
		} else {
			this.publish="已发布";
		}
	}

	public String getServantType() {
		return ServantTypeEnum.getServantTypeEnumString(new Integer(servantType));
	}

	public void setServantType(String servantType) {
		this.servantType = servantType;
	}
	
}
