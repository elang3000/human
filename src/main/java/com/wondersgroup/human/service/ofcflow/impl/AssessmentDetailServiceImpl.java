
package com.wondersgroup.human.service.ofcflow.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.core.util.DateUtils;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.bo.ofc.Assessment;
import com.wondersgroup.human.bo.ofcflow.AssessmentDetail;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowCollect;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowUnitPercent;
import com.wondersgroup.human.service.ofc.AssessmentService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.AssessmentDetailService;
import com.wondersgroup.human.service.ofcflow.AssessmentFlowUnitPercentService;

@Service
public class AssessmentDetailServiceImpl extends GenericServiceImpl<AssessmentDetail>
        implements AssessmentDetailService {
	
	@Autowired
	private AssessmentFlowUnitPercentService assessmentFlowUnitPercentService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private AssessmentService assessmentService;
	
	
	@Override
	public Page<AssessmentDetail> getCurrentUnitDetailsPage(OrganNode org, String assessCollectId,String resultId,String name, Integer page, Integer limit) {
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("select t from AssessmentDetail t left join t.servant where t.removed=:removed and t.servant.departId=:departId and t.assessmentFlowCollect.id=:assessCollectId  ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("departId", org.getId());
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("assessCollectId", assessCollectId);
		listqueryparameter.add(queryParameteritem);
		if(StringUtils.isNotBlank(name)){
			hql.append(" and t.servant.name like :name ");
			queryParameteritem=new QueryParameter("name", "%"+name+"%");
			listqueryparameter.add(queryParameteritem);
		}
		if(StringUtils.isNotBlank(resultId)){
			hql.append(" and t.result.id= :resultId");
			queryParameteritem=new QueryParameter("resultId", resultId);
			listqueryparameter.add(queryParameteritem);
		}
		hql.append(" order by t.servant.id ");
		Page<AssessmentDetail> pageInfo = this.findByHQL(hql.toString(), listqueryparameter, page, limit);
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AssessmentDetail.class);
//		detachedCriteria.add(Restrictions.eq("servant.", org.getId()));
//		detachedCriteria.add(Restrictions.eq("removed", false));
//		if(StringUtils.isNotBlank(name)){
//			detachedCriteria.add(Restrictions.eq("servant.name", name));
//		}
//		Page<AssessmentDetail> allActiveServant = assessmentDetailRepository.findByCriteria(detachedCriteria, page,limit);
		return pageInfo;
	}
	
	@Override
	public List<AssessmentDetail> getCurrentUnitDetails(OrganNode org,String assessCollectId) {
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("select t from AssessmentDetail t left join t.servant where t.removed=:removed and t.servant.departId=:departId and t.assessmentFlowCollect.id=:assessCollectId");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("departId", org.getId());
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("assessCollectId", assessCollectId);
		listqueryparameter.add(queryParameteritem);
		List<AssessmentDetail> list = this.findByHQL(hql.toString(), listqueryparameter);
		return list;
	}

	@Override
	public void complete(OrganNode org, AssessmentFlowCollect assessmentFlowCollect) {
		AssessmentFlowUnitPercent percent = this.assessmentFlowUnitPercentService.getByCollectAndOrg(assessmentFlowCollect.getId(), org);
		//percent为空表示是季度考核,季度考核完成插入子集数据,percnet不为空表示是年度考核

		List<AssessmentDetail> currentUnitDetails = this.getCurrentUnitDetails(org,assessmentFlowCollect.getId());
		if(percent==null){
			for (AssessmentDetail assessmentDetail : currentUnitDetails) {
				Assessment assessment=new Assessment();
				assessment.setServant(assessmentDetail.getServant());
				//考核结论代码DM017  14 季度考核,季度考核是新增的,国标中没有
				CodeInfo seasonCodeInfo = dictableService.getCodeInfoByCode("14", "DM017");
				assessment.setCategory(seasonCodeInfo);
				Date dateYear = DateUtils.parseDate(assessmentFlowCollect.getYear().toString(), "yyyy");
				assessment.setStartDate(getStartDate(assessmentFlowCollect.getYear(),assessmentFlowCollect.getSeason()));
				assessment.setEndDate(getEndDate(assessmentFlowCollect.getYear(),assessmentFlowCollect.getSeason()));
				assessment.setOrganizationName(assessmentDetail.getServant().getDepartName());
				assessment.setOpinion(assessmentDetail.getRemarks());
				assessment.setAllParticipantName(assessmentDetail.getLastOperator());
				assessment.setConclusionCategory(assessmentDetail.getResult());
				assessment.setAssessmentYear(dateYear);
				assessmentService.save(assessment);
				System.out.println(assessment);
			}
		}
		for (AssessmentDetail assessmentDetail : currentUnitDetails) {
			//设置状态为1
			assessmentDetail.setStatus(CommonConst.YES);
		}
//		this.saveAll(AssessmentDetail.class, currentUnitDetails);
	}
	
	/**
	 * 
	 * @Title: getStartDate 
	 * @Description: 通过季度获取开始时间
	 * @param season
	 * @return
	 * @return: Date
	 */
	public Date getStartDate(Integer year,Integer season){
		String dateStr=year+String.format("%02d", (season-1)*3+1);
		return DateUtils.parseDate(dateStr, "yyyyMM");
	}
	
	/**
	 * 
	 * @Title: getStartDate 
	 * @Description: 通过季度获取结束时间
	 * @param season
	 * @return
	 * @return: Date
	 */
	public Date getEndDate(Integer year,Integer season){
	     Calendar calendar = Calendar.getInstance();
	      // 设置时间,当前时间不用设置
	      calendar.set(Calendar.YEAR, year);
	      calendar.set(Calendar.MONTH, season*3);
	      calendar.set(Calendar.DAY_OF_MONTH, 1); 
	      calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
	      return calendar.getTime();
	}

	@Override
	public boolean isAssessAll(OrganNode org, String assessCollectId) {
		List<AssessmentDetail> currentUnitDetails = getCurrentUnitDetails(org,assessCollectId);
		List<AssessmentDetail> allAssessDetails = getAllAssessDetails(org,assessCollectId);
		//假如考核单下所有人员数和已经考核的人员数相等,则考核已经完成
		if(currentUnitDetails.size()==allAssessDetails.size()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<AssessmentDetail> getAllAssessDetails(OrganNode org, String assessCollectId) {

		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("select t from AssessmentDetail t left join t.servant where t.removed=:removed  and t.assessmentFlowCollect.id=:assessCollectId and t.result.id is not null");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		if(org != null){
			queryParameteritem=new QueryParameter("departId", org.getId());
			listqueryparameter.add(queryParameteritem);
			hql.append(" and t.servant.departId=:departId");
		}
//		queryParameteritem=new QueryParameter("departId", org.getId());
//		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("assessCollectId", assessCollectId);
		listqueryparameter.add(queryParameteritem);
		List<AssessmentDetail> list = this.findByHQL(hql.toString(), listqueryparameter);
		return list;
	
	}

	@Override
	public boolean isConfirmAssess(OrganNode org, String assessCollectId) {
		List<AssessmentDetail> currentUnitDetails = getCurrentUnitDetails(org,assessCollectId);
		List<AssessmentDetail> allAssessDetails = getAllAssessStatusDetails(org,assessCollectId);
		//假如考核单下所有人员数和已经考核的人员数相等,则考核已经完成
		if(currentUnitDetails.size()==allAssessDetails.size()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<AssessmentDetail> getAllAssessStatusDetails(OrganNode org, String assessCollectId) {

		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("select t from AssessmentDetail t left join t.servant where t.removed=:removed  and t.assessmentFlowCollect.id=:assessCollectId and t.status=:status");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		if(org != null){
			queryParameteritem=new QueryParameter("departId", org.getId());
			listqueryparameter.add(queryParameteritem);
			hql.append(" and t.servant.departId=:departId");
		}

		queryParameteritem=new QueryParameter("assessCollectId", assessCollectId);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("status", CommonConst.YES);
		listqueryparameter.add(queryParameteritem);
		List<AssessmentDetail> list = this.findByHQL(hql.toString(), listqueryparameter);
		return list;
	
	}

	@Override
	public List<AssessmentDetail> getAllFineDetails(String assessCollectId) {
		//考核结论代码DM018  1优秀
		CodeInfo fineCodeInfo = dictableService.getCodeInfoByCode("1", "DM018");
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("select t from AssessmentDetail t left join t.servant where t.removed=:removed  and t.assessmentFlowCollect.id=:assessCollectId and t.status=:status and result=:result order by t.servant.departId");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("assessCollectId", assessCollectId);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("status", CommonConst.YES);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("result", fineCodeInfo.getId());
		listqueryparameter.add(queryParameteritem);
		List<AssessmentDetail> list = this.findByHQL(hql.toString(), listqueryparameter);
		return list;
	
	}

	@Override
	public List<AssessmentDetail> getAllDetails(Object object, String collectId) {
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("select t from AssessmentDetail t left join t.servant where t.removed=:removed  and t.assessmentFlowCollect.id=:assessCollectId  order by t.servant.departId");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("assessCollectId", collectId);
		listqueryparameter.add(queryParameteritem);
		List<AssessmentDetail> list = this.findByHQL(hql.toString(), listqueryparameter);
		return list;
	
	}
	
}
