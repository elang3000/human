/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: SystemReportController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.resource
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年9月20日 下午6:44:13
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年9月20日 下午6:44:13
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.system.controller.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.util.DateUtils;
import com.wondersgroup.framework.resource.bo.SystemReport;
import com.wondersgroup.framework.resource.bo.SystemReportParam;
import com.wondersgroup.framework.resource.service.SystemReportService;
import com.wondersgroup.framework.util.StringUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

/**
 * @ClassName: SystemReportController
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年9月20日 下午6:44:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("system/report")
public class SystemReportController extends GenericController {
	
	private final static String SYSTEM_REPORT_INDEX = "resource/querySystemReportIndex",
	        SYSTEM_REPORT_CREATE_INDEX = "resource/createSystemReportIndex",
	        SYSTEM_REPORT_EDIT_INDEX = "resource/editSystemReportIndex",
	        SYSTEM_REPORT_PARAM_EDIT_INDEX = "resource/editSystemReportParamIndex",
	        SYSTEM_REPORT_SEARCH_INDEX = "resource/searchReportContentIndex";
	
	@Autowired
	SystemReportService systemReportService;
	
	@Autowired
	DataSource dataSource;
	
	@RequestMapping("/index")
	public String index() {
		
		return SYSTEM_REPORT_INDEX;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Page<SystemReport> page(String code, String name, Integer limit, Integer page) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SystemReport.class);
		
		if (StringUtils.isNotBlank(code)) {
			detachedCriteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		return systemReportService.findByCriteria(detachedCriteria, page, limit);
	}
	
	@RequestMapping("/create/index")
	public String create() {
		
		return SYSTEM_REPORT_CREATE_INDEX;
	}
	
	@RequestMapping("/create/save")
	@ResponseBody
	public AjaxResult saveCreate(SystemReport report) {
		
		try {
			systemReportService.save(report);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护系统报表参数", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/edit/index")
	public String edit(String id, Model model) {
		
		SystemReport report = systemReportService.get(id);
		model.addAttribute("report", report);
		return SYSTEM_REPORT_EDIT_INDEX;
	}
	
	@RequestMapping("/edit/save")
	@ResponseBody
	public AjaxResult saveEdit(SystemReport report) {
		
		try {
			SystemReport sourceReport = systemReportService.get(report.getId());
			sourceReport.setCode(report.getCode());
			sourceReport.setName(report.getName());
			sourceReport.setTemplate(report.getTemplate());
			sourceReport.setDescription(report.getDescription());
			systemReportService.update(sourceReport);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护系统报表参数", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/remove/save")
	@ResponseBody
	public AjaxResult remove(String id) {
		
		try {
			SystemReport report = systemReportService.get(id);
			systemReportService.delete(report);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护系统报表参数", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/param/index")
	public String eidtReportParam(String id, Model model) {
		
		model.addAttribute("reportId", id);
		return SYSTEM_REPORT_PARAM_EDIT_INDEX;
	}
	
	@RequestMapping("/param/save")
	@ResponseBody
	public AjaxResult saveReportParam(String reportId, SystemReportParam param) {
		
		try {
			SystemReport report = systemReportService.get(reportId);
			param.setReport(report);
			systemReportService.saveSystemReportParam(param);
			
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护系统报表参数", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/param/remove/save")
	@ResponseBody
	public AjaxResult remove(String reportId, String id) {
		
		try {
			SystemReport report = systemReportService.loadWithLazy(reportId, "params");
			Iterator<SystemReportParam> iterator = report.getParams().iterator();
			while (iterator.hasNext()) {
				SystemReportParam param = iterator.next();
				if (param != null && StringUtils.equals(param.getId(), id))
					param.setReport(null);
			}
			systemReportService.update(report);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护系统报表参数", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("/param/page")
	@ResponseBody
	public Page<SystemReportParam> paramPage(String reportId, Integer page, Integer limit) {
		
		return systemReportService.querySystemReportParam(reportId, limit, page);
	}
	
	@RequestMapping("/search")
	public String reportIndex(String code, Model model) {
		SystemReport report = systemReportService.findUniqueBy("code", code);
		model.addAttribute("reportCode", code);
		model.addAttribute("title", report.getName());
		return SYSTEM_REPORT_SEARCH_INDEX;
	}
	
	@RequestMapping("/do")
	@ResponseBody
	public void reportView(String reportCode, Model model) {
		
		this.getResponse().setContentType("text/html");
		SystemReport report = systemReportService.findUniqueBy("code", reportCode);
		report = systemReportService.loadWithLazy(report.getId(), "params");
		
		String jasperPath = new StringBuilder().append(systemReportService.getSystemReportTemplatePath())
		        .append(report.getTemplate()).toString();
		
		Map<String,Object> reportParams = new HashMap<String,Object>();
		List<SystemReportParam> params = report.getParams();
		for (SystemReportParam param : params) {
			String key = param.getCode();
			String value = this.getRequest().getParameter(key);
			reportParams.put(key, value);
		}
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperPath);
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, dataSource.getConnection());
			
			String format = this.getRequest().getParameter("format");
			if (StringUtils.equals(format, "html")) {
				PrintWriter out = this.getResponse().getWriter();
				HtmlExporter exporter = new HtmlExporter();
				
				this.getRequest().getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_LIST_SESSION_ATTRIBUTE,
				        jasperPrintList);
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				
				SimpleHtmlExporterOutput output = new SimpleHtmlExporterOutput(out);
				
				exporter.setExporterOutput(output);
				exporter.exportReport();
			} else {
				String fileName = report.getName() + "（" + DateUtils.formatDate(Calendar.getInstance().getTime()) + "）";
				this.getResponse().setHeader("Content-Disposition",
				        "filename=" + URLEncoder.encode(fileName + ".pdf", "UTF-8"));
				this.getResponse().setContentType("application/pdf ");
				this.getResponse().setCharacterEncoding("UTF-8");
				
				SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(
				        this.getResponse().getOutputStream());
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				configuration.setMetadataTitle(fileName);
				exporter.setConfiguration(configuration);
				exporter.setExporterOutput(output);
				exporter.exportReport();
			}
		} catch (JRException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
	}
}
