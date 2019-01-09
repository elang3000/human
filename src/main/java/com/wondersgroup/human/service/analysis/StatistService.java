/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: StatistService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.analysis 
 * 描述: TODO
 * 创建人: Wonders-Rain   
 * 创建时间: 2018年9月13日 下午5:11:13 
 * 版本号: V1.0
 * 修改人：Wonders-Rain 
 * 修改时间：2018年9月13日 下午5:11:13 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.analysis;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: StatistService 
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年9月13日 下午5:11:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface StatistService {
	
	/**
	 * @Title: statistServantYears 
	 * @Description: 根据所提供的组织集合，查询该集合下的公务员所在年代；
	 * @param organNodeIds
	 * @return
	 * @return: Map<String,Integer>
	 */
	Map<String,Integer> statistServantYears(List<String> organNodeIds);
	
	/**
	 * @Title: statistServantTopEducation 
	 * @Description: TODO
	 * @param organNodeIds
	 * @return
	 * @return: Map<String,Integer>
	 */
	Map<String,Integer> statistServantTopEducation(List<String> organNodeIds);
	
	/**
	 * 最高学历毕业学校学校属性（是否985，是否211，是否双一流，是否普通）
	 * @Title: statistServantSchoolNature 
	 * @Description: TODO
	 * @param organNodeIds
	 * @return
	 * @return: Map<String,Integer>
	 */
	public Map<String, Integer> statistServantSchoolNature(List<String> organNodeIds);
	
	/**
	 * 是否上海本地人口（身份证号码310开头）
	 * @Title: statistServantisshanghailocal 
	 * @Description: TODO
	 * @param organNodeIds
	 * @return
	 * @return: Map<String,Integer>
	 */
	public Map<String, Integer> statistServantisshanghailocal(List<String> organNodeIds);
}
