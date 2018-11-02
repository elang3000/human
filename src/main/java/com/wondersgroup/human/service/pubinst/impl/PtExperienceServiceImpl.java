/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ExperienceServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-简历 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年7月2日15:43:59
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日15:44:02
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
import com.wondersgroup.framework.dict.service.impl.DictableServiceImpl;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.pubinst.PtExperience;
import com.wondersgroup.human.bo.pubinst.PtPost;
import com.wondersgroup.human.repository.pubinst.PtExperienceRepository;
import com.wondersgroup.human.service.pubinst.PtExperienceService;
import com.wondersgroup.human.vo.ofc.ExperienceVO;
import com.wondersgroup.human.vo.pubinst.PtExperienceVO;

/**
 * @ClassName: ExperienceServiceImpl
 * @Description: 人员信息子表-简历 服务实现类
 * @author: jiang
 * @date: 2018年7月2日15:51:21
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtExperienceServiceImpl extends GenericServiceImpl<PtExperience> implements PtExperienceService {
	
	@Autowired
	private PtExperienceRepository experienceRepository;
	
	@Autowired
	private PtExperienceService ptExperienceService;
	
	@Autowired
	private DictableService dict;
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.EducationService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtExperienceVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtExperience> experiencePage = experienceRepository.findByFilter(filter, sort, page, limit);
		List<PtExperienceVO> voList = new ArrayList<>();
		for (PtExperience e : experiencePage.getResult()) {
			PtExperienceVO vo = new PtExperienceVO(e);
			voList.add(vo);
		}
		return new Page<>(experiencePage.getStart(), experiencePage.getCurrentPageSize(), experiencePage.getTotalSize(),
				experiencePage.getPageSize(), voList);
	}


	@Override
	public void SaveOrUpdateExperienceBypost(PtPost post) {
		PtExperience ptex = new PtExperience();
		String postName = dict.loadCodeInfoById(post.getPostCode().getId()).getName();
		ptex.setCreater(post.getCreater());
		ptex.setCreateTime(post.getCreateTime());
		ptex.setStartDate(post.getApprovalDate());
		ptex.setRemoved(false);
		ptex.setPublicInstitution(post.getPublicInstitution());
		ptex.setFormerJob(postName);
		ptex.setFormerJobCategory(post.getAttribute());
		ptex.setFormerUnit(post.getTenureName());
		DictUtils.operationCodeInfo(ptex);
		ptExperienceService.saveOrUpdate(ptex);
	}
	
}
