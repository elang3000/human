/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PersonnelRecordService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月7日 下午5:55:50 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月7日 下午5:55:50 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.pubinst;

import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.pubinst.PtManagerRecord;
import com.wondersgroup.human.event.pubinst.PtManagerRecordEvent;
import com.wondersgroup.human.vo.pubinst.PtManagerRecordVO;

/**
 * @ClassName: PersonnelRecordService
 * @Description: 人员进出管理服务接口
 * @author: lihao
 * @date: 2018年6月7日下午5:55:50 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public interface PtManagerRecordService extends GenericService<PtManagerRecord> {

	/**
	 * @Title: createManagerRecord
	 * @Description: 人员进出管理触发事件
	 * @param event事件
	 * @return: void
	 */
	public void createManagerRecord(PtManagerRecordEvent event);

	/**
	 * @Title: queryManagerRecord
	 * @Description: 查询人员进出管理列表数据转换为VO的分页查询
	 * @param filter查询条件
	 * @param page页码
	 * @param limit页大小
	 * @return
	 * @return: Page<HumanKeepRecordVO>
	 */
	Page<PtManagerRecordVO> queryManagerRecord(Map<String, Object> filter, Integer page, Integer limit);

}
