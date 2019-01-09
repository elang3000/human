/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: InstitutionOrgFormationMgrFlowVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 事业单位信息维护流程表单 VO
 * 创建人: jiang
 * 创建时间: 2018年12月5日14:56:04
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月5日14:56:07
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofcflow.ImportantEventApply;
import com.wondersgroup.human.bo.ofcflow.InstitutionOrgFormationMgrFlow;

/**
 * @ClassName: InstitutionOrgFormationMgrFlowVO
 * @Description: 事业单位信息维护流程表单 VO
 * @author: jiang
 * @date: 2018年12月5日14:56:10
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class InstitutionOrgFormationMgrFlowVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * @fieldName: 单位名称
	 */
	private String unitBasicName;
	
	/**
	 * * 操作类型
	 **/
	private String optionType;
	
	/**
	 * * 操作类型Code码
	 **/
	private String optionTypeCode;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 状态名称
	 */
	private String statusName;
	
	public InstitutionOrgFormationMgrFlowVO() {
		
	}
	
	public InstitutionOrgFormationMgrFlowVO(InstitutionOrgFormationMgrFlow s) {
		this.id = s.getId();
		this.unitBasicName = s.getOrgInfo().getUnitBasicName();
		this.status = String.valueOf(s.getStatus());
		if (s.getOptionType() != null) {
			this.optionType = s.getOptionType().getName();
			this.optionTypeCode = s.getOptionType().getCode();
		}
		this.statusName = convertState(s.getStatus());
		this.createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(s.getCreateTime());
		// 如果已经提交流程，并且是起始节点，设置流程状态为99，不能编辑和删除
		if (s.getFlowRecord() != null && s.getStatus() == InstitutionOrgFormationMgrFlow.STATUS_ORG_FORMATION_MGR_FLOW_STATE) {
			this.status = "99";
		} else {
			this.status = String.valueOf(s.getStatus());
		}
	}
	
	public String convertState(int state) {
		
		if (state == 0) {
			return "待提交申请";
		} else if (state == 1) {
			return "待上级审核";
		} else if (state == 2) {
			return "审核通过";
		} else if (state == -1) {
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
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public String getStatusName() {
		
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		
		this.statusName = statusName;
	}
	
	public String getCreateTime() {
		
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		
		this.createTime = createTime;
	}
	
	public String getOptionTypeCode() {
		
		return optionTypeCode;
	}
	
	public void setOptionTypeCode(String optionTypeCode) {
		
		this.optionTypeCode = optionTypeCode;
	}
	
}
