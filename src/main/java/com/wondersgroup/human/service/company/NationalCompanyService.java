package com.wondersgroup.human.service.company;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.vo.company.NationalCompanyVo;
import com.wondersgroup.human.vo.socialworker.SocialWorkerVO;

public interface NationalCompanyService extends GenericService<NationalCompany>{

	/*
	 * @Title: getPage 
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return
	 * @return: Page<ServantVO>
	 */
	public Page<NationalCompanyVo> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);

}
