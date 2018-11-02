package com.wondersgroup.human.service.company;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.company.CyOutMgr;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.vo.company.NcOutMgrVo;
import com.wondersgroup.human.vo.socialworker.SocialWorkerVO;

public interface NcOutMgrService extends GenericService<CyOutMgr>{

	/*
	 * 批量保存
	 */
	public void saveBatchDraftSocialWorker(List<NationalCompany> list);
	
	
	/*
	@Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return
	 * @return: Page<ServantVO>
	*/
	public Page<NcOutMgrVo> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);

	/*
	 * 导入国企人员信息
	 */
	public String saveImportRecord(MultipartFile file,int parseInt) throws Exception;
	
}
