/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: LoanToController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 借调
 * 创建人: tzy   
 * 创建时间: 2018年9月20日 下午3:02:15 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月20日 下午3:02:15 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.LoanTo;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.LoanToService;
import com.wondersgroup.human.vo.ofcflow.LoanToVO;

/** 
 * @ClassName: LoanToController 
 * @Description: 借调
 * @author: tzy
 * @date: 2018年9月20日 下午3:02:15
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/loanto")
public class LoanToController extends GenericController{
	@Autowired
	private LoanToService loanToService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private OrganNodeService organNodeService;
	
	/**
	 * 借调列表
	 */
	private final static String LOANTO_LIST = "models/ofcflow/loanTo/loanToList";
	/**
	 * 借调编辑页面
	 */
	private final static String LOANTO_EDIT = "models/ofcflow/loanTo/loanToEdit";
	/**
	 * 借调查看页面
	 */
	private final static String LOANTO_VIEW = "models/ofcflow/loanTo/loanToView";
	
	/**
	 * @Title: index 
	 * @Description: 借调列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return LOANTO_LIST;
	}
	/**
	 * @Title: edit 
	 * @Description: 编辑页面
	 * @param	id 
	 * @return
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String id,Model model) {
		if(StringUtils.isNotBlank(id)){
			LoanTo z = loanToService.get(id);
			model.addAttribute("d", z);
			model.addAttribute("s", z.getServant());
		}
		OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		if(organ!=null){
			model.addAttribute("createOrganNodeId", organ.getId());
		}else{
			model.addAttribute("createOrganNodeId", "");
		}
		return LOANTO_EDIT;
	}
	/**
	 * @Title: toView 
	 * @Description: 查看页面
	 * @param	id 
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toView")
	public String toView(String id,Model model) {
		LoanTo z = loanToService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		return LOANTO_VIEW;
	}
	/**
	 * @Title: infoList 
	 * @Description: 借调列表
	 * @param model
	 * @param name
	 * @param targetOrgan
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<LoanToVO>
	 */
	@ResponseBody
	@RequestMapping("/infoList")
	public Page<LoanToVO> infoList(String name,String targetOrgan,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from LoanTo where removed=:removed and creater=:creater ");
		listqueryparameter.add(new QueryParameter("removed", false));
		listqueryparameter.add(new QueryParameter("creater", SecurityUtils.getUserId()));//只能操作自己的数据
		if(StringUtils.isNotBlank(name)){
			hql.append( " and servant.name like :name");
			listqueryparameter.add(new QueryParameter("name", "%"+name+"%"));
		}
		if(StringUtils.isNotBlank(targetOrgan)){
			hql.append( " and targetOrgan like :targetOrgan");
			listqueryparameter.add(new QueryParameter("targetOrgan", "%"+targetOrgan+"%"));
		}
		hql.append( " order by createTime desc");
		
		Page<LoanToVO> pageInfo = loanToService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

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
	public AjaxResult save(LoanTo temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				LoanTo post = loanToService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				loanToService.saveOrUpdate(post);//保存
			}else{//新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setStatus(LoanTo.STATUS_STATE);//流程状态，待备案
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("借调人员信息不能为空！");
				}
				Servant servant = servantService.load(temp.getServant().getId());
				OrganNode organ = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(organ);//借调单位
				loanToService.saveOrUpdate(temp);
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
	 * @Description: 备案
	 * @param temp	 
	 * @param request
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(LoanTo temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		try {
			temp.setStatus(LoanTo.STATUS_FINISH);//流程状态，已备案
			if (StringUtils.isBlank(temp.getId())) {
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("借调人员信息不能为空！");
				}
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				Servant servant = servantService.load(temp.getServant().getId());
				OrganNode organ = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(organ);//转出单位
				loanToService.saveFlow(temp);
			} else {
				LoanTo out = loanToService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, out);
				DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
				loanToService.saveFlow(out);
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
			LoanTo l = loanToService.get(id);
			loanToService.delete(l);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
