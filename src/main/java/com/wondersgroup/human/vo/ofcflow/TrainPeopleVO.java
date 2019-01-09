/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServantVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员基本信息VO
 * 创建人: tzy
 * 创建时间: 2018年5月28日 上午10:06:33
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月28日 上午10:06:33
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.TrainPeople;

/**
 * @ClassName: TrainPeopleVO
 * @Description:培训人员基本信息VO
 * @author: lihao
 * @date: 2018年5月28日 上午10:06:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class TrainPeopleVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名 ,在公安户籍管理部门登记注册、人事档案中记载的、正在使用的该人姓名全称。
	 */
	private String name;
	
	/**
	 * @fieldName: sex
	 * @fieldType: java.lang.String
	 * @Description: 性别。
	 */
	private String sex;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 公民身份证号 ,公安机关对（公民）居民给出的唯一的、终身不变的法定号码。
	 */
	private String cardNo;
	
	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位名称 ,该人人事关系所在的单位名称。
	 */
	private String departName;
	
	/**
	 * @Description: 人事关系所在单位ID。
	 */
	private String departId;
	
	/**
	 * @fieldName: jobLevel
	 * @fieldType: java.lang.String
	 * @Description: 现职务级别
	 */
	private String jobLevel;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	private String postName;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	private String postAttributeName;
	
	
	/**
	 * TrainPeopleVO
	 * @Description:将TrainPeople bo转换为vo
	 * @param s 转换的TrainPeople BO对象
	 */
	public TrainPeopleVO(TrainPeople s) {
		this.id = s.getId();
		this.name = s.getServant().getName();
		this.postName = s.getServant().getNowPostName();
		if (s.getServant().getSex() != null) {
			this.sex = s.getServant().getSex().getName();
		}
		this.cardNo = s.getServant().getCardNoView();
		this.departName = s.getServant().getDepartName();
		this.departId = s.getServant().getDepartId();
		if (s.getServant().getNowJobLevel() != null) {
			this.jobLevel = s.getServant().getNowJobLevel().getName();
		}
		if (s.getServant().getNowPostAttribute() != null) {
			this.postAttributeName = s.getServant().getNowPostAttribute().getName();
		}
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
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
	
	public String getJobLevel() {
		
		return jobLevel;
	}
	
	public void setJobLevel(String jobLevel) {
		
		this.jobLevel = jobLevel;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getPostName() {
		
		return postName;
	}
	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}
	
	public String getPostAttributeName() {
		
		return postAttributeName;
	}
	
	public void setPostAttributeName(String postAttributeName) {
		
		this.postAttributeName = postAttributeName;
	}
	
	public String getDepartId() {
		
		return departId;
	}
	
	public void setDepartId(String departId) {
		
		this.departId = departId;
	}
}
