/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenKLBOutMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 跨类别转任 转出情况信息 服务实现类
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
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOut;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBOutService;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenKLBOutVO;

/** 
 * @ClassName: ZhuanRenKLBOutMgrServiceImpl 
 * @Description: 跨类别转任 转出情况信息 服务实现类
 * @author: tzy
 * @date: 2018年8月6日 下午3:00:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ZhuanRenKLBOutServiceImpl extends GenericServiceImpl<ZhuanRenKLBOut> implements ZhuanRenKLBOutService{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ZhuanRenKLBIntoMgrVO>
	 */
	public Page<ZhuanRenKLBOutVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<ZhuanRenKLBOut> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ZhuanRenKLBOutVO> voList = new ArrayList<>();
		for(ZhuanRenKLBOut s:temppage.getResult()){
			ZhuanRenKLBOutVO vo = new ZhuanRenKLBOutVO(s);
			voList.add(vo);
		}
		Page<ZhuanRenKLBOutVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/** (non Javadoc) 
	 * @see com.wondersgroup.human.service.ofcflow.ZhuanRenTLBOutService#deleteServant(java.lang.String) 
	 */
	@Override
	public void deleteServant(String id) {
		if(id.contains(",")){//批量删除
			for(String i : id.split(",")){
				ZhuanRenKLBOut into = get(i);
				delete(into);
			}
		}else{
			ZhuanRenKLBOut into = get(id);
			delete(into);
		}
	}
}
