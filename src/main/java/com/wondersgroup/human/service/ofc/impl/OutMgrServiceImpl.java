/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OutMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl 
 * 描述: 人员信息子表-调出情况 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月30日 上午10:40:37 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月30日 上午10:40:37 
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
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.repository.ofc.OutMgrRepository;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.vo.ofc.OutMgrVO;

/** 
 * @ClassName: OutMgrServiceImpl 
 * @Description: 人员信息子表-调出情况 服务实现类
 * @author: tzy
 * @date: 2018年7月30日 上午10:40:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class OutMgrServiceImpl extends GenericServiceImpl<OutMgr> implements OutMgrService{
	@Autowired
	private OutMgrRepository outMgrRepository;
	
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.OutMgrService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<OutMgrVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<OutMgr> outMgrPage = outMgrRepository.findByFilter(filter, sort, page, limit);
		List<OutMgrVO> voList = new ArrayList<>();
		for (OutMgr e : outMgrPage.getResult()) {
			OutMgrVO vo = new OutMgrVO(e);
			voList.add(vo);
		}
		return new Page<>(outMgrPage.getStart(), outMgrPage.getCurrentPageSize(), outMgrPage.getTotalSize(),
				outMgrPage.getPageSize(), voList);
	}
}
