/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainingHoursVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月13日 下午4:52:33 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月13日 下午4:52:33 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.pubinst;

/**
 * @ClassName: TrainingHoursVO
 * @Description: TODO
 * @author: lihao
 * @date: 2018年6月13日
 *        下午4:52:33 @version [版本号, YYYY-MM-DD] @see       [相关类/方法] @since     [产品/模块版本]
 */
public class PtTrainingHoursVO {
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;

	/**
	 * @fieldName: trainName
	 * @fieldType: java.lang.String
	 * @Description: 培训名称。
	 */
	private String trainName;

	/**
	 * @fieldName: hours
	 * @fieldType: java.lang.String
	 * @Description: 课时。
	 */
	private String hours;

	/**
	 * @fieldName: trainDetail
	 * @fieldType: String
	 * @Description: 培训详情
	 *
	 */
	private String trainDetail;

	/**
	 * @fieldName: name
	 * @fieldType: String
	 * @Description: 姓名
	 *
	 */
	private String name;

	/**
	 * @fieldName: sex
	 * @fieldType: String
	 * @Description: 性别
	 *
	 */
	private String sex;

	/**
	 * @fieldName: cardNo
	 * @fieldType: String
	 * @Description: 身份证号
	 *
	 */
	private String cardNo;

	/**
	 * @fieldName: departName
	 * @fieldType: String
	 * @Description: 所在单位
	 *
	 */
	private String departName;
	
	/**
	 * @fieldName: servantId
	 * @fieldType: String
	 * @Description: 人员ID
	 *
	 */
	private String servantId;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the trainName
	 */
	public String getTrainName() {
		return trainName;
	}

	/**
	 * @param trainName the trainName to set
	 */
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	/**
	 * @return the hours
	 */
	public String getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(String hours) {
		this.hours = hours;
	}

	/**
	 * @return the trainDetail
	 */
	public String getTrainDetail() {
		return trainDetail;
	}

	/**
	 * @param trainDetail the trainDetail to set
	 */
	public void setTrainDetail(String trainDetail) {
		this.trainDetail = trainDetail;
	}

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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
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
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return the servantId
	 */
	public String getServantId() {
		return servantId;
	}

	/**
	 * @param servantId the servantId to set
	 */
	public void setServantId(String servantId) {
		this.servantId = servantId;
	}
}
