/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantSummaryController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 录用汇总和流程控制器
 * 创建人: tzy   
 * 创建时间: 2018年7月19日 下午3:18:14 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月19日 下午3:18:14 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantRelationReport;
import com.wondersgroup.human.bo.ofcflow.DraftServantReport;
import com.wondersgroup.human.service.ofcflow.DraftServantRelationReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.DraftServantReportVO;

/**
 * 
 * @ClassName: DraftServantReportResultController 
 * @Description: 录用汇总控制器
 * @author: youyd
 * @date: 2018年8月30日 下午6:38:55
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("ofcflow/draftServantReportResult")
public class DraftServantReportResultController extends GenericController {
	
	//列表
	private final static String DRAFT_SERVANT_REPORT_LIST="models/ofcflow/draftServant/draftServantReportResultView";
	//详情页
	private final static String DRAFT_SERVANT_REPORT_DETAIL="models/ofcflow/draftServant/draftServantReportResultDetail";
	
	@Autowired
	private DraftServantReportService draftServantReportService;
	
	@Autowired
	private DraftServantService draftServantService;
	
	@Autowired
	private DraftServantRelationReportService draftServantRelationReportService;
	
	@RequestMapping(value="/index")
	public String draftServantListResultListView(){
		return DRAFT_SERVANT_REPORT_LIST;
	}
	
	
	@RequestMapping(value="/detail")
	public String draftServantListResultDetailView(Model model,String id){
		DraftServantReport draftServantReport = this.draftServantReportService.get(id);
		model.addAttribute("dsr", draftServantReport);
		String servantIds = this.draftServantRelationReportService.getServantIdByReport(id);
		model.addAttribute("ids",servantIds);
		return DRAFT_SERVANT_REPORT_DETAIL;
	}
	
	/**
	 * 
	 * @Title: getDraftServantReport 
	 * @Description: 获取汇总报告 表单列表数据
	 * @return
	 * @return: List<DraftServantReport>
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Page<DraftServantReportVO> getDraftServantReport(Model model,Integer limit,Integer page,String serialNumber,String signer){
		StringBuilder hql=new StringBuilder();
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		hql.append("from DraftServantReport where removed=:removed ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		if(serialNumber!=null&&!serialNumber.equals("")){
			hql.append( " and serialNumber=:serialNumber");
			queryParameteritem=new QueryParameter("serialNumber", serialNumber);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("serialNumber", serialNumber);
		}
		if(signer!=null&&!signer.equals("")){
			hql.append( " and signer=:signer");
			queryParameteritem=new QueryParameter("signer", signer);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("signer", signer);
		}
		Page<DraftServantReport> pageResult = draftServantReportService.findByHQL(hql.toString(), listqueryparameter, page, limit);
		List<DraftServantReport> results = pageResult.getResult();
		List<DraftServantReportVO> resultsVO=new ArrayList<>();
		for (DraftServantReport draftServantReport : results) {
			DraftServantReportVO dsrvo=new DraftServantReportVO(draftServantReport);
			resultsVO.add(dsrvo);
		}
		Page<DraftServantReportVO> pageVO=new Page<>(pageResult.getStart(), pageResult.getCurrentPageSize(), pageResult.getTotalSize(), pageResult.getPageSize(), resultsVO);
		return pageVO;
	}
	
	@ResponseBody
	@RequestMapping("/agreeSummary")
	public AjaxResult agreeSummary(String id){
/*		try {
			draftServantReportService.addProbationServant(id);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("公务员录用", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE,
					getMessage("system.error", new String[] { ex.getMessage() }));
		}*/
		try {
			//市局批复同意汇总表单
			draftServantReportService.updateAgreeServantReport(id);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("公务员录用", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE,
					getMessage("system.error", new String[] { ex.getMessage() }));
		}
	}
	
	
	/**
	 * 电子介绍信
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportDzjsx")
    public @ResponseBody void exportDzjsx(String id,HttpServletRequest request,HttpServletResponse response){
			Calendar calendar = Calendar.getInstance();// 取当前日期。
			DraftServant draftServant = this.draftServantService.get(id);
			DraftServantRelationReport reportByServant = this.draftServantRelationReportService.getReportByServant(id);
			String signer = reportByServant.getReport().getSigner();
			//获得数据  
            Map<String, Object> map = new HashMap<String, Object>(); 
            map.put("year", DateUtils.getYear());
            map.put("month",DateUtils.getMonth());
            map.put("day",DateUtils.getDay()); 
            map.put("name", draftServant.getName());
            map.put("draftUnitName", draftServant.getDraftUnitName());
            map.put("signer", signer);
            map.put("probationStartTime", draftServant.getProbationStartTime());
            try {
                WordUtils.exportMillCertificateWord(request,response,map,draftServant.getName(),"dzjsx.ftl");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }    
    }
	
}
