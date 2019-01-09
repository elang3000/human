package com.wondersgroup.human.vo.instflow;


import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.human.bo.instflow.RecordableRecord;

/**
 * @ClassName: RecordableRecordVO
 * @Description:人员离退备案VO
 * @author: wangzhanfei
 * @date: 2018年9月13日 上午10:57:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class RecordableRecordVO {

private String id;
	
	private String name;
	private String sex;
	private String cardNo;
	private String remark;
	
	//离退人当前机构
	//private String recordOrgan;
	//离退日期
	private Date recordDate;
	//单位
	private String departName;
	
	//备案类型
	private String recodeWay;
	
	public RecordableRecordVO(){
		
	}
	
	public RecordableRecordVO(RecordableRecord record) {
		this.id = record.getId();
		this.name = record.getPublicInstitution().getName();
		this.cardNo = record.getPublicInstitution().getCardNoView();
		if(record.getPublicInstitution().getSex() != null){
			this.sex = record.getPublicInstitution().getSex().getName();
		}
		
		if (StringUtils.isNotBlank(record.getRemark())) {
			this.remark = record.getRemark();
		}
		//this.recordOrgan = record.getRecordOrgan().getName();
		this.recordDate = record.getRecordDate();
		this.departName = record.getPublicInstitution().getDepartName();
		this.recodeWay = record.getRecodeWay().getName();
	}
	
	

	public String getRecodeWay() {
		return recodeWay;
	}

	public void setRecodeWay(String recodeWay) {
		this.recodeWay = recodeWay;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
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

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	
	
}
