/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: LoanTo.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 借调 VO
 * 创建人: tzy
 * 创建时间: 2018年9月25日 下午3:17:58
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年9月25日 下午3:17:58
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofcflow.LoanTo;

/**
 * @ClassName: LoanTo
 * @Description: 借调 VO
 * @author: tzy
 * @date: 2018年9月25日 下午3:17:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class LoanToVO {
	
	/**
	 * @Description: ID。
	 */
	private String id;
	
	/**
	 * @Description: 人员姓名。
	 */
	private String name;
	
	/**
	 * @Description: 人员性别。
	 */
	private String sex;
	
	/**
	 * @Description: 人员身份证号。
	 */
	private String cardNo;
	
	/**
	 * @Description: 借出单位
	 */
	private String sourceOrgan;
	
	/**
	 * @Description: 去向单位名称。
	 */
	private String targetOrgan;
	
	/**
	 * @Description: 操作时间。
	 */
	private String createTime;
	
	/**
	 * @Description: 状态。
	 */
	private String status;
	
	public LoanToVO() {
		
	}
	
	public LoanToVO(LoanTo l) {
		this.id = l.getId();
		if (l.getServant() != null) {
			this.name = l.getServant().getName();
			this.sex = l.getServant().getSex().getName();
			this.cardNo = l.getServant().getCardNoView();
		}
		this.sourceOrgan = l.getSourceOrgan() == null ? "" : l.getSourceOrgan().getName();
		this.targetOrgan = l.getTargetOrgan();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.createTime = sdf.format(l.getCreateTime());
		this.status = String.valueOf(l.getStatus());
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(String sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
	
	public String getTargetOrgan() {
		
		return targetOrgan;
	}
	
	public void setTargetOrgan(String targetOrgan) {
		
		this.targetOrgan = targetOrgan;
	}
	
	public String getCreateTime() {
		
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		
		this.createTime = createTime;
	}
	
	public String getSex() {
		
		return sex;
	}
	
	public void setSex(String sex) {
		
		this.sex = sex;
	}
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
}
