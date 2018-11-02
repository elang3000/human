/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPlanService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月13日 上午9:11:09 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月13日 上午9:11:09 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.TrainPlan;
import com.wondersgroup.human.vo.ofcflow.TrainPlanVO;

/** 
 * @ClassName: TrainPlanService 
 * @Description: 培训信息服务接口
 * @author: lihao
 * @date: 2018年9月13日 上午9:11:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface TrainPlanService extends GenericService<TrainPlan>{

	/** 
	 * @Title: pageList 
	 * @Description: 培训学时bo转vo
	 * @param rainPlan
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<TrainPlanVO>
	 */
	Page<TrainPlanVO> pageList(TrainPlan trainPlan, Integer page, Integer limit);

	/** 
	 * @Title: savePlan 
	 * @Description: TODO
	 * @param temp
	 * @param opinion
	 * @param r
	 * @return: void
	 */
	public void savePlan(TrainPlan temp, String opinion, String r);

	/** 
	 * @Title: savePlan2 
	 * @Description: TODO
	 * @param trainPlan
	 * @param opinion
	 * @param r
	 * @return: void
	 */
	public void savePlan2(TrainPlan trainPlan, String opinion, String r);

}
