/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignPeopleServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月20日 上午11:06:03 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月20日 上午11:06:03 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.bo.ofcflow.ResignPeople;
import com.wondersgroup.human.service.ofcflow.ResignPeopleService;
import com.wondersgroup.human.vo.ofcflow.ResignPeopleVO;

/** 
 * @ClassName: ResignPeopleServiceImpl 
 * @Description: 辞职人员信息服务接口实现类
 * @author: lihao
 * @date: 2018年12月20日 上午11:06:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ResignPeopleServiceImpl extends GenericServiceImpl<ResignPeople> implements ResignPeopleService{

	/**
	 * @see com.wondersgroup.human.service.ofcflow.ResignPeopleService#resignPeopleList(com.wondersgroup.human.bo.ofcflow.ResignPeople, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ResignPeopleVO> resignPeopleList(String planId, Integer page, Integer limit) {
		
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(ResignPeople.class);
		
		detachedcriteria.add(Restrictions.eq("plan.id", planId));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<ResignPeople> resignPage = this.findByCriteria(detachedcriteria, page, limit);
		List<ResignPeopleVO> voList = new ArrayList<>();
		for (ResignPeople rs : resignPage.getResult()) {
			ResignPeopleVO vo = new ResignPeopleVO(rs);
			voList.add(vo);
		}
		Page<ResignPeopleVO> result = new Page<>(resignPage.getStart(), resignPage.getCurrentPageSize(),
				resignPage.getTotalSize(), resignPage.getPageSize(), voList);
		return result;
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.ResignPeopleService#peopleList(com.wondersgroup.human.bo.ofcflow.ResignPeople, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ResignPeopleVO> peopleList(ResignPeople resignPeople, Integer page, Integer limit) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());//当前登录所在单位
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(ResignPeople.class);
				
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		if (resignPeople.getServant() != null && StringUtils.isNotBlank(resignPeople.getServant().getName())) {// 姓名
			s.add(Restrictions.like("s.name", resignPeople.getServant().getName(), MatchMode.ANYWHERE));
		}
		if (resignPeople.getServant() != null && StringUtils.isNotBlank(resignPeople.getServant().getCardNo())) {// 身份证
			s.add(Restrictions.eq("s.cardNo", resignPeople.getServant().getCardNo()));
		}
		if (resignPeople.getReason() != null && StringUtils.isNotBlank(resignPeople.getReason().getId())) {// 辞职原因
			DetachedCriteria re = detachedcriteria.createAlias("reason", "re");
			re.add(Restrictions.eq("re.id", resignPeople.getReason().getId()));
		}
		if (resignPeople.getPlan() != null && StringUtils.isNotBlank(resignPeople.getPlan().getId())) {// 批次ID
			detachedcriteria.add(Restrictions.like("plan.id", resignPeople.getPlan().getId(), MatchMode.ANYWHERE));
		}
		if(x!=null&&x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)){
			detachedcriteria.add(Restrictions.eq("lastOperator", SecurityUtils.getUserId()));
		}else{
			detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		}
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<ResignPeople> resignPage = this.findByCriteria(detachedcriteria, page, limit);
		List<ResignPeopleVO> voList = new ArrayList<>();
		for (ResignPeople rs : resignPage.getResult()) {
			ResignPeopleVO vo = new ResignPeopleVO(rs);
			voList.add(vo);
		}
		Page<ResignPeopleVO> result = new Page<>(resignPage.getStart(), resignPage.getCurrentPageSize(),
				resignPage.getTotalSize(), resignPage.getPageSize(), voList);
		return result;
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.ResignPeopleService#getResignPeopleCountByPlanId(java.lang.String) 
	 */
	@Override
	public List<ResignPeople> getResignPeopleCountByPlanId(String id) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(ResignPeople.class);
		
		detachedcriteria.add(Restrictions.eq("plan.id", id));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		List<ResignPeople> list = this.findByCriteria(detachedcriteria);
		
		return list;
	}

}
