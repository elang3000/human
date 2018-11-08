/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerRecordVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月8日 上午11:35:31 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月8日 上午11:35:31 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.ManagerRecord;

/**
 * @ClassName: ItemRecordVO
 * @Description: TODO
 * @author: lihao
 * @date: 2018年6月8日上午11:35:31 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public class ItemRecordVO {

	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;

	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名
	 */
	private String name;

	/**
	 * @fieldName: sex
	 * @fieldType: java.lang.String
	 * @Description: 性别
	 */
	private String sex;

	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 身份证号
	 */
	private String cardNo;

	/**
	 * @fieldName: recordName
	 * @fieldType: java.lang.String
	 * @Description: 记录类型名称。
	 */
	private String recordType;

	/**
	 * @fieldName: managerType
	 * @fieldType: java.lang.String
	 * @Description: 进出管理类型。
	 */
	private String itemType;

	/**
	 * @fieldName: recordTime
	 * @fieldType: java.util.DATE
	 * @Description: 记录时间。
	 */
	private String recordTime;
	
	public ItemRecordVO(ManagerRecord m){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		this.id = m.getId();
		this.name = m.getServant().getName();
		if(m.getServant()!=null&&m.getServant().getSex()!=null){
			this.sex = m.getServant().getSex().getName();
		}
		this.cardNo = m.getServant().getCardNo();
		if(m.getRecordType()!=null){
			this.recordType = m.getRecordType().getName();
		}
		if (m.getRecordTime() != null) {
			this.recordTime  = sdf.format(m.getRecordTime()); 
		}
		this.itemType = convertState(m.getItemType());
	}
	
	public String convertState(int state) {
		if (state == 0) {
			return "进";
		} else if (state == 1) {
			return "出";
		} else if (state == 2) {
			return "管理";
		} else {
			return "";
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType
	 *            the recordType to set
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the recordTime
	 */
	public String getRecordTime() {
		return recordTime;
	}

	/**
	 * @param recordTime the recordTime to set
	 */
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
