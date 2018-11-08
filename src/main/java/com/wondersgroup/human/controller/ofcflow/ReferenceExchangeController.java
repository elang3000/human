/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 参公交流 控制器
 * 创建人: tzy   
 * 创建时间: 2018年9月27日 下午3:23:40 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月27日 下午3:23:40 
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

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.provider.DictCacheProvider;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchange;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOut;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoMgr;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeVO;

/** 
 * @ClassName: ReferenceExchangeController 
 * @Description: 参公交流 控制器
 * @author: tzy
 * @date: 2018年9月27日 下午3:23:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/exchange")
public class ReferenceExchangeController extends GenericController{
	@Autowired
	private ReferenceExchangeService referenceExchangeService;
	@Autowired
	private ReferenceExchangeOutService referenceExchangeOutService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private OrganNodeService organNodeService;
	@Autowired
	private FlowRecordService flowRecordService;
	@Autowired
	private DictableService dictableService;

	@Autowired
	private FormationControlService formationControlService;
	
	/**
	 * 参公交流列表
	 */
	private final static String EXCHANGE_INTO_LIST = "models/ofcflow/exchange/intoList";
	/**
	 * 人员检索页面
	 */
	private final static String CHECK_SERVANT = "models/ofcflow/exchange/checkServant";
	/**
	 * 参公交流人员不存在系统的编辑页面
	 */
	private final static String EXCHANGE_INTO_EDIT = "models/ofcflow/exchange/intoEdit";
	/**
	 * 参公交流人员已存在系统的编辑页面
	 */
	private final static String EXCHANGE_INTO_EDIT_EXIST = "models/ofcflow/exchange/intoEditExist";
	/**
	 * 流程处理页面
	 */
	private final static String EXCHANGE_INTO_FLOW = "models/ofcflow/exchange/intoFlow";
	/**
	 * 流程待办列表
	 */
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	
	/**
	 * @Title: recruitList 
	 * @Description: 转入情况列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return EXCHANGE_INTO_LIST;
	}
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","ReferenceExchange_THIS,ReferenceExchange_OUTER");
		return RECRUIT_EMPLOYPLAN_FLOW;
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
		ReferenceExchange z = referenceExchangeService.load(flow.getBusId());
		if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
			model.addAttribute("d", z);
			model.addAttribute("s", z.getServant());
		}else{//如果人员id为空
			Servant temp = new Servant();
			BeanUtils.copyPropertiesIgnoreNull(z, temp);
			temp.setId(null);//将ID置空，以免数据出错
			model.addAttribute("s", temp);//人员基本信息
			model.addAttribute("d", z);
		}
		model.addAttribute("isFlow", true);
		if(z.getStatus()==ReferenceExchange.STATUS_EXCHANGE_STATE){
			if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				return EXCHANGE_INTO_EDIT_EXIST;
			}else{//如果人员id为空
				return EXCHANGE_INTO_EDIT;
			}
		}else{
			return EXCHANGE_INTO_FLOW;
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
	@RequestMapping("/view")
	public String view(Model model,String id) {
		ReferenceExchange z = referenceExchangeService.load(id);
		if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
			model.addAttribute("d", z);//转入信息
			model.addAttribute("s", z.getServant());//人员基本信息
		}else{//如果人员id为空
			Servant temp = new Servant();
			BeanUtils.copyPropertiesIgnoreNull(z, temp);
			model.addAttribute("s", temp);//人员基本信息
			model.addAttribute("d", z);
		}
		return EXCHANGE_INTO_FLOW;
	}
	/**
	 * @Title: edit 
	 * @Description: 编辑页面
	 * @param	id 参公交流表单id，如果id存在，则判断对象中servant属性是否为空，如果id不存在，则判断servantId是否为空
	 * @return
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String id,String servantId,String name,String cardNo,Model model) {
		if(StringUtils.isNotBlank(id)){
			ReferenceExchange z = referenceExchangeService.load(id);
			if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				model.addAttribute("d", z);
				model.addAttribute("s", z.getServant());
				return EXCHANGE_INTO_EDIT_EXIST;
			}else{//如果人员id为空
				model.addAttribute("d", z);
				return EXCHANGE_INTO_EDIT;
			}
		}else{
			if(StringUtils.isNotBlank(servantId)){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				model.addAttribute("s", servantService.load(servantId));
				return EXCHANGE_INTO_EDIT_EXIST;
			}else{//如果人员id为空，返回姓名和身份证号到页面，需要录入其他基本信息，姓名和身份证号不能录入
				Map<String,String> map = new HashMap<>();
				map.put("name", name);
				map.put("cardNo", cardNo);
				model.addAttribute("d", map);
				return EXCHANGE_INTO_EDIT;
			}
		}
	}
	/**
	 * @Title: check
	 * @Description: 人员检索页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/check")
	public String check() {
		return CHECK_SERVANT;
	}
	/**
	 * @Title: checkServant 
	 * @Description: 根据姓名和身份证号校验人员是否存在
	 * @param name		姓名
	 * @param cardNo	身份证号
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/checkServant")
	public AjaxResult checkServant(String name,String cardNo){
		AjaxResult result = new AjaxResult(false);
		result.setMessage("系统中不存在该参公人员，无法发起转任申请！");
		try {
			DetachedCriteria d = DetachedCriteria.forClass(ReferenceExchange.class);
			d.add(Restrictions.eq("name", name));
			d.add(Restrictions.eq("cardNo", cardNo));
			d.add(Restrictions.ne("status", ReferenceExchange.STATUS_EXCHANGE_FINISH));
			d.add(Restrictions.eq("removed", false));
			List<ReferenceExchange> z = referenceExchangeService.findByCriteria(d);
			if(z.size()>0){//跨类别转入表中检索，如果检索出数据，则是编辑操作
				Map<String,String> map = new HashMap<>();
				if(z.get(0).getStatus()>ReferenceExchange.STATUS_EXCHANGE_STATE||!SecurityUtils.getUserId().equals(z.get(0).getCreater())){
					map.put("code", "3");
					result.setData(map);
					result.setSuccess(true);
					result.setMessage("该人员正在转任流程中，无法再次发起转任申请！");
				}else{
					map.put("id", z.get(0).getId());
					map.put("code", "2");
					Servant s = z.get(0).getServant();
					if(s!=null){
						map.put("departname", s.getDepartName());
					}
					result.setData(map);
					result.setSuccess(true);
					result.setMessage("已录入此人员的转任信息，请点击提交后在表单中修改该人员详细信息！");
				}
			}else{
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
				detachedCriteria.add(Restrictions.eq("name", name));
				detachedCriteria.add(Restrictions.eq("cardNo", cardNo));
				CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
				detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
				detachedCriteria.add(Restrictions.eq("removed", false));
				List<CodeInfo> typeList = DictCacheProvider.getCodeInfoByCodeTypeAndParentCode(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, "2");//人员类别为参公人员的CODE
				List<String> personType = new ArrayList<>();
				for(CodeInfo t:typeList){
					personType.add(t.getId());
				}
				detachedCriteria.add(Restrictions.in("personType.id", personType));//只查询参公人员
				List<Servant> s = servantService.findByCriteria(detachedCriteria);
				if(s.size()>0){
					Map<String,String> map = new HashMap<>();
					map.put("departname", s.get(0).getDepartName());
					map.put("id", s.get(0).getId());
					map.put("code", "1");
					result.setData(map);
					result.setSuccess(true);
					result.setMessage("系统中存在该人员，请点击提交后录入信息发起转入流程！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Title: intoList 
	 * @Description: 参公交流转入情况列表
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
	@RequestMapping("/intoList")
	public Page<ReferenceExchangeVO> intoList(Model model,String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ReferenceExchange where removed=:removed and creater=:creater");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
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
		
		Page<ReferenceExchangeVO> pageInfo = referenceExchangeService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 参公交流信息保存
	 * @param temp		参公交流信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ReferenceExchange temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ReferenceExchange post = referenceExchangeService.get(temp.getId());
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				formationControlService.queryJudgeFormationNum(post.getTargetOrgan().getId());
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				referenceExchangeService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				formationControlService.queryJudgeFormationNum(x.getId());
				
				temp.setId(null);
				temp.setStatus(ReferenceExchange.STATUS_EXCHANGE_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//转入单位为当前单位
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("参公交流人员信息不能为空！");
				}
				Servant servant = servantService.get(temp.getServant().getId());
				OrganNode n = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(n);//转任人员转出单位
				temp.setSourceOrganName(n.getName());//原单位名称
				temp.setAreaType(ReferenceExchange.AREA_THIS);//本区转任
				
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
	 * @param temp		参公交流信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveOuter")
	public AjaxResult saveOuter(ReferenceExchange temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ReferenceExchange post = referenceExchangeService.get(temp.getId());
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				formationControlService.queryJudgeFormationNum(post.getTargetOrgan().getId());
				
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				referenceExchangeService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				//编控，校验编制数是否足够，判断数据能否保存，如果超编，抛出异常
				formationControlService.queryJudgeFormationNum(x.getId());
				
				temp.setId(null);
				temp.setStatus(ReferenceExchange.STATUS_EXCHANGE_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//转入单位为当前单位
				temp.setAreaType(ReferenceExchange.AREA_OUTER);//外区转任
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
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			ReferenceExchange into = referenceExchangeService.get(id);
			referenceExchangeService.delete(into);
			result.setMessage("删除成功！");
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
	public AjaxResult operationFlow(ReferenceExchange temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				temp.setId(null);
				temp.setStatus(ReferenceExchange.STATUS_EXCHANGE_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//转入单位为当前单位
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("参公交流人员信息不能为空！");
				}
				Servant servant = servantService.get(temp.getServant().getId());
				OrganNode n = organNodeService.load(servant.getDepartId());
				if(n==null||StringUtils.isBlank(n.getId())){
					throw new BusinessException("参公交流人员单位信息不能为空！");
				}
				temp.setSourceOrgan(n);//转任人员转出单位
				temp.setSourceOrganName(n.getName());//原单位名称
				temp.setAreaType(ReferenceExchange.AREA_THIS);//本区转任
				
				referenceExchangeService.saveFlow(temp,opinion,r);//提交流程
			} else {
				ReferenceExchange post = referenceExchangeService.get(temp.getId());
				temp.setStatus(post.getStatus());//设置一下，后面发送通知时在使用
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				referenceExchangeService.saveFlow(post,opinion,r);//提交流程
			}
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
	public AjaxResult operationFlowOuter(ReferenceExchange temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				temp.setId(null);
				temp.setStatus(ReferenceExchange.STATUS_EXCHANGE_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//转入单位为当前单位
				temp.setAreaType(ReferenceExchange.AREA_OUTER);//外区转任
				
				referenceExchangeService.saveFlowOuter(temp,opinion,r);//提交流程
			} else {
				ReferenceExchange post = referenceExchangeService.get(temp.getId());
				temp.setStatus(post.getStatus());//设置一下，后面发送通知时在使用
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				referenceExchangeService.saveFlowOuter(post,opinion,r);//提交流程
			}
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String,String> params = new HashMap<>();
			params.put("sourceOrgan", "");//源单位名称
			params.put("targetOrgan", "");//目标单位名称
			params.put("name", "");//转任人员名称
			params.put("date", "");//转任时间
			if("1".equals(type)){
				ReferenceExchange into = referenceExchangeService.get(id);
				params.put("sourceOrgan", into.getSourceOrganName());//源单位名称
				params.put("targetOrgan", into.getTargetOrgan()==null?"":into.getTargetOrgan().getName());//目标单位名称
				if(ZhuanRenKLBIntoMgr.AREA_THIS.equals(into.getAreaType())){//本区
					params.put("name", into.getServant().getName());//转任人员名称
				}else{
					params.put("name", into.getName());//转任人员名称
				}
				if(into.getOutDate()!=null){
					params.put("date", sdf.format(into.getOutDate()));//转任时间
				}else{
					params.put("date", sdf.format(new Date()));//转任时间
				}
			}else{
				ReferenceExchangeOut out = referenceExchangeOutService.get(id);
				params.put("sourceOrgan", out.getSourceOrgan().getName());//源单位名称
				params.put("targetOrgan", out.getGotoUnitName());//目标单位名称
				params.put("name", out.getServant().getName());//转任人员名称
				if(out.getOutDate()!=null){
					params.put("date", sdf.format(out.getOutDate()));//转任时间
				}else{
					params.put("date", sdf.format(new Date()));//转任时间
				}
			}
			params.put("now", sdf.format(new Date()));//打印介绍信时间
			WordUtils.exportMillCertificateWord(request, response, params, params.get("sourceOrgan")+"参公交流介绍信", "cgjlIntroduction.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
