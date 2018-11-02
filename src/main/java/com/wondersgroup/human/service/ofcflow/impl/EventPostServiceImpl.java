/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EventPostServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 流程信息 职务 临时表 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月31日 下午2:06:14 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月31日 下午2:06:14 
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
import com.wondersgroup.human.bo.ofcflow.EventPost;
import com.wondersgroup.human.service.ofcflow.EventPostService;
import com.wondersgroup.human.vo.ofcflow.EventPostVO;

/** 
 * @ClassName: EventPostServiceImpl 
 * @Description: 流程信息 职务 临时表 服务实现类
 * @author: tzy
 * @date: 2018年7月31日 下午2:06:14
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class EventPostServiceImpl extends GenericServiceImpl<EventPost> implements EventPostService{
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
	public Page<EventPostVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<EventPost> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<EventPostVO> voList = new ArrayList<>();
		for(EventPost s:temppage.getResult()){
			EventPostVO vo = new EventPostVO(s);
			voList.add(vo);
		}
		Page<EventPostVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
}
