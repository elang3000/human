package com.wondersgroup.human.service.instflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.instflow.RecordableRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.instflow.RecordableRecordQuery;
import com.wondersgroup.human.repository.instflow.RecordableRecordRepository;
import com.wondersgroup.human.repository.pubinst.PublicInstitutionRepository;
import com.wondersgroup.human.service.instflow.RecordableRecordService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.instflow.RecordableRecordVO;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

@Service
public class RecordableRecordServiceImpl extends GenericServiceImpl<RecordableRecord>
		implements RecordableRecordService {

	@Autowired
	private RecordableRecordRepository recordableRecordRepository;

	@Autowired
	private PublicInstitutionRepository publicInstitutionRepository;

	@Autowired
	private RecordableRecordService recordableRecordService;
	@Autowired
	private UserService userService;

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private PublicInstitutionService publicInstitutionService;

	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private OrganizationService organizationService;

	@Override
	public RecordableRecord findRecordableRecordByPid(String id) {
		// TODO Auto-generated method stub
		return recordableRecordRepository.findRecordableRecordByPid(id);
	}

	/*
	 * /** (non Javadoc)
	 * 
	 * @Title: getPage
	 * 
	 * @Description: 数据转换为VO的分页查询
	 * 
	 * @param arg0 查询条件
	 * 
	 * @param arg1 排序规则
	 * --
	 * @param arg2 页码
	 * 
	 * @param arg3 页大小
	 * 
	 * @return
	 * -
	 * @see com.wondersgroup.human.service.instflow.RecordableRecordService#
	 * findByFilterVO(java.util.List, com.wondersgroup.framework.core.bo.Sorts,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<RecordableRecordVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		Page<RecordableRecord> recordableRecordPage = recordableRecordRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<RecordableRecordVO> voList = new ArrayList<>();
		for (RecordableRecord p : recordableRecordPage.getResult()) {
			RecordableRecordVO vo = new RecordableRecordVO(p);
			voList.add(vo);
		}
		Page<RecordableRecordVO> page = new Page<>(recordableRecordPage.getStart(),
				recordableRecordPage.getCurrentPageSize(), recordableRecordPage.getTotalSize(),
				recordableRecordPage.getPageSize(), voList);
		return page;
	}

	// @Description: 数据转换为VO的分页查询
	@SuppressWarnings("deprecation")
	public Page<PublicInstitutionVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		Page<PublicInstitution> publicInstitutionPage = publicInstitutionRepository.findByFilter(arg0, arg1, arg2,
				arg3);
		List<PublicInstitutionVO> voList = new ArrayList<>();
		for (PublicInstitution p : publicInstitutionPage.getResult()) {
			PublicInstitutionVO vo = new PublicInstitutionVO(p);
			voList.add(vo);
		}
		Page<PublicInstitutionVO> page = new Page<>(publicInstitutionPage.getStart(),
				publicInstitutionPage.getCurrentPageSize(), publicInstitutionPage.getTotalSize(),
				publicInstitutionPage.getPageSize(), voList);
		return page;
	}

	@Override
	public void saveRegister(RecordableRecord temp, String opinion, String r, String planState) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		if (userOrg == null) {
			throw new BusinessException("当前用户所在单位不存在!");
		}
		
		//List<OrganNode> parList = organizationService.getParentOrganNodeByOrganNodeAnOrganRelationType(userOrg.getId(), CommonConst.ORGAN_RELATION_HR_CODE);
		
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		String id = temp.getId();

		PublicInstitution publicInstitution = temp.getPublicInstitution();

		if (StringUtils.isBlank(id)) {
			saveOrUpdate(temp);// 保存业务数据
		}

	/*	FlowRecord flow = null;
		if (StringUtils.isNotBlank(planState) && RecordableRecord.INST_INFO_RECORDABLE_STATE_POST == Integer.parseInt(planState)) {// 提交环节，先生成流程数据
			// 保存业务流程主表信息
			// registerExist = saveBusinessInfo(temp, opinion);
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"人员离退备案");//流程业务名称
			flow.setBusType("RecordableRecord");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_INS); //分类事业单位
			flow = workflowService.createFlowRecord(flow, "REPORT_INFO_RECORD_APPLY");// 初始节点
		} else {
			//temp = recordableRecordService.findRecordableRecordByPid(id);
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);// 提交下个节点
		}
		
		// 保存业务信息
		if (FlowRecord.PASS.equals(r) && StringUtils.isNotBlank(opinion)) {// 审批通过
			temp.setOpinion(opinion);
		}
		
		List<OrganNode> parList = organizationService.getParentOrganNodeByOrganNodeAnOrganRelationType(userOrg.getId(), CommonConst.ORGAN_RELATION_HR_CODE);
		if (parList != null && parList.size() > 0) {
			if (RecordableRecord.INST_INFO_RECORDABLE_STATE_LEADER == temp.getPlanState()
					&& FlowRecord.PASS.equals(r)) {// 离退备案上级确认
				
				temp.setPlanState(null);
				temp.setFlowRecord(null);//修改当前业务的流程节点

				//修改主表状态
				updateInstState(publicInstitution, temp);
			} else {
				temp.setPlanState(RecordableRecord.power.get(flow.getOperationCode()));//实际有权限的操作节点
				temp.setFlowRecord(flow);//修改当前业务的流程节点
			}
		}else {

			//workflowService.completeWorkItem(flow);
			updateInstState(publicInstitution, temp);
			
			temp.setPlanState(null);//实际有权限的操作节点
			temp.setFlowRecord(null);//修改当前业务的流程节点
		}
*/
		String title = "事业单位人员离退备案通知!!";
		String content ="请查看"+temp.getPublicInstitution().getName()+"("+temp.getPublicInstitution().getCardNo() + ")+的备案信息";

		//通知人员信息登记
		publicInstitutionService.getPublicQuLeadersToNotice(temp.getId(), RecordableRecord.class.getSimpleName(), title, content);
		
		recordableRecordRepository.merge(temp);

	}
	
	
    /**
     * 修改主表状态
     * @param publicInstitution  主表
     * @param temp 离职表信息
     */
