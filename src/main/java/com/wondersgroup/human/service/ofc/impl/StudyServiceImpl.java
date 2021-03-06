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
import com.wondersgroup.human.bo.ofc.Degree;
import com.wondersgroup.human.bo.ofc.Study;
import com.wondersgroup.human.repository.ofc.StudyRepository;
import com.wondersgroup.human.service.ofc.StudyService;
import com.wondersgroup.human.vo.ofc.DegreeVO;
import com.wondersgroup.human.vo.ofc.StudyVO;

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
public class StudyServiceImpl extends GenericServiceImpl<Study> implements StudyService {
	
	@Autowired
	private StudyRepository studyRepository;
	
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
	public Page<StudyVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<Study> studyPage = studyRepository.findByFilter(filter, sort, page, limit);
		List<StudyVO> voList = new ArrayList<>();
		for (Study e : studyPage.getResult()) {
			StudyVO vo = new StudyVO(e);
			voList.add(vo);
		}
		return new Page<>(studyPage.getStart(), studyPage.getCurrentPageSize(), studyPage.getTotalSize(),
				studyPage.getPageSize(), voList);
	}
	
}
