/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantBasicInfoServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl 
 * 描述: TODO
 * 创建人: Wonders-Rain   
 * 创建时间: 2018年9月24日 下午4:05:12 
 * 版本号: V1.0
 * 修改人：Wonders-Rain 
 * 修改时间：2018年9月24日 下午4:05:12 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofc.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.bo.ofc.ServantBasicInfo;
import com.wondersgroup.human.repository.ofc.ServantBasicInfoRepository;
import com.wondersgroup.human.service.ofc.ServantBasicInfoService;


/** 
 * @ClassName: ServantBasicInfoServiceImpl 
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年9月24日 下午4:05:12
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ServantBasicInfoServiceImpl implements ServantBasicInfoService {
	
	
	@Autowired
	ServantBasicInfoRepository servantBasicInfoRepository;
	
	/**
	 * @see com.wondersgroup.human.service.ofc.ServantBasicInfoService#load(java.io.Serializable) 
	 */
	@Override
	public ServantBasicInfo load(Serializable id) {
		
		return servantBasicInfoRepository.load(id);
	}
	
	/** 
	 * @see com.wondersgroup.human.service.ofc.ServantBasicInfoService#get(java.io.Serializable) 
	 */
	@Override
	public ServantBasicInfo get(Serializable id) {
		
		return servantBasicInfoRepository.get(id);
	}
	
	/** 
	 * @see com.wondersgroup.human.service.ofc.ServantBasicInfoService#findByCriteria(org.hibernate.criterion.DetachedCriteria) 
	 */
	@Override
	public List<ServantBasicInfo> findByCriteria(DetachedCriteria detachedCriteria) {
		
		return servantBasicInfoRepository.findByCriteria(detachedCriteria);
	}
	
	/**
	 * @see com.wondersgroup.human.service.ofc.ServantBasicInfoService#findByCriteria(org.hibernate.criterion.DetachedCriteria, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ServantBasicInfo> findByCriteria(DetachedCriteria detachedCriteria, Integer pageNumber,
	        Integer pageSize) {
		
		return servantBasicInfoRepository.findByCriteria(detachedCriteria, pageNumber, pageSize);
	}
	
}
