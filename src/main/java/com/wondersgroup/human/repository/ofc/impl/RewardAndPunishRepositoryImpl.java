/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RewardAndPunishRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年7月12日 上午11:05:34 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年7月12日 上午11:05:34 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.repository.ofc.RewardAndPunishRepository;
import com.wondersgroup.human.vo.analysis.RewardCountVO;

/** 
 * @ClassName: RewardAndPunishRepositoryImpl 
 * @Description: 奖惩知识库接口实现
 * @author: lihao
 * @date: 2018年7月12日 上午11:05:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class RewardAndPunishRepositoryImpl extends GenericRepositoryImpl<RewardAndPunish> implements RewardAndPunishRepository{

	public Class<RewardAndPunish> getEntityClass() {
		return RewardAndPunish.class;
	}

	@Override
	public Date getLatestThirdClassDate(Servant servant) {
		StringBuilder sql=new StringBuilder();
		sql.append("select max(t.A14225) from a14 t where A14205B=(select cci.id ");
		sql.append("  from cf_code_info cci ");
		sql.append(" where cci.type_id = (select cct.id ");
		sql.append("                        from cf_code_type cct ");
		sql.append("                       where cct.code = 'GBT_8563_1_2005') ");
		sql.append("   and cci.name = '记三等功') and t.SERVANT_ID=:servantId order by t.A14225 ");
		Query queryObject =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
        queryObject.setParameter("servantId", servant.getId());
        Date date = (Date)queryObject.uniqueResult();
		return date;
	}
	
	/** 
	 * @see com.wondersgroup.human.repository.ofc.StudyRepository#getRewardCount(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<RewardCountVO> getRewardCount(String departName, Integer year, Integer page, Integer limit) {
		StringBuilder sql=new StringBuilder();
		sql.append("select organid as departId,B01004 as departName,ifnull(A.aaa,0) as houor,ifnull(B.bbb,0) as commendation, ");
		sql.append("ifnull(C.ccc,0) as firReward,ifnull(D.ddd,0) as secReward,ifnull(E.eee,0) as thiReward ");
		sql.append("from B01 b1 ");

		//荣誉称号
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as aaa from a14 a14 ");//关联荣誉称号人数 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1119' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) A on A.departId = b1.organid ");//结束
		
		//嘉奖
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as bbb from a14 a14 ");//关联嘉奖人数 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1118' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) B on B.departId = b1.organid ");//结束
		
		//一等功
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as ccc from a14 a14 ");//关联一等功 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1111' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) C on C.departId = b1.organid ");//结束
		
		//二等功
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as ddd from a14 a14 ");//关联二等功人数 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1112' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) D on D.departId = b1.organid ");//结束
		
		//三等功
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as eee from a14 a14 ");//关联三等功人数 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1113' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) E on E.departId = b1.organid ");//结束
		
		sql.append("where removed = 'N' ");
		if(StringUtils.isNotBlank(departName)){
			sql.append("and B01004 like :departName ");
		}

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
		List<RewardCountVO> listVO=new ArrayList<>();
		for (Object object : list) {
			Map<String,Object> map=(Map<String,Object>)object;
			listVO.add(new RewardCountVO(map));
		}

		Page<RewardCountVO> pages = new Page<>((page-1)*limit, page, listAll.size(), limit, listVO);
		return pages;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofc.RewardAndPunishRepository#getRewardCountByDepartId(java.lang.String, java.lang.Integer) 
	 */
	@Override
	public Map<String, BigDecimal> getRewardCountByDepartId(String departId, Integer year) {
		StringBuilder sql=new StringBuilder();
		sql.append("select ifnull(A.aaa,0) as \"授予荣誉称号\",ifnull(B.bbb,0) as \"嘉奖\", ");
		sql.append("ifnull(C.ccc,0) as \"记一等功\",ifnull(D.ddd,0) as \"记二等功\",ifnull(E.eee,0) as \"记三等功\" ");
		sql.append("from B01 b1 ");

		//荣誉称号
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as aaa from a14 a14 ");//关联荣誉称号人数 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1119' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) A on A.departId = b1.organid ");//结束
		
		//嘉奖
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as bbb from a14 a14 ");//关联嘉奖人数 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1118' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) B on B.departId = b1.organid ");//结束
		
		//一等功
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as ccc from a14 a14 ");//关联一等功 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1111' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) C on C.departId = b1.organid ");//结束
		
		//二等功
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as ddd from a14 a14 ");//关联二等功人数 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1112' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) D on D.departId = b1.organid ");//结束
		
		//三等功
		sql.append("left join  ");
		sql.append("(select departId,count(A01057A) as eee from a14 a14 ");//关联三等功人数 开始
		sql.append("join a01 a1 on a1.id=a14.servant_id ");
		sql.append("JOIN CF_CODE_INFO TP ON TP.ID = a14.A14205B AND TP.TYPE_ID = (SELECT ID FROM CF_CODE_TYPE WHERE CODE = 'GBT_8563_1_2005')  AND TP.CODE = '1113' ");
		sql.append("where a14.removed = 'N' ");
		if(year!=null){
			sql.append("and a14.A14225 between :start and :end ");//时间
		}
		sql.append("group by departId,A01057A) E on E.departId = b1.organid ");//结束
		
		sql.append("where removed = 'N' ");
		sql.append("and organid = :departId ");

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
