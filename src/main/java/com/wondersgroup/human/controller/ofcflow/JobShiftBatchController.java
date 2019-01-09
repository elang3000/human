
package com.wondersgroup.human.controller.ofcflow;

import com.wondersgroup.common.contant.FlowBusTypeConstant;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.bo.ofcflow.JobShiftB;
import com.wondersgroup.human.bo.ofcflow.JobShiftCollect;
import com.wondersgroup.human.bo.ofcflow.JobShiftDepose;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.JobShiftBService;
import com.wondersgroup.human.service.ofcflow.JobShiftCollectService;
import com.wondersgroup.human.service.ofcflow.JobShiftDeposeService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.ofc.PostVO;
import com.wondersgroup.human.vo.ofcflow.JobShiftBVO;
import com.wondersgroup.human.vo.ofcflow.JobShiftCollectVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * Title: JobChangeController.java
 * </p>
 * <p>
 * Description:职务变动控制器
 * </p>
 * @author youyd
 * @date 2018年8月14日
 * @version 1.0
 */
@Controller
@RequestMapping("ofcflow/jobchangeB")
public class JobShiftBatchController extends GenericController {

	public static final String JOBSHIFT_PROMOTE_REASON = "职务变动升职";

	@Autowired
	private ServantService servantService;
	
	@Autowired
	private PostService postService;
	
	
	@Autowired
	private JobShiftBService jobShiftBService;
	
	@Autowired
	private JobShiftDeposeService jobShiftDeposeService;
	
	@Autowired
	private FlowRecordService flowRecordService;

	@Autowired
	private OrgFormationService orgFormationService;
	
	@Autowired
	private OrgInfoService orgInfoService;	
	
	@Autowired
	private JobShiftCollectService jobShiftCollectService;

	@Autowired
	private JobLevelService jobLevelService;


    
	// 职务变动列表页面
	private final static String JOBCHANGE_BATCH_INDEX_PAGE = "models/ofcflow/jobChange/batch/jobChangeIndex";

	// 职务变动列表页面
	private final static String JOBCHANGE_HUMAN_INDEX_PAGE = "models/ofcflow/jobChange/batch/jobChangeHumanIndex";



	// 新增批量职务变动 晋升 页面
	private final static String JOBCHANGE_BATCH_PROMOTE_ADD_PAGE = "models/ofcflow/jobChange/batch/jobChangeBatchPromoteAdd";

	//职务变动 晋升详情 浏览页面
	private final static String JONCHANGE_BATCH_PROMOTE_VIEW_PAGE = "models/ofcflow/jobChange/batch/jobChangeBatchPromoteView";

	// 职务变动晋升单人修改页面
	private final static String JOBCHANGE_PROMOTE_PAGE_EDIT = "models/ofcflow/jobChange/batch/jobChangePromoteEdit";

	// 职务变动晋升单人 时间 修改页面
	private final static String JOBCHANGE_PROMOTE_TIME_EDIT = "models/ofcflow/jobChange/batch/jobChangePromoteTimeEdit";

	// 职务变动-晋升查看页面
	private final static String JOBCHANGE_PROMOTE_VIEW = "models/ofcflow/jobChange/batch/jobChangePromoteView";

	// 降职流程审批页面
	private final static String JOBCHANGE_BATCH_PROMOTE_FLOW_PAGE = "models/ofcflow/jobChange/batch/flow/jobChangePromoteFlow";



	// 职务变动-降职页面
	private final static String JOBCHANGE_BATCH_DEMOTE_ADD_PAGE = "models/ofcflow/jobChange/batch/jobChangeBatchDemoteAdd";
	// 职务变动-降职页面
	private final static String JOBCHANGE_BATCH_DEMOTE_PAGE = "models/ofcflow/jobChange/batch/jobChangeBatchDemote";
	// 降职流程审批页面
	private final static String JOBCHANGE_BATCH_DEMOTE_FLOW_PAGE = "models/ofcflow/jobChange/batch/flow/jobChangeDemoteFlow";
	// 职务变动-免职页面
	private final static String JOBCHANGE_BATCH_DEPOSE_PAGE = "models/ofcflow/jobChange/batch/jobChangeBatchDepose";
	// 职务变动-免职流程审批页面
	private final static String JOBCHANGE_BATCH_DEPOSE_FLOW_PAGE = "models/ofcflow/jobChange/batch/flow/jobChangeDeposeFlow";
	// 职务变动-轮岗  
	private final static String JOBCHANGE_BATCH_SHIFT_PAGE = "models/ofcflow/jobChange/batch/jobChangeBatchShift";
	// 职务变动管理流程代办列表
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";




	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动主页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String jobChangeIndexPage(Model model) {
		return JOBCHANGE_BATCH_INDEX_PAGE;
	}

	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动人员查看页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/indexHuman")
	public String jobChangeHumanIndexPage(Model model) {
		return JOBCHANGE_HUMAN_INDEX_PAGE;
	}

