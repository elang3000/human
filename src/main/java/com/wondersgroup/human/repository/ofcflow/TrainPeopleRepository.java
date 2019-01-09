/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPeopleRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月15日 上午9:11:32 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月15日 上午9:11:32 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow;

import java.util.Date;
import java.util.List;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.TrainPeople;

/** 
 * @ClassName: TrainPeopleRepository 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年11月15日 上午9:11:32
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface TrainPeopleRepository extends GenericRepository<TrainPeople>{

	/** 
	 * @Title: exportByPerson 
	 * @Description: 导出按人员
	 * @param start
	 * @param end
	 * @return
	 * @return: List<?>
	 */
	List<?> exportByPerson(String start,String end);

	/** 
	 * @Title: exportByUnit 
	 * @Description: 导出按单位
	 * @param start
	 * @param end
	 * @param hour1
	 * @param hour2
	 * @return
	 * @return: List<?>
	 */
	List<?> exportByUnit(String start, String end, Integer hour1, Integer hour2);

}
