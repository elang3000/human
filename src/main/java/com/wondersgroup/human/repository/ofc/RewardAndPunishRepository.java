/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RewardAndPunishRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年7月12日 上午11:04:46 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年7月12日 上午11:04:46 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.vo.analysis.PunishCountVO;
import com.wondersgroup.human.vo.analysis.RewardCountVO;

/** 
 * @ClassName: RewardAndPunishRepository 
 * @Description: 奖惩知识库接口
 * @author: lihao
 * @date: 2018年7月12日 上午11:04:46
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RewardAndPunishRepository  extends GenericRepository<RewardAndPunish>{

	Date getLatestThirdClassDate(Servant servant);
	
	/** 
	 * @Title: getRewardCount 
	 * @Description: 专项统计-奖励统计列表
	 * @param departId
	 * @param year
	 * @return
	 * @return: Map<String,Integer>
	 */
	public Page<RewardCountVO> getRewardCount(String departName,Integer year, Integer page, Integer limit);

	/** 
	 * @Title: getRewardCountByDepartId 
	 * @Description: 专项统计-奖励统计柱状图详情
	 * @param departId
	 * @param year
	 * @return
	 * @return: Map<String,Integer>
	 */
	Map<String, BigDecimal> getRewardCountByDepartId(String departId, Integer year);

	/** 
	 * @Title: getPunishCount 
	 * @Description: 专项统计-处分统计列表
	 * @param departName
	 * @param year
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<PunishCountVO>
	 */
	Page<PunishCountVO> getPunishCount(String departName, Integer year, Integer page, Integer limit);

	/** 
	 * @Title: getPunishCountByDepartId 
	 * @Description: 专项统计-处分统计柱状图详情
	 * @param departId
	 * @param year
	 * @return
	 * @return: Map<String,BigDecimal>
	 */
	Map<String, BigDecimal> getPunishCountByDepartId(String departId, Integer year);

}
