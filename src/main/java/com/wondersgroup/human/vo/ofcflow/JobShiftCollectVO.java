package com.wondersgroup.human.vo.ofcflow;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.core.util.DateUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

public class JobShiftCollectVO {

	    private String id;
		// 职务变动计划表名称
		private String collectName;
		
		// 职务变动计划类别 使用FlowBusTypeConstant 静态量
		private String collectType;
		

		// 批次人数
		/**
		 * @fieldName: sumNumber
		 * @fieldType: java.lang.Integer
		 * @Description: 转任总人数。
		 */
		private Integer sumNumber;
		
		/**
		 * @fieldName: plusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description:正科级（领导）转任人数。
		 */
		private Integer plusKeLeaderNum;
		
		/**
		 * @fieldName: plusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description:正科级（非领导）转任人数。
		 */
		private Integer plusKeNoLeaderNum;
		
		/**
		 * @fieldName: minusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description:副科级（领导）转任人数。
		 */
		private Integer minusKeLeaderNum;
		
		/**
		 * @fieldName: minusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description:副科级（非领导）转任人数。
		 */
		private Integer minusKeNoLeaderNum;
		
		/**
		 * @fieldName: clerkNumber
		 * @fieldType: java.lang.Integer
		 * @Description:科员转任人数。
		 */
		private Integer clerkNumber;
		
		/**
		 * @fieldName: cClerkNumber
		 * @fieldType: java.lang.Integer
		 * @Description:办事员转任人数。
		 */
		private Integer cClerkNumber;
		
		/**
		 * @fieldName: filePath
		 * @fieldType: java.lang.String
		 * @Description: 附件FTP路径。
		 */
		private String filePath;
		
		
		private Integer status;
		
		
		private Integer unitPlanningTotal;
		
		/**
		 * @fieldName: 实有人数
		 * @fieldType: java.lang.Integer
		 * @Description: 本机构内占编制的实有人数。
		 */
		private Integer actualNumber;
		
		/**
		 * @fieldName: 空编人数
		 * @fieldType: java.lang.Integer
		 * @Description: 本机构内空编或超编人数。
		 */
		private Integer vacancyExcessNumber;
		
		/**
		 * @fieldName: notIntoNum
		 * @fieldType: java.lang.Integer
		 * @Description: 供给关系尚未调入人员
		 */
		private Integer notIntoNum;
		
		/**
		 * @fieldName: notOutNum
		 * @fieldType: java.lang.Integer
		 * @Description: 供给关系尚未调出人员
		 */
		private Integer notOutNum;
		
		/**
		 * @fieldName: approveChuNum
		 * @fieldType: java.lang.Integer
		 * @Description: 定编 处级人数
		 */
		private Integer approveChuNum;
		
		/**
		 * @fieldName: realChuNum
		 * @fieldType: java.lang.Integer
		 * @Description: 实有 处级人数
		 */
		private Integer realChuNum;
		
		/**
		 * @fieldName: approvePlusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 定编 正科级领导人数
		 */
		private Integer approvePlusKeLeaderNum;
		
		/**
		 * @fieldName: realPlusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 实有 正科级领导人数
		 */
		private Integer realPlusKeLeaderNum;
		
		/**
		 * @fieldName: notIntoPlusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 未调入 正科级领导人数
		 */
		private Integer notIntoPlusKeLeaderNum;
		
		/**
		 * @fieldName: notOutPlusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 未调出 正科级领导人数
		 */
		private Integer notOutPlusKeLeaderNum;
		
		/**
		 * @fieldName: approvePlusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 定编 正科级非领导人数
		 */
		private Integer approvePlusKeNoLeaderNum;
		
		/**
		 * @fieldName: realPlusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 实有 正科级非领导人数
		 */
		private Integer realPlusKeNoLeaderNum;
		
		/**
		 * @fieldName: notIntoPlusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 未调入 正科级非领导人数
		 */
		private Integer notIntoPlusKeNoLeaderNum;
		
