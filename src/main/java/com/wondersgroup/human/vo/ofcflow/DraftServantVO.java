/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DraftServantVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 拟录用人员信息VO
 * 创建人: GP
 * 创建时间: 2018年6月26日 下午5:04:10
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2018年6月26日 下午5:04:10
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.enums.ServantTypeEnum;

/**
 * @ClassName: DraftServantVO
 * @Description: 拟录用人员信息VO
 * @author: GP
 * @date: 2018年6月26日 下午5:04:10
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class DraftServantVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 年度
	 */
	private String yearTip;
	
	/**
	 * 录用单位
	 */
	private String draftUnitName;
	
	/**
	 * 录用部门
	 */
	private String draftDeptName;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 身份证
	 */
	private String cardNo;
	
	/**
	 * 录用鉴定评语
	 */
	private String employComment;
	
	/**
	 * 录用标识
	 */
	private String employResult;
	
	/**
	 * 录用情况
	 */
	private String employSituation;
	
	private String state;
	
	/**
	 * 专业能力测试成绩
	 */
	private Float aptitudeTestScore;
	
	/**
	 * 公共科目笔试成绩
	 */
	private Float publicSubjectTestScore;
	
	/**
	 * 笔试（专业科目）成绩
	 */
	private Float professionalSubjectScore;
	
	/**
	 * 面试成绩
	 */
	private Float interviewScore;
	
	/**
	 * 其他科目成绩
	 */
	private Float otherSubjectScore;
	
	/**
	 * 政审考核
	 */
	private String politicalExam;
	/**
	 * 人员类型
	 */
	private Integer servantType;
	
	
	
	public String getServantType() {
		return ServantTypeEnum.getServantTypeEnumString(servantType);
	}

	public void setServantType(Integer servantType) {
		this.servantType = servantType;
	}

	public DraftServantVO() {
		
	}
	
	public DraftServantVO(DraftServant draftServant) {
		this.cardNo = draftServant.getCardNo();
		if (draftServant.getDraftDeptName() != null) {
			this.draftDeptName = draftServant.getDraftDeptName();
		} else {
			this.draftDeptName = "";
		}
		if (draftServant.getDraftUnitName() != null) {
			this.draftUnitName = draftServant.getDraftUnitName();
		} else {
			this.draftUnitName = "";
		}
		if (draftServant.getEmployComment() != null) {
			this.employComment = draftServant.getEmployComment();
		} else {
			this.employComment = "暂无";
		}
		
		if (draftServant.getEmployResult() != null) {
			this.employResult = draftServant.getEmployResult().getName();
		} else this.employResult = "暂无";
		if (draftServant.getEmploySituation() != null) {
			this.employSituation = draftServant.getEmploySituation().getName();
		} else this.employSituation = "暂无";
		
		this.name = draftServant.getName();
		if (draftServant.getSex() != null) {
			this.sex = draftServant.getSex().getName();
		} else {
			this.sex = "信息缺失";
		}
		if (draftServant.getYearTip() != null) {
			this.yearTip = draftServant.getYearTip() + "";
		} else {
			this.yearTip = "缺失";
		}
		
		if (draftServant.getStatus() == 0) {
			this.state = "未汇总";
		} else {
			this.state = "已汇总";
		}
		this.aptitudeTestScore = draftServant.getAptitudeTestScore();
		this.publicSubjectTestScore = draftServant.getPublicSubjectTestScore();
		this.professionalSubjectScore = draftServant.getProfessionalSubjectScore();
		this.interviewScore = draftServant.getInterviewScore();
		this.otherSubjectScore = draftServant.getOtherSubjectScore();
		this.politicalExam = draftServant.getPoliticalExam();
		this.id = draftServant.getId();
		this.servantType=draftServant.getServantType();
	}
	
	public String getYearTip() {
		
		return yearTip;
	}
	
	public void setYearTip(String yearTip) {
		
		this.yearTip = yearTip;
	}
	
	public String getDraftUnitName() {
		
		return draftUnitName;
	}
	
	public void setDraftUnitName(String draftUnitName) {
		
		this.draftUnitName = draftUnitName;
	}
	
	public String getDraftDeptName() {
		
		return draftDeptName;
	}
	
	public void setDraftDeptName(String draftDeptName) {
		
		this.draftDeptName = draftDeptName;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getSex() {
		
		return sex;
	}
	
	public void setSex(String sex) {
		
		this.sex = sex;
	}
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public String getEmployComment() {
		
		return employComment;
	}
	
	public void setEmployComment(String employComment) {
		
		this.employComment = employComment;
	}
	
	public String getEmployResult() {
		
		return employResult;
	}
	
	public void setEmployResult(String employResult) {
		
		this.employResult = employResult;
	}
	
	public String getState() {
		
		return state;
	}
	
	public void setState(String state) {
		
		this.state = state;
	}
	
	public Float getAptitudeTestScore() {
		
		return aptitudeTestScore;
	}
	
	public void setAptitudeTestScore(Float aptitudeTestScore) {
		
		this.aptitudeTestScore = aptitudeTestScore;
	}
	
	public Float getPublicSubjectTestScore() {
		
		return publicSubjectTestScore;
	}
	
	public void setPublicSubjectTestScore(Float publicSubjectTestScore) {
		
		this.publicSubjectTestScore = publicSubjectTestScore;
	}
	
	public Float getProfessionalSubjectScore() {
		
		return professionalSubjectScore;
	}
	
	public void setProfessionalSubjectScore(Float professionalSubjectScore) {
		
		this.professionalSubjectScore = professionalSubjectScore;
	}
	
	public Float getInterviewScore() {
		
		return interviewScore;
	}
	
	public void setInterviewScore(Float interviewScore) {
		
		this.interviewScore = interviewScore;
	}
	
	public Float getOtherSubjectScore() {
		
		return otherSubjectScore;
	}
	
	public void setOtherSubjectScore(Float otherSubjectScore) {
		
		this.otherSubjectScore = otherSubjectScore;
	}
	
	public String getPoliticalExam() {
		
		return politicalExam;
	}
	
	public void setPoliticalExam(String politicalExam) {
		
		this.politicalExam = politicalExam;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getEmploySituation() {
		
		return employSituation;
	}
	
	public void setEmploySituation(String employSituation) {
		
		this.employSituation = employSituation;
	}
	
}
