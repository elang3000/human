/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeBatchService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年12月17日 上午11:00:16 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年12月17日 上午11:00:16 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchange;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeBatch;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeBatchVO;

/** 
 * @ClassName: ReferenceExchangeBatchService 
 * @Description: TODO
 * @author: GP
 * @date: 2018年12月17日 上午11:00:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ReferenceExchangeBatchService extends GenericService<ReferenceExchangeBatch>{
	
	
	public Page<ReferenceExchangeBatchVO> findbyHQLforVO(String hql,List<QueryParameter> args,Integer page,Integer pageNo);
	
	public Page<ReferenceExchangeBatchVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit);
	
	
	/**
	 * @Title: savePeople 
	 * @Description: 添加本区参公交流人员信息
	 * @param id
	 * @param servantIds	人员ID，逗号分隔
	 * @return: void
	 */
	public void savePeople(String id,String servantIds);
	
	/**
	 * @Title: saveFlow 
	 * @Description: 审批本区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ReferenceExchangeBatch temp,String opinion,String r);
	/**
	 * @Title: saveFlowOuter 
	 * @Description: 审批外区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlowOuter(ReferenceExchangeBatch temp,String opinion,String r);
	
	/**
	 * 获取批量下所有参公交流信息
	 * @Title: itembatchItems 
	 * @Description: TODO
	 * @param batchId
	 * @return
	 * @return: List<ReferenceExchange>
	 */
	public List<ReferenceExchange> itembatchItems(String batchId);
}
