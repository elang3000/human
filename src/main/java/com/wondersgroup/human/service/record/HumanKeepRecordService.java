package com.wondersgroup.human.service.record;

import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.event.record.HumanKeepRecordEvent;
import com.wondersgroup.human.vo.record.HumanKeepRecordVO;

/**
 * 备案记录服务接口
 * 
 * @ClassName: HumanKeepRecordService
 * @Description: 备案记录服务接口
 * @author: GP
 * @date: 2018年5月24日下午3:06:21 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public interface HumanKeepRecordService extends GenericService<HumanKeepRecord> {

	/**
	 * 
	 * @Title: queryServantKeepRecord
	 * @Description: 分页查询备案列表
	 * @return: Page<HumanKeepRecordVO>
	 */
	Page<HumanKeepRecordVO> queryServantKeepRecord(Map<String, Object> filter, Integer pageNumber, Integer pageSize);

	/**
	 * 
	 * @Title: createAnnouncement
	 * @Description: 创建备案事件
	 * @return: void
	 */
	public void createHumanKeepRecord(HumanKeepRecordEvent humanKeepRecordevent);
}
