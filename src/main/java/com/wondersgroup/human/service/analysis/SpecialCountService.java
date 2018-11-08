/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: SpecialCountService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月1日 上午10:34:56 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月1日 上午10:34:56 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.analysis;

import java.math.BigDecimal;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.vo.analysis.MagCountVO;
import com.wondersgroup.human.vo.analysis.PunishCountVO;
import com.wondersgroup.human.vo.analysis.RewardCountVO;

/** 
 * @ClassName: SpecialCountService 
 * @Description: 专项统计服务类
 * @author: lihao
 * @date: 2018年11月1日 上午10:34:56
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface SpecialCountService {

	/** 
	 * @Title: getRewardCount 
	 * @Description: 专项统计-奖励统计列表页面
	 * @param departName
	 * @param year
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<RewardCountVO>
	 */
	public Page<RewardCountVO> getRewardCount(String departName, Integer year, Integer page, Integer limit);

	/** 
	 * @Title: getRewardCountByDepartId 
	 * @Description: 专项统计-奖励统计柱状图详情
	 * @param departId
	 * @param year
	 * @return
	 * @return: Map<String,BigDecimal>
	 */
	public Map<String, BigDecimal> getRewardCountByDepartId(String departId, Integer year);

	/** 
	 * @Title: getPunishCount 
	 * @Description: 专项统计-处分统计列表页面
	 * @param departName
	 * @param year
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<PunishCountVO>
	 */
	public Page<PunishCountVO> getPunishCount(String departName, Integer year, Integer page, Integer limit);

	/** 
	 * @Title: getPunishCountByDepartId 
	 * @Description: 专项统计-处分统计柱状图详情
	 * @param departId
	 * @param year
	 * @return
	 * @return: Map<String,BigDecimal>
	 */
	public Map<String, BigDecimal> getPunishCountByDepartId(String departId, Integer year);

	/** 
	 * @Title: getMagCount 
	 * @Description: 专项统计-进出管统计列表页面
	 * @param departName
	 * @param year
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<MagCountVO>
	 */
	public Page<MagCountVO> getMagCount(String departName, Integer year, Integer page, Integer limit);

	/** 
	 * @Title: getMagCountByDepartId 
	 * @Description: 专项统计-进出管统计柱状图详情
	 * @param departId
	 * @param year
	 * @return
	 * @return: Map<String,BigDecimal>
	 */
	public Map<String, BigDecimal> getMagCountByDepartId(String departId, Integer year);
}
