/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 单位基础信息 控制器
 * 创建人: jiang
 * 创建时间: 2018年9月12日15:44:38
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月12日15:44:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.controller.organization;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.organization.OrgInfoVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: OrgInfoController
 * @Description: 单位基础信息 控制器
 * @author: jiang
 * @date: 2018年9月12日15:45:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/orgInfo")
@Controller
public class OrgInfoController extends GenericController {
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrgFormationService orgFormationService;
	
	@Autowired
	private InstitutionOrgFormationService institutionOrgFormationService;
	
	/**
	 * 返回视图路径
	 */
	private static final String ORG_INFO_VIEW_LIST = "models/organization/orgInfoViewList";
	
	private static final String ORG_SERVANT_VIEW_LIST = "models/organization/orgServantViewList";
	
	private static final String ORG_INFO_VIEW_PAGE = "models/organization/orgInfoViewPage";
	
	private static final String ORG_SERVANT_VIEW_PAGE = "models/organization/selectOrganServantIndex";
	
	private static final String ORG_SERVANTS_VIEW_PAGE = "models/organization/selectOrganServantsIndex";
	
	private static final String ORG_VIEW_PAGE = "models/organization/selectOrgansIndex";
	
	private static final String INST_ORG_INFO_VIEW_PAGE = "models/organization/instOrgInfoViewPage";
	
	/**
	 * @Title: orgInfolist
	 * @Description: 单位基础信息列表
	 * @return: String
	 */
	@RequestMapping("/viewList")
	public String orgInfoList() {
		
		return ORG_INFO_VIEW_LIST;
	}
	
	/**
	 * @Title: orgServantPage
	 * @Description: 单位人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantViewList")
	public String orgServantPage() {
		
		return ORG_SERVANT_VIEW_LIST;
	}
	
	@RequestMapping("/selectServant")
	public String selectOrganServant(String createOrganNodeId, Model model) {
		
		model.addAttribute("createOrganNodeId", createOrganNodeId);
		return ORG_SERVANT_VIEW_PAGE;
	}
	
	/**
	 * @Title: selectOrganServant1 
	 * @Description: 选择人员列表
	 * @param createOrganNodeId
	 * @param ids
	 * @param names
	 * @param model
	 * @param	busClass  排除业务数据实体类路径  示例：com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto
	 * @param	busCol  排除业务数据字段名	   示例：zhuanRenTLBIntoBatch.id
	 * @param	busId  排除业务数据ID值
 	 * @param	busParentClass  排除业务数据批次状态为-1（业务中止）的  示例：com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto类中批次变量zhuanRenTLBOutBatch
 	 * @param	type  传入1 时人社局可查看所有人员
 	 * @param	personType  人员类型，区分事业人员和公务员，不传值时默认公务员，1：公务员   2：事业人员
	 * @return
	 * @return: String
	 */
	@RequestMapping("/selectServants")
	public String selectOrganServant1(String createOrganNodeId,String ids,String names, Model model,String busClass,
			String busCol,String busId,String type,String busParentClass,String personType) {
		
		model.addAttribute("ids", ids);
		model.addAttribute("names", names);
		model.addAttribute("createOrganNodeId", createOrganNodeId);
		model.addAttribute("busClass", busClass);
		model.addAttribute("busCol", busCol);
		model.addAttribute("busId", busId);
		model.addAttribute("type", type);
		model.addAttribute("busParentClass", busParentClass);
		if(StringUtils.isBlank(personType)){
			model.addAttribute("personType", "1");
		}else{
			model.addAttribute("personType", personType);
		}
		return ORG_SERVANTS_VIEW_PAGE;
	}
	
	/**
	 * @Title: orgServantPage
	 * @Description: 单位列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/selectOrgans")
	public String selectOrgans(Model model,String id,String url) {
		model.addAttribute("id",id);
		model.addAttribute("url",url);
		return ORG_VIEW_PAGE;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 机构信息列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查询机构信息列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<OrgInfoVO> pageList(OrgInfo orgInfo, Integer limit, Integer page, String parentOrganId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrgInfo.class);
		if (StringUtils.isNotBlank(orgInfo.getUnitBasicName())) {
			detachedCriteria.add(Restrictions.like("unitBasicName", orgInfo.getUnitBasicName(), MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(parentOrganId)) {// organ下的单位
			// 查询本节点下所有单位
			List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(parentOrganId);
			List<String> parentOrganList = new ArrayList<String>();
			for (OrganNode org : orgNodeList) {
				parentOrganList.add(org.getId());
			}
			detachedCriteria.createAlias("organ", "org", JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in("org.id", parentOrganList));
			
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<OrgInfoVO> pageInfo = orgInfoService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: viewPage
	 * @Description: 查看页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@Log(title = "查询机构信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/viewPage")
	public String viewPage(Model model, String id) {
		
		OrgInfo orgInfo = orgInfoService.get(id);
		
		System.out.println(orgInfo.getOrgan().getOrganNodeType().getCode());
		if (CommonConst.ORGAN_TYPE_D_CLASS_CODE.equals(orgInfo.getOrgan().getOrganNodeType().getCode())) {
			OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", orgInfo.getId());
			
			model.addAttribute("orgInfo", orgInfo);
			model.addAttribute("orgFormation", orgFormation);
			return ORG_INFO_VIEW_PAGE;
		} else if (CommonConst.ORGAN_TYPE_UNIT_CODE.equals(orgInfo.getOrgan().getOrganNodeType().getCode())) {
			InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id", orgInfo.getId());
			
			model.addAttribute("orgInfo", orgInfo);
			model.addAttribute("orgFormation", orgFormation);
			return INST_ORG_INFO_VIEW_PAGE;
		}
		else{
			throw new BusinessException("单位类型异常！");
		}
	}
}
