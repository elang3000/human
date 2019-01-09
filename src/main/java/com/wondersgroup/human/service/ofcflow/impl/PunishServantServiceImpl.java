/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PunishServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月25日 上午11:32:13 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月25日 上午11:32:13 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.ofcflow.PunishServant;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.dto.ofcflow.PunishServantQueryParam;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerManageRecordEvent;
import com.wondersgroup.human.event.record.ServantHumamKeepRecordEvent;
import com.wondersgroup.human.service.ofc.RewardAndPunishService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.PunishServantService;
import com.wondersgroup.human.vo.ofcflow.PunishServantVO;

/** 
 * @ClassName: PunishServantServiceImpl 
 * @Description: 处分服务接口实现
 * @author: lihao
 * @date: 2018年9月25日 上午11:32:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class PunishServantServiceImpl extends GenericServiceImpl<PunishServant> implements PunishServantService{

	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	ServantService servantService;
	@Autowired
	private RewardAndPunishService rewardAndPunishService;
	
	/** 
	 * @see com.wondersgroup.human.service.ofcflow.PunishServantService#pageList(com.wondersgroup.human.vo.ofc.PunishVO, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<PunishServantVO> pageList(PunishServantQueryParam param, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(PunishServant.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			s.add(Restrictions.like("s.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			s.add(Restrictions.eq("s.cardNo",param.getCardNo()));
		}
		if (StringUtils.isNotBlank(param.getPunishFileName())) {// 处分文件号
			detachedcriteria.add(Restrictions.like("punishFileName", param.getPunishFileName(), MatchMode.ANYWHERE));
		}
		if (param.getPunishReason() != null && StringUtils.isNotBlank(param.getPunishReason().getId())) {// 处分原因
			DetachedCriteria pr = detachedcriteria.createAlias("punishReason", "pr");
			pr.add(Restrictions.eq("pr.id", param.getPunishReason().getId()));
		}
		
		detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<PunishServant> punisPage = this.findByCriteria(detachedcriteria, page, limit);
		List<PunishServantVO> voList = new ArrayList<>();
		for (PunishServant ps : punisPage.getResult()) {
			PunishServantVO vo = new PunishServantVO(ps);
			voList.add(vo);
		}
		Page<PunishServantVO> result = new Page<>(punisPage.getStart(), punisPage.getCurrentPageSize(),
				punisPage.getTotalSize(), punisPage.getPageSize(), voList);
		return result;
	}

	/**  
	 * @see com.wondersgroup.human.service.ofcflow.PunishServantService#savePunish(com.wondersgroup.human.bo.ofcflow.PunishServant, java.lang.String, java.lang.String) 
	 */
	@Override
	public void savePunish(PunishServant temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(PunishServant.PUNISH_SERVANT_STEP1==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("处分备案");//流程业务名称
			flow.setBusType("PunishServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV); //分类公务员单位
			flow = workflowService.createFlowRecord(flow, "PUNISH_SERVANT_STEP1");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(PunishServant.PUNISH_SERVANT_STEP2 == temp.getStatus()&&FlowRecord.PASS.equals(r)){//死亡最后环节
			temp.setStatus(PunishServant.PUNISH_SERVANT_PASS);//通过
			Calendar cal = Calendar.getInstance();
			cal.setTime(temp.getPunishApprovalDate());//设置起时间
			cal.add(Calendar.MONTH, temp.getPunishYear());//增加
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(temp.getPunishApprovalDate());//设置起时间
			cal1.add(Calendar.MONTH, temp.getPunishYear());//增加
			cal1.add(Calendar.MONTH, -1);//增加
			
			temp.setPunishApprovalEndDate(cal.getTime());//处分结束时间
			temp.setWarnDate(cal1.getTime());//处分结束时前一个月预警时间
			temp.setFlowRecord(null);//修改当前业务的流程节点
			
			createServant(temp);
		}else{
			temp.setStatus(PunishServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
	
	
	public void createServant(PunishServant temp){
		
		//添加处分子集
		RewardAndPunish rewardAndPunish = new RewardAndPunish();
		rewardAndPunish.setServant(temp.getServant());
		rewardAndPunish.setType(1);
		rewardAndPunish.setPunishName(temp.getPunishCode().getName());//名称
		rewardAndPunish.setPunishCode(temp.getPunishCode());//名称code
		rewardAndPunish.setPunishNo(temp.getPunishFileName());//处分文件号
		rewardAndPunish.setPunishApprovalDate(temp.getPunishApprovalDate());//处分时间
		rewardAndPunish.setPunishRevokeDate(temp.getPunishApprovalEndDate());//处分解除时间
		rewardAndPunish.setPunishApprovalUnitName(temp.getServant().getDepartName());//处分单位
		rewardAndPunish.setPunishDescription(temp.getContent());//受惩戒说明
		rewardAndPunish.setPunishReason(temp.getPunishReason());//处分原因
		DictUtils.operationCodeInfo(rewardAndPunish);//将CodeInfo中id为空的属性 设置为null
		
		rewardAndPunishService.save(rewardAndPunish);
		
		ManagerRecordDTO dto = new ManagerRecordDTO(temp.getServant().getId(),ManagerRecord.HUMAN_CF);
		ManagerManageRecordEvent event = new ManagerManageRecordEvent(dto);
		EventManager.send(event);
		
		HumankeepRecordDTO dto2 = new HumankeepRecordDTO(temp.getServant().getId(),HumanKeepRecord.KEEP_CF);
		ServantHumamKeepRecordEvent event2 = new ServantHumamKeepRecordEvent(dto2);	
		EventManager.send(event2);
	}

}
