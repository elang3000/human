/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: IntoMgrServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-调入情况 服务实现类
 * 创建人: tzy
 * 创建时间: 2018年7月30日 上午10:34:04
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月30日 上午10:34:04
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
import com.wondersgroup.human.bo.pubinst.PtIntoMgr;
import com.wondersgroup.human.repository.pubinst.PtIntoMgrRepository;
import com.wondersgroup.human.service.pubinst.PtIntoMgrService;
import com.wondersgroup.human.vo.pubinst.PtIntoMgrVO;

/**
 * @ClassName: IntoMgrServiceImpl
 * @Description: 人员信息子表-调入情况 服务实现类
 * @author: tzy
 * @date: 2018年7月30日 上午10:34:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtIntoMgrServiceImpl extends GenericServiceImpl<PtIntoMgr> implements PtIntoMgrService {
	
	@Autowired
	private PtIntoMgrRepository intoMgrRepository;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.IntoMgrService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtIntoMgrVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtIntoMgr> intoMgrPage = intoMgrRepository.findByFilter(filter, sort, page, limit);
		List<PtIntoMgrVO> voList = new ArrayList<>();
		for (PtIntoMgr e : intoMgrPage.getResult()) {
			PtIntoMgrVO vo = new PtIntoMgrVO(e);
			voList.add(vo);
		}
		return new Page<>(intoMgrPage.getStart(), intoMgrPage.getCurrentPageSize(), intoMgrPage.getTotalSize(),
				intoMgrPage.getPageSize(), voList);
	}
	
}
