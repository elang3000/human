/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EducationServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-学历 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:48:42
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:48:44
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.socialworker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.bo.socialworker.SrEducation;
import com.wondersgroup.human.repository.socialworker.SwEducationRepository;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwEducationService;
import com.wondersgroup.human.vo.socialworker.SwEducationVO;

/**
 * @ClassName: EducationServiceImpl
 * @Description: 人员信息子表-学历 服务实现类
 * @author: jiang
 * @date: 2018年7月2日14:48:54
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class SwEducationServiceImpl extends GenericServiceImpl<SrEducation> implements SwEducationService {
	
	
	@Autowired
	private SwEducationRepository sweducationRepository;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private SocialWorkerService socialWorkerService;
	
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
	public Page<SwEducationVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<SrEducation> educationPage = sweducationRepository.findByFilter(filter, sort, page, limit);
		List<SwEducationVO> voList = new ArrayList<>();
		for (SrEducation e : educationPage.getResult()) {
			SwEducationVO vo = new SwEducationVO(e);
			voList.add(vo);
		}
		return new Page<SwEducationVO>(educationPage.getStart(), educationPage.getCurrentPageSize(), educationPage.getTotalSize(),
				educationPage.getPageSize(), voList);
	}
	
	//重写saveOrUpdate
	@Override
	public void saveOrUpdate(SrEducation entity) {
		
		//获取大专业名称保存到bigProfessionName字段
				if(entity.getProfessionCode()!=null){
					CodeInfo codeInfo = dictableService.loadWithLazy(entity.getProfessionCode().getId(), "parent");
					String firstName = dictableService.loadCodeInfoById(codeInfo.getParent().getParent().getId()).getName().trim();
					entity.setBigProfessionName(firstName);
				}
				
		//若最高学历标识为1，则需要更新公务员中最高学历属性显示
		CodeInfo yesCodeInfo=dictableService.getCodeInfoByCode("1",DictTypeCodeContant.CODE_TYPE_YESNO);
		CodeInfo noCodeInfo=dictableService.getCodeInfoByCode("0",DictTypeCodeContant.CODE_TYPE_YESNO);
		if(entity.getTopFlag().getId().equals(yesCodeInfo.getId())){
			//先重置该公务员下其他所有学历最高标识设为0
			this.executeRestAllTopEducationFlag(entity.getSocialWorker().getId());
			//修改公务员A01表中最高学历和毕业时间输出显示
			SocialWorker social = socialWorkerService.get(entity.getSocialWorker().getId());
			social.setTopEducation(entity.getCode().getId());
			social.setGraduateDate(entity.getGraduateDate());
			socialWorkerService.update(social);
		}else if(entity.getTopFlag().getId().equals(noCodeInfo.getId())){
			//如果该学历和公务员学历名称一致，选择否后，公务员信息表中最高学历设置为null
			SocialWorker social = socialWorkerService.get(entity.getSocialWorker().getId());
			if(StringUtils.isNotBlank(social.getTopEducation())&&social.getTopEducation().equals(entity.getName())){
				social.setTopEducation(null);
				social.setGraduateDate(null);
				socialWorkerService.update(social);
			}
		}
		super.saveOrUpdate(entity);
	}
	
	
	/**
	 * @Title: executeRestAllTopEducationFlag
	 * @Description: 重置该人员下所有学历最高标识
	 * @param Id 
	 * @see com.wondersgroup.human.service.ofc.EducationService#restAllTopEducationFlag(java.lang.String)
	 */
	@Override
	public void executeRestAllTopEducationFlag(String Id) {
		CodeInfo codeInfo=dictableService.getCodeInfoByCode("0",DictTypeCodeContant.CODE_TYPE_YESNO);
		sweducationRepository.updateAllEducationTopTipBySid(Id,codeInfo);
	}

	@Override
	public void delete(SrEducation entity) {
		//如果删除的是最高标识的学历，更新公务员信息表中最高学历字段
		CodeInfo yesCodeInfo=dictableService.getCodeInfoByCode("1",DictTypeCodeContant.CODE_TYPE_YESNO);
		if(entity.getTopFlag().getId().equals(yesCodeInfo.getId())){
			SocialWorker social = socialWorkerService.get(entity.getSocialWorker().getId());
			social.setTopEducation(null);
			social.setGraduateDate(null);
			socialWorkerService.update(social);
		}
		super.delete(entity);
	}
	
	
}
