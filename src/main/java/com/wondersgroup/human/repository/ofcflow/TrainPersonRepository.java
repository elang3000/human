/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPlanRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月13日 上午9:23:09 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月13日 上午9:23:09 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.TrainPerson;
import com.wondersgroup.human.bo.ofcflow.TrainYearPlan;

import java.util.Date;
import java.util.List;

/** 
 * @ClassName: TrainPlanRepository 
 * @Description: 培训个人数据库操作接口
 * @author: lihao
 * @date: 2018年9月13日 上午9:23:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface TrainPersonRepository extends GenericRepository<TrainPerson> {
	
	public List<?> exportByUnit(TrainYearPlan typ);

	/** 
	 * @Title: exportByPerson 
	 * @Description: TODO
	 * @param typ
	 * @return
	 * @return: List
	 */
	public List<?> exportByPerson(TrainYearPlan typ);

	/** 
	 * @Title: getTrainHours 
	 * @Description: TODO
	 * @param id
	 * @param start
	 * @param end
	 * @return
	 * @return: int
	 */
	public int getTrainHours(String id, Date start, Date end);

}
