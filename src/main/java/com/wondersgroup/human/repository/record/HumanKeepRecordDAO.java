
package com.wondersgroup.human.repository.record;

import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.vo.record.HumanKeepRecordVO;

/**
 * @ClassName: HumanKeepRecordDAO
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年5月29日 下午5:20:05
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface HumanKeepRecordDAO extends GenericRepository<HumanKeepRecord> {
	
	Page<HumanKeepRecordVO> queryServantKeepRecord(Map<String, Object> filter, Integer pageNumber, Integer pageSize);
}
