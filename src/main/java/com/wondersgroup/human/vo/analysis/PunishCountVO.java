/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PunishCountVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月5日 下午2:14:09 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月5日 下午2:14:09 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.analysis;

import java.math.BigDecimal;
import java.util.Map;

/** 
 * @ClassName: PunishCountVO 
 * @Description: 专项统计-处分
 * @author: lihao
 * @date: 2018年11月5日 下午2:14:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class PunishCountVO {
	String departId;//单位id
	String departName;//单位名
	Integer warn;//警告
	Integer demerit;//记过
	Integer seriousDemerit;//记大过
	Integer degrade;//降级
	Integer dismiss;//撤职
	Integer fire;//开除
	
	public PunishCountVO(Map<String,Object> map){
		if(map.get("DEPARTID")!=null)
			this.departId=(String)map.get("DEPARTID");
		if(map.get("DEPARTNAME")!=null)
			this.departName=(String)map.get("DEPARTNAME");
		if(map.get("FIRE")!=null)
			this.fire=((BigDecimal)map.get("FIRE")).intValue();
		if(map.get("WARN")!=null)
			this.warn=((BigDecimal)map.get("WARN")).intValue();
		if(map.get("SERIOUSDEMERIT")!=null)
			this.seriousDemerit=((BigDecimal)map.get("SERIOUSDEMERIT")).intValue();
		if(map.get("DEMERIT")!=null)
			this.demerit=((BigDecimal)map.get("DEMERIT")).intValue();
		if(map.get("DEGRADE")!=null)
			this.degrade=((BigDecimal)map.get("DEGRADE")).intValue();
		if(map.get("DISMISS")!=null)
			this.dismiss=((BigDecimal)map.get("DISMISS")).intValue();
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
	 * @return the warn
	 */
	public Integer getWarn() {
		return warn;
	}

	/**
	 * @param warn the warn to set
	 */
	public void setWarn(Integer warn) {
		this.warn = warn;
	}

	/**
	 * @return the demerit
	 */
	public Integer getDemerit() {
		return demerit;
	}

	/**
	 * @param demerit the demerit to set
	 */
	public void setDemerit(Integer demerit) {
		this.demerit = demerit;
	}

	/**
	 * @return the seriousDemerit
	 */
	public Integer getSeriousDemerit() {
		return seriousDemerit;
	}

	/**
	 * @param seriousDemerit the seriousDemerit to set
	 */
	public void setSeriousDemerit(Integer seriousDemerit) {
		this.seriousDemerit = seriousDemerit;
	}

	/**
	 * @return the degrade
	 */
	public Integer getDegrade() {
		return degrade;
	}

	/**
	 * @param degrade the degrade to set
	 */
	public void setDegrade(Integer degrade) {
		this.degrade = degrade;
	}

	/**
	 * @return the dismiss
	 */
	public Integer getDismiss() {
		return dismiss;
	}

	/**
	 * @param dismiss the dismiss to set
	 */
	public void setDismiss(Integer dismiss) {
		this.dismiss = dismiss;
	}

	/**
	 * @return the fire
	 */
	public Integer getFire() {
		return fire;
	}

	/**
	 * @param fire the fire to set
	 */
	public void setFire(Integer fire) {
		this.fire = fire;
	}
}
