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
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.pubinst.PtCompetence;
import com.wondersgroup.human.repository.pubinst.PtCompetenceRepository;
import com.wondersgroup.human.service.pubinst.PtCompetenceService;
import com.wondersgroup.human.vo.pubinst.PtCompetenceVO;

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
public class PtCompetenceServiceImpl extends GenericServiceImpl<PtCompetence> implements PtCompetenceService {
	
	@Autowired
	private PtCompetenceRepository competenceRepository;
	
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
	public Page<PtCompetenceVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtCompetence> competencePage = competenceRepository.findByFilter(filter, sort, page, limit);
		List<PtCompetenceVO> voList = new ArrayList<>();
		for (PtCompetence e : competencePage.getResult()) {
			PtCompetenceVO vo = new PtCompetenceVO(e);
			voList.add(vo);
		}
		return new Page<>(competencePage.getStart(), competencePage.getCurrentPageSize(), competencePage.getTotalSize(),
				competencePage.getPageSize(), voList);
	}
	
}
