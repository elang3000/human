/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: CompetenceVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-专业技术任职资格VO
 * 创建人: jiang
 * 创建时间: 2018年8月20日17:16:54
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月20日17:16:59
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Competence;

/**
 * @ClassName: CompetenceVO
 * @Description: 人员信息子表-专业技术任职资格VO
 * @author: jiang
 * @date: 2018年8月20日10:27:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class CompetenceVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 专业技术任职资格名称
	 * @fieldType: java.lang.String
	 * @Description: 职称的中文名称。
	 */
	private String name;
	
	/**
	 * @fieldName: 专业技术任职资格级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 职称的级别标识。GB/T 12407-2008
	 */
	private String lvl;
	
	/**
	 * @fieldName: 获得专业技术任职资格日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得专业技术职务任职资格的正式批准日期，以资格证书为准。GB/T 7408-2005
	 */
	private String gainDate;
	
	/**
	 * @fieldName: 专业技术任职资格评委会或考试名称
	 * @fieldType: java.lang.String
	 * @Description: 该人取得该专业技术职务任职资格通过的评委会或考试的全称。
	 */
	private String jury;
	
	/**
	 * @fieldName: 专业技术任职资格审批单位
	 * @fieldType: java.lang.String
	 * @Description: 专业技术人员取得专业技术资格的审批单位名称。
	 */
	private String approvalUnit;
	
	public CompetenceVO() {
		
	}
	
	/**
	 * @Title:CompetenceVO
	 * @Description:将Competence bo转换为vo
	 * @param s 转换的Competence BO对象
	 */
	public CompetenceVO(Competence s) {
		this.id = s.getId();
		this.name = s.getName();
		this.jury = s.getJury();
		this.approvalUnit = s.getApprovalUnit();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getGainDate() != null) {
			this.gainDate = sdf.format(s.getGainDate());
		}
		if (s.getLvl() != null) {
			this.lvl = s.getLvl().getName();
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

	
	public String getLvl() {
		
		return lvl;
	}

	
	public void setLvl(String lvl) {
		
		this.lvl = lvl;
	}

	
	public String getGainDate() {
		
		return gainDate;
	}

	
	public void setGainDate(String gainDate) {
		
		this.gainDate = gainDate;
	}

	
	public String getJury() {
		
		return jury;
	}

	
	public void setJury(String jury) {
		
		this.jury = jury;
	}

	
	public String getApprovalUnit() {
		
		return approvalUnit;
	}

	
	public void setApprovalUnit(String approvalUnit) {
		
		this.approvalUnit = approvalUnit;
	}
	
}
