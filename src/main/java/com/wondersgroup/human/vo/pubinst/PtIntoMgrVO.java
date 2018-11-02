/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: IntoMgrVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-学位VO
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

import com.wondersgroup.human.bo.ofc.IntoMgr;
import com.wondersgroup.human.bo.pubinst.PtIntoMgr;

/**
 * @ClassName: IntoMgrVO
 * @Description: 人员信息子表-学位VO
 * @author: jiang
 * @date: 2018年7月2日14:08:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PtIntoMgrVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	
	/**
	 * @fieldName: 进入本单位日期
	 * @fieldType: java.util.Date
	 * @Description: 由本单位人事管理部门认定的该人进入本单位工作的日期。GB/T 7408-2005
	 */
	private String enterTheUnitDate;
	
	/**
	 * @fieldName: 进入本单位变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 分为国家统一分配、调动、招收录用情况等。GB/T 12405-2008
	 */
	private String enterTheUnitChangeType;
	
	
	/**
	 * @fieldName: 进入本单位原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调入本单位原因。DM015
	 */
	private String enterReason;
	
	/**
	 * @fieldName: 进入本单位前工作单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人进入本单位前原工作单位名称。
	 */
	private String formerUnitName;
	
	
	public PtIntoMgrVO() {
		
	}
	
	/**
	 * @Title:IntoMgrVO
	 * @Description:将IntoMgr bo转换为vo
	 * @param s 转换的IntoMgr BO对象
	 */
	public PtIntoMgrVO(PtIntoMgr s) {
		this.id = s.getId();
		this.formerUnitName = s.getFormerUnitName();
		if (s.getEnterTheUnitChangeType() != null) {
			this.enterTheUnitChangeType = s.getEnterTheUnitChangeType().getName();
		}
		if (s.getEnterTheUnitDate() != null) {
			this.enterTheUnitDate = new SimpleDateFormat("yyyy-MM-dd").format(s.getEnterTheUnitDate());
		}
		if (s.getEnterReason() != null) {
			this.enterReason = s.getEnterReason().getName();
		}
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getEnterTheUnitDate() {
		
		return enterTheUnitDate;
	}

	
	public void setEnterTheUnitDate(String enterTheUnitDate) {
		
		this.enterTheUnitDate = enterTheUnitDate;
	}

	
	public String getEnterTheUnitChangeType() {
		
		return enterTheUnitChangeType;
	}

	
	public void setEnterTheUnitChangeType(String enterTheUnitChangeType) {
		
		this.enterTheUnitChangeType = enterTheUnitChangeType;
	}

	
	public String getEnterReason() {
		
		return enterReason;
	}

	
	public void setEnterReason(String enterReason) {
		
		this.enterReason = enterReason;
	}

	
	public String getFormerUnitName() {
		
		return formerUnitName;
	}

	
	public void setFormerUnitName(String formerUnitName) {
		
		this.formerUnitName = formerUnitName;
	}
	
	
}
