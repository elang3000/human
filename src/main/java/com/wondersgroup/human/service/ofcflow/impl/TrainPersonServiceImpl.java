/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月13日 上午9:13:50 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月13日 上午9:13:50 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.bo.ofcflow.TrainPerson;
import com.wondersgroup.human.bo.ofcflow.TrainYearPlan;
import com.wondersgroup.human.repository.ofcflow.TrainPersonRepository;
import com.wondersgroup.human.service.ofcflow.TrainPersonService;
import com.wondersgroup.human.vo.ofcflow.TrainPersonVO;

/** 
 * @ClassName: TrainPlanServiceImpl 
 * @Description: 培训个人服务类
 * @author: lihao
 * @date: 2018年9月13日 上午9:13:50
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TrainPersonServiceImpl extends GenericServiceImpl<TrainPerson> implements TrainPersonService {

	@Autowired
	TrainPersonRepository trainPersonRepository;
	
	/**  
	 * @see com.wondersgroup.human.service.ofcflow.TrainPersonService#pageList(java.lang.String) 
	 */
	@Override
	public Page<TrainPersonVO> pageList(String planId, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(TrainPerson.class);
		//根据培训信息id查询
		DetachedCriteria p = detachedcriteria.createAlias("plan", "p");
		p.add(Restrictions.eq("p.id", planId));
		
		detachedcriteria.add(Restrictions.eq("removed", false));
		//创建时间倒叙
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<TrainPerson> trainPage = this.findByCriteria(detachedcriteria,page,limit);
		List<TrainPersonVO> voList = new ArrayList<>();
		for (TrainPerson t : trainPage.getResult()) {
			TrainPersonVO vo = new TrainPersonVO(t);
			voList.add(vo);
		}
		Page<TrainPersonVO> result = new Page<>(trainPage.getStart(), trainPage.getCurrentPageSize(),
					trainPage.getTotalSize(), trainPage.getPageSize(), voList);
		return result;
	}

	/**  
	 * @see com.wondersgroup.human.service.ofcflow.TrainPersonService#exportByUnit(com.wondersgroup.human.bo.ofcflow.TrainYearPlan) 
	 */
	@Override
	public List<?> exportByUnit(TrainYearPlan typ) {
		return trainPersonRepository.exportByUnit(typ);
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.TrainPersonService#exportByPerson(com.wondersgroup.human.bo.ofcflow.TrainYearPlan) 
	 */
	@Override
	public List<?> exportByPerson(TrainYearPlan typ) {
		return trainPersonRepository.exportByPerson(typ);
	}

}
