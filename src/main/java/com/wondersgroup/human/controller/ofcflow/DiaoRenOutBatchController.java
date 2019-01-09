/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaoRenOutBatchController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 调任 批量转出
 * 创建人: tzy   
 * 创建时间: 2018年12月19日 上午10:54:58 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月19日 上午10:54:58 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOut;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOutBatch;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutBatchService;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutService;
import com.wondersgroup.human.vo.ofcflow.DiaoRenOutBatchVO;
import com.wondersgroup.human.vo.ofcflow.DiaoRenOutVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: DiaoRenOutBatchController 
 * @Description: 调任 批量转出
 * @author: tzy
 * @date: 2018年12月19日 上午10:54:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/diaorenOutB")
public class DiaoRenOutBatchController extends GenericController{
	@Autowired
	private DiaoRenOutService diaoRenOutService;
	@Autowired
	private DiaoRenOutBatchService diaoRenOutBatchService;
	
	/**
	 * 转出情况列表
	 */
	private final static String DIAOREN_OUT_LIST = "models/ofcflow/diaoRenB/outList";
	/**
	 * 转出情况编辑页面
	 */
	private final static String DIAOREN_OUT_EDIT = "models/ofcflow/diaoRenB/outEdit";
	/**
	 * 转出情况流程页面
	 */
	private final static String DIAOREN_OUT_FLOW = "models/ofcflow/diaoRenB/outFlow";
	/**
	 * 选择批量转任人员列表
	 */
	public final static String DIAOREN_OUT_SERVANT_LIST = "models/ofcflow/diaoRenB/outServantList";
	/**
	 * 转任人员流程处理页面
	 */
	public final static String DIAOREN_OUT_BATCH_FLOW = "models/ofcflow/diaoRenB/outBatchFlow";
	/**
	 * 选择转出单位页面
	 */
	private final static String DIAOREN_OUT_SELECT_ORG = "models/ofcflow/diaoRenB/outSelectOrg";
	
	/**
	 * @Title: index 
	 * @Description: 转出情况列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return DIAOREN_OUT_LIST;
	}
	/**
	 * @Title: selectOrg 
	 * @Description: 选择转出单位页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/selectOrg")
	public String selectOrg() {
		return DIAOREN_OUT_SELECT_ORG;
	}
	/**
	 * @Title: servantList 
	 * @Description: 选择转任人员
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantList")
	public String servantList(Model model,String id,String orgId) {
		if(StringUtils.isNotBlank(orgId)){
			DiaoRenOutBatch z = new DiaoRenOutBatch();
			OrganNode org = new OrganNode();
			org.setId(orgId);
			z.setSourceOrgan(org);
			model.addAttribute("d", z);
		}else{
			DiaoRenOutBatch z = diaoRenOutBatchService.get(id);
			model.addAttribute("d", z);
		}
		return DIAOREN_OUT_SERVANT_LIST;
	}
	/**
	 * @Title: view 
	 * @Description: 查看详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/view")
	public String view(Model model,String id) {
		DiaoRenOutBatch z = diaoRenOutBatchService.get(id);
		model.addAttribute("d", z);
		return DIAOREN_OUT_BATCH_FLOW;
	}
	/**
	 * @Title: edit 
	 * @Description: 人员信息编辑页面
	 * @param	id 转出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantEdit")
	public String servantEdit(String id,Model model) {
		DiaoRenOut z = diaoRenOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		return DIAOREN_OUT_EDIT;
	}
	/**
	 * @Title: toFlow 
	 * @Description: 流程页面
	 * @param	id 转出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toFlow")
	public String toFlow(String id,Model model) {
		DiaoRenOut z = diaoRenOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		model.addAttribute("isFlow", true);
		return DIAOREN_OUT_FLOW;
	}
	/**
	 * @Title: servantView 
	 * @Description: 人员信息查看页面
	 * @param	id 转出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantView")
	public String servantView(String id,Model model,String status) {
		DiaoRenOut z = diaoRenOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("status", status);
		model.addAttribute("s", z.getServant());
		return DIAOREN_OUT_FLOW;
	}
	/**
	 * @Title: intoList 
	 * @Description: 调任转出情况列表
	 * @param model
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DiaoRenOutBatchVO>
	 */
	@Log(title = "查询调出列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/outList")
	public Page<DiaoRenOutBatchVO> outList(Model model,String sourceOrgan,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DiaoRenOutBatch where removed=:removed and creater=:creater");
		listqueryparameter.add(new QueryParameter("removed", false));
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
		if(StringUtils.isNotBlank(sourceOrgan)){//转出单位
			hql.append( " and sourceOrgan.id = :sourceOrgan");
			listqueryparameter.add(new QueryParameter("sourceOrgan", sourceOrgan));
		}
		hql.append( " order by createTime desc");
		
		Page<DiaoRenOutBatchVO> pageInfo = diaoRenOutBatchService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: outServantList 
	 * @Description: 调任转出人员情况列表
	 * @param model
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DiaoRenOutVO>
	 */
	@Log(title = "查询调出人员列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/outServantList")
	public Page<DiaoRenOutVO> outServantList(Model model,Integer limit,Integer page,String id) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DiaoRenOut where removed=:removed and diaoRenOutBatch.id=:diaoRenOutBatch");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("diaoRenOutBatch", id));//按批次加载数据
		hql.append( " order by createTime desc");
		
		Page<DiaoRenOutVO> pageInfo = diaoRenOutService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 保存批次信息
	 * @param temp
	 * @return: AjaxResult
	 */
	@Log(title = "编辑调出信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(DiaoRenOutBatch temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				DiaoRenOutBatch post = diaoRenOutBatchService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				diaoRenOutBatchService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				if(temp.getSourceOrgan()==null||StringUtils.isBlank(temp.getSourceOrgan().getId())){
					throw new BusinessException("转出单位信息不能为空！");
				}
				temp.setId(null);
				temp.setStatus(DiaoRenOutBatch.STATUS_DIAOCHU_STATE);//流程状态，待提交
				
				diaoRenOutBatchService.saveOrUpdate(temp);
			}
			result.setData(temp.getId());
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
	 * @Title: delete 
	 * @Description: 删除
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "删除调出信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			diaoRenOutBatchService.remove(id);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * @Title: savePeople 
	 * @Description: 转出添加人员
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "新增调出人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/savePeople")
	public AjaxResult savePeople(DiaoRenOutBatch z,String servantIds){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(servantIds)){
				throw new BusinessException("添加人员信息获取失败！");
			}
			diaoRenOutBatchService.savePeople(z,servantIds);
			result.setData(z.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("添加失败！");
		}
		return result;
	}
	/**
	 * @Title: delete 
	 * @Description: 人员信息删除
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "删除调出人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/servantDelete")
	public AjaxResult servantDelete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(id)){
				throw new BusinessException("请选择待删除的人员！");
			}
			diaoRenOutService.deleteServant(id);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * @Title: save 
	 * @Description: 转出情况信息保存
	 * @param temp		转任信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑调出人员信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveThis")
	public AjaxResult saveThis(DiaoRenOut temp){
		AjaxResult result = new AjaxResult(true);
		try {
			DiaoRenOut post = diaoRenOutService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
			diaoRenOutService.saveOrUpdate(post);//保存
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
	 * @Title: operationFlow 
	 * @Description: 审批转出信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批调出流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(DiaoRenOutBatch temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(temp.getId())){
				throw new BusinessException("请先添加转任人员！");
			}
			DiaoRenOutBatch post = diaoRenOutBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
			diaoRenOutBatchService.saveFlow(post,opinion,r);//提交流程
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
