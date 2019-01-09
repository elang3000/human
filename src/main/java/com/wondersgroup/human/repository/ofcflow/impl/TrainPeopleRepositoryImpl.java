/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPeopleRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月15日 上午9:11:42 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月15日 上午9:11:42 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.TrainPeople;
import com.wondersgroup.human.repository.ofcflow.TrainPeopleRepository;

/** 
 * @ClassName: TrainPeopleRepositoryImpl 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年11月15日 上午9:11:42
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class TrainPeopleRepositoryImpl extends GenericRepositoryImpl<TrainPeople> implements TrainPeopleRepository{

	public Class<TrainPeople> getEntityClass() {
		return TrainPeople.class;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofcflow.TrainPeopleRepository#exportByPerson(java.util.Date, java.util.Date) 
	 */
	@Override
	public List<?> exportByPerson(String start,String end) {
		StringBuilder sql=new StringBuilder();
		sql.append("select a.A01001,con.name,wm_concat(ht.TRAIN_CLASS_NAME),sum(ht.hours) from human_train_people tp  ");
		sql.append("left join human_train ht on tp.train_id = ht.id  ");
		sql.append("left join A01 a on a.id = tp.servant_id  ");
		sql.append("left join CS_ORGAN_NODE con on con.id = tp.organid  ");
		sql.append("where tp.removed = 'N' and   ht.removed = 'N' and ht.status = 9 ");
		if(start!=null){
			sql.append("and ht.start_date >= :start ");
		}
		if(end!=null){
			sql.append("and ht.end_date <= :end ");
		}
		sql.append("group by con.name,a.A01001  ");
		Query query =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		query.setParameter("start", start);
		query.setParameter("end", end);
	    List list=query.list();
		return list;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofcflow.TrainPeopleRepository#exportByUnit(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public List<?> exportByUnit(String start, String end, Integer hour1, Integer hour2) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT AAA,IFNULL(BBB,0),IFNULL(CCC,0),IFNULL(DDD,0),IFNULL(EEE,0),IFNULL(FFF,0),IFNULL(GGG,0) FROM (");
		
		
		//前三项数据   单位id，完成一定学时，完成最低学时
		sql.append("select ORGANID as aaa,ifnull(sum(bb), 0) as bbb,ifnull(sum(cc), 0) as ccc from(");
		sql.append("SELECT ORGANID,CASE WHEN totalhours >= :hour1 THEN COUNT(SERVANT_ID) END bb,CASE WHEN totalhours >= :hour2 THEN COUNT(SERVANT_ID) END cc FROM(");
		sql.append("select ORGANID,SERVANT_ID,sum(hours) totalhours from human_train_people tp ");
		sql.append("left join human_train tr on tr.id = tp.train_id ");
		sql.append("where tp.removed = 'N' and tr.removed = 'N' and tr.status = 9 ");
		sql.append("and tr.start_date >= :start ");
		sql.append("and tr.end_date <= :end ");		
		sql.append("group by SERVANT_ID,ORGANID)group by ORGANID,totalhours)group by organid) A LEFT JOIN (");
		//结束
		
		//第四项数据 本市及以上单位组织的脱产培训
		sql.append("select organid,count(servant_id) AS DDD from human_train_people tp ");
		sql.append("left join human_train tr on tr.id = tp.train_id ");
		sql.append("LEFT JOIN CF_CODE_INFO CI ON CI.ID = tr.level ");
		sql.append("LEFT JOIN CF_CODE_INFO CI2 ON CI2.ID = tr.nature ");
		sql.append("where ci.type_id = (select id from cf_code_type where code = 'TRAIN_LEVEL') and (ci.code = '1' or ci.code = '4') ");
		sql.append("and ci2.type_id = (select id from cf_code_type where code = '0114') and ci2.code = '1' ");
		sql.append("and tp.removed = 'N' and tr.removed = 'N' and tr.status = 9 ");
		sql.append("and tr.start_date >= :start ");
		sql.append("and tr.end_date <= :end ");		
		sql.append("group by organid ) B ON A.AAA = B.ORGANID LEFT JOIN (");
		//结束
		
		//第五项数据  本级单位组织的脱产培训
		sql.append("select organid,count(servant_id) AS EEE from human_train_people tp ");
		sql.append("left join human_train tr on tr.id = tp.train_id ");
		sql.append("LEFT JOIN CF_CODE_INFO CI ON CI.ID = tr.level ");
		sql.append("LEFT JOIN CF_CODE_INFO CI2 ON CI2.ID = tr.nature ");
		sql.append("where ci.type_id = (select id from cf_code_type where code = 'TRAIN_LEVEL') and ci.code = '3'");
		sql.append("and ci2.type_id = (select id from cf_code_type where code = '0114') and ci2.code = '1' ");
		sql.append("and tp.removed = 'N' and tr.removed = 'N' and tr.status = 9 ");
		sql.append("and tr.start_date >= :start ");
		sql.append("and tr.end_date <= :end ");	
		sql.append("group by organid ) C ON A.AAA = C.ORGANID LEFT JOIN (");
		//结束
		
		//第六项数据  参加境外培训人次
		sql.append("select organid,count(servant_id) AS FFF from human_train_people tp ");
		sql.append("left join human_train tr on tr.id = tp.train_id ");
		sql.append("LEFT JOIN CF_CODE_INFO CI ON CI.ID = tr.is_abroad  ");
		sql.append("where ci.type_id = (select id from cf_code_type where code = 'DM215') and ci.code = '1'  ");
		sql.append("and tp.removed = 'N' and tr.removed = 'N' and tr.status = 9 ");
		sql.append("and tr.start_date >= :start ");
		sql.append("and tr.end_date <= :end ");	
		sql.append("group by organid ) D ON A.AAA = D.ORGANID LEFT JOIN (");
		//结束
		
		//第七项数据  组织部门投入的干部教育培训专项经费（万元）
		sql.append("select hostorgan_id,sum(funds) as GGG from human_train tr ");
		sql.append("where tr.removed = 'N' and tr.status = 9 ");
		sql.append("and tr.start_date >= :start ");
		sql.append("and tr.end_date <= :end ");	
		sql.append("group by hostorgan_id) E ON A.AAA = E.hostorgan_id ");
		//结束
		
		Query query =this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("hour1", hour1);
		query.setParameter("hour2", hour2);
		
	    List list=query.list();
		return list;
	}

}
