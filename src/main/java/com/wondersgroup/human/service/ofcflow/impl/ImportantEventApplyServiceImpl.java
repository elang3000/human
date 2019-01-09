/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ImportantEventApplyServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 重大事项申请 服务接口实现类
 * 创建人: jiang
 * 创建时间: 2018年12月14日16:25:36
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月14日16:25:38
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ImportantEventApply;
import com.wondersgroup.human.bo.ofcflow.InstitutionOrgFormationMgrFlow;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoBatch;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.repository.ofcflow.ImportantEventApplyRepository;
import com.wondersgroup.human.service.ofcflow.ImportantEventApplyService;
import com.wondersgroup.human.vo.ofcflow.ImportantEventApplyVO;
import com.wondersgroup.human.vo.ofcflow.InstitutionOrgFormationMgrFlowVO;

/**
 * @ClassName: ImportantEventApplyServiceImpl
 * @Description: 重大事项申请 服务接口实现类
 * @author: jiang
 * @date: 2018年12月5日15:03:10
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class ImportantEventApplyServiceImpl extends GenericServiceImpl<ImportantEventApply>
		implements ImportantEventApplyService {
	
	@Autowired
	private ImportantEventApplyRepository importantEventApplyRepository;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public Page<ImportantEventApplyVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit) {
		
		Page<ImportantEventApply> importantEventApplyPage = importantEventApplyRepository
				.findByCriteria(detachedCriteria, pageNo, limit);
		List<ImportantEventApplyVO> voList = new ArrayList<>();
		for (ImportantEventApply s : importantEventApplyPage.getResult()) {
			ImportantEventApplyVO vo = new ImportantEventApplyVO(s);
			voList.add(vo);
		}
		Page<ImportantEventApplyVO> page = new Page<>(importantEventApplyPage.getStart(),
				importantEventApplyPage.getCurrentPageSize(), importantEventApplyPage.getTotalSize(),
				importantEventApplyPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public void startApplyFlow(ImportantEventApply apply) {
		
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(apply.getId())) {
			saveOrUpdate(apply);// 保存业务数据
		}
		
		// 创建初始流程对象
		FlowRecord flow = new FlowRecord();
		flow.setAppNodeId(appNode.getId());// 流程业务所在系统
		flow.setBusId(apply.getId());// 流程业务ID
		flow.setBusName("重大事项申请流程");// 流程业务名称
		flow.setBusType("H001003023");// 流程业务类型
		flow.setTargetOrganNode(userOrg);// 流程业务目标组织
		flow.setTargetSecurityUser(user);// 流程业务目标人员
		flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV);// 流程分类
		flow = workflowService.createFlowRecord(flow, "STATUS_IMPORTANT_EVENT_STATE");// 初始节点
		
		apply.setStatus(ImportantEventApply.power.get(flow.getOperationCode()));// 实际有权限的操作节点
		apply.setFlowRecord(flow);// 修改当前业务的流程节点
		importantEventApplyRepository.merge(apply);
		
	}
	
	@Override
	public void commitApplyFlow(ImportantEventApply apply, String opinion, String result) {
		
		FlowRecord flow = apply.getFlowRecord();
		flow.setOpinion(opinion);
		flow.setResult(result);
		flow = workflowService.completeWorkItem(flow);// 提交下个节点
		if (flow == null) {
			if(FlowRecord.PASS.equals(result)){
				// 流程正常结束，做通过操作
				apply.setStatus(ImportantEventApply.STATUS_IMPORTANT_EVENT_TRIAL_7);
			}else if(FlowRecord.STOP.equals(result)){
				apply.setStatus(FlowRecord.BUS_STOP);
				
				//流程非正常结束，发送通知
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"重大事项申请"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"重大事项申请"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, apply.getCreater(), title, content, "","重大事项申请")));
				
			}
			
		} else {
			// 流程未结束
			apply.setStatus(ImportantEventApply.power.get(flow.getOperationCode()));
			apply.setFlowRecord(flow);
		}
		importantEventApplyRepository.update(apply);
		
	}
	
}
