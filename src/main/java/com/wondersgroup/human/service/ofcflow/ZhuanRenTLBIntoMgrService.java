/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBIntoMgrService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 同类别转任 调入情况信息 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年8月1日 上午10:59:47 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月1日 上午10:59:47 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBIntoMgr;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutMgr;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBIntoMgrVO;

/** 
 * @ClassName: ZhuanRenTLBIntoMgrService 
 * @Description: 同类别转任 转入情况信息 服务接口
 * @author: tzy
 * @date: 2018年8月1日 上午10:59:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ZhuanRenTLBIntoMgrService extends GenericService<ZhuanRenTLBIntoMgr>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<DiaoRenIntoMgrVO>
	 */
	public Page<ZhuanRenTLBIntoMgrVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
	
	/**
	 * @Title: remove 
	 * @Description: 删除 转任调入信息并删除根据该人员姓名和身份证号删除子集
	 * @return: void
	 */
	public void remove(String id);
	/**
	 * @Title: createOut 
	 * @Description: 发起同类别转任，转出流程
	 * @param id
	 * @return: void
	 */
	public ZhuanRenTLBOutMgr createOut(ZhuanRenTLBIntoMgr z);
	/**
	 * @Title: saveFlow 
	 * @Description: 审批本区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ZhuanRenTLBIntoMgr temp,String opinion,String r);
	/**
	 * @Title: saveFlowOuter 
	 * @Description: 审批外区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlowOuter(ZhuanRenTLBIntoMgr temp,String opinion,String r);
}
