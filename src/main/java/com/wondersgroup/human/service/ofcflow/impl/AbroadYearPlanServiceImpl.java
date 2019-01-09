/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadYearPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月27日 下午7:20:07 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月27日 下午7:20:07 
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
import com.wondersgroup.human.bo.ofcflow.AbroadYearPlan;
import com.wondersgroup.human.service.ofcflow.AbroadYearPlanService;
import com.wondersgroup.human.vo.ofcflow.AbroadYearPlanVO;

/** 
 * @ClassName: AbroadYearPlanServiceImpl 
 * @Description:因公出国计划服务实现类
 * @author: lihao
 * @date: 2018年9月27日 下午7:20:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class AbroadYearPlanServiceImpl extends GenericServiceImpl<AbroadYearPlan> implements AbroadYearPlanService {

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.AbroadYearPlanService#pageList(com.wondersgroup.human.bo.ofcflow.AbroadYearPlan, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<AbroadYearPlanVO> pageList(AbroadYearPlan abroadYearPlan, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(AbroadYearPlan.class);
		if (abroadYearPlan.getStartDate()!=null) {// 开始时间
			detachedcriteria.add(Restrictions.ge("startDate", abroadYearPlan.getStartDate()));
		}
		if (abroadYearPlan.getEndDate()!=null) {// 结束时间
			detachedcriteria.add(Restrictions.le("endDate", abroadYearPlan.getEndDate()));
		}
		detachedcriteria.add(Restrictions.eq("removed", false));
		//创建时间倒叙
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<AbroadYearPlan> abroadPage = this.findByCriteria(detachedcriteria, page, limit);
		List<AbroadYearPlanVO> voList = new ArrayList<>();
		for (AbroadYearPlan a : abroadPage.getResult()) {
			AbroadYearPlanVO vo = new AbroadYearPlanVO(a);
			voList.add(vo);
		}
		Page<AbroadYearPlanVO> result = new Page<>(abroadPage.getStart(), abroadPage.getCurrentPageSize(),
				abroadPage.getTotalSize(), abroadPage.getPageSize(), voList);
		return result;
	}

}
