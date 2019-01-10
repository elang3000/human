/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrganController.java
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.organ
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年6月7日 上午10:56:36
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年6月7日 上午10:56:36
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.system.controller.organ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganNodeType;
import com.wondersgroup.framework.organization.bo.OrganRelation;
import com.wondersgroup.framework.organization.bo.OrganRelationType;
import com.wondersgroup.framework.organization.bo.OrganRule;
import com.wondersgroup.framework.organization.bo.OrganTree;
import com.wondersgroup.framework.organization.bo.OrganTreeType;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganNodeTypeService;
import com.wondersgroup.framework.organization.service.OrganRelationService;
import com.wondersgroup.framework.organization.service.OrganRelationTypeService;
import com.wondersgroup.framework.organization.service.OrganRuleService;
import com.wondersgroup.framework.organization.service.OrganTreeService;
import com.wondersgroup.framework.organization.service.OrganTreeTypeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.organization.vo.BaseOrganVO;
import com.wondersgroup.framework.organization.vo.OrganNodeVO;
import com.wondersgroup.framework.organization.vo.OrganRelationVO;
import com.wondersgroup.framework.organization.vo.OrganRuleVO;
import com.wondersgroup.framework.organization.vo.OrganTreeVO;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.vo.UserVO;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: OrganController
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年6月7日 上午10:56:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller("systemOrganController")
@RequestMapping("system/organ/")
public class OrganController extends GenericController {
	
	private final static String ORGAN_TREE_INDEX = "organ/queryOrganTreeIndex",
	        ORGAN_TREE_CREATE = "organ/createOrganTreeIndex", ORGAN_TREE_EDIT = "organ/editOrganTreeIndex",
	        ORGAN_NODE_TYPE_INDEX = "organ/queryOrganNodeTypeIndex",
	        ORGAN_NODE_TYPE_CREATE = "organ/createOrganNodeTypeIndex",
	        ORGAN_NODE_TYPE_EDIT = "organ/editOrganNodeTypeIndex",
	        ORGAN_RELATION_TYPE_INDEX = "organ/queryOrganRelationTypeIndex",
	        ORGAN_RELATION_TYPE_CREATE = "organ/createOrganRelationTypeIndex",
	        ORGAN_RELATION_TYPE_EDIT = "organ/editOrganRelationTypeIndex",
	        ORGAN_TREE_TYPE_INDEX = "organ/queryOrganTreeTypeIndex",
	        ORGAN_TREE_TYPE_CREATE = "organ/createOrganTreeTypeIndex",
	        ORGAN_TREE_TYPE_EDIT = "organ/editOrganTreeTypeIndex", ORGAN_RULE_INDEX = "organ/queryOrganRuleIndex",
	        ORGAN_RULE_CREATE = "organ/createOrganRuleIndex", ORGAN_RULE_EDIT = "organ/editOrganRuleIndex",
	        ORGAN_NODE_INDEX = "organ/queryOrganNodeIndex", ORGAN_NODE_DETAIL_INDEX = "organ/editOrganNodeDetailIndex",
	        ORGAN_NODE_USER_INDEX = "organ/moveOrganUserIndex",
	        ORGAN_RELATION_CREATE_INDEX = "organ/createOrganRelationIndex",
	        ORGAN_RELATION_EDIT_INDEX = "organ/editOrganRelationIndex",
	        ORGAN_NODE_CREATE_INDEX = "organ/createOrganNodeIndex";
	
	@Autowired
	OrganTreeService organTreeService;
	
	@Autowired
	OrganTreeTypeService organTreeTypeService;
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@Autowired
	OrganNodeTypeService organNodeTypeService;
	
	@Autowired
	OrganRelationTypeService organRelationTypeService;
	
	@Autowired
	OrganRuleService organRuleService;
	
	@Autowired
	OrganRelationService organRelationService;
	
	@RequestMapping("tree/type/query")
	@ResponseBody
	public List<BaseOrganVO> queryAvailabledOrganTreeType() {
		
		List<OrganTreeType> types = organTreeTypeService.getAllAvailabledOrganTreeType();
		List<BaseOrganVO> vos = new ArrayList<BaseOrganVO>();
		for (OrganTreeType type : types) {
			BaseOrganVO vo = new BaseOrganVO();
			vo.setId(type.getId());
			vo.setCode(type.getCode());
			vo.setName(type.getName());
			vos.add(vo);
		}
		return vos;
	}
	
	@RequestMapping("tree/query")
	@ResponseBody
	public List<OrganTreeVO> queryAvailabledOrganTree() {
		
		List<OrganTree> trees = organTreeService.queryAvailabledOrganTree();
		List<OrganTreeVO> result = new ArrayList<OrganTreeVO>();
		for (OrganTree tree : trees) {
			OrganTreeVO vo = new OrganTreeVO();
			vo.setId(tree.getId());
			vo.setCode(tree.getCode());
			vo.setName(tree.getName());
			vo.setDescription(tree.getDescription());
			result.add(vo);
		}
		return result;
	}
	
