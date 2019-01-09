/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBOutBatchService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 同类别转任 批量转出情况信息 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年8月6日 下午2:59:34 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月6日 下午2:59:34 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutBatch;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBOutBatchVO;

/** 
 * @ClassName: ZhuanRenTLBOutBatchService 
 * @Description: 同类别转任 批量转出情况信息 服务接口
 * @author: tzy
 * @date: 2018年8月6日 下午2:59:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ZhuanRenTLBOutBatchService extends GenericService<ZhuanRenTLBOutBatch>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ZhuanRenTLBOutBatchVO>
	 */
	public Page<ZhuanRenTLBOutBatchVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
	/**
	 * @Title: remove 
	 * @Description: 根据批量表ID删除 批次数据并删除批次下人员数据
	 * @param id
	 * @return: void
	 */
	public void remove(String id);
	
	/**
	 * @Title: savePeople 
	 * @Description: 添加转任人员信息
	 * @param id
	 * @param servantIds	人员ID，逗号分隔
	 * @return: void
	 */
	public void savePeople(ZhuanRenTLBOutBatch z,String servantIds);
	/**
	 * @Title: saveFlow 
	 * @Description: 审批转任
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ZhuanRenTLBOutBatch temp,String opinion,String r);
}
