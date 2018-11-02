package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowUnitPercent;

public interface AssessmentFlowUnitPercentService extends GenericService<AssessmentFlowUnitPercent>{
	
	/**
	 * 
	 * @Title: getByCollectAndOrg 
	 * @Description: 通过collectId和单位获取AssessmentFlowUnitPercent
	 * @param collectId
	 * @param org
	 * @return
	 * @return: AssessmentFlowUnitPercent
	 */
	public AssessmentFlowUnitPercent getByCollectAndOrg(String collectId,OrganNode org);

	public void updateDataAndFlow(AssessmentFlowUnitPercent percent, String opinion, String result);

	/**
	 * 
	 * @Title: getPercentByCollect 
	 * @Description: 获取考核单下所有单位的信息
	 * @param collectId
	 * @return
	 * @return: List<AssessmentFlowUnitPercent>
	 */
	public List<AssessmentFlowUnitPercent> getPercentByCollect(String collectId);

	/**
	 * 
	 * @Title: getFinishPercentByCollect 
	 * @Description: 获取考核单下已经考核完成单位信息
	 * @param collectId
	 * @return
	 * @return: List<AssessmentFlowUnitPercent>
	 */
	public List<AssessmentFlowUnitPercent> getFinishPercentByCollect(String collectId);
	
}
