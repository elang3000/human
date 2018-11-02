/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: StudyServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-学习（培训、进修） 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年8月21日15:11:26
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月21日15:11:28
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
import com.wondersgroup.human.bo.pubinst.PtStudy;
import com.wondersgroup.human.repository.pubinst.PtStudyRepository;
import com.wondersgroup.human.service.pubinst.PtStudyService;
import com.wondersgroup.human.vo.pubinst.PtStudyVO;

/**
 * @ClassName: StudyServiceImpl
 * @Description: 人员信息子表-学习（培训、进修） 服务实现类
 * @author: jiang
 * @date: 2018年8月21日15:11:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtStudyServiceImpl extends GenericServiceImpl<PtStudy> implements PtStudyService {
	
	@Autowired
	private PtStudyRepository studyRepository;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.StudyService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtStudyVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtStudy> studyPage = studyRepository.findByFilter(filter, sort, page, limit);
		List<PtStudyVO> voList = new ArrayList<>();
		for (PtStudy e : studyPage.getResult()) {
			PtStudyVO vo = new PtStudyVO(e);
			voList.add(vo);
		}
		return new Page<>(studyPage.getStart(), studyPage.getCurrentPageSize(), studyPage.getTotalSize(),
				studyPage.getPageSize(), voList);
	}
	
}
