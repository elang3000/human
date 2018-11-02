/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: SpecialCountServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.analysis.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月1日 上午10:35:04 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月1日 上午10:35:04 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.analysis.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.repository.ofc.RewardAndPunishRepository;
import com.wondersgroup.human.service.analysis.SpecialCountService;
import com.wondersgroup.human.vo.analysis.RewardCountVO;

/** 
 * @ClassName: SpecialCountServiceImpl 
 * @Description: 专项统计服务类实现
 * @author: lihao
 * @date: 2018年11月1日 上午10:35:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class SpecialCountServiceImpl implements SpecialCountService {

	@Autowired
	RewardAndPunishRepository rewardAndPunishRepository;
	
	/** 
	 * @see com.wondersgroup.human.service.ofc.RewardAndPunishService#getRewardCount(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<RewardCountVO> getRewardCount(String departName, Integer year, Integer page, Integer limit) {
		return rewardAndPunishRepository.getRewardCount(departName, year, page, limit);
	}

	/**
	 * @see com.wondersgroup.human.service.analysis.SpecialCountService#getRewardCountByDepartId(java.lang.String, java.lang.Integer) 
	 */
	@Override
	public Map<String, BigDecimal> getRewardCountByDepartId(String departId, Integer year) {
		return rewardAndPunishRepository.getRewardCountByDepartId(departId,year);
	}
}
