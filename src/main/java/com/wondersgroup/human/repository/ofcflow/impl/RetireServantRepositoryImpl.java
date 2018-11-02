/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RetireServantRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月25日 上午11:33:37 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月25日 上午11:33:37 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.human.bo.ofcflow.RetireServant;
import com.wondersgroup.human.repository.ofcflow.RetireServantRepository;
import com.wondersgroup.human.vo.ofc.ServantVO;
import com.wondersgroup.human.vo.ofcflow.RetireServantVO;
import com.wondersgroup.human.vo.record.HumanKeepRecordVO;

/** 
 * @ClassName: RetireServantRepositoryImpl 
 * @Description: 退休知识库接口实现
 * @author: lihao
 * @date: 2018年6月25日 上午11:33:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class RetireServantRepositoryImpl extends GenericRepositoryImpl<RetireServant> implements RetireServantRepository{

	public Class<RetireServant> getEntityClass() {
		return RetireServant.class;
	}

	/**  
	 * @see com.wondersgroup.human.repository.ofcflow.RetireServantRepository#queryServantKeepRecord(java.util.Map, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<RetireServantVO> queryServantKeepRecord(Map<String, Object> filter, Integer page, Integer limit) {
		StringBuffer sql = new StringBuffer();
		sql.append("select A1.ID AS \"id\",A1.A01001 AS \"name\","
				+ "C1.NAME as \"sex\",A1.A01085 AS \"cardNo\",A1.A01057A AS \"departName\","
				+ "c3.NAME AS \"jobLevel\",C2.NAME AS \"postAttributeName\",A1.SH_A0704 AS \"postName\"  "
				+ "FROM A01 A1 "
				+ "LEFT JOIN CF_CODE_INFO C1 ON C1.ID = A1.A01004  "
				+ "LEFT JOIN CF_CODE_INFO C2 ON C2.ID = A1.SH_A0702  "
				+ "LEFT JOIN CF_CODE_INFO C3 ON C3.ID = A1.SH_A0192E  "
				+ "WHERE A1.removed = 'N' ");
		if(filter.containsKey("name")){
			sql.append("AND A1.A01001 like :name ");
		}
		if(filter.containsKey("cardNo")){
			sql.append("AND A1.A01085 like :cardNo ");
		}
		if(filter.containsKey("sex")){
			sql.append("AND A1.A01004 = :sex ");
		}
		if(filter.containsKey("age")){
			sql.append("AND ROUND(TO_NUMBER(sysdate-A1.A01007))>= (:age*365+:age/4) ");
		}
		
		sql.append("ORDER BY A1.SH_REPORT_DATE DESC");
		List<QueryParameter> params = new ArrayList<QueryParameter>();
		Iterator<Entry<String, Object>> iterator = filter.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			params.add(new QueryParameter(entry.getKey(), entry.getValue()));
		}
		return findBySQLWithTranferClass(sql.toString(), params, page, limit, RetireServantVO.class);
	}

}
