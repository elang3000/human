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
package com.wondersgroup.human.service.pubinst.impl;

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
import com.wondersgroup.human.bo.pubinst.PtEducation;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.repository.pubinst.PtEducationRepository;
import com.wondersgroup.human.service.pubinst.PtEducationService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtEducationVO;

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
public class PtEducationServiceImpl extends GenericServiceImpl<PtEducation> implements PtEducationService {
	
	@Autowired
	private PtEducationRepository educationRepository;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
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
	public Page<PtEducationVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtEducation> educationPage = educationRepository.findByFilter(filter, sort, page, limit);
		List<PtEducationVO> voList = new ArrayList<>();
		for (PtEducation e : educationPage.getResult()) {
			PtEducationVO vo = new PtEducationVO(e);
			voList.add(vo);
		}
		return new Page<PtEducationVO>(educationPage.getStart(), educationPage.getCurrentPageSize(), educationPage.getTotalSize(),
				educationPage.getPageSize(), voList);
	}
	
	//重写saveOrUpdate
	@Override
	public void saveOrUpdate(PtEducation entity) {
		
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
			this.executeRestAllTopEducationFlag(entity.getPublicInstitution().getId());
			//修改公务员A01表中最高学历和毕业时间输出显示
			PublicInstitution pubinst = publicInstitutionService.get(entity.getPublicInstitution().getId());
			pubinst.setTopEducation(entity.getCode().getId());
			pubinst.setGraduateDate(entity.getGraduateDate());
			publicInstitutionService.update(pubinst);
		}else if(entity.getTopFlag().getId().equals(noCodeInfo.getId())){
			//如果该学历和公务员学历名称一致，选择否后，公务员信息表中最高学历设置为null
			PublicInstitution pubinst = publicInstitutionService.get(entity.getPublicInstitution().getId());
			if(StringUtils.isNotBlank(pubinst.getTopEducation())&&pubinst.getTopEducation().equals(entity.getName())){
				pubinst.setTopEducation(null);
				pubinst.setGraduateDate(null);
				publicInstitutionService.update(pubinst);
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
		educationRepository.updateAllEducationTopTipBySid(Id,codeInfo);
	}

	@Override
	public void delete(PtEducation entity) {
		//如果删除的是最高标识的学历，更新公务员信息表中最高学历字段
		CodeInfo yesCodeInfo=dictableService.getCodeInfoByCode("1",DictTypeCodeContant.CODE_TYPE_YESNO);
		if(entity.getTopFlag().getId().equals(yesCodeInfo.getId())){
			PublicInstitution pubinst = publicInstitutionService.get(entity.getPublicInstitution().getId());
			pubinst.setTopEducation(null);
			pubinst.setGraduateDate(null);
			publicInstitutionService.update(pubinst);
		}
		super.delete(entity);
	}
	
	
}
