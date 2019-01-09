/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenKLBIntoBatchController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 跨类别转任 批量处理转任人员 控制器
 * 创建人: tzy   
 * 创建时间: 2018年12月10日 上午10:47:58 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月10日 上午10:47:58 
 * 修改任务号
 * 修改内容：
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
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBInto;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOut;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOutBatch;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBIntoBatchService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBIntoService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBOutBatchService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBOutService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.ImgPicUtil;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenKLBIntoBatchVO;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenKLBIntoVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: ZhuanRenKLBIntoBatchController 
 * @Description: 跨类别转任 批量处理转任人员 控制器
 * @author: tzy
 * @date: 2018年12月10日 上午10:47:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/zrklbIntoB")
public class ZhuanRenKLBIntoBatchController extends GenericController{
	@Autowired
	private ZhuanRenKLBIntoBatchService zhuanRenKLBIntoBatchService;
	@Autowired
	private ZhuanRenKLBIntoService zhuanRenKLBIntoService;
	@Autowired
	private ZhuanRenKLBOutService zhuanRenKLBOutService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private FlowRecordService flowRecordService;
	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;
	@Autowired
	private OrgFormationService orgFormationService;
	@Autowired
	private OrgInfoService orgInfoService;	
	@Autowired
	private JobLevelService jobLevelService;
	@Autowired
	private ZhuanRenKLBOutBatchService zhuanRenKLBOutBatchService;
	