	@RequestMapping("tree/query/page")
	@ResponseBody
	public Page<OrganTreeVO> queryAvailabledOrganTreePage(String code, String name, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		if (StringUtils.isNotBlank(code)) {
			filter.add(new Predicate("code", Operator.LIKE, code, ""));
		}
		if (StringUtils.isNotBlank(name)) {
			filter.add(new Predicate("name", Operator.LIKE, name, ""));
		}
		
		Page<OrganTree> trees = organTreeService.queryAvailabledOrganTreePage(filter, null, page, limit);
		List<OrganTreeVO> result = new ArrayList<OrganTreeVO>();
		for (OrganTree tree : trees.getResult()) {
			OrganTreeVO vo = new OrganTreeVO();
			vo.setId(tree.getId());
			vo.setCode(tree.getCode());
			vo.setName(tree.getName());
			vo.setDescription(tree.getDescription());
			if (tree.getRootNode() != null) {
				vo.setRootNodeId(tree.getRootNode().getId());
				vo.setRootNodeName(tree.getRootNode().getName());
			}
			if (tree.getTreeType() != null) {
				vo.setTreeTypeId(tree.getTreeType().getId());
				vo.setTreeTypeName(tree.getTreeType().getName());
			}
			result.add(vo);
		}
		return new Page<OrganTreeVO>(trees.getStart(), trees.getCurrentPageSize(), trees.getTotalSize(),
		        trees.getPageSize(), result);
	}
	
	@RequestMapping("tree/index")
	public String queryOrganTreeIndex() {
		
		return ORGAN_TREE_INDEX;
	}
	
	@RequestMapping("tree/create")
	public String createOrganTreeIndex() {
		
		return ORGAN_TREE_CREATE;
	}
	
