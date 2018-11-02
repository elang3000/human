/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RewardAndPunishService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年7月12日 上午11:01:56 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年7月12日 上午11:01:56 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofc;

import java.util.Date;
import java.util.List;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.vo.ofc.PunishVO;
import com.wondersgroup.human.vo.ofc.RewardVO;

/** 
 * @ClassName: RewardAndPunishService 
 * @Description: 奖惩服务接口
 * @author: lihao
 * @date: 2018年7月12日 上午11:01:56
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RewardAndPunishService  extends GenericService<RewardAndPunish>{
	
	/**
	 * 
	 * @Title: getPage 
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return
	 * @return: Page<PunishVO>
	 */
	public Page<PunishVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);
	
	Page<RewardVO> getRewardPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit);
	Page<PunishVO> getPunishPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit);
	
	/**
	 * 
	 * @Title: hasPublish 
	 * @Description: 是否曾经有过处分
	 * @param servant
	 * @return
	 * @return: boolean
	 */
	public boolean hasPublish(Servant servant);
	
	/**
	 * 
	 * @Title: getLatestThirdClassDate 
	 * @Description: 获得最近一次记三等功的时间
	 * @param servant
	 * @return
	 * @return: Date
	 */
	public Date getLatestThirdClassDate(Servant servant);
}
