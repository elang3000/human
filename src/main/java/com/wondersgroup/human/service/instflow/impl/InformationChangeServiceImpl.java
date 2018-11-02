package com.wondersgroup.human.service.instflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.instflow.InfoChangePublicInstitution;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.repository.instflow.InfoChangePublicInstitutionRepository;
import com.wondersgroup.human.repository.instflow.InformationChangeRepository;
import com.wondersgroup.human.service.instflow.InfoChangePublicInstitutionService;
import com.wondersgroup.human.service.instflow.InformationChangeService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.instflow.InformationChangesVO;
@Service
public class InformationChangeServiceImpl extends GenericServiceImpl<InformationChange> implements InformationChangeService{

	@Autowired
	private InformationChangeRepository informationChangeRepository;
	
	@Autowired
	private InformationChangeService informationChangeService;
	
	@Autowired
	private InfoChangePublicInstitutionRepository infoChangePublicInstitutionRepository;
	@Autowired
	private InfoChangePublicInstitutionService infoChangePublicInstitutionService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private DictableService dictableService;
	
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	@Autowired
	private OrganizationService organizationService;
	
	
	@Override
	public InformationChange findInformationChangeByPid(String id) {
		// TODO Auto-generated method stub
		return informationChangeRepository.findInformationChangePid(id);
	}

	/*
	 * 
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0 查询条件
	 * @param arg1 排序规则
	 * @param arg2 页码
	 * @param arg3 页大小
	 * @return
	 */
	@Override
	public Page<InformationChangesVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		Page<InformationChange> informationPage= informationChangeRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<InformationChangesVO> voList= new ArrayList<>();
		for (InformationChange p : informationPage.getResult()) {
			InformationChangesVO vo= new InformationChangesVO(p);
			voList.add(vo);
		}
		Page<InformationChangesVO> page = new Page<>(informationPage.getStart(), informationPage.getCurrentPageSize(), informationPage.getTotalSize(), informationPage.getPageSize(),voList);
		return page;
	}

	@Override
	public void saveRegister(InformationChange temp, String opinion, String r, String planState) {
		//当前登录人
		SecurityUser user= userService.load(SecurityUtils.getUserId());
		//当前登录所在单位
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		if (userOrg == null) {
			throw new BusinessException("当前用户所在单位不存在!");
		}

		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		String id = temp.getId();
		
		InfoChangePublicInstitution infoChangPublictitution= temp.getInfoChangePublicInstitution();
		if(StringUtils.isBlank(id)){//保存业务数据
			saveOrUpdate(temp);
			infoChangePublicInstitutionService.saveOrUpdate(infoChangPublictitution);
		}else {
			infoChangePublicInstitutionService.merge(infoChangPublictitution);
		}
		FlowRecord flow = null;
		if(StringUtils.isNotBlank(planState) && InformationChange.INST_INFO_INFORMATION_STATE_POST == Integer.parseInt(planState)){//提交环节,生成流程数据
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"人员信息更改审核");//流程业务名称
			flow.setBusType("InformationChange");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员 
			flow = workflowService.createFlowRecord(flow, "REPORT_INFO_INFORMATION_ALTER_APPLY");//生成初始节点
			
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		
		//保存业务信息
		if(FlowRecord.PASS.equals(r) && StringUtils.isNotBlank(opinion)){//审批通过
			temp.setOpinion(opinion);
		}
		 
		if(InformationChange.INST_INFO_QU_INFORMATION_STATE == temp.getPlanState() 
				&& FlowRecord.PASS.equals(r)){//人员信息更改提交区单位审核
			//进行修改信息的同步
		    changePublicInfo(temp);
			
			temp.setPlanState(null);//修改当前业务流程节点
			temp.setFlowRecord(null);//
			
			
		}else{
			String operationCode = flow.getOperationCode();
			temp.setPlanState(InformationChange.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		informationChangeRepository.merge(temp);
		
		
	}

	/**
	 * 修改人员信息更新
	 * @param temp
	 */
	private void changePublicInfo(InformationChange temp) {
		
		InfoChangePublicInstitution infoChangPublictitution = temp.getInfoChangePublicInstitution();
		PublicInstitution publicInstitution = temp.getPublicInstitution();
		//name修改
		if (StringUtils.isNotBlank(infoChangPublictitution.getName())) {
			publicInstitution.setName(infoChangPublictitution.getName());
		}
		
		//sex修改
		if(StringUtils.isNotBlank(infoChangPublictitution.getSex().getId())){
			publicInstitution.setSex(infoChangPublictitution.getSex());
		}
		//birthDate修改
		if(StringUtils.isNotBlank(infoChangPublictitution.getBirthDate().toString())){
			publicInstitution.setBirthDate(infoChangPublictitution.getBirthDate());
		}
		
		//cardNo修改
		if(StringUtils.isNotBlank(infoChangPublictitution.getCardNo())){
			publicInstitution.setCardNo(infoChangPublictitution.getCardNo());
		}
		
		//民族nation修改
		if(StringUtils.isNotBlank(infoChangPublictitution.getNation().getId())){
			publicInstitution.setNation(infoChangPublictitution.getNation());
		}
		//政治面貌politics修改
		if(StringUtils.isNotBlank(infoChangPublictitution.getPolitics().getId())){
			publicInstitution.setPolitics(infoChangPublictitution.getPolitics());
		}
		//健康health修改
		if(StringUtils.isNotBlank(infoChangPublictitution.getHealth().getId())){
			publicInstitution.setHealth(infoChangPublictitution.getHealth());
		}
		//marriage婚姻修改
		if(StringUtils.isNotBlank(infoChangPublictitution.getMarriage().getId())){
			publicInstitution.setMarriage(infoChangPublictitution.getMarriage());
		}
		//籍贯nativePlaceName
		if(StringUtils.isNotBlank(infoChangPublictitution.getNativePlaceName())){
			publicInstitution.setNativePlaceName(infoChangPublictitution.getNativePlaceName());
		}
		
	}

	@Override
	public boolean operationPublicFlag(PublicInstitution publicInst) {
		InformationChange operationAlterFlag = informationChangeRepository.operationAlterFlag(publicInst);
		if (operationAlterFlag != null) {
			return true;
		}
		return false;
	}

}