	/**
	 * 主页数据
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/indexData")
    @ResponseBody
	public Page<JobShiftCollectVO> jobChangeIndexData(Integer page,String jobChangeType, Integer limit){
        if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from JobShiftCollect where removed=:removed and creater=:creater ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
        if(StringUtils.isNotBlank(jobChangeType)){
            hql.append(" and collectType=:jobChangeType");
            listqueryparameter.add(new QueryParameter("jobChangeType", jobChangeType));
        }
		hql.append( " order by createTime desc");
		Page<JobShiftCollectVO> pageInfo = jobShiftCollectService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);
		return  pageInfo;
    }

	/**
	 * 主页数据
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/humanIndexData")
    @ResponseBody
	public Page<JobShiftBVO> jobChangeHumanIndexData(Integer page, Integer limit,String name,String cardNo){
        if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from JobShiftB where removed=:removed and creater=:creater ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
		hql.append( " order by createTime desc");
		Page<JobShiftBVO> pageInfo = jobShiftBService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);
		return  pageInfo;
    }


    @RequestMapping("/jobchangePromoteView")
	public String jobchangePromoteView(String id,Model model){
		JobShiftCollect collect = this.jobShiftCollectService.get(id);
		// 获取操作的人员对象
		model.addAttribute("d", collect);
		switch(collect.getCollectType()){
			case FlowBusTypeConstant.FLOW_JOBSHIFT_PROMOTEB:
				return JONCHANGE_BATCH_PROMOTE_VIEW_PAGE;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_DEMOTE:
				return JONCHANGE_BATCH_PROMOTE_VIEW_PAGE;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_DEPOSE:
				return JONCHANGE_BATCH_PROMOTE_VIEW_PAGE;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT:
				return JONCHANGE_BATCH_PROMOTE_VIEW_PAGE;
			default:
				throw new BusinessException("没有对应的职务变动类型!");
		}
	}



	/**
	 * 打开新增collect页面 不同类型跳转到不同页面
	 * @param model
	 * @param jobChangeType
	 * @return
	 */
    @RequestMapping("/addJobShiftCollect")
    public String addJobShiftCollect(Model model,String jobChangeType){
    	JobShiftCollect collect=new JobShiftCollect();
    	collect.setCollectType(jobChangeType);
    	//假如是免职
    	 if(jobChangeType.equals(FlowBusTypeConstant.FLOW_JOBSHIFT_DEPOSE)){
    		return JOBCHANGE_BATCH_DEMOTE_ADD_PAGE;
    	//假如是轮岗
    	}else if(jobChangeType.equals(FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT)){
    		return JOBCHANGE_BATCH_SHIFT_PAGE;
    	}else if(jobChangeType.equals(FlowBusTypeConstant.FLOW_JOBSHIFT_DEMOTE)){
    		return JOBCHANGE_BATCH_SHIFT_PAGE;
    	}else if(jobChangeType.equals(FlowBusTypeConstant.FLOW_JOBSHIFT_PROMOTEB)){
    		//查询当前单位编制情况
    		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
    		OrgInfo org = orgInfoService.findUniqueBy("organ.id", x.getId());
    		if(org!=null){
    			OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", org.getId());
    			if(orgFormation!=null){
    				BeanUtils.copyPropertiesIgnoreNull(orgFormation, collect);
    				collect.setId(null);
    			}
    		}
    		model.addAttribute("d", collect);
    		
        	return JOBCHANGE_BATCH_PROMOTE_ADD_PAGE;
    	}else{
    		throw new BusinessException("不是正确的职务变动类型!");
    	}
    			
    }
    
    
    /**
	 * @Title: jobChangeCollectSave
	 * @Description: 职务变动-保存职务变动申请单,并且跳转到对应的详细表单中
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping(value = "/operatePromoteFlow")
	public AjaxResult jobChangeCollectSave(JobShiftCollect temp, String jobChangeType,String opinion, String result) {
		DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
		AjaxResult ajaxResult = new AjaxResult(true);
		String examineFilePath = temp.getExamineFilePath();
		String personInfoFilePath = temp.getPersonInfoFilePath();
		try {
			if(StringUtils.isNotBlank(temp.getId())){
				//更新
				JobShiftCollect jobShiftCollect = jobShiftCollectService.get(temp.getId());
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				
				BeanUtils.copyPropertiesIgnoreNull(temp, jobShiftCollect);
				DictUtils.operationCodeInfo(jobShiftCollect);//将CodeInfo中id为空的属性 设置为null

				// 是否在第七个步骤中上传了考核材料
				if (jobShiftCollect.getFlowRecord() != null
				        && jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP7)) {
					if (examineFilePath.equals("[]")) {
						throw new BusinessException("请上传考核以及测评材料文件!");
					} else {
						jobShiftCollect.setExamineFilePath(examineFilePath);
					}
				}
				// 是否在第12个步骤中上传了公示人员信息文件
				if (jobShiftCollect.getFlowRecord() != null
				        && jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP12)) {
					if (personInfoFilePath.equals("[]")) {
						throw new BusinessException("请上传任职文件!");
					} else {
						jobShiftCollect.setPersonInfoFilePath(personInfoFilePath);
					}
				}
				// 是否在第13个步骤中是否填写时间信息
				if (jobShiftCollect.getFlowRecord() != null
				        && jobShiftCollect.getFlowRecord().getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP13)) {
                    AjaxResult ajaxResult1 = validDownload(jobShiftCollect.getId());
                    if(!ajaxResult1.getSuccess()){
                        throw new BusinessException(ajaxResult1.getMessage());
                    }
                }
				if (StringUtils.isBlank(result) || (!FlowRecord.PASS.equals(result)
				        && !FlowRecord.NOPASS.equals(result)&& !FlowRecord.STOP.equals(result))) { throw new BusinessException("审批结果信息不正确！"); }
				// 审批职务变动晋升
				jobShiftCollectService.updatePromoteFlowB(jobShiftCollect, opinion, result);
				ajaxResult.setMessage("保存成功！");
				

			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				OrgInfo org = orgInfoService.findUniqueBy("organ.id", x.getId());
				if(org!=null){
					OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", org.getId());
					if(orgFormation!=null){
						BeanUtils.copyPropertiesIgnoreNull(orgFormation, temp);
						temp.setId(null);
						temp.setCreater(null);
					}
				}
				jobShiftCollectService.updatePromoteFlowB(temp, opinion, result);
			}
			ajaxResult.setData(temp.getId());
			ajaxResult.setMessage("保存成功！");
		} catch (BusinessException e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！"+e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 通过collectid获取人员列表
	 * @param id
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/jobShiftList/{id}")
	@ResponseBody
	public Page<JobShiftBVO> getJobShiftList(@PathVariable(name = "id",required = true) String id,Integer page, Integer limit){
		Page<JobShiftBVO> jobShiftPage=jobShiftBService.getShiftByCollectId(id,page,limit);
		return jobShiftPage;
	}

	/**
	 * 选择人员,将选中的人员保存
	 * @param ids
	 * @param id
	 * @return
	 */
	@RequestMapping("/addJobShiftBatch")
	@ResponseBody
	public AjaxResult addJobShiftBatch(String ids,String id){
		
		AjaxResult ajaxResult = new AjaxResult(true);
		JobShiftCollect jobShiftCollect = this.jobShiftCollectService.get(id);

		try {
			if (StringUtils.isBlank(ids)) {
				throw new BusinessException("请选择对应的人员!");
			}else{
				String[] idArray = ids.split(",");
				for(int i=0;i<idArray.length;i++){
					JobShiftB jobShiftB=new JobShiftB();
					jobShiftB.setJobShiftCollect(jobShiftCollect);
					jobShiftB.setServant(servantService.get(idArray[i]));
					this.jobShiftBService.save(jobShiftB);
				}
			}
			ajaxResult.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！");
		}
		return ajaxResult;
		
	}

