/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 录用
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月22日 上午10:35:22 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月22日 上午10:35:22 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantEduInfo;
import com.wondersgroup.human.bo.ofcflow.DraftServantImportRecord;
import com.wondersgroup.human.bo.ofcflow.DraftServantRelationReport;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.dto.ofcflow.UnitBasicInfoDTO;
import com.wondersgroup.human.service.ofcflow.DraftServantEduInfoService;
import com.wondersgroup.human.service.ofcflow.DraftServantImportRecordService;
import com.wondersgroup.human.service.ofcflow.DraftServantRelationReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.ofcflow.DraftServantImportRecordVO;
import com.wondersgroup.human.vo.ofcflow.DraftServantVO;

import net.sf.json.JSONObject;

/**
 * @ClassName: DraftServantController
 * @Description: 录用
 * @author: wangzhanfei
 * @date: 2018年5月22日 上午10:35:22 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("ofcflow/draftServant")
public class DraftServantController extends GenericController {

	// 公务员录用列表
	private final static String DRAFT_SERVANT_LIST = "models/ofcflow/draftServant/draftServantLlist";
	// 公务员录用导入记录列表
	private final static String DRAFT_SERVANT_IMPORT_LIST = "models/ofcflow/draftServant/draftServantImportLlist";
	// 编辑录用信息
	private final static String DRAFT_SERVANT_EMPLOY_EDIT = "models/ofcflow/draftServant/draftServantEmployEdit";
	// 拟录用汇总
	private final static String DRAFT_SERVANT_COLLECT_LIST = "models/ofcflow/draftServant/draftServantCollectList";
	// 录用查看
	private final static String DRAFT_SERVANT_STATUS_LIST = "models/ofcflow/draftServant/draftServantStatusList";
	
	// 公务员录用导入
//	private final static String DRAFT_SERVANT_IMPORT = "models/ofcflow/draftServant/draftServantImportForm";
	private final static String DRAFT_SERVANT_IMPORT = "models/ofcflow/draftServant/draftServantImportFormNew";
	// 拟录用名单列表
	private final static String DRAFT_SERVANT_BEFORE_LIST = "models/ofcflow/draftServant/draftServantBeforeList";
	// 录用详细信息查看
	private final static String DRAFT_SERVANT_VIEW = "models/ofcflow/draftServant/draftServantView";
	// 录用流程页面
	private final static String DRAFT_SERVANT_PROCESS_VIEW = "models/ofcflow/draftServant/draftServantProcessView";
	
	private static final String RSJ_ALLNAME = "上海市长宁区人力资源和社会保障局";
	
	@Value("default.organization.tree.code")
	private static String defaultOrgTreeCode;
	
	@Autowired
	private DraftServantImportRecordService draftServantImportRecordService;
	
	@Autowired
	private DraftServantService draftServantService;
	@Autowired
	private DraftServantEduInfoService draftServantEduInfoService;
	
	@Autowired
	private OrgInfoService basciInfoService;
	
	@Autowired
	private DraftServantRelationReportService draftServantRelationReportService;
	
	@Autowired
	private DraftServantReportService draftServantReportService;

	// 公务员录用列表
	@RequestMapping("/index")
	public String recruitList() {

		return DRAFT_SERVANT_LIST;
	}
	// 公务员录用导入记录列表
	@RequestMapping("/importList")
	public String importList(Model model) {
		OrganNode org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		//是否人社局
		boolean isBureau = true;
		if (org.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
			isBureau=true;
		} else {
			isBureau=false;
			return "redirect: employStatusList";
		}
		model.addAttribute("isBureau", isBureau);
		return DRAFT_SERVANT_IMPORT_LIST;
	}

	// 编辑录用信息
	@RequestMapping("/employInfoEditPage")
	public String employInfoEditPage(String id, Model model) {
		OrganNode c = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		DraftServant d = draftServantService.load(id);
		
		boolean isCurrentOrgan=false;
		
		isCurrentOrgan=(c!=null&&d.getDraftUnit().getOrgan().getId().equals(c.getId()));
		
		model.addAttribute("d", d);
		// 根据基础信息ID 获取学历学位子集
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("draftServantId", Operator.EQ, id, "");
		filter.add(p);
		List<DraftServantEduInfo> eduList = draftServantEduInfoService.findByFilter(filter);
		model.addAttribute("eduList", eduList);
		model.addAttribute("isCurrentOrgan", isCurrentOrgan);
		DraftServantRelationReport servantRelationReport = this.draftServantRelationReportService.getReportByServant(id);
		model.addAttribute("report",servantRelationReport==null?null:servantRelationReport.getReport());
		return DRAFT_SERVANT_EMPLOY_EDIT;
	}

	// 拟录用汇总
	@RequestMapping("/collectList")
	public String collectList() {

		return DRAFT_SERVANT_COLLECT_LIST;
	}

	// 录用查看
	@RequestMapping("/employStatusList")
	public String employStatusList(Model model) {
		OrganNode org = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		//是否人社局
		boolean isBureau = true;
		if (org.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
			isBureau=true;
		} else {
			isBureau=false;
		}
		model.addAttribute("isUnit", !isBureau);
		return DRAFT_SERVANT_STATUS_LIST;
	}
	//拟录用人员列表查看
	@RequestMapping("/beforeList")
	public String beforeList(String id, Model model) {
		model.addAttribute("importId", id);
		return DRAFT_SERVANT_BEFORE_LIST;
	}
	//拟录用人员详细信息查看
	@RequestMapping("/draftServantView")
	public String draftServantView(String id, Model model) {
		DraftServant d = draftServantService.load(id);
		if(d.getEmploySituation()!=null){
			model.addAttribute("isReport", "1");//显示录用信息
		}
		model.addAttribute("d", d);
		// 根据基础信息ID 获取学历学位子集
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("draftServantId", Operator.EQ, id, "");
		filter.add(p);
		List<DraftServantEduInfo> eduList = draftServantEduInfoService.findByFilter(filter);
		model.addAttribute("eduList", eduList);
		return DRAFT_SERVANT_VIEW;
	}
	/**
	 * @Title: draftServantList 
	 * @Description: 拟录用名单列表
	 * @param model
	 * @param yearTip
	 * @param name
	 * @param cardNo
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DraftServantVO>
	 */
	@ResponseBody
	@RequestMapping("/draftServantList")
	public Page<DraftServantVO> draftServantList(Model model,Integer yearTip,String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServant where removed=:removed and publish=:publish and status=:status ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("publish", DraftServant.EMPLOY_PUBLISH);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("status", DraftServant.STATUS_EMPLOY_NO_COLLECT);
		listqueryparameter.add(queryParameteritem);
		if(yearTip!=null){
			hql.append( " and yearTip=:yearTip");
			queryParameteritem=new QueryParameter("yearTip", yearTip);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("yearTip", yearTip);
		}
		if(StringUtils.isNotBlank(name)){
			hql.append( " and name like :name");
			queryParameteritem=new QueryParameter("name", "%"+name+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("name", name);
		}
		if(StringUtils.isNotBlank(cardNo)){
			hql.append( " and cardNo like :cardNo");
			queryParameteritem=new QueryParameter("cardNo", "%"+cardNo+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("cardNo", cardNo);
		}
		hql.append( " order by createTime desc");
		
		Page<DraftServantVO> pageInfo = draftServantService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: updateDraft 
	 * @Description: 报送汇总
	 * @param id 	 如果id中有逗号，批量报送汇总
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/collectById")
	public AjaxResult collectById(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			draftServantService.updateCollectById(id,DraftServant.STATUS_EMPLOY_COLLECT);//报送汇总
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: updateDraft 
	 * @Description: 保存录用信息
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/updateDraft")
	public AjaxResult updateDraft(DraftServant temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
			DraftServant draft = draftServantService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, draft);
			draftServantService.update(draft);// 保存
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: updateDraft 
	 * @Description: 确认电子介绍信
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/confirmLetter")
	public AjaxResult confirmLetter(DraftServant temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
			DraftServant draft = draftServantService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, draft);
			draft.setIsElecLetter(DraftServant.ISELECLETTER_YES);
			draftServantService.update(draft);// 保存
			result.setMessage("保存成功！");
			draftServantReportService.addProbationServantSingle(draft.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: draftServantPage 
	 * @Description: 录用查看人员列表
	 * @param model
	 * @param yearTip
	 * @param name
	 * @param cardNo
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DraftServantVO>
	 */
	@ResponseBody
	@RequestMapping("/draftServantPage")
	public Page<DraftServantVO> draftServantPage(Model model,Integer yearTip,String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServant where removed=:removed and publish=:publish");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("publish", DraftServant.EMPLOY_PUBLISH);
		listqueryparameter.add(queryParameteritem);
		if(yearTip!=null){
			hql.append( " and yearTip=:yearTip");
			queryParameteritem=new QueryParameter("yearTip", yearTip);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("yearTip", yearTip);
		}
		if(StringUtils.isNotBlank(name)){
			hql.append( " and name like :name");
			queryParameteritem=new QueryParameter("name", "%"+name+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("name", name);
		}
		if(StringUtils.isNotBlank(cardNo)){
			hql.append( " and cardNo like :cardNo");
			queryParameteritem=new QueryParameter("cardNo", "%"+cardNo+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("cardNo", cardNo);
		}
		if(organNode!=null&&(!organNode.getAllName().equals(RSJ_ALLNAME))){
			hql.append(" and draftUnit.organ.id = :unitId");
			queryParameteritem=new QueryParameter("unitId", organNode.getId());
			listqueryparameter.add(queryParameteritem);
			
		}
		hql.append( " order by createTime desc");
		
		Page<DraftServantVO> pageInfo = draftServantService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	
	@RequestMapping("draftservantImport")
	public String draftservantImport(){
		return DRAFT_SERVANT_IMPORT;
	}
	
	@RequestMapping("importdraftservant")
	public String importdraftservant(Model model,HttpServletRequest request,Integer recordYear,String importInfo) {
		
		try{
			String importInfojson=importInfo.replace("[", "").replace("]", "");
			 JSONObject json_test = JSONObject.fromObject(importInfojson);
			 String importId=(String)json_test.get("path");
			 
			 
			 draftServantImportRecordService.updaterecordandServantInfo(importId,recordYear);
			 
		}catch(BusinessException e){
			model.addAttribute("info",e.getMessage());
			model.addAttribute("returnPath","ofcflow/draftServant/index");
			return VIEW_TIPS_INFO;
		}
		
		return "redirect:index";
	}
	/**
	 * @Title: importPageList 
	 * @Description: 拟录用导入记录列表
	 * @param model
	 * @param yearTip
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DraftServantImportRecordVO>
	 */
	@ResponseBody
	@RequestMapping("/importPageList")
	public Page<DraftServantImportRecordVO> importPageList(Model model,Integer yearTip,Integer limit,Integer page,String importDate) {
		
		//		获取当前登录人：
		String userId = SecurityUtils.getUserId();//当前登录人信息
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServantImportRecord where removed=:removed");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		if(yearTip!=null){
			hql.append( " and yearTip=:yearTip");
			queryParameteritem=new QueryParameter("yearTip", yearTip);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("yearTip", yearTip);
		}
		if(importDate!=null&&!importDate.equals("")){
			hql.append( " and to_char(importDate,'yyyy-MM-dd')=:importDate");
			queryParameteritem=new QueryParameter("importDate", importDate);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("importDate", importDate);
		}
		hql.append( " order by importDate desc");
		
		Page<DraftServantImportRecordVO> pageInfo = draftServantImportRecordService.getdraftServantImportRecordVOPage(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: importdraftservantInfo 
	 * @Description: 导入拟录用人员数据  ,@RequestParam(value="servantType", required=true) int age
	 * @param request
	 * @return
	 * @return: Map<String,String>
	 */
	@RequestMapping("importdraftservantInfo")
	@ResponseBody
	public Map<String,String> importdraftservantInfo(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		Map<String,String> data = new HashMap<String,String>();//存放文件在ftp服务器的路径和文件名称，code为1时文件保存成功
		try{
			String field = multipartRequest.getParameter("field");//数据input的name值，用以获取文件
			String folder = multipartRequest.getParameter("folder");//年份 人员类型  临时通过此参数传递
			String[] split = folder.split("_");
			String year=split[0];
			int servantType=Integer.parseInt(split[1]);
			if(!(StringUtils.isNotBlank(year)&&servantType!=-1)){
				data.put("code", "0");
				data.put("msg", "请完善年份和文件类型信息!");
				return data;
			}
//			if(StringUtils.isBlank(year)){
//				throw new BusinessException("请填写年份！");
//			}
			if(field==null||"".equals(field)||"undefined".equals(field)){//layui的默认值file
				field="file";
			}
			MultipartFile file = multipartRequest.getFile(field);
			if(file!=null && file.getSize()>0){
				//保存文件到ftp服务器
				DraftServantImportRecord reocrd= draftServantImportRecordService.saveImportRecord(file,Integer.parseInt(year),servantType);
				data.put("path", reocrd.getId());
				data.put("name", reocrd.getId());
				data.put("code", "1");
				String failStr=reocrd.getFailStr();
				if(failStr.equals("")||failStr==null){
					data.put("msg", "信息上传成功!");
				}else{
					data.put("msg",  "信息部分上传成功!由于用人部门和系统不匹配,未能成功上传记录如下:"+failStr);
				}
				
			}
		}catch(Exception e){
			data.put("code", "0");
			data.put("msg", "上传失败!请检查上传文件!");
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * @Title: importDel 
	 * @Description: 删除该次导入记录数据
	 * @param model
	 * @param request
	 * @param id	 导入数据表记录ID
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/importDel")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			draftServantImportRecordService.removeDraftServantImportRecord(id);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * @Title: publish 
	 * @Description: 发布
	 * @param id	导入数据表记录ID
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/publish")
	public AjaxResult publish(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			draftServantImportRecordService.savePublish(id);
			result.setMessage("发布成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("发布失败！");
		}
		return result;
	}
	/**
	 * 
	 * @Title: draftServantBeforePage 
	 * @Description: 查看已发布的拟录用人员列表
	 * @param model
	 * @param yearTip
	 * @param name
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DraftServantVO>
	 */
	@ResponseBody
	@RequestMapping("/draftServantBeforePage")
	public Page<DraftServantVO> draftServantBeforePage(Model model,Integer yearTip,String name,String cardNo,String id,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServant where removed=:removed and importRecordId=:importRecordId");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("importRecordId", id);
		listqueryparameter.add(queryParameteritem);
		if(yearTip!=null){
			hql.append( " and yearTip=:yearTip");
			queryParameteritem=new QueryParameter("yearTip", yearTip);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("yearTip", yearTip);
		}
		if(StringUtils.isNotBlank(name)){
			hql.append( " and name like :name");
			queryParameteritem=new QueryParameter("name", "%"+name+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("name", name);
		}
		if(StringUtils.isNotBlank(cardNo)){
			hql.append( " and cardNo like :cardNo");
			queryParameteritem=new QueryParameter("cardNo", "%"+cardNo+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("cardNo", cardNo);
		}
		hql.append( " order by createTime desc");
		
		Page<DraftServantVO> pageInfo = draftServantService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);
		
		return pageInfo;
	}
	
	@RequestMapping("/draftProcessPage")
	public String draftProcessPage(){
		return DRAFT_SERVANT_PROCESS_VIEW;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getUnitSelect",method={RequestMethod.GET,RequestMethod.POST})
	public List<UnitBasicInfoDTO> getUnitSelect(){
		List<QueryParameter> ifilter=new ArrayList<QueryParameter>();
		QueryParameter iqueryparameter=new QueryParameter("removed", false);
		ifilter.add(iqueryparameter);
		List<OrgInfo> unitList = basciInfoService.findByHQL("from UnitBasicInfo a where  a.removed=:removed", ifilter);
		return UnitBasicInfoDTO.UnitBasicInfo2DTO(unitList);
	}
	
	
}
