/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainServantVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月13日 上午11:17:24 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月13日 上午11:17:24 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofcflow.TrainServant;

/**
 * @ClassName: TrainServantVO
 * @Description: 培训vo
 * @author: lihao
 * @date: 2018年11月13日
 *        上午11:17:24 @version [版本号, YYYY-MM-DD] @see       [相关类/方法] @since     [产品/模块版本]
 */
public class TrainServantVO {

	private String id;
	private String trainName;
	private String studyType;
	private String hostOrgan;
	private String trainOrganName;
	private Integer days;
	private Integer hours;
	private String startDate;
	private String endDate;
	private String status;
	private String trainClassName;
	private Integer statusSign;

	public TrainServantVO(TrainServant t) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		this.id = t.getId();
		this.trainName = t.getTrainName();
		this.trainClassName = t.getTrainClassName();
		if (t.getStudyType() != null) {
			this.studyType = t.getStudyType().getName();
		}
		if (t.getHostOrgan() != null) {
			this.hostOrgan = t.getHostOrgan().getName();
		}
		this.trainOrganName = t.getTrainOrganName();
		this.days = t.getDays();
		this.hours = t.getHours();
		this.trainName = t.getTrainName();
		if (t.getStartDate() != null) {
			this.startDate = sdf.format(t.getStartDate());
		}
		if (t.getEndDate() != null) {
			this.endDate = sdf.format(t.getEndDate());
		}
		// 如果已经提交流程，并且是起始节点，设置流程状态为99，不能编辑和删除
		if (t.getFlowRecord() != null && t.getStatus() == TrainServant.STATUS_TRAIN_PLAN_STEP1) {
			this.statusSign = 99;
		} else {
			this.statusSign = t.getStatus();
		}
		this.status = convertState(t.getStatus());

	}

	public String convertState(int state) {
		if(state == FlowRecord.BUS_STOP){
			return "业务被中止";
		}else if (state == 1) {
			return "待提交培训考核";
		} else if (state == 2) {
			return "待上级单位初审";
		} else if (state == 3) {
			return "待区人事主管部门一级初审";
		} else if (state == 4) {
			return "待区人事主管部门二级初审";
		} else if (state == 5) {
			return "待区人事主管部门三级初审";
		} else if (state == 6) {
			return "待区人事主管部门四级初审";
		} else if (state == 7) {
			return "待组织部复审";
		} else if (state == 8) {
			return "待区人事管理部门确认";
		} else {
			return "已完成";
		}
	}

	/**
	 * @return the trainName
	 */
	public String getTrainName() {
		return trainName;
	}

	/**
	 * @param trainName
	 *            the trainName to set
	 */
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	/**
	 * @return the studyType
	 */
	public String getStudyType() {
		return studyType;
	}

	/**
	 * @param studyType
	 *            the studyType to set
	 */
	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	/**
	 * @return the hostOrgan
	 */
	public String getHostOrgan() {
		return hostOrgan;
	}

	/**
	 * @param hostOrgan
	 *            the hostOrgan to set
	 */
	public void setHostOrgan(String hostOrgan) {
		this.hostOrgan = hostOrgan;
	}

	/**
	 * @return the trainOrganName
	 */
	public String getTrainOrganName() {
		return trainOrganName;
	}

	/**
	 * @param trainOrganName
	 *            the trainOrganName to set
	 */
	public void setTrainOrganName(String trainOrganName) {
		this.trainOrganName = trainOrganName;
	}

	/**
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(Integer days) {
		this.days = days;
	}

	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the statusSign
	 */
	public Integer getStatusSign() {
		return statusSign;
	}

	/**
	 * @param statusSign
	 *            the statusSign to set
	 */
	public void setStatusSign(Integer statusSign) {
		this.statusSign = statusSign;
	}

	/**
	 * @return the trainClassName
	 */
	public String getTrainClassName() {
		return trainClassName;
	}

	/**
	 * @param trainClassName
	 *            the trainClassName to set
	 */
	public void setTrainClassName(String trainClassName) {
		this.trainClassName = trainClassName;
	}
}
