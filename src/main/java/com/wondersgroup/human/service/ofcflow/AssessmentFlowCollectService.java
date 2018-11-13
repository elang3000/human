/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月22日 上午9:52:47 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月22日 上午9:52:47 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowCollect;
import com.wondersgroup.human.vo.ofcflow.AssessFlowUnitCollectVO;
import com.wondersgroup.human.vo.ofcflow.AssessmentFlowUnitPercentVO;

/**
 * @ClassName: DeathServantService
 * @Description: 考核计划表service
 * @author: youyd
 * @date: 2018年9月25日上午19:52:47 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public interface AssessmentFlowCollectService extends GenericService<AssessmentFlowCollect> {
	
	/**
	 * 
	 * @Title: createAssessmentDetailByServantListAndCollect 
	 * @Description: 保存AssessmentFlowCollect,并且生成AssessmentDetail的记录
	 * @param servantList
	 * @param collect
	 * @return: void
	 */
	public void createCollectAndAssessmentDetail(AssessmentFlowCollect collect);

	/**
	 * 
	 * @Title: getUnitAssessProgress 
	 * @Description: 获取各单位考核详情
	 * @param assessCollectId
	 * @return
	 * @return: Page<AssessProgressVO>
	 */
	public Page<AssessmentFlowUnitPercentVO> getUnitAssessProgress(String assessCollectId,String unitName,Integer page,Integer limit);

	/**
	 * 
	 * @Title: getCollectAndFlowStatus 
	 * @Description: 查询collect一级当前单位的流程状态
	 * @param org
	 * @param page
	 * @param limit
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	public Page<AssessFlowUnitCollectVO> getCollectAndFlowStatus(OrganNode org,Integer year, Integer page, Integer limit);
}
