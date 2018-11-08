/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PersonnelRecordRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月7日 下午6:00:01 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月7日 下午6:00:01 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofc;

import java.math.BigDecimal;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.vo.analysis.MagCountVO;
import com.wondersgroup.human.vo.ofc.ManagerRecordVO;

/** 
 * @ClassName: ManagerRecordRepository 
 * @Description: 人员进出管理 知识库接口
 * @author: lihao
 * @date: 2018年6月7日 下午6:00:01
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ManagerRecordRepository  extends GenericRepository<ManagerRecord>{

	/** 
	 * @Title: queryManagerRecord 
	 * @Description: 进出管理列表bo转vo分页查询
	 * @param filter
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<HumanKeepRecordVO>
	 */
	Page<ManagerRecordVO> queryManagerRecord(Map<String, Object> filter, Integer page, Integer limit);

	/** 
	 * @Title: getMagCount 
	 * @Description: 专项统计进出管列表
	 * @param departName
	 * @param year
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<MagCountVO>
	 */
	Page<MagCountVO> getMagCount(String departName, Integer year, Integer page, Integer limit);

	/** 
	 * @Title: getMagCountByDepartId 
	 * @Description: 专项统计进出管柱状图
	 * @param departId
	 * @param year
	 * @return
	 * @return: Map<String,BigDecimal>
	 */
	Map<String, BigDecimal> getMagCountByDepartId(String departId, Integer year);

}