		/**
		 * @fieldName: notOutPlusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 未调出 正科级非领导人数
		 */
		private Integer notOutPlusKeNoLeaderNum;
		
		/**
		 * @fieldName: approveMinusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 定编 副科级领导人数
		 */
		private Integer approveMinusKeLeaderNum;
		
		/**
		 * @fieldName: realMinusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 实有 副科级领导人数
		 */
		private Integer realMinusKeLeaderNum;
		
		/**
		 * @fieldName: notIntoMinusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 未调入 副科级领导人数
		 */
		private Integer notIntoMinusKeLeaderNum;
		
		/**
		 * @fieldName: notOutMinusKeLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 未调出 副科级领导人数
		 */
		private Integer notOutMinusKeLeaderNum;
		
		/**
		 * @fieldName: approveMinusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 定编 副科级非领导人数
		 */
		private Integer approveMinusKeNoLeaderNum;
		
		/**
		 * @fieldName: realMinusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 定编 副科级非领导人数
		 */
		private Integer realMinusKeNoLeaderNum;
		
		/**
		 * @fieldName: notIntoMinusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 未调入 副科级非领导人数
		 */
		private Integer notIntoMinusKeNoLeaderNum;
		
		/**
		 * @fieldName: notOutMinusKeNoLeaderNum
		 * @fieldType: java.lang.Integer
		 * @Description: 未调出 副科级非领导人数
		 */
		private Integer notOutMinusKeNoLeaderNum;
		
		
		private String creater;
		
		private Date createTime;
		
