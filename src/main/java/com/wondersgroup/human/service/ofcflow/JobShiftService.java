package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.JobShift;

import java.util.List;
import java.util.Map;

public interface JobShiftService extends GenericService<JobShift>{

	
	/**
	 * 
	 * @Title: updatePromoteFlow 
	 * @Description: 职务变动升职流程操作
	 * @param jobShift
	 * @param opinion
	 * @param result
	 * @return: void
	 */
	public void updatePromoteFlow(JobShift jobShift, String opinion, String result);
	
	/**
	 * 
	 * @Title: examineDemote 
	 * @Description: 审批职务变动降职,轮岗流程操作
	 * @param jobShift 表单对象
	 * @param opinion 审批意见
	 * @param result 审批结果
	 * @param isShift 是否是轮岗标志
	 * @return: void
	 */
	public void updateDemoteFlow(JobShift jobShift, String opinion, String result,boolean isShift);

	/**
	 * 获取当前单位职务变动表单提交记录数据
	 * @return
	 */
	public Page<Map> getFormRecordData(String orgId, String jobChangeType, String name, Integer page, Integer limit);


	
}
