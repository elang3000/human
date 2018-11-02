/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AssessmentRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: 人员信息子表-考核 知识库实现类
 * 创建人: jiang   
 * 创建时间: 2018年7月2日10:30:52
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日10:30:55
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofc.Assessment;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.repository.ofc.AssessmentRepository;

/**
 * @ClassName: AssessmentRepositoryImpl 
 * @Description: 人员信息子表-考核 知识库实现类
 * @author: jiang
 * @date: 2018年7月2日10:31:06
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class AssessmentRepositoryImpl extends GenericRepositoryImpl<Assessment> implements AssessmentRepository {
	
	public Class<Assessment> getEntityClass() {
		
		return Assessment.class;
	}

	/**
	 * (non Javadoc) 
	 * @Title: isRecent3YearsFine
	 * @Description: 查看前两年是否优秀
	 * @param servant
	 * @return 
	 * @see com.wondersgroup.human.repository.ofc.AssessmentRepository#isRecent3YearsFine(com.wondersgroup.human.bo.ofc.Servant)
	 */
	@Override
	public boolean isRecent3YearsFine(Servant servant,Integer year) {
		List<Integer> years=new ArrayList<>();
		years.add(year-1);
		years.add(year-2);
		StringBuilder sql=new StringBuilder();
		sql.append("select count(1) ");
		sql.append("  from A15 t ");
		sql.append("  left join cf_code_info cci ");
		sql.append("    on t.A15017 = cci.id ");
		sql.append("  left join cf_code_info cci2 ");
		sql.append("    on t.A15001 = cci2.id ");
		sql.append(" where to_char(t.A15021, 'YYYY') in (:years) ");
		sql.append("   and cci.name = '优秀' ");
		sql.append("   and cci2.name = '年度考核' ");
		sql.append("and t.servant_id = :servantId ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
        queryObject.setParameter("servantId", servant.getId());
        queryObject.setParameterList("years", years);
        BigDecimal count = (BigDecimal)queryObject.uniqueResult();
        return   count.intValue()==2;
	}

	/**
	 * (non Javadoc) 
	 * @Title: isCurrentYearSeasonFine
	 * @Description: 季度考核是否全是优秀
	 * @param servant
	 * @return 
	 * @see com.wondersgroup.human.repository.ofc.AssessmentRepository#isCurrentYearSeasonFine(com.wondersgroup.human.bo.ofc.Servant)
	 */
	@Override
	public boolean isCurrentYearSeasonFine(Servant servant,Integer year) {

		StringBuilder sql=new StringBuilder();
		sql.append("select count(*) ");
		sql.append("  from A15 t ");
		sql.append("  left join cf_code_info cci ");
		sql.append("    on t.A15017 = cci.id ");
		sql.append("  left join cf_code_info cci2 ");
		sql.append("    on t.A15001 = cci2.id ");
		sql.append(" where to_char(t.A15021, 'YYYY') = :year ");
		sql.append("   and cci.name = '优秀' ");
		sql.append("   and cci2.name = '季度考核' ");
		sql.append("   and t.servant_id = :servantId ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
        queryObject.setParameter("servantId", servant.getId());
        queryObject.setParameter("year", year);
        BigDecimal count = (BigDecimal)queryObject.uniqueResult();
		return count.intValue()>=4;
//		return true;
	
	}
}
