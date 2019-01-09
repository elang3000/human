/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 录用信息管理 服务接口
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:05:33 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:05:33 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.JobShiftB;
import com.wondersgroup.human.bo.ofcflow.JobShiftCollect;
import com.wondersgroup.human.vo.ofcflow.JobShiftCollectVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** 
 * @ClassName: JobShiftCollectService 
 * @Description: 职务变动计划表 接口
 * @author: youyd	
 * @date: 2018年6月26日 上午10:05:33
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface JobShiftCollectService extends GenericService<JobShiftCollect>{

	//查询首页列表数据
	Page<JobShiftCollectVO> findbyHQLforVO(String hql, List<QueryParameter> listqueryparameter, Integer pageNo,
	        Integer pagesize);

	void updatePromoteFlowB(JobShiftCollect jobShiftCollect, String opinion, String result);

	void checkAndLockFormation(JobShiftCollect jobShiftCollect);

    String createFiles(List<JobShiftB> list, JobShiftCollect jobShiftCollect, String id, HttpServletRequest request);
}
