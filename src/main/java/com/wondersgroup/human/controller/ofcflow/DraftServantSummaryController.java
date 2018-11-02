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

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantReport;
import com.wondersgroup.human.enums.ServantTypeEnum;
import com.wondersgroup.human.service.ofcflow.DraftServantReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.util.ExcelUtils;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.DraftServantVO;

/** 
 * @ClassName: DraftServantSummaryController 
 * @Description: 录用汇总和流程控制器
 * @author: tzy
 * @date: 2018年7月19日 下午3:18:14
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/draftServantSummary")
public class DraftServantSummaryController extends GenericController {
	
	//录用汇总上报列表
	private final static String DRAFT_SERVANT_REPORT_LIST = "models/ofcflow/draftServant/draftServantReportList";
	// 录用详细信息查看
	private final static String DRAFT_SERVANT_VIEW = "models/ofcflow/draftServant/draftServantView";
	
	//ftp文件放置路径
	//基础路径
	@Value("#{mvc['ftp.baseurl']}")
	private static String preFtpUrl;
	//后置路径
	private final static String postFtpUrl="ofcflow/employ/";
	//全路径
	private String ftpurl="/human/ofcflow/employ/";
	
	@Autowired
	private DraftServantService draftServantService;
	
	@Autowired
	private DraftServantReportService draftServantReportService;
	

	

	
	/**
	 * @Title: employInfoEditPage 
	 * @Description: 汇总上报列表页面
	 * @param ids	选中汇总上报的录用人员ID
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/draftServantReport")
	public String employInfoEditPage(String ids, Model model) {
		model.addAttribute("ids", ids);

		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServant where removed=:removed and publish=:publish and status=:status and servantType=:servantType");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("publish", DraftServant.EMPLOY_PUBLISH);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("status", DraftServant.STATUS_EMPLOY_COLLECT);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("servantType", ServantTypeEnum.SERVANT_GONGWUYUAN.getCode());
		listqueryparameter.add(queryParameteritem);
		if(ids.contains(",")){
			hql.append(" and id in ( ");
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				if(i!=0){
					hql.append(",:id").append(i);
				}else{
					hql.append(":id").append(i);
				}
				queryParameteritem=new QueryParameter("id"+i, idsArr[i]);
				listqueryparameter.add(queryParameteritem);
			}
			hql.append(" ) ");
		}else{
			hql.append(" and id in (:ids) ");
			queryParameteritem=new QueryParameter("ids", ids);
			listqueryparameter.add(queryParameteritem);
		}
		//获取公务员列表
		List<DraftServant> listgwy = draftServantService.findByHQL(hql.toString(), listqueryparameter);
		
		//获取参公列表
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("servantType", ServantTypeEnum.SERVANT_CANGONG.getCode());
		listqueryparameter.set(3, queryParameteritem);
		List<DraftServant> listcg = draftServantService.findByHQL(hql.toString(), listqueryparameter);
		
		hql.append(" and cardNo like '310%'");
		List<DraftServant> listcg310 = draftServantService.findByHQL(hql.toString(), listqueryparameter);
		queryParameteritem=new QueryParameter("servantType", ServantTypeEnum.SERVANT_GONGWUYUAN.getCode());
		listqueryparameter.set(3, queryParameteritem);
		List<DraftServant> listgwy310 = draftServantService.findByHQL(hql.toString(), listqueryparameter);
		
		model.addAttribute("total", listgwy.size()+listcg.size());
		model.addAttribute("total310", listgwy310.size()+listcg310.size());
		model.addAttribute("total_gwy", listgwy.size());
		model.addAttribute("total_gwy310", listgwy310.size());
		model.addAttribute("total_cg", listcg.size());
		model.addAttribute("total_cg310", listcg310.size());
		if(listcg.size()>0){
			model.addAttribute("cgExpName", listcg.get(0).getName());
		}else{
			model.addAttribute("cgExpName", "");
		}
		if(listgwy.size()>0){
			model.addAttribute("gwyExpName", listgwy.get(0).getName());
		}else{
			model.addAttribute("gwyExpName","");
		}
		
		
		return DRAFT_SERVANT_REPORT_LIST;
	}
	/**
	 * @Title: draftServantView 
	 * @Description: 录用详情查看，包含录用信息
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/draftServantReportView")
	public String draftServantReportView(String id, Model model) {
		DraftServant d = draftServantService.load(id);
		model.addAttribute("d", d);
		model.addAttribute("isReport", "1");//显示录用信息
		return DRAFT_SERVANT_VIEW;
	}
	/**
	 * 
	 * @Title: draftServantPage 
	 * @Description: 拟录用汇总人员列表
	 * @param model
	 * @param yearTip
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DraftServantVO>
	 */
	@ResponseBody
	@RequestMapping("/collectPage")
	public Page<DraftServantVO> draftServantPage(Model model,Integer yearTip,Integer limit,Integer page,Integer servantType,String name,String cardNo) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServant where removed=:removed and publish=:publish and status=:status ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("publish", DraftServant.EMPLOY_PUBLISH);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("status", DraftServant.STATUS_EMPLOY_COLLECT);
		listqueryparameter.add(queryParameteritem);
		if(yearTip!=null){
			hql.append( " and yearTip=:yearTip");
			queryParameteritem=new QueryParameter("yearTip", yearTip);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("yearTip", yearTip);
		}
		if(name!=null&&!name.equals("")){
			hql.append( " and name=:name");
			queryParameteritem=new QueryParameter("name", name);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("name", name);
		}
		if(cardNo!=null&&!cardNo.equals("")){
			hql.append( " and cardNo like :cardNo");
			queryParameteritem=new QueryParameter("cardNo", "%"+cardNo+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("cardNo", cardNo);
		}
		if(servantType!=null&&servantType>0){
			hql.append( " and servantType=:servantType");
			//人员类型
			queryParameteritem=new QueryParameter("servantType", servantType);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("servantType", servantType);
		}
		hql.append( " order by createTime desc");
		
		Page<DraftServantVO> pageInfo = draftServantService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: cancelCollectById 
	 * @Description: 取消汇总
	 * @param id		如果id中有逗号，批量取消汇总
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/cancelCollectById")
	public AjaxResult cancelCollectById(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
			draftServantService.updateCollectById(id,DraftServant.STATUS_EMPLOY_NO_COLLECT);//取消汇总
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * 
	 * @Title: reportPage 
	 * @Description: 选中人员的汇总上报列表
	 * @param ids		选中汇总上报的录用人员id，逗号隔开
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DraftServantVO>
	 */
	@ResponseBody
	@RequestMapping("/reportPage")
	public Page<DraftServantVO> reportPage(String ids,Integer limit,Integer page,Integer servantType) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServant where removed=:removed and publish=:publish and servantType=:servantType");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("publish", DraftServant.EMPLOY_PUBLISH);
//		listqueryparameter.add(queryParameteritem);
//		queryParameteritem=new QueryParameter("status", DraftServant.STATUS_EMPLOY_COLLECT);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("servantType", servantType);
		listqueryparameter.add(queryParameteritem);
		if(ids.contains(",")){
			hql.append(" and id in ( ");
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				if(i!=0){
					hql.append(",:id").append(i);
				}else{
					hql.append(":id").append(i);
				}
				queryParameteritem=new QueryParameter("id"+i, idsArr[i]);
				listqueryparameter.add(queryParameteritem);
			}
			hql.append(" ) ");
		}else{
			hql.append(" and id in (:ids) ");
			queryParameteritem=new QueryParameter("ids", ids);
			listqueryparameter.add(queryParameteritem);
		}
		hql.append( " order by createTime desc");
		
		Page<DraftServantVO> pageInfo = draftServantService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);
		
		return pageInfo;
	}
	
	/**
	 * @Title: saveReport 
	 * @Description: 保存上报信息，启动流程
	 * @param ids	上报的录用人员id，逗号隔开形式
	 * @param type	0：暂存	1：提交
	 * @param report	上报信息，两个附件的ftp路径,上报人员人数统计,表单信息
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveReport")
	public AjaxResult saveReport(String ids, String type, DraftServantReport report,String cgExpName,String gwyExpName) {
		AjaxResult result = new AjaxResult(true);
		try {
//			JSONArray askForJson = JSONArray.fromObject();
//			JSONArray repostJson = JSONArray.fromObject();
			if (StringUtils.isBlank(ids)) {
				throw new BusinessException("信息不完整，上报失败!");
			}
			String userId = SecurityUtils.getUserId();// 当前登录人信息
			OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(userId);
			report.setUserId(userId);
			report.setCreateDept(organNode);

			
			// 创建公务员请示文档,保存文档路径信息==================================================
			// 1.设置文档参数
			Map<String, Object> gwyqsMap = new HashMap<String, Object>();
			gwyqsMap.put("nunit", report.getUnitName());
			gwyqsMap.put("nexpnm", gwyExpName);
			gwyqsMap.put("ntotal", report.getTotalGwy());
			gwyqsMap.put("nyear", DateUtils.getYear());
			gwyqsMap.put("nmonth", DateUtils.getMonth());
			gwyqsMap.put("nday", DateUtils.getDay());
			gwyqsMap.put("nnumb", report.getSerialNumber());
			gwyqsMap.put("nsigner", report.getSigner());
			//生成参公请示文档
			File gwyqsFile = WordUtils.createMillCertificateWord(gwyqsMap, "gwyqs.ftl");
			String gwyqsFileName=new Date().getTime()+".doc";
			//上传文档至ftp
			FtpTool.ftpUpload(ftpurl, gwyqsFile, gwyqsFileName);
			//设置文档ftp路径 
			report.setAskForFilePathGWY(gwyqsFileName);
			
			// 创建参公请示文档,保存文档路径信息====================================================
			// 1.设置文档参数
			Map<String, Object> cgqsMap = new HashMap<String, Object>();
			cgqsMap.put("munit", report.getUnitName());
			cgqsMap.put("myear", DateUtils.getYear());
			cgqsMap.put("mnumb", report.getSerialNumber());
			cgqsMap.put("msigner", report.getSigner());
			cgqsMap.put("mexpnm", gwyExpName);
			cgqsMap.put("mcgtotal", report.getTotalCg());
			cgqsMap.put("mmonth", DateUtils.getMonth());
			cgqsMap.put("mday", DateUtils.getDay());
			//生成参公请示文档
			File cgqsFile = WordUtils.createMillCertificateWord(cgqsMap, "cgqs.ftl");
			String cgqsFileName=new Date().getTime()+".doc";
			//上传文档至ftp
			FtpTool.ftpUpload(ftpurl, cgqsFile, cgqsFileName);
			//设置文档ftp路径 
			report.setAskForFilePathCG(cgqsFileName);
			
			
			// 创建公务员审核模板,保存文档信息============================================================
			Map<String, Object> gwyshMap = new HashMap<String, Object>();
			gwyshMap.put("nexpnm", cgExpName);
			gwyshMap.put("ntotal", report.getTotalGwy());
			gwyshMap.put("nyear", DateUtils.getYear());
			gwyshMap.put("nmonth", DateUtils.getMonth());
			gwyshMap.put("nday", DateUtils.getDay());
			gwyshMap.put("ntotaltoz", report.getTotalGwy310());
			gwyshMap.put("ntotaltozn", report.getTotalGwy()-report.getTotalGwy310());
			//生成参公请示文档
			File gwyshFile = WordUtils.createMillCertificateWord(gwyshMap, "gwysh.ftl");
			String gwyshFileName=new Date().getTime()+".doc";
			//上传文档至ftp
			FtpTool.ftpUpload(ftpurl, gwyshFile, gwyshFileName);
			//设置文档ftp路径 
			report.setRepostFilePathGWY(gwyshFileName);

			// 创建参公审核模板,保存文档信息=============================
			
			Map<String, Object> cgshMap = new HashMap<String, Object>();
			cgshMap.put("mexpnm", cgExpName);
			cgshMap.put("mtotal", report.getTotalCg());
			cgshMap.put("mtotaltoz", report.getTotalCg310());
			cgshMap.put("mtotaltozn", report.getTotalCg()-report.getTotalCg310());
			cgshMap.put("mcgtotal", report.getTotalCg());
			cgshMap.put("mmonth", DateUtils.getMonth());
			cgshMap.put("mday", DateUtils.getDay());
			cgshMap.put("myear", DateUtils.getYear());
			//生成参公请示文档
			File cgshFile = WordUtils.createMillCertificateWord(cgshMap, "cgsh.ftl");
			String cgshFileName=new Date().getTime()+".doc";
			//上传文档至ftp
			FtpTool.ftpUpload(ftpurl, cgshFile, cgshFileName);
			//设置文档ftp路径 
			report.setRepostFilePathCG(cgshFileName);

			
			// 创建公务员审核情况汇总模板,保存文档信息==============
			List<DraftServant> gwyDraftServants = this.draftServantReportService.getDraftServantByTypeInIds(ids, ServantTypeEnum.SERVANT_GONGWUYUAN.getCode());
			Map<String,Object> gwyhzmap=new HashMap<>();
			gwyhzmap.put("size",gwyDraftServants.size() );
			gwyhzmap.put("list", gwyDraftServants);
			//1创建excel文件
			File gwyhz = ExcelUtils.createMillCertificateExcel(gwyhzmap,"dwshhz.ftl");
			String gwyhzName=new Date().getTime()+".xls";
			//上传文档至ftp
			FtpTool.ftpUpload(ftpurl, gwyhz, gwyhzName);
			//2设置excel文档ftp路径
			report.setNo310FilePathGWY(gwyhzName);
			
			// 创建参公审核情况汇总模板,保存文档信息==============
			List<DraftServant> cgDraftServants = this.draftServantReportService.getDraftServantByTypeInIds(ids, ServantTypeEnum.SERVANT_CANGONG.getCode());
			Map<String,Object> zghzmap=new HashMap<>();
			zghzmap.put("size",cgDraftServants.size() );
			zghzmap.put("list", cgDraftServants);
			//1创建excel文件
			File cghz = ExcelUtils.createMillCertificateExcel(zghzmap,"dwshhz.ftl");
			String cghzName=new Date().getTime()+".xls";
			//上传文档至ftp
			FtpTool.ftpUpload(ftpurl, cghz, cghzName);
			//2设置excel文档ftp路径
			report.setNo310FilePathCG(cghzName);
			
			

			draftServantReportService.createReportWork(ids, type, report);// 保存数据，启动流程

			result.setMessage("上报成功！");

		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("上报失败！");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: downloadDraftServantFile 
	 * @Description: 公务员录用时上传的文件的下载方法
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return: void
	 */
	@RequestMapping("/downloadDraftServantFile")
    public @ResponseBody void downloadDraftServantFile(@RequestParam(name="fileName",required=true) String fileName,@RequestParam(name="fileShowName",required=true) String fileShowName,HttpServletResponse response) throws IOException{
	    ServletOutputStream  out = response.getOutputStream();  
        response.setCharacterEncoding("utf-8");  
        response.setContentType("application/msword");  
        // 设置浏览器以下载的方式处理该文件名  
        response.setHeader("Content-Disposition", "attachment;filename="  
                .concat(String.valueOf(URLEncoder.encode(fileShowName, "UTF-8"))));  
	    byte[] ftpDownloadInByte = FtpTool.ftpDownloadInByte("/human/"+postFtpUrl, fileName);
		out.write(ftpDownloadInByte);
    }
	

	
}