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
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.pubinst.PtOutMgr;
import com.wondersgroup.human.repository.pubinst.PtOutMgrRepository;
import com.wondersgroup.human.service.pubinst.PtOutMgrService;
import com.wondersgroup.human.vo.pubinst.PtOutMgrVO;

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
public class PtOutMgrServiceImpl extends GenericServiceImpl<PtOutMgr> implements PtOutMgrService{
	@Autowired
	private PtOutMgrRepository outMgrRepository;
	
	
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
	public Page<PtOutMgrVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtOutMgr> outMgrPage = outMgrRepository.findByFilter(filter, sort, page, limit);
		List<PtOutMgrVO> voList = new ArrayList<>();
		for (PtOutMgr e : outMgrPage.getResult()) {
			PtOutMgrVO vo = new PtOutMgrVO(e);
			voList.add(vo);
		}
		return new Page<>(outMgrPage.getStart(), outMgrPage.getCurrentPageSize(), outMgrPage.getTotalSize(),
				outMgrPage.getPageSize(), voList);
	}
}
