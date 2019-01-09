/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 招录计划 服务实现类
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:13:23 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:13:23 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.instflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
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
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.bo.pubinst.PtAppointDismissDuty;
import com.wondersgroup.human.bo.pubinst.PtExperience;
import com.wondersgroup.human.bo.pubinst.PtPost;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.instflow.AlternatingRotationQueryParam;
import com.wondersgroup.human.dto.instflow.InfoRegisterQueryParam;
import com.wondersgroup.human.repository.instflow.AlternatingRotationRepository;
import com.wondersgroup.human.service.instflow.AlternatingRotationService;
import com.wondersgroup.human.service.pubinst.PtAppointDismissDutyService;
import com.wondersgroup.human.service.pubinst.PtExperienceService;
import com.wondersgroup.human.service.pubinst.PtPostService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.instflow.AlternatingRotationVO;
import com.wondersgroup.human.vo.instflow.MemberInfoRegisterVO;

/** 
 * @ClassName: AlternatingRotatingServiceImpl 
 * @Description: 交流轮岗  service实现
 * @author: lyf
 * @date: 2018年9月14日 上午11:13:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class AlternatingRotatingServiceImpl  extends GenericServiceImpl<AlternatingRotation> implements AlternatingRotationService{
	
	@Autowired
	private AlternatingRotationRepository alternatingRotationRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private PtExperienceService experienceService;
	
	@Autowired
	private PtAppointDismissDutyService   appointDismissDutyService;
	
	@Autowired
	private PtPostService postService ;
	
	@Autowired
	private PtExperienceService ptExperienceService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	

	@Override
	public AlternatingRotation findAlternatingRotationByPid(String id) {
		return alternatingRotationRepository.findAlternatingRotationByPid(id);
	}
	
	@Override
	public void  executeAlternatingRotation(AlternatingRotation temp, String opinion, String r, String planState) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		String id = temp.getId();
		if(StringUtils.isBlank(id)){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow = null;
		if(StringUtils.isNotBlank(planState) && AlternatingRotation.INST_INFO_STATE_POST == Integer.parseInt(planState)){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"交流轮岗");//流程业务名称
			flow.setBusType("AlternatingRotation");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_INS); //分类事业单位
			flow = workflowService.createFlowRecord(flow, "REPORT_INFO_ALTERROTATION_APPLY");//初始节点
			//String id2 = flow.getId();
		}else{
		    //temp = findAlternatingRotationByPid(temp.getId());
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		
		//保存业务信息
		if(FlowRecord.PASS.equals(r)){//审批通过
			temp.setOpinion(opinion);
		}
		if(AlternatingRotation.INST_INFO_STATE_END == temp.getPlanState()&&FlowRecord.PASS.equals(r)){//招录计划最后环节
			temp.setPlanState(null);
			temp.setFlowRecord(null);//修改当前业务的流程节点
			
			//如果是外区交流进来的改为正式入库人员
			PublicInstitution publicInstitution = temp.getPublicInstitution();
			//修改任职状态   在职
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", "DM200");//在职CODE
			publicInstitution.setIsOnHold(isOnHold);
			
			//交流到那个单位
			OrganNode alterRotaOrgan = temp.getAlterRotaOrgan();
			publicInstitution.setDepartId(alterRotaOrgan.getId());
			publicInstitution.setDepartName(alterRotaOrgan.getName());
			
			publicInstitutionService.merge(publicInstitution);
			temp.setPublicInstitution(publicInstitution);
			
			merge(temp);
			
			//新增简历  职务信息
			addPostInfo(temp);
			
			String title = "轮岗交流";
			String content = publicInstitution.getName() + "进行了轮岗交流 ,请进行人员信息维护";
			//发通知
			publicInstitutionService.getPublicQuLeadersToNotice(temp.getId(), AlternatingRotation.class.getSimpleName(), title, content);
		}else{
			temp.setPlanState(AlternatingRotation.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		
		alternatingRotationRepository.merge(temp);
	}
	
	
	/**
	 * 添加一条职务信息
	 * @param user 
	 * @param alternatingRotatingExist
	 */
	private PtPost  addPostInfo(AlternatingRotation alter) {
		//之前的现任职务
		
		PtPost post = new PtPost();
	    PublicInstitution publicInstitution = alter.getPublicInstitution();
	    post.setPublicInstitution(publicInstitution);
	    post.setCommunicationSign(alter.getCommunicationSign());
	    post.setAttribute(alter.getAttribute()); //职务属性
	    post.setPostCode(alter.getPostCode()); //职务名称
	    post.setPostName(alter.getPostCode().getName());
	    //CodeInfo nowPostSign = dictableService.getCodeInfoByCode("1", "DM215");//在职CODE
	    //post.setNowPostSign(nowPostSign); //现任职务
	    CodeInfo inOfficeCode = dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_TYPE_POST_STATUS);
	    post.setTenureName(publicInstitution.getDepartName());
	    post.setTenureStatus(inOfficeCode);
		
		post.setApprovalDate(alter.getAlterrotaDate());//任职日期
	    
	    postService.save(post);
	    
	    //更新简历信息
		ptExperienceService.SaveOrUpdateExperienceBypost(post);
	    
	    return post;
	}


	/**
	 * 简历   职务  信息保存
	 * @param alternatingRotatingExist
	 */
	private void addExperienceAndAppointDutyInfo(AlternatingRotation alternatingRotatingExist) {
		//新增简历记录   
		PtExperience experience = new PtExperience();
		experience.setPublicInstitution(alternatingRotatingExist.getPublicInstitution());
		experience.setIntroduce(alternatingRotatingExist.getRemark());
		//简历开始时间  和结束时间
		experience.setStartDate(new Date());
		experience.setEndDate(new Date());
		//当前单位
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		experience.setFormerUnit(x.getName());
		experience.setFormerUnitCode(x.getCode());
		//在某一单位（部门）工作（学习）期间所从事的工作或担任的职务
		//experience.setFormerJob(alternatingRotatingExist.getPublicInstitution());
		//该人曾在单位担任的职务类别
		//experience.setFormerJobCategory(alternatingRotatingExist.getPublicInstitution());
		//能够证明该人在某单位从事学习、工作及担任某种职务的人员姓名。
		//experience.setReterence(reterence);
		experienceService.save(experience);
		
		
		//新增职务信息
		PtAppointDismissDuty duty = new PtAppointDismissDuty();
		duty.setPublicInstitution(alternatingRotatingExist.getPublicInstitution());
		//拟任免机构名称.与拟任免职务相对应的工作机构及工作机构部门名称。
		duty.setAppointDismissOrganizationName(alternatingRotatingExist.getAlterRotaOrgan().getName());
		//拟任免机构代码
		duty.setAppointDismissOrganizationCode(alternatingRotatingExist.getAlterRotaOrgan().getCode());
		//拟任免职务名称.该人拟任或拟免的职务名称。
		//duty.setAppointDismissDutyName(appointDismissDutyName);
		//拟任免职务代码.
		//duty.setAppointDismissDutyCode(appointDismissDutyCode);
		appointDismissDutyService.save(duty);
	}

	@Override
	public boolean operationAlterFlag(PublicInstitution publicInstitution) {
		AlternatingRotation alternatingRotation = alternatingRotationRepository.operationAlterFlag(publicInstitution);
		if (alternatingRotation != null) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public Page<AlternatingRotationVO> pageList(AlternatingRotationQueryParam param, Integer page, Integer limit) {
		
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位

		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(AlternatingRotation.class);
		DetachedCriteria s = detachedcriteria.createAlias("publicInstitution", "p");
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			s.add(Restrictions.like("p.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			s.add(Restrictions.eq("p.cardNo", param.getCardNo()));
		}
		if (x != null && x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
			detachedcriteria.add(Restrictions.eq("lastOperator", SecurityUtils.getUserId()));
		} else {
			detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		}
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<AlternatingRotation> registerPage = this.findByCriteria(detachedcriteria, page, limit);
		List<AlternatingRotationVO> voList = new ArrayList<>();
		for (AlternatingRotation rs : registerPage.getResult()) {
			AlternatingRotationVO vo = new AlternatingRotationVO(rs);
			voList.add(vo);
		}
		Page<AlternatingRotationVO> result = new Page<>(registerPage.getStart(), registerPage.getCurrentPageSize(),
				registerPage.getTotalSize(), registerPage.getPageSize(), voList);
		return result;
	}

	
	
}
