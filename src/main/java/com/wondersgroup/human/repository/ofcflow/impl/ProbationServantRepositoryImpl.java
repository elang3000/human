/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProbationServantRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 试用期管理 知识库接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月25日 下午3:15:41 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月25日 下午3:15:41 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.apache.velocity.runtime.directive.Foreach;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;
import com.wondersgroup.human.repository.ofcflow.ProbationServantRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName: ProbationServantRepositoryImpl 
 * @Description: 试用期管理 知识库接口实现类
 * @author: tzy
 * @date: 2018年7月25日 下午3:15:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class ProbationServantRepositoryImpl extends GenericRepositoryImpl<ProbationServant> implements ProbationServantRepository{
	
	public Class<ProbationServant> getEntityClass() {
		return ProbationServant.class;
	}

	@Override
	public Map<String, Integer> getUnitProbationCount() {
		StringBuilder sql=new StringBuilder();
		sql.append("select count(p.id) value, b.id ");
		sql.append("  from HUMAN_PROBATION_SERVANT p ");
		sql.append(" inner join HUMAN_DRAFT_SERVANT d ");
		sql.append("    on p.DRAFT_SERVANT_ID = d.id ");
		sql.append("  left join B01 b ");
		sql.append("    on d.DRAFT_UNIT_ID = b.id ");
		sql.append(" where p.STATUS in (0, 1, 2, 3, 4, 5, 7) ");
		sql.append(" group by b.id ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		Map<String, Integer> resultMap = new HashMap();
		List<Map<String, Object>> list =queryObject.list();
		for(Map<String, Object> map : list){
			resultMap.put((String)map.get("ID"),((BigDecimal)map.get("VALUE")).intValue());
		}
		return resultMap;
	}
}
