/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenKLBIntoMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 跨类别转任 调入情况信息 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年9月20日 上午11:00:49 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月20日 上午11:00:49 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.IntoMgr;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.EventDegree;
import com.wondersgroup.human.bo.ofcflow.EventEducation;
import com.wondersgroup.human.bo.ofcflow.EventExperience;
import com.wondersgroup.human.bo.ofcflow.EventFamily;
import com.wondersgroup.human.bo.ofcflow.EventPost;
import com.wondersgroup.human.bo.ofcflow.EventRewardAndPunish;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoMgr;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOutMgr;
import com.wondersgroup.human.repository.ofcflow.ZhuanRenTLBIntoMgrRepository;
import com.wondersgroup.human.service.ofc.IntoMgrService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.EventDegreeService;
import com.wondersgroup.human.service.ofcflow.EventEducationService;
import com.wondersgroup.human.service.ofcflow.EventExperienceService;
import com.wondersgroup.human.service.ofcflow.EventFamilyService;
import com.wondersgroup.human.service.ofcflow.EventPostService;
import com.wondersgroup.human.service.ofcflow.EventRewardAndPunishService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBIntoMgrService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBOutMgrService;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenKLBIntoMgrVO;

/** 
 * @ClassName: ZhuanRenKLBIntoMgrServiceImpl 
 * @Description: 跨类别转任 转入情况信息 服务实现类
 * @author: tzy
 * @date: 2018年9月20日 上午11:00:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ZhuanRenKLBIntoMgrServiceImpl extends GenericServiceImpl<ZhuanRenKLBIntoMgr> implements ZhuanRenKLBIntoMgrService{
	
	@Autowired
	private EventDegreeService eventDegreeService;
	@Autowired
	private EventEducationService eventEducationService;
	@Autowired
	private EventExperienceService eventExperienceService;
	@Autowired
	private EventFamilyService eventFamilyService;
	@Autowired
	private EventPostService eventPostService;
	@Autowired
	private EventRewardAndPunishService eventRewardAndPunishService;
	@Autowired
	private ZhuanRenKLBOutMgrService zhuanRenKLBOutMgrService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private IntoMgrService intoMgrService;
	@Autowired
	private OutMgrService outMgrService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private ZhuanRenTLBIntoMgrRepository repository;
	@Autowired
	private PostService postService;
	
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ZhuanRenKLBIntoMgrVO>
	 */
	public Page<ZhuanRenKLBIntoMgrVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<ZhuanRenKLBIntoMgr> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ZhuanRenKLBIntoMgrVO> voList = new ArrayList<>();
		for(ZhuanRenKLBIntoMgr s:temppage.getResult()){
			ZhuanRenKLBIntoMgrVO vo = new ZhuanRenKLBIntoMgrVO(s);
			voList.add(vo);
		}
		Page<ZhuanRenKLBIntoMgrVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: remove 
	 * @Description: 删除 转任调入信息并删除根据该人员姓名和身份证号删除子集
	 * @return: void
	 */
	public void remove(String id){
		ZhuanRenKLBIntoMgr z = load(id);
		
		List<Predicate> filter = new ArrayList<>();//查询条件
		filter.add(new Predicate("personName", Operator.EQ,z.getName(), ""));
		filter.add(new Predicate("cardNo", Operator.EQ,z.getCardNo(), ""));
		
		List<EventDegree> s = eventDegreeService.findByFilter(filter);//学位子集
		for(EventDegree e:s){
			eventDegreeService.delete(e);
		}
		List<EventEducation> u = eventEducationService.findByFilter(filter);//学历子集
		for(EventEducation e:u){
			eventEducationService.delete(e);
		}
		List<EventExperience> p = eventExperienceService.findByFilter(filter);//简历子集
		for(EventExperience e:p){
			eventExperienceService.delete(e);
		}
		List<EventFamily> f = eventFamilyService.findByFilter(filter);//家庭成员子集
		for(EventFamily e:f){
			eventFamilyService.delete(e);
		}
		List<EventPost> o = eventPostService.findByFilter(filter);//职务子集
		for(EventPost e:o){
			eventPostService.delete(e);
		}
		List<EventRewardAndPunish> a = eventRewardAndPunishService.findByFilter(filter);//奖惩子集
		for(EventRewardAndPunish e:a){
			eventRewardAndPunishService.delete(e);
		}
		delete(z);
	}
	
	/**
	 * @Title: createServant
	 * @Description: 更新人员基本信息和转入转出子集信息
	 * @param id
	 * @return: void
	 */
	public void createServant(ZhuanRenKLBIntoMgr z){
		Servant servant = z.getServant();
		if(servant==null){//外区转任，需要生成人员基本信息和转入转出、职务子集信息
			servant = new Servant();//人员基本信息
			servant.setName(z.getName());//姓名
			servant.setCardNo(z.getCardNo());//身份证
			servant.setSex(z.getSex());//性别
			servant.setBirthDate(z.getBirthDate());//出生日期
			servant.setNativePlaceName(z.getNativePlaceName());//籍贯名称
			servant.setNativePlace(z.getNativePlace());//籍贯
			servant.setBirthPlaceName(z.getBirthPlaceName());//出生地名称
			servant.setBirthPlace(z.getBirthPlace());//出生地
			servant.setNation(z.getNation());//民族
			servant.setPolitics(z.getPolitics());//政治面貌
			servant.setHealth(z.getHealth());//健康状况
			servant.setPersonType(z.getPersonType());//人员类别
			
			servant.setDepartId(z.getTargetOrgan().getId());//单位id
			servant.setDepartName(z.getTargetOrgan().getName());//单位名称
			servant.setDepartCode(z.getTargetOrgan().getCode());//单位代码
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
			servant.setIsOnHold(isOnHold);//在职状态
			servant.setNowPostName(z.getPostName());//职务名称
			servant.setNowPostCode(z.getPostCode());//职务代码
			servant.setNowPostAttribute(z.getAttribute());//职务属性
			servantService.save(servant);
		}else{//如果是本区转任，更新该人员原数据的在职状态为调出，并生成新数据和转入转出、职务子集信息
			Map<String,String> params = new HashMap<>();//存储过程入参
			params.put("SERVANTID", servant.getId());
			List<String> backList = new ArrayList<>();//返回参数名
			backList.add("SERVANTNEWID");
			backList.add("prm_AppCode");
			backList.add("prm_ErrorMsg");
			ProcedureOutputs out = repository.executeStoreProcedure("COPY_SERVANT", params,backList);//调用复制人员存储过程，返回新生成人员id
			String servantNewId = (String)out.getOutputParameterValue(backList.get(0));//新生成人员数据id
			//生成新数据
			servant= servantService.get(servantNewId);
			servant.setDepartId(z.getTargetOrgan().getId());//单位id
			servant.setDepartName(z.getTargetOrgan().getName());//单位名称
			servant.setDepartCode(z.getTargetOrgan().getCode());//单位代码
			servant.setPersonType(z.getPersonType());//人员类别
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
			servant.setIsOnHold(isOnHold);//在职状态
			servant.setNowPostName(z.getPostName());//职务名称
			servant.setNowPostCode(z.getPostCode());//职务代码
			servant.setNowPostAttribute(z.getAttribute());//职务属性
			servantService.update(servant);
			//修改原数据状态为调出
			Servant oldServant = servantService.get(z.getServant().getId());
			CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
			oldServant.setIsOnHold(outer);//调出状态
			servantService.update(oldServant);
		}
		//职务子集
		Post post = new Post();
		post.setServant(servant);//人员信息
		CodeInfo YES = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);// 是 CODE
		post.setNowPostSign(YES);//现任职务
		CodeInfo NO = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);//否 CODE
		post.setHighestPostSign(NO);//最高职务
		post.setTenureName(z.getTargetOrgan().getName());//任职机构名称
		post.setTenureCode(z.getTargetOrgan().getCode());//任职机构代码
		post.setTakeDate(new Date());
		post.setAttribute(z.getAttribute());//职务属性
		post.setPostName(z.getPostName());//职务名称
		post.setPostCode(z.getPostCode());//职务代码
		postService.saveOrUpdate(post);
		
		IntoMgr into = new IntoMgr();//转入子集信息
		into.setServant(servant);//人员信息
		into.setEnterTheUnitChangeType(z.getEnterTheUnitChangeType());//进入本单位变动类别
		into.setEnterReason(z.getEnterReason());//进入本单位原因
		into.setEnterTheUnitDate(z.getEnterTheUnitDate());//进入本单位日期
		into.setFormerUnitName(z.getFormerUnitName());//进入本单位前工作单位名称
		into.setFormerUnitJobName(z.getFormerUnitJobName());//在原单位职务名称
		into.setFormerUnitRank(z.getFormerUnitRank());//在原单位职务级别
		into.setIntoBasicWorkTime(z.getIntoBasicWorkTime());//进入本单位时基层工作经历时间（月）
		intoMgrService.save(into);
		
		if(ZhuanRenKLBIntoMgr.AREA_THIS.equals(z.getAreaType())){//本区转任才新增一条转出子集
			OutMgr out = new OutMgr();//转出子集信息
			ZhuanRenKLBOutMgr o = zhuanRenKLBOutMgrService.get(z.getZhuanRenKLBOutMgr().getId());
			out.setServant(servant);//人员基本信息
			out.setCategory(o.getCategory());//调出本单位类别
			out.setReasonType(o.getReasonType());//调动原因
			out.setOutDate(o.getOutDate());//调出本单位日期
			out.setGotoUnitName(o.getGotoUnitName());//调往单位名称
			out.setProposeType(o.getProposeType());//提出调动类型
			out.setRemark(o.getRemark());//调出备注
			outMgrService.save(out);
		}
	}
	/**
	 * @Title: createOut 
	 * @Description: 发起跨类别转任，转出流程
	 * @param id
	 * @return: void
	 */
	public ZhuanRenKLBOutMgr createOut(ZhuanRenKLBIntoMgr z){
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBOutMgr.class);
		d.add(Restrictions.eq("zhuanRenKLBIntoMgr", z));
		List<ZhuanRenKLBOutMgr> list = zhuanRenKLBOutMgrService.findByCriteria(d);
		if(list==null||list.size()==0){
			ZhuanRenKLBOutMgr out = new ZhuanRenKLBOutMgr();
			out.setZhuanRenKLBIntoMgr(z);//关联转入数据
			out.setServant(z.getServant());//人员信息
			out.setOutDate(z.getEnterTheUnitDate());//调出时间
			out.setGotoUnitName(z.getTargetOrgan().getName());//调往单位名称
			zhuanRenKLBOutMgrService.save(out);
			return out;
		}
		return null;
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 审批本区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ZhuanRenKLBIntoMgr temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("本区人员跨类别转入"+userOrg.getName());//流程业务名称
			flow.setBusType("ZhuanRenKLBIntoMgr_THIS");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_ZHUANREN_KLB_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_TRIAL_4==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事审核通过之后，生成转出单位待办信息，生成转出数据
				ZhuanRenKLBOutMgr out = createOut(temp);//生成转出信息
				if(out!=null){
					temp.setZhuanRenKLBOutMgr(out);
				}
				flow = workflowService.completeFlowRecordByAppoint(flow,temp.getSourceOrgan());
			}else if(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_AGREE==temp.getStatus()&&FlowRecord.PASS.equals(r)){//转出单位同意，保存转出信息
				ZhuanRenKLBOutMgr outTemp = temp.getZhuanRenKLBOutMgr();
				ZhuanRenKLBOutMgr out = zhuanRenKLBOutMgrService.load(outTemp.getId());
				BeanUtils.copyPropertiesIgnoreNull(outTemp, out);
				DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
				zhuanRenKLBOutMgrService.update(out);
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}else if(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事主管部门打印电子介绍信之后，生成转入单位待办，更新转入转出子集信息
				createServant(temp);//更新人员基本信息
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}else{
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}
		}
		if(flow==null&&FlowRecord.PASS.equals(r)){//流程最后环节
			temp.setStatus(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_FINISH);
			temp.setFlowRecord(null);//修改当前业务的流程节点
		}else{
			temp.setStatus(ZhuanRenKLBIntoMgr.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
	/**
	 * @Title: saveFlowOuter 
	 * @Description: 审批外区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlowOuter(ZhuanRenKLBIntoMgr temp,String opinion,String r){
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		FlowRecord flow;
		if(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，先生成流程数据
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("外区人员转入"+userOrg.getName());//流程业务名称
			flow.setBusType("ZhuanRenKLBIntoMgr_OUTER");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_ZHUANREN_KLB_OUTER_STATE");//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){//区人事主管部门打印电子介绍信之后，生成转入单位待办，更新转入转出子集信息
				createServant(temp);//更新人员基本信息
			}
			flow = workflowService.completeWorkItem(flow);//提交下个节点
		}
		if(flow==null&&FlowRecord.PASS.equals(r)){//流程最后环节
			temp.setStatus(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_FINISH);
			temp.setFlowRecord(null);//修改当前业务的流程节点
		}else{
			temp.setStatus(ZhuanRenKLBIntoMgr.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
		}
		update(temp);
	}
}