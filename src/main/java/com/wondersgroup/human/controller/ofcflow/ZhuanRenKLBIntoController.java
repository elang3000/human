/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenKLBIntoController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 跨类别转任 调入情况
 * 创建人: tzy   
 * 创建时间: 2018年9月20日 上午11:08:41 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月20日 上午11:08:41 
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
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoMgr;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOutMgr;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBIntoMgrService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBOutMgrService;
import com.wondersgroup.human.util.WordUtils;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenKLBIntoMgrVO;

/** 
 * @ClassName: ZhuanRenKLBIntoController 
 * @Description: 跨类别转任 本区转任情况
 * @author: tzy
 * @date: 2018年9月20日 上午11:08:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/zrklbInto")
public class ZhuanRenKLBIntoController extends GenericController implements ApplicationContextAware{
	@Autowired
	private ZhuanRenKLBIntoMgrService zhuanRenKLBIntoMgrService;
	@Autowired
	private ZhuanRenKLBOutMgrService zhuanRenKLBOutMgrService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private OrganNodeService organNodeService;
	@Autowired
	private FlowRecordService flowRecordService;
	@Autowired
	private DictableService dictableService;
	
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
	 * 转入情况列表
	 */
	private final static String ZHUANREN_INTO_LIST = "models/ofcflow/zhuanRenKLB/intoList";
	/**
	 * 人员检索页面
	 */
	private final static String CHECK_SERVANT = "models/ofcflow/zhuanRenKLB/checkServant";
	/**
	 * 转任人员不存在系统的编辑页面
	 */
	private final static String ZHUANREN_INTO_EDIT = "models/ofcflow/zhuanRenKLB/intoEdit";
	/**
	 * 转任人员已存在系统的编辑页面
	 */
	private final static String ZHUANREN_INTO_EDIT_EXIST = "models/ofcflow/zhuanRenKLB/intoEditExist";
	/**
	 * 流程处理页面
	 */
	private final static String ZHUANREN_INTO_FLOW = "models/ofcflow/zhuanRenKLB/intoFlow";
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
	public String recruitList() {
		return ZHUANREN_INTO_LIST;
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
		ZhuanRenKLBIntoMgr z = zhuanRenKLBIntoMgrService.load(flow.getBusId());
		if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
			model.addAttribute("d", z);
			model.addAttribute("s", z.getServant());
			model.addAttribute("o", z.getZhuanRenKLBOutMgr());//转出信息
		}else{//如果人员id为空
			Servant temp = new Servant();
			BeanUtils.copyPropertiesIgnoreNull(z, temp);
			temp.setId(null);//将ID置空，以免数据出错
			model.addAttribute("s", temp);//人员基本信息
			model.addAttribute("d", z);
		}
		model.addAttribute("isFlow", true);
		if(z.getStatus()==ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE){
			if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				return ZHUANREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空
				return ZHUANREN_INTO_EDIT;
			}
		}else{
			return ZHUANREN_INTO_FLOW;
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
		ZhuanRenKLBIntoMgr z = zhuanRenKLBIntoMgrService.load(id);
		if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
			model.addAttribute("d", z);//转入信息
			model.addAttribute("s", z.getServant());//人员基本信息
			model.addAttribute("o", z.getZhuanRenKLBOutMgr());//转出信息
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
	 * @Description: 编辑页面
	 * @param	id 跨类别转任表单id，如果id存在，则判断对象中servant属性是否为空，如果id不存在，则判断servantId是否为空
	 * @return
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String id,String servantId,String name,String cardNo,Model model) {
		if(StringUtils.isNotBlank(id)){
			ZhuanRenKLBIntoMgr z = zhuanRenKLBIntoMgrService.load(id);
			if(z.getServant()!=null){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				model.addAttribute("d", z);
				model.addAttribute("s", z.getServant());
				return ZHUANREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空
				model.addAttribute("d", z);
				return ZHUANREN_INTO_EDIT;
			}
		}else{
			if(StringUtils.isNotBlank(servantId)){//如果人员id不为空，该人员在系统中已存在，查询人员基本信息，返回人员已存在的编辑页面
				model.addAttribute("s", servantService.load(servantId));
				return ZHUANREN_INTO_EDIT_EXIST;
			}else{//如果人员id为空，返回姓名和身份证号到页面，需要录入其他基本信息，姓名和身份证号不能录入
				Map<String,String> map = new HashMap<>();
				map.put("name", name);
				map.put("cardNo", cardNo);
				model.addAttribute("d", map);
				return ZHUANREN_INTO_EDIT;
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
		result.setMessage("系统中不存在该公务员，无法发起转任申请！");
		try {
			DetachedCriteria d = DetachedCriteria.forClass(ZhuanRenKLBIntoMgr.class);
			d.add(Restrictions.eq("name", name));
			d.add(Restrictions.eq("cardNo", cardNo));
			d.add(Restrictions.ne("status", ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_FINISH));
			d.add(Restrictions.eq("removed", false));
			List<ZhuanRenKLBIntoMgr> z = zhuanRenKLBIntoMgrService.findByCriteria(d);
			if(z.size()>0){//跨类别转入表中检索，如果检索出数据，则是编辑操作
				Map<String,String> map = new HashMap<>();
				if(z.get(0).getStatus()>ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE||!SecurityUtils.getUserId().equals(z.get(0).getCreater())){
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
				List<CodeInfo> typeList = DictCacheProvider.getCodeInfoByCodeTypeAndParentCode(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, "1");//人员类别为公务员的CODE
				List<String> personType = new ArrayList<>();
				for(CodeInfo t:typeList){
					personType.add(t.getId());
				}
				detachedCriteria.add(Restrictions.in("personType.id", personType));//只查询公务员
				List<Servant> s = servantService.findByCriteria(detachedCriteria);
				if(s.size()>0){
					Map<String,String> map = new HashMap<>();
					OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
					if(organ.getId().equals(s.get(0).getDepartId())){
						map.put("code", "5");
						result.setData(map);
						result.setSuccess(true);
						result.setMessage("该人员已在本单位，无法发起转任申请！");
					}else{
						map.put("departname", s.get(0).getDepartName());
						map.put("id", s.get(0).getId());
						map.put("code", "1");
						result.setData(map);
						result.setSuccess(true);
						result.setMessage("系统中存在该人员，请点击提交后录入信息发起转入流程！");
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
	@ResponseBody
	@RequestMapping("/intoList")
	public Page<ZhuanRenKLBIntoMgrVO> intoList(Model model,String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ZhuanRenKLBIntoMgr where removed=:removed and creater=:creater");
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
		
		Page<ZhuanRenKLBIntoMgrVO> pageInfo = zhuanRenKLBIntoMgrService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 本区转任  跨类别转任调入情况信息保存
	 * @param temp		转任信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ZhuanRenKLBIntoMgr temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ZhuanRenKLBIntoMgr post = zhuanRenKLBIntoMgrService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				zhuanRenKLBIntoMgrService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				temp.setId(null);
				temp.setStatus(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//转入单位为当前单位
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("转任人员信息不能为空！");
				}
				Servant servant = servantService.get(temp.getServant().getId());
				OrganNode n = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(n);//转任人员转出单位
				temp.setFormerUnitName(n.getName());//原单位名称
				if(servant.getNowPostCode()!=null){
					temp.setFormerUnitJobName(servant.getNowPostCode().getName());//原职务名称
				}
				temp.setAreaType(ZhuanRenKLBIntoMgr.AREA_THIS);//本区转任
				
				zhuanRenKLBIntoMgrService.saveOrUpdate(temp);
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
	 * @Description: 外区转任  跨类别转任调入情况信息保存
	 * @param temp		转任信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/saveOuter")
	public AjaxResult saveOuter(ZhuanRenKLBIntoMgr temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ZhuanRenKLBIntoMgr post = zhuanRenKLBIntoMgrService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				zhuanRenKLBIntoMgrService.saveOrUpdate(post);//保存
			}else{//新增
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				if(x==null||StringUtils.isBlank(x.getId())){
					throw new BusinessException("单位信息不能为空！");
				}
				temp.setId(null);
				temp.setStatus(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//转入单位为当前单位
				temp.setAreaType(ZhuanRenKLBIntoMgr.AREA_OUTER);//外区转任
				zhuanRenKLBIntoMgrService.saveOrUpdate(temp);
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
	 * @Description: 删除 转任调入信息并删除根据该人员姓名和身份证号删除子集
	 * @param id
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			ZhuanRenKLBIntoMgr into = zhuanRenKLBIntoMgrService.get(id);
			zhuanRenKLBIntoMgrService.delete(into);
//			zhuanRenKLBIntoMgrService.remove(id);
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
	 * @Description: 审批本区转任信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ZhuanRenKLBIntoMgr temp, HttpServletRequest request) {
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
				temp.setStatus(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//转入单位为当前单位
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("转任人员信息不能为空！");
				}
				Servant servant = servantService.get(temp.getServant().getId());
				OrganNode n = organNodeService.load(servant.getDepartId());
				if(n==null||StringUtils.isBlank(n.getId())){
					throw new BusinessException("转任人员单位信息不能为空！");
				}
				temp.setSourceOrgan(n);//转任人员转出单位
				temp.setFormerUnitName(n.getName());//原单位名称
				if(servant.getNowPostCode()!=null){
					temp.setFormerUnitJobName(servant.getNowPostCode().getName());//原职务名称
				}
				temp.setAreaType(ZhuanRenKLBIntoMgr.AREA_THIS);//本区转任
				
				zhuanRenKLBIntoMgrService.saveFlow(temp,opinion,r);//提交流程
			} else {
				ZhuanRenKLBIntoMgr post = zhuanRenKLBIntoMgrService.get(temp.getId());
				temp.setStatus(post.getStatus());//设置一下，后面发送通知时在使用
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				zhuanRenKLBIntoMgrService.saveFlow(post,opinion,r);//提交流程
			}
			if(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){
				//发送通知
				String title = messageSource.getMessage("zhuanRenTitle", new Object[]{temp.getName()}, Locale.CHINESE);
				String content = messageSource.getMessage("zhuanRenContent", new Object[]{temp.getName()}, Locale.CHINESE);
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
	 * @Description: 审批外区转任信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlowOuter")
	public AjaxResult operationFlowOuter(ZhuanRenKLBIntoMgr temp, HttpServletRequest request) {
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
				temp.setStatus(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE);//流程状态，待提交
				temp.setTargetOrgan(x);//转入单位为当前单位
				temp.setAreaType(ZhuanRenKLBIntoMgr.AREA_OUTER);//外区转任
				
				zhuanRenKLBIntoMgrService.saveFlowOuter(temp,opinion,r);//提交流程
			} else {
				ZhuanRenKLBIntoMgr post = zhuanRenKLBIntoMgrService.get(temp.getId());
				temp.setStatus(post.getStatus());//设置一下，后面发送通知时在使用
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				zhuanRenKLBIntoMgrService.saveFlowOuter(post,opinion,r);//提交流程
			}
			if(ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_PRINT==temp.getStatus()&&FlowRecord.PASS.equals(r)){
				//发送通知
				String title = messageSource.getMessage("zhuanRenTitle", new Object[]{temp.getName()}, Locale.CHINESE);
				String content = messageSource.getMessage("zhuanRenContent", new Object[]{temp.getName()}, Locale.CHINESE);
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
				ZhuanRenKLBIntoMgr into = zhuanRenKLBIntoMgrService.get(id);
				params.put("sourceOrgan", into.getSourceOrgan()==null?"":into.getSourceOrgan().getName());//源单位名称
				params.put("targetOrgan", into.getTargetOrgan()==null?"":into.getTargetOrgan().getName());//目标单位名称
				if(ZhuanRenKLBIntoMgr.AREA_THIS.equals(into.getAreaType())){//本区
					params.put("name", into.getServant().getName());//转任人员名称
				}else{
					params.put("name", into.getName());//转任人员名称
				}
				if(into.getEnterTheUnitDate()!=null){
					params.put("date", sdf.format(into.getEnterTheUnitDate()));//转任时间
				}else{
					params.put("date", sdf.format(new Date()));//转任时间
				}
			}else{
				ZhuanRenKLBOutMgr out = zhuanRenKLBOutMgrService.get(id);
				params.put("sourceOrgan", out.getSourceOrgan()==null?"":out.getSourceOrgan().getName());//源单位名称
				params.put("targetOrgan", out.getGotoUnitName());//目标单位名称
				params.put("name", out.getServant().getName());//转任人员名称
				if(out.getOutDate()!=null){
					params.put("date", sdf.format(out.getOutDate()));//转任时间
				}else{
					params.put("date", sdf.format(new Date()));//转任时间
				}
			}
			params.put("now", sdf.format(new Date()));//打印介绍信时间
			WordUtils.exportMillCertificateWord(request, response, params, params.get("sourceOrgan")+"转任介绍信", "zrIntroduction.ftl");
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
