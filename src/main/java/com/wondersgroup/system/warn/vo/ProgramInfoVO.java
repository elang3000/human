/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProgramInfoVO.java
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.vo
 * 描述: 预警预告：查询方案 VO
 * 创建人: tzy
 * 创建时间: 2018年10月29日 下午5:34:50
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年10月29日 下午5:34:50
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.system.warn.vo;

import java.text.SimpleDateFormat;

import com.wondersgroup.system.warn.bo.ProgramInfo;

/**
 * @ClassName: ProgramInfoVO
 * @Description: 预警预告：查询方案 VO
 * @author: tzy
 * @date: 2018年10月29日 下午5:34:50
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ProgramInfoVO {
	
	private String id;
	
	/**
	 * @Description: 方案名称
	 */
	private String name;
	
	/**
	 * @Description: 方案描述
	 */
	private String describe;
	
	/**
	 * @Description: 方案类型 1 预警 2 预告
	 */
	private String programType;
	
	/**
	 * @Description: 方案执行时间 定时器执行时间
	 */
	private String programTime;
	
	/**
	 * @Description: 数据创建时间
	 */
	private String createTime;
	
	/**
	 * @Description: 方案启动状态 1 启动 2 关闭
	 */
	private String programState;
	
	/**
	 * @Description: 通知内容
	 */
	private String newsContent;
	
	public ProgramInfoVO() {
		
	}
	
	public ProgramInfoVO(ProgramInfo p) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = p.getId();
		this.name = p.getName();
		this.describe = p.getDescribe();
		this.newsContent = p.getNewsContent();
		this.programTime = p.getProgramTimeDescribe();
		if (ProgramInfo.WARN.equals(p.getProgramType())) {
			this.programType = "预警方案";
		} else if (ProgramInfo.PREVIEW.equals(p.getProgramType())) {
			this.programType = "预告方案";
		}
		if (p.getCreateTime() != null) {
			this.createTime = sdf.format(p.getCreateTime());
		}
		this.programState = p.getProgramState();
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
	
	public String getProgramType() {
		
		return programType;
	}
	
	public void setProgramType(String programType) {
		
		this.programType = programType;
	}
	
	public String getProgramTime() {
		
		return programTime;
	}
	
	public void setProgramTime(String programTime) {
		
		this.programTime = programTime;
	}
	
	public String getCreateTime() {
		
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		
		this.createTime = createTime;
	}
	
	public String getProgramState() {
		
		return programState;
	}
	
	public void setProgramState(String programState) {
		
		this.programState = programState;
	}
	
	public String getDescribe() {
		
		return describe;
	}
	
	public void setDescribe(String describe) {
		
		this.describe = describe;
	}
	
	public String getNewsContent() {
		
		return newsContent;
	}
	
	public void setNewsContent(String newsContent) {
		
		this.newsContent = newsContent;
	}
}