/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainingHoursRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月13日 下午5:09:13 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月13日 下午5:09:13 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.pubinst.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.human.bo.pubinst.PtTrainingHours;
import com.wondersgroup.human.repository.pubinst.PtTrainingHoursRepository;
import com.wondersgroup.human.vo.ofc.TrainingHoursVO;
import com.wondersgroup.human.vo.pubinst.PtTrainingHoursVO;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/** 
 * @ClassName: TrainingHoursRepositoryImpl 
 * @Description: 培训 知识库接口实现类
 * @author: lihao
 * @date: 2018年6月13日 下午5:09:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class PtTrainingHoursRepositoryImpl  extends GenericRepositoryImpl<PtTrainingHours> implements PtTrainingHoursRepository{

	/** 
	 * @see com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl#getEntityClass() 
	 */
	@Override
	public Class<PtTrainingHours> getEntityClass() {
		return PtTrainingHours.class;
	}

	/**  
	 * @see com.wondersgroup.human.repository.ofc.TrainingHoursRepository#queryTrainingHours(java.util.Map, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<PtTrainingHoursVO> queryTrainingHours(Map<String, Object> filter, Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT T.TRAIN_NAME AS \"trainName\",to_char(T.TRAIN_HOURS,'9999') AS \"hours\",T.TRAIN_DETAIL AS \"trainDetail\","
				+ "A.A01001 AS \"name\",A .A01085 AS \"cardNo\",A .A01057A AS \"departName\",T.ID AS \"id\",C1. NAME AS \"sex\",A.ID AS \"servantId\" "
				+ "FROM HUMAN_TRAIN_HOURS_RECORD T "
				+ "LEFT JOIN HUMAN_TRAIN_HOURS_SERVANT TH ON TH.TRAIN_HOURS_ID = T . ID "
				+ "LEFT JOIN A01 A ON TH.SERVANT_ID = A . ID "
				+ "LEFT JOIN CF_CODE_INFO C1 ON C1. ID = A .A01004 "
				+ "WHERE T.removed = 'N' AND A .removed = 'N' ");
		if(filter.containsKey("trainName")){
			sql.append("AND T.TRAIN_NAME like :trainName ");
		}
		if(filter.containsKey("name")){
			sql.append("AND A.A01001 like :name ");
		}
		if(filter.containsKey("departName")){
			sql.append("AND A.A01057A like :departName ");
		}
		sql.append("ORDER BY T.CREATE_TIME DESC");
		List<QueryParameter> params = new ArrayList<QueryParameter>();
		Iterator<Entry<String, Object>> iterator = filter.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			params.add(new QueryParameter(entry.getKey(), entry.getValue()));
		}
		return findBySQLWithTranferClass(sql.toString(), params, pageNumber, pageSize, PtTrainingHoursVO.class);
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.TrainingHoursRepository#queryTrainingHours(java.lang.String) 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Page<PtTrainingHoursVO> queryTrainingHours(String trainId) {
		 StringBuffer sql = new StringBuffer();
		 sql.append("SELECT T.TRAIN_NAME AS \"trainName\",to_char(T.TRAIN_HOURS,'9999') AS \"hours\",T.TRAIN_DETAIL AS \"trainDetail\","
					+ "A.A01001 AS \"name\",A .A01085 AS \"cardNo\",A .A01057A AS \"departName\",T.ID AS \"id\",C1. NAME AS \"sex\",A.ID AS \"servantId\" "
					+ "FROM HUMAN_TRAIN_HOURS_RECORD T "
					+ "LEFT JOIN HUMAN_TRAIN_HOURS_SERVANT TH ON TH.TRAIN_HOURS_ID = T . ID "
					+ "LEFT JOIN A01 A ON TH.SERVANT_ID = A . ID "
					+ "LEFT JOIN CF_CODE_INFO C1 ON C1. ID = A .A01004 "
		 			+ "WHERE T.removed = 'N' AND A .removed = 'N' AND T.ID = :id ");
		 List<QueryParameter> params = new ArrayList<QueryParameter>();
		 params.add(new QueryParameter("id", trainId));
		 
        SQLQuery query = currentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(TrainingHoursVO.class));
        query.setParameter("id", trainId);
		List<TrainingHoursVO> list = query.list();
	    return new Page(0, 100, 100, 100, list);
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.TrainingHoursRepository#queryTrainingHoursServantList(java.util.List, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<PublicInstitutionVO> queryTrainingHoursServantList(Map<String, Object> filter, Integer page, Integer limit) {
		 StringBuffer sql = new StringBuffer();
		 sql.append("SELECT A.A01001 AS \"name\",A .A01085 AS \"cardNo\",A .A01057A AS \"departName\",A.ID AS \"id\",C1. NAME AS \"sex\" "
					+ "FROM A01 A "
					+ "LEFT JOIN CF_CODE_INFO C1 ON C1. ID = A .A01004 "
		 			+ "WHERE A.removed = 'N'  AND A.ID  not in "
		 			+ "(SELECT SERVANT_ID FROM HUMAN_TRAIN_HOURS_SERVANT WHERE TRAIN_HOURS_ID = :trainId) AND A.A01063 = :isOnHold ");
			if(filter.containsKey("name")){
				sql.append("AND A.A01001 like :name ");
			}
			if(filter.containsKey("cardNo")){
				sql.append("AND T.A01085 like :cardNo ");
			}
			if(filter.containsKey("sex")){
				sql.append("AND A.A01004 = :sex ");
			}
			sql.append("ORDER BY A.SH_REPORT_DATE DESC");
			List<QueryParameter> params = new ArrayList<QueryParameter>();
			Iterator<Entry<String, Object>> iterator = filter.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				params.add(new QueryParameter(entry.getKey(), entry.getValue()));
			}
		return findBySQLWithTranferClass(sql.toString(), params, page, limit, PublicInstitutionVO.class);
	}

}

	
    