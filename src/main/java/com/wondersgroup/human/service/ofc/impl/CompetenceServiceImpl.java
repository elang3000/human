/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: CompetenceServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-专业技术资格 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年8月21日09:48:16
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月21日09:48:18
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
import com.wondersgroup.human.bo.ofc.Competence;
import com.wondersgroup.human.bo.ofc.Degree;
import com.wondersgroup.human.repository.ofc.CompetenceRepository;
import com.wondersgroup.human.service.ofc.CompetenceService;
import com.wondersgroup.human.vo.ofc.CompetenceVO;

/**
 * @ClassName: CompetenceServiceImpl
 * @Description: 人员信息子表-专业技术资格 服务实现类
 * @author: jiang
 * @date: 2018年8月21日09:48:58
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class CompetenceServiceImpl extends GenericServiceImpl<Competence> implements CompetenceService {
	
	@Autowired
	private CompetenceRepository competenceRepository;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.DegreeService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<CompetenceVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<Competence> competencePage = competenceRepository.findByFilter(filter, sort, page, limit);
		List<CompetenceVO> voList = new ArrayList<>();
		for (Competence e : competencePage.getResult()) {
			CompetenceVO vo = new CompetenceVO(e);
			voList.add(vo);
		}
		return new Page<>(competencePage.getStart(), competencePage.getCurrentPageSize(), competencePage.getTotalSize(),
				competencePage.getPageSize(), voList);
	}
	
}
