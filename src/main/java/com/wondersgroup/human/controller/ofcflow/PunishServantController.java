/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PunishServantController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月25日 上午11:00:01 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月25日 上午11:00:01 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.ofcflow;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.PunishServant;
import com.wondersgroup.human.dto.ofcflow.PunishServantQueryParam;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.PunishServantService;
import com.wondersgroup.human.vo.ofcflow.PunishServantVO;

/** 
 * @ClassName: PunishServantController 
 * @Description: 处分控制类
 * @author: lihao
 * @date: 2018年9月25日 上午11:00:01
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

@Controller
@RequestMapping("ofcflow/punish")
public class PunishServantController extends GenericController {
	@Autowired
	ServantService servantService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private PunishServantService punishServantService;
	
	@Autowired
	OrganNodeService organNodeService;

	// 页面路径--处分列表
	private final static String PUNISH_INDEX = "models/ofcflow/punish/index";
	
	// 页面路径--添加处分
	private final static String ADD_PUNISH = "models/ofcflow/punish/punish";
	
	// 页面路径--培训信息流程列表
	private final static String FLOW_LIST = "models/ofcflow/employPlan/flow";
	
	// 页面路径--培训信息流程审批页面
	private final static String PUNISH_FLOW = "models/ofcflow/punish/punishFlow";
	
	// 页面路径--培训信息详情页面
	private final static String PUNISH_VIEW = "models/ofcflow/punish/punishDetail";

		
	
	/**
	 * 处分列表
	 * 
	 * @Title: 
	 * @Description: 处分列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return PUNISH_INDEX;
	}
	
	/**
	 * 处分列表
	 * 
	 * @Title: 
	 * @Description: 处分添加页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/addPunish")
	public String addPunish(String id,Model model) {
		if(StringUtils.isNotBlank(id)){
			PunishServant punishServant = punishServantService.get(id);
			model.addAttribute("punishServant", punishServant);
			model.addAttribute("servant", punishServant.getServant());
			model.addAttribute("organ", punishServant.getOrgan());
		}
		OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		if(organ!=null){
			model.addAttribute("createOrganNodeId", organ.getId());
		}else{
			model.addAttribute("createOrganNodeId", "");
		}
		return ADD_PUNISH;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 申请列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PunishServantVO> pageList(PunishServantQueryParam param, Integer limit, Integer page) {
		Page<PunishServantVO> pageInfo = punishServantService.pageList(param, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 处分信息保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PunishServant temp) {
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException(" 处分人员信息不能为空！");
				}
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				punishServantService.save(temp);// 保存
			} else {
				PunishServant punishServant = punishServantService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, punishServant);
				punishServantService.update(punishServant);// 保存
			}
			result.setMessage("保存成功！");
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: remove 
	 * @Description: 	处分信息删除
	 * @param id		
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/remove")
	public AjaxResult remove(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			PunishServant punishServant = punishServantService.get(id);
			punishServantService.delete(punishServant);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","PunishServant");
		return FLOW_LIST;
	}
	
	/**
	 * @Title: trainFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/punishFlow")
	public String planFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		PunishServant punishServant = punishServantService.get(flow.getBusId());
		model.addAttribute("punishServant", punishServant);
		model.addAttribute("servant",punishServant.getServant());
		if(PunishServant.PUNISH_SERVANT_STEP1==punishServant.getStatus()){//如果是待提交
			return ADD_PUNISH;
		}else{
			return PUNISH_FLOW;
		}
	}
	
	/**
	 * @Title: operationFlow 
	 * @Description: 审批计划
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(PunishServant temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				punishServantService.savePunish(temp,opinion,r);
			} else {
				PunishServant punishServant = punishServantService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp,punishServant);
				DictUtils.operationCodeInfo(punishServant);//将CodeInfo中id为空的属性 设置为null
				punishServantService.savePunish(punishServant,opinion,r);
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
	
	/**
	 * @Title: planView 
	 * @Description: 录用计划详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/view")
	public String planView(Model model,String id) {
		PunishServant punishServant = punishServantService.get(id);
		model.addAttribute("punishServant",punishServant);
		model.addAttribute("servant", punishServant.getServant());
		return PUNISH_VIEW;//
	}
}
