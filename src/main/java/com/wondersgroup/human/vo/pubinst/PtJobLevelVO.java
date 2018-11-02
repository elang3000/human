/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: JobLevelVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-录聘VO
 * 创建人: jiang
 * 创建时间: 2018年6月12日09:52:11
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日09:52:14
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.pubinst;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.pubinst.PtJobLevel;

/**
 * @ClassName: JobLevelVO
 * @Description: 人员信息子表-职级VO
 * @author: jiang
 * @date: 2018年6月12日09:52:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PtJobLevelVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 职级名称
	 * @fieldType: java.lang.String
	 * @Description: 该人的职位等级或级别等级名称。
	 */
	private String name;
	
	/**
	 * @fieldName: 职级批准日期
	 * @fieldType: java.lang.String
	 * @Description:组织、干部、人事部门批准该人该职级的日期。
	 */
	private String approvalDate;
	
	/**
	 * @fieldName: 职级批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准该人该职级的具体单位名称。
	 */
	private String approvalUnitName;
	
	/**
	 * @fieldName: 职级批准文号
	 * @fieldType: java.lang.String
	 * @Description: 批准该人该职级的文件的编号全称。
	 */
	private String approvalNo;
	
	/**
	 * @fieldName: 职级终止日期
	 * @fieldType: java.lang.String
	 * @Description: 该人该职级的终止日期。
	 */
	private String endDate;
	
	
	public PtJobLevelVO() {
		
	}
	
	/**
	 * @Title:JobLevelVO
	 * @Description:将JobLevel bo转换为vo
	 * @param s 转换的JobLevel BO对象
	 */
	public PtJobLevelVO(PtJobLevel s) {
		this.id = s.getId();
		this.name = s.getName();
		this.approvalUnitName = s.getApprovalUnitName();
		this.approvalNo = s.getApprovalNo();
		if (s.getApprovalDate() != null) {
			this.approvalDate = new SimpleDateFormat("yyyy-MM-dd").format(s.getApprovalDate());
		}
		if (s.getEndDate() != null) {
			this.endDate = new SimpleDateFormat("yyyy-MM-dd").format(s.getEndDate());
		}
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
	
	public String getApprovalDate() {
		
		return approvalDate;
	}
	
	public void setApprovalDate(String approvalDate) {
		
		this.approvalDate = approvalDate;
	}
	
	public String getApprovalUnitName() {
		
		return approvalUnitName;
	}
	
	public void setApprovalUnitName(String approvalUnitName) {
		
		this.approvalUnitName = approvalUnitName;
	}
	
	public String getEndDate() {
		
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		
		this.endDate = endDate;
	}
	
	public String getApprovalNo() {
		
		return approvalNo;
	}

	
	public void setApprovalNo(String approvalNo) {
		
		this.approvalNo = approvalNo;
	}
	
}
