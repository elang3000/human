
package com.wondersgroup.human.service.ofcflow.impl;

import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.human.bo.ofc.JobChange;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerOutRecordEvent;
import com.wondersgroup.human.service.ofc.JobChangeService;
import com.wondersgroup.human.service.ofc.ServantService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.bo.ofcflow.JobShiftDepose;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofcflow.JobShiftDeposeService;
import com.wondersgroup.human.service.organization.FormationControlService;

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
    private FormationControlService formationControlService;

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

    @Override
    public void updateDeposeFlow(JobShiftDepose jobShiftDepose, String opinion, String result) {


        SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
        OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
        AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
        if (StringUtils.isBlank(jobShiftDepose.getId())) {
            saveOrUpdate(jobShiftDepose);// 保存业务数据
        }
        FlowRecord flow;
        if (jobShiftDepose.getFlowRecord() == null) {// 提交环节，先生成流程数据
            // 设置申请人员的单位
            jobShiftDepose.setSourceOrganNode(userOrg);
//			jobShiftDepose.setApprovalDismissalCode(approvalDismissalCode);
            jobShiftDepose.setNominationDismissalCode(userOrg.getCode());
            jobShiftDepose.setNominationDismissalName(userOrg.getAllName());
            flow = new FlowRecord();
            flow.setAppNodeId(appNode.getId());// 流程业务所在系统
            flow.setBusId(jobShiftDepose.getId());// 流程业务ID
            flow.setBusName(userOrg.getAllName() + "职务变动信息");// 流程业务名称
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


            //数据库中的post
            Post oldPost = jobShiftDepose.getPost();
            System.out.println(jobShiftDepose.getPost().getId());
            oldPost.setNominationDismissalName(jobShiftDepose.getNominationDismissalName());
            oldPost.setNominationDismissalCode(jobShiftDepose.getNominationDismissalCode());
            oldPost.setNominationDismissalDate(jobShiftDepose.getNominationDismissalDate());
            oldPost.setNominationDismissalNumber(jobShiftDepose.getNominationDismissalNumber());
//			jobShiftDepose.getApprovalDismissalName();
//			jobShiftDepose.getApprovalDismissalCode();
            oldPost.setApprovalDismissalDate(jobShiftDepose.getApprovalDismissalDate());
            oldPost.setApprovalDismissalNumber(jobShiftDepose.getApprovalDismissalNumber());
            oldPost.setDismissalType(jobShiftDepose.getDismissalType());
            oldPost.setDismissalReason(jobShiftDepose.getDismissalReason());
            oldPost.setDismissalChange(jobShiftDepose.getDismissalChange());
            oldPost.setDismissalMode(jobShiftDepose.getDismissalMode());
            postService.update(oldPost);

            //FIXME 免职之后 是否需要让出编制?
            //旧职位
            Post formerPost = postService.load(jobShiftDepose.getPost().getId());
            //解锁旧编制
//            formationControlService.executeUnlockPostOutNum(jobShiftDepose.getSourceOrganNode().getId(), formerPost.getPostCode().getCode(), jobShiftDepose.getPost().getIsLowToHigh());
            //插入数据
//            formationControlService.executeOutPost(jobShiftDepose.getSourceOrganNode().getId(), formerPost.getPostCode().getCode(), jobShiftDepose.getPost().getIsLowToHigh());


            // 2 发送消息 =====================================================
            String title = messageSource.getMessage(JobShift.PROMOTE_TITLE, new Object[]{
                    jobShiftDepose.getServant().getName()
            }, Locale.CHINESE);
            String content = messageSource.getMessage(JobShift.PROMOTE_CONTENT, new Object[]{
                    jobShiftDepose.getServant().getName()
            }, Locale.CHINESE);
            applicationContext.publishEvent(new AnnouncementEventData(true, jobShiftDepose.getCreater(), title, content, ""));


            //3 流程结束,插入职务变动子集信息   ===================================================
            //流程结束,插入子集信息
            JobChange change = new JobChange();
            BeanUtils.copyProperties(jobShiftDepose, change);
            change.setFormerUnitCode(jobShiftDepose.getSourceOrganNode().getId());
            change.setFormerUnitName(jobShiftDepose.getSourceOrganNode().getAllName());
            change.setNewUnitCode(jobShiftDepose.getSourceOrganNode().getId());
            change.setNewUnitName(jobShiftDepose.getSourceOrganNode().getAllName());
            jobChangeService.save(change);


            //4 流程结束,升职后将原职务设置为非现任职务,插入新post子集,将新职务设置为现任职务     ==================================================

            //考核结论代码DM018  1优秀
            CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");
            CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("1", "DM215");
            Post prePost = jobShiftDepose.getPost();
            //升职后将原职务设置为非现任职务
            prePost.setNowPostSign(noCodeInfo);
            postService.update(prePost);

            //5 暂时不加,流程结束,新职务插入简历子集.!!!!旧简历加上结束时间,但是就简历没法找,所以暂时没有此操作===========================================


            //6 增加进出管操作
            ManagerRecordDTO dto = new ManagerRecordDTO(jobShiftDepose.getServant().getId(), ManagerRecord.HUMAN_ZWBD);
            ManagerOutRecordEvent event = new ManagerOutRecordEvent(dto);
            EventManager.send(event);

            System.out.println("流程结束");

        }
        jobShiftDepose.setFlowRecord(flow);
        this.update(jobShiftDepose);


    }


}
