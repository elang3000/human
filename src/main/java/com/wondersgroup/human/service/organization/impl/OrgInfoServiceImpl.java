/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgInfoServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 人员信息维护服务接口实现类
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
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.repository.organization.OrgInfoRepository;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.organization.OrgInfoVO;

/**
 * @ClassName: OrgInfoServiceImpl
 * @Description: 单位信息维护服务接口实现类
 * @author: jiang
 * @date: 2018年5月21日 上午11:05:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class OrgInfoServiceImpl extends GenericServiceImpl<OrgInfo> implements OrgInfoService {
	
	@Autowired
	private OrgInfoRepository orgInfoRepository;
	
	@Override
	public Page<OrgInfoVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit) {
		
		Page<OrgInfo> orgInfoPage = orgInfoRepository.findByCriteria(detachedCriteria, pageNo, limit);
		List<OrgInfoVO> voList = new ArrayList<>();
		for (OrgInfo s : orgInfoPage.getResult()) {
			OrgInfoVO vo = new OrgInfoVO(s);
			voList.add(vo);
		}
		Page<OrgInfoVO> page = new Page<>(orgInfoPage.getStart(), orgInfoPage.getCurrentPageSize(),
				orgInfoPage.getTotalSize(), orgInfoPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public OrgInfo findLikeSimpleName(String simpleName) {
		StringBuilder sb=new StringBuilder();
		sb.append("from OrgInfo a where (a.unitBasicName like :unitBasicName or a.unitBasicSimpleName like :unitBasicSimpleName)");
		sb.append(" and a.removed=:removed");
		List<QueryParameter> ifilter=new ArrayList<QueryParameter>();
		QueryParameter iqueryparameter=new QueryParameter("unitBasicName", simpleName);
		ifilter.add(iqueryparameter);
		iqueryparameter=new QueryParameter("unitBasicSimpleName", simpleName);
		ifilter.add(iqueryparameter);
		iqueryparameter=new QueryParameter("removed", false);
		ifilter.add(iqueryparameter);
		List<OrgInfo> list=this.findByHQL(sb.toString(), ifilter);
		if (list.size()==1) {
			return list.get(0);
		}else if(list.size()>1){
			System.out.println("=========================================================================================================");
			System.out.println("=========================================================================================================");
			System.out.println("=========================================================================================================");
			return null;
		}else
			return null;
	}
	
}
