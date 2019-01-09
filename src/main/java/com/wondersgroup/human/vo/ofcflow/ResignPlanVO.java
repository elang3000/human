/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignPlanVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月20日 下午3:47:47 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月20日 下午3:47:47 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.human.bo.ofcflow.ResignPlan;

/** 
 * @ClassName: ResignPlanVO 
 * @Description: 辞职批次VO
 * @author: lihao
 * @date: 2018年12月20日 下午3:47:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ResignPlanVO {
	
	@Autowired
	UserService userService;
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: status
	 * @fieldType: String
	 * @Description: 状态文字
	 */
	private String status;
	
	/**
	 * @fieldName: statusSign
	 * @fieldType: Integer
	 * @Description: 状态数字
	 */
	private Integer statusSign;
	
	/**
	 * @fieldName: resignName
	 * @fieldType: java.lang.String
	 * @Description: 辞职批次名称
	 */
	private String resignName;
	
	/**
	 * @fieldName: resignNumber
	 * @fieldType: Integer
	 * @Description: 人数
	 */
	private Integer resignNumber;
	
	private String createTime;
	
	public ResignPlanVO(ResignPlan r){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = r.getId();
		this.resignName = r.getResignName();
		this.resignNumber = r.getResignNumber();
		this.statusSign = r.getStatus();
		this.status = convertState(r.getStatus());
		if (r.getCreateTime() != null) {
			this.createTime = sdf.format(r.getCreateTime());
		}
	}
	
	public String convertState(int state) {
		if (state == 1) {
			return "待提交辞职信息";
		} else if (state == 2) {
			return "待区人事主管部门确认";
		} else if (state == 3) {
			return "待区人事主管部门打印";
		} else if (state == 4) {
			return "区人事主管部门已备案";
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
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusSign
	 */
	public Integer getStatusSign() {
		return statusSign;
	}

	/**
	 * @param statusSign the statusSign to set
	 */
	public void setStatusSign(Integer statusSign) {
		this.statusSign = statusSign;
	}

	/**
	 * @return the resignName
	 */
	public String getResignName() {
		return resignName;
	}

	/**
	 * @param resignName the resignName to set
	 */
	public void setResignName(String resignName) {
		this.resignName = resignName;
	}

	/**
	 * @return the resignNumber
	 */
	public Integer getResignNumber() {
		return resignNumber;
	}

	/**
	 * @param resignNumber the resignNumber to set
	 */
	public void setResignNumber(Integer resignNumber) {
		this.resignNumber = resignNumber;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
