/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ExerciseController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 挂职锻炼
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
import com.wondersgroup.human.bo.ofcflow.Exercise;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.ExerciseService;
import com.wondersgroup.human.vo.ofcflow.ExerciseVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: ExerciseController 
 * @Description: 挂职锻炼
 * @author: tzy
 * @date: 2018年9月20日 下午3:02:15
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/exercise")
public class ExerciseController extends GenericController{
	@Autowired
	private ExerciseService exerciseService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private OrganNodeService organNodeService;
	
	/**
	 * 挂职锻炼列表
	 */
	private final static String EXERCISE_LIST = "models/ofcflow/exercise/exerciseList";
	/**
	 * 挂职锻炼编辑页面
	 */
	private final static String EXERCISE_EDIT = "models/ofcflow/exercise/exerciseEdit";
	/**
	 * 挂职锻炼查看页面
	 */
	private final static String EXERCISE_VIEW = "models/ofcflow/exercise/exerciseView";
	
	/**
	 * @Title: index 
	 * @Description: 挂职锻炼列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return EXERCISE_LIST;
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
			Exercise z = exerciseService.get(id);
			model.addAttribute("d", z);
			model.addAttribute("s", z.getServant());
		}
		OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		if(organ!=null){
			model.addAttribute("createOrganNodeId", organ.getId());
		}else{
			model.addAttribute("createOrganNodeId", "");
		}
		return EXERCISE_EDIT;
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
		Exercise z = exerciseService.get(id);
		model.addAttribute("d", z);
		model.addAttribute("s", z.getServant());
		return EXERCISE_VIEW;
	}
	/**
	 * @Title: infoList 
	 * @Description: 挂职锻炼列表
	 * @param model
	 * @param name
	 * @param targetOrgan
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ExerciseVO>
	 */
	@Log(title = "查询挂职锻炼列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/infoList")
	public Page<ExerciseVO> infoList(String name,String targetOrgan,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from Exercise where removed=:removed and creater=:creater ");
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
		
		Page<ExerciseVO> pageInfo = exerciseService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 保存
	 * @param temp
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "编辑挂职锻炼信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(Exercise temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				Exercise post = exerciseService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				exerciseService.saveOrUpdate(post);//保存
			}else{//新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setStatus(Exercise.STATUS_STATE);//流程状态，待备案
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("挂职锻炼人员信息不能为空！");
				}
				Servant servant = servantService.load(temp.getServant().getId());
				OrganNode organ = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(organ);//挂职锻炼单位
				exerciseService.saveOrUpdate(temp);
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
	@Log(title = "备案挂职锻炼信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(Exercise temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		try {
			temp.setStatus(Exercise.STATUS_FINISH);//流程状态，已备案
			if (StringUtils.isBlank(temp.getId())) {
				if(temp.getServant()==null||StringUtils.isBlank(temp.getServant().getId())){
					throw new BusinessException("挂职锻炼人员信息不能为空！");
				}
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				Servant servant = servantService.load(temp.getServant().getId());
				OrganNode organ = organNodeService.load(servant.getDepartId());
				temp.setSourceOrgan(organ);//转出单位
				exerciseService.saveFlow(temp);
			} else {
				Exercise out = exerciseService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, out);
				DictUtils.operationCodeInfo(out);//将CodeInfo中id为空的属性 设置为null
				exerciseService.saveFlow(out);
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
	@Log(title = "删除挂职锻炼信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			Exercise l = exerciseService.get(id);
			exerciseService.delete(l);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
