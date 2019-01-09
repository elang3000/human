package com.wondersgroup.human.service.ofcflow;

import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.JobShiftB;
import com.wondersgroup.human.vo.ofcflow.JobShiftBVO;

public interface JobShiftBService extends GenericService<JobShiftB>{

	
	/**
	 * 
	 * @Title: updatePromoteFlow 
	 * @Description: 职务变动升职流程操作
	 * @param JobShiftB
	 * @param opinion
	 * @param result
	 * @return: void
	 */
	public void updatePromoteFlow(JobShiftB JobShiftB, String opinion, String result);
	
	/**
	 * 
	 * @Title: examineDemote 
	 * @Description: 审批职务变动降职,轮岗流程操作
	 * @param JobShiftB 表单对象
	 * @param opinion 审批意见
	 * @param result 审批结果
	 * @param isShift 是否是轮岗标志
	 * @return: void
	 */
	public void updateDemoteFlow(JobShiftB JobShiftB, String opinion, String result,boolean isShift);

	/**
	 * 获取当前单位职务变动表单提交记录数据
	 * @return
	 */
	public Page<Map> getFormRecordData(String orgId, String jobChangeType, String name, Integer page, Integer limit);


	/**
	 * 
	 * @param servantId
	 * @return
	 */
    public List<String> getHandledPostIds(String servantId);

    /**
     * 
     * @Title: getShiftByCollectId 
     * @Description: 根据collectid获取职务变动列表
     * @param id
     * @return
     * @return: Page<JobShiftBVO>
     */
	public Page<JobShiftBVO> getShiftByCollectId(String id,Integer page, Integer limit);


	List<JobShiftB> getShiftByCollectId(String id);
}
