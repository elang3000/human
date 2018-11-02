/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: MainController.java
 * 工程名: human
 * 包名: com.wondersgroup.human
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月18日 下午7:32:43
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月18日 下午7:32:43
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.framework.controller.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.SystemParamContant;
import com.wondersgroup.framework.announcement.bo.Announcement;
import com.wondersgroup.framework.announcement.service.AnnouncementService;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.menu.bo.MenuResource;
import com.wondersgroup.framework.menu.vo.MenuResourceVO;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.resource.connector.provider.SystemParamProvider;
import com.wondersgroup.framework.security.bo.SecurityGroup;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.shiro.SystemAuthorizingRealm.Principal;
import com.wondersgroup.framework.util.RuntimeUtils;
import com.wondersgroup.framework.util.RuntimeUtils.CPU;
import com.wondersgroup.framework.util.RuntimeUtils.Memory;
import com.wondersgroup.framework.util.RuntimeUtils.OS;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;

/**
 * @ClassName: MainController
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年5月18日 下午7:32:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("main")
public class MainController extends GenericController {
	
	@Autowired
	private AnnouncementService announcementService;
	
	private final static String DEFAULT_MAIN_INDEX = "index";
	
	@Autowired
	FlowRecordService flowRecordService;
	
	@RequestMapping("/index")
	public String index(Model model) {
		
		String userId = SecurityUtils.getUserId();// 当前登录人ID
		String appCode = SecurityUtils.getPrincipal().getAppCode();
		
		AppNode appNode = getAppNodeService().findNodeByCode(appCode);
		model.addAttribute("appNode", appNode);
		SecurityUtils.getSession().setAttribute("appNode", appNode);
		List<MenuResourceVO> menuResources = getMenuService().getAuthMenuByAppNodeAndUserId(appNode.getId(), userId);
		model.addAttribute("menus", menuResources);
		List<MenuResourceVO> shortcutMenuResources = new ArrayList<MenuResourceVO>();
		generateMeneResource(menuResources, shortcutMenuResources);
		SecurityUtils.getSession().setAttribute("shortcut", shortcutMenuResources);
		// 获取报表菜单
		MenuResource parentMenu = getMenuService().findUniqueBy("code", CommonConst.SYSTEM_REPORT_CODE);
		MenuResource[] reportObjects = getMenuService().getChildMenuResourceOrderlyByParentMenu(parentMenu);
		List<MenuResourceVO> reports = new ArrayList<MenuResourceVO>();
		for (MenuResource menu : reportObjects) {
			reports.add(menu.toViewObject());
		}
		SecurityUtils.getSession().setAttribute("reports", reports);
		
		Principal principal = SecurityUtils.getPrincipal();
		model.addAttribute("principal", principal);
		
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(userId);
		model.addAttribute("organNode", organNode);
		
		// 获取自己未读消息数量
		List<Announcement> list = announcementService.queryAnnouncementByUserIdAndState(userId,
		        Announcement.ANNOUNCEMENT_STATUS_UNREAD);
		if (list != null && list.size() > 0) {
			model.addAttribute("unreadAnnouncement", list.size());
			model.addAttribute("isUnreadAnnouncement", true);
		} else {
			model.addAttribute("isUnreadAnnouncement", false);
		}
		return DEFAULT_MAIN_INDEX;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/content")
	public String content(Model model) {
		
		List<MenuResourceVO> shortcutMenuResources = (List<MenuResourceVO>) SecurityUtils.getSession()
		        .getAttribute("shortcut");
		model.addAttribute("shortcut", shortcutMenuResources);
		String appCode = (String) SecurityUtils.getSession().getAttribute("appCode");
		if (StringUtils.equals(appCode, SystemParamProvider.getString(SystemParamContant.DEFAULT_APPLICATION_CODE))) {
			// 获取用户对象
			String securityUserId = SecurityUtils.getUserId();
			SecurityUser securityUser = getUserService().load(securityUserId);
			List<SecurityGroup> groups = getUserService().getAllGroupsOfUser(securityUser);
			SecurityGroup group = null;
			// 获取用户最大层级分组
			if (groups != null && !groups.isEmpty()) {
				for (SecurityGroup gp : groups) {
					Integer type = Integer.parseInt(gp.getType());
					if (group != null) {
						Integer groupType = Integer.parseInt(gp.getType());
						if (type > groupType) {
							group = gp;
						}
					} else {
						group = gp;
					}
				}
			}
			if (group != null && (StringUtils.equals(SecurityGroup.SECURITY_GROUP_TYPE_LEVEL, group.getType())
			        || StringUtils.equals(SecurityGroup.SECURITY_GROUP_TYPE_TOP, group.getType()))) {
				List<MenuResourceVO> reports = (List<MenuResourceVO>) SecurityUtils.getSession()
				        .getAttribute("reports");
				if (reports != null && !reports.isEmpty()) {
					shortcutMenuResources.addAll(reports);
					model.addAttribute("shortcut", shortcutMenuResources);
				}
				model.addAttribute("idLeader", true);
				SecurityUtils.getSession().setAttribute("group", group);
			} else {
				model.addAttribute("idLeader", false);
			}
			return "main/bizContent";
		} else {
			OS os = RuntimeUtils.os();
			model.addAttribute("os", os);
			return "main/systemContent";
		}
	}
	
	@RequestMapping("cpu/query")
	@ResponseBody
	public Map<String, Object> getCpu() {
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date currentTime = Calendar.getInstance().getTime();
			String formatStr = format.format(currentTime);
			result.put("x", formatStr);
			List<CPU> cpus = RuntimeUtils.cpu();
			Double perc = 0d;
			for (CPU cpu : cpus) {
				perc += cpu.getCombinedPerc();
			}
			result.put("y", Math.ceil(perc / cpus.size() * 100));
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("memory/query")
	@ResponseBody
	public Map<String, Object> getMemery() {
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Memory memory = RuntimeUtils.memory();
			
			Map<String, Object> mem = new HashMap<String, Object>();
			
			Map<String, Object> memFree = new HashMap<String, Object>();
			memFree.put("value", memory.getMemFree() / 1024 / 1024);
			memFree.put("name", "已使用物理内存");
			mem.put("memFree", memFree);
			Map<String, Object> memUsed = new HashMap<String, Object>();
			memUsed.put("value", memory.getMemUsed() / 1024 / 1024);
			memUsed.put("name", "空闲物理内存");
			mem.put("memUsed", memUsed);
			result.put("mem", mem);
			
			Map<String, Object> swap = new HashMap<String, Object>();
			Map<String, Object> swapFree = new HashMap<String, Object>();
			swapFree.put("value", memory.getSwapFree() / 1024 / 1024);
			swapFree.put("name", "已使用交换内存");
			swap.put("swapFree", swapFree);
			
			Map<String, Object> swapUsed = new HashMap<String, Object>();
			swapUsed.put("value", memory.getSwapUsed() / 1024 / 1024);
			swapUsed.put("name", "空闲交换内存");
			swap.put("swapUsed", swapUsed);
			result.put("swap", swap);
			
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void generateMeneResource(List<MenuResourceVO> menuResources, List<MenuResourceVO> shortcutMenuResources) {
		
		for (MenuResourceVO menu : menuResources) {
			
			if (menu.getIsShortcut() && StringUtils.isNotBlank(menu.getLinkPath())) {
				shortcutMenuResources.add(menu);
			}
			if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
				generateMeneResource(menu.getChildren(), shortcutMenuResources);
			}
		}
	}
	
	@RequestMapping("doing/counter")
	@ResponseBody
	public List<Integer> queryDoingCounter() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ofcFlowState", FlowRecord.DOING);
		SecurityGroup group = (SecurityGroup) SecurityUtils.getSession().getAttribute("group");
		if (group != null) {
			// 查询单位领导分组
			if (StringUtils.equals(SecurityGroup.SECURITY_GROUP_TYPE_LEVEL, group.getType())) {
				OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
				params.put("targetOrganNode", organNode);
			}
			// 查询区级领导分组
			if (StringUtils.equals(SecurityGroup.SECURITY_GROUP_TYPE_TOP, group.getType())) {
				
			}
		} else {
			params.put("targetSecurityUser", getUserService().get(SecurityUtils.getUserId()));
		}
		
		List<Integer> counter = new ArrayList<Integer>();
		params.put("category", FlowRecord.FLOW_RECORD_CATEGORY_GOV);
		Integer servantCounter = flowRecordService.getWorkFlowCounter(params);
		counter.add(servantCounter);
		
		params.put("category", FlowRecord.FLOW_RECORD_CATEGORY_INS);
		Integer insCounter = flowRecordService.getWorkFlowCounter(params);
		counter.add(insCounter);
		
		params.put("category", FlowRecord.FLOW_RECORD_CATEGORY_ORG);
		Integer organCounter = flowRecordService.getWorkFlowCounter(params);
		counter.add(organCounter);
		
		params.put("category", FlowRecord.FLOW_RECORD_CATEGORY_PLAN);
		Integer planCounter = flowRecordService.getWorkFlowCounter(params);
		counter.add(planCounter);
		
		return counter;
	}
	
	@RequestMapping("model/doing/counter")
	@ResponseBody
	public List<Map<String,Object>> queryModelDoingCounter() {
		
		Map<String, Integer> all = flowRecordService.countWorkFLowBusinessNum(null, null,
		        Calendar.getInstance().getTime());
		Map<String, Integer> done = flowRecordService.countWorkFLowBusinessNum(null, FlowRecord.DONE,
		        Calendar.getInstance().getTime());
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

		
		generateWorkFLowStatic("RecruitEmployPlan","招录计划上报事项完成率", all, done, result);
		generateWorkFLowStatic("RecruitPost","招录职务上报事项完成率", all, done, result);
		
		generateWorkFLowStatic("ProbationServant","试用期考核合格事项完成率", all, done, result);
		generateWorkFLowStatic("CancelProbationServant","试用期考核不合格事项完成率", all, done, result);
		
		generateWorkFLowStatic("ZhuanRenTLBIntoMgr_THIS","本区同类别转任事项完成率", all, done, result);
		generateWorkFLowStatic("ZhuanRenTLBIntoMgr_OUTER","外区同类别转任事项完成率", all, done, result);
		generateWorkFLowStatic("ZhuanRenTLBOutMgr","同类别转任转出事项完成率", all, done, result);
		
		generateWorkFLowStatic("ZhuanRenKLBIntoMgr_THIS","本区跨类别转任事项完成率", all, done, result);
		generateWorkFLowStatic("ZhuanRenKLBIntoMgr_OUTER","外区跨类别转任事项完成率", all, done, result);
		generateWorkFLowStatic("ZhuanRenKLBOutMgr","跨类别转任转出事项完成率", all, done, result);
		
		generateWorkFLowStatic("ReferenceExchange_THIS","本区参公交流事项完成率", all, done, result);
		generateWorkFLowStatic("ReferenceExchange_OUTER","外区参公交流事项完成率", all, done, result);
		
		generateWorkFLowStatic("DiaoRenIntoMgr_THIS","本区内调入事项完成率", all, done, result);
		generateWorkFLowStatic("DiaoRenIntoMgr_OUTER","外区调入事项完成率", all, done, result);
		generateWorkFLowStatic("DiaoRenOutMgr_THIS","调出到本区事项完成率", all, done, result);
		
		generateWorkFLowStatic("ResignServant","公务员辞职事项完成率", all, done, result);
		
		generateWorkFLowStatic("DeathServant","公务员死亡事项完成率", all, done, result);
		
		generateWorkFLowStatic("Train","培训学时考核事项完成率", all, done, result);
		
		generateWorkFLowStatic("PunishServant","公务员处分事项完成率", all, done, result);
		
		generateWorkFLowStatic("JOBSHIFT_PROMOTE","公务员升职事项完成率", all, done, result);
		generateWorkFLowStatic("JOBSHIFT_DEMOTE","公务员降职事项完成率", all, done, result);
		generateWorkFLowStatic("JOBSHIFT_DEPOSE","公务员免职事项完成率", all, done, result);
		generateWorkFLowStatic("JOBSHIFT_SHIFT","公务员轮岗事项完成率", all, done, result);
		
		generateWorkFLowStatic("ASSESS_REWARD","年度考核奖励事项完成率", all, done, result);
		return result;
	}

	/** 
	 * @Title: generateWorkFLowStatic 
	 * @Description: TODO
	 * @param all
	 * @param done
	 * @param result
	 * @return: void
	 */
	private void generateWorkFLowStatic(String modelCode,String modelName,Map<String, Integer> all, Map<String, Integer> done,
	        List<Map<String, Object>> result) {
		if (all.containsKey(modelCode)) {
			Map<String,Object> param = new HashMap<String,Object>();
			Double allCounter = all.get(modelCode).doubleValue();
			if (allCounter > 0) {
				Integer t = done.get(modelCode);
				Double doneCounter = 0D;
				if (t != null) {
					doneCounter = t.doubleValue();
				}
				param.put("code", modelCode);
				param.put("name", modelName);
				param.put("allNum", allCounter);
				param.put("doingNum", doneCounter);
				result.add(param);
			}
		}
	}
	
	@RequestMapping("deploying/{id}")
	public String waitDeploy(@PathVariable("id") String id) {
		
		return "main/deploying";
	}
	
}
