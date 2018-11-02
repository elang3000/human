/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月27日 下午7:19:08 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月27日 下午7:19:08 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.AbroadYearPlan;
import com.wondersgroup.human.vo.ofcflow.AbroadYearPlanVO;

/** 
 * @ClassName: AbroadService 
 * @Description: 参公交流汇总服务接口类
 * @author: lihao
 * @date: 2018年9月27日 下午7:19:08
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface AbroadYearPlanService extends GenericService<AbroadYearPlan>{

	/** 
	 * @Title: pageList 
	 * @Description: 因公出国计划服务接口类
	 * @param abroadYearPlan
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<AbroadYearPlanVO>
	 */
	Page<AbroadYearPlanVO> pageList(AbroadYearPlan abroadYearPlan, Integer page, Integer limit);

}
