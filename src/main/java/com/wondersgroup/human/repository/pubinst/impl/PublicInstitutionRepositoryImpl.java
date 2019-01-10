/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PublicInstitutionRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.impl 
 * 描述: 人员信息维护知识库接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年5月21日 上午11:22:54 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月21日 上午11:22:54 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.pubinst.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.businesspersonel.BusinessParam;
import com.wondersgroup.human.repository.pubinst.PublicInstitutionRepository;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/** 
 * @ClassName: PublicInstitutionRepositoryImpl
 * @Description: 人员信息维护知识库实现类
 * @author: tzy
 * @date: 2018年5月21日 上午11:22:54
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class PublicInstitutionRepositoryImpl extends GenericRepositoryImpl<PublicInstitution> implements PublicInstitutionRepository{

	public Class<PublicInstitution> getEntityClass() {
		return PublicInstitution.class;
	}

	
	@Override
	public Map<String, Integer> statistBusinesstTopEducation(List<String> organNodeIds, String isHold) {
		String queryString = "SELECT SH_ZGXL AS ZGXL,COUNT(ID) AS NUM FROM C01 WHERE REMOVED = 'N' AND C01063= :zgxl AND DEPARTID IN (:departId) GROUP BY SH_ZGXL";
		
		SQLQuery query = currentSession().createSQLQuery(queryString);
		query.setString("zgxl", isHold);
		query.setParameterList("departId", organNodeIds);
		
		@SuppressWarnings("unchecked")
		List<Object[]> queryResult = query.list();
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (Object[] entry : queryResult) {
			String name = (String) entry[0];
			if (StringUtils.isBlank(name)) {
				name = "未知";
			}
			Integer number = ((BigDecimal) entry[1]).intValue();
			result.put(name, number);
		}
		return result;
	}
	
	
	
	/**
	 * 事业单位综合查询
	 */
	@Override
	public Page<PublicInstitutionVO> queryBusinesstInfoBySeniorCondation(List<BusinessParam> spList,
			Map<String, String> m, Integer page, Integer limit) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from C01 where removed ='N' and id in (");
		sql.append("select distinct c1.id from c01 c1 ");
		for(String key:m.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
			String value = m.get(key).toString();//
			sql.append("left join "+key+" "+value+" on c1.id = "+value+".publicInstitution_id ");
        }
		sql.append("where 1=1 and c1.removed = 'N' ");
		for(String key:m.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
			String value = m.get(key).toString();//
			sql.append("and "+value+".removed = 'N' ");
        }
		for(BusinessParam s : spList){
			sql.append(" AND  "+s.getCode1()+" "+s.getCode3()+" '"+s.getCode2()+"' ");
		}
		sql.append(" group by c1.id) order by SH_REPORT_DATE desc");//上报时间
		
		Query query =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(PublicInstitution.class);
		List<PublicInstitution> list1=query.list();
		query.setFirstResult((page - 1) * limit);  
		query.setMaxResults(limit); 
        List<PublicInstitution> list =query.list();  
		List<PublicInstitutionVO> listVO=new ArrayList<>();
		for (PublicInstitution s : list) {
			PublicInstitutionVO vo = new PublicInstitutionVO(s);
			listVO.add(vo);
		}

		Page<PublicInstitutionVO> pages = new Page<>((page-1)*limit, page, list1.size(), limit, listVO);
		return pages;
	}


	
}
