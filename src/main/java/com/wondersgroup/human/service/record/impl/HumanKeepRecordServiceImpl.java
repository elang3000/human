
package com.wondersgroup.human.service.record.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.record.HumanKeepRecordEvent;
import com.wondersgroup.human.repository.record.HumanKeepRecordDAO;
import com.wondersgroup.human.service.record.HumanKeepRecordService;
import com.wondersgroup.human.vo.record.HumanKeepRecordVO;

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
	private HumanKeepRecordDAO humanKeepRecordDAO;

	@Autowired
	DictableService dictableService;

	/**
	 * 备案类型code
	 */

	private final String RECORD_TYPE_CODE = "recordType";

	/**
	 * 备案状态code
	 */

	private final String RETURNED_CODE = "returned";

	/**
	 * @see com.wondersgroup.human.service.record.HumanKeepRecordService#queryServantKeepRecord(java.util.Map,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<HumanKeepRecordVO> queryServantKeepRecord(Map<String, Object> filter, Integer pageNumber,
			Integer pageSize) {
		return humanKeepRecordDAO.queryServantKeepRecord(filter, pageNumber, pageSize);
	}

	/**
	 * @see com.wondersgroup.human.service.record.HumanKeepRecordService#createHumanKeepRecord(com.wondersgroup.human.event.record.HumanKeepRecordEvent)
	 */
	@Override
	public void createHumanKeepRecord(HumanKeepRecordEvent humanKeepRecordevent) {
		HumankeepRecordDTO dto = (HumankeepRecordDTO) humanKeepRecordevent.getSource();
		HumanKeepRecord record = new HumanKeepRecord();

		CodeInfo recordTypeCodeInfo = dictableService.getCodeInfoByCode(dto.getRecordType(), RECORD_TYPE_CODE);
		CodeInfo returnedCodeInfo = dictableService.getCodeInfoByCode(dto.getReturned(), RETURNED_CODE);

		record.setBusinessEntityId(dto.getBusinessEntityId());
		record.setBusinessEntityTable(dto.getBusinessEntityTable());
		record.setDescription(dto.getDescription());
		record.setExt1(dto.getExt1());
		record.setExt2(dto.getExt2());
		record.setExt3(dto.getExt3());
		record.setExt4(new Date());
		record.setExt5(new Date());
		record.setExt6(dto.getExt6());
		record.setHumanId(dto.getHumanId());
		record.setRecordType(recordTypeCodeInfo);
		record.setReturned(recordTypeCodeInfo);
		record.setUnitType(null);

		save(record);
	}

}
