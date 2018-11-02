/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OrgFormationMgrFlowService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service 
 * 描述: 单位编制信息维护流程表单 服务接口
 * 创建人: jiang  
 * 创建时间: 2018年9月19日14:26:33
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年9月19日14:26:35
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.OrgFormationMgrFlow;
import com.wondersgroup.human.vo.ofcflow.OrgFormationMgrFlowVO;

/** 
 * @ClassName: OrgFormationMgrFlowService 
 * @Description: 单位编制信息维护流程表单 服务接口
 * @author: jiang
 * @date: 2018年9月19日14:26:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface OrgFormationMgrFlowService extends GenericService<OrgFormationMgrFlow>{
	

	public Page<OrgFormationMgrFlowVO> getPage(DetachedCriteria detachedCriteria, Integer page, Integer limit);

	/**
	 * 启动单位编制调整申请流程
	 * @Title: startOrgFormationAdjustFlow 
	 * @Description: TODO
	 * @param temp
	 * @return: void
	 */
	public void startOrgFormationAdjustFlow(OrgFormationMgrFlow flow);

	
	/**
	 * 
	 * @Title: commitAuditAdjustFlow 
	 * @Description: 审批编制调整申请表单
	 * @param flow
	 * @param opinion
	 * @param result
	 * @return: void
	 */
	public void commitAuditAdjustFlow(OrgFormationMgrFlow flow, String opinion, String result);
	
}
