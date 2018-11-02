/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 职务简章-职位信息 服务接口
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:12:04 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:12:04 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.RecruitPost;
import com.wondersgroup.human.vo.ofcflow.RecruitPostVO;

/** 
 * @ClassName: RecruitEmployPlanService 
 * @Description: 职务简章-职位信息 服务接口
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午11:12:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RecruitPostService extends GenericService<RecruitPost>{
	/**
	 * 
	 * @Title: findByFilterVO 
	 * @Description: 列表分页
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @return: Page<RecruitEmployPlanVO>
	 */
	Page<RecruitPostVO> findByFilterVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
	
}