	/**
	 * 删除选中人员
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteJobShiftBatch")
	@ResponseBody
	public AjaxResult deleteJobShiftBatch(String ids){
		
		AjaxResult ajaxResult = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(ids)) {
				throw new BusinessException("请选择对应的人员!");
			}else{
				String[] idArray = ids.split(",");
				for(int i=0;i<idArray.length;i++){
					JobShiftB jobShiftB = new JobShiftB();
					jobShiftB.setId(idArray[i]);
					this.jobShiftBService.delete(jobShiftB);
				}
			}
			ajaxResult.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！");
		}
		return ajaxResult;
		
	}


	/**
	 * 获取原职务下拉菜单列表数据
	 * @param servantId
	 * @return
	 */
	@RequestMapping("/activeAndNoChangeJob/{servantId}")
	@ResponseBody
	public List<PostVO> getAllActiveAndNoChangeJob(@PathVariable(name = "servantId",required = true) String servantId){
    	//所有在职职位
		List<Post> allPost = this.postService.getAllPost(servantId);
		//获取所有正在处理的职位的id,
		List<String> jobShiftIds=this.jobShiftBService.getHandledPostIds(servantId);
		List<PostVO> postVOS = new ArrayList<>();
		allPost.forEach(post -> {
			//假如流程中没有包含此post
			if(jobShiftIds==null||!jobShiftIds.contains(post.getId())){
				postVOS.add(new PostVO(post));
			}
		});
		return postVOS;
	}


