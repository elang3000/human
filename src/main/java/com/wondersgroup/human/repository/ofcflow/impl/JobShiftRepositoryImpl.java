package com.wondersgroup.human.repository.ofcflow.impl;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.ofcflow.JobShift;
import com.wondersgroup.human.repository.ofcflow.JobShiftRepository;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JobShiftRepositoryImpl extends GenericRepositoryImpl<JobShift> implements JobShiftRepository{

	@Autowired
	private DictableService dictableService;

	@Override
	public Class<JobShift> getEntityClass() {
		return JobShift.class;
	}

	@Override
	public Page<Map> getIndexData(String orgId, String jobChangeType, String name, Integer page, Integer limit) {
		List<CodeInfo> jobChangeCodes = new ArrayList<>();
		List<String> posttenurechanges = new ArrayList<>();
		if(StringUtils.isNotBlank(jobChangeType)&&jobChangeType.equals("JOBSHIFT_PROMOTE")){
			//职务变动代码DM006 职务晋升
			jobChangeCodes = dictableService.findCodeInfoByCodeType("DM006", "2");

		}else if(StringUtils.isNotBlank(jobChangeType)&&jobChangeType.equals("JOBSHIFT_DEMOTE")){
			//职务变动代码 降职
			jobChangeCodes.add(dictableService.getCodeInfoByCode("5", "DM006"));
		}else if(StringUtils.isNotBlank(jobChangeType)&&jobChangeType.equals("JOBSHIFT_DEPOSE")){
			//职务变动代码 免职
			jobChangeCodes = dictableService.findCodeInfoByCodeType("DM006", "4");
		}else if(StringUtils.isNotBlank(jobChangeType)&&jobChangeType.equals("JOBSHIFT_SHIFT")){
			//职务变动代码 轮岗
			jobChangeCodes.add(dictableService.getCodeInfoByCode("6", "DM006"));
		}
		for(CodeInfo codeInfo:jobChangeCodes){
			posttenurechanges.add(codeInfo.getId());
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*, ");
		sql.append("       a.A01001 name, ");
		sql.append("       a.A01085 cardNo, ");
		sql.append("       c.name POSTTENURECHANGENAME, ");
		sql.append("       to_char(t.create_time, 'yyyy-MM-dd HH24:mi:ss') createtime, ");
		sql.append("       nvl(c1.name,'-') newPostName ");
		sql.append("  from (select jd.id, ");
		sql.append("               'm' jobchangetype, ");
		sql.append("               jd.servant, ");
		sql.append("               jd.create_time, ");
		sql.append("               jd.REMARK, ");
		sql.append("               jd.DISMISSAL_CHANGE POSTTENURECHANGE, ");
		sql.append("               a.A02016A formerPostName, ");
		sql.append("               '-' newPostCode, ");
		sql.append("               jd.sourceOrganNode_id orgid ");
		sql.append("          from HUMAN_SERVANT_JOBSHIFT_DEPOSE jd ");
		sql.append("          left join a02 a ");
		sql.append("            on jd.post = a.id ");
		sql.append("        union ");
		sql.append("        select j.id, ");
		sql.append("               'sjl' jobchangetype, ");
		sql.append("               j.servant, ");
		sql.append("               j.create_time, ");
		sql.append("               j.REMARK, ");
		sql.append("               j.POSTTENURECHANGE, ");
		sql.append("               j.formerPostName, ");
		sql.append("               j.newPostCode, ");
		sql.append("               j.sourceOrganNode_id orgid ");
		sql.append("          from HUMAN_SERVANT_JOBSHIFT j) t ");
		sql.append("  left join a01 a ");
		sql.append("    on t.servant = a.id ");
		sql.append("  left join cf_code_info c ");
		sql.append("    on t.POSTTENURECHANGE = c.id ");
		sql.append("  left join cf_code_info c1 ");
		sql.append("    on t.newPostCode = c1.id ");
		sql.append(" where t.orgid = :orgId ");
		if(StringUtils.isNotBlank(name)){
			sql.append(" and a.A01001 like :name");
		}
		if(posttenurechanges.size()>0) {
			sql.append("   and t.POSTTENURECHANGE in ");
			sql.append("       (:posttenurechange) ");
		}
		sql.append(" order by t.create_time desc; ");
		Query sqlQuery = this.currentSession().createSQLQuery(sql.toString());
		sqlQuery.setParameter("orgId", orgId);
		if(posttenurechanges.size()>0){
			sqlQuery.setParameterList("posttenurechange",posttenurechanges);
		}
		if(StringUtils.isNotBlank(name)){
			sqlQuery.setParameter("name","%"+name+"%");
		}
		List listAll=sqlQuery.list();
		sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		sqlQuery.setFirstResult((page - 1) * limit);
		sqlQuery.setMaxResults(limit);
		List list = sqlQuery.list();
		for(int i=0;i<list.size();i++){
			Map map = (Map)list.get(0);
			map.put("id",map.get("ID"));
		}
		Page<Map> pages = new Page<>((page-1)*limit, page, listAll.size(), limit, list);
		return pages;
	}

	/**
	 * 获取人员所有正在处理的postid
	 *
	 * @param servantId
	 * @return
	 */
	@Override
	public List<String> getHandledPostIds(String servantId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select POST id ");
		sql.append("  from HUMAN_SERVANT_JOBSHIFT_DEPOSE ");
		sql.append(" where servant = :servantId and status!=0");
		sql.append("union ");
		sql.append("select PREPOST id ");
		sql.append("  from HUMAN_SERVANT_JOBSHIFT ");
		sql.append(" where servant = :servantId and status!=0; ");
		Query sqlQuery = this.currentSession().createSQLQuery(sql.toString());
		sqlQuery.setParameter("servantId", servantId);
		List list = sqlQuery.list();
		List<String> result = new ArrayList<>();
		list.forEach(object->{
			result.add((String)object);
		});
		return result;
	}
}
