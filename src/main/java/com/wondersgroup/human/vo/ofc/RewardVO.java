/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RewardVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-奖励VO
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:08:15
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:08:18
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import javax.persistence.Column;

import com.wondersgroup.human.bo.ofc.RewardAndPunish;

/**
 * @ClassName: RewardVO
 * @Description: 人员信息子表-奖励VO
 * @author: jiang
 * @date: 2018年7月2日14:08:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class RewardVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 人员受奖惩类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人因某种业绩成就受到党和国家某级组织给予的奖励或惩处的级别。DM019
	 */
	private String category;
	
	/**
	 * @fieldName: 人员受奖励决定原始文件
	 * @fieldType: java.lang.String
	 * @Description: 决定对该人进行奖励的原始文件号。
	 */
	private String rewardNo;
	
	/**
	 * @fieldName: 人员受奖励名称
	 * @fieldType: java.lang.String
	 * @Description: 党和国家某级组织授予该人的奖励及荣誉称号的名称。
	 */
	private String rewardName;
	
	/**
	 * @fieldName: 人员受奖励代码名称
	 * @fieldType: java.lang.String
	 * @Description: 党和国家某级组织授予该人的奖励及荣誉称号的名称。
	 */
	private String rewardCodeName;
	
	/**
	 * @fieldName: 人员奖励批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准授予奖励的某级组织机构的名称。
	 */
	private String rewardApprovalUnitName;
	
	/**
	 * @fieldName: 人员奖励批准日期
	 * @fieldType: java.util.Date
	 * @Description: 党和国家某级组织批准授予该人奖励的日期。GB/T 7408-2005
	 */
	private String rewardApprovalDate;
	
	/**
	 * @fieldName: 荣誉称号名称
	 * @fieldType: java.lang.String
	 * @Description: 受到党和国家某级组织授予该人的荣誉称号的名称。
	 */
	private String honoraryName;
	
	public RewardVO() {
		
	}
	
	/**
	 * @Title:RewardVO
	 * @Description:将Reward bo转换为vo
	 * @param s 转换的Reward BO对象
	 */
	public RewardVO(RewardAndPunish s) {
		this.id = s.getId();
		this.rewardNo = s.getRewardNo();
		this.rewardName = s.getRewardName();
		this.rewardApprovalUnitName = s.getRewardApprovalUnitName();
		this.honoraryName = s.getHonoraryName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getRewardApprovalDate() != null) {
			this.rewardApprovalDate = sdf.format(s.getRewardApprovalDate());
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

	
	public String getRewardNo() {
		
		return rewardNo;
	}

	
	public void setRewardNo(String rewardNo) {
		
		this.rewardNo = rewardNo;
	}

	
	public String getRewardName() {
		
		return rewardName;
	}

	
	public void setRewardName(String rewardName) {
		
		this.rewardName = rewardName;
	}

	
	public String getRewardApprovalUnitName() {
		
		return rewardApprovalUnitName;
	}

	
	public void setRewardApprovalUnitName(String rewardApprovalUnitName) {
		
		this.rewardApprovalUnitName = rewardApprovalUnitName;
	}

	
	public String getRewardApprovalDate() {
		
		return rewardApprovalDate;
	}

	
	public void setRewardApprovalDate(String rewardApprovalDate) {
		
		this.rewardApprovalDate = rewardApprovalDate;
	}

	
	public String getRewardCodeName() {
		
		return rewardCodeName;
	}

	
	public void setRewardCodeName(String rewardCodeName) {
		
		this.rewardCodeName = rewardCodeName;
	}

	
	public String getHonoraryName() {
		
		return honoraryName;
	}

	
	public void setHonoraryName(String honoraryName) {
		
		this.honoraryName = honoraryName;
	}
	
}
