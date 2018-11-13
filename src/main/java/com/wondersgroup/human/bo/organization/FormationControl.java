/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: FormationControl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.organization
 * 描述: 编制控制类
 * 创建人: jiang
 * 创建时间: 2018年10月23日10:03:17
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;

/**
 * 机构编制信息
 * @ClassName: FormationControl
 * @Description: 编制控制类
 * @author: jiang
 * @date: 2018年10月23日10:03:08
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_FORMATION_CONTROL")
public class FormationControl extends GenericEntity {
	
	private static final long serialVersionUID = 299081902696419075L;
	
	//正处
	public static final String DIRECTOR = "131";
	//副处
	public static final String DEPUTY_DIRECTOR = "132";
	//正科
	public static final String SECTION_CHIEF = "141";
	//副科
	public static final String DEPUTY_SECTION_CHIEF = "142";
	//科员
	public static final String CLERK = "150";
	//办事员
	public static final String C_CLERK = "160";
	
	/**
	 * 实空=定编数-在编数-处级领导空缺数-供给关系尚未调入人员+允许溢出人员数
	 */
	
	/**
	 * 关联组织结构
	 **/
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ORGAN_ID")
	private OrganNode organ;
	
	/**
	 * 是否进行边控
	 **/
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "IS_OPEN_CTRL")
	private CodeInfo isOpenControl;
	
	/**
	 * 是否高职低配（副科没位子，就去正科占用）
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "IS_LOW_HIGH")
	private CodeInfo isLowToHigh;
	
	/**
	 * 人员编制溢出规则
	 */
	@Column(name = "OVER_FLOW_RULE", length = 500)
	private String overflowRule;
	
	/**
	 * 人员编制溢出数量
	 */
	@Column(name = "OVER_FLOW_NUM", length = 2)
	private Integer overflowNum;
	
	public OrganNode getOrgan() {
		
		return organ;
	}
	
	public void setOrgan(OrganNode organ) {
		
		this.organ = organ;
	}
	
	public CodeInfo getIsOpenControl() {
		
		return isOpenControl;
	}
	
	public void setIsOpenControl(CodeInfo isOpenControl) {
		
		this.isOpenControl = isOpenControl;
	}
	
	public CodeInfo getIsLowToHigh() {
		
		return isLowToHigh;
	}
	
	public void setIsLowToHigh(CodeInfo isLowToHigh) {
		
		this.isLowToHigh = isLowToHigh;
	}
	
	public String getOverflowRule() {
		
		return overflowRule;
	}
	
	public void setOverflowRule(String overflowRule) {
		
		this.overflowRule = overflowRule;
	}
	
	public Integer getOverflowNum() {
		
		return overflowNum;
	}
	
	public void setOverflowNum(Integer overflowNum) {
		
		this.overflowNum = overflowNum;
	}
	
}
