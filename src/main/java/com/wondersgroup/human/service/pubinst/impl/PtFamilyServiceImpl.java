/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: FamilyServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-家庭 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年8月20日11:07:09
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月20日11:07:26
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
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.pubinst.PtFamily;
import com.wondersgroup.human.repository.pubinst.PtFamilyRepository;
import com.wondersgroup.human.service.pubinst.PtFamilyService;
import com.wondersgroup.human.vo.pubinst.PtFamilyVO;

/**
 * @ClassName: DegreeServiceImpl
 * @Description: 人员信息子表-家庭 服务实现类
 * @author: jiang
 * @date: 2018年8月20日11:07:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtFamilyServiceImpl extends GenericServiceImpl<PtFamily> implements PtFamilyService {
	
	@Autowired
	private PtFamilyRepository familyRepository;
	
	@Autowired
	private DictableService dictableService;
	
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
	public Page<PtFamilyVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtFamily> familyPage = familyRepository.findByFilter(filter, sort, page, limit);
		List<PtFamilyVO> voList = new ArrayList<>();
		for (PtFamily e : familyPage.getResult()) {
			PtFamilyVO vo = new PtFamilyVO(e);
			voList.add(vo);
		}
		return new Page<>(familyPage.getStart(), familyPage.getCurrentPageSize(), familyPage.getTotalSize(),
				familyPage.getPageSize(), voList);
	}
	
}
