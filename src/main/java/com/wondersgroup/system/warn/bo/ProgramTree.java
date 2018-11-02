/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServantTree.java
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.bo
 * 描述: 预警预告：字段信息
 * 创建人: tzy
 * 创建时间: 2018年10月31日 上午11:27:36
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年10月31日 上午11:27:36
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.warn.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.framework.controller.viewobject.GenericTreeVO;
import com.wondersgroup.framework.core.bo.GenericTreeEntity;

/**
 * @ClassName: ServantTree
 * @Description: 预警预告：字段信息 tree
 * @author: tzy
 * @date: 2018年10月31日 上午11:27:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_WARN_PROGRAMTREE")
public class ProgramTree extends GenericTreeEntity<ProgramTree> {
	
	private static final long serialVersionUID = -7563437204986201364L;
	
	/**
	 * @fieldName: dataType
	 * @fieldType: java.lang.String
	 * @Description: 数据类型：1-人员基本信息，2-单位信息
	 */
	@Column(name = "DATA_TYPE")
	private String dataType;
	
	/**
	 * @fieldName: colType
	 * @fieldType: java.lang.String
	 * @Description: 字段类型：1-字符串，2-日期，3-数据字典
	 */
	@Column(name = "COL_TYPE", length = 1)
	private String colType;
	
	/**
	 * @fieldName: codeValue
	 * @fieldType: java.lang.String
	 * @Description: 字段类型为数据字典时，该字段存储数据字典code码
	 */
	@Column(name = "CODEVALUE", length = 20)
	private String codeValue;
	
	/**
	 * (non Javadoc)
	 * @Title: getTarget
	 * @Description: 返回树形VO
	 * @return
	 * @see com.wondersgroup.framework.core.bo.GenericTreeEntity#getTarget()
	 */
	@Override
	public GenericTreeVO getTarget() {
		
		GenericTreeVO vo = new GenericTreeVO();
		vo.setCode(this.getCode());
		vo.setId(this.getId());
		vo.setName(this.getName());
		if (this.getParent() != null) {
			vo.setParentId(this.getParent().getId());
		}
		return vo;
	}
	
	public String getDataType() {
		
		return dataType;
	}
	
	public void setDataType(String dataType) {
		
		this.dataType = dataType;
	}
	
	public String getColType() {
		
		return colType;
	}
	
	public void setColType(String colType) {
		
		this.colType = colType;
	}
	
	public String getCodeValue() {
		
		return codeValue;
	}
	
	public void setCodeValue(String codeValue) {
		
		this.codeValue = codeValue;
	}
}