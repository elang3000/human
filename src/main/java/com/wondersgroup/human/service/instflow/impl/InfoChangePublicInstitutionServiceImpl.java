package com.wondersgroup.human.service.instflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.instflow.InfoChangePublicInstitution;
import com.wondersgroup.human.repository.instflow.InfoChangePublicInstitutionRepository;
import com.wondersgroup.human.service.instflow.InfoChangePublicInstitutionService;
import com.wondersgroup.human.vo.instflow.InfoChangePublicInstitutionVO;

@Service
public class InfoChangePublicInstitutionServiceImpl extends GenericServiceImpl<InfoChangePublicInstitution> implements InfoChangePublicInstitutionService{

	@Autowired
	private InfoChangePublicInstitutionRepository infoChangePublicInstitutionRepository;
	
	
	
	/*
	 * 
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0 查询条件
	 * @param arg1 排序规则
	 * @param arg2 页码
	 * @param arg3 页大小
	 * @return
	 */
	@Override
	public Page<InfoChangePublicInstitutionVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {

		Page<InfoChangePublicInstitution> infoPage= infoChangePublicInstitutionRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<InfoChangePublicInstitutionVO> infoList= new ArrayList<>();
		for (InfoChangePublicInstitution i : infoPage.getResult()) {
			InfoChangePublicInstitutionVO vo =new InfoChangePublicInstitutionVO(i);
			infoList.add(vo);
		}
		
		Page<InfoChangePublicInstitutionVO> page =new Page<>(infoPage.getStart(), infoPage.getCurrentPageSize(), infoPage.getTotalSize(), infoPage.getPageSize(), infoList);
		
		return page;
	}

}
