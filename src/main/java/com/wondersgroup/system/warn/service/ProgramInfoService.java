/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProgramInfoService.java 
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.service 
 * 描述: 预警预告：查询方案 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年10月29日 下午5:47:44 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月29日 下午5:47:44 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.system.warn.service;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.system.warn.bo.ProgramInfo;
import com.wondersgroup.system.warn.vo.ProgramInfoVO;

/** 
 * @ClassName: ProgramInfoService 
 * @Description: 预警预告：查询方案 服务接口
 * @author: tzy
 * @date: 2018年10月29日 下午5:47:44
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ProgramInfoService extends GenericService<ProgramInfo>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ProgramInfoVO>
	 */
	public Page<ProgramInfoVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
}
