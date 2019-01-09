/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenKLBOutBatchMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 跨类别转任 批量转出情况信息 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月6日 下午3:00:34 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月6日 下午3:00:34 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.config.ReadProperties;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Experience;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.Material;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOut;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOutBatch;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerOutRecordEvent;
import com.wondersgroup.human.event.record.ServantHumamKeepRecordEvent;
import com.wondersgroup.human.service.ofc.ExperienceService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.MaterialService;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBOutBatchService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBOutService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenKLBOutBatchVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/** 
 * @ClassName: ZhuanRenKLBOutBatchMgrServiceImpl 
 * @Description: 跨类别转任 批量转出情况信息 服务实现类
 * @author: tzy
 * @date: 2018年8月6日 下午3:00:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ZhuanRenKLBOutBatchServiceImpl extends GenericServiceImpl<ZhuanRenKLBOutBatch> implements ZhuanRenKLBOutBatchService{
	@Autowired
	private ZhuanRenKLBOutService zhuanRenKLBOutService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private OutMgrService outMgrService;
	@Autowired
	private FormationControlService formationControlService;
	@Autowired
	private ExperienceService experienceService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private UserService userService;
	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private JobLevelService jobLevelService;
	@Autowired
	private MessageSource messageSource;
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
	public Page<ZhuanRenKLBOutBatchVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<ZhuanRenKLBOutBatch> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ZhuanRenKLBOutBatchVO> voList = new ArrayList<>();
		for(ZhuanRenKLBOutBatch s:temppage.getResult()){
			ZhuanRenKLBOutBatchVO vo = new ZhuanRenKLBOutBatchVO(s);
			voList.add(vo);
		}
		Page<ZhuanRenKLBOutBatchVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: remove 
	 * @Description: 根据批量表ID删除 批次数据并删除批次下人员数据
	 * @param id
	 * @return: void
	 */
	public void remove(String id){
		DetachedCriteria criteria = DetachedCriteria.forClass(ZhuanRenKLBOut.class);
		criteria.add(Restrictions.eq("zhuanRenKLBOutBatch.id", id));
		criteria.add(Restrictions.eq("removed", false));
		List<ZhuanRenKLBOut> list = zhuanRenKLBOutService.findByCriteria(criteria);
		for(ZhuanRenKLBOut t : list){
			zhuanRenKLBOutService.delete(t);
		}
		ZhuanRenKLBOutBatch z = get(id);
		delete(z);
	}
	
	/**
	 * @Title: savePeople 
	 * @Description: 添加转任人员信息
	 * @param id
	 * @param servantIds	人员ID，逗号分隔
	 * @return: void
	 */
	public void savePeople(ZhuanRenKLBOutBatch z,String servantIds){
		if(StringUtils.isBlank(z.getId())){//如果没有保存批次信息，先保存
			z.setStatus(ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_STATE);//流程状态，待提交
			save(z);
		}else{
			z = get(z.getId());
		}
		String[] idArr = servantIds.split(",");
		for(String i : idArr){
			Servant servant = servantService.get(i);
			ZhuanRenKLBOut zz = new ZhuanRenKLBOut();
			JobLevel tempJ = jobLevelService.getJobLevelByServantId(servant.getId());//查询当前人员的现行职级
			zz.setJobLevelName(tempJ.getName());
			if(CommonConst.YES==tempJ.getIsLeader()){
				zz.setIsLeaderView("领导");
			}else{
				zz.setIsLeaderView("非领导");
			}
			zz.setRealJobLevelCode(tempJ.getRealJobLevelCode());
			zz.setRealLeader(tempJ.getRealLeader());
			zz.setZhuanRenKLBOutBatch(z);//关联批次信息
			zz.setServant(servant);//关联人员信息
			zhuanRenKLBOutService.save(zz);
		}
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 审批转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ZhuanRenKLBOutBatch temp,String opinion,String r){

		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		AppNode appNode = (AppNode)SecurityUtils.getSession().getAttribute("appNode");
		if(StringUtils.isBlank(temp.getId())){
			saveOrUpdate(temp);//保存业务数据
		}
		
		if(ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_STATE==temp.getStatus()){//提交环节，锁编
			executeLockOutFormationAndPost(temp);
		}
		if(ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_TRIAL==temp.getStatus()){//转出单位确认环节，校验数据是否填写
			//1.校验数据是否填写
			DetachedCriteria criteria = DetachedCriteria.forClass(ZhuanRenKLBOut.class);
			criteria.add(Restrictions.eq("zhuanRenKLBOutBatch.id", temp.getId()));
			criteria.add(Restrictions.eq("removed", false));
			List<ZhuanRenKLBOut> list = zhuanRenKLBOutService.findByCriteria(criteria);//该批次下所有转任人员信息，判断各个职级人数是否和批次中人数一致
			for(ZhuanRenKLBOut i : list){
				if(StringUtils.isBlank(i.getGotoUnitName())){
					throw new BusinessException("请先编辑所有人员的转任信息！");
				}
			}
		}
		
		FlowRecord flow;
		if(ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_STATE==temp.getStatus()&&temp.getFlowRecord()==null){//提交环节，生成流程数据
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());//流程业务所在系统
			flow.setBusId(temp.getId());//流程业务ID
			flow.setBusName("跨类别转出");//流程业务名称
			flow.setBusType("ZhuanRenKLBOutMgr");//流程业务类型
			flow.setTargetOrganNode(userOrg);//流程业务目标组织
			flow.setTargetSecurityUser(user);;//流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_ZHUANCHU_KLB_STATE",temp.getSourceOrgan());//初始节点
		}else{
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_STATE==temp.getStatus()){
				flow.setResult(FlowRecord.PASS);
				flow = workflowService.completeFlowRecordByAppoint(flow,temp.getSourceOrgan());//提交下个节点
			}else{
				if(ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_TRIAL_4==temp.getStatus()){
					createIntroduction(temp.getId());
				}
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}
			
		}
		if(flow==null){
			temp.setFlowRecord(null);//修改当前业务的流程节点
			if(FlowRecord.PASS.equals(r)){//流程最后环节
				updateServant(temp);
				executeFinish(temp);
				temp.setStatus(ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_FINISH);
			}else if(FlowRecord.STOP.equals(r)){//流程被中止
				executeUnLockFormationAndPost(temp);//返编
				
				temp.setStatus(FlowRecord.BUS_STOP);
				
				String title = messageSource.getMessage("stopFlowTitle", new Object[]{"跨类别转出"}, Locale.CHINESE);
				String content = messageSource.getMessage("stopFlowContent", new Object[]{"跨类别转出"}, Locale.CHINESE);
				AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, "","跨类别转出")));
			}
			
		}else{
			temp.setStatus(ZhuanRenKLBOutBatch.power.get(flow.getOperationCode()));//实际有权限的操作节点
			temp.setFlowRecord(flow);//修改当前业务的流程节点
			
			if(ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_STATE==temp.getStatus()&&FlowRecord.NOPASS.equals(r)){//如果流程退回到最初节点，解锁编制，每次提交重新锁编
				executeUnLockFormationAndPost(temp);
			}
		}
		update(temp);
	}
	/**
	 * @Title: executeFinish 
	 * @Description: 流程结束，真实占编，解锁未调出编制
	 * @param temp
	 * @return: void
	 */
	public void executeFinish(ZhuanRenKLBOutBatch temp){
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBOut.class);
		d.add(Restrictions.eq("zhuanRenKLBOutBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenKLBOut> intoList = zhuanRenKLBOutService.findByCriteria(d);
		
		formationControlService.executeUnlockOutFormationNum(temp.getSourceOrgan().getId(),intoList.size());//1.解锁调出单位未调出编制
		formationControlService.executeOutFormation(temp.getSourceOrgan().getId(),intoList.size());//2.减少调出单位实际编制数
		
		for(ZhuanRenKLBOut z : intoList){
			formationControlService.executeUnlockPostOutNum(temp.getSourceOrgan().getId(),z.getRealJobLevelCode(), z.getRealLeader());//1.解锁职级调出数
			formationControlService.executeOutPost(temp.getSourceOrgan().getId(),z.getRealJobLevelCode(), z.getRealLeader());//2.减少调出单位实际职级数
		}
	}
	/**
	 * @Title: executeUnLockFormationAndPost 
	 * @Description: 返编 未调入/未调出
	 * @param temp
	 * @return: void
	 */
	public void executeUnLockFormationAndPost(ZhuanRenKLBOutBatch temp){
		List<JudgePostResult> list = new ArrayList<>();//转入单位真实职级
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBOut.class);
		d.add(Restrictions.eq("zhuanRenKLBOutBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenKLBOut> intoList = zhuanRenKLBOutService.findByCriteria(d);
		for(ZhuanRenKLBOut z : intoList){
			JudgePostResult j = new JudgePostResult(z.getRealJobLevelCode(), z.getRealLeader(),1);
			list.add(j);
		}
		formationControlService.executeUnLockIntoFormationAndPost(temp.getSourceOrgan().getId(), list);//返编未调出
	}
	/**
	 * @Title: executeLockOutFormationAndPost 
	 * @Description: 锁未调出编制
	 * @param temp
	 * @return: List<JudgePostResult> 原单位职级信息
	 */
	public void executeLockOutFormationAndPost(ZhuanRenKLBOutBatch temp){
		List<JudgePostResult> list = new ArrayList<>();
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBOut.class);
		d.add(Restrictions.eq("zhuanRenKLBOutBatch.id", temp.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenKLBOut> intoList = zhuanRenKLBOutService.findByCriteria(d);
		for(ZhuanRenKLBOut z : intoList){
				if(StringUtils.isBlank(z.getRealJobLevelCode())){
					throw new BusinessException("占编职级信息异常，请联系管理员！");
				}
				JudgePostResult j = new JudgePostResult(z.getRealJobLevelCode(), z.getRealLeader(),1);
				list.add(j);
		}
		formationControlService.executeLockOutFormationAndPost(temp.getSourceOrgan().getId(), list);//锁未调出
	}
	/**
	 * @Title: updateServant 
	 * @Description: 更新人员信息
	 * @return: void
	 */
	private void updateServant(ZhuanRenKLBOutBatch z){
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBOut.class);
		d.add(Restrictions.eq("zhuanRenKLBOutBatch.id", z.getId()));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenKLBOut> list = zhuanRenKLBOutService.findByCriteria(d);
		for(ZhuanRenKLBOut temp : list){
			//修改原数据状态为调出
			Servant oldServant = servantService.get(temp.getServant().getId());
			CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
			oldServant.setIsOnHold(outer);//调出状态
			servantService.update(oldServant);
			//转出子集信息
			OutMgr out = new OutMgr();
			out.setServant(oldServant);//人员基本信息
			out.setCategory(temp.getCategory());//调出本单位类别
			out.setReasonType(temp.getReasonType());//调动原因
			out.setOutDate(temp.getOutDate());//调出本单位日期
			out.setGotoUnitName(temp.getGotoUnitName());//调往单位名称
			out.setProposeType(temp.getProposeType());//提出调动类型
			out.setRemark(temp.getRemark());//调出备注
			outMgrService.save(out);
			//更新简历
			Experience e = experienceService.getLatestExperienceByServantId(oldServant.getId());
			e.setEndDate(new Date());
			experienceService.update(e);
			//进出管理
			ManagerRecordDTO dto = new ManagerRecordDTO(temp.getServant().getId(),ManagerRecord.HUMAN_TLZC);
			ManagerOutRecordEvent event = new ManagerOutRecordEvent(dto);
			EventManager.send(event);
			//备案管理
			HumankeepRecordDTO dto2 = new HumankeepRecordDTO(temp.getServant().getId(),HumanKeepRecord.KEEP_TLZC);
			ServantHumamKeepRecordEvent event2 = new ServantHumamKeepRecordEvent(dto2);	
			EventManager.send(event2);
		}
	}
	/**
	 * @Title: createIntroduction 
	 * @Description: 生成该批次的介绍信  每个人员一个介绍信，一个编号
	 * @param id		批次id
	 * @return: void
	 */
	private void createIntroduction(String id){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String templet = savePath+"\\static\\templates\\introduce.xls";//模板路径
		String folder = ReadProperties.getInstance().FTP_DIR_NAME_MATERIAL;//ftp文件路径
		
		Map<String,Object> params = new HashMap<>();//所有参数
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ZhuanRenKLBOutBatch batch = get(id);
		
		DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBOut.class);
		d.add(Restrictions.eq("zhuanRenKLBOutBatch.id", id));
		d.add(Restrictions.eq("removed", false));
		List<ZhuanRenKLBOut> list = zhuanRenKLBOutService.findByCriteria(d);//批次下所有转任人员信息
		params.put("sumNumber", "壹");//转任人数
		params.put("now", sdf.format(new Date()));//打印介绍信时间
		params.put("busType", "跨类别转出");//转移原因
		params.put("sourceOrgan", batch.getSourceOrgan().getName());//源单位名称
		//当前操作人
		params.put("userName", SecurityUtils.getPrincipal().getName());
		for(int i =0;i<list.size();i++){
			ZhuanRenKLBOut into = list.get(i);
			Material m = new Material();//材料信息 一定要生成
			try {
				m.setBusId(into.getId());
				m.setBusType("ZhuanRenKLBOut");
				m.setBusName("跨类别转出");
				
				List<String[]> dataList = new ArrayList<>();//所有人员信息
				params.put("enterTheUnitDate", "");//报道日期
				String[] p = new String[6];
				params.put("name", into.getServant().getName());
				params.put("targetOrgan", into.getGotoUnitName());//目标单位名称
				p[0] = into.getServant().getName();//转任人员名称
				p[1] = into.getServant().getSex()==null?"":into.getServant().getSex().getName();//调任人员性别
				p[2] = batch.getSourceOrgan().getName();//源单位名称
				p[4] = into.getServant().getNowPostName()==null?"":into.getServant().getNowPostName();//原职务
				p[5] = into.getServant().getNowJobLevel()==null?"":into.getServant().getNowJobLevel().getName();//原级别
				dataList.add(p);
				params.put("listData", dataList);//list数据需要用list标签
				
				Map<String,String> number = ofcFlowNumberService.executeNumber("ZhuanRenKLB", into.getId());//介绍信编号
				params.put("number", number.get("year")+"字第"+number.get("number"));//介绍信编号
				//编号转换为大写
				String cnYear = Number2CN.convert(number.get("year"));
				String cnNumber = Number2CN.convert(number.get("number"));
				params.put("cnNumber", cnYear+"字第"+cnNumber+"号");//介绍信编号大写
				String uuid = UUID.randomUUID().toString();
				String path = savePath+"\\static\\templates\\"+uuid+".xls";//生成excel文件路径，临时存放，上传到ftp服务器之后会删除
				ExcelUtilsPOI.replaceModel(params, templet,path, 2,null);//替换模板数据 生成excel到tomcat服务器
				
				//保存文件到ftp服务器
				String fileName = uuid+".xls";
				
				File file = new File(path);
				if(FtpTool.ftpUpload(folder, file, fileName)){//文件成功上传到ftp，删除本地文件并保存材料表数据
					m.setFlowNumber(number.get("number"));
					m.setName(dataList.get(0)[0]+"转任介绍信"+".xls");
					m.setFtpFilefolder(folder);
					m.setFtpFileName(fileName);
					file.delete();//删除文件
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			materialService.save(m);
		}
	}
}
