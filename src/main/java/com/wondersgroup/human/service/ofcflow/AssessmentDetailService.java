package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.human.bo.ofcflow.AssessmentDetail;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowCollect;

public interface AssessmentDetailService extends GenericService<AssessmentDetail>{

	/**
	 * 
	 * @Title: getCurrentUnitDetail 
	 * @Description: 获取当前单位Detail
	 * @param org
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<AssessmentDetail>
	 */
	Page<AssessmentDetail> getCurrentUnitDetailsPage(OrganNode org,String assessCollectId,String resultId,String name, Integer page, Integer limit);

	/**
	 * 
	 * @Title: getCurrentUnitDetails 
	 * @Description: 获取当前单位所有Detail
	 * @param org
	 * @return
	 * @return: List<AssessmentDetail>
	 */
	List<AssessmentDetail> getCurrentUnitDetails(OrganNode org,String assessCollectId);

	/**
	 * 
	 * @Title: complete 
	 * @Description: 完成当前单位的考核单
	 * @param org
	 * @param assessCollectId
	 * @return: void
	 */
	void complete(OrganNode org, AssessmentFlowCollect assessCollect);

	/**
	 * 
	 * @Title: isAssessAll 
	 * @Description: 是否确认考核
	 * @param org
	 * @param assessCollectId
	 * @return
	 * @return: boolean
	 */
	boolean isAssessAll(OrganNode org, String assessCollectId);
	
	/**
	 * 
	 * @Title: isConfirmAssess 
	 * @Description: 是否确认考核
	 * @param org
	 * @param assessCollectId
	 * @return
	 * @return: boolean
	 */
	boolean isConfirmAssess(OrganNode org, String assessCollectId);
	
	/**
	 * 
	 * @Title: getAllAssessStatusDetails 
	 * @Description: 获取当前单位下所有状态为1的人员(已经确认考核完成)
	 * @param org
	 * @param assessCollectId
	 * @return: void
	 */
	List<AssessmentDetail> getAllAssessStatusDetails(OrganNode org, String assessCollectId);
	
	/**
	 * 
	 * @Title: getAllAssessDetails 
	 * @Description: 获取所有已经被考核的人员
	 * @param org
	 * @param assessCollectId
	 * @return
	 * @return: List<AssessmentDetail>
	 */
	List<AssessmentDetail> getAllAssessDetails(OrganNode org, String assessCollectId);
	
	/**
	 * 
	 * @Title: getAllFineDetails 
	 * @Description: 获取所有考核优秀的人员
	 * @param assessCollectId
	 * @return
	 * @return: List<AssessmentDetail>
	 */
	List<AssessmentDetail> getAllFineDetails(String assessCollectId);

	List<AssessmentDetail> getAllDetails(Object object, String collectId);
	
	
}
