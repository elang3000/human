/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: InstitutionOrgFormationMgrFlowVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 重大事项申请单 VO
 * 创建人: jiang
 * 创建时间: 2018年12月14日16:32:42
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月14日16:32:46
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofcflow.ImportantEventApply;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoBatch;

/**
 * @ClassName: ImportantEventApplyVO
 * @Description: 重大事项申请单 VO
 * @author: jiang
 * @date: 2018年12月14日16:32:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ImportantEventApplyVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * @fieldName: 事项标题
	 */
	private String eventTitle;
	
	/**
	 * * 事项类型
	 **/
	private String eventType;
	
	/**
	 * * 申请单位
	 **/
	private String applyOrganName;
	
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
	
	public ImportantEventApplyVO() {
		
	}
	
	public ImportantEventApplyVO(ImportantEventApply s) {
		this.id = s.getId();
		this.eventTitle = s.getEventTitle();
		this.status = String.valueOf(s.getStatus());
		if (s.getApplyOrgan() != null) {
			this.applyOrganName = s.getApplyOrgan().getName();
		}
		if (s.getEventType() != null) {
			this.eventType = s.getEventType().getName();
		}
		this.statusName = convertState(s.getStatus());
		this.createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(s.getCreateTime());
		
		// 如果已经提交流程，并且是起始节点，设置流程状态为99，不能编辑和删除
		if (s.getFlowRecord() != null && s.getStatus() == ImportantEventApply.STATUS_IMPORTANT_EVENT_STATE) {
			this.status = "99";
		} else {
			this.status = String.valueOf(s.getStatus());
		}
	}
	
	public String convertState(int state) {
		
		if (state == 0) {
			return "待提交申请";
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
			return "审核通过，指定办理人员";
		} else if (state == 7) {
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
	
	public String getEventTitle() {
		
		return eventTitle;
	}
	
	public void setEventTitle(String eventTitle) {
		
		this.eventTitle = eventTitle;
	}
	
	public String getEventType() {
		
		return eventType;
	}
	
	public void setEventType(String eventType) {
		
		this.eventType = eventType;
	}
	
	public String getApplyOrganName() {
		
		return applyOrganName;
	}
	
	public void setApplyOrganName(String applyOrganName) {
		
		this.applyOrganName = applyOrganName;
	}
	
	public String getCreateTime() {
		
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		
		this.createTime = createTime;
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
	
}
