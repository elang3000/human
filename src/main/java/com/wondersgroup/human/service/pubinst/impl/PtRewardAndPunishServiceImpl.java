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
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.pubinst.PtRewardAndPunish;
import com.wondersgroup.human.repository.pubinst.PtRewardAndPunishRepository;
import com.wondersgroup.human.service.pubinst.PtRewardAndPunishService;
import com.wondersgroup.human.vo.ofc.RewardVO;
import com.wondersgroup.human.vo.pubinst.PtPunishVO;
import com.wondersgroup.human.vo.pubinst.PtRewardVO;

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
public class PtRewardAndPunishServiceImpl extends GenericServiceImpl<PtRewardAndPunish> implements PtRewardAndPunishService {
	
	@Autowired
	private PtRewardAndPunishRepository rewardAndPunishRepository;
	
	/**
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<PtPunishVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		
		Page<PtRewardAndPunish> punishPage = rewardAndPunishRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<PtPunishVO> voList = new ArrayList<>();
		List list = punishPage.getResult();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof PtRewardAndPunish) {
				PtRewardAndPunish r = (PtRewardAndPunish) list.get(i);
				PtPunishVO vo = new PtPunishVO(r);
				voList.add(vo);
			} else {
				Object[] o = (Object[]) list.get(0);
				PtRewardAndPunish r = (PtRewardAndPunish) o[1];
				PtPunishVO vo = new PtPunishVO(r);
				voList.add(vo);
			}
		}
		Page<PtPunishVO> page = new Page<>(punishPage.getStart(), punishPage.getCurrentPageSize(),
				punishPage.getTotalSize(), punishPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public Page<PtRewardVO> getRewardPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtRewardAndPunish> rewardPage = rewardAndPunishRepository.findByFilter(filter, sort, page, limit);
		List<PtRewardVO> voList = new ArrayList<>();
		for (PtRewardAndPunish e : rewardPage.getResult()) {
			PtRewardVO vo = new PtRewardVO(e);
			voList.add(vo);
		}
		return new Page<>(rewardPage.getStart(), rewardPage.getCurrentPageSize(), rewardPage.getTotalSize(),
				rewardPage.getPageSize(), voList);
	}
	
	@Override
	public Page<PtPunishVO> getPunishPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtRewardAndPunish> punishPage = rewardAndPunishRepository.findByFilter(filter, sort, page, limit);
		List<PtPunishVO> voList = new ArrayList<>();
		for (PtRewardAndPunish e : punishPage.getResult()) {
			PtPunishVO vo = new PtPunishVO(e);
			voList.add(vo);
		}
		return new Page<>(punishPage.getStart(), punishPage.getCurrentPageSize(), punishPage.getTotalSize(),
				punishPage.getPageSize(), voList);
	}
	
}
