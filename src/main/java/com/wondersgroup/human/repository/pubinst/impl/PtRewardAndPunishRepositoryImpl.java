/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RewardAndPunishRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年7月12日 上午11:05:34 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年7月12日 上午11:05:34 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.pubinst.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.pubinst.PtRewardAndPunish;
import com.wondersgroup.human.repository.pubinst.PtRewardAndPunishRepository;

/** 
 * @ClassName: RewardAndPunishRepositoryImpl 
 * @Description: 奖惩知识库接口实现
 * @author: lihao
 * @date: 2018年7月12日 上午11:05:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class PtRewardAndPunishRepositoryImpl extends GenericRepositoryImpl<PtRewardAndPunish> implements PtRewardAndPunishRepository{

	public Class<PtRewardAndPunish> getEntityClass() {
		return PtRewardAndPunish.class;
	}
}
