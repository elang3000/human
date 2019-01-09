/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenKLBIntoService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 跨类别转任 调入情况信息 服务接口
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
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBInto;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenKLBIntoVO;

/** 
 * @ClassName: ZhuanRenKLBIntoService 
 * @Description: 跨类别转任 转入情况信息 服务接口
 * @author: tzy
 * @date: 2018年8月1日 上午10:59:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ZhuanRenKLBIntoService extends GenericService<ZhuanRenKLBInto>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<DiaoRenIntoVO>
	 */
	public Page<ZhuanRenKLBIntoVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);

	/**
	 * @Title: deleteServant 
	 * @Description: 删除选中人员
	 * @param id
	 * @return: void
	 */
	public void deleteServant(String id);
}
