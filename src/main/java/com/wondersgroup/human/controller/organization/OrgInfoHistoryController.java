/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoHistoryController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 单位基础信息调整历史 控制器
 * 创建人: jiang
 * 创建时间: 2018年9月21日14:12:01
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月21日14:12:03
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
import com.wondersgroup.human.bo.organization.OrgInfoHistory;
import com.wondersgroup.human.service.organization.OrgInfoHistoryService;
import com.wondersgroup.human.vo.organization.OrgInfoHistoryVO;

/**
 * @ClassName: OrgInfoHistoryController
 * @Description: 单位基础信息调整历史 控制器
 * @author: jiang
 * @date: 2018年9月21日14:12:06
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/orgInfoHistory")
@Controller
public class OrgInfoHistoryController extends GenericController {
	
	@Autowired
	private OrgInfoHistoryService orgInfoHistoryService;
	
	@Autowired
	private OrganizationService organizationService;
	
	/**
	 * 返回视图路径
	 */
	private static final String ORG_INFO_HISTORY_LIST = "models/organization/orgInfoHistoryList";
	
	private static final String ORG_INFO_HISTORY_VIEW = "models/organization/orgInfoHistoryView";
	
	/**
	 * @Title: list
	 * @Description: 单位基础信息调整历史列表
	 * @return: String
	 */
	@RequestMapping("/list")
	public String List() {
		
		return ORG_INFO_HISTORY_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 机构调整历史列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<OrgInfoHistoryVO> pageList(OrgInfoHistory orgInfoHistory, Integer limit, Integer page, String organId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrgInfoHistory.class);
		detachedCriteria.createAlias("orgInfo", "org", JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias("orgInfo.organ", "organ", JoinType.LEFT_OUTER_JOIN);
		
		if (orgInfoHistory.getOrgInfo() != null
				&& StringUtils.isNotBlank(orgInfoHistory.getOrgInfo().getUnitBasicName())) {
			detachedCriteria.add(Restrictions.like("org.unitBasicName", orgInfoHistory.getOrgInfo().getUnitBasicName(),
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
		Page<OrgInfoHistoryVO> pageInfo = orgInfoHistoryService.getPage(detachedCriteria, page, limit);
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
	@RequestMapping("/view")
	public String view(Model model, String id) {
		
		OrgInfoHistory orgInfoHistory = orgInfoHistoryService.get(id);
		
		model.addAttribute("orgInfo", orgInfoHistory);
		model.addAttribute("orgInfoMgrFlow", orgInfoHistory.getOrgInfoMgrFlow());
		return ORG_INFO_HISTORY_VIEW;
	}
}
