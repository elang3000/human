/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPlanService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月28日 上午9:48:16 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月28日 上午9:48:16 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.AbroadPlan;
import com.wondersgroup.human.vo.ofcflow.AbroadPlanVO;

/** 
 * @ClassName: AbroadPlanService 
 * @Description: 单位因公出国服务接口类
 * @author: lihao
 * @date: 2018年9月28日 上午9:48:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface AbroadPlanService extends GenericService<AbroadPlan>{

	/** 
	 * @Title: pageList 
	 * @Description: 单位因公出国bo->vo
	 * @param abroadPlan
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<AbroadPlanVO>
	 */
	Page<AbroadPlanVO> pageList(AbroadPlan abroadPlan, Integer page, Integer limit);

	/** 
	 * @Title: savePlan 
	 * @Description: TODO
	 * @param abroadPlan
	 * @param opinion
	 * @param r
	 * @return: void
	 */
	public void savePlan(AbroadPlan abroadPlan, String opinion, String r);

}
