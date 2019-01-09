
package com.wondersgroup.human.service.record.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.record.HumanKeepRecordParam;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.record.HumanKeepRecordEvent;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.record.HumanKeepRecordService;
import com.wondersgroup.human.vo.record.KeepRecordVO;

/**
 * 备案记录服务实现类
 * 
 * @ClassName: HumanKeepRecordServiceImpl
 * @Description: 备案记录服务实现类
 * @author: GP
 * @date: 2018年5月24日 下午3:06:40 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Service
public class HumanKeepRecordServiceImpl extends GenericServiceImpl<HumanKeepRecord> implements HumanKeepRecordService {

	@Autowired
	DictableService dictableService;
	
	@Autowired
	ServantService servantService;

	/**
	 * @see com.wondersgroup.human.service.record.HumanKeepRecordService#createHumanKeepRecord(com.wondersgroup.human.event.record.HumanKeepRecordEvent)
	 */
	@Override
	public void createHumanKeepRecord(HumanKeepRecordEvent humanKeepRecordevent) {
		HumankeepRecordDTO dto = (HumankeepRecordDTO) humanKeepRecordevent.getSource();
		HumanKeepRecord record = new HumanKeepRecord();
		Servant servant = servantService.get(dto.getHumanId()); 
		CodeInfo recordTypeCodeInfo = dictableService.getCodeInfoByCode(dto.getRecordType(), "KeepRecord");

		record.setServant(servant);
		record.setRecordType(recordTypeCodeInfo);//备案类型
		record.setOrganId(servant.getDepartId());
		record.setOrganName(servant.getDepartName());
		record.setRecordTime(new Date());//备案时间
		
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
	 * @see com.wondersgroup.human.service.record.HumanKeepRecordService#getPage(com.wondersgroup.human.dto.record.HumanKeepRecordParam, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<KeepRecordVO> getPage(HumanKeepRecordParam param, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(HumanKeepRecord.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			s.add(Restrictions.like("s.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			s.add(Restrictions.eq("s.cardNo",param.getCardNo()));
		}
		if (StringUtils.isNotBlank(param.getRecordType())) {// 事项管理
			detachedcriteria.add(Restrictions.eq("recordType.id", param.getRecordType()));
		}
		
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("recordTime"));
		Page<HumanKeepRecord> keepPage = this.findByCriteria(detachedcriteria, page, limit);
		List<KeepRecordVO> voList = new ArrayList<>();
		for (HumanKeepRecord ds : keepPage.getResult()) {
			KeepRecordVO vo = new KeepRecordVO(ds);
			voList.add(vo);
		}
		Page<KeepRecordVO> result = new Page<>(keepPage.getStart(), keepPage.getCurrentPageSize(),
				keepPage.getTotalSize(), keepPage.getPageSize(), voList);
		return result;
	}

}
