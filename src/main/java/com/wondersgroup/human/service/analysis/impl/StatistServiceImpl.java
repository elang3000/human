/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: StatistServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.analysis.impl
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年9月13日 下午5:11:37
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年9月13日 下午5:11:37
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.service.analysis.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.util.DateUtils;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.repository.ofc.ServantRepository;
import com.wondersgroup.human.service.analysis.StatistService;

/**
 * @ClassName: StatistServiceImpl
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年9月13日 下午5:11:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class StatistServiceImpl implements StatistService {
	
	@Autowired
	ServantRepository servantRepository;
	
	@Autowired
	DictableService dictableService;
	
	/**
	 * @see com.wondersgroup.human.service.analysis.StatistService#statistServantYears(java.util.List)
	 */
	@Override
	public Map<String, Integer> statistServantYears(List<String> organNodeIds) {
		
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		Map<String, Integer> result = new LinkedHashMap<String, Integer>();
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.YEAR, -20);
		Integer year = calender.get(Calendar.YEAR);
		for (Integer index = 0; index < 5; index++) {
			year = calender.get(Calendar.YEAR);
			Integer startYear = new BigDecimal(year).setScale(-1, BigDecimal.ROUND_DOWN).intValue();
			Integer endYear = new BigDecimal(year).setScale(-1, BigDecimal.ROUND_UP).intValue();
			if (startYear == endYear) {
				startYear -= 10;
			}
			Integer count = statistServantYears(organNodeIds, isOnHold, DateUtils.getYearFirst(startYear),
			        DateUtils.getYearLast(endYear));
			result.put(startYear + "-" + endYear, count);
			calender.add(Calendar.YEAR, -10);
		}
		return result;
	}
	
	private Integer statistServantYears(List<String> organNodeIds, CodeInfo isOnHold, Date startTime, Date endTime) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
		detachedCriteria.add(Restrictions.eq("isOnHold", isOnHold));
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.in("departId", organNodeIds));
		detachedCriteria.add(Restrictions.between("birthDate", startTime, endTime));
		// detachedCriteria.setProjection(Projections.rowCount());
		List<Servant> servants = servantRepository.findByCriteria(detachedCriteria);
		return servants.size();
	}
	
	/**
	 * @see com.wondersgroup.human.service.analysis.StatistService#statistServantTopEducation(java.util.List)
	 */
	@Override
	public Map<String, Integer> statistServantTopEducation(List<String> organNodeIds) {
		
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		return servantRepository.statistServantTopEducation(organNodeIds, isOnHold.getId());
	}
}
