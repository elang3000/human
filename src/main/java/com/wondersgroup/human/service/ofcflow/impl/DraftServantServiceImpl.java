/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 录用信息管理 服务实现类
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:09:53 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:09:53 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofc.Employ;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.repository.ofcflow.DraftServantDAO;
import com.wondersgroup.human.service.ofc.EmployService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.vo.ofcflow.DraftServantVO;

/** 
 * @ClassName: DraftServantServiceImpl 
 * @Description: 录用信息管理 服务实现类
 * @author: GP
 * @date: 2018年6月26日 上午10:09:53
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DraftServantServiceImpl extends GenericServiceImpl<DraftServant> implements DraftServantService{
	@Autowired
	private DraftServantDAO draftServantDAO;

	/** (non Javadoc) 
	 * @Title: saveBatch
	 * @Description: TODO
	 * @param list 
	 * @see com.wondersgroup.human.service.ofcflow.DraftServantService#saveBatch(java.util.List) 
	 */
	@Override
	public void saveBatchDraftServant(List<DraftServant> list) {
		for (DraftServant draftServant : list) {
			save(draftServant);
		}
	}

	/** (non Javadoc) 
	 * @Title: findByCondMap
	 * @Description: TODO
	 * @param condition
	 * @return 
	 * @see com.wondersgroup.human.service.ofcflow.DraftServantService#findByCondMap(java.util.Map) 
	 */
	@Override
	public List<DraftServant> findByCondMap(Map condition) {
		
		return draftServantDAO.findByCondMap(condition);
	}

	/** (non Javadoc) 
	 * @Title: findbyHQLforVO
	 * @Description: TODO
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return 
	 * @see com.wondersgroup.human.service.ofcflow.DraftServantService#findbyHQLforVO(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<DraftServantVO> findbyHQLforVO(String hql, List<QueryParameter> listqueryparametry, Integer pageNo,
	        Integer pagesize) {
		
		Page<DraftServant> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<DraftServantVO> voList = new ArrayList<>();
		for(DraftServant s:temppage.getResult()){
			DraftServantVO vo = new DraftServantVO(s);
			voList.add(vo);
		}
		Page<DraftServantVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	
	/**
	 * @Title: savePublishByRecordId 
	 * @Description: 将导入数据修改为发布
	 * @param id
	 * @return: void
	 */
	public void savePublishByRecordId(String id){
		draftServantDAO.publishByRecordId(id);
	}
	/**
	 * @Title: updateCollectById 
	 * @Description: 报送汇总
	 * @param id	如果id中有逗号，批量报送汇总
	 * @param status	汇总状态
	 * @return: void
	 */
	public void updateCollectById(String id,int status){
		if(id.contains(",")){
			String[] ids = id.split(",");
			for (String i : ids) {
				DraftServant ds = load(i);
				ds.setStatus(status);
				update(ds);
			}
		}else{
			DraftServant ds = load(id);
			ds.setStatus(status);
			update(ds);
		}
	}

	@Override
	public String getUnitIdByOrganId(String organId) {
		return this.draftServantDAO.getUnitIdByOrganId(organId);
	}
	
}
