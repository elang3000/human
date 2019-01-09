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

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FormationControl extends GenericEntity {
	
	private static final long serialVersionUID = 299081902696419075L;
	
	/**
	 * 机关职级map
	 * key：标识，value：字典code
	 * 实空=定编数-在编数-处级领导空缺数-供给关系尚未调入人员+允许溢出人员数
	 */
	
	public final static String DIRECTOR ="131";// 正处
	public final static String DEPUTY_DIRECTOR ="132";// 副处
	public final static String SECTION_CHIEF ="141";// 正科
	public final static String DEPUTY_SECTION_CHIEF ="142";// 副科
	public final static String CLERK ="150";// 科员
	public final static String C_CLERK ="160";// 办事员
	
	
	/**
	 * 事业单位职级
	 * 实空=定编数-在编数-供给关系尚未调入人员+允许溢出人员数
	 */
	public final static String SYDW_MGR_I ="9001";// 管理一级
	public final static String SYDW_MGR_II ="9002";// 管理二级
	public final static String SYDW_MGR_III ="9003";// 管理三级
	public final static String SYDW_MGR_IV ="9004";// 管理四级
	public final static String SYDW_MGR_V ="9005";// 管理五级
	public final static String SYDW_MGR_VI ="9006";// 管理六级
	public final static String SYDW_MGR_VII ="9007";// 管理七级
	public final static String SYDW_MGR_VIII ="9008";// 管理八级
	public final static String SYDW_MGR_IX ="9009";// 管理九级
	public final static String SYDW_MGR_X ="9010";// 管理十级
	
	public final static String SYDW_TECH_I = "9101"; // 专技一级
	public final static String SYDW_TECH_II = "9102"; // 专技二级
	public final static String SYDW_TECH_III = "9103"; // 专技三级
	public final static String SYDW_TECH_IV = "9104"; // 专技四级
	public final static String SYDW_TECH_V = "9105"; // 专技五级
	public final static String SYDW_TECH_VI = "9106"; // 专技六级
	public final static String SYDW_TECH_VII = "9107"; // 专技七级
	public final static String SYDW_TECH_VIII = "9108"; // 专技八级
	public final static String SYDW_TECH_IX = "9109"; // 专技九级
	public final static String SYDW_TECH_X = "9110"; // 专技十级
	public final static String SYDW_TECH_XI = "9111"; // 专技十一级
	public final static String SYDW_TECH_XII = "9112"; // 专技十二级
	public final static String SYDW_TECH_XIII = "9113"; // 专技十三级
	
	public final static String SYDW_WORK_I = "9201"; // 工勤技术工一级
	public final static String SYDW_WORK_II = "9202"; // 工勤技术工二级
	public final static String SYDW_WORK_III = "9203"; // 工勤技术工三级
	public final static String SYDW_WORK_IV = "9204"; // 工勤技术工四级
	public final static String SYDW_WORK_V = "9205"; // 工勤技术工五级
	
	
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
