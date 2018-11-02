/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaorenIntoController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 公务员调任-调入流程控制器
 * 创建人: tzy   
 * 创建时间: 2018年10月16日 下午3:53:45 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月16日 下午3:53:45 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DiaoRenIntoMgr;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOutMgr;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.company.NationalCompanyService;
import com.wondersgroup.human.service.ofcflow.DiaoRenIntoMgrService;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutMgrService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.DiaoRenIntoMgrVO;

import net.sf.json.JSONArray;

/** 
 * @ClassName: DiaorenIntoController 
 * @Description: 公务员调任-调入流程控制器
 * @author: tzy
 * @date: 2018年10月16日 下午3:53:45
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/diaoren")
public class DiaorenIntoController extends GenericController implements ApplicationContextAware{
	@Autowired
	private DiaoRenIntoMgrService diaoRenIntoMgrService;
	@Autowired
	private DiaoRenOutMgrService diaoRenOutMgrService;
	@Autowired
	private OrganNodeService organNodeService;
	@Autowired
	private FlowRecordService flowRecordService;
	@Autowired
	private DictableService dictableService;
	/**
	 * 事业单位人员信息service
	 */
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	/**
	 * 国企职工人员信息service
	 */
	@Autowired
	private NationalCompanyService nationalCompanyService;
	
	/**
	 * 读取message.properties配置文件数据
	 */
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 通知消息
	 */
	private ApplicationContext applicationContext;
	
	/**
	 * 调入情况列表
	 */
	private final static String DIAOREN_INTO_LIST = "models/ofcflow/diaoren/intoList";
	/**
	 * 人员检索页面
	 */
	private final static String CHECK_PERSON = "models/ofcflow/diaoren/checkPerson";
	/**
	 * 调任人员不存在系统的编辑页面
	 */
	private final static String DIAOREN_INTO_EDIT = "models/ofcflow/diaoren/intoEdit";
	/**
	 * 调任人员已存在系统的编辑页面
	 */
	private final static String DIAOREN_INTO_EDIT_EXIST = "models/ofcflow/diaoren/intoEditExist";
	/**
	 * 流程处理页面
	 */
	private final static String DIAOREN_INTO_FLOW = "models/ofcflow/diaoren/intoFlow";
	/**
	 * 流程待办列表
	 */
	private final static String RECRUIT_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	/**
	 * 调出情况编辑页面 外区
	 */
	private final static String DIAOREN_OUT_EDIT_OUTER = "models/ofcflow/diaoren/outEditOuter";
	/**
	 * 调出情况编辑页面 本区
	 */
	private final static String DIAOREN_OUT_EDIT_THIS = "models/ofcflow/diaoren/outEditThis";
	/**
	 * 调出情况流程页面 外区
	 */
	private final static String DIAOREN_OUT_FLOW_OUTER = "models/ofcflow/diaoren/outFlowOuter";
	/**
	 * 调出情况流程页面 本区
	 */
	private final static String DIAOREN_OUT_FLOW_THIS = "models/ofcflow/diaoren/outFlowThis";
	
	/**
	 * @Title: recruitList 
	 * @Description: 调入情况列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String recruitList() {
		return DIAOREN_INTO_LIST;
	}
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","DiaoRenIntoMgr_THIS,DiaoRenIntoMgr_OUTER,DiaoRenOutMgr_THIS");
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
		if("DiaoRenOutMgr_THIS".equals(flow.getBusType())){//调出流程页面
			DiaoRenOutMgr z = diaoRenOutMgrService.get(flow.getBusId());
			model.addAttribute("d", z);
			model.addAttribute("s", z.getServant());
			model.addAttribute("isFlow", true);
			if(DiaoRenOutMgr.STATUS_DIAOCHU_STATE==z.getStatus()){//待提交节点，返回可编辑页面
				if(DiaoRenOutMgr.AREA_THIS.equals(z.getAreaType())){//本区
					return DIAOREN_OUT_EDIT_THIS;
				}else{
					return DIAOREN_OUT_EDIT_OUTER;
				}
			}else{
				if(DiaoRenOutMgr.AREA_OUTER.equals(z.getAreaType())){//本区
					return DIAOREN_OUT_FLOW_OUTER;
				}else{
					return DIAOREN_OUT_FLOW_THIS;
				}
			}
		}else{
			DiaoRenIntoMgr z = diaoRenIntoMgrService.load(flow.getBusId());
			if(DiaoRenIntoMgr.AREA_THIS.equals(z.getAreaType())){//本区调任，人员来自事业单位或者国企职工
				model.addAttribute("d", z);
				if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(z.getSourceType())){//事业单位人员
					model.addAttribute("s", z.getPublicInstitution());
				}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(z.getSourceType())){//国企职工人员
					model.addAttribute("s", z.getNationalCompany());
				}
				model.addAttribute("o", z.getDiaoRenOutMgr());//调出信息
			}else{//如果人员id为空
				Servant temp = new Servant();
				BeanUtils.copyPropertiesIgnoreNull(z, temp);
				temp.setId(null);//将ID置空，以免数据出错
				model.addAttribute("s", temp);//人员基本信息
				model.addAttribute("d", z);
			}
			model.addAttribute("isFlow", true);
			if(z.getStatus()==DiaoRenIntoMgr.STATUS_DIAOREN_STATE){
				if(DiaoRenIntoMgr.AREA_THIS.equals(z.getAreaType())){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
					return DIAOREN_INTO_EDIT_EXIST;
				}else{//如果人员id为空
					return DIAOREN_INTO_EDIT;
				}
			}else{
				return DIAOREN_INTO_FLOW;
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
	@RequestMapping("/view")
	public String view(Model model,String id) {
		DiaoRenIntoMgr z = diaoRenIntoMgrService.load(id);
		if(DiaoRenIntoMgr.AREA_THIS.equals(z.getAreaType())){//本区调任，人员来自事业单位或者国企职工
			model.addAttribute("d", z);
			if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(z.getSourceType())){//事业单位人员
				model.addAttribute("s", z.getPublicInstitution());
			}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(z.getSourceType())){//国企职工人员
				model.addAttribute("s", z.getNationalCompany());
			}
			model.addAttribute("o", z.getDiaoRenOutMgr());//调出信息
		}else{//如果人员id为空
			Servant temp = new Servant();
			BeanUtils.copyPropertiesIgnoreNull(z, temp);
			temp.setId(null);//将ID置空，以免数据出错
			model.addAttribute("s", temp);//人员基本信息
			model.addAttribute("d", z);
		}
		return DIAOREN_INTO_FLOW;
	}
	/**
	 * @Title: edit 
	 * @Description: 编辑页面
	 * @param	id 跨类别调任表单id，如果id存在，则判断对象中servant属性是否为空，如果id不存在，则判断servantId是否为空
	 * @param	type 1：事业单位人员  2：国企职工人员
	 * @return
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String id,String servantId,String name,String cardNo,String type,Model model) {
		if(StringUtils.isNotBlank(id)){
			DiaoRenIntoMgr z = diaoRenIntoMgrService.load(id);
			if(DiaoRenIntoMgr.AREA_THIS.equals(z.getAreaType())){//本区调任，人员来自事业单位或者国企职工
				model.addAttribute("d", z);
				if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(z.getSourceType())){//事业单位人员
					model.addAttribute("s", z.getPublicInstitution());
				}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(z.getSourceType())){//国企职工人员
					model.addAttribute("s", z.getNationalCompany());
				}
				model.addAttribute("sourceType", z.getSourceType());
				return DIAOREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空
				model.addAttribute("d", z);
				return DIAOREN_INTO_EDIT;
			}
		}else{
			if(StringUtils.isNotBlank(servantId)){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(type)){//事业单位人员
					model.addAttribute("s", publicInstitutionService.load(servantId));
				}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(type)){//国企职工人员
					model.addAttribute("s", nationalCompanyService.load(servantId));
				}
				model.addAttribute("sourceType", type);
				
				return DIAOREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空，返回姓名和身份证号到页面，需要录入其他基本信息，姓名和身份证号不能录入
				Map<String,String> map = new HashMap<>();
				map.put("name", name);
				map.put("cardNo", cardNo);
				model.addAttribute("d", map);
				return DIAOREN_INTO_EDIT;
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
	public String check(Model model) {
		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> map1 = new HashMap<>();
		map1.put("key", DiaoRenIntoMgr.SOURCE_TYPE_1);
		map1.put("value", "事业单位人员");
		list.add(map1);
//		Map<String,String> map2 = new HashMap<>();
//		map2.put("key", DiaoRenIntoMgr.SOURCE_TYPE_2);
//		map2.put("value", "国企职工人员");
//		list.add(map2);
		model.addAttribute("typeList", JSONArray.fromObject(list).toString());
		return CHECK_PERSON;
	}
	/**
	 * @Title: checkServant 
	 * @Description: 根据姓名和身份证号校验人员是否存在
	 * @param name		姓名
	 * @param cardNo	身份证号
	 * @param type 1：事业单位人员  2：国企职工人员
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/checkServant")
	public AjaxResult checkServant(String name,String cardNo,String type){
		AjaxResult result = new AjaxResult(false);
		result.setMessage("系统中不存在该人员，无法发起调任申请！");
		try {
			DetachedCriteria d = DetachedCriteria.forClass(DiaoRenIntoMgr.class);
			d.add(Restrictions.eq("name", name));
			d.add(Restrictions.eq("cardNo", cardNo));
			d.add(Restrictions.ne("status", DiaoRenIntoMgr.STATUS_DIAOREN_FINISH));
			d.add(Restrictions.eq("removed", false));
			List<DiaoRenIntoMgr> z = diaoRenIntoMgrService.findByCriteria(d);
			if(z.size()>0){//跨类别调入表中检索，如果检索出数据，则是编辑操作
				Map<String,String> map = new HashMap<>();
				if(z.get(0).getStatus()>DiaoRenIntoMgr.STATUS_DIAOREN_STATE||!SecurityUtils.getUserId().equals(z.get(0).getCreater())){
					map.put("code", "3");
					result.setData(map);
					result.setSuccess(true);
					result.setMessage("该人员正在调任流程中，无法再次发起调任申请！");
				}else{
					map.put("id", z.get(0).getId());
					map.put("code", "2");
					Servant s = z.get(0).getServant();
					if(s!=null){
						map.put("departname", s.getDepartName());
					}
					result.setData(map);
					result.setSuccess(true);
					result.setMessage("已录入此人员的调任信息，请点击提交后在表单中修改该人员详细信息！");
				}
			}else{
				if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(type)){//事业单位人员
					DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PublicInstitution.class);
					detachedCriteria.add(Restrictions.eq("name", name));
					detachedCriteria.add(Restrictions.eq("cardNo", cardNo));
					CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
					detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
					detachedCriteria.add(Restrictions.eq("removed", false));
					
//					List<CodeInfo> typeList = DictCacheProvider.getCodeInfoByCodeTypeAndParentCode(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, "4");//人员类别为事业单位人员的CODE
//					List<String> personType = new ArrayList<>();
//					for(CodeInfo t:typeList){
//						personType.add(t.getId());
//					}
//					detachedCriteria.add(Restrictions.in("personType.id", personType));//只查询事业单位人员
					List<PublicInstitution> s = publicInstitutionService.findByCriteria(detachedCriteria);
					if(s.size()>0){
						Map<String,String> map = new HashMap<>();
						map.put("departname", s.get(0).getDepartName());
						map.put("id", s.get(0).getId());
						map.put("code", "1");
						result.setData(map);
						result.setSuccess(true);
						result.setMessage("系统中存在该人员，请点击提交后录入信息发起调入流程！");
					}
				}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(type)){//国企职工人员
					DetachedCriteria detachedCriteria = DetachedCriteria.forClass(NationalCompany.class);
					detachedCriteria.add(Restrictions.eq("name", name));
					detachedCriteria.add(Restrictions.eq("cardNo", cardNo));
					CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
					detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
					detachedCriteria.add(Restrictions.eq("removed", false));
//					List<CodeInfo> typeList = DictCacheProvider.getCodeInfoByCodeTypeAndParentCode(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, "5");//人员类别为国企职工的CODE
//					List<String> personType = new ArrayList<>();
//					for(CodeInfo t:typeList){
//						personType.add(t.getId());
//					}
//					detachedCriteria.add(Restrictions.in("personType.id", personType));//只查询国企职工
					List<NationalCompany> s = nationalCompanyService.findByCriteria(detachedCriteria);
					if(s.size()>0){
						Map<String,String> map = new HashMap<>();
						map.put("departname", s.get(0).getDepartName());
						map.put("id", s.get(0).getId());
						map.put("code", "1");
						result.setData(map);
						result.setSuccess(true);
						result.setMessage("系统中存在该人员，请点击提交后录入信息发起调入流程！");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
	@ResponseBody
	@RequestMapping("/intoList")
	public Page<DiaoRenIntoMgrVO> intoList(Model model,String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DiaoRenIntoMgr where removed=:removed and creater=:creater");
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
		
		Page<DiaoRenIntoMgrVO> pageInfo = diaoRenIntoMgrService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 本区调任  调入情况信息保存
	 * @param temp		调任信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(DiaoRenIntoMgr temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				DiaoRenIntoMgr post = diaoRenIntoMgrService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				diaoRenIntoMgrService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				temp.setId(null);
				temp.setStatus(DiaoRenIntoMgr.STATUS_DIAOREN_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//调入单位为当前单位
				if(temp.getSourceType()==null||StringUtils.isBlank(temp.getSourceType())){
					throw new BusinessException("人员类别不能为空！");
				}
				if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(temp.getSourceType())){//事业单位人员
					PublicInstitution servant = publicInstitutionService.get(temp.getPublicInstitution().getId());
					OrganNode n = organNodeService.load(servant.getDepartId());
					temp.setSourceOrgan(n);//调任人员调出单位
					temp.setFormerUnitName(n.getName());//原单位名称
					if(servant.getNowPostCode()!=null){
						temp.setFormerUnitJobName(servant.getNowPostCode().getName());//原职务名称
					}
				}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(temp.getSourceType())){//国企职工人员
					NationalCompany servant = nationalCompanyService.get(temp.getNationalCompany().getId());
					OrganNode n = organNodeService.load(servant.getDepartId());
					temp.setSourceOrgan(n);//调任人员调出单位
					temp.setFormerUnitName(n.getName());//原单位名称
					if(servant.getNowPostCode()!=null){
						temp.setFormerUnitJobName(servant.getNowPostCode().getName());//原职务名称
					}
				}
				temp.setAreaType(DiaoRenIntoMgr.AREA_THIS);//本区调任
				
				diaoRenIntoMgrService.saveOrUpdate(temp);
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
	 * @Description: 外区调任  调入情况信息保存
	 * @param temp		调任信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveOuter")
	public AjaxResult saveOuter(DiaoRenIntoMgr temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				DiaoRenIntoMgr post = diaoRenIntoMgrService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				diaoRenIntoMgrService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				temp.setId(null);
				temp.setStatus(DiaoRenIntoMgr.STATUS_DIAOREN_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//调入单位为当前单位
				temp.setAreaType(DiaoRenIntoMgr.AREA_OUTER);//外区调任
				diaoRenIntoMgrService.saveOrUpdate(temp);
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
			DiaoRenIntoMgr into = diaoRenIntoMgrService.get(id);
			diaoRenIntoMgrService.delete(into);
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
	 * @Description: 审批本区调任信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(DiaoRenIntoMgr temp, HttpServletRequest request) {
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
				if(temp.getSourceType()==null||StringUtils.isBlank(temp.getSourceType())){
					throw new BusinessException("人员类别不能为空！");
				}
				temp.setId(null);
				temp.setStatus(DiaoRenIntoMgr.STATUS_DIAOREN_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//调入单位为当前单位
				temp.setAreaType(DiaoRenIntoMgr.AREA_THIS);//本区调任
				if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(temp.getSourceType())){//事业单位人员
					if(temp.getPublicInstitution()==null||StringUtils.isBlank(temp.getPublicInstitution().getId())){
						throw new BusinessException("调任人员信息不能为空！");
					}
					PublicInstitution servant = publicInstitutionService.get(temp.getPublicInstitution().getId());
					OrganNode n = organNodeService.load(servant.getDepartId());
					if(n==null||StringUtils.isBlank(n.getId())){
						throw new BusinessException("调任人员单位信息不能为空！");
					}
					temp.setSourceOrgan(n);//调任人员调出单位
					temp.setFormerUnitName(n.getName());//原单位名称
					if(servant.getNowPostCode()!=null){
						temp.setFormerUnitJobName(servant.getNowPostCode().getName());//原职务名称
					}
				}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(temp.getSourceType())){//国企职工人员
					if(temp.getNationalCompany()==null||StringUtils.isBlank(temp.getNationalCompany().getId())){
						throw new BusinessException("调任人员信息不能为空！");
					}
					NationalCompany servant = nationalCompanyService.get(temp.getNationalCompany().getId());
					OrganNode n = organNodeService.load(servant.getDepartId());
					if(n==null||StringUtils.isBlank(n.getId())){
						throw new BusinessException("调任人员单位信息不能为空！");
					}
					temp.setSourceOrgan(n);//调任人员调出单位
					temp.setFormerUnitName(n.getName());//原单位名称
					if(servant.getNowPostCode()!=null){
						temp.setFormerUnitJobName(servant.getNowPostCode().getName());//原职务名称
					}
				}
				
				diaoRenIntoMgrService.saveFlow(temp,opinion,r);//提交流程
			} else {
				DiaoRenIntoMgr post = diaoRenIntoMgrService.get(temp.getId());
				temp.setStatus(post.getStatus());//设置一下，后面发送通知时在使用
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				diaoRenIntoMgrService.saveFlow(post,opinion,r);//提交流程
			}
			if(DiaoRenIntoMgr.STATUS_DIAOREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){
				//发送通知
				String title = messageSource.getMessage("diaoRenTitle", new Object[]{temp.getName()}, Locale.CHINESE);
				String content = messageSource.getMessage("diaoRenContent", new Object[]{temp.getName()}, Locale.CHINESE);
				applicationContext.publishEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, ""));
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
	 * @Description: 审批外区调任信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlowOuter")
	public AjaxResult operationFlowOuter(DiaoRenIntoMgr temp, HttpServletRequest request) {
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
				temp.setStatus(DiaoRenIntoMgr.STATUS_DIAOREN_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//调入单位为当前单位
				temp.setAreaType(DiaoRenIntoMgr.AREA_OUTER);//外区调任
				
				diaoRenIntoMgrService.saveFlowOuter(temp,opinion,r);//提交流程
			} else {
				DiaoRenIntoMgr post = diaoRenIntoMgrService.get(temp.getId());
				temp.setStatus(post.getStatus());//设置一下，后面发送通知时在使用
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				diaoRenIntoMgrService.saveFlowOuter(post,opinion,r);//提交流程
			}
			if(DiaoRenIntoMgr.STATUS_DIAOREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){
				//发送通知
				String title = messageSource.getMessage("diaoRenTitle", new Object[]{temp.getName()}, Locale.CHINESE);
				String content = messageSource.getMessage("diaoRenContent", new Object[]{temp.getName()}, Locale.CHINESE);
				applicationContext.publishEvent(new AnnouncementEventData(true, temp.getCreater(), title, content, ""));
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
	 * @param type	 1:调入  2:调出
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
			params.put("name", "");//调任人员名称
			params.put("date", "");//调任时间
			if("1".equals(type)){
				DiaoRenIntoMgr into = diaoRenIntoMgrService.get(id);
				params.put("sourceOrgan", into.getFormerUnitName());//源单位名称
				params.put("targetOrgan", into.getTargetOrgan()==null?"":into.getTargetOrgan().getName());//目标单位名称
				if(DiaoRenIntoMgr.AREA_THIS.equals(into.getAreaType())){//本区
					if(DiaoRenIntoMgr.SOURCE_TYPE_1.equals(into.getSourceType())){//事业人员
						params.put("name", into.getPublicInstitution().getName());//调任人员名称
					}else if(DiaoRenIntoMgr.SOURCE_TYPE_2.equals(into.getSourceType())){//国企职工
						params.put("name", into.getNationalCompany().getName());//调任人员名称
					}
				}else{
					params.put("name", into.getName());//调任人员名称
				}
				if(into.getEnterTheUnitDate()!=null){
					params.put("date", sdf.format(into.getEnterTheUnitDate()));//调任时间
				}else{
					params.put("date", sdf.format(new Date()));//调任时间
				}
			}else{
				DiaoRenOutMgr out = diaoRenOutMgrService.get(id);
				params.put("sourceOrgan", out.getSourceOrgan()==null?"":out.getSourceOrgan().getName());//源单位名称
				params.put("targetOrgan", out.getGotoUnitName());//目标单位名称
				params.put("name", out.getServant().getName());//调任人员名称
				if(out.getOutDate()!=null){
					params.put("date", sdf.format(out.getOutDate()));//调任时间
				}else{
					params.put("date", sdf.format(new Date()));//调任时间
				}
			}
			params.put("now", sdf.format(new Date()));//打印介绍信时间
			WordUtils.exportMillCertificateWord(request, response, params, params.get("sourceOrgan")+"调任介绍信", "drIntroduction.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** (non Javadoc) 
	 * @Title: setApplicationContext
	 * @Description: 通知消息
	 * @param applicationContext
	 * @throws BeansException 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext) 
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
