/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPeopleService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月15日 上午9:09:06 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月15日 上午9:09:06 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.TrainPeople;
import com.wondersgroup.human.vo.ofcflow.TrainPeopleVO;

/** 
 * @ClassName: TrainPeopleService 
 * @Description: 培训人员
 * @author: lihao
 * @date: 2018年11月15日 上午9:09:06
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface TrainPeopleService extends GenericService<TrainPeople>{

	/** 
	 * @Title: pageList 
	 * @Description: 获取培训人员列表
	 * @param id
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ServantVO>
	 */
	Page<TrainPeopleVO> pageList(String id,Servant servant, Integer page, Integer limit);

	/** 
	 * @Title: exportByPerson 
	 * @Description: 按人员导出
	 * @param start
	 * @param end
	 * @return
	 * @return: List<?>
	 */
	List<?> exportByPerson(String start,String end);

	/** 
	 * @Title: exportByUnit 
	 * @Description: 按单位导出
	 * @param start
	 * @param end
	 * @param hour1
	 * @param hour2
	 * @return
	 * @return: List<?>
	 */
	List<?> exportByUnit(String start, String end, Integer hour1, Integer hour2);

}
