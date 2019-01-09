package com.wondersgroup.human.service.instflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.instflow.InformationChange;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.instflow.InformationChangesQuery;
import com.wondersgroup.human.vo.instflow.InformationChangesVO;

public interface InformationChangeService extends GenericService<InformationChange>{

	//根据主表外键id查询
	public InformationChange findInformationChangeByPid(String id);

	
	
	Page<InformationChangesVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);
	
	
	//审批人员登记
	public void saveRegister(InformationChange temp, String opinion, String r, String planState);

	
	Page<InformationChangesVO> pageList(InformationChangesQuery infor, Integer page, Integer limit);


	/**
	 * 判断是否已在处理流程中
	 * @param publicInst  人员信息对象
	 * @return boolean
	 */
	public boolean operationPublicFlag(PublicInstitution publicInst);

}
