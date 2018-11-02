/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EventFamilyVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 流程信息 奖励和惩罚 VO
 * 创建人: tzy
 * 创建时间: 2018年8月2日 下午3:18:44
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月2日 下午3:18:44
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofcflow.EventRewardAndPunish;

/**
 * @ClassName: EventFamilyVO
 * @Description: 流程信息 奖励和惩罚 VO
 * @author: tzy
 * @date: 2018年8月2日 下午3:18:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class EventRewardAndPunishVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * @fieldName: 人员受奖惩综述
	 */
	private String reviews;
	
	/**
	 * @fieldName: 人员受奖惩类别
	 */
	private String category;
	
	/**
	 * @fieldName: 人员受奖励名称
	 */
	private String rewardName;
	
	/**
	 * @fieldName: 人员奖励批准日期
	 */
	private String rewardApprovalDate;
	
	/**
	 * @fieldName: 人员奖励批准单位名称
	 */
	private String rewardApprovalUnitName;
	
	/**
	 * @fieldName: 人员受奖励原因
	 */
	private String rewardReason;
	
	/**
	 * @fieldName: 人员受惩戒名称
	 */
	private String punishName;
	
	/**
	 * @fieldName: 人员受惩戒批准日期
	 */
	private String punishApprovalDate;
	
	/**
	 * @fieldName: 人员受惩戒批准单位名称
	 */
	private String punishApprovalUnitName;
	
	/**
	 * @fieldName: 人员受惩戒原因
	 */
	private String punishReason;
	
	public EventRewardAndPunishVO() {
		
	}
	
	public EventRewardAndPunishVO(EventRewardAndPunish e) {
		this.id = e.getId();
		this.reviews = e.getReviews();
		if (e.getCategory() != null) {
			this.category = e.getCategory().getName();
		}
		this.rewardName = e.getRewardName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (e.getRewardApprovalDate() != null) {
			this.rewardApprovalDate = sdf.format(e.getRewardApprovalDate());
		}
		this.rewardApprovalUnitName = e.getRewardApprovalUnitName();
		if (e.getRewardReason() != null) {
			this.rewardReason = e.getRewardReason().getName();
		}
		this.punishName = e.getPunishName();
		if (e.getPunishApprovalDate() != null) {
			this.punishApprovalDate = sdf.format(e.getPunishApprovalDate());
		}
		this.punishApprovalUnitName = e.getPunishApprovalUnitName();
		if (e.getPunishReason() != null) {
			this.punishReason = e.getPunishReason().getName();
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getReviews() {
		
		return reviews;
	}
	
	public void setReviews(String reviews) {
		
		this.reviews = reviews;
	}
	
	public String getCategory() {
		
		return category;
	}
	
	public void setCategory(String category) {
		
		this.category = category;
	}
	
	public String getRewardName() {
		
		return rewardName;
	}
	
	public void setRewardName(String rewardName) {
		
		this.rewardName = rewardName;
	}
	
	public String getRewardApprovalDate() {
		
		return rewardApprovalDate;
	}
	
	public void setRewardApprovalDate(String rewardApprovalDate) {
		
		this.rewardApprovalDate = rewardApprovalDate;
	}
	
	public String getRewardApprovalUnitName() {
		
		return rewardApprovalUnitName;
	}
	
	public void setRewardApprovalUnitName(String rewardApprovalUnitName) {
		
		this.rewardApprovalUnitName = rewardApprovalUnitName;
	}
	
	public String getRewardReason() {
		
		return rewardReason;
	}
	
	public void setRewardReason(String rewardReason) {
		
		this.rewardReason = rewardReason;
	}
	
	public String getPunishName() {
		
		return punishName;
	}
	
	public void setPunishName(String punishName) {
		
		this.punishName = punishName;
	}
	
	public String getPunishApprovalDate() {
		
		return punishApprovalDate;
	}
	
	public void setPunishApprovalDate(String punishApprovalDate) {
		
		this.punishApprovalDate = punishApprovalDate;
	}
	
	public String getPunishApprovalUnitName() {
		
		return punishApprovalUnitName;
	}
	
	public void setPunishApprovalUnitName(String punishApprovalUnitName) {
		
		this.punishApprovalUnitName = punishApprovalUnitName;
	}
	
	public String getPunishReason() {
		
		return punishReason;
	}
	
	public void setPunishReason(String punishReason) {
		
		this.punishReason = punishReason;
	}
	
}
