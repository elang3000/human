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

package com.wondersgroup.human.repository.ofc.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.repository.ofc.ManagerRecordRepository;
import com.wondersgroup.human.vo.analysis.MagCountVO;
import com.wondersgroup.human.vo.ofc.ManagerRecordVO;

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
public class ManagerRecordRepositoryImpl extends GenericRepositoryImpl<ManagerRecord>
        implements ManagerRecordRepository {
	
	public Class<ManagerRecord> getEntityClass() {
		
		return ManagerRecord.class;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.ManagerRecordRepository#queryManagerRecord(java.util.Map, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ManagerRecordVO> queryManagerRecord(Map<String, Object> filter, Integer page, Integer limit) {
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
		return findBySQLWithTranferClass(sql.toString(), params, page, limit, ManagerRecordVO.class);
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.ManagerRecordRepository#getMagCount(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<MagCountVO> getMagCount(String departName, Integer year, Integer page, Integer limit) {
		StringBuilder sql=new StringBuilder();
		sql.append("select organid as departId,B01001 as departName,ifnull(A.aaa,0) as inMag, ");
		sql.append("ifnull(B.bbb,0) as outMag,ifnull(C.ccc,0) as mag ");
		sql.append("from B01 b1 ");

		//进
		sql.append("left join  ");
		sql.append("(select b11.organid as org,count(ir.item_type) as aaa from b01 b11 ");//开始
		sql.append("join HUMAN_ITEM_RECORD ir on ir.ORGAN_ID = b11.organid ");
		sql.append("where ir.removed = 'N' and ir.item_type = 0 "); //0-进
		if(year!=null){
			sql.append("and ir.RECORD_TIME between :start and :end ");//时间
		}
		sql.append("group by b11.organid) A on A.org = b1.organid ");//结束
		
		//出
		sql.append("left join  ");
		sql.append("(select b11.organid as org,count(ir.item_type) as bbb from b01 b11 ");//开始
		sql.append("join HUMAN_ITEM_RECORD ir on ir.ORGAN_ID = b11.organid ");
		sql.append("where ir.removed = 'N' and ir.item_type = 1 "); //0-出
		if(year!=null){
			sql.append("and ir.RECORD_TIME between :start and :end ");//时间
		}
		sql.append("group by b11.organid) B on B.org = b1.organid ");//结束
		
		//管理
		sql.append("left join  ");
		sql.append("(select b11.organid as org,count(ir.item_type) as ccc from b01 b11 ");//开始
		sql.append("join HUMAN_ITEM_RECORD ir on ir.ORGAN_ID = b11.organid ");
		sql.append("where ir.removed = 'N' and ir.item_type = 2 "); //0-管理
		if(year!=null){
			sql.append("and ir.RECORD_TIME between :start and :end ");//时间
		}
		sql.append("group by b11.organid) C on C.org = b1.organid ");//结束
		
		
		sql.append("where removed = 'N' ");
		if(StringUtils.isNotBlank(departName)){
			sql.append("and B01001 like :departName ");
		}
		sql.append("order by create_time desc ");
		
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		if(StringUtils.isNotBlank(departName)){
			queryObject.setParameter("departName", "%"+departName+"%");
		}
		if(year!=null){
			queryObject.setParameter("start", DateUtils.getYearFirst(year));
			queryObject.setParameter("end", DateUtils.getYearLast(year));
		}
	    List listAll=queryObject.list();
	 	queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        queryObject.setFirstResult((page - 1) * limit);  
        queryObject.setMaxResults(limit); 
		List list = queryObject.list();
		List<MagCountVO> listVO=new ArrayList<>();
		for (Object object : list) {
			Map<String,Object> map=(Map<String,Object>)object;
			listVO.add(new MagCountVO(map));
		}

		Page<MagCountVO> pages = new Page<>((page-1)*limit, page, listAll.size(), limit, listVO);
		return pages;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.ManagerRecordRepository#getMagCountByDepartId(java.lang.String, java.lang.Integer) 
	 */
	@Override
	public Map<String, BigDecimal> getMagCountByDepartId(String departId, Integer year) {
		StringBuilder sql=new StringBuilder();
		sql.append("select ifnull(A.aaa,0) as \"1\", ");
		sql.append("ifnull(B.bbb,0) as \"2\",ifnull(C.ccc,0) as \"3\" ");
		sql.append("from B01 b1 ");

		//进
		sql.append("left join  ");
		sql.append("(select b11.organid as org,count(ir.item_type) as aaa from b01 b11 ");//开始
		sql.append("join HUMAN_ITEM_RECORD ir on ir.ORGAN_ID = b11.organid ");
		sql.append("where ir.removed = 'N' and ir.item_type = 0 "); //0-进
		if(year!=null){
			sql.append("and ir.RECORD_TIME between :start and :end ");//时间
		}
		sql.append("group by b11.organid) A on A.org = b1.organid ");//结束
		
		//出
		sql.append("left join  ");
		sql.append("(select b11.organid as org,count(ir.item_type) as bbb from b01 b11 ");//开始
		sql.append("join HUMAN_ITEM_RECORD ir on ir.ORGAN_ID = b11.organid ");
		sql.append("where ir.removed = 'N' and ir.item_type = 1 "); //0-出
		if(year!=null){
			sql.append("and ir.RECORD_TIME between :start and :end ");//时间
		}
		sql.append("group by b11.organid) B on B.org = b1.organid ");//结束
		
		//管理
		sql.append("left join  ");
		sql.append("(select b11.organid as org,count(ir.item_type) as ccc from b01 b11 ");//开始
		sql.append("join HUMAN_ITEM_RECORD ir on ir.ORGAN_ID = b11.organid ");
		sql.append("where ir.removed = 'N' and ir.item_type = 2 "); //0-管理
		if(year!=null){
			sql.append("and ir.RECORD_TIME between :start and :end ");//时间
		}
		sql.append("group by b11.organid) C on C.org = b1.organid ");//结束
		
		sql.append("where removed = 'N' ");
		sql.append("and organid = :departId ");
		sql.append("order by create_time desc ");
		
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		queryObject.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		queryObject.setParameter("departId", departId);
		if(year!=null){
			queryObject.setParameter("start", DateUtils.getYearFirst(year));
			queryObject.setParameter("end", DateUtils.getYearLast(year));
		}
		Map<String, BigDecimal> result = (Map<String, BigDecimal>) queryObject.uniqueResult();
		
		return result;
	}
}
