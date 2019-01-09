/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ReferenceExchangeBatchController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow
 * 描述: TODO
 * 创建人: GP
 * 创建时间: 2018年12月17日 上午10:58:48
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2018年12月17日 上午10:58:48
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
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchange;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBIntoBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOut;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeBatchService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.ImgPicUtil;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeBatchVO;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeVO;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBIntoVO;

/**
 * @ClassName: ReferenceExchangeBatchController
 * @Description: TODO
 * @author: GP
 * @date: 2018年12月17日 上午10:58:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("ofcflow/exchangeB")
public class ReferenceExchangeBatchController extends GenericController {
	
	private final String EXCHANGE_BATCH_INDEX = "models/ofcflow/exchangebatch/exchangebatchindex";
	
	private final String EXCHANGE_BATCH_CHECK = "models/ofcflow/exchangebatch/exchangebatchcheck";
	
	private final String EXCHANGE_BATCH_SERVANT_LIST = "models/ofcflow/exchangebatch/intoServantList";
	
	/**
	 * 参公交流人员不存在系统的编辑页面
	 */
	private final static String EXCHANGE_INTO_EDIT = "models/ofcflow/exchangebatch/intoEdit";
	
	/**
	 * 参公交流人员已存在系统的编辑页面
	 */
	private final static String EXCHANGE_INTO_EDIT_EXIST = "models/ofcflow/exchangebatch/intoEditExist";
	
	/**
	 * 参公交流表单查看页面
	 */
	private final static String EXCHANGE_INTO_FLOW = "models/ofcflow/exchangebatch/intoFlow";
	
	/**
	 * 参公交流人员流程处理页面
	 */
	private final static String EXCHANGE_INTO_BATCH_FLOW = "models/ofcflow/exchangebatch/intoBatchFlow";
	
	/**
	 * 转出
	 */
	private final static String EXCHANGE_OUT_BATCH_INDEX = "models/ofcflow/exchangebatch/outlist";
	
	/**
	 * 流程待办列表
	 */
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	
	@Autowired
	private ReferenceExchangeBatchService referenceExchangeBatchService;
	
	@Autowired
	private OrgFormationService orgFormationService;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private FormationControlService formationControlService;
	
	@Autowired
	private ReferenceExchangeService referenceExchangeService;
	
	@Autowired
	private JobLevelService jobLevelService;
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;
	
	@RequestMapping("/index")
	public String exchangbatchindex(Model model) {
		
		return EXCHANGE_BATCH_INDEX;
	}
	
	@RequestMapping("/check")
	public String check(Model model, String areaType) {
		
		ReferenceExchangeBatch z = new ReferenceExchangeBatch();
		z.setAreaType(areaType);
		// 查询当前单位编制情况
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		OrgInfo org = orgInfoService.findUniqueBy("organ.id", x.getId());
		if (org != null) {
			OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", org.getId());
			if (orgFormation != null) {
				BeanUtils.copyPropertiesIgnoreNull(orgFormation, z);
				z.setId(null);
			}
		}
		model.addAttribute("d", z);
		model.addAttribute("organCode", x.getCode());// 不能选择人员来源单位为自己单位
		
		return EXCHANGE_BATCH_CHECK;
	}
	
	/**
	 * 保存
	 * @Title: save
	 * @Description: TODO 保存参公交流批量基本信息
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ReferenceExchangeBatch temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				ReferenceExchangeBatch post = referenceExchangeBatchService.get(temp.getId());
				// 编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				String unitId = post.getTargetOrgan().getId();
				formationControlService.queryJudgeFormationNum(unitId, temp.getSumNumber());// 校验人数
				// if(temp.getSectionChiefNumber()!=null&&temp.getSectionChiefNumber()!=0){
				// formationControlService.queryJudgePostNum(unitId,
				// FormationControl.xzjg.get("SECTION_CHIEF"),
				// temp.getSectionChiefNumber());//校验正科职级人数
				// }
				// if(temp.getDeputySectionChiefNumber()!=null&&temp.getDeputySectionChiefNumber()!=0){
				// formationControlService.queryJudgePostNum(unitId,
				// FormationControl.xzjg.get("DEPUTY_SECTION_CHIEF"),
				// temp.getDeputySectionChiefNumber());//校验副科职级人数
				// }
				// if(temp.getClerkNumber()!=null&&temp.getClerkNumber()!=0){
				// formationControlService.queryJudgePostNum(unitId,
				// FormationControl.xzjg.get("CLERK"), temp.getClerkNumber());//校验科员职级人数
				// }
				// if(temp.getcClerkNumber()!=null&&temp.getcClerkNumber()!=0){
				// formationControlService.queryJudgePostNum(unitId,
				// FormationControl.xzjg.get("C_CLERK"), temp.getcClerkNumber());//校验办事员职级人数
				// }
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);// 将CodeInfo中id为空的属性 设置为null
				referenceExchangeBatchService.saveOrUpdate(post);// 保存
			} else {// 新增
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if (x == null || StringUtils.isBlank(x.getId())) { throw new BusinessException("单位信息不能为空！"); }
				// 编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				String unitId = x.getId();
				formationControlService.queryJudgeFormationNum(unitId, temp.getSumNumber());// 校验人数
				// 查询当前单位的编制信息，放入业务表中
				OrgInfo org = orgInfoService.findUniqueBy("organ.id", x.getId());
				if (org != null) {
					OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", org.getId());
					if (orgFormation != null) {
						BeanUtils.copyPropertiesIgnoreNull(orgFormation, temp);// 复制编制信息
						temp.setId(null);// 将id置空，新增数据
					}
				}
				// if(temp.getSectionChiefNumber()!=null&&temp.getSectionChiefNumber()!=0){
				// formationControlService.queryJudgePostNum(unitId,
				// FormationControl.xzjg.get("SECTION_CHIEF"),
				// temp.getSectionChiefNumber());//校验正科职级人数
				// }
				// if(temp.getDeputySectionChiefNumber()!=null&&temp.getDeputySectionChiefNumber()!=0){
				// formationControlService.queryJudgePostNum(unitId,
				// FormationControl.xzjg.get("DEPUTY_SECTION_CHIEF"),
				// temp.getDeputySectionChiefNumber());//校验副科职级人数
				// }
				// if(temp.getClerkNumber()!=null&&temp.getClerkNumber()!=0){
				// formationControlService.queryJudgePostNum(unitId,
				// FormationControl.xzjg.get("CLERK"), temp.getClerkNumber());//校验科员职级人数
				// }
				// if(temp.getcClerkNumber()!=null&&temp.getcClerkNumber()!=0){
				// formationControlService.queryJudgePostNum(unitId,
				// FormationControl.xzjg.get("C_CLERK"), temp.getcClerkNumber());//校验办事员职级人数
				// }
				
				temp.setId(null);
				temp.setStatus(ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE);// 流程状态，待提交
				temp.setTargetOrgan(x);// 转入单位为当前单位
				temp.setApplyDate(new Date());
				referenceExchangeBatchService.saveOrUpdate(temp);
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
	 * @Title: servantList
	 * @Description: 选择参公交流人员
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantList")
	public String servantList(Model model, String id) {
		
		ReferenceExchangeBatch z = referenceExchangeBatchService.get(id);
		model.addAttribute("d", z);
		return EXCHANGE_BATCH_SERVANT_LIST;
	}
	
	/**
	 * @Title: edit
	 * @Description: 人员信息编辑页面
	 * @param id 参公交流表单id，如果id存在，则判断对象中servant属性是否为空，如果id不存在，则判断servantId是否为空
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantEdit")
	public String servantEdit(String id, String servantId, String name, String cardNo, Model model, String batchId) {
		
		if (StringUtils.isNotBlank(id)) {
			ReferenceExchange z = referenceExchangeService.load(id);
			if (z.getServant() != null) {// 如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				JobLevel tempJ = jobLevelService.getJobLevelByServantId(z.getServant().getId());// 查询当前人员的现行职级
				z.setJobLevelCode(tempJ.getCode());
				z.setJobLevelName(tempJ.getName());
				model.addAttribute("d", z);
				model.addAttribute("s", z.getServant());
				model.addAttribute("batchId", z.getReferenceExchangeBatch().getId());
				return EXCHANGE_INTO_EDIT_EXIST;
			} else {// 如果人员id为空
				model.addAttribute("d", z);
				model.addAttribute("batchId", z.getReferenceExchangeBatch().getId());
				return EXCHANGE_INTO_EDIT;
			}
		} else {
			if (StringUtils.isNotBlank(servantId)) {// 如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				
				model.addAttribute("s", servantService.load(servantId));
				return EXCHANGE_INTO_EDIT_EXIST;
			} else {// 如果人员id为空，返回姓名和身份证号到页面，需要录入其他基本信息，姓名和身份证号不能录入
				model.addAttribute("batchId", batchId);
				return EXCHANGE_INTO_EDIT;
			}
		}
	}
	
	/**
	 * @Title: view
	 * @Description: 查看详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantView")
	public String servantView(Model model, String id) {
		
		ReferenceExchange z = referenceExchangeService.load(id);
		if (z.getServant() != null) {// 如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
			model.addAttribute("d", z);// 转入信息
			model.addAttribute("s", z.getServant());// 人员基本信息
		} else {// 如果人员id为空
			Servant temp = new Servant();
			BeanUtils.copyPropertiesIgnoreNull(z, temp);
			model.addAttribute("s", temp);// 人员基本信息
			model.addAttribute("d", z);
		}
		return EXCHANGE_INTO_FLOW;
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
		
		ReferenceExchangeBatch r = referenceExchangeBatchService.get(id);
		model.addAttribute("d", r);
		return EXCHANGE_INTO_BATCH_FLOW;
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
		model.addAttribute("introductionName", "参公交流介绍信");
		FlowRecord flow = flowRecordService.get(id);
		ReferenceExchangeBatch z = referenceExchangeBatchService.load(flow.getBusId());
		List<ReferenceExchange> exitems=referenceExchangeBatchService.itembatchItems(z.getId());
		StringBuilder itemidssb=new StringBuilder();
		for (ReferenceExchange referenceExchange : exitems) {
			itemidssb.append(referenceExchange.getId());
			itemidssb.append(",");
		}
		itemidssb.deleteCharAt(itemidssb.length()-1);
		model.addAttribute("d", z);
		model.addAttribute("itemids", itemidssb.toString());
		model.addAttribute("isFlow", true);
		if (z.getStatus() == ZhuanRenTLBIntoBatch.STATUS_ZHUANREN_STATE) {
			return EXCHANGE_BATCH_SERVANT_LIST;
		} else {
			return EXCHANGE_INTO_BATCH_FLOW;
		}
	}
	
	/**
	 * 转出情况
	 * @Title: outindex
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@RequestMapping("/outindex")
	public String outindex() {
		
		return EXCHANGE_OUT_BATCH_INDEX;
	}
	
	/**
	 * @Title: flow
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		
		model.addAttribute("busType", "ReferenceExchange_THIS,ReferenceExchange_OUTER");
		return RECRUIT_EMPLOYPLAN_FLOW;
	}
	
	@ResponseBody
	@RequestMapping("/pagelist")
	public Page<ReferenceExchangeBatchVO> pagelist(Model model, String areaType, Integer sumNumberS, Integer sumNumberE,
	        Integer limit, Integer pageNo) {
		
		if (pageNo == null || pageNo == 0)
			pageNo = 1;
		// List<QueryParameter> listqueryparameter=new ArrayList<>();
		// StringBuilder hql=new StringBuilder();
		// hql.append("from ReferenceExchangeBatch where removed=:removed and creater=:creater");
		// QueryParameter queryParameteritem=new QueryParameter("removed", false);
		// listqueryparameter.add(queryParameteritem);
		// listqueryparameter.add(new QueryParameter("creater",
		// SecurityUtils.getUserId()));//只能操作自己的数据
		// if(StringUtils.isNotBlank(areaType)){//转入
		// hql.append( " and areaType = :areaType");
		// queryParameteritem=new QueryParameter("areaType", areaType);
		// listqueryparameter.add(queryParameteritem);
		// }
		// if(sumNumberS!=null){//参公交流人数起
		// hql.append( " and sumNumber >= :sumNumberS");
		// queryParameteritem=new QueryParameter("sumNumberS", sumNumberS);
		// listqueryparameter.add(queryParameteritem);
		// }
		// if(sumNumberE!=null){//参公交流人数止
		// hql.append( " and sumNumber <= :sumNumberE");
		// queryParameteritem=new QueryParameter("sumNumberE", sumNumberE);
		// listqueryparameter.add(queryParameteritem);
		// }
		// hql.append( " order by createTime desc");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReferenceExchangeBatch.class);
		if (StringUtils.isNotBlank(areaType)) {// 转入
			detachedCriteria.add(Restrictions.eq("areaType", areaType));
		}
		if (sumNumberS != null) {// 参公交流人数起
			detachedCriteria.add(Restrictions.gt("sumNumber", sumNumberS));
		}
		if (sumNumberE != null) {// 参公交流人数止
			detachedCriteria.add(Restrictions.le("sumNumber", sumNumberS));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		Page<ReferenceExchangeBatchVO> pageInfo = referenceExchangeBatchService.getPage(detachedCriteria, pageNo,
		        limit);// .findbyHQLforVO(hql.toString(), listqueryparameter, pageNo, limit);
		
		return pageInfo;
	}
	
	/**
	 * @Title: intoServantList
	 * @Description: 参公交流选择人员情况列表
	 * @param model
	 * @param name
	 * @param cardNo
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ZhuanRenTLBIntoVO>
	 */
	@ResponseBody
	@RequestMapping("/intoServantList")
	public Page<ReferenceExchangeVO> intoServantList(Model model, String name, String cardNo, Integer limit,
	        Integer page, String id) {
		
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter = new ArrayList<>();
		StringBuilder hql = new StringBuilder();
		hql.append(
		        "from ReferenceExchange where removed=:removed and referenceExchangeBatch.id=:referenceExchangeBatch");
		QueryParameter queryParameteritem = new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("referenceExchangeBatch", id));// 按批次加载数据
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and name like :name");
			queryParameteritem = new QueryParameter("name", "%" + name + "%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("name", name);
		}
		if (StringUtils.isNotBlank(cardNo)) {
			hql.append(" and cardNo = :cardNo");
			queryParameteritem = new QueryParameter("cardNo", cardNo);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("cardNo", cardNo);
		}
		hql.append(" order by createTime desc");
		
		Page<ReferenceExchangeVO> pageInfo = referenceExchangeService.findbyHQLforVO(hql.toString(), listqueryparameter,
		        page, limit);
		
		return pageInfo;
	}
	
	/**
	 * @Title: savePeople
	 * @Description: 本区参公交流添加人员
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/savePeople")
	public AjaxResult savePeople(String id, String servantIds) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(id)) { throw new BusinessException("校验编制信息获取失败！"); }
			if (StringUtils.isBlank(servantIds)) { throw new BusinessException("添加人员信息获取失败！"); }
			referenceExchangeBatchService.savePeople(id, servantIds);
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
	 * @Title: save
	 * @Description: 本区参公交流 参公交流调入情况信息保存
	 * @param temp 参公交流信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveThis")
	public AjaxResult saveThis(ReferenceExchange temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				ReferenceExchange post = referenceExchangeService.get(temp.getId());
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);// 将CodeInfo中id为空的属性 设置为null
				referenceExchangeService.saveOrUpdate(post);// 保存
			} else {// 新增
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				// 编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				
				temp.setId(null);
				temp.setStatus(ReferenceExchange.STATUS_EXCHANGE_STATE);// 流程状态，待提交
				if (temp.getServant() == null || StringUtils
				        .isBlank(temp.getServant().getId())) { throw new BusinessException("参公交流人员信息不能为空！"); }
				Servant servant = servantService.get(temp.getServant().getId());
				OrganNode n = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(n);// 参公交流人员转出单位
				temp.setSourceOrganName(n.getName());// 原单位名称
				temp.setAreaType(ReferenceExchange.AREA_THIS);// 本区参公交流
				
				referenceExchangeService.saveOrUpdate(temp);
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
	 * @Title: saveOuter
	 * @Description: 外区参公交流信息保存
	 * @param temp 参公交流信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveOuter")
	public AjaxResult saveOuter(ReferenceExchange temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			List<Servant> servantList = servantService.getServantByCardNo(temp.getCardNo());
			
			if (servantList != null && servantList.size() > 0) { throw new BusinessException("该人员在系统中已存在！"); }
			// 保存头像
			if (StringUtils.isNotBlank(temp.getPhotoPath())) {
				temp.setPhotoPath(ImgPicUtil.savePic(temp.getPhotoPath()));
			}
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				ReferenceExchange post = referenceExchangeService.get(temp.getId());
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);// 将CodeInfo中id为空的属性 设置为null
				referenceExchangeService.saveOrUpdate(post);// 保存
			} else {// 新增
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				temp.setStatus(ReferenceExchange.STATUS_EXCHANGE_STATE);// 流程状态，待提交
				temp.setAreaType(ReferenceExchange.AREA_OUTER);// 外区参公交流
				referenceExchangeService.saveOrUpdate(temp);
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
			ReferenceExchangeBatch reb = referenceExchangeBatchService.get(id);
			referenceExchangeBatchService.delete(reb);// (id);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/servantDelete")
	public AjaxResult servantDelete(String id) {
		AjaxResult result = new AjaxResult(true);
		try {
		if(id.contains(",")){//批量删除
			for(String i : id.split(",")){
				ReferenceExchange re = referenceExchangeService.get(i);
				referenceExchangeService.delete(re);// (id);
			}
			result.setMessage("删除成功！");
		}else{
			ReferenceExchange re = referenceExchangeService.get(id);
			referenceExchangeService.delete(re);// (id);
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
	 * @Title: operationFlow
	 * @Description: 审批本区参公交流信息
	 * @param temp
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ReferenceExchangeBatch temp, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");// 审批意见
		String r = request.getParameter("result");// 审批结果
		try {
			ReferenceExchangeBatch post = referenceExchangeBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);// 将CodeInfo中id为空的属性 设置为null
			referenceExchangeBatchService.saveFlow(post, opinion, r);// 提交流程
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
	 * @Description: 审批外区参公交流信息
	 * @param temp
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlowOuter")
	public AjaxResult operationFlowOuter(ReferenceExchangeBatch temp, HttpServletRequest request) {
		
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");// 审批意见
		String r = request.getParameter("result");// 审批结果
		try {
			ReferenceExchangeBatch post = referenceExchangeBatchService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, post);
			DictUtils.operationCodeInfo(post);// 将CodeInfo中id为空的属性 设置为null
			referenceExchangeBatchService.saveFlowOuter(post, opinion, r);// 提交流程
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
			ReferenceExchange into = referenceExchangeService.get(id);
			
			ReferenceExchangeBatch batch = into.getReferenceExchangeBatch();
			params.put("sourceOrgan", batch.getSourceOrgan().getName());// 源单位名称
			params.put("targetOrgan", batch.getTargetOrgan() == null ? "" : batch.getTargetOrgan().getName());// 目标单位名称
			 params.put("enterTheUnitDate",batch.getEnterTheUnitDate()==null?"":sdf.format(batch.getEnterTheUnitDate()));//报道日期
			
			params.put("sumNumber", Number2CN.cvt(1, true));// 参公交流人数
			String[] p = new String[6];
			p[2] = into.getSourceOrganName();// 源单位名称
			if (ZhuanRenTLBIntoBatch.AREA_THIS.equals(into.getReferenceExchangeBatch().getAreaType())) {// 本区
				params.put("name", into.getServant().getName());
				p[0] = into.getServant().getName();// 参公交流人员名称
				p[1] = into.getServant().getSex() == null ? "" : into.getServant().getSex().getName();// 参公交流人员性别
				p[4] = into.getServant().getNowPostName() == null ? "" : into.getServant().getNowPostName();// 原职务
				p[5] = into.getJobLevelName() == null ? "" : into.getJobLevelName();// 原级别
			} else {
				params.put("name", into.getName());
				p[0] = into.getName();// 参公交流人员名称
				p[1] = into.getSex() == null ? "" : into.getSex().getName();// 性别
				p[4] = "";// 原职务
				p[5] = "";// 原级别
			}
			dataList.add(p);
			params.put("listData", dataList);// list数据需要用list标签
			params.put("now", sdf.format(new Date()));// 打印介绍信时间
			params.put("busType", "参公交流");// 转移原因
			
			Map<String, String> number = ofcFlowNumberService.executeNumber("ReferenceExchange", id);// 介绍信编号
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
