/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: HumanKeepRecordVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.record.Impl
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月29日 下午5:17:20
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月29日 下午5:17:20
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.record.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.repository.record.HumanKeepRecordDAO;
import com.wondersgroup.human.vo.record.HumanKeepRecordVO;

@Repository
public class HumanKeepRecordDAOImpl extends GenericRepositoryImpl<HumanKeepRecord> implements HumanKeepRecordDAO {

	@Override
	public Class<HumanKeepRecord> getEntityClass() {

		return HumanKeepRecord.class;
	}

	/** 
	 * @see com.wondersgroup.human.repository.record.HumanKeepRecordDAO#queryServantKeepRecord(java.util.Map, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<HumanKeepRecordVO> queryServantKeepRecord(Map<String, Object> filter, Integer pageNumber,
	        Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A .A01001 AS \"name\",C1. NAME AS \"sex\",A .A01085 AS \"cardNo\","
				+ "A .A01057A AS \"departName\","
				+ "R. unit_type AS \"unitType\",C4. NAME AS \"recordType\","
				+ "c5. NAME AS \"returned\",R. ID AS \"id\","
				+ "c6.NAME AS \"jobLevel\",C7.NAME AS \"postAttributeName\",A.SH_A0704 AS \"postName\"  "
				+ " FROM HUMAN_KEEP_RECORD R "
				+ "LEFT JOIN A01 A ON R.HUMAN_ID = A . ID "
				+ "LEFT JOIN CF_CODE_INFO C1 ON C1. ID = A .A01004 "
				+ "LEFT JOIN CF_CODE_INFO C2 ON C2. ID = A .SH_A0221 "
				+ "LEFT JOIN CF_CODE_INFO C4 ON C4. ID = R .RECORD_TYPE  "
				+ "LEFT JOIN CF_CODE_INFO C5 ON C5. ID = R .RETURNED  "
				+ "LEFT JOIN CF_CODE_INFO C6 ON C6.ID = A.SH_A0702  "
				+ "LEFT JOIN CF_CODE_INFO C7 ON C7.ID = A.SH_A0192E  "
				+ "WHERE R.removed = 'N' AND A .removed = 'N' ");
		if(filter.containsKey("name")){
			sql.append("AND A.A01001 like :name ");
		}
		if(filter.containsKey("cardNo")){
			sql.append("AND A.A01085 like :cardNo ");
		}
		if(filter.containsKey("recordType")){
			sql.append("AND R.RECORD_TYPE = :recordType ");
		}
		
		sql.append("ORDER BY R.CREATE_TIME DESC");
		List<QueryParameter> params = new ArrayList<QueryParameter>();
		Iterator<Entry<String, Object>> iterator = filter.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			params.add(new QueryParameter(entry.getKey(), entry.getValue()));
		}
		return findBySQLWithTranferClass(sql.toString(), params, pageNumber, pageSize, HumanKeepRecordVO.class);
	}
}
