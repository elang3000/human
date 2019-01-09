/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProbationController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 试用期管理 控制器
 * 创建人: tzy   
 * 创建时间: 2018年7月25日 下午3:11:21 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月25日 下午3:11:21 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;
import com.wondersgroup.human.bo.ofcflow.ProbationTimeLength;
import com.wondersgroup.human.service.ofcflow.ProbationServantService;
import com.wondersgroup.human.service.ofcflow.ProbationTimeLengthService;
import com.wondersgroup.human.vo.ofcflow.ProbationServantVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: ProbationController 
 * @Description: 试用期管理 控制器
 * @author: tzy
 * @date: 2018年7月25日 下午3:11:21
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/probation")
public class ProbationServantController extends GenericController{
	/**
	 * 试用期名单列表
	 */
	private final static String PROBATION_LIST = "models/ofcflow/probation/probationLlist";
	/**
	 * 编辑试用期信息
	 */
	private final static String PROBATION_EDIT = "models/ofcflow/probation/probationEdit";
	/**
	 * 查看/审核 试用期信息
	 */
	private final static String PROBATION_VIEW = "models/ofcflow/probation/probationView";
	
	/**
	 * 管理试用期时长
	 */
	private final static String PROBATION_TIME_LENGTH = "models/ofcflow/probation/timeLength";

	/**
	 * 试用期管理流程待办列表
	 */
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	
	@Autowired
	private ProbationServantService probationServantService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private ProbationTimeLengthService probationTimeLengthService;
	
