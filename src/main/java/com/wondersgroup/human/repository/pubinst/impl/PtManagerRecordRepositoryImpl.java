/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: PersonnelRecordRepositoryImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl
 * 描述: TODO
 * 创建人: Administrator
 * 创建时间: 2018年6月7日 下午6:00:41
 * 版本号: V1.0
 * 修改人：Administrator
 * 修改时间：2018年6月7日 下午6:00:41
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.repository.pubinst.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.human.bo.pubinst.PtManagerRecord;
import com.wondersgroup.human.repository.pubinst.PtManagerRecordRepository;
import com.wondersgroup.human.vo.ofc.ManagerRecordVO;
import com.wondersgroup.human.vo.pubinst.PtManagerRecordVO;

/**
 * @ClassName: ManagerRecordRepositoryImpl
 * @Description: 人员进出管理 知识库接口实现类
 * @author: lihao
 * @date: 2018年6月7日 下午6:00:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class PtManagerRecordRepositoryImpl extends GenericRepositoryImpl<PtManagerRecord>
        implements PtManagerRecordRepository {
	
	public Class<PtManagerRecord> getEntityClass() {
		
		return PtManagerRecord.class;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.ManagerRecordRepository#queryManagerRecord(java.util.Map, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<PtManagerRecordVO> queryManagerRecord(Map<String, Object> filter, Integer page, Integer limit) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A .A01001 AS \"name\",A.A01004 AS \"sex\",A .A01085 AS \"cardNo\","
				+ "M. ID AS \"id\",M.RECORD_TYPE AS \"recordType\",M.UNIT_TYPE AS \"managerType\","
				+ "M.STATUS AS \"status\",TO_CHAR(M.RECORD_TIME,'YYYY-mm-dd HH24:mi:ss') AS \"recordTime\" "
				+ "FROM HUMAN_MANAGE_RECORD M "
				+ "LEFT JOIN A01 A ON M.HUMAN_ID = A . ID "
				+ "WHERE M.removed = 'N' AND A .removed = 'N' ");
		if(filter.containsKey("name")){
			sql.append("AND A.A01001 like :name ");
		}
		if(filter.containsKey("cardNo")){
			sql.append("AND A.A01085 like :cardNo ");
		}
		if(filter.containsKey("sex")){
			sql.append("AND A.A01004 = :sex ");
		}
		if(filter.containsKey("recordType")){
			sql.append("AND M.RECORD_TYPE = :recordType ");
		}
		if(filter.containsKey("managerType")){
			sql.append("AND M.UNIT_TYPE = :managerType ");
		}
		sql.append("ORDER BY M.CREATE_TIME DESC");
		List<QueryParameter> params = new ArrayList<QueryParameter>();
		Iterator<Entry<String, Object>> iterator = filter.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			params.add(new QueryParameter(entry.getKey(), entry.getValue()));
		}
		return findBySQLWithTranferClass(sql.toString(), params, page, limit, PtManagerRecordVO.class);
	}
}
