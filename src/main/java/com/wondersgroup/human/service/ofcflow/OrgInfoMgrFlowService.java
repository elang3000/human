/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OrgInfoMgrFlowService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service 
 * 描述: 单位信息维护流程表单 服务接口
 * 创建人: jiang  
 * 创建时间: 2018年9月13日10:53:20
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年9月13日10:53:22
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.OrgInfoMgrFlow;
import com.wondersgroup.human.vo.ofcflow.OrgInfoMgrFlowVO;

/** 
 * @ClassName: OrgInfoMgrFlowService 
 * @Description: 单位信息维护流程表单 服务接口
 * @author: jiang
 * @date: 2018年9月13日10:53:01
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface OrgInfoMgrFlowService extends GenericService<OrgInfoMgrFlow>{
	

	public Page<OrgInfoMgrFlowVO> getPage(DetachedCriteria detachedCriteria, Integer page, Integer limit);

	/**
	 * 启动单位新增申请流程
	 * @Title: startOrgApplyFlow 
	 * @Description: TODO
	 * @param temp
	 * @return: void
	 */
	public void startOrgApplyFlow(OrgInfoMgrFlow temp);

	/**
	 * 
	 * @Title: commitAuditApplyFlow 
	 * @Description: TODO
	 * @param flow 审批新增申请表单
	 * @param opinion 审批意见
	 * @param result 审批结果
	 * @return: void
	 */
	public void commitAuditApplyFlow(OrgInfoMgrFlow flow, String opinion, String result);

	public void startOrgAdjustFlow(OrgInfoMgrFlow temp);
	
	public void commitAuditAdjustFlow(OrgInfoMgrFlow flow, String opinion, String result);
	
}
