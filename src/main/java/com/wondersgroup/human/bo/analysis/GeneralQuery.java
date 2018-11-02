/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: GeneralQuery.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年10月18日 下午3:36:43 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年10月18日 下午3:36:43 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.bo.analysis;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.wondersgroup.framework.core.bo.GenericEntity;

/** 
 * @ClassName: GeneralQuery 
 * @Description: 综合查询下拉菜单条件
 * @author: lihao
 * @date: 2018年10月18日 下午3:36:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Entity(name = "HUMAN_GENERAL_QUERY")
public class GeneralQuery extends GenericEntity {

	private static final long serialVersionUID = -6124402418745571238L;

	/**
	 * @fieldName: queryName
	 * @fieldType: java.lang.String
	 * @Description: 查询条件 格式：（子集名称）查询条件名称
	 */
	@Column(name = "QUERY_NAME", length = 180)
	private String name;
	
	/**
	 * @fieldName: queryName
	 * @fieldType: java.lang.String
	 * @Description: 查询条件 name 用于查询条件 格式: xx(类).查询值name,name值
	 */
	@Column(name = "QUERY_CODE", length = 180)
	private String code;
	
	/**
	 * @fieldName: queryName
	 * @fieldType: java.lang.String
	 * @Description: id值
	 */
	@Column(name = "QUERY_CODE_ID", length = 180)
	private String codeId;
	
	/**
	 * @fieldName: queryName
	 * @fieldType: java.lang.String
	 * @Description: 查询条件类型（单级下拉框，多级下拉框，文本，数字，时间范围）
	 */
	@Column(name = "QUERY_TYPE", length = 180)
	private String type;
	
	/**
	 * @fieldName: queryName
	 * @fieldType: java.lang.String
	 * @Description: 查询条件类型为下拉框时对应的URL地址
	 */
	@Column(name = "QUERY_URL", length = 180)
	private String url;
	
	/**
	 * @fieldName: queryName
	 * @fieldType: java.lang.String
	 * @Description: 查询条件类型为下拉框时对应的URL地址
	 */
	@Column(name = "QUERY_ORDER")
	private Integer queryOrder;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the codeId
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * @param codeId the codeId to set
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
}
