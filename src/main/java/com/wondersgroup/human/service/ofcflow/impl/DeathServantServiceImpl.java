/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月22日 上午9:53:38 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月22日 上午9:53:38 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
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
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DeathServant;
import com.wondersgroup.human.dto.ofcflow.DeathServantQueryParam;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DeathServantService;
import com.wondersgroup.human.vo.ofcflow.DeathVO;

/**
 * @ClassName: DeathServantServiceImpl
 * @Description: 死亡服务接口实现类
 * @author: lihao
 * @date: 2018年6月22日上午9:53:38 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Service
public class DeathServantServiceImpl extends GenericServiceImpl<DeathServant> implements DeathServantService {

	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	ServantService servantService;
	@Autowired
	private DictableService dictableService;
	
	/** 
	 * @see com.wondersgroup.human.service.ofcflow.DeathServantService#saveDeath(com.wondersgroup.human.bo.ofcflow.DeathServant, java.lang.String, java.lang.String) 
	 */
	@Override
	public void saveDeath(DeathServant temp, String opinion, String r) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(DeathServant.DEATH_EMPLOY_APPLY==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"死亡");//流程业务名称
			flow.setBusType("DeathServant");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_GOV); //分类公务员单位
			flow = workflowService.createFlowRecord(flow, "DEATH_EMPLOY_APPLY");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(DeathServant.DEATH_EMPLOY_CONFIRM == temp.getStatus()&&FlowRecord.PASS.equals(r)){//死亡最后环节
			temp.setStatus(DeathServant.DEATH_EMPLOY_DONE);//通过
			temp.setFlowRecord(null);//修改当前业务的流程节点
			
			Servant s = servantService.get(temp.getServant().getId());
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("4", "DM200");// 死亡CODE
			s.setIsOnHold(isOnHold);
			servantService.update(s);
		}else{
			temp.setStatus(DeathServant.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}

	/**  
	 * @see com.wondersgroup.human.service.ofcflow.DeathServantService#pageList(com.wondersgroup.human.bo.ofcflow.DeathServant, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<DeathVO> pageList(DeathServantQueryParam param, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(DeathServant.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			s.add(Restrictions.like("s.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			s.add(Restrictions.like("s.cardNo",param.getCardNo(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getRemark())) {// 备注
			detachedcriteria.add(Restrictions.like("remark", param.getRemark(), MatchMode.ANYWHERE));
		}
		detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("deathDate"));
		Page<DeathServant> deathPage = this.findByCriteria(detachedcriteria, page, limit);
		List<DeathVO> voList = new ArrayList<>();
		for (DeathServant ds : deathPage.getResult()) {
			DeathVO vo = new DeathVO(ds);
			voList.add(vo);
		}
		Page<DeathVO> result = new Page<>(deathPage.getStart(), deathPage.getCurrentPageSize(),
				deathPage.getTotalSize(), deathPage.getPageSize(), voList);
		return result;
	}

}
