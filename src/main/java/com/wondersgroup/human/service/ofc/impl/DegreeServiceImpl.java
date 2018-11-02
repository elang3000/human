/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DegreeServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-学位 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:22:16
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:22:19
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.ofc.Degree;
import com.wondersgroup.human.bo.ofc.Education;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.repository.ofc.DegreeRepository;
import com.wondersgroup.human.service.ofc.DegreeService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.DegreeVO;

/**
 * @ClassName: DegreeServiceImpl
 * @Description: 人员信息子表-学位 服务实现类
 * @author: jiang
 * @date: 2018年7月2日14:22:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class DegreeServiceImpl extends GenericServiceImpl<Degree> implements DegreeService {
	
	@Autowired
	private DegreeRepository degreeRepository;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private ServantService servantService;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.DegreeService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<DegreeVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<Degree> degreePage = degreeRepository.findByFilter(filter, sort, page, limit);
		List<DegreeVO> voList = new ArrayList<>();
		for (Degree e : degreePage.getResult()) {
			DegreeVO vo = new DegreeVO(e);
			voList.add(vo);
		}
		return new Page<>(degreePage.getStart(), degreePage.getCurrentPageSize(), degreePage.getTotalSize(),
				degreePage.getPageSize(), voList);
	}
	
	@Override
	public void saveOrUpdate(Degree entity) {

		// 若最高学历标识为1，则需要更新公务员中最高学位属性显示
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		Servant servant = servantService.get(entity.getServant().getId());
		if (entity.getTopFlag().getId().equals(yesCodeInfo.getId())) {
			// 先重置该公务员下其他所有学位最高标识设为0
			this.executeRestAllTopDegreeFlag(entity.getServant().getId());
			// 修改公务员A01表中最高学位的输出显示
			servant.setTopDegree(entity.getName());
			servant.setTopDegreeCode(entity.getCode());
			servantService.update(servant);
		} else if (entity.getTopFlag().getId().equals(noCodeInfo.getId())) {
			// 如果该学历和公务员学位名称一致，选择否后，公务员信息表中最高学位设置为null
			if (StringUtils.isNotBlank(servant.getTopDegree()) && servant.getTopDegree().equals(entity.getName())) {
				servant.setTopDegree(null);
				servant.setTopDegreeCode(null);
				servantService.update(servant);
			}
		}
		
		super.saveOrUpdate(entity);
	}
	
	/**
	 * @Title: executeRestAllTopDegreeFlag
	 * @Description: 重置该人员下所有学位的最高标识
	 * @param servantId
	 * @see com.wondersgroup.human.service.ofc.EducationService#restAllTopEducationFlag(java.lang.String)
	 */
	@Override
	public void executeRestAllTopDegreeFlag(String servantId) {
		
		CodeInfo codeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		degreeRepository.updateServantAllDegreeTopTipBySid(servantId, codeInfo);
	}
	
	@Override
	public void delete(Degree entity) {
		
		// 如果删除的是最高标识的学历，更新公务员信息表中最高学历字段
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		if (entity.getTopFlag().getId().equals(yesCodeInfo.getId())) {
			Servant servant = servantService.get(entity.getServant().getId());
			servant.setTopDegree(null);
			servantService.update(servant);
		}
		super.delete(entity);
	}
}
