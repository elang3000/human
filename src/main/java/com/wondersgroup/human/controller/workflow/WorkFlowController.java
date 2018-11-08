/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: WorkFlowController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.workflow
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年8月31日 下午3:31:35
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年8月31日 下午3:31:35
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.controller.workflow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.console.bo.FrameWorkResource;
import com.wondersgroup.framework.console.service.FrameWorkService;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.security.bo.SecurityGroup;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.framework.workflow.vo.FlowRecordVO;

/**
 * @ClassName: WorkFlowController
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年8月31日 下午3:31:35
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("workflow/")
public class WorkFlowController extends GenericController {
	
	private final static String WORKFLOW_DOING_INDEX = "models/workflow/queryWorkFlowDoingIndex",
	        WORKFLOW_DONE_INDEX = "models/workflow/queryWorkFlowDoneIndex",
	        WORKFLOW_DETAIL_INDEX = "models/workflow/queryWorkFlowDetailIndex";
	
	private static Map<String, String> flowRecordMapping = new HashMap<String, String>();
	
	/*
	 * 事业单位待办，已办事项
	 */
	private final static String WORKFLOW_DOING_INDEX_Public = "models/workflow/queryWorkFlowDoingIndexPublic",
	        WORKFLOW_DONE_INDEX_Public = "models/workflow/queryWorkFlowDoneIndexPublic";
	
	
	static {
		flowRecordMapping.put("RecruitEmployPlan", "/ofcflow/recruit/planFlow");//招录计划
		flowRecordMapping.put("RecruitPost", "/ofcflow/recruit/planFlow");//招录职位
		
		flowRecordMapping.put("ProbationServant", "/ofcflow/probation/workFlow");//试用期 考核合格
		flowRecordMapping.put("CancelProbationServant", "/ofcflow/probation/workFlow");//试用期 考核不合格
		
		flowRecordMapping.put("ZhuanRenTLBIntoMgr_THIS", "/ofcflow/zrtlbInto/workFlow");//本区内同类别转任
		flowRecordMapping.put("ZhuanRenTLBIntoMgr_OUTER", "/ofcflow/zrtlbInto/workFlow");//外区同类别转任
		flowRecordMapping.put("ZhuanRenTLBOutMgr", "/ofcflow/zrtlbInto/workFlow");//同类别转任转出
		
		flowRecordMapping.put("ZhuanRenKLBIntoMgr_THIS", "/ofcflow/zrklbInto/workFlow");//本区内跨类别转任
		flowRecordMapping.put("ZhuanRenKLBIntoMgr_OUTER", "/ofcflow/zrklbInto/workFlow");//外区跨类别转任
		flowRecordMapping.put("ZhuanRenKLBOutMgr", "/ofcflow/zrklbInto/workFlow");//跨类别转任转出
		
		flowRecordMapping.put("ReferenceExchange_THIS", "/ofcflow/exchange/workFlow");//本区内参公交流
		flowRecordMapping.put("ReferenceExchange_OUTER", "/ofcflow/exchange/workFlow");//外区参公交流
		
		flowRecordMapping.put("DiaoRenIntoMgr_THIS", "/ofcflow/diaoren/workFlow");//本区内调入
		flowRecordMapping.put("DiaoRenIntoMgr_OUTER", "/ofcflow/diaoren/workFlow");//外区调入
		flowRecordMapping.put("DiaoRenOutMgr_THIS", "/ofcflow/diaoren/workFlow");//调出到本区
		
		flowRecordMapping.put("ResignServant", "/ofcflow/resign/resignFlow");//辞职
		
		flowRecordMapping.put("DeathServant", "/ofcflow/death/deathFlow");//死亡
		
		flowRecordMapping.put("Train", "/ofcflow/train/trainFlow");//培训学时考核
		
		flowRecordMapping.put("PunishServant", "/ofcflow/punish/punishFlow");//处分
		
		flowRecordMapping.put("Abroad", "/ofcflow/abroad/abroadFlow");//因公出国政审


		flowRecordMapping.put("JOBSHIFT_PROMOTE", "/ofcflow/jobchange/jobChangeFlow");//职务变动-升职
		flowRecordMapping.put("JOBSHIFT_DEMOTE", "/ofcflow/jobchange/jobChangeFlow");//职务变动-降职
		flowRecordMapping.put("JOBSHIFT_DEPOSE", "/ofcflow/jobchange/jobChangeFlow");//职务变动-免职
		flowRecordMapping.put("JOBSHIFT_SHIFT", "/ofcflow/jobchange/jobChangeFlow");//职务变动-轮岗
		flowRecordMapping.put("ASSESS_REWARD", "/ofcflow/assess/assessFlow");//年度考核奖励

		flowRecordMapping.put("H004001001", "/orgInfoflow/applyApprovalPage");
		flowRecordMapping.put("H004001002", "/orgInfoflow/adjustApprovalPage");
		flowRecordMapping.put("H004001004", "/orgFormationFlow/adjustApprovalPage");
		flowRecordMapping.put("MemberInfoRegister", "/instflow/inforegister/queryRegisterInfo");
		flowRecordMapping.put("AlternatingRotation", "/instflow/alternatingrotation/queryRegisterInfo");
		flowRecordMapping.put("InformationChange", "/instflow/informationchanges/queryinformation");
		flowRecordMapping.put("RecordableRecord", "/instflow/recordablerecord/queryRegister");
		
		
		
	}
	
	@Autowired
	FlowRecordService flowRecordService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@Autowired
	FrameWorkService frameWorkService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("doing/index")
	public String getWorkFlowDoingIndex(String busId, Model model,String category) {
		
		model.addAttribute("category", category);//数据类型,区分公务员和事业单位等
		model.addAttribute("busId", busId);
		model.addAttribute("userId", SecurityUtils.getUserId());
		return WORKFLOW_DOING_INDEX;
	}
	
	@RequestMapping("done/index")
	public String getWorkFlowDoneIndex(String busId, Model model) {
		
		model.addAttribute("busId", busId);
		return WORKFLOW_DONE_INDEX;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("doing/indexPublic")
	public String getWorkFlowDoingIndexPublic(String busId, Model model) {
		
		model.addAttribute("busId", busId);
		model.addAttribute("userId", SecurityUtils.getUserId());
		return WORKFLOW_DOING_INDEX_Public;
	}
	
	@RequestMapping("done/indexPublic")
	public String getWorkFlowDoneIndexPublic(String busId, Model model) {
		
		model.addAttribute("busId", busId);
		return WORKFLOW_DONE_INDEX_Public;
	}
	
	@RequestMapping("complete/task")
	public String completeFlowRecord(String id,Model model) {
		
		FlowRecord flowRecord = flowRecordService.get(id);
		String mapping = flowRecordMapping.get(flowRecord.getBusType());
		if (!StringUtils.startsWith(mapping, "/")) {
			mapping += "/" + mapping;
		}
		
		return "forward:" + mapping;
	}
	
	@RequestMapping("doing/page")
	@ResponseBody
	public Page<FlowRecordVO> queryWorkFlowDoingPage(HttpServletRequest request, String busType,
	        String sourceOrganNodeId, String busId, Integer limit, Integer page) {
		
		String category = request.getParameter("category");//数据类型,区分公务员和事业单位等
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ofcFlowState", FlowRecord.DOING);
		if (StringUtils.isNotBlank(category)) {
			params.put("category", category);
		}
		if (StringUtils.isNotBlank(busId)) {
			params.put("busId", busId);
		}
		if (StringUtils.isNotBlank(busType)) {
			if (busType.contains(",")) {
				List<String> busTypeList = Arrays.asList(busType.split(","));
				params.put("busTypeList", busTypeList);
			} else {
				params.put("busType", busType);
			}
		}
		if (StringUtils.isNotBlank(sourceOrganNodeId)) {
			OrganNode sourceOrganNode = organNodeService.get(sourceOrganNodeId);
			params.put("sourceOrganNode", sourceOrganNode);
		}
		
		SecurityGroup group = (SecurityGroup)SecurityUtils.getSession().getAttribute("group");
		if (group != null) {
			// 查询单位领导分组
			if (StringUtils.equals(SecurityGroup.SECURITY_GROUP_TYPE_LEVEL, group.getType())) {
				OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				params.put("targetOrganNode", organNode);
			}
			// 查询区级领导分组
			if (StringUtils.equals(SecurityGroup.SECURITY_GROUP_TYPE_TOP, group.getType())) {
				
			}
		} else {
			params.put("targetSecurityUser", userService.get(SecurityUtils.getUserId()));
		}
		return flowRecordService.findFlowRecord(params, page, limit, false);
	}
	
	@RequestMapping("done/page")
	@ResponseBody
	public Page<FlowRecordVO> queryWorkFLowDonePage(HttpServletRequest request, String busType, String sourceOrganNodeId, String busId,
	        Integer limit, Integer page) {
		String category = request.getParameter("category");//数据类型,区分公务员和事业单位等
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ofcFlowState", FlowRecord.DONE);
		if (StringUtils.isNotBlank(category)) {
			params.put("category", category);
		}
		if (StringUtils.isNotBlank(busId)) {
			params.put("busId", busId);
		}
		if (StringUtils.isNotBlank(busType)) {
			params.put("busType", busType);
		}
		if (StringUtils.isNotBlank(sourceOrganNodeId)) {
			OrganNode sourceOrganNode = organNodeService.get(sourceOrganNodeId);
			params.put("sourceOrganNode", sourceOrganNode);
		}
		params.put("targetSecurityUser", userService.get(SecurityUtils.getUserId()));
		return flowRecordService.findFlowRecord(params, page, limit, false);
	}
	
	@RequestMapping("detail/index")
	public String queryFlowRecordDetail(String id, Model model) {
		
		FlowRecord flowRecord = flowRecordService.get(id);
		flowRecord = flowRecordService.queryLastFlowRecord(flowRecord.getBusId(), flowRecord.getBusType());
		List<FrameWorkResource> frameWorkResources = frameWorkService
		        .getParentFrameWorkResourceByCode(flowRecord.getAppNodeId(), flowRecord.getOperationCode(), true);
		List<FlowRecordSeq> seqs = new ArrayList<FlowRecordSeq>();
		Integer index = 1;
		FlowRecordSeq seq = new FlowRecordSeq();
		seq.setCode("START");
		seq.setName("开始");
		seq.setIndex(index);
		seq.setComplete(true);
		seqs.add(seq);
		Boolean complete = true;
		Boolean active = false;
		for (FrameWorkResource resource : frameWorkResources) {
			index++;
			active = false;
			seq = new FlowRecordSeq();
			seq.setIndex(index);
			seq.setCode(resource.getCode());
			seq.setName(resource.getResourceName());
			if (flowRecord.getBusState() != FlowRecord.BUS_DONE
			        && StringUtils.equals(resource.getCode(), flowRecord.getOperationCode())) {
				complete = false;
				active = true;
			}
			seq.setComplete(complete);
			seq.setActive(active);
			seqs.add(seq);
		}
		seq = new FlowRecordSeq();
		seq.setCode("END");
		seq.setName("结束");
		seq.setIndex(index + 1);
		seq.setComplete(flowRecord.getBusState() == FlowRecord.BUS_DONE);
		seqs.add(seq);
		model.addAttribute("seqs", seqs);
		List<FlowRecordVO> records = flowRecordService.findFlowRecordByBusinessId(flowRecord.getBusId(),
		        flowRecord.getBusType(), false);
		model.addAttribute("records", records);
		return WORKFLOW_DETAIL_INDEX;
	}
	
	public class FlowRecordSeq implements Serializable {
		
		private static final long serialVersionUID = 2682218377096418690L;
		
		private String code;
		
		private String name;
		
		private Integer index;
		
		private Boolean complete;
		
		private Boolean active;
		
		public String getCode() {
			
			return code;
		}
		
		public void setCode(String code) {
			
			this.code = code;
		}
		
		public String getName() {
			
			return name;
		}
		
		public void setName(String name) {
			
			this.name = name;
		}
		
		public Integer getIndex() {
			
			return index;
		}
		
		public void setIndex(Integer index) {
			
			this.index = index;
		}
		
		public Boolean getComplete() {
			
			return complete;
		}
		
		public void setComplete(Boolean complete) {
			
			this.complete = complete;
		}
		
		public Boolean getActive() {
			
			return active;
		}
		
		public void setActive(Boolean active) {
			
			this.active = active;
		}
	}
}
