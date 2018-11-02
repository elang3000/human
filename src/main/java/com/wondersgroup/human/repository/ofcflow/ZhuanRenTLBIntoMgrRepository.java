/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBIntoMgrRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: 同类别转任 调入情况信息 知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年8月1日 上午10:57:07 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月1日 上午10:57:07 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow;

import java.util.List;
import java.util.Map;

import org.hibernate.procedure.ProcedureOutputs;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBIntoMgr;

/** 
 * @ClassName: ZhuanRenTLBIntoMgrRepository 
 * @Description: 同类别转任 转入情况信息 知识库接口
 * @author: tzy
 * @date: 2018年8月1日 上午10:57:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ZhuanRenTLBIntoMgrRepository extends GenericRepository<ZhuanRenTLBIntoMgr>{
	
	/**
	 * @Title: executeStoreProcedure 
	 * @Description: 调用存储过程
	 * @param storeProcedureName 存储过程名称
	 * @param params	入参：key为参数名，value为参数值
	 * @param backList	返回值：返回参数名list
	 * @return
	 * @return: ProcedureOutputs
	 */
	public ProcedureOutputs executeStoreProcedure(String storeProcedureName,Map<String,String> params,List<String> backList);
}
