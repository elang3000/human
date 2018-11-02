/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EventEducation.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 流程信息 学历 临时表
 * 创建人: tzy
 * 创建时间: 2018年8月2日 下午2:37:37
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月2日 下午2:37:37
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.human.bo.ofc.base.BaseEducation;

/**
 * @ClassName: EventEducation
 * @Description: 流程信息 学历
 * @author: tzy
 * @date: 2018年8月2日 下午2:37:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_OFCFLOW_EVENTEDUCATION")
public class EventEducation extends BaseEducation<EventEducation> {
	
	private static final long serialVersionUID = -807626600406332594L;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description:人员姓名，用于标识该职务信息属于哪个人员，因为长宁区中有两个人的身份证号不唯一。
	 */
	@Column(name = "NAME", length = 30)
	private String personName;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description:身份证号，用于标识该职务信息属于哪个人员。
	 */
	@Column(name = "CARDNO", length = 30)
	private String cardNo;
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public String getPersonName() {
		
		return personName;
	}
	
	public void setPersonName(String personName) {
		
		this.personName = personName;
	}
}
