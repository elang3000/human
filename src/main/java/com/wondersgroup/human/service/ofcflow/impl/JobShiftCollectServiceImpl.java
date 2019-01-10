
package com.wondersgroup.human.service.ofcflow.impl;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.contant.FlowBusTypeConstant;
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
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.*;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.bo.ofcflow.JobShiftB;
import com.wondersgroup.human.bo.ofcflow.JobShiftCollect;
import com.wondersgroup.human.bo.ofcflow.Material;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.controller.ofcflow.JobShiftBatchController;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerManageRecordEvent;
import com.wondersgroup.human.service.ofc.JobChangeService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofcflow.JobShiftBService;
import com.wondersgroup.human.service.ofcflow.JobShiftCollectService;
import com.wondersgroup.human.service.ofcflow.MaterialService;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.JobShiftCollectVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class JobShiftCollectServiceImpl extends GenericServiceImpl<JobShiftCollect>
        implements JobShiftCollectService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JobShiftBService jobShiftBService;

	@Autowired
	private FormationControlService formationControlService;

	@Autowired
	private JobLevelService jobLevelService;

	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private JobChangeService jobChangeService;

	@Autowired
	private DictableService dictableService;

	@Autowired
	private PostService postService;

	@Override
	public Page<JobShiftCollectVO> findbyHQLforVO(String hql, List<QueryParameter> listqueryparameter, Integer pageNo,
	        Integer pagesize) {
		Page<JobShiftCollect> temppage=this.findByHQL(hql, listqueryparameter, pageNo, pagesize);
		List<JobShiftCollectVO> voList = new ArrayList<>();
		for(JobShiftCollect s:temppage.getResult()){
			JobShiftCollectVO vo = new JobShiftCollectVO();
			try {
				BeanUtils.copyProperties(vo, s);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// FIXME Auto-generated catch block
				e.printStackTrace();
			}
			voList.add(vo);
		}
		Page<JobShiftCollectVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
		
	}

	@Override
	public void updatePromoteFlowB(JobShiftCollect jobShiftCollect, String opinion, String result) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(jobShiftCollect.getId())) {
			save(jobShiftCollect);// 保存业务数据
		}
		FlowRecord flow;
		int flowStep=0;
		if (jobShiftCollect.getFlowRecord() == null) {// 提交环节，先生成流程数据
			// 设置业务流程发起组织部门
			jobShiftCollect.setSourceOrganNode(userOrg);
			jobShiftCollect.setStatus(JobShiftCollect.JOBSHIFT_SUBMIT);//流程状态，待提交
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(jobShiftCollect.getId());// 流程业务ID
			flow.setBusName("职务变动晋升");// 流程业务名称
			flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_PROMOTEB);// 流程业务类型
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);// 流程业务目标人员
			flow = workflowService.createFlowRecord(flow, JobShiftCollect.JOBSHIFTB_PROMOTE_STEP1);// 初始节点
		}else {
			flowStep=JobShiftCollect.allSteps.indexOf(jobShiftCollect.getFlowRecord().getOperationCode());
            //假如是第八步驳回到第七步
			if (jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP8)&&FlowRecord.NOPASS.equals(result)) {
                //操作编制  放出锁定入编制和锁定出编制
                executeUnLockFormationAndPost(jobShiftCollect);//返编
			}
			if (jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP7)) {
				//校验编制 并且锁定编制
				this.checkAndLockFormation(jobShiftCollect);
			}

			flow = jobShiftCollect.getFlowRecord();
			jobShiftCollect.setStatus(jobShiftCollect.getStatus() + 1);
			flow.setOpinion(opinion);
			flow.setResult(result);
			if (result.equals(FlowRecord.PASS)
			        && (jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP6)
			                || jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP11))) {
				flow = workflowService.completeFlowRecordByAppoint(flow, jobShiftCollect.getSourceOrganNode());
			} else {
				flow = workflowService.completeWorkItem(flow);// 提交下个节点
			}
		}
		
		if (null == flow&&FlowRecord.PASS.equals(result)) {

			//解锁编制
			executeFinish(jobShiftCollect);

			//获取职务变动列表
			List<JobShiftB> list = this.jobShiftBService.getShiftByCollectId(jobShiftCollect.getId());

			// 2 发送消息 =====================================================
			String title = messageSource.getMessage(JobShift.PROMOTE_TITLE, new Object[] {
					jobShiftCollect.getCollectName()
			}, Locale.CHINESE);
			String content = messageSource.getMessage(JobShift.PROMOTE_CONTENT, new Object[] {
					jobShiftCollect.getCollectName()
			}, Locale.CHINESE);
			//发送通知
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, jobShiftCollect.getCreater(), title, content, "")));

			for (JobShiftB jobShiftB : list) {
				Servant servant = jobShiftB.getServant();

				try {
					//3 流程结束,插入职务变动子集信息   ===================================================
					JobChange change = new JobChange();
					BeanUtils.copyProperties(change, jobShiftB);
					change.setFormerUnitCode(jobShiftCollect.getSourceOrganNode().getId());
					change.setFormerUnitName(jobShiftCollect.getSourceOrganNode().getAllName());
					change.setNewUnitCode(jobShiftCollect.getSourceOrganNode().getId());
					change.setNewUnitName(jobShiftCollect.getSourceOrganNode().getAllName());
					change.setNewPostName(jobShiftB.getNewPostName());
					jobChangeService.save(change);


					//4 将之前职务设置为不在任职务,加入新职务子集     ==================================================

					//任职状态,DM215 是 和 否
					CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");

					//任职状态,DM007 表示该人是否任职或不任职
					CodeInfo inOfficeCodeInfo = dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_TYPE_POST_STATUS);
					CodeInfo notInOfficeCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_POST_STATUS);

					Post prePost = jobShiftB.getPrePost();
					//在职状态为不在职
					prePost.setTenureStatus(notInOfficeCodeInfo);
					postService.update(prePost);


					//插入新post子集,将新职务设置为现任职务
					Post newPost=new Post();
					newPost.setServant(servant);
					newPost.setTenureName(servant.getDepartName());
					newPost.setAttribute(jobShiftB.getNewPostAttribute());
					newPost.setPostName(jobShiftB.getNewPostCode().getName());
					newPost.setPostCode(jobShiftB.getNewPostCode());
					newPost.setApprovalDate(new Date());
					//设置任职状态为在任
					newPost.setTenureStatus(inOfficeCodeInfo);
					//任职变动类别
					newPost.setTenureChange(jobShiftB.getPostTenureChange());
					postService.save(newPost);


					CodeInfo newJobLevel = jobShiftB.getNewJobLevel();


					//5 判断假如新职级不为空且不和老职级相同则生成新职级并保存到servant当中
					if(null!=newJobLevel&&newJobLevel.getId()!=servant.getNowJobLevel().getId()){
						JobLevel jobLevel = new JobLevel();
						jobLevel.setServant(servant);
						jobLevel.setName(jobShiftB.getNewJobLevel().getName());
						jobLevel.setCode(jobShiftB.getNewJobLevel());
						jobLevel.setApprovalDate(new Date());
						jobLevel.setCurrentIdentification(yesCodeInfo);
						jobLevel.setIsLeader(jobShiftB.getIsLeader());//是否领导
						jobLevel.setRealJobLevelCode(jobShiftB.getRealJobLevelCode());
						jobLevel.setRealLeader(jobShiftB.getRealLeader());//真实占编的是否领导值
						jobLevelService.save(jobLevel);
					}

					//7 增加进出管操作 =============================================
					ManagerRecordDTO dto = new ManagerRecordDTO(jobShiftB.getServant().getId(), ManagerRecord.HUMAN_ZWBD);
					ManagerManageRecordEvent event = new ManagerManageRecordEvent(dto);
					EventManager.send(event);

				}catch (Exception e){
					e.printStackTrace();
				}


			}






		}else if(null == flow&&FlowRecord.STOP.equals(result)){
			if(flowStep>6){
				//假如流程状态为终止 并且操作步骤大于第六步(第七步锁编) 操作编制  放出锁定入编制和锁定出编制
				executeUnLockFormationAndPost(jobShiftCollect);//返编
			}
            jobShiftCollect.setStatus(FlowRecord.BUS_STOP);
			//发送消息
            String title = messageSource.getMessage("stopFlowTitle", new Object[]{"职务变动"}, Locale.CHINESE);
            String content = messageSource.getMessage("stopFlowContent", new Object[]{"职务变动"}, Locale.CHINESE);
            AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, jobShiftCollect.getCreater(), title, content, "")));
		}
		jobShiftCollect.setFlowRecord(flow);
		this.update(jobShiftCollect);
		
	}


	@Override
	public void updateDemoteFlowB(JobShiftCollect jobShiftCollect, String opinion, String result) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(jobShiftCollect.getId())) {
			save(jobShiftCollect);// 保存业务数据
		}
		FlowRecord flow;
		int flowStep=0;
		if (jobShiftCollect.getFlowRecord() == null) {// 提交环节，先生成流程数据
			// 设置业务流程发起组织部门
			jobShiftCollect.setSourceOrganNode(userOrg);
			jobShiftCollect.setStatus(JobShiftCollect.JOBSHIFT_SUBMIT);//流程状态，待提交
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(jobShiftCollect.getId());// 流程业务ID
			flow.setBusName("职务变动降职");// 流程业务名称
			flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_DEMOTE);// 流程业务类型
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);// 流程业务目标人员
			flow = workflowService.createFlowRecord(flow, JobShiftCollect.JOBSHIFT_DEMOTE_STEP1);// 初始节点
			//校验编制 并且锁定编制
			this.checkAndLockFormation(jobShiftCollect);
		}else {
			flowStep=JobShiftCollect.allSteps.indexOf(jobShiftCollect.getFlowRecord().getOperationCode());
            //假如是第二步驳回到第一步
			if (jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFT_DEMOTE_STEP2)&&FlowRecord.NOPASS.equals(result)) {
                //操作编制  放出锁定入编制和锁定出编制
                executeUnLockFormationAndPost(jobShiftCollect);//返编
			}
			if (jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP1)) {
				//校验编制 并且锁定编制
				this.checkAndLockFormation(jobShiftCollect);
			}

			flow = jobShiftCollect.getFlowRecord();
			jobShiftCollect.setStatus(jobShiftCollect.getStatus() + 1);
			flow.setOpinion(opinion);
			flow.setResult(result);
			if (result.equals(FlowRecord.PASS)
			        && (jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP6)
			                || jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP11))) {
				flow = workflowService.completeFlowRecordByAppoint(flow, jobShiftCollect.getSourceOrganNode());
			} else {
				flow = workflowService.completeWorkItem(flow);// 提交下个节点
			}
		}

		if (null == flow&&FlowRecord.PASS.equals(result)) {

			//解锁编制
			executeFinish(jobShiftCollect);

			//获取职务变动列表
			List<JobShiftB> list = this.jobShiftBService.getShiftByCollectId(jobShiftCollect.getId());

			// 2 发送消息 =====================================================
			String title = messageSource.getMessage(JobShift.PROMOTE_TITLE, new Object[] {
					jobShiftCollect.getCollectName()
			}, Locale.CHINESE);
			String content = messageSource.getMessage(JobShift.PROMOTE_CONTENT, new Object[] {
					jobShiftCollect.getCollectName()
			}, Locale.CHINESE);
			//发送通知
			AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, jobShiftCollect.getCreater(), title, content, "")));

			for (JobShiftB jobShiftB : list) {
				Servant servant = jobShiftB.getServant();

				try {
					//3 流程结束,插入职务变动子集信息   ===================================================
					JobChange change = new JobChange();
					BeanUtils.copyProperties(change, jobShiftB);
					change.setFormerUnitCode(jobShiftCollect.getSourceOrganNode().getId());
					change.setFormerUnitName(jobShiftCollect.getSourceOrganNode().getAllName());
					change.setNewUnitCode(jobShiftCollect.getSourceOrganNode().getId());
					change.setNewUnitName(jobShiftCollect.getSourceOrganNode().getAllName());
					change.setNewPostName(jobShiftB.getNewPostName());
					jobChangeService.save(change);


					//4 将之前职务设置为不在任职务,加入新职务子集     ==================================================

					//任职状态,DM215 是 和 否
					CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");

					//任职状态,DM007 表示该人是否任职或不任职
					CodeInfo inOfficeCodeInfo = dictableService.getCodeInfoByCode("2", DictTypeCodeContant.CODE_TYPE_POST_STATUS);
					CodeInfo notInOfficeCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_POST_STATUS);

					Post prePost = jobShiftB.getPrePost();
					//在职状态为不在职
					prePost.setTenureStatus(notInOfficeCodeInfo);
					postService.update(prePost);


					//插入新post子集,将新职务设置为现任职务
					Post newPost=new Post();
					newPost.setServant(servant);
					newPost.setTenureName(servant.getDepartName());
					newPost.setAttribute(jobShiftB.getNewPostAttribute());
					newPost.setPostName(jobShiftB.getNewPostCode().getName());
					newPost.setPostCode(jobShiftB.getNewPostCode());
					newPost.setApprovalDate(new Date());
					//设置任职状态为在任
					newPost.setTenureStatus(inOfficeCodeInfo);
					//任职变动类别
					newPost.setTenureChange(jobShiftB.getPostTenureChange());
					postService.save(newPost);


					CodeInfo newJobLevel = jobShiftB.getNewJobLevel();


					//5 判断假如新职级不为空且不和老职级相同则生成新职级并保存到servant当中
					if(null!=newJobLevel&&newJobLevel.getId()!=servant.getNowJobLevel().getId()){
						JobLevel jobLevel = new JobLevel();
						jobLevel.setServant(servant);
						jobLevel.setName(jobShiftB.getNewJobLevel().getName());
						jobLevel.setCode(jobShiftB.getNewJobLevel());
						jobLevel.setApprovalDate(new Date());
						jobLevel.setCurrentIdentification(yesCodeInfo);
						jobLevel.setIsLeader(jobShiftB.getIsLeader());//是否领导
						jobLevel.setRealJobLevelCode(jobShiftB.getRealJobLevelCode());
						jobLevel.setRealLeader(jobShiftB.getRealLeader());//真实占编的是否领导值
						jobLevelService.save(jobLevel);
					}

					//7 增加进出管操作 =============================================
					ManagerRecordDTO dto = new ManagerRecordDTO(jobShiftB.getServant().getId(), ManagerRecord.HUMAN_ZWBD);
					ManagerManageRecordEvent event = new ManagerManageRecordEvent(dto);
					EventManager.send(event);

				}catch (Exception e){
					e.printStackTrace();
				}


			}

		}else if(null == flow&&FlowRecord.STOP.equals(result)){
			//假如流程状态为终止 并且操作步骤大于第六步(第七步锁编) 操作编制  放出锁定入编制和锁定出编制
			executeUnLockFormationAndPost(jobShiftCollect);//返编
            jobShiftCollect.setStatus(FlowRecord.BUS_STOP);
			//发送消息
            String title = messageSource.getMessage("stopFlowTitle", new Object[]{"职务变动"}, Locale.CHINESE);
            String content = messageSource.getMessage("stopFlowContent", new Object[]{"职务变动"}, Locale.CHINESE);
            AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, jobShiftCollect.getCreater(), title, content, "")));
		}
		jobShiftCollect.setFlowRecord(flow);
		this.update(jobShiftCollect);

	}



	@Override
	public void checkAndLockFormation(JobShiftCollect jobShiftCollect) {
		//获取所有表单上报的人员
		List<JobShiftB> shiftBList = this.jobShiftBService.getShiftByCollectId(jobShiftCollect.getId());

		//1.校验转任人数是否正确
		checkNumber(jobShiftCollect);
		//2.校验编控，锁编，回写真实占编数据
		executeValidateAndLockFormation(jobShiftCollect,shiftBList);


		List<JudgePostResult> list = new ArrayList<>();
		for (int i = 0; i < shiftBList.size(); i++) {
			JobLevel jobLevel = jobLevelService.getJobLevelByServantId(shiftBList.get(i).getServant().getId());
			list.add(new JudgePostResult(jobLevel.getRealJobLevelCode(),jobLevel.getRealLeader(),1));
		}
		//3.锁调出单位编制
		formationControlService.executeLockOutFormationAndPost(jobShiftCollect.getSourceOrganNode().getId(),list);
	}

	@Override
	public String createFiles(List<JobShiftB> list, JobShiftCollect jobShiftCollect, String id, HttpServletRequest request) {
		StringBuilder ids = new StringBuilder();
		for (JobShiftB jobShiftB : list) {
			try {
				ids.append(jobShiftB.getId() + ",");
				String name=jobShiftB.getServant().getName();
				Material m = new Material();//材料信息 一定要生成
				m.setBusId(jobShiftB.getId());
				m.setBusType("JobShift");
				m.setBusName("职务变动");
				m.setFolderName(name);
				Map<String,Object> params = new HashMap<>();//所有参数
				List<String[]> dataList = new ArrayList<>();//所有人员信息

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				//生成电子介绍信====================
				params.put("targetOrgan", jobShiftCollect.getSourceOrganNode().getName());//目标单位名称
				params.put("enterTheUnitDate", sdf.format(jobShiftB.getPostChangeDate()));//报道日期
				params.put("sumNumber", Number2CN.cvt(1,true));//转任人数
				params.put("name", name);
				params.put("now", sdf.format(new Date()));//打印介绍信时间
				params.put("busType", JobShiftBatchController.JOBSHIFT_PROMOTE_REASON);//转移原因
				List<JobShiftB> listTable = new ArrayList<>();
				listTable.add(jobShiftB);
				for (int i = 0; i < listTable.size(); i++) {
					String[] p = new String[6];
					p[0] = listTable.get(0).getServant().getName();
					p[1] = listTable.get(0).getServant().getSex().getName();
                    p[3] = listTable.get(0).getFormerPostName();
                    p[4] = listTable.get(0).getFormerJobLevelName();
					dataList.add(p);
				}
				params.put("listData", dataList);//list数据需要用list标签

				Map<String,String> number = ofcFlowNumberService.executeNumber("JobShift", id);//介绍信编号
				params.put("number", number.get("year")+"字第"+number.get("number"));//介绍信编号

				//编号转换为大写
				String cnYear = Number2CN.convert(number.get("year"));
				String cnNumber = Number2CN.convert(number.get("number"));
				params.put("cnNumber", cnYear+"字第"+cnNumber+"号");//介绍信编号大写

				//当前操作人
				params.put("userName", SecurityUtils.getPrincipal().getName());

				String savePath = request.getSession().getServletContext().getRealPath("/");
				String templet = savePath+"\\static\\templates\\introduce.xls";//模板路径
				String folder = ReadProperties.getInstance().FTP_DIR_NAME_MATERIAL;//ftp文件路径

				String uuid = UUID.randomUUID().toString();
				String path = savePath+"\\static\\templates\\"+uuid+".xls";//生成excel文件路径，临时存放，上传到ftp服务器之后会删除
				ExcelUtilsPOI.replaceModel(params, templet,path, 2,null);//替换模板数据 生成excel到tomcat服务器

				//保存文件到ftp服务器
				String fileName = uuid+".xls";

				File file = new File(path);
				if(FtpTool.ftpUpload(folder, file, fileName)){//文件成功上传到ftp，删除本地文件并保存材料表数据
					m.setFlowNumber(number.get("number"));
					m.setName(dataList.get(0)[0]+"职务变动介绍信"+".xls");
					m.setFtpFilefolder(folder);
					m.setFtpFileName(fileName);
					file.delete();//删除文件
				}


				//生成通知文档==================
				String fileName2=UUID.randomUUID().toString()+".doc";
				Map<String, Object> zwbdtzMap = new HashMap<String, Object>();
				zwbdtzMap.put("number",  number.get("year")+"字第"+number.get("number"));//介绍信编号
				zwbdtzMap.put("name", name);
				zwbdtzMap.put("departName", jobShiftCollect.getSourceOrganNode().getName());
				zwbdtzMap.put("newPostName", jobShiftB.getNewPostName());
				zwbdtzMap.put("dateStr", DateUtils.format(new Date(), "yyyy年MM月dd日"));
				zwbdtzMap.put("salaryTime", DateUtils.getPreMonth("yyyy年MM月"));

				File zwbdtzFile = WordUtils.createMillCertificateWord(zwbdtzMap, "zwbdtz.ftl");
				FtpTool.ftpUpload(folder, zwbdtzFile, fileName2);

				String fileShowName = name+"职务变动介绍信.xls:"+name+"职务变动批准通知.doc";
				String fileFtpName = fileName +":"+ fileName2 ;

				m.setName(fileShowName);
				m.setFtpFileName(fileFtpName);
				materialService.save(m);

			} catch (IOException e) {
				e.printStackTrace();
				throw new BusinessException("下载文档失败!"+e.getMessage());
			}

		}
		jobShiftCollect.setIsDownLoad(CommonConst.YES);
		this.saveOrUpdate(jobShiftCollect);
		return ids.toString();
	}


	public void executeFinish(JobShiftCollect temp){
		List<JobShiftB> shiftBList = jobShiftBService.getShiftByCollectId(temp.getId());

		formationControlService.executeUnlockOutFormationNum(temp.getSourceOrganNode().getId(),shiftBList.size());//2.解锁调出单位未调出编制
		formationControlService.executeOutFormation(temp.getSourceOrganNode().getId(),shiftBList.size());//4.减少调出单位实际编制数

		formationControlService.executeUnlockIntoFormationNum(temp.getSourceOrganNode().getId(),shiftBList.size());//1.解锁调入单位未调入编制
		formationControlService.executeIntoFormation(temp.getSourceOrganNode().getId(),shiftBList.size());//3.增加调入单位实际编制数

		for(JobShiftB z : shiftBList){

			JobLevel jobLevel = jobLevelService.getJobLevelByServantId(z.getServant().getId());

			formationControlService.executeUnlockPostOutNum(temp.getSourceOrganNode().getId(),jobLevel.getRealJobLevelCode(), jobLevel.getRealLeader());//2.解锁职级调出数

			formationControlService.executeOutPost(temp.getSourceOrganNode().getId(),jobLevel.getRealJobLevelCode(), jobLevel.getRealLeader());//4.减少调出单位实际职级数

			//职级
			formationControlService.executeUnlockPostIntoNum(temp.getSourceOrganNode().getId(),z.getRealJobLevelCode(),z.getRealLeader());//1.解锁职级调入数
		}
	}

	/**
	 * @Title: checkNumber
	 * @Description: 校验职务变动人数是否和校验编制各职级人数一致
	 * @param temp
	 * @return: void
	 */
	private void checkNumber(JobShiftCollect temp){
		//获取所有表单上报的人员
		List<JobShiftB> shiftBList = this.jobShiftBService.getShiftByCollectId(temp.getId());

		int plusKeLeaderNum = 0;//乡科级正职（领导）转任人数
		int plusKeNoLeaderNum = 0;//乡科级正职（非领导）转任人数
		int minusKeLeaderNum = 0;//乡科级副职（领导）转任人数
		int minusKeNoLeaderNum = 0;//乡科级副职（非领导）转任人数
		int clerkNumber = 0;//科员转任人数
		int cClerkNumber = 0;//办事员转任人数
		for(JobShiftB i : shiftBList){
			if(i.getNewJobLevel()==null){
				throw new BusinessException("请先编辑所有人员的职务变动信息！");
			}
			String code = i.getNewJobLevel().getCode();
			if(FormationControl.SECTION_CHIEF.equals(code)){//乡科级正职
				if(i.getIsLeader()== CommonConst.YES){//领导
					plusKeLeaderNum++;
				}else if(i.getIsLeader()==CommonConst.NO){//非领导
					plusKeNoLeaderNum++;
				}
			}else if(FormationControl.DEPUTY_SECTION_CHIEF.equals(code)){//乡科级副职
				if(i.getIsLeader()==CommonConst.YES){//领导
					minusKeLeaderNum++;
				}else if(i.getIsLeader()==CommonConst.NO){//非领导
					minusKeNoLeaderNum++;
				}
			}else if(FormationControl.CLERK.equals(code)){//科员
				clerkNumber++;
			}else if(FormationControl.C_CLERK.equals(code)){//办事员
				cClerkNumber++;
			}else{
				throw new BusinessException(i.getNewJobLevel().getName()+" 该职级信息校验异常！");
			}
		}
		if((temp.getPlusKeLeaderNum()!=null&&temp.getPlusKeLeaderNum()!=plusKeLeaderNum)||(temp.getPlusKeLeaderNum()==null&&plusKeLeaderNum>0)){//乡科级正职（领导）
			throw new BusinessException("乡科级正职（领导）人数和校验人数不一致！");
		}
		if((temp.getPlusKeNoLeaderNum()!=null&&temp.getPlusKeNoLeaderNum()!=plusKeNoLeaderNum)||(temp.getPlusKeNoLeaderNum()==null&&plusKeNoLeaderNum>0)){//乡科级正职（非领导）
			throw new BusinessException("乡科级正职（非领导）人数和校验人数不一致！");
		}
		if((temp.getMinusKeLeaderNum()!=null&&temp.getMinusKeLeaderNum()!=minusKeLeaderNum)||(temp.getMinusKeLeaderNum()==null&&minusKeLeaderNum>0)){//乡科级副职（领导）
			throw new BusinessException("乡科级副职（领导）人数和校验人数不一致！");
		}
		if((temp.getMinusKeNoLeaderNum()!=null&&temp.getMinusKeNoLeaderNum()!=minusKeNoLeaderNum)||(temp.getMinusKeNoLeaderNum()==null&&minusKeNoLeaderNum>0)){//乡科级副职（非领导）
			throw new BusinessException("乡科级副职（非领导）人数和校验人数不一致！");
		}
		if((temp.getClerkNumber()!=null&&temp.getClerkNumber()!=clerkNumber)||(temp.getClerkNumber()==null&&clerkNumber>0)){//科员
			throw new BusinessException("科员级人数和校验人数不一致！");
		}
		if((temp.getcClerkNumber()!=null&&temp.getcClerkNumber()!=cClerkNumber)||(temp.getcClerkNumber()==null&&cClerkNumber>0)){//办事员
			throw new BusinessException("办事员级人数和校验人数不一致！");
		}
	}


	/**
	 * @Title: executeValidateAndLockFormation
	 * @Description: 校验编控并锁编
	 * @param temp
	 * @return: List<JudgePostResult> 原单位职级信息
	 */
	public void executeValidateAndLockFormation(JobShiftCollect temp,List<JobShiftB> shiftBList){
		List<JudgePostResult> list = new ArrayList<>();

		for(JobShiftB z : shiftBList){
			try{
				if(StringUtils.isBlank(z.getNewJobLevel().getCode())){
					throw new BusinessException("占编职级信息异常，请联系管理员！");
				}
			}catch (Exception e){
				throw new BusinessException("占编职级信息异常，请联系管理员！");
			}

			JudgePostResult j = new JudgePostResult(z.getNewJobLevel().getCode(), z.getIsLeader(),1);
			list.add(j);
		}
		List<JudgePostResult> result = formationControlService.executeValidateAndLockFormation(temp.getSourceOrganNode().getId(), list);//校验编控并锁编
		for(int i=0;i<shiftBList.size();i++){//回写真实占编数据
			JobShiftB z = shiftBList.get(i);
			z.setRealJobLevelCode(result.get(i).getPostLvlCode());
			z.setRealLeader(result.get(i).getIsLeader());
			this.jobShiftBService.update(z);
		}
	}


    /**
     * @Title: executeUnLockFormationAndPost
     * @Description: 返编
     * @param temp
     * @return: void
     */
    public void executeUnLockFormationAndPost(JobShiftCollect temp){
        List<JudgePostResult> list = new ArrayList<>();//职务变动后真实职级
        List<JudgePostResult> list2 = new ArrayList<>();//原真实职级
        //获取所有表单上报的人员
        List<JobShiftB> intoList = this.jobShiftBService.getShiftByCollectId(temp.getId());
        //返回未调入编制
        for (JobShiftB jobShiftB : intoList) {
            JudgePostResult j = new JudgePostResult(jobShiftB.getRealJobLevelCode(), jobShiftB.getRealLeader(),1);
            list.add(j);
        }
        //返回未调出编制
        for(JobShiftB jobShiftB : intoList){
            JobLevel jobLevel = jobLevelService.getJobLevelByServantId(jobShiftB.getServant().getId());
            JudgePostResult j = new JudgePostResult(jobLevel.getRealJobLevelCode(), jobLevel.getRealLeader(),1);
            list2.add(j);
        }
        formationControlService.executeUnLockOutFormationAndPost(temp.getSourceOrganNode().getId(), list2);//返编未调出
        formationControlService.executeUnLockIntoFormationAndPost(temp.getSourceOrganNode().getId(), list);//返编未调入
    }

}
