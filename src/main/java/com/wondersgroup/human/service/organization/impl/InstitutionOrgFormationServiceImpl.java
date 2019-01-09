/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: InstitutionOrgFormationServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 事业单位编制信息维护服务接口实现类
 * 创建人: jiang
 * 创建时间: 2018年12月5日15:41:16
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月5日15:41:20
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.organization.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;
import com.wondersgroup.human.repository.organization.InstitutionOrgFormationRepository;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationService;

/**
 * @ClassName: InstitutionOrgFormationServiceImpl
 * @Description: 事业单位编制信息维护服务接口实现类
 * @author: jiang
 * @date: 2018年12月5日15:41:28
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class InstitutionOrgFormationServiceImpl extends GenericServiceImpl<InstitutionOrgFormation>
		implements InstitutionOrgFormationService {
	
	@Autowired
	private InstitutionOrgFormationRepository orgInfoRepository;
	
	@Override
	public Page<InstitutionOrgFormation> getOrgFormationByName(String orgName, Integer limit, Integer page) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("select t from InstitutionOrgFormation t left join t.orgInfo o  where  t.removed=:removed");
		List<QueryParameter> ifilter = new ArrayList<QueryParameter>();
		QueryParameter iqueryparameter = new QueryParameter("removed", false);
		ifilter.add(iqueryparameter);
		if (StringUtils.isNotBlank(orgName)) {
			hql.append(" and t.orgInfo.unitBasicName like :orgName");
			iqueryparameter = new QueryParameter("orgName", "%" + orgName + "%");
			ifilter.add(iqueryparameter);
		}
		Page<InstitutionOrgFormation> result = this.findByHQL(hql.toString(), ifilter, page, limit);
		return result;
	}
	
}
