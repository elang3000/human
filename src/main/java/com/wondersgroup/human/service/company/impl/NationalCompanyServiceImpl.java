package com.wondersgroup.human.service.company.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.repository.company.NationalCompanyRepository;
import com.wondersgroup.human.service.company.NationalCompanyService;
import com.wondersgroup.human.vo.company.NationalCompanyVo;

@Service
public class NationalCompanyServiceImpl extends GenericServiceImpl<NationalCompany> implements NationalCompanyService{

	
	@Autowired
	NationalCompanyRepository nationalCompanyRepository;
	
	/*
	 *  @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * (non-Javadoc)
	 * @see com.wondersgroup.human.service.company.NationalCompanyService#getPage(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<NationalCompanyVo> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		Page<NationalCompany> nationalCompanyPage = nationalCompanyRepository.findByFilter(arg0,arg1,arg2,arg3);
		List<NationalCompanyVo> voList = new ArrayList<>();
		for(NationalCompany p:nationalCompanyPage.getResult()){
			NationalCompanyVo vo = new NationalCompanyVo(p);
			voList.add(vo);
		}
		Page<NationalCompanyVo> page = new Page<>(nationalCompanyPage.getStart(), nationalCompanyPage.getCurrentPageSize(), nationalCompanyPage.getTotalSize(), nationalCompanyPage.getPageSize(), voList);
		return page;
	}

}
