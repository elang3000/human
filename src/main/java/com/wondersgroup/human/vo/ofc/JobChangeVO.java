/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: JobChangeVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-职务变动VO
 * 创建人: jiang
 * 创建时间: 2018年11月20日10:45:14
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年11月20日10:45:17
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.JobChange;

/**
 * @ClassName: JobChangeVO
 * @Description: 人员信息子表-职务变动VO
 * @author: jiang
 * @date: 2018年7月2日14:08:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class JobChangeVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 原单位名称
	 * @fieldType: java.lang.String
	 * @Description: 与该人原任职务相对应的工作机构及工作机构部门名称。
	 */
	private String formerUnitName;
	
	/**
	 * @fieldName: 新单位名称
	 * @fieldType: java.lang.String
	 * @Description: 与该人新任职务相对应的工作机构及工作机构部门名称。
	 */
	private String newUnitName;
	
	/**
	 * @fieldName:职务变动日期
	 * @fieldType: java.util.Date
	 * @Description: 该人此次职务变动发生的日期，一般为由具有法定管理权限的机构签发
	 *               的文件确定的该职务任职或免职的日期或由会议决定该职务任职或免职的日期。GB/T 7408-2005
	 */
	private String postChangeDate;
	
	/**
	 * @fieldName: 职务变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务相对于原任职务变动的类别划分。DM006
	 */
	private String postTenureChange;
	
	public JobChangeVO() {
		
	}
	
	/**
	 * @Title:JobChangeVO
	 * @Description:将JobChange bo转换为vo
	 * @param s 转换的JobChange BO对象
	 */
	public JobChangeVO(JobChange s) {
		this.id = s.getId();
		this.formerUnitName = s.getFormerUnitName();
		this.newUnitName = s.getNewUnitName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getPostChangeDate() != null) {
			this.postChangeDate = sdf.format(s.getPostChangeDate());
		}
		if(s.getPostTenureChange() != null){
			this.postTenureChange = s.getPostTenureChange().getName();
		}
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getFormerUnitName() {
		
		return formerUnitName;
	}

	
	public void setFormerUnitName(String formerUnitName) {
		
		this.formerUnitName = formerUnitName;
	}

	
	public String getNewUnitName() {
		
		return newUnitName;
	}

	
	public void setNewUnitName(String newUnitName) {
		
		this.newUnitName = newUnitName;
	}

	
	public String getPostChangeDate() {
		
		return postChangeDate;
	}

	
	public void setPostChangeDate(String postChangeDate) {
		
		this.postChangeDate = postChangeDate;
	}

	
	public String getPostTenureChange() {
		
		return postTenureChange;
	}

	
	public void setPostTenureChange(String postTenureChange) {
		
		this.postTenureChange = postTenureChange;
	}
	
	
	
}
