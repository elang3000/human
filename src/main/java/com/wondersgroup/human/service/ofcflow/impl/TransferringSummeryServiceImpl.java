/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TransferringSummeryServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 选调交流汇总信息 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月16日 上午11:16:01 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月16日 上午11:16:01 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.TransferringSummery;
import com.wondersgroup.human.repository.ofcflow.TransferringSummeryRepository;
import com.wondersgroup.human.service.ofcflow.TransferringSummeryService;
import com.wondersgroup.human.vo.ofcflow.TransferringSummeryVO;

/** 
 * @ClassName: TransferringSummeryServiceImpl 
 * @Description: 选调交流汇总信息 服务实现类
 * @author: tzy
 * @date: 2018年8月16日 上午11:16:01
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TransferringSummeryServiceImpl extends GenericServiceImpl<TransferringSummery> implements TransferringSummeryService{
	
	@Autowired
	private TransferringSummeryRepository summeryRepository;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0 查询条件
	 * @param arg1 排序规则
	 * @param arg2 页码
	 * @param arg3 页大小
	 * @return
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<TransferringSummeryVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3){
		Page<TransferringSummery> postPage = summeryRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<TransferringSummeryVO> voList = new ArrayList<>();
		for (TransferringSummery s : postPage.getResult()) {
			TransferringSummeryVO vo = new TransferringSummeryVO(s);
			voList.add(vo);
		}
		Page<TransferringSummeryVO> page = new Page<>(postPage.getStart(), postPage.getCurrentPageSize(), postPage.getTotalSize(),
				postPage.getPageSize(), voList);
		return page;
	}
}
