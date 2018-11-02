/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaorenOutController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 公务员调任-调出流程控制器
 * 创建人: tzy   
 * 创建时间: 2018年10月16日 下午3:53:56 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月16日 下午3:53:56 
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
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOutMgr;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutMgrService;
import com.wondersgroup.human.vo.ofcflow.DiaoRenOutMgrVO;

/** 
 * @ClassName: DiaorenOutController 
 * @Description: 公务员调任-调出流程控制器
 * @author: tzy
 * @date: 2018年10月16日 下午3:53:56
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/diaorenOut")
public class DiaorenOutController extends GenericController{
	@Autowired
	private DiaoRenOutMgrService diaoRenOutMgrService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private OrganNodeService organNodeService;
	
	/**
	 * 调出情况列表
	 */
	private final static String DIAOREN_OUT_LIST = "models/ofcflow/diaoren/outList";
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
	 * 检索人员是否存在页面
	 */
	private final static String CHECK_SERVANT = "models/ofcflow/diaoren/checkServantOut";
	
	/**
	 * @Title: recruitList 
	 * @Description: 调出情况列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String recruitList() {
		return DIAOREN_OUT_LIST;
	}
	/**
	 * @Title: edit 
	 * @Description: 编辑页面
	 * @param	id 调出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String id,String servantId,String areaType,Model model) {
		if(StringUtils.isNotBlank(id)){
			DiaoRenOutMgr z = diaoRenOutMgrService.get(id);
			model.addAttribute("d", z);
			model.addAttribute("s", z.getServant());
			areaType = z.getAreaType();
		}else{
			Servant s = servantService.load(servantId);
			model.addAttribute("s", s);
		}
		if(DiaoRenOutMgr.AREA_THIS.equals(areaType)){//本区
			return DIAOREN_OUT_EDIT_THIS;
		}else{
			return DIAOREN_OUT_EDIT_OUTER;
		}
	}
	/**
	 * @Title: toFlow 
	 * @Description: 流程页面
	 * @param	id 调出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toFlow")
	public String toFlow(String id,Model model) {
		DiaoRenOutMgr z = diaoRenOutMgrService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		model.addAttribute("isFlow", true);
		if(DiaoRenOutMgr.AREA_OUTER.equals(z.getAreaType())){
			return DIAOREN_OUT_FLOW_OUTER;
		}else{
			return DIAOREN_OUT_FLOW_THIS;
		}
	}
	/**
	 * @Title: toView 
	 * @Description: 查看页面
	 * @param	id 调出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toView")
	public String toView(String id,Model model) {
		DiaoRenOutMgr z = diaoRenOutMgrService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		if(DiaoRenOutMgr.AREA_OUTER.equals(z.getAreaType())){
			return DIAOREN_OUT_FLOW_OUTER;
		}else{
			return DIAOREN_OUT_FLOW_THIS;
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
		result.setMessage("系统中不存在该公务员，无法发起调任申请！");
		try {
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
				Servant servant = s.get(0);
				DetachedCriteria d = DetachedCriteria.forClass(DiaoRenOutMgr.class);
				d.add(Restrictions.eq("servant", servant));
				d.add(Restrictions.ne("status", DiaoRenOutMgr.STATUS_DIAOCHU_FINISH));
				d.add(Restrictions.eq("removed", false));
				List<DiaoRenOutMgr> z = diaoRenOutMgrService.findByCriteria(d);
				Map<String,String> map = new HashMap<>();
				if(z.size()>0){//跨类别调出表中检索，如果检索出数据，则是编辑操作
					if(z.get(0).getStatus()>DiaoRenOutMgr.STATUS_DIAOCHU_STATE||!SecurityUtils.getUserId().equals(z.get(0).getCreater())){
						map.put("code", "3");
						result.setData(map);
						result.setSuccess(true);
						result.setMessage("该人员正在调任流程中，无法再次发起调任申请！");
					}else{
						map.put("id", z.get(0).getId());
						map.put("code", "2");
						map.put("departname", servant.getDepartName());
						result.setData(map);
						result.setSuccess(true);
						result.setMessage("已录入此人员的调任信息，请点击提交后在表单中修改该人员详细信息！");
					}
				}else{
					map.put("departname", servant.getDepartName());
					map.put("id", servant.getId());
					map.put("code", "1");
					result.setData(map);
					result.setSuccess(true);
					result.setMessage("系统中存在该人员，请点击提交后录入信息发起调出流程！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Title: infoList 
	 * @Description: 调出列表
	 * @param model
	 * @param name
	 * @param cardNo
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<DiaoRenOutMgrVO>
	 */
	@ResponseBody
	@RequestMapping("/infoList")
	public Page<DiaoRenOutMgrVO> infoList(Model model,String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DiaoRenOutMgr where removed=:removed and (creater=:creater or (sourceOrgan=:sourceOrgan and status in('11','12'))) and status is not null");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
		listqueryparameter.add(new QueryParameter("sourceOrgan", OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId())));//只能操作自己的数据
		if(StringUtils.isNotBlank(name)){
			hql.append( " and servant.name like :name");
			queryParameteritem=new QueryParameter("name", "%"+name+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("name", name);
		}
		if(StringUtils.isNotBlank(cardNo)){
			hql.append( " and servant.cardNo like :cardNo");
			queryParameteritem=new QueryParameter("cardNo", "%"+cardNo+"%");
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("cardNo", cardNo);
		}
		hql.append( " order by createTime desc");
		
		Page<DiaoRenOutMgrVO> pageInfo = diaoRenOutMgrService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(DiaoRenOutMgr temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				DiaoRenOutMgr post = diaoRenOutMgrService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				if(DiaoRenOutMgr.AREA_THIS.equals(temp.getAreaType())){//本区
					if(temp.getTargetOrgan()!=null&&StringUtils.isNotBlank(temp.getTargetOrgan().getId())){
						OrganNode target = organNodeService.load(temp.getTargetOrgan().getId());
						temp.setGotoUnitName(target.getName());
					}
				}
				diaoRenOutMgrService.saveOrUpdate(post);//保存
			}else{//新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				if(DiaoRenOutMgr.AREA_OUTER.equals(temp.getAreaType())){//外区
					temp.setStatus(DiaoRenOutMgr.STATUS_DIAOCHU_STATE_OUTER);//流程状态，待提交
				}else{
					temp.setStatus(DiaoRenOutMgr.STATUS_DIAOCHU_STATE);//流程状态，待提交
					if(temp.getTargetOrgan()!=null&&StringUtils.isNotBlank(temp.getTargetOrgan().getId())){
						OrganNode target = organNodeService.load(temp.getTargetOrgan().getId());
						temp.setGotoUnitName(target.getName());
					}
				}
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("调出人员信息不能为空！");
				}
				Servant servant = servantService.load(temp.getServant().getId());
				OrganNode organ = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(organ);//调出单位
				diaoRenOutMgrService.saveOrUpdate(temp);
			}
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: operationFlow 
	 * @Description: 发起调出信息 本区
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(DiaoRenOutMgr temp, HttpServletRequest request) {
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
				temp.setStatus(DiaoRenOutMgr.STATUS_DIAOCHU_STATE);//流程状态，待提交
				temp.setSourceOrgan(x);//原单位为当前单位
				temp.setAreaType(DiaoRenOutMgr.AREA_THIS);//本区调任
				if(temp.getTargetOrgan()!=null&&StringUtils.isNotBlank(temp.getTargetOrgan().getId())){
					OrganNode target = organNodeService.load(temp.getTargetOrgan().getId());
					temp.setGotoUnitName(target.getName());
				}
				diaoRenOutMgrService.saveFlow(temp,opinion,r);//提交流程
			} else {
				DiaoRenOutMgr post = diaoRenOutMgrService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				if(temp.getTargetOrgan()!=null&&StringUtils.isNotBlank(temp.getTargetOrgan().getId())){
					OrganNode target = organNodeService.load(temp.getTargetOrgan().getId());
					temp.setGotoUnitName(target.getName());
				}
				diaoRenOutMgrService.saveFlow(post,opinion,r);//提交流程
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
	 * @Description: 发起调出信息 外区
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlowOuter")
	public AjaxResult operationFlowOuter(DiaoRenOutMgr temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("调出人员信息不能为空！");
				}
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				temp.setStatus(DiaoRenOutMgr.STATUS_DIAOCHU_CONFIRM_OUTER);//流程状态，待调出单位备案
				Servant servant = servantService.load(temp.getServant().getId());
				OrganNode organ = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(organ);//调出单位
				temp.setAreaType(DiaoRenOutMgr.AREA_OUTER);//外区调任
				diaoRenOutMgrService.saveOrUpdate(temp);
			} else {
				DiaoRenOutMgr out = diaoRenOutMgrService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, out);
				DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
				diaoRenOutMgrService.saveFlowOuter(out);
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
			DiaoRenOutMgr l = diaoRenOutMgrService.get(id);
			diaoRenOutMgrService.delete(l);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
