
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.core.util.DateUtils;
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
import com.wondersgroup.human.bo.ofc.Assessment;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.ofcflow.AssessmentDetail;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowCollect;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowUnitPercent;
import com.wondersgroup.human.service.ofc.AssessmentService;
import com.wondersgroup.human.service.ofc.RewardAndPunishService;
import com.wondersgroup.human.service.ofcflow.AssessmentDetailService;
import com.wondersgroup.human.service.ofcflow.AssessmentFlowUnitPercentService;

@Service
public class AssessmentFlowUnitPercentServiceImpl extends GenericServiceImpl<AssessmentFlowUnitPercent>
        implements AssessmentFlowUnitPercentService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private AssessmentDetailService assessmentDetailService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private RewardAndPunishService rewardAndPunishService;
	
	@Override
	public AssessmentFlowUnitPercent getByCollectAndOrg(String collectId, OrganNode org) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AssessmentFlowUnitPercent.class);
		detachedCriteria.add(Restrictions.eq("orgNode.id", org.getId()));
		detachedCriteria.add(Restrictions.eq("assessmentFlowCollect.id", collectId));
		detachedCriteria.add(Restrictions.eq("removed", false));
		List<AssessmentFlowUnitPercent> unitPercents = this.findByCriteria(detachedCriteria);
		if (unitPercents.size() != 0) { return unitPercents.get(0); }
		return null;
	}
	
	@Override
	public void updateDataAndFlow(AssessmentFlowUnitPercent percent, String opinion, String result) {
		AssessmentFlowCollect assessmentFlowCollect = percent.getAssessmentFlowCollect();
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		//是否记三等功结果
		CodeInfo creditCodeInfo =dictableService.getCodeInfoByCode("1", "DM215");
		FlowRecord flow;
		if (percent.getFlowRecord() == null) {// 提交环节，先生成流程数据
			percent.setFlowStatus(0);
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(percent.getId());// 流程业务ID
			flow.setBusName("年度考核");// 流程业务名称
			flow.setBusType(FlowBusTypeConstant.FLOW_ASSESS_REWARD);// 流程业务类型
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);// 流程业务目标人员
			flow = workflowService.createFlowRecord(flow, AssessmentFlowUnitPercent.FLOW_ASSESS_STEP1);// 初始节点
		} else {
			flow = percent.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(result);
			if (result.equals(FlowRecord.PASS)
			        && percent.getFlowRecord().getOperationCode().equals(AssessmentFlowUnitPercent.FLOW_ASSESS_STEP6)) {
				flow = workflowService.completeFlowRecordByAppoint(flow, percent.getOrgNode());
			} else {
				flow = workflowService.completeWorkItem(flow);// 提交下个节点
			}
		}
		if (null == flow) {
			// 流程结束,插入子集信息
			percent.setFlowStatus(1);
			percent.setStatus(CommonConst.YES);
			List<AssessmentDetail> currentUnitDetails = this.assessmentDetailService
			        .getCurrentUnitDetails(percent.getOrgNode(), assessmentFlowCollect.getId());
			// 考核结论代码DM018 1优秀
			CodeInfo fineCodeInfo = dictableService.getCodeInfoByCode("1", "DM018");
			// 考核结论代码DM017 11 年度考核
			CodeInfo yearCodeInfo = dictableService.getCodeInfoByCode("11", "DM017");
			for (AssessmentDetail assessmentDetail : currentUnitDetails) {
				Assessment assessment = new Assessment();
				assessment.setServant(assessmentDetail.getServant());
				
				assessment.setCategory(yearCodeInfo);
				Date dateYear = DateUtils.parseDate(assessmentFlowCollect.getYear().toString(), "yyyy");
				assessment.setStartDate(dateYear);
				assessment.setEndDate(getEndDate(assessmentFlowCollect.getYear(), 4));
				assessment.setOrganizationName(assessmentDetail.getServant().getDepartName());
				assessment.setOpinion(assessmentDetail.getRemarks());
				assessment.setAllParticipantName(assessmentDetail.getLastOperator());
				assessment.setConclusionCategory(assessmentDetail.getResult());
				assessment.setAssessmentYear(dateYear);
				assessmentService.save(assessment);
				
				// 假如考核优秀,则插入奖励子集
				if (assessmentDetail.getResult().getId().equals(fineCodeInfo.getId())) {
					RewardAndPunish rewardAndPunish = new RewardAndPunish();
					rewardAndPunish.setServant(assessmentDetail.getServant());
					rewardAndPunish.setType(0);
					// 假如记三等功
					if (creditCodeInfo.getId().equals(assessmentDetail.getIscredit())) {
						CodeInfo sdgCodeInfo = dictableService.getCodeInfoByCode("1113", "GBT_8563_1_2005");
						rewardAndPunish.setRewardName(sdgCodeInfo.getName());
						rewardAndPunish.setRewardCode(sdgCodeInfo);
						rewardAndPunish.setHonoraryName(sdgCodeInfo.getName());
					} else {
						// 嘉奖
						CodeInfo jjCodeInfo = dictableService.getCodeInfoByCode("1118", "GBT_8563_1_2005");
						rewardAndPunish.setRewardName(jjCodeInfo.getName());
						rewardAndPunish.setRewardCode(jjCodeInfo);
						rewardAndPunish.setHonoraryName(jjCodeInfo.getName());
					}
					rewardAndPunish.setRewardApprovalUnitName(assessmentDetail.getServant().getDepartName());
					rewardAndPunish.setRewardApprovalDate(new Date());
					rewardAndPunishService.save(rewardAndPunish);
				}
			}
		}
		percent.setFlowRecord(flow);
		this.update(percent);
	}
	
	/**
	 * @Title: getStartDate
	 * @Description: 通过季度获取结束时间
	 * @param season
	 * @return
	 * @return: Date
	 */
	public Date getEndDate(Integer year, Integer season) {
		
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, season * 3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}

	@Override
	public List<AssessmentFlowUnitPercent> getPercentByCollect(String collectId) {

		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AssessmentFlowUnitPercent.class);
		detachedCriteria.add(Restrictions.eq("assessmentFlowCollect.id", collectId));
		detachedCriteria.add(Restrictions.eq("removed", false));
		List<AssessmentFlowUnitPercent> unitPercents = this.findByCriteria(detachedCriteria);
		return unitPercents;
	
	}

	@Override
	public List<AssessmentFlowUnitPercent> getFinishPercentByCollect(String collectId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AssessmentFlowUnitPercent.class);
		detachedCriteria.add(Restrictions.eq("assessmentFlowCollect.id", collectId));
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("status", 1));
		List<AssessmentFlowUnitPercent> unitPercents = this.findByCriteria(detachedCriteria);
		return unitPercents;
	}
	
}
