/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: StudyVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-学习（培训、进修）VO
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:08:15
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:08:18
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.pubinst;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Study;
import com.wondersgroup.human.bo.pubinst.PtStudy;

/**
 * @ClassName: 
 * @Description: 人员信息子表-学习（培训、进修）VO
 * @author: jiang
 * @date: 2018年7月2日14:08:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PtStudyVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 学习（培训、进修）类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人参加工作后接受的培训、进修等教育的类别。DM040
	 */
	private String category;
	
	/**
	 * @fieldName: 学习（培训、进修）起始日期
	 * @fieldType: java.util.Date
	 * @Description: 实际学习的起始日期。GB/T 7408-2005
	 */
	private String startDate;
	
	/**
	 * @fieldName: 学习（培训、进修）结束日期
	 * @fieldType: java.util.Date
	 * @Description: 该人参加培训或进修的实际结束日期。GB/T 7408-2005
	 */
	private String endDate;
	
	/**
	 * @fieldName: 学习（培训、进修）主办单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人参加培训或进修的主办单位名称。
	 */
	private String hostUnitName;
	
	/**
	 * @fieldName: 在学单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人学习培训时所在的教育部门或单位的名称。
	 */
	private String studyUnitName;
	
	public PtStudyVO() {
		
	}
	
	/**
	 * @Title:
	 * @Description:将Study bo转换为vo
	 * @param s 转换的Study BO对象
	 */
	public PtStudyVO(PtStudy s) {
		this.id = s.getId();
		this.hostUnitName = s.getHostUnitName();
		this.studyUnitName = s.getStudyUnitName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getStartDate() != null) {
			this.startDate = sdf.format(s.getStartDate());
		}
		if (s.getEndDate() != null) {
			this.endDate = sdf.format(s.getEndDate());
		}
		if (s.getCategory() != null) {
			this.category = s.getCategory().getName();
		}
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getCategory() {
		
		return category;
	}

	
	public void setCategory(String category) {
		
		this.category = category;
	}

	
	public String getStartDate() {
		
		return startDate;
	}

	
	public void setStartDate(String startDate) {
		
		this.startDate = startDate;
	}

	
	public String getEndDate() {
		
		return endDate;
	}

	
	public void setEndDate(String endDate) {
		
		this.endDate = endDate;
	}

	
	public String getHostUnitName() {
		
		return hostUnitName;
	}

	
	public void setHostUnitName(String hostUnitName) {
		
		this.hostUnitName = hostUnitName;
	}

	
	public String getStudyUnitName() {
		
		return studyUnitName;
	}

	
	public void setStudyUnitName(String studyUnitName) {
		
		this.studyUnitName = studyUnitName;
	}
	
}
