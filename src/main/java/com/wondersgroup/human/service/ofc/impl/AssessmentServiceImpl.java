/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: AssessmentServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-考核 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年7月2日10:33:13
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日10:33:15
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.ofc.Assessment;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.repository.ofc.AssessmentRepository;
import com.wondersgroup.human.service.ofc.AssessmentService;
import com.wondersgroup.human.vo.ofc.AssessmentVO;

/**
 * @ClassName: AssessmentServiceImpl
 * @Description: 人员信息子表-录用 服务实现类
 * @author: jiang
 * @date: 2018年7月2日10:33:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class AssessmentServiceImpl extends GenericServiceImpl<Assessment> implements AssessmentService {
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.AssessmentService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<AssessmentVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<Assessment> assessmentPage = assessmentRepository.findByFilter(filter, sort, page, limit);
		List<AssessmentVO> voList = new ArrayList<>();
		for (Assessment e : assessmentPage.getResult()) {
			AssessmentVO vo = new AssessmentVO(e);
			voList.add(vo);
		}
		return new Page<>(assessmentPage.getStart(), assessmentPage.getCurrentPageSize(), assessmentPage.getTotalSize(),
				assessmentPage.getPageSize(), voList);
	}

	@Override
	public boolean isRecent3YearsFine(Servant servant,Integer year) {
//		//考核结论代码DM018  1优秀
//		CodeInfo fineCodeInfo = dictableService.getCodeInfoByCode("1", "DM018");
//		//考核类型代码DM017  11年度考核
//		CodeInfo yearAssessCodeInfo = dictableService.getCodeInfoByCode("11", "DM017");
		return assessmentRepository.isRecent3YearsFine(servant,year);
	}

	@Override
	public boolean isCurrentYearSeasonFine(Servant servant,Integer year) {
		
		return assessmentRepository.isCurrentYearSeasonFine(servant,year);
	}
	
}
