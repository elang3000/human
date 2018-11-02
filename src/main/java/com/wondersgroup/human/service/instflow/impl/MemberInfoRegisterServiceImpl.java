/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 招录计划 服务实现类
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:13:23 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:13:23 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.instflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.repository.instflow.MemberInfoRegisterRepository;
import com.wondersgroup.human.service.instflow.MemberInfoRegisterService;
import com.wondersgroup.human.vo.instflow.MemberInfoRegisterVO;

/** 
 * @ClassName: RecruitEmployPlanServiceImpl 
 * @Description: 人员信息登记
 * @author: lyf
 * @date: 2018年5月28日 上午11:13:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class MemberInfoRegisterServiceImpl  extends GenericServiceImpl<MemberInfoRegister> implements MemberInfoRegisterService{
	
	@Autowired
	MemberInfoRegisterRepository memberInfoRegisterRepository;

	@Override
	public MemberInfoRegister findMemberInfoRegisterByPid(String id) {
		return memberInfoRegisterRepository.findMemberInfoRegisterByPid(id);
	}
	
	/**
	 * (non Javadoc) 
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return 
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<MemberInfoRegisterVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		Page<MemberInfoRegister> memberInfoRegisterPage = memberInfoRegisterRepository.findByFilter(arg0,arg1,arg2,arg3);
		List<MemberInfoRegisterVO> voList = new ArrayList<>();
		for(MemberInfoRegister p:memberInfoRegisterPage.getResult()){
			MemberInfoRegisterVO vo = new MemberInfoRegisterVO(p);
			voList.add(vo);
		}
		Page<MemberInfoRegisterVO> page = new Page<>(memberInfoRegisterPage.getStart(), memberInfoRegisterPage.getCurrentPageSize(), memberInfoRegisterPage.getTotalSize(), memberInfoRegisterPage.getPageSize(), voList);
		return page;
	}

}
