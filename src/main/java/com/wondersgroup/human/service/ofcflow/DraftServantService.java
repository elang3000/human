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

import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.vo.ofcflow.DraftServantVO;

/** 
 * @ClassName: DraftServantService 
 * @Description: 录用信息管理 服务接口
 * @author: GP
 * @date: 2018年6月26日 上午10:05:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DraftServantService extends GenericService<DraftServant>{
	public void saveBatchDraftServant(List<DraftServant> list);
	
	public List<DraftServant> findByCondMap(Map condition);
	
	public Page<DraftServantVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize);
	
	/**
	 * @Title: savePublishByRecordId 
	 * @Description: 将导入数据修改为发布
	 * @param id
	 * @return: void
	 */
	public void savePublishByRecordId(String id);
	/**
	 * @Title: updateCollectById 
	 * @Description: 报送汇总
	 * @param id	如果id中有逗号，批量报送汇总
	 * @param status	汇总状态
	 * @return: void
	 */
	public void updateCollectById(String id,int status);
	
	/**
	 * 
	 * @Title: getOrganIdByUnitId 
	 * @Description: 通过unitid查询对应的organnode的id
	 * @param unitId
	 * @return
	 * @return: String
	 */
	public String getUnitIdByOrganId(String organId);
}
