/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 单位信息历史调整 服务接口实现类
 * 创建人: jiang
 * 创建时间: 2018年9月20日19:05:48
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月20日19:00:07
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
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.bo.organization.OrgInfoHistory;
import com.wondersgroup.human.repository.organization.OrgInfoHistoryRepository;
import com.wondersgroup.human.service.organization.OrgInfoHistoryService;
import com.wondersgroup.human.vo.organization.OrgInfoHistoryVO;

/**
 * @ClassName: OrgInfoHistoryServiceImpl
 * @Description: 单位信息历史调整 服务接口实现类
 * @author: jiang
 * @date: 2018年9月20日19:05:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class OrgInfoHistoryServiceImpl extends GenericServiceImpl<OrgInfoHistory> implements OrgInfoHistoryService {
	
	@Autowired
	private OrgInfoHistoryRepository orgInfoHistoryRepository;
	
	@Override
	public Page<OrgInfoHistoryVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit) {
		
		Page<OrgInfoHistory> orgInfoHistoryPage = orgInfoHistoryRepository.findByCriteria(detachedCriteria, pageNo, limit);
		List<OrgInfoHistoryVO> voList = new ArrayList<>();
		for (OrgInfoHistory s : orgInfoHistoryPage.getResult()) {
			OrgInfoHistoryVO vo = new OrgInfoHistoryVO(s);
			voList.add(vo);
		}
		Page<OrgInfoHistoryVO> page = new Page<>(orgInfoHistoryPage.getStart(), orgInfoHistoryPage.getCurrentPageSize(),
				orgInfoHistoryPage.getTotalSize(), orgInfoHistoryPage.getPageSize(), voList);
		return page;
	}
}
