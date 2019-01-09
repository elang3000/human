/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignPeopleService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月20日 上午11:05:18 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月20日 上午11:05:18 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ResignPeople;
import com.wondersgroup.human.vo.ofcflow.ResignPeopleVO;

/** 
 * @ClassName: ResignPeopleService 
 * @Description: 辞职人员信息服务接口
 * @author: lihao
 * @date: 2018年12月20日 上午11:05:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ResignPeopleService extends GenericService<ResignPeople>{

	/** 
	 * @Title: resignPeopleList 
	 * @Description: 流程批次内辞职人员查询列表
	 * @param planId
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ResignPeopleVO>
	 */
	Page<ResignPeopleVO> resignPeopleList(String planId, Integer page, Integer limit);

	/** 
	 * @Title: peopleList 
	 * @Description: 所有辞职人员查询列表
	 * @param resignPeople
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ResignPeopleVO>
	 */
	Page<ResignPeopleVO> peopleList(ResignPeople resignPeople, Integer page, Integer limit);

	/** 
	 * @Title: getResignPeopleCountByPlanId 
	 * @Description: 获取计划内人数
	 * @param id
	 * @return
	 * @return: List
	 */
	public List<ResignPeople> getResignPeopleCountByPlanId(String id);

}