	/**
	 * 修改职务变动晋升
	 * @param jobShift
	 * @return
	 */
	@RequestMapping("/editJobShiftPromote")
	@ResponseBody
	public AjaxResult editJobShiftPromote(JobShiftB jobShift){
		AjaxResult ajaxResult = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(jobShift.getId())){
				JobShiftB dbJobShiftB = jobShiftBService.get(jobShift.getId());
				BeanUtils.copyPropertiesIgnoreNull(jobShift,dbJobShiftB);
				jobShiftBService.update(dbJobShiftB);
				ajaxResult.setMessage("保存成功！");
			}else{
				throw new BusinessException("不是有效的记录,id为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！");
		}
		return ajaxResult;
	}

	/**
	 * 下载电子介绍信和通知文件,打包下载
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/downLoadLetter")
	public String downLoadLetter(String id,HttpServletRequest request, HttpServletResponse response){

		if(org.apache.commons.lang.StringUtils.isBlank(id)){
			throw new BusinessException("打印信息不正确！");
		}
		JobShiftCollect jobShiftCollect = this.jobShiftCollectService.get(id);
		List<JobShiftB> list = this.jobShiftBService.getShiftByCollectId(id);
		String ids=null;
		if(jobShiftCollect.getIsDownLoad()!=null&&jobShiftCollect.getIsDownLoad()==1){
			StringBuilder sb=new StringBuilder();
			for (JobShiftB jobShiftB : list) {
				sb.append(jobShiftB.getId() + ",");
			}
			ids = sb.toString();
		}else{
			ids=this.jobShiftCollectService.createFiles(list,jobShiftCollect,id,request);
		}

		if(com.wondersgroup.framework.util.StringUtils.isNotBlank(ids.toString())){
			return "redirect:/ofcflow/material/downLoad?name=rztz&"+"id="+ids.substring(0,ids.length()-1);
		}
		throw new BusinessException("没有对应的文件!");
	}

	/**
	 * 验证是否可以下载(人员是否都已经填了任职时间)
	 * @param id
	 * @return
	 */
	@RequestMapping("/validDownload")
	@ResponseBody
	public AjaxResult validDownload(String id){
		AjaxResult ajaxResult = new AjaxResult(true);
		try {
			List<JobShiftB> list = this.jobShiftBService.getShiftByCollectId(id);
			if(list.size()==0){
				throw new BusinessException("没有对应的人员！");
			}
			for (JobShiftB jobShiftB : list) {
				if(jobShiftB.getPostChangeDate()==null){
					throw new BusinessException("人员:" + jobShiftB.getServant().getName() + "任职时间未填写");
				}
			}
			ajaxResult.setMessage("成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("失败！"+e.getMessage());
		}
		return ajaxResult;
	}



	/**
	 * @Title: flow
	 * @Description: 流程审批列表页面
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType", FlowBusTypeConstant.FLOW_JOBSHIFTB_ALL);
		return RECRUIT_EMPLOYPLAN_FLOW;
	}
	
	/**
	 * @Title: jobChangeFlow
	 * @Description: 流程审批页面跳转,根据流程类型不同,分别重定向到不同的页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/jobChangeFlow")
	public String jobChangeFlow(Model model, String id) {
		FlowRecord flow = flowRecordService.load(id);
		String busType = flow.getBusType();
		//根据流程的bustype跳转到不同的页面
		switch (busType) {
			case FlowBusTypeConstant.FLOW_JOBSHIFT_PROMOTEB:
				return "forward:promoteFlowPage/" + id;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_DEMOTE:
				return "forward:demoteFlowPage/" + id;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_DEPOSE:
				return "forward:deposeFlowPage/" + id;
			case FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT:
				return "forward:demoteFlowPage/" + id;
			default:
				throw new BusinessException("无法找到指定页面!");
		}
	}
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动--晋升修改表单页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"/promote/{id}","/promote/{id}/{isStep13}"}, method = {RequestMethod.GET})
	public String jobChangePromote(@PathVariable(value = "id") String id, Model model,@PathVariable(value = "isStep13", required = false) Integer isStep13 ) {

		JobShiftB jobShiftB = jobShiftBService.get(id);
		model.addAttribute("servant", jobShiftB.getServant());
		JobLevel jobLevel = jobLevelService.getJobLevelByServantId(jobShiftB.getServant().getId());
		Set<String> jobLevelSet = new HashSet<>(Arrays.asList("141","142","150","160"));
//		jobLevelSet.remove(jobShiftB.getServant().getNowJobLevel().getCode());
		jobShiftB.getServant().getNowJobLevel().getCode();
		String j = Arrays.toString(jobLevelSet.toArray());
		j = j.replace(" ", "");
		model.addAttribute("jobLevelArray", j.substring(1,j.length()-1));
		model.addAttribute("jobLevel", jobLevel);
		model.addAttribute("jobShift", jobShiftB);
		if(null!=isStep13){
			return JOBCHANGE_PROMOTE_TIME_EDIT;
		}
		return JOBCHANGE_PROMOTE_PAGE_EDIT;
	}

	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动--晋升表单查看页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/promoteView/{id}", method = {RequestMethod.GET})
	public String jobChangePromoteView(@PathVariable(value = "id") String id, Model model) {

		JobShiftB jobShiftB = jobShiftBService.get(id);
		model.addAttribute("servant", jobShiftB.getServant());
		JobLevel jobLevel = jobLevelService.getJobLevelByServantId(jobShiftB.getServant().getId());
		Set<String> jobLevelSet = new HashSet<>(Arrays.asList("141","142","150","160"));
//		jobLevelSet.remove(jobShiftB.getServant().getNowJobLevel().getCode());
		jobShiftB.getServant().getNowJobLevel().getCode();
		String j = Arrays.toString(jobLevelSet.toArray());
		j = j.replace(" ", "");
		model.addAttribute("jobLevelArray", j.substring(1,j.length()-1));
		model.addAttribute("jobLevel", jobLevel);
		model.addAttribute("jobShift", jobShiftB);
		return JOBCHANGE_PROMOTE_VIEW;
	}

	/**
	 * @Title: demoteFlowPage
	 * @Description: 晋升流程审批页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/promoteFlowPage/{id}")
	public String promoteFlowPage(@PathVariable(value = "id") String id, Model model) {
		
		FlowRecord flow = flowRecordService.load(id);
		// 获取业务对象
		JobShiftCollect collect = this.jobShiftCollectService.get(flow.getBusId());
		// 获取操作的人员对象
		model.addAttribute("d", collect);
		model.addAttribute("isShowTable", JobShiftCollect.allSteps.indexOf(flow.getOperationCode())>=6);
		// 第7部时显示文件上传按钮
		model.addAttribute("isStep7", flow.getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP7));
		model.addAttribute("isStep12", flow.getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP12));
		model.addAttribute("isStep13", flow.getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP13));
		//假如是第一步的跳转到表单页面
		if (flow.getOperationCode().equals(JobShiftCollect.JOBSHIFTB_PROMOTE_STEP1)) { return JOBCHANGE_BATCH_PROMOTE_ADD_PAGE; }
		//跳转到流程审批页面
		return JOBCHANGE_BATCH_PROMOTE_FLOW_PAGE;
	}
	
	
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动--降职
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/demote/{servantId}/post/{postId}", method = {
	        RequestMethod.GET
	})
	public String jobChangeDemote(@PathVariable(value = "servantId") String servantId,
	        @PathVariable(value = "postId") String postId, Model model) {
		
		Servant servant = servantService.get(servantId);
		Post post = postService.get(postId);
		model.addAttribute("servant", servant);
		model.addAttribute("post", post);
		model.addAttribute("isShift", false);
		model.addAttribute("head", "降职");
		Set<String> jobLevelSet = new HashSet<>(Arrays.asList("141","142","150","160"));
		jobLevelSet.remove(servant.getNowJobLevel().getCode());
		String j = Arrays.toString(jobLevelSet.toArray());
		j = j.replace(" ", "");
		model.addAttribute("jobLevelArray", j.substring(1,j.length()-1));
		return JOBCHANGE_BATCH_DEMOTE_PAGE;
	}
	
	/**
	 * @Title: demoteFlowPage
	 * @Description: 降职流程审批页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/demoteFlowPage/{id}")
	public String demoteFlowPage(@PathVariable(value = "id") String id, Model model) {
		
		FlowRecord flow = flowRecordService.load(id);
		// 获取业务对象
		JobShiftB jobShift = this.jobShiftBService.get(flow.getBusId());
		// 获取操作的人员对象
		Servant servant = jobShift.getServant();
		Post post = jobShift.getPrePost();
		model.addAttribute("post", post);
		model.addAttribute("servant", servant);
		model.addAttribute("jobShift", jobShift);
		model.addAttribute("isShift", flow.getBusType().equals(FlowBusTypeConstant.FLOW_JOBSHIFT_SHIFT));
		if (flow.getOperationCode().equals(JobShift.JOBSHIFT_DEMOTE_STEP1)) { return JOBCHANGE_BATCH_DEMOTE_PAGE; }
		return JOBCHANGE_BATCH_DEMOTE_FLOW_PAGE;
	}
	
	/**
	 * @Title: jobChangeSave
	 * @Description: 职务变动-降职 流程操作
	 * @param result 审批结果 opinion 审批意见 jobShift 表单对象
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping(value = "/operateDemoteFlow")
	public AjaxResult jobChangeDemoteSave(JobShiftB jobShift, String opinion, String result,boolean isShift) {

		AjaxResult ajaxResult = new AjaxResult(true);
		if (!StringUtils.isBlank(jobShift.getId())) {
			JobShiftB jobShiftDB = this.jobShiftBService.get(jobShift.getId());
			BeanUtils.copyPropertiesIgnoreNull(jobShift, jobShiftDB);
			DictUtils.operationCodeInfo(jobShift);//将CodeInfo中id为空的属性 设置为null
			jobShift = jobShiftDB;
		}else{
			DictUtils.operationCodeInfo(jobShift);//将CodeInfo中id为空的属性 设置为null
		}

		try {
			if (StringUtils.isBlank(result) || (!FlowRecord.PASS.equals(result)
			        && !FlowRecord.NOPASS.equals(result))) { throw new BusinessException("审批结果信息不正确！"); }
			// 审批职务变动降职
			jobShiftBService.updateDemoteFlow(jobShift, opinion, result, isShift);
			ajaxResult.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！");
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 职务变动--免职表单页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/depose/{servantId}/post/{postId}", method = {
	        RequestMethod.GET
	})
	public String jobChangeDepose(@PathVariable(value = "servantId") String servantId,
	        @PathVariable(value = "postId") String postId, Model model) {
		Servant servant = servantService.get(servantId);
		Post post = postService.get(postId);
		model.addAttribute("servant", servant);
		model.addAttribute("post", post);
		Set<String> jobLevelSet = new HashSet<>(Arrays.asList("141","142","150","160"));
		jobLevelSet.remove(servant.getNowJobLevel().getCode());
		String j = Arrays.toString(jobLevelSet.toArray());
		j = j.replace(" ", "");
		model.addAttribute("jobLevelArray", j.substring(1,j.length()-1));
		return JOBCHANGE_BATCH_DEPOSE_PAGE;
	}
	
	/**
	 * @Title: deposeFlowPage
	 * @Description: 免职流程审批页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/deposeFlowPage/{id}")
	public String deposeFlowPage(@PathVariable(value = "id") String id, Model model) {
		
		FlowRecord flow = flowRecordService.load(id);
		// 获取业务对象
		JobShiftDepose jobShiftDepose = this.jobShiftDeposeService.get(flow.getBusId());
		// 获取操作的人员对象
		Servant servant = jobShiftDepose.getServant();
		Post post = jobShiftDepose.getPost();
		model.addAttribute("post", post);
		model.addAttribute("servant", servant);
		model.addAttribute("jobShiftDepose", jobShiftDepose);
		//假如是流程第一步,那么跳转到表单页面
		if (flow.getOperationCode().equals(JobShift.JOBSHIFT_DEMOTE_STEP1)) { return JOBCHANGE_BATCH_DEPOSE_PAGE; }
		return JOBCHANGE_BATCH_DEPOSE_FLOW_PAGE;
	}
	
	/**
	 * @Title:
	 * @Description: 职务变动-免职 流程操作
	 * @param isSubmit 是否提交 result 审批结果 opinion 审批意见 jobShift 表单对象
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping(value = "/operateDeposeFlow")
	public AjaxResult jobChangeDeposeSave(JobShiftDepose jobShiftDepose, String opinion, String result,
	        String isSubmit) {
		
		AjaxResult ajaxResult = new AjaxResult(true);
		if (!StringUtils.isBlank(jobShiftDepose.getId())) {
			JobShiftDepose jobShiftDB = this.jobShiftDeposeService.get(jobShiftDepose.getId());
			BeanUtils.copyPropertiesIgnoreNull(jobShiftDepose, jobShiftDB);
			DictUtils.operationCodeInfo(jobShiftDepose);//将CodeInfo中id为空的属性 设置为null
			jobShiftDepose = jobShiftDB;
		}else{
			DictUtils.operationCodeInfo(jobShiftDepose);//将CodeInfo中id为空的属性 设置为null
		}

		try {
			if (StringUtils.isBlank(result) || (!FlowRecord.PASS.equals(result)
			        && !FlowRecord.NOPASS.equals(result))) { throw new BusinessException("审批结果信息不正确！"); }
			// 审批职务变动降职
			jobShiftDeposeService.updateDeposeFlow(jobShiftDepose, opinion, result);
			ajaxResult.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("保存失败！");
		}
		return ajaxResult;
	}
	
	/**
	 * @Title: jobChangeIndex
	 * @Description: 轮岗
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/shift/{servantId}/post/{postId}", method = {
	        RequestMethod.GET
	})
	public String jobChangeShift(@PathVariable(value = "servantId") String servantId,
	        @PathVariable(value = "postId") String postId, Model model) {
		
		Servant servant = servantService.get(servantId);
		Post post = postService.get(postId);
		model.addAttribute("servant", servant);
		model.addAttribute("post", post);
		model.addAttribute("isShift", true);
		model.addAttribute("head", "轮岗");
		// 和降职使用同一个页面
		Set<String> jobLevelSet = new HashSet<>(Arrays.asList("141","142","150","160"));
		jobLevelSet.remove(servant.getNowJobLevel().getCode());
		String j = Arrays.toString(jobLevelSet.toArray());
		j = j.replace(" ", "");
		model.addAttribute("jobLevelArray", j.substring(1,j.length()-1));
		return JOBCHANGE_BATCH_DEMOTE_PAGE;
	}
	


	public static String getCardNoView(String cardNo) {

		if (com.wondersgroup.framework.util.StringUtils.isBlank(cardNo)) {
			return "";
		} else {
			if (cardNo.length() <= 4) {
				return "XXXX";
			} else {
				return com.wondersgroup.framework.util.StringUtils.substring(cardNo, 0, (cardNo.length() - 4)) + "XXXX";
			}
		}
	}
	
}
