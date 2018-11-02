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
package com.wondersgroup.human.service.socialworker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.socialworker.SrExperience;
import com.wondersgroup.human.bo.socialworker.SrPost;
import com.wondersgroup.human.repository.socialworker.SwExperienceRepository;
import com.wondersgroup.human.service.socialworker.SwExperienceService;
import com.wondersgroup.human.vo.socialworker.SwExperienceVO;

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
public class SwExperienceServiceImpl extends GenericServiceImpl<SrExperience> implements SwExperienceService {
	
	@Autowired
	private SwExperienceRepository swexperienceRepository;
	
	@Autowired
	private SwExperienceService swExperienceService;
	
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
	public Page<SwExperienceVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<SrExperience> experiencePage = swexperienceRepository.findByFilter(filter, sort, page, limit);
		List<SwExperienceVO> voList = new ArrayList<>();
		for (SrExperience e : experiencePage.getResult()) {
			SwExperienceVO vo = new SwExperienceVO(e);
			voList.add(vo);
		}
		return new Page<>(experiencePage.getStart(), experiencePage.getCurrentPageSize(), experiencePage.getTotalSize(),
				experiencePage.getPageSize(), voList);
	}


	@Override
	public void SaveOrUpdateExperienceBypost(SrPost post) {
		SrExperience ptex = new SrExperience();
		String postName = dict.loadCodeInfoById(post.getPostCode().getId()).getName();
		ptex.setCreater(post.getCreater());
		ptex.setCreateTime(post.getCreateTime());
		ptex.setStartDate(post.getApprovalDate());
		ptex.setRemoved(false);
		//ptex.setPublicInstitution(post.getPublicInstitution());
		ptex.setSocialWorker(post.getSocialWorker());
		ptex.setFormerJob(postName);
		ptex.setFormerJobCategory(post.getAttribute());
		ptex.setFormerUnit(post.getTenureName());
		DictUtils.operationCodeInfo(ptex);
		swExperienceService.saveOrUpdate(ptex);
	}
	
}
