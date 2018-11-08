/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProbationServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 试用期管理 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年7月25日 下午3:18:40 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月25日 下午3:18:40 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;
import com.wondersgroup.human.vo.ofcflow.ProbationServantVO;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: ProbationServantService 
 * @Description: 试用期管理 服务接口
 * @author: tzy
 * @date: 2018年7月25日 下午3:18:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ProbationServantService extends GenericService<ProbationServant>{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 列表分页查询 返回VO对象
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ProbationServantVO>
	 */
	public Page<ProbationServantVO> findbyHQLforVO(String hql, List<QueryParameter> listqueryparametry, Integer pageNo,Integer pagesize);
	/**
	 * @Title: updateStatusById 
	 * @Description: 修改状态
	 * @param id	如果id中有逗号，批量修改
	 * @param status	状态
	 * @return: void
	 */
	public void updateStatusById(String id,int status);
	/**
	 * @Title: importServant 
	 * @Description: 数据导入人员基本信息表：DraftServant-->(Servant,Employ),ProbationServant-->Probation,DraftServantEduInfo-->Education
	 * @param id	试用期表 id
	 * @return: void
	 */
	public void getServantNotMemory(String id);
	/**
	 * @Title: saveFlow 
	 * @Description: 审批试用期信息
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ProbationServant temp,String opinion,String r);
	/**
	 * @Title: saveFlowCancel 
	 * @Description: 审批试用期信息 取消录用
	 * @param temp
	 * @return: void
	 */
	public void saveFlowCancel(ProbationServant temp,String opinion,String r);


	/**
	 *
	 * @return 获取各单位试用期人数统计 返回orginfoid 和 count数量
	 */
	public Map<String,Integer> getUnitProbationCount();
}
