/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RewardCountVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年10月31日 上午10:55:47 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年10月31日 上午10:55:47 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.analysis;

import java.math.BigDecimal;
import java.util.Map;

/** 
 * @ClassName: RewardCountVO 
 * @Description: 专项统计-奖励
 * @author: lihao
 * @date: 2018年10月31日 上午10:55:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class RewardCountVO {

	String departId;//单位id
	String departName;//单位名
	Integer houor;//荣誉称号
	Integer commendation;//嘉奖
	Integer firReward;//一等功
	Integer secReward;//二等功
	Integer thiReward;//三等功
	
	public RewardCountVO(Map<String,Object> map){
		if(map.get("DEPARTID")!=null)
			this.departId=(String)map.get("DEPARTID");
		if(map.get("DEPARTNAME")!=null)
			this.departName=(String)map.get("DEPARTNAME");
		if(map.get("HOUOR")!=null)
			this.houor=((BigDecimal)map.get("HOUOR")).intValue();
		if(map.get("COMMENDATION")!=null)
			this.commendation=((BigDecimal)map.get("COMMENDATION")).intValue();
		if(map.get("FIRREWARD")!=null)
			this.firReward=((BigDecimal)map.get("FIRREWARD")).intValue();
		if(map.get("SECREWARD")!=null)
			this.secReward=((BigDecimal)map.get("SECREWARD")).intValue();
		if(map.get("THIREWARD")!=null)
			this.thiReward=((BigDecimal)map.get("THIREWARD")).intValue();
	}

	/**
	 * @return the departId
	 */
	public String getDepartId() {
		return departId;
	}

	/**
	 * @param departId the departId to set
	 */
	public void setDepartId(String departId) {
		this.departId = departId;
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
	 * @return the houor
	 */
	public Integer getHouor() {
		return houor;
	}

	/**
	 * @param houor the houor to set
	 */
	public void setHouor(Integer houor) {
		this.houor = houor;
	}

	/**
	 * @return the commendation
	 */
	public Integer getCommendation() {
		return commendation;
	}

	/**
	 * @param commendation the commendation to set
	 */
	public void setCommendation(Integer commendation) {
		this.commendation = commendation;
	}

	/**
	 * @return the firReward
	 */
	public Integer getFirReward() {
		return firReward;
	}

	/**
	 * @param firReward the firReward to set
	 */
	public void setFirReward(Integer firReward) {
		this.firReward = firReward;
	}

	/**
	 * @return the secReward
	 */
	public Integer getSecReward() {
		return secReward;
	}

	/**
	 * @param secReward the secReward to set
	 */
	public void setSecReward(Integer secReward) {
		this.secReward = secReward;
	}

	/**
	 * @return the thiReward
	 */
	public Integer getThiReward() {
		return thiReward;
	}

	/**
	 * @param thiReward the thiReward to set
	 */
	public void setThiReward(Integer thiReward) {
		this.thiReward = thiReward;
	}
}
