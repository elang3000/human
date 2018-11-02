/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.organization
 * 描述: TODO
 * 创建人: jiang
 * 创建时间: 2018年9月21日14:30:41
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月21日14:30:44
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.vo.organization;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.organization.OrgInfoHistory;

/**
 * @ClassName: OrgInfoVO
 * @Description: TODO
 * @author: jiang
 * @date: 2018年9月21日14:30:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */

public class OrgInfoHistoryVO {
	
	private String id;
	
	// 调整时间
	private String createTime;
	
	// 调整对象
	private String unitBasicName;
	
	// 调整类型
	private String optionType;
	
	public OrgInfoHistoryVO() {
		
	}
	
	public OrgInfoHistoryVO(OrgInfoHistory orgInfoHistory) {
		this.id = orgInfoHistory.getId();
		this.unitBasicName = orgInfoHistory.getOrgInfo().getUnitBasicName();
		if (orgInfoHistory.getOrgInfo().getCreateTime() != null) {
			this.createTime = new SimpleDateFormat("yyyy-MM-dd").format(orgInfoHistory.getOrgInfo().getCreateTime());
		}
		if (orgInfoHistory.getOrgInfoMgrFlow().getOptionType() != null) {
			this.optionType = orgInfoHistory.getOrgInfoMgrFlow().getOptionType().getName();
		}
		
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getCreateTime() {
		
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		
		this.createTime = createTime;
	}
	
	public String getUnitBasicName() {
		
		return unitBasicName;
	}
	
	public void setUnitBasicName(String unitBasicName) {
		
		this.unitBasicName = unitBasicName;
	}
	
	public String getOptionType() {
		
		return optionType;
	}
	
	public void setOptionType(String optionType) {
		
		this.optionType = optionType;
	}
	
}
