
package com.wondersgroup.human.service.ofcflow.impl;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.*;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.bo.ofcflow.JobShiftDepose;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerManageRecordEvent;
import com.wondersgroup.human.service.ofc.JobChangeService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.JobShiftDeposeService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.organization.JudgePostResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
public class JobShiftDeposeServiceImpl extends GenericServiceImpl<JobShiftDepose>
        implements JobShiftDeposeService {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private PostService postService;

    @Autowired
    private DictableService dictableService;

    @Autowired
    private ServantService servantService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CodeInfoService codeInfoService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JobChangeService jobChangeService;

    @Autowired
    private FormationControlService formationControlService;

    @Autowired
    private JobLevelService jobLevelService;

    @Override
    public void updateDeposeFlow(JobShiftDepose jobShiftDepose, String opinion, String result) {


        SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
        OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
        AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
        if (StringUtils.isBlank(jobShiftDepose.getId())) {
            saveOrUpdate(jobShiftDepose);// 保存业务数据
        }
        Servant servant = servantService.get(jobShiftDepose.getServant().getId());
        String servantId=servant.getId();
        //旧职级
        JobLevel formerJobLevel = jobLevelService.getJobLevelByServantId(servantId);//查询当前人员的现行职级
        //新职级
        CodeInfo newJobLevel=null;
        //新职级是否为空
        if(null!=jobShiftDepose.getNewJobLevel()){
            newJobLevel = codeInfoService.get(jobShiftDepose.getNewJobLevel().getId());
        }

        FlowRecord flow;
        if (jobShiftDepose.getFlowRecord() == null) {// 提交环节，先生成流程数据

            if(null!=newJobLevel&&newJobLevel.getId()!=formerJobLevel.getCode().getId()) {
                String orgId = servant.getDepartId();
                //锁定职位调出编控
//                formationControlService.executeLockPostOutNum(orgId, formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
                //编控，校验职位编制数是否足够，判断数据能否保存，如果超编，抛出异常
//                JudgePostResult queryJudgePostNum = formationControlService.queryJudgePostNum(orgId, newJobLevel.getCode());
                //锁定职位调入编控
//                formationControlService.executeLockPostIntoNum(orgId, newJobLevel.getCode(), queryJudgePostNum.isLowToHigh);
                //锁定职位调出编控
//                formationControlService.executeLockPostOutNum(orgId, formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
                //保存是否高职低配到业务表
//                jobShiftDepose.setLowToHigh(queryJudgePostNum.isLowToHigh);
            }



            // 设置申请人员的单位
            jobShiftDepose.setSourceOrganNode(userOrg);
            //提名免职的单位代码
            jobShiftDepose.setNominationDismissalCode(userOrg.getCode());
            //提名免职的单位名称
            jobShiftDepose.setNominationDismissalName(userOrg.getAllName());
            flow = new FlowRecord();
            flow.setAppNodeId(appNode.getId());// 流程业务所在系统
            flow.setBusId(jobShiftDepose.getId());// 流程业务ID
            flow.setBusName("职务变动");// 流程业务名称
            flow.setTargetOrganNode(userOrg);// 流程业务目标组织
            flow.setTargetSecurityUser(user);// 流程业务目标人员
            flow.setBusType(FlowBusTypeConstant.FLOW_JOBSHIFT_DEPOSE);// 流程业务类型
            flow = workflowService.createFlowRecord(flow, JobShift.JOBSHIFT_DEMOTE_STEP1);// 初始节点,降职和轮岗和免职使用相同的流程,起始节点一样

        } else {
            flow = jobShiftDepose.getFlowRecord();
            flow.setOpinion(opinion);
            flow.setResult(result);
            flow = workflowService.completeWorkItem(flow);// 提交下个节点
        }
        if (null == flow) {
            //任职状态,DM215 是 和 否
            CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");
            CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");

            //1 判断假如新职级不为空且不和老职级相同则 编控操作 让出老职务的编制 =================================
            if(null!=newJobLevel&&newJobLevel.getId()!=servant.getNowJobLevel().getId()) {
                //解锁职位调入编控
//                formationControlService.executeUnlockPostIntoNum(servant.getDepartId(), newJobLevel.getCode(), jobShiftDepose.getLowToHigh());
                //解锁职位调出编控
//                formationControlService.executeUnlockPostOutNum(servant.getDepartId(), formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());
                //出编控
//                formationControlService.executeOutPost(servant.getDepartId(), formerJobLevel.getCode().getCode(), formerJobLevel.getIsLowToHigh());

            }



            //设置任职状态为不在任
            CodeInfo notInOfficeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_POST_STATUS);

            //数据库中的post
            Post oldPost = jobShiftDepose.getPost();
            //提名免职的单位名称
            oldPost.setNominationDismissalName(jobShiftDepose.getNominationDismissalName());
            //提名免职的单位代码
            oldPost.setNominationDismissalCode(jobShiftDepose.getNominationDismissalCode());
            //提出免职日期
            oldPost.setNominationDismissalDate(jobShiftDepose.getNominationDismissalDate());
            //提出免职文号,决定该人免职的文件的编号全称
            oldPost.setNominationDismissalNumber(jobShiftDepose.getNominationDismissalNumber());
            //决定或批准免职的日期
            oldPost.setApprovalDismissalDate(jobShiftDepose.getApprovalDismissalDate());
            //决定或批准免职的文号
            oldPost.setApprovalDismissalNumber(jobShiftDepose.getApprovalDismissalNumber());
            //免职方式
            oldPost.setDismissalType(jobShiftDepose.getDismissalType());
            //免职原因
            oldPost.setDismissalReason(jobShiftDepose.getDismissalReason());
            //免职变动类别
            oldPost.setDismissalChange(jobShiftDepose.getDismissalChange());
            //免职方式
            oldPost.setDismissalMode(jobShiftDepose.getDismissalMode());
            //设置不在职状态
            oldPost.setTenureStatus(notInOfficeInfo);
            postService.update(oldPost);



            // 2 发送消息 =====================================================
            String title = messageSource.getMessage(JobShift.PROMOTE_TITLE, new Object[]{
                    jobShiftDepose.getServant().getName()
            }, Locale.CHINESE);
            String content = messageSource.getMessage(JobShift.PROMOTE_CONTENT, new Object[]{
                    jobShiftDepose.getServant().getName()
            }, Locale.CHINESE);
            //发送通知
            AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, jobShiftDepose.getCreater(), title, content, "")));


            //3 流程结束,插入职务变动子集信息   ===================================================
            //流程结束,插入子集信息
            JobChange change = new JobChange();
            BeanUtils.copyProperties(jobShiftDepose, change);
            change.setFormerUnitCode(jobShiftDepose.getSourceOrganNode().getId());
            change.setFormerUnitName(jobShiftDepose.getSourceOrganNode().getAllName());
            change.setNewUnitCode(jobShiftDepose.getSourceOrganNode().getId());
            change.setNewUnitName(jobShiftDepose.getSourceOrganNode().getAllName());
            jobChangeService.save(change);


            //5 暂时不加,流程结束,新职务插入简历子集.!!!!旧简历加上结束时间,但是就简历没法找,所以暂时没有此操作===========================================

            //6 判断假如新职级不为空且不和老职级相同则生成新职级并保存到servant当中
            if(null!=newJobLevel&&newJobLevel.getId()!=servant.getNowJobLevel().getId()){
                JobLevel jobLevel = new JobLevel();
                jobLevel.setServant(servant);
//                jobLevel.setIsLowToHigh(jobShiftDepose.getLowToHigh());
                jobLevel.setName(jobShiftDepose.getNewJobLevel().getName());
                jobLevel.setCode(jobShiftDepose.getNewJobLevel());
                jobLevel.setApprovalDate(new Date());
                jobLevel.setCurrentIdentification(yesCodeInfo);
                jobLevelService.save(jobLevel);
            }


            //7 增加进出管操作
            ManagerRecordDTO dto = new ManagerRecordDTO(jobShiftDepose.getServant().getId(), ManagerRecord.HUMAN_ZWBD);
            ManagerManageRecordEvent event = new ManagerManageRecordEvent(dto);
            EventManager.send(event);

        }
        jobShiftDepose.setFlowRecord(flow);
        this.update(jobShiftDepose);


    }


}
