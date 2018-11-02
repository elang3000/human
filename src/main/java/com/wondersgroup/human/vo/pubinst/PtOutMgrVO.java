/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OutMgrVO.java
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

import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.pubinst.PtOutMgr;

/**
 * @ClassName: OutMgrVO
 * @Description: 人员信息子表-学位VO
 * @author: jiang
 * @date: 2018年7月2日14:08:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PtOutMgrVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 调出本单位类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调出或离开本单位的情况分类。GB/T 12405-2008
	 */
	private String category;
	
	/**
	 * @fieldName: 调出本单位日期
	 * @fieldType: java.util.Date
	 * @Description: 由组织、干部、人事、劳动部门签发的该人调出、退职、除名等文件的日期或者因其他原因实际离开的日期。GB/T 7408-2005
	 */
	private String outDate;
	
	/**
	 * @fieldName: 调往单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人调往的工作单位的名称。
	 */
	private String gotoUnitName;
	
	
	
	/**
	 * @fieldName: 调动原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人工作调动的原因。DM015
	 */
	private String reasonType;
	
	
	public PtOutMgrVO() {
		
	}
	
	/**
	 * @Title:OutMgrVO
	 * @Description:将OutMgr bo转换为vo
	 * @param s 转换的OutMgr BO对象
	 */
	public PtOutMgrVO(PtOutMgr s) {
		this.id = s.getId();
		this.gotoUnitName = s.getGotoUnitName();
		if (s.getCategory() != null) {
			this.category = s.getCategory().getName();
		}
		if (s.getOutDate() != null) {
			this.outDate = new SimpleDateFormat("yyyy-MM-dd").format(s.getOutDate());
		}
		if (s.getReasonType() != null) {
			this.reasonType = s.getReasonType().getName();
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

	
	public String getOutDate() {
		
		return outDate;
	}

	
	public void setOutDate(String outDate) {
		
		this.outDate = outDate;
	}

	
	public String getGotoUnitName() {
		
		return gotoUnitName;
	}

	
	public void setGotoUnitName(String gotoUnitName) {
		
		this.gotoUnitName = gotoUnitName;
	}

	
	public String getReasonType() {
		
		return reasonType;
	}

	
	public void setReasonType(String reasonType) {
		
		this.reasonType = reasonType;
	}
	
	
}
