/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServantRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月22日 上午9:55:42 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月22日 上午9:55:42 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowCollect;
import com.wondersgroup.human.vo.ofcflow.AssessFlowUnitCollectVO;
import com.wondersgroup.human.vo.ofcflow.AssessmentFlowUnitPercentVO;

/**
 * 
 * @ClassName: AssessmentFlowCollectRepository 
 * @Description: 考核计划表DAO
 * @author: youyd
 * @date: 2018年9月25日 下午3:45:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface AssessmentFlowCollectRepository extends GenericRepository<AssessmentFlowCollect> {

	/**
	 * 
	 * @Title: getUnitAssessProgress 
	 * @Description: 查询考核进度情况
	 * @param assessmentFlowCollectId
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<AssessProgressVO>
	 */
	public Page<AssessmentFlowUnitPercentVO> getUnitAssessProgress(String assessmentFlowCollectId,String unitName,Integer page,Integer limit);

	public Page<AssessFlowUnitCollectVO> getCollectAndFlowStatus(OrganNode org, Integer page, Integer limit);
}
