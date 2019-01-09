/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPeopleServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月15日 上午9:09:19 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月15日 上午9:09:19 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.TrainPeople;
import com.wondersgroup.human.repository.ofcflow.TrainPeopleRepository;
import com.wondersgroup.human.service.ofcflow.TrainPeopleService;
import com.wondersgroup.human.vo.ofcflow.TrainPeopleVO;

/** 
 * @ClassName: TrainPeopleServiceImpl 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年11月15日 上午9:09:19
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TrainPeopleServiceImpl extends GenericServiceImpl<TrainPeople> implements TrainPeopleService{

	@Autowired
	TrainPeopleRepository trainPeopleRepository;
	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainPeopleService#pageList(java.lang.String, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<TrainPeopleVO> pageList(String id,Servant servant, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(TrainPeople.class);
		
		DetachedCriteria t = detachedcriteria.createAlias("train", "t");
		t.add(Restrictions.eq("t.id", id));
		
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		if (StringUtils.isNotBlank(servant.getName())) {
			s.add(Restrictions.like("s.name", servant.getName(), MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(servant.getCardNo())) {// 身份证
			s.add(Restrictions.eq("s.cardNo", servant.getCardNo()));
		}
		if (servant.getSex() != null && StringUtils.isNotBlank(servant.getSex().getId())) {// 性别
			s.add(Restrictions.eq("s.sex.id", servant.getSex().getId()));
		}
		
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<TrainPeople> trianpPage = this.findByCriteria(detachedcriteria, page, limit);
		List<TrainPeopleVO> voList = new ArrayList<>();
		for (TrainPeople ps : trianpPage.getResult()) {
			TrainPeopleVO vo = new TrainPeopleVO(ps);
			voList.add(vo);
		}
		Page<TrainPeopleVO> result = new Page<>(trianpPage.getStart(), trianpPage.getCurrentPageSize(),
				trianpPage.getTotalSize(), trianpPage.getPageSize(), voList);
		return result;
	}

	/**  
	 * @see com.wondersgroup.human.service.ofcflow.TrainPeopleService#exportByPerson(java.util.Date, java.util.Date) 
	 */
	@Override
	public List<?> exportByPerson(String start,String end) {
		return trainPeopleRepository.exportByPerson(start,end);
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainPeopleService#exportByUnit(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public List<?> exportByUnit(String start, String end, Integer hour1, Integer hour2) {
		return trainPeopleRepository.exportByUnit(start,end,hour1,hour2);
	}

}
