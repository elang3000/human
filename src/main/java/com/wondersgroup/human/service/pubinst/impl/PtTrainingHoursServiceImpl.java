/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainingHoursServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月13日 下午5:05:03 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月13日 下午5:05:03 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.pubinst.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.pubinst.PtTrainingHours;
import com.wondersgroup.human.repository.pubinst.PtTrainingHoursRepository;
import com.wondersgroup.human.service.pubinst.PtTrainingHoursService;
import com.wondersgroup.human.vo.pubinst.PtTrainingHoursVO;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/**
 * @ClassName: TrainingHoursServiceImpl
 * @Description: 培训 服务实现类
 * @author: lihao
 * @date: 2018年6月13日下午5:05:03 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Service
public class PtTrainingHoursServiceImpl extends GenericServiceImpl<PtTrainingHours> implements PtTrainingHoursService {

	@Autowired
	private PtTrainingHoursRepository trainingHoursRepository;

	/**
	 * @see com.wondersgroup.human.service.ofc.TrainingHoursService#queryTrainingHours(java.util.Map,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtTrainingHoursVO> queryTrainingHours(Map<String, Object> filter, Integer pageNumber, Integer pageSize) {
		return trainingHoursRepository.queryTrainingHours(filter, pageNumber, pageSize);
	}

	/**
	 * @see com.wondersgroup.human.service.ofc.TrainingHoursService#getPage(java.lang.String)
	 */
	@Override
	public Page<PtTrainingHoursVO> getPage(String trainId) {
		return trainingHoursRepository.queryTrainingHours(trainId);
	}

	/**
	 * @see com.wondersgroup.human.service.ofc.TrainingHoursService#getPage(java.util.List,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PublicInstitutionVO> getPage(Map<String, Object> filter, Integer page, Integer limit) {
		return trainingHoursRepository.queryTrainingHoursServantList(filter, page, limit);
	}
}
