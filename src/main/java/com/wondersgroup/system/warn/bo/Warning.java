/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Warning.java
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.bo
 * 描述: 预警预告：定时执行任务
 * 创建人: tzy
 * 创建时间: 2018年10月30日 上午10:51:13
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年10月30日 上午10:51:13
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.warn.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: Warning
 * @Description: 预警预告：定时执行任务
 * @author: tzy
 * @date: 2018年10月30日 上午10:51:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_WARN_WARNING")
public class Warning extends GenericEntity {
	
	private static final long serialVersionUID = 1233083572545860392L;
	
	/**
	 * @fieldName: bulidDate
	 * @fieldType: java.lang.String
	 * @Description: 创建时间
	 */
	@Column(name = "BULIDDATE")
	private String bulidDate;
	
	/**
	 * @fieldName: dataNum
	 * @fieldType: java.lang.String
	 * @Description: 数据条数
	 */
	@Column(name = "DATANUM")
	private String dataNum;
	
	/**
	 * @fieldName: programInfo
	 * @fieldType: com.wondersgroup.system.warn.bo.ProgramInfo
	 * @Description: 预警预告方案
	 */
	@ManyToOne
	@JoinColumn(name = "PROGRAMINFO_ID")
	private ProgramInfo programInfo;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息
	 */
	@ManyToOne
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: organNode
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 单位信息
	 */
	@ManyToOne
	@JoinColumn(name = "ORGANNODE_ID")
	private OrganNode organNode;
	
	public String getBulidDate() {
		
		return bulidDate;
	}
	
	public void setBulidDate(String bulidDate) {
		
		this.bulidDate = bulidDate;
	}
	
	public String getDataNum() {
		
		return dataNum;
	}
	
	public void setDataNum(String dataNum) {
		
		this.dataNum = dataNum;
	}
	
	public ProgramInfo getProgramInfo() {
		
		return programInfo;
	}
	
	public void setProgramInfo(ProgramInfo programInfo) {
		
		this.programInfo = programInfo;
	}
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}
	
	public OrganNode getOrganNode() {
		
		return organNode;
	}
	
	public void setOrganNode(OrganNode organNode) {
		
		this.organNode = organNode;
	}
}