	@RequestMapping("tree/create/save")
	@ResponseBody
	public AjaxResult createOrganTree(OrganTree tree, String organTreeTypeId) {
		
		try {
			OrganTreeType treeType = organTreeTypeService.get(organTreeTypeId);
			tree.setCreater(SecurityUtils.getUserId());
			tree.setCreateTime(Calendar.getInstance().getTime());
			tree.setTreeType(treeType);
			organTreeService.save(tree);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("创建组织树", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/edit")
	public String editOrganTreeIndex(String id, Model model) {
		
		OrganTree tree = organTreeService.load(id);
		model.addAttribute("tree", tree);
		return ORGAN_TREE_EDIT;
	}
	
	@RequestMapping("tree/edit/save")
	@ResponseBody
	public AjaxResult editOrganTree(OrganTree tree, String organTreeTypeId) {
		
		try {
			OrganTree organTree = organTreeService.get(tree.getId());
			OrganTreeType treeType = organTreeTypeService.get(organTreeTypeId);
			organTree.setCode(tree.getCode());
			organTree.setName(tree.getName());
			organTree.setDescription(tree.getDescription());
			organTree.setTreeType(treeType);
			organTree.setLastOperateTime(Calendar.getInstance().getTime());
			organTree.setLastOperator(SecurityUtils.getUserId());
			organTreeService.update(organTree);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护组织树", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/remove/save")
	@ResponseBody
	public AjaxResult removeOrganTree(String id) {
		
		try {
			OrganTree organTree = organTreeService.get(id);
			organTree.setRemoved(true);
			organTree.setLastOperateTime(Calendar.getInstance().getTime());
			organTree.setLastOperator(SecurityUtils.getUserId());
			organTreeService.update(organTree);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除组织树", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/node/maintain")
	public String maintainOrganNode(String id, Model model) {
		
		model.addAttribute("organTreeId", id);
		return ORGAN_NODE_INDEX;
	}
	
	@RequestMapping("tree/node/create")
	public String createOrganNode(String organNodeId, String organTreeId, Model model) {
		
		if (StringUtils.isNotBlank(organNodeId)) {
			model.addAttribute("parentId", organNodeId);
			OrganNode parentOrganNode = organNodeService.get(organNodeId);
			OrganNodeVO organNodeVO = new OrganNodeVO();
			organNodeVO.setId(parentOrganNode.getId());
			organNodeVO.setCode(parentOrganNode.getCode());
			organNodeVO.setName(parentOrganNode.getName());
			organNodeVO.setAllName(parentOrganNode.getAllName());
			model.addAttribute("parentOrganNode", organNodeVO);
		} else {
			model.addAttribute("parentId", "");
		}
		model.addAttribute("organTreeId", organTreeId);
		return ORGAN_NODE_CREATE_INDEX;
	}
	
	@RequestMapping("tree/node/create/save")
	@ResponseBody
	public AjaxResult saveCreateOrganNode(OrganNode organNode, String parentOrganNodeId, String organTreeId,
	        String organNodeTypeId, String adminUserId) {
		
		try {
			OrganTree organTree = organTreeService.get(organTreeId);
			OrganNodeType organNodeType = organNodeTypeService.get(organNodeTypeId);
			organNode.setOrganNodeType(organNodeType);
			if (StringUtils.isNotBlank(adminUserId)) {
				SecurityUser securityUser = getUserService().get(adminUserId);
				organNode.setAdminUser(securityUser);
			}
			organNode.setCreater(SecurityUtils.getUserId());
			if (StringUtils.isNotBlank(parentOrganNodeId)) {
				OrganNode selectedNode = organNodeService.get(parentOrganNodeId);
				organNodeService.addOrganNodeToTree(organNode, selectedNode, organTree);
			} else {
				organNodeService.addOrganNodeToTree(organNode, null, organTree);
			}
			
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("新建组织节点", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@ResponseBody
	@RequestMapping("tree/node/query")
	public List<HashMap<String, Object>> queryOrganTreeRelations(String organTreeId, String id) {
		
		OrganTree organTree;
		if (StringUtils.isNotBlank(organTreeId)) {
			organTree = organTreeService.get(organTreeId);
		} else {
			organTree = organizationService.getDefaultOrganTree();
		}
		List<HashMap<String, Object>> treeData = new ArrayList<HashMap<String, Object>>();
		if (StringUtils.isBlank(id)) {
			// 获取根节点对象
			List<OrganNode> nodes = organizationService.getOrganRootNodes(organTree);
			for (OrganNode organNode : nodes) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", organNode.getId());
				map.put("name", organNode.getName());
				map.put("allName", organNode.getAllName());
				map.put("parentId", null);
				map.put("organTreeId", organTree.getId());
				treeData.add(map);
			}
		} else {
			// 获取节点对象
			List<OrganNode> nodes = organizationService.getOrganChildrenNodes(organTree, id);
			for (OrganNode organNode : nodes) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", organNode.getId());
				map.put("name", organNode.getName());
				map.put("allName", organNode.getAllName());
				map.put("organTreeId", organTree.getId());
				treeData.add(map);
			}
		}
		return treeData;
	}
	
	@RequestMapping("tree/node/detail")
	public String queryOrganTreeNodeDetail(String id, String organTreeId, Model model) {
		
		OrganTree organTree = organTreeService.get(organTreeId);
		OrganNode organNode = null;
		if (StringUtils.isNotBlank(id)) {
			organNode = organNodeService.get(id);
		} else {
			organNode = organTree.getRootNode();
		}
		OrganNodeVO organNodeVO = new OrganNodeVO();
		organNodeVO.setId(organNode.getId());
		organNodeVO.setCode(organNode.getCode());
		organNodeVO.setName(organNode.getName());
		organNodeVO.setAllName(organNode.getAllName());
		organNodeVO.setDescription(organNode.getDescription());
		organNodeVO.setDeptAddress(organNode.getDeptAddress());
		organNodeVO.setPrincipal(organNode.getPrincipal());
		organNodeVO.setPrincipalPhone(organNode.getPrincipalPhone());
		organNodeVO.setFax(organNode.getFax());
		organNodeVO.setBizType(organNode.getBizType());
		OrganNodeType organNodeType = organNode.getOrganNodeType();
		organNodeVO.setOrganNodeTypeId(organNodeType.getId());
		organNodeVO.setOrganNodeTypeName(organNodeType.getName());
		organNodeVO.setTop(organNodeType.getTop());
		organNodeVO.setDown(organNodeType.getDown());
		organNodeVO.setPeople(organNodeType.getPeople());
		organNodeVO.setRelation(organNodeType.getRelation());
		SecurityUser adminUser = organNode.getAdminUser();
		if (adminUser != null) {
			organNodeVO.setAdminUserId(adminUser.getId());
		}
		OrganNode parentOrganNode = organNodeService.getParentNode(organNode, organTree);
		if (parentOrganNode != null) {
			organNodeVO.setParentId(parentOrganNode.getId());
		}
		organNodeVO.setTreeTypeId(organTree.getTreeType().getId());
		Long orders = organNodeService.getOrdersByOrganTree(organNode, organTree);
		organNodeVO.setOrders(orders);
		model.addAttribute("organNode", organNodeVO);
		model.addAttribute("organTreeId", organTreeId);
		return ORGAN_NODE_DETAIL_INDEX;
	}
	
	@RequestMapping("tree/node/edit/save")
	@ResponseBody
	public AjaxResult saveEditOrganNode(OrganNodeVO organNode) {
		
		try {
			OrganTree organTree = organTreeService.get(organNode.getTreeTypeId());
			OrganNode targetOrganNode = organNodeService.get(organNode.getId());
			
			targetOrganNode.setCode(organNode.getCode());
			targetOrganNode.setName(organNode.getName());
			targetOrganNode.setAllName(organNode.getAllName());
			targetOrganNode.setDeptAddress(organNode.getDeptAddress());
			targetOrganNode.setFax(organNode.getFax());
			targetOrganNode.setPrincipal(organNode.getPrincipal());
			targetOrganNode.setPrincipalPhone(organNode.getPrincipalPhone());
			targetOrganNode.setDescription(organNode.getDescription());
			targetOrganNode.setBizType(organNode.getBizType());
			if (StringUtils.isNotBlank(organNode.getOrganNodeTypeId())) {
				OrganNodeType organNodeType = organNodeTypeService.get(organNode.getOrganNodeTypeId());
				targetOrganNode.setOrganNodeType(organNodeType);
			}
			if (StringUtils.isNotBlank(organNode.getAdminUserId())) {
				SecurityUser securityUser = getUserService().get(organNode.getAdminUserId());
				targetOrganNode.setAdminUser(securityUser);
			}
			organNodeService.updateOrganNode(targetOrganNode);
			Map<String, Object> ordersMap = new HashMap<String, Object>();
			ordersMap.put(organNode.getId(), organNode.getOrders());
			organNodeService.updateOrdersForOrganNode(organTree, ordersMap);
			;
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("创建组织节点更新", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/node/remove/save")
	@ResponseBody
	public AjaxResult removeOrganNode(OrganNodeVO organNode) {
		
		try {
			OrganTree organTree = organTreeService.get(organNode.getTreeTypeId());
			OrganNode targetOrganNode = organNodeService.get(organNode.getId());
			organNodeService.removeOrganNodeFromTree(targetOrganNode, organTree, true);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除组织节点", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("node/query")
	@ResponseBody
	public List<OrganNodeVO> queryAvailabledOrganNode(String organTreeId, String organNodeName,String organNodeType) {
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		return organizationService.queryAvailabledDepartmentOrganNode(organTreeId, organNodeName, organNode,organNodeType);
	}
	/**
	 * @Title: queryAllAvailabledOrganNode 
	 * @Description: 根据组织树treeid加载所有单位
	 * @param organTreeId
	 * @param organNodeName
	 * @return
	 * @return: List<OrganNodeVO>
	 */
	@RequestMapping("node/queryAll")
	@ResponseBody
	public List<OrganNodeVO> queryAllAvailabledOrganNode(String organTreeId, String organNodeName,String organNodeType) {
		return organizationService.queryAllAvailabledOrganNode(organTreeId, organNodeName,organNodeType);
	}
	
	@RequestMapping("node/user/query/{securityUserId}")
	@ResponseBody
	public List<OrganNodeVO> queryAvailabledUser(@PathVariable("securityUserId") String securityUserId,
	        String organTreeId) {
		
		return organizationService.getOrgaNodeByUserIdAndTreeId(securityUserId, organTreeId);
	}
	
	@RequestMapping("node/user/move")
	public String moveOrganUser(String organTreeId, String organNodeId, String userId, Model model) {
		
		OrganNode organNode = organNodeService.get(organNodeId);
		OrganNodeVO organNodeVO = new OrganNodeVO();
		organNodeVO.setId(organNode.getId());
		organNodeVO.setCode(organNode.getCode());
		organNodeVO.setName(organNode.getName());
		organNodeVO.setAllName(organNode.getAllName());
		
		model.addAttribute("organNode", organNodeVO);
		model.addAttribute("userId", userId);
		model.addAttribute("organTreeId", organTreeId);
		return ORGAN_NODE_USER_INDEX;
	}
	
	@RequestMapping("node/user/move/save")
	@ResponseBody
	public AjaxResult saveCreateOrganUser(String organNodeId, String targetOrangNodeId, String userId) {
		
		try {
			
			if (StringUtils.isBlank(
			        targetOrangNodeId)) { return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, "请选择移动目标组织。"); }
			OrganNode organNode = organNodeService.get(organNodeId);
			SecurityUser securityUser = getUserService().get(userId);
			organNodeService.removeUserFromOrganNode(securityUser, organNode);
			organNode = organNodeService.get(targetOrangNodeId);
			organNodeService.addUserToOrganNode(securityUser, organNode);
			
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("创建组织节点类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("node/type/index")
	public String queryOrganNodeTypeIndex() {
		
		return ORGAN_NODE_TYPE_INDEX;
	}
	
	@RequestMapping("node/type/query/accredit")
	@ResponseBody
	public List<OrganNodeType> queryOrganNodeType(String organTreeId, String organNodeId) {
		
		if (StringUtils.isNotBlank(organNodeId)) {
			OrganTree organTree = organTreeService.loadWithLazy(organTreeId, "treeType");
			OrganNode organNode = organNodeService.loadWithLazy(organNodeId, "organNodeType");
			OrganNodeType[] types = organRuleService.getChildNodeTypesWithTreeAndParentNode(organTree, organNode);
			return Arrays.asList(types);
		} else {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrganNodeType.class);
			detachedCriteria.add(Restrictions.eq("removed", false));
			return organNodeTypeService.findByCriteria(detachedCriteria);
		}
	}
	
	@RequestMapping("node/type/query")
	@ResponseBody
	public List<OrganNodeType> queryOrganNodeType() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrganNodeType.class);
		detachedCriteria.add(Restrictions.eq("removed", false));
		return organNodeTypeService.findByCriteria(detachedCriteria);
	}
	
	@RequestMapping("node/type/query/page")
	@ResponseBody
	public Page<OrganNodeType> queryOrganNodeTypePage(String code, String name, Integer limit, Integer page) {
		//logger.debug("执行时间：" + Calendar.getInstance().getTimeInMillis());
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrganNodeType.class);
		if (StringUtils.isNotBlank(code)) {
			detachedCriteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<OrganNodeType> result = organNodeTypeService.findByCriteria(detachedCriteria, page, limit);
		//logger.debug("执行时间：" + Calendar.getInstance().getTimeInMillis());
		return result;
	}
	
	@RequestMapping("node/type/create")
	public String createOrganNodeTypeIndex() {
		
		return ORGAN_NODE_TYPE_CREATE;
	}
	
	@RequestMapping("node/type/create/save")
	@ResponseBody
	public AjaxResult saveCreateOrganNodeType(OrganNodeType type) {
		
		try {
			type.setId(null);
			type.setCreater(SecurityUtils.getUserId());
			type.setCreateTime(Calendar.getInstance().getTime());
			organNodeTypeService.save(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.node.type.create.success"));
		} catch (Exception ex) {
			logger.error("创建组织节点类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("node/type/edit")
	public String editOrganNodeTypeIndex(String id, Model model) {
		
		OrganNodeType type = organNodeTypeService.get(id);
		model.addAttribute("type", type);
		return ORGAN_NODE_TYPE_EDIT;
	}
	
	@RequestMapping("node/type/edit/save")
	@ResponseBody
	public AjaxResult saveEditOrganNodeType(OrganNodeType type) {
		
		try {
			type.setLastOperator(SecurityUtils.getUserId());
			type.setLastOperateTime(Calendar.getInstance().getTime());
			organNodeTypeService.update(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.node.type.edit.success"));
		} catch (Exception ex) {
			logger.error("维护组织节点类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("node/type/remove/save")
	@ResponseBody
	public AjaxResult removeEditOrganNodeType(String id) {
		
		try {
			OrganNodeType type = organNodeTypeService.get(id);
			type.setRemoved(true);
			type.setLastOperator(SecurityUtils.getUserId());
			type.setLastOperateTime(Calendar.getInstance().getTime());
			organNodeTypeService.update(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.node.type.remove.success"));
		} catch (Exception ex) {
			logger.error("删除组织节点类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("relation/type/index")
	public String getOrganRelationTypeIndex(String code, String name) {
		
		return ORGAN_RELATION_TYPE_INDEX;
	}
	
	@RequestMapping("relation/type/query")
	@ResponseBody
	public List<OrganRelationType> queryOrganRelationType() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrganRelationType.class);
		detachedCriteria.add(Restrictions.eq("removed", false));
		return organRelationTypeService.findByCriteria(detachedCriteria);
	}
	
	@RequestMapping("relation/type/query/page")
	@ResponseBody
	public Page<OrganRelationType> queryOrganRelationTypePage(String code, String name, Integer limit, Integer page) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrganRelationType.class);
		if (StringUtils.isNotBlank(code)) {
			detachedCriteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		return organRelationTypeService.findByCriteria(detachedCriteria, page, limit);
	}
	
	@RequestMapping("relation/create")
	public String crateOrganNodeRelation(String organNodeId, String organTreeId, Model model) {
		
		model.addAttribute("organTreeId", organTreeId);
		OrganNode organNode = organNodeService.get(organNodeId);
		OrganNodeVO organNodeVO = new OrganNodeVO();
		organNodeVO.setId(organNode.getId());
		organNodeVO.setCode(organNode.getCode());
		organNodeVO.setName(organNode.getName());
		organNodeVO.setAllName(organNode.getAllName());
		model.addAttribute("organNode", organNodeVO);
		return ORGAN_RELATION_CREATE_INDEX;
	}
	
	@RequestMapping("relation/edit")
	public String editOrganNodeRelation(String organRelationId, String organTreeId, Model model) {
		
		OrganRelation relation = organRelationService.loadOrganRelationByIdWithLazy(organRelationId, new String[] {
		        "childNode", "parentNode", "organRelationType"
		});
		
		model.addAttribute("relation", relation);
		model.addAttribute("organTreeId", organTreeId);
		return ORGAN_RELATION_EDIT_INDEX;
	}
	
	@RequestMapping("relation/create/save")
	@ResponseBody
	public AjaxResult saveCreateOrganNodeRelation(String organTreeId, String childNodeId, String parentNodeId,
	        String organRelationTypeId, String description) {
		
		try {
			
			OrganRelationType organRelationType = organRelationTypeService.get(organRelationTypeId);
			Boolean exist = organRelationService.existOrganRelation(parentNodeId, childNodeId,
			        organRelationType.getCode());
			
			if (exist) {
				return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, "已存在相同的组织关系！");
			} else {
				OrganTree organTree = organTreeService.get(organTreeId);
				OrganNode childOrganNode = organNodeService.get(childNodeId);
				OrganNode parentOrganNode = organNodeService.get(parentNodeId);
				OrganRelation relation = new OrganRelation();
				relation.setName(organTree.getCode() + ",relation");
				relation.setChildNode(childOrganNode);
				relation.setParentNode(parentOrganNode);
				relation.setOrganRelationType(organRelationType);
				relation.setDescription(description);
				relation.setCreater(SecurityUtils.getUserId());
				relation.setCreateTime(Calendar.getInstance().getTime());
				organRelationService.save(relation);
				
				return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
			}
		} catch (Exception ex) {
			logger.error("新增组织关系", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("relation/edit/save")
	@ResponseBody
	public AjaxResult saveEditOrganNodeRelation(String organRelationId, String parentNodeId, String organRelationTypeId,
	        String description) {
		
		try {
			OrganRelation relation = organRelationService.loadWithLazy(organRelationId, new String[] {
			        "parentNode", "childNode", "organRelationType"
			});
			OrganRelationType organRelationType = organRelationTypeService.get(organRelationTypeId);
			Boolean exist = organRelationService.existOrganRelation(parentNodeId, relation.getChildNode().getId(),
			        organRelationType.getCode());
			if (!exist) {
				OrganNode parentOrganNode = organNodeService.get(parentNodeId);
				relation.setParentNode(parentOrganNode);
				relation.setOrganRelationType(organRelationType);
			}
			relation.setDescription(description);
			relation.setLastOperator(SecurityUtils.getUserId());
			relation.setLastOperateTime(Calendar.getInstance().getTime());
			organRelationService.update(relation);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("修改组织关系", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
		
	}
	
	@RequestMapping("relation/remove/save")
	@ResponseBody
	public AjaxResult removeOrganNodeRelation(String id) {
		
		try {
			OrganRelation relation = organRelationService.get(id);
			relation.setRemoved(true);
			relation.setLastOperator(SecurityUtils.getUserId());
			relation.setLastOperateTime(Calendar.getInstance().getTime());
			organRelationService.update(relation);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("修改组织关系", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("relation/type/create")
	public String createOrganRelationTypeIndex() {
		
		return ORGAN_RELATION_TYPE_CREATE;
	}
	
	@RequestMapping("relation/type/create/save")
	@ResponseBody
	public AjaxResult saveCreateOrganRelationType(OrganRelationType type) {
		
		try {
			type.setId(null);
			type.setCreater(SecurityUtils.getUserId());
			type.setCreateTime(Calendar.getInstance().getTime());
			organRelationTypeService.save(type);
			
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.relation.type.remove.success"));
		} catch (Exception ex) {
			logger.error("创建组织关系类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("relation/type/edit")
	public String editOrganRelationTypeIndex(String id, Model model) {
		
		OrganRelationType type = organRelationTypeService.get(id);
		model.addAttribute("type", type);
		return ORGAN_RELATION_TYPE_EDIT;
	}
	
	@RequestMapping("relation/type/edit/save")
	@ResponseBody
	public AjaxResult saveEditOrganRelationType(OrganRelationType type) {
		
		try {
			type.setLastOperator(SecurityUtils.getUserId());
			type.setLastOperateTime(Calendar.getInstance().getTime());
			organRelationTypeService.update(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.relation.type.remove.success"));
		} catch (Exception ex) {
			logger.error("维护组织关系类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("relation/type/remove/save")
	@ResponseBody
	public AjaxResult removeOrganRelationType(String id) {
		
		OrganRelationType type = organRelationTypeService.get(id);
		try {
			type.setLastOperator(SecurityUtils.getUserId());
			type.setLastOperateTime(Calendar.getInstance().getTime());
			type.setRemoved(true);
			organRelationTypeService.update(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.relation.type.remove.success"));
		} catch (Exception ex) {
			logger.error("删除组织关系类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/type/index")
	public String queryOrganTreeTypeIndex() {
		
		return ORGAN_TREE_TYPE_INDEX;
	}
	
	@RequestMapping("tree/type/query/page")
	@ResponseBody
	public Page<OrganTreeType> queryOrganTreeTypePage(String code, String name, Integer limit, Integer page) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrganTreeType.class);
		if (StringUtils.isNotBlank(code)) {
			detachedCriteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		return organTreeTypeService.findByCriteria(detachedCriteria, page, limit);
	}
	
	@RequestMapping("tree/type/create")
	public String createOrganTreeTypeIndex() {
		
		return ORGAN_TREE_TYPE_CREATE;
	}
	
	@RequestMapping("tree/type/create/save")
	@ResponseBody
	public AjaxResult saveCreateOrganTreeType(OrganTreeType type) {
		
		try {
			type.setId(null);
			type.setCreater(SecurityUtils.getUserId());
			type.setCreateTime(Calendar.getInstance().getTime());
			organTreeTypeService.save(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.tree.type.remove.success"));
		} catch (Exception ex) {
			logger.error("创建组织类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/type/edit")
	public String editOrganTreeTypeIndex(String id, Model model) {
		
		OrganTreeType type = organTreeTypeService.get(id);
		model.addAttribute("type", type);
		return ORGAN_TREE_TYPE_EDIT;
	}
	
	@RequestMapping("tree/type/edit/save")
	@ResponseBody
	public AjaxResult saveEditOrganTreeTypeIndex(OrganTreeType type) {
		
		try {
			type.setLastOperator(SecurityUtils.getUserId());
			type.setLastOperateTime(Calendar.getInstance().getTime());
			organTreeTypeService.update(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.relation.type.remove.success"));
		} catch (Exception ex) {
			logger.error("维护组织类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
		
	}
	
	@RequestMapping("tree/type/remove/save")
	@ResponseBody
	public AjaxResult removeOrganTreeType(String id) {
		
		OrganTreeType type = organTreeTypeService.get(id);
		try {
			type.setLastOperator(SecurityUtils.getUserId());
			type.setLastOperateTime(Calendar.getInstance().getTime());
			type.setRemoved(true);
			organTreeTypeService.update(type);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE,
			        getMessage("security.organ.tree.type.remove.success"));
		} catch (Exception ex) {
			logger.error("删除组织类型", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/rule/index")
	public String getOrganRuleIndex(String id, Model model) {
		
		OrganTreeType type = organTreeTypeService.get(id);
		model.addAttribute("type", type);
		return ORGAN_RULE_INDEX;
	}
	
	@RequestMapping("tree/rule/create")
	public String createOrganRule(String organTreeTypeId, Model model) {
		
		OrganTreeType type = organTreeTypeService.get(organTreeTypeId);
		model.addAttribute("type", type);
		return ORGAN_RULE_CREATE;
	}
	
	@RequestMapping("tree/rule/create/save")
	@ResponseBody
	public AjaxResult saveCreateOrganRule(OrganRuleVO param) {
		
		try {
			OrganRule rule = new OrganRule();
			OrganTreeType organTreeType = organTreeTypeService.get(param.getTreeTypeId());
			OrganNodeType superiorNodeType = organNodeTypeService.get(param.getSuperiorNodeTypeId()),
			        subordinateNodeType = organNodeTypeService.get(param.getSubordinateNodeTypeId());
			rule.setOrganTreeType(organTreeType);
			rule.setSuperiorNodeType(superiorNodeType);
			rule.setSubordinateNodeType(subordinateNodeType);
			rule.setCode(param.getCode());
			rule.setName(param.getName());
			rule.setDescription(param.getDescription());
			rule.setCreater(SecurityUtils.getUserId());
			rule.setCreateTime(Calendar.getInstance().getTime());
			organRuleService.save(rule);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("新建组织类型规则", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/rule/edit")
	public String editOrganRule(String organTreeTypeId, String id, Model model) {
		
		OrganTreeType type = organTreeTypeService.get(organTreeTypeId);
		model.addAttribute("type", type);
		OrganRule rule = organRuleService.get(id);
		model.addAttribute("rule", rule);
		return ORGAN_RULE_EDIT;
	}
	
	@RequestMapping("tree/rule/edit/save")
	@ResponseBody
	public AjaxResult saveEditOrganRule(OrganRuleVO rule) {
		
		try {
			OrganRule organRule = organRuleService.get(rule.getId());
			OrganTreeType organTreeType = organTreeTypeService.get(rule.getTreeTypeId());
			OrganNodeType superiorNodeType = organNodeTypeService.get(rule.getSuperiorNodeTypeId()),
			        subordinateNodeType = organNodeTypeService.get(rule.getSubordinateNodeTypeId());
			organRule.setOrganTreeType(organTreeType);
			organRule.setSuperiorNodeType(superiorNodeType);
			organRule.setSubordinateNodeType(subordinateNodeType);
			organRule.setCode(rule.getCode());
			organRule.setName(rule.getName());
			organRule.setDescription(rule.getDescription());
			organRuleService.update(organRule);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("维护组织类型规则", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/rule/remove/save")
	@ResponseBody
	public AjaxResult removeOrganRule(String id) {
		
		try {
			OrganRule organRule = organRuleService.get(id);
			organRuleService.delete(organRule);
			return new AjaxResult(true, AjaxResult.MESSAGE_SUCCESS_TYPE, getMessage("system.success"));
		} catch (Exception ex) {
			logger.error("删除组织类型规则", ex);
			return new AjaxResult(false, AjaxResult.MESSAGE_ERROR_TYPE, getMessage("system.error", new String[] {
			        ex.getMessage()
			}));
		}
	}
	
	@RequestMapping("tree/rule/query/page")
	@ResponseBody
	public Page<OrganRuleVO> queryOrganRulePage(String id, Integer limit, Integer page) {
		
		OrganTreeType type = organTreeTypeService.get(id);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrganRule.class);
		detachedCriteria.add(Restrictions.eq("organTreeType", type));
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<OrganRule> rules = organRuleService.findByCriteria(detachedCriteria, page, limit);
		
		List<OrganRuleVO> result = new ArrayList<OrganRuleVO>();
		for (OrganRule rule : rules.getResult()) {
			OrganRuleVO vo = new OrganRuleVO();
			vo.setId(rule.getId());
			vo.setCode(rule.getCode());
			vo.setName(rule.getName());
			vo.setDescription(rule.getDescription());
			
			OrganTreeType treeType = rule.getOrganTreeType();
			vo.setTreeTypeId(treeType.getId());
			vo.setTreeTypeCode(treeType.getCode());
			vo.setTreeTypeName(treeType.getName());
			
			OrganNodeType subNodeType = rule.getSubordinateNodeType();
			
			vo.setSubordinateNodeTypeId(subNodeType.getId());
			vo.setSubordinateNodeTypeCode(subNodeType.getCode());
			vo.setSubordinateNodeTypeName(subNodeType.getName());
			
			OrganNodeType supNodeType = rule.getSuperiorNodeType();
			
			vo.setSuperiorNodeTypeId(supNodeType.getId());
			vo.setSuperiorNodeTypeCode(supNodeType.getCode());
			vo.setSuperiorNodeTypeName(supNodeType.getName());
			result.add(vo);
		}
		
		return new Page<OrganRuleVO>(rules.getStart(), rules.getCurrentPageSize(), rules.getTotalSize(),
		        rules.getPageSize(), result);
	}
	
	@RequestMapping("/user/quey")
	@ResponseBody
	public Page<UserVO> querySecurityUserInOrganNode(String organNodeId, String organTreeId, String loginName,
	        String name, Integer limit, Integer page) {
		
		OrganTree organTree = organTreeService.get(organTreeId);
		OrganNode organNode = organNodeService.get(organNodeId);
		Map<String, Object> filter = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(loginName)) {
			filter.put("loginName", loginName);
		}
		if (StringUtils.isNotBlank(name)) {
			filter.put("name", name);
		}
		Page<SecurityUser> securityUsers = organNodeService.queryAllUserInAdminNodeByPage(null, organTree, organNode,filter, page,
		        limit);
		List<UserVO> result = new ArrayList<UserVO>();
		for (SecurityUser user : securityUsers.getResult()) {
			UserVO userVo = new UserVO();
			userVo.setId(user.getId());
			userVo.setLoginName(user.getLoginName());
			userVo.setAccountType(user.getAccountType());
			userVo.setName(user.getName());
			userVo.setOfficePhone(user.getOfficePhone());
			userVo.setMobile1(user.getMobile1());
			userVo.setEmail(user.getEmail());
			userVo.setStatus(user.getStatus());
			userVo.setSex(user.getSex());
			userVo.setAccountType(user.getAccountType());
			userVo.setAddress(user.getAddress());
			result.add(userVo);
		}
		return new Page<UserVO>(securityUsers.getStart(), securityUsers.getCurrentPageSize(),
		        securityUsers.getTotalSize(), securityUsers.getPageSize(), result);
	}
	
	@RequestMapping("relation/source/query")
	@ResponseBody
	public Page<OrganRelationVO> getOrganRelationWithSourceNode(String organNodeId, String organNodeName,
	        String organRelationTypeCode, Integer limit, Integer page) {
		
		return organRelationService.getOrganRelationWithSourceNode(organNodeId, organNodeName, organRelationTypeCode,
		        limit, page);
	}
	
	@ResponseBody
	@RequestMapping("relation/target/query")
	public Page<OrganRelationVO> getOrganRelationWithTargetNode(String organNodeId, String organNodeName,
	        String organRelationTypeCode, Integer limit, Integer page) {
		
		return organRelationService.getOrganRelationWithTargetNode(organNodeId, organNodeName, organRelationTypeCode,
		        limit, page);
	}
}
