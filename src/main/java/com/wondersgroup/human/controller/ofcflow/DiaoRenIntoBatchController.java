/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaoRenIntoBatchController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 调任 批量处理调任人员 控制器
 * 创建人: tzy   
 * 创建时间: 2018年12月10日 上午10:47:58 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月10日 上午10:47:58 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DiaoRenInto;
import com.wondersgroup.human.bo.ofcflow.DiaoRenIntoBatch;
import com.wondersgroup.human.bo.ofcflow.DiaoRenIntoMgr;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOut;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOutBatch;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.bo.pubinst.PtJobLevel;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DiaoRenIntoBatchService;
import com.wondersgroup.human.service.ofcflow.DiaoRenIntoService;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutBatchService;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.service.pubinst.PtJobLevelService;
import com.wondersgroup.human.util.ImgPicUtil;
import com.wondersgroup.human.vo.ofcflow.DiaoRenIntoBatchVO;
import com.wondersgroup.human.vo.ofcflow.DiaoRenIntoVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: DiaoRenIntoBatchController 
 * @Description: 调任 批量处理调任人员 控制器
 * @author: tzy
 * @date: 2018年12月10日 上午10:47:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/diaorenB")
public class DiaoRenIntoBatchController extends GenericController{
	@Autowired
	private DiaoRenIntoBatchService diaoRenIntoBatchService;
	@Autowired
	private DiaoRenIntoService diaoRenIntoService;
	@Autowired
	private DiaoRenOutService diaoRenOutService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private FlowRecordService flowRecordService;
	@Autowired
	private OrgFormationService orgFormationService;
	@Autowired
	private OrgInfoService orgInfoService;	
	@Autowired
	private PtJobLevelService ptJobLevelService;
	@Autowired
	private DiaoRenOutBatchService diaoRenOutBatchService;
	
