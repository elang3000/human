/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EmployServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-录用 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年6月1日10:08:23
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月1日10:08:33
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
import com.wondersgroup.human.bo.pubinst.PtEmploy;
import com.wondersgroup.human.repository.pubinst.PtEmployRepository;
import com.wondersgroup.human.service.pubinst.PtEmployService;
import com.wondersgroup.human.vo.pubinst.PtEmployVO;

/**
 * @ClassName: EmployServiceImpl
 * @Description: 人员信息子表-录用 服务实现类
 * @author: jiang
 * @date: 2018年6月1日09:48:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtEmployServiceImpl extends GenericServiceImpl<PtEmploy> implements PtEmployService {
	
	@Autowired
	private PtEmployRepository employRepository;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param object
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.EmployService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtEmployVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtEmploy> employPage = employRepository.findByFilter(filter, sort, page, limit);
		List<PtEmployVO> voList = new ArrayList<>();
		for (PtEmploy s : employPage.getResult()) {
			PtEmployVO vo = new PtEmployVO(s);
			voList.add(vo);
		}
		return new Page<>(employPage.getStart(), employPage.getCurrentPageSize(), employPage.getTotalSize(),
				employPage.getPageSize(), voList);
	}
	
}
