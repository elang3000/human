/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainYearPlanService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月18日 上午10:32:38 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月18日 上午10:32:38 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.TrainYearPlan;
import com.wondersgroup.human.vo.ofcflow.TrainYearPlanVO;

/** 
 * @ClassName: TrainYearPlanService 
 * @Description: 培训年度考核服务接口类
 * @author: lihao
 * @date: 2018年9月18日 上午10:32:38
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface TrainYearPlanService extends GenericService<TrainYearPlan> {

	/** 
	 * @Title: pageList 
	 * @Description: 培训年度考核bo转vo
	 * @param planId
	 * @return
	 * @return: Page<TrainPersonVO>
	 */
	Page<TrainYearPlanVO> pageList(TrainYearPlan trainYearPlan,Integer page, Integer limit);
}
