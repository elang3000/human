/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ReferenceExchangeOutBatchController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow
 * 描述: TODO
 * 创建人: GP
 * 创建时间: 2018年12月20日 下午4:56:40
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2018年12月20日 下午4:56:40
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.controller.ofcflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchange;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOut;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOutBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBIntoBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOut;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutBatch;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutBatchService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeOutBatchVO;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeOutVO;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBOutBatchVO;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBOutVO;

/**
 * @ClassName: ReferenceExchangeOutBatchController
 * @Description: TODO
 * @author: GP
 * @date: 2018年12月20日 下午4:56:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("ofcflow/exchangeOutB")
public class ReferenceExchangeOutBatchController {
	
	/**
	 * 转出情况列表
	 */
	private final static String REFERENCEEXCHANGE_OUT_LIST = "models/ofcflow/exchangeoutbatch/outList";
	
	/**
	 * 转出情况编辑页面
	 */
	private final static String REFERENCEEXCHANGE_OUT_EDIT = "models/ofcflow/exchangeoutbatch/outEdit";
	
	/**
	 * 转出情况流程页面
	 */
	private final static String REFERENCEEXCHANGE_OUT_FLOW = "models/ofcflow/exchangeoutbatch/outFlow";
	
	/**
	 * 选择批量参公交流人员列表
	 */
	private final static String REFERENCEEXCHANGE_OUT_SERVANT_LIST = "models/ofcflow/exchangeoutbatch/outServantList";
	
	/**
	 * 参公交流人员流程处理页面
	 */
	private final static String REFERENCEEXCHANGE_OUT_BATCH_FLOW = "models/ofcflow/exchangeoutbatch/outBatchFlow";
	
	/**
	 * 选择转出单位页面
	 */
	private final static String REFERENCEEXCHANGE_OUT_SELECT_ORG = "models/ofcflow/exchangeoutbatch/outSelectOrg";
	
	@Autowired
	private ReferenceExchangeOutBatchService referenceExchangeOutBatchService;
	
	@Autowired
	private ReferenceExchangeOutService referenceExchangeOutService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;
	
	/**
	 * 人员转出首页
	 * @Title: outindex
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@RequestMapping("/outindex")
	public String outindex() {
		
		return REFERENCEEXCHANGE_OUT_LIST;
	}
	
	/**
	 * @Title: selectOrg
	 * @Description: 选择转出单位页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/selectOrg")
	public String selectOrg() {
		
		return REFERENCEEXCHANGE_OUT_SELECT_ORG;
	}
	
	/**
	 * @Title: servantList
	 * @Description: 选择参公交流转出人员
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantList")
	public String servantList(Model model, String id, String orgId) {
		
		if (StringUtils.isNotBlank(orgId)) {
			ReferenceExchangeOutBatch z = new ReferenceExchangeOutBatch();
			OrganNode org = organNodeService.load(orgId);
			z.setSourceOrgan(org);
			model.addAttribute("d", z);
			
		} else {
			ReferenceExchangeOutBatch z = referenceExchangeOutBatchService.get(id);
			model.addAttribute("d", z);
		}
		return REFERENCEEXCHANGE_OUT_SERVANT_LIST;
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
	public String view(Model model, String id) {
		
		ReferenceExchangeOutBatch z = referenceExchangeOutBatchService.get(id);
		model.addAttribute("d", z);
		return REFERENCEEXCHANGE_OUT_BATCH_FLOW;
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
	public String workFlow(Model model, String id) {
		
		FlowRecord flow = flowRecordService.get(id);
		model.addAttribute("introductionName", "参公交流介绍信");
		List<ReferenceExchangeOut> exitems=referenceExchangeOutBatchService.itembatchItems(id);
		StringBuilder itemidssb=new StringBuilder();
		for (ReferenceExchangeOut referenceExchange : exitems) {
			itemidssb.append(referenceExchange.getId());
			itemidssb.append(",");
		}
		if(itemidssb.length()>1){
			itemidssb.deleteCharAt(itemidssb.length()-1);
			model.addAttribute("itemids", itemidssb.toString());
		}
		if ("ReferenceExchange_OutMgr".equals(flow.getBusType())) {// 跳转到转出流程页面
			ReferenceExchangeOutBatch z = referenceExchangeOutBatchService.load(flow.getBusId());
			model.addAttribute("d", z);
			model.addAttribute("isFlow", true);
			if (z.getStatus() == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE) {
				return REFERENCEEXCHANGE_OUT_SERVANT_LIST;
			} else {
				return REFERENCEEXCHANGE_OUT_BATCH_FLOW;
			}
		} else {
			ReferenceExchangeOutBatch z = referenceExchangeOutBatchService.load(flow.getBusId());
			model.addAttribute("d", z);
			model.addAttribute("isFlow", true);
			if (z.getStatus() == ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE) {
				return REFERENCEEXCHANGE_OUT_SERVANT_LIST;
			} else {
				return REFERENCEEXCHANGE_OUT_BATCH_FLOW;
			}
		}
	}
	
	/**
	 * @Title: edit
	 * @Description: 人员信息编辑页面
	 * @param id 转出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantEdit")
	public String servantEdit(String id, Model model) {
		
		ReferenceExchangeOut z = referenceExchangeOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		return REFERENCEEXCHANGE_OUT_EDIT;
	}
	
	/**
	 * @Title: toFlow
	 * @Description: 流程页面
	 * @param id 转出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toFlow")
	public String toFlow(String id, Model model) {
		
		ReferenceExchangeOut z = referenceExchangeOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		model.addAttribute("isFlow", true);
		return REFERENCEEXCHANGE_OUT_FLOW;
	}
	
	/**
	 * @Title: servantView
	 * @Description: 人员信息查看页面
	 * @param id 转出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantView")
	public String servantView(String id, Model model) {
		
		ReferenceExchangeOut z = referenceExchangeOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		return REFERENCEEXCHANGE_OUT_FLOW;
	}
	
	/**
	 * @Title: intoList
	 * @Description: 参公交流转出情况列表
	 * @param model
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ZhuanRenTLBOutBatchVO>
	 */
	@ResponseBody
	@RequestMapping("/outList")
	public Page<ReferenceExchangeOutBatchVO> outList(Model model, String sourceOrgan, Integer limit, Integer page) {
		
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter = new ArrayList<>();
		StringBuilder hql = new StringBuilder();
		hql.append("from ReferenceExchangeOutBatch where removed=:removed and creater=:creater");
		listqueryparameter.add(new QueryParameter("removed", false));
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));// 只能操作自己的数据
		if (StringUtils.isNotBlank(sourceOrgan)) {// 转出单位
			hql.append(" and sourceOrgan.id = :sourceOrgan");
			listqueryparameter.add(new QueryParameter("sourceOrgan", sourceOrgan));
		}
		hql.append(" order by createTime desc");
		
		Page<ReferenceExchangeOutBatchVO> pageInfo = referenceExchangeOutBatchService.findbyHQLforVO(hql.toString(),
		        listqueryparameter, page, limit);
		
		return pageInfo;
	}
	
	/**
	 * @Title: outServantList
	 * @Description: 参公交流转出人员情况列表
	 * @param model
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ZhuanRenTLBOutVO>
	 */
	@ResponseBody
	@RequestMapping("/outServantList")
	public Page<ReferenceExchangeOutVO> outServantList(Model model, Integer limit, Integer page, String id) {
		
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter = new ArrayList<>();
		StringBuilder hql = new StringBuilder();
		hql.append(
		        "from ReferenceExchangeOut where removed=:removed and referenceExchangeOutBatch.id=:referenceExchangeOutBatch");
		QueryParameter queryParameteritem = new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("referenceExchangeOutBatch", id));// 按批次加载数据
		hql.append(" order by createTime desc");
		
		Page<ReferenceExchangeOutVO> pageInfo = referenceExchangeOutService.findbyHQLforVO(hql.toString(),
		        listqueryparameter, page, limit);
		
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 保存批次信息
	 * @param temp
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ReferenceExchangeOutBatch temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				ReferenceExchangeOutBatch post = referenceExchangeOutBatchService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);// 将CodeInfo中id为空的属性 设置为null
				referenceExchangeOutBatchService.saveOrUpdate(post);// 保存
			} else {// 新增
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				if (temp.getSourceOrgan() == null || StringUtils
				        .isBlank(temp.getSourceOrgan().getId())) { throw new BusinessException("转出单位信息不能为空！"); }
				temp.setId(null);
				temp.setStatus(ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE);// 流程状态，待提交
				
				referenceExchangeOutBatchService.saveOrUpdate(temp);
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
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			referenceExchangeOutBatchService.remove(id);
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
	@ResponseBody
	@RequestMapping("/savePeople")
	public AjaxResult savePeople(ReferenceExchangeOutBatch z, String servantIds,String sourceOrganId) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(servantIds)) { throw new BusinessException("添加人员信息获取失败！"); }
			if (z.getSourceOrgan()==null) {
				OrganNode org = organNodeService.load(sourceOrganId);
				z.setSourceOrgan(org);
			}
			referenceExchangeOutBatchService.savePeople(z, servantIds);
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
	@ResponseBody
	@RequestMapping("/servantDelete")
	public AjaxResult servantDelete(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			if(id.contains(",")){//批量删除
				for(String i : id.split(",")){
					ReferenceExchangeOut post = referenceExchangeOutService.get(i);
					 referenceExchangeOutService.delete(post);
				}
				result.setMessage("删除成功！");
			}else{
				ReferenceExchangeOut post = referenceExchangeOutService.get(id);
				 referenceExchangeOutService.delete(post);
				result.setMessage("删除成功！");
			}
			
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
	 * @param temp 参公交流信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveThis")
	public AjaxResult saveThis(ReferenceExchangeOut temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			ReferenceExchangeOut post = referenceExchangeOutService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);// 将CodeInfo中id为空的属性 设置为null
			referenceExchangeOutService.saveOrUpdate(post);// 保存
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
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ReferenceExchangeOutBatch temp, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");// 审批意见
		String r = request.getParameter("result");// 审批结果
		try {
			ReferenceExchangeOutBatch post = referenceExchangeOutBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);// 将CodeInfo中id为空的属性 设置为null
			referenceExchangeOutBatchService.saveFlow(post, opinion, r);// 提交流程
			result.setMessage("操作成功！");
		} catch (BusinessException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @Title: printIntroduction
	 * @Description: 打印介绍信
	 * @param type 1:转入 2:转出
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/printIntroduction")
	public void printIntroduction(String id, String type, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			if (StringUtils.isBlank(id)) { throw new BusinessException("打印信息不正确！"); }
			Map<String, Object> params = new HashMap<>();// 所有参数
			List<String[]> dataList = new ArrayList<>();// 所有人员信息
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			ReferenceExchangeOut into = referenceExchangeOutService.get(id);// 批次下所有转任人员信息
			ReferenceExchangeOutBatch batch = into.getReferenceExchangeOutBatch();
			params.put("sourceOrgan", batch.getSourceOrgan().getName());// 源单位名称
			params.put("enterTheUnitDate", "");// 报道日期
			params.put("sumNumber", Number2CN.cvt(1, true));// 转任人数
			String[] p = new String[6];
			params.put("name", into.getServant().getName());
			params.put("targetOrgan", into.getGotoUnitName());// 目标单位名称
			p[0] = into.getServant().getName();// 转任人员名称
			p[1] = into.getServant().getSex() == null ? "" : into.getServant().getSex().getName();// 调任人员性别
			p[2] = batch.getSourceOrgan().getName();// 源单位名称
			p[4] = into.getServant().getNowPostName() == null ? "" : into.getServant().getNowPostName();// 原职务
			p[5] = into.getServant().getNowJobLevel() == null ? "" : into.getServant().getNowJobLevel().getName();// 原级别
			dataList.add(p);
			params.put("listData", dataList);// list数据需要用list标签
			
			params.put("now", sdf.format(new Date()));// 打印介绍信时间
			params.put("busType", "参公交流");// 转移原因
			
			Map<String, String> number = ofcFlowNumberService.executeNumber("ReferenceExchangeOUT", id);// 介绍信编号
			params.put("number", number.get("year") + "字第" + number.get("number"));// 介绍信编号
			// 编号转换为大写
			String cnYear = Number2CN.convert(number.get("year"));
			String cnNumber = Number2CN.convert(number.get("number"));
			params.put("cnNumber", cnYear + "字第" + cnNumber + "号");// 介绍信编号大写
			// 当前操作人
			params.put("userName", SecurityUtils.getPrincipal().getName());
			
			String savePath = request.getSession().getServletContext().getRealPath("/");
			String templet = savePath + "\\static\\templates\\introduce.xls";// 模板路径
			String path = savePath + "\\static\\templates\\" + id + ".xls";// 生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet, path, 2, null);// 替换模板数据 生成excel到tomcat服务器
			ExcelUtilsPOI.exceldown(path, params.get("sourceOrgan") + "参公交流介绍信" + ".xls", request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
