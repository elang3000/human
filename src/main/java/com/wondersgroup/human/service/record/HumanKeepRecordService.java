package com.wondersgroup.human.service.record;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.record.HumanKeepRecordParam;
import com.wondersgroup.human.event.record.HumanKeepRecordEvent;
import com.wondersgroup.human.vo.record.KeepRecordVO;

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
	 * @Title: createAnnouncement
	 * @Description: 创建备案事件
	 * @return: void
	 */
	public void createHumanKeepRecord(HumanKeepRecordEvent humanKeepRecordevent);

	/** 
	 * @Title: getPage 
	 * @Description: TODO
	 * @param param
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<KeepRecordVO>
	 */
	Page<KeepRecordVO> getPage(HumanKeepRecordParam param, Integer page, Integer limit);
}
