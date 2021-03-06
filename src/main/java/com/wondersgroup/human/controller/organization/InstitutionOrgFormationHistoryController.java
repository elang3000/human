/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgFormationHistoryController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 事业单位编制调整历史 控制器
 * 创建人: jiang
 * 创建时间: 2018年12月10日10:39:21
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月10日10:39:23
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

import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormationHistory;
import com.wondersgroup.human.bo.organization.OrgFormationHistory;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationHistoryService;
import com.wondersgroup.human.vo.organization.InstitutionOrgFormationHistoryVO;
import com.wondersgroup.human.vo.organization.OrgFormationHistoryVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: InstitutionOrgFormationHistoryController
 * @Description: 事业单位单位编制调整历史 控制器
 * @author: jiang
 * @date: 2018年12月10日10:39:26
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/institutionOrgFormationHistory")
@Controller
public class InstitutionOrgFormationHistoryController extends GenericController {
	
	@Autowired
	private InstitutionOrgFormationHistoryService institutionOrgFormationHistoryService;
	
	@Autowired
	private OrganizationService organizationService;
	
	/**
	 * 返回视图路径
	 */
	private static final String ORG_INFO_HISTORY_LIST = "models/organization/instOrgFormationHistoryList";
	
	private static final String ORG_INFO_HISTORY_VIEW = "models/organization/instOrgFormationHistoryView";
	
	/**
	 * @Title: list
	 * @Description: 事业单位编制信息调整历史列表
	 * @return: String
	 */
	@RequestMapping("/list")
	public String List() {
		
		return ORG_INFO_HISTORY_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 单位编制调整历史列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查询事业单位机构编制历史信息列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<InstitutionOrgFormationHistoryVO> pageList(InstitutionOrgFormationHistory orgFormationHistory, Integer limit, Integer page, String organId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(InstitutionOrgFormationHistory.class);
		detachedCriteria.createAlias("orgInfo", "org", JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias("orgInfo.organ", "organ", JoinType.LEFT_OUTER_JOIN);
		
		if (orgFormationHistory.getOrgInfo() != null
				&& StringUtils.isNotBlank(orgFormationHistory.getOrgInfo().getUnitBasicName())) {
			detachedCriteria.add(Restrictions.like("org.unitBasicName", orgFormationHistory.getOrgInfo().getUnitBasicName(),
					MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(organId)) {// organ下的单位
			// 查询本节点下所有单位
			List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(organId);
			List<String> organList = new ArrayList<String>();
			for (OrganNode org : orgNodeList) {
				organList.add(org.getId());
			}
			
			detachedCriteria.add(Restrictions.in("organ.id", organList));
			
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<InstitutionOrgFormationHistoryVO> pageInfo = institutionOrgFormationHistoryService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: view
	 * @Description: 查看页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@Log(title = "查询事业单位机构编控历史信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/view")
	public String view(Model model, String id) {
		
		InstitutionOrgFormationHistory orgFormationHistory = institutionOrgFormationHistoryService.get(id);
		
		model.addAttribute("orgFormation", orgFormationHistory);
		model.addAttribute("orgFormationMgrFlow", orgFormationHistory.getInstOrgFormationMgrFlow());
		return ORG_INFO_HISTORY_VIEW;
	}
}
