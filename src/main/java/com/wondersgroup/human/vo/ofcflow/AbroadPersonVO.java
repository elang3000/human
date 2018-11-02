/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPersonVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月28日 下午3:35:59 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月28日 下午3:35:59 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;
import com.wondersgroup.human.bo.ofcflow.AbroadPlan;

/** 
 * @ClassName: AbroadPersonVO 
 * @Description: 因公出国人员名单
 * @author: lihao
 * @date: 2018年9月28日 下午3:35:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class AbroadPersonVO {

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
	 * @fieldName: birthDate
	 * @fieldType: java.util.Date
	 * @Description: 出生日期，GB/T 7408-2005 该人在公安户籍管理部门登记注册的、在人事档案中记载的并经组织、干部、人事部门确认的出生年月日。
	 */
	private String birthDate;
	
	/**
	 * @fieldName: birthPlaceName
	 * @fieldType: java.lang.String
	 * @Description: 出生地名称,该人出生时所在地的当前县级及县级以上国家行政区划名称
	 */
	private String birthPlaceName;
	
	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位名称 ,该人人事关系所在的单位名称。
	 */
	private String departName;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 现任职务名称 ,该人担任职务的具体名称。
	 */
	private String postName;
	
	/**
	 * @Title:ServantVO
	 * @Description:将servant bo转换为vo
	 * @param s 转换的Servant BO对象
	 */
	public AbroadPersonVO(AbroadPlan s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		this.id = s.getId();
		this.name = s.getServant().getName();
		this.birthPlaceName = s.getServant().getBirthPlaceName();
		this.postName = s.getServant().getNowPostName();
		if (s.getServant().getBirthDate() != null) {
			this.birthDate = sdf.format(s.getServant().getBirthDate());
		}
		if (s.getServant().getSex() != null) {
			this.sex = s.getServant().getSex().getName();
		}
		this.departName = s.getServant().getDepartName();
	}

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
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the birthPlaceName
	 */
	public String getBirthPlaceName() {
		return birthPlaceName;
	}

	/**
	 * @param birthPlaceName the birthPlaceName to set
	 */
	public void setBirthPlaceName(String birthPlaceName) {
		this.birthPlaceName = birthPlaceName;
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
	 * @return the postName
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * @param postName the postName to set
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
}
