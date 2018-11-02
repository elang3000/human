/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainYearPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月18日 上午10:34:04 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月18日 上午10:34:04 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.bo.ofcflow.TrainYearPlan;
import com.wondersgroup.human.service.ofcflow.TrainYearPlanService;
import com.wondersgroup.human.vo.ofcflow.TrainYearPlanVO;

/** 
 * @ClassName: TrainYearPlanServiceImpl 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年9月18日 上午10:34:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TrainYearPlanServiceImpl extends GenericServiceImpl<TrainYearPlan> implements TrainYearPlanService {

	/**
	 * @see com.wondersgroup.human.service.ofcflow.TrainYearPlanService#pageList() 
	 */
	@Override
	public Page<TrainYearPlanVO> pageList(TrainYearPlan trainYearPlan,Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(TrainYearPlan.class);
		if (trainYearPlan.getStartDate()!=null) {// 开始时间
			detachedcriteria.add(Restrictions.le("startDate", trainYearPlan.getStartDate()));
		}
		if (trainYearPlan.getEndDate()!=null) {// 结束时间
			detachedcriteria.add(Restrictions.ge("endDate", trainYearPlan.getEndDate()));
		}
		detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		detachedcriteria.add(Restrictions.eq("removed", false));
		//创建时间倒叙
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<TrainYearPlan> trainPage = this.findByCriteria(detachedcriteria, page, limit);
		List<TrainYearPlanVO> voList = new ArrayList<>();
		for (TrainYearPlan t : trainPage.getResult()) {
			TrainYearPlanVO vo = new TrainYearPlanVO(t);
			voList.add(vo);
		}
		Page<TrainYearPlanVO> result = new Page<>(trainPage.getStart(), trainPage.getCurrentPageSize(),
				trainPage.getTotalSize(), trainPage.getPageSize(), voList);
		return result;
	}

}
