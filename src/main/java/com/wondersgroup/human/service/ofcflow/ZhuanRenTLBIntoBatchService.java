/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBBatchService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 同类别转任 批量数据表 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年12月7日 上午9:54:07 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月7日 上午9:54:07 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBIntoBatch;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBIntoBatchVO;

/** 
 * @ClassName: ZhuanRenTLBBatchService 
 * @Description: 同类别转任 批量数据表 服务接口
 * @author: tzy
 * @date: 2018年12月7日 上午9:54:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ZhuanRenTLBIntoBatchService extends GenericService<ZhuanRenTLBIntoBatch>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ZhuanRenTLBIntoBatchVO>
	 */
	public Page<ZhuanRenTLBIntoBatchVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
	
	/**
	 * @Title: remove 
	 * @Description: 根据批量表ID删除 批次数据并删除批次下人员数据
	 * @param id
	 * @return: void
	 */
	public void remove(String id);
	
	/**
	 * @Title: savePeople 
	 * @Description: 添加本区转任人员信息
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
	public void saveFlow(ZhuanRenTLBIntoBatch temp,String opinion,String r);
	/**
	 * @Title: saveFlowOuter 
	 * @Description: 审批外区转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlowOuter(ZhuanRenTLBIntoBatch temp,String opinion,String r);
	/**
	 * @Title: queryValidateFormationAndPostLvlNum 
	 * @Description: 校验编控
	 * @param temp
	 * @return: void
	 */
	public void queryValidateFormationAndPostLvlNum(ZhuanRenTLBIntoBatch temp);
}
