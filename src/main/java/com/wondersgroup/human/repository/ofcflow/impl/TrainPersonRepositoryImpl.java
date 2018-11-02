/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainPlanRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月13日 上午9:24:41 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月13日 上午9:24:41 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.ofcflow.TrainPerson;
import com.wondersgroup.human.bo.ofcflow.TrainPlan;
import com.wondersgroup.human.bo.ofcflow.TrainYearPlan;
import com.wondersgroup.human.repository.ofcflow.TrainPersonRepository;

/** 
 * @ClassName: TrainPlanRepositoryImpl 
 * @Description: 培训个人数据库类
 * @author: lihao
 * @date: 2018年9月13日 上午9:24:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class TrainPersonRepositoryImpl extends GenericRepositoryImpl<TrainPerson> implements TrainPersonRepository {

	@Autowired
	private DictableService dictableService;
	
	public Class<TrainPerson> getEntityClass() {
		return TrainPerson.class;
	}

	/** 
	 * @see com.wondersgroup.human.repository.ofcflow.TrainPersonRepository#l(java.lang.String) 
	 */
	@Override
	public List<?> exportByUnit(TrainYearPlan typ) {
		
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT AAA as deptId,IFNULL(BBB,0) as countHours,IFNULL(CCC,0) as countHours2,IFNULL(DDD,0) countCity,IFNULL(EEE,0) countUnit,IFNULL(FFF,0) countAbroad,IFNULL(GGG,0) sumFunds FROM( ");
		sql.append("SELECT DEPT_ID aaa,CASE WHEN HOURS >= 450 THEN COUNT(SERVANT_ID) END BBB,CASE WHEN HOURS >= 405 AND HOURS < 450 THEN COUNT(SERVANT_ID) END CCC  FROM ( ");
		sql.append("SELECT DEPT_ID,SERVANT_ID,SUM(aa.HOURS) HOURS FROM HUMAN_TRAIN_PERSON aa left join human_train_plan bb on bb.id = aa.train_id left join human_train_year_plan cc on cc.id = bb.yearplan_id where aa.removed=:removed and cc.id=:yearPlanId and bb.status>=:status and bb.removed=:removed and cc.removed=:removed GROUP BY DEPT_ID,SERVANT_ID)GROUP BY DEPT_ID,HOURS) A ");
		sql.append("left join (");
		sql.append("SELECT DEPT_ID,count(SERVANT_ID) AS DDD FROM HUMAN_TRAIN_PERSON aa left join human_train_plan bb on bb.id = aa.train_id left join human_train_year_plan cc on cc.id = bb.yearplan_id WHERE IS_ABROAD = :isAbroad and nature = :isNature and (level = :level1 or level = :level2) and aa.removed=:removed and cc.id=:yearPlanId and bb.status>=:status  and bb.removed=:removed and cc.removed=:removed GROUP BY DEPT_ID) B ");
		sql.append("on a.aaa=B.DEPT_ID left join (");
		sql.append("SELECT DEPT_ID,COUNT(SERVANT_ID) AS EEE FROM HUMAN_TRAIN_PERSON aa left join human_train_plan bb on bb.id = aa.train_id left join human_train_year_plan cc on cc.id = bb.yearplan_id WHERE IS_ABROAD = :isAbroad and nature = :isNature and level = :level3 and aa.removed=:removed and cc.id=:yearPlanId and bb.status>=:status  and bb.removed=:removed and cc.removed=:removed GROUP BY DEPT_ID) C ");
		sql.append("on a.aaa=C.DEPT_ID left join (");
		sql.append("SELECT DEPT_ID,COUNT(SERVANT_ID) AS FFF FROM HUMAN_TRAIN_PERSON aa left join human_train_plan bb on bb.id = aa.train_id left join human_train_year_plan cc on cc.id = bb.yearplan_id WHERE IS_ABROAD != :isAbroad and aa.removed=:removed and cc.id=:yearPlanId and bb.status>=:status  and bb.removed=:removed and cc.removed=:removed GROUP BY DEPT_ID) D ");
		sql.append("on a.aaa=D.DEPT_ID left join (");
		sql.append("SELECT DEPT_ID,sum(funds) AS GGG FROM HUMAN_TRAIN_PERSON aa left join human_train_plan bb on bb.id = aa.train_id left join human_train_year_plan cc on cc.id = bb.yearplan_id where aa.removed=:removed and cc.id=:yearPlanId and bb.status>=:status  and bb.removed=:removed and cc.removed=:removed GROUP BY DEPT_ID) E ");
		sql.append("on a.aaa=E.DEPT_ID ");
		
		SQLQuery query = currentSession().createSQLQuery(sql.toString());
		query.setString("removed","N");
		query.setString("yearPlanId",typ.getId());
		query.setString("status",String.valueOf(TrainPlan.STATUS_TRAIN_PLAN_PASS));
		CodeInfo isAbroad = dictableService.getCodeInfoByCode(TrainPerson.IS_ABROAD_CODE, "DM215");// 非境外培训
		query.setString("isAbroad",isAbroad.getId());
		CodeInfo isNature = dictableService.getCodeInfoByCode(TrainPerson.IS_NATURE_CODE, "0114");// 全脱产培训
		query.setString("isNature",isNature.getId());
		CodeInfo level1 = dictableService.getCodeInfoByCode(TrainPerson.level1, "TRAIN_LEVEL");// 市级培训
		query.setString("level1",level1.getId());
		CodeInfo level2 = dictableService.getCodeInfoByCode(TrainPerson.level2, "TRAIN_LEVEL");// 市级以上培训
		query.setString("level2",level2.getId());
		CodeInfo level3 = dictableService.getCodeInfoByCode(TrainPerson.level3, "TRAIN_LEVEL");// 本单位培训
		query.setString("level3",level3.getId());
		List<?> list = query.list();
		return list;
	}

	/** (non Javadoc) 
	 * @Title: exportByPerson
	 * @Description: TODO
	 * @param typ
	 * @return 
	 * @see com.wondersgroup.human.repository.ofcflow.TrainPersonRepository#exportByPerson(com.wondersgroup.human.bo.ofcflow.TrainYearPlan) 
	 */
	@Override
	public List<?> exportByPerson(TrainYearPlan typ) {
		StringBuilder sql=new StringBuilder();
		sql.append("select a.A01001,con.name,wm_concat(bb.train_name),sum(aa.hours),sum(aa.funds) from human_train_person aa ");
		sql.append("left join human_train_plan bb on bb.id = aa.train_id ");
		sql.append("left join human_train_year_plan cc on cc.id =  bb.yearplan_id ");
		sql.append("left join CS_ORGAN_NODE con on con.id = aa.dept_id ");
		sql.append("left join A01 a on a.id = aa.servant_id ");
		sql.append("where aa.removed = :removed  and bb.removed = :removed and cc.removed = :removed ");
		sql.append("and cc.id =:yearPlanId and bb.status>=:status ");
		sql.append("group by con.name,a.A01001 ");
		
		SQLQuery query = currentSession().createSQLQuery(sql.toString());
		query.setString("removed","N");
		query.setString("yearPlanId",typ.getId());
		query.setString("status",String.valueOf(TrainPlan.STATUS_TRAIN_PLAN_PASS));
		List<?> list = query.list();
		return list;
	}

}
