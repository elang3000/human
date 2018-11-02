/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: FormationControlVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.organization
 * 描述: TODO
 * 创建人: jiang
 * 创建时间: 2018年10月25日15:08:46
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年10月25日15:08:48
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.vo.organization;

import com.wondersgroup.human.bo.organization.FormationControl;

/**
 * @ClassName: FormationControlVO
 * @Description: TODO
 * @author: jiang
 * @date: 2018年10月25日15:08:52
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */

public class FormationControlVO {
	
	private String id;
	
	/**
	 * 单位名称
	 **/
	private String orgInfo;
	
	
	/**
	 * 是否进行边控
	 **/
	private String isOpenControl;
	
	/**
	 * 是否高职低配（副科没位子，就去正科占用）
	 */
	private String isLowToHigh;
	
	/**
	 * 人员编制溢出规则
	 */
	private String overflowRule;
	
	/**
	 * 人员编制溢出人数
	 */
	private String overflowNum;
	
	public FormationControlVO() {
		
	}
	
	public FormationControlVO(FormationControl formationControl) {
		this.id = formationControl.getId();
		this.overflowRule = formationControl.getOverflowRule();
		this.overflowNum = String.valueOf(formationControl.getOverflowNum());
		if (formationControl.getOrgan() != null) {
			this.orgInfo = formationControl.getOrgan().getName();
		}
		if (formationControl.getIsOpenControl() != null) {
			this.isOpenControl = formationControl.getIsOpenControl().getName();
		}
		if (formationControl.getIsLowToHigh() != null) {
			this.isLowToHigh = formationControl.getIsLowToHigh().getName();
		}
	}

	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	
	public String getOrgInfo() {
		
		return orgInfo;
	}

	
	public void setOrgInfo(String orgInfo) {
		
		this.orgInfo = orgInfo;
	}

	
	public String getIsOpenControl() {
		
		return isOpenControl;
	}

	
	public void setIsOpenControl(String isOpenControl) {
		
		this.isOpenControl = isOpenControl;
	}

	
	public String getIsLowToHigh() {
		
		return isLowToHigh;
	}

	
	public void setIsLowToHigh(String isLowToHigh) {
		
		this.isLowToHigh = isLowToHigh;
	}

	
	public String getOverflowRule() {
		
		return overflowRule;
	}

	
	public void setOverflowRule(String overflowRule) {
		
		this.overflowRule = overflowRule;
	}

	
	public String getOverflowNum() {
		
		return overflowNum;
	}

	
	public void setOverflowNum(String overflowNum) {
		
		this.overflowNum = overflowNum;
	}
	
}
