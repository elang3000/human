/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantReportServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 录用信息上报 服务接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月20日 下午2:29:32 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月20日 下午2:29:32 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantRelationReport;
import com.wondersgroup.human.bo.ofcflow.DraftServantReport;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;
import com.wondersgroup.human.bo.ofcflow.ProbationTimeLength;
import com.wondersgroup.human.service.ofcflow.DraftServantRelationReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantReportService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.service.ofcflow.ProbationServantService;
import com.wondersgroup.human.service.ofcflow.ProbationTimeLengthService;

/** 
 * @ClassName: DraftServantReportServiceImpl 
 * @Description: 录用信息上报 服务接口实现类
 * @author: tzy
 * @date: 2018年7月20日 下午2:29:32
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DraftServantReportServiceImpl extends GenericServiceImpl<DraftServantReport> implements DraftServantReportService{
	
	@Autowired
	private DraftServantService draftServantService;
	@Autowired
	private DraftServantRelationReportService relationReportService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ProbationServantService probationServantService;
	@Autowired
	private CodeInfoService codeInfoService;
	@Autowired
	private ProbationTimeLengthService probationTimeLengthService;
	
	/**
	 * @Title: createReportWork 
	 * @Description: 保存上报信息，启动流程
	 * @param ids	上报的录用人员id，逗号隔开形式
	 * @param type	0：暂存	1：提交
	 * @param report	上报信息，两个附件的ftp路径
	 * @return: void
	 */
	public void createReportWork(String ids,String type,DraftServantReport report){
		if(StringUtils.isNotBlank(report.getId())){//编辑
			DraftServantReport r = load(report.getId());
			BeanUtils.copyPropertiesIgnoreNull(report, r);
			update(r);
		}else{//新增
			report.setStatus("");
			save(report);//保存上报信息
			String[] idArr = ids.split(",");//录用人员id
			for (String id : idArr) {
				DraftServant ds = draftServantService.load(id);
				//将报告和录用人员信息关联
				DraftServantRelationReport relation = new DraftServantRelationReport();
				relation.setReport(report);
				relation.setDraftServant(ds);
				relationReportService.save(relation);
				//然后将拟录用人员的状态改为审核中
				ds.setStatus(DraftServant.STATUS_SUBMIT);
				draftServantService.update(ds);
			}
		}
		
		if("0".equals(type)){//暂存
			
		}else{
//			List<OrganNode> upperDepartNode = organizationService.getParentOrganNodeByOrganNodeAnOrganRelationType(report.getCreateDept().getId(), CommonConst.ORGAN_RELATION_HR);
//			addProbationServant(report.getId());//数据进入试用期
		}
	}
	/**
	 * @Title: addProbationServant 
	 * @Description: 录用数据进入试用期
	 * @param reportId	上报信息ID
	 * @return
	 * @return: boolean
	 */
	public boolean addProbationServant(String reportId){
		try {
			List<QueryParameter> listqueryparameter=new ArrayList<QueryParameter>();
			listqueryparameter.add(new QueryParameter("removed", false));
			listqueryparameter.add(new QueryParameter("reportId", reportId));
			List<DraftServantRelationReport> rlist =relationReportService.findByHQL("from DraftServantRelationReport where removed=:removed and report.id=:reportId ",listqueryparameter);
			ProbationServant bp;
			for (DraftServantRelationReport draftServantRelationReport : rlist) {
				if(DraftServant.EMPLOY_RESULT_EMPLOY_ID.equals(draftServantRelationReport.getDraftServant().getEmploySituation().getCode())){
					bp = new ProbationServant();
					bp.setDraftServant(draftServantRelationReport.getDraftServant());
					Date now = new Date();
					bp.setProbationStartDate(now);
					Calendar cal = Calendar.getInstance();
				    cal.setTime(now);//设置起时间
				    cal.add(Calendar.YEAR, 1);//增加一年，试用期默认一年
				    bp.setProbationDate(12);
					bp.setProbationEndDate(cal.getTime());
					bp.setStatus(ProbationServant.STATUS_EMPLOY_STATE);
					bp.setIssend(ProbationServant.UN_SEND);
					probationServantService.save(bp);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<DraftServant> getDraftServantByTypeInIds(String ids, int servantType) {

		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from DraftServant where removed=:removed and publish=:publish and servantType=:servantType");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("publish", DraftServant.EMPLOY_PUBLISH);
//		listqueryparameter.add(queryParameteritem);
//		queryParameteritem=new QueryParameter("status", DraftServant.STATUS_EMPLOY_COLLECT);
		listqueryparameter.add(queryParameteritem);
		queryParameteritem=new QueryParameter("servantType", servantType);
		listqueryparameter.add(queryParameteritem);
		if(ids.contains(",")){
			hql.append(" and id in ( ");
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				if(i!=0){
					hql.append(",:id").append(i);
				}else{
					hql.append(":id").append(i);
				}
				queryParameteritem=new QueryParameter("id"+i, idsArr[i]);
				listqueryparameter.add(queryParameteritem);
			}
			hql.append(" ) ");
		}else{
			hql.append(" and id in (:ids) ");
			queryParameteritem=new QueryParameter("ids", ids);
			listqueryparameter.add(queryParameteritem);
		}
		hql.append( " order by createTime desc");
		
		List<DraftServant> list = this.findByHQL(hql.toString(), listqueryparameter, DraftServant.class);
		
		return list;
	}
	
	
	@Override
	public void updateAgreeServantReport(String id) {
		DraftServantReport draftServantReport = this.get(id);
		draftServantReport.setIsAgree(DraftServantReport.SERVANTREPORT_AGREE);
		update(draftServantReport);
		
	}
	
	
	/**
	 * (non Javadoc) 
	 * @Title: addProbationServantSingle
	 * @Description: 单个人员录用进入试用期
	 * @param servantId
	 * @return 
	 * @see com.wondersgroup.human.service.ofcflow.DraftServantReportService#addProbationServantSingle(java.lang.String)
	 */
	@Override
	public boolean addProbationServantSingle(String servantId) {
		ProbationServant bp;
		try {
				DraftServant draftServant = draftServantService.get(servantId);
				if(DraftServant.EMPLOY_RESULT_EMPLOY_ID.equals(codeInfoService.get(draftServant.getEmploySituation().getId()).getCode())){
					bp = new ProbationServant();
					bp.setDraftServant(draftServant);
					bp.setProbationStartDate(draftServant.getProbationStartTime());
					Calendar cal = Calendar.getInstance();
				    cal.setTime(draftServant.getProbationStartTime());//设置起时间
				    
				    //查询试用期时长
				    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProbationTimeLength.class);
				    List<ProbationTimeLength> list = probationTimeLengthService.findByCriteria(detachedCriteria);
				    int month = 12;//默认试用期时长为12个月
					if(list!=null&&list.size()>0){
						month=list.get(0).getProbationDate();
					}
				    cal.add(Calendar.MONTH, month);//根据试用期时长计算试用期结束时间
				    bp.setProbationDate(month);
					bp.setProbationEndDate(cal.getTime());
					bp.setStatus(ProbationServant.STATUS_EMPLOY_STATE);
					bp.setIssend(ProbationServant.UN_SEND);
					bp.setCancelFlag(ProbationServant.UN_SEND);
					bp.setIsDelay(ProbationServant.UN_SEND);
					probationServantService.save(bp);
				}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
}
