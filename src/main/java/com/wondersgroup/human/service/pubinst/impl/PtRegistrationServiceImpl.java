/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RegistrationServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-登记 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年8月22日15:42:25
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月22日15:42:27
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.pubinst.PtRegistration;
import com.wondersgroup.human.repository.pubinst.PtRegistrationRepository;
import com.wondersgroup.human.service.pubinst.PtRegistrationService;
import com.wondersgroup.human.vo.pubinst.PtRegistrationVO;

/**
 * @ClassName: RegistrationServiceImpl
 * @Description: 人员信息子表-登记 服务实现类
 * @author: jiang
 * @date: 2018年8月22日15:42:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtRegistrationServiceImpl extends GenericServiceImpl<PtRegistration> implements PtRegistrationService {
	
	@Autowired
	private PtRegistrationRepository registrationRepository;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: TODO
	 * @param filter
	 * @param sort
	 * @param page
	 * @param limit
	 * @return
	 * @see com.wondersgroup.human.service.ofc.RegistrationService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtRegistrationVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit) {
		
		Page<PtRegistration> registrationPage = registrationRepository.findByFilter(filter, sort, page, limit);
		List<PtRegistrationVO> voList = new ArrayList<>();
		for (PtRegistration e : registrationPage.getResult()) {
			PtRegistrationVO vo = new PtRegistrationVO(e);
			voList.add(vo);
		}
		return new Page<>(registrationPage.getStart(), registrationPage.getCurrentPageSize(), registrationPage.getTotalSize(),
				registrationPage.getPageSize(), voList);
	}
}
