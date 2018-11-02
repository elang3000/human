/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaoRenOutMgrService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 调任流程 调出情况信息-服务接口
 * 创建人: tzy   
 * 创建时间: 2018年10月16日 下午3:06:26 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月16日 下午3:06:26 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOutMgr;
import com.wondersgroup.human.vo.ofcflow.DiaoRenOutMgrVO;

/** 
 * @ClassName: DiaoRenOutMgrService 
 * @Description: 调任流程 调出情况信息-服务接口
 * @author: tzy
 * @date: 2018年10月16日 下午3:06:26
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DiaoRenOutMgrService extends GenericService<DiaoRenOutMgr>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<DiaoRenOutMgrVO>
	 */
	public Page<DiaoRenOutMgrVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
	/**
	 * @Title: saveFlow 
	 * @Description: 审批本区调任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(DiaoRenOutMgr temp,String opinion,String r);
	/**
	 * @Title: saveFlowOuter 
	 * @Description: 审批外区调任
	 * @param temp
	 * @return: void
	 */
	public void saveFlowOuter(DiaoRenOutMgr temp);
}
