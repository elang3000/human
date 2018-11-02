/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PersonnelRecordServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月7日 下午5:57:15 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月7日 下午5:57:15 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.pubinst.PtManagerRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.pubinst.PtManagerRecordDTO;
import com.wondersgroup.human.event.pubinst.PtManagerRecordEvent;
import com.wondersgroup.human.repository.pubinst.PtManagerRecordRepository;
import com.wondersgroup.human.service.pubinst.PtManagerRecordService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtManagerRecordVO;

/**
 * @ClassName: PersonnelRecordServiceImpl
 * @Description: 人员进出管理服务接口实现类
 * @author: lihao
 * @date: 2018年6月7日下午5:57:15
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */

@Service
public class PtManagerRecordServiceImpl extends GenericServiceImpl<PtManagerRecord> implements PtManagerRecordService {

	@Autowired
	PtManagerRecordRepository managerRecordRepository;

	@Autowired
	PublicInstitutionService publicInstitutionService;

	@Autowired
	DictableService dictableService;

	/**
	 * @see com.wondersgroup.human.service.ofc.ManagerRecordService#createManagerRecord(com.wondersgroup.human.event.ofc.ManagerRecordEvent)
	 */
	@Override
	public void createManagerRecord(PtManagerRecordEvent event) {
		PtManagerRecordDTO dto = (PtManagerRecordDTO) event.getSource();
		PtManagerRecord record = new PtManagerRecord();
		PublicInstitution servant = publicInstitutionService.get(dto.getHumanId());
		CodeInfo recordTypeCodeInfo = dictableService.getCodeInfoByCode(dto.getRecordType(), "");
		CodeInfo managerTypeCodeInfo = dictableService.getCodeInfoByCode(dto.getManagerType(), "");

		record.setPublicInstitution(servant);
		record.setAreaExamineAudit(dto.getAreaExamineAudit());
		record.setAreaExamineOpinion(dto.getAreaExamineOpinion());
		record.setAreaExamineTime(dto.getAreaExamineTime());
		record.setBusinessEntityId(dto.getBusinessEntityId());
		record.setBusinessEntityTable(dto.getBusinessEntityTable());
		record.setCityExamineAudit(dto.getCityExamineAudit());
		record.setCityExamineOpinion(dto.getCityExamineOpinion());
		record.setCityExamineTime(dto.getCityExamineTime());
		record.setExt1(dto.getExt1());
		record.setExt2(dto.getExt2());
		record.setExt3(dto.getExt3());
		record.setExt4(dto.getExt4());
		record.setExt5(dto.getExt5());
		record.setExt6(dto.getExt6());
		record.setLeaderExamineAudit(dto.getLeaderExamineAudit());
		record.setLeaderExamineOpinion(dto.getLeaderExamineOpinion());
		record.setLeaderExamineTime(dto.getLeaderExamineTime());
		record.setRecordTime(dto.getRecordTime());
		record.setRecordType(recordTypeCodeInfo);
		record.setManagerType(managerTypeCodeInfo);
		record.setStatus(dto.getStatus());
		save(record);
	}

	/**
	 * @see com.wondersgroup.human.service.ofc.ManagerRecordService#queryManagerRecord(java.util.Map,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<PtManagerRecordVO> queryManagerRecord(Map<String, Object> filter, Integer page, Integer limit) {
		Page<PtManagerRecordVO> list = managerRecordRepository.queryManagerRecord(filter, page, limit);
		List<PtManagerRecordVO> list2 = new ArrayList<>();
		for (PtManagerRecordVO v : list.getResult()) {
			PtManagerRecordVO vo = new PtManagerRecordVO();
			if (StringUtils.isNotBlank(v.getSex())) {
				CodeInfo sexCodeInfo = dictableService.loadCodeInfoById(v.getSex());
				vo.setSex(sexCodeInfo.getName());
			}
			vo.setName(v.getName());
			vo.setCardNo(v.getCardNo());
			if (StringUtils.isNotBlank(v.getRecordType())) {
				CodeInfo recordTypeCodeInfo = dictableService.loadCodeInfoById(v.getRecordType());
				vo.setRecordType(recordTypeCodeInfo.getName());
			}
			if (StringUtils.isNotBlank(v.getManagerType())) {
				CodeInfo managerTypeCodeInfo = dictableService.loadCodeInfoById(v.getManagerType());
				vo.setManagerType(managerTypeCodeInfo.getName());
			}
			vo.setRecordTime(v.getRecordTime());
			vo.setStatus(v.getStatus());
			vo.setId(v.getId());
			list2.add(vo);
		}
		Page<PtManagerRecordVO> page1 = new Page<>(list.getStart(), list.getCurrentPageSize(), list.getTotalSize(),
				list.getPageSize(), list2);
		return page1;
	}


}