	/**
	 * @Title: index 
	 * @Description: 试用期名单列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return PROBATION_LIST;
	}
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","ProbationServant,CancelProbationServant");
		return RECRUIT_EMPLOYPLAN_FLOW;
	}
	/**
	 * @Title: timeLength 
	 * @Description: 管理试用期时长页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/timeLength")
	public String timeLength(Model model) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProbationTimeLength.class);
		List<ProbationTimeLength> list = probationTimeLengthService.findByCriteria(detachedCriteria);
		if(list!=null&&list.size()>0){
			model.addAttribute("probationDate",list.get(0).getProbationDate());
			model.addAttribute("remark",list.get(0).getRemark());
			model.addAttribute("id",list.get(0).getId());
		}else{
			model.addAttribute("probationDate",12);//默认为12
		}
		return PROBATION_TIME_LENGTH;
	}
	/**
	 * @Title: probationEdit 
	 * @Description: 试用期信息编辑页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		ProbationServant p = probationServantService.load(id);
		model.addAttribute("p", p);
		model.addAttribute("d", p.getDraftServant());
		return PROBATION_EDIT;
	}
	/**
	 * @Title: view 
	 * @Description: 试用期信息查看页面
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/view")
	public String view(String id, Model model) {
		ProbationServant p = probationServantService.load(id);
		model.addAttribute("p", p);
		model.addAttribute("d", p.getDraftServant());
		return PROBATION_VIEW;
	}
	
	/**
	 * @Title: probationServantList 
	 * @Description: 试用期名单列表
	 * @param model
	 * @param name		姓名查询条件
	 * @param isTimeOK	试用期状态	1：满  0：未满     默认查询试用期满的数据
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ProbationServantVO>
	 */
	@Log(title = "查询试用期列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/probationServantList")
	public Page<ProbationServantVO> probationServantList(Model model,String name,String isTimeOK,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ProbationServant where removed=:removed and draftServant.draftUnit.organ=:organNode ");
		listqueryparameter.add(new QueryParameter("removed", false));
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		listqueryparameter.add(new QueryParameter("organNode", x));
		if(StringUtils.isNotBlank(name)){
			hql.append( " and draftServant.name like :name");
			listqueryparameter.add(new QueryParameter("name", "%"+name+"%"));
		}
		//试用期开始一年为试用期满，未满一年为试用期未满
		if("0".equals(isTimeOK)){//未满
			hql.append(" and to_char(sysdate,'yyyy-MM-dd')<to_char(probationEndDate,'yyyy-MM-dd') ");
		}else{
			hql.append(" and to_char(sysdate,'yyyy-MM-dd')>=to_char(probationEndDate,'yyyy-MM-dd') ");
		}
		hql.append( " order by createTime desc");
		
		Page<ProbationServantVO> pageInfo = probationServantService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: timeSave 
	 * @Description: 保存试用期时长
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "保存试用期时长信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/timeSave")
	public AjaxResult timeSave(ProbationTimeLength temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){
				ProbationTimeLength servant = probationTimeLengthService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, servant);
				probationTimeLengthService.update(servant);//保存
			}else{
				probationTimeLengthService.save(temp);//保存
			}
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: save 
	 * @Description: 保存试用期信息
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "保存试用期信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ProbationServant temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
			ProbationServant servant = probationServantService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, servant);
			//先通过试用时长和试用期开始时间计算出 试用期结束时间，判断：如果不是取消录用，试用期结束时间未到，不能提交流程
			Date start = servant.getProbationStartDate();//试用期开始时间
			int le = servant.getProbationDate();//试用时长 月
			Calendar cal = Calendar.getInstance();
		    cal.setTime(start);//设置起时间
		    cal.add(Calendar.MONTH, le);//增加 试用时长月份
		    servant.setProbationEndDate(cal.getTime());//更新结束时间
			probationServantService.update(servant);//保存
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: workFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/workFlow")
	public String workFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.get(id);
		ProbationServant p = probationServantService.get(flow.getBusId());
		model.addAttribute("p", p);
		model.addAttribute("d", p.getDraftServant());
		if(ProbationServant.STATUS_EMPLOY_STATE==p.getStatus()){//如果是待提交
			return PROBATION_EDIT;
		}else{
			model.addAttribute("isFlow", true);
			return PROBATION_VIEW;
		}
	}
	/**
	 * @Title: operationFlow 
	 * @Description: 审批试用期信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批试用期流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ProbationServant temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		String isSubmit = request.getParameter("isSubmit");//如果值为1  则是提交 操作 ，提交操作才计算试用期结束时间
		try {
			
			
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r)&&!FlowRecord.STOP.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			ProbationServant servant = probationServantService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp,servant);
			DictUtils.operationCodeInfo(servant);//将CodeInfo中id为空的属性 设置为null
			
			if("1".equals(isSubmit)){
				servant.setProbationStatus(dictableService.loadCodeInfoById(servant.getProbationStatus().getId()));//设置考核结果字典
				
				//先通过试用时长和试用期开始时间计算出 试用期结束时间，判断：如果不是取消录用，试用期结束时间未到，不能提交流程
				Date start = servant.getProbationStartDate();//试用期开始时间
				int le = servant.getProbationDate();//试用时长 月
				Calendar cal = Calendar.getInstance();
			    cal.setTime(start);//设置起时间
			    cal.add(Calendar.MONTH, le);//增加 试用时长月份
			    servant.setProbationEndDate(cal.getTime());//更新结束时间
			}
			
			if("4".equals(servant.getProbationStatus().getCode())||"3".equals(servant.getProbationStatus().getCode())){//取消录用||考核不合格
				if("[]".equals(servant.getCancelLetterPath())){//关于取消XX同志公务员录用的请示 文件路径
					throw new BusinessException("请上传取消公务员录用的请示！");
				}
				if("[]".equals(servant.getCancelWrittenPath())){//考生试用期内取消录用的书面申请 文件路径
					throw new BusinessException("请上传取消录用的书面申请！");
				}
				if("[]".equals(servant.getCancelRecordsPath())){//新录用公务员取消录用审批备案表 文件路径
					throw new BusinessException("请上传取消录用审批备案表！");
				}
				if("3".equals(servant.getProbationStatus().getCode())){//如果是考核不合格，判断试用期是否结束
					if(servant.getProbationEndDate().after(new Date())){
						throw new BusinessException("试用期还未结束，暂时不能提交流程！");
					}
				}
				probationServantService.saveFlowCancel(servant,opinion,r);
			}else{//延长试用期||考核合格
				if(servant.getProbationEndDate().after(new Date())){
					throw new BusinessException("试用期还未结束，暂时不能提交流程！");
				}
				
			    probationServantService.saveFlow(servant,opinion,r);
			}
			
			result.setMessage("操作成功！");
		} catch (BusinessException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("操作失败！");
		}
		return result;
	}
}