		private String remark;

		
		public String getCollectName() {

			return collectName;
		}

		
		public void setCollectName(String collectName) {
			
			this.collectName = collectName;
		}

		
		public String getCollectType() {
			
			return collectType;
		}

		
		public void setCollectType(String collectType) {
			
			this.collectType = collectType;
		}

		

		
		public Integer getSumNumber() {
			
			return sumNumber;
		}

		
		public void setSumNumber(Integer sumNumber) {
			
			this.sumNumber = sumNumber;
		}

		
		public Integer getPlusKeLeaderNum() {
			
			return plusKeLeaderNum;
		}

		
		public void setPlusKeLeaderNum(Integer plusKeLeaderNum) {
			
			this.plusKeLeaderNum = plusKeLeaderNum;
		}

		
		public Integer getPlusKeNoLeaderNum() {
			
			return plusKeNoLeaderNum;
		}

		
		public void setPlusKeNoLeaderNum(Integer plusKeNoLeaderNum) {
			
			this.plusKeNoLeaderNum = plusKeNoLeaderNum;
		}

		
		public Integer getMinusKeLeaderNum() {
			
			return minusKeLeaderNum;
		}

		
		public void setMinusKeLeaderNum(Integer minusKeLeaderNum) {
			
			this.minusKeLeaderNum = minusKeLeaderNum;
		}

		
		public Integer getMinusKeNoLeaderNum() {
			
			return minusKeNoLeaderNum;
		}

		
		public void setMinusKeNoLeaderNum(Integer minusKeNoLeaderNum) {
			
			this.minusKeNoLeaderNum = minusKeNoLeaderNum;
		}

		
		public Integer getClerkNumber() {
			
			return clerkNumber;
		}

		
		public void setClerkNumber(Integer clerkNumber) {
			
			this.clerkNumber = clerkNumber;
		}

		
		public Integer getcClerkNumber() {
			
			return cClerkNumber;
		}

		
		public void setcClerkNumber(Integer cClerkNumber) {
			
			this.cClerkNumber = cClerkNumber;
		}

		
		public String getFilePath() {
			
			return filePath;
		}

		
		public void setFilePath(String filePath) {
			
			this.filePath = filePath;
		}

		
		public Integer getStatus() {
			
			return status;
		}

		
		public void setStatus(Integer status) {
			
			this.status = status;
		}

		
		public Integer getUnitPlanningTotal() {
			
			return unitPlanningTotal;
		}

		
		public void setUnitPlanningTotal(Integer unitPlanningTotal) {
			
			this.unitPlanningTotal = unitPlanningTotal;
		}

		
		public Integer getActualNumber() {
			
			return actualNumber;
		}

		
		public void setActualNumber(Integer actualNumber) {
			
			this.actualNumber = actualNumber;
		}

		
		public Integer getVacancyExcessNumber() {
			
			return vacancyExcessNumber;
		}

		
		public void setVacancyExcessNumber(Integer vacancyExcessNumber) {
			
			this.vacancyExcessNumber = vacancyExcessNumber;
		}

		
		public Integer getNotIntoNum() {
			
			return notIntoNum;
		}

		
		public void setNotIntoNum(Integer notIntoNum) {
			
			this.notIntoNum = notIntoNum;
		}

		
		public Integer getNotOutNum() {
			
			return notOutNum;
		}

		
		public void setNotOutNum(Integer notOutNum) {
			
			this.notOutNum = notOutNum;
		}

		
		public Integer getApproveChuNum() {
			
			return approveChuNum;
		}

		
		public void setApproveChuNum(Integer approveChuNum) {
			
			this.approveChuNum = approveChuNum;
		}

		
		public Integer getRealChuNum() {
			
			return realChuNum;
		}

		
		public void setRealChuNum(Integer realChuNum) {
			
			this.realChuNum = realChuNum;
		}

		
		public Integer getApprovePlusKeLeaderNum() {
			
			return approvePlusKeLeaderNum;
		}

		
		public void setApprovePlusKeLeaderNum(Integer approvePlusKeLeaderNum) {
			
			this.approvePlusKeLeaderNum = approvePlusKeLeaderNum;
		}

		
		public Integer getRealPlusKeLeaderNum() {
			
			return realPlusKeLeaderNum;
		}

		
		public void setRealPlusKeLeaderNum(Integer realPlusKeLeaderNum) {
			
			this.realPlusKeLeaderNum = realPlusKeLeaderNum;
		}

		
		public Integer getNotIntoPlusKeLeaderNum() {
			
			return notIntoPlusKeLeaderNum;
		}

		
		public void setNotIntoPlusKeLeaderNum(Integer notIntoPlusKeLeaderNum) {
			
			this.notIntoPlusKeLeaderNum = notIntoPlusKeLeaderNum;
		}

		
		public Integer getNotOutPlusKeLeaderNum() {
			
			return notOutPlusKeLeaderNum;
		}

		
		public void setNotOutPlusKeLeaderNum(Integer notOutPlusKeLeaderNum) {
			
			this.notOutPlusKeLeaderNum = notOutPlusKeLeaderNum;
		}

		
		public Integer getApprovePlusKeNoLeaderNum() {
			
			return approvePlusKeNoLeaderNum;
		}

		
		public void setApprovePlusKeNoLeaderNum(Integer approvePlusKeNoLeaderNum) {
			
			this.approvePlusKeNoLeaderNum = approvePlusKeNoLeaderNum;
		}

		
		public Integer getRealPlusKeNoLeaderNum() {
			
			return realPlusKeNoLeaderNum;
		}

		
		public void setRealPlusKeNoLeaderNum(Integer realPlusKeNoLeaderNum) {
			
			this.realPlusKeNoLeaderNum = realPlusKeNoLeaderNum;
		}

		
		public Integer getNotIntoPlusKeNoLeaderNum() {
			
			return notIntoPlusKeNoLeaderNum;
		}

		
		public void setNotIntoPlusKeNoLeaderNum(Integer notIntoPlusKeNoLeaderNum) {
			
			this.notIntoPlusKeNoLeaderNum = notIntoPlusKeNoLeaderNum;
		}

		
		public Integer getNotOutPlusKeNoLeaderNum() {
			
			return notOutPlusKeNoLeaderNum;
		}

		
		public void setNotOutPlusKeNoLeaderNum(Integer notOutPlusKeNoLeaderNum) {
			
			this.notOutPlusKeNoLeaderNum = notOutPlusKeNoLeaderNum;
		}

		
		public Integer getApproveMinusKeLeaderNum() {
			
			return approveMinusKeLeaderNum;
		}

		
		public void setApproveMinusKeLeaderNum(Integer approveMinusKeLeaderNum) {
			
			this.approveMinusKeLeaderNum = approveMinusKeLeaderNum;
		}

		
		public Integer getRealMinusKeLeaderNum() {
			
			return realMinusKeLeaderNum;
		}

		
		public void setRealMinusKeLeaderNum(Integer realMinusKeLeaderNum) {
			
			this.realMinusKeLeaderNum = realMinusKeLeaderNum;
		}

		
		public Integer getNotIntoMinusKeLeaderNum() {
			
			return notIntoMinusKeLeaderNum;
		}

		
		public void setNotIntoMinusKeLeaderNum(Integer notIntoMinusKeLeaderNum) {
			
			this.notIntoMinusKeLeaderNum = notIntoMinusKeLeaderNum;
		}

		
		public Integer getNotOutMinusKeLeaderNum() {
			
			return notOutMinusKeLeaderNum;
		}

		
		public void setNotOutMinusKeLeaderNum(Integer notOutMinusKeLeaderNum) {
			
			this.notOutMinusKeLeaderNum = notOutMinusKeLeaderNum;
		}

		
		public Integer getApproveMinusKeNoLeaderNum() {
			
			return approveMinusKeNoLeaderNum;
		}

		
		public void setApproveMinusKeNoLeaderNum(Integer approveMinusKeNoLeaderNum) {
			
			this.approveMinusKeNoLeaderNum = approveMinusKeNoLeaderNum;
		}

		
		public Integer getRealMinusKeNoLeaderNum() {
			
			return realMinusKeNoLeaderNum;
		}

		
		public void setRealMinusKeNoLeaderNum(Integer realMinusKeNoLeaderNum) {
			
			this.realMinusKeNoLeaderNum = realMinusKeNoLeaderNum;
		}

		
		public Integer getNotIntoMinusKeNoLeaderNum() {
			
			return notIntoMinusKeNoLeaderNum;
		}

		
		public void setNotIntoMinusKeNoLeaderNum(Integer notIntoMinusKeNoLeaderNum) {
			
			this.notIntoMinusKeNoLeaderNum = notIntoMinusKeNoLeaderNum;
		}

		
		public Integer getNotOutMinusKeNoLeaderNum() {
			
			return notOutMinusKeNoLeaderNum;
		}

		
		public void setNotOutMinusKeNoLeaderNum(Integer notOutMinusKeNoLeaderNum) {
			
			this.notOutMinusKeNoLeaderNum = notOutMinusKeNoLeaderNum;
		}
		
		public String getJobChangeTypeStr(){
			if(StringUtils.isNotBlank(this.collectType)){
				if(this.collectType.equals(FlowBusTypeConstant.FLOW_JOBSHIFT_PROMOTEB)){
					return "晋升";
				}else if(this.collectType.equals(FlowBusTypeConstant.FLOW_JOBSHIFT_DEMOTE)){
					return "降职";
				}else if(this.collectType.equals(FlowBusTypeConstant.FLOW_JOBSHIFT_DEPOSE)){
					return "免职";
				}else if(this.collectType.equals(FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT)){
					return "轮岗";
				}
			}
				return "";
		}


		public String getCreater() {
			
			return creater;
		}


		public void setCreater(String creater) {
			
			this.creater = creater;
		}


		public Date getCreateTime() {
			
			return createTime;
		}
		
		public String getCreateTimeStr() {
		
			return 	DateUtils.format(createTime, "yyyy-MM-dd HH:mm");
		}


		public void setCreateTime(Date createTime) {
			
			this.createTime = createTime;
		}


		public String getRemark() {
			
			return remark;
		}


		public void setRemark(String remark) {
			
			this.remark = remark;
		}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
