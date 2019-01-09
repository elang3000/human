/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 招录计划 服务实现类
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:13:23 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:13:23 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.instflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.dto.instflow.InfoRegisterQueryParam;
import com.wondersgroup.human.repository.instflow.MemberInfoRegisterRepository;
import com.wondersgroup.human.service.instflow.MemberInfoRegisterService;
import com.wondersgroup.human.vo.instflow.MemberInfoRegisterVO;

/**
 * @ClassName: RecruitEmployPlanServiceImpl
 * @Description: 人员信息登记
 * @author: lyf
 * @date: 2018年5月28日
 *        上午11:13:23 @version [版本号, YYYY-MM-DD] @see       [相关类/方法] @since     [
 * 产品/模块版本]
 */
@Service
public class MemberInfoRegisterServiceImpl extends GenericServiceImpl<MemberInfoRegister>
		implements MemberInfoRegisterService {

	@Autowired
	MemberInfoRegisterRepository memberInfoRegisterRepository;

	@Override
	public MemberInfoRegister findMemberInfoRegisterByPid(String id) {
		return memberInfoRegisterRepository.findMemberInfoRegisterByPid(id);
	}

	/**
	 * (non Javadoc)
	 * 
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0
	 *            查询条件
	 * @param arg1
	 *            排序规则
	 * @param arg2
	 *            页码
	 * @param arg3
	 *            页大小
	 * @return
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public Page<MemberInfoRegisterVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		Page<MemberInfoRegister> memberInfoRegisterPage = memberInfoRegisterRepository.findByFilter(arg0, arg1, arg2,
				arg3);
		List<MemberInfoRegisterVO> voList = new ArrayList<>();
		for (MemberInfoRegister p : memberInfoRegisterPage.getResult()) {
			MemberInfoRegisterVO vo = new MemberInfoRegisterVO(p);
			voList.add(vo);
		}
		Page<MemberInfoRegisterVO> page = new Page<>(memberInfoRegisterPage.getStart(),
				memberInfoRegisterPage.getCurrentPageSize(), memberInfoRegisterPage.getTotalSize(),
				memberInfoRegisterPage.getPageSize(), voList);
		return page;
	}

	@Override
	public Page<MemberInfoRegisterVO> pageList(InfoRegisterQueryParam param, Integer page, Integer limit) {
		
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位

		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(MemberInfoRegister.class);
		DetachedCriteria s = detachedcriteria.createAlias("publicInstitution", "p");
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			s.add(Restrictions.like("p.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			s.add(Restrictions.eq("p.cardNo", param.getCardNo()));
		}
		/**********原因**************/
		/**
		 * if (param.getReason() != null && StringUtils.isNotBlank(param.getReason().getId())) {// 辞职原因
		 *		DetachedCriteria re = detachedcriteria.createAlias("reason", "re");
		 *		re.add(Restrictions.eq("re.id", param.getReason().getId()));
		 *  }
		 */
		if (x != null && x.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
			detachedcriteria.add(Restrictions.eq("lastOperator", SecurityUtils.getUserId()));
		} else {
			detachedcriteria.add(Restrictions.eq("creater", SecurityUtils.getUserId()));
		}
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<MemberInfoRegister> registerPage = this.findByCriteria(detachedcriteria, page, limit);
		List<MemberInfoRegisterVO> voList = new ArrayList<>();
		for (MemberInfoRegister rs : registerPage.getResult()) {
			MemberInfoRegisterVO vo = new MemberInfoRegisterVO(rs);
			voList.add(vo);
		}
		Page<MemberInfoRegisterVO> result = new Page<>(registerPage.getStart(), registerPage.getCurrentPageSize(),
				registerPage.getTotalSize(), registerPage.getPageSize(), voList);
		return result;
	}

}
