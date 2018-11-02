/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RewardAndPunishServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: TODO
 * 创建人: lihao
 * 创建时间: 2018年7月12日 上午11:03:03
 * 版本号: V1.0
 * 修改人：lihao
 * 修改时间：2018年7月12日 上午11:03:03
 * 修改任务号
 * 修改内容：TODO
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
import com.wondersgroup.human.bo.socialworker.SrRewardAndPunish;
import com.wondersgroup.human.repository.socialworker.SwRewardAndPunishRepository;
import com.wondersgroup.human.service.socialworker.SwRewardAndPunishService;
import com.wondersgroup.human.vo.socialworker.SwPunishVO;
import com.wondersgroup.human.vo.socialworker.SwRewardVO;

/**
 * @ClassName: RewardAndPunishServiceImpl
 * @Description: 奖惩服务接口实现
 * @author: lihao
 * @date: 2018年7月12日 上午11:03:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class SwRewardAndPunishServiceImpl extends GenericServiceImpl<SrRewardAndPunish> implements SwRewardAndPunishService {
	
	@Autowired
	private SwRewardAndPunishRepository rewardAndPunishRepository;
	
	/**
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<SwPunishVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		
		Page<SrRewardAndPunish> punishPage = rewardAndPunishRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<SwPunishVO> voList = new ArrayList<>();
		List list = punishPage.getResult();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof SrRewardAndPunish) {
				SrRewardAndPunish r = (SrRewardAndPunish) list.get(i);
				SwPunishVO vo = new SwPunishVO(r);
				voList.add(vo);
			} else {
				Object[] o = (Object[]) list.get(0);
				SrRewardAndPunish r = (SrRewardAndPunish) o[1];
				SwPunishVO vo = new SwPunishVO(r);
				voList.add(vo);
			}
		}
		Page<SwPunishVO> page = new Page<>(punishPage.getStart(), punishPage.getCurrentPageSize(),
				punishPage.getTotalSize(), punishPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public Page<SwRewardVO> getRewardPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<SrRewardAndPunish> rewardPage = rewardAndPunishRepository.findByFilter(filter, sort, page, limit);
		List<SwRewardVO> voList = new ArrayList<>();
		for (SrRewardAndPunish e : rewardPage.getResult()) {
			SwRewardVO vo = new SwRewardVO(e);
			voList.add(vo);
		}
		return new Page<>(rewardPage.getStart(), rewardPage.getCurrentPageSize(), rewardPage.getTotalSize(),
				rewardPage.getPageSize(), voList);
	}
	
	@Override
	public Page<SwPunishVO> getPunishPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<SrRewardAndPunish> punishPage = rewardAndPunishRepository.findByFilter(filter, sort, page, limit);
		List<SwPunishVO> voList = new ArrayList<>();
		for (SrRewardAndPunish e : punishPage.getResult()) {
			SwPunishVO vo = new SwPunishVO(e);
			voList.add(vo);
		}
		return new Page<>(punishPage.getStart(), punishPage.getCurrentPageSize(), punishPage.getTotalSize(),
				punishPage.getPageSize(), voList);
	}
	
}
