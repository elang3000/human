/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PublicInstitutionServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl 
 * 描述: 人员信息维护服务接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年5月21日 上午11:05:33 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月21日 上午11:05:33 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityGroup;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.bo.instflow.RecordableRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.businesspersonel.BusinessParam;
import com.wondersgroup.human.repository.pubinst.PublicInstitutionRepository;
import com.wondersgroup.human.service.instflow.AlternatingRotationService;
import com.wondersgroup.human.service.instflow.InformationChangeService;
import com.wondersgroup.human.service.instflow.MemberInfoRegisterService;
import com.wondersgroup.human.service.instflow.RecordableRecordService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/** 
 * @ClassName: ServantServiceImpl 
 * @Description: 人员信息维护服务接口实现类
 * @author: tzy
 * @date: 2018年5月21日 上午11:05:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class PublicInstitutionServiceImpl extends GenericServiceImpl<PublicInstitution> implements PublicInstitutionService{
	
	@Autowired
	private PublicInstitutionRepository publicInstitutionRepository;
	
	@Autowired
	private MemberInfoRegisterService memberInfoRegisterService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkflowService workflowService;
	

	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private AlternatingRotationService alternatingRotationService;
	
	@Autowired
	private InformationChangeService informationChangeService;
	
	@Autowired
	private RecordableRecordService recordableRecordService;
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	
	@Autowired
	private FormationControlService formationControlService;
	

	/**
	 * (non Javadoc) 
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件   filter
	 * @param arg1	排序规则   sort
	 * @param arg2	页码		page
	 * @param arg3	页大小	limit
	 * @return 
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("deprecation")
	public Page<PublicInstitutionVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3){
		Page<PublicInstitution> publicInstitutionPage= publicInstitutionRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<PublicInstitutionVO> voList = new ArrayList<>();
		for(PublicInstitution p:publicInstitutionPage.getResult()){
			PublicInstitutionVO vo = new PublicInstitutionVO(p);
			voList.add(vo);
		}
		Page<PublicInstitutionVO> page = new Page<>(publicInstitutionPage.getStart(), publicInstitutionPage.getCurrentPageSize(), publicInstitutionPage.getTotalSize(), publicInstitutionPage.getPageSize(), voList);
		return page;
	}

	@Override
	public void saveRegister(PublicInstitution temp, String opinion, String r, String planState, String mid) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		String id = temp.getId();
		//当前单位
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		if(StringUtils.isBlank(id)){
			
			CodeInfo typemember = dictableService.getCodeInfoByCode("4", DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE);
			if(typemember != null){
				temp.setPersonType(typemember);  //设置人员类别-事业单位人员    
			}
			//任职状态  其他人员  
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("5", "DM200");//在职CODE
			temp.setIsOnHold(isOnHold);
			
			
            if (x == null) {
            	throw new BusinessException("当前单位不存在！");
			}
			temp.setDepartName(x.getName());
			temp.setDepartId(x.getId());
			
			saveOrUpdate(temp);//保存业务数据
		}
		
		MemberInfoRegister registerExist = null;
		FlowRecord flow = null;
		if(StringUtils.isNotBlank(planState) && MemberInfoRegister.INST_INFO_REGISTER_STATE_POST == Integer.parseInt(planState)){//提交环节，先生成流程数据
			
			//保存业务流程主表信息
			registerExist = saveBusinessInfo(temp, opinion);
			
			//验编制职数   不通过会抛出异常   无需判断
			saveFormationControl(registerExist.getInforPost());
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(registerExist.getId());//流程业务ID
			flow.setBusName(userOrg.getAllName()+"人员登记");//流程业务名称
			flow.setBusType("MemberInfoRegister");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);//流程业务目标人员
			flow.setCategory(FlowRecord.FLOW_RECORD_CATEGORY_INS); //分类事业单位
			flow = workflowService.createFlowRecord(flow, "REPORT_INFO_REGISTER");//初始节点
		}else{
			//registerExist = memberInfoRegisterService.findMemberInfoRegisterByPid(id);
			registerExist = memberInfoRegisterService.get(mid);
			//registerExist = memberInfoRegisterService.load(mid);
			flow = registerExist.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
		    //flow.setRemark(); //备注
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		
		//保存业务信息
		if(FlowRecord.PASS.equals(r)){//审批通过
			registerExist.setOpinion(opinion);
		}
		if(MemberInfoRegister.INST_INFO_REGISTER_STATE_END == registerExist.getPlanState()&&FlowRecord.PASS.equals(r)){//招录计划最后环节
			registerExist.setPlanState(null);
			registerExist.setFlowRecord(null);//修改当前业务的流程节点
			
			//修改任职状态   在职
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", "DM200");//在职CODE
			temp.setIsOnHold(isOnHold);
			merge(temp);
			
			//正式占编占职
			realIntoInstFormationControl(x, temp.getNowJobLevel());
			
			//TODO 职级已经填写    这里需要生成职级职级子集
			String title = "事业单位人员信息登记信息维护通知";
			String content = "请完善" + registerExist.getPublicInstitution().getName() + "(" + registerExist.getPublicInstitution().getCardNo() + ")的职务信息、职级信息、学历信息、学位信息、考核信息、家庭信息";
			//通知人员信息登记
			publicInstitutionService.getPublicQuLeadersToNotice(registerExist.getId(), MemberInfoRegister.class.getSimpleName(), title, content);
		}else{
			registerExist.setPlanState(MemberInfoRegister.power.get(flow.getOperationCode()));//实际有权限的操作节点
			registerExist.setFlowRecord(flow);//修改当前业务的流程节点
		}
		memberInfoRegisterService.merge(registerExist);
	}
	
	
	/**
	 * 正式入编
	 * @param x
	 * @param nowJobLevel
	 */
	private void realIntoInstFormationControl(OrganNode x, CodeInfo nowJobLevel) {
		//解锁  编制职数
		formationControlService.executeUnlockIntoInstFormationNum(x.getId());; //解锁定入编   -1
		formationControlService.executeUnlockInstPostIntoNum(x.getId(), nowJobLevel.getCode()); //解锁职数
		
		//正式占编占职
		formationControlService.executeIntoInstFormation(x.getId());
		formationControlService.executeIntoInstPost(x.getId(), nowJobLevel.getCode());
	}

	@Override
	public boolean saveFormationControl(CodeInfo nowJobLevel) {
		boolean flag = false;
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		//编制控制
		boolean numFlag = formationControlService.queryJudgeInstFormationNum(x.getId());
		if (numFlag) {
			//控制数据判断
			boolean postFlag = formationControlService.queryJudgeInstPostNum(x.getId(), nowJobLevel.getCode());
			if (postFlag) { //占编占职
				formationControlService.executeLockIntoInstFormationNum(x.getId()); //编
				formationControlService.executeLockInstPostIntoNum(x.getId(), nowJobLevel.getCode()); //占职
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 保存流程主表信息
	 * @param temp
	 * @param opinion
	 * @return
	 */
	private MemberInfoRegister saveBusinessInfo(PublicInstitution temp, String opinion) {
		MemberInfoRegister member = new MemberInfoRegister();
		member.setPublicInstitution(temp);
		member.setOpinion(opinion);
		
		if (temp.getNowJobLevel() != null) {
			CodeInfo nowJobLevel = codeInfoService.load(temp.getNowJobLevel().getId());
			String postName = "";
			if (nowJobLevel != null) {
				CodeInfo parent = nowJobLevel.getParent();
				if (parent != null) {
					postName = parent.getName() + " " + nowJobLevel.getName();
				}else {
					postName = nowJobLevel.getName();
				}
			}
			member.setInforPost(nowJobLevel);
			member.setInforPostName(postName);
		}
		
		memberInfoRegisterService.save(member);
		
		return member;
	}

	@Override
	public boolean getPublicQuLeadersToNotice(String id, String type, String title, String content) {
		SecurityGroup group = userService.loadGroupByCode("SY_QU_LEADERS_INFORMATION");
		SecurityUser[] allUsersInGroup = userService.getAllUsersInGroup(group);
		MemberInfoRegister memberInfoRegister = null;
		AlternatingRotation alternatingRotation = null;
		InformationChange informationChange = null;
		RecordableRecord recordableRecord = null;
        String infoCreater = null;
        
		if ("MemberInfoRegister".equals(type)) {
			memberInfoRegister = memberInfoRegisterService.load(id);
			infoCreater = memberInfoRegister.getCreater();
		}else if("AlternatingRotation".equals(type)){
			alternatingRotation = alternatingRotationService.load(id);
			infoCreater = alternatingRotation.getCreater();
		}else if("InformationChange".equals(type)){
			informationChange = informationChangeService.load(id);
			infoCreater = informationChange.getCreater();
		}else if("RecordableRecord".equals(type)){
			recordableRecord = recordableRecordService.load(id);
			infoCreater = recordableRecord.getCreater();
		}
		
		if (allUsersInGroup != null && allUsersInGroup.length > 0) {
			for (SecurityUser securityUser : allUsersInGroup) {
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, infoCreater, title, content, "")));
			}
			return true;
		}
		return false;
	}

	
	/********************************************事业单位综合查询************************************************************************/
	
	//查询所有在职人员
	@Override
	public List<PublicInstitution> getAllActiveBusiness() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PublicInstitution.class);
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
		detachedCriteria.add(Restrictions.eq("creater", "SYSTEM"));
		detachedCriteria.add(Restrictions.isNotNull("departId"));
		List<PublicInstitution> allActiveServant = publicInstitutionRepository.findByCriteria(detachedCriteria);
		return allActiveServant;
	}

	
	@Override
	public Page<PublicInstitutionVO> queryBusinessInfoBySeniorCondation(List<BusinessParam> spList,
			Map<String, String> m, Integer page, Integer limit) {
		return publicInstitutionRepository.queryBusinesstInfoBySeniorCondation(spList, m, page, limit);
	}

	
	/**
	 * 通过姓名和身份证查询对应的PublicInstitution
	 */
	@Override
	public List<PublicInstitution> getBusinessByCardNo(String cardNo) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PublicInstitution.class);
		detachedCriteria.add(Restrictions.eq("cardNo", cardNo));
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
		detachedCriteria.add(Restrictions.eq("removed", false));
		List<PublicInstitution> allActiveServant = publicInstitutionRepository.findByCriteria(detachedCriteria);
		return allActiveServant;
	}



	/** (non Javadoc) 
	 * @see com.wondersgroup.human.service.pubinst.PublicInstitutionService#getPage(org.hibernate.criterion.DetachedCriteria, java.lang.Integer, java.lang.Integer, java.lang.String) 
	 */
	@Override
	public Page<PublicInstitutionVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit, String ids) {
		Page<PublicInstitution> servantPage = publicInstitutionRepository.findByCriteria(detachedCriteria, pageNo, limit);
		List<PublicInstitutionVO> voList = new ArrayList<>();
//		String[] id = ids.split(",");
		for (PublicInstitution s : servantPage.getResult()) {
			PublicInstitutionVO vo = new PublicInstitutionVO(s);
//			for(String ant:id){
//				if(ant.equals(s.getId())){
//					vo.setLAY_CHECKED(true);
//					break;
//				}
//			}
			voList.add(vo);
		}
		Page<PublicInstitutionVO> page = new Page<>(servantPage.getStart(), servantPage.getCurrentPageSize(),
				servantPage.getTotalSize(), servantPage.getPageSize(), voList);
		return page;
	}

	

}
