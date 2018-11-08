/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: MagCountVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月8日 上午10:41:49 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月8日 上午10:41:49 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.analysis;

import java.math.BigDecimal;
import java.util.Map;

/** 
 * @ClassName: MagCountVO 
 * @Description: 进出管统计
 * @author: lihao
 * @date: 2018年11月8日 上午10:41:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class MagCountVO {

	String departId;//单位id
	String departName;//单位名
	Integer in;//进
	Integer out;//出
	Integer mag;//管理
	
	public MagCountVO(Map<String,Object> map){
		if(map.get("DEPARTID")!=null)
			this.departId=(String)map.get("DEPARTID");
		if(map.get("DEPARTNAME")!=null)
			this.departName=(String)map.get("DEPARTNAME");
		if(map.get("INMAG")!=null)
			this.in=((BigDecimal)map.get("INMAG")).intValue();
		if(map.get("OUTMAG")!=null)
			this.out=((BigDecimal)map.get("OUTMAG")).intValue();
		if(map.get("MAG")!=null)
			this.mag=((BigDecimal)map.get("MAG")).intValue();
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
	 * @return the in
	 */
	public Integer getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(Integer in) {
		this.in = in;
	}

	/**
	 * @return the out
	 */
	public Integer getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(Integer out) {
		this.out = out;
	}

	/**
	 * @return the mag
	 */
	public Integer getMag() {
		return mag;
	}

	/**
	 * @param mag the mag to set
	 */
	public void setMag(Integer mag) {
		this.mag = mag;
	}
}
