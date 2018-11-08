/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReferenceExchangeOutService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 参公交流调出
 * 创建人: tzy   
 * 创建时间: 2018年11月8日 下午6:44:20 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年11月8日 下午6:44:20 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOut;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeOutVO;

/** 
 * @ClassName: ReferenceExchangeOutService 
 * @Description: 参公交流调出
 * @author: tzy
 * @date: 2018年11月8日 下午6:44:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ReferenceExchangeOutService extends GenericService<ReferenceExchangeOut>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ReferenceExchangeOutVO>
	 */
	public Page<ReferenceExchangeOutVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
	/**
	 * @Title: saveFlow 
	 * @Description: 审批调出流程
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ReferenceExchangeOut temp);
}
