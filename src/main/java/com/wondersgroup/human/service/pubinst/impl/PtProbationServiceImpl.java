/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ProbationServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-试用 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年6月11日15:50:51
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月11日15:50:53
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.pubinst.PtProbation;
import com.wondersgroup.human.repository.pubinst.PtProbationRepository;
import com.wondersgroup.human.service.pubinst.PtProbationService;
import com.wondersgroup.human.vo.pubinst.PtProbationVO;

/**
 * @ClassName: ProbationServiceImpl
 * @Description: 人员信息子表-录用 服务实现类
 * @author: jiang
 * @date: 2018年6月11日15:51:38
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtProbationServiceImpl extends GenericServiceImpl<PtProbation> implements PtProbationService {
	
	@Autowired
	private PtProbationRepository probationRepository;
	
	/**
	 * (non Javadoc) 
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return 
	 * @see com.wondersgroup.human.service.ofc.ProbationService#getPage(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtProbationVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtProbation> probationPage = probationRepository.findByFilter(filter, sort, page, limit);
		List<PtProbationVO> voList = new ArrayList<>();
		for (PtProbation s : probationPage.getResult()) {
			PtProbationVO vo = new PtProbationVO(s);
			voList.add(vo);
		}
		return new Page<>(probationPage.getStart(), probationPage.getCurrentPageSize(), probationPage.getTotalSize(),
				probationPage.getPageSize(), voList);
	}
	
}
