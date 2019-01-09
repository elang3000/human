/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DiaoRenOutMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 公务员调任 转出情况信息 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月6日 下午3:00:34 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月6日 下午3:00:34 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.DiaoRenOut;
import com.wondersgroup.human.service.ofcflow.DiaoRenOutService;
import com.wondersgroup.human.vo.ofcflow.DiaoRenOutVO;

/** 
 * @ClassName: DiaoRenOutMgrServiceImpl 
 * @Description: 公务员调任 转出情况信息 服务实现类
 * @author: tzy
 * @date: 2018年8月6日 下午3:00:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DiaoRenOutServiceImpl extends GenericServiceImpl<DiaoRenOut> implements DiaoRenOutService{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<DiaoRenIntoMgrVO>
	 */
	public Page<DiaoRenOutVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<DiaoRenOut> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<DiaoRenOutVO> voList = new ArrayList<>();
		for(DiaoRenOut s:temppage.getResult()){
			DiaoRenOutVO vo = new DiaoRenOutVO(s);
			voList.add(vo);
		}
		Page<DiaoRenOutVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}

	/** (non Javadoc) 
	 * @see com.wondersgroup.human.service.ofcflow.DiaoRenOutService#deleteServant(java.lang.String) 
	 */
	@Override
	public void deleteServant(String id) {
		if(id.contains(",")){//批量删除
			for(String i : id.split(",")){
				DiaoRenOut into = get(i);
				delete(into);
			}
		}else{
			DiaoRenOut into = get(id);
			delete(into);
		}
	}
	
}
