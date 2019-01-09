/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeOutController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 参公交流调出
 * 创建人: tzy   
 * 创建时间: 2018年11月8日 下午6:50:28 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年11月8日 下午6:50:28 
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
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOut;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutService;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeOutVO;

/** 
 * @ClassName: ReferenceExchangeOutController 
 * @Description: 参公交流调出
 * @author: tzy
 * @date: 2018年11月8日 下午6:50:28
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/exchangeOut")
public class ReferenceExchangeOutController extends GenericController{

	@Autowired
	private ReferenceExchangeOutService referenceExchangeOutService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private OrganNodeService organNodeService;
	
	/**
	 * 调出情况列表
	 */
	private final static String ZHUANREN_INTO_LIST = "models/ofcflow/exchange/outList";
	/**
	 * 调出情况编辑页面
	 */
	private final static String ZHUANREN_OUT_EDIT = "models/ofcflow/exchange/outEdit";
	/**
	 * 调出情况流程页面
	 */
	private final static String ZHUANREN_OUT_FLOW = "models/ofcflow/exchange/outFlow";
	/**
	 * 检索人员是否存在页面
	 */
	private final static String CHECK_SERVANT = "models/ofcflow/exchange/checkServantOut";
	
	/**
	 * @Title: recruitList 
	 * @Description: 调出情况列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String recruitList() {
		return ZHUANREN_INTO_LIST;
	}
	/**
	 * @Title: edit 
	 * @Description: 编辑页面
	 * @param	id 调出数据id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String id,String servantId,Model model) {
		if(StringUtils.isNotBlank(id)){
			ReferenceExchangeOut z = referenceExchangeOutService.get(id);
			model.addAttribute("d", z);
			model.addAttribute("s", z.getServant());
		}else{
			Servant s = servantService.load(servantId); 
			model.addAttribute("s", s);
		}
		return ZHUANREN_OUT_EDIT;
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
		ReferenceExchangeOut z = referenceExchangeOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		model.addAttribute("isFlow", true);
		return ZHUANREN_OUT_FLOW;
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
		ReferenceExchangeOut z = referenceExchangeOutService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		return ZHUANREN_OUT_FLOW;
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
		result.setMessage("系统中不存在该参公人员，无法发起调任申请！");
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
			detachedCriteria.add(Restrictions.eq("name", name));
			detachedCriteria.add(Restrictions.eq("cardNo", cardNo));
			CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
			detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
			detachedCriteria.add(Restrictions.eq("removed", false));
//			OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
//			detachedCriteria.add(Restrictions.eq("departId", organ.getId()));//只查询自己单位的人员
			List<CodeInfo> typeList = DictCacheProvider.getCodeInfoByCodeTypeAndParentCode(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, "2");//人员类别为参照管理人员的CODE
			List<String> personType = new ArrayList<>();
			for(CodeInfo t:typeList){
				personType.add(t.getId());
			}
			detachedCriteria.add(Restrictions.in("personType.id", personType));//只查询参公人员
			List<Servant> s = servantService.findByCriteria(detachedCriteria);
			if(s.size()>0){
				Servant servant = s.get(0);
				DetachedCriteria d = DetachedCriteria.forClass(ReferenceExchangeOut.class);
				d.add(Restrictions.eq("servant", servant));
				d.add(Restrictions.ne("status", ReferenceExchangeOut.STATUS_DIAOCHU_FINISH));
				d.add(Restrictions.eq("removed", false));
				List<ReferenceExchangeOut> z = referenceExchangeOutService.findByCriteria(d);
				Map<String,String> map = new HashMap<>();
				if(z.size()>0){//同类别调出表中检索，如果检索出数据，则是编辑操作
					if(z.get(0).getStatus()>ReferenceExchangeOut.STATUS_DIAOCHU_STATE||!SecurityUtils.getUserId().equals(z.get(0).getCreater())){
						map.put("code", "3");
						result.setData(map);
						result.setSuccess(true);
						result.setMessage("该人员正在参公交流调出流程中，无法再次发起参公交流调出申请！");
					}else{
						map.put("id", z.get(0).getId());
						map.put("code", "2");
						map.put("departname", servant.getDepartName());
						result.setData(map);
						result.setSuccess(true);
						result.setMessage("已录入此人员的参公交流信息，请点击提交后在表单中修改该人员详细信息！");
					}
				}else{
					map.put("departname", servant.getDepartName());
					map.put("id", servant.getId());
					map.put("code", "1");
					result.setData(map);
					result.setSuccess(true);
					result.setMessage("系统中存在该人员，请点击提交后录入信息发起参公交流调出流程！");
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
	 * @return: Page<ReferenceExchangeOutVO>
	 */
	@ResponseBody
	@RequestMapping("/infoList")
	public Page<ReferenceExchangeOutVO> infoList(Model model,String name,String cardNo,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ReferenceExchangeOut where removed=:removed and (creater=:creater or (sourceOrgan=:sourceOrgan and status in('1','2'))) and status is not null");
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
			hql.append( " and servant.cardNo = :cardNo");
			queryParameteritem=new QueryParameter("cardNo", cardNo);
			listqueryparameter.add(queryParameteritem);
			model.addAttribute("cardNo", cardNo);
		}
		hql.append( " order by createTime desc");
		
		Page<ReferenceExchangeOutVO> pageInfo = referenceExchangeOutService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

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
	public AjaxResult save(ReferenceExchangeOut temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ReferenceExchangeOut post = referenceExchangeOutService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				referenceExchangeOutService.saveOrUpdate(post);//保存
			}else{//新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setStatus(ReferenceExchangeOut.STATUS_DIAOCHU_STATE);//流程状态，待提交
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("调出人员信息不能为空！");
				}
				Servant servant = servantService.load(temp.getServant().getId());
				OrganNode organ = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(organ);//调出单位
				referenceExchangeOutService.saveOrUpdate(temp);
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
	 * @Description: 发起调出信息
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(ReferenceExchangeOut temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isBlank(temp.getId())) {
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("调出人员信息不能为空！");
				}
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				temp.setStatus(ReferenceExchangeOut.STATUS_DIAOCHU_STATE);//流程状态
				Servant servant = servantService.load(temp.getServant().getId());
				OrganNode organ = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(organ);//调出单位
				referenceExchangeOutService.saveFlow(temp);
			} else {
				ReferenceExchangeOut out = referenceExchangeOutService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, out);
				DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
				referenceExchangeOutService.saveFlow(out);
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
			ReferenceExchangeOut l = referenceExchangeOutService.get(id);
			referenceExchangeOutService.delete(l);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}

}
