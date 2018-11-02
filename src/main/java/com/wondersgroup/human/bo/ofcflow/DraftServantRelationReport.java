/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DraftServantRelationReport.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 录用 流程和信息关联表
 * 创建人: tzy
 * 创建时间: 2018年7月20日 上午10:21:14
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月20日 上午10:21:14
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: DraftServantRelationReport
 * @Description: 录用 流程和信息关联表
 * @author: tzy
 * @date: 2018年7月20日 上午10:21:14
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_DRAFT_SERVANT_R_REPORT")
public class DraftServantRelationReport extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description:
	 */
	private static final long serialVersionUID = 4248198901722255732L;
	
	/**
	 * @fieldName: report
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DraftServantReport
	 * @Description: 流程表ID
	 */
	@ManyToOne
	@JoinColumn(name = "REPORT_ID")
	private DraftServantReport report;
	
	/**
	 * @fieldName: draftServant
	 * @fieldType: com.wondersgroup.human.bo.ofcflow.DraftServant
	 * @Description: 信息表ID
	 */
	@ManyToOne
	@JoinColumn(name = "DRAFT_SERVANT_ID")
	private DraftServant draftServant;
	
	public DraftServantReport getReport() {
		
		return report;
	}
	
	public void setReport(DraftServantReport report) {
		
		this.report = report;
	}
	
	public DraftServant getDraftServant() {
		
		return draftServant;
	}
	
	public void setDraftServant(DraftServant draftServant) {
		
		this.draftServant = draftServant;
	}
	
}
