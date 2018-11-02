/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ExerciseService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 挂职锻炼 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年9月25日 下午3:05:51 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月25日 下午3:05:51 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.Exercise;
import com.wondersgroup.human.vo.ofcflow.ExerciseVO;

/** 
 * @ClassName: ExerciseService 
 * @Description: 挂职锻炼 服务接口
 * @author: tzy
 * @date: 2018年9月25日 下午3:05:51
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ExerciseService extends GenericService<Exercise>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ExerciseVO>
	 */
	public Page<ExerciseVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
	/**
	 * @Title: saveFlow 
	 * @Description: 挂职锻炼信息备案
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(Exercise temp);
}
