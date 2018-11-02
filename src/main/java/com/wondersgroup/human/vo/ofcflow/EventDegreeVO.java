/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EventDegreeVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 流程信息 学位 临时表 VO
 * 创建人: tzy
 * 创建时间: 2018年8月2日 下午3:18:44
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月2日 下午3:18:44
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofcflow.EventDegree;

/**
 * @ClassName: EventDegreeVO
 * @Description: 流程信息 学位 临时表 VO
 * @author: tzy
 * @date: 2018年8月2日 下午3:18:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class EventDegreeVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * @fieldName: 学位名称
	 */
	private String name;
	
	/**
	 * @fieldName: 学位授予日期
	 */
	private String grantDate;
	
	/**
	 * @fieldName: 学位授予单位
	 */
	private String grantUnit;
	
	/**
	 * @fieldName: 学位证书号
	 */
	private String degreeNo;
	
	public EventDegreeVO() {
		
	}
	
	public EventDegreeVO(EventDegree e) {
		this.id = e.getId();
		this.name = e.getName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (e.getGrantDate() != null) {
			this.grantDate = sdf.format(e.getGrantDate());
		}
		this.grantUnit = e.getGrantUnit();
		this.degreeNo = e.getDegreeNo();
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getGrantDate() {
		
		return grantDate;
	}
	
	public void setGrantDate(String grantDate) {
		
		this.grantDate = grantDate;
	}
	
	public String getGrantUnit() {
		
		return grantUnit;
	}
	
	public void setGrantUnit(String grantUnit) {
		
		this.grantUnit = grantUnit;
	}
	
	public String getDegreeNo() {
		
		return degreeNo;
	}
	
	public void setDegreeNo(String degreeNo) {
		
		this.degreeNo = degreeNo;
	}
}
