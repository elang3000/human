/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: SocialWorkerServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl 
 * 描述: 人员信息维护服务接口实现类
 * 创建人: lyf   
 * 创建时间: 2018年8月22日 上午11:05:33 
 * 版本号: V1.0
 * 修改人：lyf
 * 修改时间：2018年8月22日 上午11:05:33 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.socialworker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.repository.socialworker.SocialWorkerRepository;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.vo.socialworker.SocialWorkerVO;

/** 
 * @ClassName: SocialWorkerServiceImpl 
 * @Description: 人员信息维护服务接口实现类
 * @author: lyf
 * @date: 2018年5月21日 上午11:05:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class SocialWorkerServiceImpl extends GenericServiceImpl<SocialWorker> implements SocialWorkerService{
	
	@Autowired
	private SocialWorkerRepository socialWorkerRepository;
	
	/**
	 * (non Javadoc) 
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return 
	 * @see com.wondersgroup.human.service.ofc.SocialWorkerService#getPage(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<SocialWorkerVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3){
		Page<SocialWorker> socialWorkerPage = socialWorkerRepository.findByFilter(arg0,arg1,arg2,arg3);
		List<SocialWorkerVO> voList = new ArrayList<>();
		for(SocialWorker p:socialWorkerPage.getResult()){
			SocialWorkerVO vo = new SocialWorkerVO(p);
			voList.add(vo);
		}
		Page<SocialWorkerVO> page = new Page<>(socialWorkerPage.getStart(), socialWorkerPage.getCurrentPageSize(), socialWorkerPage.getTotalSize(), socialWorkerPage.getPageSize(), voList);
		return page;
	}
}
