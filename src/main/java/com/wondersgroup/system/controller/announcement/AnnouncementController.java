/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AnnouncementController.java 
 * 工程名: human
 * 包名: com.wondersgroup.system.controller.announcement 
 * 描述: 消息中心 控制器
 * 创建人: tzy   
 * 创建时间: 2018年10月29日 上午10:44:29 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月29日 上午10:44:29 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.system.controller.announcement;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.announcement.bo.Announcement;
import com.wondersgroup.framework.announcement.service.AnnouncementService;
import com.wondersgroup.framework.announcement.vo.AnnouncementVO;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.SecurityUtils;

/** 
 * @ClassName: AnnouncementController 
 * @Description: 消息中心 控制器
 * @author: tzy
 * @date: 2018年10月29日 上午10:44:29
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("announcement")
public class AnnouncementController extends GenericController{
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private UserService userService;
	
	/**
	 * 数据列表
	 */
	private final static String DATA_LIST = "models/announcement/announcementList";
	
	/**
	 * 消息详细信息
	 */
	private final static String DATA_DETAIL = "models/announcement/announcementDetail";
	
	/**
	 * @Title: index 
	 * @Description: 数据列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return DATA_LIST;
	}
	/**
	 * @Title: detail 
	 * @Description: 数据详细信息页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/detail")
	public String detail(String id,Model model) {
		SecurityUser user = userService.load(SecurityUtils.getUserId());//当前登录人
		model.addAttribute("user",user.getName());
		Announcement announcement = announcementService.saveAnnouncementInMemory(id);
		model.addAttribute("a",announcement);
		OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(announcement.getUserId());
		model.addAttribute("organName", organ.getName());
		return DATA_DETAIL;
	}
	
	/**
	 * @Title: announcementList 
	 * @Description: 数据列表
	 * @param state	消息状态：0--未读，1--已读
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<Announcement>
	 */
	@ResponseBody
	@RequestMapping("/announcementList")
	public Page<AnnouncementVO> announcementList(String state,Integer limit,Integer page) {
		Map<String,Object> condMap = new HashMap<>();
		
		if (page == null || page == 0)
			page = 1;
		
		condMap.put("userId", SecurityUtils.getUserId());//当前登录人id
		condMap.put("removed", false);//未删除
		if(StringUtils.isNotBlank(state)){
			condMap.put("state", Integer.parseInt(state));
		}
		Page<AnnouncementVO> pageInfo = announcementService.getUserAnnouncementListbyPage(condMap,page,limit,null);
		
		return pageInfo;
	}
}
