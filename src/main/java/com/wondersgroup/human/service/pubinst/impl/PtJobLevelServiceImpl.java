/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: JobLevelServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-职级 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年6月12日10:15:37
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月12日10:15:41
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.pubinst.PtJobLevel;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.repository.pubinst.PtJobLevelRepository;
import com.wondersgroup.human.service.pubinst.PtJobLevelService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtJobLevelVO;

/**
 * @ClassName: JobLevelServiceImpl
 * @Description: 人员信息子表-职级 服务实现类
 * @author: jiang
 * @date: 2018年6月12日10:18:50
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtJobLevelServiceImpl extends GenericServiceImpl<PtJobLevel> implements PtJobLevelService {
	
	@Autowired
	private PtJobLevelRepository jobLevelRepository;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param object
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.JobLevelService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtJobLevelVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtJobLevel> jobLevelPage = jobLevelRepository.findByFilter(filter, sort, page, limit);
		List<PtJobLevelVO> voList = new ArrayList<>();
		for (PtJobLevel s : jobLevelPage.getResult()) {
			PtJobLevelVO vo = new PtJobLevelVO(s);
			voList.add(vo);
		}
		return new Page<PtJobLevelVO>(jobLevelPage.getStart(), jobLevelPage.getCurrentPageSize(), jobLevelPage.getTotalSize(),
				jobLevelPage.getPageSize(), voList);
	}
	
	@Override
	public void saveOrUpdate(PtJobLevel entity) {
		
		// 若最高学历标识为1，则需要更新公务员中最高学位属性显示
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		PublicInstitution pubinst = publicInstitutionService.get(entity.getPublicInstitution().getId());
		if (entity.getCurrentIdentification().getId().equals(yesCodeInfo.getId())) {
			// 先重置该公务员下其他所有现行职级标识设为0
			this.executeRestAllCurrentLevelFlag(entity.getPublicInstitution().getId());
			// 修改公务员A01表中职级的输出显示
			pubinst.setNowJobLevel(entity.getCode());
			publicInstitutionService.update(pubinst);
		} else if (entity.getCurrentIdentification().getId().equals(noCodeInfo.getId())) {
			// 如果该职级和公务员学位CODE一致，选择否后，公务员信息表中现行职级设置为null
			if (pubinst.getNowJobLevel()!=null && (pubinst.getNowJobLevel().getId()).equals(entity.getCode().getId())) {
				pubinst.setNowJobLevel(null);
				publicInstitutionService.update(pubinst);
			}
		}
		
		super.saveOrUpdate(entity);
	}
	
	/**
	 * @Title: executeRestAllCurrentLevelFlag
	 * @Description: 重置该人员下所有职级的现行标识
	 * @param Id
	 * @see com.wondersgroup.human.service.ofc.EducationService#restAllTopEducationFlag(java.lang.String)
	 */
	@Override
	public void executeRestAllCurrentLevelFlag(String Id) {
		
		CodeInfo codeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		jobLevelRepository.updateAllCurrentLevelBySid(Id, codeInfo);
	}
	
	@Override
	public void delete(PtJobLevel entity) {
		
		// 如果删除的是现行标识的职级，更新公务员信息表中职级字段
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		if (entity.getCurrentIdentification().getId().equals(yesCodeInfo.getId())) {
			PublicInstitution pubinst = publicInstitutionService.get(entity.getPublicInstitution().getId());
			pubinst.setNowJobLevel(null);
			publicInstitutionService.update(pubinst);
		}
		super.delete(entity);
	}
	/** (non Javadoc) 
	 * @Title: getJobLevelByServantId
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.wondersgroup.human.service.ofc.JobLevelService#getJobLevelByServantId(java.lang.String) 
	 */
	@Override
	public PtJobLevel getJobLevelByServantId(String id) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(PtJobLevel.class);
		DetachedCriteria s = detachedcriteria.createAlias("publicInstitution", "s");
		s.add(Restrictions.eq("s.id", id));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.add(Restrictions.eq("currentIdentification", dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO)));
		
		List<PtJobLevel> list = this.findByCriteria(detachedcriteria);
		
		if(list.size()>0){
			return list.get(0);
		}else{
			throw new BusinessException("当前人员没有现行职级!");
		}
	}
}
