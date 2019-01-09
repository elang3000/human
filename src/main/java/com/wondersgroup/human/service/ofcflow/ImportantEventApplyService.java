/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ImportantEventApplyService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service 
 * 描述: 事业单位编制信息维护流程表单 服务接口
 * 创建人: jiang  
 * 创建时间: 2018年12月14日16:13:09
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年12月14日16:13:12
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ImportantEventApply;
import com.wondersgroup.human.bo.ofcflow.InstitutionOrgFormationMgrFlow;
import com.wondersgroup.human.vo.ofcflow.ImportantEventApplyVO;

/** 
 * @ClassName: ImportantEventApplyService 
 * @Description: 重大事项申请 服务接口
 * @author: jiang
 * @date: 2018年12月14日16:12:46
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ImportantEventApplyService extends GenericService<ImportantEventApply>{
	

	public Page<ImportantEventApplyVO> getPage(DetachedCriteria detachedCriteria, Integer page, Integer limit);
	
	/**
	 * 启动申请流程
	 * @Title: startOrgFormationAdjustFlow 
	 * @Description: TODO
	 * @param temp
	 * @return: void
	 */
	public void startApplyFlow(ImportantEventApply flow);

	
	/**
	 * 
	 * @Title: commitAuditAdjustFlow 
	 * @Description: 审批申请表单
	 * @param flow
	 * @param opinion
	 * @param result
	 * @return: void
	 */
	public void commitApplyFlow(ImportantEventApply flow, String opinion, String result);

}
