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

import java.util.Date;
import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.TrainPerson;
import com.wondersgroup.human.bo.ofcflow.TrainYearPlan;
import com.wondersgroup.human.vo.ofcflow.TrainPersonVO;

/** 
 * @ClassName: TrainPersonService 
 * @Description: 培训个人信息服务接口
 * @author: lihao
 * @date: 2018年9月13日 上午9:11:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface TrainPersonService extends GenericService<TrainPerson>{

	/** 
	 * @Title: pageList 
	 * @Description: 培训信息个人bo转vo
	 * @param planId
	 * @param limit 
	 * @param page 
	 * @return
	 * @return: Page<TrainPersonVO>
	 */
	Page<TrainPersonVO> pageList(String planId, Integer page, Integer limit);

	/** 
	 * @Title: exportByUnit 
	 * @Description: TODO
	 * @param typ
	 * @return
	 * @return: List
	 */
	List<?> exportByUnit(TrainYearPlan typ);

	/** 
	 * @Title: exportByPerson 
	 * @Description: TODO
	 * @param typ
	 * @return
	 * @return: List
	 */
	List<?> exportByPerson(TrainYearPlan typ);

	public int getTrainHours(String id,Date start,Date end);
}
