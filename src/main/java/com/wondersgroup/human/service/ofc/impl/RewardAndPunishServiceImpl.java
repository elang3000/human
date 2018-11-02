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
package com.wondersgroup.human.service.ofc.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.repository.ofc.RewardAndPunishRepository;
import com.wondersgroup.human.service.ofc.RewardAndPunishService;
import com.wondersgroup.human.vo.ofc.PunishVO;
import com.wondersgroup.human.vo.ofc.RewardVO;

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
public class RewardAndPunishServiceImpl extends GenericServiceImpl<RewardAndPunish> implements RewardAndPunishService {
	
	@Autowired
	private RewardAndPunishRepository rewardAndPunishRepository;
	
	/**
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<PunishVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		
		Page<RewardAndPunish> punishPage = rewardAndPunishRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<PunishVO> voList = new ArrayList<>();
		List list = punishPage.getResult();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof RewardAndPunish) {
				RewardAndPunish r = (RewardAndPunish) list.get(i);
				PunishVO vo = new PunishVO(r);
				voList.add(vo);
			} else {
				Object[] o = (Object[]) list.get(0);
				RewardAndPunish r = (RewardAndPunish) o[1];
				PunishVO vo = new PunishVO(r);
				voList.add(vo);
			}
		}
		Page<PunishVO> page = new Page<>(punishPage.getStart(), punishPage.getCurrentPageSize(),
				punishPage.getTotalSize(), punishPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public Page<RewardVO> getRewardPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<RewardAndPunish> rewardPage = rewardAndPunishRepository.findByFilter(filter, sort, page, limit);
		List<RewardVO> voList = new ArrayList<>();
		for (RewardAndPunish e : rewardPage.getResult()) {
			RewardVO vo = new RewardVO(e);
			voList.add(vo);
		}
		return new Page<>(rewardPage.getStart(), rewardPage.getCurrentPageSize(), rewardPage.getTotalSize(),
				rewardPage.getPageSize(), voList);
	}
	
	@Override
	public Page<PunishVO> getPunishPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<RewardAndPunish> punishPage = rewardAndPunishRepository.findByFilter(filter, sort, page, limit);
		List<PunishVO> voList = new ArrayList<>();
		for (RewardAndPunish e : punishPage.getResult()) {
			PunishVO vo = new PunishVO(e);
			voList.add(vo);
		}
		return new Page<>(punishPage.getStart(), punishPage.getCurrentPageSize(), punishPage.getTotalSize(),
				punishPage.getPageSize(), voList);
	}

	@Override
	public boolean hasPublish(Servant servant) {
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("servant.id", Operator.EQ, servant.getId(), "");
		filter.add(p);
		p = new Predicate("type", Operator.EQ,RewardAndPunish.TYPE_OF_PUNISH, "");
		filter.add(p);
		List<RewardAndPunish> punishs = this.findByFilter(filter);
		return punishs.size()!=0?false:true;
	}

	@Override
	public Date getLatestThirdClassDate(Servant servant) {
		Date date=rewardAndPunishRepository.getLatestThirdClassDate(servant);
		return date;
	}
}
