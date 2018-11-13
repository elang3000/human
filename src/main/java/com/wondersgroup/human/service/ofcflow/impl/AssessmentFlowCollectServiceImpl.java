/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月22日 上午9:53:38 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月22日 上午9:53:38 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.AssessmentDetail;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowCollect;
import com.wondersgroup.human.bo.ofcflow.AssessmentFlowUnitPercent;
import com.wondersgroup.human.repository.ofcflow.AssessmentFlowCollectRepository;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.AssessmentDetailService;
import com.wondersgroup.human.service.ofcflow.AssessmentFlowCollectService;
import com.wondersgroup.human.service.ofcflow.AssessmentFlowUnitPercentService;
import com.wondersgroup.human.vo.ofcflow.AssessFlowUnitCollectVO;
import com.wondersgroup.human.vo.ofcflow.AssessmentFlowUnitPercentVO;

/**
 * @ClassName: DeathServantServiceImpl
 * @Description: 考核计划表serviceimpl
 * @author: youyd
 * @date: 2018年9月25日上午19:53:38 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Service
public class AssessmentFlowCollectServiceImpl extends GenericServiceImpl<AssessmentFlowCollect> implements AssessmentFlowCollectService {

	@Autowired
	private AssessmentDetailService assessmentDetailService;
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	private AssessmentFlowCollectRepository assessmentFlowCollectRepository;
	
	@Autowired
	private AssessmentFlowUnitPercentService assessmentFlowUnitPercentService;
	

	
	@Override
	public void createCollectAndAssessmentDetail(AssessmentFlowCollect collect) {
		//所有有人的单位
		Map<String,Integer> departIdMap=new HashMap<>();
		this.save(collect);
		List<Servant> allActiveServant=servantService.getAllActiveServant();
		List<AssessmentDetail> listAssessmentDetail=new ArrayList<>();
		for (Servant servant : allActiveServant) {
			//计算各单位人员数量
			if(departIdMap.containsKey(servant.getDepartId())){
				departIdMap.replace(servant.getDepartId(), departIdMap.get(servant.getDepartId())+1);
			}else{
				departIdMap.put(servant.getDepartId(), 1);
			}
			//初始化人员的考核信息
			AssessmentDetail assessmentDetail=new AssessmentDetail();
			assessmentDetail.setServant(servant);
			assessmentDetail.setAssessmentFlowCollect(collect);
			assessmentDetail.setStatus(AssessmentDetail.NOASSESS);
			listAssessmentDetail.add(assessmentDetail);
		}
		
		//假如是年度考核,则创建所有单位优秀人员数量比例数据
		if(collect.getAssessmentType()==1){
			List<AssessmentFlowUnitPercent> unitPercents=new ArrayList<>();
			for (String departId : departIdMap.keySet()) {
				  AssessmentFlowUnitPercent unitPercent=new AssessmentFlowUnitPercent();
				  unitPercent.setAssessmentFlowCollect(collect);
				  OrganNode orgnode=organNodeService.get(departId);
				  unitPercent.setOrgNode(orgnode);
				  unitPercent.setOrgName(orgnode.getName());
				  unitPercent.setDraftOutstandingNumb(countDraftOutstandingNumb(departIdMap.get(departId)));
				  unitPercent.setOutstandingNumb(countDraftOutstandingNumb(departIdMap.get(departId)));
				  unitPercent.setStatus(CommonConst.NO);
				  unitPercent.setFlowStatus(-1);
				  unitPercents.add(unitPercent);
			}
			assessmentFlowUnitPercentService.saveAll(unitPercents);
			//循环给所有考核人员赋予AssessmentFlowUnitPercent对象
			for(AssessmentDetail detail :listAssessmentDetail){
				for(AssessmentFlowUnitPercent assessmentFlowUnitPercent:unitPercents){
					//将单位百分比对象赋予给
					if(detail.getServant().getDepartId().equals(assessmentFlowUnitPercent.getOrgNode().getId())){
						detail.setAssessmentFlowUnitPercent(assessmentFlowUnitPercent);
						break;
					}
				}
				
			}
		}
		//保存所有记录到单位人员考核详情表
		assessmentDetailService.saveAll(listAssessmentDetail);
	}

	@Override
	public Page<AssessmentFlowUnitPercentVO> getUnitAssessProgress(String assessCollectId,String unitName,Integer page,Integer limit) {
		return assessmentFlowCollectRepository.getUnitAssessProgress(assessCollectId, unitName,page, limit);
	}

	//设置拟优秀数量,用当前单位人员数量*15%,最少一个
	private Integer countDraftOutstandingNumb(int allUnitNumb){
		double numb=allUnitNumb*0.15; 
		if(numb<1){
			return 1;
		}
		return new BigDecimal(numb).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}

	@Override
	public Page<AssessFlowUnitCollectVO> getCollectAndFlowStatus(OrganNode org,Integer year, Integer page, Integer limit) {
//		StringBuilder sql=new StringBuilder();
//		sql.append("select pt.flow_status, ct.*, pt.*, pt.flowrecord_id ");
//		sql.append("  from HUMAN_ASSESSMENT_FLOW_COLLECT ct ");
//		sql.append("  left join (select * ");
//		sql.append("               from HUMAN_ASSESSMENT_FLOW_UNIT_PERCENT t ");
//		sql.append("              where t.orgnode = :orgId) pt ");
//		sql.append("    on ct.id = pt.assessment_flow_collect ");
		Page<AssessFlowUnitCollectVO> map=this.assessmentFlowCollectRepository.getCollectAndFlowStatus(org,year,page,limit);
		return map;
	}
	
}
