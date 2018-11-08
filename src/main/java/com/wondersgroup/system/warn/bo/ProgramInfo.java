/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProgramInfo.java
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.bo
 * 描述: 预警预告：查询方案
 * 创建人: tzy
 * 创建时间: 2018年10月29日 下午4:48:34
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年10月29日 下午4:48:34
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.warn.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: ProgramInfo
 * @Description: 预警预告：查询方案
 * @author: tzy
 * @date: 2018年10月29日 下午4:48:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_WARN_PROGRAMINFO")
public class ProgramInfo extends GenericEntity {
	
	private static final long serialVersionUID = -4387528671351184173L;
	
	/**
	 * 方案类型：1预警；方案启动状态 1 启动；预警信息类型：1 个人信息
	 */
	public static final String WARN = "1";
	
	/**
	 * 方案类型：2预告；方案启动状态 2 关闭；预警信息类型：2 单位信息
	 */
	public static final String PREVIEW = "2";
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 方案名称
	 */
	@Column(name = "NAME", length = 200)
	private String name;
	
	/**
	 * @fieldName: describe
	 * @fieldType: java.lang.String
	 * @Description: 方案描述
	 */
	@Column(name = "DESCRIBE", length = 400)
	private String describe;
	
	/**
	 * @fieldName: programType
	 * @fieldType: java.lang.String
	 * @Description: 方案类型 1 预警 2 预告
	 */
	@Column(name = "PROGRAM_TYPE", length = 1)
	private String programType;
	
	/**
	 * @fieldName: programTime
	 * @fieldType: java.lang.String
	 * @Description: 方案执行时间 定时器执行时间
	 */
	@Column(name = "PROGRAM_TIME", length = 100)
	private String programTime;
	
	/**
	 * @fieldName: programTimeDescribe
	 * @fieldType: java.lang.String
	 * @Description: 方案执行时间描述
	 */
	@Column(name = "PROGRAM_TIME_DESCRIBE", length = 400)
	private String programTimeDescribe;
	
	/**
	 * @fieldName: programState
	 * @fieldType: java.lang.String
	 * @Description: 方案启动状态 1 启动 2 关闭
	 */
	@Column(name = "PROGRAM_STATE", length = 1)
	private String programState;
	
	/**
	 * @fieldName: programCode
	 * @fieldType: java.lang.String
	 * @Description: 预警信息类型：1 个人信息 2 单位信息
	 */
	@Column(name = "PROGRAM_CODE", length = 1)
	private String programCode;
	
	/**
	 * @fieldName: resultSql
	 * @fieldType: java.lang.String
	 * @Description: 生成的sql语句
	 */
	@Column(name = "RESULT_SQL", length = 4000)
	private String resultSql;
	
	/**
	 * @fieldName: newsContent
	 * @fieldType: java.lang.String
	 * @Description:通知消息主体+查询条件生成的消息
	 */
	@Column(name = "NEWS_CONTENT", length = 2000)
	private String newsContent;
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getDescribe() {
		
		return describe;
	}
	
	public void setDescribe(String describe) {
		
		this.describe = describe;
	}
	
	public String getProgramType() {
		
		return programType;
	}
	
	public void setProgramType(String programType) {
		
		this.programType = programType;
	}
	
	public String getProgramTime() {
		
		return programTime;
	}
	
	public void setProgramTime(String programTime) {
		
		this.programTime = programTime;
	}
	
	public String getProgramTimeDescribe() {
		
		return programTimeDescribe;
	}
	
	public void setProgramTimeDescribe(String programTimeDescribe) {
		
		this.programTimeDescribe = programTimeDescribe;
	}
	
	public String getProgramState() {
		
		return programState;
	}
	
	public void setProgramState(String programState) {
		
		this.programState = programState;
	}
	
	public String getProgramCode() {
		
		return programCode;
	}
	
	public void setProgramCode(String programCode) {
		
		this.programCode = programCode;
	}
	
	public String getResultSql() {
		
		return resultSql;
	}
	
	public void setResultSql(String resultSql) {
		
		this.resultSql = resultSql;
	}
	
	public String getNewsContent() {
		
		return newsContent;
	}
	
	public void setNewsContent(String newsContent) {
		
		this.newsContent = newsContent;
	}
	
}
