package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.JobShiftDepose;

public interface JobShiftDeposeService extends GenericService<JobShiftDepose>{

	
	/**
	 * 
	 * @Title: updateDeposeFlow 
	 * @Description: 职务变动免职流程操作
	 * @param jobShift
	 * @param opinion
	 * @param result
	 * @return: void
	 */
	public void updateDeposeFlow(JobShiftDepose jobShift, String opinion, String result);

	
}
