/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: StatistController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.analysis
 * 描述: 统计控制器
 * 创建人: Wonders-Rain
 * 创建时间: 2018年9月13日 下午4:49:42
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年9月13日 下午4:49:42
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.controller.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.service.analysis.StatistService;

/**
 * @ClassName: StatistController
 * @Description: 统计专用控制器
 * @author: Wonders-Rain
 * @date: 2018年9月13日 下午4:49:42
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
public class StatistController extends GenericController {
	
	@Autowired
	StatistService statistService;
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	@RequestMapping("statist/education")
	@ResponseBody
	public List<Map<String, Object>> statistServantTopEducation() {
		
		List<String> organNodeIds = new ArrayList<String>();
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		organNode = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE);
		List<OrganNode> organNodes = organizationService.getAllChildOrganNode(organNode.getId());
		for (OrganNode node : organNodes) {
			organNodeIds.add(node.getId());
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Integer> queryResult = statistService.statistServantTopEducation(organNodeIds);
		Iterator<Entry<String, Integer>> iterator = queryResult.entrySet().iterator();
		
		Integer otherValue = 0;
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			Map<String, Object> res = new HashMap<String, Object>();
			switch (entry.getKey()) {
				case "未知" : 
					res.put("name", "未知");
					res.put("counter", entry.getValue());
					result.add(res);
					break;
				case "博士研究生毕业" : 
					res.put("name", "博士");
					res.put("counter", entry.getValue());
					result.add(res);
					break;
				case "硕士研究生毕业" : 
					res.put("name", "硕士");
					res.put("counter", entry.getValue());
					result.add(res);
					break; 
				case "大学本科毕业" : 
					res.put("name", "本科");
					res.put("counter", entry.getValue());
					result.add(res);
					break; 
				case "大学专科毕业" : 
					res.put("name", "专科");
					res.put("counter", entry.getValue());
					result.add(res);
					break; 
				default : 
					otherValue += entry.getValue();
					break;
			}
			
		}
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("name", "专科及以下");
		res.put("counter", otherValue);
		result.add(res);
		return result;
	}
	
	@RequestMapping("statist/years")
	@ResponseBody
	public List<Map<String, Object>> statistServantYears() {
		
		List<String> organNodeIds = new ArrayList<String>();
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		organNode = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE);
		List<OrganNode> organNodes = organizationService.getAllChildOrganNode(organNode.getId());
		for (OrganNode node : organNodes) {
			organNodeIds.add(node.getId());
		}
		Map<String, Integer> years = statistService.statistServantYears(organNodeIds);
		Iterator<Entry<String, Integer>> iterator = years.entrySet().iterator();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("name", entry.getKey());
			res.put("counter", entry.getValue());
			result.add(res);
		}
		return result;
	}
	
	@RequestMapping("statist/schoolnature")
	@ResponseBody
	public List<Map<String, Object>> statistServantSchoolNature() {
		
		List<String> organNodeIds = new ArrayList<String>();
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		organNode = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE);
		List<OrganNode> organNodes = organizationService.getAllChildOrganNode(organNode.getId());
		for (OrganNode node : organNodes) {
			organNodeIds.add(node.getId());
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Integer> queryResult = statistService.statistServantSchoolNature(organNodeIds);
		Iterator<Entry<String, Integer>> iterator = queryResult.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("name", entry.getKey());
			res.put("counter", entry.getValue());
			result.add(res);
		}
		return result;
	}
	
	@RequestMapping("statist/isshlocal")
	@ResponseBody
	public List<Map<String, Object>> statistServantisshanghailocal() {
		
		List<String> organNodeIds = new ArrayList<String>();
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		organNode = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE);
		List<OrganNode> organNodes = organizationService.getAllChildOrganNode(organNode.getId());
		for (OrganNode node : organNodes) {
			organNodeIds.add(node.getId());
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Integer> queryResult = statistService.statistServantisshanghailocal(organNodeIds);
		Iterator<Entry<String, Integer>> iterator = queryResult.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("name", entry.getKey());
			res.put("counter", entry.getValue());
			result.add(res);
		}
		return result;
	}
}
