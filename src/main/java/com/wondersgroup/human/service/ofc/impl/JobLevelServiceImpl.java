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
package com.wondersgroup.human.service.ofc.impl;

import java.io.Serializable;
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
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.repository.ofc.JobLevelRepository;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.vo.ofc.JobLevelVO;

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
public class JobLevelServiceImpl extends GenericServiceImpl<JobLevel> implements JobLevelService {
	
	@Autowired
	private JobLevelRepository jobLevelRepository;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@Autowired
	private FormationControlService formationControlService;
	
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
	public Page<JobLevelVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<JobLevel> jobLevelPage = jobLevelRepository.findByFilter(filter, sort, page, limit);
		List<JobLevelVO> voList = new ArrayList<>();
		for (JobLevel s : jobLevelPage.getResult()) {
			JobLevelVO vo = new JobLevelVO(s);
			voList.add(vo);
		}
		return new Page<>(jobLevelPage.getStart(), jobLevelPage.getCurrentPageSize(), jobLevelPage.getTotalSize(),
				jobLevelPage.getPageSize(), voList);
	}
	
	private void updateServantByPostLvl(JobLevel entity) {
		
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		Servant servant = servantService.get(entity.getServant().getId());
		if (entity.getCurrentIdentification().getId().equals(yesCodeInfo.getId())) {
			// 先重置该公务员下其他所有现行职级标识设为0
			this.executeRestAllCurrentLevelFlag(entity.getServant().getId());
			// 修改公务员A01表中职级的输出显示
			servant.setNowJobLevel(entity.getCode());
			servantService.update(servant);
		} else if (entity.getCurrentIdentification().getId().equals(noCodeInfo.getId())) {
			// 如果该职级和公务员职级CODE一致，选择否后，公务员信息表中现行职级设置为null
			if (servant.getNowJobLevel() != null
					&& (servant.getNowJobLevel().getId()).equals(entity.getCode().getId())) {
				servant.setNowJobLevel(null);
				servantService.update(servant);
			}
		}
	}
	
	/**
	 * @Title: executeRestAllCurrentLevelFlag
	 * @Description: 重置该人员下所有职级的现行标识
	 * @param servantId
	 * @see com.wondersgroup.human.service.ofc.EducationService#restAllTopEducationFlag(java.lang.String)
	 */
	@Override
	public void executeRestAllCurrentLevelFlag(String servantId) {
		
		CodeInfo codeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		jobLevelRepository.updateServantAllCurrentLevelBySid(servantId, codeInfo);
	}
	
	@Override
	public void update(JobLevel entity) {
		
		updateServantByPostLvl(entity);
		super.update(entity);
	}
	
	@Override
	public Serializable save(JobLevel entity) {
		
		Servant servant = servantService.get(entity.getServant().getId());
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		if (entity.getCurrentIdentification().getId().equals(yesCodeInfo.getId())) {
			// 如果是现行职级，那么就入职级编制数
			CodeInfo postLvlCode = codeInfoService.get(entity.getCode().getId());
			formationControlService.executeIntoPost(servant.getDepartId(), postLvlCode.getCode(),
					entity.getIsLowToHigh());
		}
		updateServantByPostLvl(entity);
		return super.save(entity);
	}
	
	@Override
	public void delete(JobLevel entity) {
		
		// 如果删除的是现行标识的职级，更新公务员信息表中职级字段
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		if (entity.getCurrentIdentification().getId().equals(yesCodeInfo.getId())) {
			Servant servant = servantService.get(entity.getServant().getId());
			servant.setNowJobLevel(null);
			servantService.update(servant);
		}
		if (entity.getCurrentIdentification().getId().equals(yesCodeInfo.getId())) {
			// 如果是现行职级，那么就出职级编制数
			CodeInfo postlvlCode = codeInfoService.get(entity.getCurrentIdentification().getId());
			formationControlService.executeOutPost(entity.getServant().getDepartId(), postlvlCode.getCode(),
					entity.getIsLowToHigh());
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
	public JobLevel getJobLevelByServantId(String id) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(JobLevel.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		s.add(Restrictions.eq("s.id", id));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.add(Restrictions.eq("currentIdentification", dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO)));
		
		List<JobLevel> list = this.findByCriteria(detachedcriteria);
		
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
