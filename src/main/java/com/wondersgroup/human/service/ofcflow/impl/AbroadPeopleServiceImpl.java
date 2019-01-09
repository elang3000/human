/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPeopleServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月11日 下午4:50:48 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月11日 下午4:50:48 
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
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.AbroadPeople;
import com.wondersgroup.human.repository.ofcflow.AbroadPeopleRepository;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.AbroadPeopleService;
import com.wondersgroup.human.vo.ofc.ServantVO;
import com.wondersgroup.human.vo.ofcflow.AbroadPeopleVO;

/** 
 * @ClassName: AbroadPeopleServiceImpl 
 * @Description: 因公出国人员服务层接口实现
 * @author: lihao
 * @date: 2018年12月11日 下午4:50:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class AbroadPeopleServiceImpl extends GenericServiceImpl<AbroadPeople> implements AbroadPeopleService {

	@Autowired
	private AbroadPeopleRepository abroadPeopleRepository;
	
	@Autowired
	ServantService servantService;
	
	/**  
	 * @see com.wondersgroup.human.service.ofcflow.AbroadPeopleService#getServantIdsByAbroadId(java.lang.String) 
	 */
	@Override
	public List<AbroadPeople> getServantIdsByAbroadId(String abroadId) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(AbroadPeople.class);
		
		detachedcriteria.add(Restrictions.eq("abroadServant.id", abroadId));
		detachedcriteria.add(Restrictions.eq("removed", false));
		//创建时间倒叙
		detachedcriteria.addOrder(Order.desc("createTime"));
		List<AbroadPeople> apList = this.findByCriteria(detachedcriteria);
		return apList;
	}

	/** 
	 * @see com.wondersgroup.human.service.ofcflow.AbroadPeopleService#getByServantIds(java.lang.String, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<AbroadPeopleVO> getByServantIds(String abroadId,String abroadYearId, Integer limit, Integer page) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AbroadPeople.class);
		
		detachedCriteria.add(Restrictions.eq("abroadServant.id", abroadId));
		
		detachedCriteria.addOrder(Order.desc("createTime"));
		detachedCriteria.add(Restrictions.eq("removed", false));
		
		Page<AbroadPeople> abroadPeoplePage = abroadPeopleRepository.findByCriteria(detachedCriteria, page, limit);
		List<AbroadPeopleVO> voList = new ArrayList<>();
		for (AbroadPeople ap : abroadPeoplePage.getResult()) {
			AbroadPeopleVO vo = new AbroadPeopleVO(ap);
			Integer isTime = abroadPeopleRepository.selectAbroadTimeByServantId(ap.getServant().getId(),abroadYearId);
			if(isTime>0){
				vo.setIsTime("是");
			}else{
				vo.setIsTime("否");
			}
			voList.add(vo);
		}
		Page<AbroadPeopleVO> result = new Page<>(abroadPeoplePage.getStart(), abroadPeoplePage.getCurrentPageSize(),
				abroadPeoplePage.getTotalSize(), abroadPeoplePage.getPageSize(), voList);
		return result;
	}

}
