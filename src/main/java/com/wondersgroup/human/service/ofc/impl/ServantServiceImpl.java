/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServantServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 人员信息维护服务接口实现类
 * 创建人: tzy
 * 创建时间: 2018年5月21日 上午11:05:33
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月21日 上午11:05:33
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.dto.analysis.ServantParam;
import com.wondersgroup.human.repository.ofc.ServantRepository;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.ServantVO;

/**
 * @ClassName: ServantServiceImpl
 * @Description: 人员信息维护服务接口实现类
 * @author: tzy
 * @date: 2018年5月21日 上午11:05:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class ServantServiceImpl extends GenericServiceImpl<Servant> implements ServantService {
	
	@Autowired
	private ServantRepository servantRepository;
	
	@Autowired
	private DictableService dictableService;
	
	@Override
	public Page<ServantVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit) {
		
		Page<Servant> servantPage = servantRepository.findByCriteria(detachedCriteria, pageNo, limit);
		List<ServantVO> voList = new ArrayList<>();
		for (Servant s : servantPage.getResult()) {
			ServantVO vo = new ServantVO(s);
			voList.add(vo);
		}
		Page<ServantVO> page = new Page<>(servantPage.getStart(), servantPage.getCurrentPageSize(),
				servantPage.getTotalSize(), servantPage.getPageSize(), voList);
		return page;
	}

	/**
	 * (non Javadoc) 
	 * @Title: getAllActiveServant
	 * @Description: 查询所有在职人员
	 * @return 
	 * @see com.wondersgroup.human.service.ofc.ServantService#getAllActiveServant()
	 */
	@Override
	public List<Servant> getAllActiveServant() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
		detachedCriteria.add(Restrictions.eq("creater", "SYSTEM"));
		detachedCriteria.add(Restrictions.isNotNull("departId"));
		List<Servant> allActiveServant = servantRepository.findByCriteria(detachedCriteria);
		return allActiveServant;
	}

	/**  
	 * @see com.wondersgroup.human.service.ofc.ServantService#queryServantInfoBySeniorCondation(java.util.Map, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ServantVO> queryServantInfoBySeniorCondation(List<ServantParam> spList, Integer page,
			Integer limit) {
		return servantRepository.queryServantInfoBySeniorCondation(spList, page, limit);
	}
}
