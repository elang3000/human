package com.wondersgroup.human.service.instflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.instflow.RecordableRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.vo.instflow.RecordableRecordVO;

public interface RecordableRecordService  extends GenericService<RecordableRecord>{

	//根据主标外键id查询
	public RecordableRecord findRecordableRecordByPid(String id);
	
	
	/**
	 * 
	 * @Title: findByFilterVO 
	 * @Description: 列表分页
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @return: Page<RecruitEmployPlanVO>
	 */
	Page<RecordableRecordVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);
	
	
	//审批人员登记
	public void saveRegister(RecordableRecord temp, String opinion, String r, String planState);


	/**
	 * 判断是否已在处理流程中
	 * @param publicInst  人员信息对象
	 * @return boolean
	 */
	public boolean operationPublicFlag(PublicInstitution publicInstitution);

}
