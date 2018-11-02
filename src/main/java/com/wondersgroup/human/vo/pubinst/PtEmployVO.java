/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EmployVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-录聘VO
 * 创建人: jiang
 * 创建时间: 2018年6月8日14:50:53
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月8日14:50:53
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.pubinst;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Employ;
import com.wondersgroup.human.bo.pubinst.PtEmploy;

/**
 * @ClassName: EmployVO
 * @Description: 人员信息子表-录聘VO
 * @author: jiang
 * @date: 2018年6月8日14:50:53
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PtEmployVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 批准录用日期
	 * @fieldType: java.lang.String
	 * @Description: 该人进入公务员、干部队伍的具体日期。GB/T 7408-2005。
	 */
	private String approveEmployDate;
	
	/**
	 * @fieldName: 录用部门
	 * @fieldType: java.lang.String
	 * @Description: 该人被录用的部门名称。
	 */
	private String employDept;
	
	/**
	 * @fieldName: 录用职位
	 * @fieldType: java.lang.String
	 * @Description: 该人被录用时的职位名称。GB/T 7408-2005。
	 */
	private String employJob;
	
	/**
	 * @fieldName:来源
	 * @fieldType: java.lang.String
	 * @Description: 单位增员类别代码（部分）。
	 */
	private String shSource;
	
	public PtEmployVO() {
		
	}
	
	/**
	 * @Title:EmployVO
	 * @Description:将Employ bo转换为vo
	 * @param s 转换的Employ BO对象
	 */
	public PtEmployVO(PtEmploy s) {
		this.id = s.getId();
		this.employDept = s.getEmployDept();
		if (s.getApproveEmployDate() != null) {
			this.approveEmployDate = new SimpleDateFormat("yyyy-MM-dd").format(s.getApproveEmployDate());
		}
		if (s.getEmployJob() != null) {
			this.employJob = s.getEmployJob().getName();
		}
		if (s.getShSource() != null) {
			this.shSource = s.getShSource().getName();
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getApproveEmployDate() {
		
		return approveEmployDate;
	}
	
	public void setApproveEmployDate(String approveEmployDate) {
		
		this.approveEmployDate = approveEmployDate;
	}
	
	public String getEmployDept() {
		
		return employDept;
	}
	
	public void setEmployDept(String employDept) {
		
		this.employDept = employDept;
	}
	
	public String getEmployJob() {
		
		return employJob;
	}
	
	public void setEmployJob(String employJob) {
		
		this.employJob = employJob;
	}
	
	public String getShSource() {
		
		return shSource;
	}
	
	public void setShSource(String shSource) {
		
		this.shSource = shSource;
	}
	
}