	/**
	 * 转入情况列表
	 */
	private final static String ZHUANREN_INTO_LIST = "models/ofcflow/zhuanRenKLBB/intoList";
	/**
	 * 校验编制页面
	 */
	private final static String ZHUANREN_CHECK_FORMATION = "models/ofcflow/zhuanRenKLBB/checkFormation";
	/**
	 * 选择或新增 批量转任人员列表
	 */
	private final static String ZHUANREN_INTO_SERVANT_LIST = "models/ofcflow/zhuanRenKLBB/intoServantList";
	/**
	 * 转任人员流程处理页面
	 */
	private final static String ZHUANREN_INTO_BATCH_FLOW = "models/ofcflow/zhuanRenKLBB/intoBatchFlow";
	/**
	 * 转任人员不存在系统的编辑页面
	 */
	private final static String ZHUANREN_INTO_EDIT = "models/ofcflow/zhuanRenKLBB/intoEdit";
	/**
	 * 转任人员已存在系统的编辑页面
	 */
	private final static String ZHUANREN_INTO_EDIT_EXIST = "models/ofcflow/zhuanRenKLBB/intoEditExist";
	/**
	 * 转任人员流程处理页面
	 */
	private final static String ZHUANREN_INTO_FLOW = "models/ofcflow/zhuanRenKLBB/intoFlow";
	/**
	 * 流程待办列表
	 */
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	
	/**
	 * @Title: index 
	 * @Description: 转入情况列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return ZHUANREN_INTO_LIST;
	}
	/**
	 * @Title: check 
	 * @Description: 检验编制页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/check")
	public String check(String areaType,Model model) {
		ZhuanRenKLBIntoBatch z = new ZhuanRenKLBIntoBatch();
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
		
		return ZHUANREN_CHECK_FORMATION;
	}
	/**
	 * @Title: servantList 
	 * @Description: 选择转任人员
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantList")
	public String servantList(Model model,String id) {
		ZhuanRenKLBIntoBatch z = zhuanRenKLBIntoBatchService.get(id);
		model.addAttribute("d", z);
		return ZHUANREN_INTO_SERVANT_LIST;
	}
	/**
	 * @Title: intoList 
	 * @Description: 跨类别转任转入情况列表
	 * @param model
	 * @param yearTip
	 * @param name
	 * @param cardNo
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DraftServantVO>
	 */
	@Log(title = "查询跨类别转任列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/intoList")
	public Page<ZhuanRenKLBIntoBatchVO> intoList(Model model,String areaType,Integer sumNumberS,Integer sumNumberE,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ZhuanRenKLBIntoBatch where removed=:removed and creater=:creater");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
		if(StringUtils.isNotBlank(areaType)){//转入
			hql.append( " and areaType = :areaType");
			queryParameteritem=new QueryParameter("areaType", areaType);
			listqueryparameter.add(queryParameteritem);
		}
		if(sumNumberS!=null){//转任人数起
			hql.append( " and sumNumber >= :sumNumberS");
			queryParameteritem=new QueryParameter("sumNumberS", sumNumberS);
			listqueryparameter.add(queryParameteritem);
		}
		if(sumNumberE!=null){//转任人数止
			hql.append( " and sumNumber <= :sumNumberE");
			queryParameteritem=new QueryParameter("sumNumberE", sumNumberE);
			listqueryparameter.add(queryParameteritem);
		}
		hql.append( " order by createTime desc");
		
		Page<ZhuanRenKLBIntoBatchVO> pageInfo = zhuanRenKLBIntoBatchService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: intoServantList 
	 * @Description: 跨类别转任转入人员情况列表
	 * @param model
	 * @param name
	 * @param cardNo
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ZhuanRenKLBIntoVO>
	 */
	@Log(title = "查询跨类别转任人员列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/intoServantList")
	public Page<ZhuanRenKLBIntoVO> intoServantList(Model model,String name,String cardNo,Integer limit,Integer page,String id) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ZhuanRenKLBInto where removed=:removed and zhuanRenKLBIntoBatch.id=:zhuanRenKLBIntoBatch");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("zhuanRenKLBIntoBatch", id));//按批次加载数据
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
		
		Page<ZhuanRenKLBIntoVO> pageInfo = zhuanRenKLBIntoService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

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
		model.addAttribute("busType","ZhuanRenKLBIntoMgr_THIS,ZhuanRenKLBIntoMgr_OUTER,ZhuanRenKLBOutMgr");
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
		ZhuanRenKLBIntoBatch z = zhuanRenKLBIntoBatchService.get(id);
		model.addAttribute("d", z);
		return ZHUANREN_INTO_BATCH_FLOW;
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
		if("ZhuanRenKLBOutMgr".equals(flow.getBusType())){//跳转到转出流程页面
			ZhuanRenKLBOutBatch z = zhuanRenKLBOutBatchService.load(flow.getBusId());
			model.addAttribute("d", z);
			model.addAttribute("isFlow", true);
			if(z.getStatus()==ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_STATE){
				return ZhuanRenKLBOutBatchController.ZHUANREN_OUT_SERVANT_LIST;
			}else{
				if(z.getStatus()==ZhuanRenKLBOutBatch.STATUS_ZHUANCHU_PRINT){
					DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBOut.class);
					d.add(Restrictions.eq("zhuanRenKLBOutBatch.id", flow.getBusId()));
					d.add(Restrictions.eq("removed", false));
					List<ZhuanRenKLBOut> list = zhuanRenKLBOutService.findByCriteria(d);//批次下所有转任人员信息
					StringBuffer ids = new StringBuffer();
					for(ZhuanRenKLBOut into : list){
						ids.append(into.getId()).append(",");
					}
					ids.deleteCharAt(ids.lastIndexOf(","));
					model.addAttribute("ids", ids);//获取批次下所有人员业务id，用于下载介绍信
					model.addAttribute("introductionName", "转任介绍信");
				}
				return ZhuanRenKLBOutBatchController.ZHUANREN_OUT_BATCH_FLOW;
			}
		}else{
			ZhuanRenKLBIntoBatch z = zhuanRenKLBIntoBatchService.load(flow.getBusId());
			model.addAttribute("d", z);
			model.addAttribute("isFlow", true);
			if(z.getStatus()==ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_STATE){
				return ZHUANREN_INTO_SERVANT_LIST;
			}else{
				if(z.getStatus()==ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_PRINT){
					DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBInto.class);
					d.add(Restrictions.eq("zhuanRenKLBIntoBatch.id", flow.getBusId()));
					d.add(Restrictions.eq("removed", false));
					List<ZhuanRenKLBInto> list = zhuanRenKLBIntoService.findByCriteria(d);//批次下所有转任人员信息
					StringBuffer ids = new StringBuffer();
					for(ZhuanRenKLBInto into : list){
						ids.append(into.getId()).append(",");
					}
					ids.deleteCharAt(ids.lastIndexOf(","));
					model.addAttribute("ids", ids);//获取批次下所有人员业务id，用于下载介绍信
					model.addAttribute("introductionName", "转任介绍信");
				}
				return ZHUANREN_INTO_BATCH_FLOW;
			}
		}
	}
	/**
	 * @Title: save 
	 * @Description: 校验编制信息并保存
	 * @param temp
	 * @return: AjaxResult
	 */
	@Log(title = "跨类别转任-保存校验编制信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ZhuanRenKLBIntoBatch temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ZhuanRenKLBIntoBatch post = zhuanRenKLBIntoBatchService.get(temp.getId());
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				zhuanRenKLBIntoBatchService.queryValidateFormationAndPostLvlNum(post);//校验编控
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				zhuanRenKLBIntoBatchService.saveOrUpdate(post);//保存
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
				temp.setTargetOrgan(x);//转入单位为当前单位
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				zhuanRenKLBIntoBatchService.queryValidateFormationAndPostLvlNum(temp);//校验编控
				
				temp.setId(null);
				temp.setStatus(ZhuanRenKLBIntoBatch.STATUS_ZHUANREN_STATE);//流程状态，待提交
				
				zhuanRenKLBIntoBatchService.saveOrUpdate(temp);
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
	@Log(title = "删除跨类别转任信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			zhuanRenKLBIntoBatchService.remove(id);
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
	 * @Description: 本区转任添加人员
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "新增跨类别转任人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
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
			zhuanRenKLBIntoBatchService.savePeople(id,servantIds);
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
		ZhuanRenKLBInto z = zhuanRenKLBIntoService.load(id);
		model.addAttribute("isFlow", isFlow);
		model.addAttribute("status", status);
		model.addAttribute("areaType", areaType);
		if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
			model.addAttribute("d", z);//转入信息
			model.addAttribute("s", z.getServant());//人员基本信息
			model.addAttribute("o", z.getZhuanRenKLBOut());//转出信息
		}else{//如果人员id为空
			Servant temp = new Servant();
			BeanUtils.copyPropertiesIgnoreNull(z, temp);
			model.addAttribute("s", temp);//人员基本信息
			model.addAttribute("d", z);
		}
		return ZHUANREN_INTO_FLOW;
	}
	/**
	 * @Title: edit 
	 * @Description: 人员信息编辑页面
	 * @param	id 跨类别转任表单id，如果id存在，则判断对象中servant属性是否为空，如果id不存在，则判断servantId是否为空
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantEdit")
	public String servantEdit(String id,String servantId,String name,String cardNo,Model model,String batchId) {
		if(StringUtils.isNotBlank(id)){
			ZhuanRenKLBInto z = zhuanRenKLBIntoService.load(id);
			if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				JobLevel tempJ = jobLevelService.getJobLevelByServantId(z.getServant().getId());//查询当前人员的现行职级
				z.setJobLevelCode(tempJ.getCode());
				z.setJobLevelName(tempJ.getName());
				z.setIsLeader(tempJ.getIsLeader());
				z.setSourceRealJobLevelCode(tempJ.getRealJobLevelCode());
				z.setSourceRealLeader(tempJ.getRealLeader());
				model.addAttribute("d", z);
				model.addAttribute("s", z.getServant());
				return ZHUANREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空
				model.addAttribute("d", z);
				model.addAttribute("batchId", z.getZhuanRenKLBIntoBatch().getId());
				return ZHUANREN_INTO_EDIT;
			}
		}else{
			if(StringUtils.isNotBlank(servantId)){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				model.addAttribute("s", servantService.load(servantId));
				return ZHUANREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空，返回姓名和身份证号到页面，需要录入其他基本信息，姓名和身份证号不能录入
				model.addAttribute("batchId", batchId);
				return ZHUANREN_INTO_EDIT;
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
	@Log(title = "删除跨类别转任人员", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/servantDelete")
	public AjaxResult servantDelete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isBlank(id)){
				throw new BusinessException("请选择待删除的人员！");
			}
			zhuanRenKLBIntoService.deleteServant(id);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * @Title: saveOuter 
	 * @Description: 外区转任  跨类别转任调入情况信息保存
	 * @param temp		转任信息
	 * @return: AjaxResult
	 */
	@Log(title = "保存跨类别转任人员信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveOuter")
	public AjaxResult saveOuter(ZhuanRenKLBInto temp){
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
				ZhuanRenKLBInto post = zhuanRenKLBIntoService.get(temp.getId());
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				zhuanRenKLBIntoService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				
				temp.setId(null);
				zhuanRenKLBIntoService.saveOrUpdate(temp);
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
	 * @Description: 本区转任  跨类别转任调入情况信息保存
	 * @param temp		转任信息
	 * @return: AjaxResult
	 */
	@Log(title = "保存跨类别转任人员信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveThis")
	public AjaxResult saveThis(ZhuanRenKLBInto temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ZhuanRenKLBInto post = zhuanRenKLBIntoService.get(temp.getId());
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				zhuanRenKLBIntoService.saveOrUpdate(post);//保存
				
				if(temp.getZhuanRenKLBOut()!=null){
					ZhuanRenKLBOut outTemp = temp.getZhuanRenKLBOut();
					ZhuanRenKLBOut out = zhuanRenKLBOutService.load(outTemp.getId());
					BeanUtils.copyPropertiesIgnoreNull(outTemp, out);
					DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
					zhuanRenKLBOutService.update(out);
				}
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				
				temp.setId(null);
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("转任人员信息不能为空！");
				}
				Servant servant = servantService.get(temp.getServant().getId());
				if(servant.getNowPostCode()!=null){
					temp.setFormerUnitJobName(servant.getNowPostCode().getName());//原职务名称
				}
				
				zhuanRenKLBIntoService.saveOrUpdate(temp);
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
	 * @Description: 审批本区转任信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批跨类别转任流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ZhuanRenKLBIntoBatch temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			ZhuanRenKLBIntoBatch post = zhuanRenKLBIntoBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
			zhuanRenKLBIntoBatchService.saveFlow(post,opinion,r);//提交流程
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
	 * @Description: 审批外区转任信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "审批跨类别转任流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlowOuter")
	public AjaxResult operationFlowOuter(ZhuanRenKLBIntoBatch temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			ZhuanRenKLBIntoBatch post = zhuanRenKLBIntoBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
			zhuanRenKLBIntoBatchService.saveFlowOuter(post,opinion,r);//提交流程
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
	 * @Title: printIntroduction 
	 * @Description: 打印介绍信
	 * @param type	 1:转入  2:转出
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/printIntroduction")
	public void printIntroduction(String id,String type, HttpServletRequest request, HttpServletResponse response) {
		try {
			if(StringUtils.isBlank(id)){
				throw new BusinessException("打印信息不正确！");
			}
			Map<String,Object> params = new HashMap<>();//所有参数
			List<String[]> dataList = new ArrayList<>();//所有人员信息
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if("1".equals(type)){
				ZhuanRenKLBIntoBatch batch = zhuanRenKLBIntoBatchService.get(id);
				params.put("sourceOrgan", batch.getSourceOrgan().getName());//源单位名称
				params.put("targetOrgan", batch.getTargetOrgan()==null?"":batch.getTargetOrgan().getName());//目标单位名称
				params.put("enterTheUnitDate", batch.getEnterTheUnitDate()==null?"":sdf.format(batch.getEnterTheUnitDate()));//报道日期
				
				DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBInto.class);
				d.add(Restrictions.eq("zhuanRenKLBIntoBatch.id", id));
				d.add(Restrictions.eq("removed", false));
				List<ZhuanRenKLBInto> list = zhuanRenKLBIntoService.findByCriteria(d);//批次下所有转任人员信息
				params.put("sumNumber", Number2CN.cvt(list.size(),true));//转任人数
				for(int i =0;i<list.size();i++){
					ZhuanRenKLBInto into = list.get(i);
					String[] p = new String[6];
					p[2] = into.getFormerUnitName();//源单位名称
					if(ZhuanRenKLBIntoBatch.AREA_THIS.equals(into.getZhuanRenKLBIntoBatch().getAreaType())){//本区
						if(i==0){
							params.put("name", list.get(0).getServant().getName());
						}
						p[0] = into.getServant().getName();//转任人员名称
						p[1] = into.getServant().getSex()==null?"":into.getServant().getSex().getName();//调任人员性别
						p[4] = into.getServant().getNowPostName()==null?"":into.getServant().getNowPostName();//原职务
						p[5] = into.getJobLevelName()==null?"":into.getJobLevelName();//原级别
					}else{
						if(i==0){
							params.put("name", into.getName());
						}
						p[0] = into.getName();//转任人员名称
						p[1] = into.getSex()==null?"":into.getSex().getName();//性别
						p[4] = "";//原职务
						p[5] = "";//原级别
					}
					dataList.add(p);
				}
				params.put("listData", dataList);//list数据需要用list标签
			}else{
//				ZhuanRenKLBOut out = zhuanRenKLBOutService.get(id);
//				params.put("sourceOrgan", out.getSourceOrgan()==null?"":out.getSourceOrgan().getName());//源单位名称
//				params.put("targetOrgan", out.getGotoUnitName());//目标单位名称
//				params.put("name", out.getServant().getName());//转任人员名称
//				params.put("sex", out.getServant().getSex()==null?"":out.getServant().getSex().getName());//调任人员性别
//				params.put("post", out.getServant().getNowPostName()==null?"":out.getServant().getNowPostName());//原职务
//				params.put("postLevel", out.getServant().getNowJobLevel()==null?"":out.getServant().getNowJobLevel().getName());//原级别
			}
			params.put("now", sdf.format(new Date()));//打印介绍信时间
			params.put("busType", "跨类别转任");//转移原因
			
			Map<String,String> number = ofcFlowNumberService.executeNumber("ZhuanRenKLB", id);//介绍信编号
			params.put("number", number.get("year")+"字第"+number.get("number"));//介绍信编号
			//编号转换为大写
			String cnYear = Number2CN.convert(number.get("year"));
			String cnNumber = Number2CN.convert(number.get("number"));
			params.put("cnNumber", cnYear+"字第"+cnNumber+"号");//介绍信编号大写
			//当前操作人
			params.put("userName", SecurityUtils.getPrincipal().getName());
			
			String savePath = request.getSession().getServletContext().getRealPath("/");
			String templet = savePath+"\\static\\templates\\introduce.xls";//模板路径
			String path = savePath+"\\static\\templates\\"+id+".xls";//生成excel文件路径，临时存放，下载成功之后会删除
			ExcelUtilsPOI.replaceModel(params, templet,path, 2,null);//替换模板数据 生成excel到tomcat服务器
		  	ExcelUtilsPOI.exceldown(path, params.get("sourceOrgan")+"转任介绍信"+".xls", request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
