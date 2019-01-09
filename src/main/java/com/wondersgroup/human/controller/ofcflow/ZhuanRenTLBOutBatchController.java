/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBOutBatchController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 同类别转任 批量转出
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
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOut;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutBatch;
import com.wondersgroup.human.service.ofcflow.ZhuanRenTLBOutBatchService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenTLBOutService;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBOutBatchVO;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBOutVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: ZhuanRenTLBOutBatchController 
 * @Description: 同类别转任 批量转出
 * @author: tzy
 * @date: 2018年12月19日 上午10:54:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/zrtlbOutB")
public class ZhuanRenTLBOutBatchController extends GenericController{
	@Autowired
	private ZhuanRenTLBOutService zhuanRenTLBOutService;
	@Autowired
	private ZhuanRenTLBOutBatchService zhuanRenTLBOutBatchService;
	
	/**
	 * 转出情况列表
	 */
	private final static String ZHUANREN_OUT_LIST = "models/ofcflow/zhuanRenB/outList";
	/**
	 * 转出情况编辑页面
	 */
	private final static String ZHUANREN_OUT_EDIT = "models/ofcflow/zhuanRenB/outEdit";
	/**
	 * 转出情况流程页面
	 */
	private final static String ZHUANREN_OUT_FLOW = "models/ofcflow/zhuanRenB/outFlow";
	/**
	 * 选择批量转任人员列表
	 */
	public final static String ZHUANREN_OUT_SERVANT_LIST = "models/ofcflow/zhuanRenB/outServantList";
	/**
	 * 转任人员流程处理页面
	 */
	public final static String ZHUANREN_OUT_BATCH_FLOW = "models/ofcflow/zhuanRenB/outBatchFlow";
	/**
	 * 选择转出单位页面
	 */
	private final static String ZHUANREN_OUT_SELECT_ORG = "models/ofcflow/zhuanRenB/outSelectOrg";
	
	/**
	 * @Title: index 
	 * @Description: 转出情况列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return ZHUANREN_OUT_LIST;
	}
	/**
	 * @Title: selectOrg 
	 * @Description: 选择转出单位页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/selectOrg")
	public String selectOrg() {
		return ZHUANREN_OUT_SELECT_ORG;
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
			ZhuanRenTLBOutBatch z = new ZhuanRenTLBOutBatch();
			OrganNode org = new OrganNode();
			org.setId(orgId);
			z.setSourceOrgan(org);
			model.addAttribute("d", z);
		}else{
			ZhuanRenTLBOutBatch z = zhuanRenTLBOutBatchService.get(id);
			model.addAttribute("d", z);
		}
		return ZHUANREN_OUT_SERVANT_LIST;
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
		ZhuanRenTLBOutBatch z = zhuanRenTLBOutBatchService.get(id);
		model.addAttribute("d", z);
		return ZHUANREN_OUT_BATCH_FLOW;
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
		ZhuanRenTLBOut z = zhuanRenTLBOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		return ZHUANREN_OUT_EDIT;
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
		ZhuanRenTLBOut z = zhuanRenTLBOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		model.addAttribute("isFlow", true);
		return ZHUANREN_OUT_FLOW;
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
		ZhuanRenTLBOut z = zhuanRenTLBOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("status", status);
		model.addAttribute("s", z.getServant());
		return ZHUANREN_OUT_FLOW;
	}
	/**
	 * @Title: intoList 
	 * @Description: 同类别转任转出情况列表
	 * @param model
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ZhuanRenTLBOutBatchVO>
	 */
	@Log(title = "查询同类别转出列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/outList")
	public Page<ZhuanRenTLBOutBatchVO> outList(Model model,String sourceOrgan,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ZhuanRenTLBOutBatch where removed=:removed and creater=:creater");
		listqueryparameter.add(new QueryParameter("removed", false));
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
		if(StringUtils.isNotBlank(sourceOrgan)){//转出单位
			hql.append( " and sourceOrgan.id = :sourceOrgan");
			listqueryparameter.add(new QueryParameter("sourceOrgan", sourceOrgan));
		}
		hql.append( " order by createTime desc");
		
		Page<ZhuanRenTLBOutBatchVO> pageInfo = zhuanRenTLBOutBatchService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: outServantList 
	 * @Description: 同类别转任转出人员情况列表
	 * @param model
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ZhuanRenTLBOutVO>
	 */
	@Log(title = "查询同类别转出人员列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/outServantList")
	public Page<ZhuanRenTLBOutVO> outServantList(Model model,Integer limit,Integer page,String id) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ZhuanRenTLBOut where removed=:removed and zhuanRenTLBOutBatch.id=:zhuanRenTLBOutBatch");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("zhuanRenTLBOutBatch", id));//按批次加载数据
		hql.append( " order by createTime desc");
		
		Page<ZhuanRenTLBOutVO> pageInfo = zhuanRenTLBOutService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 保存批次信息
	 * @param temp
	 * @return: AjaxResult
	 */
	@Log(title = "保存同类别转出信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ZhuanRenTLBOutBatch temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ZhuanRenTLBOutBatch post = zhuanRenTLBOutBatchService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				zhuanRenTLBOutBatchService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				if(temp.getSourceOrgan()==null||StringUtils.isBlank(temp.getSourceOrgan().getId())){
					throw new BusinessException("转出单位信息不能为空！");
				}
				temp.setId(null);
				temp.setStatus(ZhuanRenTLBOutBatch.STATUS_ZHUANCHU_STATE);//流程状态，待提交
				
				zhuanRenTLBOutBatchService.saveOrUpdate(temp);
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
	@Log(title = "删除同类别转出信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			zhuanRenTLBOutBatchService.remove(id);
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
	@Log(title = "新增同类别转出人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/savePeople")
	public AjaxResult savePeople(ZhuanRenTLBOutBatch z,String servantIds){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(servantIds)){
				throw new BusinessException("添加人员信息获取失败！");
			}
			zhuanRenTLBOutBatchService.savePeople(z,servantIds);
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
	@Log(title = "删除同类别转出人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/servantDelete")
	public AjaxResult servantDelete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(id)){
				throw new BusinessException("请选择待删除的人员！");
			}
			zhuanRenTLBOutService.deleteServant(id);
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
	@Log(title = "编辑同类别转出人员信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveThis")
	public AjaxResult saveThis(ZhuanRenTLBOut temp){
		AjaxResult result = new AjaxResult(true);
		try {
			ZhuanRenTLBOut post = zhuanRenTLBOutService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
			zhuanRenTLBOutService.saveOrUpdate(post);//保存
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
	@Log(title = "审批同类别转出流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ZhuanRenTLBOutBatch temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(temp.getId())){
				throw new BusinessException("请先添加转任人员！");
			}
			ZhuanRenTLBOutBatch post = zhuanRenTLBOutBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
			zhuanRenTLBOutBatchService.saveFlow(post,opinion,r);//提交流程
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
