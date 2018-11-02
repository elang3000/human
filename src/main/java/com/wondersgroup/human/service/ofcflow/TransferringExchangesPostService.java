/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TransferringExchangesPostService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 选调职位信息 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年8月13日 上午10:17:59 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月13日 上午10:17:59 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.TransferringExchangesPost;
import com.wondersgroup.human.vo.ofcflow.TransferringExchangesPostVO;

/** 
 * @ClassName: TransferringExchangesPostService 
 * @Description: 选调职位信息 服务接口
 * @author: tzy
 * @date: 2018年8月13日 上午10:17:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface TransferringExchangesPostService extends GenericService<TransferringExchangesPost>{
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0 查询条件
	 * @param arg1 排序规则
	 * @param arg2 页码
	 * @param arg3 页大小
	 * @return
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<TransferringExchangesPostVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);
}
