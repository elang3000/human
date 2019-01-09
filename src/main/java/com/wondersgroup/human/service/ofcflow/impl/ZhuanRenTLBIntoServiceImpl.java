/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBIntoMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 同类别转任 调入情况信息 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月1日 上午11:00:49 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月1日 上午11:00:49 
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
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto;
import com.wondersgroup.human.service.ofcflow.ZhuanRenTLBIntoService;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBIntoVO;

/** 
 * @ClassName: ZhuanRenTLBIntoMgrServiceImpl 
 * @Description: 同类别转任 转入情况信息 服务实现类
 * @author: tzy
 * @date: 2018年8月1日 上午11:00:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ZhuanRenTLBIntoServiceImpl extends GenericServiceImpl<ZhuanRenTLBInto> implements ZhuanRenTLBIntoService{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ZhuanRenTLBIntoMgrVO>
	 */
	public Page<ZhuanRenTLBIntoVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<ZhuanRenTLBInto> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ZhuanRenTLBIntoVO> voList = new ArrayList<>();
		for(ZhuanRenTLBInto s:temppage.getResult()){
			ZhuanRenTLBIntoVO vo = new ZhuanRenTLBIntoVO(s);
			voList.add(vo);
		}
		Page<ZhuanRenTLBIntoVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}

	/** (non Javadoc) 
	 * @see com.wondersgroup.human.service.ofcflow.ZhuanRenTLBIntoService#deleteServant(java.lang.String) 
	 */
	@Override
	public void deleteServant(String id) {
		if(id.contains(",")){//批量删除
			for(String i : id.split(",")){
				ZhuanRenTLBInto into = get(i);
				delete(into);
			}
		}else{
			ZhuanRenTLBInto into = get(id);
			delete(into);
		}
	}
	
}
