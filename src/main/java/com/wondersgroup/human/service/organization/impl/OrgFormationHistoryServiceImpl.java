/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgFormationHistoryServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 单位编制历史调整 接口实现类
 * 创建人: jiang
 * 创建时间: 2018年5月21日 上午11:05:33
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年5月21日 上午11:05:33
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.organization.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.organization.OrgFormationHistory;
import com.wondersgroup.human.bo.organization.OrgInfoHistory;
import com.wondersgroup.human.repository.organization.OrgFormationHistoryRepository;
import com.wondersgroup.human.service.organization.OrgFormationHistoryService;
import com.wondersgroup.human.vo.organization.OrgFormationHistoryVO;
import com.wondersgroup.human.vo.organization.OrgInfoHistoryVO;

/**
 * @ClassName: OrgFormationHistoryServiceImpl
 * @Description: 单位编制历史调整 接口实现类
 * @author: jiang
 * @date: 2018年9月20日18:57:09
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class OrgFormationHistoryServiceImpl extends GenericServiceImpl<OrgFormationHistory> implements OrgFormationHistoryService {
	
	@Autowired
	private OrgFormationHistoryRepository orgFormationHistoryRepository;
	
	@Override
	public Page<OrgFormationHistoryVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit) {
		
		Page<OrgFormationHistory> orgFormationHistoryPage = orgFormationHistoryRepository.findByCriteria(detachedCriteria, pageNo, limit);
		List<OrgFormationHistoryVO> voList = new ArrayList<>();
		for (OrgFormationHistory s : orgFormationHistoryPage.getResult()) {
			OrgFormationHistoryVO vo = new OrgFormationHistoryVO(s);
			voList.add(vo);
		}
		Page<OrgFormationHistoryVO> page = new Page<>(orgFormationHistoryPage.getStart(), orgFormationHistoryPage.getCurrentPageSize(),
				orgFormationHistoryPage.getTotalSize(), orgFormationHistoryPage.getPageSize(), voList);
		return page;
	}
}
