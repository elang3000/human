/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EventFamilyService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 流程信息 家庭 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年8月2日 下午3:07:28 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月2日 下午3:07:28 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.EventFamily;
import com.wondersgroup.human.vo.ofcflow.EventFamilyVO;

/** 
 * @ClassName: EventFamilyService 
 * @Description: 流程信息 家庭 服务接口
 * @author: tzy
 * @date: 2018年8月2日 下午3:07:28
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface EventFamilyService extends GenericService<EventFamily>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 返回VO的分页查询
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<EventPostVO>
	 */
	public Page<EventFamilyVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
}