	/**
	 * 调入情况列表
	 */
	private final static String DIAOREN_INTO_LIST = "models/ofcflow/diaoRenB/intoList";
	/**
	 * 校验编制页面
	 */
	private final static String DIAOREN_CHECK_FORMATION = "models/ofcflow/diaoRenB/checkFormation";
	/**
	 * 选择或新增 批量调任人员列表
	 */
	private final static String DIAOREN_INTO_SERVANT_LIST = "models/ofcflow/diaoRenB/intoServantList";
	/**
	 * 调任人员流程处理页面
	 */
	private final static String DIAOREN_INTO_BATCH_FLOW = "models/ofcflow/diaoRenB/intoBatchFlow";
	/**
	 * 调任人员不存在系统的编辑页面
	 */
	private final static String DIAOREN_INTO_EDIT = "models/ofcflow/diaoRenB/intoEdit";
	/**
	 * 调任人员已存在系统的编辑页面
	 */
	private final static String DIAOREN_INTO_EDIT_EXIST = "models/ofcflow/diaoRenB/intoEditExist";
	/**
	 * 调任人员流程处理页面
	 */
	private final static String DIAOREN_INTO_FLOW = "models/ofcflow/diaoRenB/intoFlow";
	/**
	 * 流程待办列表
	 */
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	
	/**
	 * @Title: index 
	 * @Description: 调入情况列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return DIAOREN_INTO_LIST;
	}
	/**
	 * @Title: check 
	 * @Description: 检验编制页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/check")
	public String check(String areaType,Model model) {
		DiaoRenIntoBatch z = new DiaoRenIntoBatch();
		z.setAreaType(areaType);
		//查询当前单位编制情况
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		OrgInfo org = orgInfoService.findUniqueBy("organ.id", x.getId());
		if(org!=null){
			OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", org.getId());
			if(orgFormation!=null){
				BeanUtils.copyPropertiesIgnoreNull(orgFormation, z);
				z.setId(null);
			}
		}
		model.addAttribute("d", z);
		model.addAttribute("organCode", x.getCode());//不能选择人员来源单位为自己单位
		
		return DIAOREN_CHECK_FORMATION;
	}
	/**
	 * @Title: servantList 
	 * @Description: 选择调任人员
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantList")
	public String servantList(Model model,String id) {
		DiaoRenIntoBatch z = diaoRenIntoBatchService.get(id);
		model.addAttribute("d", z);
		return DIAOREN_INTO_SERVANT_LIST;
	}
	/**
	 * @Title: intoList 
	 * @Description: 调任调入情况列表
	 * @param model
	 * @param yearTip
	 * @param name
	 * @param cardNo
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DraftServantVO>
	 */
	@Log(title = "查询调任列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/intoList")
	public Page<DiaoRenIntoBatchVO> intoList(Model model,String areaType,Integer sumNumberS,Integer sumNumberE,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DiaoRenIntoBatch where removed=:removed and creater=:creater");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
		if(StringUtils.isNotBlank(areaType)){//调入
			hql.append( " and areaType = :areaType");
			queryParameteritem=new QueryParameter("areaType", areaType);
			listqueryparameter.add(queryParameteritem);
		}
		if(sumNumberS!=null){//调任人数起
			hql.append( " and sumNumber >= :sumNumberS");
			queryParameteritem=new QueryParameter("sumNumberS", sumNumberS);
			listqueryparameter.add(queryParameteritem);
		}
		if(sumNumberE!=null){//调任人数止
			hql.append( " and sumNumber <= :sumNumberE");
			queryParameteritem=new QueryParameter("sumNumberE", sumNumberE);
			listqueryparameter.add(queryParameteritem);
		}
		hql.append( " order by createTime desc");
		
		Page<DiaoRenIntoBatchVO> pageInfo = diaoRenIntoBatchService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: intoServantList 
	 * @Description: 调任调入人员情况列表
	 * @param model
	 * @param name
	 * @param cardNo
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DiaoRenIntoVO>
	 */
	@Log(title = "查询调任人员列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/intoServantList")
	public Page<DiaoRenIntoVO> intoServantList(Model model,String name,String cardNo,Integer limit,Integer page,String id) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DiaoRenInto where removed=:removed and diaoRenIntoBatch.id=:diaoRenIntoBatch");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("diaoRenIntoBatch", id));//按批次加载数据
		if(StringUtils.isNotBlank(name)){
			hql.append( " and name like :name");
			queryParameteritem=new QueryParameter("name", "%"+name+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("name", name);
		}
		if(StringUtils.isNotBlank(cardNo)){
			hql.append( " and cardNo = :cardNo");
			queryParameteritem=new QueryParameter("cardNo", cardNo);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("cardNo", cardNo);
		}
		hql.append( " order by createTime desc");
		
		Page<DiaoRenIntoVO> pageInfo = diaoRenIntoService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","DiaoRenIntoMgr_THIS,DiaoRenIntoMgr_OUTER,DiaoRenOutMgr_THIS,DiaoRenOutMgr_OUTER");
		return RECRUIT_EMPLOYPLAN_FLOW;
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
		DiaoRenIntoBatch z = diaoRenIntoBatchService.get(id);
		model.addAttribute("d", z);
		return DIAOREN_INTO_BATCH_FLOW;
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
		if("DiaoRenOutMgr_THIS".equals(flow.getBusType())||"DiaoRenOutMgr_OUTER".equals(flow.getBusType())){//跳转到调出流程页面
			DiaoRenOutBatch z = diaoRenOutBatchService.load(flow.getBusId());
			model.addAttribute("d", z);
			model.addAttribute("isFlow", true);
			if(z.getStatus()==DiaoRenOutBatch.STATUS_DIAOCHU_STATE){
				return DiaoRenOutBatchController.DIAOREN_OUT_SERVANT_LIST;
			}else{
				if(z.getStatus()==DiaoRenOutBatch.STATUS_DIAOCHU_PRINT){
					DetachedCriteria d = DetachedCriteria.forClass(DiaoRenOut.class);
					d.add(Restrictions.eq("diaoRenOutBatch.id", flow.getBusId()));
					d.add(Restrictions.eq("removed", false));
					List<DiaoRenOut> list = diaoRenOutService.findByCriteria(d);//批次下所有调任人员信息
					StringBuffer ids = new StringBuffer();
					for(DiaoRenOut into : list){
						ids.append(into.getId()).append(",");
					}
					ids.deleteCharAt(ids.lastIndexOf(","));
					model.addAttribute("ids", ids);//获取批次下所有人员业务id，用于下载介绍信
					model.addAttribute("introductionName", "调任介绍信");
				}
				return DiaoRenOutBatchController.DIAOREN_OUT_BATCH_FLOW;
			}
		}else{
			DiaoRenIntoBatch z = diaoRenIntoBatchService.load(flow.getBusId());
			model.addAttribute("d", z);
			model.addAttribute("isFlow", true);
			if(z.getStatus()==DiaoRenIntoBatch.STATUS_DIAOREN_STATE){
				return DIAOREN_INTO_SERVANT_LIST;
			}else{
				if(z.getStatus()==DiaoRenIntoBatch.STATUS_DIAOREN_PRINT){
					DetachedCriteria d = DetachedCriteria.forClass(DiaoRenInto.class);
					d.add(Restrictions.eq("diaoRenIntoBatch.id", flow.getBusId()));
					d.add(Restrictions.eq("removed", false));
					List<DiaoRenInto> list = diaoRenIntoService.findByCriteria(d);//批次下所有调任人员信息
					StringBuffer ids = new StringBuffer();
					for(DiaoRenInto into : list){
						ids.append(into.getId()).append(",");
					}
					ids.deleteCharAt(ids.lastIndexOf(","));
					model.addAttribute("ids", ids);//获取批次下所有人员业务id，用于下载介绍信
					model.addAttribute("introductionName", "调任介绍信");
				}
				return DIAOREN_INTO_BATCH_FLOW;
			}
		}
	}
	/**
	 * @Title: save 
	 * @Description: 校验编制信息并保存
	 * @param temp
	 * @return: AjaxResult
	 */
	@Log(title = "调任-保存校验编制信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(DiaoRenIntoBatch temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				DiaoRenIntoBatch post = diaoRenIntoBatchService.get(temp.getId());
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				diaoRenIntoBatchService.queryValidateFormationAndPostLvlNum(post);//校验编控
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				diaoRenIntoBatchService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				
				//查询当前单位的编制信息，放入业务表中
				OrgInfo org = orgInfoService.findUniqueBy("organ.id", x.getId());
				if(org!=null){
					OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", org.getId());
					if(orgFormation!=null){
						BeanUtils.copyPropertiesIgnoreNull(orgFormation, temp);//复制编制信息
						temp.setId(null);//将id置空，新增数据
					}
				}
				temp.setTargetOrgan(x);//调入单位为当前单位
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				diaoRenIntoBatchService.queryValidateFormationAndPostLvlNum(temp);//校验编控
				
				temp.setId(null);
				temp.setStatus(DiaoRenIntoBatch.STATUS_DIAOREN_STATE);//流程状态，待提交
				temp.setSourceType(DiaoRenIntoBatch.SOURCE_TYPE_1);
				
				diaoRenIntoBatchService.saveOrUpdate(temp);
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
	@Log(title = "删除调任信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			diaoRenIntoBatchService.remove(id);
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
	 * @Description: 本区调任添加人员
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "新增调任人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/savePeople")
	public AjaxResult savePeople(String id,String servantIds){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(id)){
				throw new BusinessException("校验编制信息获取失败！");
			}
			if(StringUtils.isBlank(servantIds)){
				throw new BusinessException("添加人员信息获取失败！");
			}
			diaoRenIntoBatchService.savePeople(id,servantIds);
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
	 * @Title: view 
	 * @Description: 人员信息 查看详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantView")
	public String servantView(Model model,String id,String isFlow,String status,String areaType) {
		DiaoRenInto z = diaoRenIntoService.load(id);
		model.addAttribute("isFlow", isFlow);
		model.addAttribute("status", status);
		model.addAttribute("areaType", areaType);
		if(DiaoRenIntoMgr.AREA_THIS.equals(z.getDiaoRenIntoBatch().getAreaType())){//本区调任，人员来自事业单位或者国企职工
			if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(z.getDiaoRenIntoBatch().getSourceType())){//事业单位人员
				model.addAttribute("s", z.getPublicInstitution());
			}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(z.getDiaoRenIntoBatch().getSourceType())){//国企职工人员
				model.addAttribute("s", z.getNationalCompany());
			}
			model.addAttribute("d", z);
			model.addAttribute("o", z.getDiaoRenOut());//调出信息
		}else{//如果人员id为空
			Servant temp = new Servant();
			BeanUtils.copyPropertiesIgnoreNull(z, temp);
			model.addAttribute("s", temp);//人员基本信息
			model.addAttribute("d", z);
		}
		return DIAOREN_INTO_FLOW;
	}
	/**
	 * @Title: edit 
	 * @Description: 人员信息编辑页面
	 * @param	id 调任表单id，如果id存在，则判断对象中servant属性是否为空，如果id不存在，则判断servantId是否为空
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantEdit")
	public String servantEdit(String id,String servantId,String name,String cardNo,Model model,String batchId) {
		if(StringUtils.isNotBlank(id)){
			DiaoRenInto z = diaoRenIntoService.load(id);
			if(DiaoRenIntoMgr.AREA_THIS.equals(z.getDiaoRenIntoBatch().getAreaType())){//本区调任，人员来自事业单位或者国企职工
				if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(z.getDiaoRenIntoBatch().getSourceType())){//事业单位人员
					PtJobLevel tempJ = ptJobLevelService.getJobLevelByServantId(z.getPublicInstitution().getId());//查询当前人员的现行职级
					z.setSourceRealJobLevelCode(tempJ.getCode().getCode());
					z.setSourceRealJobLevelName(tempJ.getName());
					model.addAttribute("s", z.getPublicInstitution());
				}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(z.getDiaoRenIntoBatch().getSourceType())){//国企职工人员
					model.addAttribute("s", z.getNationalCompany());
				}
				model.addAttribute("d", z);
				return DIAOREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空
				model.addAttribute("d", z);
				model.addAttribute("batchId", z.getDiaoRenIntoBatch().getId());
				return DIAOREN_INTO_EDIT;
			}
		}else{
			if(StringUtils.isNotBlank(servantId)){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				model.addAttribute("s", servantService.load(servantId));
				return DIAOREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空，返回姓名和身份证号到页面，需要录入其他基本信息，姓名和身份证号不能录入
				model.addAttribute("batchId", batchId);
				return DIAOREN_INTO_EDIT;
			}
		}
	}
	/**
	 * @Title: delete 
	 * @Description: 人员信息删除
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "删除调任人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/servantDelete")
	public AjaxResult servantDelete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(id)){
				throw new BusinessException("请选择待删除的人员！");
			}
			diaoRenIntoService.deleteServant(id);
			result.setMessage("删除成功！");
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * @Title: saveOuter 
	 * @Description: 外区调任  调任调入情况信息保存
	 * @param temp		调任信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑调任信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveOuter")
	public AjaxResult saveOuter(DiaoRenInto temp){
		AjaxResult result = new AjaxResult(true);
		try {
			List<Servant> servantList = servantService.getServantByCardNo(temp.getCardNo());
			
			if(servantList!=null && servantList.size()>0){
				throw new BusinessException("该人员在系统中已存在！");
			}
			// 保存头像
			if(StringUtils.isNotBlank(temp.getPhotoPath())){
				temp.setPhotoPath(ImgPicUtil.savePic(temp.getPhotoPath()));
			}
			if(StringUtils.isNotBlank(temp.getId())){//更新
				DiaoRenInto post = diaoRenIntoService.get(temp.getId());
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				diaoRenIntoService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				
				temp.setId(null);
				diaoRenIntoService.saveOrUpdate(temp);
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
	 * @Title: save 
	 * @Description: 本区调任  调任调入情况信息保存
	 * @param temp		调任信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑调任信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveThis")
	public AjaxResult saveThis(DiaoRenInto temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				DiaoRenInto post = diaoRenIntoService.get(temp.getId());
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				diaoRenIntoService.saveOrUpdate(post);//保存
				
				if(temp.getDiaoRenOut()!=null){
					DiaoRenOut outTemp = temp.getDiaoRenOut();
					DiaoRenOut out = diaoRenOutService.load(outTemp.getId());
					BeanUtils.copyPropertiesIgnoreNull(outTemp, out);
					DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
					diaoRenOutService.update(out);
				}
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				
				temp.setId(null);
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("调任人员信息不能为空！");
				}
				Servant servant = servantService.get(temp.getServant().getId());
				if(servant.getNowPostCode()!=null){
					temp.setFormerUnitJobName(servant.getNowPostCode().getName());//原职务名称
				}
				
				diaoRenIntoService.saveOrUpdate(temp);
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
	 * @Title: operationFlow 
	 * @Description: 审批本区调任信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批调任流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(DiaoRenIntoBatch temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			DiaoRenIntoBatch post = diaoRenIntoBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
			diaoRenIntoBatchService.saveFlow(post,opinion,r);//提交流程
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
	 * @Title: operationFlowOuter 
	 * @Description: 审批外区调任信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批调任流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlowOuter")
	public AjaxResult operationFlowOuter(DiaoRenIntoBatch temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			DiaoRenIntoBatch post = diaoRenIntoBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
			diaoRenIntoBatchService.saveFlowOuter(post,opinion,r);//提交流程
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
