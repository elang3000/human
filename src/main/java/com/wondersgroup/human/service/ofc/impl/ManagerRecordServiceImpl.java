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
package com.wondersgroup.human.service.ofc.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.dto.ofc.ManagerRecordParam;
import com.wondersgroup.human.event.ofc.ManagerRecordEvent;
import com.wondersgroup.human.repository.ofc.ManagerRecordRepository;
import com.wondersgroup.human.service.ofc.ManagerRecordService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.ItemRecordVO;
import com.wondersgroup.human.vo.ofc.ManagerRecordVO;

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
public class ManagerRecordServiceImpl extends GenericServiceImpl<ManagerRecord> implements ManagerRecordService {

	@Autowired
	ManagerRecordRepository managerRecordRepository;

	@Autowired
	ServantService servantService;

	@Autowired
	DictableService dictableService;

	/**
	 * @see com.wondersgroup.human.service.ofc.ManagerRecordService#createManagerRecord(com.wondersgroup.human.event.ofc.ManagerRecordEvent)
	 */
	@Override
	public void createManagerRecord(ManagerRecordEvent event) {
		ManagerRecordDTO dto = (ManagerRecordDTO) event.getSource();
		ManagerRecord record = new ManagerRecord();
		Servant servant = servantService.get(dto.getServantId());
		CodeInfo recordTypeCodeInfo = dictableService.getCodeInfoByCode(dto.getRecordType(), "HumanChange");

		record.setServant(servant);
		record.setRecordTime(new Date());
		record.setRecordType(recordTypeCodeInfo);
		record.setItemType(event.getItemType());
		record.setOrganId(servant.getDepartId());
		record.setOrganName(servant.getDepartName());
		
		record.setBusinessEntityId(dto.getBusinessEntityId());
		record.setBusinessEntityTable(dto.getBusinessEntityTable());
		record.setExt1(dto.getExt1());
		record.setExt2(dto.getExt2());
		record.setExt3(dto.getExt3());
		record.setExt4(dto.getExt4());
		record.setExt5(dto.getExt5());
		record.setExt6(dto.getExt6());
		
		save(record);
	}

	/**
	 * @see com.wondersgroup.human.service.ofc.ManagerRecordService#queryManagerRecord(java.util.Map,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<ManagerRecordVO> queryManagerRecord(Map<String, Object> filter, Integer page, Integer limit) {
		Page<ManagerRecordVO> list = managerRecordRepository.queryManagerRecord(filter, page, limit);
		List<ManagerRecordVO> list2 = new ArrayList<>();
		for (ManagerRecordVO v : list.getResult()) {
			ManagerRecordVO vo = new ManagerRecordVO();
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
		Page<ManagerRecordVO> page1 = new Page<>(list.getStart(), list.getCurrentPageSize(), list.getTotalSize(),
				list.getPageSize(), list2);
		return page1;
	}

	/** 
	 * @see com.wondersgroup.human.service.ofc.ManagerRecordService#getPage(com.wondersgroup.human.dto.ofc.ManagerRecordParam, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<ItemRecordVO> getPage(ManagerRecordParam param, Integer page, Integer limit) {
		
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(ManagerRecord.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			s.add(Restrictions.like("s.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			s.add(Restrictions.like("s.cardNo",param.getCardNo(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getSex())) {// 性别
			s.add(Restrictions.eq("s.sex.id",param.getSex()));
		}
		if (param.getItemType()!=null) {// 进出管理
			detachedcriteria.add(Restrictions.eq("itemType", param.getItemType()));
		}
		if (StringUtils.isNotBlank(param.getRecordType())) {// 事项管理
			detachedcriteria.add(Restrictions.eq("recordType.id", param.getRecordType()));
		}
		
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("recordTime"));
		Page<ManagerRecord> managerPage = this.findByCriteria(detachedcriteria, page, limit);
		List<ItemRecordVO> voList = new ArrayList<>();
		for (ManagerRecord ds : managerPage.getResult()) {
			ItemRecordVO vo = new ItemRecordVO(ds);
			voList.add(vo);
		}
		Page<ItemRecordVO> result = new Page<>(managerPage.getStart(), managerPage.getCurrentPageSize(),
				managerPage.getTotalSize(), managerPage.getPageSize(), voList);
		return result;
	}
}