/*	private void updateInstState(PublicInstitution publicInstitution, RecordableRecord temp) {
		CodeInfo codeInfo = temp.getRecodeWay();
		publicInstitutionService.merge(publicInstitution);
		if ("1".equals(codeInfo.getCode()) || "2".equals(codeInfo.getCode())) { //类型1
			//   辞退						//辞职
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("2", "DM200");//修改状态 辞退CODE
			publicInstitution.setIsOnHold(isOnHold);
		}
		
		if("3".equals(codeInfo.getCode())){
			//   死亡
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("4", "DM200");//修改状态 去世CODE
			publicInstitution.setIsOnHold(isOnHold);
			
		}
		if("4".equals(codeInfo.getCode()) || "5".equals(codeInfo.getCode())){
			//   退休								//解聘
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("5", "DM200");//修改状态  其他人员
			publicInstitution.setIsOnHold(isOnHold);
			
		}
		
	}*/

	// 保存流程主表信息
	private RecordableRecord saveBusinessInfo(PublicInstitution temp, String opinion) {
		RecordableRecord record = new RecordableRecord();
		record.setPublicInstitution(temp);
		recordableRecordService.save(record);
		return record;
	}

	@Override
	public boolean operationPublicFlag(PublicInstitution publicInstitution) {
		RecordableRecord operationAlterFlag = recordableRecordRepository.operationAlterFlag(publicInstitution);
		if (operationAlterFlag != null) {
			return true;
		}
		return false;
	}

	@Override
	public Page<RecordableRecordVO> pageList(RecordableRecordQuery record, Integer page, Integer limit) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位

		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(RecordableRecord.class);
		DetachedCriteria s = detachedcriteria.createAlias("publicInstitution", "p");
		
		if(StringUtils.isNotBlank(record.getName())){
			s.add(Restrictions.like("p.name", record.getName(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(record.getCardNo())){
			s.add(Restrictions.eq("p.cardNo", record.getCardNo()));
		}
		
		if(StringUtils.isNotBlank(record.getIsOnHold())){
			detachedcriteria.add(Restrictions.like("isOnHold", record.getIsOnHold(),MatchMode.ANYWHERE));//状态查询
		}
		
		
		
		if (x != null && x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
			detachedcriteria.add(Restrictions.eq("lastOperator", SecurityUtils.getUserId()));
		} else {
			detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		}
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<RecordableRecord> recordPage = this.findByCriteria(detachedcriteria, page, limit);
		List<RecordableRecordVO> voList = new ArrayList<>();
		for (RecordableRecord rs : recordPage.getResult()) {
			RecordableRecordVO vo = new RecordableRecordVO(rs);
			voList.add(vo);
		}
		Page<RecordableRecordVO> infoPage = new Page<>(recordPage.getStart(), recordPage.getCurrentPageSize(), recordPage.getTotalSize(), recordPage.getPageSize(), voList);
		
		return infoPage;
	}

}
