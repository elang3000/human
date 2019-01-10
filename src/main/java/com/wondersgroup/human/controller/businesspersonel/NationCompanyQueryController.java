package com.wondersgroup.human.controller.businesspersonel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: NationCompanyQueryController 国企
 * @Description: 综合查询控制类
 * @author: lihao
 * @date: 2018年8月20日下午2:31:55 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@RequestMapping("/business/nation")
@Controller
public class NationCompanyQueryController {

	
	/**
	 * 国企职工人员综合查询
	 */
	private static final String BUSINESS_NATION_QUERY = "models/nationcompanyquery/nationalCompanyQueryList";
	

	/**
	 * 综合查询信息列表
	 * @return
	 */
	@RequestMapping("/nationalCompanyQueryList")
	public String nationalCompanyQueryList(){
		
		return BUSINESS_NATION_QUERY;
	}
	
}
