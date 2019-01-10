package com.wondersgroup.human.controller.businesspersonel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;

/**
 * @ClassName: SocialWorkerQueryController 社工综合查询controller
 * @Description: 综合查询控制类
 * @author: lihao
 * @date: 2018年8月20日下午2:31:55 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@RequestMapping("/business/social")
@Controller
public class SocialWorkerQueryController {

	
	@Autowired
	private SocialWorkerService socialWorkerService;
	/**
	 * 社工综合查询人员列表
	 */
	public static final String VIEW_SOCIALWORKER_QUERY = "models/socialworkerquery/socialworkerqueryList"; 

	//public static final String VIEW_SOCIALWORKER_MAIN  = "models/socialworkerquery/socialworkerQueryMain";
	
	@RequestMapping("/socialworkerqueryList")
	public String socialworkerqueryList(){
		return VIEW_SOCIALWORKER_QUERY;
	}

	

}
