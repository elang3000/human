package com.wondersgroup.human.controller.pubinst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: OfcController
 * @Description: 信息维护控制器
 * @author: GP
 * @date: 2018年5月8日 下午3:29:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("/pubinst/org")
public class PtOrganController extends GenericController {
	
	@Autowired
	private OrganizationService organizationService;
	
	/**
	 * @Title: orgRelations
	 * @Description: 组织节点关系
	 * @return
	 * @return: AjaxResult
	 */
	@Log(title = "新增组织 节点", operatorType = OperatorType.MANAGE, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/orgRelations")
	public List<HashMap<String, Object>> orgRelations(String id) {
		
		List<HashMap<String, Object>> treeData = new ArrayList<HashMap<String, Object>>();
		if (StringUtils.isBlank(id)) {
			// 获取根节点对象
			List<OrganNode> nodes = organizationService.getOrganRootNodes();
			for (OrganNode organNode : nodes) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", organNode.getId());
				map.put("name", organNode.getName());
				map.put("allName", organNode.getAllName());
				map.put("parentId", null);
				treeData.add(map);
			}
		} else {
			// 获取节点对象
			List<OrganNode> nodes = organizationService.getOrganChildrenNodes(id);
			for (OrganNode organNode : nodes) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", organNode.getId());
				map.put("name", organNode.getName());
				map.put("allName", organNode.getAllName());
				treeData.add(map);
			}
		}
		return treeData;
	}
	
}
