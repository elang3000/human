/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProbationServantVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 试用期管理 VO
 * 创建人: tzy
 * 创建时间: 2018年7月25日 下午3:37:17
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月25日 下午3:37:17
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;

/**
 * @ClassName: ProbationServantVO
 * @Description: 试用期管理 VO
 * @author: tzy
 * @date: 2018年7月25日 下午3:37:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ProbationServantVO {
	
	/**
	 * ID
	 */
	private String id;
	
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
	 * 录用单位
	 */
	private String draftDeptName;
	
	/**
	 * @Description: 试用期开始时间
	 */
	private String probationStartDate;
	
	/**
	 * @Description: 试用期结束时间
	 */
	private String probationEndDate;
	
	/**
	 * 考核状态
	 */
	private String probationStatus;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 状态
	 */
	private String statusFlag;
	
	public ProbationServantVO() {
		
	}
	
	public ProbationServantVO(ProbationServant s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = s.getId();
		if (s.getDraftServant() != null) {
			this.name = s.getDraftServant().getName();
			if (s.getDraftServant().getSex() != null) {
				this.sex = s.getDraftServant().getSex().getName();
			}
			this.cardNo = s.getDraftServant().getCardNoView();
			this.draftDeptName = s.getDraftServant().getDraftUnit().getOrgan().getName();
		}
		if (s.getProbationStartDate() != null) {
			this.probationStartDate = sdf.format(s.getProbationStartDate());
		}
		if (s.getProbationEndDate() != null) {
			this.probationEndDate = sdf.format(s.getProbationEndDate());
		}
		if (s.getProbationStatus() != null) {
			this.probationStatus = s.getProbationStatus().getName();
		}
		this.status = convertState(s.getStatus());
		this.statusFlag = String.valueOf(s.getStatus());
	}
	
	public String convertState(int state) {
		
		if (state == 0) {
			return "待提交试用期信息";
		} else if (state == 1) {
			return "待上级单位审核";
		} else if (state == 2) {
			return "待区人事主管部门一级审核";
		} else if (state == 3) {
			return "待区人事主管部门二级审核";
		} else if (state == 4) {
			return "待区人事主管部门三级审核";
		} else if (state == 5) {
			return "待区人事主管部门四级审核";
		} else if (state == 6) {
			return "区人事主管部门审核通过";
		} else if (state == 7) {
			return "取消录用，待区人事主管部门备案确认";
		} else if (state == 8) {
			return "取消录用，区人事主管部门已备案确认";
		} else if (state == FlowRecord.BUS_STOP) {
			return "业务被中止";
		} else {
			return "";
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
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
	
	public String getDraftDeptName() {
		
		return draftDeptName;
	}
	
	public void setDraftDeptName(String draftDeptName) {
		
		this.draftDeptName = draftDeptName;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public String getProbationStatus() {
		
		return probationStatus;
	}
	
	public void setProbationStatus(String probationStatus) {
		
		this.probationStatus = probationStatus;
	}
	
	public String getProbationStartDate() {
		
		return probationStartDate;
	}
	
	public void setProbationStartDate(String probationStartDate) {
		
		this.probationStartDate = probationStartDate;
	}
	
	public String getProbationEndDate() {
		
		return probationEndDate;
	}
	
	public void setProbationEndDate(String probationEndDate) {
		
		this.probationEndDate = probationEndDate;
	}
	
	public String getStatusFlag() {
		
		return statusFlag;
	}
	
	public void setStatusFlag(String statusFlag) {
		
		this.statusFlag = statusFlag;
	}
	
}
