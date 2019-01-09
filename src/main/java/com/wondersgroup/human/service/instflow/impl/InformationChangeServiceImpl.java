package com.wondersgroup.human.service.instflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.Vlookup;
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
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.bo.pubinst.PtExperience;
import com.wondersgroup.human.bo.pubinst.PtJobLevel;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.instflow.InformationChangesQuery;
import com.wondersgroup.human.repository.instflow.InformationChangeRepository;
import com.wondersgroup.human.service.instflow.InformationChangeService;
import com.wondersgroup.human.service.pubinst.PtExperienceService;
import com.wondersgroup.human.service.pubinst.PtJobLevelService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.instflow.InformationChangesVO;
@Service
public class InformationChangeServiceImpl extends GenericServiceImpl<InformationChange> implements InformationChangeService{

	@Autowired
	private InformationChangeRepository informationChangeRepository;
	
	@Autowired
	private InformationChangeService informationChangeService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	
	
	
	
	
	@Autowired
	private PtJobLevelService ptJobLevelService ;
	
	@Autowired
	private PtExperienceService ptExperienceService;
	
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
		
		if(StringUtils.isBlank(id)){//保存业务数据
			saveOrUpdate(temp);
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
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_INS);//分类事业单位
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
		   // changePublicInfo(temp);
			
			temp.setPlanState(null);//修改当前业务流程节点
			temp.setFlowRecord(null);//
			addPostInfo(temp);
			
			//是否是最高标识  是   TODO需要进行修改
			/*CodeInfo currentIdentification = temp.getCurrentIdentification();
			if ("是".equals(currentIdentification.getName())) {
				PublicInstitution publicInstitution = temp.getPublicInstitution();
				publicInstitution.setNowJobLevel(temp.getCode());
				publicInstitutionService.merge(publicInstitution);
			}*/
			
			merge(temp);
			
			String title = "事业单位人员信息变更通知!";
			String content ="请完善"+temp.getPublicInstitution().getName()+"("+temp.getPublicInstitution().getCardNo() + ")+的职级信息";

			//通知人员信息登记
			publicInstitutionService.getPublicQuLeadersToNotice(temp.getId(), InformationChange.class.getSimpleName(), title, content);
		}else{
			String operationCode = flow.getOperationCode();
			temp.setPlanState(InformationChange.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		informationChangeRepository.merge(temp);
		
		
	}

	/**
	 * 添加一条职务信息
	 * @param alter
	 * @return
	 */
	private PtJobLevel addPostInfo(InformationChange alter) {
		
		//职级
		PtJobLevel job = new PtJobLevel();
		PublicInstitution publicInstitution = alter.getPublicInstitution();
		job.setPublicInstitution(publicInstitution);
		job.setName(alter.getName());//职级名称
		job.setCode(alter.getCode());//职级代码
		job.setApprovalDate(alter.getApprovalDate());//职级批准日期
		job.setEndDate(alter.getEndDate());//职级终止日期
		job.setCurrentIdentification(alter.getCurrentIdentification());//现行职级标识
		job.setApprovalNo(alter.getApprovalNo());//职级批准文号
		ptJobLevelService.save(job);
		
		
		//简历
		PtExperience exp = new PtExperience();
	    exp.setPublicInstitution(publicInstitution);
	    OrganNode currentUnit = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//获取当前单位
	    exp.setFormerUnit(currentUnit.getName());//简历 所在单位
	    exp.setStartDate(new Date());//简历起始日期
	    
	    exp.setIntroduce(alter.getPublicInstitution().getName()+"的职级信息变更为:"+"职级名称:"+alter.getName());
	    
	    ptExperienceService.save(exp);
	    
	    //更新简历信息
	    ptJobLevelService.saveOrUpdate(job);
		
	
		return job;
		
	}

	/**
	 * 简历 职级 信息保存
	 * @param informationChangeExJob
	 */
	private void addExperienceAndJobLevel(InformationChange informationChangeExJob){
		
		//新增简历记录
		PtExperience experience = new PtExperience();
		experience.setPublicInstitution(informationChangeExJob.getPublicInstitution());
		//简历 开始和结束时间
		experience.setStartDate(new Date());
		experience.setEndDate(new Date());
		
		//所在单位
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		//experience.setFormerJob(x.getName());//所在单位
	//	experience.setFormerUnitCode(x.getCode()); //单位代码
		experience.setFormerUnit(x.getName());
		experience.setFormerUnitCode(x.getCode());
		ptExperienceService.save(experience);
		
		
		//新增职级信息
		PtJobLevel ptjob = new PtJobLevel();
		ptjob.setPublicInstitution(informationChangeExJob.getPublicInstitution());
		//职级名称
		ptjob.setName(informationChangeExJob.getName());
		//职级代码
		ptjob.setCode(informationChangeExJob.getCode());
		
		ptJobLevelService.save(ptjob);
		
		
	}

	@Override
	public boolean operationPublicFlag(PublicInstitution publicInst) {
		InformationChange operationAlterFlag = informationChangeRepository.operationAlterFlag(publicInst);
		if (operationAlterFlag != null) {
			return true;
		}
		return false;
	}

	@Override
	public Page<InformationChangesVO> pageList( InformationChangesQuery infor, Integer page, Integer limit) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位

		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(InformationChange.class);
		DetachedCriteria s = detachedcriteria.createAlias("publicInstitution", "p");
		
		
		if(StringUtils.isNotBlank(infor.getName1())){
			s.add(Restrictions.like("p.name", infor.getName1(), MatchMode.ANYWHERE));//姓名查询
		}
		if(StringUtils.isNotBlank(infor.getCardNo())){
			s.add(Restrictions.eq("p.cardNo", infor.getCardNo()));
		}
		if(StringUtils.isNotBlank(infor.getName())){
			detachedcriteria.add(Restrictions.like("name", infor.getName(), MatchMode.ANYWHERE));//职级查询
		}
		
		
		if (x != null && x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
			detachedcriteria.add(Restrictions.eq("lastOperator", SecurityUtils.getUserId()));
		} else {
			detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		}
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<InformationChange> inforPage = this.findByCriteria(detachedcriteria, page, limit);
		List<InformationChangesVO> voList = new ArrayList<>();
		for (InformationChange rs : inforPage.getResult()) {
			InformationChangesVO vo = new InformationChangesVO(rs);
			voList.add(vo);
		}
		
		Page<InformationChangesVO> result = new Page<>(inforPage.getStart(), inforPage.getCurrentPageSize(), inforPage.getTotalSize(), inforPage.getPageSize(), voList);
			
		
		
		return result;
	}

}
